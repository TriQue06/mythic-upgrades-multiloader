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
        super(new ResourceLocation(Constants.MOD_ID, "overworld"), RegionType.OVERWORLD, 1);
    }

    @Override
    public void addBiomes(Registry<Biome> registry,
                          Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        Climate.Parameter full  = Climate.Parameter.span(-1.0f, 1.0f);
        // Only underground depth (matches Deep Dark approach)
        Climate.Parameter depth = Climate.Parameter.span(0.2f, 0.9f);
        // Exclude deep ocean floors — same restriction Deep Dark uses
        Climate.Parameter cont  = Climate.Parameter.span(0.03f, 1.0f);

        // Weirdness spans are 0.02 wide (was 0.25) — reduces frequency to ~Deep Dark rarity.
        // Positioned at four distinct weirdness pockets to avoid mutual overlap.
        addBiome(mapper, Climate.parameters(full, full, cont, full, depth,
                Climate.Parameter.span(-1.00f, -0.98f), 0.0f), MythicBiomes.AQUAMARINE_CAVES);

        addBiome(mapper, Climate.parameters(full, full, cont, full, depth,
                Climate.Parameter.span(-0.50f, -0.48f), 0.0f), MythicBiomes.PERIDOT_CAVES);

        addBiome(mapper, Climate.parameters(full, full, cont, full, depth,
                Climate.Parameter.span( 0.00f,  0.02f), 0.0f), MythicBiomes.TOPAZ_CAVES);

        addBiome(mapper, Climate.parameters(full, full, cont, full, depth,
                Climate.Parameter.span( 0.50f,  0.52f), 0.0f), MythicBiomes.CITRINE_CAVES);

        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {});
    }
}
