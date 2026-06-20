package net.trique.mythicupgrades.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.trique.mythicupgrades.Constants;
import terrablender.api.ModifiedVanillaOverworldBuilder;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class MythicOverworldRegion extends Region {

    // Cave biomes are purely underground — depth 0.4-0.9 avoids any surface overlap.
    // Values above ~0.35 correspond to y levels well below the terrain surface.
    private static final Climate.Parameter CAVE_DEPTH = Climate.Parameter.span(0.4f, 0.9f);

    // Continentalness: avoid oceans (< -0.19) so caves don't appear under the sea floor.
    private static final Climate.Parameter INLAND = Climate.Parameter.span(-0.11f, 0.55f);

    public MythicOverworldRegion() {
        super(new ResourceLocation(Constants.MOD_ID, "overworld"), RegionType.OVERWORLD, 4);
    }

    @Override
    public void addBiomes(Registry<Biome> registry,
                          Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // Fill all vanilla surface biome slots first so TerraBlender never selects a cave
        // biome for a surface position within our region (would cause surface = cave biome).
        this.addModifiedVanillaOverworldBiomes(mapper, ModifiedVanillaOverworldBuilder::build);

        // Cave biomes — each occupies a distinct temperature × humidity × weirdness slice
        // while sharing the same CAVE_DEPTH (underground only).

        // Aquamarine Caves: cold + wet + very negative weirdness
        addBiome(mapper, Climate.parameters(
            Climate.Parameter.span(-1.0f, -0.45f),
            Climate.Parameter.span(0.1f,   0.4f),
            INLAND,
            Climate.Parameter.span(-0.375f, 0.45f),
            CAVE_DEPTH,
            Climate.Parameter.span(-0.93f, -0.56f),
            0.0f
        ), MythicBiomes.AQUAMARINE_CAVES);

        // Citrine Caves: hot + dry + very positive weirdness
        addBiome(mapper, Climate.parameters(
            Climate.Parameter.span(0.55f, 1.0f),
            Climate.Parameter.span(-1.0f, -0.35f),
            INLAND,
            Climate.Parameter.span(-0.375f, 0.45f),
            CAVE_DEPTH,
            Climate.Parameter.span(0.56f, 0.93f),
            0.0f
        ), MythicBiomes.CITRINE_CAVES);

        // Peridot Caves: cool-temperate + low erosion (mountain roots) + neutral weirdness
        addBiome(mapper, Climate.parameters(
            Climate.Parameter.span(-0.15f, 0.2f),
            Climate.Parameter.span(-0.35f, 0.1f),
            INLAND,
            Climate.Parameter.span(-0.78f, -0.375f),
            CAVE_DEPTH,
            Climate.Parameter.span(-0.4f, 0.4f),
            0.0f
        ), MythicBiomes.PERIDOT_CAVES);

        // Topaz Caves: warm + high erosion (valleys / badlands roots) + neutral weirdness
        addBiome(mapper, Climate.parameters(
            Climate.Parameter.span(0.2f, 0.55f),
            Climate.Parameter.span(-0.35f, 0.1f),
            INLAND,
            Climate.Parameter.span(0.45f, 1.0f),
            CAVE_DEPTH,
            Climate.Parameter.span(-0.4f, 0.4f),
            0.0f
        ), MythicBiomes.TOPAZ_CAVES);
    }
}
