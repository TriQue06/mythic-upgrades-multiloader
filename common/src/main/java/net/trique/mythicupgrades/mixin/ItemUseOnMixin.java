package net.trique.mythicupgrades.mixin;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.trique.mythicupgrades.MythicEffects;
import net.trique.mythicupgrades.MythicStats;
import net.trique.mythicupgrades.item.MythicItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemUseOnMixin {

    @Inject(method = "useOn", at = @At("RETURN"))
    private void onUseOn(UseOnContext ctx, CallbackInfoReturnable<InteractionResult> cir) {
        if (cir.getReturnValue() == InteractionResult.PASS) return;
        Player player = ctx.getPlayer();
        if (player == null || player.level().isClientSide()) return;
        if (!isJadeTool((Item)(Object)this)) return;
        player.addEffect(new MobEffectInstance(MythicEffects.JADE_AURA, MythicStats.JADE_TOOL_AURA_DURATION_TICKS, 4));
        player.addEffect(new MobEffectInstance(MythicEffects.BOUNCER, MythicStats.JADE_TOOL_USE_BOUNCER_DURATION_TICKS, 0));
    }

    @Unique
    private static boolean isJadeTool(Item item) {
        return item == MythicItems.JADE_SWORD || item == MythicItems.JADE_PICKAXE
            || item == MythicItems.JADE_AXE || item == MythicItems.JADE_SHOVEL
            || item == MythicItems.JADE_HOE;
    }
}
