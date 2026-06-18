package net.trique.mythicupgrades.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SmithingTrimRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.item.MythicItems;

import java.util.List;
import java.util.function.Consumer;

public class MythicRecipeProvider extends RecipeProvider {

    public MythicRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> writer) {
        // --- Gem smelting/blasting ---
        gemCooking(writer, MythicItems.AQUAMARINE, List.of(MythicBlocks.AQUAMARINE_ORE, MythicBlocks.DEEPSLATE_AQUAMARINE_ORE), "aquamarine");
        gemCooking(writer, MythicItems.KYANITE,    List.of(MythicBlocks.KYANITE_ORE,    MythicBlocks.DEEPSLATE_KYANITE_ORE),    "kyanite");
        gemCooking(writer, MythicItems.CITRINE,    List.of(MythicBlocks.CITRINE_ORE,    MythicBlocks.DEEPSLATE_CITRINE_ORE),    "citrine");
        gemCooking(writer, MythicItems.TOPAZ,      List.of(MythicBlocks.TOPAZ_ORE,      MythicBlocks.DEEPSLATE_TOPAZ_ORE),      "topaz");
        gemCooking(writer, MythicItems.PERIDOT,    List.of(MythicBlocks.PERIDOT_ORE,    MythicBlocks.DEEPSLATE_PERIDOT_ORE),    "peridot");
        gemCooking(writer, MythicItems.RUBY,       List.of(MythicBlocks.RUBY_ORE),      "ruby");
        gemCooking(writer, MythicItems.SAPPHIRE,   List.of(MythicBlocks.SAPPHIRE_ORE),  "sapphire");
        gemCooking(writer, MythicItems.JADE,       List.of(MythicBlocks.JADE_ORE),      "jade");
        gemCooking(writer, MythicItems.AMETRINE,   List.of(MythicBlocks.AMETRINE_ORE),  "ametrine");

        // --- Necoium: raw smelting + ore direct smelting ---
        // raw_necoium → necoium_ingot
        gemCooking(writer, MythicItems.NECOIUM_INGOT, List.of(MythicItems.RAW_NECOIUM), "necoium_ingot_from_raw");
        // necoium_ore → necoium_ingot (direct, like iron_ore → iron_ingot)
        gemCooking(writer, MythicItems.NECOIUM_INGOT, List.of(MythicBlocks.NECOIUM_ORE), "necoium_ingot_from_ore");

        // --- Gem storage blocks ---
        storageBlock(writer, MythicItems.AQUAMARINE, MythicBlocks.AQUAMARINE_BLOCK, "aquamarine");
        storageBlock(writer, MythicItems.KYANITE,    MythicBlocks.KYANITE_BLOCK,    "kyanite");
        storageBlock(writer, MythicItems.CITRINE,    MythicBlocks.CITRINE_BLOCK,    "citrine");
        storageBlock(writer, MythicItems.TOPAZ,      MythicBlocks.TOPAZ_BLOCK,      "topaz");
        storageBlock(writer, MythicItems.PERIDOT,    MythicBlocks.PERIDOT_BLOCK,    "peridot");
        storageBlock(writer, MythicItems.RUBY,       MythicBlocks.RUBY_BLOCK,       "ruby");
        storageBlock(writer, MythicItems.SAPPHIRE,   MythicBlocks.SAPPHIRE_BLOCK,   "sapphire");
        storageBlock(writer, MythicItems.JADE,       MythicBlocks.JADE_BLOCK,       "jade");
        storageBlock(writer, MythicItems.AMETRINE,   MythicBlocks.AMETRINE_BLOCK,   "ametrine");

        // --- Necoium storage blocks ---
        storageBlock(writer, MythicItems.RAW_NECOIUM,   MythicBlocks.RAW_NECOIUM_BLOCK, "raw_necoium");
        storageBlock(writer, MythicItems.NECOIUM_INGOT, MythicBlocks.NECOIUM_BLOCK,     "necoium_ingot");

        // --- Gem storage blocks unpack ---
        unpackBlock(writer, MythicBlocks.AQUAMARINE_BLOCK, MythicItems.AQUAMARINE, "aquamarine");
        unpackBlock(writer, MythicBlocks.KYANITE_BLOCK,    MythicItems.KYANITE,    "kyanite");
        unpackBlock(writer, MythicBlocks.CITRINE_BLOCK,    MythicItems.CITRINE,    "citrine");
        unpackBlock(writer, MythicBlocks.TOPAZ_BLOCK,      MythicItems.TOPAZ,      "topaz");
        unpackBlock(writer, MythicBlocks.PERIDOT_BLOCK,    MythicItems.PERIDOT,    "peridot");
        unpackBlock(writer, MythicBlocks.RUBY_BLOCK,       MythicItems.RUBY,       "ruby");
        unpackBlock(writer, MythicBlocks.SAPPHIRE_BLOCK,   MythicItems.SAPPHIRE,   "sapphire");
        unpackBlock(writer, MythicBlocks.JADE_BLOCK,       MythicItems.JADE,       "jade");
        unpackBlock(writer, MythicBlocks.AMETRINE_BLOCK,   MythicItems.AMETRINE,   "ametrine");

        // --- Necoium storage blocks unpack ---
        unpackBlock(writer, MythicBlocks.RAW_NECOIUM_BLOCK, MythicItems.RAW_NECOIUM,   "raw_necoium");
        unpackBlock(writer, MythicBlocks.NECOIUM_BLOCK,     MythicItems.NECOIUM_INGOT, "necoium_ingot");

        // --- Tools and armor (9 gems, necoium excluded) ---
        toolAndArmor(writer, MythicItems.AQUAMARINE, "aquamarine",
            MythicItems.AQUAMARINE_SWORD, MythicItems.AQUAMARINE_PICKAXE,
            MythicItems.AQUAMARINE_AXE,   MythicItems.AQUAMARINE_SHOVEL, MythicItems.AQUAMARINE_HOE,
            MythicItems.AQUAMARINE_HELMET, MythicItems.AQUAMARINE_CHESTPLATE,
            MythicItems.AQUAMARINE_LEGGINGS, MythicItems.AQUAMARINE_BOOTS);

        toolAndArmor(writer, MythicItems.KYANITE, "kyanite",
            MythicItems.KYANITE_SWORD, MythicItems.KYANITE_PICKAXE,
            MythicItems.KYANITE_AXE,   MythicItems.KYANITE_SHOVEL, MythicItems.KYANITE_HOE,
            MythicItems.KYANITE_HELMET, MythicItems.KYANITE_CHESTPLATE,
            MythicItems.KYANITE_LEGGINGS, MythicItems.KYANITE_BOOTS);

        toolAndArmor(writer, MythicItems.CITRINE, "citrine",
            MythicItems.CITRINE_SWORD, MythicItems.CITRINE_PICKAXE,
            MythicItems.CITRINE_AXE,   MythicItems.CITRINE_SHOVEL, MythicItems.CITRINE_HOE,
            MythicItems.CITRINE_HELMET, MythicItems.CITRINE_CHESTPLATE,
            MythicItems.CITRINE_LEGGINGS, MythicItems.CITRINE_BOOTS);

        toolAndArmor(writer, MythicItems.TOPAZ, "topaz",
            MythicItems.TOPAZ_SWORD, MythicItems.TOPAZ_PICKAXE,
            MythicItems.TOPAZ_AXE,   MythicItems.TOPAZ_SHOVEL, MythicItems.TOPAZ_HOE,
            MythicItems.TOPAZ_HELMET, MythicItems.TOPAZ_CHESTPLATE,
            MythicItems.TOPAZ_LEGGINGS, MythicItems.TOPAZ_BOOTS);

        toolAndArmor(writer, MythicItems.PERIDOT, "peridot",
            MythicItems.PERIDOT_SWORD, MythicItems.PERIDOT_PICKAXE,
            MythicItems.PERIDOT_AXE,   MythicItems.PERIDOT_SHOVEL, MythicItems.PERIDOT_HOE,
            MythicItems.PERIDOT_HELMET, MythicItems.PERIDOT_CHESTPLATE,
            MythicItems.PERIDOT_LEGGINGS, MythicItems.PERIDOT_BOOTS);

        toolAndArmor(writer, MythicItems.RUBY, "ruby",
            MythicItems.RUBY_SWORD, MythicItems.RUBY_PICKAXE,
            MythicItems.RUBY_AXE,   MythicItems.RUBY_SHOVEL, MythicItems.RUBY_HOE,
            MythicItems.RUBY_HELMET, MythicItems.RUBY_CHESTPLATE,
            MythicItems.RUBY_LEGGINGS, MythicItems.RUBY_BOOTS);

        toolAndArmor(writer, MythicItems.SAPPHIRE, "sapphire",
            MythicItems.SAPPHIRE_SWORD, MythicItems.SAPPHIRE_PICKAXE,
            MythicItems.SAPPHIRE_AXE,   MythicItems.SAPPHIRE_SHOVEL, MythicItems.SAPPHIRE_HOE,
            MythicItems.SAPPHIRE_HELMET, MythicItems.SAPPHIRE_CHESTPLATE,
            MythicItems.SAPPHIRE_LEGGINGS, MythicItems.SAPPHIRE_BOOTS);

        toolAndArmor(writer, MythicItems.JADE, "jade",
            MythicItems.JADE_SWORD, MythicItems.JADE_PICKAXE,
            MythicItems.JADE_AXE,   MythicItems.JADE_SHOVEL, MythicItems.JADE_HOE,
            MythicItems.JADE_HELMET, MythicItems.JADE_CHESTPLATE,
            MythicItems.JADE_LEGGINGS, MythicItems.JADE_BOOTS);

        toolAndArmor(writer, MythicItems.AMETRINE, "ametrine",
            MythicItems.AMETRINE_SWORD, MythicItems.AMETRINE_PICKAXE,
            MythicItems.AMETRINE_AXE,   MythicItems.AMETRINE_SHOVEL, MythicItems.AMETRINE_HOE,
            MythicItems.AMETRINE_HELMET, MythicItems.AMETRINE_CHESTPLATE,
            MythicItems.AMETRINE_LEGGINGS, MythicItems.AMETRINE_BOOTS);

        // --- Crystal block crafting (4 shards ↔ 1 crystal block) ---
        crystalBlock(writer, MythicItems.AQUAMARINE_CRYSTAL_SHARD, MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK, "aquamarine");
        crystalBlock(writer, MythicItems.KYANITE_CRYSTAL_SHARD,    MythicBlocks.KYANITE_CRYSTAL_BLOCK,    "kyanite");
        crystalBlock(writer, MythicItems.CITRINE_CRYSTAL_SHARD,    MythicBlocks.CITRINE_CRYSTAL_BLOCK,    "citrine");
        crystalBlock(writer, MythicItems.TOPAZ_CRYSTAL_SHARD,      MythicBlocks.TOPAZ_CRYSTAL_BLOCK,      "topaz");
        crystalBlock(writer, MythicItems.PERIDOT_CRYSTAL_SHARD,    MythicBlocks.PERIDOT_CRYSTAL_BLOCK,    "peridot");
        crystalBlock(writer, MythicItems.RUBY_CRYSTAL_SHARD,       MythicBlocks.RUBY_CRYSTAL_BLOCK,       "ruby");
        crystalBlock(writer, MythicItems.SAPPHIRE_CRYSTAL_SHARD,   MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK,   "sapphire");
        crystalBlock(writer, MythicItems.JADE_CRYSTAL_SHARD,       MythicBlocks.JADE_CRYSTAL_BLOCK,       "jade");
        crystalBlock(writer, MythicItems.AMETRINE_CRYSTAL_SHARD,   MythicBlocks.AMETRINE_CRYSTAL_BLOCK,   "ametrine");

        // --- Armor trim smithing recipes ---
        armorTrim(writer, MythicItems.AQUAMARINE_HELMET,      "aquamarine_helmet");
        armorTrim(writer, MythicItems.AQUAMARINE_CHESTPLATE,  "aquamarine_chestplate");
        armorTrim(writer, MythicItems.AQUAMARINE_LEGGINGS,    "aquamarine_leggings");
        armorTrim(writer, MythicItems.AQUAMARINE_BOOTS,       "aquamarine_boots");
        armorTrim(writer, MythicItems.KYANITE_HELMET,         "kyanite_helmet");
        armorTrim(writer, MythicItems.KYANITE_CHESTPLATE,     "kyanite_chestplate");
        armorTrim(writer, MythicItems.KYANITE_LEGGINGS,       "kyanite_leggings");
        armorTrim(writer, MythicItems.KYANITE_BOOTS,          "kyanite_boots");
        armorTrim(writer, MythicItems.CITRINE_HELMET,         "citrine_helmet");
        armorTrim(writer, MythicItems.CITRINE_CHESTPLATE,     "citrine_chestplate");
        armorTrim(writer, MythicItems.CITRINE_LEGGINGS,       "citrine_leggings");
        armorTrim(writer, MythicItems.CITRINE_BOOTS,          "citrine_boots");
        armorTrim(writer, MythicItems.TOPAZ_HELMET,           "topaz_helmet");
        armorTrim(writer, MythicItems.TOPAZ_CHESTPLATE,       "topaz_chestplate");
        armorTrim(writer, MythicItems.TOPAZ_LEGGINGS,         "topaz_leggings");
        armorTrim(writer, MythicItems.TOPAZ_BOOTS,            "topaz_boots");
        armorTrim(writer, MythicItems.PERIDOT_HELMET,         "peridot_helmet");
        armorTrim(writer, MythicItems.PERIDOT_CHESTPLATE,     "peridot_chestplate");
        armorTrim(writer, MythicItems.PERIDOT_LEGGINGS,       "peridot_leggings");
        armorTrim(writer, MythicItems.PERIDOT_BOOTS,          "peridot_boots");
        armorTrim(writer, MythicItems.RUBY_HELMET,            "ruby_helmet");
        armorTrim(writer, MythicItems.RUBY_CHESTPLATE,        "ruby_chestplate");
        armorTrim(writer, MythicItems.RUBY_LEGGINGS,          "ruby_leggings");
        armorTrim(writer, MythicItems.RUBY_BOOTS,             "ruby_boots");
        armorTrim(writer, MythicItems.SAPPHIRE_HELMET,        "sapphire_helmet");
        armorTrim(writer, MythicItems.SAPPHIRE_CHESTPLATE,    "sapphire_chestplate");
        armorTrim(writer, MythicItems.SAPPHIRE_LEGGINGS,      "sapphire_leggings");
        armorTrim(writer, MythicItems.SAPPHIRE_BOOTS,         "sapphire_boots");
        armorTrim(writer, MythicItems.JADE_HELMET,            "jade_helmet");
        armorTrim(writer, MythicItems.JADE_CHESTPLATE,        "jade_chestplate");
        armorTrim(writer, MythicItems.JADE_LEGGINGS,          "jade_leggings");
        armorTrim(writer, MythicItems.JADE_BOOTS,             "jade_boots");
        armorTrim(writer, MythicItems.AMETRINE_HELMET,        "ametrine_helmet");
        armorTrim(writer, MythicItems.AMETRINE_CHESTPLATE,    "ametrine_chestplate");
        armorTrim(writer, MythicItems.AMETRINE_LEGGINGS,      "ametrine_leggings");
        armorTrim(writer, MythicItems.AMETRINE_BOOTS,         "ametrine_boots");
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private void gemCooking(Consumer<FinishedRecipe> writer, Item result, List<ItemLike> inputs, String idPrefix) {
        for (ItemLike input : inputs) {
            String inputName = BuiltInRegistries.ITEM.getKey(input.asItem()).getPath();
            SimpleCookingRecipeBuilder
                    .smelting(Ingredient.of(input), RecipeCategory.MISC, result, 0.7f, 200)
                    .unlockedBy("has_" + inputName, has(input))
                    .save(writer, new ResourceLocation(Constants.MOD_ID, idPrefix + "_from_smelting_" + inputName));
            SimpleCookingRecipeBuilder
                    .blasting(Ingredient.of(input), RecipeCategory.MISC, result, 0.7f, 100)
                    .unlockedBy("has_" + inputName, has(input))
                    .save(writer, new ResourceLocation(Constants.MOD_ID, idPrefix + "_from_blasting_" + inputName));
        }
    }

    private void storageBlock(Consumer<FinishedRecipe> writer, Item material, Block block, String name) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .define('#', material)
                .pattern("###").pattern("###").pattern("###")
                .unlockedBy("has_" + name, has(material))
                .save(writer, new ResourceLocation(Constants.MOD_ID, name + "_block_from_" + name));
    }

    private void unpackBlock(Consumer<FinishedRecipe> writer, Block block, Item material, String name) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, material, 9)
                .requires(block)
                .unlockedBy("has_" + name + "_block", has(block))
                .save(writer, new ResourceLocation(Constants.MOD_ID, name + "_from_" + name + "_block"));
    }

    private void crystalBlock(Consumer<FinishedRecipe> writer, Item shard, Block block, String gem) {
        // 4 shards (2×2) → 1 crystal block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
            .define('S', shard)
            .pattern("SS").pattern("SS")
            .unlockedBy("has_" + gem + "_crystal_shard", has(shard))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_block_from_shards"));
        // 1 crystal block → 4 shards
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, shard, 4)
            .requires(block)
            .unlockedBy("has_" + gem + "_crystal_block", has(block))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_shard_from_block"));
    }

    private void armorTrim(Consumer<FinishedRecipe> writer, Item armor, String name) {
        SmithingTrimRecipeBuilder.smithingTrim(
            Ingredient.of(ItemTags.TRIM_TEMPLATES),
            Ingredient.of(armor),
            Ingredient.of(ItemTags.TRIM_MATERIALS),
            RecipeCategory.MISC
        ).unlocks("has_smithing_trim_template", has(ItemTags.TRIM_TEMPLATES))
         .save(writer, new ResourceLocation(Constants.MOD_ID, name + "_smithing_trim"));
    }

    private void toolAndArmor(Consumer<FinishedRecipe> writer, Item gem, String n,
                               Item sword, Item pickaxe, Item axe, Item shovel, Item hoe,
                               Item helmet, Item chestplate, Item leggings, Item boots) {
        // Sword: G / G / S
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
                .define('G', gem).define('S', Items.STICK)
                .pattern("G").pattern("G").pattern("S")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_sword"));

        // Pickaxe: GGG / _S_ / _S_
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                .define('G', gem).define('S', Items.STICK)
                .pattern("GGG").pattern(" S ").pattern(" S ")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_pickaxe"));

        // Axe: GG / GS / _S
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                .define('G', gem).define('S', Items.STICK)
                .pattern("GG").pattern("GS").pattern(" S")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_axe"));

        // Shovel: G / S / S
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                .define('G', gem).define('S', Items.STICK)
                .pattern("G").pattern("S").pattern("S")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_shovel"));

        // Hoe: GG / _S / _S
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
                .define('G', gem).define('S', Items.STICK)
                .pattern("GG").pattern(" S").pattern(" S")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_hoe"));

        // Helmet: GGG / G_G
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
                .define('G', gem)
                .pattern("GGG").pattern("G G")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_helmet"));

        // Chestplate: G_G / GGG / GGG
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
                .define('G', gem)
                .pattern("G G").pattern("GGG").pattern("GGG")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_chestplate"));

        // Leggings: GGG / G_G / G_G
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
                .define('G', gem)
                .pattern("GGG").pattern("G G").pattern("G G")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_leggings"));

        // Boots: G_G / G_G
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
                .define('G', gem)
                .pattern("G G").pattern("G G")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_boots"));
    }
}
