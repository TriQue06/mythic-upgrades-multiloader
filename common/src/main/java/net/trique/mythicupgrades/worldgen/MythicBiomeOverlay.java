package net.trique.mythicupgrades.worldgen;

/**
 * Deterministic, mod-count-independent biome placement.
 *
 * <p>Weighted biome pools (TerraBlender regions, Fabric's {@code TheEndBiomes})
 * hand out a <em>share</em> of the world: the more mods join the pool, the
 * smaller every existing share becomes. In a large modpack a weight-1 entry
 * collapses to a fraction of a percent and effectively stops generating.
 *
 * <p>This class instead claims <em>absolute</em> territory. The world is cut
 * into square cells of side {@link #gridFor}; every cell hosts exactly one
 * jittered patch centre of the requested radius. The resulting area fraction is
 *
 * <pre>  density = pi * radius^2 / grid^2  </pre>
 *
 * which depends on nothing but our own numbers. Installing a hundred further
 * worldgen mods cannot change it, and because every cell holds a patch the
 * distance to the nearest one is bounded by roughly two grid cells — so
 * {@code /locate biome} always terminates quickly.
 *
 * <p>All coordinates are quart (biome-cell) coordinates: 1 quart = 4 blocks.
 */
public final class MythicBiomeOverlay {

    /** Largest density the geometry supports before patches would overlap (pi/4). */
    public static final double MAX_DENSITY = 0.78;

    private MythicBiomeOverlay() {}

    /**
     * Cell side, in quarts, that yields {@code density} coverage for patches of
     * {@code radiusQuarts}. Clamped so patches in neighbouring cells cannot merge.
     */
    public static int gridFor(int radiusQuarts, double density) {
        double d = Math.min(Math.max(density, 0.0001), MAX_DENSITY);
        int grid = (int) Math.round(radiusQuarts * Math.sqrt(Math.PI / d));
        return Math.max(grid, 2 * radiusQuarts + 1);
    }

    /** Converts a block radius from the config into quarts, never below one cell. */
    public static int blocksToQuarts(int blocks) {
        return Math.max(1, blocks / 4);
    }

    /**
     * Whether the quart column {@code (x, z)} falls inside a patch. {@code salt}
     * separates independent overlays so two biomes never share the same centres.
     */
    public static boolean inPatch(int x, int z, int salt, int radiusQuarts, int gridQuarts) {
        int cellX = Math.floorDiv(x, gridQuarts);
        int cellZ = Math.floorDiv(z, gridQuarts);
        int rSq = radiusQuarts * radiusQuarts;

        // A patch centre near a cell border reaches into its neighbours, so the
        // eight surrounding cells have to be tested as well.
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                int cx = cellX + dx;
                int cz = cellZ + dz;
                long h = hash(cx, cz, salt);

                int centreX = cx * gridQuarts + (int) Math.floorMod(h >>> 8, gridQuarts);
                int centreZ = cz * gridQuarts + (int) Math.floorMod(h >>> 36, gridQuarts);

                int distX = x - centreX;
                int distZ = z - centreZ;
                if (distX * distX + distZ * distZ < rSq) return true;
            }
        }
        return false;
    }

    private static long hash(int cx, int cz, int salt) {
        long h = ((long) cx * 6364136223846793005L) ^ ((long) cz * 1442695040888963407L);
        h ^= (long) salt * 2654435761L;
        h ^= h >>> 33;
        h *= 0xff51afd7ed558ccdL;
        h ^= h >>> 33;
        h *= 0xc4ceb9fe1a85ec53L;
        h ^= h >>> 33;
        return h;
    }
}
