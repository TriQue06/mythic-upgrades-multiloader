package net.trique.mythicupgrades.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.worldgen.CaveGemType;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MythicBiomeDataProvider implements DataProvider {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final PackOutput.PathProvider biomePath;

    public MythicBiomeDataProvider(PackOutput output) {
        this.biomePath = output.createPathProvider(PackOutput.Target.DATA_PACK, "worldgen/biome");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (CaveGemType gem : CaveGemType.values()) {
            futures.add(save(cache, gem.id + "_caves", biomeJson(gem)));
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private static JsonObject biomeJson(CaveGemType gem) {
        JsonObject obj = new JsonObject();
        obj.add("carvers", new JsonObject());
        obj.addProperty("creature_spawn_probability", 0.07);
        obj.addProperty("downfall", gem.downfall);
        obj.add("effects", effects(gem));
        obj.add("features", features(gem));
        obj.addProperty("has_precipitation", gem.precipitation);
        obj.add("spawners", emptySpawners());
        obj.add("spawn_costs", new JsonObject());
        obj.addProperty("temperature", gem.temperature);
        return obj;
    }

    private static JsonObject effects(CaveGemType gem) {
        JsonObject obj = new JsonObject();
        obj.addProperty("fog_color", 12638463);
        obj.add("mood_sound", moodSound());
        obj.addProperty("sky_color", 8103167);
        obj.addProperty("water_color", gem.waterColor);
        obj.addProperty("water_fog_color", 329011);
        return obj;
    }

    private static JsonObject moodSound() {
        JsonObject obj = new JsonObject();
        obj.addProperty("block_search_extent", 8);
        obj.addProperty("offset", 2.0);
        obj.addProperty("sound", "minecraft:ambient.cave");
        obj.addProperty("tick_delay", 6000);
        return obj;
    }

    private static JsonArray features(CaveGemType gem) {
        JsonArray steps = new JsonArray();
        // steps 0-3: empty
        for (int i = 0; i < 4; i++) steps.add(new JsonArray());
        // step 4: SURFACE_STRUCTURES — monster room
        JsonArray step4 = new JsonArray();
        step4.add("minecraft:monster_room");
        steps.add(step4);
        // step 5: STRONGHOLDS — empty
        steps.add(new JsonArray());
        // step 6: UNDERGROUND_ORES — stone blobs, ore, deepslate ore
        JsonArray step6 = new JsonArray();
        step6.add(mod(gem.id + "_stone_blobs"));
        step6.add(mod(gem.id + "_ore"));
        step6.add(mod(gem.id + "_deepslate_ore"));
        steps.add(step6);
        // step 7: UNDERGROUND_DECORATION — crystal blobs, buds
        JsonArray step7 = new JsonArray();
        step7.add(mod(gem.id + "_crystal_blobs"));
        step7.add(mod(gem.id + "_crystal_buds"));
        step7.add(mod(gem.id + "_crystal_buds_rare"));
        steps.add(step7);
        // step 8: FLUID_SPRINGS — springs
        JsonArray step8 = new JsonArray();
        step8.add("minecraft:spring_water");
        step8.add("minecraft:spring_lava");
        steps.add(step8);
        // steps 9-10: empty
        steps.add(new JsonArray());
        steps.add(new JsonArray());
        return steps;
    }

    private static JsonObject emptySpawners() {
        JsonObject obj = new JsonObject();
        for (String category : new String[]{"ambient","axolotls","creature","misc","monster",
                                            "underground_water_creature","water_ambient","water_creature"}) {
            obj.add(category, new JsonArray());
        }
        return obj;
    }

    private static String mod(String name) { return Constants.MOD_ID + ":" + name; }

    private CompletableFuture<?> save(CachedOutput cache, String name, JsonObject json) {
        Path path = biomePath.json(new ResourceLocation(Constants.MOD_ID, name));
        return DataProvider.saveStable(cache, GSON.toJsonTree(json), path);
    }

    @Override
    public String getName() { return "MythicUpgrades Biomes"; }
}
