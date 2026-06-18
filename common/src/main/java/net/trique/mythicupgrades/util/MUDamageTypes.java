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

    public static DamageSource deflecting(LivingEntity source) {
        Holder<DamageType> holder = source.level().registryAccess()
            .registryOrThrow(Registries.DAMAGE_TYPE)
            .getHolderOrThrow(DEFLECTING);
        return new DamageSource(holder, source, source);
    }

    public static DamageSource percentage(LivingEntity source) {
        Holder<DamageType> holder = source.level().registryAccess()
            .registryOrThrow(Registries.DAMAGE_TYPE)
            .getHolderOrThrow(PERCENTAGE);
        return new DamageSource(holder, source, source);
    }

    public static DamageSource topazShock(LivingEntity source) {
        Holder<DamageType> holder = source.level().registryAccess()
            .registryOrThrow(Registries.DAMAGE_TYPE)
            .getHolderOrThrow(TOPAZ_SHOCK);
        return new DamageSource(holder, source, source);
    }
}
