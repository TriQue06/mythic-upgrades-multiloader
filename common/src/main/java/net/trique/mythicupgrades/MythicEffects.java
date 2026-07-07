package net.trique.mythicupgrades;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.trique.mythicupgrades.effect.ArcaneAuraEffect;
import net.trique.mythicupgrades.effect.BloodThirstEffect;
import net.trique.mythicupgrades.effect.ChargedEffect;
import net.trique.mythicupgrades.effect.JadeAuraEffect;
import net.trique.mythicupgrades.effect.StaticFieldEffect;
import net.trique.mythicupgrades.effect.FreezeEffect;
import net.trique.mythicupgrades.effect.DamageDeflectionEffect;
import net.trique.mythicupgrades.effect.IceShieldEffect;
import net.trique.mythicupgrades.effect.LethalIncubationEffect;
import net.trique.mythicupgrades.effect.MiasmaEffect;
import net.trique.mythicupgrades.effect.IceBombEffect;
import net.trique.mythicupgrades.effect.NecoiumShareEffect;
import net.trique.mythicupgrades.effect.TopazReactionEffect;

import java.util.function.BiFunction;

public class MythicEffects {

    // Holders are null until register() is called by the platform.
    // All fields are set to registry-backed Holder.Reference in register().
    public static Holder<MobEffect> DAMAGE_DEFLECTION;
    public static Holder<MobEffect> ARCANE_AURA;
    public static Holder<MobEffect> TOPAZ_REACTION;
    public static Holder<MobEffect> FREEZE;
    public static Holder<MobEffect> BLOOD_THIRST;
    public static Holder<MobEffect> LETHAL_INCUBATION;
    public static Holder<MobEffect> MIASMA;
    public static Holder<MobEffect> ICE_SHIELD;
    public static Holder<MobEffect> ICE_BOMB;
    public static Holder<MobEffect> CHARGED;
    public static Holder<MobEffect> STATIC_FIELD;
    public static Holder<MobEffect> JADE_AURA;
    public static Holder<MobEffect> NECOIUM_SHARE;

    /**
     * Registers all effects. The platform lambda must register the effect and return
     * the registry-backed {@code Holder<MobEffect>} (not Holder.direct). This is called once,
     * before MythicPotions.register() and before any gameplay code runs.
     */
    public static void register(BiFunction<String, MobEffect, Holder<MobEffect>> reg) {
        DAMAGE_DEFLECTION = reg.apply("damage_deflection", new DamageDeflectionEffect());
        ARCANE_AURA       = reg.apply("arcane_aura",       new ArcaneAuraEffect());
        TOPAZ_REACTION    = reg.apply("topaz_reaction",    new TopazReactionEffect());
        FREEZE            = reg.apply("freeze",            new FreezeEffect());
        BLOOD_THIRST      = reg.apply("blood_thirst",      new BloodThirstEffect());
        LETHAL_INCUBATION = reg.apply("lethal_incubation", new LethalIncubationEffect());
        MIASMA            = reg.apply("miasma",            new MiasmaEffect());
        ICE_SHIELD        = reg.apply("ice_shield",        new IceShieldEffect());
        ICE_BOMB          = reg.apply("ice_bomb",          new IceBombEffect());
        CHARGED           = reg.apply("charged",           new ChargedEffect());
        STATIC_FIELD      = reg.apply("static_field",      new StaticFieldEffect());
        JADE_AURA         = reg.apply("jade_aura",         new JadeAuraEffect());
        NECOIUM_SHARE     = reg.apply("necoium_share",     new NecoiumShareEffect());
        Constants.LOG.info("MythicEffects registered.");
    }
}
