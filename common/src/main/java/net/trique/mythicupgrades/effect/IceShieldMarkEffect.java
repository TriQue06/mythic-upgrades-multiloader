package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class IceShieldMarkEffect extends MobEffect {
    public IceShieldMarkEffect() {
        super(MobEffectCategory.HARMFUL, MythicAnims.ICE_SHIELD_MARK_EFFECT_COLOR);
    }
}
