package net.trique.mythicupgrades;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.trique.mythicupgrades.MythicEffects;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.block.entity.MythicUpgradingTableBlockEntity;
import net.trique.mythicupgrades.item.MythicItems;
import net.trique.mythicupgrades.menu.MythicUpgradingTableMenu;
import net.trique.mythicupgrades.registry.MythicBlockEntityTypes;
import net.trique.mythicupgrades.registry.MythicMenuTypes;
import net.trique.mythicupgrades.registry.MythicRecipeTypes;
import net.trique.mythicupgrades.worldgen.MythicFeatures;
import net.trique.mythicupgrades.worldgen.TerraBlenderCompat;

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

        if (FabricLoader.getInstance().isModLoaded("terrablender")) {
            TerraBlenderCompat.init();
        }

        // Rare crystal buds in all overworld underground areas
        for (String gem : new String[]{"aquamarine", "citrine", "peridot", "topaz"}) {
            ResourceKey<PlacedFeature> key = ResourceKey.create(Registries.PLACED_FEATURE,
                new ResourceLocation(Constants.MOD_ID, gem + "_crystal_buds_rare"));
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_DECORATION, key);
        }

        CommonClass.init();
    }
}
