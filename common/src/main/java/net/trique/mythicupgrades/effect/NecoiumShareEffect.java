package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.trique.mythicupgrades.handler.NecoiumShareHandler;

public class NecoiumShareEffect extends MobEffect {
    public NecoiumShareEffect() {
        super(MobEffectCategory.BENEFICIAL, MythicAnims.NECOIUM_SHARE_EFFECT_COLOR);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 10 == 0;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level() instanceof ServerLevel serverLevel) {
            NecoiumShareHandler.onTick(serverLevel, entity);
        }
        return true;
    }
}
