package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.worldgen.MythicBiomes;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MythicBiomeTagsProvider extends TagsProvider<Biome> {

    public MythicBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                   @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.BIOME, lookupProvider, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BiomeTags.IS_OVERWORLD)
                .addOptional(MythicBiomes.COLD_MYTHIC_CAVES.location())
                .addOptional(MythicBiomes.WARM_MYTHIC_CAVES.location());

        tag(BiomeTags.IS_NETHER)
                .addOptional(MythicBiomes.MYTHIC_RIFTS.location());

        tag(BiomeTags.IS_END)
                .addOptional(MythicBiomes.MYTHIC_BARRENS.location());
    }
}
