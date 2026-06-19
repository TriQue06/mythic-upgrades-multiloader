package net.trique.mythicupgrades.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.trique.mythicupgrades.MythicStats;
import net.trique.mythicupgrades.util.MUDamageTypes;

public class TopazShockEffect extends MobEffect {
    public TopazShockEffect() {
        super(MobEffectCategory.HARMFUL, 0xE64A1B);
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public void applyInstantenousEffect(Entity source, Entity indirectSource, LivingEntity entity, int amplifier, double health) {
        float damage = (amplifier + 1) * MythicStats.TOPAZ_SHOCK_DAMAGE_PER_LEVEL;
        entity.hurt(MUDamageTypes.topazShockEffect(entity), damage);
    }
}
