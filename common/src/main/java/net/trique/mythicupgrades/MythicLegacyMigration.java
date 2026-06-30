package net.trique.mythicupgrades;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;
import net.trique.mythicupgrades.item.MythicItems;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MythicLegacyMigration {

    public static final Queue<LevelChunk> PENDING_CHUNKS = new ConcurrentLinkedQueue<>();

    private static final Map<Item, Potion> POTION_MAP   = new IdentityHashMap<>();
    private static final Map<Item, Item>   TEMPLATE_MAP = new IdentityHashMap<>();

    public static void init() {
        POTION_MAP.put(MythicItems.LEGACY_AQUAMARINE_POTION, MythicPotions.ICE_SHIELD);
        POTION_MAP.put(MythicItems.LEGACY_CITRINE_POTION,    MythicPotions.STATIC_FIELD);
        POTION_MAP.put(MythicItems.LEGACY_TOPAZ_POTION,      MythicPotions.TOPAZ_REACTION);
        POTION_MAP.put(MythicItems.LEGACY_PERIDOT_POTION,    MythicPotions.MIASMA);
        POTION_MAP.put(MythicItems.LEGACY_RUBY_POTION,       MythicPotions.BLOOD_THIRST);
        POTION_MAP.put(MythicItems.LEGACY_SAPPHIRE_POTION,   MythicPotions.DAMAGE_DEFLECTION);
        POTION_MAP.put(MythicItems.LEGACY_JADE_POTION,       MythicPotions.JADE_AURA);
        POTION_MAP.put(MythicItems.LEGACY_AMETRINE_POTION,   MythicPotions.ARCANE_AURA);

        TEMPLATE_MAP.put(MythicItems.LEGACY_AQUAMARINE_TEMPLATE, MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE);
        TEMPLATE_MAP.put(MythicItems.LEGACY_CITRINE_TEMPLATE,    MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE);
        TEMPLATE_MAP.put(MythicItems.LEGACY_TOPAZ_TEMPLATE,      MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE);
        TEMPLATE_MAP.put(MythicItems.LEGACY_PERIDOT_TEMPLATE,    MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE);
        TEMPLATE_MAP.put(MythicItems.LEGACY_RUBY_TEMPLATE,       MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE);
        TEMPLATE_MAP.put(MythicItems.LEGACY_SAPPHIRE_TEMPLATE,   MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE);
        TEMPLATE_MAP.put(MythicItems.LEGACY_JADE_TEMPLATE,       MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE);
        TEMPLATE_MAP.put(MythicItems.LEGACY_AMETRINE_TEMPLATE,   MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE);
    }

    public static void drainPendingChunks() {
        LevelChunk chunk;
        while ((chunk = PENDING_CHUNKS.poll()) != null) {
            migrateChunk(chunk);
        }
    }

    public static void migratePlayer(Player player) {
        boolean changed = false;
        changed |= replaceIn(player.getInventory().items);
        changed |= replaceIn(player.getInventory().armor);
        changed |= replaceIn(player.getInventory().offhand);
        if (changed) {
            player.getInventory().setChanged();
            Constants.LOG.info("Migrated legacy items for player {}.", player.getName().getString());
        }
    }

    public static void migrateChunk(LevelChunk chunk) {
        try {
            List<BlockEntity> entities = new ArrayList<>(chunk.getBlockEntities().values());
            for (BlockEntity be : entities) {
                if (be == null || !(be instanceof Container container)) continue;
                try {
                    boolean changed = false;
                    for (int i = 0; i < container.getContainerSize(); i++) {
                        ItemStack stack = container.getItem(i);
                        if (stack.isEmpty()) continue;
                        ItemStack replacement = replace(stack);
                        if (replacement != null) {
                            container.setItem(i, replacement);
                            changed = true;
                        }
                    }
                    if (changed) be.setChanged();
                } catch (Exception e) {
                    Constants.LOG.warn("MythicLegacyMigration: skipped block entity at {} ({})", be.getBlockPos(), e.getMessage());
                }
            }
        } catch (Exception e) {
            Constants.LOG.warn("MythicLegacyMigration: failed to migrate chunk {} ({})", chunk.getPos(), e.getMessage());
        }
    }

    private static boolean replaceIn(List<ItemStack> slots) {
        boolean changed = false;
        for (int i = 0; i < slots.size(); i++) {
            ItemStack stack = slots.get(i);
            if (stack.isEmpty()) continue;
            ItemStack replacement = replace(stack);
            if (replacement != null) {
                slots.set(i, replacement);
                changed = true;
            }
        }
        return changed;
    }

    public static ItemStack replace(ItemStack stack) {
        Item item = stack.getItem();
        Potion newPotion = POTION_MAP.get(item);
        if (newPotion != null) {
            ItemStack result = new ItemStack(Items.POTION, stack.getCount());
            result.set(DataComponents.POTION_CONTENTS, new PotionContents(BuiltInRegistries.POTION.wrapAsHolder(newPotion)));
            return result;
        }
        Item newItem = TEMPLATE_MAP.get(item);
        if (newItem != null) {
            return new ItemStack(newItem, stack.getCount());
        }
        return null;
    }
}
