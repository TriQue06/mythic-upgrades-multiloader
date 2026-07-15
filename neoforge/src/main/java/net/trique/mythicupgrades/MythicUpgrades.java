package net.trique.mythicupgrades;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.item.MythicItems;
import net.trique.mythicupgrades.worldgen.MythicFeatures;
import net.trique.mythicupgrades.worldgen.TerraBlenderCompat;

@Mod(Constants.MOD_ID)
public class MythicUpgrades {

    public MythicUpgrades(IEventBus modEventBus, net.neoforged.fml.ModContainer container) {
        MythicConfig.load(net.neoforged.fml.loading.FMLPaths.CONFIGDIR.get());

        modEventBus.addListener(this::onRegister);
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::onClientSetup);

        if (net.neoforged.fml.loading.FMLEnvironment.dist.isClient()) {
            container.registerExtensionPoint(
                net.neoforged.neoforge.client.gui.IConfigScreenFactory.class,
                (modContainer, parent) -> net.trique.mythicupgrades.client.MythicConfigScreen.create(parent));
        }
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
                    ResourceKey<MobEffect> key = ResourceKey.create(
                        BuiltInRegistries.MOB_EFFECT.key(),
                        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
                    helper.register(key.location(), effect);
                    return BuiltInRegistries.MOB_EFFECT.getHolder(key).orElseThrow(
                        () -> new IllegalStateException("MythicEffects: unregistered on NeoForge: " + name));
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
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
    public static class NeoForgeEvents {

        @SubscribeEvent
        public static void onBrewingRecipes(RegisterBrewingRecipesEvent event) {
            var builder = event.getBuilder();

            builder.addMix(Potions.AWKWARD, MythicItems.AQUAMARINE_CRYSTAL_SHARD, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD), Items.REDSTONE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD), Items.GLOWSTONE_DUST, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD_STRONG));

            builder.addMix(Potions.AWKWARD, MythicItems.CITRINE_CRYSTAL_SHARD, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD), Items.REDSTONE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD), Items.GLOWSTONE_DUST, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD_STRONG));

            builder.addMix(Potions.AWKWARD, MythicItems.TOPAZ_CRYSTAL_SHARD, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION), Items.REDSTONE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION), Items.GLOWSTONE_DUST, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.TOPAZ_REACTION_STRONG));

            builder.addMix(Potions.AWKWARD, MythicItems.PERIDOT_CRYSTAL_SHARD, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA), Items.REDSTONE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA), Items.GLOWSTONE_DUST, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA_STRONG));

            builder.addMix(Potions.AWKWARD, MythicItems.RUBY_CRYSTAL_SHARD, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST), Items.REDSTONE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST), Items.GLOWSTONE_DUST, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.BLOOD_THIRST_STRONG));

            builder.addMix(Potions.AWKWARD, MythicItems.SAPPHIRE_CRYSTAL_SHARD, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION), Items.REDSTONE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION), Items.GLOWSTONE_DUST, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.DAMAGE_DEFLECTION_STRONG));

            builder.addMix(Potions.AWKWARD, MythicItems.JADE_CRYSTAL_SHARD, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA), Items.REDSTONE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA), Items.GLOWSTONE_DUST, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.JADE_AURA_STRONG));

            builder.addMix(Potions.AWKWARD, MythicItems.AMETRINE_CRYSTAL_SHARD, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA), Items.REDSTONE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA), Items.GLOWSTONE_DUST, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ARCANE_AURA_STRONG));

            builder.addMix(Potions.AWKWARD, MythicItems.NECOIUM_INGOT, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.NECOIUM_SHARE));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.NECOIUM_SHARE), Items.REDSTONE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.NECOIUM_SHARE_LONG));

            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_SHIELD), Items.FERMENTED_SPIDER_EYE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_BOMB));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_BOMB), Items.REDSTONE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_BOMB_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_BOMB), Items.GLOWSTONE_DUST, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_BOMB_STRONG));

            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.ICE_BOMB), Items.FERMENTED_SPIDER_EYE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.FREEZE));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.FREEZE), Items.REDSTONE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.FREEZE_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.FREEZE), Items.GLOWSTONE_DUST, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.FREEZE_STRONG));

            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.STATIC_FIELD), Items.FERMENTED_SPIDER_EYE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.CHARGED));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.CHARGED), Items.REDSTONE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.CHARGED_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.CHARGED), Items.GLOWSTONE_DUST, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.CHARGED_STRONG));

            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.MIASMA), Items.FERMENTED_SPIDER_EYE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.LETHAL_INCUBATION));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.LETHAL_INCUBATION), Items.REDSTONE, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.LETHAL_INCUBATION_LONG));
            builder.addMix(BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.LETHAL_INCUBATION), Items.GLOWSTONE_DUST, BuiltInRegistries.POTION.wrapAsHolder(MythicPotions.LETHAL_INCUBATION_STRONG));
        }

    }
}
