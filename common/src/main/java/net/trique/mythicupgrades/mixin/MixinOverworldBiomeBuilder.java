package net.trique.mythicupgrades.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.trique.mythicupgrades.worldgen.MythicBiomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(OverworldBiomeBuilder.class)
public class MixinOverworldBiomeBuilder {

    // Inject at HEAD so our cave biomes are first in the parameter list and always
    // win the nearest-neighbour tie-break over vanilla cave biomes (lush, dripstone).
    // Full temp/humidity/cont/erosion coverage guarantees fitness=0 at depth 0.4–0.9,
    // which beats every vanilla surface biome sitting at depth=0.0 (dist²=0.16).
    // This runs in both the vanilla region and our TerraBlender region, giving 100%
    // underground coverage — F3 will always show our biome name when underground.
    @Inject(method = "addBiomes", at = @At("HEAD"))
    private void mythicupgrades$addCaveBiomes(
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper,
            CallbackInfo ci) {

        // During Forge datagen, VanillaRegistries.createLookup() builds the vanilla
        // RegistrySetBuilder and calls this method. Our biome keys are not in that
        // builder, causing "Unreferenced key" errors. Skip the injection in datagen.
        try {
            boolean isDatagen = (boolean) Class
                    .forName("net.minecraftforge.data.loading.DatagenModLoader")
                    .getMethod("isRunningDataGen")
                    .invoke(null);
            if (isDatagen) return;
        } catch (Throwable ignored) {}

        Climate.Parameter full  = Climate.Parameter.span(-1.0f, 1.0f);
        Climate.Parameter depth = Climate.Parameter.span(0.4f, 0.9f);

        mapper.accept(Pair.of(Climate.parameters(full, full, full, full, depth,
                Climate.Parameter.span(-1.0f, -0.5f), 0.0f), MythicBiomes.AQUAMARINE_CAVES));

        mapper.accept(Pair.of(Climate.parameters(full, full, full, full, depth,
                Climate.Parameter.span(-0.5f,  0.0f), 0.0f), MythicBiomes.PERIDOT_CAVES));

        mapper.accept(Pair.of(Climate.parameters(full, full, full, full, depth,
                Climate.Parameter.span( 0.0f,  0.5f), 0.0f), MythicBiomes.TOPAZ_CAVES));

        mapper.accept(Pair.of(Climate.parameters(full, full, full, full, depth,
                Climate.Parameter.span( 0.5f,  1.0f), 0.0f), MythicBiomes.CITRINE_CAVES));
    }
}
