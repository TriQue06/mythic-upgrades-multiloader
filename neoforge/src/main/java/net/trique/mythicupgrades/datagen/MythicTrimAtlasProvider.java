package net.trique.mythicupgrades.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.trique.mythicupgrades.Constants;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MythicTrimAtlasProvider implements DataProvider {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final List<String> VANILLA_TRIM_TEXTURES = List.of(
        "trims/models/armor/coast",         "trims/models/armor/coast_leggings",
        "trims/models/armor/dune",          "trims/models/armor/dune_leggings",
        "trims/models/armor/eye",           "trims/models/armor/eye_leggings",
        "trims/models/armor/host",          "trims/models/armor/host_leggings",
        "trims/models/armor/raiser",        "trims/models/armor/raiser_leggings",
        "trims/models/armor/rib",           "trims/models/armor/rib_leggings",
        "trims/models/armor/sentry",        "trims/models/armor/sentry_leggings",
        "trims/models/armor/shaper",        "trims/models/armor/shaper_leggings",
        "trims/models/armor/silence",       "trims/models/armor/silence_leggings",
        "trims/models/armor/snout",         "trims/models/armor/snout_leggings",
        "trims/models/armor/spire",         "trims/models/armor/spire_leggings",
        "trims/models/armor/tide",          "trims/models/armor/tide_leggings",
        "trims/models/armor/vex",           "trims/models/armor/vex_leggings",
        "trims/models/armor/ward",          "trims/models/armor/ward_leggings",
        "trims/models/armor/wayfinder",     "trims/models/armor/wayfinder_leggings",
        "trims/models/armor/wild",          "trims/models/armor/wild_leggings",
        "trims/models/armor/flow",          "trims/models/armor/flow_leggings",
        "trims/models/armor/bolt",          "trims/models/armor/bolt_leggings"
    );

    private record TrimColor(String name, String armorMaterial) {}

    private static final List<TrimColor> COLORS = List.of(
        new TrimColor("aquamarine", "mythicupgrades:aquamarine"),
        new TrimColor("citrine",    null),
        new TrimColor("topaz",      "mythicupgrades:topaz"),
        new TrimColor("peridot",    "mythicupgrades:peridot"),
        new TrimColor("ruby",       "mythicupgrades:ruby"),
        new TrimColor("sapphire",   "mythicupgrades:sapphire"),
        new TrimColor("jade",       "mythicupgrades:jade"),
        new TrimColor("ametrine",   "mythicupgrades:ametrine"),
        new TrimColor("necoium",    null)
    );

    private final PackOutput output;

    public MythicTrimAtlasProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        JsonObject permutations = new JsonObject();
        for (TrimColor color : COLORS) {
            permutations.addProperty(color.name(), Constants.MOD_ID + ":trims/color_palettes/" + color.name());
            if (color.armorMaterial() != null) {
                permutations.addProperty(color.name() + "_darker", Constants.MOD_ID + ":trims/color_palettes/" + color.name() + "_darker");
            }
        }

        JsonArray textures = new JsonArray();
        for (String tex : VANILLA_TRIM_TEXTURES) textures.add("minecraft:" + tex);

        JsonObject source = new JsonObject();
        source.addProperty("type", "minecraft:paletted_permutations");
        source.add("textures", textures);
        source.addProperty("palette_key", "minecraft:trims/color_palettes/trim_palette");
        source.add("permutations", permutations);

        JsonArray sources = new JsonArray();
        sources.add(source);

        JsonObject root = new JsonObject();
        root.add("sources", sources);

        Path assetPath = output.getOutputFolder(PackOutput.Target.RESOURCE_PACK);
        Path filePath = assetPath.resolve("assets/minecraft/atlases/armor_trims.json");
        return DataProvider.saveStable(cache, GSON.toJsonTree(root), filePath);
    }

    @Override
    public String getName() {
        return "MythicUpgrades Trim Atlas";
    }
}
