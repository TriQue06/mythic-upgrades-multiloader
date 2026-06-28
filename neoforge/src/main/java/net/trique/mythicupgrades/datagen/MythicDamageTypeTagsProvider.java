package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.tags.TagsProvider;
import net.trique.mythicupgrades.Constants;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MythicDamageTypeTagsProvider extends TagsProvider<DamageType> {

    private static final ResourceLocation DEFLECTING            = rl("deflecting_damage_type");
    private static final ResourceLocation PERCENTAGE            = rl("percentage_damage_type");
    private static final ResourceLocation TOPAZ_SHOCK           = rl("topaz_shock_damage_type");
    private static final ResourceLocation PERIDOT_INCUBATION    = rl("peridot_incubation_damage_type");
    private static final ResourceLocation ICE_SHIELD_MARK_BURST = rl("ice_shield_mark_burst_damage_type");
    private static final ResourceLocation ICE_SHIELD_REFLECT    = rl("ice_shield_reflect_damage_type");
    private static final ResourceLocation CITRINE_CHAIN         = rl("citrine_chain_damage_type");
    private static final ResourceLocation STATIC_FIELD          = rl("static_field_damage_type");

    public MythicDamageTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                        @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.DAMAGE_TYPE, lookupProvider, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(DamageTypeTags.BYPASSES_ARMOR)
            .addOptional(DEFLECTING)
            .addOptional(PERCENTAGE)
            .addOptional(TOPAZ_SHOCK)
            .addOptional(PERIDOT_INCUBATION)
            .addOptional(ICE_SHIELD_MARK_BURST)
            .addOptional(ICE_SHIELD_REFLECT)
            .addOptional(CITRINE_CHAIN);

        tag(DamageTypeTags.BYPASSES_COOLDOWN)
            .addOptional(DEFLECTING);

        tag(DamageTypeTags.BYPASSES_EFFECTS)
            .addOptional(DEFLECTING)
            .addOptional(PERCENTAGE)
            .addOptional(TOPAZ_SHOCK)
            .addOptional(PERIDOT_INCUBATION)
            .addOptional(ICE_SHIELD_MARK_BURST)
            .addOptional(ICE_SHIELD_REFLECT)
            .addOptional(CITRINE_CHAIN)
            .addOptional(STATIC_FIELD);
    }

    private static ResourceLocation rl(String name) {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name);
    }
}
