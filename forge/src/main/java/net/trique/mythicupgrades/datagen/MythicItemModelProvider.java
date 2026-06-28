package net.trique.mythicupgrades.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.trique.mythicupgrades.Constants;

public class MythicItemModelProvider extends ItemModelProvider {

    private static final String[] GEMS = {
        "aquamarine", "citrine", "topaz", "peridot", "ruby", "sapphire", "jade", "ametrine"
    };
    private static final String[] TOOLS = {"sword", "pickaxe", "axe", "shovel", "hoe"};
    private static final String[] ARMOR = {"helmet", "chestplate", "leggings", "boots"};

    public MythicItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Constants.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        for (String gem : GEMS) {
            gemItems(gem);
        }
        generated("raw_necoium", modLoc("item/raw_necoium"));
        generated("necoium_ingot", modLoc("item/necoium_ingot"));
        generated("necoium_carrot", modLoc("item/necoium_carrot"));
        generated("mythic_upgrade_smithing_template", modLoc("item/mythic_upgrade_smithing_template"));
        blockItem("necoium_ore");
        blockItem("deepslate_necoium_ore");
        blockItem("raw_necoium_block");
        blockItem("necoium_block");
    }

    private void gemItems(String gem) {
        generated(gem, modLoc("item/" + gem));
        generated(gem + "_ingot", modLoc("item/" + gem + "_ingot"));
        generated(gem + "_crystal_shard", modLoc("item/" + gem + "_crystal_shard"));
        generated(gem + "_upgrade_smithing_template", modLoc("item/mythic_upgrade_smithing_template"));
        for (String tool : TOOLS) {
            handheld(gem + "_" + tool, modLoc("item/" + gem + "_" + tool));
        }
        for (String piece : ARMOR) {
            generated(gem + "_" + piece, modLoc("item/" + gem + "_" + piece));
        }
        blockItem(gem + "_ore");
        if (gem.equals("aquamarine") || gem.equals("citrine") || gem.equals("topaz") || gem.equals("peridot")) {
            blockItem("deepslate_" + gem + "_ore");
        }
        blockItem(gem + "_block");
        blockItem(gem + "_crystal_block");
        blockItem("budding_" + gem + "_crystal");
        clusterItem(gem + "_crystal_cluster");
        clusterItem("large_" + gem + "_crystal_bud");
        clusterItem("medium_" + gem + "_crystal_bud");
        clusterItem("small_" + gem + "_crystal_bud");
        blockItem(gem + "_crystal_block_slab");
        blockItem(gem + "_crystal_block_stairs");
        blockItem("polished_" + gem + "_crystal_block");
        blockItem("polished_" + gem + "_crystal_block_slab");
        blockItem("polished_" + gem + "_crystal_block_stairs");
        blockItem(gem + "_crystal_bricks");
        blockItem(gem + "_crystal_bricks_slab");
        blockItem(gem + "_crystal_bricks_stairs");
        wallItem(gem + "_crystal_bricks_wall", gem + "_crystal_bricks");
        blockItem(gem + "_crystal_pillar");
        blockItem("cut_" + gem + "_crystal_pillar");
        blockItem(gem + "_schist");
        blockItem(gem + "_schist_slab");
        blockItem(gem + "_schist_stairs");
        wallItem(gem + "_schist_wall", gem + "_schist");
        blockItem("polished_" + gem + "_schist");
        blockItem("polished_" + gem + "_schist_slab");
        blockItem("polished_" + gem + "_schist_stairs");
    }

    private void generated(String name, ResourceLocation texture) {
        withExistingParent(name, mcLoc("item/generated")).texture("layer0", texture);
    }

    private void handheld(String name, ResourceLocation texture) {
        withExistingParent(name, mcLoc("item/handheld")).texture("layer0", texture);
    }

    private void blockItem(String name) {
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(
            new ResourceLocation(Constants.MOD_ID, "block/" + name)));
    }

    private void wallItem(String name, String texture) {
        withExistingParent(name, mcLoc("block/wall_inventory"))
            .texture("wall", modLoc("block/" + texture));
    }

    private void clusterItem(String name) {
        withExistingParent(name, mcLoc("item/generated")).texture("layer0", modLoc("block/" + name));
    }
}
