package net.trique.mythicupgrades.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.trique.mythicupgrades.Constants;

import java.util.List;

public class MythicItemModelProvider extends ItemModelProvider {

    private static final String[] GEMS = {
        "aquamarine", "citrine", "topaz", "peridot", "ruby", "sapphire", "jade", "ametrine"
    };
    private static final String[] TOOLS = {"sword", "pickaxe", "axe", "shovel", "hoe"};
    private static final String[] ARMOR = {"helmet", "chestplate", "leggings", "boots"};

    private record TrimMat(String name, float index, boolean hasDarker) {}

    private static final List<TrimMat> ALL_TRIM_MATERIALS = buildTrimMaterials();

    private static List<TrimMat> buildTrimMaterials() {
        List<TrimMat> mats = new java.util.ArrayList<>(List.of(
            new TrimMat("quartz",    0.1f, false),
            new TrimMat("iron",      0.2f, true),
            new TrimMat("netherite", 0.3f, true),
            new TrimMat("redstone",  0.4f, false),
            new TrimMat("copper",    0.5f, false),
            new TrimMat("gold",      0.6f, true),
            new TrimMat("emerald",   0.7f, false),
            new TrimMat("diamond",   0.8f, true),
            new TrimMat("lapis",     0.9f, false),
            new TrimMat("amethyst",  1.0f, false)
        ));
        for (MythicTrimMaterialProvider.Entry e : MythicTrimMaterialProvider.ENTRIES) {
            mats.add(new TrimMat(e.name(), e.itemModelIndex(), e.armorMaterial() != null));
        }
        // override resolution is order-sensitive: predicates must be ascending
        mats.sort(java.util.Comparator.comparingDouble(TrimMat::index));
        return mats;
    }

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

        vanillaTrimmableArmor();
    }

    private void vanillaTrimmableArmor() {
        record VanillaSet(String prefix, String matchTrim) {}
        VanillaSet[] sets = {
            new VanillaSet("chainmail", null),
            new VanillaSet("iron",      "iron"),
            new VanillaSet("golden",    "gold"),
            new VanillaSet("diamond",   "diamond"),
            new VanillaSet("netherite", "netherite"),
        };

        for (VanillaSet set : sets) {
            for (String piece : ARMOR) {
                String item = set.prefix() + "_" + piece;
                trimmableArmor("minecraft", item, piece,
                    List.of(mcLoc("item/" + item)), set.matchTrim());
            }
        }

        for (String piece : ARMOR) {
            String item = "leather_" + piece;
            trimmableArmor("minecraft", item, piece,
                List.of(mcLoc("item/" + item), mcLoc("item/" + item + "_overlay")), null);
        }

        trimmableArmor("minecraft", "turtle_helmet", "helmet",
            List.of(mcLoc("item/turtle_helmet")), null);
    }

    private void trimmableArmor(String namespace, String item, String piece,
                                List<ResourceLocation> baseLayers, String matchTrim) {
        ItemModelBuilder base = withExistingParent(namespace + ":" + item, mcLoc("item/generated"));
        for (int i = 0; i < baseLayers.size(); i++) {
            base.texture("layer" + i, baseLayers.get(i));
        }

        for (TrimMat mat : ALL_TRIM_MATERIALS) {
            boolean darker = mat.hasDarker() && mat.name().equals(matchTrim);
            ResourceLocation trimTex = mcLoc("trims/items/" + piece + "_trim_" + mat.name() + (darker ? "_darker" : ""));
            existingFileHelper.trackGenerated(trimTex, TEXTURE);

            ItemModelBuilder trimModel = withExistingParent(
                namespace + ":" + item + "_" + mat.name() + "_trim", mcLoc("item/generated"));
            for (int i = 0; i < baseLayers.size(); i++) {
                trimModel.texture("layer" + i, baseLayers.get(i));
            }
            trimModel.texture("layer" + baseLayers.size(), trimTex);

            base.override()
                .predicate(mcLoc("trim_type"), mat.index())
                .model(trimModel)
                .end();
        }
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
            trimmableArmor(Constants.MOD_ID, gem + "_" + piece, piece,
                List.of(modLoc("item/" + gem + "_" + piece)), gem);
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
