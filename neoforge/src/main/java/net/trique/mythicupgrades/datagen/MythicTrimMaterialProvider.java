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

    private record TrimEntry(String name, String color, String armorMaterial) {}

    // 26.2: trim materials are purely asset-driven; items link to materials via the
    // provides_trim_material component set in MythicItems.
    private static final List<TrimEntry> ENTRIES = List.of(
        new TrimEntry("aquamarine", "#057B9E", "mythicupgrades:aquamarine"),
        new TrimEntry("citrine", "#DCB40A", null),
        new TrimEntry("topaz", "#D1480D", "mythicupgrades:topaz"),
        new TrimEntry("peridot", "#61AD0F", "mythicupgrades:peridot"),
        new TrimEntry("ruby", "#A90C37", "mythicupgrades:ruby"),
        new TrimEntry("sapphire", "#0C46B2", "mythicupgrades:sapphire"),
        new TrimEntry("jade", "#1D8B30", "mythicupgrades:jade"),
        new TrimEntry("ametrine", "#8422AE", "mythicupgrades:ametrine"),
        new TrimEntry("necoium", "#9F1C73", null)
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

            if (entry.armorMaterial() != null) {
                JsonObject overrides = new JsonObject();
                overrides.addProperty(entry.armorMaterial(), entry.name() + "_darker");
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
