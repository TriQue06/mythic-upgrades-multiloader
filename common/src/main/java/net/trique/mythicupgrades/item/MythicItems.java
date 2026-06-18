package net.trique.mythicupgrades.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.trique.mythicupgrades.Constants;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class MythicItems {

    private static final List<Map.Entry<String, Item>> DEFERRED = new ArrayList<>();

    private static <T extends Item> T defer(String name, T item) {
        DEFERRED.add(new AbstractMap.SimpleEntry<>(name, item));
        return item;
    }

    // Gems
    public static final Item AQUAMARINE = defer("aquamarine", new Item(new Item.Properties()));
    public static final Item KYANITE = defer("kyanite", new Item(new Item.Properties()));
    public static final Item CITRINE = defer("citrine", new Item(new Item.Properties()));
    public static final Item TOPAZ = defer("topaz", new Item(new Item.Properties()));
    public static final Item PERIDOT = defer("peridot", new Item(new Item.Properties()));
    public static final Item RUBY = defer("ruby", new Item(new Item.Properties()));
    public static final Item SAPPHIRE = defer("sapphire", new Item(new Item.Properties()));
    public static final Item JADE = defer("jade", new Item(new Item.Properties()));
    public static final Item AMETRINE = defer("ametrine", new Item(new Item.Properties()));

    // Necoium (metal workflow: raw → smelt → ingot)
    public static final Item RAW_NECOIUM = defer("raw_necoium", new Item(new Item.Properties()));
    public static final Item NECOIUM_INGOT = defer("necoium_ingot", new Item(new Item.Properties()));

    // Aquamarine tools & armor
    public static final Item AQUAMARINE_SWORD = defer("aquamarine_sword", new SwordItem(MythicToolMaterials.AQUAMARINE, 3, -2.4F, new Item.Properties()));
    public static final Item AQUAMARINE_PICKAXE = defer("aquamarine_pickaxe", new PickaxeItem(MythicToolMaterials.AQUAMARINE, 1, -2.8F, new Item.Properties()) {});
    public static final Item AQUAMARINE_AXE = defer("aquamarine_axe", new AxeItem(MythicToolMaterials.AQUAMARINE, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item AQUAMARINE_SHOVEL = defer("aquamarine_shovel", new ShovelItem(MythicToolMaterials.AQUAMARINE, 1.5F, -3.0F, new Item.Properties()));
    public static final Item AQUAMARINE_HOE = defer("aquamarine_hoe", new HoeItem(MythicToolMaterials.AQUAMARINE, -4, 0.0F, new Item.Properties()) {});
    public static final Item AQUAMARINE_HELMET = defer("aquamarine_helmet", new ArmorItem(MythicArmorMaterials.AQUAMARINE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item AQUAMARINE_CHESTPLATE = defer("aquamarine_chestplate", new ArmorItem(MythicArmorMaterials.AQUAMARINE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item AQUAMARINE_LEGGINGS = defer("aquamarine_leggings", new ArmorItem(MythicArmorMaterials.AQUAMARINE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item AQUAMARINE_BOOTS = defer("aquamarine_boots", new ArmorItem(MythicArmorMaterials.AQUAMARINE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Kyanite tools & armor
    public static final Item KYANITE_SWORD = defer("kyanite_sword", new SwordItem(MythicToolMaterials.KYANITE, 3, -2.4F, new Item.Properties()));
    public static final Item KYANITE_PICKAXE = defer("kyanite_pickaxe", new PickaxeItem(MythicToolMaterials.KYANITE, 1, -2.8F, new Item.Properties()) {});
    public static final Item KYANITE_AXE = defer("kyanite_axe", new AxeItem(MythicToolMaterials.KYANITE, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item KYANITE_SHOVEL = defer("kyanite_shovel", new ShovelItem(MythicToolMaterials.KYANITE, 1.5F, -3.0F, new Item.Properties()));
    public static final Item KYANITE_HOE = defer("kyanite_hoe", new HoeItem(MythicToolMaterials.KYANITE, -4, 0.0F, new Item.Properties()) {});
    public static final Item KYANITE_HELMET = defer("kyanite_helmet", new ArmorItem(MythicArmorMaterials.KYANITE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item KYANITE_CHESTPLATE = defer("kyanite_chestplate", new ArmorItem(MythicArmorMaterials.KYANITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item KYANITE_LEGGINGS = defer("kyanite_leggings", new ArmorItem(MythicArmorMaterials.KYANITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item KYANITE_BOOTS = defer("kyanite_boots", new ArmorItem(MythicArmorMaterials.KYANITE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Citrine tools & armor
    public static final Item CITRINE_SWORD = defer("citrine_sword", new SwordItem(MythicToolMaterials.CITRINE, 3, -2.4F, new Item.Properties()));
    public static final Item CITRINE_PICKAXE = defer("citrine_pickaxe", new PickaxeItem(MythicToolMaterials.CITRINE, 1, -2.8F, new Item.Properties()) {});
    public static final Item CITRINE_AXE = defer("citrine_axe", new AxeItem(MythicToolMaterials.CITRINE, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item CITRINE_SHOVEL = defer("citrine_shovel", new ShovelItem(MythicToolMaterials.CITRINE, 1.5F, -3.0F, new Item.Properties()));
    public static final Item CITRINE_HOE = defer("citrine_hoe", new HoeItem(MythicToolMaterials.CITRINE, -4, 0.0F, new Item.Properties()) {});
    public static final Item CITRINE_HELMET = defer("citrine_helmet", new ArmorItem(MythicArmorMaterials.CITRINE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item CITRINE_CHESTPLATE = defer("citrine_chestplate", new ArmorItem(MythicArmorMaterials.CITRINE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item CITRINE_LEGGINGS = defer("citrine_leggings", new ArmorItem(MythicArmorMaterials.CITRINE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item CITRINE_BOOTS = defer("citrine_boots", new ArmorItem(MythicArmorMaterials.CITRINE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Topaz tools & armor
    public static final Item TOPAZ_SWORD = defer("topaz_sword", new SwordItem(MythicToolMaterials.TOPAZ, 3, -2.4F, new Item.Properties()));
    public static final Item TOPAZ_PICKAXE = defer("topaz_pickaxe", new PickaxeItem(MythicToolMaterials.TOPAZ, 1, -2.8F, new Item.Properties()) {});
    public static final Item TOPAZ_AXE = defer("topaz_axe", new AxeItem(MythicToolMaterials.TOPAZ, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item TOPAZ_SHOVEL = defer("topaz_shovel", new ShovelItem(MythicToolMaterials.TOPAZ, 1.5F, -3.0F, new Item.Properties()));
    public static final Item TOPAZ_HOE = defer("topaz_hoe", new HoeItem(MythicToolMaterials.TOPAZ, -4, 0.0F, new Item.Properties()) {});
    public static final Item TOPAZ_HELMET = defer("topaz_helmet", new ArmorItem(MythicArmorMaterials.TOPAZ, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item TOPAZ_CHESTPLATE = defer("topaz_chestplate", new ArmorItem(MythicArmorMaterials.TOPAZ, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item TOPAZ_LEGGINGS = defer("topaz_leggings", new ArmorItem(MythicArmorMaterials.TOPAZ, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item TOPAZ_BOOTS = defer("topaz_boots", new ArmorItem(MythicArmorMaterials.TOPAZ, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Peridot tools & armor
    public static final Item PERIDOT_SWORD = defer("peridot_sword", new SwordItem(MythicToolMaterials.PERIDOT, 3, -2.4F, new Item.Properties()));
    public static final Item PERIDOT_PICKAXE = defer("peridot_pickaxe", new PickaxeItem(MythicToolMaterials.PERIDOT, 1, -2.8F, new Item.Properties()) {});
    public static final Item PERIDOT_AXE = defer("peridot_axe", new AxeItem(MythicToolMaterials.PERIDOT, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item PERIDOT_SHOVEL = defer("peridot_shovel", new ShovelItem(MythicToolMaterials.PERIDOT, 1.5F, -3.0F, new Item.Properties()));
    public static final Item PERIDOT_HOE = defer("peridot_hoe", new HoeItem(MythicToolMaterials.PERIDOT, -4, 0.0F, new Item.Properties()) {});
    public static final Item PERIDOT_HELMET = defer("peridot_helmet", new ArmorItem(MythicArmorMaterials.PERIDOT, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item PERIDOT_CHESTPLATE = defer("peridot_chestplate", new ArmorItem(MythicArmorMaterials.PERIDOT, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item PERIDOT_LEGGINGS = defer("peridot_leggings", new ArmorItem(MythicArmorMaterials.PERIDOT, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item PERIDOT_BOOTS = defer("peridot_boots", new ArmorItem(MythicArmorMaterials.PERIDOT, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Ruby tools & armor
    public static final Item RUBY_SWORD = defer("ruby_sword", new SwordItem(MythicToolMaterials.RUBY, 3, -2.4F, new Item.Properties()));
    public static final Item RUBY_PICKAXE = defer("ruby_pickaxe", new PickaxeItem(MythicToolMaterials.RUBY, 1, -2.8F, new Item.Properties()) {});
    public static final Item RUBY_AXE = defer("ruby_axe", new AxeItem(MythicToolMaterials.RUBY, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item RUBY_SHOVEL = defer("ruby_shovel", new ShovelItem(MythicToolMaterials.RUBY, 1.5F, -3.0F, new Item.Properties()));
    public static final Item RUBY_HOE = defer("ruby_hoe", new HoeItem(MythicToolMaterials.RUBY, -4, 0.0F, new Item.Properties()) {});
    public static final Item RUBY_HELMET = defer("ruby_helmet", new ArmorItem(MythicArmorMaterials.RUBY, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item RUBY_CHESTPLATE = defer("ruby_chestplate", new ArmorItem(MythicArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item RUBY_LEGGINGS = defer("ruby_leggings", new ArmorItem(MythicArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item RUBY_BOOTS = defer("ruby_boots", new ArmorItem(MythicArmorMaterials.RUBY, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Sapphire tools & armor
    public static final Item SAPPHIRE_SWORD = defer("sapphire_sword", new SwordItem(MythicToolMaterials.SAPPHIRE, 3, -2.4F, new Item.Properties()));
    public static final Item SAPPHIRE_PICKAXE = defer("sapphire_pickaxe", new PickaxeItem(MythicToolMaterials.SAPPHIRE, 1, -2.8F, new Item.Properties()) {});
    public static final Item SAPPHIRE_AXE = defer("sapphire_axe", new AxeItem(MythicToolMaterials.SAPPHIRE, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item SAPPHIRE_SHOVEL = defer("sapphire_shovel", new ShovelItem(MythicToolMaterials.SAPPHIRE, 1.5F, -3.0F, new Item.Properties()));
    public static final Item SAPPHIRE_HOE = defer("sapphire_hoe", new HoeItem(MythicToolMaterials.SAPPHIRE, -4, 0.0F, new Item.Properties()) {});
    public static final Item SAPPHIRE_HELMET = defer("sapphire_helmet", new ArmorItem(MythicArmorMaterials.SAPPHIRE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item SAPPHIRE_CHESTPLATE = defer("sapphire_chestplate", new ArmorItem(MythicArmorMaterials.SAPPHIRE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item SAPPHIRE_LEGGINGS = defer("sapphire_leggings", new ArmorItem(MythicArmorMaterials.SAPPHIRE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item SAPPHIRE_BOOTS = defer("sapphire_boots", new ArmorItem(MythicArmorMaterials.SAPPHIRE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Jade tools & armor
    public static final Item JADE_SWORD = defer("jade_sword", new SwordItem(MythicToolMaterials.JADE, 3, -2.4F, new Item.Properties()));
    public static final Item JADE_PICKAXE = defer("jade_pickaxe", new PickaxeItem(MythicToolMaterials.JADE, 1, -2.8F, new Item.Properties()) {});
    public static final Item JADE_AXE = defer("jade_axe", new AxeItem(MythicToolMaterials.JADE, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item JADE_SHOVEL = defer("jade_shovel", new ShovelItem(MythicToolMaterials.JADE, 1.5F, -3.0F, new Item.Properties()));
    public static final Item JADE_HOE = defer("jade_hoe", new HoeItem(MythicToolMaterials.JADE, -4, 0.0F, new Item.Properties()) {});
    public static final Item JADE_HELMET = defer("jade_helmet", new ArmorItem(MythicArmorMaterials.JADE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item JADE_CHESTPLATE = defer("jade_chestplate", new ArmorItem(MythicArmorMaterials.JADE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item JADE_LEGGINGS = defer("jade_leggings", new ArmorItem(MythicArmorMaterials.JADE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item JADE_BOOTS = defer("jade_boots", new ArmorItem(MythicArmorMaterials.JADE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Ametrine tools & armor
    public static final Item AMETRINE_SWORD = defer("ametrine_sword", new SwordItem(MythicToolMaterials.AMETRINE, 3, -2.4F, new Item.Properties()));
    public static final Item AMETRINE_PICKAXE = defer("ametrine_pickaxe", new PickaxeItem(MythicToolMaterials.AMETRINE, 1, -2.8F, new Item.Properties()) {});
    public static final Item AMETRINE_AXE = defer("ametrine_axe", new AxeItem(MythicToolMaterials.AMETRINE, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item AMETRINE_SHOVEL = defer("ametrine_shovel", new ShovelItem(MythicToolMaterials.AMETRINE, 1.5F, -3.0F, new Item.Properties()));
    public static final Item AMETRINE_HOE = defer("ametrine_hoe", new HoeItem(MythicToolMaterials.AMETRINE, -4, 0.0F, new Item.Properties()) {});
    public static final Item AMETRINE_HELMET = defer("ametrine_helmet", new ArmorItem(MythicArmorMaterials.AMETRINE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item AMETRINE_CHESTPLATE = defer("ametrine_chestplate", new ArmorItem(MythicArmorMaterials.AMETRINE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item AMETRINE_LEGGINGS = defer("ametrine_leggings", new ArmorItem(MythicArmorMaterials.AMETRINE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item AMETRINE_BOOTS = defer("ametrine_boots", new ArmorItem(MythicArmorMaterials.AMETRINE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Crystal shards (one per gem, necoium excluded)
    public static final Item AQUAMARINE_CRYSTAL_SHARD = defer("aquamarine_crystal_shard", new Item(new Item.Properties()));
    public static final Item KYANITE_CRYSTAL_SHARD = defer("kyanite_crystal_shard", new Item(new Item.Properties()));
    public static final Item CITRINE_CRYSTAL_SHARD = defer("citrine_crystal_shard", new Item(new Item.Properties()));
    public static final Item TOPAZ_CRYSTAL_SHARD = defer("topaz_crystal_shard", new Item(new Item.Properties()));
    public static final Item PERIDOT_CRYSTAL_SHARD = defer("peridot_crystal_shard", new Item(new Item.Properties()));
    public static final Item RUBY_CRYSTAL_SHARD = defer("ruby_crystal_shard", new Item(new Item.Properties()));
    public static final Item SAPPHIRE_CRYSTAL_SHARD = defer("sapphire_crystal_shard", new Item(new Item.Properties()));
    public static final Item JADE_CRYSTAL_SHARD = defer("jade_crystal_shard", new Item(new Item.Properties()));
    public static final Item AMETRINE_CRYSTAL_SHARD = defer("ametrine_crystal_shard", new Item(new Item.Properties()));

    public static void register(BiFunction<String, Item, Item> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
        Constants.LOG.info("MythicItems registered.");
    }
}
