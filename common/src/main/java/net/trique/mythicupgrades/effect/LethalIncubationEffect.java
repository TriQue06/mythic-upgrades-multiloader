package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class LethalIncubationEffect extends MobEffect {
    public LethalIncubationEffect() {
        super(MobEffectCategory.HARMFUL, MythicAnims.LETHAL_INCUBATION_EFFECT_COLOR);
    }
}
