package net.trique.mythicupgrades;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.trique.mythicupgrades.item.MythicItems;

import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class MythicPotions {

    private static final List<Map.Entry<String, Potion>> DEFERRED = new ArrayList<>();

    private static Potion def(String id, String baseName, MobEffect effect, int duration, int amplifier) {
        Potion p = new Potion(baseName, new MobEffectInstance(effect, duration, amplifier));
        DEFERRED.add(new AbstractMap.SimpleEntry<>(id, p));
        return p;
    }

    // Aquamarine → Ice Shield (3:00 III / 8:00 III / 1:30 V)
    public static final Potion ICE_SHIELD        = def("ice_shield",        "ice_shield", MythicEffects.ICE_SHIELD, 3600, 2);
    public static final Potion ICE_SHIELD_LONG   = def("long_ice_shield",   "ice_shield", MythicEffects.ICE_SHIELD, 9600, 2);
    public static final Potion ICE_SHIELD_STRONG = def("strong_ice_shield", "ice_shield", MythicEffects.ICE_SHIELD, 1800, 4);

    // Citrine → Static Field
    public static final Potion STATIC_FIELD        = def("static_field",        "static_field", MythicEffects.STATIC_FIELD, 3600, 2);
    public static final Potion STATIC_FIELD_LONG   = def("long_static_field",   "static_field", MythicEffects.STATIC_FIELD, 9600, 2);
    public static final Potion STATIC_FIELD_STRONG = def("strong_static_field", "static_field", MythicEffects.STATIC_FIELD, 1800, 4);

    // Topaz → Topaz Reaction
    public static final Potion TOPAZ_REACTION        = def("topaz_reaction",        "topaz_reaction", MythicEffects.TOPAZ_REACTION, 3600, 2);
    public static final Potion TOPAZ_REACTION_LONG   = def("long_topaz_reaction",   "topaz_reaction", MythicEffects.TOPAZ_REACTION, 9600, 2);
    public static final Potion TOPAZ_REACTION_STRONG = def("strong_topaz_reaction", "topaz_reaction", MythicEffects.TOPAZ_REACTION, 1800, 4);

    // Peridot → Miasma
    public static final Potion MIASMA        = def("miasma",        "miasma", MythicEffects.MIASMA, 3600, 2);
    public static final Potion MIASMA_LONG   = def("long_miasma",   "miasma", MythicEffects.MIASMA, 9600, 2);
    public static final Potion MIASMA_STRONG = def("strong_miasma", "miasma", MythicEffects.MIASMA, 1800, 4);

    // Ruby → Blood Thirst
    public static final Potion BLOOD_THIRST        = def("blood_thirst",        "blood_thirst", MythicEffects.BLOOD_THIRST, 3600, 2);
    public static final Potion BLOOD_THIRST_LONG   = def("long_blood_thirst",   "blood_thirst", MythicEffects.BLOOD_THIRST, 9600, 2);
    public static final Potion BLOOD_THIRST_STRONG = def("strong_blood_thirst", "blood_thirst", MythicEffects.BLOOD_THIRST, 1800, 4);

    // Sapphire → Damage Deflection
    public static final Potion DAMAGE_DEFLECTION        = def("damage_deflection",        "damage_deflection", MythicEffects.DAMAGE_DEFLECTION, 3600, 2);
    public static final Potion DAMAGE_DEFLECTION_LONG   = def("long_damage_deflection",   "damage_deflection", MythicEffects.DAMAGE_DEFLECTION, 9600, 2);
    public static final Potion DAMAGE_DEFLECTION_STRONG = def("strong_damage_deflection", "damage_deflection", MythicEffects.DAMAGE_DEFLECTION, 1800, 4);

    // Jade → Jade Aura
    public static final Potion JADE_AURA        = def("jade_aura",        "jade_aura", MythicEffects.JADE_AURA, 3600, 2);
    public static final Potion JADE_AURA_LONG   = def("long_jade_aura",   "jade_aura", MythicEffects.JADE_AURA, 9600, 2);
    public static final Potion JADE_AURA_STRONG = def("strong_jade_aura", "jade_aura", MythicEffects.JADE_AURA, 1800, 4);

    // Ametrine → Arcane Aura
    public static final Potion ARCANE_AURA        = def("arcane_aura",        "arcane_aura", MythicEffects.ARCANE_AURA, 3600, 2);
    public static final Potion ARCANE_AURA_LONG   = def("long_arcane_aura",   "arcane_aura", MythicEffects.ARCANE_AURA, 9600, 2);
    public static final Potion ARCANE_AURA_STRONG = def("strong_arcane_aura", "arcane_aura", MythicEffects.ARCANE_AURA, 1800, 4);

    public static void register(BiFunction<String, Potion, Potion> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
        Constants.LOG.info("MythicPotions registered.");
    }

    public static void registerBrewingRecipes() {
        try {
            Method addMix = PotionBrewing.class.getDeclaredMethod("addMix", Potion.class, Item.class, Potion.class);
            addMix.setAccessible(true);

            mix(addMix, Potions.AWKWARD, MythicItems.AQUAMARINE_CRYSTAL_SHARD, ICE_SHIELD);
            mix(addMix, ICE_SHIELD, Items.REDSTONE, ICE_SHIELD_LONG);
            mix(addMix, ICE_SHIELD, Items.GLOWSTONE_DUST, ICE_SHIELD_STRONG);

            mix(addMix, Potions.AWKWARD, MythicItems.CITRINE_CRYSTAL_SHARD, STATIC_FIELD);
            mix(addMix, STATIC_FIELD, Items.REDSTONE, STATIC_FIELD_LONG);
            mix(addMix, STATIC_FIELD, Items.GLOWSTONE_DUST, STATIC_FIELD_STRONG);

            mix(addMix, Potions.AWKWARD, MythicItems.TOPAZ_CRYSTAL_SHARD, TOPAZ_REACTION);
            mix(addMix, TOPAZ_REACTION, Items.REDSTONE, TOPAZ_REACTION_LONG);
            mix(addMix, TOPAZ_REACTION, Items.GLOWSTONE_DUST, TOPAZ_REACTION_STRONG);

            mix(addMix, Potions.AWKWARD, MythicItems.PERIDOT_CRYSTAL_SHARD, MIASMA);
            mix(addMix, MIASMA, Items.REDSTONE, MIASMA_LONG);
            mix(addMix, MIASMA, Items.GLOWSTONE_DUST, MIASMA_STRONG);

            mix(addMix, Potions.AWKWARD, MythicItems.RUBY_CRYSTAL_SHARD, BLOOD_THIRST);
            mix(addMix, BLOOD_THIRST, Items.REDSTONE, BLOOD_THIRST_LONG);
            mix(addMix, BLOOD_THIRST, Items.GLOWSTONE_DUST, BLOOD_THIRST_STRONG);

            mix(addMix, Potions.AWKWARD, MythicItems.SAPPHIRE_CRYSTAL_SHARD, DAMAGE_DEFLECTION);
            mix(addMix, DAMAGE_DEFLECTION, Items.REDSTONE, DAMAGE_DEFLECTION_LONG);
            mix(addMix, DAMAGE_DEFLECTION, Items.GLOWSTONE_DUST, DAMAGE_DEFLECTION_STRONG);

            mix(addMix, Potions.AWKWARD, MythicItems.JADE_CRYSTAL_SHARD, JADE_AURA);
            mix(addMix, JADE_AURA, Items.REDSTONE, JADE_AURA_LONG);
            mix(addMix, JADE_AURA, Items.GLOWSTONE_DUST, JADE_AURA_STRONG);

            mix(addMix, Potions.AWKWARD, MythicItems.AMETRINE_CRYSTAL_SHARD, ARCANE_AURA);
            mix(addMix, ARCANE_AURA, Items.REDSTONE, ARCANE_AURA_LONG);
            mix(addMix, ARCANE_AURA, Items.GLOWSTONE_DUST, ARCANE_AURA_STRONG);

            Constants.LOG.info("MythicPotions brewing recipes registered.");
        } catch (Exception e) {
            Constants.LOG.error("Failed to register brewing recipes", e);
        }
    }

    private static void mix(Method addMix, Potion input, Item ingredient, Potion output) throws Exception {
        addMix.invoke(null, input, ingredient, output);
    }
}
