package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.trique.mythicupgrades.Constants;

import java.util.List;

public class MythicPlacedFeatures {

    // Necoium ore placed feature keys, referenced from MythicBiomeBootstrap and BiomeModifier
    public static final ResourceKey<PlacedFeature> NECOIUM_ORE_PF = ResourceKey.create(
            Registries.PLACED_FEATURE, new ResourceLocation(Constants.MOD_ID, "necoium_ore"));
    public static final ResourceKey<PlacedFeature> DEEPSLATE_NECOIUM_ORE_PF = ResourceKey.create(
            Registries.PLACED_FEATURE, new ResourceLocation(Constants.MOD_ID, "deepslate_necoium_ore"));
    public static final ResourceKey<PlacedFeature> NECOIUM_ORE_EXTRA_PF = ResourceKey.create(
            Registries.PLACED_FEATURE, new ResourceLocation(Constants.MOD_ID, "necoium_ore_extra"));
    public static final ResourceKey<PlacedFeature> DEEPSLATE_NECOIUM_ORE_EXTRA_PF = ResourceKey.create(
            Registries.PLACED_FEATURE, new ResourceLocation(Constants.MOD_ID, "deepslate_necoium_ore_extra"));

    public static void bootstrap(BootstapContext<PlacedFeature> ctx) {
        HolderGetter<ConfiguredFeature<?, ?>> features = ctx.lookup(Registries.CONFIGURED_FEATURE);

        for (CaveGemType gem : CaveGemType.values()) {
            var stoneBlobsH    = features.getOrThrow(gem.stoneBlobsCF());
            var crystalBlobsH  = features.getOrThrow(gem.crystalBlobsCF());
            var crystalBudsH   = features.getOrThrow(gem.crystalBudsCF());
            var crystalBudsRH  = features.getOrThrow(gem.crystalBudsRareCF());
            var oreH           = features.getOrThrow(gem.oreCF());
            var deepslateOreH  = features.getOrThrow(gem.deepslateOreCF());

            // Stone blobs: 30 per chunk, y -64 to 30, biome-filtered
            ctx.register(gem.stoneBlobsPF(), new PlacedFeature(stoneBlobsH, List.of(
                CountPlacement.of(30),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(30)),
                BiomeFilter.biome()
            )));

            // Crystal blobs: 8 per chunk, y -64 to 30, biome-filtered
            ctx.register(gem.crystalBlobsPF(), new PlacedFeature(crystalBlobsH, List.of(
                CountPlacement.of(8),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(30)),
                BiomeFilter.biome()
            )));

            // Crystal buds: 12 per chunk, y -64 to 30, biome-filtered
            ctx.register(gem.crystalBudsPF(), new PlacedFeature(crystalBudsH, List.of(
                CountPlacement.of(12),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(30)),
                BiomeFilter.biome()
            )));

            // Crystal buds rare: 1-in-20 chunks, y -64 to 20, biome-filtered
            ctx.register(gem.crystalBudsRarePF(), new PlacedFeature(crystalBudsRH, List.of(
                RarityFilter.onAverageOnceEvery(20),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(20)),
                BiomeFilter.biome()
            )));

            // Stone ore: 8 per chunk, y 0 to 30, biome-filtered
            ctx.register(gem.orePF(), new PlacedFeature(oreH, List.of(
                CountPlacement.of(8),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(30)),
                BiomeFilter.biome()
            )));

            // Deepslate ore: 10 per chunk, y -64 to 8 (trapezoid), biome-filtered
            ctx.register(gem.deepslateOrePF(), new PlacedFeature(deepslateOreH, List.of(
                CountPlacement.of(10),
                InSquarePlacement.spread(),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(8)),
                BiomeFilter.biome()
            )));
        }

        // Necoium ore — diamond-level rarity in all overworld biomes (via BiomeModifier)
        var necoiumCF          = features.getOrThrow(MythicConfiguredFeatures.NECOIUM_ORE_CF);
        var deepslateNecoiumCF = features.getOrThrow(MythicConfiguredFeatures.DEEPSLATE_NECOIUM_ORE_CF);

        ctx.register(NECOIUM_ORE_PF, new PlacedFeature(necoiumCF, List.of(
            CountPlacement.of(10),
            InSquarePlacement.spread(),
            HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(16)),
            BiomeFilter.biome()
        )));
        ctx.register(DEEPSLATE_NECOIUM_ORE_PF, new PlacedFeature(deepslateNecoiumCF, List.of(
            CountPlacement.of(10),
            InSquarePlacement.spread(),
            HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-16)),
            BiomeFilter.biome()
        )));

        // Extra necoium ore for mythic cave biomes (added via MythicBiomeBootstrap)
        ctx.register(NECOIUM_ORE_EXTRA_PF, new PlacedFeature(necoiumCF, List.of(
            CountPlacement.of(20),
            InSquarePlacement.spread(),
            HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(16)),
            BiomeFilter.biome()
        )));
        ctx.register(DEEPSLATE_NECOIUM_ORE_EXTRA_PF, new PlacedFeature(deepslateNecoiumCF, List.of(
            CountPlacement.of(20),
            InSquarePlacement.spread(),
            HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-16)),
            BiomeFilter.biome()
        )));
    }
}
