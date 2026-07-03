package net.trique.mythicupgrades.effect;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.trique.mythicupgrades.MythicAnims;
import net.trique.mythicupgrades.MythicEffects;
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
        List<Holder<MobEffect>> shareableEffects = NecoiumShareHandler.getShareableEffects();
        for (Holder<MobEffect> effectHolder : shareableEffects) {
            MobEffectInstance inst = entity.getEffect(effectHolder);
            // Only boost effects the entity owns (non-ambient = not from necoium share)
            if (inst != null && !inst.isAmbient()) {
                int boostedAmp = inst.getAmplifier() + 1;
                MobEffectInstance current = entity.getEffect(effectHolder);
                // Add hidden ambient copy at amp+1; visible=false distinguishes it from share copies
                if (current == null || current.isAmbient() && !current.isVisible()
                        || current.getAmplifier() < boostedAmp) {
                    entity.addEffect(new MobEffectInstance(effectHolder, BOOST_DURATION, boostedAmp, true, false, false));
                }
            }
        }
        return true;
    }
}
