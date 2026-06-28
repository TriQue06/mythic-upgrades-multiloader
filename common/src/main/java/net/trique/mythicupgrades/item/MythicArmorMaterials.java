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

    private static final java.util.IdentityHashMap<Holder<ArmorMaterial>, Integer> DUR_MULTS = new java.util.IdentityHashMap<>();

    public static int getDurability(Holder<ArmorMaterial> material, ArmorItem.Type type) {
        int mult = DUR_MULTS.getOrDefault(material, 37);
        int idx = type.getSlot().getIndex();
        return (idx >= 0 && idx < HEALTH_PER_SLOT.length) ? HEALTH_PER_SLOT[idx] * mult : mult * 13;
    }

    private static Holder<ArmorMaterial> make(String name, int durMult, int[] protection, int enchant, float toughness, float kbRes, Ingredient repair) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath("mythicupgrades", name);
        Holder<ArmorMaterial> holder = Holder.direct(new ArmorMaterial(
            defense(protection),
            enchant,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            () -> repair,
            List.of(new ArmorMaterial.Layer(id)),
            toughness,
            kbRes
        ));
        DUR_MULTS.put(holder, durMult);
        return holder;
    }

    public static final Holder<ArmorMaterial> AQUAMARINE = make("aquamarine", 37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.AQUAMARINE_INGOT));
    public static final Holder<ArmorMaterial> CITRINE    = make("citrine",    37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.CITRINE_INGOT));
    public static final Holder<ArmorMaterial> TOPAZ      = make("topaz",      74, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.TOPAZ_INGOT));
    public static final Holder<ArmorMaterial> PERIDOT    = make("peridot",    37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.PERIDOT_INGOT));
    public static final Holder<ArmorMaterial> RUBY       = make("ruby",       37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.RUBY_INGOT));
    public static final Holder<ArmorMaterial> SAPPHIRE   = make("sapphire",   37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.SAPPHIRE_INGOT));
    public static final Holder<ArmorMaterial> JADE       = make("jade",       37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.JADE_INGOT));
    public static final Holder<ArmorMaterial> AMETRINE   = make("ametrine",   37, new int[]{3, 6, 8, 3}, 15, 3.0F, 0.1F, Ingredient.of(MythicItems.AMETRINE_INGOT));
}
