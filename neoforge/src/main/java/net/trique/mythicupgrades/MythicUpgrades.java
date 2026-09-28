package net.trique.mythicupgrades;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.item.MythicItems;
import net.trique.mythicupgrades.worldgen.MythicFeatures;

@Mod(Constants.MOD_ID)
public class MythicUpgrades {

    public MythicUpgrades(IEventBus modEventBus, net.neoforged.fml.ModContainer container) {
        MythicConfig.load(net.neoforged.fml.loading.FMLPaths.CONFIGDIR.get());

        modEventBus.addListener(this::onRegister);
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::onClientSetup);

        if (net.neoforged.fml.loading.FMLEnvironment.getDist().isClient()) {
            container.registerExtensionPoint(
                net.neoforged.neoforge.client.gui.IConfigScreenFactory.class,
                (modContainer, parent) -> net.trique.mythicupgrades.client.MythicConfigScreen.create(parent));
        }
    }

    private void onRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.BLOCK)) {
            event.register(Registries.BLOCK, helper ->
                MythicBlocks.register((name, block) -> {
                    helper.register(Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), block);
                    return block;
                })
            );
        } else if (event.getRegistryKey().equals(Registries.ITEM)) {
            event.register(Registries.ITEM, helper -> {
                MythicBlocks.registerItems((name, item) -> {
                    helper.register(Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), item);
                    return item;
                });
                MythicItems.register((name, item) -> {
                    helper.register(Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), item);
                    return item;
                });
            });
        } else if (event.getRegistryKey().equals(Registries.CREATIVE_MODE_TAB)) {
            event.register(Registries.CREATIVE_MODE_TAB, helper ->
                MythicCreativeTabs.register((name, tab) -> {
                    helper.register(Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), tab);
                    return tab;
                })
            );
        } else if (event.getRegistryKey().equals(Registries.MOB_EFFECT)) {
            event.register(Registries.MOB_EFFECT, helper ->
                MythicEffects.register((name, effect) -> {
                    ResourceKey<MobEffect> key = ResourceKey.create(
                        BuiltInRegistries.MOB_EFFECT.key(),
                        Identifier.fromNamespaceAndPath(Constants.MOD_ID, name));
                    helper.register(key.identifier(), effect);
                    return BuiltInRegistries.MOB_EFFECT.get(key).orElseThrow(
                        () -> new IllegalStateException("MythicEffects: unregistered on NeoForge: " + name));
                })
            );
        } else if (event.getRegistryKey().equals(Registries.POTION)) {
            event.register(Registries.POTION, helper ->
                MythicPotions.register((name, potion) -> {
                    helper.register(Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), potion);
                    return potion;
                })
            );
        } else if (event.getRegistryKey().equals(Registries.FEATURE_TYPE)) {
            event.register(Registries.FEATURE_TYPE, helper ->
                MythicFeatures.register((name, featureType) ->
                    helper.register(Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), featureType))
            );
        } else if (event.getRegistryKey().equals(Registries.SOUND_EVENT)) {
            event.register(Registries.SOUND_EVENT, helper ->
                MythicSounds.register((name, sound) -> {
                    helper.register(Identifier.fromNamespaceAndPath(Constants.MOD_ID, name), sound);
                    return sound;
                })
            );
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
        });
        CommonClass.init();
    }

    private void onClientSetup(FMLClientSetupEvent event) {
    }

}
