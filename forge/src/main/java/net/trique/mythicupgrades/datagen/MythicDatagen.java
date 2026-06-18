package net.trique.mythicupgrades.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.trique.mythicupgrades.Constants;

import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MythicDatagen {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        PackOutput output = event.getGenerator().getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        event.getGenerator().addProvider(event.includeClient(),
                new MythicBlockStateProvider(output, existingFileHelper));

        event.getGenerator().addProvider(event.includeClient(),
                new MythicItemModelProvider(output, existingFileHelper));

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
    }
}
