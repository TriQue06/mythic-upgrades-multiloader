package net.trique.mythicupgrades.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.MythicStats;

public class JadeAuraEffect extends MobEffect {
    private static final ResourceLocation SPEED_ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "jade_aura_speed");

    public JadeAuraEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x58cf3e);
    }

    @Override
    public void addAttributeModifiers(AttributeMap attributeMap, int amplifier) {
        var inst = attributeMap.getInstance(Attributes.MOVEMENT_SPEED);
        if (inst != null)
            inst.addTransientModifier(new AttributeModifier(SPEED_ID,
                (amplifier + 1) * MythicStats.JADE_AURA_SPEED_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    @Override
    public void removeAttributeModifiers(AttributeMap attributeMap) {
        var inst = attributeMap.getInstance(Attributes.MOVEMENT_SPEED);
        if (inst != null) inst.removeModifier(SPEED_ID);
    }
}