package net.trique.mythicupgrades.item;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MythicArmorMaterials {

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};

    private static Map<ArmorItem.Type, Integer> defense(int[] protection) {
        Map<ArmorItem.Type, Integer> map = new EnumMap<>(ArmorItem.Type.class);
        ArmorItem.Type[] types = ArmorItem.Type.values();
        for (int i = 0; i < types.length; i++) {
            map.put(types[i], protection[types[i].getSlot().getIndex()]);
        }
        return map;
    }

    private static Holder<ArmorMaterial> make(String name, int durMult, int[] protection, int enchant, float toughness, float kbRes, Ingredient repair) {
        Map<ArmorItem.Type, Integer> durability = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            durability.put(type, HEALTH_PER_SLOT[type.getSlot().getIndex()] * durMult);
        }
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("mythicupgrades", name);
        return Holder.direct(new ArmorMaterial(
            durability,
            enchant,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> repair,
            List.of(new ArmorMaterial.Layer(id)),
            toughness,
            kbRes
        ));
    }

    public static final Holder<ArmorMaterial> AQUAMARINE = make("aquamarine", 37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.AQUAMARINE));
    public static final Holder<ArmorMaterial> CITRINE    = make("citrine",    37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.CITRINE));
    public static final Holder<ArmorMaterial> TOPAZ      = make("topaz",      74, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.TOPAZ));
    public static final Holder<ArmorMaterial> PERIDOT    = make("peridot",    37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.PERIDOT));
    public static final Holder<ArmorMaterial> RUBY       = make("ruby",       37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.RUBY));
    public static final Holder<ArmorMaterial> SAPPHIRE   = make("sapphire",   37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.SAPPHIRE));
    public static final Holder<ArmorMaterial> JADE       = make("jade",       37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.JADE));
    public static final Holder<ArmorMaterial> AMETRINE   = make("ametrine",   37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.AMETRINE));
}
