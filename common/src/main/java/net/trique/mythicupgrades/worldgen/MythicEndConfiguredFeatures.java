package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.trique.mythicupgrades.worldgen.feature.CrystalBudFeatureConfig;

import java.util.List;

public class MythicEndConfiguredFeatures {

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> ctx) {
        HolderGetter<Block> blocks = ctx.lookup(Registries.BLOCK);

        for (EndGemType gem : EndGemType.values()) {
            Block stone   = blocks.getOrThrow(gem.stoneBlock()).value();
            Block crystal = blocks.getOrThrow(gem.crystalBlock()).value();
            Block ore     = blocks.getOrThrow(gem.oreBlock()).value();
            Block endStone = Blocks.END_STONE;

            // Stone blobs — fill end stone with gem stone (size 64)
            ctx.register(gem.stoneBlobsCF(), new ConfiguredFeature<>(Feature.ORE,
                new OreConfiguration(List.of(
                    OreConfiguration.target(new BlockMatchTest(endStone), stone.defaultBlockState())
                ), 64)
            ));

            // Crystal blobs — replace end stone and gem stone with crystal block (size 20)
            ctx.register(gem.crystalBlobsCF(), new ConfiguredFeature<>(Feature.ORE,
                new OreConfiguration(List.of(
                    OreConfiguration.target(new BlockMatchTest(endStone), crystal.defaultBlockState()),
                    OreConfiguration.target(new BlockMatchTest(stone),    crystal.defaultBlockState())
                ), 20)
            ));

            // Crystal buds — weighted mix of all 4 bud sizes (tries 96)
            ctx.register(gem.crystalBudsCF(), new ConfiguredFeature<>(MythicFeatures.CRYSTAL_BUD,
                new CrystalBudFeatureConfig(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(budState(blocks, gem.smallBud()),  4)
                        .add(budState(blocks, gem.mediumBud()), 3)
                        .add(budState(blocks, gem.largeBud()),  2)
                        .add(budState(blocks, gem.cluster()),   1)
                        .build()),
                    96, 5, 4
                )
            ));

            // Crystal buds rare — smaller patch, no cluster variant (tries 16)
            ctx.register(gem.crystalBudsRareCF(), new ConfiguredFeature<>(MythicFeatures.CRYSTAL_BUD,
                new CrystalBudFeatureConfig(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(budState(blocks, gem.smallBud()),  3)
                        .add(budState(blocks, gem.mediumBud()), 2)
                        .add(budState(blocks, gem.largeBud()),  1)
                        .build()),
                    16, 4, 3
                )
            ));

            // Ore — replaces end stone (size 7)
            ctx.register(gem.oreCF(), new ConfiguredFeature<>(Feature.ORE,
                new OreConfiguration(List.of(
                    OreConfiguration.target(new BlockMatchTest(endStone), ore.defaultBlockState()),
                    OreConfiguration.target(new BlockMatchTest(stone),    ore.defaultBlockState())
                ), 7)
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
