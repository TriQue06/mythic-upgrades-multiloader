package net.trique.mythicupgrades;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.item.MythicItems;
import net.trique.mythicupgrades.worldgen.MythicFeatures;
import net.trique.mythicupgrades.worldgen.TerraBlenderCompat;

@Mod(Constants.MOD_ID)
public class MythicUpgrades {

    public MythicUpgrades(IEventBus modEventBus) {
        modEventBus.addListener(this::onRegister);
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::onClientSetup);
    }

    private void onRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.BLOCK)) {
            event.register(Registries.BLOCK, helper ->
                MythicBlocks.register((name, block) -> {
                    helper.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), block);
                    return block;
                })
            );
        } else if (event.getRegistryKey().equals(Registries.ITEM)) {
            event.register(Registries.ITEM, helper -> {
                MythicBlocks.registerItems((name, item) -> {
                    helper.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), item);
                    return item;
                });
                MythicItems.register((name, item) -> {
                    helper.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), item);
                    return item;
                });
            });
        } else if (event.getRegistryKey().equals(Registries.CREATIVE_MODE_TAB)) {
            event.register(Registries.CREATIVE_MODE_TAB, helper ->
                MythicCreativeTabs.register((name, tab) -> {
                    helper.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), tab);
                    return tab;
                })
            );
        } else if (event.getRegistryKey().equals(Registries.MOB_EFFECT)) {
            event.register(Registries.MOB_EFFECT, helper ->
                MythicEffects.register((name, effect) -> {
                    helper.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), effect);
                    return effect;
                })
            );
        } else if (event.getRegistryKey().equals(Registries.POTION)) {
            event.register(Registries.POTION, helper ->
                MythicPotions.register((name, potion) -> {
                    helper.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), potion);
                    return potion;
                })
            );
        } else if (event.getRegistryKey().equals(Registries.FEATURE)) {
            event.register(Registries.FEATURE, helper ->
                MythicFeatures.register((name, feature) -> {
                    helper.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), feature);
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
        // TODO phase 2: crystal cluster/bud render types — add "render_type": "minecraft:cutout"
        // to each block model JSON (ItemBlockRenderTypes was removed in NeoForge 1.21.1)
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
    public static class NeoForgeEvents {

        @SubscribeEvent
        public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
            MythicLegacyMigration.migratePlayer(event.getEntity());
            MythicLegacyMigration.drainPendingChunks();
        }

        @SubscribeEvent
        public static void onChunkLoad(ChunkEvent.Load event) {
            if (event.getLevel().isClientSide()) return;
            if (!(event.getChunk() instanceof LevelChunk levelChunk)) return;
            if (!(event.getLevel() instanceof net.minecraft.server.level.ServerLevel serverLevel)) return;
            if (serverLevel.getServer().getTickCount() > 0) {
                MythicLegacyMigration.migrateChunk(levelChunk);
            } else {
                MythicLegacyMigration.PENDING_CHUNKS.offer(levelChunk);
            }
        }
    }
}
