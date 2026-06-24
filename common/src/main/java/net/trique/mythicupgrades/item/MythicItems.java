package net.trique.mythicupgrades.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.MythicEffects;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import net.minecraft.world.effect.MobEffect;

public class MythicItems {

    private static final List<Map.Entry<String, Item>> DEFERRED = new ArrayList<>();

    private static <T extends Item> T defer(String name, T item) {
        DEFERRED.add(new AbstractMap.SimpleEntry<>(name, item));
        return item;
    }

    private static FoodProperties shardFood(MobEffect effect) {
        return new FoodProperties.Builder()
            .nutrition(1).saturationMod(0.1f).alwaysEat()
            .effect(new MobEffectInstance(effect, 1200, 2, false, false, true), 1.0f)
            .build();
    }

    public static final Item AQUAMARINE = defer("aquamarine", new Item(new Item.Properties()));
    public static final Item CITRINE = defer("citrine", new Item(new Item.Properties()));
    public static final Item TOPAZ = defer("topaz", new Item(new Item.Properties()));
    public static final Item PERIDOT = defer("peridot", new Item(new Item.Properties()));
    public static final Item RUBY = defer("ruby", new Item(new Item.Properties()));
    public static final Item SAPPHIRE = defer("sapphire", new Item(new Item.Properties()));
    public static final Item JADE = defer("jade", new Item(new Item.Properties()));
    public static final Item AMETRINE = defer("ametrine", new Item(new Item.Properties()));

    public static final Item AQUAMARINE_INGOT = defer("aquamarine_ingot", new Item(new Item.Properties()));
    public static final Item CITRINE_INGOT = defer("citrine_ingot", new Item(new Item.Properties()));
    public static final Item TOPAZ_INGOT = defer("topaz_ingot", new Item(new Item.Properties()));
    public static final Item PERIDOT_INGOT = defer("peridot_ingot", new Item(new Item.Properties()));
    public static final Item RUBY_INGOT = defer("ruby_ingot", new Item(new Item.Properties()));
    public static final Item SAPPHIRE_INGOT = defer("sapphire_ingot", new Item(new Item.Properties()));
    public static final Item JADE_INGOT = defer("jade_ingot", new Item(new Item.Properties()));
    public static final Item AMETRINE_INGOT = defer("ametrine_ingot", new Item(new Item.Properties()));

    public static final Item RAW_NECOIUM = defer("raw_necoium", new Item(new Item.Properties()));
    public static final Item NECOIUM_INGOT = defer("necoium_ingot", new Item(new Item.Properties()));
    public static final Item NECOIUM_CARROT = defer("necoium_carrot", new NecoiumCarrotItem());

    public static final Item MYTHIC_UPGRADE_SMITHING_TEMPLATE = defer("mythic_upgrade_smithing_template", MythicUpgradeTemplateItem.create());

    public static final Item AQUAMARINE_SWORD = defer("aquamarine_sword", new SwordItem(MythicToolMaterials.AQUAMARINE, 3, -2.4F, new Item.Properties()));
    public static final Item AQUAMARINE_PICKAXE = defer("aquamarine_pickaxe", new PickaxeItem(MythicToolMaterials.AQUAMARINE, 1, -2.8F, new Item.Properties()) {});
    public static final Item AQUAMARINE_AXE = defer("aquamarine_axe", new AxeItem(MythicToolMaterials.AQUAMARINE, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item AQUAMARINE_SHOVEL = defer("aquamarine_shovel", new ShovelItem(MythicToolMaterials.AQUAMARINE, 1.5F, -3.0F, new Item.Properties()));
    public static final Item AQUAMARINE_HOE = defer("aquamarine_hoe", new HoeItem(MythicToolMaterials.AQUAMARINE, -4, 0.0F, new Item.Properties()) {});
    public static final Item AQUAMARINE_HELMET = defer("aquamarine_helmet", new ArmorItem(MythicArmorMaterials.AQUAMARINE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item AQUAMARINE_CHESTPLATE = defer("aquamarine_chestplate", new ArmorItem(MythicArmorMaterials.AQUAMARINE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item AQUAMARINE_LEGGINGS = defer("aquamarine_leggings", new ArmorItem(MythicArmorMaterials.AQUAMARINE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item AQUAMARINE_BOOTS = defer("aquamarine_boots", new ArmorItem(MythicArmorMaterials.AQUAMARINE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final Item CITRINE_SWORD = defer("citrine_sword", new SwordItem(MythicToolMaterials.CITRINE, 3, -2.4F, new Item.Properties()));
    public static final Item CITRINE_PICKAXE = defer("citrine_pickaxe", new PickaxeItem(MythicToolMaterials.CITRINE, 1, -2.8F, new Item.Properties()) {});
    public static final Item CITRINE_AXE = defer("citrine_axe", new AxeItem(MythicToolMaterials.CITRINE, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item CITRINE_SHOVEL = defer("citrine_shovel", new ShovelItem(MythicToolMaterials.CITRINE, 1.5F, -3.0F, new Item.Properties()));
    public static final Item CITRINE_HOE = defer("citrine_hoe", new HoeItem(MythicToolMaterials.CITRINE, -4, 0.0F, new Item.Properties()) {});
    public static final Item CITRINE_HELMET = defer("citrine_helmet", new ArmorItem(MythicArmorMaterials.CITRINE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item CITRINE_CHESTPLATE = defer("citrine_chestplate", new ArmorItem(MythicArmorMaterials.CITRINE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item CITRINE_LEGGINGS = defer("citrine_leggings", new ArmorItem(MythicArmorMaterials.CITRINE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item CITRINE_BOOTS = defer("citrine_boots", new ArmorItem(MythicArmorMaterials.CITRINE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final Item TOPAZ_SWORD = defer("topaz_sword", new SwordItem(MythicToolMaterials.TOPAZ, 3, -2.4F, new Item.Properties()));
    public static final Item TOPAZ_PICKAXE = defer("topaz_pickaxe", new PickaxeItem(MythicToolMaterials.TOPAZ, 1, -2.8F, new Item.Properties()) {});
    public static final Item TOPAZ_AXE = defer("topaz_axe", new AxeItem(MythicToolMaterials.TOPAZ, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item TOPAZ_SHOVEL = defer("topaz_shovel", new ShovelItem(MythicToolMaterials.TOPAZ, 1.5F, -3.0F, new Item.Properties()));
    public static final Item TOPAZ_HOE = defer("topaz_hoe", new HoeItem(MythicToolMaterials.TOPAZ, -4, 0.0F, new Item.Properties()) {});
    public static final Item TOPAZ_HELMET = defer("topaz_helmet", new ArmorItem(MythicArmorMaterials.TOPAZ, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item TOPAZ_CHESTPLATE = defer("topaz_chestplate", new ArmorItem(MythicArmorMaterials.TOPAZ, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item TOPAZ_LEGGINGS = defer("topaz_leggings", new ArmorItem(MythicArmorMaterials.TOPAZ, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item TOPAZ_BOOTS = defer("topaz_boots", new ArmorItem(MythicArmorMaterials.TOPAZ, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final Item PERIDOT_SWORD = defer("peridot_sword", new SwordItem(MythicToolMaterials.PERIDOT, 3, -2.4F, new Item.Properties()));
    public static final Item PERIDOT_PICKAXE = defer("peridot_pickaxe", new PickaxeItem(MythicToolMaterials.PERIDOT, 1, -2.8F, new Item.Properties()) {});
    public static final Item PERIDOT_AXE = defer("peridot_axe", new AxeItem(MythicToolMaterials.PERIDOT, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item PERIDOT_SHOVEL = defer("peridot_shovel", new ShovelItem(MythicToolMaterials.PERIDOT, 1.5F, -3.0F, new Item.Properties()));
    public static final Item PERIDOT_HOE = defer("peridot_hoe", new HoeItem(MythicToolMaterials.PERIDOT, -4, 0.0F, new Item.Properties()) {});
    public static final Item PERIDOT_HELMET = defer("peridot_helmet", new ArmorItem(MythicArmorMaterials.PERIDOT, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item PERIDOT_CHESTPLATE = defer("peridot_chestplate", new ArmorItem(MythicArmorMaterials.PERIDOT, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item PERIDOT_LEGGINGS = defer("peridot_leggings", new ArmorItem(MythicArmorMaterials.PERIDOT, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item PERIDOT_BOOTS = defer("peridot_boots", new ArmorItem(MythicArmorMaterials.PERIDOT, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final Item RUBY_SWORD = defer("ruby_sword", new SwordItem(MythicToolMaterials.RUBY, 3, -2.4F, new Item.Properties()));
    public static final Item RUBY_PICKAXE = defer("ruby_pickaxe", new PickaxeItem(MythicToolMaterials.RUBY, 1, -2.8F, new Item.Properties()) {});
    public static final Item RUBY_AXE = defer("ruby_axe", new AxeItem(MythicToolMaterials.RUBY, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item RUBY_SHOVEL = defer("ruby_shovel", new ShovelItem(MythicToolMaterials.RUBY, 1.5F, -3.0F, new Item.Properties()));
    public static final Item RUBY_HOE = defer("ruby_hoe", new HoeItem(MythicToolMaterials.RUBY, -4, 0.0F, new Item.Properties()) {});
    public static final Item RUBY_HELMET = defer("ruby_helmet", new ArmorItem(MythicArmorMaterials.RUBY, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item RUBY_CHESTPLATE = defer("ruby_chestplate", new ArmorItem(MythicArmorMaterials.RUBY, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item RUBY_LEGGINGS = defer("ruby_leggings", new ArmorItem(MythicArmorMaterials.RUBY, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item RUBY_BOOTS = defer("ruby_boots", new ArmorItem(MythicArmorMaterials.RUBY, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final Item SAPPHIRE_SWORD = defer("sapphire_sword", new SwordItem(MythicToolMaterials.SAPPHIRE, 4, -2.4F, new Item.Properties()));
    public static final Item SAPPHIRE_PICKAXE = defer("sapphire_pickaxe", new PickaxeItem(MythicToolMaterials.SAPPHIRE, 2, -2.8F, new Item.Properties()) {});
    public static final Item SAPPHIRE_AXE = defer("sapphire_axe", new AxeItem(MythicToolMaterials.SAPPHIRE, 6.0F, -3.0F, new Item.Properties()) {});
    public static final Item SAPPHIRE_SHOVEL = defer("sapphire_shovel", new ShovelItem(MythicToolMaterials.SAPPHIRE, 2.5F, -3.0F, new Item.Properties()));
    public static final Item SAPPHIRE_HOE = defer("sapphire_hoe", new HoeItem(MythicToolMaterials.SAPPHIRE, -3, 0.0F, new Item.Properties()) {});
    public static final Item SAPPHIRE_HELMET = defer("sapphire_helmet", new ArmorItem(MythicArmorMaterials.SAPPHIRE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item SAPPHIRE_CHESTPLATE = defer("sapphire_chestplate", new ArmorItem(MythicArmorMaterials.SAPPHIRE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item SAPPHIRE_LEGGINGS = defer("sapphire_leggings", new ArmorItem(MythicArmorMaterials.SAPPHIRE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item SAPPHIRE_BOOTS = defer("sapphire_boots", new ArmorItem(MythicArmorMaterials.SAPPHIRE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final Item JADE_SWORD = defer("jade_sword", new SwordItem(MythicToolMaterials.JADE, 3, -1.6F, new Item.Properties()));
    public static final Item JADE_PICKAXE = defer("jade_pickaxe", new PickaxeItem(MythicToolMaterials.JADE, 1, -2.0F, new Item.Properties()) {});
    public static final Item JADE_AXE = defer("jade_axe", new AxeItem(MythicToolMaterials.JADE, 5.0F, -2.2F, new Item.Properties()) {});
    public static final Item JADE_SHOVEL = defer("jade_shovel", new ShovelItem(MythicToolMaterials.JADE, 1.5F, -2.2F, new Item.Properties()));
    public static final Item JADE_HOE = defer("jade_hoe", new HoeItem(MythicToolMaterials.JADE, -4, 0.8F, new Item.Properties()) {});
    public static final Item JADE_HELMET = defer("jade_helmet", new ArmorItem(MythicArmorMaterials.JADE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item JADE_CHESTPLATE = defer("jade_chestplate", new ArmorItem(MythicArmorMaterials.JADE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item JADE_LEGGINGS = defer("jade_leggings", new ArmorItem(MythicArmorMaterials.JADE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item JADE_BOOTS = defer("jade_boots", new ArmorItem(MythicArmorMaterials.JADE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final Item AMETRINE_SWORD = defer("ametrine_sword", new SwordItem(MythicToolMaterials.AMETRINE, 3, -2.4F, new Item.Properties()));
    public static final Item AMETRINE_PICKAXE = defer("ametrine_pickaxe", new PickaxeItem(MythicToolMaterials.AMETRINE, 1, -2.8F, new Item.Properties()) {});
    public static final Item AMETRINE_AXE = defer("ametrine_axe", new AxeItem(MythicToolMaterials.AMETRINE, 5.0F, -3.0F, new Item.Properties()) {});
    public static final Item AMETRINE_SHOVEL = defer("ametrine_shovel", new ShovelItem(MythicToolMaterials.AMETRINE, 1.5F, -3.0F, new Item.Properties()));
    public static final Item AMETRINE_HOE = defer("ametrine_hoe", new HoeItem(MythicToolMaterials.AMETRINE, -4, 0.0F, new Item.Properties()) {});
    public static final Item AMETRINE_HELMET = defer("ametrine_helmet", new ArmorItem(MythicArmorMaterials.AMETRINE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item AMETRINE_CHESTPLATE = defer("ametrine_chestplate", new ArmorItem(MythicArmorMaterials.AMETRINE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item AMETRINE_LEGGINGS = defer("ametrine_leggings", new ArmorItem(MythicArmorMaterials.AMETRINE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item AMETRINE_BOOTS = defer("ametrine_boots", new ArmorItem(MythicArmorMaterials.AMETRINE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final Item AQUAMARINE_CRYSTAL_SHARD = defer("aquamarine_crystal_shard", new Item(new Item.Properties().food(shardFood(MythicEffects.ICE_SHIELD))));
    public static final Item CITRINE_CRYSTAL_SHARD = defer("citrine_crystal_shard", new Item(new Item.Properties().food(shardFood(MythicEffects.STATIC_FIELD))));
    public static final Item TOPAZ_CRYSTAL_SHARD = defer("topaz_crystal_shard", new Item(new Item.Properties().food(shardFood(MythicEffects.TOPAZ_REACTION))));
    public static final Item PERIDOT_CRYSTAL_SHARD = defer("peridot_crystal_shard", new Item(new Item.Properties().food(shardFood(MythicEffects.MIASMA))));
    public static final Item RUBY_CRYSTAL_SHARD = defer("ruby_crystal_shard", new Item(new Item.Properties().food(shardFood(MythicEffects.BLOOD_THIRST))));
    public static final Item SAPPHIRE_CRYSTAL_SHARD = defer("sapphire_crystal_shard", new Item(new Item.Properties().food(shardFood(MythicEffects.DAMAGE_DEFLECTION))));
    public static final Item JADE_CRYSTAL_SHARD = defer("jade_crystal_shard", new Item(new Item.Properties().food(shardFood(MythicEffects.JADE_AURA))));
    public static final Item AMETRINE_CRYSTAL_SHARD = defer("ametrine_crystal_shard", new Item(new Item.Properties().food(shardFood(MythicEffects.ARCANE_AURA))));

    // Legacy potion items — kept registered so old inventories don't lose them as "unknown items".
    // A join-time migration converts these to the new vanilla-style potions. Remove in the next update.
    public static final Item LEGACY_AQUAMARINE_POTION = defer("aquamarine_potion", new Item(new Item.Properties()));
    public static final Item LEGACY_CITRINE_POTION    = defer("citrine_potion",    new Item(new Item.Properties()));
    public static final Item LEGACY_TOPAZ_POTION      = defer("topaz_potion",      new Item(new Item.Properties()));
    public static final Item LEGACY_PERIDOT_POTION    = defer("peridot_potion",    new Item(new Item.Properties()));
    public static final Item LEGACY_RUBY_POTION       = defer("ruby_potion",       new Item(new Item.Properties()));
    public static final Item LEGACY_SAPPHIRE_POTION   = defer("sapphire_potion",   new Item(new Item.Properties()));
    public static final Item LEGACY_JADE_POTION       = defer("jade_potion",       new Item(new Item.Properties()));
    public static final Item LEGACY_AMETRINE_POTION   = defer("ametrine_potion",   new Item(new Item.Properties()));

    // Legacy per-gem smithing templates — unified into one universal template. Remove in the next update.
    public static final Item LEGACY_AQUAMARINE_TEMPLATE = defer("aquamarine_upgrade_smithing_template", new Item(new Item.Properties()));
    public static final Item LEGACY_CITRINE_TEMPLATE    = defer("citrine_upgrade_smithing_template",    new Item(new Item.Properties()));
    public static final Item LEGACY_TOPAZ_TEMPLATE      = defer("topaz_upgrade_smithing_template",      new Item(new Item.Properties()));
    public static final Item LEGACY_PERIDOT_TEMPLATE    = defer("peridot_upgrade_smithing_template",    new Item(new Item.Properties()));
    public static final Item LEGACY_RUBY_TEMPLATE       = defer("ruby_upgrade_smithing_template",       new Item(new Item.Properties()));
    public static final Item LEGACY_SAPPHIRE_TEMPLATE   = defer("sapphire_upgrade_smithing_template",   new Item(new Item.Properties()));
    public static final Item LEGACY_JADE_TEMPLATE       = defer("jade_upgrade_smithing_template",       new Item(new Item.Properties()));
    public static final Item LEGACY_AMETRINE_TEMPLATE   = defer("ametrine_upgrade_smithing_template",   new Item(new Item.Properties()));

    public static void register(BiFunction<String, Item, Item> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
        Constants.LOG.info("MythicItems registered.");
    }
}
