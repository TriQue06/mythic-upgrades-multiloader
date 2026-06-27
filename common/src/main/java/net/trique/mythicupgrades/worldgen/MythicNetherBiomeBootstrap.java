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

public class MythicNetherBiomeBootstrap {

    public static void bootstrap(BootstapContext<Biome> ctx) {
        HolderGetter<PlacedFeature>           features = ctx.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers  = ctx.lookup(Registries.CONFIGURED_CARVER);

        for (NetherGemType gem : NetherGemType.values()) {
            ctx.register(gem.netherBiome(), buildBiome(gem, features, carvers));
        }
    }

    private static Biome buildBiome(NetherGemType gem,
                                    HolderGetter<PlacedFeature> features,
                                    HolderGetter<ConfiguredWorldCarver<?>> carvers) {

        BiomeGenerationSettings.Builder gen = new BiomeGenerationSettings.Builder(features, carvers);

        // Vanilla nether ores (quartz, gold, ancient debris) — first for consistent FeatureSorter ordering
        BiomeDefaultFeatures.addNetherDefaultOres(gen);

        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,       features.getOrThrow(gem.stoneBlobsPF()));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,       features.getOrThrow(gem.orePF()));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, features.getOrThrow(gem.crystalBlobsPF()));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, features.getOrThrow(gem.crystalBudsPF()));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, features.getOrThrow(gem.crystalBudsRarePF()));

        MobSpawnSettings spawns = new MobSpawnSettings.Builder()
                .creatureGenerationProbability(0.0f)
                .build();

        int fogColor = gem == NetherGemType.RUBY ? 0xBF3030 : 0x3030BF;
        int skyColor = gem == NetherGemType.RUBY ? 0x420000 : 0x000042;

        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(fogColor)
                .skyColor(skyColor)
                .waterColor(gem.waterColor)
                .waterFogColor(0x050533)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .ambientAdditionsSound(new AmbientAdditionsSettings(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(MythicSounds.AMBIENT_MYTHIC_CHIME), 0.0111))
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
