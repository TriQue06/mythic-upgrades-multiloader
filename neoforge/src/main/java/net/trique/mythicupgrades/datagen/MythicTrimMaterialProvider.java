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

    private record TrimEntry(String name, String color) {}

    // 26.3: a trim material is just a palette id + description. Palettes live in
    // assets/mythicupgrades/textures/palettes/trim/; the darker variant on same-gem
    // armor is an equipment-asset trim_override (see MythicItemModelProvider).
    private static final List<TrimEntry> ENTRIES = List.of(
        new TrimEntry("aquamarine", "#057B9E"),
        new TrimEntry("citrine", "#DCB40A"),
        new TrimEntry("topaz", "#D1480D"),
        new TrimEntry("peridot", "#61AD0F"),
        new TrimEntry("ruby", "#A90C37"),
        new TrimEntry("sapphire", "#0C46B2"),
        new TrimEntry("jade", "#1D8B30"),
        new TrimEntry("ametrine", "#8422AE"),
        new TrimEntry("necoium", "#9F1C73")
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

            JsonObject description = new JsonObject();
            description.addProperty("color", entry.color());
            description.addProperty("translate", "trim_material." + Constants.MOD_ID + "." + entry.name());
            json.add("description", description);
            json.addProperty("palette_id", Constants.MOD_ID + ":trim/" + entry.name());

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
