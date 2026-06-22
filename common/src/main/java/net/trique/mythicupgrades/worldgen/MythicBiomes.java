package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.trique.mythicupgrades.Constants;

public class MythicBiomes {

    public static final ResourceKey<Biome> AQUAMARINE_CAVES  = key("aquamarine_caves");
    public static final ResourceKey<Biome> CITRINE_CAVES     = key("citrine_caves");
    public static final ResourceKey<Biome> PERIDOT_CAVES     = key("peridot_caves");
    public static final ResourceKey<Biome> TOPAZ_CAVES       = key("topaz_caves");
    public static final ResourceKey<Biome> AMETRINE_BARRENS   = key("ametrine_barrens");
    public static final ResourceKey<Biome> JADE_BARRENS       = key("jade_barrens");
    public static final ResourceKey<Biome> RUBY_RIFT           = key("ruby_rift");
    public static final ResourceKey<Biome> SAPPHIRE_RIFT       = key("sapphire_rift");

    private static ResourceKey<Biome> key(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, name));
    }

    public static void init() {}
}
