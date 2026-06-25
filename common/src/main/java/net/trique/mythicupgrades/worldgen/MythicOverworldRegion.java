package net.trique.mythicupgrades.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.trique.mythicupgrades.Constants;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class MythicOverworldRegion extends Region {

    public MythicOverworldRegion() {
        super(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "overworld"), RegionType.OVERWORLD, 1);
    }

    @Override
    public void addBiomes(Registry<Biome> registry,
                          Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {

        // Within our TerraBlender slice (~9% of the world), all underground is
        // covered by mythic cave biomes - giving large continuous patches.
        // Outside our slice, vanilla biomes generate normally.
        // The 4 biomes are divided by weirdness quarters so each has its own territory.

        Climate.Parameter full  = Climate.Parameter.span(-1.0f, 1.0f);
        Climate.Parameter depth = Climate.Parameter.span(0.5f, 0.9f);

        // Each biome occupies one quarter of (temperature x weirdness) space,
        // so ~25% of our 9% TerraBlender slice = ~2% of the world per biome.
        addBiome(mapper, Climate.parameters(
                Climate.Parameter.span(-1.0f, 0.0f), full, full, full, depth,
                Climate.Parameter.span(-1.0f, 0.0f), 0.0f),
                MythicBiomes.AQUAMARINE_CAVES);

        addBiome(mapper, Climate.parameters(
                Climate.Parameter.span(-1.0f, 0.0f), full, full, full, depth,
                Climate.Parameter.span( 0.0f, 1.0f), 0.0f),
                MythicBiomes.CITRINE_CAVES);

        addBiome(mapper, Climate.parameters(
                Climate.Parameter.span( 0.0f, 1.0f), full, full, full, depth,
                Climate.Parameter.span(-1.0f, 0.0f), 0.0f),
                MythicBiomes.PERIDOT_CAVES);

        addBiome(mapper, Climate.parameters(
                Climate.Parameter.span( 0.0f, 1.0f), full, full, full, depth,
                Climate.Parameter.span( 0.0f, 1.0f), 0.0f),
                MythicBiomes.TOPAZ_CAVES);

        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {});
    }
}
