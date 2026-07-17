package net.trique.mythicupgrades;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.trique.mythicupgrades.MythicEffects;
import net.trique.mythicupgrades.MythicPotions;
import net.trique.mythicupgrades.MythicSounds;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.item.MythicItems;
import net.trique.mythicupgrades.worldgen.CaveGemType;
import net.trique.mythicupgrades.worldgen.EndGemType;
import net.trique.mythicupgrades.worldgen.MythicFeatures;
import net.trique.mythicupgrades.worldgen.MythicPlacedFeatures;
import net.trique.mythicupgrades.worldgen.NetherGemType;

public class MythicUpgrades implements ModInitializer {

    @Override
    public void onInitialize() {
        MythicConfig.load(net.fabricmc.loader.api.FabricLoader.getInstance().getConfigDir());

        MythicEffects.register((name, effect) -> {
            ResourceKey<MobEffect> key = ResourceKey.create(
                BuiltInRegistries.MOB_EFFECT.key(),
                Identifier.fromNamespaceAndPath(Constants.MOD_ID, name));
            return ((WritableRegistry<MobEffect>) BuiltInRegistries.MOB_EFFECT)
                .register(key, effect, RegistrationInfo.BUILT_IN);
        });

        MythicBlocks.register((name, block) ->
            Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), block));

        MythicBlocks.registerItems((name, item) ->
            Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), item));

        MythicItems.register((name, item) ->
            Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), item));

        MythicCreativeTabs.register((name, tab) ->
            Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), tab));

        MythicPotions.register((name, potion) ->
            Registry.register(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), potion));

        MythicFeatures.register((name, feature) ->
            Registry.register(BuiltInRegistries.FEATURE, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), feature));

        MythicSounds.register((name, sound) ->
            Registry.register(BuiltInRegistries.SOUND_EVENT, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), sound));

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
            GenerationStep.Decoration.UNDERGROUND_ORES, MythicPlacedFeatures.NECOIUM_ORE_PF);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
            GenerationStep.Decoration.UNDERGROUND_ORES, MythicPlacedFeatures.DEEPSLATE_NECOIUM_ORE_PF);

        for (CaveGemType gem : CaveGemType.values()) {
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, gem.geodePF());
        }

        for (NetherGemType gem : NetherGemType.values()) {
            BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, gem.geodePF());
        }

        for (EndGemType gem : EndGemType.values()) {
            BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Decoration.UNDERGROUND_ORES, gem.geodePF());
        }

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (key.identifier().equals(Identifier.withDefaultNamespace("chests/end_city_treasure"))) {
                tableBuilder.withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE)
                            .when(LootItemRandomChanceCondition.randomChance(0.25f)))
                );
            }
        });

        FabricBrewingHelper.register();

        CommonClass.init();
    }
}
