package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.worldgen.MythicDamageTypeBootstrap;

import java.util.concurrent.CompletableFuture;

public class MythicDamageTypeTagsProvider extends TagsProvider<DamageType> {

    public MythicDamageTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.DAMAGE_TYPE, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(DamageTypeTags.BYPASSES_ARMOR)
            .addOptional(MythicDamageTypeBootstrap.DEFLECTING)
            .addOptional(MythicDamageTypeBootstrap.PERCENTAGE)
            .addOptional(MythicDamageTypeBootstrap.TOPAZ_SHOCK)
            .addOptional(MythicDamageTypeBootstrap.PERIDOT_INCUBATION)
            .addOptional(MythicDamageTypeBootstrap.ICE_SHIELD_REFLECT)
            .addOptional(MythicDamageTypeBootstrap.CITRINE_CHAIN);

        tag(DamageTypeTags.BYPASSES_COOLDOWN)
            .addOptional(MythicDamageTypeBootstrap.DEFLECTING);

        tag(DamageTypeTags.BYPASSES_EFFECTS)
            .addOptional(MythicDamageTypeBootstrap.DEFLECTING)
            .addOptional(MythicDamageTypeBootstrap.PERCENTAGE)
            .addOptional(MythicDamageTypeBootstrap.TOPAZ_SHOCK)
            .addOptional(MythicDamageTypeBootstrap.PERIDOT_INCUBATION)
            .addOptional(MythicDamageTypeBootstrap.ICE_SHIELD_REFLECT)
            .addOptional(MythicDamageTypeBootstrap.CITRINE_CHAIN)
            .addOptional(MythicDamageTypeBootstrap.STATIC_FIELD);
    }
}
