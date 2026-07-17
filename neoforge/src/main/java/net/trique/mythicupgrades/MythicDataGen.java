package net.trique.mythicupgrades;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.trique.mythicupgrades.datagen.MythicBiomeTagsProvider;
import net.trique.mythicupgrades.datagen.MythicBlockLootTableProvider;
import net.trique.mythicupgrades.datagen.MythicBlockModelProvider;
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

@EventBusSubscriber(modid = Constants.MOD_ID)
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
    public static void onGatherData(GatherDataEvent.Client event) {
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<net.minecraft.core.HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.createDatapackRegistryObjects(BUILDER, Set.of(Constants.MOD_ID));

        event.createProvider(MythicBlockTagsProvider::new);
        event.createProvider(MythicItemTagsProvider::new);
        event.createProvider(MythicBiomeTagsProvider::new);
        event.createProvider(MythicDamageTypeTagsProvider::new);

        event.addProvider(new LootTableProvider(output, Set.of(),
            List.of(new LootTableProvider.SubProviderEntry(MythicBlockLootTableProvider::new, LootContextParamSets.BLOCK)),
            lookupProvider));

        event.createProvider(MythicRecipeProvider.Runner::new);

        event.addProvider(new MythicTrimMaterialProvider(output));
        event.addProvider(new MythicTrimAtlasProvider(output));

        event.addProvider(new MythicItemModelProvider(output));
        event.addProvider(new MythicBlockModelProvider(output));
    }
}
