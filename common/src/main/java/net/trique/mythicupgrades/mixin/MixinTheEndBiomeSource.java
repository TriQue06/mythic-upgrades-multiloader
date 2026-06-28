package net.trique.mythicupgrades.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.TheEndBiomeSource;
import net.trique.mythicupgrades.worldgen.MythicBiomes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.util.stream.Stream;

@Mixin(TheEndBiomeSource.class)
public class MixinTheEndBiomeSource {

    @Unique private static final Logger LOGGER = LoggerFactory.getLogger("MythicUpgrades/EndBiome");

    // Ametrine replaces END_HIGHLANDS; Jade replaces END_MIDLANDS.
    @Unique private Holder<Biome> mythicupgrades$ametrineBarrens = null;
    @Unique private Holder<Biome> mythicupgrades$jadeBarrens     = null;

    @Inject(
        method = "<init>(Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;)V",
        at = @At("TAIL")
    )
    private void mythicupgrades$onInit(
            Holder<Biome> end, Holder<Biome> highlands, Holder<Biome> midlands,
            Holder<Biome> islands, Holder<Biome> barrens, CallbackInfo ci) {

        // TerraBlender 4.x handles End biomes via EndBiomeRegistry — skip mixin fallback.
        // Use initialize=false to avoid triggering EndBiomeRegistry's static initializer
        // before TerraBlender's config is loaded (would NPE at bootstrap time).
        try {
            Class.forName("terrablender.api.EndBiomeRegistry", false,
                    Thread.currentThread().getContextClassLoader());
            return;
        } catch (ClassNotFoundException ignored) {}

        // Skip during Forge datagen
        try {
            boolean isDatagen = (boolean) Class
                    .forName("net.minecraftforge.data.loading.DatagenModLoader")
                    .getMethod("isRunningDataGen")
                    .invoke(null);
            if (isDatagen) return;
        } catch (Throwable ignored) {}

        // Walk Holder.Reference fields to locate the Registry<Biome>.
        Registry<Biome> biomeReg = mythicupgrades$findRegistry(highlands);
        if (biomeReg == null) {
            LOGGER.warn("Could not locate biome Registry — End biomes will not generate");
            return;
        }

        mythicupgrades$ametrineBarrens = biomeReg.getHolder(MythicBiomes.AMETRINE_BARRENS).orElse(null);
        mythicupgrades$jadeBarrens     = biomeReg.getHolder(MythicBiomes.JADE_BARRENS).orElse(null);
        LOGGER.info("End biomes resolved — ametrine={} jade={}", mythicupgrades$ametrineBarrens, mythicupgrades$jadeBarrens);
    }

    @Unique
    @SuppressWarnings("unchecked")
    private static Registry<Biome> mythicupgrades$findRegistry(Holder<Biome> holder) {
        try {
            for (Field f : Holder.Reference.class.getDeclaredFields()) {
                f.setAccessible(true);
                Object val = f.get(holder);
                if (val == null) continue;
                if (val instanceof Registry<?> r) return (Registry<Biome>) r;
                // Anonymous inner class wrapping the registry (MappedRegistry$1)
                for (Field inner : val.getClass().getDeclaredFields()) {
                    try {
                        inner.setAccessible(true);
                        Object innerVal = inner.get(val);
                        if (innerVal instanceof Registry<?> r) return (Registry<Biome>) r;
                    } catch (Exception ignored) {}
                }
            }
        } catch (Throwable e) {
            LOGGER.error("Registry reflection failed", e);
        }
        return null;
    }

    @Inject(method = "collectPossibleBiomes", at = @At("RETURN"), cancellable = true)
    private void mythicupgrades$addPossibleBiomes(CallbackInfoReturnable<Stream<Holder<Biome>>> cir) {
        Stream<Holder<Biome>> extra = Stream.empty();
        if (mythicupgrades$ametrineBarrens != null) extra = Stream.concat(extra, Stream.of(mythicupgrades$ametrineBarrens));
        if (mythicupgrades$jadeBarrens     != null) extra = Stream.concat(extra, Stream.of(mythicupgrades$jadeBarrens));
        cir.setReturnValue(Stream.concat(cir.getReturnValue(), extra));
    }

    @Inject(method = "getNoiseBiome", at = @At("RETURN"), cancellable = true)
    private void mythicupgrades$injectEndBiomes(int x, int y, int z, Climate.Sampler sampler,
                                                 CallbackInfoReturnable<Holder<Biome>> cir) {
        Holder<Biome> current = cir.getReturnValue();

        if (mythicupgrades$ametrineBarrens != null && current.is(Biomes.END_HIGHLANDS)) {
            if (mythicupgrades$inRegion(x, z, 0)) {
                cir.setReturnValue(mythicupgrades$ametrineBarrens);
                return;
            }
        }

        if (mythicupgrades$jadeBarrens != null && current.is(Biomes.END_MIDLANDS)) {
            if (mythicupgrades$inRegion(x, z, 1)) {
                cir.setReturnValue(mythicupgrades$jadeBarrens);
            }
        }
    }

    /**
     * Divides the world into GRID×GRID biome-unit cells (~384 blocks wide each).
     * Roughly 1/8 of cells become a mythic biome zone, producing large contiguous blobs.
     * The blob boundary is softened by checking up to 1 cell of overlap with neighbours.
     */
    @Unique
    private static final int GRID = 96;

    @Unique
    private static boolean mythicupgrades$inRegion(int x, int z, int salt) {
        int cellX = Math.floorDiv(x, GRID);
        int cellZ = Math.floorDiv(z, GRID);

        // Check the cell we're in and immediate neighbours so blobs can overlap borders
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                int cx = cellX + dx;
                int cz = cellZ + dz;
                long h = cellHash(cx, cz, salt);
                if ((h & 0b11111L) != 0L) continue; // ~1/32 cells are active

                // Jittered centre of this active cell
                int centreX = cx * GRID + (int) ((h >>> 8) & (GRID - 1));
                int centreZ = cz * GRID + (int) ((h >>> 24) & (GRID - 1));

                int distX = x - centreX;
                int distZ = z - centreZ;
                // Blob radius: 44 biome-units (~176 blocks)
                if (distX * distX + distZ * distZ < 44 * 44) return true;
            }
        }
        return false;
    }

    @Unique
    private static long cellHash(int cx, int cz, int salt) {
        long h = ((long) cx * 6364136223846793005L) ^ ((long) cz * 1442695040888963407L);
        h ^= (long) salt * 2654435761L;
        h ^= h >>> 33;
        h *= 0xff51afd7ed558ccdL;
        h ^= h >>> 33;
        return h;
    }
}
