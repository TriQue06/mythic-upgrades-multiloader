package net.trique.mythicupgrades.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.trique.mythicupgrades.MythicEffects;
import net.trique.mythicupgrades.MythicStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningBolt.class)
public class LightningBoltMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void redirectFromCitrine(CallbackInfo ci) {
        LightningBolt self = (LightningBolt)(Object)this;
        if (self.tickCount != 0) return;
        if (!(self.level() instanceof ServerLevel serverLevel)) return;

        double radius = 3.0;
        AABB bb = new AABB(self.getX() - radius, self.getY() - radius, self.getZ() - radius,
            self.getX() + radius, self.getY() + radius, self.getZ() + radius);

        boolean hasCitrineWearer = serverLevel.getEntitiesOfClass(LivingEntity.class, bb)
            .stream().anyMatch(e -> e.getEffect(MythicEffects.STATIC_FIELD) != null);

        if (hasCitrineWearer) {
            double angle = serverLevel.getRandom().nextDouble() * 2 * Math.PI;
            double dist = MythicStats.CITRINE_LIGHTNING_REDIRECT_DISTANCE;
            self.moveTo(self.getX() + dist * Math.cos(angle), self.getY(), self.getZ() + dist * Math.sin(angle));
        }
    }
}
