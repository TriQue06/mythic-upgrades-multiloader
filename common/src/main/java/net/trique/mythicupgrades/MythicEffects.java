package net.trique.mythicupgrades;

import net.minecraft.core.Holder;
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

    private static Holder<MobEffect> defer(String name, MobEffect effect) {
        DEFERRED.add(new AbstractMap.SimpleEntry<>(name, effect));
        return Holder.direct(effect);
    }

    public static final Holder<MobEffect> DAMAGE_DEFLECTION = defer("damage_deflection", new DamageDeflectionEffect());
    public static final Holder<MobEffect> ARCANE_AURA = defer("arcane_aura", new ArcaneAuraEffect());
    public static final Holder<MobEffect> TOPAZ_REACTION = defer("topaz_reaction", new TopazReactionEffect());
    public static final Holder<MobEffect> FREEZE = defer("freeze", new FreezeEffect());
    public static final Holder<MobEffect> BLOOD_THIRST = defer("blood_thirst", new BloodThirstEffect());
    public static final Holder<MobEffect> LETHAL_INCUBATION = defer("lethal_incubation", new LethalIncubationEffect());
    public static final Holder<MobEffect> MIASMA = defer("miasma", new MiasmaEffect());
    public static final Holder<MobEffect> ICE_SHIELD_MARK = defer("ice_shield_mark", new IceShieldMarkEffect());
    public static final Holder<MobEffect> ICE_SHIELD = defer("ice_shield", new IceShieldEffect());
    public static final Holder<MobEffect> ICE_BOMB = defer("ice_bomb", new IceBombEffect());
    public static final Holder<MobEffect> CHARGED = defer("charged", new ChargedEffect());
    public static final Holder<MobEffect> STATIC_FIELD = defer("static_field", new StaticFieldEffect());
    public static final Holder<MobEffect> BOUNCER = defer("bouncer", new BouncerEffect());
    public static final Holder<MobEffect> JADE_AURA = defer("jade_aura", new JadeAuraEffect());
    public static final Holder<MobEffect> NECOIUM_SHARE = defer("necoium_share", new NecoiumShareEffect());

    public static void register(BiFunction<String, MobEffect, MobEffect> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
        Constants.LOG.info("MythicEffects registered.");
    }
}
