package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class MythicEndPlacedFeatures {

    // End islands sit roughly at y=55-75; features generate across y=40-80.
    private static final int END_MIN_Y = 40;
    private static final int END_MAX_Y = 80;

    public static void bootstrap(BootstapContext<PlacedFeature> ctx) {
        HolderGetter<ConfiguredFeature<?, ?>> features = ctx.lookup(Registries.CONFIGURED_FEATURE);

        for (EndGemType gem : EndGemType.values()) {
            // Stone blobs: 30 per chunk, biome-filtered
            ctx.register(gem.stoneBlobsPF(), new PlacedFeature(
                features.getOrThrow(gem.stoneBlobsCF()), List.of(
                    CountPlacement.of(30),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(END_MIN_Y), VerticalAnchor.absolute(END_MAX_Y)),
                    BiomeFilter.biome()
            )));

            // Crystal blobs: 8 per chunk, biome-filtered
            ctx.register(gem.crystalBlobsPF(), new PlacedFeature(
                features.getOrThrow(gem.crystalBlobsCF()), List.of(
                    CountPlacement.of(8),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(END_MIN_Y), VerticalAnchor.absolute(END_MAX_Y)),
                    BiomeFilter.biome()
            )));

            // Crystal buds: 12 per chunk, biome-filtered
            ctx.register(gem.crystalBudsPF(), new PlacedFeature(
                features.getOrThrow(gem.crystalBudsCF()), List.of(
                    CountPlacement.of(12),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(END_MIN_Y), VerticalAnchor.absolute(END_MAX_Y)),
                    BiomeFilter.biome()
            )));

            // Crystal buds rare: 1-in-20 chunks, biome-filtered
            ctx.register(gem.crystalBudsRarePF(), new PlacedFeature(
                features.getOrThrow(gem.crystalBudsRareCF()), List.of(
                    RarityFilter.onAverageOnceEvery(20),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(END_MIN_Y), VerticalAnchor.absolute(END_MAX_Y)),
                    BiomeFilter.biome()
            )));

            // Ore: 8 per chunk, biome-filtered
            ctx.register(gem.orePF(), new PlacedFeature(
                features.getOrThrow(gem.oreCF()), List.of(
                    CountPlacement.of(8),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(END_MIN_Y), VerticalAnchor.absolute(END_MAX_Y)),
                    BiomeFilter.biome()
            )));
        }
    }
}
