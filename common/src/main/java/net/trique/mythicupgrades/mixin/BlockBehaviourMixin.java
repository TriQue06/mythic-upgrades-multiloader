package net.trique.mythicupgrades.mixin;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.trique.mythicupgrades.item.MythicToolMaterials;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(BlockBehaviour.class)
public abstract class BlockBehaviourMixin {

    @Inject(method = "getDrops", at = @At("RETURN"), cancellable = true)
    private void smeltLoot(BlockState blockState, LootParams.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir) {
        ItemStack tool = builder.getOptionalParameter(LootContextParams.TOOL);
        if (tool == null) return;
        if (!(tool.getItem() instanceof TieredItem tieredItem)) return;
        if (tieredItem.getTier() != MythicToolMaterials.TOPAZ) return;

        List<ItemStack> original = cir.getReturnValue();

        List<Pair<ItemStack, Float>> list = original.stream().map(stack -> {
            var result = builder.getLevel().getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), builder.getLevel());
            return result.map(recipe -> {
                ItemStack smelted = recipe.getResultItem(builder.getLevel().registryAccess()).copy();
                smelted.setCount(stack.getCount() * smelted.getCount());
                float exp = ((AbstractCookingRecipe) recipe).getExperience();
                return Pair.of(smelted, exp);
            }).filter(pair -> !pair.getLeft().isEmpty()).orElse(Pair.of(stack, 0.0F));
        }).toList();

        float xp = (float) list.stream().mapToDouble(Pair::getRight).sum();
        if (xp > 0.0F && builder.getOptionalParameter(LootContextParams.THIS_ENTITY) != null) {
            ExperienceOrb.award(builder.getLevel(), builder.getParameter(LootContextParams.THIS_ENTITY).position(), Math.round(xp));
        }

        cir.setReturnValue(list.stream().map(Pair::getLeft).collect(Collectors.toCollection(ObjectArrayList::new)));
    }
}
