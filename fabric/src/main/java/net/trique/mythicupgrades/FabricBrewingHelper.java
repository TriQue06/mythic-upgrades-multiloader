package net.trique.mythicupgrades;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.trique.mythicupgrades.item.MythicItems;

// PotionBrewing.addMix is widened by mythicupgrades.accesswidener so Loom can compile
// this direct call and correctly remap it in the production JAR.
// Forge uses BrewingRecipeRegistry instead (PotionBrewing.addMix was removed in 47.3+).
public final class FabricBrewingHelper {

    private FabricBrewingHelper() {}

    public static void register() {
        PotionBrewing.addMix(Potions.AWKWARD, MythicItems.AQUAMARINE_CRYSTAL_SHARD, MythicPotions.ICE_SHIELD);
        PotionBrewing.addMix(MythicPotions.ICE_SHIELD, Items.REDSTONE, MythicPotions.ICE_SHIELD_LONG);
        PotionBrewing.addMix(MythicPotions.ICE_SHIELD, Items.GLOWSTONE_DUST, MythicPotions.ICE_SHIELD_STRONG);

        PotionBrewing.addMix(Potions.AWKWARD, MythicItems.CITRINE_CRYSTAL_SHARD, MythicPotions.STATIC_FIELD);
        PotionBrewing.addMix(MythicPotions.STATIC_FIELD, Items.REDSTONE, MythicPotions.STATIC_FIELD_LONG);
        PotionBrewing.addMix(MythicPotions.STATIC_FIELD, Items.GLOWSTONE_DUST, MythicPotions.STATIC_FIELD_STRONG);

        PotionBrewing.addMix(Potions.AWKWARD, MythicItems.TOPAZ_CRYSTAL_SHARD, MythicPotions.TOPAZ_REACTION);
        PotionBrewing.addMix(MythicPotions.TOPAZ_REACTION, Items.REDSTONE, MythicPotions.TOPAZ_REACTION_LONG);
        PotionBrewing.addMix(MythicPotions.TOPAZ_REACTION, Items.GLOWSTONE_DUST, MythicPotions.TOPAZ_REACTION_STRONG);

        PotionBrewing.addMix(Potions.AWKWARD, MythicItems.PERIDOT_CRYSTAL_SHARD, MythicPotions.MIASMA);
        PotionBrewing.addMix(MythicPotions.MIASMA, Items.REDSTONE, MythicPotions.MIASMA_LONG);
        PotionBrewing.addMix(MythicPotions.MIASMA, Items.GLOWSTONE_DUST, MythicPotions.MIASMA_STRONG);

        PotionBrewing.addMix(Potions.AWKWARD, MythicItems.RUBY_CRYSTAL_SHARD, MythicPotions.BLOOD_THIRST);
        PotionBrewing.addMix(MythicPotions.BLOOD_THIRST, Items.REDSTONE, MythicPotions.BLOOD_THIRST_LONG);
        PotionBrewing.addMix(MythicPotions.BLOOD_THIRST, Items.GLOWSTONE_DUST, MythicPotions.BLOOD_THIRST_STRONG);

        PotionBrewing.addMix(Potions.AWKWARD, MythicItems.SAPPHIRE_CRYSTAL_SHARD, MythicPotions.DAMAGE_DEFLECTION);
        PotionBrewing.addMix(MythicPotions.DAMAGE_DEFLECTION, Items.REDSTONE, MythicPotions.DAMAGE_DEFLECTION_LONG);
        PotionBrewing.addMix(MythicPotions.DAMAGE_DEFLECTION, Items.GLOWSTONE_DUST, MythicPotions.DAMAGE_DEFLECTION_STRONG);

        PotionBrewing.addMix(Potions.AWKWARD, MythicItems.JADE_CRYSTAL_SHARD, MythicPotions.JADE_AURA);
        PotionBrewing.addMix(MythicPotions.JADE_AURA, Items.REDSTONE, MythicPotions.JADE_AURA_LONG);
        PotionBrewing.addMix(MythicPotions.JADE_AURA, Items.GLOWSTONE_DUST, MythicPotions.JADE_AURA_STRONG);

        PotionBrewing.addMix(Potions.AWKWARD, MythicItems.AMETRINE_CRYSTAL_SHARD, MythicPotions.ARCANE_AURA);
        PotionBrewing.addMix(MythicPotions.ARCANE_AURA, Items.REDSTONE, MythicPotions.ARCANE_AURA_LONG);
        PotionBrewing.addMix(MythicPotions.ARCANE_AURA, Items.GLOWSTONE_DUST, MythicPotions.ARCANE_AURA_STRONG);

        PotionBrewing.addMix(Potions.AWKWARD, MythicItems.NECOIUM_INGOT, MythicPotions.NECOIUM_SHARE);
        PotionBrewing.addMix(MythicPotions.NECOIUM_SHARE, Items.REDSTONE, MythicPotions.NECOIUM_SHARE_LONG);

        Constants.LOG.info("MythicPotions brewing recipes registered (Fabric).");
    }
}
