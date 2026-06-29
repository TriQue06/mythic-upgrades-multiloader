package net.trique.mythicupgrades.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.trique.mythicupgrades.Constants;

public class MythicItemModelProvider extends ItemModelProvider {

    private static final String[] GEMS = {
        "aquamarine", "citrine", "topaz", "peridot", "ruby", "sapphire", "jade", "ametrine"
    };

    public MythicItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Constants.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        for (String gem : GEMS) {
            gemItems(gem);
        }

        generated("raw_necoium",                       modLoc("item/raw_necoium"));
        generated("necoium_ingot",                     modLoc("item/necoium_ingot"));
        generated("necoium_carrot",                    modLoc("item/necoium_carrot"));
        generated("mythic_upgrade_smithing_template",  modLoc("item/mythic_upgrade_smithing_template"));

        blockItem("necoium_ore");
        blockItem("deepslate_necoium_ore");
        blockItem("raw_necoium_block");
        blockItem("necoium_block");

        // Legacy items — keep in registry but reuse existing textures
        for (String gem : GEMS) {
            generated(gem + "_upgrade_smithing_template", modLoc("item/mythic_upgrade_smithing_template"));
        }
    }

    private void gemItems(String gem) {
        boolean caveGem = gem.equals("aquamarine") || gem.equals("citrine") || gem.equals("topaz") || gem.equals("peridot");

        generated(gem,                  modLoc("item/" + gem));
        generated(gem + "_ingot",       modLoc("item/" + gem + "_ingot"));
        generated(gem + "_crystal_shard", modLoc("item/" + gem + "_crystal_shard"));

        handheld(gem + "_sword",   modLoc("item/" + gem + "_sword"));
        handheld(gem + "_pickaxe", modLoc("item/" + gem + "_pickaxe"));
        handheld(gem + "_axe",     modLoc("item/" + gem + "_axe"));
        handheld(gem + "_shovel",  modLoc("item/" + gem + "_shovel"));
        handheld(gem + "_hoe",     modLoc("item/" + gem + "_hoe"));

        generated(gem + "_helmet",     modLoc("item/" + gem + "_helmet"));
        generated(gem + "_chestplate", modLoc("item/" + gem + "_chestplate"));
        generated(gem + "_leggings",   modLoc("item/" + gem + "_leggings"));
        generated(gem + "_boots",      modLoc("item/" + gem + "_boots"));

        blockItem(gem + "_ore");
        if (caveGem) blockItem("deepslate_" + gem + "_ore");
        blockItem(gem + "_block");
        blockItem(gem + "_crystal_block");
        blockItem("budding_" + gem + "_crystal");
        blockItem("polished_" + gem + "_crystal_block");
        blockItem(gem + "_crystal_bricks");
        blockItem(gem + "_crystal_block_slab");
        blockItem(gem + "_crystal_block_stairs");
        blockItem("polished_" + gem + "_crystal_block_slab");
        blockItem("polished_" + gem + "_crystal_block_stairs");
        blockItem(gem + "_crystal_bricks_slab");
        blockItem(gem + "_crystal_bricks_stairs");
        blockItem(gem + "_crystal_pillar");
        blockItem("cut_" + gem + "_crystal_pillar");
        blockItem(gem + "_schist");
        blockItem(gem + "_schist_slab");
        blockItem(gem + "_schist_stairs");
        blockItem("polished_" + gem + "_schist");
        blockItem("polished_" + gem + "_schist_slab");
        blockItem("polished_" + gem + "_schist_stairs");

        clusterItem(gem + "_crystal_cluster");
        clusterItem("large_" + gem + "_crystal_bud");
        clusterItem("medium_" + gem + "_crystal_bud");
        clusterItem("small_" + gem + "_crystal_bud");
    }

    private void generated(String name, ResourceLocation tex) {
        withExistingParent(name, mcLoc("item/generated")).texture("layer0", tex);
    }

    private void handheld(String name, ResourceLocation tex) {
        withExistingParent(name, mcLoc("item/handheld")).texture("layer0", tex);
    }

    private void blockItem(String name) {
        withExistingParent(name, modLoc("block/" + name));
    }

    private void clusterItem(String name) {
        withExistingParent(name, mcLoc("item/generated")).texture("layer0", modLoc("block/" + name));
    }
}
