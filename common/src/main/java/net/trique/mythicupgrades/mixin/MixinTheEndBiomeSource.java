package net.trique.mythicupgrades.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.TheEndBiomeSource;
import net.trique.mythicupgrades.mixin.accessor.HolderReferenceAccessor;
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

@Mixin(TheEndBiomeSource.class)
public class MixinTheEndBiomeSource {

    @Unique private static final Logger LOGGER = LoggerFactory.getLogger("MythicUpgrades/EndBiome");

    @Mutable @Final @Shadow private Holder<Biome> highlands;
    @Mutable @Final @Shadow private Holder<Biome> midlands;

    @Inject(
        method = "<init>(Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;)V",
        at = @At("TAIL")
    )
    private void mythicupgrades$onInit(
            Holder<Biome> end, Holder<Biome> highlands, Holder<Biome> midlands,
            Holder<Biome> islands, Holder<Biome> barrens, CallbackInfo ci) {

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

        Holder<Biome> mythicBarrens = biomeReg.get(MythicBiomes.MYTHIC_BARRENS).orElse(null);

        if (mythicBarrens != null) {
            this.highlands = mythicBarrens;
            this.midlands  = mythicBarrens;
        }

        LOGGER.info("End biomes injected — highlands={} midlands={}", this.highlands, this.midlands);
    }

    @Unique
    @SuppressWarnings("unchecked")
    private static Registry<Biome> mythicupgrades$findRegistry(Holder<Biome> holder) {
        if (!(holder instanceof Holder.Reference<Biome> ref)) return null;
        HolderOwner<?> owner = ((HolderReferenceAccessor) ref).mythicupgrades$getOwner();
        return owner instanceof Registry<?> r ? (Registry<Biome>) r : null;
    }
}
