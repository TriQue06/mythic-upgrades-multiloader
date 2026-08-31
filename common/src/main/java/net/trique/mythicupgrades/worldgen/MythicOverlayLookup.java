package net.trique.mythicupgrades.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.trique.mythicupgrades.mixin.accessor.HolderReferenceAccessor;

import javax.annotation.Nullable;

/**
 * Resolves our biome holders from whatever a {@code BiomeSource} was built
 * against. Biome sources carry no registry reference in 1.20.1, so the owner is
 * read off one of the holders they were handed.
 */
public final class MythicOverlayLookup {

    private MythicOverlayLookup() {}

    private static Boolean datagen;

    /**
     * Datagen builds a throwaway registry set; overlaying biomes there would leak
     * into the generated files. Reflective because the class is Forge-only.
     */
    public static boolean isDatagen() {
        if (datagen == null) {
            boolean running = false;
            try {
                running = (boolean) Class
                    .forName("net.minecraftforge.data.loading.DatagenModLoader")
                    .getMethod("isRunningDataGen")
                    .invoke(null);
            } catch (Throwable ignored) {}
            datagen = running;
        }
        return datagen;
    }

    /**
     * Looks up {@code key} in the same registry that produced {@code sample}.
     * Null when the datapack defining the biome is absent from this world.
     *
     * <p>Datapack-loaded holders are not owned by the {@link Registry} itself but
     * by the lookup view it hands to {@code RegistryOps}, so the {@link HolderGetter}
     * case is the one that actually fires in a running world.
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public static Holder<Biome> resolve(@Nullable Holder<Biome> sample, ResourceKey<Biome> key) {
        if (!(sample instanceof Holder.Reference<Biome> ref)) return null;
        HolderOwner<?> owner = ((HolderReferenceAccessor) ref).mythicupgrades$getOwner();

        if (owner instanceof HolderGetter<?> getter) {
            return ((HolderGetter<Biome>) getter).get(key).orElse(null);
        }
        if (owner instanceof Registry<?> registry) {
            return ((Registry<Biome>) registry).getHolder(key).orElse(null);
        }
        return null;
    }
}
