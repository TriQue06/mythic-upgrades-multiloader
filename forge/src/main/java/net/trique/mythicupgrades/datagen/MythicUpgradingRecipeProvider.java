package net.trique.mythicupgrades.datagen;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.trique.mythicupgrades.Constants;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MythicUpgradingRecipeProvider implements DataProvider {

    private final PackOutput output;

    // 9 gems in order
    private static final String[] GEMS = {
        "aquamarine", "kyanite", "citrine", "topaz", "peridot",
        "ruby", "sapphire", "jade", "ametrine"
    };

    // 9 equipment types
    private static final String[] EQUIPMENT = {
        "sword", "pickaxe", "axe", "shovel", "hoe",
        "helmet", "chestplate", "leggings", "boots"
    };

    public MythicUpgradingRecipeProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        Path recipePath = output.getOutputFolder(PackOutput.Target.DATA_PACK)
                .resolve(Constants.MOD_ID)
                .resolve("recipes");

        for (String gem : GEMS) {
            for (String equip : EQUIPMENT) {
                String netherite = "minecraft:netherite_" + equip;
                String addition  = "mythicupgrades:" + gem;
                String crystal   = "mythicupgrades:" + gem + "_crystal_shard";
                String result    = "mythicupgrades:" + gem + "_" + equip;
                String id        = "upgrading/" + gem + "_" + equip;

                JsonObject json = buildRecipeJson(netherite, addition, crystal, result);
                Path filePath = recipePath.resolve(id + ".json");
                futures.add(DataProvider.saveStable(cache, json, filePath));
            }
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private JsonObject buildRecipeJson(String base, String addition, String crystal, String result) {
        JsonObject json = new JsonObject();
        json.addProperty("type", "mythicupgrades:upgrading");

        JsonObject baseObj = new JsonObject();
        baseObj.addProperty("item", base);
        json.add("base", baseObj);

        JsonObject addObj = new JsonObject();
        addObj.addProperty("item", addition);
        json.add("addition", addObj);

        JsonObject crystalObj = new JsonObject();
        crystalObj.addProperty("item", crystal);
        json.add("crystal", crystalObj);

        JsonObject resultObj = new JsonObject();
        resultObj.addProperty("item", result);
        json.add("result", resultObj);

        return json;
    }

    @Override
    public String getName() {
        return "MythicUpgrades Upgrading Recipes";
    }
}
