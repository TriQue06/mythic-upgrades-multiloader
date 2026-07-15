package net.trique.mythicupgrades;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.trique.mythicupgrades.client.MythicConfigScreen;

public class MythicModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return MythicConfigScreen::create;
    }
}
