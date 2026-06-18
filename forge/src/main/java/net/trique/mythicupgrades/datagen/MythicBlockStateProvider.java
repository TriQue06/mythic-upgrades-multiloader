package net.trique.mythicupgrades.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.block.MythicBlocks;

import java.util.Objects;

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
        simpleBlock(MythicBlocks.RAW_NECOIUM_BLOCK);
        simpleBlock(MythicBlocks.NECOIUM_BLOCK);
        simpleBlock(MythicBlocks.MYTHIC_UPGRADING_TABLE,
            models().getExistingFile(modLoc("block/mythic_upgrading_table")));
    }

    private void gemBlocks(String gem) {
        simpleBlock(block(gem + "_ore"));
        if (gem.equals("aquamarine") || gem.equals("citrine") || gem.equals("topaz") || gem.equals("peridot")) {
            simpleBlock(block("deepslate_" + gem + "_ore"));
        }
        simpleBlock(block(gem + "_block"));
        simpleBlock(block(gem + "_crystal_block"));
        simpleBlock(block("budding_" + gem + "_crystal"));
        clusterBlock(block(gem + "_crystal_cluster"), gem + "_crystal_cluster");
        clusterBlock(block("large_" + gem + "_crystal_bud"), "large_" + gem + "_crystal_bud");
        clusterBlock(block("medium_" + gem + "_crystal_bud"), "medium_" + gem + "_crystal_bud");
        clusterBlock(block("small_" + gem + "_crystal_bud"), "small_" + gem + "_crystal_bud");

        simpleBlock(block("polished_" + gem + "_crystal_block"));
        simpleBlock(block(gem + "_crystal_bricks"));

        slabAndStairs(gem + "_crystal_block", modLoc("block/" + gem + "_crystal_block"));
        slabAndStairs("polished_" + gem + "_crystal_block", modLoc("block/polished_" + gem + "_crystal_block"));
        slabAndStairs(gem + "_crystal_bricks", modLoc("block/" + gem + "_crystal_bricks"));

        axisBlock((RotatedPillarBlock) block(gem + "_crystal_pillar"),
            modLoc("block/" + gem + "_crystal_pillar"),
            modLoc("block/" + gem + "_crystal_pillar_top"));
        axisBlock((RotatedPillarBlock) block("cut_" + gem + "_crystal_pillar"),
            modLoc("block/cut_" + gem + "_crystal_pillar"),
            modLoc("block/" + gem + "_crystal_pillar_top"));
    }

    private void slabAndStairs(String baseName, ResourceLocation texture) {
        slabBlock((SlabBlock) block(baseName + "_slab"), modLoc("block/" + baseName), texture);
        stairsBlock((StairBlock) block(baseName + "_stairs"), texture);
    }

    private void clusterBlock(Block b, String name) {
        ModelFile model = models().cross(name, modLoc("block/" + name));
        getVariantBuilder(b).forAllStatesExcept(state -> {
            int x = 0, y = 0;
            switch (state.getValue(BlockStateProperties.FACING)) {
                case DOWN -> x = 180;
                case NORTH -> x = 90;
                case SOUTH -> { x = 90; y = 180; }
                case EAST -> { x = 90; y = 90; }
                case WEST -> { x = 90; y = 270; }
                default -> {}
            }
            return ConfiguredModel.builder().modelFile(model).rotationX(x).rotationY(y).build();
        }, BlockStateProperties.WATERLOGGED);
    }

    private Block block(String name) {
        return Objects.requireNonNull(
            ForgeRegistries.BLOCKS.getValue(new ResourceLocation(Constants.MOD_ID, name)),
            "Block not found: " + name
        );
    }
}
