package net.trique.mythicupgrades.mixin;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;
import net.trique.mythicupgrades.MythicLegacyMigration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelChunk.class)
public class LevelChunkMixin {

    @Shadow private Level level;

    @Inject(method = "addAndRegisterBlockEntity", at = @At("TAIL"))
    private void onBlockEntityAdded(BlockEntity be, CallbackInfo ci) {
        if (level == null || level.isClientSide()) return;
        if (!(be instanceof Container container)) return;

        boolean changed = false;
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (stack.isEmpty()) continue;
            ItemStack replacement = MythicLegacyMigration.replace(stack);
            if (replacement != null) {
                container.setItem(i, replacement);
                changed = true;
            }
        }
        if (changed) be.setChanged();
    }
}
