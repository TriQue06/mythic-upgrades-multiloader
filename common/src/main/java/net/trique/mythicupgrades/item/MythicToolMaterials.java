package net.trique.mythicupgrades.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.trique.mythicupgrades.Constants;

public class MythicToolMaterials {

    private static TagKey<Item> repairTag(String gem) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, gem + "_tool_materials"));
    }

    private static ToolMaterial make(String gem) {
        return new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9.0F, 4.0F, 15, repairTag(gem));
    }

    public static final ToolMaterial AQUAMARINE = make("aquamarine");
    public static final ToolMaterial CITRINE = make("citrine");
    public static final ToolMaterial TOPAZ = make("topaz");
    public static final ToolMaterial PERIDOT = make("peridot");
    public static final ToolMaterial RUBY = make("ruby");
    public static final ToolMaterial SAPPHIRE = make("sapphire");
    public static final ToolMaterial JADE = make("jade");
    public static final ToolMaterial AMETRINE = make("ametrine");
}
