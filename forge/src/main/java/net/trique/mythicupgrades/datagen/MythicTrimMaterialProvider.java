package net.trique.mythicupgrades.datagen;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.item.MythicItems;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MythicTrimMaterialProvider implements DataProvider {

    private final PackOutput output;

    private static final Entry[] ENTRIES = {
        new Entry("aquamarine", "mythicupgrades:aquamarine_crystal_shard", "#057B9E", 1.1f),
        new Entry("citrine", "mythicupgrades:citrine_crystal_shard", "#DCB40A", 1.3f),
        new Entry("topaz", "mythicupgrades:topaz_crystal_shard", "#D1480D", 1.4f),
        new Entry("peridot", "mythicupgrades:peridot_crystal_shard", "#61AD0F", 1.5f),
        new Entry("ruby", "mythicupgrades:ruby_crystal_shard", "#A90C37", 1.6f),
        new Entry("sapphire", "mythicupgrades:sapphire_crystal_shard", "#0C46B2", 1.7f),
        new Entry("jade", "mythicupgrades:jade_crystal_shard", "#1D8B30", 1.8f),
        new Entry("ametrine", "mythicupgrades:ametrine_crystal_shard", "#8422AE", 1.9f),
        new Entry("necoium", "mythicupgrades:necoium_ingot", "#9F1C73", 2.0f),
    };

    public MythicTrimMaterialProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        Path base = output.getOutputFolder(PackOutput.Target.DATA_PACK)
                .resolve(Constants.MOD_ID)
                .resolve("trim_material");

        for (Entry e : ENTRIES) {
            JsonObject json = new JsonObject();
            json.addProperty("asset_name", "mythicupgrades_" + e.name);

            JsonObject desc = new JsonObject();
            desc.addProperty("color", e.color);
            desc.addProperty("translate", "trim_material.mythicupgrades." + e.name);
            json.add("description", desc);

            json.addProperty("ingredient", e.ingredient);
            json.addProperty("item_model_index", e.itemModelIndex);
            json.add("override_armor_materials", new JsonObject());

            futures.add(DataProvider.saveStable(cache, json, base.resolve(e.name + ".json")));
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "MythicUpgrades Trim Materials";
    }

    private record Entry(String name, String ingredient, String color, float itemModelIndex) {}
}
