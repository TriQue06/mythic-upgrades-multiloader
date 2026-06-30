package net.trique.mythicupgrades.mixin;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.trique.mythicupgrades.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(ArmorTrim.class)
public class ArmorTrimMixin {

    private static final Set<String> DARKER_TRIM_ASSET_NAMES = Set.of(
        "aquamarine", "topaz", "peridot", "ruby", "sapphire", "jade", "ametrine"
    );

    @Inject(method = "getColorPaletteSuffix", at = @At("HEAD"), cancellable = true)
    private static void mu_injectDarkerTrimForModdedArmor(Holder<TrimMaterial> trimMaterialHolder, Holder<ArmorMaterial> armorMaterialHolder, CallbackInfoReturnable<String> cir) {
        ArmorMaterial armorMaterial = armorMaterialHolder.value();
        if (armorMaterial.layers().isEmpty()) return;
        ResourceLocation tex = armorMaterial.layers().get(0).texture(false);
        if (!tex.getNamespace().equals(Constants.MOD_ID)) return;

        String trimAssetName = trimMaterialHolder.value().assetName();
        if (!DARKER_TRIM_ASSET_NAMES.contains(trimAssetName)) return;

        String armorName = tex.getPath()
            .replace("textures/models/armor/", "")
            .replace("_layer_1.png", "");
        if (armorName.equals(trimAssetName)) {
            cir.setReturnValue(trimAssetName + "_darker");
        }
    }
}