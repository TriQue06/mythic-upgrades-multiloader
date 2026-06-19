package net.trique.mythicupgrades;

import net.minecraft.world.effect.MobEffect;
import net.trique.mythicupgrades.effect.ArcaneAuraEffect;
import net.trique.mythicupgrades.effect.BloodThirstEffect;
import net.trique.mythicupgrades.effect.DamageDeflectionEffect;
import net.trique.mythicupgrades.effect.IceShieldEffect;
import net.trique.mythicupgrades.effect.IcyAuraEffect;
import net.trique.mythicupgrades.effect.LethalIncubationEffect;
import net.trique.mythicupgrades.effect.MiasmaEffect;
import net.trique.mythicupgrades.effect.TopazReactionEffect;
import net.trique.mythicupgrades.effect.TopazShockEffect;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class MythicEffects {

    private static final List<Map.Entry<String, MobEffect>> DEFERRED = new ArrayList<>();

    private static <T extends MobEffect> T defer(String name, T effect) {
        DEFERRED.add(new AbstractMap.SimpleEntry<>(name, effect));
        return effect;
    }

    public static final MobEffect DAMAGE_DEFLECTION = defer("damage_deflection", new DamageDeflectionEffect());
    public static final MobEffect ARCANE_AURA = defer("arcane_aura", new ArcaneAuraEffect());
    public static final MobEffect TOPAZ_REACTION = defer("topaz_reaction", new TopazReactionEffect());
    public static final MobEffect TOPAZ_SHOCK = defer("topaz_shock", new TopazShockEffect());
    public static final MobEffect BLOOD_THIRST = defer("blood_thirst", new BloodThirstEffect());
    public static final MobEffect LETHAL_INCUBATION = defer("lethal_incubation", new LethalIncubationEffect());
    public static final MobEffect MIASMA = defer("miasma", new MiasmaEffect());
    public static final MobEffect ICY_AURA = defer("icy_aura", new IcyAuraEffect());
    public static final MobEffect ICE_SHIELD = defer("ice_shield", new IceShieldEffect());

    public static void register(BiFunction<String, MobEffect, MobEffect> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
        Constants.LOG.info("MythicEffects registered.");
    }
}
