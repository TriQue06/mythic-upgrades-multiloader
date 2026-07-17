package net.trique.mythicupgrades.item;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.MythicEffects;

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

    private static Item.Properties props(String name) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(Constants.MOD_ID, name)));
    }

    private static Item.Properties trimMat(String name, String gem) {
        return props(name).trimMaterial(ResourceKey.create(Registries.TRIM_MATERIAL,
                Identifier.fromNamespaceAndPath(Constants.MOD_ID, gem)));
    }

    private static Item armor(String name, ArmorMaterial material, ArmorType type) {
        return new Item(props(name).humanoidArmor(material, type));
    }

    private static Item.Properties shardFood(String name, String gem, Holder<MobEffect> effect) {
        FoodProperties food = new FoodProperties.Builder()
            .nutrition(1).saturationModifier(0.1f).alwaysEdible()
            .build();
        Consumable consumable = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                new MobEffectInstance(effect, 1800, 2, false, false, true), 1.0f))
            .build();
        return trimMat(name, gem).food(food, consumable);
    }

    public static final Item AQUAMARINE = defer("aquamarine", new Item(props("aquamarine")));
    public static final Item CITRINE = defer("citrine", new Item(props("citrine")));
    public static final Item TOPAZ = defer("topaz", new Item(props("topaz")));
    public static final Item PERIDOT = defer("peridot", new Item(props("peridot")));
    public static final Item RUBY = defer("ruby", new Item(props("ruby")));
    public static final Item SAPPHIRE = defer("sapphire", new Item(props("sapphire")));
    public static final Item JADE = defer("jade", new Item(props("jade")));
    public static final Item AMETRINE = defer("ametrine", new Item(props("ametrine")));

    public static final Item AQUAMARINE_INGOT = defer("aquamarine_ingot", new Item(props("aquamarine_ingot")));
    public static final Item CITRINE_INGOT = defer("citrine_ingot", new Item(props("citrine_ingot")));
    public static final Item TOPAZ_INGOT = defer("topaz_ingot", new Item(props("topaz_ingot")));
    public static final Item PERIDOT_INGOT = defer("peridot_ingot", new Item(props("peridot_ingot")));
    public static final Item RUBY_INGOT = defer("ruby_ingot", new Item(props("ruby_ingot")));
    public static final Item SAPPHIRE_INGOT = defer("sapphire_ingot", new Item(props("sapphire_ingot")));
    public static final Item JADE_INGOT = defer("jade_ingot", new Item(props("jade_ingot")));
    public static final Item AMETRINE_INGOT = defer("ametrine_ingot", new Item(props("ametrine_ingot")));

    public static final Item RAW_NECOIUM = defer("raw_necoium", new Item(props("raw_necoium")));
    public static final Item NECOIUM_INGOT = defer("necoium_ingot", new Item(trimMat("necoium_ingot", "necoium")));
    public static final Item NECOIUM_CARROT = defer("necoium_carrot", new Item(props("necoium_carrot")
            .food(new FoodProperties.Builder().nutrition(6).saturationModifier(1.5f).alwaysEdible().build(),
                  Consumables.defaultFood()
                      .onConsume(new ApplyStatusEffectsConsumeEffect(
                          new MobEffectInstance(MythicEffects.NECOIUM_SHARE, 6000, 0), 1.0f))
                      .build())));

    public static final Item MYTHIC_UPGRADE_SMITHING_TEMPLATE = defer("mythic_upgrade_smithing_template",
            MythicUpgradeTemplateItem.create(props("mythic_upgrade_smithing_template").rarity(Rarity.UNCOMMON)));

    public static final Item AQUAMARINE_SWORD = defer("aquamarine_sword", new Item(props("aquamarine_sword").sword(MythicToolMaterials.AQUAMARINE, 3.0F, -2.4F)));
    public static final Item AQUAMARINE_PICKAXE = defer("aquamarine_pickaxe", new Item(props("aquamarine_pickaxe").pickaxe(MythicToolMaterials.AQUAMARINE, 1.0F, -2.8F)));
    public static final Item AQUAMARINE_AXE = defer("aquamarine_axe", new AxeItem(MythicToolMaterials.AQUAMARINE, 5.0F, -3.0F, props("aquamarine_axe")));
    public static final Item AQUAMARINE_SHOVEL = defer("aquamarine_shovel", new ShovelItem(MythicToolMaterials.AQUAMARINE, 1.5F, -3.0F, props("aquamarine_shovel")));
    public static final Item AQUAMARINE_HOE = defer("aquamarine_hoe", new HoeItem(MythicToolMaterials.AQUAMARINE, -4.0F, 0.0F, props("aquamarine_hoe")));
    public static final Item AQUAMARINE_HELMET = defer("aquamarine_helmet", armor("aquamarine_helmet", MythicArmorMaterials.AQUAMARINE, ArmorType.HELMET));
    public static final Item AQUAMARINE_CHESTPLATE = defer("aquamarine_chestplate", armor("aquamarine_chestplate", MythicArmorMaterials.AQUAMARINE, ArmorType.CHESTPLATE));
    public static final Item AQUAMARINE_LEGGINGS = defer("aquamarine_leggings", armor("aquamarine_leggings", MythicArmorMaterials.AQUAMARINE, ArmorType.LEGGINGS));
    public static final Item AQUAMARINE_BOOTS = defer("aquamarine_boots", armor("aquamarine_boots", MythicArmorMaterials.AQUAMARINE, ArmorType.BOOTS));

    public static final Item CITRINE_SWORD = defer("citrine_sword", new Item(props("citrine_sword").sword(MythicToolMaterials.CITRINE, 3.0F, -2.4F)));
    public static final Item CITRINE_PICKAXE = defer("citrine_pickaxe", new Item(props("citrine_pickaxe").pickaxe(MythicToolMaterials.CITRINE, 1.0F, -2.8F)));
    public static final Item CITRINE_AXE = defer("citrine_axe", new AxeItem(MythicToolMaterials.CITRINE, 5.0F, -3.0F, props("citrine_axe")));
    public static final Item CITRINE_SHOVEL = defer("citrine_shovel", new ShovelItem(MythicToolMaterials.CITRINE, 1.5F, -3.0F, props("citrine_shovel")));
    public static final Item CITRINE_HOE = defer("citrine_hoe", new HoeItem(MythicToolMaterials.CITRINE, -4.0F, 0.0F, props("citrine_hoe")));
    public static final Item CITRINE_HELMET = defer("citrine_helmet", armor("citrine_helmet", MythicArmorMaterials.CITRINE, ArmorType.HELMET));
    public static final Item CITRINE_CHESTPLATE = defer("citrine_chestplate", armor("citrine_chestplate", MythicArmorMaterials.CITRINE, ArmorType.CHESTPLATE));
    public static final Item CITRINE_LEGGINGS = defer("citrine_leggings", armor("citrine_leggings", MythicArmorMaterials.CITRINE, ArmorType.LEGGINGS));
    public static final Item CITRINE_BOOTS = defer("citrine_boots", armor("citrine_boots", MythicArmorMaterials.CITRINE, ArmorType.BOOTS));

    public static final Item TOPAZ_SWORD = defer("topaz_sword", new Item(props("topaz_sword").sword(MythicToolMaterials.TOPAZ, 3.0F, -2.4F)));
    public static final Item TOPAZ_PICKAXE = defer("topaz_pickaxe", new Item(props("topaz_pickaxe").pickaxe(MythicToolMaterials.TOPAZ, 1.0F, -2.8F)));
    public static final Item TOPAZ_AXE = defer("topaz_axe", new AxeItem(MythicToolMaterials.TOPAZ, 5.0F, -3.0F, props("topaz_axe")));
    public static final Item TOPAZ_SHOVEL = defer("topaz_shovel", new ShovelItem(MythicToolMaterials.TOPAZ, 1.5F, -3.0F, props("topaz_shovel")));
    public static final Item TOPAZ_HOE = defer("topaz_hoe", new HoeItem(MythicToolMaterials.TOPAZ, -4.0F, 0.0F, props("topaz_hoe")));
    public static final Item TOPAZ_HELMET = defer("topaz_helmet", armor("topaz_helmet", MythicArmorMaterials.TOPAZ, ArmorType.HELMET));
    public static final Item TOPAZ_CHESTPLATE = defer("topaz_chestplate", armor("topaz_chestplate", MythicArmorMaterials.TOPAZ, ArmorType.CHESTPLATE));
    public static final Item TOPAZ_LEGGINGS = defer("topaz_leggings", armor("topaz_leggings", MythicArmorMaterials.TOPAZ, ArmorType.LEGGINGS));
    public static final Item TOPAZ_BOOTS = defer("topaz_boots", armor("topaz_boots", MythicArmorMaterials.TOPAZ, ArmorType.BOOTS));

    public static final Item PERIDOT_SWORD = defer("peridot_sword", new Item(props("peridot_sword").sword(MythicToolMaterials.PERIDOT, 3.0F, -2.4F)));
    public static final Item PERIDOT_PICKAXE = defer("peridot_pickaxe", new Item(props("peridot_pickaxe").pickaxe(MythicToolMaterials.PERIDOT, 1.0F, -2.8F)));
    public static final Item PERIDOT_AXE = defer("peridot_axe", new AxeItem(MythicToolMaterials.PERIDOT, 5.0F, -3.0F, props("peridot_axe")));
    public static final Item PERIDOT_SHOVEL = defer("peridot_shovel", new ShovelItem(MythicToolMaterials.PERIDOT, 1.5F, -3.0F, props("peridot_shovel")));
    public static final Item PERIDOT_HOE = defer("peridot_hoe", new HoeItem(MythicToolMaterials.PERIDOT, -4.0F, 0.0F, props("peridot_hoe")));
    public static final Item PERIDOT_HELMET = defer("peridot_helmet", armor("peridot_helmet", MythicArmorMaterials.PERIDOT, ArmorType.HELMET));
    public static final Item PERIDOT_CHESTPLATE = defer("peridot_chestplate", armor("peridot_chestplate", MythicArmorMaterials.PERIDOT, ArmorType.CHESTPLATE));
    public static final Item PERIDOT_LEGGINGS = defer("peridot_leggings", armor("peridot_leggings", MythicArmorMaterials.PERIDOT, ArmorType.LEGGINGS));
    public static final Item PERIDOT_BOOTS = defer("peridot_boots", armor("peridot_boots", MythicArmorMaterials.PERIDOT, ArmorType.BOOTS));

    public static final Item RUBY_SWORD = defer("ruby_sword", new Item(props("ruby_sword").sword(MythicToolMaterials.RUBY, 3.0F, -2.4F)));
    public static final Item RUBY_PICKAXE = defer("ruby_pickaxe", new Item(props("ruby_pickaxe").pickaxe(MythicToolMaterials.RUBY, 1.0F, -2.8F)));
    public static final Item RUBY_AXE = defer("ruby_axe", new AxeItem(MythicToolMaterials.RUBY, 5.0F, -3.0F, props("ruby_axe")));
    public static final Item RUBY_SHOVEL = defer("ruby_shovel", new ShovelItem(MythicToolMaterials.RUBY, 1.5F, -3.0F, props("ruby_shovel")));
    public static final Item RUBY_HOE = defer("ruby_hoe", new HoeItem(MythicToolMaterials.RUBY, -4.0F, 0.0F, props("ruby_hoe")));
    public static final Item RUBY_HELMET = defer("ruby_helmet", armor("ruby_helmet", MythicArmorMaterials.RUBY, ArmorType.HELMET));
    public static final Item RUBY_CHESTPLATE = defer("ruby_chestplate", armor("ruby_chestplate", MythicArmorMaterials.RUBY, ArmorType.CHESTPLATE));
    public static final Item RUBY_LEGGINGS = defer("ruby_leggings", armor("ruby_leggings", MythicArmorMaterials.RUBY, ArmorType.LEGGINGS));
    public static final Item RUBY_BOOTS = defer("ruby_boots", armor("ruby_boots", MythicArmorMaterials.RUBY, ArmorType.BOOTS));

    public static final Item SAPPHIRE_SWORD = defer("sapphire_sword", new Item(props("sapphire_sword").sword(MythicToolMaterials.SAPPHIRE, 4.0F, -2.4F)));
    public static final Item SAPPHIRE_PICKAXE = defer("sapphire_pickaxe", new Item(props("sapphire_pickaxe").pickaxe(MythicToolMaterials.SAPPHIRE, 2.0F, -2.8F)));
    public static final Item SAPPHIRE_AXE = defer("sapphire_axe", new AxeItem(MythicToolMaterials.SAPPHIRE, 6.0F, -3.0F, props("sapphire_axe")));
    public static final Item SAPPHIRE_SHOVEL = defer("sapphire_shovel", new ShovelItem(MythicToolMaterials.SAPPHIRE, 2.5F, -3.0F, props("sapphire_shovel")));
    public static final Item SAPPHIRE_HOE = defer("sapphire_hoe", new HoeItem(MythicToolMaterials.SAPPHIRE, -3.0F, 0.0F, props("sapphire_hoe")));
    public static final Item SAPPHIRE_HELMET = defer("sapphire_helmet", armor("sapphire_helmet", MythicArmorMaterials.SAPPHIRE, ArmorType.HELMET));
    public static final Item SAPPHIRE_CHESTPLATE = defer("sapphire_chestplate", armor("sapphire_chestplate", MythicArmorMaterials.SAPPHIRE, ArmorType.CHESTPLATE));
    public static final Item SAPPHIRE_LEGGINGS = defer("sapphire_leggings", armor("sapphire_leggings", MythicArmorMaterials.SAPPHIRE, ArmorType.LEGGINGS));
    public static final Item SAPPHIRE_BOOTS = defer("sapphire_boots", armor("sapphire_boots", MythicArmorMaterials.SAPPHIRE, ArmorType.BOOTS));

    public static final Item JADE_SWORD = defer("jade_sword", new Item(props("jade_sword").sword(MythicToolMaterials.JADE, 3.0F, -2.4F)));
    public static final Item JADE_PICKAXE = defer("jade_pickaxe", new Item(props("jade_pickaxe").pickaxe(MythicToolMaterials.JADE, 1.0F, -2.8F)));
    public static final Item JADE_AXE = defer("jade_axe", new AxeItem(MythicToolMaterials.JADE, 5.0F, -3.0F, props("jade_axe")));
    public static final Item JADE_SHOVEL = defer("jade_shovel", new ShovelItem(MythicToolMaterials.JADE, 1.5F, -3.0F, props("jade_shovel")));
    public static final Item JADE_HOE = defer("jade_hoe", new HoeItem(MythicToolMaterials.JADE, -4.0F, 0.0F, props("jade_hoe")));
    public static final Item JADE_HELMET = defer("jade_helmet", armor("jade_helmet", MythicArmorMaterials.JADE, ArmorType.HELMET));
    public static final Item JADE_CHESTPLATE = defer("jade_chestplate", armor("jade_chestplate", MythicArmorMaterials.JADE, ArmorType.CHESTPLATE));
    public static final Item JADE_LEGGINGS = defer("jade_leggings", armor("jade_leggings", MythicArmorMaterials.JADE, ArmorType.LEGGINGS));
    public static final Item JADE_BOOTS = defer("jade_boots", armor("jade_boots", MythicArmorMaterials.JADE, ArmorType.BOOTS));

    public static final Item AMETRINE_SWORD = defer("ametrine_sword", new Item(props("ametrine_sword").sword(MythicToolMaterials.AMETRINE, 3.0F, -2.4F)));
    public static final Item AMETRINE_PICKAXE = defer("ametrine_pickaxe", new Item(props("ametrine_pickaxe").pickaxe(MythicToolMaterials.AMETRINE, 1.0F, -2.8F)));
    public static final Item AMETRINE_AXE = defer("ametrine_axe", new AxeItem(MythicToolMaterials.AMETRINE, 5.0F, -3.0F, props("ametrine_axe")));
    public static final Item AMETRINE_SHOVEL = defer("ametrine_shovel", new ShovelItem(MythicToolMaterials.AMETRINE, 1.5F, -3.0F, props("ametrine_shovel")));
    public static final Item AMETRINE_HOE = defer("ametrine_hoe", new HoeItem(MythicToolMaterials.AMETRINE, -4.0F, 0.0F, props("ametrine_hoe")));
    public static final Item AMETRINE_HELMET = defer("ametrine_helmet", armor("ametrine_helmet", MythicArmorMaterials.AMETRINE, ArmorType.HELMET));
    public static final Item AMETRINE_CHESTPLATE = defer("ametrine_chestplate", armor("ametrine_chestplate", MythicArmorMaterials.AMETRINE, ArmorType.CHESTPLATE));
    public static final Item AMETRINE_LEGGINGS = defer("ametrine_leggings", armor("ametrine_leggings", MythicArmorMaterials.AMETRINE, ArmorType.LEGGINGS));
    public static final Item AMETRINE_BOOTS = defer("ametrine_boots", armor("ametrine_boots", MythicArmorMaterials.AMETRINE, ArmorType.BOOTS));

    public static final Item AQUAMARINE_CRYSTAL_SHARD = defer("aquamarine_crystal_shard", new Item(shardFood("aquamarine_crystal_shard", "aquamarine", MythicEffects.ICE_SHIELD)));
    public static final Item CITRINE_CRYSTAL_SHARD = defer("citrine_crystal_shard", new Item(shardFood("citrine_crystal_shard", "citrine", MythicEffects.STATIC_FIELD)));
    public static final Item TOPAZ_CRYSTAL_SHARD = defer("topaz_crystal_shard", new Item(shardFood("topaz_crystal_shard", "topaz", MythicEffects.TOPAZ_REACTION)));
    public static final Item PERIDOT_CRYSTAL_SHARD = defer("peridot_crystal_shard", new Item(shardFood("peridot_crystal_shard", "peridot", MythicEffects.MIASMA)));
    public static final Item RUBY_CRYSTAL_SHARD = defer("ruby_crystal_shard", new Item(shardFood("ruby_crystal_shard", "ruby", MythicEffects.BLOOD_THIRST)));
    public static final Item SAPPHIRE_CRYSTAL_SHARD = defer("sapphire_crystal_shard", new Item(shardFood("sapphire_crystal_shard", "sapphire", MythicEffects.DAMAGE_DEFLECTION)));
    public static final Item JADE_CRYSTAL_SHARD = defer("jade_crystal_shard", new Item(shardFood("jade_crystal_shard", "jade", MythicEffects.JADE_AURA)));
    public static final Item AMETRINE_CRYSTAL_SHARD = defer("ametrine_crystal_shard", new Item(shardFood("ametrine_crystal_shard", "ametrine", MythicEffects.ARCANE_AURA)));

    public static void register(BiFunction<String, Item, Item> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
        Constants.LOG.info("MythicItems registered.");
    }
}
