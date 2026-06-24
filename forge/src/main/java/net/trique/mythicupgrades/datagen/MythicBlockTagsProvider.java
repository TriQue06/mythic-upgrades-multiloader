package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.block.MythicBlocks;

import java.util.concurrent.CompletableFuture;

public class MythicBlockTagsProvider extends BlockTagsProvider {

    public MythicBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p) {

        // ── PICKAXE MINEABLE ──────────────────────────────────────────────────
        // Her yeni blok buraya eklenecek.
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
            // Ores
            .add(MythicBlocks.AQUAMARINE_ORE, MythicBlocks.DEEPSLATE_AQUAMARINE_ORE)
            .add(MythicBlocks.CITRINE_ORE, MythicBlocks.DEEPSLATE_CITRINE_ORE)
            .add(MythicBlocks.TOPAZ_ORE, MythicBlocks.DEEPSLATE_TOPAZ_ORE)
            .add(MythicBlocks.PERIDOT_ORE, MythicBlocks.DEEPSLATE_PERIDOT_ORE)
            .add(MythicBlocks.RUBY_ORE, MythicBlocks.SAPPHIRE_ORE)
            .add(MythicBlocks.JADE_ORE, MythicBlocks.AMETRINE_ORE)
            .add(MythicBlocks.NECOIUM_ORE, MythicBlocks.DEEPSLATE_NECOIUM_ORE)
            // Gem blocks
            .add(MythicBlocks.AQUAMARINE_BLOCK, MythicBlocks.CITRINE_BLOCK, MythicBlocks.TOPAZ_BLOCK)
            .add(MythicBlocks.PERIDOT_BLOCK, MythicBlocks.RUBY_BLOCK, MythicBlocks.SAPPHIRE_BLOCK)
            .add(MythicBlocks.JADE_BLOCK, MythicBlocks.AMETRINE_BLOCK, MythicBlocks.NECOIUM_BLOCK)
            .add(MythicBlocks.RAW_NECOIUM_BLOCK)
            // Crystal blocks & decorative
            .add(MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK, MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS, MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_SLAB, MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_STAIRS)
            .add(MythicBlocks.AQUAMARINE_CRYSTAL_PILLAR, MythicBlocks.CUT_AQUAMARINE_CRYSTAL_PILLAR)
            .add(MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER, MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD, MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD, MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD)
            .add(MythicBlocks.BUDDING_AQUAMARINE_CRYSTAL)

            .add(MythicBlocks.CITRINE_CRYSTAL_BLOCK, MythicBlocks.CITRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.CITRINE_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.CITRINE_CRYSTAL_BRICKS, MythicBlocks.CITRINE_CRYSTAL_BRICKS_SLAB, MythicBlocks.CITRINE_CRYSTAL_BRICKS_STAIRS)
            .add(MythicBlocks.CITRINE_CRYSTAL_PILLAR, MythicBlocks.CUT_CITRINE_CRYSTAL_PILLAR)
            .add(MythicBlocks.CITRINE_CRYSTAL_CLUSTER, MythicBlocks.LARGE_CITRINE_CRYSTAL_BUD, MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD, MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD)
            .add(MythicBlocks.BUDDING_CITRINE_CRYSTAL)

            .add(MythicBlocks.TOPAZ_CRYSTAL_BLOCK, MythicBlocks.TOPAZ_CRYSTAL_BLOCK_SLAB, MythicBlocks.TOPAZ_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK, MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.TOPAZ_CRYSTAL_BRICKS, MythicBlocks.TOPAZ_CRYSTAL_BRICKS_SLAB, MythicBlocks.TOPAZ_CRYSTAL_BRICKS_STAIRS)
            .add(MythicBlocks.TOPAZ_CRYSTAL_PILLAR, MythicBlocks.CUT_TOPAZ_CRYSTAL_PILLAR)
            .add(MythicBlocks.TOPAZ_CRYSTAL_CLUSTER, MythicBlocks.LARGE_TOPAZ_CRYSTAL_BUD, MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD, MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD)
            .add(MythicBlocks.BUDDING_TOPAZ_CRYSTAL)

            .add(MythicBlocks.PERIDOT_CRYSTAL_BLOCK, MythicBlocks.PERIDOT_CRYSTAL_BLOCK_SLAB, MythicBlocks.PERIDOT_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK, MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.PERIDOT_CRYSTAL_BRICKS, MythicBlocks.PERIDOT_CRYSTAL_BRICKS_SLAB, MythicBlocks.PERIDOT_CRYSTAL_BRICKS_STAIRS)
            .add(MythicBlocks.PERIDOT_CRYSTAL_PILLAR, MythicBlocks.CUT_PERIDOT_CRYSTAL_PILLAR)
            .add(MythicBlocks.PERIDOT_CRYSTAL_CLUSTER, MythicBlocks.LARGE_PERIDOT_CRYSTAL_BUD, MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD, MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD)
            .add(MythicBlocks.BUDDING_PERIDOT_CRYSTAL)

            .add(MythicBlocks.RUBY_CRYSTAL_BLOCK, MythicBlocks.RUBY_CRYSTAL_BLOCK_SLAB, MythicBlocks.RUBY_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK, MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.RUBY_CRYSTAL_BRICKS, MythicBlocks.RUBY_CRYSTAL_BRICKS_SLAB, MythicBlocks.RUBY_CRYSTAL_BRICKS_STAIRS)
            .add(MythicBlocks.RUBY_CRYSTAL_PILLAR, MythicBlocks.CUT_RUBY_CRYSTAL_PILLAR)
            .add(MythicBlocks.RUBY_CRYSTAL_CLUSTER, MythicBlocks.LARGE_RUBY_CRYSTAL_BUD, MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD, MythicBlocks.SMALL_RUBY_CRYSTAL_BUD)
            .add(MythicBlocks.BUDDING_RUBY_CRYSTAL)

            .add(MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK, MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_SLAB, MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS, MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_SLAB, MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_STAIRS)
            .add(MythicBlocks.SAPPHIRE_CRYSTAL_PILLAR, MythicBlocks.CUT_SAPPHIRE_CRYSTAL_PILLAR)
            .add(MythicBlocks.SAPPHIRE_CRYSTAL_CLUSTER, MythicBlocks.LARGE_SAPPHIRE_CRYSTAL_BUD, MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD, MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD)
            .add(MythicBlocks.BUDDING_SAPPHIRE_CRYSTAL)

            .add(MythicBlocks.JADE_CRYSTAL_BLOCK, MythicBlocks.JADE_CRYSTAL_BLOCK_SLAB, MythicBlocks.JADE_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.JADE_CRYSTAL_BRICKS, MythicBlocks.JADE_CRYSTAL_BRICKS_SLAB, MythicBlocks.JADE_CRYSTAL_BRICKS_STAIRS)
            .add(MythicBlocks.JADE_CRYSTAL_PILLAR, MythicBlocks.CUT_JADE_CRYSTAL_PILLAR)
            .add(MythicBlocks.JADE_CRYSTAL_CLUSTER, MythicBlocks.LARGE_JADE_CRYSTAL_BUD, MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD, MythicBlocks.SMALL_JADE_CRYSTAL_BUD)
            .add(MythicBlocks.BUDDING_JADE_CRYSTAL)

            .add(MythicBlocks.AMETRINE_CRYSTAL_BLOCK, MythicBlocks.AMETRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.AMETRINE_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK, MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_STAIRS)
            .add(MythicBlocks.AMETRINE_CRYSTAL_BRICKS, MythicBlocks.AMETRINE_CRYSTAL_BRICKS_SLAB, MythicBlocks.AMETRINE_CRYSTAL_BRICKS_STAIRS)
            .add(MythicBlocks.AMETRINE_CRYSTAL_PILLAR, MythicBlocks.CUT_AMETRINE_CRYSTAL_PILLAR)
            .add(MythicBlocks.AMETRINE_CRYSTAL_CLUSTER, MythicBlocks.LARGE_AMETRINE_CRYSTAL_BUD, MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD, MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD)
            .add(MythicBlocks.BUDDING_AMETRINE_CRYSTAL)

            .add(MythicBlocks.MYTHIC_UPGRADING_TABLE)

            // Stone blocks (all gems)
            .add(MythicBlocks.AQUAMARINE_STONE, MythicBlocks.AQUAMARINE_STONE_SLAB, MythicBlocks.AQUAMARINE_STONE_STAIRS)
            .add(MythicBlocks.POLISHED_AQUAMARINE_STONE, MythicBlocks.POLISHED_AQUAMARINE_STONE_SLAB, MythicBlocks.POLISHED_AQUAMARINE_STONE_STAIRS)
            .add(MythicBlocks.CITRINE_STONE, MythicBlocks.CITRINE_STONE_SLAB, MythicBlocks.CITRINE_STONE_STAIRS)
            .add(MythicBlocks.POLISHED_CITRINE_STONE, MythicBlocks.POLISHED_CITRINE_STONE_SLAB, MythicBlocks.POLISHED_CITRINE_STONE_STAIRS)
            .add(MythicBlocks.TOPAZ_STONE, MythicBlocks.TOPAZ_STONE_SLAB, MythicBlocks.TOPAZ_STONE_STAIRS)
            .add(MythicBlocks.POLISHED_TOPAZ_STONE, MythicBlocks.POLISHED_TOPAZ_STONE_SLAB, MythicBlocks.POLISHED_TOPAZ_STONE_STAIRS)
            .add(MythicBlocks.PERIDOT_STONE, MythicBlocks.PERIDOT_STONE_SLAB, MythicBlocks.PERIDOT_STONE_STAIRS)
            .add(MythicBlocks.POLISHED_PERIDOT_STONE, MythicBlocks.POLISHED_PERIDOT_STONE_SLAB, MythicBlocks.POLISHED_PERIDOT_STONE_STAIRS)
            .add(MythicBlocks.RUBY_STONE, MythicBlocks.RUBY_STONE_SLAB, MythicBlocks.RUBY_STONE_STAIRS)
            .add(MythicBlocks.POLISHED_RUBY_STONE, MythicBlocks.POLISHED_RUBY_STONE_SLAB, MythicBlocks.POLISHED_RUBY_STONE_STAIRS)
            .add(MythicBlocks.SAPPHIRE_STONE, MythicBlocks.SAPPHIRE_STONE_SLAB, MythicBlocks.SAPPHIRE_STONE_STAIRS)
            .add(MythicBlocks.POLISHED_SAPPHIRE_STONE, MythicBlocks.POLISHED_SAPPHIRE_STONE_SLAB, MythicBlocks.POLISHED_SAPPHIRE_STONE_STAIRS)
            .add(MythicBlocks.JADE_STONE, MythicBlocks.JADE_STONE_SLAB, MythicBlocks.JADE_STONE_STAIRS)
            .add(MythicBlocks.POLISHED_JADE_STONE, MythicBlocks.POLISHED_JADE_STONE_SLAB, MythicBlocks.POLISHED_JADE_STONE_STAIRS)
            .add(MythicBlocks.AMETRINE_STONE, MythicBlocks.AMETRINE_STONE_SLAB, MythicBlocks.AMETRINE_STONE_STAIRS)
            .add(MythicBlocks.POLISHED_AMETRINE_STONE, MythicBlocks.POLISHED_AMETRINE_STONE_SLAB, MythicBlocks.POLISHED_AMETRINE_STONE_STAIRS);

        // ── NEEDS IRON TOOL (en az demir kazma gerektirir) ───────────────────
        // Cevherler, külçe blokları ve ham cevher blokları buraya.
        tag(BlockTags.NEEDS_IRON_TOOL)
            .add(MythicBlocks.AQUAMARINE_ORE, MythicBlocks.DEEPSLATE_AQUAMARINE_ORE, MythicBlocks.AQUAMARINE_BLOCK)
            .add(MythicBlocks.CITRINE_ORE, MythicBlocks.DEEPSLATE_CITRINE_ORE, MythicBlocks.CITRINE_BLOCK)
            .add(MythicBlocks.TOPAZ_ORE, MythicBlocks.DEEPSLATE_TOPAZ_ORE, MythicBlocks.TOPAZ_BLOCK)
            .add(MythicBlocks.PERIDOT_ORE, MythicBlocks.DEEPSLATE_PERIDOT_ORE, MythicBlocks.PERIDOT_BLOCK)
            .add(MythicBlocks.RUBY_ORE, MythicBlocks.RUBY_BLOCK)
            .add(MythicBlocks.SAPPHIRE_ORE, MythicBlocks.SAPPHIRE_BLOCK)
            .add(MythicBlocks.JADE_ORE, MythicBlocks.JADE_BLOCK)
            .add(MythicBlocks.AMETRINE_ORE, MythicBlocks.AMETRINE_BLOCK)
            .add(MythicBlocks.NECOIUM_ORE, MythicBlocks.DEEPSLATE_NECOIUM_ORE, MythicBlocks.NECOIUM_BLOCK, MythicBlocks.RAW_NECOIUM_BLOCK);

        // ── NEEDS DIAMOND TOOL (en az elmas kazma gerektirir) ────────────────
        // Gerekirse buraya ekle.
        // tag(BlockTags.NEEDS_DIAMOND_TOOL)
        //     .add(MythicBlocks.NECOIUM_ORE);
    }
}
