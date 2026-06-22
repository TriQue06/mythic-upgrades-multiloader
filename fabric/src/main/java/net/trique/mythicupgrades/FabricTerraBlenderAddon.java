package net.trique.mythicupgrades;

import net.trique.mythicupgrades.worldgen.TerraBlenderCompat;
import terrablender.api.TerraBlenderApi;

public class FabricTerraBlenderAddon implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        TerraBlenderCompat.init();
    }
}
