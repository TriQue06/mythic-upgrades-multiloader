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
    AQUAMARINE("aquamarine", true),
    CITRINE   ("citrine",    false),
    PERIDOT   ("peridot",    true),
    TOPAZ     ("topaz",      false);

    public final String  id;
    public final boolean cold;

    CaveGemType(String id, boolean cold) {
        this.id   = id;
        this.cold = cold;
    }

    public ResourceKey<Block> stoneBlock()      { return block(id + "_schist"); }
    public ResourceKey<Block> crystalBlock()   { return block(id + "_crystal_block"); }
    public ResourceKey<Block> buddingCrystal() { return block("budding_" + id + "_crystal"); }
    public ResourceKey<Block> oreBlock()       { return block(id + "_ore"); }
    public ResourceKey<Block> deepslateOre()   { return block("deepslate_" + id + "_ore"); }
    public ResourceKey<Block> smallBud()       { return block("small_" + id + "_crystal_bud"); }
    public ResourceKey<Block> mediumBud()      { return block("medium_" + id + "_crystal_bud"); }
    public ResourceKey<Block> largeBud()       { return block("large_" + id + "_crystal_bud"); }
    public ResourceKey<Block> cluster()        { return block(id + "_crystal_cluster"); }

    private ResourceKey<Block> block(String name) {
        return ResourceKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, name));
    }

    public ResourceKey<Biome> biome() {
        return cold ? MythicBiomes.COLD_MYTHIC_CAVES : MythicBiomes.WARM_MYTHIC_CAVES;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> stoneBlobsCF()      { return cf(id + "_stone_blobs"); }
    public ResourceKey<ConfiguredFeature<?, ?>> crystalBlobsCF()    { return cf(id + "_crystal_blobs"); }
    public ResourceKey<ConfiguredFeature<?, ?>> crystalBudsCF()     { return cf(id + "_crystal_buds"); }
    public ResourceKey<ConfiguredFeature<?, ?>> oreCF()             { return cf(id + "_ore"); }
    public ResourceKey<ConfiguredFeature<?, ?>> geodeCF()           { return cf(id + "_geode"); }

    private ResourceKey<ConfiguredFeature<?, ?>> cf(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, name));
    }

    public ResourceKey<PlacedFeature> stoneBlobsPF()      { return pf(id + "_stone_blobs"); }
    public ResourceKey<PlacedFeature> crystalBlobsPF()    { return pf(id + "_crystal_blobs"); }
    public ResourceKey<PlacedFeature> crystalBudsPF()     { return pf(id + "_crystal_buds"); }
    public ResourceKey<PlacedFeature> orePF()             { return pf(id + "_ore"); }
    public ResourceKey<PlacedFeature> geodePF()           { return pf(id + "_geode"); }

    private ResourceKey<PlacedFeature> pf(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MOD_ID, name));
    }
}