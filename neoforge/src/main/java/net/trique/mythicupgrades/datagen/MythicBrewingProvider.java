package net.trique.mythicupgrades.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.BrewingProvider;
import net.minecraft.data.recipes.BrewingRecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.MythicPotions;
import net.trique.mythicupgrades.item.MythicItems;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Brewing is data-driven since 26.3: every mix and every container
 * transformation is its own recipe, shared by both loaders through the
 * generated resources.
 */
public class MythicBrewingProvider extends BrewingProvider {

    private final RecipeOutput output;
    private final Set<Holder<Potion>> modPotions = new LinkedHashSet<>();

    public MythicBrewingProvider(RecipeOutput output) {
        super(output);
        this.output = output;
    }

    @Override
    protected void addContainers() {
        this.addContainer(Items.POTION);
        this.addContainer(Items.SPLASH_POTION);
        this.addContainer(Items.LINGERING_POTION);
    }

    @Override
    protected void addContainerTransformations() {
        // Vanilla's provider only emits transformations for vanilla potions, and the
        // base implementation here would re-emit them for every input potion we touch
        // (awkward included). Ours are generated for mod potions only, in buildMixes().
    }

    @Override
    protected void buildMixes() {
        mix(Potions.AWKWARD, MythicItems.AQUAMARINE_CRYSTAL_SHARD, MythicPotions.ICE_SHIELD);
        mix(MythicPotions.ICE_SHIELD, Items.REDSTONE, MythicPotions.ICE_SHIELD_LONG);
        mix(MythicPotions.ICE_SHIELD, Items.GLOWSTONE_DUST, MythicPotions.ICE_SHIELD_STRONG);

        mix(Potions.AWKWARD, MythicItems.CITRINE_CRYSTAL_SHARD, MythicPotions.STATIC_FIELD);
        mix(MythicPotions.STATIC_FIELD, Items.REDSTONE, MythicPotions.STATIC_FIELD_LONG);
        mix(MythicPotions.STATIC_FIELD, Items.GLOWSTONE_DUST, MythicPotions.STATIC_FIELD_STRONG);

        mix(Potions.AWKWARD, MythicItems.TOPAZ_CRYSTAL_SHARD, MythicPotions.TOPAZ_REACTION);
        mix(MythicPotions.TOPAZ_REACTION, Items.REDSTONE, MythicPotions.TOPAZ_REACTION_LONG);
        mix(MythicPotions.TOPAZ_REACTION, Items.GLOWSTONE_DUST, MythicPotions.TOPAZ_REACTION_STRONG);

        mix(Potions.AWKWARD, MythicItems.PERIDOT_CRYSTAL_SHARD, MythicPotions.MIASMA);
        mix(MythicPotions.MIASMA, Items.REDSTONE, MythicPotions.MIASMA_LONG);
        mix(MythicPotions.MIASMA, Items.GLOWSTONE_DUST, MythicPotions.MIASMA_STRONG);

        mix(Potions.AWKWARD, MythicItems.RUBY_CRYSTAL_SHARD, MythicPotions.BLOOD_THIRST);
        mix(MythicPotions.BLOOD_THIRST, Items.REDSTONE, MythicPotions.BLOOD_THIRST_LONG);
        mix(MythicPotions.BLOOD_THIRST, Items.GLOWSTONE_DUST, MythicPotions.BLOOD_THIRST_STRONG);

        mix(Potions.AWKWARD, MythicItems.SAPPHIRE_CRYSTAL_SHARD, MythicPotions.DAMAGE_DEFLECTION);
        mix(MythicPotions.DAMAGE_DEFLECTION, Items.REDSTONE, MythicPotions.DAMAGE_DEFLECTION_LONG);
        mix(MythicPotions.DAMAGE_DEFLECTION, Items.GLOWSTONE_DUST, MythicPotions.DAMAGE_DEFLECTION_STRONG);

        mix(Potions.AWKWARD, MythicItems.JADE_CRYSTAL_SHARD, MythicPotions.JADE_AURA);
        mix(MythicPotions.JADE_AURA, Items.REDSTONE, MythicPotions.JADE_AURA_LONG);
        mix(MythicPotions.JADE_AURA, Items.GLOWSTONE_DUST, MythicPotions.JADE_AURA_STRONG);

        mix(Potions.AWKWARD, MythicItems.AMETRINE_CRYSTAL_SHARD, MythicPotions.ARCANE_AURA);
        mix(MythicPotions.ARCANE_AURA, Items.REDSTONE, MythicPotions.ARCANE_AURA_LONG);
        mix(MythicPotions.ARCANE_AURA, Items.GLOWSTONE_DUST, MythicPotions.ARCANE_AURA_STRONG);

        mix(Potions.AWKWARD, MythicItems.NECOIUM_INGOT, MythicPotions.NECOIUM_SHARE);
        mix(MythicPotions.NECOIUM_SHARE, Items.REDSTONE, MythicPotions.NECOIUM_SHARE_LONG);

        mix(MythicPotions.ICE_SHIELD, Items.FERMENTED_SPIDER_EYE, MythicPotions.ICE_BOMB);
        mix(MythicPotions.ICE_BOMB, Items.REDSTONE, MythicPotions.ICE_BOMB_LONG);
        mix(MythicPotions.ICE_BOMB, Items.GLOWSTONE_DUST, MythicPotions.ICE_BOMB_STRONG);

        mix(MythicPotions.ICE_BOMB, Items.FERMENTED_SPIDER_EYE, MythicPotions.FREEZE);
        mix(MythicPotions.FREEZE, Items.REDSTONE, MythicPotions.FREEZE_LONG);
        mix(MythicPotions.FREEZE, Items.GLOWSTONE_DUST, MythicPotions.FREEZE_STRONG);

        mix(MythicPotions.STATIC_FIELD, Items.FERMENTED_SPIDER_EYE, MythicPotions.CHARGED);
        mix(MythicPotions.CHARGED, Items.REDSTONE, MythicPotions.CHARGED_LONG);
        mix(MythicPotions.CHARGED, Items.GLOWSTONE_DUST, MythicPotions.CHARGED_STRONG);

        mix(MythicPotions.MIASMA, Items.FERMENTED_SPIDER_EYE, MythicPotions.LETHAL_INCUBATION);
        mix(MythicPotions.LETHAL_INCUBATION, Items.REDSTONE, MythicPotions.LETHAL_INCUBATION_LONG);
        mix(MythicPotions.LETHAL_INCUBATION, Items.GLOWSTONE_DUST, MythicPotions.LETHAL_INCUBATION_STRONG);

        for (Holder<Potion> potion : modPotions) {
            save(BrewingRecipeBuilder.brewingContainerTransform(Items.POTION, potion, Items.GUNPOWDER, Items.SPLASH_POTION));
            save(BrewingRecipeBuilder.brewingContainerTransform(Items.SPLASH_POTION, potion, Items.DRAGON_BREATH, Items.LINGERING_POTION));
        }
    }

    private void mix(Holder<Potion> input, Item reagent, Potion output) {
        mix(input, reagent, holder(output));
    }

    private void mix(Potion input, Item reagent, Potion output) {
        mix(holder(input), reagent, holder(output));
    }

    private void mix(Holder<Potion> input, Item reagent, Holder<Potion> output) {
        this.buildMix(input, reagent, output);
        if (isModPotion(input)) modPotions.add(input);
        if (isModPotion(output)) modPotions.add(output);
    }

    private static Holder<Potion> holder(Potion potion) {
        return BuiltInRegistries.POTION.wrapAsHolder(potion);
    }

    private static boolean isModPotion(Holder<Potion> potion) {
        return potion.unwrapKey().map(k -> k.identifier().getNamespace().equals(Constants.MOD_ID)).orElse(false);
    }

    /** Recipe ids default to the container's namespace ({@code minecraft}); keep ours in our own. */
    @Override
    protected void save(BrewingRecipeBuilder builder) {
        Identifier id = Identifier.fromNamespaceAndPath(Constants.MOD_ID, builder.defaultId().identifier().getPath());
        builder.save(this.output, ResourceKey.create(Registries.RECIPE, id));
    }
}
