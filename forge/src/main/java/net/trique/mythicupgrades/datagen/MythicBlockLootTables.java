package net.trique.mythicupgrades.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
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
        // Overworld ores — silk touch drops block, otherwise fortune-boosted gem
        add(MythicBlocks.AQUAMARINE_ORE,           block -> createOreDrop(block, MythicItems.AQUAMARINE.asItem()));
        add(MythicBlocks.DEEPSLATE_AQUAMARINE_ORE, block -> createOreDrop(block, MythicItems.AQUAMARINE.asItem()));
        add(MythicBlocks.KYANITE_ORE,              block -> createOreDrop(block, MythicItems.KYANITE.asItem()));
        add(MythicBlocks.DEEPSLATE_KYANITE_ORE,    block -> createOreDrop(block, MythicItems.KYANITE.asItem()));
        add(MythicBlocks.CITRINE_ORE,              block -> createOreDrop(block, MythicItems.CITRINE.asItem()));
        add(MythicBlocks.DEEPSLATE_CITRINE_ORE,    block -> createOreDrop(block, MythicItems.CITRINE.asItem()));
        add(MythicBlocks.TOPAZ_ORE,                block -> createOreDrop(block, MythicItems.TOPAZ.asItem()));
        add(MythicBlocks.DEEPSLATE_TOPAZ_ORE,      block -> createOreDrop(block, MythicItems.TOPAZ.asItem()));
        add(MythicBlocks.PERIDOT_ORE,              block -> createOreDrop(block, MythicItems.PERIDOT.asItem()));
        add(MythicBlocks.DEEPSLATE_PERIDOT_ORE,    block -> createOreDrop(block, MythicItems.PERIDOT.asItem()));

        // Nether ores
        add(MythicBlocks.RUBY_ORE,     block -> createOreDrop(block, MythicItems.RUBY.asItem()));
        add(MythicBlocks.SAPPHIRE_ORE, block -> createOreDrop(block, MythicItems.SAPPHIRE.asItem()));

        // End ores — necoium drops raw_necoium (metal workflow)
        add(MythicBlocks.JADE_ORE,     block -> createOreDrop(block, MythicItems.JADE.asItem()));
        add(MythicBlocks.AMETRINE_ORE, block -> createOreDrop(block, MythicItems.AMETRINE.asItem()));
        add(MythicBlocks.NECOIUM_ORE,  block -> createOreDrop(block, MythicItems.RAW_NECOIUM.asItem()));

        // Storage blocks — always drop themselves
        dropSelf(MythicBlocks.RAW_NECOIUM_BLOCK);
        dropSelf(MythicBlocks.AQUAMARINE_BLOCK);
        dropSelf(MythicBlocks.KYANITE_BLOCK);
        dropSelf(MythicBlocks.CITRINE_BLOCK);
        dropSelf(MythicBlocks.TOPAZ_BLOCK);
        dropSelf(MythicBlocks.PERIDOT_BLOCK);
        dropSelf(MythicBlocks.RUBY_BLOCK);
        dropSelf(MythicBlocks.SAPPHIRE_BLOCK);
        dropSelf(MythicBlocks.JADE_BLOCK);
        dropSelf(MythicBlocks.AMETRINE_BLOCK);
        dropSelf(MythicBlocks.NECOIUM_BLOCK);

        // Crystal blocks — always drop themselves
        dropSelf(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.KYANITE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.CITRINE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.TOPAZ_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.PERIDOT_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.RUBY_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.JADE_CRYSTAL_BLOCK);
        dropSelf(MythicBlocks.AMETRINE_CRYSTAL_BLOCK);

        // Budding crystal blocks — silk touch only, nothing otherwise
        add(MythicBlocks.BUDDING_AQUAMARINE_CRYSTAL, createSilkTouchOnlyTable(MythicBlocks.BUDDING_AQUAMARINE_CRYSTAL));
        add(MythicBlocks.BUDDING_KYANITE_CRYSTAL,    createSilkTouchOnlyTable(MythicBlocks.BUDDING_KYANITE_CRYSTAL));
        add(MythicBlocks.BUDDING_CITRINE_CRYSTAL,    createSilkTouchOnlyTable(MythicBlocks.BUDDING_CITRINE_CRYSTAL));
        add(MythicBlocks.BUDDING_TOPAZ_CRYSTAL,      createSilkTouchOnlyTable(MythicBlocks.BUDDING_TOPAZ_CRYSTAL));
        add(MythicBlocks.BUDDING_PERIDOT_CRYSTAL,    createSilkTouchOnlyTable(MythicBlocks.BUDDING_PERIDOT_CRYSTAL));
        add(MythicBlocks.BUDDING_RUBY_CRYSTAL,       createSilkTouchOnlyTable(MythicBlocks.BUDDING_RUBY_CRYSTAL));
        add(MythicBlocks.BUDDING_SAPPHIRE_CRYSTAL,   createSilkTouchOnlyTable(MythicBlocks.BUDDING_SAPPHIRE_CRYSTAL));
        add(MythicBlocks.BUDDING_JADE_CRYSTAL,       createSilkTouchOnlyTable(MythicBlocks.BUDDING_JADE_CRYSTAL));
        add(MythicBlocks.BUDDING_AMETRINE_CRYSTAL,   createSilkTouchOnlyTable(MythicBlocks.BUDDING_AMETRINE_CRYSTAL));

        // Small / medium buds — silk touch only
        add(MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD,  createSilkTouchOnlyTable(MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD, createSilkTouchOnlyTable(MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_KYANITE_CRYSTAL_BUD,     createSilkTouchOnlyTable(MythicBlocks.SMALL_KYANITE_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_KYANITE_CRYSTAL_BUD,    createSilkTouchOnlyTable(MythicBlocks.MEDIUM_KYANITE_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD,     createSilkTouchOnlyTable(MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD,    createSilkTouchOnlyTable(MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD,       createSilkTouchOnlyTable(MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD,      createSilkTouchOnlyTable(MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD,     createSilkTouchOnlyTable(MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD,    createSilkTouchOnlyTable(MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_RUBY_CRYSTAL_BUD,        createSilkTouchOnlyTable(MythicBlocks.SMALL_RUBY_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD,       createSilkTouchOnlyTable(MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD,    createSilkTouchOnlyTable(MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD,   createSilkTouchOnlyTable(MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_JADE_CRYSTAL_BUD,        createSilkTouchOnlyTable(MythicBlocks.SMALL_JADE_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD,       createSilkTouchOnlyTable(MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD));
        add(MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD,    createSilkTouchOnlyTable(MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD));
        add(MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD,   createSilkTouchOnlyTable(MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD));

        // Large buds — 1 shard without silk touch, self with silk touch
        add(MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD, block -> createSingleItemTableWithSilkTouch(block, MythicItems.AQUAMARINE_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_KYANITE_CRYSTAL_BUD,    block -> createSingleItemTableWithSilkTouch(block, MythicItems.KYANITE_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_CITRINE_CRYSTAL_BUD,    block -> createSingleItemTableWithSilkTouch(block, MythicItems.CITRINE_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_TOPAZ_CRYSTAL_BUD,      block -> createSingleItemTableWithSilkTouch(block, MythicItems.TOPAZ_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_PERIDOT_CRYSTAL_BUD,    block -> createSingleItemTableWithSilkTouch(block, MythicItems.PERIDOT_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_RUBY_CRYSTAL_BUD,       block -> createSingleItemTableWithSilkTouch(block, MythicItems.RUBY_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_SAPPHIRE_CRYSTAL_BUD,   block -> createSingleItemTableWithSilkTouch(block, MythicItems.SAPPHIRE_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_JADE_CRYSTAL_BUD,       block -> createSingleItemTableWithSilkTouch(block, MythicItems.JADE_CRYSTAL_SHARD));
        add(MythicBlocks.LARGE_AMETRINE_CRYSTAL_BUD,   block -> createSingleItemTableWithSilkTouch(block, MythicItems.AMETRINE_CRYSTAL_SHARD));

        // Clusters — 4 shards (fortune) without silk touch, self with silk touch
        add(MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER, block -> createClusterDrop(block, MythicItems.AQUAMARINE_CRYSTAL_SHARD));
        add(MythicBlocks.KYANITE_CRYSTAL_CLUSTER,    block -> createClusterDrop(block, MythicItems.KYANITE_CRYSTAL_SHARD));
        add(MythicBlocks.CITRINE_CRYSTAL_CLUSTER,    block -> createClusterDrop(block, MythicItems.CITRINE_CRYSTAL_SHARD));
        add(MythicBlocks.TOPAZ_CRYSTAL_CLUSTER,      block -> createClusterDrop(block, MythicItems.TOPAZ_CRYSTAL_SHARD));
        add(MythicBlocks.PERIDOT_CRYSTAL_CLUSTER,    block -> createClusterDrop(block, MythicItems.PERIDOT_CRYSTAL_SHARD));
        add(MythicBlocks.RUBY_CRYSTAL_CLUSTER,       block -> createClusterDrop(block, MythicItems.RUBY_CRYSTAL_SHARD));
        add(MythicBlocks.SAPPHIRE_CRYSTAL_CLUSTER,   block -> createClusterDrop(block, MythicItems.SAPPHIRE_CRYSTAL_SHARD));
        add(MythicBlocks.JADE_CRYSTAL_CLUSTER,       block -> createClusterDrop(block, MythicItems.JADE_CRYSTAL_SHARD));
        add(MythicBlocks.AMETRINE_CRYSTAL_CLUSTER,   block -> createClusterDrop(block, MythicItems.AMETRINE_CRYSTAL_SHARD));
    }

    private LootTable.Builder createClusterDrop(Block block, Item shard) {
        return createSilkTouchDispatchTable(block,
            applyExplosionDecay(block,
                LootItem.lootTableItem(shard)
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                    .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(
            MythicBlocks.AQUAMARINE_ORE, MythicBlocks.DEEPSLATE_AQUAMARINE_ORE,
            MythicBlocks.KYANITE_ORE,    MythicBlocks.DEEPSLATE_KYANITE_ORE,
            MythicBlocks.CITRINE_ORE,    MythicBlocks.DEEPSLATE_CITRINE_ORE,
            MythicBlocks.TOPAZ_ORE,      MythicBlocks.DEEPSLATE_TOPAZ_ORE,
            MythicBlocks.PERIDOT_ORE,    MythicBlocks.DEEPSLATE_PERIDOT_ORE,
            MythicBlocks.RUBY_ORE,       MythicBlocks.SAPPHIRE_ORE,
            MythicBlocks.JADE_ORE,       MythicBlocks.AMETRINE_ORE, MythicBlocks.NECOIUM_ORE,
            MythicBlocks.RAW_NECOIUM_BLOCK,
            MythicBlocks.AQUAMARINE_BLOCK, MythicBlocks.KYANITE_BLOCK,
            MythicBlocks.CITRINE_BLOCK,    MythicBlocks.TOPAZ_BLOCK,
            MythicBlocks.PERIDOT_BLOCK,    MythicBlocks.RUBY_BLOCK,
            MythicBlocks.SAPPHIRE_BLOCK,   MythicBlocks.JADE_BLOCK,
            MythicBlocks.AMETRINE_BLOCK,   MythicBlocks.NECOIUM_BLOCK,
            // Crystal blocks
            MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK, MythicBlocks.BUDDING_AQUAMARINE_CRYSTAL,
            MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER, MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD,
            MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD, MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD,
            MythicBlocks.KYANITE_CRYSTAL_BLOCK, MythicBlocks.BUDDING_KYANITE_CRYSTAL,
            MythicBlocks.KYANITE_CRYSTAL_CLUSTER, MythicBlocks.LARGE_KYANITE_CRYSTAL_BUD,
            MythicBlocks.MEDIUM_KYANITE_CRYSTAL_BUD, MythicBlocks.SMALL_KYANITE_CRYSTAL_BUD,
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
            MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD, MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD
        );
    }
}
