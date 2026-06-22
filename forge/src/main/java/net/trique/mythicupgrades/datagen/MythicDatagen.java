package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.worldgen.MythicBiomeBootstrap;
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

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MythicDatagen {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        PackOutput output = event.getGenerator().getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.getGenerator().addProvider(event.includeClient(),
                new MythicBlockStateProvider(output, existingFileHelper));

        event.getGenerator().addProvider(event.includeClient(),
                new MythicItemModelProvider(output, existingFileHelper));

        event.getGenerator().addProvider(event.includeClient(),
                new MythicTrimAtlasProvider(output));

        event.getGenerator().addProvider(event.includeServer(),
                new LootTableProvider(output, Set.of(),
                        List.of(new LootTableProvider.SubProviderEntry(
                                MythicBlockLootTables::new, LootContextParamSets.BLOCK))));

        event.getGenerator().addProvider(event.includeServer(),
                new MythicRecipeProvider(output));

        event.getGenerator().addProvider(event.includeServer(),
                new MythicUpgradingRecipeProvider(output));

        event.getGenerator().addProvider(event.includeServer(),
                new MythicTrimMaterialProvider(output));

        MythicBlockTagsProvider blockTags = new MythicBlockTagsProvider(output, lookupProvider, existingFileHelper);
        event.getGenerator().addProvider(event.includeServer(), blockTags);

        event.getGenerator().addProvider(event.includeServer(),
                new MythicItemTagsProvider(output, lookupProvider, blockTags.contentsGetter()));

        event.getGenerator().addProvider(event.includeServer(),
                new DatapackBuiltinEntriesProvider(output, lookupProvider,
                        new RegistrySetBuilder()
                                .add(Registries.CONFIGURED_FEATURE, ctx -> {
                                    MythicConfiguredFeatures.bootstrap(ctx);
                                    MythicEndConfiguredFeatures.bootstrap(ctx);
                                    MythicNetherConfiguredFeatures.bootstrap(ctx);
                                })
                                .add(Registries.PLACED_FEATURE, ctx -> {
                                    MythicPlacedFeatures.bootstrap(ctx);
                                    MythicEndPlacedFeatures.bootstrap(ctx);
                                    MythicNetherPlacedFeatures.bootstrap(ctx);
                                })
                                .add(Registries.BIOME, ctx -> {
                                    MythicBiomeBootstrap.bootstrap(ctx);
                                    MythicEndBiomeBootstrap.bootstrap(ctx);
                                    MythicNetherBiomeBootstrap.bootstrap(ctx);
                                }),
                        Set.of(Constants.MOD_ID)));
    }
}
