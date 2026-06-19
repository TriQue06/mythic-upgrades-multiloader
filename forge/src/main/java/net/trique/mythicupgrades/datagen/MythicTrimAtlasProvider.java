package net.trique.mythicupgrades.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class MythicTrimAtlasProvider implements DataProvider {

    private static final String[] VANILLA_TRIM_TEXTURES = {
        "trims/models/armor/coast",          "trims/models/armor/coast_leggings",
        "trims/models/armor/dune",           "trims/models/armor/dune_leggings",
        "trims/models/armor/eye",            "trims/models/armor/eye_leggings",
        "trims/models/armor/host",           "trims/models/armor/host_leggings",
        "trims/models/armor/raiser",         "trims/models/armor/raiser_leggings",
        "trims/models/armor/rib",            "trims/models/armor/rib_leggings",
        "trims/models/armor/sentry",         "trims/models/armor/sentry_leggings",
        "trims/models/armor/shaper",         "trims/models/armor/shaper_leggings",
        "trims/models/armor/silence",        "trims/models/armor/silence_leggings",
        "trims/models/armor/snout",          "trims/models/armor/snout_leggings",
        "trims/models/armor/spire",          "trims/models/armor/spire_leggings",
        "trims/models/armor/tide",           "trims/models/armor/tide_leggings",
        "trims/models/armor/vex",            "trims/models/armor/vex_leggings",
        "trims/models/armor/ward",           "trims/models/armor/ward_leggings",
        "trims/models/armor/wayfinder",      "trims/models/armor/wayfinder_leggings",
        "trims/models/armor/wild",           "trims/models/armor/wild_leggings",
    };

    private final PackOutput output;

    public MythicTrimAtlasProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        JsonObject source = new JsonObject();
        source.addProperty("type", "minecraft:paletted_permutations");

        JsonArray textures = new JsonArray();
        for (String tex : VANILLA_TRIM_TEXTURES) textures.add(tex);
        source.add("textures", textures);

        source.addProperty("palette_key", "trims/color_palettes/trim_palette");

        JsonObject permutations = new JsonObject();
        for (MythicTrimMaterialProvider.Entry e : MythicTrimMaterialProvider.ENTRIES) {
            permutations.addProperty(e.name(), "trims/color_palettes/" + e.name());
            if (e.armorMaterial() != null)
                permutations.addProperty(e.name() + "_darker", "trims/color_palettes/" + e.name() + "_darker");
        }
        source.add("permutations", permutations);

        JsonArray sources = new JsonArray();
        sources.add(source);

        JsonObject root = new JsonObject();
        root.add("sources", sources);

        Path path = output.getOutputFolder(PackOutput.Target.RESOURCE_PACK)
                .resolve("minecraft/atlases/armor_trims.json");

        return DataProvider.saveStable(cache, root, path);
    }

    @Override
    public String getName() {
        return "MythicUpgrades Trim Atlas";
    }
}
