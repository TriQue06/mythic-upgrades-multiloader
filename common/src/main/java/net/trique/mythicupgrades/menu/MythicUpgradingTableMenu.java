package net.trique.mythicupgrades.menu;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.block.entity.MythicUpgradingTableBlockEntity;
import net.trique.mythicupgrades.item.MythicItems;
import net.trique.mythicupgrades.registry.MythicMenuTypes;

import java.util.Objects;

public class MythicUpgradingTableMenu extends AbstractContainerMenu {

    private final Container container;
    private final ContainerData data;
    private final ContainerLevelAccess access;

    // Slot pixel positions matching the GUI layout
    private static final int SLOT_NECOIUM_X = 16,  SLOT_NECOIUM_Y = 15;
    private static final int SLOT_FUEL_X    = 16,  SLOT_FUEL_Y    = 53;
    private static final int SLOT_BASE_X    = 53,  SLOT_BASE_Y    = 47;
    private static final int SLOT_ADD1_X    = 71,  SLOT_ADD1_Y    = 35;
    private static final int SLOT_ADD2_X    = 71,  SLOT_ADD2_Y    = 57;
    private static final int SLOT_RESULT_X  = 114, SLOT_RESULT_Y  = 42;

    // Player inventory layout
    private static final int PLAYER_INV_START_X = 8;
    private static final int PLAYER_INV_START_Y = 84;
    private static final int PLAYER_HOTBAR_Y    = 142;

    /** Client-side factory used by MenuType registration (no ContainerData available yet). */
    public static MythicUpgradingTableMenu createClientMenu(int containerId, Inventory playerInv) {
        return new MythicUpgradingTableMenu(containerId, playerInv, ContainerLevelAccess.NULL, new SimpleContainerData(4));
    }

    public MythicUpgradingTableMenu(int containerId, Inventory playerInv,
                                     ContainerLevelAccess access, ContainerData data) {
        super(MythicMenuTypes.UPGRADING_TABLE, containerId);
        this.access = access;
        this.data = data;

        // Determine the container from access or create a dummy
        Container[] containerRef = { null };
        access.execute((level, pos) -> {
            if (level.getBlockEntity(pos) instanceof MythicUpgradingTableBlockEntity be) {
                containerRef[0] = be;
            }
        });
        this.container = Objects.requireNonNullElseGet(containerRef[0],
                () -> new SimpleContainer(MythicUpgradingTableBlockEntity.NUM_SLOTS));
        this.container.startOpen(playerInv.player);

        // --- Block entity slots ---

        // Slot 0: necoium ingot
        addSlot(new Slot(container, MythicUpgradingTableBlockEntity.SLOT_NECOIUM_INGOT,
                SLOT_NECOIUM_X, SLOT_NECOIUM_Y) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(MythicItems.NECOIUM_INGOT);
            }
        });

        // Slot 1: fuel
        addSlot(new Slot(container, MythicUpgradingTableBlockEntity.SLOT_FUEL,
                SLOT_FUEL_X, SLOT_FUEL_Y) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity
                        .getFuel().containsKey(stack.getItem());
            }
        });

        // Slot 2: base (netherite_* items)
        addSlot(new Slot(container, MythicUpgradingTableBlockEntity.SLOT_BASE,
                SLOT_BASE_X, SLOT_BASE_Y) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                String path = net.minecraft.core.registries.BuiltInRegistries.ITEM
                        .getKey(stack.getItem()).getPath();
                return path.startsWith("netherite_");
            }
        });

        // Slot 3: gem addition
        addSlot(new Slot(container, MythicUpgradingTableBlockEntity.SLOT_ADDITION1,
                SLOT_ADD1_X, SLOT_ADD1_Y));

        // Slot 4: crystal shard
        addSlot(new Slot(container, MythicUpgradingTableBlockEntity.SLOT_ADDITION2,
                SLOT_ADD2_X, SLOT_ADD2_Y));

        // Slot 5: result (output-only, not player-insertable, consume inputs on take)
        addSlot(new Slot(container, MythicUpgradingTableBlockEntity.SLOT_RESULT,
                SLOT_RESULT_X, SLOT_RESULT_Y) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                access.execute((level, pos) -> {
                    if (level.getBlockEntity(pos) instanceof MythicUpgradingTableBlockEntity be) {
                        be.consumeUpgradeInputs();
                    }
                    level.playSound(null, pos, SoundEvents.SMITHING_TABLE_USE,
                            SoundSource.BLOCKS, 1.0F, 1.0F);
                });
                super.onTake(player, stack);
            }
        });

        // --- Player inventory slots (rows 0-2) ---
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInv, col + row * 9 + 9,
                        PLAYER_INV_START_X + col * 18, PLAYER_INV_START_Y + row * 18));
            }
        }

        // --- Player hotbar slots ---
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInv, col,
                    PLAYER_INV_START_X + col * 18, PLAYER_HOTBAR_Y));
        }

        addDataSlots(data);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(access, player, MythicBlocks.MYTHIC_UPGRADING_TABLE);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemstack = slotStack.copy();
            if (index == MythicUpgradingTableBlockEntity.SLOT_RESULT) {
                // Result → player inventory
                if (!moveItemStackTo(slotStack, 6, 42, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(slotStack, itemstack);
            } else if (index < 6) {
                // Block entity slots → player inventory
                if (!moveItemStackTo(slotStack, 6, 42, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Player inventory → try to put in block entity slots (0-4, not result)
                if (!moveItemStackTo(slotStack, 0, 5, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
            if (slotStack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return itemstack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.container.stopOpen(player);
    }

    // -------------------------------------------------------------------------
    // Accessors for the screen
    // -------------------------------------------------------------------------

    public int getBurnTime()     { return data.get(0); }
    public int getBurnDuration() { return data.get(1); }
    public int getCookTime()     { return data.get(2); }
    public int getNecoiumFuel()  { return data.get(3); }

    public boolean isBurning()   { return getBurnTime() > 0; }

    /** Returns 0..13 for fire animation height (like furnace). */
    public int getBurnProgress() {
        int bt = getBurnTime();
        int bd = getBurnDuration();
        if (bd == 0) return 0;
        return bt * 13 / bd;
    }

    /** Returns 0..24 for arrow width. */
    public int getCookProgress() {
        int ct = getCookTime();
        if (ct == 0) return 0;
        return ct * 24 / MythicUpgradingTableBlockEntity.COOK_TIME_TOTAL;
    }

    /** Returns bar pixel fill width 0..60 (left-to-right, matches 60px bar in GUI). */
    public int getNecoiumBarWidth() {
        int fuel = getNecoiumFuel();
        return fuel * 60 / MythicUpgradingTableBlockEntity.MAX_NECOIUM_FUEL;
    }
}
