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

public class MythicNetherPlacedFeatures {

    public static void bootstrap(BootstapContext<PlacedFeature> ctx) {
        HolderGetter<ConfiguredFeature<?, ?>> features = ctx.lookup(Registries.CONFIGURED_FEATURE);

        for (NetherGemType gem : NetherGemType.values()) {
            var stoneBlobsH   = features.getOrThrow(gem.stoneBlobsCF());
            var crystalBlobsH = features.getOrThrow(gem.crystalBlobsCF());
            var crystalBudsH  = features.getOrThrow(gem.crystalBudsCF());
            var crystalBudsRH = features.getOrThrow(gem.crystalBudsRareCF());
            var oreH          = features.getOrThrow(gem.oreCF());

            // Stone blobs: 30 per chunk, y 5 to 120
            ctx.register(gem.stoneBlobsPF(), new PlacedFeature(stoneBlobsH, List.of(
                CountPlacement.of(30),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(120)),
                BiomeFilter.biome()
            )));

            // Crystal blobs: 8 per chunk, y 5 to 120
            ctx.register(gem.crystalBlobsPF(), new PlacedFeature(crystalBlobsH, List.of(
                CountPlacement.of(8),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(120)),
                BiomeFilter.biome()
            )));

            // Crystal buds: 12 per chunk, y 5 to 120
            ctx.register(gem.crystalBudsPF(), new PlacedFeature(crystalBudsH, List.of(
                CountPlacement.of(12),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(120)),
                BiomeFilter.biome()
            )));

            // Crystal buds rare: 1-in-20 chunks, y 5 to 110
            ctx.register(gem.crystalBudsRarePF(), new PlacedFeature(crystalBudsRH, List.of(
                RarityFilter.onAverageOnceEvery(20),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(110)),
                BiomeFilter.biome()
            )));

            // Ore: 20 per chunk, y 0 to 128
            ctx.register(gem.orePF(), new PlacedFeature(oreH, List.of(
                CountPlacement.of(20),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128)),
                BiomeFilter.biome()
            )));

            // Geode: 1-in-24 chunks (vanilla amethyst rarity), y 0 to 64
            var geodeH = features.getOrThrow(gem.geodeCF());
            ctx.register(gem.geodePF(), new PlacedFeature(geodeH, List.of(
                RarityFilter.onAverageOnceEvery(24),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)),
                BiomeFilter.biome()
            )));

            // Geode extra: 1-in-8 chunks in the matching gem rift biome
            ctx.register(gem.geodeExtraPF(), new PlacedFeature(geodeH, List.of(
                RarityFilter.onAverageOnceEvery(8),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)),
                BiomeFilter.biome()
            )));
        }
    }
}
