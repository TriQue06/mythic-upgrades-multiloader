package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MythicBiomeBootstrap {

    private static final ResourceKey<PlacedFeature> SPRING_WATER = ResourceKey.create(
            Registries.PLACED_FEATURE, new ResourceLocation("minecraft", "spring_water"));
    private static final ResourceKey<PlacedFeature> SPRING_LAVA  = ResourceKey.create(
            Registries.PLACED_FEATURE, new ResourceLocation("minecraft", "spring_lava"));

    public static void bootstrap(BootstapContext<Biome> ctx) {
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

        // Dungeon / monster room (correct step: UNDERGROUND_STRUCTURES)
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_STRUCTURES, features.getOrThrow(CavePlacements.MONSTER_ROOM));

        // Stone variants — granite, diorite, andesite, tuff, dirt, gravel veins
        // Metal ores (coal/iron/gold/diamond/etc.) are added via BiomeModifier / BiomeModifications
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(OrePlacements.ORE_GRANITE_UPPER));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(OrePlacements.ORE_GRANITE_LOWER));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(OrePlacements.ORE_DIORITE_UPPER));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(OrePlacements.ORE_DIORITE_LOWER));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(OrePlacements.ORE_ANDESITE_UPPER));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(OrePlacements.ORE_ANDESITE_LOWER));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(OrePlacements.ORE_TUFF));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(OrePlacements.ORE_DIRT));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(OrePlacements.ORE_GRAVEL));

        // Extra necoium ore density for mythic cave biomes
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(MythicPlacedFeatures.NECOIUM_ORE_EXTRA_PF));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(MythicPlacedFeatures.DEEPSLATE_NECOIUM_ORE_EXTRA_PF));

        // Gem stone blobs and ore veins
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(gem.stoneBlobsPF()));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(gem.orePF()));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, features.getOrThrow(gem.deepslateOrePF()));

        // Cave ambient decoration
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, features.getOrThrow(CavePlacements.GLOW_LICHEN));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, features.getOrThrow(gem.crystalBlobsPF()));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, features.getOrThrow(gem.crystalBudsPF()));
        gen.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, features.getOrThrow(gem.crystalBudsRarePF()));

        gen.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, features.getOrThrow(SPRING_WATER));
        gen.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, features.getOrThrow(SPRING_LAVA));

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
