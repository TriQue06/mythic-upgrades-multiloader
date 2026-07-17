package net.trique.mythicupgrades.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.trique.mythicupgrades.Constants;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 26.2 block asset generator. Emits blockstates + block/item models for every
 * registered mythicupgrades block, mirroring the shape/behaviour of the old
 * Forge-era MythicBlockStateProvider (deleted) but hand-built via raw JSON so
 * it stays portable across Fabric/NeoForge (shared common/src/generated).
 */
public class MythicBlockModelProvider implements DataProvider {

    private static final String[] GEMS = {
        "aquamarine", "citrine", "topaz", "peridot", "ruby", "sapphire", "jade", "ametrine"
    };
    // gems that have a deepslate ore variant registered in MythicBlocks
    private static final String[] DEEPSLATE_ORE_GEMS = {"aquamarine", "citrine", "topaz", "peridot"};

    private final PackOutput output;
    private final List<CompletableFuture<?>> futures = new ArrayList<>();
    private Path assets;
    private CachedOutput cache;

    public MythicBlockModelProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        this.cache = cache;
        this.assets = output.getOutputFolder(PackOutput.Target.RESOURCE_PACK);
        futures.clear();

        for (String gem : GEMS) {
            gemBlocks(gem);
        }

        simpleBlock("necoium_ore");
        simpleBlock("deepslate_necoium_ore");
        simpleBlock("raw_necoium_block");
        simpleBlock("necoium_block");

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private void gemBlocks(String gem) {
        simpleBlock(gem + "_ore");
        for (String deepslateGem : DEEPSLATE_ORE_GEMS) {
            if (deepslateGem.equals(gem)) {
                simpleBlock("deepslate_" + gem + "_ore");
                break;
            }
        }

        simpleBlock(gem + "_block");
        simpleBlock(gem + "_crystal_block");
        simpleBlock("budding_" + gem + "_crystal");

        cluster(gem + "_crystal_cluster");
        cluster("large_" + gem + "_crystal_bud");
        cluster("medium_" + gem + "_crystal_bud");
        cluster("small_" + gem + "_crystal_bud");

        simpleBlock("polished_" + gem + "_crystal_block");
        simpleBlock(gem + "_crystal_bricks");

        slabAndStairs(gem + "_crystal_block");
        slabAndStairs("polished_" + gem + "_crystal_block");
        slabAndStairs(gem + "_crystal_bricks");

        pillar(gem + "_crystal_pillar", gem + "_crystal_pillar_top");
        pillar("cut_" + gem + "_crystal_pillar", gem + "_crystal_pillar_top");

        wall(gem + "_crystal_bricks_wall", gem + "_crystal_bricks");

        simpleBlock(gem + "_schist");
        simpleBlock("polished_" + gem + "_schist");
        slabAndStairs(gem + "_schist");
        slabAndStairs("polished_" + gem + "_schist");
        wall(gem + "_schist_wall", gem + "_schist");
    }

    private void slabAndStairs(String base) {
        slab(base + "_slab", base);
        stairs(base + "_stairs", base);
    }

    // ---- io helpers ----

    private void save(JsonObject json, String relativePath) {
        futures.add(DataProvider.saveStable(cache, json, assets.resolve(relativePath)));
    }

    private static String blockId(String name) {
        return Constants.MOD_ID + ":block/" + name;
    }

    private static String tex(String name) {
        return Constants.MOD_ID + ":block/" + name;
    }

    private void saveBlockstate(String name, JsonObject blockstate) {
        save(blockstate, Constants.MOD_ID + "/blockstates/" + name + ".json");
    }

    private void saveBlockModel(String name, JsonObject model) {
        save(model, Constants.MOD_ID + "/models/block/" + name + ".json");
    }

    private void saveItemModel(String name, JsonObject model) {
        save(model, Constants.MOD_ID + "/models/item/" + name + ".json");
    }

    private void itemModelInherit(String name) {
        JsonObject model = new JsonObject();
        model.addProperty("parent", blockId(name));
        saveItemModel(name, model);
        saveItemClientDef(name);
    }

    // 26.2 client item definition: assets/mythicupgrades/items/<name>.json
    private void saveItemClientDef(String name) {
        JsonObject modelRef = new JsonObject();
        modelRef.addProperty("type", "minecraft:model");
        modelRef.addProperty("model", Constants.MOD_ID + ":item/" + name);
        JsonObject root = new JsonObject();
        root.add("model", modelRef);
        save(root, Constants.MOD_ID + "/items/" + name + ".json");
    }

    private static JsonObject variantModel(String model, Integer x, Integer y, boolean uvlock) {
        JsonObject obj = new JsonObject();
        obj.addProperty("model", model);
        if (x != null && x != 0) obj.addProperty("x", x);
        if (y != null && y != 0) obj.addProperty("y", y);
        if (uvlock) obj.addProperty("uvlock", true);
        return obj;
    }

    // ---- simple full cube blocks ----

    private void simpleBlock(String name) {
        JsonObject textures = new JsonObject();
        textures.addProperty("all", tex(name));
        JsonObject model = new JsonObject();
        model.addProperty("parent", "minecraft:block/cube_all");
        model.add("textures", textures);
        saveBlockModel(name, model);

        JsonObject variants = new JsonObject();
        variants.add("", variantModel(blockId(name), null, null, false));
        JsonObject blockstate = new JsonObject();
        blockstate.add("variants", variants);
        saveBlockstate(name, blockstate);

        itemModelInherit(name);
    }

    // ---- pillars ----

    private void pillar(String name, String topTexture) {
        JsonObject textures = new JsonObject();
        textures.addProperty("end", tex(topTexture));
        textures.addProperty("side", tex(name));
        JsonObject model = new JsonObject();
        model.addProperty("parent", "minecraft:block/cube_column");
        model.add("textures", textures);
        saveBlockModel(name, model);

        JsonObject variants = new JsonObject();
        variants.add("axis=y", variantModel(blockId(name), null, null, false));
        variants.add("axis=x", variantModel(blockId(name), 90, 90, false));
        variants.add("axis=z", variantModel(blockId(name), 90, null, false));
        JsonObject blockstate = new JsonObject();
        blockstate.add("variants", variants);
        saveBlockstate(name, blockstate);

        itemModelInherit(name);
    }

    // ---- amethyst-style clusters/buds ----

    private static final String[] FACINGS = {"down", "up", "north", "south", "east", "west"};

    private void cluster(String name) {
        JsonObject textures = new JsonObject();
        textures.addProperty("cross", tex(name));
        JsonObject model = new JsonObject();
        model.addProperty("parent", "minecraft:block/cross");
        model.add("textures", textures);
        saveBlockModel(name, model);

        // blockstate: mirrors vanilla amethyst_cluster.json exactly (facing only, no waterlogged keys)
        JsonObject variants = new JsonObject();
        for (String facing : FACINGS) {
            int x, y;
            switch (facing) {
                case "down" -> { x = 180; y = 0; }
                case "north" -> { x = 90; y = 0; }
                case "south" -> { x = 90; y = 180; }
                case "east" -> { x = 90; y = 90; }
                case "west" -> { x = 90; y = 270; }
                default -> { x = 0; y = 0; } // up
            }
            variants.add("facing=" + facing, variantModel(blockId(name), x, y, false));
        }
        JsonObject blockstate = new JsonObject();
        blockstate.add("variants", variants);
        saveBlockstate(name, blockstate);

        // item model: flat generated sprite with vanilla amethyst display tweaks,
        // not a 3D inherit of the cross block model
        JsonObject itemModel = new JsonObject();
        JsonObject itemTextures = new JsonObject();
        itemTextures.addProperty("layer0", tex(name));
        JsonObject display = new JsonObject();
        if (name.endsWith("_cluster")) {
            // vanilla amethyst_cluster item model
            itemModel.addProperty("parent", "minecraft:item/generated");
            display.add("head", displayEntry(null, new int[]{0, 14, -5}, null));
        } else if (name.startsWith("large_")) {
            // vanilla large_amethyst_bud item model (parent carries the shared bud display)
            itemModel.addProperty("parent", "minecraft:item/amethyst_bud");
            display.add("fixed", displayEntry(null, new int[]{0, 4, 0}, null));
        } else if (name.startsWith("medium_")) {
            itemModel.addProperty("parent", "minecraft:item/amethyst_bud");
            display.add("fixed", displayEntry(null, new int[]{0, 6, 0}, null));
        } else {
            // small bud
            itemModel.addProperty("parent", "minecraft:item/amethyst_bud");
            display.add("firstperson_righthand",
                    displayEntry(new int[]{0, -90, 25}, new int[]{0, 6, 0}, new double[]{0.68, 0.68, 0.68}));
            display.add("fixed", displayEntry(null, new int[]{0, 7, 0}, null));
        }
        itemModel.add("textures", itemTextures);
        itemModel.add("display", display);
        saveItemModel(name, itemModel);
        saveItemClientDef(name);
    }

    private static JsonObject displayEntry(int[] rotation, int[] translation, double[] scale) {
        JsonObject entry = new JsonObject();
        if (rotation != null) {
            JsonArray arr = new JsonArray();
            for (int v : rotation) arr.add(v);
            entry.add("rotation", arr);
        }
        if (translation != null) {
            JsonArray arr = new JsonArray();
            for (int v : translation) arr.add(v);
            entry.add("translation", arr);
        }
        if (scale != null) {
            JsonArray arr = new JsonArray();
            for (double v : scale) arr.add(v);
            entry.add("scale", arr);
        }
        return entry;
    }

    // ---- slabs ----

    private void slab(String name, String base) {
        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", tex(base));
        textures.addProperty("top", tex(base));
        textures.addProperty("side", tex(base));

        JsonObject bottomModel = new JsonObject();
        bottomModel.addProperty("parent", "minecraft:block/slab");
        bottomModel.add("textures", textures);
        saveBlockModel(name, bottomModel);

        JsonObject topModel = new JsonObject();
        topModel.addProperty("parent", "minecraft:block/slab_top");
        topModel.add("textures", textures.deepCopy());
        saveBlockModel(name + "_top", topModel);

        JsonObject variants = new JsonObject();
        variants.add("type=bottom", variantModel(blockId(name), null, null, false));
        variants.add("type=double", variantModel(blockId(base), null, null, false));
        variants.add("type=top", variantModel(blockId(name + "_top"), null, null, false));
        JsonObject blockstate = new JsonObject();
        blockstate.add("variants", variants);
        saveBlockstate(name, blockstate);

        JsonObject itemModel = new JsonObject();
        itemModel.addProperty("parent", blockId(name));
        saveItemModel(name, itemModel);
        saveItemClientDef(name);
    }

    // ---- stairs ----

    private static int stairsBaseY(String facing) {
        return switch (facing) {
            case "east" -> 0;
            case "south" -> 90;
            case "west" -> 180;
            case "north" -> 270;
            default -> 0;
        };
    }

    private void stairs(String name, String base) {
        JsonObject textures = new JsonObject();
        textures.addProperty("bottom", tex(base));
        textures.addProperty("top", tex(base));
        textures.addProperty("side", tex(base));

        JsonObject straightModel = new JsonObject();
        straightModel.addProperty("parent", "minecraft:block/stairs");
        straightModel.add("textures", textures);
        saveBlockModel(name, straightModel);

        JsonObject innerModel = new JsonObject();
        innerModel.addProperty("parent", "minecraft:block/inner_stairs");
        innerModel.add("textures", textures.deepCopy());
        saveBlockModel(name + "_inner", innerModel);

        JsonObject outerModel = new JsonObject();
        outerModel.addProperty("parent", "minecraft:block/outer_stairs");
        outerModel.add("textures", textures.deepCopy());
        saveBlockModel(name + "_outer", outerModel);

        JsonObject variants = new JsonObject();
        String[] facings = {"east", "west", "south", "north"};
        for (String facing : facings) {
            int baseY = stairsBaseY(facing);
            int outerLeftY = (baseY + 270) % 360;
            int outerRightY = baseY;
            int innerLeftY = (baseY + 270) % 360;
            int innerRightY = baseY;

            // bottom half
            variants.add("facing=" + facing + ",half=bottom,shape=straight",
                    variantModel(blockId(name), null, y0(baseY), baseY != 0));
            variants.add("facing=" + facing + ",half=bottom,shape=outer_right",
                    variantModel(blockId(name + "_outer"), null, y0(outerRightY), outerRightY != 0));
            variants.add("facing=" + facing + ",half=bottom,shape=outer_left",
                    variantModel(blockId(name + "_outer"), null, y0(outerLeftY), true));
            variants.add("facing=" + facing + ",half=bottom,shape=inner_right",
                    variantModel(blockId(name + "_inner"), null, y0(innerRightY), innerRightY != 0));
            variants.add("facing=" + facing + ",half=bottom,shape=inner_left",
                    variantModel(blockId(name + "_inner"), null, y0(innerLeftY), true));

            // top half: x=180 always, left/right swap relative to bottom (upside-down mirrors handedness)
            variants.add("facing=" + facing + ",half=top,shape=straight",
                    variantModel(blockId(name), 180, y0(baseY), true));
            variants.add("facing=" + facing + ",half=top,shape=outer_right",
                    variantModel(blockId(name + "_outer"), 180, y0(outerLeftY), true));
            variants.add("facing=" + facing + ",half=top,shape=outer_left",
                    variantModel(blockId(name + "_outer"), 180, y0(outerRightY), true));
            variants.add("facing=" + facing + ",half=top,shape=inner_right",
                    variantModel(blockId(name + "_inner"), 180, y0(innerLeftY), true));
            variants.add("facing=" + facing + ",half=top,shape=inner_left",
                    variantModel(blockId(name + "_inner"), 180, y0(innerRightY), true));
        }
        JsonObject blockstate = new JsonObject();
        blockstate.add("variants", variants);
        saveBlockstate(name, blockstate);

        JsonObject itemModel = new JsonObject();
        itemModel.addProperty("parent", blockId(name));
        saveItemModel(name, itemModel);
        saveItemClientDef(name);
    }

    private static Integer y0(int y) {
        return y == 0 ? null : y;
    }

    // ---- walls ----

    private void wall(String name, String base) {
        JsonObject textures = new JsonObject();
        textures.addProperty("wall", tex(base));

        JsonObject postModel = new JsonObject();
        postModel.addProperty("parent", "minecraft:block/template_wall_post");
        postModel.add("textures", textures);
        saveBlockModel(name, postModel);

        JsonObject sideModel = new JsonObject();
        sideModel.addProperty("parent", "minecraft:block/template_wall_side");
        sideModel.add("textures", textures.deepCopy());
        saveBlockModel(name + "_side", sideModel);

        JsonObject sideTallModel = new JsonObject();
        sideTallModel.addProperty("parent", "minecraft:block/template_wall_side_tall");
        sideTallModel.add("textures", textures.deepCopy());
        saveBlockModel(name + "_side_tall", sideTallModel);

        JsonArray multipart = new JsonArray();
        multipart.add(multipartCase(variantModel(blockId(name), null, null, false), "up", "true"));
        multipart.add(multipartCase(variantModel(blockId(name + "_side"), null, null, true), "north", "low"));
        multipart.add(multipartCase(variantModel(blockId(name + "_side_tall"), null, null, true), "north", "tall"));
        multipart.add(multipartCase(variantModel(blockId(name + "_side"), null, 90, true), "east", "low"));
        multipart.add(multipartCase(variantModel(blockId(name + "_side_tall"), null, 90, true), "east", "tall"));
        multipart.add(multipartCase(variantModel(blockId(name + "_side"), null, 180, true), "south", "low"));
        multipart.add(multipartCase(variantModel(blockId(name + "_side_tall"), null, 180, true), "south", "tall"));
        multipart.add(multipartCase(variantModel(blockId(name + "_side"), null, 270, true), "west", "low"));
        multipart.add(multipartCase(variantModel(blockId(name + "_side_tall"), null, 270, true), "west", "tall"));

        JsonObject blockstate = new JsonObject();
        blockstate.add("multipart", multipart);
        saveBlockstate(name, blockstate);

        JsonObject itemModel = new JsonObject();
        itemModel.addProperty("parent", "minecraft:block/wall_inventory");
        itemModel.add("textures", textures.deepCopy());
        saveItemModel(name, itemModel);
        saveItemClientDef(name);
    }

    private static JsonObject multipartCase(JsonObject apply, String property, String value) {
        JsonObject when = new JsonObject();
        when.addProperty(property, value);
        JsonObject entry = new JsonObject();
        entry.add("apply", apply);
        entry.add("when", when);
        return entry;
    }

    @Override
    public String getName() {
        return "MythicUpgrades Block Models";
    }
}
