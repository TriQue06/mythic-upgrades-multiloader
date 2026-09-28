package net.trique.mythicupgrades.worldgen;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.trique.mythicupgrades.worldgen.feature.CrystalBudFeature;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Feature types — the codecs registered under {@code worldgen/feature_type}.
 * The configured instances live in the datapack {@code worldgen/feature} registry.
 */
public class MythicFeatures {

    private static final List<Map.Entry<String, MapCodec<? extends Feature>>> DEFERRED = new ArrayList<>();

    public static final MapCodec<CrystalBudFeature> CRYSTAL_BUD = defer("crystal_bud", CrystalBudFeature.CODEC);

    private static <T extends Feature> MapCodec<T> defer(String name, MapCodec<T> codec) {
        DEFERRED.add(new AbstractMap.SimpleEntry<>(name, codec));
        return codec;
    }

    public static void register(BiConsumer<String, MapCodec<? extends Feature>> reg) {
        DEFERRED.forEach(e -> reg.accept(e.getKey(), e.getValue()));
    }
}
