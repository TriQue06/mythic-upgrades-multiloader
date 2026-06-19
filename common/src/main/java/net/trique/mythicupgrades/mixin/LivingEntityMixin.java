package net.trique.mythicupgrades.mixin;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
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

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.phys.Vec3;
import java.util.Comparator;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Unique private int mu_sapphireLevel = -1;
    @Unique private int mu_ametrineLevel = -1;
    @Unique private int mu_topazLevel = -1;
    @Unique private int mu_rubyLevel = -1;
    @Unique private int mu_peridotLevel = -1;
    @Unique private int mu_aquamarineLevel = -1;
    @Unique private int mu_citrineLevel = -1;
    @Unique private int mu_jadeLevel = -1;
    @Unique private int mu_miasmaTick = 0;
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

    @Unique private int mu_miasmaWaveTick = -1;
    @Unique private float mu_miasmaWaveMaxRadius = 0f;
    @Unique private double mu_miasmaWaveX = 0, mu_miasmaWaveY = 0, mu_miasmaWaveZ = 0;
    @Unique private int mu_miasmaWaveLevel = 0;

    @Unique private int mu_incubationWaveTick = -1;
    @Unique private float mu_incubationWaveMaxRadius = 0f;
    @Unique private double mu_incubationWaveX = 0, mu_incubationWaveY = 0, mu_incubationWaveZ = 0;
    @Unique private int mu_incubationWaveLevel = 0;

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

        int peridotLevel = 0;
        if (self.getItemBySlot(EquipmentSlot.HEAD).getItem() == MythicItems.PERIDOT_HELMET) peridotLevel += MythicStats.PERIDOT_HELMET_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.CHEST).getItem() == MythicItems.PERIDOT_CHESTPLATE) peridotLevel += MythicStats.PERIDOT_CHESTPLATE_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.LEGS).getItem() == MythicItems.PERIDOT_LEGGINGS) peridotLevel += MythicStats.PERIDOT_LEGGINGS_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.FEET).getItem() == MythicItems.PERIDOT_BOOTS) peridotLevel += MythicStats.PERIDOT_BOOTS_LEVELS;
        if (peridotLevel != mu_peridotLevel) {
            mu_peridotLevel = peridotLevel;
            mu_miasmaTick = 0;
            self.removeEffect(MythicEffects.MIASMA);
            if (peridotLevel > 0)
                self.addEffect(new MobEffectInstance(MythicEffects.MIASMA, -1, peridotLevel - 1, false, false, true));
        }
        if (peridotLevel > 0) {
            mu_miasmaTick++;
            if (mu_miasmaTick >= MythicStats.MIASMA_INTERVAL_TICKS && self.level() instanceof ServerLevel serverLevel) {
                mu_miasmaTick = 0;
                spawnMiasmaCloud(serverLevel, self, peridotLevel);
                mu_miasmaWaveTick = 0;
                mu_miasmaWaveMaxRadius = Math.min(peridotLevel * MythicStats.MIASMA_CLOUD_RADIUS_PER_LEVEL, MythicStats.MIASMA_CLOUD_MAX_RADIUS);
                mu_miasmaWaveX = self.getX();
                mu_miasmaWaveY = self.getY() + 1.0;
                mu_miasmaWaveZ = self.getZ();
                mu_miasmaWaveLevel = peridotLevel;
            }
        }

        int aquamarineLevel = 0;
        if (self.getItemBySlot(EquipmentSlot.HEAD).getItem() == MythicItems.AQUAMARINE_HELMET) aquamarineLevel += MythicStats.AQUAMARINE_HELMET_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.CHEST).getItem() == MythicItems.AQUAMARINE_CHESTPLATE) aquamarineLevel += MythicStats.AQUAMARINE_CHESTPLATE_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.LEGS).getItem() == MythicItems.AQUAMARINE_LEGGINGS) aquamarineLevel += MythicStats.AQUAMARINE_LEGGINGS_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.FEET).getItem() == MythicItems.AQUAMARINE_BOOTS) aquamarineLevel += MythicStats.AQUAMARINE_BOOTS_LEVELS;
        if (aquamarineLevel != mu_aquamarineLevel) {
            mu_aquamarineLevel = aquamarineLevel;
            self.removeEffect(MythicEffects.ICE_SHIELD);
            if (aquamarineLevel > 0)
                self.addEffect(new MobEffectInstance(MythicEffects.ICE_SHIELD, -1, aquamarineLevel - 1, false, false, true));
        }

        int citrineLevel = 0;
        if (self.getItemBySlot(EquipmentSlot.HEAD).getItem() == MythicItems.CITRINE_HELMET) citrineLevel += MythicStats.CITRINE_HELMET_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.CHEST).getItem() == MythicItems.CITRINE_CHESTPLATE) citrineLevel += MythicStats.CITRINE_CHESTPLATE_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.LEGS).getItem() == MythicItems.CITRINE_LEGGINGS) citrineLevel += MythicStats.CITRINE_LEGGINGS_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.FEET).getItem() == MythicItems.CITRINE_BOOTS) citrineLevel += MythicStats.CITRINE_BOOTS_LEVELS;
        if (citrineLevel != mu_citrineLevel) {
            mu_citrineLevel = citrineLevel;
            self.removeEffect(MythicEffects.STATIC_FIELD);
            if (citrineLevel > 0)
                self.addEffect(new MobEffectInstance(MythicEffects.STATIC_FIELD, -1, citrineLevel - 1, false, false, true));
        }
        if (citrineLevel > 0 && self.level() instanceof ServerLevel citrineSLevel) {
            float fieldRadius = Math.min(citrineLevel * MythicStats.STATIC_FIELD_RADIUS_PER_LEVEL, MythicStats.STATIC_FIELD_MAX_RADIUS);
            if (self.tickCount % MythicAnims.CITRINE_STATIC_FIELD_PARTICLE_INTERVAL == 0)
                emitStaticFieldParticles(citrineSLevel, self, fieldRadius, citrineLevel);
            if (self.tickCount % 20 == 0) {
                float fieldDamage = Math.min(citrineLevel * MythicStats.STATIC_FIELD_DAMAGE_PER_LEVEL_PER_SECOND, MythicStats.STATIC_FIELD_MAX_DAMAGE_PER_SECOND);
                AABB fbb = new AABB(self.getX() - fieldRadius, self.getY() - fieldRadius, self.getZ() - fieldRadius,
                    self.getX() + fieldRadius, self.getY() + fieldRadius, self.getZ() + fieldRadius);
                for (LivingEntity entity : citrineSLevel.getEntitiesOfClass(LivingEntity.class, fbb)) {
                    if (entity == self) continue;
                    if (entity.distanceTo(self) <= fieldRadius)
                        entity.hurt(MUDamageTypes.staticField(self), fieldDamage);
                }
            }
        }

        int jadeLevel = 0;
        if (self.getItemBySlot(EquipmentSlot.HEAD).getItem() == MythicItems.JADE_HELMET) jadeLevel += MythicStats.JADE_HELMET_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.CHEST).getItem() == MythicItems.JADE_CHESTPLATE) jadeLevel += MythicStats.JADE_CHESTPLATE_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.LEGS).getItem() == MythicItems.JADE_LEGGINGS) jadeLevel += MythicStats.JADE_LEGGINGS_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.FEET).getItem() == MythicItems.JADE_BOOTS) jadeLevel += MythicStats.JADE_BOOTS_LEVELS;
        if (jadeLevel != mu_jadeLevel) {
            mu_jadeLevel = jadeLevel;
            self.removeEffect(MythicEffects.JADE_AURA);
            if (jadeLevel > 0)
                self.addEffect(new MobEffectInstance(MythicEffects.JADE_AURA, -1, jadeLevel - 1, false, false, true));
        }
        if (jadeLevel > 0 && self.level() instanceof ServerLevel jadeSLevel) {
            if (self.tickCount % MythicStats.JADE_TRAIL_INTERVAL_TICKS == 0)
                spawnJadeTrailCloud(jadeSLevel, self, jadeLevel);
        }

        if (self.getEffect(MythicEffects.BOUNCER) != null) {
            Item mainHand = self.getMainHandItem().getItem();
            Item offHand = self.getOffhandItem().getItem();
            if (!isJadeTool(mainHand) && !isJadeTool(offHand))
                self.removeEffect(MythicEffects.BOUNCER);
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

        if (mu_miasmaWaveTick >= 0 && self.level() instanceof ServerLevel mslevel) {
            for (int i = 0; i < MythicAnims.MIASMA_WAVE_COUNT; i++) {
                float r = (mu_miasmaWaveTick - i * MythicAnims.MIASMA_WAVE_GAP_TICKS) * MythicAnims.MIASMA_WAVE_SPEED_PER_TICK;
                if (r > 0 && r <= mu_miasmaWaveMaxRadius)
                    emitMiasmaWaveRing(mslevel, mu_miasmaWaveX, mu_miasmaWaveY, mu_miasmaWaveZ, r, mu_miasmaWaveLevel);
            }
            mu_miasmaWaveTick++;
            float lastMr = (mu_miasmaWaveTick - (MythicAnims.MIASMA_WAVE_COUNT - 1) * MythicAnims.MIASMA_WAVE_GAP_TICKS) * MythicAnims.MIASMA_WAVE_SPEED_PER_TICK;
            if (lastMr > mu_miasmaWaveMaxRadius) mu_miasmaWaveTick = -1;
        }

        if (mu_incubationWaveTick >= 0 && self.level() instanceof ServerLevel ilevel) {
            for (int i = 0; i < MythicAnims.LETHAL_INCUBATION_WAVE_COUNT; i++) {
                float r = (mu_incubationWaveTick - i * MythicAnims.LETHAL_INCUBATION_WAVE_GAP_TICKS) * MythicAnims.LETHAL_INCUBATION_WAVE_SPEED_PER_TICK;
                if (r > 0 && r <= mu_incubationWaveMaxRadius)
                    emitLethalIncubationWaveRing(ilevel, mu_incubationWaveX, mu_incubationWaveY, mu_incubationWaveZ, r, mu_incubationWaveLevel);
            }
            mu_incubationWaveTick++;
            float lastIr = (mu_incubationWaveTick - (MythicAnims.LETHAL_INCUBATION_WAVE_COUNT - 1) * MythicAnims.LETHAL_INCUBATION_WAVE_GAP_TICKS) * MythicAnims.LETHAL_INCUBATION_WAVE_SPEED_PER_TICK;
            if (lastIr > mu_incubationWaveMaxRadius) mu_incubationWaveTick = -1;
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void captureHealth(DamageSource source, float amount, CallbackInfoReturnable<Boolean> ci) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (self.level().isClientSide()) return;
        if ("lightningBolt".equals(source.getMsgId()) && self.getEffect(MythicEffects.STATIC_FIELD) != null) {
            ci.setReturnValue(false);
            return;
        }
        mu_healthBefore = self.getHealth();
    }

    @Inject(method = "hurt", at = @At("TAIL"))
    private void onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (self.level().isClientSide() || !cir.getReturnValue()) return;

        String msgId = source.getMsgId();
        if ("deflecting".equals(msgId) || "percentage".equals(msgId) || "topaz_shock".equals(msgId)
            || "peridot_incubation".equals(msgId) || "ice_shield_mark_burst".equals(msgId) || "ice_shield_reflect".equals(msgId)
            || "citrine_chain".equals(msgId) || "static_field".equals(msgId)) return;

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
            float shockRadius = Math.min(effectLevel * MythicStats.TOPAZ_ARMOR_SHOCK_RADIUS_PER_LEVEL, MythicStats.TOPAZ_ARMOR_SHOCK_MAX_RADIUS);

            mu_topazWaveTick = 0;
            mu_topazWaveMaxRadius = shockRadius;
            mu_topazWaveX = self.getX();
            mu_topazWaveY = self.getY() + 1.0;
            mu_topazWaveZ = self.getZ();

            applyShockInRange(serverLevel, self, null, shockRadius, effectLevel, self);
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
                    int effectiveLevel = MythicStats.TOPAZ_TOOL_EFFECTIVE_LEVEL;
                    float shockRadius = Math.min(effectiveLevel * MythicStats.TOPAZ_ARMOR_SHOCK_RADIUS_PER_LEVEL, MythicStats.TOPAZ_ARMOR_SHOCK_MAX_RADIUS);
                    emitTopazInstantBurst(serverLevel, self.getX(), self.getY() + 1.0, self.getZ(), shockRadius);
                    applyShockInRange(serverLevel, self, directAttacker, shockRadius, effectiveLevel, directAttacker);
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

            if (isPeridotTool(weapon)) {
                int toolLevel = MythicStats.LETHAL_INCUBATION_TOOL_LEVEL;
                int duration = toolLevel * MythicStats.LETHAL_INCUBATION_DURATION_TICKS_PER_LEVEL;
                self.addEffect(new MobEffectInstance(MythicEffects.LETHAL_INCUBATION, duration, toolLevel - 1));
            }

            if (isAquamarineTool(weapon) && self.level() instanceof ServerLevel serverLevel) {
                self.addEffect(new MobEffectInstance(MythicEffects.FREEZE, MythicStats.AQUAMARINE_TOOL_FREEZE_TICKS, 0));
                self.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, MythicStats.AQUAMARINE_TOOL_SLOWNESS_DURATION_TICKS, MythicStats.AQUAMARINE_TOOL_SLOWNESS_AMPLIFIER));
                self.addEffect(new MobEffectInstance(MythicEffects.ICE_SHIELD_MARK, MythicStats.ICE_SHIELD_MARK_DURATION_TICKS, 0));
                MythicState.ICE_SHIELD_SOURCE.put(self, directAttacker);
                emitAquamarineHitParticles(serverLevel, self);
            }

            if (isCitrineTool(weapon) && self.level() instanceof ServerLevel serverLevel) {
                self.addEffect(new MobEffectInstance(MythicEffects.CHARGED, MythicStats.CITRINE_TOOL_CHARGED_DURATION_TICKS, 0));
                self.addEffect(new MobEffectInstance(MythicEffects.FREEZE, MythicStats.CITRINE_TOOL_FREEZE_TICKS, 0));
                if (actualDamage > 0)
                    applyChainLightning(serverLevel, self, directAttacker, actualDamage);
            }

            if (isJadeTool(weapon)) {
                directAttacker.addEffect(new MobEffectInstance(MythicEffects.BOUNCER, MythicStats.JADE_TOOL_BOUNCER_DURATION_TICKS, 0));
                if (self.getRandom().nextFloat() < MythicStats.JADE_TOOL_TELEPORT_CHANCE)
                    randomTeleportNear(directAttacker);
                if (self.level() instanceof ServerLevel jadeSLevel)
                    emitJadeBouncerParticles(jadeSLevel, directAttacker);
            }

            MobEffectInstance iceShield = self.getEffect(MythicEffects.ICE_SHIELD);
            if (iceShield != null) {
                int level = iceShield.getAmplifier() + 1;
                directAttacker.hurt(MUDamageTypes.iceShieldReflect(self), level * MythicStats.ICE_SHIELD_MAGIC_DAMAGE_PER_LEVEL);
                int slownessDur = Math.min(level * MythicStats.ICE_SHIELD_SLOWNESS_DURATION_TICKS_PER_LEVEL, MythicStats.ICE_SHIELD_SLOWNESS_MAX_DURATION_TICKS);
                int slownessAmp = Math.min(level - 1, MythicStats.ICE_SHIELD_SLOWNESS_MAX_AMPLIFIER);
                directAttacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, slownessDur, slownessAmp));
            }

            MobEffectInstance staticField = self.getEffect(MythicEffects.STATIC_FIELD);
            if (staticField != null && self.level() instanceof ServerLevel sfLevel) {
                int sfEffectLevel = staticField.getAmplifier() + 1;
                float lightningChance = Math.min(sfEffectLevel * MythicStats.STATIC_FIELD_LIGHTNING_CHANCE_PER_LEVEL, MythicStats.STATIC_FIELD_MAX_LIGHTNING_CHANCE);
                if (self.getRandom().nextFloat() < lightningChance) {
                    LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(sfLevel);
                    if (bolt != null) {
                        bolt.moveTo(directAttacker.getX(), directAttacker.getY(), directAttacker.getZ());
                        sfLevel.addFreshEntity(bolt);
                    }
                }
            }
        }
    }

    @Inject(method = "jumpFromGround", at = @At("TAIL"))
    private void applyJadeJumpBoost(CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;
        float extra = 0f;
        MobEffectInstance jadeAura = self.getEffect(MythicEffects.JADE_AURA);
        if (jadeAura != null)
            extra += (jadeAura.getAmplifier() + 1) * MythicStats.JADE_AURA_JUMP_PER_LEVEL;
        if (self.getEffect(MythicEffects.BOUNCER) != null)
            extra += MythicStats.JADE_TOOL_BOUNCER_JUMP_BOOST;
        if (extra > 0) {
            Vec3 v = self.getDeltaMovement();
            self.setDeltaMovement(v.x, v.y + extra, v.z);
        }
    }

    @Inject(method = "die", at = @At("HEAD"))
    private void onDeath(DamageSource source, CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (self.level().isClientSide()) return;
        if (!(self.level() instanceof ServerLevel serverLevel)) return;

        MobEffectInstance incub = self.getEffect(MythicEffects.LETHAL_INCUBATION);
        if (incub != null) {
            int effectLevel = incub.getAmplifier() + 1;
            applyLethalIncubationBurst(serverLevel, self, effectLevel);
            mu_incubationWaveTick = 0;
            mu_incubationWaveMaxRadius = MythicStats.LETHAL_INCUBATION_SHOCK_RADIUS;
            mu_incubationWaveX = self.getX();
            mu_incubationWaveY = self.getY() + 1.0;
            mu_incubationWaveZ = self.getZ();
            mu_incubationWaveLevel = effectLevel;
        }

        if (self.getEffect(MythicEffects.ICE_SHIELD_MARK) != null) {
            LivingEntity icySource = MythicState.ICE_SHIELD_SOURCE.get(self);
            applyIceShieldMarkBurst(serverLevel, self, icySource);
            MythicState.ICE_SHIELD_SOURCE.remove(self);
        }

        if (self.getEffect(MythicEffects.CHARGED) != null) {
            int count = MythicStats.CITRINE_CHARGED_LIGHTNING_MIN +
                self.getRandom().nextInt(MythicStats.CITRINE_CHARGED_LIGHTNING_MAX - MythicStats.CITRINE_CHARGED_LIGHTNING_MIN + 1);
            for (int i = 0; i < count; i++) {
                double ox = (self.getRandom().nextDouble() - 0.5) * 2.0 * MythicStats.CITRINE_CHARGED_LIGHTNING_SPREAD;
                double oz = (self.getRandom().nextDouble() - 0.5) * 2.0 * MythicStats.CITRINE_CHARGED_LIGHTNING_SPREAD;
                LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
                if (bolt != null) {
                    bolt.moveTo(self.getX() + ox, self.getY(), self.getZ() + oz);
                    serverLevel.addFreshEntity(bolt);
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
    private static Vector3f colorFromHex(int hex) {
        return new Vector3f((hex >> 16 & 0xFF) / 255f, (hex >> 8 & 0xFF) / 255f, (hex & 0xFF) / 255f);
    }

    @Unique
    private static void emitTopazWaveRing(ServerLevel level, double cx, double cy, double cz, float radius) {
        DustParticleOptions c1 = new DustParticleOptions(colorFromHex(MythicAnims.TOPAZ_COLOR_1), MythicAnims.TOPAZ_ARMOR_WAVE_PARTICLE_SCALE);
        DustParticleOptions c2 = new DustParticleOptions(colorFromHex(MythicAnims.TOPAZ_COLOR_2), MythicAnims.TOPAZ_ARMOR_WAVE_PARTICLE_SCALE);
        DustParticleOptions c3 = new DustParticleOptions(colorFromHex(MythicAnims.TOPAZ_COLOR_3), MythicAnims.TOPAZ_ARMOR_WAVE_PARTICLE_SCALE);
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
    private static void emitMiasmaWaveRing(ServerLevel level, double cx, double cy, double cz, float radius, int waveLevel) {
        DustParticleOptions c1 = new DustParticleOptions(new Vector3f(0.247f, 0.533f, 0.055f), MythicAnims.MIASMA_WAVE_PARTICLE_SCALE);
        DustParticleOptions c2 = new DustParticleOptions(new Vector3f(0.557f, 0.839f, 0.078f), MythicAnims.MIASMA_WAVE_PARTICLE_SCALE);
        DustParticleOptions c3 = new DustParticleOptions(new Vector3f(0.894f, 0.961f, 0.224f), MythicAnims.MIASMA_WAVE_PARTICLE_SCALE);
        int step = MythicAnims.MIASMA_WAVE_STEP_DEGREES;
        int segment = step * 3;
        int ppp = Math.max(1, waveLevel * MythicAnims.MIASMA_WAVE_PARTICLES_PER_LEVEL);
        for (int angle = 0; angle < 360; angle += step) {
            double rad = Math.toRadians(angle);
            double px = cx + radius * Math.cos(rad);
            double pz = cz + radius * Math.sin(rad);
            int mod = angle % segment;
            DustParticleOptions color = mod < step ? c1 : mod < step * 2 ? c2 : c3;
            level.sendParticles(color, px, cy, pz, ppp, 0, 0.08, 0, 0);
        }
    }

    @Unique
    private static void emitLethalIncubationWaveRing(ServerLevel level, double cx, double cy, double cz, float radius, int waveLevel) {
        DustParticleOptions c1 = new DustParticleOptions(new Vector3f(0.247f, 0.533f, 0.055f), MythicAnims.LETHAL_INCUBATION_WAVE_PARTICLE_SCALE);
        DustParticleOptions c2 = new DustParticleOptions(new Vector3f(0.557f, 0.839f, 0.078f), MythicAnims.LETHAL_INCUBATION_WAVE_PARTICLE_SCALE);
        DustParticleOptions c3 = new DustParticleOptions(new Vector3f(0.894f, 0.961f, 0.224f), MythicAnims.LETHAL_INCUBATION_WAVE_PARTICLE_SCALE);
        int step = MythicAnims.LETHAL_INCUBATION_WAVE_STEP_DEGREES;
        int segment = step * 3;
        int ppp = Math.max(1, waveLevel * MythicAnims.LETHAL_INCUBATION_WAVE_PARTICLES_PER_LEVEL);
        for (int angle = 0; angle < 360; angle += step) {
            double rad = Math.toRadians(angle);
            double px = cx + radius * Math.cos(rad);
            double pz = cz + radius * Math.sin(rad);
            int mod = angle % segment;
            DustParticleOptions color = mod < step ? c1 : mod < step * 2 ? c2 : c3;
            level.sendParticles(color, px, cy, pz, ppp, 0, 0.08, 0, 0);
        }
    }

    @Unique
    private static void emitIceShieldMarkBurst(ServerLevel level, double cx, double cy, double cz, float maxRadius) {
        DustParticleOptions dust = new DustParticleOptions(new Vector3f(0.051f, 0.612f, 0.757f), MythicAnims.AQUAMARINE_PARTICLE_SCALE);
        float spacing = 0.7f;
        int step = 8;
        for (float r = spacing; r <= maxRadius; r += spacing) {
            for (int angle = 0; angle < 360; angle += step) {
                double rad = Math.toRadians(angle);
                level.sendParticles(dust, cx + r * Math.cos(rad), cy, cz + r * Math.sin(rad), 1, 0, 0.15, 0, 0);
            }
        }
    }

    @Unique
    private static void emitCitrineChainParticles(ServerLevel level, LivingEntity from, LivingEntity to) {
        DustParticleOptions dust = new DustParticleOptions(colorFromHex(MythicAnims.CITRINE_COLOR), MythicAnims.CITRINE_CHAIN_PARTICLE_SCALE);
        double fx = from.getX(), fy = from.getY() + from.getBbHeight() * 0.5, fz = from.getZ();
        double tx = to.getX(), ty = to.getY() + to.getBbHeight() * 0.5, tz = to.getZ();
        int count = MythicAnims.CITRINE_CHAIN_PARTICLE_COUNT;
        for (int i = 0; i < count; i++) {
            double t = (double) i / count;
            level.sendParticles(dust, fx + (tx - fx) * t, fy + (ty - fy) * t, fz + (tz - fz) * t, 1, 0, 0, 0, 0);
        }
    }

    @Unique
    private static void emitStaticFieldParticles(ServerLevel level, LivingEntity self, float radius, int waveLevel) {
        DustParticleOptions dust = new DustParticleOptions(colorFromHex(MythicAnims.CITRINE_COLOR), MythicAnims.CITRINE_STATIC_FIELD_PARTICLE_SCALE);
        int count = waveLevel * MythicAnims.CITRINE_STATIC_FIELD_PARTICLES_PER_TICK_PER_LEVEL;
        double cx = self.getX(), cy = self.getY() + self.getBbHeight() * 0.5, cz = self.getZ();
        for (int i = 0; i < count; i++) {
            double theta = self.getRandom().nextDouble() * 2 * Math.PI;
            double phi = Math.acos(2 * self.getRandom().nextDouble() - 1);
            double px = cx + radius * Math.sin(phi) * Math.cos(theta);
            double py = cy + radius * Math.cos(phi);
            double pz = cz + radius * Math.sin(phi) * Math.sin(theta);
            level.sendParticles(dust, px, py, pz, 1, 0, 0, 0, 0);
        }
    }

    @Unique
    private static void emitAquamarineHitParticles(ServerLevel level, LivingEntity entity) {
        DustParticleOptions dust = new DustParticleOptions(new Vector3f(0.051f, 0.612f, 0.757f), MythicAnims.AQUAMARINE_PARTICLE_SCALE);
        level.sendParticles(dust, entity.getX(), entity.getY() + entity.getBbHeight() * 0.5, entity.getZ(), 12, 0.3, 0.4, 0.3, 0);
    }

    @Unique
    private static void applyLethalIncubationBurst(ServerLevel level, LivingEntity center, int effectLevel) {
        float radius = MythicStats.LETHAL_INCUBATION_SHOCK_RADIUS;
        int poisonAmplifier = effectLevel - 1;
        int poisonDuration = effectLevel * MythicStats.LETHAL_INCUBATION_POISON_DURATION_TICKS_PER_LEVEL;
        AABB bb = new AABB(center.getX() - radius, center.getY() - radius, center.getZ() - radius,
            center.getX() + radius, center.getY() + radius, center.getZ() + radius);
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, bb)) {
            if (entity == center) continue;
            if (entity.distanceTo(center) > radius) continue;
            entity.hurt(MUDamageTypes.peridotIncubation(center), MythicStats.LETHAL_INCUBATION_SHOCK_DAMAGE);
            entity.addEffect(new MobEffectInstance(MobEffects.POISON, poisonDuration, poisonAmplifier));
        }
    }

    @Unique
    private static void applyIceShieldMarkBurst(ServerLevel level, LivingEntity center, LivingEntity excluded) {
        float radius = MythicStats.ICE_SHIELD_MARK_BURST_RADIUS;
        AABB bb = new AABB(center.getX() - radius, center.getY() - radius, center.getZ() - radius,
            center.getX() + radius, center.getY() + radius, center.getZ() + radius);
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, bb)) {
            if (entity == center) continue;
            if (excluded != null && entity == excluded) continue;
            if (entity.distanceTo(center) > radius) continue;
            entity.hurt(MUDamageTypes.iceShieldMarkBurst(center), MythicStats.ICE_SHIELD_MARK_BURST_DAMAGE);
            entity.addEffect(new MobEffectInstance(MythicEffects.FREEZE, MythicStats.ICE_SHIELD_MARK_BURST_FREEZE_TICKS, 0));
        }
        emitIceShieldMarkBurst(level, center.getX(), center.getY() + 1.0, center.getZ(), radius);
    }

    @Unique
    private static void spawnMiasmaCloud(ServerLevel level, LivingEntity center, int peridotLevel) {
        float radius = Math.min(peridotLevel * MythicStats.MIASMA_CLOUD_RADIUS_PER_LEVEL, MythicStats.MIASMA_CLOUD_MAX_RADIUS);
        int poisonAmplifier = Math.min(peridotLevel - 1, MythicStats.MIASMA_POISON_MAX_AMPLIFIER);
        int poisonDuration = peridotLevel * MythicStats.MIASMA_POISON_DURATION_TICKS_PER_LEVEL;
        int cloudDuration = peridotLevel * MythicStats.MIASMA_CLOUD_DURATION_TICKS_PER_LEVEL;
        AreaEffectCloud cloud = new AreaEffectCloud(level, center.getX(), center.getY(), center.getZ());
        cloud.setOwner(center);
        cloud.setRadius(radius);
        cloud.setDuration(cloudDuration);
        cloud.setRadiusPerTick(-cloud.getRadius() / cloud.getDuration());
        cloud.setWaitTime(20);
        cloud.addEffect(new MobEffectInstance(MobEffects.POISON, poisonDuration, poisonAmplifier));
        cloud.setParticle(new DustParticleOptions(new Vector3f(0.247f, 0.533f, 0.055f), 1.0f));
        level.addFreshEntity(cloud);
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
    private static void applyChainLightning(ServerLevel level, LivingEntity origin, LivingEntity excluded, float damage) {
        float chainDamage = damage * MythicStats.CITRINE_TOOL_CHAIN_FRACTION;
        float range = MythicStats.CITRINE_TOOL_CHAIN_RANGE;
        AABB bb = new AABB(origin.getX() - range, origin.getY() - range, origin.getZ() - range,
            origin.getX() + range, origin.getY() + range, origin.getZ() + range);
        List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, bb);
        nearby.sort(Comparator.comparingDouble(e -> e.distanceTo(origin)));
        int count = 0;
        for (LivingEntity target : nearby) {
            if (count >= MythicStats.CITRINE_TOOL_CHAIN_TARGETS) break;
            if (target == origin || target == excluded) continue;
            target.hurt(MUDamageTypes.citrineChain(excluded), chainDamage);
            emitCitrineChainParticles(level, origin, target);
            count++;
        }
    }

    @Unique
    private static void applyShockInRange(ServerLevel level, LivingEntity center, LivingEntity excluded, float radius, int shockLevel, LivingEntity source) {
        float damage = shockLevel * MythicStats.TOPAZ_SHOCK_DAMAGE_PER_LEVEL;
        float knockback = shockLevel * MythicStats.TOPAZ_SHOCK_KNOCKBACK_PER_LEVEL;
        AABB bb = new AABB(center.getX() - radius, center.getY() - radius, center.getZ() - radius,
            center.getX() + radius, center.getY() + radius, center.getZ() + radius);
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, bb)) {
            if (entity == center || (excluded != null && entity == excluded)) continue;
            if (entity.distanceTo(center) <= radius) {
                entity.hurt(MUDamageTypes.topazShock(source), damage);
                entity.knockback(knockback, center.getX() - entity.getX(), center.getZ() - entity.getZ());
            }
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

    @Unique
    private static boolean isPeridotTool(Item item) {
        return item == MythicItems.PERIDOT_SWORD || item == MythicItems.PERIDOT_PICKAXE
            || item == MythicItems.PERIDOT_AXE || item == MythicItems.PERIDOT_SHOVEL || item == MythicItems.PERIDOT_HOE;
    }

    @Unique
    private static boolean isCitrineTool(Item item) {
        return item == MythicItems.CITRINE_SWORD || item == MythicItems.CITRINE_PICKAXE
            || item == MythicItems.CITRINE_AXE || item == MythicItems.CITRINE_SHOVEL || item == MythicItems.CITRINE_HOE;
    }

    @Unique
    private static boolean isAquamarineTool(Item item) {
        return item == MythicItems.AQUAMARINE_SWORD || item == MythicItems.AQUAMARINE_PICKAXE
            || item == MythicItems.AQUAMARINE_AXE || item == MythicItems.AQUAMARINE_SHOVEL || item == MythicItems.AQUAMARINE_HOE;
    }

    @Unique
    private static boolean isJadeTool(Item item) {
        return item == MythicItems.JADE_SWORD || item == MythicItems.JADE_PICKAXE
            || item == MythicItems.JADE_AXE || item == MythicItems.JADE_SHOVEL || item == MythicItems.JADE_HOE;
    }

    @Unique
    private static void spawnJadeTrailCloud(ServerLevel level, LivingEntity owner, int jadeLevel) {
        int trailLevel = (jadeLevel + 1) / 2; // ceiling(jadeLevel / 2)
        AreaEffectCloud cloud = new AreaEffectCloud(level, owner.getX(), owner.getY(), owner.getZ());
        cloud.setOwner(owner);
        cloud.setRadius(MythicStats.JADE_TRAIL_RADIUS);
        cloud.setDuration(MythicStats.JADE_TRAIL_DURATION_TICKS);
        cloud.setRadiusPerTick(0);
        cloud.setRadiusOnUse(0);
        cloud.setWaitTime(0);
        cloud.addEffect(new MobEffectInstance(MythicEffects.JADE_AURA, MythicStats.JADE_TRAIL_EFFECT_DURATION_TICKS, trailLevel - 1));
        cloud.setParticle(new DustParticleOptions(colorFromHex(MythicAnims.JADE_COLOR_2), MythicAnims.JADE_TRAIL_PARTICLE_SCALE));
        level.addFreshEntity(cloud);
    }

    @Unique
    private static void emitJadeBouncerParticles(ServerLevel level, LivingEntity entity) {
        DustParticleOptions c1 = new DustParticleOptions(colorFromHex(MythicAnims.JADE_COLOR_2), MythicAnims.JADE_BOUNCER_PARTICLE_SCALE);
        DustParticleOptions c2 = new DustParticleOptions(colorFromHex(MythicAnims.JADE_COLOR_3), MythicAnims.JADE_BOUNCER_PARTICLE_SCALE);
        double cx = entity.getX(), cy = entity.getY() + entity.getBbHeight() * 0.5, cz = entity.getZ();
        int count = MythicAnims.JADE_BOUNCER_PARTICLE_COUNT;
        for (int i = 0; i < count; i++) {
            level.sendParticles(i % 2 == 0 ? c1 : c2, cx, cy, cz, 1, 0.25, 0.35, 0.25, 0);
        }
    }
}
