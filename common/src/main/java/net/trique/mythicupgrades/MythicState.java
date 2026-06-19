package net.trique.mythicupgrades;

import net.minecraft.world.entity.LivingEntity;

import java.util.WeakHashMap;

public class MythicState {
    public static final WeakHashMap<LivingEntity, Integer> TOPAZ_TOOL_HIT_COUNTS = new WeakHashMap<>();
    public static final WeakHashMap<LivingEntity, LivingEntity> ICE_SHIELD_SOURCE = new WeakHashMap<>();
    public static final WeakHashMap<LivingEntity, float[]> TOPAZ_PENDING_WAVES = new WeakHashMap<>();
}
