package net.trique.mythicupgrades;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.trique.mythicupgrades.MythicEffects;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.block.entity.MythicUpgradingTableBlockEntity;
import net.trique.mythicupgrades.item.MythicItems;
import net.trique.mythicupgrades.menu.MythicUpgradingTableMenu;
import net.trique.mythicupgrades.registry.MythicBlockEntityTypes;
import net.trique.mythicupgrades.registry.MythicMenuTypes;
import net.trique.mythicupgrades.registry.MythicRecipeTypes;
import net.trique.mythicupgrades.worldgen.CaveGemType;
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

        MythicBlockEntityTypes.UPGRADING_TABLE = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                new ResourceLocation(Constants.MOD_ID, "mythic_upgrading_table"),
                FabricBlockEntityTypeBuilder.create(MythicUpgradingTableBlockEntity::new,
                        MythicBlocks.MYTHIC_UPGRADING_TABLE).build());
        MythicBlockEntityTypes.onRegistered();

        MythicMenuTypes.UPGRADING_TABLE = ScreenHandlerRegistry.registerSimple(
                new ResourceLocation(Constants.MOD_ID, "mythic_upgrading_table"),
                MythicUpgradingTableMenu::createClientMenu);

        MythicRecipeTypes.registerType((name, type) ->
            Registry.register(BuiltInRegistries.RECIPE_TYPE, new ResourceLocation(Constants.MOD_ID, name), type));

        MythicRecipeTypes.registerSerializer((name, serializer) ->
            Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, new ResourceLocation(Constants.MOD_ID, name), serializer));

        MythicEffects.register((name, effect) ->
            Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(Constants.MOD_ID, name), effect));

        MythicFeatures.register((name, feature) ->
            Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(Constants.MOD_ID, name), feature));

        // FeatureSorter cycle prevention:
        // Cave biome JSONs already contain ALL features (glow_lichen, vanilla ores, monster_room,
        // springs, gem ores) in their bootstrap in vanilla-compatible order.
        // BiomeModifications only adds features that are ALWAYS appended last across ALL overworld
        // biomes — so they can never conflict with bootstrap feature ordering.

        // crystal_buds_rare: appended last in UNDERGROUND_DECORATION for all overworld biomes.
        // In vanilla biomes: bootstrap features come first, then this is appended after.
        // In cave biomes: same — JSON bootstrap first, then this appended after.
        for (String gem : new String[]{"aquamarine", "citrine", "peridot", "topaz"}) {
            ResourceKey<PlacedFeature> key = ResourceKey.create(Registries.PLACED_FEATURE,
                new ResourceLocation(Constants.MOD_ID, gem + "_crystal_buds_rare"));
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, key);
        }

        // necoium: appended last in UNDERGROUND_ORES for all overworld biomes.
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
            GenerationStep.Decoration.UNDERGROUND_ORES, MythicPlacedFeatures.NECOIUM_ORE_PF);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
            GenerationStep.Decoration.UNDERGROUND_ORES, MythicPlacedFeatures.DEEPSLATE_NECOIUM_ORE_PF);

        // overworld geodes: global (all overworld) + extra in home cave biome
        for (CaveGemType gem : CaveGemType.values()) {
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES, gem.geodePF());
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(gem.biome()),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, gem.geodeExtraPF());
        }

        // nether geodes: global (all nether) + extra in home rift biome
        for (NetherGemType gem : NetherGemType.values()) {
            BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES, gem.geodePF());
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(gem.netherBiome()),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, gem.geodeExtraPF());
        }

        // end geodes: global (all end) + extra in home barren biome
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

        CommonClass.init();
    }
}
