package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.trique.mythicupgrades.Constants;

public class MythicDamageTypeBootstrap {

    public static final ResourceKey<DamageType> DEFLECTING            = key("deflecting_damage_type");
    public static final ResourceKey<DamageType> PERCENTAGE            = key("percentage_damage_type");
    public static final ResourceKey<DamageType> TOPAZ_SHOCK           = key("topaz_shock_damage_type");
    public static final ResourceKey<DamageType> PERIDOT_INCUBATION    = key("peridot_incubation_damage_type");
    public static final ResourceKey<DamageType> ICE_SHIELD_REFLECT    = key("ice_shield_reflect_damage_type");
    public static final ResourceKey<DamageType> CITRINE_CHAIN         = key("citrine_chain_damage_type");
    public static final ResourceKey<DamageType> STATIC_FIELD          = key("static_field_damage_type");
    public static final ResourceKey<DamageType> ICE_BOMB_BURST        = key("ice_bomb_burst_damage_type");

    public static void bootstrap(BootstrapContext<DamageType> ctx) {
        ctx.register(DEFLECTING,            new DamageType("deflecting",         DamageScaling.NEVER, 0.0f));
        ctx.register(PERCENTAGE,            new DamageType("percentage",          DamageScaling.NEVER, 0.0f));
        ctx.register(TOPAZ_SHOCK,           new DamageType("topaz_shock",        DamageScaling.NEVER, 0.0f));
        ctx.register(PERIDOT_INCUBATION,    new DamageType("peridot_incubation", DamageScaling.NEVER, 0.0f));
        ctx.register(ICE_SHIELD_REFLECT,    new DamageType("ice_shield_reflect", DamageScaling.NEVER, 0.0f));
        ctx.register(CITRINE_CHAIN,         new DamageType("citrine_chain",      DamageScaling.NEVER, 0.0f));
        ctx.register(STATIC_FIELD,          new DamageType("static_field",       DamageScaling.NEVER, 0.0f));
        ctx.register(ICE_BOMB_BURST,        new DamageType("ice_bomb_burst",     DamageScaling.NEVER, 0.0f));
    }

    private static ResourceKey<DamageType> key(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE,
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }
}
