package net.trique.mythicupgrades;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.TheEndBiomes;
import net.minecraft.world.level.biome.Biomes;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.trique.mythicupgrades.MythicEffects;
import net.trique.mythicupgrades.MythicSounds;
import net.trique.mythicupgrades.MythicLegacyMigration;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.item.MythicItems;
import net.trique.mythicupgrades.worldgen.CaveGemType;
import net.trique.mythicupgrades.worldgen.MythicBiomes;
import net.trique.mythicupgrades.worldgen.EndGemType;
import net.trique.mythicupgrades.worldgen.MythicFeatures;
import net.trique.mythicupgrades.worldgen.MythicPlacedFeatures;
import net.trique.mythicupgrades.worldgen.NetherGemType;

public class MythicUpgrades implements ModInitializer {

    @Override
    public void onInitialize() {
        MythicBlocks.register((name, block) ->
            Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, name), block));

        MythicBlocks.registerItems((name, item) ->
            Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, name), item));

        MythicItems.register((name, item) ->
            Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, name), item));

        MythicCreativeTabs.register((name, tab) ->
            Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(Constants.MOD_ID, name), tab));

        MythicEffects.register((name, effect) ->
            Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(Constants.MOD_ID, name), effect));

        MythicSounds.register();

        MythicPotions.register((name, potion) ->
            Registry.register(BuiltInRegistries.POTION, new ResourceLocation(Constants.MOD_ID, name), potion));

        MythicFeatures.register((name, feature) ->
            Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(Constants.MOD_ID, name), feature));

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

        TheEndBiomes.addHighlandsBiome(MythicBiomes.AMETRINE_BARRENS, 1.0);
        TheEndBiomes.addMidlandsBiome(Biomes.END_HIGHLANDS, MythicBiomes.JADE_BARRENS, 1.0);
        TheEndBiomes.addMidlandsBiome(MythicBiomes.AMETRINE_BARRENS, MythicBiomes.JADE_BARRENS, 1.0);

        for (EndGemType gem : EndGemType.values()) {
            BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Decoration.UNDERGROUND_ORES, gem.geodePF());
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(gem.endBiome()),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, gem.geodeExtraPF());
        }

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(new ResourceLocation("minecraft", "chests/end_city_treasure"))) {
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
