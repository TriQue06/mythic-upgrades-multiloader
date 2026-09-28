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
 * Emits the mod-side items-atlas fragment that palette-swaps the vanilla trim item
 * overlays with our gem palettes. Atlas JSONs merge across packs, so this only adds
 * sources. 26.3 dropped the armor_trims atlas: worn trims are recoloured at render
 * time from palette ids, so only the item overlays still need permutations.
 */
public class MythicTrimAtlasProvider implements DataProvider {

    private static final String[] MATERIALS = {
        "aquamarine", "citrine", "topaz", "peridot", "ruby", "sapphire", "jade", "ametrine", "necoium"
    };
    // citrine and necoium have no darker palette variant
    private static final String[] DARKER = {
        "aquamarine", "topaz", "peridot", "ruby", "sapphire", "jade", "ametrine"
    };


    private final PackOutput output;

    public MythicTrimAtlasProvider(PackOutput output) {
        this.output = output;
    }

    private static JsonObject permutations() {
        JsonObject permutations = new JsonObject();
        for (String material : MATERIALS) {
            permutations.addProperty(material, Constants.MOD_ID + ":trim/" + material);
        }
        for (String material : DARKER) {
            permutations.addProperty(material + "_darker", Constants.MOD_ID + ":trim/" + material + "_darker");
        }
        return permutations;
    }

    private static JsonObject atlas(JsonArray textures) {
        JsonObject source = new JsonObject();
        source.addProperty("type", "minecraft:paletted_permutations");
        source.addProperty("palette_key", "minecraft:trim_base");
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
