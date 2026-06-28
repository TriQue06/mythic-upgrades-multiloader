package net.trique.mythicupgrades;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.trique.mythicupgrades.datagen.MythicBiomeTagsProvider;
import net.trique.mythicupgrades.datagen.MythicBlockLootTableProvider;
import net.trique.mythicupgrades.datagen.MythicBlockStateProvider;
import net.trique.mythicupgrades.datagen.MythicBlockTagsProvider;
import net.trique.mythicupgrades.datagen.MythicDamageTypeTagsProvider;
import net.trique.mythicupgrades.datagen.MythicItemModelProvider;
import net.trique.mythicupgrades.datagen.MythicItemTagsProvider;
import net.trique.mythicupgrades.datagen.MythicRecipeProvider;
import net.trique.mythicupgrades.datagen.MythicTrimAtlasProvider;
import net.trique.mythicupgrades.datagen.MythicTrimMaterialProvider;
import net.trique.mythicupgrades.worldgen.MythicBiomeBootstrap;
import net.trique.mythicupgrades.worldgen.MythicBiomeModifierBootstrap;
import net.trique.mythicupgrades.worldgen.MythicDamageTypeBootstrap;
import net.trique.mythicupgrades.worldgen.MythicConfiguredFeatures;
import net.trique.mythicupgrades.worldgen.MythicEndBiomeBootstrap;
import net.trique.mythicupgrades.worldgen.MythicEndConfiguredFeatures;
import net.trique.mythicupgrades.worldgen.MythicEndPlacedFeatures;
import net.trique.mythicupgrades.worldgen.MythicNetherBiomeBootstrap;
import net.trique.mythicupgrades.worldgen.MythicNetherConfiguredFeatures;
import net.trique.mythicupgrades.worldgen.MythicNetherPlacedFeatures;
import net.trique.mythicupgrades.worldgen.MythicPlacedFeatures;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class MythicDataGen {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
        .add(Registries.CONFIGURED_FEATURE, ctx -> {
            MythicConfiguredFeatures.bootstrap(ctx);
            MythicNetherConfiguredFeatures.bootstrap(ctx);
            MythicEndConfiguredFeatures.bootstrap(ctx);
        })
        .add(Registries.PLACED_FEATURE, ctx -> {
            MythicPlacedFeatures.bootstrap(ctx);
            MythicNetherPlacedFeatures.bootstrap(ctx);
            MythicEndPlacedFeatures.bootstrap(ctx);
        })
        .add(Registries.BIOME, ctx -> {
            MythicBiomeBootstrap.bootstrap(ctx);
            MythicNetherBiomeBootstrap.bootstrap(ctx);
            MythicEndBiomeBootstrap.bootstrap(ctx);
        })
        .add(Registries.DAMAGE_TYPE, MythicDamageTypeBootstrap::bootstrap)
        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MythicBiomeModifierBootstrap::bootstrap);

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<net.minecraft.core.HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Datapack built-in entries (worldgen + biome modifiers)
        gen.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
            output, lookupProvider, BUILDER, Set.of(Constants.MOD_ID)
        ));

        // Block/item tags
        var blockTags = new MythicBlockTagsProvider(output, lookupProvider, existingFileHelper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new MythicItemTagsProvider(output, lookupProvider, existingFileHelper));
        gen.addProvider(event.includeServer(), new MythicBiomeTagsProvider(output, lookupProvider, existingFileHelper));
        gen.addProvider(event.includeServer(), new MythicDamageTypeTagsProvider(output, lookupProvider, existingFileHelper));

        // Loot tables
        gen.addProvider(event.includeServer(), new LootTableProvider(output, Set.of(),
            List.of(new LootTableProvider.SubProviderEntry(MythicBlockLootTableProvider::new, LootContextParamSets.BLOCK)),
            lookupProvider));

        // Recipes
        gen.addProvider(event.includeServer(), new MythicRecipeProvider(output, lookupProvider));

        // Trim materials + atlas
        gen.addProvider(event.includeServer(), new MythicTrimMaterialProvider(output));
        gen.addProvider(event.includeClient(), new MythicTrimAtlasProvider(output));

        // Block states + models
        gen.addProvider(event.includeClient(), new MythicBlockStateProvider(output, existingFileHelper));
        gen.addProvider(event.includeClient(), new MythicItemModelProvider(output, existingFileHelper));
    }
}
