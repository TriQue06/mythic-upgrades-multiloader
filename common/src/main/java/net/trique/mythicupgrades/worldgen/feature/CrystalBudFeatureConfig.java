package net.trique.mythicupgrades.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record CrystalBudFeatureConfig(
    BlockStateProvider state,
    int tries,
    int spreadXZ,
    int spreadY
) implements FeatureConfiguration {

    public static final Codec<CrystalBudFeatureConfig> CODEC = RecordCodecBuilder.create(inst -> inst.group(
        BlockStateProvider.CODEC.fieldOf("state").forGetter(CrystalBudFeatureConfig::state),
        Codec.intRange(1, 512).fieldOf("tries").forGetter(CrystalBudFeatureConfig::tries),
        Codec.intRange(0, 16).fieldOf("spread_xz").forGetter(CrystalBudFeatureConfig::spreadXZ),
        Codec.intRange(0, 16).fieldOf("spread_y").forGetter(CrystalBudFeatureConfig::spreadY)
    ).apply(inst, CrystalBudFeatureConfig::new));
}
