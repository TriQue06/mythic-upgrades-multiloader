package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.BlockReplacement;
import net.minecraft.world.level.levelgen.feature.GeodeFeature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.trique.mythicupgrades.worldgen.feature.CrystalBudFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;

import java.util.List;

public class MythicNetherConfiguredFeatures {

    public static void bootstrap(BootstrapContext<Feature> ctx) {
        HolderGetter<Block> blocks = ctx.lookup(Registries.BLOCK);

        for (NetherGemType gem : NetherGemType.values()) {
            Block stone = blocks.getOrThrow(gem.stoneBlock()).value();
            Block crystal = blocks.getOrThrow(gem.crystalBlock()).value();
            Block ore = blocks.getOrThrow(gem.oreBlock()).value();

            ctx.register(gem.stoneBlobsCF(), new OreFeature(List.of(
                    BlockReplacement.replace(new BlockMatchTest(Blocks.NETHERRACK), stone.defaultBlockState())
                ), 64));

            ctx.register(gem.crystalBlobsCF(), new OreFeature(List.of(
                    BlockReplacement.replace(new BlockMatchTest(Blocks.NETHERRACK), crystal.defaultBlockState()),
                    BlockReplacement.replace(new BlockMatchTest(stone), crystal.defaultBlockState())
                ), 20));

            ctx.register(gem.crystalBudsCF(), new CrystalBudFeature(
                    Holder.direct(new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(budState(blocks, gem.mediumBud()), 3)
                        .add(budState(blocks, gem.largeBud()), 2)
                        .add(budState(blocks, gem.cluster()), 1))),
                    96, 5, 4
                ));


            ctx.register(gem.oreCF(), new OreFeature(List.of(
                    BlockReplacement.replace(new BlockMatchTest(Blocks.NETHERRACK), ore.defaultBlockState())
                ), 6));

            Block buddingBlock = blocks.getOrThrow(gem.buddingCrystal()).value();
            Block smallBud = blocks.getOrThrow(gem.smallBud()).value();
            Block mediumBud = blocks.getOrThrow(gem.mediumBud()).value();
            Block largeBud = blocks.getOrThrow(gem.largeBud()).value();
            Block clusterBlock = blocks.getOrThrow(gem.cluster()).value();

            ctx.register(gem.geodeCF(), new GeodeFeature(
                    new GeodeBlockSettings(
                        BlockStateProvider.holderOf(Blocks.AIR),
                        BlockStateProvider.holderOf(crystal),
                        BlockStateProvider.holderOf(buddingBlock),
                        BlockStateProvider.holderOf(Blocks.CALCITE),
                        BlockStateProvider.holderOf(Blocks.SMOOTH_BASALT),
                        List.of(
                            smallBud.defaultBlockState(),
                            mediumBud.defaultBlockState(),
                            largeBud.defaultBlockState(),
                            clusterBlock.defaultBlockState()
                        ),
                        blocks.getOrThrow(BlockTags.FEATURES_CANNOT_REPLACE),
                        blocks.getOrThrow(BlockTags.GEODE_INVALID_BLOCKS)
                    ),
                    new GeodeLayerSettings(1.7, 2.2, 3.2, 4.2),
                    new GeodeCrackSettings(0.95, 2.0, 2),
                    0.35, 0.083, true,
                    UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2),
                    -16, 16, 0.05, 1
                ));
        }
    }

    private static BlockState budState(HolderGetter<Block> blocks, net.minecraft.resources.ResourceKey<Block> key) {
        BlockState state = blocks.getOrThrow(key).value().defaultBlockState();
        if (state.hasProperty(BlockStateProperties.FACING))
            state = state.setValue(BlockStateProperties.FACING, Direction.UP);
        if (state.hasProperty(BlockStateProperties.WATERLOGGED))
            state = state.setValue(BlockStateProperties.WATERLOGGED, false);
        return state;
    }
}
