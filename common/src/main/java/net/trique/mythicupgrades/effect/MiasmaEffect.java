package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class MiasmaEffect extends MobEffect {
    public MiasmaEffect() {
        super(MobEffectCategory.BENEFICIAL, MythicAnims.MIASMA_EFFECT_COLOR);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.removeEffect(MobEffects.POISON);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}