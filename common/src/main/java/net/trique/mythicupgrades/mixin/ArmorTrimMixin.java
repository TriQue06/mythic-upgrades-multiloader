package net.trique.mythicupgrades.mixin;

import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.trique.mythicupgrades.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(ArmorTrim.class)
public class ArmorTrimMixin {

    // Trim material names that have a _darker palette variant for matching modded armor
    private static final Set<String> DARKER_TRIM_ASSET_NAMES = Set.of(
        "aquamarine", "topaz", "peridot", "ruby", "sapphire", "jade", "ametrine"
    );

    @Inject(method = "getColorPaletteSuffix", at = @At("HEAD"), cancellable = true)
    private void mu_injectDarkerTrimForModdedArmor(ArmorMaterial armorMaterial, CallbackInfoReturnable<String> cir) {
        // Vanilla already handles ArmorMaterials enum values; only act on modded armors
        if (armorMaterial instanceof ArmorMaterials) return;

        ArmorTrim self = (ArmorTrim) (Object) this;
        String trimAssetName = self.material().value().assetName();

        if (!DARKER_TRIM_ASSET_NAMES.contains(trimAssetName)) return;

        // Check if the armor material is ours and matches the trim material
        String armorName = armorMaterial.getName();
        if (armorName.equals(Constants.MOD_ID + ":" + trimAssetName)) {
            cir.setReturnValue(trimAssetName + "_darker");
        }
    }
}
