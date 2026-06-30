package net.trique.mythicupgrades.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.block.MythicBlocks;

public class MythicBlockStateProvider extends BlockStateProvider {

    private static final String[] GEMS = {
        "aquamarine", "citrine", "topaz", "peridot", "ruby", "sapphire", "jade", "ametrine"
    };

    public MythicBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Constants.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (String gem : GEMS) {
            gemBlocks(gem);
        }
        simpleBlock(MythicBlocks.NECOIUM_ORE);
        simpleBlock(MythicBlocks.DEEPSLATE_NECOIUM_ORE);
        simpleBlock(MythicBlocks.RAW_NECOIUM_BLOCK);
        simpleBlock(MythicBlocks.NECOIUM_BLOCK);
    }

    private void gemBlocks(String gem) {
        simpleBlock(getBlock(gem + "_ore"));
        if (gem.equals("aquamarine") || gem.equals("citrine") || gem.equals("topaz") || gem.equals("peridot")) {
            simpleBlock(getBlock("deepslate_" + gem + "_ore"));
        }
        simpleBlock(getBlock(gem + "_block"));
        simpleBlock(getBlock(gem + "_crystal_block"));
        simpleBlock(getBlock("budding_" + gem + "_crystal"));
        clusterBlock(getBlock(gem + "_crystal_cluster"),       gem + "_crystal_cluster");
        clusterBlock(getBlock("large_" + gem + "_crystal_bud"), "large_" + gem + "_crystal_bud");
        clusterBlock(getBlock("medium_" + gem + "_crystal_bud"), "medium_" + gem + "_crystal_bud");
        clusterBlock(getBlock("small_" + gem + "_crystal_bud"), "small_" + gem + "_crystal_bud");

        simpleBlock(getBlock("polished_" + gem + "_crystal_block"));
        simpleBlock(getBlock(gem + "_crystal_bricks"));

        slabAndStairs(gem + "_crystal_block",          modLoc("block/" + gem + "_crystal_block"));
        slabAndStairs("polished_" + gem + "_crystal_block", modLoc("block/polished_" + gem + "_crystal_block"));
        slabAndStairs(gem + "_crystal_bricks",         modLoc("block/" + gem + "_crystal_bricks"));

        axisBlock((RotatedPillarBlock) getBlock(gem + "_crystal_pillar"),
            modLoc("block/" + gem + "_crystal_pillar"),
            modLoc("block/" + gem + "_crystal_pillar_top"));
        axisBlock((RotatedPillarBlock) getBlock("cut_" + gem + "_crystal_pillar"),
            modLoc("block/cut_" + gem + "_crystal_pillar"),
            modLoc("block/" + gem + "_crystal_pillar_top"));

        simpleBlock(getBlock(gem + "_schist"));
        simpleBlock(getBlock("polished_" + gem + "_schist"));
        slabAndStairs(gem + "_schist",          modLoc("block/" + gem + "_schist"));
        slabAndStairs("polished_" + gem + "_schist", modLoc("block/polished_" + gem + "_schist"));
    }

    private void slabAndStairs(String baseName, ResourceLocation texture) {
        slabBlock((SlabBlock) getBlock(baseName + "_slab"), modLoc("block/" + baseName), texture);
        stairsBlock((StairBlock) getBlock(baseName + "_stairs"), texture);
    }

    private void clusterBlock(Block b, String name) {
        ModelFile model = models().cross(name, modLoc("block/" + name)).renderType("minecraft:cutout");
        getVariantBuilder(b).forAllStatesExcept(state -> {
            int x = 0, y = 0;
            switch (state.getValue(BlockStateProperties.FACING)) {
                case DOWN  -> x = 180;
                case NORTH -> x = 90;
                case SOUTH -> { x = 90; y = 180; }
                case EAST  -> { x = 90; y = 90; }
                case WEST  -> { x = 90; y = 270; }
                default    -> {}
            }
            return ConfiguredModel.builder().modelFile(model).rotationX(x).rotationY(y).build();
        }, BlockStateProperties.WATERLOGGED);
    }

    private Block getBlock(String name) {
        Block b = switch (name) {
            case "aquamarine_ore"                       -> MythicBlocks.AQUAMARINE_ORE;
            case "deepslate_aquamarine_ore"             -> MythicBlocks.DEEPSLATE_AQUAMARINE_ORE;
            case "citrine_ore"                          -> MythicBlocks.CITRINE_ORE;
            case "deepslate_citrine_ore"                -> MythicBlocks.DEEPSLATE_CITRINE_ORE;
            case "topaz_ore"                            -> MythicBlocks.TOPAZ_ORE;
            case "deepslate_topaz_ore"                  -> MythicBlocks.DEEPSLATE_TOPAZ_ORE;
            case "peridot_ore"                          -> MythicBlocks.PERIDOT_ORE;
            case "deepslate_peridot_ore"                -> MythicBlocks.DEEPSLATE_PERIDOT_ORE;
            case "ruby_ore"                             -> MythicBlocks.RUBY_ORE;
            case "sapphire_ore"                         -> MythicBlocks.SAPPHIRE_ORE;
            case "jade_ore"                             -> MythicBlocks.JADE_ORE;
            case "ametrine_ore"                         -> MythicBlocks.AMETRINE_ORE;
            case "necoium_ore"                          -> MythicBlocks.NECOIUM_ORE;
            case "deepslate_necoium_ore"                -> MythicBlocks.DEEPSLATE_NECOIUM_ORE;
            case "aquamarine_block"                     -> MythicBlocks.AQUAMARINE_BLOCK;
            case "citrine_block"                        -> MythicBlocks.CITRINE_BLOCK;
            case "topaz_block"                          -> MythicBlocks.TOPAZ_BLOCK;
            case "peridot_block"                        -> MythicBlocks.PERIDOT_BLOCK;
            case "ruby_block"                           -> MythicBlocks.RUBY_BLOCK;
            case "sapphire_block"                       -> MythicBlocks.SAPPHIRE_BLOCK;
            case "jade_block"                           -> MythicBlocks.JADE_BLOCK;
            case "ametrine_block"                       -> MythicBlocks.AMETRINE_BLOCK;
            case "necoium_block"                        -> MythicBlocks.NECOIUM_BLOCK;
            case "raw_necoium_block"                    -> MythicBlocks.RAW_NECOIUM_BLOCK;
            case "aquamarine_crystal_block"             -> MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK;
            case "citrine_crystal_block"                -> MythicBlocks.CITRINE_CRYSTAL_BLOCK;
            case "topaz_crystal_block"                  -> MythicBlocks.TOPAZ_CRYSTAL_BLOCK;
            case "peridot_crystal_block"                -> MythicBlocks.PERIDOT_CRYSTAL_BLOCK;
            case "ruby_crystal_block"                   -> MythicBlocks.RUBY_CRYSTAL_BLOCK;
            case "sapphire_crystal_block"               -> MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK;
            case "jade_crystal_block"                   -> MythicBlocks.JADE_CRYSTAL_BLOCK;
            case "ametrine_crystal_block"               -> MythicBlocks.AMETRINE_CRYSTAL_BLOCK;
            case "budding_aquamarine_crystal"           -> MythicBlocks.BUDDING_AQUAMARINE_CRYSTAL;
            case "budding_citrine_crystal"              -> MythicBlocks.BUDDING_CITRINE_CRYSTAL;
            case "budding_topaz_crystal"                -> MythicBlocks.BUDDING_TOPAZ_CRYSTAL;
            case "budding_peridot_crystal"              -> MythicBlocks.BUDDING_PERIDOT_CRYSTAL;
            case "budding_ruby_crystal"                 -> MythicBlocks.BUDDING_RUBY_CRYSTAL;
            case "budding_sapphire_crystal"             -> MythicBlocks.BUDDING_SAPPHIRE_CRYSTAL;
            case "budding_jade_crystal"                 -> MythicBlocks.BUDDING_JADE_CRYSTAL;
            case "budding_ametrine_crystal"             -> MythicBlocks.BUDDING_AMETRINE_CRYSTAL;
            case "aquamarine_crystal_cluster"           -> MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER;
            case "citrine_crystal_cluster"              -> MythicBlocks.CITRINE_CRYSTAL_CLUSTER;
            case "topaz_crystal_cluster"                -> MythicBlocks.TOPAZ_CRYSTAL_CLUSTER;
            case "peridot_crystal_cluster"              -> MythicBlocks.PERIDOT_CRYSTAL_CLUSTER;
            case "ruby_crystal_cluster"                 -> MythicBlocks.RUBY_CRYSTAL_CLUSTER;
            case "sapphire_crystal_cluster"             -> MythicBlocks.SAPPHIRE_CRYSTAL_CLUSTER;
            case "jade_crystal_cluster"                 -> MythicBlocks.JADE_CRYSTAL_CLUSTER;
            case "ametrine_crystal_cluster"             -> MythicBlocks.AMETRINE_CRYSTAL_CLUSTER;
            case "large_aquamarine_crystal_bud"         -> MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD;
            case "large_citrine_crystal_bud"            -> MythicBlocks.LARGE_CITRINE_CRYSTAL_BUD;
            case "large_topaz_crystal_bud"              -> MythicBlocks.LARGE_TOPAZ_CRYSTAL_BUD;
            case "large_peridot_crystal_bud"            -> MythicBlocks.LARGE_PERIDOT_CRYSTAL_BUD;
            case "large_ruby_crystal_bud"               -> MythicBlocks.LARGE_RUBY_CRYSTAL_BUD;
            case "large_sapphire_crystal_bud"           -> MythicBlocks.LARGE_SAPPHIRE_CRYSTAL_BUD;
            case "large_jade_crystal_bud"               -> MythicBlocks.LARGE_JADE_CRYSTAL_BUD;
            case "large_ametrine_crystal_bud"           -> MythicBlocks.LARGE_AMETRINE_CRYSTAL_BUD;
            case "medium_aquamarine_crystal_bud"        -> MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD;
            case "medium_citrine_crystal_bud"           -> MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD;
            case "medium_topaz_crystal_bud"             -> MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD;
            case "medium_peridot_crystal_bud"           -> MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD;
            case "medium_ruby_crystal_bud"              -> MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD;
            case "medium_sapphire_crystal_bud"          -> MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD;
            case "medium_jade_crystal_bud"              -> MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD;
            case "medium_ametrine_crystal_bud"          -> MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD;
            case "small_aquamarine_crystal_bud"         -> MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD;
            case "small_citrine_crystal_bud"            -> MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD;
            case "small_topaz_crystal_bud"              -> MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD;
            case "small_peridot_crystal_bud"            -> MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD;
            case "small_ruby_crystal_bud"               -> MythicBlocks.SMALL_RUBY_CRYSTAL_BUD;
            case "small_sapphire_crystal_bud"           -> MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD;
            case "small_jade_crystal_bud"               -> MythicBlocks.SMALL_JADE_CRYSTAL_BUD;
            case "small_ametrine_crystal_bud"           -> MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD;
            case "polished_aquamarine_crystal_block"    -> MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK;
            case "polished_citrine_crystal_block"       -> MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK;
            case "polished_topaz_crystal_block"         -> MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK;
            case "polished_peridot_crystal_block"       -> MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK;
            case "polished_ruby_crystal_block"          -> MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK;
            case "polished_sapphire_crystal_block"      -> MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK;
            case "polished_jade_crystal_block"          -> MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK;
            case "polished_ametrine_crystal_block"      -> MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK;
            case "aquamarine_crystal_bricks"            -> MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS;
            case "citrine_crystal_bricks"               -> MythicBlocks.CITRINE_CRYSTAL_BRICKS;
            case "topaz_crystal_bricks"                 -> MythicBlocks.TOPAZ_CRYSTAL_BRICKS;
            case "peridot_crystal_bricks"               -> MythicBlocks.PERIDOT_CRYSTAL_BRICKS;
            case "ruby_crystal_bricks"                  -> MythicBlocks.RUBY_CRYSTAL_BRICKS;
            case "sapphire_crystal_bricks"              -> MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS;
            case "jade_crystal_bricks"                  -> MythicBlocks.JADE_CRYSTAL_BRICKS;
            case "ametrine_crystal_bricks"              -> MythicBlocks.AMETRINE_CRYSTAL_BRICKS;
            case "aquamarine_crystal_block_slab"        -> MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_SLAB;
            case "citrine_crystal_block_slab"           -> MythicBlocks.CITRINE_CRYSTAL_BLOCK_SLAB;
            case "topaz_crystal_block_slab"             -> MythicBlocks.TOPAZ_CRYSTAL_BLOCK_SLAB;
            case "peridot_crystal_block_slab"           -> MythicBlocks.PERIDOT_CRYSTAL_BLOCK_SLAB;
            case "ruby_crystal_block_slab"              -> MythicBlocks.RUBY_CRYSTAL_BLOCK_SLAB;
            case "sapphire_crystal_block_slab"          -> MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_SLAB;
            case "jade_crystal_block_slab"              -> MythicBlocks.JADE_CRYSTAL_BLOCK_SLAB;
            case "ametrine_crystal_block_slab"          -> MythicBlocks.AMETRINE_CRYSTAL_BLOCK_SLAB;
            case "aquamarine_crystal_block_stairs"      -> MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_STAIRS;
            case "citrine_crystal_block_stairs"         -> MythicBlocks.CITRINE_CRYSTAL_BLOCK_STAIRS;
            case "topaz_crystal_block_stairs"           -> MythicBlocks.TOPAZ_CRYSTAL_BLOCK_STAIRS;
            case "peridot_crystal_block_stairs"         -> MythicBlocks.PERIDOT_CRYSTAL_BLOCK_STAIRS;
            case "ruby_crystal_block_stairs"            -> MythicBlocks.RUBY_CRYSTAL_BLOCK_STAIRS;
            case "sapphire_crystal_block_stairs"        -> MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_STAIRS;
            case "jade_crystal_block_stairs"            -> MythicBlocks.JADE_CRYSTAL_BLOCK_STAIRS;
            case "ametrine_crystal_block_stairs"        -> MythicBlocks.AMETRINE_CRYSTAL_BLOCK_STAIRS;
            case "polished_aquamarine_crystal_block_slab"   -> MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_SLAB;
            case "polished_citrine_crystal_block_slab"      -> MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_SLAB;
            case "polished_topaz_crystal_block_slab"        -> MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_SLAB;
            case "polished_peridot_crystal_block_slab"      -> MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_SLAB;
            case "polished_ruby_crystal_block_slab"         -> MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_SLAB;
            case "polished_sapphire_crystal_block_slab"     -> MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_SLAB;
            case "polished_jade_crystal_block_slab"         -> MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_SLAB;
            case "polished_ametrine_crystal_block_slab"     -> MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_SLAB;
            case "polished_aquamarine_crystal_block_stairs" -> MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_STAIRS;
            case "polished_citrine_crystal_block_stairs"    -> MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_STAIRS;
            case "polished_topaz_crystal_block_stairs"      -> MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_STAIRS;
            case "polished_peridot_crystal_block_stairs"    -> MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_STAIRS;
            case "polished_ruby_crystal_block_stairs"       -> MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_STAIRS;
            case "polished_sapphire_crystal_block_stairs"   -> MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_STAIRS;
            case "polished_jade_crystal_block_stairs"       -> MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_STAIRS;
            case "polished_ametrine_crystal_block_stairs"   -> MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_STAIRS;
            case "aquamarine_crystal_bricks_slab"       -> MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_SLAB;
            case "citrine_crystal_bricks_slab"          -> MythicBlocks.CITRINE_CRYSTAL_BRICKS_SLAB;
            case "topaz_crystal_bricks_slab"            -> MythicBlocks.TOPAZ_CRYSTAL_BRICKS_SLAB;
            case "peridot_crystal_bricks_slab"          -> MythicBlocks.PERIDOT_CRYSTAL_BRICKS_SLAB;
            case "ruby_crystal_bricks_slab"             -> MythicBlocks.RUBY_CRYSTAL_BRICKS_SLAB;
            case "sapphire_crystal_bricks_slab"         -> MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_SLAB;
            case "jade_crystal_bricks_slab"             -> MythicBlocks.JADE_CRYSTAL_BRICKS_SLAB;
            case "ametrine_crystal_bricks_slab"         -> MythicBlocks.AMETRINE_CRYSTAL_BRICKS_SLAB;
            case "aquamarine_crystal_bricks_stairs"     -> MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_STAIRS;
            case "citrine_crystal_bricks_stairs"        -> MythicBlocks.CITRINE_CRYSTAL_BRICKS_STAIRS;
            case "topaz_crystal_bricks_stairs"          -> MythicBlocks.TOPAZ_CRYSTAL_BRICKS_STAIRS;
            case "peridot_crystal_bricks_stairs"        -> MythicBlocks.PERIDOT_CRYSTAL_BRICKS_STAIRS;
            case "ruby_crystal_bricks_stairs"           -> MythicBlocks.RUBY_CRYSTAL_BRICKS_STAIRS;
            case "sapphire_crystal_bricks_stairs"       -> MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_STAIRS;
            case "jade_crystal_bricks_stairs"           -> MythicBlocks.JADE_CRYSTAL_BRICKS_STAIRS;
            case "ametrine_crystal_bricks_stairs"       -> MythicBlocks.AMETRINE_CRYSTAL_BRICKS_STAIRS;
            case "aquamarine_crystal_pillar"            -> MythicBlocks.AQUAMARINE_CRYSTAL_PILLAR;
            case "citrine_crystal_pillar"               -> MythicBlocks.CITRINE_CRYSTAL_PILLAR;
            case "topaz_crystal_pillar"                 -> MythicBlocks.TOPAZ_CRYSTAL_PILLAR;
            case "peridot_crystal_pillar"               -> MythicBlocks.PERIDOT_CRYSTAL_PILLAR;
            case "ruby_crystal_pillar"                  -> MythicBlocks.RUBY_CRYSTAL_PILLAR;
            case "sapphire_crystal_pillar"              -> MythicBlocks.SAPPHIRE_CRYSTAL_PILLAR;
            case "jade_crystal_pillar"                  -> MythicBlocks.JADE_CRYSTAL_PILLAR;
            case "ametrine_crystal_pillar"              -> MythicBlocks.AMETRINE_CRYSTAL_PILLAR;
            case "cut_aquamarine_crystal_pillar"        -> MythicBlocks.CUT_AQUAMARINE_CRYSTAL_PILLAR;
            case "cut_citrine_crystal_pillar"           -> MythicBlocks.CUT_CITRINE_CRYSTAL_PILLAR;
            case "cut_topaz_crystal_pillar"             -> MythicBlocks.CUT_TOPAZ_CRYSTAL_PILLAR;
            case "cut_peridot_crystal_pillar"           -> MythicBlocks.CUT_PERIDOT_CRYSTAL_PILLAR;
            case "cut_ruby_crystal_pillar"              -> MythicBlocks.CUT_RUBY_CRYSTAL_PILLAR;
            case "cut_sapphire_crystal_pillar"          -> MythicBlocks.CUT_SAPPHIRE_CRYSTAL_PILLAR;
            case "cut_jade_crystal_pillar"              -> MythicBlocks.CUT_JADE_CRYSTAL_PILLAR;
            case "cut_ametrine_crystal_pillar"          -> MythicBlocks.CUT_AMETRINE_CRYSTAL_PILLAR;
            case "aquamarine_schist"                     -> MythicBlocks.AQUAMARINE_SCHIST;
            case "citrine_schist"                        -> MythicBlocks.CITRINE_SCHIST;
            case "topaz_schist"                          -> MythicBlocks.TOPAZ_SCHIST;
            case "peridot_schist"                        -> MythicBlocks.PERIDOT_SCHIST;
            case "ruby_schist"                           -> MythicBlocks.RUBY_SCHIST;
            case "sapphire_schist"                       -> MythicBlocks.SAPPHIRE_SCHIST;
            case "jade_schist"                           -> MythicBlocks.JADE_SCHIST;
            case "ametrine_schist"                       -> MythicBlocks.AMETRINE_SCHIST;
            case "polished_aquamarine_schist"            -> MythicBlocks.POLISHED_AQUAMARINE_SCHIST;
            case "polished_citrine_schist"               -> MythicBlocks.POLISHED_CITRINE_SCHIST;
            case "polished_topaz_schist"                 -> MythicBlocks.POLISHED_TOPAZ_SCHIST;
            case "polished_peridot_schist"               -> MythicBlocks.POLISHED_PERIDOT_SCHIST;
            case "polished_ruby_schist"                  -> MythicBlocks.POLISHED_RUBY_SCHIST;
            case "polished_sapphire_schist"              -> MythicBlocks.POLISHED_SAPPHIRE_SCHIST;
            case "polished_jade_schist"                  -> MythicBlocks.POLISHED_JADE_SCHIST;
            case "polished_ametrine_schist"              -> MythicBlocks.POLISHED_AMETRINE_SCHIST;
            case "aquamarine_schist_slab"                -> MythicBlocks.AQUAMARINE_SCHIST_SLAB;
            case "citrine_schist_slab"                   -> MythicBlocks.CITRINE_SCHIST_SLAB;
            case "topaz_schist_slab"                     -> MythicBlocks.TOPAZ_SCHIST_SLAB;
            case "peridot_schist_slab"                   -> MythicBlocks.PERIDOT_SCHIST_SLAB;
            case "ruby_schist_slab"                      -> MythicBlocks.RUBY_SCHIST_SLAB;
            case "sapphire_schist_slab"                  -> MythicBlocks.SAPPHIRE_SCHIST_SLAB;
            case "jade_schist_slab"                      -> MythicBlocks.JADE_SCHIST_SLAB;
            case "ametrine_schist_slab"                  -> MythicBlocks.AMETRINE_SCHIST_SLAB;
            case "aquamarine_schist_stairs"              -> MythicBlocks.AQUAMARINE_SCHIST_STAIRS;
            case "citrine_schist_stairs"                 -> MythicBlocks.CITRINE_SCHIST_STAIRS;
            case "topaz_schist_stairs"                   -> MythicBlocks.TOPAZ_SCHIST_STAIRS;
            case "peridot_schist_stairs"                 -> MythicBlocks.PERIDOT_SCHIST_STAIRS;
            case "ruby_schist_stairs"                    -> MythicBlocks.RUBY_SCHIST_STAIRS;
            case "sapphire_schist_stairs"                -> MythicBlocks.SAPPHIRE_SCHIST_STAIRS;
            case "jade_schist_stairs"                    -> MythicBlocks.JADE_SCHIST_STAIRS;
            case "ametrine_schist_stairs"                -> MythicBlocks.AMETRINE_SCHIST_STAIRS;
            case "polished_aquamarine_schist_slab"       -> MythicBlocks.POLISHED_AQUAMARINE_SCHIST_SLAB;
            case "polished_citrine_schist_slab"          -> MythicBlocks.POLISHED_CITRINE_SCHIST_SLAB;
            case "polished_topaz_schist_slab"            -> MythicBlocks.POLISHED_TOPAZ_SCHIST_SLAB;
            case "polished_peridot_schist_slab"          -> MythicBlocks.POLISHED_PERIDOT_SCHIST_SLAB;
            case "polished_ruby_schist_slab"             -> MythicBlocks.POLISHED_RUBY_SCHIST_SLAB;
            case "polished_sapphire_schist_slab"         -> MythicBlocks.POLISHED_SAPPHIRE_SCHIST_SLAB;
            case "polished_jade_schist_slab"             -> MythicBlocks.POLISHED_JADE_SCHIST_SLAB;
            case "polished_ametrine_schist_slab"         -> MythicBlocks.POLISHED_AMETRINE_SCHIST_SLAB;
            case "polished_aquamarine_schist_stairs"     -> MythicBlocks.POLISHED_AQUAMARINE_SCHIST_STAIRS;
            case "polished_citrine_schist_stairs"        -> MythicBlocks.POLISHED_CITRINE_SCHIST_STAIRS;
            case "polished_topaz_schist_stairs"          -> MythicBlocks.POLISHED_TOPAZ_SCHIST_STAIRS;
            case "polished_peridot_schist_stairs"        -> MythicBlocks.POLISHED_PERIDOT_SCHIST_STAIRS;
            case "polished_ruby_schist_stairs"           -> MythicBlocks.POLISHED_RUBY_SCHIST_STAIRS;
            case "polished_sapphire_schist_stairs"       -> MythicBlocks.POLISHED_SAPPHIRE_SCHIST_STAIRS;
            case "polished_jade_schist_stairs"           -> MythicBlocks.POLISHED_JADE_SCHIST_STAIRS;
            case "polished_ametrine_schist_stairs"       -> MythicBlocks.POLISHED_AMETRINE_SCHIST_STAIRS;
            default -> throw new IllegalArgumentException("Unknown block: " + name);
        };
        return b;
    }
}
