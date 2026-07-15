package net.trique.mythicupgrades.client;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.trique.mythicupgrades.MythicConfig;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Cloth Config screen for MythicStats. Client-only — must not be classloaded on
 * dedicated servers. Categories are grouped per gem and tinted with the gem's
 * trim color; entry labels are derived from the field names.
 */
public final class MythicConfigScreen {

    private record GemCategory(String name, int color, String... prefixes) {}

    private static final GemCategory[] CATEGORIES = {
        new GemCategory("Aquamarine", 0x2FA9D4, "AQUAMARINE_", "ICE_SHIELD_", "ICE_BOMB_"),
        new GemCategory("Citrine",    0xDCB40A, "CITRINE_", "STATIC_FIELD_"),
        new GemCategory("Topaz",      0xE86A28, "TOPAZ_"),
        new GemCategory("Peridot",    0x7CC91F, "PERIDOT_", "LETHAL_INCUBATION_", "MIASMA_"),
        new GemCategory("Ruby",       0xE0356A, "RUBY_", "BLOOD_THIRST_"),
        new GemCategory("Sapphire",   0x3B6EE0, "SAPPHIRE_", "DEFLECT_"),
        new GemCategory("Jade",       0x2FAE4C, "JADE_"),
        new GemCategory("Ametrine",   0xA94BD6, "AMETRINE_", "ARCANE_AURA_"),
    };

    private MythicConfigScreen() {}

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(Component.literal("Mythic Upgrades").withStyle(ChatFormatting.BOLD))
            .setDefaultBackgroundTexture(new ResourceLocation("minecraft", "textures/block/amethyst_block.png"))
            .setSavingRunnable(MythicConfig::save);

        ConfigEntryBuilder entries = builder.entryBuilder();

        Map<String, ConfigCategory> categories = new LinkedHashMap<>();
        for (GemCategory gem : CATEGORIES) {
            categories.put(gem.name(), builder.getOrCreateCategory(
                Component.literal(gem.name()).withStyle(Style.EMPTY
                    .withColor(TextColor.fromRgb(gem.color()))
                    .withBold(true))));
        }

        for (Field field : MythicConfig.statFields()) {
            GemCategory gem = categoryFor(field.getName());
            ConfigCategory category = gem != null
                ? categories.get(gem.name())
                : categories.computeIfAbsent("General", name ->
                    builder.getOrCreateCategory(Component.literal(name).withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD)));
            category.addEntry(entryFor(entries, field));
        }

        return builder.build();
    }

    private static GemCategory categoryFor(String fieldName) {
        for (GemCategory gem : CATEGORIES) {
            for (String prefix : gem.prefixes()) {
                if (fieldName.startsWith(prefix)) return gem;
            }
        }
        return null;
    }

    private static AbstractConfigListEntry<?> entryFor(ConfigEntryBuilder entries, Field field) {
        Component label = Component.literal(prettify(field.getName()));
        Number defaultValue = MythicConfig.defaultValue(field);
        Component tooltip = Component.literal("Default: " + defaultValue)
            .withStyle(ChatFormatting.DARK_GRAY);
        Class<?> type = field.getType();

        if (type == int.class) {
            return entries.startIntField(label, MythicConfig.get(field).intValue())
                .setDefaultValue(defaultValue.intValue())
                .setTooltip(tooltip)
                .setSaveConsumer(value -> MythicConfig.set(field, value))
                .build();
        }
        if (type == float.class) {
            return entries.startFloatField(label, MythicConfig.get(field).floatValue())
                .setDefaultValue(defaultValue.floatValue())
                .setTooltip(tooltip)
                .setSaveConsumer(value -> MythicConfig.set(field, value))
                .build();
        }
        return entries.startDoubleField(label, MythicConfig.get(field).doubleValue())
            .setDefaultValue(defaultValue.doubleValue())
            .setTooltip(tooltip)
            .setSaveConsumer(value -> MythicConfig.set(field, value))
            .build();
    }

    /** SAPPHIRE_HELMET_LEVELS -> "Sapphire Helmet Levels" */
    private static String prettify(String fieldName) {
        String[] words = fieldName.toLowerCase(Locale.ROOT).split("_");
        StringBuilder out = new StringBuilder();
        for (String word : words) {
            if (word.isEmpty()) continue;
            if (out.length() > 0) out.append(' ');
            out.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
        }
        return out.toString();
    }
}
