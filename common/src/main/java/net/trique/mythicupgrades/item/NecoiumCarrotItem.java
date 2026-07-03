package net.trique.mythicupgrades.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.trique.mythicupgrades.MythicEffects;

public class NecoiumCarrotItem extends Item {
    public NecoiumCarrotItem() {
        super(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(6).alwaysEdible()
            .saturationModifier(1.5f)
            .build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);
        if (!level.isClientSide) {
            entity.addEffect(new MobEffectInstance(MythicEffects.NECOIUM_SHARE, 1800, 0));
        }
        return result;
    }
}
