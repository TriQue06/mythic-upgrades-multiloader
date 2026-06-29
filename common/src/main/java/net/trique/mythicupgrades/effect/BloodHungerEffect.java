package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class BloodHungerEffect extends MobEffect {

    public BloodHungerEffect() {
        super(MobEffectCategory.BENEFICIAL, MythicAnims.BLOOD_HUNGER_EFFECT_COLOR);
    }
}
