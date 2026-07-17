package net.trique.mythicupgrades;

import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.trique.mythicupgrades.item.MythicItems;

public class FabricBrewingHelper {

    private static Holder<Potion> h(Potion potion) {
        return BuiltInRegistries.POTION.wrapAsHolder(potion);
    }

    public static void register() {
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(MythicItems.AQUAMARINE_CRYSTAL_SHARD), h(MythicPotions.ICE_SHIELD));
            builder.registerPotionRecipe(h(MythicPotions.ICE_SHIELD), Ingredient.of(Items.REDSTONE), h(MythicPotions.ICE_SHIELD_LONG));
            builder.registerPotionRecipe(h(MythicPotions.ICE_SHIELD), Ingredient.of(Items.GLOWSTONE_DUST), h(MythicPotions.ICE_SHIELD_STRONG));

            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(MythicItems.CITRINE_CRYSTAL_SHARD), h(MythicPotions.STATIC_FIELD));
            builder.registerPotionRecipe(h(MythicPotions.STATIC_FIELD), Ingredient.of(Items.REDSTONE), h(MythicPotions.STATIC_FIELD_LONG));
            builder.registerPotionRecipe(h(MythicPotions.STATIC_FIELD), Ingredient.of(Items.GLOWSTONE_DUST), h(MythicPotions.STATIC_FIELD_STRONG));

            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(MythicItems.TOPAZ_CRYSTAL_SHARD), h(MythicPotions.TOPAZ_REACTION));
            builder.registerPotionRecipe(h(MythicPotions.TOPAZ_REACTION), Ingredient.of(Items.REDSTONE), h(MythicPotions.TOPAZ_REACTION_LONG));
            builder.registerPotionRecipe(h(MythicPotions.TOPAZ_REACTION), Ingredient.of(Items.GLOWSTONE_DUST), h(MythicPotions.TOPAZ_REACTION_STRONG));

            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(MythicItems.PERIDOT_CRYSTAL_SHARD), h(MythicPotions.MIASMA));
            builder.registerPotionRecipe(h(MythicPotions.MIASMA), Ingredient.of(Items.REDSTONE), h(MythicPotions.MIASMA_LONG));
            builder.registerPotionRecipe(h(MythicPotions.MIASMA), Ingredient.of(Items.GLOWSTONE_DUST), h(MythicPotions.MIASMA_STRONG));

            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(MythicItems.RUBY_CRYSTAL_SHARD), h(MythicPotions.BLOOD_THIRST));
            builder.registerPotionRecipe(h(MythicPotions.BLOOD_THIRST), Ingredient.of(Items.REDSTONE), h(MythicPotions.BLOOD_THIRST_LONG));
            builder.registerPotionRecipe(h(MythicPotions.BLOOD_THIRST), Ingredient.of(Items.GLOWSTONE_DUST), h(MythicPotions.BLOOD_THIRST_STRONG));

            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(MythicItems.SAPPHIRE_CRYSTAL_SHARD), h(MythicPotions.DAMAGE_DEFLECTION));
            builder.registerPotionRecipe(h(MythicPotions.DAMAGE_DEFLECTION), Ingredient.of(Items.REDSTONE), h(MythicPotions.DAMAGE_DEFLECTION_LONG));
            builder.registerPotionRecipe(h(MythicPotions.DAMAGE_DEFLECTION), Ingredient.of(Items.GLOWSTONE_DUST), h(MythicPotions.DAMAGE_DEFLECTION_STRONG));

            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(MythicItems.JADE_CRYSTAL_SHARD), h(MythicPotions.JADE_AURA));
            builder.registerPotionRecipe(h(MythicPotions.JADE_AURA), Ingredient.of(Items.REDSTONE), h(MythicPotions.JADE_AURA_LONG));
            builder.registerPotionRecipe(h(MythicPotions.JADE_AURA), Ingredient.of(Items.GLOWSTONE_DUST), h(MythicPotions.JADE_AURA_STRONG));

            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(MythicItems.AMETRINE_CRYSTAL_SHARD), h(MythicPotions.ARCANE_AURA));
            builder.registerPotionRecipe(h(MythicPotions.ARCANE_AURA), Ingredient.of(Items.REDSTONE), h(MythicPotions.ARCANE_AURA_LONG));
            builder.registerPotionRecipe(h(MythicPotions.ARCANE_AURA), Ingredient.of(Items.GLOWSTONE_DUST), h(MythicPotions.ARCANE_AURA_STRONG));

            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(MythicItems.NECOIUM_INGOT), h(MythicPotions.NECOIUM_SHARE));
            builder.registerPotionRecipe(h(MythicPotions.NECOIUM_SHARE), Ingredient.of(Items.REDSTONE), h(MythicPotions.NECOIUM_SHARE_LONG));

            builder.registerPotionRecipe(h(MythicPotions.ICE_SHIELD), Ingredient.of(Items.FERMENTED_SPIDER_EYE), h(MythicPotions.ICE_BOMB));
            builder.registerPotionRecipe(h(MythicPotions.ICE_BOMB), Ingredient.of(Items.REDSTONE), h(MythicPotions.ICE_BOMB_LONG));
            builder.registerPotionRecipe(h(MythicPotions.ICE_BOMB), Ingredient.of(Items.GLOWSTONE_DUST), h(MythicPotions.ICE_BOMB_STRONG));

            builder.registerPotionRecipe(h(MythicPotions.ICE_BOMB), Ingredient.of(Items.FERMENTED_SPIDER_EYE), h(MythicPotions.FREEZE));
            builder.registerPotionRecipe(h(MythicPotions.FREEZE), Ingredient.of(Items.REDSTONE), h(MythicPotions.FREEZE_LONG));
            builder.registerPotionRecipe(h(MythicPotions.FREEZE), Ingredient.of(Items.GLOWSTONE_DUST), h(MythicPotions.FREEZE_STRONG));

            builder.registerPotionRecipe(h(MythicPotions.STATIC_FIELD), Ingredient.of(Items.FERMENTED_SPIDER_EYE), h(MythicPotions.CHARGED));
            builder.registerPotionRecipe(h(MythicPotions.CHARGED), Ingredient.of(Items.REDSTONE), h(MythicPotions.CHARGED_LONG));
            builder.registerPotionRecipe(h(MythicPotions.CHARGED), Ingredient.of(Items.GLOWSTONE_DUST), h(MythicPotions.CHARGED_STRONG));

            builder.registerPotionRecipe(h(MythicPotions.MIASMA), Ingredient.of(Items.FERMENTED_SPIDER_EYE), h(MythicPotions.LETHAL_INCUBATION));
            builder.registerPotionRecipe(h(MythicPotions.LETHAL_INCUBATION), Ingredient.of(Items.REDSTONE), h(MythicPotions.LETHAL_INCUBATION_LONG));
            builder.registerPotionRecipe(h(MythicPotions.LETHAL_INCUBATION), Ingredient.of(Items.GLOWSTONE_DUST), h(MythicPotions.LETHAL_INCUBATION_STRONG));
        });
    }
}
