package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class IceShieldEffect extends MobEffect {
    public IceShieldEffect() {
        super(MobEffectCategory.BENEFICIAL, MythicAnims.ICE_SHIELD_EFFECT_COLOR);
    }
}