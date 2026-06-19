package net.trique.mythicupgrades.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.AABB;
import net.trique.mythicupgrades.MythicAnims;
import net.trique.mythicupgrades.MythicEffects;
import net.trique.mythicupgrades.MythicState;
import net.trique.mythicupgrades.MythicStats;
import net.trique.mythicupgrades.item.MythicItems;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerGameMode.class)
public abstract class ServerPlayerGameModeMixin {

    @Shadow public ServerPlayer player;

    @Inject(method = "destroyBlock", at = @At("RETURN"))
    private void onDestroyBlock(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) return;
        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        Item tool = player.getMainHandItem().getItem();
        if (!isTopazTool(tool)) return;

        int count = MythicState.TOPAZ_TOOL_HIT_COUNTS.getOrDefault(player, 0) + 1;
        if (count >= MythicStats.TOPAZ_TOOL_SHOCK_INTERVAL) {
            MythicState.TOPAZ_TOOL_HIT_COUNTS.put(player, 0);
            double cx = pos.getX() + 0.5;
            double cy = pos.getY() + 0.5;
            double cz = pos.getZ() + 0.5;
            int shockLevel = MythicStats.TOPAZ_TOOL_EFFECTIVE_LEVEL;
            float shockRadius = Math.min(shockLevel * MythicStats.TOPAZ_ARMOR_SHOCK_RADIUS_PER_LEVEL, MythicStats.TOPAZ_ARMOR_SHOCK_MAX_RADIUS);

            emitTopazInstantBurst(serverLevel, cx, cy, cz, shockRadius);

            AABB bb = new AABB(cx - shockRadius, cy - shockRadius, cz - shockRadius, cx + shockRadius, cy + shockRadius, cz + shockRadius);
            for (LivingEntity entity : serverLevel.getEntitiesOfClass(LivingEntity.class, bb)) {
                if (entity == player) continue;
                if (entity.distanceTo(player) <= shockRadius)
                    entity.addEffect(new MobEffectInstance(MythicEffects.TOPAZ_SHOCK, 1, shockLevel - 1));
            }

            serverLevel.playSound(null, cx, cy, cz, MythicAnims.TOPAZ_WAVE_SOUND_1, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_1);
            serverLevel.playSound(null, cx, cy, cz, MythicAnims.TOPAZ_WAVE_SOUND_2, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_2);
        } else {
            MythicState.TOPAZ_TOOL_HIT_COUNTS.put(player, count);
        }
    }

    @Unique
    private static void emitTopazInstantBurst(ServerLevel level, double cx, double cy, double cz, float maxRadius) {
        float spacing = 1.2f;
        for (float r = spacing; r <= maxRadius; r += spacing) {
            emitTopazRing(level, cx, cy, cz, r);
        }
    }

    @Unique
    private static Vector3f colorFromHex(int hex) {
        return new Vector3f((hex >> 16 & 0xFF) / 255f, (hex >> 8 & 0xFF) / 255f, (hex & 0xFF) / 255f);
    }

    @Unique
    private static void emitTopazRing(ServerLevel level, double cx, double cy, double cz, float radius) {
        DustParticleOptions c1 = new DustParticleOptions(colorFromHex(MythicAnims.TOPAZ_COLOR_1), MythicAnims.TOPAZ_TOOL_WAVE_PARTICLE_SCALE);
        DustParticleOptions c2 = new DustParticleOptions(colorFromHex(MythicAnims.TOPAZ_COLOR_2), MythicAnims.TOPAZ_TOOL_WAVE_PARTICLE_SCALE);
        DustParticleOptions c3 = new DustParticleOptions(colorFromHex(MythicAnims.TOPAZ_COLOR_3), MythicAnims.TOPAZ_TOOL_WAVE_PARTICLE_SCALE);
        int step = MythicAnims.TOPAZ_TOOL_WAVE_STEP_DEGREES;
        int segment = step * 3;
        for (int angle = 0; angle < 360; angle += step) {
            double rad = Math.toRadians(angle);
            double px = cx + radius * Math.cos(rad);
            double pz = cz + radius * Math.sin(rad);
            int mod = angle % segment;
            DustParticleOptions color = mod < step ? c1 : mod < step * 2 ? c2 : c3;
            level.sendParticles(color, px, cy, pz, MythicAnims.TOPAZ_TOOL_WAVE_PARTICLES_PER_POINT, 0, 0.08, 0, 0);
        }
    }

    @Unique
    private static boolean isTopazTool(Item item) {
        return item == MythicItems.TOPAZ_SWORD || item == MythicItems.TOPAZ_PICKAXE
            || item == MythicItems.TOPAZ_AXE || item == MythicItems.TOPAZ_SHOVEL
            || item == MythicItems.TOPAZ_HOE;
    }
}
