package net.trique.mythicupgrades.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.trique.mythicupgrades.MythicStats;
import net.trique.mythicupgrades.worldgen.MythicBiomeOverlay;
import net.trique.mythicupgrades.worldgen.MythicBiomes;
import net.trique.mythicupgrades.worldgen.MythicOverlayLookup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stamps the Mythic cave and Nether biomes onto a deterministic grid rather than
 * entering them into TerraBlender's weighted region pool, so their coverage does
 * not shrink as a modpack grows. See {@link MythicBiomeOverlay}.
 *
 * <p>The Overworld and the Nether share this class, so the dimension is inferred
 * once from the biomes the source can produce and cached for the hot path.
 */
// priority 500: mixins are applied in ascending priority order and HEAD callbacks
// run in that same order, so a LOW priority is what puts ours first — ahead of
// mods that inject at HEAD and cancel unconditionally (TerraBlender does exactly
// this for the Overworld and the Nether). An @At("RETURN") injector cannot work
// at all against such a mod: it returns before the original returns are reached.
@Mixin(value = MultiNoiseBiomeSource.class, priority = 500)
public abstract class MixinMultiNoiseBiomeSource {

    @Unique private static final int KIND_UNKNOWN = -1;
    @Unique private static final int KIND_OTHER = 0;
    @Unique private static final int KIND_OVERWORLD = 1;
    @Unique private static final int KIND_NETHER = 2;

    @Unique private int mythicupgrades$kind = KIND_UNKNOWN;
    @Unique private Holder<Biome> mythicupgrades$coldCaves;
    @Unique private Holder<Biome> mythicupgrades$warmCaves;
    @Unique private Holder<Biome> mythicupgrades$rifts;

    @Inject(method = "collectPossibleBiomes", at = @At("RETURN"), cancellable = true)
    private void mythicupgrades$collectPossibleBiomes(CallbackInfoReturnable<Stream<Holder<Biome>>> cir) {
        // Reading the stream consumes it, and the caller still needs to run
        // .distinct() over the result — so every exit below has to hand back a
        // fresh stream, not the one we drained.
        List<Holder<Biome>> biomes = cir.getReturnValue().toList();
        cir.setReturnValue(biomes.stream());
        if (biomes.isEmpty()) return;

        mythicupgrades$resolveFrom(biomes);
        if (mythicupgrades$kind == KIND_OTHER) return;

        List<Holder<Biome>> extra = new ArrayList<>();
        if (mythicupgrades$coldCaves != null) extra.add(mythicupgrades$coldCaves);
        if (mythicupgrades$warmCaves != null) extra.add(mythicupgrades$warmCaves);
        if (mythicupgrades$rifts != null) extra.add(mythicupgrades$rifts);

        // /locate biome only searches biomes listed here, so this must not be skipped.
        if (!extra.isEmpty()) {
            cir.setReturnValue(Stream.concat(biomes.stream(), extra.stream()));
        }
    }

    /** Identifies the dimension and looks up our biomes; sets kind to OTHER when neither applies. */
    @Unique
    private void mythicupgrades$resolveFrom(Iterable<Holder<Biome>> biomes) {
        Holder<Biome> sample = null;
        int kind = KIND_OTHER;
        for (Holder<Biome> holder : biomes) {
            if (sample == null) sample = holder;
            if (mythicupgrades$isNetherMarker(holder)) { kind = KIND_NETHER; break; }
            if (mythicupgrades$isOverworldMarker(holder)) { kind = KIND_OVERWORLD; break; }
        }
        mythicupgrades$kind = kind;
        if (kind == KIND_OTHER || sample == null || MythicOverlayLookup.isDatagen()) return;

        if (kind == KIND_OVERWORLD) {
            mythicupgrades$coldCaves = MythicOverlayLookup.resolve(sample, MythicBiomes.COLD_MYTHIC_CAVES);
            mythicupgrades$warmCaves = MythicOverlayLookup.resolve(sample, MythicBiomes.WARM_MYTHIC_CAVES);
        } else {
            mythicupgrades$rifts = MythicOverlayLookup.resolve(sample, MythicBiomes.MYTHIC_RIFTS);
        }
    }

    // Deliberately keyed on vanilla biomes rather than the #is_nether / #is_overworld
    // tags: this runs while the server is still being constructed, before datapack
    // tags are bound to their holders, so a tag test would report false for every
    // biome — and the result is memoized, disabling the overlay for the whole world.
    @Unique
    private static boolean mythicupgrades$isNetherMarker(Holder<Biome> holder) {
        return holder.is(Biomes.NETHER_WASTES) || holder.is(Biomes.CRIMSON_FOREST)
            || holder.is(Biomes.WARPED_FOREST) || holder.is(Biomes.SOUL_SAND_VALLEY)
            || holder.is(Biomes.BASALT_DELTAS);
    }

    @Unique
    private static boolean mythicupgrades$isOverworldMarker(Holder<Biome> holder) {
        return holder.is(Biomes.PLAINS) || holder.is(Biomes.FOREST)
            || holder.is(Biomes.OCEAN) || holder.is(Biomes.DESERT)
            || holder.is(Biomes.TAIGA) || holder.is(Biomes.LUSH_CAVES)
            || holder.is(Biomes.DRIPSTONE_CAVES) || holder.is(Biomes.DEEP_DARK);
    }

    @Inject(method = "getNoiseBiome", at = @At("HEAD"), cancellable = true)
    private void mythicupgrades$getNoiseBiome(int x, int y, int z, Climate.Sampler sampler,
                                              CallbackInfoReturnable<Holder<Biome>> cir) {
        if (mythicupgrades$kind == KIND_UNKNOWN) {
            // TerraBlender swaps in a fresh biome source for the Overworld and the
            // Nether after ours has already been set up, and never calls
            // collectPossibleBiomes on it — so resolve straight off possibleBiomes(),
            // which is populated no matter who built the source.
            mythicupgrades$resolveFrom(((BiomeSource) (Object) this).possibleBiomes());
        }
        if (mythicupgrades$kind == KIND_OTHER) return;

        if (mythicupgrades$kind == KIND_NETHER) {
            if (mythicupgrades$rifts == null) return;
            int radius = MythicBiomeOverlay.blocksToQuarts(MythicStats.WORLDGEN_NETHER_RADIUS);
            int grid = MythicBiomeOverlay.gridFor(radius, MythicStats.WORLDGEN_NETHER_DENSITY);
            if (MythicBiomeOverlay.inPatch(x, z, 11, radius, grid)) {
                cir.setReturnValue(mythicupgrades$rifts);
            }
            return;
        }

        if (mythicupgrades$coldCaves == null || mythicupgrades$warmCaves == null) return;

        int blockY = y << 2;
        if (blockY < MythicStats.WORLDGEN_CAVE_MIN_Y || blockY > MythicStats.WORLDGEN_CAVE_MAX_Y) return;

        int radius = MythicBiomeOverlay.blocksToQuarts(MythicStats.WORLDGEN_CAVE_RADIUS);
        int grid = MythicBiomeOverlay.gridFor(radius, MythicStats.WORLDGEN_CAVE_DENSITY);
        if (!MythicBiomeOverlay.inPatch(x, z, 21, radius, grid)) return;

        // Split cold/warm on the climate sample rather than on the biome that would
        // have been returned: at HEAD there is no result yet, and reading another
        // mod's biome would put us back at the mercy of what else is installed.
        cir.setReturnValue(sampler.sample(x, y, z).temperature() < 0L
            ? mythicupgrades$coldCaves
            : mythicupgrades$warmCaves);
    }
}
