package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FreezeEffect extends MobEffect {
    public FreezeEffect() {
        super(MobEffectCategory.HARMFUL, MythicAnims.FREEZE_EFFECT_COLOR);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.setTicksFrozen(entity.getTicksRequiredToFreeze() + 2);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
