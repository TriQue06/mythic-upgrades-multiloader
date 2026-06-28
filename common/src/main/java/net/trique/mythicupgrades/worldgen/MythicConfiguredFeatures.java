package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.worldgen.feature.CrystalBudFeatureConfig;

import java.util.List;

public class MythicConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> NECOIUM_ORE_CF = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "necoium_ore"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_NECOIUM_ORE_CF = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "deepslate_necoium_ore"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> RAW_NECOIUM_BLOCK_IN_CAVES_CF = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, "raw_necoium_block_in_caves"));

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> ctx) {
        HolderGetter<Block> blocks = ctx.lookup(Registries.BLOCK);

        for (CaveGemType gem : CaveGemType.values()) {
            Block stone    = blocks.getOrThrow(gem.stoneBlock()).value();
            Block crystal  = blocks.getOrThrow(gem.crystalBlock()).value();
            Block ore      = blocks.getOrThrow(gem.oreBlock()).value();
            Block dsOre    = blocks.getOrThrow(gem.deepslateOre()).value();

            // Stone blobs — fill stone and deepslate with gem stone (size 64)
            ctx.register(gem.stoneBlobsCF(), new ConfiguredFeature<>(Feature.ORE,
                new OreConfiguration(List.of(
                    OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),    stone.defaultBlockState()),
                    OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), stone.defaultBlockState())
                ), 64)
            ));

            // Crystal blobs — replace stone, gem stone, and deepslate with crystal block (size 20)
            ctx.register(gem.crystalBlobsCF(), new ConfiguredFeature<>(Feature.ORE,
                new OreConfiguration(List.of(
                    OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),    crystal.defaultBlockState()),
                    OreConfiguration.target(new BlockMatchTest(stone),                              crystal.defaultBlockState()),
                    OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), crystal.defaultBlockState())
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

            // Ore — replaces stone_ore_replaceables with stone variant, deepslate_ore_replaceables
            // and gem stone with deepslate variant; one generation covers the full y -64 to 30 range
            ctx.register(gem.oreCF(), new ConfiguredFeature<>(Feature.ORE,
                new OreConfiguration(List.of(
                    OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),     ore.defaultBlockState()),
                    OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), dsOre.defaultBlockState()),
                    OreConfiguration.target(new BlockMatchTest(stone),                               dsOre.defaultBlockState())
                ), 7)
            ));
        }

        // Geodes — hollow crystal-lined caves matching amethyst geode rarity and structure
        for (CaveGemType gem : CaveGemType.values()) {
            Block crystalBlock = blocks.getOrThrow(gem.crystalBlock()).value();
            Block buddingBlock = blocks.getOrThrow(gem.buddingCrystal()).value();
            Block smallBud     = blocks.getOrThrow(gem.smallBud()).value();
            Block mediumBud    = blocks.getOrThrow(gem.mediumBud()).value();
            Block largeBud     = blocks.getOrThrow(gem.largeBud()).value();
            Block clusterBlock = blocks.getOrThrow(gem.cluster()).value();

            ctx.register(gem.geodeCF(), new ConfiguredFeature<>(Feature.GEODE,
                new GeodeConfiguration(
                    new GeodeBlockSettings(
                        BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(crystalBlock),
                        BlockStateProvider.simple(buddingBlock),
                        BlockStateProvider.simple(Blocks.CALCITE),
                        BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
                        List.of(
                            smallBud.defaultBlockState(),
                            mediumBud.defaultBlockState(),
                            largeBud.defaultBlockState(),
                            clusterBlock.defaultBlockState()
                        ),
                        BlockTags.FEATURES_CANNOT_REPLACE,
                        BlockTags.GEODE_INVALID_BLOCKS
                    ),
                    new GeodeLayerSettings(1.7, 2.2, 3.2, 4.2),
                    new GeodeCrackSettings(0.95, 2.0, 2),
                    0.35, 0.083, true,
                    UniformInt.of(4, 6), UniformInt.of(3, 4), UniformInt.of(1, 2),
                    -16, 16, 0.05, 1
                )
            ));
        }

        // Necoium ores — diamond-level rarity, no biome restriction
        ResourceKey<Block> necoiumOreKey = ResourceKey.create(Registries.BLOCK,
                new ResourceLocation(Constants.MOD_ID, "necoium_ore"));
        ResourceKey<Block> deepslateNecoiumKey = ResourceKey.create(Registries.BLOCK,
                new ResourceLocation(Constants.MOD_ID, "deepslate_necoium_ore"));
        Block necoiumOre        = blocks.getOrThrow(necoiumOreKey).value();
        Block deepslateNecoiumOre = blocks.getOrThrow(deepslateNecoiumKey).value();

        ctx.register(NECOIUM_ORE_CF, new ConfiguredFeature<>(Feature.ORE,
            new OreConfiguration(List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), necoiumOre.defaultBlockState())
            ), 6)
        ));
        ctx.register(DEEPSLATE_NECOIUM_ORE_CF, new ConfiguredFeature<>(Feature.ORE,
            new OreConfiguration(List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), deepslateNecoiumOre.defaultBlockState())
            ), 5)
        ));

        ResourceKey<Block> rawNecoiumBlockKey = ResourceKey.create(Registries.BLOCK,
                new ResourceLocation(Constants.MOD_ID, "raw_necoium_block"));
        Block rawNecoiumBlock = blocks.getOrThrow(rawNecoiumBlockKey).value();
        ctx.register(RAW_NECOIUM_BLOCK_IN_CAVES_CF, new ConfiguredFeature<>(Feature.ORE,
            new OreConfiguration(List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), rawNecoiumBlock.defaultBlockState()),
                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), rawNecoiumBlock.defaultBlockState())
            ), 4)
        ));
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
