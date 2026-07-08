package net.trique.mythicupgrades;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

import java.util.function.BiFunction;

public class MythicPotions {

    public static Potion ICE_SHIELD;
    public static Potion ICE_SHIELD_LONG;
    public static Potion ICE_SHIELD_STRONG;

    public static Potion STATIC_FIELD;
    public static Potion STATIC_FIELD_LONG;
    public static Potion STATIC_FIELD_STRONG;

    public static Potion TOPAZ_REACTION;
    public static Potion TOPAZ_REACTION_LONG;
    public static Potion TOPAZ_REACTION_STRONG;

    public static Potion MIASMA;
    public static Potion MIASMA_LONG;
    public static Potion MIASMA_STRONG;

    public static Potion BLOOD_THIRST;
    public static Potion BLOOD_THIRST_LONG;
    public static Potion BLOOD_THIRST_STRONG;

    public static Potion DAMAGE_DEFLECTION;
    public static Potion DAMAGE_DEFLECTION_LONG;
    public static Potion DAMAGE_DEFLECTION_STRONG;

    public static Potion JADE_AURA;
    public static Potion JADE_AURA_LONG;
    public static Potion JADE_AURA_STRONG;

    public static Potion ARCANE_AURA;
    public static Potion ARCANE_AURA_LONG;
    public static Potion ARCANE_AURA_STRONG;

    public static Potion NECOIUM_SHARE;
    public static Potion NECOIUM_SHARE_LONG;

    public static Potion ICE_BOMB;
    public static Potion ICE_BOMB_LONG;
    public static Potion ICE_BOMB_STRONG;

    public static Potion FREEZE;
    public static Potion FREEZE_LONG;
    public static Potion FREEZE_STRONG;

    public static Potion CHARGED;
    public static Potion CHARGED_LONG;
    public static Potion CHARGED_STRONG;

    public static Potion LETHAL_INCUBATION;
    public static Potion LETHAL_INCUBATION_LONG;
    public static Potion LETHAL_INCUBATION_STRONG;

    public static void register(BiFunction<String, Potion, Potion> reg) {
        ICE_SHIELD = def(reg, "ice_shield", "ice_shield", MythicEffects.ICE_SHIELD, 3600, 2);
        ICE_SHIELD_LONG = def(reg, "long_ice_shield", "ice_shield", MythicEffects.ICE_SHIELD, 9600, 2);
        ICE_SHIELD_STRONG = def(reg, "strong_ice_shield", "ice_shield", MythicEffects.ICE_SHIELD, 1800, 4);

        STATIC_FIELD = def(reg, "static_field", "static_field", MythicEffects.STATIC_FIELD, 3600, 2);
        STATIC_FIELD_LONG = def(reg, "long_static_field", "static_field", MythicEffects.STATIC_FIELD, 9600, 2);
        STATIC_FIELD_STRONG = def(reg, "strong_static_field", "static_field", MythicEffects.STATIC_FIELD, 1800, 4);

        TOPAZ_REACTION = def(reg, "topaz_reaction", "topaz_reaction", MythicEffects.TOPAZ_REACTION, 3600, 2);
        TOPAZ_REACTION_LONG = def(reg, "long_topaz_reaction", "topaz_reaction", MythicEffects.TOPAZ_REACTION, 9600, 2);
        TOPAZ_REACTION_STRONG = def(reg, "strong_topaz_reaction", "topaz_reaction", MythicEffects.TOPAZ_REACTION, 1800, 4);

        MIASMA = def(reg, "miasma", "miasma", MythicEffects.MIASMA, 3600, 2);
        MIASMA_LONG = def(reg, "long_miasma", "miasma", MythicEffects.MIASMA, 9600, 2);
        MIASMA_STRONG = def(reg, "strong_miasma", "miasma", MythicEffects.MIASMA, 1800, 4);

        BLOOD_THIRST = def(reg, "blood_thirst", "blood_thirst", MythicEffects.BLOOD_THIRST, 3600, 2);
        BLOOD_THIRST_LONG = def(reg, "long_blood_thirst", "blood_thirst", MythicEffects.BLOOD_THIRST, 9600, 2);
        BLOOD_THIRST_STRONG = def(reg, "strong_blood_thirst", "blood_thirst", MythicEffects.BLOOD_THIRST, 1800, 4);

        DAMAGE_DEFLECTION = def(reg, "damage_deflection", "damage_deflection", MythicEffects.DAMAGE_DEFLECTION, 3600, 2);
        DAMAGE_DEFLECTION_LONG = def(reg, "long_damage_deflection", "damage_deflection", MythicEffects.DAMAGE_DEFLECTION, 9600, 2);
        DAMAGE_DEFLECTION_STRONG = def(reg, "strong_damage_deflection", "damage_deflection", MythicEffects.DAMAGE_DEFLECTION, 1800, 4);

        JADE_AURA = def(reg, "jade_aura", "jade_aura", MythicEffects.JADE_AURA, 3600, 2);
        JADE_AURA_LONG = def(reg, "long_jade_aura", "jade_aura", MythicEffects.JADE_AURA, 9600, 2);
        JADE_AURA_STRONG = def(reg, "strong_jade_aura", "jade_aura", MythicEffects.JADE_AURA, 1800, 4);

        ARCANE_AURA = def(reg, "arcane_aura", "arcane_aura", MythicEffects.ARCANE_AURA, 3600, 2);
        ARCANE_AURA_LONG = def(reg, "long_arcane_aura", "arcane_aura", MythicEffects.ARCANE_AURA, 9600, 2);
        ARCANE_AURA_STRONG = def(reg, "strong_arcane_aura", "arcane_aura", MythicEffects.ARCANE_AURA, 1800, 4);

        NECOIUM_SHARE = def(reg, "necoium_share", "necoium_share", MythicEffects.NECOIUM_SHARE, 1800, 0);
        NECOIUM_SHARE_LONG = def(reg, "long_necoium_share", "necoium_share", MythicEffects.NECOIUM_SHARE, 4800, 0);

        ICE_BOMB = def(reg, "ice_bomb", "ice_bomb", MythicEffects.ICE_BOMB, 3600, 2);
        ICE_BOMB_LONG = def(reg, "long_ice_bomb", "ice_bomb", MythicEffects.ICE_BOMB, 9600, 2);
        ICE_BOMB_STRONG = def(reg, "strong_ice_bomb", "ice_bomb", MythicEffects.ICE_BOMB, 1800, 4);

        FREEZE = def(reg, "freeze", "freeze", MythicEffects.FREEZE, 3600, 2);
        FREEZE_LONG = def(reg, "long_freeze", "freeze", MythicEffects.FREEZE, 9600, 2);
        FREEZE_STRONG = def(reg, "strong_freeze", "freeze", MythicEffects.FREEZE, 1800, 4);

        CHARGED = def(reg, "charged", "charged", MythicEffects.CHARGED, 3600, 2);
        CHARGED_LONG = def(reg, "long_charged", "charged", MythicEffects.CHARGED, 9600, 2);
        CHARGED_STRONG = def(reg, "strong_charged", "charged", MythicEffects.CHARGED, 1800, 4);

        LETHAL_INCUBATION = def(reg, "lethal_incubation", "lethal_incubation", MythicEffects.LETHAL_INCUBATION, 3600, 2);
        LETHAL_INCUBATION_LONG = def(reg, "long_lethal_incubation", "lethal_incubation", MythicEffects.LETHAL_INCUBATION, 9600, 2);
        LETHAL_INCUBATION_STRONG = def(reg, "strong_lethal_incubation", "lethal_incubation", MythicEffects.LETHAL_INCUBATION, 1800, 4);

        Constants.LOG.info("MythicPotions registered.");
    }

    private static Potion def(BiFunction<String, Potion, Potion> reg, String id, String baseName,
                               Holder<MobEffect> effect, int duration, int amplifier) {
        return reg.apply(id, new Potion(baseName, new MobEffectInstance(effect, duration, amplifier)));
    }

    public static void registerBrewingRecipes() {
    }
}
