package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.item.MythicItems;

import java.util.concurrent.CompletableFuture;

public class MythicItemTagsProvider extends TagsProvider<Item> {

    public MythicItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.ITEM, lookupProvider);
    }

    private ModTagAppender<Item> tagB(TagKey<Item> key) {
        return new ModTagAppender<>(tag(key), BuiltInRegistries.ITEM);
    }

    private static TagKey<Item> modTag(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tagB(ItemTags.SWORDS)
            .add(MythicItems.AQUAMARINE_SWORD, MythicItems.CITRINE_SWORD, MythicItems.TOPAZ_SWORD, MythicItems.PERIDOT_SWORD)
            .add(MythicItems.RUBY_SWORD, MythicItems.SAPPHIRE_SWORD, MythicItems.JADE_SWORD, MythicItems.AMETRINE_SWORD);

        tagB(ItemTags.PICKAXES)
            .add(MythicItems.AQUAMARINE_PICKAXE, MythicItems.CITRINE_PICKAXE, MythicItems.TOPAZ_PICKAXE, MythicItems.PERIDOT_PICKAXE)
            .add(MythicItems.RUBY_PICKAXE, MythicItems.SAPPHIRE_PICKAXE, MythicItems.JADE_PICKAXE, MythicItems.AMETRINE_PICKAXE);

        tagB(ItemTags.AXES)
            .add(MythicItems.AQUAMARINE_AXE, MythicItems.CITRINE_AXE, MythicItems.TOPAZ_AXE, MythicItems.PERIDOT_AXE)
            .add(MythicItems.RUBY_AXE, MythicItems.SAPPHIRE_AXE, MythicItems.JADE_AXE, MythicItems.AMETRINE_AXE);

        tagB(ItemTags.SHOVELS)
            .add(MythicItems.AQUAMARINE_SHOVEL, MythicItems.CITRINE_SHOVEL, MythicItems.TOPAZ_SHOVEL, MythicItems.PERIDOT_SHOVEL)
            .add(MythicItems.RUBY_SHOVEL, MythicItems.SAPPHIRE_SHOVEL, MythicItems.JADE_SHOVEL, MythicItems.AMETRINE_SHOVEL);

        tagB(ItemTags.HOES)
            .add(MythicItems.AQUAMARINE_HOE, MythicItems.CITRINE_HOE, MythicItems.TOPAZ_HOE, MythicItems.PERIDOT_HOE)
            .add(MythicItems.RUBY_HOE, MythicItems.SAPPHIRE_HOE, MythicItems.JADE_HOE, MythicItems.AMETRINE_HOE);

        tagB(ItemTags.TRIM_MATERIALS)
            .add(MythicItems.AQUAMARINE_CRYSTAL_SHARD, MythicItems.CITRINE_CRYSTAL_SHARD, MythicItems.TOPAZ_CRYSTAL_SHARD, MythicItems.PERIDOT_CRYSTAL_SHARD)
            .add(MythicItems.RUBY_CRYSTAL_SHARD, MythicItems.SAPPHIRE_CRYSTAL_SHARD, MythicItems.JADE_CRYSTAL_SHARD, MythicItems.AMETRINE_CRYSTAL_SHARD)
            .add(MythicItems.NECOIUM_INGOT);

        tagB(ItemTags.TRIMMABLE_ARMOR)
            .add(MythicItems.AQUAMARINE_HELMET, MythicItems.AQUAMARINE_CHESTPLATE, MythicItems.AQUAMARINE_LEGGINGS, MythicItems.AQUAMARINE_BOOTS)
            .add(MythicItems.CITRINE_HELMET, MythicItems.CITRINE_CHESTPLATE, MythicItems.CITRINE_LEGGINGS, MythicItems.CITRINE_BOOTS)
            .add(MythicItems.TOPAZ_HELMET, MythicItems.TOPAZ_CHESTPLATE, MythicItems.TOPAZ_LEGGINGS, MythicItems.TOPAZ_BOOTS)
            .add(MythicItems.PERIDOT_HELMET, MythicItems.PERIDOT_CHESTPLATE, MythicItems.PERIDOT_LEGGINGS, MythicItems.PERIDOT_BOOTS)
            .add(MythicItems.RUBY_HELMET, MythicItems.RUBY_CHESTPLATE, MythicItems.RUBY_LEGGINGS, MythicItems.RUBY_BOOTS)
            .add(MythicItems.SAPPHIRE_HELMET, MythicItems.SAPPHIRE_CHESTPLATE, MythicItems.SAPPHIRE_LEGGINGS, MythicItems.SAPPHIRE_BOOTS)
            .add(MythicItems.JADE_HELMET, MythicItems.JADE_CHESTPLATE, MythicItems.JADE_LEGGINGS, MythicItems.JADE_BOOTS)
            .add(MythicItems.AMETRINE_HELMET, MythicItems.AMETRINE_CHESTPLATE, MythicItems.AMETRINE_LEGGINGS, MythicItems.AMETRINE_BOOTS);

        // Repair tags referenced by MythicToolMaterials / MythicArmorMaterials
        tagB(modTag("aquamarine_tool_materials")).add(MythicItems.AQUAMARINE_INGOT);
        tagB(modTag("citrine_tool_materials")).add(MythicItems.CITRINE_INGOT);
        tagB(modTag("topaz_tool_materials")).add(MythicItems.TOPAZ_INGOT);
        tagB(modTag("peridot_tool_materials")).add(MythicItems.PERIDOT_INGOT);
        tagB(modTag("ruby_tool_materials")).add(MythicItems.RUBY_INGOT);
        tagB(modTag("sapphire_tool_materials")).add(MythicItems.SAPPHIRE_INGOT);
        tagB(modTag("jade_tool_materials")).add(MythicItems.JADE_INGOT);
        tagB(modTag("ametrine_tool_materials")).add(MythicItems.AMETRINE_INGOT);

        tagB(modTag("repairs_aquamarine_armor")).add(MythicItems.AQUAMARINE_INGOT);
        tagB(modTag("repairs_citrine_armor")).add(MythicItems.CITRINE_INGOT);
        tagB(modTag("repairs_topaz_armor")).add(MythicItems.TOPAZ_INGOT);
        tagB(modTag("repairs_peridot_armor")).add(MythicItems.PERIDOT_INGOT);
        tagB(modTag("repairs_ruby_armor")).add(MythicItems.RUBY_INGOT);
        tagB(modTag("repairs_sapphire_armor")).add(MythicItems.SAPPHIRE_INGOT);
        tagB(modTag("repairs_jade_armor")).add(MythicItems.JADE_INGOT);
        tagB(modTag("repairs_ametrine_armor")).add(MythicItems.AMETRINE_INGOT);
    }
}
