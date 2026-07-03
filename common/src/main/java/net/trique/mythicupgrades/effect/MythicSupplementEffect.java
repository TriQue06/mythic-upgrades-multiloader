package net.trique.mythicupgrades.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.trique.mythicupgrades.MythicAnims;
import net.trique.mythicupgrades.handler.NecoiumShareHandler;

import java.util.List;

public class MythicSupplementEffect extends MobEffect {

    private static final int BOOST_DURATION = 40;

    public MythicSupplementEffect() {
        super(MobEffectCategory.BENEFICIAL, MythicAnims.MYTHIC_SUPPLEMENT_EFFECT_COLOR);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 10 == 0;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        List<MobEffect> shareableEffects = NecoiumShareHandler.getShareableEffects();
        for (MobEffect effect : shareableEffects) {
            MobEffectInstance inst = entity.getEffect(effect);
            if (inst != null && !inst.isAmbient()) {
                int boostedAmp = inst.getAmplifier() + 1;
                MobEffectInstance current = entity.getEffect(effect);
                if (current == null || (current.isAmbient() && !current.showParticles())
                        || current.getAmplifier() < boostedAmp) {
                    entity.addEffect(new MobEffectInstance(effect, BOOST_DURATION, boostedAmp, true, false, false));
                }
            }
        }
        return true;
    }
}
