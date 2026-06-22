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

public class MythicWorldgenProvider implements DataProvider {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final PackOutput.PathProvider cfPath;
    private final PackOutput.PathProvider pfPath;

    public MythicWorldgenProvider(PackOutput output) {
        this.cfPath    = output.createPathProvider(PackOutput.Target.DATA_PACK, "worldgen/configured_feature");
        this.pfPath    = output.createPathProvider(PackOutput.Target.DATA_PACK, "worldgen/placed_feature");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (CaveGemType gem : CaveGemType.values()) {
            String g = gem.id;

            // Configured features
            futures.add(save(cache, cfPath, g + "_stone_blobs",      stoneBlobsCF(g)));
            futures.add(save(cache, cfPath, g + "_crystal_blobs",    crystalBlobsCF(g)));
            futures.add(save(cache, cfPath, g + "_crystal_buds",     crystalBudsCF(g, 96, 5, 4, true)));
            futures.add(save(cache, cfPath, g + "_crystal_buds_rare", crystalBudsCF(g, 16, 4, 3, false)));
            futures.add(save(cache, cfPath, g + "_ore",              oreCF(g)));
            futures.add(save(cache, cfPath, g + "_deepslate_ore",    deepslateOreCF(g)));

            // Placed features
            futures.add(save(cache, pfPath, g + "_stone_blobs",      placedUniform(g + "_stone_blobs",     30, -64, 30)));
            futures.add(save(cache, pfPath, g + "_crystal_blobs",    placedUniform(g + "_crystal_blobs",    8, -64, 30)));
            futures.add(save(cache, pfPath, g + "_crystal_buds",     placedUniform(g + "_crystal_buds",    12, -64, 30)));
            futures.add(save(cache, pfPath, g + "_crystal_buds_rare", placedRare(g + "_crystal_buds_rare", 20, -64, 20)));
            futures.add(save(cache, pfPath, g + "_ore",              placedUniform(g + "_ore",              8,   0, 30)));
            futures.add(save(cache, pfPath, g + "_deepslate_ore",    placedTrapezoid(g + "_deepslate_ore", 10, -64,  8)));
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    // ── Configured features ───────────────────────────────────────────────────

    private static JsonObject stoneBlobsCF(String g) {
        return oreFeature(64,
            oreTarget(tagPredicate("minecraft:stone_ore_replaceables"),       blockState(mod(g + "_stone"))),
            oreTarget(tagPredicate("minecraft:deepslate_ore_replaceables"),   blockState(mod(g + "_stone"))));
    }

    private static JsonObject crystalBlobsCF(String g) {
        return oreFeature(20,
            oreTarget(tagPredicate("minecraft:stone_ore_replaceables"),       blockState(mod(g + "_crystal_block"))),
            oreTarget(blockPredicate(mod(g + "_stone")),                      blockState(mod(g + "_crystal_block"))),
            oreTarget(tagPredicate("minecraft:deepslate_ore_replaceables"),   blockState(mod(g + "_crystal_block"))));
    }

    private static JsonObject crystalBudsCF(String g, int tries, int spreadXZ, int spreadY, boolean withCluster) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "mythicupgrades:crystal_bud");

        JsonObject cfg = new JsonObject();

        JsonArray entries = new JsonArray();
        entries.add(weightedEntry(budState(mod("small_" + g + "_crystal_bud")),  withCluster ? 4 : 3));
        entries.add(weightedEntry(budState(mod("medium_" + g + "_crystal_bud")), withCluster ? 3 : 2));
        entries.add(weightedEntry(budState(mod("large_" + g + "_crystal_bud")),  withCluster ? 2 : 1));
        if (withCluster) entries.add(weightedEntry(budState(mod(g + "_crystal_cluster")), 1));

        JsonObject stateProvider = new JsonObject();
        stateProvider.addProperty("type", "minecraft:weighted_state_provider");
        stateProvider.add("entries", entries);

        cfg.add("state", stateProvider);
        cfg.addProperty("tries", tries);
        cfg.addProperty("spread_xz", spreadXZ);
        cfg.addProperty("spread_y", spreadY);

        obj.add("config", cfg);
        return obj;
    }

    private static JsonObject oreCF(String g) {
        return oreFeature(7,
            oreTarget(tagPredicate("minecraft:stone_ore_replaceables"), blockState(mod(g + "_ore"))));
    }

    private static JsonObject deepslateOreCF(String g) {
        return oreFeature(7,
            oreTarget(tagPredicate("minecraft:deepslate_ore_replaceables"), blockState(mod("deepslate_" + g + "_ore"))),
            oreTarget(blockPredicate(mod(g + "_stone")),                    blockState(mod("deepslate_" + g + "_ore"))));
    }

    // ── Placed features ───────────────────────────────────────────────────────

    private static JsonObject placedUniform(String featureId, int count, int minY, int maxY) {
        return placed(featureId, countMod(count), inSquare(), uniformHeight(minY, maxY), biomeFilter());
    }

    private static JsonObject placedRare(String featureId, int chance, int minY, int maxY) {
        return placed(featureId, rarityFilter(chance), inSquare(), uniformHeight(minY, maxY), biomeFilter());
    }

    private static JsonObject placedTrapezoid(String featureId, int count, int minY, int maxY) {
        return placed(featureId, countMod(count), inSquare(), trapezoidHeight(minY, maxY), biomeFilter());
    }

    private static JsonObject placed(String featureId, JsonObject... modifiers) {
        JsonObject obj = new JsonObject();
        obj.addProperty("feature", mod(featureId));
        JsonArray placement = new JsonArray();
        for (JsonObject m : modifiers) placement.add(m);
        obj.add("placement", placement);
        return obj;
    }

    // ── JSON helpers ──────────────────────────────────────────────────────────

    private static JsonObject oreFeature(int size, JsonObject... targets) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "minecraft:ore");
        JsonObject cfg = new JsonObject();
        cfg.addProperty("size", size);
        cfg.addProperty("discard_chance_on_air_exposure", 0.0);
        JsonArray arr = new JsonArray();
        for (JsonObject t : targets) arr.add(t);
        cfg.add("targets", arr);
        obj.add("config", cfg);
        return obj;
    }

    private static JsonObject oreTarget(JsonObject predicate, JsonObject state) {
        JsonObject obj = new JsonObject();
        obj.add("predicate", predicate);
        obj.add("state", state);
        return obj;
    }

    private static JsonObject tagPredicate(String tag) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "minecraft:tag_match");
        obj.addProperty("tag", tag);
        return obj;
    }

    private static JsonObject blockPredicate(String block) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "minecraft:block_match");
        obj.addProperty("block", block);
        return obj;
    }

    private static JsonObject blockState(String name) {
        JsonObject obj = new JsonObject();
        obj.addProperty("Name", name);
        return obj;
    }

    private static JsonObject budState(String name) {
        JsonObject obj = new JsonObject();
        obj.addProperty("Name", name);
        JsonObject props = new JsonObject();
        props.addProperty("facing", "up");
        props.addProperty("waterlogged", "false");
        obj.add("Properties", props);
        return obj;
    }

    private static JsonObject weightedEntry(JsonObject state, int weight) {
        JsonObject obj = new JsonObject();
        obj.addProperty("weight", weight);
        obj.add("data", state);
        return obj;
    }

    private static JsonObject countMod(int count) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "minecraft:count");
        obj.addProperty("count", count);
        return obj;
    }

    private static JsonObject rarityFilter(int chance) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "minecraft:rarity_filter");
        obj.addProperty("chance", chance);
        return obj;
    }

    private static JsonObject inSquare() {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "minecraft:in_square");
        return obj;
    }

    private static JsonObject uniformHeight(int minY, int maxY) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "minecraft:height_range");
        JsonObject height = new JsonObject();
        height.addProperty("type", "minecraft:uniform");
        JsonObject min = new JsonObject(); min.addProperty("absolute", minY);
        JsonObject max = new JsonObject(); max.addProperty("absolute", maxY);
        height.add("min_inclusive", min);
        height.add("max_inclusive", max);
        obj.add("height", height);
        return obj;
    }

    private static JsonObject trapezoidHeight(int minY, int maxY) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "minecraft:height_range");
        JsonObject height = new JsonObject();
        height.addProperty("type", "minecraft:trapezoid");
        JsonObject min = new JsonObject(); min.addProperty("absolute", minY);
        JsonObject max = new JsonObject(); max.addProperty("absolute", maxY);
        height.add("min_inclusive", min);
        height.add("max_inclusive", max);
        obj.add("height", height);
        return obj;
    }

    private static JsonObject biomeFilter() {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "minecraft:biome");
        return obj;
    }

    private static String mod(String name) {
        return Constants.MOD_ID + ":" + name;
    }

    private CompletableFuture<?> save(CachedOutput cache, PackOutput.PathProvider provider,
                                      String name, JsonObject json) {
        Path path = provider.json(new ResourceLocation(Constants.MOD_ID, name));
        return DataProvider.saveStable(cache, GSON.toJsonTree(json), path);
    }

    @Override
    public String getName() { return "Mythic Upgrades Worldgen"; }
}
