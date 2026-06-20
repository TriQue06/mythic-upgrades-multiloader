package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.trique.mythicupgrades.Constants;

public class MythicBiomes {

    public static final ResourceKey<Biome> AQUAMARINE_CAVES = key("aquamarine_caves");
    public static final ResourceKey<Biome> CITRINE_CAVES    = key("citrine_caves");
    public static final ResourceKey<Biome> PERIDOT_CAVES    = key("peridot_caves");
    public static final ResourceKey<Biome> TOPAZ_CAVES      = key("topaz_caves");

    private static ResourceKey<Biome> key(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, name));
    }

    public static void init() {}
}
