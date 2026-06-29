package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ChargedEffect extends MobEffect {
    public ChargedEffect() {
        super(MobEffectCategory.HARMFUL, MythicAnims.CHARGED_EFFECT_COLOR);
    }
}
