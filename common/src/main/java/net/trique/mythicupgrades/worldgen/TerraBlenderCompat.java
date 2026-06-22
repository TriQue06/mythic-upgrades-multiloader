package net.trique.mythicupgrades.worldgen;

import terrablender.api.Regions;

public class TerraBlenderCompat {

    public static void init() {
        Regions.register(new MythicOverworldRegion());
        Regions.register(new MythicNetherRegion());
    }
}
