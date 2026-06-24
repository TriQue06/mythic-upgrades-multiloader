package net.trique.mythicupgrades;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import net.trique.mythicupgrades.MythicEffects;
import net.trique.mythicupgrades.MythicLegacyMigration;
import net.trique.mythicupgrades.MythicPotions;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.item.MythicItems;
import net.trique.mythicupgrades.worldgen.MythicFeatures;
import net.trique.mythicupgrades.worldgen.TerraBlenderCompat;

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
        } else if (event.getRegistryKey().equals(Registries.MOB_EFFECT)) {
            event.register(Registries.MOB_EFFECT, helper ->
                MythicEffects.register((name, effect) -> {
                    helper.register(new ResourceLocation(Constants.MOD_ID, name), effect);
                    return effect;
                })
            );
        } else if (event.getRegistryKey().equals(Registries.POTION)) {
            event.register(Registries.POTION, helper ->
                MythicPotions.register((name, potion) -> {
                    helper.register(new ResourceLocation(Constants.MOD_ID, name), potion);
                    return potion;
                })
            );
        } else if (event.getRegistryKey().equals(Registries.FEATURE)) {
            event.register(Registries.FEATURE, helper ->
                MythicFeatures.register((name, feature) -> {
                    helper.register(new ResourceLocation(Constants.MOD_ID, name), feature);
                    return feature;
                })
            );
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            if (ModList.get().isLoaded("terrablender")) {
                TerraBlenderCompat.init();
            }
            MythicPotions.registerBrewingRecipes();
        });
        CommonClass.init();
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            registerCutoutRenderLayers();
        });
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
            MythicLegacyMigration.migratePlayer(event.getEntity());
        }

        @SubscribeEvent
        public static void onChunkLoad(ChunkEvent.Load event) {
            if (!event.getLevel().isClientSide() && event.getChunk() instanceof LevelChunk levelChunk) {
                MythicLegacyMigration.migrateChunk(levelChunk);
            }
        }

        @SubscribeEvent
        public static void onLootTableLoad(LootTableLoadEvent event) {
            if (event.getName().equals(new ResourceLocation("chests/end_city_treasure"))) {
                event.getTable().addPool(
                    LootPool.lootPool()
                        .add(LootItem.lootTableItem(MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE)
                            .when(LootItemRandomChanceCondition.randomChance(0.25f)))
                        .build()
                );
            }
        }
    }

    private static void registerCutoutRenderLayers() {
        RenderType cutout = RenderType.cutout();
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.CITRINE_CRYSTAL_CLUSTER, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.TOPAZ_CRYSTAL_CLUSTER, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.PERIDOT_CRYSTAL_CLUSTER, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.RUBY_CRYSTAL_CLUSTER, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SAPPHIRE_CRYSTAL_CLUSTER, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.JADE_CRYSTAL_CLUSTER, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.AMETRINE_CRYSTAL_CLUSTER, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_CITRINE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_TOPAZ_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_PERIDOT_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_RUBY_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_SAPPHIRE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_JADE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.LARGE_AMETRINE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_RUBY_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_JADE_CRYSTAL_BUD, cutout);
        ItemBlockRenderTypes.setRenderLayer(MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD, cutout);
    }
}
