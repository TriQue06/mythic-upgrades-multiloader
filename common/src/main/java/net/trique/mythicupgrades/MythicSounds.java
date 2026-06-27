package net.trique.mythicupgrades;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class MythicSounds {

    public static final SoundEvent AMBIENT_MYTHIC_CHIME = SoundEvent.createVariableRangeEvent(
            new ResourceLocation(Constants.MOD_ID, "ambient.mythic_chime"));

    public static void register() {
        Registry.register(BuiltInRegistries.SOUND_EVENT,
                new ResourceLocation(Constants.MOD_ID, "ambient.mythic_chime"),
                AMBIENT_MYTHIC_CHIME);
    }
}
