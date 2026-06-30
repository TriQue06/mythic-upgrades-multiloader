package net.trique.mythicupgrades.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private record TrimEntry(String name, String color, float modelIndex, String armorMaterial) {}

    private static final List<TrimEntry> ENTRIES = List.of(
        new TrimEntry("aquamarine", "#057B9E", 1.1f, "mythicupgrades:aquamarine"),
        new TrimEntry("citrine",    "#DCB40A", 1.3f, null),
        new TrimEntry("topaz",      "#D1480D", 1.4f, "mythicupgrades:topaz"),
        new TrimEntry("peridot",    "#61AD0F", 1.5f, "mythicupgrades:peridot"),
        new TrimEntry("ruby",       "#A90C37", 1.6f, "mythicupgrades:ruby"),
        new TrimEntry("sapphire",   "#0C46B2", 1.7f, "mythicupgrades:sapphire"),
        new TrimEntry("jade",       "#1D8B30", 1.8f, "mythicupgrades:jade"),
        new TrimEntry("ametrine",   "#8422AE", 1.9f, "mythicupgrades:ametrine"),
        new TrimEntry("necoium",    "#9F1C73", 2.0f, null)
    );

    private final PackOutput output;

    public MythicTrimMaterialProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        Path dataPath = output.getOutputFolder(PackOutput.Target.DATA_PACK);

        for (TrimEntry entry : ENTRIES) {
            JsonObject json = new JsonObject();
            json.addProperty("asset_name", entry.name());

            JsonObject description = new JsonObject();
            description.addProperty("color", entry.color());
            description.addProperty("translate", "trim_material." + Constants.MOD_ID + "." + entry.name());
            json.add("description", description);

            json.addProperty("ingredient", Constants.MOD_ID + ":" + entry.name() + "_crystal_shard");
            if (entry.name().equals("necoium")) {
                json.addProperty("ingredient", Constants.MOD_ID + ":necoium_ingot");
            }

            json.addProperty("item_model_index", entry.modelIndex());

            if (entry.armorMaterial() != null) {
                JsonObject overrides = new JsonObject();
                JsonObject assetOverride = new JsonObject();
                assetOverride.addProperty("asset_name", entry.name() + "_darker");
                overrides.add(entry.armorMaterial(), assetOverride);
                json.add("override_armor_assets", overrides);
            }

            Path filePath = dataPath.resolve(Constants.MOD_ID + "/trim_material/" + entry.name() + ".json");
            futures.add(DataProvider.saveStable(cache, GSON.toJsonTree(json), filePath));
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "MythicUpgrades Trim Materials";
    }
}
