package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.worldgen.MythicDamageTypeBootstrap;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MythicDamageTypeTagsProvider extends TagsProvider<DamageType> {

    public MythicDamageTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                        @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.DAMAGE_TYPE, lookupProvider, Constants.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(DamageTypeTags.BYPASSES_ARMOR)
            .addOptional(MythicDamageTypeBootstrap.DEFLECTING.location())
            .addOptional(MythicDamageTypeBootstrap.PERCENTAGE.location())
            .addOptional(MythicDamageTypeBootstrap.TOPAZ_SHOCK.location())
            .addOptional(MythicDamageTypeBootstrap.PERIDOT_INCUBATION.location())
            .addOptional(MythicDamageTypeBootstrap.ICE_SHIELD_MARK_BURST.location())
            .addOptional(MythicDamageTypeBootstrap.ICE_SHIELD_REFLECT.location())
            .addOptional(MythicDamageTypeBootstrap.CITRINE_CHAIN.location());

        tag(DamageTypeTags.BYPASSES_COOLDOWN)
            .addOptional(MythicDamageTypeBootstrap.DEFLECTING.location());

        tag(DamageTypeTags.BYPASSES_EFFECTS)
            .addOptional(MythicDamageTypeBootstrap.DEFLECTING.location())
            .addOptional(MythicDamageTypeBootstrap.PERCENTAGE.location())
            .addOptional(MythicDamageTypeBootstrap.TOPAZ_SHOCK.location())
            .addOptional(MythicDamageTypeBootstrap.PERIDOT_INCUBATION.location())
            .addOptional(MythicDamageTypeBootstrap.ICE_SHIELD_MARK_BURST.location())
            .addOptional(MythicDamageTypeBootstrap.ICE_SHIELD_REFLECT.location())
            .addOptional(MythicDamageTypeBootstrap.CITRINE_CHAIN.location())
            .addOptional(MythicDamageTypeBootstrap.STATIC_FIELD.location());
    }
}
