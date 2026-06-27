package net.trique.mythicupgrades;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.trique.mythicupgrades.item.MythicItems;

public class FabricBrewingHelper {

    public static void register() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            // Aquamarine → Ice Shield
            builder.addMix(Potions.AWKWARD,                                           Ingredient.of(MythicItems.AQUAMARINE), BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD),        Ingredient.of(Items.REDSTONE),         BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD),        Ingredient.of(Items.GLOWSTONE_DUST),   BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD_STRONG));

            // Citrine → Static Field
            builder.addMix(Potions.AWKWARD,                                           Ingredient.of(MythicItems.CITRINE),    BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD),      Ingredient.of(Items.REDSTONE),         BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD),      Ingredient.of(Items.GLOWSTONE_DUST),   BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD_STRONG));

            // Topaz → Topaz Reaction
            builder.addMix(Potions.AWKWARD,                                           Ingredient.of(MythicItems.TOPAZ),      BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION),    Ingredient.of(Items.REDSTONE),         BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION),    Ingredient.of(Items.GLOWSTONE_DUST),   BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION_STRONG));

            // Peridot → Miasma
            builder.addMix(Potions.AWKWARD,                                           Ingredient.of(MythicItems.PERIDOT),    BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA),            Ingredient.of(Items.REDSTONE),         BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA),            Ingredient.of(Items.GLOWSTONE_DUST),   BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA_STRONG));

            // Ruby → Blood Thirst
            builder.addMix(Potions.AWKWARD,                                           Ingredient.of(MythicItems.RUBY),       BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST),      Ingredient.of(Items.REDSTONE),         BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST),      Ingredient.of(Items.GLOWSTONE_DUST),   BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST_STRONG));

            // Sapphire → Damage Deflection
            builder.addMix(Potions.AWKWARD,                                           Ingredient.of(MythicItems.SAPPHIRE),   BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION), Ingredient.of(Items.REDSTONE),         BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION), Ingredient.of(Items.GLOWSTONE_DUST),   BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION_STRONG));

            // Jade → Jade Aura
            builder.addMix(Potions.AWKWARD,                                           Ingredient.of(MythicItems.JADE),       BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA),         Ingredient.of(Items.REDSTONE),         BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA),         Ingredient.of(Items.GLOWSTONE_DUST),   BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA_STRONG));

            // Ametrine → Arcane Aura
            builder.addMix(Potions.AWKWARD,                                           Ingredient.of(MythicItems.AMETRINE),   BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA),       Ingredient.of(Items.REDSTONE),         BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA),       Ingredient.of(Items.GLOWSTONE_DUST),   BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA_STRONG));

            // Necoium → Necoium Share
            builder.addMix(Potions.AWKWARD,                                           Ingredient.of(MythicItems.NECOIUM_INGOT), BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.NECOIUM_SHARE));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.NECOIUM_SHARE),     Ingredient.of(Items.REDSTONE),            BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.NECOIUM_SHARE_LONG));
        });
    }
}
