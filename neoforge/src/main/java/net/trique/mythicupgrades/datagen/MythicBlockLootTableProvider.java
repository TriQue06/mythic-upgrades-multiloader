package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.item.MythicItems;

import java.util.List;
import java.util.Set;

public class MythicBlockLootTableProvider extends BlockLootSubProvider {

    public MythicBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        // Cave gem ores → gem (with fortune)
        add(MythicBlocks.AQUAMARINE_ORE,           createOreDrop(MythicBlocks.AQUAMARINE_ORE,           MythicItems.AQUAMARINE));
        add(MythicBlocks.DEEPSLATE_AQUAMARINE_ORE, createOreDrop(MythicBlocks.DEEPSLATE_AQUAMARINE_ORE, MythicItems.AQUAMARINE));
        add(MythicBlocks.CITRINE_ORE,              createOreDrop(MythicBlocks.CITRINE_ORE,              MythicItems.CITRINE));
        add(MythicBlocks.DEEPSLATE_CITRINE_ORE,   createOreDrop(MythicBlocks.DEEPSLATE_CITRINE_ORE,   MythicItems.CITRINE));
        add(MythicBlocks.TOPAZ_ORE,               createOreDrop(MythicBlocks.TOPAZ_ORE,               MythicItems.TOPAZ));
        add(MythicBlocks.DEEPSLATE_TOPAZ_ORE,     createOreDrop(MythicBlocks.DEEPSLATE_TOPAZ_ORE,     MythicItems.TOPAZ));
        add(MythicBlocks.PERIDOT_ORE,             createOreDrop(MythicBlocks.PERIDOT_ORE,             MythicItems.PERIDOT));
        add(MythicBlocks.DEEPSLATE_PERIDOT_ORE,   createOreDrop(MythicBlocks.DEEPSLATE_PERIDOT_ORE,   MythicItems.PERIDOT));
        // Nether gem ores
        add(MythicBlocks.RUBY_ORE,                createOreDrop(MythicBlocks.RUBY_ORE,                MythicItems.RUBY));
        add(MythicBlocks.SAPPHIRE_ORE,            createOreDrop(MythicBlocks.SAPPHIRE_ORE,            MythicItems.SAPPHIRE));
        // End gem ores
        add(MythicBlocks.JADE_ORE,                createOreDrop(MythicBlocks.JADE_ORE,                MythicItems.JADE));
        add(MythicBlocks.AMETRINE_ORE,            createOreDrop(MythicBlocks.AMETRINE_ORE,            MythicItems.AMETRINE));
        // Necoium
        add(MythicBlocks.NECOIUM_ORE,             createOreDrop(MythicBlocks.NECOIUM_ORE,             MythicItems.RAW_NECOIUM));
        add(MythicBlocks.DEEPSLATE_NECOIUM_ORE,   createOreDrop(MythicBlocks.DEEPSLATE_NECOIUM_ORE,   MythicItems.RAW_NECOIUM));
        dropSelf(MythicBlocks.RAW_NECOIUM_BLOCK);
        dropSelf(MythicBlocks.NECOIUM_BLOCK);

        // Gem storage blocks
        dropSelf(MythicBlocks.AQUAMARINE_BLOCK); dropSelf(MythicBlocks.CITRINE_BLOCK);
        dropSelf(MythicBlocks.TOPAZ_BLOCK);      dropSelf(MythicBlocks.PERIDOT_BLOCK);
        dropSelf(MythicBlocks.RUBY_BLOCK);       dropSelf(MythicBlocks.SAPPHIRE_BLOCK);
        dropSelf(MythicBlocks.JADE_BLOCK);       dropSelf(MythicBlocks.AMETRINE_BLOCK);

        // Crystal blocks
        dropSelf(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK); dropSelf(MythicBlocks.CITRINE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.TOPAZ_CRYSTAL_BLOCK);      dropSelf(MythicBlocks.PERIDOT_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.RUBY_CRYSTAL_BLOCK);       dropSelf(MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.JADE_CRYSTAL_BLOCK);       dropSelf(MythicBlocks.AMETRINE_CRYSTAL_BLOCK);

        // Budding crystals — silk touch only
        add(MythicBlocks.BUDDING_AQUAMARINE_CRYSTAL, createSilkTouchOnlyTable(MythicBlocks.BUDDING_AQUAMARINE_CRYSTAL));
        add(MythicBlocks.BUDDING_CITRINE_CRYSTAL,    createSilkTouchOnlyTable(MythicBlocks.BUDDING_CITRINE_CRYSTAL));
        add(MythicBlocks.BUDDING_TOPAZ_CRYSTAL,      createSilkTouchOnlyTable(MythicBlocks.BUDDING_TOPAZ_CRYSTAL));
        add(MythicBlocks.BUDDING_PERIDOT_CRYSTAL,    createSilkTouchOnlyTable(MythicBlocks.BUDDING_PERIDOT_CRYSTAL));
        add(MythicBlocks.BUDDING_RUBY_CRYSTAL,       createSilkTouchOnlyTable(MythicBlocks.BUDDING_RUBY_CRYSTAL));
        add(MythicBlocks.BUDDING_SAPPHIRE_CRYSTAL,   createSilkTouchOnlyTable(MythicBlocks.BUDDING_SAPPHIRE_CRYSTAL));
        add(MythicBlocks.BUDDING_JADE_CRYSTAL,       createSilkTouchOnlyTable(MythicBlocks.BUDDING_JADE_CRYSTAL));
        add(MythicBlocks.BUDDING_AMETRINE_CRYSTAL,   createSilkTouchOnlyTable(MythicBlocks.BUDDING_AMETRINE_CRYSTAL));

        // Clusters — 4 shards with fortune (or silk touch = block)
        add(MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER, createAmethystClusterDrops(MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER, MythicItems.AQUAMARINE_CRYSTAL_SHARD));
        add(MythicBlocks.CITRINE_CRYSTAL_CLUSTER,    createAmethystClusterDrops(MythicBlocks.CITRINE_CRYSTAL_CLUSTER,    MythicItems.CITRINE_CRYSTAL_SHARD));
        add(MythicBlocks.TOPAZ_CRYSTAL_CLUSTER,      createAmethystClusterDrops(MythicBlocks.TOPAZ_CRYSTAL_CLUSTER,      MythicItems.TOPAZ_CRYSTAL_SHARD));
        add(MythicBlocks.PERIDOT_CRYSTAL_CLUSTER,    createAmethystClusterDrops(MythicBlocks.PERIDOT_CRYSTAL_CLUSTER,    MythicItems.PERIDOT_CRYSTAL_SHARD));
        add(MythicBlocks.RUBY_CRYSTAL_CLUSTER,       createAmethystClusterDrops(MythicBlocks.RUBY_CRYSTAL_CLUSTER,       MythicItems.RUBY_CRYSTAL_SHARD));
        add(MythicBlocks.SAPPHIRE_CRYSTAL_CLUSTER,   createAmethystClusterDrops(MythicBlocks.SAPPHIRE_CRYSTAL_CLUSTER,   MythicItems.SAPPHIRE_CRYSTAL_SHARD));
        add(MythicBlocks.JADE_CRYSTAL_CLUSTER,       createAmethystClusterDrops(MythicBlocks.JADE_CRYSTAL_CLUSTER,       MythicItems.JADE_CRYSTAL_SHARD));
        add(MythicBlocks.AMETRINE_CRYSTAL_CLUSTER,   createAmethystClusterDrops(MythicBlocks.AMETRINE_CRYSTAL_CLUSTER,   MythicItems.AMETRINE_CRYSTAL_SHARD));

        // Small/medium buds — silk touch only
        addSilkTouchOnlyGroup(MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD,  MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD,  MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD,  MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD);
        addSilkTouchOnlyGroup(MythicBlocks.SMALL_RUBY_CRYSTAL_BUD,        MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD, MythicBlocks.SMALL_JADE_CRYSTAL_BUD,   MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD);
        addSilkTouchOnlyGroup(MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD, MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD, MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD,  MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD);
        addSilkTouchOnlyGroup(MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD,       MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD,MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD,   MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD);

        // Large buds — 1 shard, or silk touch = block
        add(MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD, createSingleItemTableWithSilkTouch(MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD, MythicItems.AQUAMARINE_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_CITRINE_CRYSTAL_BUD,    createSingleItemTableWithSilkTouch(MythicBlocks.LARGE_CITRINE_CRYSTAL_BUD,    MythicItems.CITRINE_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_TOPAZ_CRYSTAL_BUD,      createSingleItemTableWithSilkTouch(MythicBlocks.LARGE_TOPAZ_CRYSTAL_BUD,      MythicItems.TOPAZ_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_PERIDOT_CRYSTAL_BUD,    createSingleItemTableWithSilkTouch(MythicBlocks.LARGE_PERIDOT_CRYSTAL_BUD,    MythicItems.PERIDOT_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_RUBY_CRYSTAL_BUD,       createSingleItemTableWithSilkTouch(MythicBlocks.LARGE_RUBY_CRYSTAL_BUD,       MythicItems.RUBY_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_SAPPHIRE_CRYSTAL_BUD,   createSingleItemTableWithSilkTouch(MythicBlocks.LARGE_SAPPHIRE_CRYSTAL_BUD,   MythicItems.SAPPHIRE_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_JADE_CRYSTAL_BUD,       createSingleItemTableWithSilkTouch(MythicBlocks.LARGE_JADE_CRYSTAL_BUD,       MythicItems.JADE_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_AMETRINE_CRYSTAL_BUD,   createSingleItemTableWithSilkTouch(MythicBlocks.LARGE_AMETRINE_CRYSTAL_BUD,   MythicItems.AMETRINE_CRYSTAL_SHARD));

        // Decorative blocks — drop themselves
        dropSelf(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK); dropSelf(MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK);      dropSelf(MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK);       dropSelf(MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK);       dropSelf(MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK);

        dropSelf(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS); dropSelf(MythicBlocks.CITRINE_CRYSTAL_BRICKS);
        dropSelf(MythicBlocks.TOPAZ_CRYSTAL_BRICKS);      dropSelf(MythicBlocks.PERIDOT_CRYSTAL_BRICKS);
        dropSelf(MythicBlocks.RUBY_CRYSTAL_BRICKS);       dropSelf(MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS);
        dropSelf(MythicBlocks.JADE_CRYSTAL_BRICKS);       dropSelf(MythicBlocks.AMETRINE_CRYSTAL_BRICKS);

        dropSelf(MythicBlocks.AQUAMARINE_CRYSTAL_PILLAR); dropSelf(MythicBlocks.CITRINE_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.TOPAZ_CRYSTAL_PILLAR);      dropSelf(MythicBlocks.PERIDOT_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.RUBY_CRYSTAL_PILLAR);       dropSelf(MythicBlocks.SAPPHIRE_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.JADE_CRYSTAL_PILLAR);       dropSelf(MythicBlocks.AMETRINE_CRYSTAL_PILLAR);

        dropSelf(MythicBlocks.CUT_AQUAMARINE_CRYSTAL_PILLAR); dropSelf(MythicBlocks.CUT_CITRINE_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.CUT_TOPAZ_CRYSTAL_PILLAR);      dropSelf(MythicBlocks.CUT_PERIDOT_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.CUT_RUBY_CRYSTAL_PILLAR);       dropSelf(MythicBlocks.CUT_SAPPHIRE_CRYSTAL_PILLAR);
        dropSelf(MythicBlocks.CUT_JADE_CRYSTAL_PILLAR);       dropSelf(MythicBlocks.CUT_AMETRINE_CRYSTAL_PILLAR);

        // Slabs
        add(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_SLAB,          createSlabItemTable(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.CITRINE_CRYSTAL_BLOCK_SLAB,             createSlabItemTable(MythicBlocks.CITRINE_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.TOPAZ_CRYSTAL_BLOCK_SLAB,               createSlabItemTable(MythicBlocks.TOPAZ_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.PERIDOT_CRYSTAL_BLOCK_SLAB,             createSlabItemTable(MythicBlocks.PERIDOT_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.RUBY_CRYSTAL_BLOCK_SLAB,                createSlabItemTable(MythicBlocks.RUBY_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_SLAB,            createSlabItemTable(MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.JADE_CRYSTAL_BLOCK_SLAB,                createSlabItemTable(MythicBlocks.JADE_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.AMETRINE_CRYSTAL_BLOCK_SLAB,            createSlabItemTable(MythicBlocks.AMETRINE_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_SLAB, createSlabItemTable(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_SLAB,    createSlabItemTable(MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_SLAB,      createSlabItemTable(MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_SLAB,    createSlabItemTable(MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_SLAB,       createSlabItemTable(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_SLAB,   createSlabItemTable(MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_SLAB,       createSlabItemTable(MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_SLAB,   createSlabItemTable(MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_SLAB));
        add(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_SLAB,         createSlabItemTable(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_SLAB));
        add(MythicBlocks.CITRINE_CRYSTAL_BRICKS_SLAB,            createSlabItemTable(MythicBlocks.CITRINE_CRYSTAL_BRICKS_SLAB));
        add(MythicBlocks.TOPAZ_CRYSTAL_BRICKS_SLAB,              createSlabItemTable(MythicBlocks.TOPAZ_CRYSTAL_BRICKS_SLAB));
        add(MythicBlocks.PERIDOT_CRYSTAL_BRICKS_SLAB,            createSlabItemTable(MythicBlocks.PERIDOT_CRYSTAL_BRICKS_SLAB));
        add(MythicBlocks.RUBY_CRYSTAL_BRICKS_SLAB,               createSlabItemTable(MythicBlocks.RUBY_CRYSTAL_BRICKS_SLAB));
        add(MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_SLAB,           createSlabItemTable(MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_SLAB));
        add(MythicBlocks.JADE_CRYSTAL_BRICKS_SLAB,               createSlabItemTable(MythicBlocks.JADE_CRYSTAL_BRICKS_SLAB));
        add(MythicBlocks.AMETRINE_CRYSTAL_BRICKS_SLAB,           createSlabItemTable(MythicBlocks.AMETRINE_CRYSTAL_BRICKS_SLAB));

        // Stairs — drop themselves
        dropSelf(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_STAIRS); dropSelf(MythicBlocks.CITRINE_CRYSTAL_BLOCK_STAIRS);
        dropSelf(MythicBlocks.TOPAZ_CRYSTAL_BLOCK_STAIRS);      dropSelf(MythicBlocks.PERIDOT_CRYSTAL_BLOCK_STAIRS);
        dropSelf(MythicBlocks.RUBY_CRYSTAL_BLOCK_STAIRS);       dropSelf(MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_STAIRS);
        dropSelf(MythicBlocks.JADE_CRYSTAL_BLOCK_STAIRS);       dropSelf(MythicBlocks.AMETRINE_CRYSTAL_BLOCK_STAIRS);
        dropSelf(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_STAIRS); dropSelf(MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_STAIRS);
        dropSelf(MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_STAIRS);      dropSelf(MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_STAIRS);
        dropSelf(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_STAIRS);       dropSelf(MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_STAIRS);
        dropSelf(MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_STAIRS);       dropSelf(MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_STAIRS);
        dropSelf(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_STAIRS); dropSelf(MythicBlocks.CITRINE_CRYSTAL_BRICKS_STAIRS);
        dropSelf(MythicBlocks.TOPAZ_CRYSTAL_BRICKS_STAIRS);      dropSelf(MythicBlocks.PERIDOT_CRYSTAL_BRICKS_STAIRS);
        dropSelf(MythicBlocks.RUBY_CRYSTAL_BRICKS_STAIRS);       dropSelf(MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_STAIRS);
        dropSelf(MythicBlocks.JADE_CRYSTAL_BRICKS_STAIRS);       dropSelf(MythicBlocks.AMETRINE_CRYSTAL_BRICKS_STAIRS);

        // Stone variants — drop themselves / slab tables
        dropSelf(MythicBlocks.AQUAMARINE_STONE); dropSelf(MythicBlocks.CITRINE_STONE);
        dropSelf(MythicBlocks.TOPAZ_STONE);      dropSelf(MythicBlocks.PERIDOT_STONE);
        dropSelf(MythicBlocks.RUBY_STONE);       dropSelf(MythicBlocks.SAPPHIRE_STONE);
        dropSelf(MythicBlocks.JADE_STONE);       dropSelf(MythicBlocks.AMETRINE_STONE);
        dropSelf(MythicBlocks.POLISHED_AQUAMARINE_STONE); dropSelf(MythicBlocks.POLISHED_CITRINE_STONE);
        dropSelf(MythicBlocks.POLISHED_TOPAZ_STONE);      dropSelf(MythicBlocks.POLISHED_PERIDOT_STONE);
        dropSelf(MythicBlocks.POLISHED_RUBY_STONE);       dropSelf(MythicBlocks.POLISHED_SAPPHIRE_STONE);
        dropSelf(MythicBlocks.POLISHED_JADE_STONE);       dropSelf(MythicBlocks.POLISHED_AMETRINE_STONE);
        dropSelf(MythicBlocks.AQUAMARINE_STONE_STAIRS); dropSelf(MythicBlocks.CITRINE_STONE_STAIRS);
        dropSelf(MythicBlocks.TOPAZ_STONE_STAIRS);      dropSelf(MythicBlocks.PERIDOT_STONE_STAIRS);
        dropSelf(MythicBlocks.RUBY_STONE_STAIRS);       dropSelf(MythicBlocks.SAPPHIRE_STONE_STAIRS);
        dropSelf(MythicBlocks.JADE_STONE_STAIRS);       dropSelf(MythicBlocks.AMETRINE_STONE_STAIRS);
        dropSelf(MythicBlocks.POLISHED_AQUAMARINE_STONE_STAIRS); dropSelf(MythicBlocks.POLISHED_CITRINE_STONE_STAIRS);
        dropSelf(MythicBlocks.POLISHED_TOPAZ_STONE_STAIRS);      dropSelf(MythicBlocks.POLISHED_PERIDOT_STONE_STAIRS);
        dropSelf(MythicBlocks.POLISHED_RUBY_STONE_STAIRS);       dropSelf(MythicBlocks.POLISHED_SAPPHIRE_STONE_STAIRS);
        dropSelf(MythicBlocks.POLISHED_JADE_STONE_STAIRS);       dropSelf(MythicBlocks.POLISHED_AMETRINE_STONE_STAIRS);
        add(MythicBlocks.AQUAMARINE_STONE_SLAB,         createSlabItemTable(MythicBlocks.AQUAMARINE_STONE_SLAB));
        add(MythicBlocks.CITRINE_STONE_SLAB,            createSlabItemTable(MythicBlocks.CITRINE_STONE_SLAB));
        add(MythicBlocks.TOPAZ_STONE_SLAB,              createSlabItemTable(MythicBlocks.TOPAZ_STONE_SLAB));
        add(MythicBlocks.PERIDOT_STONE_SLAB,            createSlabItemTable(MythicBlocks.PERIDOT_STONE_SLAB));
        add(MythicBlocks.RUBY_STONE_SLAB,               createSlabItemTable(MythicBlocks.RUBY_STONE_SLAB));
        add(MythicBlocks.SAPPHIRE_STONE_SLAB,           createSlabItemTable(MythicBlocks.SAPPHIRE_STONE_SLAB));
        add(MythicBlocks.JADE_STONE_SLAB,               createSlabItemTable(MythicBlocks.JADE_STONE_SLAB));
        add(MythicBlocks.AMETRINE_STONE_SLAB,           createSlabItemTable(MythicBlocks.AMETRINE_STONE_SLAB));
        add(MythicBlocks.POLISHED_AQUAMARINE_STONE_SLAB, createSlabItemTable(MythicBlocks.POLISHED_AQUAMARINE_STONE_SLAB));
        add(MythicBlocks.POLISHED_CITRINE_STONE_SLAB,   createSlabItemTable(MythicBlocks.POLISHED_CITRINE_STONE_SLAB));
        add(MythicBlocks.POLISHED_TOPAZ_STONE_SLAB,     createSlabItemTable(MythicBlocks.POLISHED_TOPAZ_STONE_SLAB));
        add(MythicBlocks.POLISHED_PERIDOT_STONE_SLAB,   createSlabItemTable(MythicBlocks.POLISHED_PERIDOT_STONE_SLAB));
        add(MythicBlocks.POLISHED_RUBY_STONE_SLAB,      createSlabItemTable(MythicBlocks.POLISHED_RUBY_STONE_SLAB));
        add(MythicBlocks.POLISHED_SAPPHIRE_STONE_SLAB,  createSlabItemTable(MythicBlocks.POLISHED_SAPPHIRE_STONE_SLAB));
        add(MythicBlocks.POLISHED_JADE_STONE_SLAB,      createSlabItemTable(MythicBlocks.POLISHED_JADE_STONE_SLAB));
        add(MythicBlocks.POLISHED_AMETRINE_STONE_SLAB,  createSlabItemTable(MythicBlocks.POLISHED_AMETRINE_STONE_SLAB));
    }

    private void addSilkTouchOnlyGroup(Block... blocks) {
        for (Block b : blocks) add(b, createSilkTouchOnlyTable(b));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(
            MythicBlocks.AQUAMARINE_ORE, MythicBlocks.DEEPSLATE_AQUAMARINE_ORE,
            MythicBlocks.CITRINE_ORE,   MythicBlocks.DEEPSLATE_CITRINE_ORE,
            MythicBlocks.TOPAZ_ORE,     MythicBlocks.DEEPSLATE_TOPAZ_ORE,
            MythicBlocks.PERIDOT_ORE,   MythicBlocks.DEEPSLATE_PERIDOT_ORE,
            MythicBlocks.RUBY_ORE,      MythicBlocks.SAPPHIRE_ORE,
            MythicBlocks.JADE_ORE,      MythicBlocks.AMETRINE_ORE,
            MythicBlocks.NECOIUM_ORE,   MythicBlocks.DEEPSLATE_NECOIUM_ORE,
            MythicBlocks.RAW_NECOIUM_BLOCK, MythicBlocks.NECOIUM_BLOCK,
            MythicBlocks.AQUAMARINE_BLOCK, MythicBlocks.CITRINE_BLOCK, MythicBlocks.TOPAZ_BLOCK, MythicBlocks.PERIDOT_BLOCK,
            MythicBlocks.RUBY_BLOCK,       MythicBlocks.SAPPHIRE_BLOCK, MythicBlocks.JADE_BLOCK, MythicBlocks.AMETRINE_BLOCK,
            MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK, MythicBlocks.CITRINE_CRYSTAL_BLOCK, MythicBlocks.TOPAZ_CRYSTAL_BLOCK, MythicBlocks.PERIDOT_CRYSTAL_BLOCK,
            MythicBlocks.RUBY_CRYSTAL_BLOCK,       MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK, MythicBlocks.JADE_CRYSTAL_BLOCK, MythicBlocks.AMETRINE_CRYSTAL_BLOCK,
            MythicBlocks.BUDDING_AQUAMARINE_CRYSTAL, MythicBlocks.BUDDING_CITRINE_CRYSTAL, MythicBlocks.BUDDING_TOPAZ_CRYSTAL, MythicBlocks.BUDDING_PERIDOT_CRYSTAL,
            MythicBlocks.BUDDING_RUBY_CRYSTAL,       MythicBlocks.BUDDING_SAPPHIRE_CRYSTAL, MythicBlocks.BUDDING_JADE_CRYSTAL, MythicBlocks.BUDDING_AMETRINE_CRYSTAL,
            MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER, MythicBlocks.CITRINE_CRYSTAL_CLUSTER, MythicBlocks.TOPAZ_CRYSTAL_CLUSTER, MythicBlocks.PERIDOT_CRYSTAL_CLUSTER,
            MythicBlocks.RUBY_CRYSTAL_CLUSTER,       MythicBlocks.SAPPHIRE_CRYSTAL_CLUSTER, MythicBlocks.JADE_CRYSTAL_CLUSTER, MythicBlocks.AMETRINE_CRYSTAL_CLUSTER,
            MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD, MythicBlocks.LARGE_CITRINE_CRYSTAL_BUD, MythicBlocks.LARGE_TOPAZ_CRYSTAL_BUD, MythicBlocks.LARGE_PERIDOT_CRYSTAL_BUD,
            MythicBlocks.LARGE_RUBY_CRYSTAL_BUD,       MythicBlocks.LARGE_SAPPHIRE_CRYSTAL_BUD, MythicBlocks.LARGE_JADE_CRYSTAL_BUD, MythicBlocks.LARGE_AMETRINE_CRYSTAL_BUD,
            MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD, MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD, MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD, MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD,
            MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD,       MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD, MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD, MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD,
            MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD, MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD, MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD, MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD,
            MythicBlocks.SMALL_RUBY_CRYSTAL_BUD,       MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD, MythicBlocks.SMALL_JADE_CRYSTAL_BUD, MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD,
            MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK, MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK,
            MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK,       MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK,
            MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS, MythicBlocks.CITRINE_CRYSTAL_BRICKS, MythicBlocks.TOPAZ_CRYSTAL_BRICKS, MythicBlocks.PERIDOT_CRYSTAL_BRICKS,
            MythicBlocks.RUBY_CRYSTAL_BRICKS,       MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS, MythicBlocks.JADE_CRYSTAL_BRICKS, MythicBlocks.AMETRINE_CRYSTAL_BRICKS,
            MythicBlocks.AQUAMARINE_CRYSTAL_PILLAR, MythicBlocks.CITRINE_CRYSTAL_PILLAR, MythicBlocks.TOPAZ_CRYSTAL_PILLAR, MythicBlocks.PERIDOT_CRYSTAL_PILLAR,
            MythicBlocks.RUBY_CRYSTAL_PILLAR,       MythicBlocks.SAPPHIRE_CRYSTAL_PILLAR, MythicBlocks.JADE_CRYSTAL_PILLAR, MythicBlocks.AMETRINE_CRYSTAL_PILLAR,
            MythicBlocks.CUT_AQUAMARINE_CRYSTAL_PILLAR, MythicBlocks.CUT_CITRINE_CRYSTAL_PILLAR, MythicBlocks.CUT_TOPAZ_CRYSTAL_PILLAR, MythicBlocks.CUT_PERIDOT_CRYSTAL_PILLAR,
            MythicBlocks.CUT_RUBY_CRYSTAL_PILLAR,       MythicBlocks.CUT_SAPPHIRE_CRYSTAL_PILLAR, MythicBlocks.CUT_JADE_CRYSTAL_PILLAR, MythicBlocks.CUT_AMETRINE_CRYSTAL_PILLAR,
            MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.CITRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.TOPAZ_CRYSTAL_BLOCK_SLAB, MythicBlocks.PERIDOT_CRYSTAL_BLOCK_SLAB,
            MythicBlocks.RUBY_CRYSTAL_BLOCK_SLAB,       MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_SLAB, MythicBlocks.JADE_CRYSTAL_BLOCK_SLAB, MythicBlocks.AMETRINE_CRYSTAL_BLOCK_SLAB,
            MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.CITRINE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.TOPAZ_CRYSTAL_BLOCK_STAIRS, MythicBlocks.PERIDOT_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.RUBY_CRYSTAL_BLOCK_STAIRS,       MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.JADE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.AMETRINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_SLAB,
            MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_SLAB,       MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_SLAB,
            MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_STAIRS, MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_STAIRS,       MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_SLAB, MythicBlocks.CITRINE_CRYSTAL_BRICKS_SLAB, MythicBlocks.TOPAZ_CRYSTAL_BRICKS_SLAB, MythicBlocks.PERIDOT_CRYSTAL_BRICKS_SLAB,
            MythicBlocks.RUBY_CRYSTAL_BRICKS_SLAB,       MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_SLAB, MythicBlocks.JADE_CRYSTAL_BRICKS_SLAB, MythicBlocks.AMETRINE_CRYSTAL_BRICKS_SLAB,
            MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_STAIRS, MythicBlocks.CITRINE_CRYSTAL_BRICKS_STAIRS, MythicBlocks.TOPAZ_CRYSTAL_BRICKS_STAIRS, MythicBlocks.PERIDOT_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.RUBY_CRYSTAL_BRICKS_STAIRS,       MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_STAIRS, MythicBlocks.JADE_CRYSTAL_BRICKS_STAIRS, MythicBlocks.AMETRINE_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.AQUAMARINE_STONE, MythicBlocks.CITRINE_STONE, MythicBlocks.TOPAZ_STONE, MythicBlocks.PERIDOT_STONE,
            MythicBlocks.RUBY_STONE,       MythicBlocks.SAPPHIRE_STONE, MythicBlocks.JADE_STONE, MythicBlocks.AMETRINE_STONE,
            MythicBlocks.POLISHED_AQUAMARINE_STONE, MythicBlocks.POLISHED_CITRINE_STONE, MythicBlocks.POLISHED_TOPAZ_STONE, MythicBlocks.POLISHED_PERIDOT_STONE,
            MythicBlocks.POLISHED_RUBY_STONE,       MythicBlocks.POLISHED_SAPPHIRE_STONE, MythicBlocks.POLISHED_JADE_STONE, MythicBlocks.POLISHED_AMETRINE_STONE,
            MythicBlocks.AQUAMARINE_STONE_SLAB, MythicBlocks.CITRINE_STONE_SLAB, MythicBlocks.TOPAZ_STONE_SLAB, MythicBlocks.PERIDOT_STONE_SLAB,
            MythicBlocks.RUBY_STONE_SLAB,       MythicBlocks.SAPPHIRE_STONE_SLAB, MythicBlocks.JADE_STONE_SLAB, MythicBlocks.AMETRINE_STONE_SLAB,
            MythicBlocks.AQUAMARINE_STONE_STAIRS, MythicBlocks.CITRINE_STONE_STAIRS, MythicBlocks.TOPAZ_STONE_STAIRS, MythicBlocks.PERIDOT_STONE_STAIRS,
            MythicBlocks.RUBY_STONE_STAIRS,       MythicBlocks.SAPPHIRE_STONE_STAIRS, MythicBlocks.JADE_STONE_STAIRS, MythicBlocks.AMETRINE_STONE_STAIRS,
            MythicBlocks.POLISHED_AQUAMARINE_STONE_SLAB, MythicBlocks.POLISHED_CITRINE_STONE_SLAB, MythicBlocks.POLISHED_TOPAZ_STONE_SLAB, MythicBlocks.POLISHED_PERIDOT_STONE_SLAB,
            MythicBlocks.POLISHED_RUBY_STONE_SLAB,       MythicBlocks.POLISHED_SAPPHIRE_STONE_SLAB, MythicBlocks.POLISHED_JADE_STONE_SLAB, MythicBlocks.POLISHED_AMETRINE_STONE_SLAB,
            MythicBlocks.POLISHED_AQUAMARINE_STONE_STAIRS, MythicBlocks.POLISHED_CITRINE_STONE_STAIRS, MythicBlocks.POLISHED_TOPAZ_STONE_STAIRS, MythicBlocks.POLISHED_PERIDOT_STONE_STAIRS,
            MythicBlocks.POLISHED_RUBY_STONE_STAIRS,       MythicBlocks.POLISHED_SAPPHIRE_STONE_STAIRS, MythicBlocks.POLISHED_JADE_STONE_STAIRS, MythicBlocks.POLISHED_AMETRINE_STONE_STAIRS
        );
    }
}
