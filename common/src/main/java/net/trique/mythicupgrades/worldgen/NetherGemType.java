package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.trique.mythicupgrades.Constants;

public enum NetherGemType {
    RUBY    ("ruby"),
    SAPPHIRE("sapphire");

    public final String id;

    NetherGemType(String id) {
        this.id = id;
    }

    public ResourceKey<Block> stoneBlock()      { return block(id + "_schist"); }
    public ResourceKey<Block> crystalBlock()   { return block(id + "_crystal_block"); }
    public ResourceKey<Block> buddingCrystal() { return block("budding_" + id + "_crystal"); }
    public ResourceKey<Block> oreBlock()       { return block(id + "_ore"); }
    public ResourceKey<Block> smallBud()       { return block("small_" + id + "_crystal_bud"); }
    public ResourceKey<Block> mediumBud()      { return block("medium_" + id + "_crystal_bud"); }
    public ResourceKey<Block> largeBud()       { return block("large_" + id + "_crystal_bud"); }
    public ResourceKey<Block> cluster()        { return block(id + "_crystal_cluster"); }

    private ResourceKey<Block> block(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    public ResourceKey<Biome> netherBiome() {
        return MythicBiomes.MYTHIC_RIFTS;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> stoneBlobsCF()      { return cf(id + "_schist_blobs"); }
    public ResourceKey<ConfiguredFeature<?, ?>> crystalBlobsCF()    { return cf(id + "_crystal_blobs"); }
    public ResourceKey<ConfiguredFeature<?, ?>> crystalBudsCF()     { return cf(id + "_crystal_buds"); }
    public ResourceKey<ConfiguredFeature<?, ?>> oreCF()             { return cf(id + "_ore"); }
    public ResourceKey<ConfiguredFeature<?, ?>> geodeCF()           { return cf(id + "_geode"); }

    private ResourceKey<ConfiguredFeature<?, ?>> cf(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    public ResourceKey<PlacedFeature> stoneBlobsPF()      { return pf(id + "_schist_blobs"); }
    public ResourceKey<PlacedFeature> crystalBlobsPF()    { return pf(id + "_crystal_blobs"); }
    public ResourceKey<PlacedFeature> crystalBudsPF()     { return pf(id + "_crystal_buds"); }
    public ResourceKey<PlacedFeature> orePF()             { return pf(id + "_ore"); }
    public ResourceKey<PlacedFeature> geodePF()           { return pf(id + "_geode"); }

    private ResourceKey<PlacedFeature> pf(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name));
    }
}
