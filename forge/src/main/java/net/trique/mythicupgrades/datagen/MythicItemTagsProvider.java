package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ItemTagsProvider;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.item.MythicItems;

import java.util.concurrent.CompletableFuture;

public class MythicItemTagsProvider extends ItemTagsProvider {

    public MythicItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                  BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagsProvider.contentsGetter(), Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p) {
        tag(ItemTags.PICKAXES)
            .add(MythicItems.AQUAMARINE_PICKAXE, MythicItems.CITRINE_PICKAXE, MythicItems.TOPAZ_PICKAXE,
                 MythicItems.PERIDOT_PICKAXE, MythicItems.RUBY_PICKAXE, MythicItems.SAPPHIRE_PICKAXE,
                 MythicItems.JADE_PICKAXE, MythicItems.AMETRINE_PICKAXE);

        tag(ItemTags.SWORDS)
            .add(MythicItems.AQUAMARINE_SWORD, MythicItems.CITRINE_SWORD, MythicItems.TOPAZ_SWORD,
                 MythicItems.PERIDOT_SWORD, MythicItems.RUBY_SWORD, MythicItems.SAPPHIRE_SWORD,
                 MythicItems.JADE_SWORD, MythicItems.AMETRINE_SWORD);

        tag(ItemTags.AXES)
            .add(MythicItems.AQUAMARINE_AXE, MythicItems.CITRINE_AXE, MythicItems.TOPAZ_AXE,
                 MythicItems.PERIDOT_AXE, MythicItems.RUBY_AXE, MythicItems.SAPPHIRE_AXE,
                 MythicItems.JADE_AXE, MythicItems.AMETRINE_AXE);

        tag(ItemTags.SHOVELS)
            .add(MythicItems.AQUAMARINE_SHOVEL, MythicItems.CITRINE_SHOVEL, MythicItems.TOPAZ_SHOVEL,
                 MythicItems.PERIDOT_SHOVEL, MythicItems.RUBY_SHOVEL, MythicItems.SAPPHIRE_SHOVEL,
                 MythicItems.JADE_SHOVEL, MythicItems.AMETRINE_SHOVEL);

        tag(ItemTags.HOES)
            .add(MythicItems.AQUAMARINE_HOE, MythicItems.CITRINE_HOE, MythicItems.TOPAZ_HOE,
                 MythicItems.PERIDOT_HOE, MythicItems.RUBY_HOE, MythicItems.SAPPHIRE_HOE,
                 MythicItems.JADE_HOE, MythicItems.AMETRINE_HOE);
    }
}
