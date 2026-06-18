package net.trique.mythicupgrades.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.registry.MythicRecipeTypes;

public class MythicUpgradingTableRecipe implements Recipe<Container> {

    private final ResourceLocation id;
    private final Ingredient base;
    private final Ingredient addition;
    private final Ingredient crystal;
    private final ItemStack result;

    public MythicUpgradingTableRecipe(ResourceLocation id, Ingredient base, Ingredient addition,
                                       Ingredient crystal, ItemStack result) {
        this.id = id;
        this.base = base;
        this.addition = addition;
        this.crystal = crystal;
        this.result = result;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return base.test(container.getItem(2))
                && addition.test(container.getItem(3))
                && crystal.test(container.getItem(4));
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return result;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MythicRecipeTypes.UPGRADING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return MythicRecipeTypes.UPGRADING;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(base);
        list.add(addition);
        list.add(crystal);
        return list;
    }

    public Ingredient getBase() { return base; }
    public Ingredient getAddition() { return addition; }
    public Ingredient getCrystal() { return crystal; }

    public static class Serializer implements RecipeSerializer<MythicUpgradingTableRecipe> {

        @Override
        public MythicUpgradingTableRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient base = Ingredient.fromJson(json.get("base"));
            Ingredient addition = Ingredient.fromJson(json.get("addition"));
            Ingredient crystal = Ingredient.fromJson(json.get("crystal"));
            JsonObject resultObj = json.getAsJsonObject("result");
            String resultItem = resultObj.get("item").getAsString();
            ItemStack result = new ItemStack(net.minecraft.core.registries.BuiltInRegistries.ITEM.get(
                    new ResourceLocation(resultItem)));
            return new MythicUpgradingTableRecipe(id, base, addition, crystal, result);
        }

        @Override
        public MythicUpgradingTableRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient base = Ingredient.fromNetwork(buf);
            Ingredient addition = Ingredient.fromNetwork(buf);
            Ingredient crystal = Ingredient.fromNetwork(buf);
            ItemStack result = buf.readItem();
            return new MythicUpgradingTableRecipe(id, base, addition, crystal, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, MythicUpgradingTableRecipe recipe) {
            recipe.base.toNetwork(buf);
            recipe.addition.toNetwork(buf);
            recipe.crystal.toNetwork(buf);
            buf.writeItem(recipe.result);
        }
    }
}
