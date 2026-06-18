package net.trique.mythicupgrades;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.block.entity.MythicUpgradingTableBlockEntity;
import net.trique.mythicupgrades.item.MythicItems;
import net.trique.mythicupgrades.menu.MythicUpgradingTableMenu;
import net.trique.mythicupgrades.registry.MythicBlockEntityTypes;
import net.trique.mythicupgrades.registry.MythicMenuTypes;
import net.trique.mythicupgrades.registry.MythicRecipeTypes;

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

        // Block entity type (Fabric uses FabricBlockEntityTypeBuilder which exposes a public factory)
        MythicBlockEntityTypes.UPGRADING_TABLE = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                new ResourceLocation(Constants.MOD_ID, "mythic_upgrading_table"),
                FabricBlockEntityTypeBuilder.create(MythicUpgradingTableBlockEntity::new,
                        MythicBlocks.MYTHIC_UPGRADING_TABLE).build());
        MythicBlockEntityTypes.onRegistered();

        // Menu type (Fabric uses ScreenHandlerRegistry which bypasses the package-private constructor)
        MythicMenuTypes.UPGRADING_TABLE = ScreenHandlerRegistry.registerSimple(
                new ResourceLocation(Constants.MOD_ID, "mythic_upgrading_table"),
                MythicUpgradingTableMenu::createClientMenu);

        MythicRecipeTypes.registerType((name, type) ->
            Registry.register(BuiltInRegistries.RECIPE_TYPE, new ResourceLocation(Constants.MOD_ID, name), type));

        MythicRecipeTypes.registerSerializer((name, serializer) ->
            Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, new ResourceLocation(Constants.MOD_ID, name), serializer));

        CommonClass.init();
    }
}
