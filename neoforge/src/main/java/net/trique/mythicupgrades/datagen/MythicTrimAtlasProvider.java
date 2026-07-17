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
 * Emits the mod-side atlas fragments that palette-swap the vanilla trim art with
 * our gem palettes. Atlas JSONs merge across packs, so these only add sources.
 * 26.2 layout: armor overlays live in the armor_trims atlas, item overlays in items.
 */
public class MythicTrimAtlasProvider implements DataProvider {

    private static final String[] MATERIALS = {
        "aquamarine", "citrine", "topaz", "peridot", "ruby", "sapphire", "jade", "ametrine", "necoium"
    };
    // citrine and necoium have no darker palette variant
    private static final String[] DARKER = {
        "aquamarine", "topaz", "peridot", "ruby", "sapphire", "jade", "ametrine"
    };

    private static final String[] PATTERNS = {
        "sentry", "dune", "coast", "wild", "ward", "eye", "vex", "tide", "snout",
        "rib", "spire", "wayfinder", "shaper", "silence", "raiser", "host", "flow", "bolt"
    };

    private final PackOutput output;

    public MythicTrimAtlasProvider(PackOutput output) {
        this.output = output;
    }

    private static JsonObject permutations() {
        JsonObject permutations = new JsonObject();
        for (String material : MATERIALS) {
            permutations.addProperty(material, Constants.MOD_ID + ":trims/color_palettes/" + material);
        }
        for (String material : DARKER) {
            permutations.addProperty(material + "_darker", Constants.MOD_ID + ":trims/color_palettes/" + material + "_darker");
        }
        return permutations;
    }

    private static JsonObject atlas(JsonArray textures) {
        JsonObject source = new JsonObject();
        source.addProperty("type", "minecraft:paletted_permutations");
        source.addProperty("palette_key", "minecraft:trims/color_palettes/trim_palette");
        source.add("permutations", permutations());
        source.add("textures", textures);

        JsonObject root = new JsonObject();
        JsonArray sources = new JsonArray();
        sources.add(source);
        root.add("sources", sources);
        return root;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        Path assets = output.getOutputFolder(PackOutput.Target.RESOURCE_PACK);

        JsonArray armorTextures = new JsonArray();
        for (String pattern : PATTERNS) {
            armorTextures.add("minecraft:trims/entity/humanoid/" + pattern);
            armorTextures.add("minecraft:trims/entity/humanoid_leggings/" + pattern);
        }
        futures.add(DataProvider.saveStable(cache, atlas(armorTextures),
                assets.resolve("minecraft/atlases/armor_trims.json")));

        JsonArray itemTextures = new JsonArray();
        for (String type : new String[]{"helmet", "chestplate", "leggings", "boots"}) {
            itemTextures.add("minecraft:trims/items/" + type + "_trim");
        }
        futures.add(DataProvider.saveStable(cache, atlas(itemTextures),
                assets.resolve("minecraft/atlases/items.json")));

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "MythicUpgrades Trim Atlases";
    }
}
