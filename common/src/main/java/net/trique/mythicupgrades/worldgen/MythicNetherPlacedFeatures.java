package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
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

    public static void bootstrap(BootstrapContext<PlacedFeature> ctx) {
        HolderGetter<ConfiguredFeature<?, ?>> features = ctx.lookup(Registries.CONFIGURED_FEATURE);

        for (NetherGemType gem : NetherGemType.values()) {
            var stoneBlobsH = features.getOrThrow(gem.stoneBlobsCF());
            var crystalBlobsH = features.getOrThrow(gem.crystalBlobsCF());
            var crystalBudsH = features.getOrThrow(gem.crystalBudsCF());
            var crystalBudsRH = features.getOrThrow(gem.crystalBudsRareCF());
            var oreH = features.getOrThrow(gem.oreCF());

            ctx.register(gem.stoneBlobsPF(), new PlacedFeature(stoneBlobsH, List.of(
                CountPlacement.of(30),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(120)),
                BiomeFilter.biome()
            )));

            ctx.register(gem.crystalBlobsPF(), new PlacedFeature(crystalBlobsH, List.of(
                CountPlacement.of(8),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(120)),
                BiomeFilter.biome()
            )));

            ctx.register(gem.crystalBudsPF(), new PlacedFeature(crystalBudsH, List.of(
                CountPlacement.of(12),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(120)),
                BiomeFilter.biome()
            )));

            ctx.register(gem.crystalBudsRarePF(), new PlacedFeature(crystalBudsRH, List.of(
                RarityFilter.onAverageOnceEvery(20),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(110)),
                BiomeFilter.biome()
            )));

            ctx.register(gem.orePF(), new PlacedFeature(oreH, List.of(
                CountPlacement.of(20),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128)),
                BiomeFilter.biome()
            )));

            var geodeH = features.getOrThrow(gem.geodeCF());
            ctx.register(gem.geodePF(), new PlacedFeature(geodeH, List.of(
                RarityFilter.onAverageOnceEvery(24),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)),
                BiomeFilter.biome()
            )));

            ctx.register(gem.geodeExtraPF(), new PlacedFeature(geodeH, List.of(
                RarityFilter.onAverageOnceEvery(8),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)),
                BiomeFilter.biome()
            )));
        }
    }
}
