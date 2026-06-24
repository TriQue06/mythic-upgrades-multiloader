package net.trique.mythicupgrades;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.trique.mythicupgrades.block.MythicBlocks;

public class FabricClientSetup implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerCutoutRenderLayers();
    }

    private static void registerCutoutRenderLayers() {
        RenderType cutout = RenderType.cutout();
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.AQUAMARINE_CRYSTAL_CLUSTER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.CITRINE_CRYSTAL_CLUSTER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.TOPAZ_CRYSTAL_CLUSTER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.PERIDOT_CRYSTAL_CLUSTER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.RUBY_CRYSTAL_CLUSTER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.SAPPHIRE_CRYSTAL_CLUSTER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.JADE_CRYSTAL_CLUSTER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.AMETRINE_CRYSTAL_CLUSTER, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.LARGE_AQUAMARINE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.LARGE_CITRINE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.LARGE_TOPAZ_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.LARGE_PERIDOT_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.LARGE_RUBY_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.LARGE_SAPPHIRE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.LARGE_JADE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.LARGE_AMETRINE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.MEDIUM_AQUAMARINE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.MEDIUM_CITRINE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.MEDIUM_TOPAZ_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.MEDIUM_PERIDOT_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.MEDIUM_RUBY_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.MEDIUM_SAPPHIRE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.MEDIUM_JADE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.MEDIUM_AMETRINE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.SMALL_AQUAMARINE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.SMALL_CITRINE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.SMALL_TOPAZ_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.SMALL_PERIDOT_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.SMALL_RUBY_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.SMALL_SAPPHIRE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.SMALL_JADE_CRYSTAL_BUD, cutout);
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.SMALL_AMETRINE_CRYSTAL_BUD, cutout);
    }
}
