package net.trique.mythicupgrades;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.item.MythicItems;

import java.util.function.BiFunction;

public class MythicCreativeTabs {

    public static CreativeModeTab ITEMS_TAB;
    public static CreativeModeTab BLOCKS_TAB;
    public static CreativeModeTab GEAR_TAB;

    private static ItemStack potion(Item potionItem, Potion p) {
        ItemStack s = new ItemStack(potionItem);
        s.set(DataComponents.POTION_CONTENTS, new PotionContents(BuiltInRegistries.POTION.wrapAsHolder(p)));
        return s;
    }

    private static void addPotionGroup(CreativeModeTab.Output output, Potion normal, Potion extended, Potion strong) {
        for (Potion p : new Potion[]{normal, extended, strong}) {
            if (p == null) continue;
            output.accept(potion(Items.POTION, p));
            output.accept(potion(Items.SPLASH_POTION, p));
            output.accept(potion(Items.LINGERING_POTION, p));
            output.accept(potion(Items.TIPPED_ARROW, p));
        }
    }

    public static void register(BiFunction<String, CreativeModeTab, CreativeModeTab> reg) {
        ITEMS_TAB = reg.apply("items",
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .title(Component.translatable("itemGroup.mythicupgrades.items"))
                .icon(() -> new ItemStack(MythicItems.AMETRINE_INGOT))
                .displayItems((params, output) -> {
                    output.accept(MythicItems.AQUAMARINE);
                    output.accept(MythicItems.AQUAMARINE_INGOT);
                    output.accept(MythicItems.AQUAMARINE_CRYSTAL_SHARD);
                    output.accept(MythicItems.CITRINE);
                    output.accept(MythicItems.CITRINE_INGOT);
                    output.accept(MythicItems.CITRINE_CRYSTAL_SHARD);
                    output.accept(MythicItems.TOPAZ);
                    output.accept(MythicItems.TOPAZ_INGOT);
                    output.accept(MythicItems.TOPAZ_CRYSTAL_SHARD);
                    output.accept(MythicItems.PERIDOT);
                    output.accept(MythicItems.PERIDOT_INGOT);
                    output.accept(MythicItems.PERIDOT_CRYSTAL_SHARD);
                    output.accept(MythicItems.RUBY);
                    output.accept(MythicItems.RUBY_INGOT);
                    output.accept(MythicItems.RUBY_CRYSTAL_SHARD);
                    output.accept(MythicItems.SAPPHIRE);
                    output.accept(MythicItems.SAPPHIRE_INGOT);
                    output.accept(MythicItems.SAPPHIRE_CRYSTAL_SHARD);
                    output.accept(MythicItems.JADE);
                    output.accept(MythicItems.JADE_INGOT);
                    output.accept(MythicItems.JADE_CRYSTAL_SHARD);
                    output.accept(MythicItems.AMETRINE);
                    output.accept(MythicItems.AMETRINE_INGOT);
                    output.accept(MythicItems.AMETRINE_CRYSTAL_SHARD);
                    output.accept(MythicItems.RAW_NECOIUM);
                    output.accept(MythicItems.NECOIUM_INGOT);
                    output.accept(MythicItems.NECOIUM_CARROT);
                    output.accept(MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE);
                    // Potions — regular, splash, lingering for each type (matches 1.20.1)
                    addPotionGroup(output, MythicPotions.ICE_SHIELD,        MythicPotions.ICE_SHIELD_LONG,        MythicPotions.ICE_SHIELD_STRONG);
                    addPotionGroup(output, MythicPotions.STATIC_FIELD,      MythicPotions.STATIC_FIELD_LONG,      MythicPotions.STATIC_FIELD_STRONG);
                    addPotionGroup(output, MythicPotions.TOPAZ_REACTION,    MythicPotions.TOPAZ_REACTION_LONG,    MythicPotions.TOPAZ_REACTION_STRONG);
                    addPotionGroup(output, MythicPotions.MIASMA,            MythicPotions.MIASMA_LONG,            MythicPotions.MIASMA_STRONG);
                    addPotionGroup(output, MythicPotions.BLOOD_THIRST,      MythicPotions.BLOOD_THIRST_LONG,      MythicPotions.BLOOD_THIRST_STRONG);
                    addPotionGroup(output, MythicPotions.DAMAGE_DEFLECTION, MythicPotions.DAMAGE_DEFLECTION_LONG, MythicPotions.DAMAGE_DEFLECTION_STRONG);
                    addPotionGroup(output, MythicPotions.JADE_AURA,         MythicPotions.JADE_AURA_LONG,         MythicPotions.JADE_AURA_STRONG);
                    addPotionGroup(output, MythicPotions.ARCANE_AURA,       MythicPotions.ARCANE_AURA_LONG,       MythicPotions.ARCANE_AURA_STRONG);
                    addPotionGroup(output, MythicPotions.NECOIUM_SHARE,     MythicPotions.NECOIUM_SHARE_LONG,     null);
                    // Negative potions
                    addPotionGroup(output, MythicPotions.ICE_BOMB,          MythicPotions.ICE_BOMB_LONG,          MythicPotions.ICE_BOMB_STRONG);
                    addPotionGroup(output, MythicPotions.FREEZE,            MythicPotions.FREEZE_LONG,            MythicPotions.FREEZE_STRONG);
                    addPotionGroup(output, MythicPotions.CHARGED,           MythicPotions.CHARGED_LONG,           MythicPotions.CHARGED_STRONG);
                    addPotionGroup(output, MythicPotions.LETHAL_INCUBATION, MythicPotions.LETHAL_INCUBATION_LONG, MythicPotions.LETHAL_INCUBATION_STRONG);
                })
                .build()
        );

        GEAR_TAB = reg.apply("gear",
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 2)
                .title(Component.translatable("itemGroup.mythicupgrades.gear"))
                .icon(() -> new ItemStack(MythicItems.AMETRINE_AXE))
                .displayItems((params, output) -> {
                    output.accept(MythicItems.AQUAMARINE_SWORD);
                    output.accept(MythicItems.AQUAMARINE_PICKAXE);
                    output.accept(MythicItems.AQUAMARINE_AXE);
                    output.accept(MythicItems.AQUAMARINE_SHOVEL);
                    output.accept(MythicItems.AQUAMARINE_HOE);
                    output.accept(MythicItems.AQUAMARINE_HELMET);
                    output.accept(MythicItems.AQUAMARINE_CHESTPLATE);
                    output.accept(MythicItems.AQUAMARINE_LEGGINGS);
                    output.accept(MythicItems.AQUAMARINE_BOOTS);
                    output.accept(MythicItems.CITRINE_SWORD);
                    output.accept(MythicItems.CITRINE_PICKAXE);
                    output.accept(MythicItems.CITRINE_AXE);
                    output.accept(MythicItems.CITRINE_SHOVEL);
                    output.accept(MythicItems.CITRINE_HOE);
                    output.accept(MythicItems.CITRINE_HELMET);
                    output.accept(MythicItems.CITRINE_CHESTPLATE);
                    output.accept(MythicItems.CITRINE_LEGGINGS);
                    output.accept(MythicItems.CITRINE_BOOTS);
                    output.accept(MythicItems.TOPAZ_SWORD);
                    output.accept(MythicItems.TOPAZ_PICKAXE);
                    output.accept(MythicItems.TOPAZ_AXE);
                    output.accept(MythicItems.TOPAZ_SHOVEL);
                    output.accept(MythicItems.TOPAZ_HOE);
                    output.accept(MythicItems.TOPAZ_HELMET);
                    output.accept(MythicItems.TOPAZ_CHESTPLATE);
                    output.accept(MythicItems.TOPAZ_LEGGINGS);
                    output.accept(MythicItems.TOPAZ_BOOTS);
                    output.accept(MythicItems.PERIDOT_SWORD);
                    output.accept(MythicItems.PERIDOT_PICKAXE);
                    output.accept(MythicItems.PERIDOT_AXE);
                    output.accept(MythicItems.PERIDOT_SHOVEL);
                    output.accept(MythicItems.PERIDOT_HOE);
                    output.accept(MythicItems.PERIDOT_HELMET);
                    output.accept(MythicItems.PERIDOT_CHESTPLATE);
                    output.accept(MythicItems.PERIDOT_LEGGINGS);
                    output.accept(MythicItems.PERIDOT_BOOTS);
                    output.accept(MythicItems.RUBY_SWORD);
                    output.accept(MythicItems.RUBY_PICKAXE);
                    output.accept(MythicItems.RUBY_AXE);
                    output.accept(MythicItems.RUBY_SHOVEL);
                    output.accept(MythicItems.RUBY_HOE);
                    output.accept(MythicItems.RUBY_HELMET);
                    output.accept(MythicItems.RUBY_CHESTPLATE);
                    output.accept(MythicItems.RUBY_LEGGINGS);
                    output.accept(MythicItems.RUBY_BOOTS);
                    output.accept(MythicItems.SAPPHIRE_SWORD);
                    output.accept(MythicItems.SAPPHIRE_PICKAXE);
                    output.accept(MythicItems.SAPPHIRE_AXE);
                    output.accept(MythicItems.SAPPHIRE_SHOVEL);
                    output.accept(MythicItems.SAPPHIRE_HOE);
                    output.accept(MythicItems.SAPPHIRE_HELMET);
                    output.accept(MythicItems.SAPPHIRE_CHESTPLATE);
                    output.accept(MythicItems.SAPPHIRE_LEGGINGS);
                    output.accept(MythicItems.SAPPHIRE_BOOTS);
                    output.accept(MythicItems.JADE_SWORD);
                    output.accept(MythicItems.JADE_PICKAXE);
                    output.accept(MythicItems.JADE_AXE);
                    output.accept(MythicItems.JADE_SHOVEL);
                    output.accept(MythicItems.JADE_HOE);
                    output.accept(MythicItems.JADE_HELMET);
                    output.accept(MythicItems.JADE_CHESTPLATE);
                    output.accept(MythicItems.JADE_LEGGINGS);
                    output.accept(MythicItems.JADE_BOOTS);
                    output.accept(MythicItems.AMETRINE_SWORD);
                    output.accept(MythicItems.AMETRINE_PICKAXE);
                    output.accept(MythicItems.AMETRINE_AXE);
                    output.accept(MythicItems.AMETRINE_SHOVEL);
                    output.accept(MythicItems.AMETRINE_HOE);
                    output.accept(MythicItems.AMETRINE_HELMET);
                    output.accept(MythicItems.AMETRINE_CHESTPLATE);
                    output.accept(MythicItems.AMETRINE_LEGGINGS);
                    output.accept(MythicItems.AMETRINE_BOOTS);
                })
                .build()
        );

        BLOCKS_TAB = reg.apply("blocks",
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 3)
                .title(Component.translatable("itemGroup.mythicupgrades.blocks"))
                .icon(() -> new ItemStack(MythicBlocks.AMETRINE_CRYSTAL_BRICKS))
                .displayItems((params, output) -> {
                    output.accept(MythicBlocks.AQUAMARINE_ORE);
                    output.accept(MythicBlocks.DEEPSLATE_AQUAMARINE_ORE);
                    output.accept(MythicBlocks.CITRINE_ORE);
                    output.accept(MythicBlocks.DEEPSLATE_CITRINE_ORE);
                    output.accept(MythicBlocks.TOPAZ_ORE);
                    output.accept(MythicBlocks.DEEPSLATE_TOPAZ_ORE);
                    output.accept(MythicBlocks.PERIDOT_ORE);
                    output.accept(MythicBlocks.DEEPSLATE_PERIDOT_ORE);
                    output.accept(MythicBlocks.RUBY_ORE);
                    output.accept(MythicBlocks.SAPPHIRE_ORE);
                    output.accept(MythicBlocks.JADE_ORE);
                    output.accept(MythicBlocks.AMETRINE_ORE);
                    output.accept(MythicBlocks.NECOIUM_ORE);
                    output.accept(MythicBlocks.DEEPSLATE_NECOIUM_ORE);
                    output.accept(MythicBlocks.RAW_NECOIUM_BLOCK);
                    output.accept(MythicBlocks.AQUAMARINE_BLOCK);
                    output.accept(MythicBlocks.CITRINE_BLOCK);
                    output.accept(MythicBlocks.TOPAZ_BLOCK);
                    output.accept(MythicBlocks.PERIDOT_BLOCK);
                    output.accept(MythicBlocks.RUBY_BLOCK);
                    output.accept(MythicBlocks.SAPPHIRE_BLOCK);
                    output.accept(MythicBlocks.JADE_BLOCK);
                    output.accept(MythicBlocks.AMETRINE_BLOCK);
                    output.accept(MythicBlocks.NECOIUM_BLOCK);
                    output.accept(MythicBlocks.AQUAMARINE_SCHIST);
                    output.accept(MythicBlocks.AQUAMARINE_SCHIST_SLAB);
                    output.accept(MythicBlocks.AQUAMARINE_SCHIST_STAIRS);
                    output.accept(MythicBlocks.POLISHED_AQUAMARINE_SCHIST);
                    output.accept(MythicBlocks.POLISHED_AQUAMARINE_SCHIST_SLAB);
                    output.accept(MythicBlocks.POLISHED_AQUAMARINE_SCHIST_STAIRS);
                    output.accept(MythicBlocks.CITRINE_SCHIST);
                    output.accept(MythicBlocks.CITRINE_SCHIST_SLAB);
                    output.accept(MythicBlocks.CITRINE_SCHIST_STAIRS);
                    output.accept(MythicBlocks.POLISHED_CITRINE_SCHIST);
                    output.accept(MythicBlocks.POLISHED_CITRINE_SCHIST_SLAB);
                    output.accept(MythicBlocks.POLISHED_CITRINE_SCHIST_STAIRS);
                    output.accept(MythicBlocks.TOPAZ_SCHIST);
                    output.accept(MythicBlocks.TOPAZ_SCHIST_SLAB);
                    output.accept(MythicBlocks.TOPAZ_SCHIST_STAIRS);
                    output.accept(MythicBlocks.POLISHED_TOPAZ_SCHIST);
                    output.accept(MythicBlocks.POLISHED_TOPAZ_SCHIST_SLAB);
                    output.accept(MythicBlocks.POLISHED_TOPAZ_SCHIST_STAIRS);
                    output.accept(MythicBlocks.PERIDOT_SCHIST);
                    output.accept(MythicBlocks.PERIDOT_SCHIST_SLAB);
                    output.accept(MythicBlocks.PERIDOT_SCHIST_STAIRS);
                    output.accept(MythicBlocks.POLISHED_PERIDOT_SCHIST);
                    output.accept(MythicBlocks.POLISHED_PERIDOT_SCHIST_SLAB);
                    output.accept(MythicBlocks.POLISHED_PERIDOT_SCHIST_STAIRS);
                    output.accept(MythicBlocks.RUBY_SCHIST);
                    output.accept(MythicBlocks.RUBY_SCHIST_SLAB);
                    output.accept(MythicBlocks.RUBY_SCHIST_STAIRS);
                    output.accept(MythicBlocks.POLISHED_RUBY_SCHIST);
                    output.accept(MythicBlocks.POLISHED_RUBY_SCHIST_SLAB);
                    output.accept(MythicBlocks.POLISHED_RUBY_SCHIST_STAIRS);
                    output.accept(MythicBlocks.SAPPHIRE_SCHIST);
                    output.accept(MythicBlocks.SAPPHIRE_SCHIST_SLAB);
                    output.accept(MythicBlocks.SAPPHIRE_SCHIST_STAIRS);
                    output.accept(MythicBlocks.POLISHED_SAPPHIRE_SCHIST);
                    output.accept(MythicBlocks.POLISHED_SAPPHIRE_SCHIST_SLAB);
                    output.accept(MythicBlocks.POLISHED_SAPPHIRE_SCHIST_STAIRS);
                    output.accept(MythicBlocks.JADE_SCHIST);
                    output.accept(MythicBlocks.JADE_SCHIST_SLAB);
                    output.accept(MythicBlocks.JADE_SCHIST_STAIRS);
                    output.accept(MythicBlocks.POLISHED_JADE_SCHIST);
                    output.accept(MythicBlocks.POLISHED_JADE_SCHIST_SLAB);
                    output.accept(MythicBlocks.POLISHED_JADE_SCHIST_STAIRS);
                    output.accept(MythicBlocks.AMETRINE_SCHIST);
                    output.accept(MythicBlocks.AMETRINE_SCHIST_SLAB);
                    output.accept(MythicBlocks.AMETRINE_SCHIST_STAIRS);
                    output.accept(MythicBlocks.POLISHED_AMETRINE_SCHIST);
                    output.accept(MythicBlocks.POLISHED_AMETRINE_SCHIST_SLAB);
                    output.accept(MythicBlocks.POLISHED_AMETRINE_SCHIST_STAIRS);
                    output.accept(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS);
                    output.accept(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_SLAB);
                    output.accept(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_STAIRS);
                    output.accept(MythicBlocks.AQUAMARINE_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.CUT_AQUAMARINE_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.BUDDING_AQUAMARINE_CRYSTAL);
                    output.accept(MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER);
                    output.accept(MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.CITRINE_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.CITRINE_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.CITRINE_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.CITRINE_CRYSTAL_BRICKS);
                    output.accept(MythicBlocks.CITRINE_CRYSTAL_BRICKS_SLAB);
                    output.accept(MythicBlocks.CITRINE_CRYSTAL_BRICKS_STAIRS);
                    output.accept(MythicBlocks.CITRINE_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.CUT_CITRINE_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.BUDDING_CITRINE_CRYSTAL);
                    output.accept(MythicBlocks.CITRINE_CRYSTAL_CLUSTER);
                    output.accept(MythicBlocks.LARGE_CITRINE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.TOPAZ_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.TOPAZ_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.TOPAZ_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.TOPAZ_CRYSTAL_BRICKS);
                    output.accept(MythicBlocks.TOPAZ_CRYSTAL_BRICKS_SLAB);
                    output.accept(MythicBlocks.TOPAZ_CRYSTAL_BRICKS_STAIRS);
                    output.accept(MythicBlocks.TOPAZ_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.CUT_TOPAZ_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.BUDDING_TOPAZ_CRYSTAL);
                    output.accept(MythicBlocks.TOPAZ_CRYSTAL_CLUSTER);
                    output.accept(MythicBlocks.LARGE_TOPAZ_CRYSTAL_BUD);
                    output.accept(MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD);
                    output.accept(MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD);
                    output.accept(MythicBlocks.PERIDOT_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.PERIDOT_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.PERIDOT_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.PERIDOT_CRYSTAL_BRICKS);
                    output.accept(MythicBlocks.PERIDOT_CRYSTAL_BRICKS_SLAB);
                    output.accept(MythicBlocks.PERIDOT_CRYSTAL_BRICKS_STAIRS);
                    output.accept(MythicBlocks.PERIDOT_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.CUT_PERIDOT_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.BUDDING_PERIDOT_CRYSTAL);
                    output.accept(MythicBlocks.PERIDOT_CRYSTAL_CLUSTER);
                    output.accept(MythicBlocks.LARGE_PERIDOT_CRYSTAL_BUD);
                    output.accept(MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD);
                    output.accept(MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD);
                    output.accept(MythicBlocks.RUBY_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.RUBY_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.RUBY_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.RUBY_CRYSTAL_BRICKS);
                    output.accept(MythicBlocks.RUBY_CRYSTAL_BRICKS_SLAB);
                    output.accept(MythicBlocks.RUBY_CRYSTAL_BRICKS_STAIRS);
                    output.accept(MythicBlocks.RUBY_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.CUT_RUBY_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.BUDDING_RUBY_CRYSTAL);
                    output.accept(MythicBlocks.RUBY_CRYSTAL_CLUSTER);
                    output.accept(MythicBlocks.LARGE_RUBY_CRYSTAL_BUD);
                    output.accept(MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD);
                    output.accept(MythicBlocks.SMALL_RUBY_CRYSTAL_BUD);
                    output.accept(MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS);
                    output.accept(MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_SLAB);
                    output.accept(MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_STAIRS);
                    output.accept(MythicBlocks.SAPPHIRE_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.CUT_SAPPHIRE_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.BUDDING_SAPPHIRE_CRYSTAL);
                    output.accept(MythicBlocks.SAPPHIRE_CRYSTAL_CLUSTER);
                    output.accept(MythicBlocks.LARGE_SAPPHIRE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.JADE_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.JADE_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.JADE_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.JADE_CRYSTAL_BRICKS);
                    output.accept(MythicBlocks.JADE_CRYSTAL_BRICKS_SLAB);
                    output.accept(MythicBlocks.JADE_CRYSTAL_BRICKS_STAIRS);
                    output.accept(MythicBlocks.JADE_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.CUT_JADE_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.BUDDING_JADE_CRYSTAL);
                    output.accept(MythicBlocks.JADE_CRYSTAL_CLUSTER);
                    output.accept(MythicBlocks.LARGE_JADE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.SMALL_JADE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.AMETRINE_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.AMETRINE_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.AMETRINE_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK);
                    output.accept(MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_SLAB);
                    output.accept(MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_STAIRS);
                    output.accept(MythicBlocks.AMETRINE_CRYSTAL_BRICKS);
                    output.accept(MythicBlocks.AMETRINE_CRYSTAL_BRICKS_SLAB);
                    output.accept(MythicBlocks.AMETRINE_CRYSTAL_BRICKS_STAIRS);
                    output.accept(MythicBlocks.AMETRINE_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.CUT_AMETRINE_CRYSTAL_PILLAR);
                    output.accept(MythicBlocks.BUDDING_AMETRINE_CRYSTAL);
                    output.accept(MythicBlocks.AMETRINE_CRYSTAL_CLUSTER);
                    output.accept(MythicBlocks.LARGE_AMETRINE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD);
                    output.accept(MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD);
                })
                .build()
        );

        Constants.LOG.info("MythicCreativeTabs registered.");
    }
}
