package net.trique.mythicupgrades.mixin;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.AABB;
import net.trique.mythicupgrades.MythicAnims;
import net.trique.mythicupgrades.MythicEffects;
import net.trique.mythicupgrades.MythicState;
import net.trique.mythicupgrades.MythicStats;
import net.trique.mythicupgrades.item.MythicItems;
import net.trique.mythicupgrades.util.MUDamageTypes;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Unique private int mu_sapphireLevel = -1;
    @Unique private int mu_ametrineLevel = -1;
    @Unique private int mu_topazLevel = -1;
    @Unique private int mu_rubyLevel = -1;
    @Unique private float mu_healthBefore = 0f;

    @Unique private int mu_arcaneWaveTick = -1;
    @Unique private float mu_arcaneWaveMaxRadius = 0f;
    @Unique private double mu_arcaneWaveX = 0;
    @Unique private double mu_arcaneWaveY = 0;
    @Unique private double mu_arcaneWaveZ = 0;

    @Unique private int mu_topazWaveTick = -1;
    @Unique private float mu_topazWaveMaxRadius = 0f;
    @Unique private double mu_topazWaveX = 0;
    @Unique private double mu_topazWaveY = 0;
    @Unique private double mu_topazWaveZ = 0;

    @Inject(method = "tick", at = @At("TAIL"))
    private void checkArmorEffects(CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (self.level().isClientSide()) return;

        int sapphireLevel = 0;
        if (self.getItemBySlot(EquipmentSlot.HEAD).getItem() == MythicItems.SAPPHIRE_HELMET) sapphireLevel += MythicStats.SAPPHIRE_HELMET_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.CHEST).getItem() == MythicItems.SAPPHIRE_CHESTPLATE) sapphireLevel += MythicStats.SAPPHIRE_CHESTPLATE_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.LEGS).getItem() == MythicItems.SAPPHIRE_LEGGINGS) sapphireLevel += MythicStats.SAPPHIRE_LEGGINGS_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.FEET).getItem() == MythicItems.SAPPHIRE_BOOTS) sapphireLevel += MythicStats.SAPPHIRE_BOOTS_LEVELS;
        if (sapphireLevel != mu_sapphireLevel) {
            mu_sapphireLevel = sapphireLevel;
            self.removeEffect(MythicEffects.DAMAGE_DEFLECTION);
            if (sapphireLevel > 0)
                self.addEffect(new MobEffectInstance(MythicEffects.DAMAGE_DEFLECTION, -1, sapphireLevel - 1, false, false, true));
        }

        int ametrineLevel = 0;
        if (self.getItemBySlot(EquipmentSlot.HEAD).getItem() == MythicItems.AMETRINE_HELMET) ametrineLevel += MythicStats.AMETRINE_HELMET_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.CHEST).getItem() == MythicItems.AMETRINE_CHESTPLATE) ametrineLevel += MythicStats.AMETRINE_CHESTPLATE_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.LEGS).getItem() == MythicItems.AMETRINE_LEGGINGS) ametrineLevel += MythicStats.AMETRINE_LEGGINGS_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.FEET).getItem() == MythicItems.AMETRINE_BOOTS) ametrineLevel += MythicStats.AMETRINE_BOOTS_LEVELS;
        if (ametrineLevel != mu_ametrineLevel) {
            mu_ametrineLevel = ametrineLevel;
            self.removeEffect(MythicEffects.ARCANE_AURA);
            if (ametrineLevel > 0)
                self.addEffect(new MobEffectInstance(MythicEffects.ARCANE_AURA, -1, ametrineLevel - 1, false, false, true));
        }

        int topazLevel = 0;
        if (self.getItemBySlot(EquipmentSlot.HEAD).getItem() == MythicItems.TOPAZ_HELMET) topazLevel += MythicStats.TOPAZ_HELMET_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.CHEST).getItem() == MythicItems.TOPAZ_CHESTPLATE) topazLevel += MythicStats.TOPAZ_CHESTPLATE_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.LEGS).getItem() == MythicItems.TOPAZ_LEGGINGS) topazLevel += MythicStats.TOPAZ_LEGGINGS_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.FEET).getItem() == MythicItems.TOPAZ_BOOTS) topazLevel += MythicStats.TOPAZ_BOOTS_LEVELS;
        if (topazLevel != mu_topazLevel) {
            mu_topazLevel = topazLevel;
            self.removeEffect(MythicEffects.TOPAZ_REACTION);
            if (topazLevel > 0)
                self.addEffect(new MobEffectInstance(MythicEffects.TOPAZ_REACTION, -1, topazLevel - 1, false, false, true));
        }

        int rubyLevel = 0;
        if (self.getItemBySlot(EquipmentSlot.HEAD).getItem() == MythicItems.RUBY_HELMET) rubyLevel += MythicStats.RUBY_HELMET_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.CHEST).getItem() == MythicItems.RUBY_CHESTPLATE) rubyLevel += MythicStats.RUBY_CHESTPLATE_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.LEGS).getItem() == MythicItems.RUBY_LEGGINGS) rubyLevel += MythicStats.RUBY_LEGGINGS_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.FEET).getItem() == MythicItems.RUBY_BOOTS) rubyLevel += MythicStats.RUBY_BOOTS_LEVELS;
        if (rubyLevel != mu_rubyLevel) {
            mu_rubyLevel = rubyLevel;
            self.removeEffect(MythicEffects.BLOOD_THIRST);
            if (rubyLevel > 0)
                self.addEffect(new MobEffectInstance(MythicEffects.BLOOD_THIRST, -1, rubyLevel - 1, false, false, true));
        }

        if (mu_arcaneWaveTick >= 0 && self.level() instanceof ServerLevel serverLevel) {
            for (int i = 0; i < MythicAnims.ARCANE_AURA_WAVE_COUNT; i++) {
                float r = (mu_arcaneWaveTick - i * MythicAnims.ARCANE_AURA_WAVE_GAP_TICKS) * MythicAnims.ARCANE_AURA_WAVE_SPEED_PER_TICK;
                if (r > 0 && r <= mu_arcaneWaveMaxRadius)
                    emitArcaneAuraWaveRing(serverLevel, mu_arcaneWaveX, mu_arcaneWaveY, mu_arcaneWaveZ, r);
            }
            mu_arcaneWaveTick++;
            float lastR = (mu_arcaneWaveTick - (MythicAnims.ARCANE_AURA_WAVE_COUNT - 1) * MythicAnims.ARCANE_AURA_WAVE_GAP_TICKS) * MythicAnims.ARCANE_AURA_WAVE_SPEED_PER_TICK;
            if (lastR > mu_arcaneWaveMaxRadius) mu_arcaneWaveTick = -1;
        }

        if (mu_topazWaveTick >= 0 && self.level() instanceof ServerLevel serverLevel) {
            for (int i = 0; i < MythicAnims.TOPAZ_ARMOR_WAVE_COUNT; i++) {
                float r = (mu_topazWaveTick - i * MythicAnims.TOPAZ_ARMOR_WAVE_GAP_TICKS) * MythicAnims.TOPAZ_ARMOR_WAVE_SPEED_PER_TICK;
                if (r > 0 && r <= mu_topazWaveMaxRadius)
                    emitTopazWaveRing(serverLevel, mu_topazWaveX, mu_topazWaveY, mu_topazWaveZ, r);
            }
            mu_topazWaveTick++;
            float lastR = (mu_topazWaveTick - (MythicAnims.TOPAZ_ARMOR_WAVE_COUNT - 1) * MythicAnims.TOPAZ_ARMOR_WAVE_GAP_TICKS) * MythicAnims.TOPAZ_ARMOR_WAVE_SPEED_PER_TICK;
            if (lastR > mu_topazWaveMaxRadius) mu_topazWaveTick = -1;
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"))
    private void captureHealth(DamageSource source, float amount, CallbackInfoReturnable<Boolean> ci) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (!self.level().isClientSide()) mu_healthBefore = self.getHealth();
    }

    @Inject(method = "hurt", at = @At("TAIL"))
    private void onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (self.level().isClientSide() || !cir.getReturnValue()) return;

        String msgId = source.getMsgId();
        if ("deflecting".equals(msgId) || "percentage".equals(msgId) || "topaz_shock".equals(msgId)) return;

        float actualDamage = mu_healthBefore - self.getHealth();

        if (actualDamage > 0) {
            MobEffectInstance deflection = self.getEffect(MythicEffects.DAMAGE_DEFLECTION);
            if (deflection != null) {
                Entity rawAttacker = source.getEntity();
                if (rawAttacker instanceof LivingEntity attacker) {
                    float reflectAmount = (deflection.getAmplifier() + 1) * MythicStats.DEFLECT_PER_LEVEL * actualDamage;
                    attacker.hurt(MUDamageTypes.deflecting(self), reflectAmount);
                }
            }
        }

        if (actualDamage > 0 && self.level() instanceof ServerLevel btServerLevel) {
            float maxSearch = MythicStats.BLOOD_THIRST_MAX_RADIUS;
            AABB searchBB = new AABB(
                self.getX() - maxSearch, self.getY() - maxSearch, self.getZ() - maxSearch,
                self.getX() + maxSearch, self.getY() + maxSearch, self.getZ() + maxSearch
            );
            for (LivingEntity btEntity : btServerLevel.getEntitiesOfClass(LivingEntity.class, searchBB)) {
                if (btEntity == self) continue;
                MobEffectInstance btEffect = btEntity.getEffect(MythicEffects.BLOOD_THIRST);
                if (btEffect == null) continue;
                int btLevel = btEffect.getAmplifier() + 1;
                float btRadius = Math.min(btLevel * MythicStats.BLOOD_THIRST_RADIUS_PER_LEVEL, MythicStats.BLOOD_THIRST_MAX_RADIUS);
                if (btEntity.distanceTo(self) > btRadius) continue;
                float healFrac = Math.min(btLevel * MythicStats.BLOOD_THIRST_HEAL_FRACTION_PER_LEVEL, MythicStats.BLOOD_THIRST_MAX_HEAL_FRACTION);
                btEntity.heal(actualDamage * healFrac);
                emitBloodThirstParticles(btServerLevel, self, btEntity, btLevel);
            }
        }

        MobEffectInstance aura = self.getEffect(MythicEffects.ARCANE_AURA);
        if (aura != null && self.level() instanceof ServerLevel serverLevel) {
            int effectLevel = aura.getAmplifier() + 1;
            float maxRadius = Math.min(effectLevel * MythicStats.ARCANE_AURA_RADIUS_PER_LEVEL, MythicStats.ARCANE_AURA_MAX_RADIUS);
            int levitationAmp = effectLevel * MythicStats.ARCANE_AURA_LEVITATION_AMP_PER_LEVEL - 1;
            int levitationDur = Math.min(effectLevel * MythicStats.ARCANE_AURA_LEVITATION_DURATION_TICKS_PER_LEVEL, MythicStats.ARCANE_AURA_LEVITATION_MAX_DURATION_TICKS);

            mu_arcaneWaveTick = 0;
            mu_arcaneWaveMaxRadius = maxRadius;
            mu_arcaneWaveX = self.getX();
            mu_arcaneWaveY = self.getY() + 1.0;
            mu_arcaneWaveZ = self.getZ();

            applyLevitationInRange(serverLevel, self, maxRadius, levitationAmp, levitationDur);
            serverLevel.playSound(null, self.getX(), self.getY(), self.getZ(), MythicAnims.ARCANE_AURA_SOUND_1, SoundSource.PLAYERS, MythicAnims.ARCANE_AURA_SOUND_VOLUME, MythicAnims.ARCANE_AURA_SOUND_PITCH_1);
            serverLevel.playSound(null, self.getX(), self.getY(), self.getZ(), MythicAnims.ARCANE_AURA_SOUND_2, SoundSource.PLAYERS, MythicAnims.ARCANE_AURA_SOUND_VOLUME, MythicAnims.ARCANE_AURA_SOUND_PITCH_2);
        }

        MobEffectInstance topaz = self.getEffect(MythicEffects.TOPAZ_REACTION);
        if (topaz != null && self.level() instanceof ServerLevel serverLevel) {
            int effectLevel = topaz.getAmplifier() + 1;
            float shockDamage = Math.min(effectLevel * MythicStats.TOPAZ_ARMOR_SHOCK_DAMAGE_PER_LEVEL, MythicStats.TOPAZ_ARMOR_SHOCK_MAX_DAMAGE);
            float shockRadius = Math.min(effectLevel * MythicStats.TOPAZ_ARMOR_SHOCK_RADIUS_PER_LEVEL, MythicStats.TOPAZ_ARMOR_SHOCK_MAX_RADIUS);

            mu_topazWaveTick = 0;
            mu_topazWaveMaxRadius = shockRadius;
            mu_topazWaveX = self.getX();
            mu_topazWaveY = self.getY() + 1.0;
            mu_topazWaveZ = self.getZ();

            applyShockDamageInRange(serverLevel, self, null, shockRadius, shockDamage);
            serverLevel.playSound(null, self.getX(), self.getY(), self.getZ(), MythicAnims.TOPAZ_WAVE_SOUND_1, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_1);
            serverLevel.playSound(null, self.getX(), self.getY(), self.getZ(), MythicAnims.TOPAZ_WAVE_SOUND_2, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_2);
        }

        Entity directEntity = source.getDirectEntity();
        Entity causingEntity = source.getEntity();
        if (directEntity != null && directEntity == causingEntity && directEntity instanceof LivingEntity directAttacker) {
            Item weapon = directAttacker.getMainHandItem().getItem();

            if (isSapphireTool(weapon)) {
                float bonusDamage = self.getMaxHealth() * MythicStats.SAPPHIRE_TOOL_PERCENT_DAMAGE;
                self.hurt(MUDamageTypes.percentage(directAttacker), bonusDamage);
            }

            if (isAmetrineTool(weapon)) {
                self.addEffect(new MobEffectInstance(MobEffects.LEVITATION, MythicStats.AMETRINE_TOOL_LEVITATION_DURATION_TICKS, MythicStats.AMETRINE_TOOL_LEVITATION_AMPLIFIER));
                if (self.getRandom().nextFloat() < MythicStats.AMETRINE_TOOL_TELEPORT_CHANCE) randomTeleportNear(self);
            }

            if (isTopazTool(weapon) && self.level() instanceof ServerLevel serverLevel) {
                int count = MythicState.TOPAZ_TOOL_HIT_COUNTS.getOrDefault(directAttacker, 0) + 1;
                if (count >= MythicStats.TOPAZ_TOOL_SHOCK_INTERVAL) {
                    MythicState.TOPAZ_TOOL_HIT_COUNTS.put(directAttacker, 0);
                    float effectiveLevel = MythicStats.TOPAZ_TOOL_EFFECTIVE_LEVEL;
                    float shockDamage = Math.min(effectiveLevel * MythicStats.TOPAZ_ARMOR_SHOCK_DAMAGE_PER_LEVEL, MythicStats.TOPAZ_ARMOR_SHOCK_MAX_DAMAGE);
                    float shockRadius = Math.min(effectiveLevel * MythicStats.TOPAZ_ARMOR_SHOCK_RADIUS_PER_LEVEL, MythicStats.TOPAZ_ARMOR_SHOCK_MAX_RADIUS);
                    emitTopazInstantBurst(serverLevel, self.getX(), self.getY() + 1.0, self.getZ(), shockRadius);
                    applyShockDamageInRange(serverLevel, self, directAttacker, shockRadius, shockDamage);
                    serverLevel.playSound(null, self.getX(), self.getY(), self.getZ(), MythicAnims.TOPAZ_WAVE_SOUND_1, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_1);
                    serverLevel.playSound(null, self.getX(), self.getY(), self.getZ(), MythicAnims.TOPAZ_WAVE_SOUND_2, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_2);
                } else {
                    MythicState.TOPAZ_TOOL_HIT_COUNTS.put(directAttacker, count);
                }
            }

            if (isRubyTool(weapon) && actualDamage > 0) {
                directAttacker.heal(actualDamage * MythicStats.RUBY_TOOL_LIFESTEAL_FRACTION);
                if (self.level() instanceof ServerLevel serverLevel) {
                    emitLifestealParticles(serverLevel, self, directAttacker);
                }
            }
        }
    }

    @Unique
    private static void emitArcaneAuraWaveRing(ServerLevel level, double cx, double cy, double cz, float radius) {
        DustParticleOptions c1 = new DustParticleOptions(new Vector3f(0.353f, 0.098f, 0.522f), MythicAnims.ARCANE_AURA_WAVE_PARTICLE_SCALE);
        DustParticleOptions c2 = new DustParticleOptions(new Vector3f(0.694f, 0.192f, 0.761f), MythicAnims.ARCANE_AURA_WAVE_PARTICLE_SCALE);
        DustParticleOptions c3 = new DustParticleOptions(new Vector3f(0.914f, 0.447f, 0.576f), MythicAnims.ARCANE_AURA_WAVE_PARTICLE_SCALE);
        int step = MythicAnims.ARCANE_AURA_WAVE_STEP_DEGREES;
        int segment = step * 3;
        for (int angle = 0; angle < 360; angle += step) {
            double rad = Math.toRadians(angle);
            double px = cx + radius * Math.cos(rad);
            double pz = cz + radius * Math.sin(rad);
            int mod = angle % segment;
            DustParticleOptions color = mod < step ? c1 : mod < step * 2 ? c2 : c3;
            level.sendParticles(color, px, cy, pz, MythicAnims.ARCANE_AURA_WAVE_PARTICLES_PER_POINT, 0, 0.08, 0, 0);
        }
    }

    @Unique
    private static void emitTopazWaveRing(ServerLevel level, double cx, double cy, double cz, float radius) {
        DustParticleOptions c1 = new DustParticleOptions(new Vector3f(0.620f, 0.176f, 0.106f), MythicAnims.TOPAZ_ARMOR_WAVE_PARTICLE_SCALE);
        DustParticleOptions c2 = new DustParticleOptions(new Vector3f(0.878f, 0.333f, 0.122f), MythicAnims.TOPAZ_ARMOR_WAVE_PARTICLE_SCALE);
        DustParticleOptions c3 = new DustParticleOptions(new Vector3f(0.976f, 0.616f, 0.235f), MythicAnims.TOPAZ_ARMOR_WAVE_PARTICLE_SCALE);
        int step = MythicAnims.TOPAZ_ARMOR_WAVE_STEP_DEGREES;
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
    private static void emitTopazInstantBurst(ServerLevel level, double cx, double cy, double cz, float maxRadius) {
        float spacing = 1.2f;
        for (float r = spacing; r <= maxRadius; r += spacing) {
            emitTopazWaveRing(level, cx, cy, cz, r);
        }
    }

    @Unique
    private static void emitLifestealParticles(ServerLevel level, LivingEntity from, LivingEntity to) {
        DustParticleOptions dust = new DustParticleOptions(new Vector3f(0.800f, 0.125f, 0.251f), MythicAnims.RUBY_LIFESTEAL_PARTICLE_SCALE);
        double fx = from.getX(), fy = from.getY() + from.getBbHeight() * 0.5, fz = from.getZ();
        double tx = to.getX(), ty = to.getY() + to.getBbHeight() * 0.5, tz = to.getZ();
        int count = MythicStats.RUBY_TOOL_LIFESTEAL_PARTICLE_COUNT;
        for (int i = 0; i < count; i++) {
            double t = (double) i / count;
            level.sendParticles(dust, fx + (tx - fx) * t, fy + (ty - fy) * t, fz + (tz - fz) * t, 1, 0, 0, 0, 0);
        }
    }

    @Unique
    private static void emitBloodThirstParticles(ServerLevel level, LivingEntity from, LivingEntity to, int btLevel) {
        DustParticleOptions dust = new DustParticleOptions(new Vector3f(0.800f, 0.125f, 0.251f), MythicAnims.RUBY_BLOOD_THIRST_PARTICLE_SCALE);
        double fx = from.getX(), fy = from.getY() + from.getBbHeight() * 0.5, fz = from.getZ();
        double tx = to.getX(), ty = to.getY() + to.getBbHeight() * 0.5, tz = to.getZ();
        int count = btLevel * MythicStats.BLOOD_THIRST_PARTICLES_PER_LEVEL;
        for (int i = 0; i < count; i++) {
            double t = (double) i / count;
            level.sendParticles(dust, fx + (tx - fx) * t, fy + (ty - fy) * t, fz + (tz - fz) * t, 1, 0, 0, 0, 0);
        }
    }

    @Unique
    private static void applyLevitationInRange(ServerLevel level, LivingEntity center, float radius, int amplifier, int duration) {
        AABB bb = new AABB(center.getX() - radius, center.getY() - radius, center.getZ() - radius,
            center.getX() + radius, center.getY() + radius, center.getZ() + radius);
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, bb)) {
            if (entity == center) continue;
            if (entity.distanceTo(center) <= radius)
                entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, duration, amplifier));
        }
    }

    @Unique
    private static void applyShockDamageInRange(ServerLevel level, LivingEntity center, LivingEntity excluded, float radius, float damage) {
        AABB bb = new AABB(center.getX() - radius, center.getY() - radius, center.getZ() - radius,
            center.getX() + radius, center.getY() + radius, center.getZ() + radius);
        List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, bb);
        for (LivingEntity entity : nearby) {
            if (entity == center || entity == excluded) continue;
            if (entity.distanceTo(center) <= radius)
                entity.hurt(MUDamageTypes.topazShock(center), damage);
        }
    }

    @Unique
    private static void randomTeleportNear(LivingEntity entity) {
        for (int i = 0; i < 16; i++) {
            double x = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
            double y = Mth.clamp(entity.getY() + (entity.getRandom().nextInt(16) - 8),
                entity.level().getMinBuildHeight(), entity.level().getMaxBuildHeight() - 1);
            double z = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
            if (entity.randomTeleport(x, y, z, true)) break;
        }
    }

    @Unique
    private static boolean isSapphireTool(Item item) {
        return item == MythicItems.SAPPHIRE_SWORD || item == MythicItems.SAPPHIRE_PICKAXE
            || item == MythicItems.SAPPHIRE_AXE || item == MythicItems.SAPPHIRE_SHOVEL || item == MythicItems.SAPPHIRE_HOE;
    }

    @Unique
    private static boolean isAmetrineTool(Item item) {
        return item == MythicItems.AMETRINE_SWORD || item == MythicItems.AMETRINE_PICKAXE
            || item == MythicItems.AMETRINE_AXE || item == MythicItems.AMETRINE_SHOVEL || item == MythicItems.AMETRINE_HOE;
    }

    @Unique
    private static boolean isTopazTool(Item item) {
        return item == MythicItems.TOPAZ_SWORD || item == MythicItems.TOPAZ_PICKAXE
            || item == MythicItems.TOPAZ_AXE || item == MythicItems.TOPAZ_SHOVEL || item == MythicItems.TOPAZ_HOE;
    }

    @Unique
    private static boolean isRubyTool(Item item) {
        return item == MythicItems.RUBY_SWORD || item == MythicItems.RUBY_PICKAXE
            || item == MythicItems.RUBY_AXE || item == MythicItems.RUBY_SHOVEL || item == MythicItems.RUBY_HOE;
    }
}
