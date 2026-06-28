package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.block.MythicBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MythicBlockTagsProvider extends BlockTagsProvider {

    public MythicBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                   @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            // Ores
            .add(MythicBlocks.AQUAMARINE_ORE, MythicBlocks.DEEPSLATE_AQUAMARINE_ORE)
            .add(MythicBlocks.CITRINE_ORE, MythicBlocks.DEEPSLATE_CITRINE_ORE)
            .add(MythicBlocks.TOPAZ_ORE, MythicBlocks.DEEPSLATE_TOPAZ_ORE)
            .add(MythicBlocks.PERIDOT_ORE, MythicBlocks.DEEPSLATE_PERIDOT_ORE)
            .add(MythicBlocks.RUBY_ORE)
            .add(MythicBlocks.SAPPHIRE_ORE)
            .add(MythicBlocks.JADE_ORE)
            .add(MythicBlocks.AMETRINE_ORE)
            .add(MythicBlocks.NECOIUM_ORE, MythicBlocks.DEEPSLATE_NECOIUM_ORE)
            // Gem storage blocks
            .add(MythicBlocks.AQUAMARINE_BLOCK, MythicBlocks.CITRINE_BLOCK, MythicBlocks.TOPAZ_BLOCK, MythicBlocks.PERIDOT_BLOCK)
            .add(MythicBlocks.RUBY_BLOCK, MythicBlocks.SAPPHIRE_BLOCK, MythicBlocks.JADE_BLOCK, MythicBlocks.AMETRINE_BLOCK)
            .add(MythicBlocks.RAW_NECOIUM_BLOCK, MythicBlocks.NECOIUM_BLOCK)
            // Crystal blocks
            .add(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK, MythicBlocks.CITRINE_CRYSTAL_BLOCK, MythicBlocks.TOPAZ_CRYSTAL_BLOCK, MythicBlocks.PERIDOT_CRYSTAL_BLOCK)
            .add(MythicBlocks.RUBY_CRYSTAL_BLOCK, MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK, MythicBlocks.JADE_CRYSTAL_BLOCK, MythicBlocks.AMETRINE_CRYSTAL_BLOCK)
            .add(MythicBlocks.BUDDING_AQUAMARINE_CRYSTAL, MythicBlocks.BUDDING_CITRINE_CRYSTAL, MythicBlocks.BUDDING_TOPAZ_CRYSTAL, MythicBlocks.BUDDING_PERIDOT_CRYSTAL)
            .add(MythicBlocks.BUDDING_RUBY_CRYSTAL, MythicBlocks.BUDDING_SAPPHIRE_CRYSTAL, MythicBlocks.BUDDING_JADE_CRYSTAL, MythicBlocks.BUDDING_AMETRINE_CRYSTAL)
            // Clusters and buds
            .add(MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER, MythicBlocks.CITRINE_CRYSTAL_CLUSTER, MythicBlocks.TOPAZ_CRYSTAL_CLUSTER, MythicBlocks.PERIDOT_CRYSTAL_CLUSTER)
            .add(MythicBlocks.RUBY_CRYSTAL_CLUSTER, MythicBlocks.SAPPHIRE_CRYSTAL_CLUSTER, MythicBlocks.JADE_CRYSTAL_CLUSTER, MythicBlocks.AMETRINE_CRYSTAL_CLUSTER)
            .add(MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD, MythicBlocks.LARGE_CITRINE_CRYSTAL_BUD, MythicBlocks.LARGE_TOPAZ_CRYSTAL_BUD, MythicBlocks.LARGE_PERIDOT_CRYSTAL_BUD)
            .add(MythicBlocks.LARGE_RUBY_CRYSTAL_BUD, MythicBlocks.LARGE_SAPPHIRE_CRYSTAL_BUD, MythicBlocks.LARGE_JADE_CRYSTAL_BUD, MythicBlocks.LARGE_AMETRINE_CRYSTAL_BUD)
            .add(MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD, MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD, MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD, MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD)
            .add(MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD, MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD, MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD, MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD)
            .add(MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD, MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD, MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD, MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD)
            .add(MythicBlocks.SMALL_RUBY_CRYSTAL_BUD, MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD, MythicBlocks.SMALL_JADE_CRYSTAL_BUD, MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD)
            // Decorative crystal blocks (polished, bricks, pillars, slabs, stairs)
            .add(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK, MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK)
            .add(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK, MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK)
            .add(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS, MythicBlocks.CITRINE_CRYSTAL_BRICKS, MythicBlocks.TOPAZ_CRYSTAL_BRICKS, MythicBlocks.PERIDOT_CRYSTAL_BRICKS)
            .add(MythicBlocks.RUBY_CRYSTAL_BRICKS, MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS, MythicBlocks.JADE_CRYSTAL_BRICKS, MythicBlocks.AMETRINE_CRYSTAL_BRICKS)
            .add(MythicBlocks.AQUAMARINE_CRYSTAL_PILLAR, MythicBlocks.CITRINE_CRYSTAL_PILLAR, MythicBlocks.TOPAZ_CRYSTAL_PILLAR, MythicBlocks.PERIDOT_CRYSTAL_PILLAR)
            .add(MythicBlocks.RUBY_CRYSTAL_PILLAR, MythicBlocks.SAPPHIRE_CRYSTAL_PILLAR, MythicBlocks.JADE_CRYSTAL_PILLAR, MythicBlocks.AMETRINE_CRYSTAL_PILLAR)
            .add(MythicBlocks.CUT_AQUAMARINE_CRYSTAL_PILLAR, MythicBlocks.CUT_CITRINE_CRYSTAL_PILLAR, MythicBlocks.CUT_TOPAZ_CRYSTAL_PILLAR, MythicBlocks.CUT_PERIDOT_CRYSTAL_PILLAR)
            .add(MythicBlocks.CUT_RUBY_CRYSTAL_PILLAR, MythicBlocks.CUT_SAPPHIRE_CRYSTAL_PILLAR, MythicBlocks.CUT_JADE_CRYSTAL_PILLAR, MythicBlocks.CUT_AMETRINE_CRYSTAL_PILLAR)
            .add(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.CITRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.TOPAZ_CRYSTAL_BLOCK_SLAB, MythicBlocks.PERIDOT_CRYSTAL_BLOCK_SLAB)
            .add(MythicBlocks.RUBY_CRYSTAL_BLOCK_SLAB, MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_SLAB, MythicBlocks.JADE_CRYSTAL_BLOCK_SLAB, MythicBlocks.AMETRINE_CRYSTAL_BLOCK_SLAB)
            .add(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.CITRINE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.TOPAZ_CRYSTAL_BLOCK_STAIRS, MythicBlocks.PERIDOT_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.RUBY_CRYSTAL_BLOCK_STAIRS, MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.JADE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.AMETRINE_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_SLAB)
            .add(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_SLAB)
            .add(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_STAIRS, MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_STAIRS, MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_STAIRS, MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_SLAB, MythicBlocks.CITRINE_CRYSTAL_BRICKS_SLAB, MythicBlocks.TOPAZ_CRYSTAL_BRICKS_SLAB, MythicBlocks.PERIDOT_CRYSTAL_BRICKS_SLAB)
            .add(MythicBlocks.RUBY_CRYSTAL_BRICKS_SLAB, MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_SLAB, MythicBlocks.JADE_CRYSTAL_BRICKS_SLAB, MythicBlocks.AMETRINE_CRYSTAL_BRICKS_SLAB)
            .add(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_STAIRS, MythicBlocks.CITRINE_CRYSTAL_BRICKS_STAIRS, MythicBlocks.TOPAZ_CRYSTAL_BRICKS_STAIRS, MythicBlocks.PERIDOT_CRYSTAL_BRICKS_STAIRS)
            .add(MythicBlocks.RUBY_CRYSTAL_BRICKS_STAIRS, MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_STAIRS, MythicBlocks.JADE_CRYSTAL_BRICKS_STAIRS, MythicBlocks.AMETRINE_CRYSTAL_BRICKS_STAIRS)
            // Stone variants
            .add(MythicBlocks.AQUAMARINE_STONE, MythicBlocks.CITRINE_STONE, MythicBlocks.TOPAZ_STONE, MythicBlocks.PERIDOT_STONE)
            .add(MythicBlocks.RUBY_STONE, MythicBlocks.SAPPHIRE_STONE, MythicBlocks.JADE_STONE, MythicBlocks.AMETRINE_STONE)
            .add(MythicBlocks.POLISHED_AQUAMARINE_STONE, MythicBlocks.POLISHED_CITRINE_STONE, MythicBlocks.POLISHED_TOPAZ_STONE, MythicBlocks.POLISHED_PERIDOT_STONE)
            .add(MythicBlocks.POLISHED_RUBY_STONE, MythicBlocks.POLISHED_SAPPHIRE_STONE, MythicBlocks.POLISHED_JADE_STONE, MythicBlocks.POLISHED_AMETRINE_STONE)
            .add(MythicBlocks.AQUAMARINE_STONE_SLAB, MythicBlocks.CITRINE_STONE_SLAB, MythicBlocks.TOPAZ_STONE_SLAB, MythicBlocks.PERIDOT_STONE_SLAB)
            .add(MythicBlocks.RUBY_STONE_SLAB, MythicBlocks.SAPPHIRE_STONE_SLAB, MythicBlocks.JADE_STONE_SLAB, MythicBlocks.AMETRINE_STONE_SLAB)
            .add(MythicBlocks.AQUAMARINE_STONE_STAIRS, MythicBlocks.CITRINE_STONE_STAIRS, MythicBlocks.TOPAZ_STONE_STAIRS, MythicBlocks.PERIDOT_STONE_STAIRS)
            .add(MythicBlocks.RUBY_STONE_STAIRS, MythicBlocks.SAPPHIRE_STONE_STAIRS, MythicBlocks.JADE_STONE_STAIRS, MythicBlocks.AMETRINE_STONE_STAIRS)
            .add(MythicBlocks.POLISHED_AQUAMARINE_STONE_SLAB, MythicBlocks.POLISHED_CITRINE_STONE_SLAB, MythicBlocks.POLISHED_TOPAZ_STONE_SLAB, MythicBlocks.POLISHED_PERIDOT_STONE_SLAB)
            .add(MythicBlocks.POLISHED_RUBY_STONE_SLAB, MythicBlocks.POLISHED_SAPPHIRE_STONE_SLAB, MythicBlocks.POLISHED_JADE_STONE_SLAB, MythicBlocks.POLISHED_AMETRINE_STONE_SLAB)
            .add(MythicBlocks.POLISHED_AQUAMARINE_STONE_STAIRS, MythicBlocks.POLISHED_CITRINE_STONE_STAIRS, MythicBlocks.POLISHED_TOPAZ_STONE_STAIRS, MythicBlocks.POLISHED_PERIDOT_STONE_STAIRS)
            .add(MythicBlocks.POLISHED_RUBY_STONE_STAIRS, MythicBlocks.POLISHED_SAPPHIRE_STONE_STAIRS, MythicBlocks.POLISHED_JADE_STONE_STAIRS, MythicBlocks.POLISHED_AMETRINE_STONE_STAIRS);

        tag(BlockTags.NEEDS_IRON_TOOL)
            // Only ores and gem/necoium storage blocks require iron tool
            .add(MythicBlocks.AQUAMARINE_ORE, MythicBlocks.DEEPSLATE_AQUAMARINE_ORE)
            .add(MythicBlocks.CITRINE_ORE, MythicBlocks.DEEPSLATE_CITRINE_ORE)
            .add(MythicBlocks.TOPAZ_ORE, MythicBlocks.DEEPSLATE_TOPAZ_ORE)
            .add(MythicBlocks.PERIDOT_ORE, MythicBlocks.DEEPSLATE_PERIDOT_ORE)
            .add(MythicBlocks.RUBY_ORE)
            .add(MythicBlocks.SAPPHIRE_ORE)
            .add(MythicBlocks.JADE_ORE)
            .add(MythicBlocks.AMETRINE_ORE)
            .add(MythicBlocks.NECOIUM_ORE, MythicBlocks.DEEPSLATE_NECOIUM_ORE)
            .add(MythicBlocks.AQUAMARINE_BLOCK, MythicBlocks.CITRINE_BLOCK, MythicBlocks.TOPAZ_BLOCK, MythicBlocks.PERIDOT_BLOCK)
            .add(MythicBlocks.RUBY_BLOCK, MythicBlocks.SAPPHIRE_BLOCK, MythicBlocks.JADE_BLOCK, MythicBlocks.AMETRINE_BLOCK)
            .add(MythicBlocks.RAW_NECOIUM_BLOCK, MythicBlocks.NECOIUM_BLOCK);
    }
}
