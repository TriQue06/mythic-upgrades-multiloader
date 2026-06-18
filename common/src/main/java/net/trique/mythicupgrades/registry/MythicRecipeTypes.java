package net.trique.mythicupgrades.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.recipe.MythicUpgradingTableRecipe;

import java.util.function.BiFunction;

public class MythicRecipeTypes {

    public static RecipeType<MythicUpgradingTableRecipe> UPGRADING;
    public static RecipeSerializer<MythicUpgradingTableRecipe> UPGRADING_SERIALIZER;

    public static final String UPGRADING_ID = "upgrading";

    /**
     * Called before registerSerializer so that the type exists when serializer is built.
     */
    public static void registerType(BiFunction<String, RecipeType<MythicUpgradingTableRecipe>, RecipeType<MythicUpgradingTableRecipe>> reg) {
        UPGRADING = reg.apply(UPGRADING_ID, new RecipeType<MythicUpgradingTableRecipe>() {
            @Override
            public String toString() {
                return Constants.MOD_ID + ":" + UPGRADING_ID;
            }
        });
        Constants.LOG.info("MythicRecipeTypes registered.");
    }

    public static void registerSerializer(BiFunction<String, RecipeSerializer<MythicUpgradingTableRecipe>, RecipeSerializer<MythicUpgradingTableRecipe>> reg) {
        UPGRADING_SERIALIZER = reg.apply(UPGRADING_ID, new MythicUpgradingTableRecipe.Serializer());
        Constants.LOG.info("MythicRecipeSerializers registered.");
    }
}
