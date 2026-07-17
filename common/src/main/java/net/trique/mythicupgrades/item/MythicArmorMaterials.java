package net.trique.mythicupgrades.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.trique.mythicupgrades.Constants;

import java.util.EnumMap;
import java.util.Map;

public class MythicArmorMaterials {

    private static Map<ArmorType, Integer> defense(int boots, int leggings, int chestplate, int helmet) {
        Map<ArmorType, Integer> map = new EnumMap<>(ArmorType.class);
        map.put(ArmorType.BOOTS, boots);
        map.put(ArmorType.LEGGINGS, leggings);
        map.put(ArmorType.CHESTPLATE, chestplate);
        map.put(ArmorType.HELMET, helmet);
        map.put(ArmorType.BODY, chestplate);
        return map;
    }

    private static ArmorMaterial make(String gem) {
        ResourceKey<EquipmentAsset> asset = ResourceKey.create(EquipmentAssets.ROOT_ID,
                Identifier.fromNamespaceAndPath(Constants.MOD_ID, gem));
        TagKey<Item> repair = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(Constants.MOD_ID, "repairs_" + gem + "_armor"));
        // Netherite-tier stats, matching the old 1.21.1 values (durability mult 37, protection 3/6/8/3)
        return new ArmorMaterial(37, defense(3, 6, 8, 3), 15,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, repair, asset);
    }

    public static final ArmorMaterial AQUAMARINE = make("aquamarine");
    public static final ArmorMaterial CITRINE = make("citrine");
    public static final ArmorMaterial TOPAZ = make("topaz");
    public static final ArmorMaterial PERIDOT = make("peridot");
    public static final ArmorMaterial RUBY = make("ruby");
    public static final ArmorMaterial SAPPHIRE = make("sapphire");
    public static final ArmorMaterial JADE = make("jade");
    public static final ArmorMaterial AMETRINE = make("ametrine");
}
