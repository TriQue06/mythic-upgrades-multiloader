package net.trique.mythicupgrades.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FreezeEffect extends MobEffect {
    public FreezeEffect() {
        super(MobEffectCategory.HARMFUL, 0x0D9CC1);
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
