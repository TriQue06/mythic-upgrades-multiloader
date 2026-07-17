package net.trique.mythicupgrades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Persists {@link MythicStats} to config/mythicupgrades.json. Field initializers
 * in MythicStats are the defaults; they are captured once before the file is read
 * so the config screen can offer per-entry reset.
 */
public final class MythicConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Map<String, Number> DEFAULTS = new LinkedHashMap<>();
    private static Path configFile;

    private MythicConfig() {}

    /** All configurable MythicStats fields, in declaration order. */
    public static List<Field> statFields() {
        List<Field> fields = new ArrayList<>();
        for (Field field : MythicStats.class.getDeclaredFields()) {
            int mods = field.getModifiers();
            if (!Modifier.isPublic(mods) || !Modifier.isStatic(mods) || Modifier.isFinal(mods)) continue;
            Class<?> type = field.getType();
            if (type == int.class || type == float.class || type == double.class) {
                fields.add(field);
            }
        }
        return fields;
    }

    public static Number defaultValue(Field field) {
        return DEFAULTS.get(field.getName());
    }

    public static Number get(Field field) {
        try {
            return (Number) field.get(null);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot read " + field.getName(), e);
        }
    }

    public static void set(Field field, Number value) {
        try {
            Class<?> type = field.getType();
            if (type == int.class) {
                field.setInt(null, value.intValue());
            } else if (type == float.class) {
                field.setFloat(null, value.floatValue());
            } else if (type == double.class) {
                field.setDouble(null, value.doubleValue());
            }
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot write " + field.getName(), e);
        }
    }

    public static void load(Path configDir) {
        configFile = configDir.resolve(Constants.MOD_ID + ".json");

        if (DEFAULTS.isEmpty()) {
            for (Field field : statFields()) {
                DEFAULTS.put(field.getName(), get(field));
            }
        }

        if (Files.exists(configFile)) {
            try {
                JsonObject json = JsonParser.parseString(
                        Files.readString(configFile, StandardCharsets.UTF_8)).getAsJsonObject();
                for (Field field : statFields()) {
                    if (json.has(field.getName())) {
                        set(field, json.get(field.getName()).getAsNumber());
                    }
                }
                Constants.LOG.info("Loaded config from {}", configFile.getFileName());
            } catch (Exception e) {
                Constants.LOG.error("Failed to read {}, keeping default values", configFile, e);
            }
        }

        // Write back so newly added options show up in the file immediately.
        save();
    }

    public static void save() {
        if (configFile == null) return;
        JsonObject json = new JsonObject();
        for (Field field : statFields()) {
            Class<?> type = field.getType();
            if (type == int.class) {
                json.addProperty(field.getName(), get(field).intValue());
            } else if (type == float.class) {
                json.addProperty(field.getName(), get(field).floatValue());
            } else {
                json.addProperty(field.getName(), get(field).doubleValue());
            }
        }
        try {
            Files.createDirectories(configFile.getParent());
            Files.writeString(configFile, GSON.toJson(json), StandardCharsets.UTF_8);
        } catch (Exception e) {
            Constants.LOG.error("Failed to write {}", configFile, e);
        }
    }
}
