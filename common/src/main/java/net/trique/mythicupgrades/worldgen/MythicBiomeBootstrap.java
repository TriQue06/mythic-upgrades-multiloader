package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MythicBiomeBootstrap {

    public static void bootstrap(BootstrapContext<Biome> ctx) {
        HolderGetter<PlacedFeature>           features = ctx.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers  = ctx.lookup(Registries.CONFIGURED_CARVER);

        for (CaveGemType gem : CaveGemType.values()) {
            ctx.register(gem.biome(), buildBiome(gem, features, carvers));
        }
    }

    private static Biome buildBiome(CaveGemType gem,
                                    HolderGetter<PlacedFeature> features,
                                    HolderGetter<ConfiguredWorldCarver<?>> carvers) {

        BiomeGenerationSettings.Builder gen = new BiomeGenerationSettings.Builder(features, carvers);

        // Vanilla underground variety (andesite, granite, diorite, tuff, calcite, gravel)
        // and vanilla ores — must come FIRST so FeatureSorter sees the same ordering
        // as vanilla cave biomes that share chunk columns with these biomes.
        BiomeDefaultFeatures.addDefaultUndergroundVariety(gen);
        BiomeDefaultFeatures.addDefaultOres(gen);

        // Extra necoium ore density — unique to mythic cave biomes
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(MythicPlacedFeatures.NECOIUM_ORE_EXTRA_PF));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(MythicPlacedFeatures.DEEPSLATE_NECOIUM_ORE_EXTRA_PF));

        // Gem stone blobs and ore veins — unique to each cave biome
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(gem.stoneBlobsPF()));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(gem.orePF()));

        // Crystal decoration — unique to each cave biome
        // glow_lichen, crystal_buds_rare, and geodes are added via BiomeModifier/BiomeModifications
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, features.getOrThrow(gem.crystalBlobsPF()));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, features.getOrThrow(gem.crystalBudsPF()));

        // spring_water and spring_lava are added via BiomeModifier/BiomeModifications

        MobSpawnSettings spawns = new MobSpawnSettings.Builder()
                .creatureGenerationProbability(0.07f)
                .build();

        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(12638463)
                .skyColor(8103167)
                .waterColor(gem.waterColor)
                .waterFogColor(329011)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .build();

        return new Biome.BiomeBuilder()
                .hasPrecipitation(gem.precipitation)
                .temperature(gem.temperature)
                .downfall(gem.downfall)
                .specialEffects(effects)
                .mobSpawnSettings(spawns)
                .generationSettings(gen.build())
                .build();
    }
}
