package net.trique.mythicupgrades.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.item.MythicItems;

import java.util.List;
import java.util.Set;

public class MythicBlockLootTables extends BlockLootSubProvider {

    public MythicBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        add(MythicBlocks.AQUAMARINE_ORE, block -> createOreDrop(block, MythicItems.AQUAMARINE.asItem()));
        add(MythicBlocks.DEEPSLATE_AQUAMARINE_ORE, block -> createOreDrop(block, MythicItems.AQUAMARINE.asItem()));
        add(MythicBlocks.CITRINE_ORE, block -> createOreDrop(block, MythicItems.CITRINE.asItem()));
        add(MythicBlocks.DEEPSLATE_CITRINE_ORE, block -> createOreDrop(block, MythicItems.CITRINE.asItem()));
        add(MythicBlocks.TOPAZ_ORE, block -> createOreDrop(block, MythicItems.TOPAZ.asItem()));
        add(MythicBlocks.DEEPSLATE_TOPAZ_ORE, block -> createOreDrop(block, MythicItems.TOPAZ.asItem()));
        add(MythicBlocks.PERIDOT_ORE, block -> createOreDrop(block, MythicItems.PERIDOT.asItem()));
        add(MythicBlocks.DEEPSLATE_PERIDOT_ORE, block -> createOreDrop(block, MythicItems.PERIDOT.asItem()));
        add(MythicBlocks.RUBY_ORE, block -> createOreDrop(block, MythicItems.RUBY.asItem()));
        add(MythicBlocks.SAPPHIRE_ORE, block -> createOreDrop(block, MythicItems.SAPPHIRE.asItem()));
        add(MythicBlocks.JADE_ORE, block -> createOreDrop(block, MythicItems.JADE.asItem()));
        add(MythicBlocks.AMETRINE_ORE, block -> createOreDrop(block, MythicItems.AMETRINE.asItem()));
        add(MythicBlocks.NECOIUM_ORE, block -> createOreDrop(block, MythicItems.RAW_NECOIUM.asItem()));

        dropSelf(MythicBlocks.RAW_NECOIUM_BLOCK);
        dropSelf(MythicBlocks.AQUAMARINE_BLOCK);
        dropSelf(MythicBlocks.CITRINE_BLOCK);
        dropSelf(MythicBlocks.TOPAZ_BLOCK);
        dropSelf(MythicBlocks.PERIDOT_BLOCK);
        dropSelf(MythicBlocks.RUBY_BLOCK);
        dropSelf(MythicBlocks.SAPPHIRE_BLOCK);
        dropSelf(MythicBlocks.JADE_BLOCK);
        dropSelf(MythicBlocks.AMETRINE_BLOCK);
        dropSelf(MythicBlocks.NECOIUM_BLOCK);

        dropSelf(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.CITRINE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.TOPAZ_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.PERIDOT_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.RUBY_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.JADE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.AMETRINE_CRYSTAL_BLOCK);

        add(MythicBlocks.BUDDING_AQUAMARINE_CRYSTAL, createSilkTouchOnlyTable(MythicBlocks.BUDDING_AQUAMARINE_CRYSTAL));
        add(MythicBlocks.BUDDING_CITRINE_CRYSTAL, createSilkTouchOnlyTable(MythicBlocks.BUDDING_CITRINE_CRYSTAL));
        add(MythicBlocks.BUDDING_TOPAZ_CRYSTAL, createSilkTouchOnlyTable(MythicBlocks.BUDDING_TOPAZ_CRYSTAL));
        add(MythicBlocks.BUDDING_PERIDOT_CRYSTAL, createSilkTouchOnlyTable(MythicBlocks.BUDDING_PERIDOT_CRYSTAL));
        add(MythicBlocks.BUDDING_RUBY_CRYSTAL, createSilkTouchOnlyTable(MythicBlocks.BUDDING_RUBY_CRYSTAL));
        add(MythicBlocks.BUDDING_SAPPHIRE_CRYSTAL, createSilkTouchOnlyTable(MythicBlocks.BUDDING_SAPPHIRE_CRYSTAL));
        add(MythicBlocks.BUDDING_JADE_CRYSTAL, createSilkTouchOnlyTable(MythicBlocks.BUDDING_JADE_CRYSTAL));
        add(MythicBlocks.BUDDING_AMETRINE_CRYSTAL, createSilkTouchOnlyTable(MythicBlocks.BUDDING_AMETRINE_CRYSTAL));

        add(MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_RUBY_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.SMALL_RUBY_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_JADE_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.SMALL_JADE_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD));

        add(MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD, block -> createSingleItemTableWithSilkTouch(block, MythicItems.AQUAMARINE_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_CITRINE_CRYSTAL_BUD, block -> createSingleItemTableWithSilkTouch(block, MythicItems.CITRINE_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_TOPAZ_CRYSTAL_BUD, block -> createSingleItemTableWithSilkTouch(block, MythicItems.TOPAZ_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_PERIDOT_CRYSTAL_BUD, block -> createSingleItemTableWithSilkTouch(block, MythicItems.PERIDOT_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_RUBY_CRYSTAL_BUD, block -> createSingleItemTableWithSilkTouch(block, MythicItems.RUBY_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_SAPPHIRE_CRYSTAL_BUD, block -> createSingleItemTableWithSilkTouch(block, MythicItems.SAPPHIRE_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_JADE_CRYSTAL_BUD, block -> createSingleItemTableWithSilkTouch(block, MythicItems.JADE_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_AMETRINE_CRYSTAL_BUD, block -> createSingleItemTableWithSilkTouch(block, MythicItems.AMETRINE_CRYSTAL_SHARD));

        add(MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER, block -> createClusterDrop(block, MythicItems.AQUAMARINE_CRYSTAL_SHARD));
        add(MythicBlocks.CITRINE_CRYSTAL_CLUSTER, block -> createClusterDrop(block, MythicItems.CITRINE_CRYSTAL_SHARD));
        add(MythicBlocks.TOPAZ_CRYSTAL_CLUSTER, block -> createClusterDrop(block, MythicItems.TOPAZ_CRYSTAL_SHARD));
        add(MythicBlocks.PERIDOT_CRYSTAL_CLUSTER, block -> createClusterDrop(block, MythicItems.PERIDOT_CRYSTAL_SHARD));
        add(MythicBlocks.RUBY_CRYSTAL_CLUSTER, block -> createClusterDrop(block, MythicItems.RUBY_CRYSTAL_SHARD));
        add(MythicBlocks.SAPPHIRE_CRYSTAL_CLUSTER, block -> createClusterDrop(block, MythicItems.SAPPHIRE_CRYSTAL_SHARD));
        add(MythicBlocks.JADE_CRYSTAL_CLUSTER, block -> createClusterDrop(block, MythicItems.JADE_CRYSTAL_SHARD));
        add(MythicBlocks.AMETRINE_CRYSTAL_CLUSTER, block -> createClusterDrop(block, MythicItems.AMETRINE_CRYSTAL_SHARD));

        dropSelf(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS);
        dropSelf(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_STAIRS);
        add(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.AQUAMARINE_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.CUT_AQUAMARINE_CRYSTAL_PILLAR);

        dropSelf(MythicBlocks.CITRINE_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.CITRINE_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.CITRINE_CRYSTAL_BRICKS);
        dropSelf(MythicBlocks.CITRINE_CRYSTAL_BRICKS_STAIRS);
        add(MythicBlocks.CITRINE_CRYSTAL_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.CITRINE_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.CUT_CITRINE_CRYSTAL_PILLAR);

        dropSelf(MythicBlocks.TOPAZ_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.TOPAZ_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.TOPAZ_CRYSTAL_BRICKS);
        dropSelf(MythicBlocks.TOPAZ_CRYSTAL_BRICKS_STAIRS);
        add(MythicBlocks.TOPAZ_CRYSTAL_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.TOPAZ_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.CUT_TOPAZ_CRYSTAL_PILLAR);

        dropSelf(MythicBlocks.PERIDOT_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.PERIDOT_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.PERIDOT_CRYSTAL_BRICKS);
        dropSelf(MythicBlocks.PERIDOT_CRYSTAL_BRICKS_STAIRS);
        add(MythicBlocks.PERIDOT_CRYSTAL_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.PERIDOT_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.CUT_PERIDOT_CRYSTAL_PILLAR);

        dropSelf(MythicBlocks.RUBY_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.RUBY_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.RUBY_CRYSTAL_BRICKS);
        dropSelf(MythicBlocks.RUBY_CRYSTAL_BRICKS_STAIRS);
        add(MythicBlocks.RUBY_CRYSTAL_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.RUBY_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.CUT_RUBY_CRYSTAL_PILLAR);

        dropSelf(MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS);
        dropSelf(MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_STAIRS);
        add(MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.SAPPHIRE_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.CUT_SAPPHIRE_CRYSTAL_PILLAR);

        dropSelf(MythicBlocks.JADE_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.JADE_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.JADE_CRYSTAL_BRICKS);
        dropSelf(MythicBlocks.JADE_CRYSTAL_BRICKS_STAIRS);
        add(MythicBlocks.JADE_CRYSTAL_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.JADE_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.CUT_JADE_CRYSTAL_PILLAR);

        dropSelf(MythicBlocks.AMETRINE_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.AMETRINE_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_STAIRS);
        add(MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.AMETRINE_CRYSTAL_BRICKS);
        dropSelf(MythicBlocks.AMETRINE_CRYSTAL_BRICKS_STAIRS);
        add(MythicBlocks.AMETRINE_CRYSTAL_BRICKS_SLAB, this::createSlabItemTable);
        dropSelf(MythicBlocks.AMETRINE_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.CUT_AMETRINE_CRYSTAL_PILLAR);
    }

    private LootTable.Builder createClusterDrop(Block block, Item shard) {
        return createSilkTouchDispatchTable(block,
            LootItem.lootTableItem(shard)
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.PICKAXES)))
                .otherwise(applyExplosionDecay(block,
                    LootItem.lootTableItem(shard)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(
            MythicBlocks.AQUAMARINE_ORE, MythicBlocks.DEEPSLATE_AQUAMARINE_ORE,
            MythicBlocks.CITRINE_ORE, MythicBlocks.DEEPSLATE_CITRINE_ORE,
            MythicBlocks.TOPAZ_ORE, MythicBlocks.DEEPSLATE_TOPAZ_ORE,
            MythicBlocks.PERIDOT_ORE, MythicBlocks.DEEPSLATE_PERIDOT_ORE,
            MythicBlocks.RUBY_ORE, MythicBlocks.SAPPHIRE_ORE,
            MythicBlocks.JADE_ORE, MythicBlocks.AMETRINE_ORE, MythicBlocks.NECOIUM_ORE,
            MythicBlocks.RAW_NECOIUM_BLOCK,
            MythicBlocks.AQUAMARINE_BLOCK,
            MythicBlocks.CITRINE_BLOCK, MythicBlocks.TOPAZ_BLOCK,
            MythicBlocks.PERIDOT_BLOCK, MythicBlocks.RUBY_BLOCK,
            MythicBlocks.SAPPHIRE_BLOCK, MythicBlocks.JADE_BLOCK,
            MythicBlocks.AMETRINE_BLOCK, MythicBlocks.NECOIUM_BLOCK,
            MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK, MythicBlocks.BUDDING_AQUAMARINE_CRYSTAL,
            MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER, MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD,
            MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD, MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD,
            MythicBlocks.CITRINE_CRYSTAL_BLOCK, MythicBlocks.BUDDING_CITRINE_CRYSTAL,
            MythicBlocks.CITRINE_CRYSTAL_CLUSTER, MythicBlocks.LARGE_CITRINE_CRYSTAL_BUD,
            MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD, MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD,
            MythicBlocks.TOPAZ_CRYSTAL_BLOCK, MythicBlocks.BUDDING_TOPAZ_CRYSTAL,
            MythicBlocks.TOPAZ_CRYSTAL_CLUSTER, MythicBlocks.LARGE_TOPAZ_CRYSTAL_BUD,
            MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD, MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD,
            MythicBlocks.PERIDOT_CRYSTAL_BLOCK, MythicBlocks.BUDDING_PERIDOT_CRYSTAL,
            MythicBlocks.PERIDOT_CRYSTAL_CLUSTER, MythicBlocks.LARGE_PERIDOT_CRYSTAL_BUD,
            MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD, MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD,
            MythicBlocks.RUBY_CRYSTAL_BLOCK, MythicBlocks.BUDDING_RUBY_CRYSTAL,
            MythicBlocks.RUBY_CRYSTAL_CLUSTER, MythicBlocks.LARGE_RUBY_CRYSTAL_BUD,
            MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD, MythicBlocks.SMALL_RUBY_CRYSTAL_BUD,
            MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK, MythicBlocks.BUDDING_SAPPHIRE_CRYSTAL,
            MythicBlocks.SAPPHIRE_CRYSTAL_CLUSTER, MythicBlocks.LARGE_SAPPHIRE_CRYSTAL_BUD,
            MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD, MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD,
            MythicBlocks.JADE_CRYSTAL_BLOCK, MythicBlocks.BUDDING_JADE_CRYSTAL,
            MythicBlocks.JADE_CRYSTAL_CLUSTER, MythicBlocks.LARGE_JADE_CRYSTAL_BUD,
            MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD, MythicBlocks.SMALL_JADE_CRYSTAL_BUD,
            MythicBlocks.AMETRINE_CRYSTAL_BLOCK, MythicBlocks.BUDDING_AMETRINE_CRYSTAL,
            MythicBlocks.AMETRINE_CRYSTAL_CLUSTER, MythicBlocks.LARGE_AMETRINE_CRYSTAL_BUD,
            MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD, MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD,
            MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_SLAB,
            MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS, MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_SLAB,
            MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.AQUAMARINE_CRYSTAL_PILLAR, MythicBlocks.CUT_AQUAMARINE_CRYSTAL_PILLAR,
            MythicBlocks.CITRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.CITRINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_SLAB,
            MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.CITRINE_CRYSTAL_BRICKS, MythicBlocks.CITRINE_CRYSTAL_BRICKS_SLAB,
            MythicBlocks.CITRINE_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.CITRINE_CRYSTAL_PILLAR, MythicBlocks.CUT_CITRINE_CRYSTAL_PILLAR,
            MythicBlocks.TOPAZ_CRYSTAL_BLOCK_SLAB, MythicBlocks.TOPAZ_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK, MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_SLAB,
            MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.TOPAZ_CRYSTAL_BRICKS, MythicBlocks.TOPAZ_CRYSTAL_BRICKS_SLAB,
            MythicBlocks.TOPAZ_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.TOPAZ_CRYSTAL_PILLAR, MythicBlocks.CUT_TOPAZ_CRYSTAL_PILLAR,
            MythicBlocks.PERIDOT_CRYSTAL_BLOCK_SLAB, MythicBlocks.PERIDOT_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK, MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_SLAB,
            MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.PERIDOT_CRYSTAL_BRICKS, MythicBlocks.PERIDOT_CRYSTAL_BRICKS_SLAB,
            MythicBlocks.PERIDOT_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.PERIDOT_CRYSTAL_PILLAR, MythicBlocks.CUT_PERIDOT_CRYSTAL_PILLAR,
            MythicBlocks.RUBY_CRYSTAL_BLOCK_SLAB, MythicBlocks.RUBY_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK, MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_SLAB,
            MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.RUBY_CRYSTAL_BRICKS, MythicBlocks.RUBY_CRYSTAL_BRICKS_SLAB,
            MythicBlocks.RUBY_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.RUBY_CRYSTAL_PILLAR, MythicBlocks.CUT_RUBY_CRYSTAL_PILLAR,
            MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_SLAB, MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_SLAB,
            MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS, MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_SLAB,
            MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.SAPPHIRE_CRYSTAL_PILLAR, MythicBlocks.CUT_SAPPHIRE_CRYSTAL_PILLAR,
            MythicBlocks.JADE_CRYSTAL_BLOCK_SLAB, MythicBlocks.JADE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_SLAB,
            MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.JADE_CRYSTAL_BRICKS, MythicBlocks.JADE_CRYSTAL_BRICKS_SLAB,
            MythicBlocks.JADE_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.JADE_CRYSTAL_PILLAR, MythicBlocks.CUT_JADE_CRYSTAL_PILLAR,
            MythicBlocks.AMETRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.AMETRINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_SLAB,
            MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.AMETRINE_CRYSTAL_BRICKS, MythicBlocks.AMETRINE_CRYSTAL_BRICKS_SLAB,
            MythicBlocks.AMETRINE_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.AMETRINE_CRYSTAL_PILLAR, MythicBlocks.CUT_AMETRINE_CRYSTAL_PILLAR
        );
    }
}
