package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class BloodThirstEffect extends MobEffect {
    public BloodThirstEffect() {
        super(MobEffectCategory.BENEFICIAL, MythicAnims.BLOOD_THIRST_EFFECT_COLOR);
    }
}
