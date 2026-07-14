package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.biome.AmbientAdditionsSettings;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.core.registries.BuiltInRegistries;
import net.trique.mythicupgrades.MythicSounds;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MythicBiomeBootstrap {

    public static void bootstrap(BootstapContext<Biome> ctx) {
        HolderGetter<PlacedFeature> features = ctx.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = ctx.lookup(Registries.CONFIGURED_CARVER);

        ctx.register(MythicBiomes.COLD_MYTHIC_CAVES, buildBiome(true, features, carvers));
        ctx.register(MythicBiomes.WARM_MYTHIC_CAVES, buildBiome(false, features, carvers));
    }

    private static Biome buildBiome(boolean cold,
                                    HolderGetter<PlacedFeature> features,
                                    HolderGetter<ConfiguredWorldCarver<?>> carvers) {

        BiomeGenerationSettings.Builder gen = new BiomeGenerationSettings.Builder(features, carvers);

        BiomeDefaultFeatures.addDefaultUndergroundVariety(gen);
        BiomeDefaultFeatures.addDefaultOres(gen);

        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(MythicPlacedFeatures.NECOIUM_ORE_EXTRA_PF));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(MythicPlacedFeatures.DEEPSLATE_NECOIUM_ORE_EXTRA_PF));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(MythicPlacedFeatures.RAW_NECOIUM_BLOCK_CAVES_PF));

        for (CaveGemType gem : CaveGemType.values()) {
            if (gem.cold != cold) continue;
            gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(gem.stoneBlobsPF()));
            gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(gem.orePF()));
            gen.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, features.getOrThrow(gem.crystalBlobsPF()));
            gen.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, features.getOrThrow(gem.crystalBudsPF()));
        }

        MobSpawnSettings spawns = new MobSpawnSettings.Builder()
                .creatureGenerationProbability(0.07f)
                .build();

        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(12638463)
                .skyColor(8103167)
                .waterColor(cold ? 3850191 : 4159204)
                .waterFogColor(329011)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .ambientAdditionsSound(new AmbientAdditionsSettings(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(MythicSounds.AMBIENT_MYTHIC_CHIME), 0.0111))
                .build();

        return new Biome.BiomeBuilder()
                .hasPrecipitation(cold)
                .temperature(cold ? 0.4f : 1.2f)
                .downfall(cold ? 0.45f : 0.1f)
                .specialEffects(effects)
                .mobSpawnSettings(spawns)
                .generationSettings(gen.build())
                .build();
    }
}
