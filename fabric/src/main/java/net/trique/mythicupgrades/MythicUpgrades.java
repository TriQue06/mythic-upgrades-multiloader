package net.trique.mythicupgrades;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.trique.mythicupgrades.MythicEffects;
import net.trique.mythicupgrades.MythicLegacyMigration;
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
        MythicEffects.register((name, effect) -> {
            ResourceKey<MobEffect> key = ResourceKey.create(
                BuiltInRegistries.MOB_EFFECT.key(),
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
            return ((WritableRegistry<MobEffect>) BuiltInRegistries.MOB_EFFECT)
                .register(key, effect, RegistrationInfo.BUILT_IN);
        });

        MythicBlocks.register((name, block) ->
            Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), block));

        MythicBlocks.registerItems((name, item) ->
            Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), item));

        MythicItems.register((name, item) ->
            Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), item));

        MythicCreativeTabs.register((name, tab) ->
            Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), tab));

        MythicPotions.register((name, potion) ->
            Registry.register(BuiltInRegistries.POTION, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), potion));

        MythicFeatures.register((name, feature) ->
            Registry.register(BuiltInRegistries.FEATURE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), feature));

        MythicSounds.register((name, sound) ->
            Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), sound));

        for (CaveGemType gem : CaveGemType.values()) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(gem.biome()),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, gem.crystalBudsRarePF());
        }

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
            GenerationStep.Decoration.UNDERGROUND_ORES, MythicPlacedFeatures.NECOIUM_ORE_PF);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
            GenerationStep.Decoration.UNDERGROUND_ORES, MythicPlacedFeatures.DEEPSLATE_NECOIUM_ORE_PF);

        for (CaveGemType gem : CaveGemType.values()) {
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, gem.geodePF());
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(gem.biome()),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, gem.geodeExtraPF());
        }

        for (NetherGemType gem : NetherGemType.values()) {
            BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, gem.geodePF());
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(gem.netherBiome()),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, gem.geodeExtraPF());
        }

        for (EndGemType gem : EndGemType.values()) {
            BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Decoration.UNDERGROUND_ORES, gem.geodePF());
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(gem.endBiome()),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, gem.geodeExtraPF());
        }

        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (key.location().equals(ResourceLocation.withDefaultNamespace("chests/end_city_treasure"))) {
                tableBuilder.withPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE)
                            .when(LootItemRandomChanceCondition.randomChance(0.25f)))
                );
            }
        });

        FabricBrewingHelper.register();

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            MythicLegacyMigration.migratePlayer(handler.player);
        });

        ServerChunkEvents.CHUNK_LOAD.register((world, chunk) ->
            MythicLegacyMigration.PENDING_CHUNKS.offer(chunk));

        ServerTickEvents.END_SERVER_TICK.register(server ->
            MythicLegacyMigration.drainPendingChunks());

        CommonClass.init();
    }
}
