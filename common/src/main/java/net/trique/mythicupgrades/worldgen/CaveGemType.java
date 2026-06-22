package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.trique.mythicupgrades.Constants;

public enum CaveGemType {
    AQUAMARINE("aquamarine", 0.5f,  0.5f,  true,  3850191),
    CITRINE   ("citrine",    1.5f,  0.0f,  false, 4159204),
    PERIDOT   ("peridot",   0.3f,  0.4f,  true,  4177782),
    TOPAZ     ("topaz",     0.8f,  0.2f,  true,  4159204);

    public final String  id;
    public final float   temperature;
    public final float   downfall;
    public final boolean precipitation;
    public final int     waterColor;

    CaveGemType(String id, float temperature, float downfall, boolean precipitation, int waterColor) {
        this.id            = id;
        this.temperature   = temperature;
        this.downfall      = downfall;
        this.precipitation = precipitation;
        this.waterColor    = waterColor;
    }

    public ResourceKey<Block> stoneBlock()   { return block(id + "_stone"); }
    public ResourceKey<Block> crystalBlock() { return block(id + "_crystal_block"); }
    public ResourceKey<Block> oreBlock()     { return block(id + "_ore"); }
    public ResourceKey<Block> deepslateOre() { return block("deepslate_" + id + "_ore"); }
    public ResourceKey<Block> smallBud()     { return block("small_" + id + "_crystal_bud"); }
    public ResourceKey<Block> mediumBud()    { return block("medium_" + id + "_crystal_bud"); }
    public ResourceKey<Block> largeBud()     { return block("large_" + id + "_crystal_bud"); }
    public ResourceKey<Block> cluster()      { return block(id + "_crystal_cluster"); }

    private ResourceKey<Block> block(String name) {
        return ResourceKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, name));
    }

    public ResourceKey<Biome> biome() {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, id + "_caves"));
    }

    public ResourceKey<ConfiguredFeature<?, ?>> stoneBlobsCF()      { return cf(id + "_stone_blobs"); }
    public ResourceKey<ConfiguredFeature<?, ?>> crystalBlobsCF()    { return cf(id + "_crystal_blobs"); }
    public ResourceKey<ConfiguredFeature<?, ?>> crystalBudsCF()     { return cf(id + "_crystal_buds"); }
    public ResourceKey<ConfiguredFeature<?, ?>> crystalBudsRareCF() { return cf(id + "_crystal_buds_rare"); }
    public ResourceKey<ConfiguredFeature<?, ?>> oreCF()             { return cf(id + "_ore"); }
    public ResourceKey<ConfiguredFeature<?, ?>> deepslateOreCF()    { return cf(id + "_deepslate_ore"); }

    private ResourceKey<ConfiguredFeature<?, ?>> cf(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, name));
    }

    public ResourceKey<PlacedFeature> stoneBlobsPF()      { return pf(id + "_stone_blobs"); }
    public ResourceKey<PlacedFeature> crystalBlobsPF()    { return pf(id + "_crystal_blobs"); }
    public ResourceKey<PlacedFeature> crystalBudsPF()     { return pf(id + "_crystal_buds"); }
    public ResourceKey<PlacedFeature> crystalBudsRarePF() { return pf(id + "_crystal_buds_rare"); }
    public ResourceKey<PlacedFeature> orePF()             { return pf(id + "_ore"); }
    public ResourceKey<PlacedFeature> deepslateOrePF()    { return pf(id + "_deepslate_ore"); }

    private ResourceKey<PlacedFeature> pf(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MOD_ID, name));
    }
}