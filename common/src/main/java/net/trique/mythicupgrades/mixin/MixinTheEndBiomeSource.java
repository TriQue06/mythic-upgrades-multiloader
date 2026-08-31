package net.trique.mythicupgrades.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.TheEndBiomeSource;
import net.trique.mythicupgrades.MythicStats;
import net.trique.mythicupgrades.worldgen.MythicBiomeOverlay;
import net.trique.mythicupgrades.worldgen.MythicBiomes;
import net.trique.mythicupgrades.worldgen.MythicOverlayLookup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.Stream;

/**
 * Stamps Mythic Barrens over the End's highlands and midlands on a deterministic
 * grid. Replaces the previous Fabric {@code TheEndBiomes} weighted-pool entry,
 * whose share collapsed as other mods added End biomes to the same pool.
 * See {@link MythicBiomeOverlay}.
 */
// priority 500: mixins are applied in ascending priority order and HEAD callbacks
// run in that same order, so a LOW priority is what puts ours first — ahead of
// mods that inject at HEAD and cancel unconditionally (TerraBlender does exactly
// this for the Overworld and the Nether). An @At("RETURN") injector cannot work
// at all against such a mod: it returns before the original returns are reached.
@Mixin(value = TheEndBiomeSource.class, priority = 500)
public abstract class MixinTheEndBiomeSource {

    /** The central island and its surrounding void are left untouched. */
    @Unique private static final int MAIN_ISLAND_QUARTS = 1024 / 4;

    @Unique private Holder<Biome> mythicupgrades$barrens;
    @Unique private boolean mythicupgrades$resolved;

    @Inject(method = "collectPossibleBiomes", at = @At("RETURN"), cancellable = true)
    private void mythicupgrades$collectPossibleBiomes(CallbackInfoReturnable<Stream<Holder<Biome>>> cir) {
        mythicupgrades$resolved = true;

        // Reading the stream consumes it, and the caller still needs to run
        // .distinct() over the result — so every exit below has to hand back a
        // fresh stream, not the one we drained.
        List<Holder<Biome>> biomes = cir.getReturnValue().toList();
        cir.setReturnValue(biomes.stream());
        if (biomes.isEmpty() || MythicOverlayLookup.isDatagen()) return;

        mythicupgrades$barrens = MythicOverlayLookup.resolve(biomes.get(0), MythicBiomes.MYTHIC_BARRENS);
        if (mythicupgrades$barrens == null) return;

        // /locate biome only searches biomes listed here, so this must not be skipped.
        cir.setReturnValue(Stream.concat(biomes.stream(), Stream.of(mythicupgrades$barrens)));
    }

    @Inject(method = "getNoiseBiome", at = @At("HEAD"), cancellable = true)
    private void mythicupgrades$getNoiseBiome(int x, int y, int z, Climate.Sampler sampler,
                                              CallbackInfoReturnable<Holder<Biome>> cir) {
        if (!mythicupgrades$resolved) {
            ((BiomeSource) (Object) this).possibleBiomes();
            mythicupgrades$resolved = true;
        }
        if (mythicupgrades$barrens == null) return;

        // Everything past the central island is outer End terrain. Matching on
        // END_HIGHLANDS/END_MIDLANDS instead would reintroduce the dilution: mods
        // that swap those for their own biomes would hide us again.
        long lx = x, lz = z;
        if (lx * lx + lz * lz < (long) MAIN_ISLAND_QUARTS * MAIN_ISLAND_QUARTS) return;

        int radius = MythicBiomeOverlay.blocksToQuarts(MythicStats.WORLDGEN_END_RADIUS);
        int grid = MythicBiomeOverlay.gridFor(radius, MythicStats.WORLDGEN_END_DENSITY);
        if (MythicBiomeOverlay.inPatch(x, z, 31, radius, grid)) {
            cir.setReturnValue(mythicupgrades$barrens);
        }
    }
}
