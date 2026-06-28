package net.trique.mythicupgrades;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
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

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class MythicEffects {

    private static final List<Map.Entry<String, MobEffect>> DEFERRED = new ArrayList<>();

    private static Holder<MobEffect> defer(String name, MobEffect effect) {
        DEFERRED.add(new AbstractMap.SimpleEntry<>(name, effect));
        return Holder.direct(effect);
    }

    public static Holder<MobEffect> DAMAGE_DEFLECTION = defer("damage_deflection", new DamageDeflectionEffect());
    public static Holder<MobEffect> ARCANE_AURA = defer("arcane_aura", new ArcaneAuraEffect());
    public static Holder<MobEffect> TOPAZ_REACTION = defer("topaz_reaction", new TopazReactionEffect());
    public static Holder<MobEffect> FREEZE = defer("freeze", new FreezeEffect());
    public static Holder<MobEffect> BLOOD_THIRST = defer("blood_thirst", new BloodThirstEffect());
    public static Holder<MobEffect> LETHAL_INCUBATION = defer("lethal_incubation", new LethalIncubationEffect());
    public static Holder<MobEffect> MIASMA = defer("miasma", new MiasmaEffect());
    public static Holder<MobEffect> ICE_SHIELD = defer("ice_shield", new IceShieldEffect());
    public static Holder<MobEffect> ICE_BOMB = defer("ice_bomb", new IceBombEffect());
    public static Holder<MobEffect> CHARGED = defer("charged", new ChargedEffect());
    public static Holder<MobEffect> STATIC_FIELD = defer("static_field", new StaticFieldEffect());
    public static Holder<MobEffect> JADE_AURA = defer("jade_aura", new JadeAuraEffect());
    public static Holder<MobEffect> NECOIUM_SHARE = defer("necoium_share", new NecoiumShareEffect());

    public static void register(BiFunction<String, MobEffect, MobEffect> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
        // Replace Holder.direct() with registry-backed holders so MobEffectInstance
        // can be serialized (Holder.direct() has no registry key → save crash).
        DAMAGE_DEFLECTION  = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(DAMAGE_DEFLECTION.value());
        ARCANE_AURA        = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(ARCANE_AURA.value());
        TOPAZ_REACTION     = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(TOPAZ_REACTION.value());
        FREEZE             = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(FREEZE.value());
        BLOOD_THIRST       = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(BLOOD_THIRST.value());
        LETHAL_INCUBATION  = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(LETHAL_INCUBATION.value());
        MIASMA             = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MIASMA.value());
        ICE_SHIELD         = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(ICE_SHIELD.value());
        ICE_BOMB           = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(ICE_BOMB.value());
        CHARGED            = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(CHARGED.value());
        STATIC_FIELD       = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(STATIC_FIELD.value());
        JADE_AURA          = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(JADE_AURA.value());
        NECOIUM_SHARE      = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(NECOIUM_SHARE.value());
        Constants.LOG.info("MythicEffects registered.");
    }
}
