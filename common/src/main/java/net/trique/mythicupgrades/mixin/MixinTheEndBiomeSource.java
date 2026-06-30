package net.trique.mythicupgrades.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.TheEndBiomeSource;
import net.trique.mythicupgrades.worldgen.MythicBiomes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(TheEndBiomeSource.class)
public class MixinTheEndBiomeSource {

    @Unique private static final Logger LOGGER = LoggerFactory.getLogger("MythicUpgrades/EndBiome");

    // Shadow the stored holder fields so we can overwrite them directly.
    // Vanilla getNoiseBiome reads these fields — replacing them is the most reliable approach.
    @Mutable @Final @Shadow private Holder<Biome> highlands;
    @Mutable @Final @Shadow private Holder<Biome> midlands;

    @Inject(
        method = "<init>(Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;)V",
        at = @At("TAIL")
    )
    private void mythicupgrades$onInit(
            Holder<Biome> end, Holder<Biome> highlands, Holder<Biome> midlands,
            Holder<Biome> islands, Holder<Biome> barrens, CallbackInfo ci) {

        // Skip during Forge/NeoForge datagen
        try {
            boolean isDatagen = (boolean) Class
                    .forName("net.minecraftforge.data.loading.DatagenModLoader")
                    .getMethod("isRunningDataGen")
                    .invoke(null);
            if (isDatagen) return;
        } catch (Throwable ignored) {}

        Registry<Biome> biomeReg = mythicupgrades$findRegistry(highlands);
        if (biomeReg == null) {
            LOGGER.warn("Could not locate biome Registry — End biomes will not generate");
            return;
        }

        Holder<Biome> ametrine = biomeReg.getHolder(MythicBiomes.AMETRINE_BARRENS).orElse(null);
        Holder<Biome> jade     = biomeReg.getHolder(MythicBiomes.JADE_BARRENS).orElse(null);

        if (ametrine != null) this.highlands = ametrine;
        if (jade     != null) this.midlands  = jade;

        LOGGER.info("End biomes injected — highlands={} midlands={}", this.highlands, this.midlands);
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
}
