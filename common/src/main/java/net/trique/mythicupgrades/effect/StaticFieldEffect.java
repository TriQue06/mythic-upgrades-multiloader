package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class StaticFieldEffect extends MobEffect {
    public StaticFieldEffect() {
        super(MobEffectCategory.BENEFICIAL, MythicAnims.STATIC_FIELD_EFFECT_COLOR);
    }
}
