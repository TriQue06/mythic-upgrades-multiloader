package net.trique.mythicupgrades.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum MythicToolMaterials implements Tier {
    AQUAMARINE(4, 2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.AQUAMARINE_INGOT)),
    CITRINE(4, 2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.CITRINE_INGOT)),
    TOPAZ(4, 2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.TOPAZ_INGOT)),
    PERIDOT(4, 2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.PERIDOT_INGOT)),
    RUBY(4, 2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.RUBY_INGOT)),
    SAPPHIRE(4, 2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.SAPPHIRE_INGOT)),
    JADE(4, 2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.JADE_INGOT)),
    AMETRINE(4, 2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.AMETRINE_INGOT));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    MythicToolMaterials(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    @Override public int getUses() { return uses; }
    @Override public float getSpeed() { return speed; }
    @Override public float getAttackDamageBonus() { return damage; }
    @Override public int getLevel() { return level; }
    @Override public int getEnchantmentValue() { return enchantmentValue; }
    @Override public Ingredient getRepairIngredient() { return repairIngredient.get(); }
}
