package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.trique.mythicupgrades.worldgen.CaveGemType;
import net.trique.mythicupgrades.worldgen.EndGemType;
import net.trique.mythicupgrades.worldgen.NetherGemType;

import java.util.concurrent.CompletableFuture;

public class MythicBiomeTagsProvider extends TagsProvider<Biome> {

    public MythicBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.BIOME, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        TagAppender<Biome> overworld = tag(BiomeTags.IS_OVERWORLD);
        for (CaveGemType gem : CaveGemType.values()) {
            overworld.addOptional(gem.biome().location());
        }

        TagAppender<Biome> nether = tag(BiomeTags.IS_NETHER);
        for (NetherGemType gem : NetherGemType.values()) {
            nether.addOptional(gem.netherBiome().location());
        }

        TagAppender<Biome> end = tag(BiomeTags.IS_END);
        for (EndGemType gem : EndGemType.values()) {
            end.addOptional(gem.endBiome().location());
        }
    }
}
