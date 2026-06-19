package net.trique.mythicupgrades.datagen;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.trique.mythicupgrades.Constants;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MythicTrimMaterialProvider implements DataProvider {

    private final PackOutput output;

    // armorMaterial: the ArmorMaterial name that triggers the _darker palette override (null = no matching armor or no darker texture)
    static final Entry[] ENTRIES = {
        new Entry("aquamarine", "mythicupgrades:aquamarine_crystal_shard", "#057B9E", 1.1f, "mythicupgrades:aquamarine"),
        new Entry("citrine",    "mythicupgrades:citrine_crystal_shard",    "#DCB40A", 1.3f, null),
        new Entry("topaz",      "mythicupgrades:topaz_crystal_shard",      "#D1480D", 1.4f, "mythicupgrades:topaz"),
        new Entry("peridot",    "mythicupgrades:peridot_crystal_shard",    "#61AD0F", 1.5f, "mythicupgrades:peridot"),
        new Entry("ruby",       "mythicupgrades:ruby_crystal_shard",       "#A90C37", 1.6f, "mythicupgrades:ruby"),
        new Entry("sapphire",   "mythicupgrades:sapphire_crystal_shard",   "#0C46B2", 1.7f, "mythicupgrades:sapphire"),
        new Entry("jade",       "mythicupgrades:jade_crystal_shard",       "#1D8B30", 1.8f, "mythicupgrades:jade"),
        new Entry("ametrine",   "mythicupgrades:ametrine_crystal_shard",   "#8422AE", 1.9f, "mythicupgrades:ametrine"),
        new Entry("necoium",    "mythicupgrades:necoium_ingot",            "#9F1C73", 2.0f, null),
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
            json.addProperty("asset_name", e.name);

            JsonObject desc = new JsonObject();
            desc.addProperty("color", e.color);
            desc.addProperty("translate", "trim_material.mythicupgrades." + e.name);
            json.add("description", desc);

            json.addProperty("ingredient", e.ingredient);
            json.addProperty("item_model_index", e.itemModelIndex);

            JsonObject overrides = new JsonObject();
            if (e.armorMaterial != null) {
                JsonObject darkerEntry = new JsonObject();
                darkerEntry.addProperty("asset_name", e.name + "_darker");
                overrides.add(e.armorMaterial, darkerEntry);
            }
            json.add("override_armor_materials", overrides);

            futures.add(DataProvider.saveStable(cache, json, base.resolve(e.name + ".json")));
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "MythicUpgrades Trim Materials";
    }

    record Entry(String name, String ingredient, String color, float itemModelIndex, String armorMaterial) {}
}
