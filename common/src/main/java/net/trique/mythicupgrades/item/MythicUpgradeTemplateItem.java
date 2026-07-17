package net.trique.mythicupgrades.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import net.trique.mythicupgrades.Constants;

import java.util.List;

public class MythicUpgradeTemplateItem extends SmithingTemplateItem {

    private static final Identifier EMPTY_SLOT_HELMET = Identifier.withDefaultNamespace("container/slot/helmet");
    private static final Identifier EMPTY_SLOT_CHESTPLATE = Identifier.withDefaultNamespace("container/slot/chestplate");
    private static final Identifier EMPTY_SLOT_LEGGINGS = Identifier.withDefaultNamespace("container/slot/leggings");
    private static final Identifier EMPTY_SLOT_BOOTS = Identifier.withDefaultNamespace("container/slot/boots");
    private static final Identifier EMPTY_SLOT_SWORD = Identifier.withDefaultNamespace("container/slot/sword");
    private static final Identifier EMPTY_SLOT_PICKAXE = Identifier.withDefaultNamespace("container/slot/pickaxe");
    private static final Identifier EMPTY_SLOT_AXE = Identifier.withDefaultNamespace("container/slot/axe");
    private static final Identifier EMPTY_SLOT_HOE = Identifier.withDefaultNamespace("container/slot/hoe");
    private static final Identifier EMPTY_SLOT_SHOVEL = Identifier.withDefaultNamespace("container/slot/shovel");
    private static final Identifier EMPTY_SLOT_INGOT = Identifier.withDefaultNamespace("container/slot/ingot");

    private static final Component APPLIES_TO = Component.translatable(
            Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Constants.MOD_ID, "mythic_upgrade_smithing_template.applies_to")))
            .withStyle(ChatFormatting.BLUE);
    private static final Component INGREDIENTS = Component.translatable(
            Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Constants.MOD_ID, "mythic_upgrade_smithing_template.ingredients")))
            .withStyle(ChatFormatting.BLUE);
    private static final Component BASE_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Constants.MOD_ID, "mythic_upgrade_smithing_template.base_slot_description")));
    private static final Component ADDITIONS_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(Constants.MOD_ID, "mythic_upgrade_smithing_template.additions_slot_description")));

    private MythicUpgradeTemplateItem(Item.Properties properties) {
        super(
            APPLIES_TO,
            INGREDIENTS,
            BASE_SLOT_DESCRIPTION,
            ADDITIONS_SLOT_DESCRIPTION,
            List.of(
                EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE,
                EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL
            ),
            List.of(EMPTY_SLOT_INGOT),
            properties
        );
    }

    public static MythicUpgradeTemplateItem create(Item.Properties properties) {
        return new MythicUpgradeTemplateItem(properties);
    }
}
