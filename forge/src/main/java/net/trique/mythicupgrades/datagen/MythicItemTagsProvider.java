package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.trique.mythicupgrades.item.MythicItems;

import java.util.concurrent.CompletableFuture;

public class MythicItemTagsProvider extends ItemTagsProvider {

    public MythicItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                  CompletableFuture<TagsProvider.TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
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

        tag(ItemTags.CLUSTER_MAX_HARVESTABLES)
            .add(MythicItems.AQUAMARINE_PICKAXE, MythicItems.CITRINE_PICKAXE, MythicItems.TOPAZ_PICKAXE,
                 MythicItems.PERIDOT_PICKAXE, MythicItems.RUBY_PICKAXE, MythicItems.SAPPHIRE_PICKAXE,
                 MythicItems.JADE_PICKAXE, MythicItems.AMETRINE_PICKAXE);

        tag(ItemTags.TRIM_MATERIALS)
            .add(MythicItems.AQUAMARINE_CRYSTAL_SHARD, MythicItems.CITRINE_CRYSTAL_SHARD,
                 MythicItems.TOPAZ_CRYSTAL_SHARD, MythicItems.PERIDOT_CRYSTAL_SHARD,
                 MythicItems.RUBY_CRYSTAL_SHARD, MythicItems.SAPPHIRE_CRYSTAL_SHARD,
                 MythicItems.JADE_CRYSTAL_SHARD, MythicItems.AMETRINE_CRYSTAL_SHARD,
                 MythicItems.NECOIUM_INGOT);

        tag(ItemTags.TRIMMABLE_ARMOR)
            .add(MythicItems.AQUAMARINE_HELMET, MythicItems.AQUAMARINE_CHESTPLATE,
                 MythicItems.AQUAMARINE_LEGGINGS, MythicItems.AQUAMARINE_BOOTS)
            .add(MythicItems.CITRINE_HELMET, MythicItems.CITRINE_CHESTPLATE,
                 MythicItems.CITRINE_LEGGINGS, MythicItems.CITRINE_BOOTS)
            .add(MythicItems.TOPAZ_HELMET, MythicItems.TOPAZ_CHESTPLATE,
                 MythicItems.TOPAZ_LEGGINGS, MythicItems.TOPAZ_BOOTS)
            .add(MythicItems.PERIDOT_HELMET, MythicItems.PERIDOT_CHESTPLATE,
                 MythicItems.PERIDOT_LEGGINGS, MythicItems.PERIDOT_BOOTS)
            .add(MythicItems.RUBY_HELMET, MythicItems.RUBY_CHESTPLATE,
                 MythicItems.RUBY_LEGGINGS, MythicItems.RUBY_BOOTS)
            .add(MythicItems.SAPPHIRE_HELMET, MythicItems.SAPPHIRE_CHESTPLATE,
                 MythicItems.SAPPHIRE_LEGGINGS, MythicItems.SAPPHIRE_BOOTS)
            .add(MythicItems.JADE_HELMET, MythicItems.JADE_CHESTPLATE,
                 MythicItems.JADE_LEGGINGS, MythicItems.JADE_BOOTS)
            .add(MythicItems.AMETRINE_HELMET, MythicItems.AMETRINE_CHESTPLATE,
                 MythicItems.AMETRINE_LEGGINGS, MythicItems.AMETRINE_BOOTS);
    }
}
