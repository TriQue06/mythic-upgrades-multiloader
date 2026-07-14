package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.trique.mythicupgrades.Constants;

public class MythicBiomeModifierBootstrap {

    private static ResourceKey<BiomeModifier> key(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MOD_ID, name));
    }

    private static ResourceKey<PlacedFeature> pf(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MOD_ID, name));
    }

    public static void bootstrap(BootstapContext<BiomeModifier> ctx) {
        var pfLookup = ctx.lookup(Registries.PLACED_FEATURE);
        var biomeLookup = ctx.lookup(Registries.BIOME);

        var overworld = biomeLookup.getOrThrow(BiomeTags.IS_OVERWORLD);
        var end      = biomeLookup.getOrThrow(BiomeTags.IS_END);
        var nether   = biomeLookup.getOrThrow(BiomeTags.IS_NETHER);

        ctx.register(key("add_overworld_geodes"), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
            overworld,
            HolderSet.direct(
                pfLookup.getOrThrow(pf("aquamarine_geode")),
                pfLookup.getOrThrow(pf("citrine_geode")),
                pfLookup.getOrThrow(pf("peridot_geode")),
                pfLookup.getOrThrow(pf("topaz_geode"))
            ),
            GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        ctx.register(key("add_necoium_ores"), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
            overworld,
            HolderSet.direct(
                pfLookup.getOrThrow(pf("necoium_ore")),
                pfLookup.getOrThrow(pf("deepslate_necoium_ore"))
            ),
            GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        ctx.register(key("add_end_geodes"), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
            end,
            HolderSet.direct(
                pfLookup.getOrThrow(pf("ametrine_geode")),
                pfLookup.getOrThrow(pf("jade_geode"))
            ),
            GenerationStep.Decoration.UNDERGROUND_ORES
        ));

        ctx.register(key("add_nether_geodes"), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
            nether,
            HolderSet.direct(
                pfLookup.getOrThrow(pf("ruby_geode")),
                pfLookup.getOrThrow(pf("sapphire_geode"))
            ),
            GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }
}
