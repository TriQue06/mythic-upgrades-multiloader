package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.tags.TagsProvider;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.worldgen.CaveGemType;
import net.trique.mythicupgrades.worldgen.EndGemType;
import net.trique.mythicupgrades.worldgen.NetherGemType;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.core.registries.Registries.BIOME;

public class MythicBiomeTagsProvider extends TagsProvider<Biome> {

    public MythicBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                   @Nullable ExistingFileHelper existingFileHelper) {
        super(output, BIOME, lookupProvider, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        var overworld = tag(BiomeTags.IS_OVERWORLD);
        for (CaveGemType gem : CaveGemType.values()) {
            overworld.addOptional(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, gem.id + "_caves"));
        }

        var nether = tag(BiomeTags.IS_NETHER);
        for (NetherGemType gem : NetherGemType.values()) {
            nether.addOptional(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, gem.id + "_rift"));
        }

        var end = tag(BiomeTags.IS_END);
        for (EndGemType gem : EndGemType.values()) {
            end.addOptional(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, gem.id + "_barrens"));
        }
    }
}
