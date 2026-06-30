package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.trique.mythicupgrades.Constants;

public class MythicBiomeModifierBootstrap {

    public static void bootstrap(BootstrapContext<BiomeModifier> ctx) {
        HolderGetter<PlacedFeature> features = ctx.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = ctx.lookup(Registries.BIOME);

        HolderSet<Biome> overworld = biomes.getOrThrow(BiomeTags.IS_OVERWORLD);
        HolderSet<Biome> nether    = biomes.getOrThrow(BiomeTags.IS_NETHER);
        HolderSet<Biome> end       = biomes.getOrThrow(BiomeTags.IS_END);

        // Add all cave gem geodes to the overworld
        ctx.register(key("add_overworld_geodes"),
            new BiomeModifiers.AddFeaturesBiomeModifier(overworld,
                HolderSet.direct(
                    features.getOrThrow(CaveGemType.AQUAMARINE.geodePF()),
                    features.getOrThrow(CaveGemType.CITRINE.geodePF()),
                    features.getOrThrow(CaveGemType.TOPAZ.geodePF()),
                    features.getOrThrow(CaveGemType.PERIDOT.geodePF())
                ),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Add necoium ore to the overworld
        ctx.register(key("add_necoium_ores"),
            new BiomeModifiers.AddFeaturesBiomeModifier(overworld,
                HolderSet.direct(
                    features.getOrThrow(MythicPlacedFeatures.NECOIUM_ORE_PF),
                    features.getOrThrow(MythicPlacedFeatures.DEEPSLATE_NECOIUM_ORE_PF)
                ),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Add crystal bud features to the overworld (rare)
        ctx.register(key("add_crystal_buds_to_overworld"),
            new BiomeModifiers.AddFeaturesBiomeModifier(overworld,
                HolderSet.direct(
                    features.getOrThrow(CaveGemType.AQUAMARINE.crystalBudsRarePF()),
                    features.getOrThrow(CaveGemType.CITRINE.crystalBudsRarePF()),
                    features.getOrThrow(CaveGemType.TOPAZ.crystalBudsRarePF()),
                    features.getOrThrow(CaveGemType.PERIDOT.crystalBudsRarePF())
                ),
                GenerationStep.Decoration.UNDERGROUND_DECORATION));

        // Add end geodes (ametrine, jade) to the end
        ctx.register(key("add_end_geodes"),
            new BiomeModifiers.AddFeaturesBiomeModifier(end,
                HolderSet.direct(
                    features.getOrThrow(EndGemType.AMETRINE.geodePF()),
                    features.getOrThrow(EndGemType.JADE.geodePF())
                ),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Add nether geodes (ruby, sapphire) to the nether
        ctx.register(key("add_nether_geodes"),
            new BiomeModifiers.AddFeaturesBiomeModifier(nether,
                HolderSet.direct(
                    features.getOrThrow(NetherGemType.RUBY.geodePF()),
                    features.getOrThrow(NetherGemType.SAPPHIRE.geodePF())
                ),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Home biome extra geodes for each cave gem
        for (CaveGemType gem : CaveGemType.values()) {
            ctx.register(key("add_" + gem.id + "_geode_extra"),
                new BiomeModifiers.AddFeaturesBiomeModifier(
                    HolderSet.direct(biomes.getOrThrow(gem.biome())),
                    HolderSet.direct(features.getOrThrow(gem.geodeExtraPF())),
                    GenerationStep.Decoration.UNDERGROUND_ORES));
        }

        // Home biome extra geodes for nether gems
        for (NetherGemType gem : NetherGemType.values()) {
            ctx.register(key("add_" + gem.id + "_rift_geode_extra"),
                new BiomeModifiers.AddFeaturesBiomeModifier(
                    HolderSet.direct(biomes.getOrThrow(gem.netherBiome())),
                    HolderSet.direct(features.getOrThrow(gem.geodeExtraPF())),
                    GenerationStep.Decoration.UNDERGROUND_ORES));
        }

        // Home biome extra geodes for end gems
        for (EndGemType gem : EndGemType.values()) {
            ctx.register(key("add_" + gem.id + "_end_geode_extra"),
                new BiomeModifiers.AddFeaturesBiomeModifier(
                    HolderSet.direct(biomes.getOrThrow(gem.endBiome())),
                    HolderSet.direct(features.getOrThrow(gem.geodeExtraPF())),
                    GenerationStep.Decoration.UNDERGROUND_ORES));
        }
    }

    private static ResourceKey<BiomeModifier> key(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }
}
