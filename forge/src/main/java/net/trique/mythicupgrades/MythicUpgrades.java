package net.trique.mythicupgrades;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.block.entity.MythicUpgradingTableBlockEntity;
import net.trique.mythicupgrades.client.screen.MythicUpgradingTableScreen;
import net.trique.mythicupgrades.item.MythicItems;
import net.trique.mythicupgrades.menu.MythicUpgradingTableMenu;
import net.trique.mythicupgrades.registry.MythicBlockEntityTypes;
import net.trique.mythicupgrades.registry.MythicMenuTypes;
import net.trique.mythicupgrades.registry.MythicRecipeTypes;

@Mod(Constants.MOD_ID)
public class MythicUpgrades {

    public MythicUpgrades() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::onRegister);
        bus.addListener(this::onCommonSetup);
        bus.addListener(this::onClientSetup);
    }

    private void onRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.BLOCK)) {
            event.register(Registries.BLOCK, helper ->
                MythicBlocks.register((name, block) -> {
                    helper.register(new ResourceLocation(Constants.MOD_ID, name), block);
                    return block;
                })
            );
        } else if (event.getRegistryKey().equals(Registries.ITEM)) {
            event.register(Registries.ITEM, helper -> {
                MythicBlocks.registerItems((name, item) -> {
                    helper.register(new ResourceLocation(Constants.MOD_ID, name), item);
                    return item;
                });
                MythicItems.register((name, item) -> {
                    helper.register(new ResourceLocation(Constants.MOD_ID, name), item);
                    return item;
                });
            });
        } else if (event.getRegistryKey().equals(Registries.CREATIVE_MODE_TAB)) {
            event.register(Registries.CREATIVE_MODE_TAB, helper ->
                MythicCreativeTabs.register((name, tab) -> {
                    helper.register(new ResourceLocation(Constants.MOD_ID, name), tab);
                    return tab;
                })
            );
        } else if (event.getRegistryKey().equals(Registries.BLOCK_ENTITY_TYPE)) {
            event.register(Registries.BLOCK_ENTITY_TYPE, helper -> {
                // On Forge, BlockEntityType.BlockEntitySupplier is public via ATs
                BlockEntityType<MythicUpgradingTableBlockEntity> type =
                        BlockEntityType.Builder.of(
                                MythicUpgradingTableBlockEntity::new,
                                MythicBlocks.MYTHIC_UPGRADING_TABLE
                        ).build(null);
                helper.register(new ResourceLocation(Constants.MOD_ID, "mythic_upgrading_table"), type);
                MythicBlockEntityTypes.UPGRADING_TABLE = type;
                MythicBlockEntityTypes.onRegistered();
            });
        } else if (event.getRegistryKey().equals(Registries.MENU)) {
            event.register(Registries.MENU, helper -> {
                // On Forge, MenuType.MenuSupplier is public via ATs; constructor needs FeatureFlagSet
                MenuType<MythicUpgradingTableMenu> type =
                        new MenuType<>(MythicUpgradingTableMenu::createClientMenu, FeatureFlags.VANILLA_SET);
                helper.register(new ResourceLocation(Constants.MOD_ID, "mythic_upgrading_table"), type);
                MythicMenuTypes.UPGRADING_TABLE = type;
            });
        } else if (event.getRegistryKey().equals(Registries.RECIPE_TYPE)) {
            event.register(Registries.RECIPE_TYPE, helper ->
                MythicRecipeTypes.registerType((name, type) -> {
                    helper.register(new ResourceLocation(Constants.MOD_ID, name), type);
                    return type;
                })
            );
        } else if (event.getRegistryKey().equals(Registries.RECIPE_SERIALIZER)) {
            event.register(Registries.RECIPE_SERIALIZER, helper ->
                MythicRecipeTypes.registerSerializer((name, serializer) -> {
                    helper.register(new ResourceLocation(Constants.MOD_ID, name), serializer);
                    return serializer;
                })
            );
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        CommonClass.init();
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(MythicMenuTypes.UPGRADING_TABLE, MythicUpgradingTableScreen::new);
            registerCutoutRenderLayers();
        });
    }

    private static void registerCutoutRenderLayers() {
        RenderType cutout = RenderType.cutout();
        // Clusters
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER,  cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.KYANITE_CRYSTAL_CLUSTER,     cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.CITRINE_CRYSTAL_CLUSTER,     cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.TOPAZ_CRYSTAL_CLUSTER,       cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.PERIDOT_CRYSTAL_CLUSTER,     cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.RUBY_CRYSTAL_CLUSTER,        cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SAPPHIRE_CRYSTAL_CLUSTER,    cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.JADE_CRYSTAL_CLUSTER,        cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.AMETRINE_CRYSTAL_CLUSTER,    cutout);
        // Large buds
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD,  cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_KYANITE_CRYSTAL_BUD,     cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_CITRINE_CRYSTAL_BUD,     cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_TOPAZ_CRYSTAL_BUD,       cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_PERIDOT_CRYSTAL_BUD,     cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_RUBY_CRYSTAL_BUD,        cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_SAPPHIRE_CRYSTAL_BUD,    cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_JADE_CRYSTAL_BUD,        cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_AMETRINE_CRYSTAL_BUD,    cutout);
        // Medium buds
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD,  cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_KYANITE_CRYSTAL_BUD,     cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD,     cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD,       cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD,     cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD,        cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD,    cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD,        cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD,    cutout);
        // Small buds
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD,  cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_KYANITE_CRYSTAL_BUD,     cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD,     cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD,       cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD,     cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_RUBY_CRYSTAL_BUD,        cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD,    cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_JADE_CRYSTAL_BUD,        cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD,    cutout);
    }
}
