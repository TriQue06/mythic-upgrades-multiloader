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
import net.minecraft.world.entity.TamableAnimal;
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
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

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
    @Unique private float mu_jadeJumpFallCredit = 0f;
    @Unique private int mu_miasmaTick = 0;
    @Unique private float mu_healthBefore = 0f;
    @Unique private float mu_topazFallBlocked = 0f;
    @Unique private Map<UUID, Integer> mu_staticFieldStacks = new HashMap<>();
    @Unique private Map<UUID, Integer> mu_staticFieldLastHitTick = new HashMap<>();
    // each entry: {createdTick, x, y, z, lingerTicks, auraLevel}
    @Unique private List<float[]> mu_jadeLingerTrail = new ArrayList<>();

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

    @Unique private int mu_iceBombWaveTick = -1;
    @Unique private float mu_iceBombWaveMaxRadius = 0f;
    @Unique private double mu_iceBombWaveX = 0, mu_iceBombWaveY = 0, mu_iceBombWaveZ = 0;

    @Unique private boolean mu_deathHadLethalIncubation = false;
    @Unique private int mu_deathLethalIncubationLevel = 0;
    @Unique private boolean mu_deathHadIceBomb = false;
    @Unique private boolean mu_deathHadCharged = false;

    @Inject(method = "tick", at = @At("TAIL"))
    private void checkArmorEffects(CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (self.level().isClientSide()) return;
        if (self instanceof ArmorStand) return;

        int sapphireLevel = 0;
        if (self.getItemBySlot(EquipmentSlot.HEAD).getItem() == MythicItems.SAPPHIRE_HELMET) sapphireLevel += MythicStats.SAPPHIRE_HELMET_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.CHEST).getItem() == MythicItems.SAPPHIRE_CHESTPLATE) sapphireLevel += MythicStats.SAPPHIRE_CHESTPLATE_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.LEGS).getItem() == MythicItems.SAPPHIRE_LEGGINGS) sapphireLevel += MythicStats.SAPPHIRE_LEGGINGS_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.FEET).getItem() == MythicItems.SAPPHIRE_BOOTS) sapphireLevel += MythicStats.SAPPHIRE_BOOTS_LEVELS;
        if (sapphireLevel != mu_sapphireLevel || (sapphireLevel > 0 && self.getEffect(MythicEffects.DAMAGE_DEFLECTION) == null)) {
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
        if (ametrineLevel != mu_ametrineLevel || (ametrineLevel > 0 && self.getEffect(MythicEffects.ARCANE_AURA) == null)) {
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
        if (topazLevel != mu_topazLevel || (topazLevel > 0 && self.getEffect(MythicEffects.TOPAZ_REACTION) == null)) {
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
        if (rubyLevel != mu_rubyLevel || (rubyLevel > 0 && self.getEffect(MythicEffects.BLOOD_THIRST) == null)) {
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
        if (peridotLevel != mu_peridotLevel || (peridotLevel > 0 && self.getEffect(MythicEffects.MIASMA) == null)) {
            mu_peridotLevel = peridotLevel;
            self.removeEffect(MythicEffects.MIASMA);
            if (peridotLevel > 0)
                self.addEffect(new MobEffectInstance(MythicEffects.MIASMA, -1, peridotLevel - 1, false, false, true));
        }
        // Miasma behavior is effect-driven: works whether from armor, potion, or command
        MobEffectInstance miasmaEff = self.getEffect(MythicEffects.MIASMA);
        if (miasmaEff != null) {
            int miasmaLevel = miasmaEff.getAmplifier() + 1;
            mu_miasmaTick++;
            if (mu_miasmaTick >= MythicStats.MIASMA_INTERVAL_TICKS && self.level() instanceof ServerLevel serverLevel) {
                mu_miasmaTick = 0;
                applyMiasmaPoison(serverLevel, self, miasmaLevel);
                mu_miasmaWaveTick = 0;
                mu_miasmaWaveMaxRadius = Math.min(miasmaLevel * MythicStats.MIASMA_CLOUD_RADIUS_PER_LEVEL, MythicStats.MIASMA_CLOUD_MAX_RADIUS);
                mu_miasmaWaveX = self.getX();
                mu_miasmaWaveY = self.getY() + 1.0;
                mu_miasmaWaveZ = self.getZ();
                mu_miasmaWaveLevel = miasmaLevel;
            }
        } else {
            mu_miasmaTick = 0;
        }

        int aquamarineLevel = 0;
        if (self.getItemBySlot(EquipmentSlot.HEAD).getItem() == MythicItems.AQUAMARINE_HELMET) aquamarineLevel += MythicStats.AQUAMARINE_HELMET_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.CHEST).getItem() == MythicItems.AQUAMARINE_CHESTPLATE) aquamarineLevel += MythicStats.AQUAMARINE_CHESTPLATE_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.LEGS).getItem() == MythicItems.AQUAMARINE_LEGGINGS) aquamarineLevel += MythicStats.AQUAMARINE_LEGGINGS_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.FEET).getItem() == MythicItems.AQUAMARINE_BOOTS) aquamarineLevel += MythicStats.AQUAMARINE_BOOTS_LEVELS;
        if (aquamarineLevel != mu_aquamarineLevel || (aquamarineLevel > 0 && self.getEffect(MythicEffects.ICE_SHIELD) == null)) {
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
        if (citrineLevel != mu_citrineLevel || (citrineLevel > 0 && self.getEffect(MythicEffects.STATIC_FIELD) == null)) {
            mu_citrineLevel = citrineLevel;
            mu_staticFieldStacks.clear();
            mu_staticFieldLastHitTick.clear();
            self.removeEffect(MythicEffects.STATIC_FIELD);
            if (citrineLevel > 0)
                self.addEffect(new MobEffectInstance(MythicEffects.STATIC_FIELD, -1, citrineLevel - 1, false, false, true));
        }
        // Static field behavior is effect-driven: works whether from armor, potion, or command
        MobEffectInstance staticFieldEff = self.getEffect(MythicEffects.STATIC_FIELD);
        if (staticFieldEff != null && self.level() instanceof ServerLevel citrineSLevel) {
            int staticLevel = staticFieldEff.getAmplifier() + 1;
            float fieldRadius = Math.min(staticLevel * MythicStats.STATIC_FIELD_RADIUS_PER_LEVEL, MythicStats.STATIC_FIELD_MAX_RADIUS);
            if (self.tickCount % MythicAnims.CITRINE_STATIC_FIELD_PARTICLE_INTERVAL == 0 && !self.isCrouching())
                emitStaticFieldParticles(citrineSLevel, self, fieldRadius, staticLevel);
            if (self.tickCount % 20 == 0) {
                float fieldDamage = Math.min(staticLevel * MythicStats.STATIC_FIELD_DAMAGE_PER_LEVEL_PER_SECOND, MythicStats.STATIC_FIELD_MAX_DAMAGE_PER_SECOND);
                AABB fbb = new AABB(self.getX() - fieldRadius, self.getY() - fieldRadius, self.getZ() - fieldRadius,
                    self.getX() + fieldRadius, self.getY() + fieldRadius, self.getZ() + fieldRadius);
                int currentTick = self.tickCount;
                mu_staticFieldLastHitTick.entrySet().removeIf(e -> {
                    if (currentTick - e.getValue() > MythicStats.STATIC_FIELD_STACK_RESET_TICKS) {
                        mu_staticFieldStacks.remove(e.getKey());
                        return true;
                    }
                    return false;
                });
                for (LivingEntity entity : citrineSLevel.getEntitiesOfClass(LivingEntity.class, fbb)) {
                    if (entity == self) continue;
                    if (entity.distanceTo(self) <= fieldRadius) {
                        UUID entityId = entity.getUUID();
                        int stacks = mu_staticFieldStacks.getOrDefault(entityId, 0);
                        float multiplier = (float)Math.pow(1.0 + MythicStats.STATIC_FIELD_STACK_DAMAGE_INCREASE, stacks);
                        entity.hurt(MUDamageTypes.staticField(self), fieldDamage * multiplier);
                        if (stacks < MythicStats.STATIC_FIELD_MAX_STACKS)
                            mu_staticFieldStacks.put(entityId, stacks + 1);
                        mu_staticFieldLastHitTick.put(entityId, currentTick);
                    }
                }
            }
        } else if (staticFieldEff == null) {
            mu_staticFieldStacks.clear();
            mu_staticFieldLastHitTick.clear();
        }

        int jadeLevel = 0;
        if (self.getItemBySlot(EquipmentSlot.HEAD).getItem() == MythicItems.JADE_HELMET) jadeLevel += MythicStats.JADE_HELMET_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.CHEST).getItem() == MythicItems.JADE_CHESTPLATE) jadeLevel += MythicStats.JADE_CHESTPLATE_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.LEGS).getItem() == MythicItems.JADE_LEGGINGS) jadeLevel += MythicStats.JADE_LEGGINGS_LEVELS;
        if (self.getItemBySlot(EquipmentSlot.FEET).getItem() == MythicItems.JADE_BOOTS) jadeLevel += MythicStats.JADE_BOOTS_LEVELS;
        if (jadeLevel != mu_jadeLevel || (jadeLevel > 0 && self.getEffect(MythicEffects.JADE_AURA) == null)) {
            mu_jadeLevel = jadeLevel;
            self.removeEffect(MythicEffects.JADE_AURA);
            if (jadeLevel > 0)
                self.addEffect(new MobEffectInstance(MythicEffects.JADE_AURA, -1, jadeLevel - 1, false, false, true));
        }
        // Linger trail processing runs regardless of current effect state so laid trail persists
        if (!mu_jadeLingerTrail.isEmpty() && self.level() instanceof ServerLevel jadeLingerLevel) {
            mu_jadeLingerTrail.removeIf(p -> self.tickCount - p[0] >= p[4]);
            for (float[] p : mu_jadeLingerTrail) {
                if (self.tickCount % 4 == 0)
                    emitJadeLingerParticles(jadeLingerLevel, p[1], p[2], p[3]);
                float cr = MythicStats.JADE_TRAIL_LINGER_CONTACT_RADIUS;
                AABB cbb = new AABB(p[1] - cr, p[2] - cr, p[3] - cr, p[1] + cr, p[2] + cr, p[3] + cr);
                for (LivingEntity touched : jadeLingerLevel.getEntitiesOfClass(LivingEntity.class, cbb)) {
                    if (touched == self) continue;
                    if (Math.sqrt(Math.pow(touched.getX()-p[1],2)+Math.pow(touched.getZ()-p[3],2)) > cr) continue;
                    int aLvl = (int)p[5];
                    int dur = MythicStats.JADE_TRAIL_CONTACT_EFFECT_DURATION_TICKS;
                    int spAmp = Math.min(aLvl - 1, 9);
                    int jpAmp = Math.min(aLvl / 2, 9);
                    touched.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, dur, spAmp, false, true, false));
                    touched.addEffect(new MobEffectInstance(MobEffects.JUMP, dur, jpAmp, false, true, false));
                }
            }
        }
        // Jade trail behavior is effect-driven: works whether from armor, potion, or command
        MobEffectInstance jadeAuraActive = self.getEffect(MythicEffects.JADE_AURA);
        if (jadeAuraActive != null && self.tickCount % MythicStats.JADE_TRAIL_INTERVAL_TICKS == 0
                && self.level() instanceof ServerLevel jadeSLevel) {
            int auraLevel = jadeAuraActive.getAmplifier() + 1;
            applyJadeTrail(jadeSLevel, self, auraLevel);
            int lingerTicks = MythicStats.JADE_TRAIL_DURATION_TICKS;
            mu_jadeLingerTrail.add(new float[]{(float)self.tickCount, (float)self.getX(), (float)self.getY(), (float)self.getZ(), (float)lingerTicks, (float)auraLevel});
        }

        if (jadeAuraActive != null && self.tickCount % 4 == 0 && self.level() instanceof ServerLevel jadeAmbientLevel) {
            emitJadeAuraAmbientParticles(jadeAmbientLevel, self, jadeAuraActive.getAmplifier() + 1);
        }

        if (self.getEffect(MythicEffects.FREEZE) != null && self.tickCount % 3 == 0 && self.level() instanceof ServerLevel freezeLevel) {
            emitIceFreezeOrbitParticles(freezeLevel, self);
        }

        float[] pendingWave = MythicState.TOPAZ_PENDING_WAVES.remove(self);
        if (pendingWave != null) {
            mu_topazWaveTick = 0;
            mu_topazWaveMaxRadius = pendingWave[3];
            mu_topazWaveX = pendingWave[0];
            mu_topazWaveY = pendingWave[1];
            mu_topazWaveZ = pendingWave[2];
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

        if (mu_iceBombWaveTick >= 0 && self.level() instanceof ServerLevel ibLevel) {
            for (int i = 0; i < MythicAnims.ICE_BOMB_WAVE_COUNT; i++) {
                float r = (mu_iceBombWaveTick - i * MythicAnims.ICE_BOMB_WAVE_GAP_TICKS) * MythicAnims.ICE_BOMB_WAVE_SPEED_PER_TICK;
                if (r > 0 && r <= mu_iceBombWaveMaxRadius)
                    emitIceBombWaveRing(ibLevel, mu_iceBombWaveX, mu_iceBombWaveY, mu_iceBombWaveZ, r);
            }
            mu_iceBombWaveTick++;
            float lastIbR = (mu_iceBombWaveTick - (MythicAnims.ICE_BOMB_WAVE_COUNT - 1) * MythicAnims.ICE_BOMB_WAVE_GAP_TICKS) * MythicAnims.ICE_BOMB_WAVE_SPEED_PER_TICK;
            if (lastIbR > mu_iceBombWaveMaxRadius) mu_iceBombWaveTick = -1;
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void captureHealth(DamageSource source, float amount, CallbackInfoReturnable<Boolean> ci) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (self.level().isClientSide()) return;
        mu_topazFallBlocked = 0f;
        if ("fall".equals(source.type().msgId())) {
            MobEffectInstance topazEff = self.getEffect(MythicEffects.TOPAZ_REACTION);
            if (topazEff != null) {
                int topazLvl = topazEff.getAmplifier() + 1;
                float reduction = Math.min(topazLvl * MythicStats.TOPAZ_FALL_REDUCTION_PER_LEVEL, MythicStats.TOPAZ_FALL_MAX_REDUCTION);
                mu_topazFallBlocked = amount * reduction;
            }
        }
        mu_healthBefore = self.getHealth();
        // Accumulate flags on every hit so multi-tick (lava/void) damage can't miss the window.
        MobEffectInstance liEffect = self.getEffect(MythicEffects.LETHAL_INCUBATION);
        if (liEffect != null) { mu_deathHadLethalIncubation = true; mu_deathLethalIncubationLevel = liEffect.getAmplifier() + 1; }
        if (self.getEffect(MythicEffects.ICE_BOMB) != null)          mu_deathHadIceBomb = true;
        if (self.getEffect(MythicEffects.CHARGED) != null)           mu_deathHadCharged = true;
    }

    @Inject(method = "hurt", at = @At("TAIL"))
    private void onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (self.level().isClientSide() || !cir.getReturnValue()) return;

        String msgId = source.type().msgId();
        if ("deflecting".equals(msgId) || "percentage".equals(msgId) || "topaz_shock".equals(msgId)
            || "peridot_incubation".equals(msgId) || "ice_shield_reflect".equals(msgId)
            || "citrine_chain".equals(msgId) || "static_field".equals(msgId) || "ice_bomb_burst".equals(msgId)) return;

        float actualDamage = mu_healthBefore - self.getHealth();

        if (actualDamage > 0) {
            MobEffectInstance deflection = self.getEffect(MythicEffects.DAMAGE_DEFLECTION);
            if (deflection != null) {
                Entity rawAttacker = source.getEntity();
                if (rawAttacker instanceof LivingEntity attacker) {
                    float reflectAmount = (deflection.getAmplifier() + 1) * MythicStats.DEFLECT_PER_LEVEL * actualDamage;
                    attacker.hurt(MUDamageTypes.deflecting(self), reflectAmount);
                    float bonusDamage = Math.min((deflection.getAmplifier() + 1) * MythicStats.DEFLECT_BONUS_DAMAGE_PER_LEVEL, MythicStats.DEFLECT_BONUS_DAMAGE_MAX);
                    attacker.hurt(MUDamageTypes.deflecting(self), bonusDamage);
                }
            }
        }

        if (mu_topazFallBlocked > 0 && !self.isDeadOrDying() && self.level() instanceof ServerLevel fallLevel) {
            MobEffectInstance topazForFall = self.getEffect(MythicEffects.TOPAZ_REACTION);
            if (topazForFall != null) {
                self.heal(mu_topazFallBlocked);
                int fallTopazLevel = topazForFall.getAmplifier() + 1;
                float fallShockRadius = Math.min(fallTopazLevel * MythicStats.TOPAZ_ARMOR_SHOCK_RADIUS_PER_LEVEL, MythicStats.TOPAZ_ARMOR_SHOCK_MAX_RADIUS);
                mu_topazWaveTick = 0;
                mu_topazWaveMaxRadius = fallShockRadius;
                mu_topazWaveX = self.getX();
                mu_topazWaveY = self.getY() + 1.0;
                mu_topazWaveZ = self.getZ();
                applyShockInRange(fallLevel, self, null, fallShockRadius, fallTopazLevel, self, true);
                fallLevel.playSound(null, self.getX(), self.getY(), self.getZ(), MythicAnims.TOPAZ_WAVE_SOUND_1, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_1);
                fallLevel.playSound(null, self.getX(), self.getY(), self.getZ(), MythicAnims.TOPAZ_WAVE_SOUND_2, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_2);
            }
            mu_topazFallBlocked = 0f;
        }

        if (actualDamage > 0 && self.level() instanceof ServerLevel btServerLevel) {
            float maxSearch = MythicStats.BLOOD_THIRST_MAX_RADIUS;
            AABB searchBB = new AABB(
                self.getX() - maxSearch, self.getY() - maxSearch, self.getZ() - maxSearch,
                self.getX() + maxSearch, self.getY() + maxSearch, self.getZ() + maxSearch
            );
            for (LivingEntity btEntity : btServerLevel.getEntitiesOfClass(LivingEntity.class, searchBB)) {
                if (btEntity == self) continue;
                if (btEntity instanceof ArmorStand) continue;
                MobEffectInstance btEff = btEntity.getEffect(MythicEffects.BLOOD_THIRST);
                if (btEff == null) continue;
                int btLevel = btEff.getAmplifier() + 1;
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
            int levitationDur = MythicStats.ARCANE_AURA_LEVITATION_DURATION_TICKS;

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

            applyShockInRange(serverLevel, self, null, shockRadius, effectLevel, self, true);
            serverLevel.playSound(null, self.getX(), self.getY(), self.getZ(), MythicAnims.TOPAZ_WAVE_SOUND_1, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_1);
            serverLevel.playSound(null, self.getX(), self.getY(), self.getZ(), MythicAnims.TOPAZ_WAVE_SOUND_2, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_2);
        }

        Entity directEntity = source.getDirectEntity();
        Entity causingEntity = source.getEntity();
        if (directEntity != null && directEntity == causingEntity && directEntity instanceof LivingEntity directAttacker) {
            Item weapon = directAttacker.getMainHandItem().getItem();

            if (isSapphireTool(weapon)) {
                float bonusDamage = Math.min(
                    self.getMaxHealth() * MythicStats.SAPPHIRE_TOOL_PERCENT_DAMAGE,
                    MythicStats.SAPPHIRE_TOOL_PERCENT_DAMAGE_CAP
                );
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
                    mu_topazWaveTick = 0;
                    mu_topazWaveMaxRadius = shockRadius;
                    mu_topazWaveX = self.getX();
                    mu_topazWaveY = self.getY() + 1.0;
                    mu_topazWaveZ = self.getZ();
                    applyShockInRange(serverLevel, self, null, shockRadius, effectiveLevel, directAttacker, false);
                    serverLevel.playSound(null, self.getX(), self.getY(), self.getZ(), MythicAnims.TOPAZ_WAVE_SOUND_1, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_1);
                    serverLevel.playSound(null, self.getX(), self.getY(), self.getZ(), MythicAnims.TOPAZ_WAVE_SOUND_2, SoundSource.PLAYERS, MythicAnims.TOPAZ_WAVE_SOUND_VOLUME, MythicAnims.TOPAZ_WAVE_SOUND_PITCH_2);
                } else {
                    MythicState.TOPAZ_TOOL_HIT_COUNTS.put(directAttacker, count);
                }
            }

            if (isRubyTool(weapon) && actualDamage > 0) {
                directAttacker.heal(actualDamage * MythicStats.RUBY_TOOL_LIFESTEAL_FRACTION + 1.0f);
                if (self.level() instanceof ServerLevel serverLevel) {
                    emitLifestealParticles(serverLevel, self, directAttacker);
                }
            }

            if (actualDamage > 0) {
                MobEffectInstance btEffD = directAttacker.getEffect(MythicEffects.BLOOD_THIRST);
                if (btEffD != null) {
                    int btRubyLevel = btEffD.getAmplifier() + 1;
                    float healFrac = Math.min(btRubyLevel * MythicStats.BLOOD_THIRST_HEAL_FRACTION_PER_LEVEL, MythicStats.BLOOD_THIRST_MAX_HEAL_FRACTION);
                    directAttacker.heal(actualDamage * healFrac);
                    if (self.level() instanceof ServerLevel btDServerLevel)
                        emitBloodThirstParticles(btDServerLevel, self, directAttacker, btRubyLevel);
                }
            }

            if (isPeridotTool(weapon)) {
                self.addEffect(new MobEffectInstance(MythicEffects.LETHAL_INCUBATION, MythicStats.LETHAL_INCUBATION_TOOL_DURATION_TICKS, 2));
            }

            if (isAquamarineTool(weapon) && self.level() instanceof ServerLevel serverLevel) {
                self.addEffect(new MobEffectInstance(MythicEffects.FREEZE, MythicStats.AQUAMARINE_TOOL_FREEZE_TICKS, 0));
                self.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, MythicStats.AQUAMARINE_TOOL_SLOWNESS_DURATION_TICKS, MythicStats.AQUAMARINE_TOOL_SLOWNESS_AMPLIFIER));
                self.addEffect(new MobEffectInstance(MythicEffects.ICE_BOMB, MythicStats.ICE_BOMB_TOOL_DURATION_TICKS, 0));
                emitAquamarineHitParticles(serverLevel, self);
            }

            if (isCitrineTool(weapon) && self.level() instanceof ServerLevel serverLevel) {
                self.addEffect(new MobEffectInstance(MythicEffects.CHARGED, MythicStats.CITRINE_TOOL_CHARGED_DURATION_TICKS, 0));
                self.addEffect(new MobEffectInstance(MythicEffects.FREEZE, MythicStats.CITRINE_TOOL_FREEZE_TICKS, 0));
                if (actualDamage > 0)
                    applyChainLightning(serverLevel, self, directAttacker, actualDamage);
            }

            if (isJadeTool(weapon)) {
                directAttacker.addEffect(new MobEffectInstance(MythicEffects.JADE_AURA, MythicStats.JADE_TOOL_AURA_DURATION_TICKS, 4));
                if (self.getRandom().nextFloat() < MythicStats.JADE_TOOL_TELEPORT_CHANCE)
                    randomTeleportNear(self);
            }

            MobEffectInstance iceShield = self.getEffect(MythicEffects.ICE_SHIELD);
            if (iceShield != null) {
                int level = iceShield.getAmplifier() + 1;
                directAttacker.hurt(MUDamageTypes.iceShieldReflect(self), level * MythicStats.ICE_SHIELD_MAGIC_DAMAGE_PER_LEVEL);
                directAttacker.addEffect(new MobEffectInstance(MythicEffects.FREEZE, MythicStats.ICE_SHIELD_FREEZE_TICKS, 0));
                if (self.level() instanceof ServerLevel iceLevel)
                    emitIceShieldStreamParticles(iceLevel, self, directAttacker);
            }

        }

        if (self.isDeadOrDying() && self.level() instanceof ServerLevel deathLevel
                && (mu_deathHadLethalIncubation || mu_deathHadIceBomb || mu_deathHadCharged)) {
            triggerDeathEffects(self, deathLevel);
        }
    }

    @Inject(method = "jumpFromGround", at = @At("TAIL"))
    private void applyJadeJumpBoost(CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;
        float extra = 0f;
        MobEffectInstance jadeAura = self.getEffect(MythicEffects.JADE_AURA);
        if (jadeAura != null)
            extra += (jadeAura.getAmplifier() + 1) * MythicStats.JADE_AURA_JUMP_PER_LEVEL;
        if (extra > 0) {
            Vec3 v = self.getDeltaMovement();
            self.setDeltaMovement(v.x, v.y + extra, v.z);
            mu_jadeJumpFallCredit = extra * 7f;
        }
    }

    @ModifyVariable(method = "causeFallDamage", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private float reduceJadeJumpFallDamage(float fallDistance) {
        if (mu_jadeJumpFallCredit > 0) {
            float credit = mu_jadeJumpFallCredit;
            mu_jadeJumpFallCredit = 0f;
            return Math.max(0f, fallDistance - credit);
        }
        return fallDistance;
    }

    @Unique
    private void triggerDeathEffects(LivingEntity self, ServerLevel serverLevel) {
        boolean hadIncub   = mu_deathHadLethalIncubation;
        boolean hadIceBomb = mu_deathHadIceBomb;
        boolean hadCharged = mu_deathHadCharged;
        int incubLevel = mu_deathLethalIncubationLevel;

        mu_deathHadLethalIncubation = false;
        mu_deathHadIceBomb          = false;
        mu_deathHadCharged          = false;
        mu_deathLethalIncubationLevel = 0;

        if (hadIncub) {
            applyLethalIncubationBurst(serverLevel, self, incubLevel);
            mu_incubationWaveTick = 0;
            mu_incubationWaveMaxRadius = MythicStats.LETHAL_INCUBATION_SHOCK_RADIUS;
            mu_incubationWaveX = self.getX();
            mu_incubationWaveY = self.getY() + 1.0;
            mu_incubationWaveZ = self.getZ();
            mu_incubationWaveLevel = incubLevel;
        }

        if (hadIceBomb) {
            applyIceBombFreezeBurst(serverLevel, self);
            mu_iceBombWaveTick = 0;
            mu_iceBombWaveMaxRadius = MythicStats.ICE_BOMB_BURST_RADIUS;
            mu_iceBombWaveX = self.getX();
            mu_iceBombWaveY = self.getY() + 1.0;
            mu_iceBombWaveZ = self.getZ();
        }

        if (hadCharged) {
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
        DustParticleOptions c1 = new DustParticleOptions(colorFromHex(MythicAnims.ARCANE_AURA_COLOR_1), MythicAnims.ARCANE_AURA_WAVE_PARTICLE_SCALE);
        DustParticleOptions c2 = new DustParticleOptions(colorFromHex(MythicAnims.ARCANE_AURA_COLOR_2), MythicAnims.ARCANE_AURA_WAVE_PARTICLE_SCALE);
        DustParticleOptions c3 = new DustParticleOptions(colorFromHex(MythicAnims.ARCANE_AURA_COLOR_3), MythicAnims.ARCANE_AURA_WAVE_PARTICLE_SCALE);
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
        DustParticleOptions dust = new DustParticleOptions(colorFromHex(MythicAnims.RUBY_COLOR), MythicAnims.RUBY_LIFESTEAL_PARTICLE_SCALE);
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
        DustParticleOptions dust = new DustParticleOptions(colorFromHex(MythicAnims.RUBY_COLOR), MythicAnims.RUBY_BLOOD_THIRST_PARTICLE_SCALE);
        double fx = from.getX(), fy = from.getY() + from.getBbHeight() * 0.5, fz = from.getZ();
        double tx = to.getX(), ty = to.getY() + to.getBbHeight() * 0.5, tz = to.getZ();
        int count = btLevel * MythicStats.BLOOD_THIRST_PARTICLES_PER_LEVEL;
        for (int i = 0; i < count; i++) {
            double t = (double) i / count;
            double spread = 0.08;
            level.sendParticles(dust, fx + (tx - fx) * t, fy + (ty - fy) * t, fz + (tz - fz) * t, 2, spread, spread, spread, 0);
        }
        level.sendParticles(dust, tx, ty, tz, 6, 0.2, 0.3, 0.2, 0);
    }

    @Unique
    private static void emitMiasmaWaveRing(ServerLevel level, double cx, double cy, double cz, float radius, int waveLevel) {
        DustParticleOptions c1 = new DustParticleOptions(colorFromHex(MythicAnims.MIASMA_COLOR_1), MythicAnims.MIASMA_WAVE_PARTICLE_SCALE);
        DustParticleOptions c2 = new DustParticleOptions(colorFromHex(MythicAnims.MIASMA_COLOR_2), MythicAnims.MIASMA_WAVE_PARTICLE_SCALE);
        DustParticleOptions c3 = new DustParticleOptions(colorFromHex(MythicAnims.MIASMA_COLOR_3), MythicAnims.MIASMA_WAVE_PARTICLE_SCALE);
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
        DustParticleOptions c1 = new DustParticleOptions(colorFromHex(MythicAnims.MIASMA_COLOR_1), MythicAnims.LETHAL_INCUBATION_WAVE_PARTICLE_SCALE);
        DustParticleOptions c2 = new DustParticleOptions(colorFromHex(MythicAnims.MIASMA_COLOR_2), MythicAnims.LETHAL_INCUBATION_WAVE_PARTICLE_SCALE);
        DustParticleOptions c3 = new DustParticleOptions(colorFromHex(MythicAnims.MIASMA_COLOR_3), MythicAnims.LETHAL_INCUBATION_WAVE_PARTICLE_SCALE);
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
        DustParticleOptions dust = new DustParticleOptions(colorFromHex(MythicAnims.AQUAMARINE_COLOR), MythicAnims.AQUAMARINE_PARTICLE_SCALE);
        level.sendParticles(dust, entity.getX(), entity.getY() + entity.getBbHeight() * 0.5, entity.getZ(), 12, 0.3, 0.4, 0.3, 0);
    }

    @Unique
    private static void applyLethalIncubationBurst(ServerLevel level, LivingEntity center, int incubationLevel) {
        float radius = MythicStats.LETHAL_INCUBATION_SHOCK_RADIUS;
        int poisonAmplifier = Math.max(0, incubationLevel - 1);
        AABB bb = new AABB(center.getX() - radius, center.getY() - radius, center.getZ() - radius,
            center.getX() + radius, center.getY() + radius, center.getZ() + radius);
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, bb)) {
            if (entity == center) continue;
            if (entity.distanceTo(center) > radius) continue;
            entity.hurt(MUDamageTypes.peridotIncubation(center), MythicStats.LETHAL_INCUBATION_SHOCK_DAMAGE);
            entity.addEffect(new MobEffectInstance(MobEffects.POISON, MythicStats.LETHAL_INCUBATION_POISON_DURATION_TICKS, poisonAmplifier));
            entity.knockback(1.0, center.getX() - entity.getX(), center.getZ() - entity.getZ());
        }
        level.explode(null, center.getX(), center.getY(), center.getZ(), 2.0f, Level.ExplosionInteraction.NONE);
    }

    @Unique
    private static void emitIceBombWaveRing(ServerLevel level, double cx, double cy, double cz, float radius) {
        DustParticleOptions c1 = new DustParticleOptions(colorFromHex(MythicAnims.ICE_BOMB_COLOR_1), MythicAnims.ICE_BOMB_WAVE_PARTICLE_SCALE);
        DustParticleOptions c2 = new DustParticleOptions(colorFromHex(MythicAnims.ICE_BOMB_COLOR_2), MythicAnims.ICE_BOMB_WAVE_PARTICLE_SCALE);
        DustParticleOptions c3 = new DustParticleOptions(colorFromHex(MythicAnims.ICE_BOMB_COLOR_3), MythicAnims.ICE_BOMB_WAVE_PARTICLE_SCALE);
        int step = MythicAnims.ICE_BOMB_WAVE_STEP_DEGREES;
        int segment = step * 3;
        for (int angle = 0; angle < 360; angle += step) {
            double rad = Math.toRadians(angle);
            double px = cx + radius * Math.cos(rad);
            double pz = cz + radius * Math.sin(rad);
            int mod = angle % segment;
            DustParticleOptions color = mod < step ? c1 : mod < step * 2 ? c2 : c3;
            level.sendParticles(color, px, cy, pz, MythicAnims.ICE_BOMB_WAVE_PARTICLES_PER_POINT, 0, 0.08, 0, 0);
        }
    }

    @Unique
    private static void applyIceBombFreezeBurst(ServerLevel level, LivingEntity center) {
        float radius = MythicStats.ICE_BOMB_BURST_RADIUS;
        AABB bb = new AABB(center.getX() - radius, center.getY() - radius, center.getZ() - radius,
            center.getX() + radius, center.getY() + radius, center.getZ() + radius);
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, bb)) {
            if (entity == center) continue;
            if (entity.distanceTo(center) > radius) continue;
            entity.hurt(MUDamageTypes.iceBombBurst(center), MythicStats.ICE_BOMB_BURST_DAMAGE);
            entity.addEffect(new MobEffectInstance(MythicEffects.FREEZE, MythicStats.ICE_BOMB_BURST_FREEZE_TICKS, 0));
            entity.knockback(1.0, center.getX() - entity.getX(), center.getZ() - entity.getZ());
        }
        level.explode(null, center.getX(), center.getY(), center.getZ(), 2.0f, Level.ExplosionInteraction.NONE);
    }

    @Unique
    private static void applyMiasmaPoison(ServerLevel level, LivingEntity owner, int peridotLevel) {
        float radius = Math.min(peridotLevel * MythicStats.MIASMA_CLOUD_RADIUS_PER_LEVEL, MythicStats.MIASMA_CLOUD_MAX_RADIUS);
        int poisonAmplifier = Math.min(peridotLevel - 1, MythicStats.MIASMA_POISON_MAX_AMPLIFIER);
        int poisonDuration = MythicStats.MIASMA_POISON_DURATION_TICKS;
        AABB bb = new AABB(owner.getX() - radius, owner.getY() - radius, owner.getZ() - radius,
            owner.getX() + radius, owner.getY() + radius, owner.getZ() + radius);
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, bb)) {
            if (entity == owner) continue;
            if (entity instanceof TamableAnimal tamed && Objects.equals(tamed.getOwnerUUID(), owner.getUUID())) continue;
            if (owner.getTeam() != null && owner.getTeam() == entity.getTeam()) continue;
            if (entity.distanceTo(owner) > radius) continue;
            entity.addEffect(new MobEffectInstance(MobEffects.POISON, poisonDuration, poisonAmplifier));
        }
        level.playSound(null, owner.getX(), owner.getY(), owner.getZ(), MythicAnims.ARCANE_AURA_SOUND_1, SoundSource.PLAYERS, 0.8f, 0.6f);
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
    private static void applyChainLightning(ServerLevel level, LivingEntity victim, LivingEntity attacker, float damage) {
        float chainDamage = damage * MythicStats.CITRINE_TOOL_CHAIN_FRACTION;
        float range = MythicStats.CITRINE_TOOL_CHAIN_RANGE;
        // Search near the attacker so that nearby enemies get chained regardless of where the victim is
        double cx = attacker != null ? attacker.getX() : victim.getX();
        double cy = attacker != null ? attacker.getY() : victim.getY();
        double cz = attacker != null ? attacker.getZ() : victim.getZ();
        AABB bb = new AABB(cx - range, cy - range, cz - range, cx + range, cy + range, cz + range);
        List<LivingEntity> nearby = level.getEntitiesOfClass(LivingEntity.class, bb);
        nearby.sort(Comparator.comparingDouble(e -> e.distanceTo(victim)));
        int count = 0;
        for (LivingEntity target : nearby) {
            if (count >= MythicStats.CITRINE_TOOL_CHAIN_TARGETS) break;
            if (target == victim || target == attacker) continue;
            target.hurt(MUDamageTypes.citrineChain(attacker), chainDamage);
            emitCitrineChainParticles(level, victim, target);
            count++;
        }
    }

    @Unique
    private static void applyShockInRange(ServerLevel level, LivingEntity center, LivingEntity excluded, float radius, int shockLevel, LivingEntity source, boolean excludeCenter) {
        float damage = shockLevel * MythicStats.TOPAZ_SHOCK_DAMAGE_PER_LEVEL;
        float knockback = shockLevel * MythicStats.TOPAZ_SHOCK_KNOCKBACK_PER_LEVEL;
        AABB bb = new AABB(center.getX() - radius, center.getY() - radius, center.getZ() - radius,
            center.getX() + radius, center.getY() + radius, center.getZ() + radius);
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, bb)) {
            if (excludeCenter && entity == center) continue;
            if (excluded != null && entity == excluded) continue;
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
    private static void applyJadeTrail(ServerLevel level, LivingEntity owner, int jadeLevel) {
        int trailAmplifier = ((jadeLevel + 1) / 2) - 1; // ceiling(jadeLevel/2) - 1
        float radius = MythicStats.JADE_TRAIL_RADIUS;
        AABB bb = new AABB(owner.getX() - radius, owner.getY() - radius, owner.getZ() - radius,
            owner.getX() + radius, owner.getY() + radius, owner.getZ() + radius);
        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, bb)) {
            if (entity == owner) continue;
            if (entity.distanceTo(owner) > radius) continue;
            MobEffectInstance current = entity.getEffect(MythicEffects.JADE_AURA);
            if (current != null && current.getAmplifier() >= trailAmplifier) continue;
            entity.addEffect(new MobEffectInstance(MythicEffects.JADE_AURA, MythicStats.JADE_TRAIL_EFFECT_DURATION_TICKS, trailAmplifier));
        }
        DustParticleOptions d1 = new DustParticleOptions(colorFromHex(MythicAnims.JADE_COLOR_1), MythicAnims.JADE_TRAIL_PARTICLE_SCALE);
        DustParticleOptions d2 = new DustParticleOptions(colorFromHex(MythicAnims.JADE_COLOR_2), MythicAnims.JADE_TRAIL_PARTICLE_SCALE);
        DustParticleOptions d3 = new DustParticleOptions(colorFromHex(MythicAnims.JADE_COLOR_3), MythicAnims.JADE_TRAIL_PARTICLE_SCALE);
        level.sendParticles(d1, owner.getX(), owner.getY() + 0.1, owner.getZ(), 6, 0.4, 0.1, 0.4, 0);
        level.sendParticles(d2, owner.getX(), owner.getY() + 0.4, owner.getZ(), 8, 0.35, 0.2, 0.35, 0);
        level.sendParticles(d3, owner.getX(), owner.getY() + 0.8, owner.getZ(), 5, 0.25, 0.3, 0.25, 0);
    }

    @Unique
    private static void emitJadeLingerParticles(ServerLevel level, float x, float y, float z) {
        DustParticleOptions c1 = new DustParticleOptions(colorFromHex(MythicAnims.JADE_COLOR_1), MythicAnims.JADE_TRAIL_PARTICLE_SCALE);
        DustParticleOptions c2 = new DustParticleOptions(colorFromHex(MythicAnims.JADE_COLOR_2), MythicAnims.JADE_TRAIL_PARTICLE_SCALE);
        DustParticleOptions c3 = new DustParticleOptions(colorFromHex(MythicAnims.JADE_COLOR_3), MythicAnims.JADE_TRAIL_PARTICLE_SCALE);
        level.sendParticles(c1, x, y + 0.1, z, 2, 0.3, 0.05, 0.3, 0);
        level.sendParticles(c2, x, y + 0.4, z, 2, 0.25, 0.05, 0.25, 0);
        level.sendParticles(c3, x, y + 0.7, z, 1, 0.2, 0.05, 0.2, 0);
    }

    @Unique
    private static void emitJadeAuraAmbientParticles(ServerLevel level, LivingEntity entity, int auraLevel) {
        DustParticleOptions c1 = new DustParticleOptions(colorFromHex(MythicAnims.JADE_COLOR_1), MythicAnims.JADE_TRAIL_PARTICLE_SCALE);
        DustParticleOptions c2 = new DustParticleOptions(colorFromHex(MythicAnims.JADE_COLOR_2), MythicAnims.JADE_TRAIL_PARTICLE_SCALE);
        DustParticleOptions c3 = new DustParticleOptions(colorFromHex(MythicAnims.JADE_COLOR_3), MythicAnims.JADE_TRAIL_PARTICLE_SCALE);
        int count = Math.min(auraLevel * 2, 10);
        double cx = entity.getX(), cy = entity.getY() + entity.getBbHeight() * 0.5, cz = entity.getZ();
        for (int i = 0; i < count; i++) {
            DustParticleOptions color = i % 3 == 0 ? c1 : i % 3 == 1 ? c2 : c3;
            level.sendParticles(color, cx, cy, cz, 1, 0.35, 0.55, 0.35, 0);
        }
    }

    @Unique
    private static void emitIceShieldStreamParticles(ServerLevel level, LivingEntity from, LivingEntity to) {
        DustParticleOptions dust = new DustParticleOptions(colorFromHex(MythicAnims.AQUAMARINE_COLOR), MythicAnims.ICE_SHIELD_STREAM_PARTICLE_SCALE);
        double fx = from.getX(), fy = from.getY() + from.getBbHeight() * 0.5, fz = from.getZ();
        double tx = to.getX(), ty = to.getY() + to.getBbHeight() * 0.5, tz = to.getZ();
        int count = 20;
        for (int i = 0; i < count; i++) {
            double t = (double) i / count;
            level.sendParticles(dust, fx + (tx - fx) * t, fy + (ty - fy) * t, fz + (tz - fz) * t, 1, 0.04, 0.04, 0.04, 0);
        }
    }

    @Unique
    private static void emitIceFreezeOrbitParticles(ServerLevel level, LivingEntity entity) {
        DustParticleOptions dust = new DustParticleOptions(colorFromHex(MythicAnims.AQUAMARINE_COLOR), MythicAnims.ICE_FREEZE_ORBIT_PARTICLE_SCALE);
        int baseAngle = (entity.tickCount * 7) % 360;
        float r = 0.75f;
        double midY = entity.getY() + entity.getBbHeight() * 0.5;
        for (int i = 0; i < 6; i++) {
            int angle = (baseAngle + i * 60) % 360;
            double rad = Math.toRadians(angle);
            level.sendParticles(dust, entity.getX() + r * Math.cos(rad), midY, entity.getZ() + r * Math.sin(rad), 1, 0, 0.04, 0, 0);
        }
    }

}
