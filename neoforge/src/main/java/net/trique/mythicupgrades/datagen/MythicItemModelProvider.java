package net.trique.mythicupgrades.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.trique.mythicupgrades.Constants;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 26.2 item asset generator. Emits:
 *  - assets/mythicupgrades/items/*.json client item definitions for every mod item
 *  - trim-aware select dispatch (on minecraft:trim_material) for mod armor
 *  - overlay models for every armor x trim material combination
 *  - assets/minecraft/items/*.json overrides for vanilla armor so mythic trim
 *    materials render item overlays there too
 *  - assets/mythicupgrades/equipment/*.json equipment assets for mod armor
 */
public class MythicItemModelProvider implements DataProvider {

    private static final String[] GEMS = {
        "aquamarine", "citrine", "topaz", "peridot", "ruby", "sapphire", "jade", "ametrine"
    };
    private static final String[] ARMOR_TYPES = {"helmet", "chestplate", "leggings", "boots"};

    private record TrimMat(String id, String asset, boolean hasDarker) {}

    private static final List<TrimMat> VANILLA_MATERIALS = List.of(
        new TrimMat("minecraft:quartz", "quartz", false),
        new TrimMat("minecraft:iron", "iron", true),
        new TrimMat("minecraft:netherite", "netherite", true),
        new TrimMat("minecraft:redstone", "redstone", false),
        new TrimMat("minecraft:copper", "copper", true),
        new TrimMat("minecraft:gold", "gold", true),
        new TrimMat("minecraft:emerald", "emerald", false),
        new TrimMat("minecraft:diamond", "diamond", true),
        new TrimMat("minecraft:lapis", "lapis", false),
        new TrimMat("minecraft:amethyst", "amethyst", false),
        new TrimMat("minecraft:resin", "resin", false)
    );

    private static final List<TrimMat> MYTHIC_MATERIALS = List.of(
        new TrimMat("mythicupgrades:aquamarine", "aquamarine", true),
        new TrimMat("mythicupgrades:citrine", "citrine", false),
        new TrimMat("mythicupgrades:topaz", "topaz", true),
        new TrimMat("mythicupgrades:peridot", "peridot", true),
        new TrimMat("mythicupgrades:ruby", "ruby", true),
        new TrimMat("mythicupgrades:sapphire", "sapphire", true),
        new TrimMat("mythicupgrades:jade", "jade", true),
        new TrimMat("mythicupgrades:ametrine", "ametrine", true),
        new TrimMat("mythicupgrades:necoium", "necoium", false)
    );

    // vanilla trimmable humanoid armor: base name -> equipment asset name ("" = no darker interactions)
    private record VanillaArmor(String item, String type, String equipmentAsset, boolean leather) {}

    private static final List<VanillaArmor> VANILLA_ARMOR = buildVanillaArmor();

    private static List<VanillaArmor> buildVanillaArmor() {
        List<VanillaArmor> list = new ArrayList<>();
        for (String type : ARMOR_TYPES) {
            list.add(new VanillaArmor("leather_" + type, type, "leather", true));
            list.add(new VanillaArmor("copper_" + type, type, "copper", false));
            list.add(new VanillaArmor("chainmail_" + type, type, "chainmail", false));
            list.add(new VanillaArmor("iron_" + type, type, "iron", false));
            list.add(new VanillaArmor("golden_" + type, type, "gold", false));
            list.add(new VanillaArmor("diamond_" + type, type, "diamond", false));
            list.add(new VanillaArmor("netherite_" + type, type, "netherite", false));
        }
        list.add(new VanillaArmor("turtle_helmet", "helmet", "turtle_scute", false));
        return list;
    }

    private static final int LEATHER_DEFAULT_TINT = -6265536;

    private final PackOutput output;
    private final List<CompletableFuture<?>> futures = new ArrayList<>();
    private Path assets;
    private CachedOutput cache;

    public MythicItemModelProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        this.cache = cache;
        this.assets = output.getOutputFolder(PackOutput.Target.RESOURCE_PACK);
        futures.clear();

        // Mod items: every registered mythicupgrades item gets a client item definition.
        for (Identifier id : BuiltInRegistries.ITEM.keySet()) {
            if (!id.getNamespace().equals(Constants.MOD_ID)) continue;
            String name = id.getPath();
            if (isMythicArmor(name)) {
                mythicArmor(name);
            } else if (isBlockItem(name)) {
                // block items get their client item definition + models from MythicBlockModelProvider
            } else {
                simpleItem(name);
            }
        }

        // Vanilla armor overrides so mythic trims show item overlays there too.
        for (VanillaArmor armor : VANILLA_ARMOR) {
            vanillaArmor(armor);
        }

        // Equipment assets for mod armor rendering.
        for (String gem : GEMS) {
            equipmentAsset(gem);
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private static boolean isBlockItem(String name) {
        return BuiltInRegistries.BLOCK.containsKey(Identifier.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    private static boolean isMythicArmor(String name) {
        for (String gem : GEMS) {
            for (String type : ARMOR_TYPES) {
                if (name.equals(gem + "_" + type)) return true;
            }
        }
        return false;
    }

    private void save(JsonObject json, String relativePath) {
        futures.add(DataProvider.saveStable(cache, json, assets.resolve(relativePath)));
    }

    private static JsonObject modelRef(String model) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "minecraft:model");
        obj.addProperty("model", model);
        return obj;
    }

    private static JsonObject wrap(JsonObject model) {
        JsonObject root = new JsonObject();
        root.add("model", model);
        return root;
    }

    private static final String[] HANDHELD_SUFFIXES = {"_sword", "_pickaxe", "_axe", "_shovel", "_hoe"};

    private static boolean isHandheld(String name) {
        for (String suffix : HANDHELD_SUFFIXES) {
            if (name.endsWith(suffix)) return true;
        }
        return false;
    }

    private void simpleItem(String name) {
        save(wrap(modelRef(Constants.MOD_ID + ":item/" + name)),
                Constants.MOD_ID + "/items/" + name + ".json");
        JsonObject model = generatedModel(Constants.MOD_ID + ":item/" + name, null, null);
        if (isHandheld(name)) {
            model.addProperty("parent", "minecraft:item/handheld");
        }
        save(model, Constants.MOD_ID + "/models/item/" + name + ".json");
    }

    // ---- mod armor ----

    private void mythicArmor(String name) {
        String gem = name.substring(0, name.indexOf('_'));
        String type = name.substring(name.indexOf('_') + 1);

        // base model (no trim)
        JsonObject baseModel = generatedModel(Constants.MOD_ID + ":item/" + name, null, null);
        save(baseModel, Constants.MOD_ID + "/models/item/" + name + ".json");

        JsonArray cases = new JsonArray();
        for (TrimMat mat : allMaterials()) {
            // darker overlay when the trim gem matches the armor gem
            String suffix = (mat.asset().equals(gem) && mat.hasDarker()) ? mat.asset() + "_darker" : mat.asset();
            String trimModelName = name + "_" + mat.asset() + "_trim";
            JsonObject trimModel = generatedModel(
                    Constants.MOD_ID + ":item/" + name,
                    "minecraft:trims/items/" + type + "_trim_" + suffix,
                    null);
            save(trimModel, Constants.MOD_ID + "/models/item/" + trimModelName + ".json");

            JsonObject caseObj = new JsonObject();
            caseObj.add("model", modelRef(Constants.MOD_ID + ":item/" + trimModelName));
            caseObj.addProperty("when", mat.id());
            cases.add(caseObj);
        }

        save(wrap(select(cases, modelRef(Constants.MOD_ID + ":item/" + name))),
                Constants.MOD_ID + "/items/" + name + ".json");
    }

    // ---- vanilla armor overrides ----

    private void vanillaArmor(VanillaArmor armor) {
        JsonArray cases = new JsonArray();

        for (TrimMat mat : VANILLA_MATERIALS) {
            String suffix = (mat.asset().equals(armor.equipmentAsset()) && mat.hasDarker())
                    ? mat.asset() + "_darker" : mat.asset();
            // vanilla ships these models already
            String model = "minecraft:item/" + armor.item() + "_" + mat.asset() + "_trim";
            cases.add(trimCase(mat.id(), model, armor.leather()));
            if (suffix.endsWith("_darker")) {
                // model name stays the same; vanilla's model already points at the darker sprite
            }
        }

        for (TrimMat mat : MYTHIC_MATERIALS) {
            // our materials never darker on vanilla armor (asset ids differ)
            String modelName = armor.item() + "_" + mat.asset() + "_trim";
            JsonObject trimModel = armor.leather()
                    ? generatedModel("minecraft:item/" + armor.item(),
                        "minecraft:item/" + armor.item() + "_overlay",
                        "minecraft:trims/items/" + armor.type() + "_trim_" + mat.asset())
                    : generatedModel("minecraft:item/" + armor.item(),
                        "minecraft:trims/items/" + armor.type() + "_trim_" + mat.asset(),
                        null);
            save(trimModel, Constants.MOD_ID + "/models/item/" + modelName + ".json");
            cases.add(trimCase(mat.id(), Constants.MOD_ID + ":item/" + modelName, armor.leather()));
        }

        JsonObject fallback = armor.leather()
                ? tinted(modelRef("minecraft:item/" + armor.item()))
                : modelRef("minecraft:item/" + armor.item());

        save(wrap(select(cases, fallback)), "minecraft/items/" + armor.item() + ".json");
    }

    private static JsonObject trimCase(String materialId, String model, boolean leather) {
        JsonObject modelObj = modelRef(model);
        if (leather) modelObj = tinted(modelObj);
        JsonObject caseObj = new JsonObject();
        caseObj.add("model", modelObj);
        caseObj.addProperty("when", materialId);
        return caseObj;
    }

    private static JsonObject tinted(JsonObject model) {
        JsonObject tint = new JsonObject();
        tint.addProperty("type", "minecraft:dye");
        tint.addProperty("default", LEATHER_DEFAULT_TINT);
        JsonArray tints = new JsonArray();
        tints.add(tint);
        model.add("tints", tints);
        return model;
    }

    private static JsonObject select(JsonArray cases, JsonObject fallback) {
        JsonObject select = new JsonObject();
        select.addProperty("type", "minecraft:select");
        select.addProperty("property", "minecraft:trim_material");
        select.add("cases", cases);
        select.add("fallback", fallback);
        return select;
    }

    private static List<TrimMat> allMaterials() {
        List<TrimMat> all = new ArrayList<>(VANILLA_MATERIALS);
        all.addAll(MYTHIC_MATERIALS);
        return all;
    }

    private static JsonObject generatedModel(String layer0, String layer1, String layer2) {
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", layer0);
        if (layer1 != null) textures.addProperty("layer1", layer1);
        if (layer2 != null) textures.addProperty("layer2", layer2);
        JsonObject model = new JsonObject();
        model.addProperty("parent", "minecraft:item/generated");
        model.add("textures", textures);
        return model;
    }

    // ---- equipment assets ----

    private void equipmentAsset(String gem) {
        JsonObject textureEntry = new JsonObject();
        textureEntry.addProperty("texture", Constants.MOD_ID + ":" + gem);
        JsonArray layerList = new JsonArray();
        layerList.add(textureEntry);

        JsonObject layers = new JsonObject();
        layers.add("humanoid", layerList.deepCopy());
        layers.add("humanoid_leggings", layerList.deepCopy());

        JsonObject root = new JsonObject();
        root.add("layers", layers);
        save(root, Constants.MOD_ID + "/equipment/" + gem + ".json");
    }

    @Override
    public String getName() {
        return "MythicUpgrades Item Definitions";
    }
}
