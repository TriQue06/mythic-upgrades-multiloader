package net.trique.mythicupgrades.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.trique.mythicupgrades.MythicStats;

import java.util.UUID;

public class JadeAuraEffect extends MobEffect {
    private static final UUID SPEED_UUID = UUID.fromString("7a9c2f1e-4b83-4d56-ae12-9cf38741b5c2");

    public JadeAuraEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x0F6B3F);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        var inst = attributeMap.getInstance(Attributes.MOVEMENT_SPEED);
        if (inst != null)
            inst.addTransientModifier(new AttributeModifier(SPEED_UUID, "Jade Aura speed",
                (amplifier + 1) * MythicStats.JADE_AURA_SPEED_PER_LEVEL, AttributeModifier.Operation.MULTIPLY_BASE));
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        var inst = attributeMap.getInstance(Attributes.MOVEMENT_SPEED);
        if (inst != null) inst.removeModifier(SPEED_UUID);
    }
}
