package net.trique.mythicupgrades.handler;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.Team;
import net.trique.mythicupgrades.MythicEffects;
import org.joml.Vector3f;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class NecoiumShareHandler {

    private static final float RANGE = 30.0f;
    // 100-tick buffer, refreshed every 10 ticks
    private static final int SHARE_DURATION = 100;

    private static final DustParticleOptions[] COLORS = {
        new DustParticleOptions(new Vector3f(0.651f, 0.055f, 0.416f), 1.4f), // #a60e6a
        new DustParticleOptions(new Vector3f(0.902f, 0.102f, 0.561f), 1.4f), // #e61a8f
        new DustParticleOptions(new Vector3f(0.957f, 0.490f, 0.627f), 1.4f), // #f47da0
    };

    private static final List<Holder<MobEffect>> SHAREABLE_EFFECTS = Arrays.asList(
        MythicEffects.DAMAGE_DEFLECTION,
        MythicEffects.ARCANE_AURA,
        MythicEffects.TOPAZ_REACTION,
        MythicEffects.BLOOD_THIRST,
        MythicEffects.MIASMA,
        MythicEffects.ICE_SHIELD,
        MythicEffects.STATIC_FIELD,
        MythicEffects.JADE_AURA
    );

    private static final Map<Level, Long> levelTickCache = new WeakHashMap<>();
    private static final Map<Level, Set<LivingEntity>> processedPerTick = new WeakHashMap<>();

    public static void onTick(ServerLevel level, LivingEntity source) {
        long currentTick = level.getGameTime();

        if (levelTickCache.getOrDefault(level, -1L) != currentTick) {
            levelTickCache.put(level, currentTick);
            processedPerTick.put(level, new HashSet<>());
        }

        Set<LivingEntity> processed = processedPerTick.get(level);
        if (processed.contains(source)) return;

        // BFS to build the connected component, respecting team boundaries
        List<LivingEntity> network = new ArrayList<>();
        Queue<LivingEntity> queue = new LinkedList<>();
        queue.add(source);
        processed.add(source);

        while (!queue.isEmpty()) {
            LivingEntity current = queue.poll();
            network.add(current);

            List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class,
                current.getBoundingBox().inflate(RANGE),
                e -> e != current
                    && !processed.contains(e)
                    && e.hasEffect(MythicEffects.NECOIUM_SHARE)
                    && canConnect(current, e));

            for (LivingEntity neighbor : nearby) {
                processed.add(neighbor);
                queue.add(neighbor);
            }
        }

        if (network.size() < 2) {
            // Alone or out of range: remove any infinite ambient copies so they don't persist forever
            for (Holder<MobEffect> effect : SHAREABLE_EFFECTS) {
                MobEffectInstance inst = source.getEffect(effect);
                if (inst != null && inst.isAmbient() && inst.getDuration() == -1) {
                    source.removeEffect(effect);
                }
            }
            return;
        }

        processNetwork(level, network, currentTick);
    }

    /**
     * Two entities can be in the same share network unless they are both players
     * on different (non-null) teams. Mobs always connect freely.
     */
    private static boolean canConnect(LivingEntity a, LivingEntity b) {
        if (!(a instanceof Player playerA) || !(b instanceof Player playerB)) return true;
        Team teamA = playerA.getTeam();
        Team teamB = playerB.getTeam();
        return teamA == null || teamB == null || teamA == teamB;
    }

    private static void processNetwork(ServerLevel level, List<LivingEntity> network, long tick) {
        for (Holder<MobEffect> effect : SHAREABLE_EFFECTS) {
            // Only count non-ambient effects as sources to avoid shared copies re-sharing themselves
            int maxAmplifier = -1;
            boolean anyInfinite = false;
            for (LivingEntity entity : network) {
                MobEffectInstance instance = entity.getEffect(effect);
                if (instance != null && !instance.isAmbient()) {
                    if (instance.getAmplifier() > maxAmplifier) maxAmplifier = instance.getAmplifier();
                    if (instance.getDuration() == -1) anyInfinite = true;
                }
            }

            if (maxAmplifier >= 0) {
                final int amp = maxAmplifier;
                // Share as infinite if any source is infinite so recipients see the infinity symbol
                final int shareDuration = anyInfinite ? -1 : SHARE_DURATION;
                for (LivingEntity entity : network) {
                    MobEffectInstance current = entity.getEffect(effect);
                    // Don't override a non-ambient (original) effect with same or lower amp
                    if (current != null && !current.isAmbient() && current.getAmplifier() >= amp) continue;
                    boolean needsUpdate = current == null
                        || current.getAmplifier() < amp
                        || (shareDuration == -1 && current.getDuration() != -1)
                        || (shareDuration != -1 && current.getDuration() != -1 && current.getDuration() < SHARE_DURATION);
                    if (needsUpdate) {
                        // ambient=true marks this as a shared copy so it's never treated as a source
                        entity.addEffect(new MobEffectInstance(effect, shareDuration, amp, true, true));
                    }
                }
            } else {
                // No non-ambient source left in network: remove shared (ambient) copies
                for (LivingEntity entity : network) {
                    MobEffectInstance current = entity.getEffect(effect);
                    if (current != null && current.isAmbient()) {
                        entity.removeEffect(effect);
                    }
                }
            }
        }

        spawnNetworkParticles(level, network, tick);
    }

    private static void spawnNetworkParticles(ServerLevel level, List<LivingEntity> network, long tick) {
        double progress = (tick % 40) / 40.0;

        for (int i = 0; i < network.size(); i++) {
            for (int j = i + 1; j < network.size(); j++) {
                spawnParticlesBetween(level, network.get(i), network.get(j), progress);
            }
        }
    }

    private static void spawnParticlesBetween(ServerLevel level, LivingEntity a, LivingEntity b, double progress) {
        double ax = a.getX(), ay = a.getY() + a.getBbHeight() * 0.5, az = a.getZ();
        double bx = b.getX(), by = b.getY() + b.getBbHeight() * 0.5, bz = b.getZ();

        ThreadLocalRandom rng = ThreadLocalRandom.current();
        double spread = 0.14;

        // 3 "balls" traveling A→B and 3 traveling B→A at offset phases
        // Period is 40 ticks with 10-tick updates → 4 distinct positions per cycle
        for (int ball = 0; ball < 3; ball++) {
            double tFwd = (progress + ball / 3.0) % 1.0;
            double tBwd = 1.0 - tFwd;

            // 5 particles per ball for density; alternating colors
            for (int k = 0; k < 5; k++) {
                DustParticleOptions fwdColor = COLORS[(k + ball) % 3];
                DustParticleOptions bwdColor = COLORS[(k + ball + 1) % 3];

                double jx = (rng.nextDouble() - 0.5) * spread;
                double jy = (rng.nextDouble() - 0.5) * spread;
                double jz = (rng.nextDouble() - 0.5) * spread;

                // A→B ball
                level.sendParticles(fwdColor,
                    ax + (bx - ax) * tFwd + jx,
                    ay + (by - ay) * tFwd + jy,
                    az + (bz - az) * tFwd + jz,
                    1, 0, 0, 0, 0);

                // B→A ball (same jitter for symmetry)
                level.sendParticles(bwdColor,
                    bx + (ax - bx) * tBwd + jx,
                    by + (ay - by) * tBwd + jy,
                    bz + (az - bz) * tBwd + jz,
                    1, 0, 0, 0, 0);
            }
        }
    }
}
