package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.attribute.AmbientAdditionsSettings;
import net.minecraft.world.attribute.AmbientMoodSettings;
import net.minecraft.world.attribute.AmbientSounds;
import net.minecraft.world.attribute.EnvironmentAttributes;

import java.util.List;
import java.util.Optional;
import net.trique.mythicupgrades.MythicSounds;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MythicNetherBiomeBootstrap {

    public static void bootstrap(BootstrapContext<Biome> ctx) {
        HolderGetter<PlacedFeature> features = ctx.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> carvers = ctx.lookup(Registries.CONFIGURED_CARVER);

        ctx.register(MythicBiomes.MYTHIC_RIFTS, buildBiome(features, carvers));
    }

    private static Biome buildBiome(HolderGetter<PlacedFeature> features,
                                    HolderGetter<ConfiguredWorldCarver<?>> carvers) {

        BiomeGenerationSettings.Builder gen = new BiomeGenerationSettings.Builder(features, carvers);

        BiomeDefaultFeatures.addNetherDefaultOres(gen);

        for (NetherGemType gem : NetherGemType.values()) {
            gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(gem.stoneBlobsPF()));
            gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(gem.orePF()));
            gen.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, features.getOrThrow(gem.crystalBlobsPF()));
            gen.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, features.getOrThrow(gem.crystalBudsPF()));
        }

        MobSpawnSettings spawns = new MobSpawnSettings.Builder()
                .creatureGenerationProbability(0.0f)
                .build();

        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .waterColor(0xA147FC)
                .build();

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(2.0f)
                .downfall(0.0f)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 0xFF78307B)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, 0xFF210021)
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0xFF050533)
                .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                    Optional.empty(),
                    Optional.of(AmbientMoodSettings.LEGACY_CAVE_SETTINGS),
                    List.of(new AmbientAdditionsSettings(
                        BuiltInRegistries.SOUND_EVENT.wrapAsHolder(MythicSounds.AMBIENT_MYTHIC_CHIME), 0.0111))))
                .specialEffects(effects)
                .mobSpawnSettings(spawns)
                .generationSettings(gen.build())
                .build();
    }
}
