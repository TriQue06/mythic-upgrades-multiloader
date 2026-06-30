package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ArcaneAuraEffect extends MobEffect {

    public ArcaneAuraEffect() {
        super(MobEffectCategory.BENEFICIAL, MythicAnims.ARCANE_AURA_EFFECT_COLOR);
    }
}