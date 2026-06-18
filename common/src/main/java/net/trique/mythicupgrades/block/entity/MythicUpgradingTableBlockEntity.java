package net.trique.mythicupgrades.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.WorldlyContainer;
import net.trique.mythicupgrades.item.MythicItems;
import net.trique.mythicupgrades.menu.MythicUpgradingTableMenu;
import net.trique.mythicupgrades.recipe.MythicUpgradingTableRecipe;
import net.trique.mythicupgrades.registry.MythicBlockEntityTypes;
import net.trique.mythicupgrades.registry.MythicRecipeTypes;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class MythicUpgradingTableBlockEntity extends BlockEntity implements WorldlyContainer, MenuProvider {

    // Slot indices
    public static final int SLOT_NECOIUM_INGOT = 0;
    public static final int SLOT_FUEL          = 1;
    public static final int SLOT_BASE          = 2;
    public static final int SLOT_ADDITION1     = 3;
    public static final int SLOT_ADDITION2     = 4;
    public static final int SLOT_RESULT        = 5;
    public static final int NUM_SLOTS          = 6;

    // ContainerData indices
    private static final int DATA_BURN_TIME     = 0;
    private static final int DATA_BURN_DURATION = 1;
    private static final int DATA_COOK_TIME     = 2;
    private static final int DATA_NECOIUM_FUEL  = 3;
    private static final int NUM_DATA_VALUES    = 4;

    public static final int COOK_TIME_TOTAL     = 200;
    public static final int MAX_NECOIUM_FUEL    = 20;
    public static final int NECOIUM_PER_SMELT   = 4;

    private net.minecraft.core.NonNullList<ItemStack> items =
            net.minecraft.core.NonNullList.withSize(NUM_SLOTS, ItemStack.EMPTY);

    // Fuel state
    private int burnTime     = 0;  // remaining ticks of current fuel
    private int burnDuration = 0;  // total ticks of fuel item that started current burn
    private int cookTime     = 0;  // progress toward smelting necoium ingot (0..200)
    private int necoiumFuel  = 0;  // stored necoium fuel units (0..20)

    private final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case DATA_BURN_TIME     -> burnTime;
                case DATA_BURN_DURATION -> burnDuration;
                case DATA_COOK_TIME     -> cookTime;
                case DATA_NECOIUM_FUEL  -> necoiumFuel;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case DATA_BURN_TIME     -> burnTime     = value;
                case DATA_BURN_DURATION -> burnDuration = value;
                case DATA_COOK_TIME     -> cookTime     = value;
                case DATA_NECOIUM_FUEL  -> necoiumFuel  = value;
            }
        }

        @Override
        public int getCount() { return NUM_DATA_VALUES; }
    };

    public MythicUpgradingTableBlockEntity(BlockPos pos, BlockState state) {
        super(MythicBlockEntityTypes.UPGRADING_TABLE, pos, state);
    }

    // -------------------------------------------------------------------------
    // Ticker
    // -------------------------------------------------------------------------

    public static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level level) {
        if (level.isClientSide) return null;
        return (lvl, pos, state, be) -> {
            if (be instanceof MythicUpgradingTableBlockEntity entity) {
                entity.tick(lvl, pos, state);
            }
        };
    }

    private void tick(Level level, BlockPos pos, BlockState state) {
        boolean wasBurning = this.isBurning();

        // --- Fuel consumption ---
        if (this.isBurning()) {
            burnTime--;
        }

        // Try to start new burn if not burning and there is fuel in slot 1
        if (!this.isBurning()) {
            ItemStack fuelStack = items.get(SLOT_FUEL);
            if (!fuelStack.isEmpty()) {
                int bt = getBurnDurationForStack(fuelStack);
                if (bt > 0) {
                    burnDuration = bt;
                    burnTime = bt;
                    // Consume one item from fuel slot
                    if (fuelStack.getItem() == net.minecraft.world.item.Items.LAVA_BUCKET) {
                        items.set(SLOT_FUEL, new ItemStack(net.minecraft.world.item.Items.BUCKET));
                    } else {
                        fuelStack.shrink(1);
                        if (fuelStack.isEmpty()) {
                            items.set(SLOT_FUEL, ItemStack.EMPTY);
                        }
                    }
                    setChanged();
                }
            }
        }

        // --- Necoium ingot smelting ---
        if (this.isBurning()) {
            ItemStack necoiumStack = items.get(SLOT_NECOIUM_INGOT);
            boolean canSmelt = !necoiumStack.isEmpty()
                    && necoiumStack.is(MythicItems.NECOIUM_INGOT)
                    && necoiumFuel < MAX_NECOIUM_FUEL;

            if (canSmelt) {
                cookTime++;
                if (cookTime >= COOK_TIME_TOTAL) {
                    cookTime = 0;
                    necoiumStack.shrink(1);
                    if (necoiumStack.isEmpty()) {
                        items.set(SLOT_NECOIUM_INGOT, ItemStack.EMPTY);
                    }
                    necoiumFuel = Math.min(MAX_NECOIUM_FUEL, necoiumFuel + NECOIUM_PER_SMELT);
                    setChanged();
                }
            } else {
                cookTime = 0;
            }
        } else {
            cookTime = 0;
        }

        // --- Upgrade result calculation ---
        updateResult(level);

        if (wasBurning != this.isBurning()) {
            setChanged();
        }
    }

    private void updateResult(Level level) {
        ItemStack baseStack    = items.get(SLOT_BASE);
        ItemStack addStack     = items.get(SLOT_ADDITION1);
        ItemStack crystalStack = items.get(SLOT_ADDITION2);

        ItemStack newResult;
        if (baseStack.isEmpty() || addStack.isEmpty() || crystalStack.isEmpty()) {
            newResult = ItemStack.EMPTY;
        } else {
            SimpleContainer container = new SimpleContainer(NUM_SLOTS);
            for (int i = 0; i < NUM_SLOTS; i++) container.setItem(i, items.get(i));

            Optional<MythicUpgradingTableRecipe> recipe = level.getRecipeManager()
                    .getRecipeFor(MythicRecipeTypes.UPGRADING, container, level);

            newResult = (recipe.isPresent() && necoiumFuel >= 1)
                    ? recipe.get().getResultItem(level.registryAccess()).copy()
                    : ItemStack.EMPTY;
        }

        if (!ItemStack.matches(items.get(SLOT_RESULT), newResult)) {
            items.set(SLOT_RESULT, newResult);
            setChanged();
        }
    }

    // Called by the menu when the player takes the result
    public void consumeUpgradeInputs() {
        items.get(SLOT_BASE).shrink(1);
        if (items.get(SLOT_BASE).isEmpty()) items.set(SLOT_BASE, ItemStack.EMPTY);
        items.get(SLOT_ADDITION1).shrink(1);
        if (items.get(SLOT_ADDITION1).isEmpty()) items.set(SLOT_ADDITION1, ItemStack.EMPTY);
        items.get(SLOT_ADDITION2).shrink(1);
        if (items.get(SLOT_ADDITION2).isEmpty()) items.set(SLOT_ADDITION2, ItemStack.EMPTY);
        necoiumFuel--;
        if (level != null && !level.isClientSide) {
            updateResult(level);
        }
        setChanged();
    }

    private boolean isBurning() {
        return burnTime > 0;
    }

    private int getBurnDurationForStack(ItemStack stack) {
        if (stack.isEmpty()) return 0;
        // Use vanilla fuel map (works on both Forge and Fabric through mixin)
        return net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity.getFuel()
                .getOrDefault(stack.getItem(), 0);
    }

    // -------------------------------------------------------------------------
    // Container
    // -------------------------------------------------------------------------

    @Override
    public int getContainerSize() {
        return NUM_SLOTS;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return ContainerHelper.removeItem(items, slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        items.set(slot, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
        if ((slot == SLOT_BASE || slot == SLOT_ADDITION1 || slot == SLOT_ADDITION2)
                && level != null && !level.isClientSide) {
            updateResult(level);
        }
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        if (level == null) return false;
        if (level.getBlockEntity(worldPosition) != this) return false;
        return player.distanceToSqr(worldPosition.getX() + 0.5, worldPosition.getY() + 0.5,
                worldPosition.getZ() + 0.5) <= 64.0;
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    // -------------------------------------------------------------------------
    // WorldlyContainer
    // -------------------------------------------------------------------------

    private static final int[] SLOTS_FOR_UP    = { SLOT_NECOIUM_INGOT };
    private static final int[] SLOTS_FOR_SIDE  = { SLOT_FUEL };
    private static final int[] SLOTS_FOR_DOWN  = { SLOT_RESULT };

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) return SLOTS_FOR_DOWN;
        if (side == Direction.UP)   return SLOTS_FOR_UP;
        return SLOTS_FOR_SIDE;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
        return canPlaceItem(slot, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
        return slot == SLOT_RESULT;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return switch (slot) {
            case SLOT_NECOIUM_INGOT -> stack.is(MythicItems.NECOIUM_INGOT);
            case SLOT_FUEL          -> getBurnDurationForStack(stack) > 0;
            case SLOT_BASE          -> {
                // accepts items whose registration name starts with "netherite_"
                String path = net.minecraft.core.registries.BuiltInRegistries.ITEM
                        .getKey(stack.getItem()).getPath();
                yield path.startsWith("netherite_");
            }
            case SLOT_ADDITION1, SLOT_ADDITION2 -> true;
            case SLOT_RESULT -> false;
            default -> false;
        };
    }

    // -------------------------------------------------------------------------
    // MenuProvider
    // -------------------------------------------------------------------------

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.mythicupgrades.upgrading_table");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInv, Player player) {
        return new MythicUpgradingTableMenu(containerId, playerInv,
                ContainerLevelAccess.create(level, worldPosition), dataAccess);
    }

    // -------------------------------------------------------------------------
    // NBT persistence
    // -------------------------------------------------------------------------

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, items);
        tag.putInt("BurnTime",     burnTime);
        tag.putInt("BurnDuration", burnDuration);
        tag.putInt("CookTime",     cookTime);
        tag.putInt("NecoiumFuel",  necoiumFuel);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        items = net.minecraft.core.NonNullList.withSize(NUM_SLOTS, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, items);
        burnTime     = tag.getInt("BurnTime");
        burnDuration = tag.getInt("BurnDuration");
        cookTime     = tag.getInt("CookTime");
        necoiumFuel  = tag.getInt("NecoiumFuel");
    }

    public ContainerData getContainerData() {
        return dataAccess;
    }
}
