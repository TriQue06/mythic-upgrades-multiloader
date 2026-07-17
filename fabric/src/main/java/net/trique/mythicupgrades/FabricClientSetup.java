package net.trique.mythicupgrades;

import net.fabricmc.api.ClientModInitializer;

public class FabricClientSetup implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Block render layers are chosen automatically from texture transparency since 26.x;
        // no explicit cutout registration is needed anymore.
    }
}
