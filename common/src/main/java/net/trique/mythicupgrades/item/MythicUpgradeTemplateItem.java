package net.trique.mythicupgrades.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;
import net.trique.mythicupgrades.Constants;

import java.util.List;

public class MythicUpgradeTemplateItem extends SmithingTemplateItem {

    private static final ResourceLocation EMPTY_SLOT_HELMET     = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS   = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS      = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_SWORD      = ResourceLocation.withDefaultNamespace("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE    = ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_AXE        = ResourceLocation.withDefaultNamespace("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_HOE        = ResourceLocation.withDefaultNamespace("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL     = ResourceLocation.withDefaultNamespace("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_INGOT      = ResourceLocation.withDefaultNamespace("item/empty_slot_ingot");

    private static final String DESC_ID = Util.makeDescriptionId("item",
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "mythic_upgrade_smithing_template"));

    private static final Component APPLIES_TO = Component.translatable(
            Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "mythic_upgrade_smithing_template.applies_to")))
            .withStyle(ChatFormatting.BLUE);
    private static final Component INGREDIENTS = Component.translatable(
            Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "mythic_upgrade_smithing_template.ingredients")))
            .withStyle(ChatFormatting.BLUE);
    private static final Component TITLE = Component.translatable(
            Util.makeDescriptionId("upgrade", ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "mythic_upgrade")))
            .withStyle(ChatFormatting.GRAY);
    private static final Component BASE_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "mythic_upgrade_smithing_template.base_slot_description")));
    private static final Component ADDITIONS_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "mythic_upgrade_smithing_template.additions_slot_description")));

    private MythicUpgradeTemplateItem() {
        super(
            APPLIES_TO,
            INGREDIENTS,
            TITLE,
            BASE_SLOT_DESCRIPTION,
            ADDITIONS_SLOT_DESCRIPTION,
            List.of(
                EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE,
                EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL
            ),
            List.of(EMPTY_SLOT_INGOT)
        );
    }

    @Override
    public String getDescriptionId() {
        return DESC_ID;
    }

    public static MythicUpgradeTemplateItem create() {
        return new MythicUpgradeTemplateItem();
    }
}
