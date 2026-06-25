package net.trique.mythicupgrades;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class MythicPotions {

    private static final List<Map.Entry<String, Potion>> DEFERRED = new ArrayList<>();

    private static Potion def(String id, String baseName, Holder<MobEffect> effect, int duration, int amplifier) {
        Potion p = new Potion(baseName, new MobEffectInstance(effect, duration, amplifier));
        DEFERRED.add(new AbstractMap.SimpleEntry<>(id, p));
        return p;
    }

    // Aquamarine → Ice Shield (3:00 III / 8:00 III / 1:30 V)
    public static final Potion ICE_SHIELD        = def("ice_shield",        "ice_shield", MythicEffects.ICE_SHIELD, 3600, 2);
    public static final Potion ICE_SHIELD_LONG   = def("long_ice_shield",   "ice_shield", MythicEffects.ICE_SHIELD, 9600, 2);
    public static final Potion ICE_SHIELD_STRONG = def("strong_ice_shield", "ice_shield", MythicEffects.ICE_SHIELD, 1800, 4);

    // Citrine → Static Field
    public static final Potion STATIC_FIELD        = def("static_field",        "static_field", MythicEffects.STATIC_FIELD, 3600, 2);
    public static final Potion STATIC_FIELD_LONG   = def("long_static_field",   "static_field", MythicEffects.STATIC_FIELD, 9600, 2);
    public static final Potion STATIC_FIELD_STRONG = def("strong_static_field", "static_field", MythicEffects.STATIC_FIELD, 1800, 4);

    // Topaz → Topaz Reaction
    public static final Potion TOPAZ_REACTION        = def("topaz_reaction",        "topaz_reaction", MythicEffects.TOPAZ_REACTION, 3600, 2);
    public static final Potion TOPAZ_REACTION_LONG   = def("long_topaz_reaction",   "topaz_reaction", MythicEffects.TOPAZ_REACTION, 9600, 2);
    public static final Potion TOPAZ_REACTION_STRONG = def("strong_topaz_reaction", "topaz_reaction", MythicEffects.TOPAZ_REACTION, 1800, 4);

    // Peridot → Miasma
    public static final Potion MIASMA        = def("miasma",        "miasma", MythicEffects.MIASMA, 3600, 2);
    public static final Potion MIASMA_LONG   = def("long_miasma",   "miasma", MythicEffects.MIASMA, 9600, 2);
    public static final Potion MIASMA_STRONG = def("strong_miasma", "miasma", MythicEffects.MIASMA, 1800, 4);

    // Ruby → Blood Thirst
    public static final Potion BLOOD_THIRST        = def("blood_thirst",        "blood_thirst", MythicEffects.BLOOD_THIRST, 3600, 2);
    public static final Potion BLOOD_THIRST_LONG   = def("long_blood_thirst",   "blood_thirst", MythicEffects.BLOOD_THIRST, 9600, 2);
    public static final Potion BLOOD_THIRST_STRONG = def("strong_blood_thirst", "blood_thirst", MythicEffects.BLOOD_THIRST, 1800, 4);

    // Sapphire → Damage Deflection
    public static final Potion DAMAGE_DEFLECTION        = def("damage_deflection",        "damage_deflection", MythicEffects.DAMAGE_DEFLECTION, 3600, 2);
    public static final Potion DAMAGE_DEFLECTION_LONG   = def("long_damage_deflection",   "damage_deflection", MythicEffects.DAMAGE_DEFLECTION, 9600, 2);
    public static final Potion DAMAGE_DEFLECTION_STRONG = def("strong_damage_deflection", "damage_deflection", MythicEffects.DAMAGE_DEFLECTION, 1800, 4);

    // Jade → Jade Aura
    public static final Potion JADE_AURA        = def("jade_aura",        "jade_aura", MythicEffects.JADE_AURA, 3600, 2);
    public static final Potion JADE_AURA_LONG   = def("long_jade_aura",   "jade_aura", MythicEffects.JADE_AURA, 9600, 2);
    public static final Potion JADE_AURA_STRONG = def("strong_jade_aura", "jade_aura", MythicEffects.JADE_AURA, 1800, 4);

    // Ametrine → Arcane Aura
    public static final Potion ARCANE_AURA        = def("arcane_aura",        "arcane_aura", MythicEffects.ARCANE_AURA, 3600, 2);
    public static final Potion ARCANE_AURA_LONG   = def("long_arcane_aura",   "arcane_aura", MythicEffects.ARCANE_AURA, 9600, 2);
    public static final Potion ARCANE_AURA_STRONG = def("strong_arcane_aura", "arcane_aura", MythicEffects.ARCANE_AURA, 1800, 4);

    public static void register(BiFunction<String, Potion, Potion> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
        Constants.LOG.info("MythicPotions registered.");
    }

    public static void registerBrewingRecipes() {
        // TODO: register via platform-specific brewing API
    }
}
