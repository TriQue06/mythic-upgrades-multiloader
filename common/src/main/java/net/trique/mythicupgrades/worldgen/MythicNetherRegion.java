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
        Climate.Parameter full = Climate.Parameter.span(-1.0f, 1.0f);
        // Outer quarter of temperature range only (0.25 wide each, was 0.5) — halves area again
        addBiome(mapper, Climate.parameters(
                Climate.Parameter.span(-1.0f, -0.75f), full, full, full, full, full, 0.0f),
                MythicBiomes.RUBY_RIFT);
        addBiome(mapper, Climate.parameters(
                Climate.Parameter.span(0.75f, 1.0f), full, full, full, full, full, 0.0f),
                MythicBiomes.SAPPHIRE_RIFT);
    }
}
