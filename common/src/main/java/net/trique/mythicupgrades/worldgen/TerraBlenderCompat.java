package net.trique.mythicupgrades.worldgen;

import terrablender.api.EndBiomeRegistry;
import terrablender.api.Regions;

public class TerraBlenderCompat {

    public static void init() {
        Regions.register(new MythicOverworldRegion());
        Regions.register(new MythicNetherRegion());
        EndBiomeRegistry.registerHighlandsBiome(MythicBiomes.AMETRINE_BARRENS, 2);
        EndBiomeRegistry.registerMidlandsBiome(MythicBiomes.JADE_BARRENS, 2);
    }
}
