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

public class MythicNetherRegion extends Region {

    public MythicNetherRegion() {
        super(new ResourceLocation(Constants.MOD_ID, "nether"), RegionType.NETHER, 1);
    }

    @Override
    public void addBiomes(Registry<Biome> registry,
                          Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // Nether mod regions are isolated - only registered biomes appear within them.
        // Pinning to narrow extreme-temperature pockets keeps coverage small.

        Climate.Parameter full = Climate.Parameter.span(-1.0f, 1.0f);

        // ruby rift - extreme cold
        addBiome(mapper, Climate.parameters(
                Climate.Parameter.span(-1.0f, -0.85f),
                full, full, full, full, full, 0.0f),
                MythicBiomes.RUBY_RIFT);

        // sapphire rift - extreme heat
        addBiome(mapper, Climate.parameters(
                Climate.Parameter.span(0.85f, 1.0f),
                full, full, full, full, full, 0.0f),
                MythicBiomes.SAPPHIRE_RIFT);
    }
}
