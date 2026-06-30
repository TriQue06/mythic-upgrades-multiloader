package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class IceBombEffect extends MobEffect {
    public IceBombEffect() {
        super(MobEffectCategory.HARMFUL, MythicAnims.ICE_BOMB_EFFECT_COLOR);
    }
}
