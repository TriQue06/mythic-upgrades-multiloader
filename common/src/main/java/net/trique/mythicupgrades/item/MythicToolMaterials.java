package net.trique.mythicupgrades.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum MythicToolMaterials implements Tier {
    AQUAMARINE(2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.AQUAMARINE)),
    CITRINE(2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.CITRINE)),
    TOPAZ(4062, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.TOPAZ)),
    PERIDOT(2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.PERIDOT)),
    RUBY(2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.RUBY)),
    SAPPHIRE(2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.SAPPHIRE)),
    JADE(2031, 14.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.JADE)),
    AMETRINE(2031, 9.0F, 4.0F, 15, () -> Ingredient.of(MythicItems.AMETRINE));

    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    MythicToolMaterials(int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    @Override public int getUses() { return uses; }
    @Override public float getSpeed() { return speed; }
    @Override public float getAttackDamageBonus() { return damage; }
    @Override public TagKey<Block> getIncorrectBlocksForDrops() { return BlockTags.INCORRECT_FOR_NETHERITE_TOOL; }
    @Override public int getEnchantmentValue() { return enchantmentValue; }
    @Override public Ingredient getRepairIngredient() { return repairIngredient.get(); }
}
