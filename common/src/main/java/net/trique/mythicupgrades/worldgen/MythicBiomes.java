package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.trique.mythicupgrades.Constants;

public class MythicBiomes {

    public static final ResourceKey<Biome> COLD_MYTHIC_CAVES = key("cold_mythic_caves");
    public static final ResourceKey<Biome> WARM_MYTHIC_CAVES = key("warm_mythic_caves");
    public static final ResourceKey<Biome> MYTHIC_RIFTS = key("mythic_rifts");
    public static final ResourceKey<Biome> MYTHIC_BARRENS = key("mythic_barrens");

    private static ResourceKey<Biome> key(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    public static void init() {}
}
