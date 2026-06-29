package net.trique.mythicupgrades.effect;
import net.trique.mythicupgrades.MythicAnims;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class DamageDeflectionEffect extends MobEffect {

    public DamageDeflectionEffect() {
        super(MobEffectCategory.BENEFICIAL, MythicAnims.DAMAGE_DEFLECTION_EFFECT_COLOR);
    }
}
