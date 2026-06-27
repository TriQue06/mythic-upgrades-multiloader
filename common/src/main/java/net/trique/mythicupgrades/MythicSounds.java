package net.trique.mythicupgrades;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class MythicSounds {

    private static final List<Map.Entry<String, SoundEvent>> DEFERRED = new ArrayList<>();

    private static SoundEvent defer(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name);
        SoundEvent event = SoundEvent.createVariableRangeEvent(id);
        DEFERRED.add(new AbstractMap.SimpleEntry<>(name, event));
        return event;
    }

    public static final SoundEvent AMBIENT_MYTHIC_CHIME = defer("ambient.mythic_chime");

    public static void register(BiFunction<String, SoundEvent, SoundEvent> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
        Constants.LOG.info("MythicSounds registered.");
    }
}
