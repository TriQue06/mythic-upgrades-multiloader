package net.trique.mythicupgrades.mixin;

import net.minecraft.core.BlockPos;
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
import net.trique.mythicupgrades.util.MUDamageTypes;
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

        if (isJadeTool(tool)) {
            player.addEffect(new MobEffectInstance(MythicEffects.JADE_AURA, MythicStats.JADE_TOOL_AURA_DURATION_TICKS, 4));
        }

        if (!isTopazTool(tool)) return;

        int count = MythicState.TOPAZ_TOOL_HIT_COUNTS.getOrDefault(player, 0) + 1;
        if (count >= MythicStats.TOPAZ_TOOL_SHOCK_INTERVAL) {
            MythicState.TOPAZ_TOOL_HIT_COUNTS.put(player, 0);
            double cx = pos.getX() + 0.5;
            double cy = pos.getY() + 0.5;
            double cz = pos.getZ() + 0.5;
            int shockLevel = MythicStats.TOPAZ_TOOL_EFFECTIVE_LEVEL;
            float shockRadius = Math.min(shockLevel * MythicStats.TOPAZ_ARMOR_SHOCK_RADIUS_PER_LEVEL, MythicStats.TOPAZ_ARMOR_SHOCK_MAX_RADIUS);

            MythicState.TOPAZ_PENDING_WAVES.put(player, new float[]{(float)cx, (float)cy, (float)cz, shockRadius});

            AABB bb = new AABB(cx - shockRadius, cy - shockRadius, cz - shockRadius, cx + shockRadius, cy + shockRadius, cz + shockRadius);
            float shockDamage = shockLevel * MythicStats.TOPAZ_SHOCK_DAMAGE_PER_LEVEL;
            float knockback = shockLevel * MythicStats.TOPAZ_SHOCK_KNOCKBACK_PER_LEVEL;
            for (LivingEntity entity : serverLevel.getEntitiesOfClass(LivingEntity.class, bb)) {
                if (entity == player) continue;
                if (entity.distanceTo(player) <= shockRadius) {
                    entity.hurt(MUDamageTypes.topazShock(player), shockDamage);
                    entity.knockback(knockback, cx - entity.getX(), cz - entity.getZ());
                }
            }

            serverLevel.playSound(null, cx, cy, cz, MythicAnims.TOPAZ_WAVE_SOUND_1, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_1);
            serverLevel.playSound(null, cx, cy, cz, MythicAnims.TOPAZ_WAVE_SOUND_2, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_2);
        } else {
            MythicState.TOPAZ_TOOL_HIT_COUNTS.put(player, count);
        }
    }

    @Unique
    private static boolean isTopazTool(Item item) {
        return item == MythicItems.TOPAZ_SWORD || item == MythicItems.TOPAZ_PICKAXE
            || item == MythicItems.TOPAZ_AXE || item == MythicItems.TOPAZ_SHOVEL
            || item == MythicItems.TOPAZ_HOE;
    }

    @Unique
    private static boolean isJadeTool(Item item) {
        return item == MythicItems.JADE_SWORD || item == MythicItems.JADE_PICKAXE
            || item == MythicItems.JADE_AXE || item == MythicItems.JADE_SHOVEL
            || item == MythicItems.JADE_HOE;
    }
}
