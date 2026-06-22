package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
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

            // Stone ore — replaces stone_ore_replaceables at y 0–30 (size 7)
            ctx.register(gem.oreCF(), new ConfiguredFeature<>(Feature.ORE,
                new OreConfiguration(List.of(
                    OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ore.defaultBlockState())
                ), 7)
            ));

            // Deepslate ore — replaces deepslate and gem stone at y -64–8 (size 7)
            ctx.register(gem.deepslateOreCF(), new ConfiguredFeature<>(Feature.ORE,
                new OreConfiguration(List.of(
                    OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), dsOre.defaultBlockState()),
                    OreConfiguration.target(new BlockMatchTest(stone),                               dsOre.defaultBlockState())
                ), 7)
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
