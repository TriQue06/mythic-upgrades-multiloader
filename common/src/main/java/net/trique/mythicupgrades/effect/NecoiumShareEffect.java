package net.trique.mythicupgrades.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.trique.mythicupgrades.handler.NecoiumShareHandler;

public class NecoiumShareEffect extends MobEffect {
    public NecoiumShareEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xe61a8f);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 10 == 0;
    }

    @Override
    public void applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        NecoiumShareHandler.onTick(level, entity);
    }
}
