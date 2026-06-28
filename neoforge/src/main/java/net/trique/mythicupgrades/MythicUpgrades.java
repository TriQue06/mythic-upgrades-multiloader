package net.trique.mythicupgrades;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
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
        } else if (event.getRegistryKey().equals(Registries.SOUND_EVENT)) {
            event.register(Registries.SOUND_EVENT, helper ->
                MythicSounds.register((name, sound) -> {
                    helper.register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), sound);
                    return sound;
                })
            );
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            if (ModList.get().isLoaded("terrablender")) {
                TerraBlenderCompat.init();
            }
        });
        CommonClass.init();
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        // Crystal cluster/bud render types are set via "render_type": "minecraft:cutout"
        // in each block model JSON (ItemBlockRenderTypes was removed in NeoForge 1.21.1)
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
    public static class NeoForgeEvents {

        @SubscribeEvent
        public static void onBrewingRecipes(RegisterBrewingRecipesEvent event) {
            var builder = event.getBuilder();

            // Aquamarine → Ice Shield
            builder.addMix(Potions.AWKWARD,                                             MythicItems.AQUAMARINE,    BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD),          Items.REDSTONE,          BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD),          Items.GLOWSTONE_DUST,    BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD_STRONG));

            // Citrine → Static Field
            builder.addMix(Potions.AWKWARD,                                             MythicItems.CITRINE,       BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD),        Items.REDSTONE,          BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD),        Items.GLOWSTONE_DUST,    BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD_STRONG));

            // Topaz → Topaz Reaction
            builder.addMix(Potions.AWKWARD,                                             MythicItems.TOPAZ,         BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION),      Items.REDSTONE,          BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION),      Items.GLOWSTONE_DUST,    BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION_STRONG));

            // Peridot → Miasma
            builder.addMix(Potions.AWKWARD,                                             MythicItems.PERIDOT,       BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA),              Items.REDSTONE,          BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA),              Items.GLOWSTONE_DUST,    BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA_STRONG));

            // Ruby → Blood Thirst
            builder.addMix(Potions.AWKWARD,                                             MythicItems.RUBY,          BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST),        Items.REDSTONE,          BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST),        Items.GLOWSTONE_DUST,    BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST_STRONG));

            // Sapphire → Damage Deflection
            builder.addMix(Potions.AWKWARD,                                             MythicItems.SAPPHIRE,      BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION),   Items.REDSTONE,          BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION),   Items.GLOWSTONE_DUST,    BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION_STRONG));

            // Jade → Jade Aura
            builder.addMix(Potions.AWKWARD,                                             MythicItems.JADE,          BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA),           Items.REDSTONE,          BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA),           Items.GLOWSTONE_DUST,    BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA_STRONG));

            // Ametrine → Arcane Aura
            builder.addMix(Potions.AWKWARD,                                             MythicItems.AMETRINE,      BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA),         Items.REDSTONE,          BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA),         Items.GLOWSTONE_DUST,    BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA_STRONG));

            // Necoium → Necoium Share
            builder.addMix(Potions.AWKWARD,                                             MythicItems.NECOIUM_INGOT, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.NECOIUM_SHARE));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.NECOIUM_SHARE),       Items.REDSTONE,          BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.NECOIUM_SHARE_LONG));
        }

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
