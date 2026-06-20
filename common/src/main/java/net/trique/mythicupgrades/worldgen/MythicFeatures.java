package net.trique.mythicupgrades.worldgen;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.trique.mythicupgrades.worldgen.feature.CrystalBudFeature;
import net.trique.mythicupgrades.worldgen.feature.CrystalBudFeatureConfig;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class MythicFeatures {

    private static final List<Map.Entry<String, Feature<?>>> DEFERRED = new ArrayList<>();

    public static final Feature<CrystalBudFeatureConfig> CRYSTAL_BUD =
        defer("crystal_bud", new CrystalBudFeature(CrystalBudFeatureConfig.CODEC));

    private static <T extends Feature<?>> T defer(String name, T feature) {
        DEFERRED.add(new AbstractMap.SimpleEntry<>(name, feature));
        return feature;
    }

    public static void register(BiFunction<String, Feature<?>, Feature<?>> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
    }
}
