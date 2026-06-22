package net.trique.mythicupgrades;

import net.minecraft.world.effect.MobEffect;
import net.trique.mythicupgrades.effect.ArcaneAuraEffect;
import net.trique.mythicupgrades.effect.BloodThirstEffect;
import net.trique.mythicupgrades.effect.BouncerEffect;
import net.trique.mythicupgrades.effect.ChargedEffect;
import net.trique.mythicupgrades.effect.JadeAuraEffect;
import net.trique.mythicupgrades.effect.StaticFieldEffect;
import net.trique.mythicupgrades.effect.FreezeEffect;
import net.trique.mythicupgrades.effect.DamageDeflectionEffect;
import net.trique.mythicupgrades.effect.IceShieldEffect;
import net.trique.mythicupgrades.effect.IceShieldMarkEffect;
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

    private static <T extends MobEffect> T defer(String name, T effect) {
        DEFERRED.add(new AbstractMap.SimpleEntry<>(name, effect));
        return effect;
    }

    public static final MobEffect DAMAGE_DEFLECTION = defer("damage_deflection", new DamageDeflectionEffect());
    public static final MobEffect ARCANE_AURA = defer("arcane_aura", new ArcaneAuraEffect());
    public static final MobEffect TOPAZ_REACTION = defer("topaz_reaction", new TopazReactionEffect());
public static final MobEffect FREEZE = defer("freeze", new FreezeEffect());
    public static final MobEffect BLOOD_THIRST = defer("blood_thirst", new BloodThirstEffect());
    public static final MobEffect LETHAL_INCUBATION = defer("lethal_incubation", new LethalIncubationEffect());
    public static final MobEffect MIASMA = defer("miasma", new MiasmaEffect());
    public static final MobEffect ICE_SHIELD_MARK = defer("ice_shield_mark", new IceShieldMarkEffect());
    public static final MobEffect ICE_SHIELD = defer("ice_shield", new IceShieldEffect());
    public static final MobEffect ICE_BOMB = defer("ice_bomb", new IceBombEffect());
    public static final MobEffect CHARGED = defer("charged", new ChargedEffect());
    public static final MobEffect STATIC_FIELD = defer("static_field", new StaticFieldEffect());
    public static final MobEffect BOUNCER = defer("bouncer", new BouncerEffect());
    public static final MobEffect JADE_AURA = defer("jade_aura", new JadeAuraEffect());
    public static final MobEffect NECOIUM_SHARE = defer("necoium_share", new NecoiumShareEffect());

    public static void register(BiFunction<String, MobEffect, MobEffect> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
        Constants.LOG.info("MythicEffects registered.");
    }
}
