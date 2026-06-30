package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class TopazReactionEffect extends MobEffect {

    public TopazReactionEffect() {
        super(MobEffectCategory.BENEFICIAL, MythicAnims.TOPAZ_REACTION_EFFECT_COLOR);
    }
}