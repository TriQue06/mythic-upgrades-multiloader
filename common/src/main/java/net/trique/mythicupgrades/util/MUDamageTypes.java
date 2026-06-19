package net.trique.mythicupgrades.util;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.trique.mythicupgrades.Constants;

public class MUDamageTypes {

    public static final ResourceKey<DamageType> DEFLECTING = ResourceKey.create(
        Registries.DAMAGE_TYPE, new ResourceLocation(Constants.MOD_ID, "deflecting_damage_type"));

    public static final ResourceKey<DamageType> PERCENTAGE = ResourceKey.create(
        Registries.DAMAGE_TYPE, new ResourceLocation(Constants.MOD_ID, "percentage_damage_type"));

    public static final ResourceKey<DamageType> TOPAZ_SHOCK = ResourceKey.create(
        Registries.DAMAGE_TYPE, new ResourceLocation(Constants.MOD_ID, "topaz_shock_damage_type"));

    public static final ResourceKey<DamageType> PERIDOT_INCUBATION = ResourceKey.create(
        Registries.DAMAGE_TYPE, new ResourceLocation(Constants.MOD_ID, "peridot_incubation_damage_type"));

    public static final ResourceKey<DamageType> ICY_AURA_BURST = ResourceKey.create(
        Registries.DAMAGE_TYPE, new ResourceLocation(Constants.MOD_ID, "icy_aura_burst_damage_type"));

    public static final ResourceKey<DamageType> ICE_SHIELD_REFLECT = ResourceKey.create(
        Registries.DAMAGE_TYPE, new ResourceLocation(Constants.MOD_ID, "ice_shield_reflect_damage_type"));

    public static DamageSource deflecting(LivingEntity source) {
        return build(DEFLECTING, source);
    }

    public static DamageSource percentage(LivingEntity source) {
        return build(PERCENTAGE, source);
    }

    public static DamageSource topazShock(LivingEntity source) {
        return build(TOPAZ_SHOCK, source);
    }

    public static DamageSource peridotIncubation(LivingEntity source) {
        return build(PERIDOT_INCUBATION, source);
    }

    public static DamageSource icyAuraBurst(LivingEntity source) {
        return build(ICY_AURA_BURST, source);
    }

    public static DamageSource iceShieldReflect(LivingEntity source) {
        return build(ICE_SHIELD_REFLECT, source);
    }

    public static DamageSource topazShockEffect(LivingEntity target) {
        Holder<DamageType> holder = target.level().registryAccess()
            .registryOrThrow(Registries.DAMAGE_TYPE)
            .getHolderOrThrow(TOPAZ_SHOCK);
        return new DamageSource(holder);
    }

    private static DamageSource build(ResourceKey<DamageType> key, LivingEntity source) {
        Holder<DamageType> holder = source.level().registryAccess()
            .registryOrThrow(Registries.DAMAGE_TYPE)
            .getHolderOrThrow(key);
        return new DamageSource(holder, source, source);
    }
}
