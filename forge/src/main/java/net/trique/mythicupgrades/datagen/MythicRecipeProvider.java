package net.trique.mythicupgrades.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
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
        gemCooking(writer, MythicItems.AQUAMARINE, List.of(MythicBlocks.AQUAMARINE_ORE, MythicBlocks.DEEPSLATE_AQUAMARINE_ORE), "aquamarine");
        gemCooking(writer, MythicItems.CITRINE, List.of(MythicBlocks.CITRINE_ORE, MythicBlocks.DEEPSLATE_CITRINE_ORE), "citrine");
        gemCooking(writer, MythicItems.TOPAZ, List.of(MythicBlocks.TOPAZ_ORE, MythicBlocks.DEEPSLATE_TOPAZ_ORE), "topaz");
        gemCooking(writer, MythicItems.PERIDOT, List.of(MythicBlocks.PERIDOT_ORE, MythicBlocks.DEEPSLATE_PERIDOT_ORE), "peridot");
        gemCooking(writer, MythicItems.RUBY, List.of(MythicBlocks.RUBY_ORE), "ruby");
        gemCooking(writer, MythicItems.SAPPHIRE, List.of(MythicBlocks.SAPPHIRE_ORE), "sapphire");
        gemCooking(writer, MythicItems.JADE, List.of(MythicBlocks.JADE_ORE), "jade");
        gemCooking(writer, MythicItems.AMETRINE, List.of(MythicBlocks.AMETRINE_ORE), "ametrine");

        gemCooking(writer, MythicItems.NECOIUM_INGOT, List.of(MythicItems.RAW_NECOIUM), "necoium_ingot_from_raw");
        gemCooking(writer, MythicItems.NECOIUM_INGOT, List.of(MythicBlocks.NECOIUM_ORE), "necoium_ingot_from_ore");

        storageBlock(writer, MythicItems.AQUAMARINE, MythicBlocks.AQUAMARINE_BLOCK, "aquamarine");
        storageBlock(writer, MythicItems.CITRINE, MythicBlocks.CITRINE_BLOCK, "citrine");
        storageBlock(writer, MythicItems.TOPAZ, MythicBlocks.TOPAZ_BLOCK, "topaz");
        storageBlock(writer, MythicItems.PERIDOT, MythicBlocks.PERIDOT_BLOCK, "peridot");
        storageBlock(writer, MythicItems.RUBY, MythicBlocks.RUBY_BLOCK, "ruby");
        storageBlock(writer, MythicItems.SAPPHIRE, MythicBlocks.SAPPHIRE_BLOCK, "sapphire");
        storageBlock(writer, MythicItems.JADE, MythicBlocks.JADE_BLOCK, "jade");
        storageBlock(writer, MythicItems.AMETRINE, MythicBlocks.AMETRINE_BLOCK, "ametrine");

        storageBlock(writer, MythicItems.RAW_NECOIUM, MythicBlocks.RAW_NECOIUM_BLOCK, "raw_necoium");
        storageBlock(writer, MythicItems.NECOIUM_INGOT, MythicBlocks.NECOIUM_BLOCK, "necoium_ingot");

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, MythicItems.NECOIUM_CARROT)
            .define('N', MythicBlocks.NECOIUM_BLOCK)
            .define('C', Items.CARROT)
            .pattern("NNN")
            .pattern("NCN")
            .pattern("NNN")
            .unlockedBy("has_necoium_block", has(MythicBlocks.NECOIUM_BLOCK))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "necoium_carrot_from_carrot"));

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, MythicItems.NECOIUM_CARROT)
            .define('N', MythicBlocks.NECOIUM_BLOCK)
            .define('C', Items.GOLDEN_CARROT)
            .pattern("NNN")
            .pattern("NCN")
            .pattern("NNN")
            .unlockedBy("has_necoium_block", has(MythicBlocks.NECOIUM_BLOCK))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "necoium_carrot_from_golden_carrot"));

        unpackBlock(writer, MythicBlocks.AQUAMARINE_BLOCK, MythicItems.AQUAMARINE, "aquamarine");
        unpackBlock(writer, MythicBlocks.CITRINE_BLOCK, MythicItems.CITRINE, "citrine");
        unpackBlock(writer, MythicBlocks.TOPAZ_BLOCK, MythicItems.TOPAZ, "topaz");
        unpackBlock(writer, MythicBlocks.PERIDOT_BLOCK, MythicItems.PERIDOT, "peridot");
        unpackBlock(writer, MythicBlocks.RUBY_BLOCK, MythicItems.RUBY, "ruby");
        unpackBlock(writer, MythicBlocks.SAPPHIRE_BLOCK, MythicItems.SAPPHIRE, "sapphire");
        unpackBlock(writer, MythicBlocks.JADE_BLOCK, MythicItems.JADE, "jade");
        unpackBlock(writer, MythicBlocks.AMETRINE_BLOCK, MythicItems.AMETRINE, "ametrine");

        unpackBlock(writer, MythicBlocks.RAW_NECOIUM_BLOCK, MythicItems.RAW_NECOIUM, "raw_necoium");
        unpackBlock(writer, MythicBlocks.NECOIUM_BLOCK, MythicItems.NECOIUM_INGOT, "necoium_ingot");

        toolAndArmor(writer, MythicItems.AQUAMARINE, "aquamarine",
            MythicItems.AQUAMARINE_SWORD, MythicItems.AQUAMARINE_PICKAXE,
            MythicItems.AQUAMARINE_AXE, MythicItems.AQUAMARINE_SHOVEL, MythicItems.AQUAMARINE_HOE,
            MythicItems.AQUAMARINE_HELMET, MythicItems.AQUAMARINE_CHESTPLATE,
            MythicItems.AQUAMARINE_LEGGINGS, MythicItems.AQUAMARINE_BOOTS);

        toolAndArmor(writer, MythicItems.CITRINE, "citrine",
            MythicItems.CITRINE_SWORD, MythicItems.CITRINE_PICKAXE,
            MythicItems.CITRINE_AXE, MythicItems.CITRINE_SHOVEL, MythicItems.CITRINE_HOE,
            MythicItems.CITRINE_HELMET, MythicItems.CITRINE_CHESTPLATE,
            MythicItems.CITRINE_LEGGINGS, MythicItems.CITRINE_BOOTS);

        toolAndArmor(writer, MythicItems.TOPAZ, "topaz",
            MythicItems.TOPAZ_SWORD, MythicItems.TOPAZ_PICKAXE,
            MythicItems.TOPAZ_AXE, MythicItems.TOPAZ_SHOVEL, MythicItems.TOPAZ_HOE,
            MythicItems.TOPAZ_HELMET, MythicItems.TOPAZ_CHESTPLATE,
            MythicItems.TOPAZ_LEGGINGS, MythicItems.TOPAZ_BOOTS);

        toolAndArmor(writer, MythicItems.PERIDOT, "peridot",
            MythicItems.PERIDOT_SWORD, MythicItems.PERIDOT_PICKAXE,
            MythicItems.PERIDOT_AXE, MythicItems.PERIDOT_SHOVEL, MythicItems.PERIDOT_HOE,
            MythicItems.PERIDOT_HELMET, MythicItems.PERIDOT_CHESTPLATE,
            MythicItems.PERIDOT_LEGGINGS, MythicItems.PERIDOT_BOOTS);

        toolAndArmor(writer, MythicItems.RUBY, "ruby",
            MythicItems.RUBY_SWORD, MythicItems.RUBY_PICKAXE,
            MythicItems.RUBY_AXE, MythicItems.RUBY_SHOVEL, MythicItems.RUBY_HOE,
            MythicItems.RUBY_HELMET, MythicItems.RUBY_CHESTPLATE,
            MythicItems.RUBY_LEGGINGS, MythicItems.RUBY_BOOTS);

        toolAndArmor(writer, MythicItems.SAPPHIRE, "sapphire",
            MythicItems.SAPPHIRE_SWORD, MythicItems.SAPPHIRE_PICKAXE,
            MythicItems.SAPPHIRE_AXE, MythicItems.SAPPHIRE_SHOVEL, MythicItems.SAPPHIRE_HOE,
            MythicItems.SAPPHIRE_HELMET, MythicItems.SAPPHIRE_CHESTPLATE,
            MythicItems.SAPPHIRE_LEGGINGS, MythicItems.SAPPHIRE_BOOTS);

        toolAndArmor(writer, MythicItems.JADE, "jade",
            MythicItems.JADE_SWORD, MythicItems.JADE_PICKAXE,
            MythicItems.JADE_AXE, MythicItems.JADE_SHOVEL, MythicItems.JADE_HOE,
            MythicItems.JADE_HELMET, MythicItems.JADE_CHESTPLATE,
            MythicItems.JADE_LEGGINGS, MythicItems.JADE_BOOTS);

        toolAndArmor(writer, MythicItems.AMETRINE, "ametrine",
            MythicItems.AMETRINE_SWORD, MythicItems.AMETRINE_PICKAXE,
            MythicItems.AMETRINE_AXE, MythicItems.AMETRINE_SHOVEL, MythicItems.AMETRINE_HOE,
            MythicItems.AMETRINE_HELMET, MythicItems.AMETRINE_CHESTPLATE,
            MythicItems.AMETRINE_LEGGINGS, MythicItems.AMETRINE_BOOTS);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE, 2)
            .define('I', MythicItems.NECOIUM_INGOT)
            .define('T', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
            .define('B', MythicBlocks.NECOIUM_BLOCK)
            .pattern("ITI")
            .pattern("IBI")
            .pattern("III")
            .unlockedBy("has_netherite_upgrade_smithing_template", has(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "mythic_upgrade_smithing_template"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE, 2)
            .define('I', MythicItems.NECOIUM_INGOT)
            .define('T', MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE)
            .define('B', MythicBlocks.NECOIUM_BLOCK)
            .pattern("ITI")
            .pattern("IBI")
            .pattern("III")
            .unlockedBy("has_mythic_upgrade_smithing_template", has(MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "mythic_upgrade_smithing_template_duplicate"));

        crystalBlock(writer, MythicItems.AQUAMARINE_CRYSTAL_SHARD, MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK, "aquamarine");
        crystalBlock(writer, MythicItems.CITRINE_CRYSTAL_SHARD, MythicBlocks.CITRINE_CRYSTAL_BLOCK, "citrine");
        crystalBlock(writer, MythicItems.TOPAZ_CRYSTAL_SHARD, MythicBlocks.TOPAZ_CRYSTAL_BLOCK, "topaz");
        crystalBlock(writer, MythicItems.PERIDOT_CRYSTAL_SHARD, MythicBlocks.PERIDOT_CRYSTAL_BLOCK, "peridot");
        crystalBlock(writer, MythicItems.RUBY_CRYSTAL_SHARD, MythicBlocks.RUBY_CRYSTAL_BLOCK, "ruby");
        crystalBlock(writer, MythicItems.SAPPHIRE_CRYSTAL_SHARD, MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK, "sapphire");
        crystalBlock(writer, MythicItems.JADE_CRYSTAL_SHARD, MythicBlocks.JADE_CRYSTAL_BLOCK, "jade");
        crystalBlock(writer, MythicItems.AMETRINE_CRYSTAL_SHARD, MythicBlocks.AMETRINE_CRYSTAL_BLOCK, "ametrine");

        armorTrim(writer, MythicItems.AQUAMARINE_HELMET, "aquamarine_helmet");
        armorTrim(writer, MythicItems.AQUAMARINE_CHESTPLATE, "aquamarine_chestplate");
        armorTrim(writer, MythicItems.AQUAMARINE_LEGGINGS, "aquamarine_leggings");
        armorTrim(writer, MythicItems.AQUAMARINE_BOOTS, "aquamarine_boots");
        armorTrim(writer, MythicItems.CITRINE_HELMET, "citrine_helmet");
        armorTrim(writer, MythicItems.CITRINE_CHESTPLATE, "citrine_chestplate");
        armorTrim(writer, MythicItems.CITRINE_LEGGINGS, "citrine_leggings");
        armorTrim(writer, MythicItems.CITRINE_BOOTS, "citrine_boots");
        armorTrim(writer, MythicItems.TOPAZ_HELMET, "topaz_helmet");
        armorTrim(writer, MythicItems.TOPAZ_CHESTPLATE, "topaz_chestplate");
        armorTrim(writer, MythicItems.TOPAZ_LEGGINGS, "topaz_leggings");
        armorTrim(writer, MythicItems.TOPAZ_BOOTS, "topaz_boots");
        armorTrim(writer, MythicItems.PERIDOT_HELMET, "peridot_helmet");
        armorTrim(writer, MythicItems.PERIDOT_CHESTPLATE, "peridot_chestplate");
        armorTrim(writer, MythicItems.PERIDOT_LEGGINGS, "peridot_leggings");
        armorTrim(writer, MythicItems.PERIDOT_BOOTS, "peridot_boots");
        armorTrim(writer, MythicItems.RUBY_HELMET, "ruby_helmet");
        armorTrim(writer, MythicItems.RUBY_CHESTPLATE, "ruby_chestplate");
        armorTrim(writer, MythicItems.RUBY_LEGGINGS, "ruby_leggings");
        armorTrim(writer, MythicItems.RUBY_BOOTS, "ruby_boots");
        armorTrim(writer, MythicItems.SAPPHIRE_HELMET, "sapphire_helmet");
        armorTrim(writer, MythicItems.SAPPHIRE_CHESTPLATE, "sapphire_chestplate");
        armorTrim(writer, MythicItems.SAPPHIRE_LEGGINGS, "sapphire_leggings");
        armorTrim(writer, MythicItems.SAPPHIRE_BOOTS, "sapphire_boots");
        armorTrim(writer, MythicItems.JADE_HELMET, "jade_helmet");
        armorTrim(writer, MythicItems.JADE_CHESTPLATE, "jade_chestplate");
        armorTrim(writer, MythicItems.JADE_LEGGINGS, "jade_leggings");
        armorTrim(writer, MythicItems.JADE_BOOTS, "jade_boots");
        armorTrim(writer, MythicItems.AMETRINE_HELMET, "ametrine_helmet");
        armorTrim(writer, MythicItems.AMETRINE_CHESTPLATE, "ametrine_chestplate");
        armorTrim(writer, MythicItems.AMETRINE_LEGGINGS, "ametrine_leggings");
        armorTrim(writer, MythicItems.AMETRINE_BOOTS, "ametrine_boots");

        stoneBlocks(writer, MythicItems.AQUAMARINE_CRYSTAL_SHARD,
            MythicBlocks.AQUAMARINE_STONE, MythicBlocks.AQUAMARINE_STONE_SLAB, MythicBlocks.AQUAMARINE_STONE_STAIRS,
            MythicBlocks.POLISHED_AQUAMARINE_STONE, MythicBlocks.POLISHED_AQUAMARINE_STONE_SLAB, MythicBlocks.POLISHED_AQUAMARINE_STONE_STAIRS,
            "aquamarine");
        stoneBlocks(writer, MythicItems.CITRINE_CRYSTAL_SHARD,
            MythicBlocks.CITRINE_STONE, MythicBlocks.CITRINE_STONE_SLAB, MythicBlocks.CITRINE_STONE_STAIRS,
            MythicBlocks.POLISHED_CITRINE_STONE, MythicBlocks.POLISHED_CITRINE_STONE_SLAB, MythicBlocks.POLISHED_CITRINE_STONE_STAIRS,
            "citrine");
        stoneBlocks(writer, MythicItems.TOPAZ_CRYSTAL_SHARD,
            MythicBlocks.TOPAZ_STONE, MythicBlocks.TOPAZ_STONE_SLAB, MythicBlocks.TOPAZ_STONE_STAIRS,
            MythicBlocks.POLISHED_TOPAZ_STONE, MythicBlocks.POLISHED_TOPAZ_STONE_SLAB, MythicBlocks.POLISHED_TOPAZ_STONE_STAIRS,
            "topaz");
        stoneBlocks(writer, MythicItems.PERIDOT_CRYSTAL_SHARD,
            MythicBlocks.PERIDOT_STONE, MythicBlocks.PERIDOT_STONE_SLAB, MythicBlocks.PERIDOT_STONE_STAIRS,
            MythicBlocks.POLISHED_PERIDOT_STONE, MythicBlocks.POLISHED_PERIDOT_STONE_SLAB, MythicBlocks.POLISHED_PERIDOT_STONE_STAIRS,
            "peridot");
        stoneBlocks(writer, MythicItems.RUBY_CRYSTAL_SHARD,
            MythicBlocks.RUBY_STONE, MythicBlocks.RUBY_STONE_SLAB, MythicBlocks.RUBY_STONE_STAIRS,
            MythicBlocks.POLISHED_RUBY_STONE, MythicBlocks.POLISHED_RUBY_STONE_SLAB, MythicBlocks.POLISHED_RUBY_STONE_STAIRS,
            "ruby");
        stoneBlocks(writer, MythicItems.SAPPHIRE_CRYSTAL_SHARD,
            MythicBlocks.SAPPHIRE_STONE, MythicBlocks.SAPPHIRE_STONE_SLAB, MythicBlocks.SAPPHIRE_STONE_STAIRS,
            MythicBlocks.POLISHED_SAPPHIRE_STONE, MythicBlocks.POLISHED_SAPPHIRE_STONE_SLAB, MythicBlocks.POLISHED_SAPPHIRE_STONE_STAIRS,
            "sapphire");
        stoneBlocks(writer, MythicItems.JADE_CRYSTAL_SHARD,
            MythicBlocks.JADE_STONE, MythicBlocks.JADE_STONE_SLAB, MythicBlocks.JADE_STONE_STAIRS,
            MythicBlocks.POLISHED_JADE_STONE, MythicBlocks.POLISHED_JADE_STONE_SLAB, MythicBlocks.POLISHED_JADE_STONE_STAIRS,
            "jade");
        stoneBlocks(writer, MythicItems.AMETRINE_CRYSTAL_SHARD,
            MythicBlocks.AMETRINE_STONE, MythicBlocks.AMETRINE_STONE_SLAB, MythicBlocks.AMETRINE_STONE_STAIRS,
            MythicBlocks.POLISHED_AMETRINE_STONE, MythicBlocks.POLISHED_AMETRINE_STONE_SLAB, MythicBlocks.POLISHED_AMETRINE_STONE_STAIRS,
            "ametrine");

        crystalSubBlocks(writer, MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK,
            MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK,
            MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS,
            MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_SLAB, MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.AQUAMARINE_CRYSTAL_PILLAR, MythicBlocks.CUT_AQUAMARINE_CRYSTAL_PILLAR, "aquamarine");

        crystalSubBlocks(writer, MythicBlocks.CITRINE_CRYSTAL_BLOCK,
            MythicBlocks.CITRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.CITRINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK,
            MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.CITRINE_CRYSTAL_BRICKS,
            MythicBlocks.CITRINE_CRYSTAL_BRICKS_SLAB, MythicBlocks.CITRINE_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.CITRINE_CRYSTAL_PILLAR, MythicBlocks.CUT_CITRINE_CRYSTAL_PILLAR, "citrine");

        crystalSubBlocks(writer, MythicBlocks.TOPAZ_CRYSTAL_BLOCK,
            MythicBlocks.TOPAZ_CRYSTAL_BLOCK_SLAB, MythicBlocks.TOPAZ_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK,
            MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.TOPAZ_CRYSTAL_BRICKS,
            MythicBlocks.TOPAZ_CRYSTAL_BRICKS_SLAB, MythicBlocks.TOPAZ_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.TOPAZ_CRYSTAL_PILLAR, MythicBlocks.CUT_TOPAZ_CRYSTAL_PILLAR, "topaz");

        crystalSubBlocks(writer, MythicBlocks.PERIDOT_CRYSTAL_BLOCK,
            MythicBlocks.PERIDOT_CRYSTAL_BLOCK_SLAB, MythicBlocks.PERIDOT_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK,
            MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.PERIDOT_CRYSTAL_BRICKS,
            MythicBlocks.PERIDOT_CRYSTAL_BRICKS_SLAB, MythicBlocks.PERIDOT_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.PERIDOT_CRYSTAL_PILLAR, MythicBlocks.CUT_PERIDOT_CRYSTAL_PILLAR, "peridot");

        crystalSubBlocks(writer, MythicBlocks.RUBY_CRYSTAL_BLOCK,
            MythicBlocks.RUBY_CRYSTAL_BLOCK_SLAB, MythicBlocks.RUBY_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK,
            MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.RUBY_CRYSTAL_BRICKS,
            MythicBlocks.RUBY_CRYSTAL_BRICKS_SLAB, MythicBlocks.RUBY_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.RUBY_CRYSTAL_PILLAR, MythicBlocks.CUT_RUBY_CRYSTAL_PILLAR, "ruby");

        crystalSubBlocks(writer, MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK,
            MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_SLAB, MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK,
            MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS,
            MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_SLAB, MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.SAPPHIRE_CRYSTAL_PILLAR, MythicBlocks.CUT_SAPPHIRE_CRYSTAL_PILLAR, "sapphire");

        crystalSubBlocks(writer, MythicBlocks.JADE_CRYSTAL_BLOCK,
            MythicBlocks.JADE_CRYSTAL_BLOCK_SLAB, MythicBlocks.JADE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK,
            MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.JADE_CRYSTAL_BRICKS,
            MythicBlocks.JADE_CRYSTAL_BRICKS_SLAB, MythicBlocks.JADE_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.JADE_CRYSTAL_PILLAR, MythicBlocks.CUT_JADE_CRYSTAL_PILLAR, "jade");

        crystalSubBlocks(writer, MythicBlocks.AMETRINE_CRYSTAL_BLOCK,
            MythicBlocks.AMETRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.AMETRINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK,
            MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_SLAB, MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_STAIRS,
            MythicBlocks.AMETRINE_CRYSTAL_BRICKS,
            MythicBlocks.AMETRINE_CRYSTAL_BRICKS_SLAB, MythicBlocks.AMETRINE_CRYSTAL_BRICKS_STAIRS,
            MythicBlocks.AMETRINE_CRYSTAL_PILLAR, MythicBlocks.CUT_AMETRINE_CRYSTAL_PILLAR, "ametrine");
    }

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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
            .define('S', shard)
            .pattern("SS").pattern("SS")
            .unlockedBy("has_" + gem + "_crystal_shard", has(shard))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_block_from_shards"));
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

    private void crystalSubBlocks(Consumer<FinishedRecipe> writer,
            Block crystalBlock, Block cbSlab, Block cbStairs,
            Block polished, Block polSlab, Block polStairs,
            Block bricks, Block bricksSlab, Block bricksStairs,
            Block pillar, Block cutPillar, String gem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, pillar, 2)
            .define('B', crystalBlock).pattern("B").pattern("B")
            .unlockedBy("has_" + gem + "_crystal_block", has(crystalBlock))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_pillar_from_crystal_block"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, polished, 4)
            .define('B', crystalBlock).pattern("BB").pattern("BB")
            .unlockedBy("has_" + gem + "_crystal_block", has(crystalBlock))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_crystal_block_from_crystal_block"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, cutPillar, 4)
            .define('P', pillar).pattern("PP").pattern("PP")
            .unlockedBy("has_" + gem + "_crystal_pillar", has(pillar))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "cut_" + gem + "_crystal_pillar_from_pillar"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bricks, 4)
            .define('P', polished).pattern("PP").pattern("PP")
            .unlockedBy("has_polished_" + gem + "_crystal_block", has(polished))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_bricks_from_polished"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, cbSlab, 6)
            .define('B', crystalBlock).pattern("BBB")
            .unlockedBy("has_" + gem + "_crystal_block", has(crystalBlock))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_block_slab_from_crystal_block"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, cbStairs, 4)
            .define('B', crystalBlock).pattern("B  ").pattern("BB ").pattern("BBB")
            .unlockedBy("has_" + gem + "_crystal_block", has(crystalBlock))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_block_stairs_from_crystal_block"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, polSlab, 6)
            .define('B', polished).pattern("BBB")
            .unlockedBy("has_polished_" + gem + "_crystal_block", has(polished))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_crystal_block_slab_from_polished"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, polStairs, 4)
            .define('B', polished).pattern("B  ").pattern("BB ").pattern("BBB")
            .unlockedBy("has_polished_" + gem + "_crystal_block", has(polished))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_crystal_block_stairs_from_polished"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bricksSlab, 6)
            .define('B', bricks).pattern("BBB")
            .unlockedBy("has_" + gem + "_crystal_bricks", has(bricks))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_bricks_slab_from_bricks"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bricksStairs, 4)
            .define('B', bricks).pattern("B  ").pattern("BB ").pattern("BBB")
            .unlockedBy("has_" + gem + "_crystal_bricks", has(bricks))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_bricks_stairs_from_bricks"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(crystalBlock), RecipeCategory.BUILDING_BLOCKS, cbSlab, 2)
            .unlockedBy("has_" + gem + "_crystal_block", has(crystalBlock))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_block_slab_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(crystalBlock), RecipeCategory.BUILDING_BLOCKS, cbStairs)
            .unlockedBy("has_" + gem + "_crystal_block", has(crystalBlock))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_block_stairs_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(crystalBlock), RecipeCategory.BUILDING_BLOCKS, polished)
            .unlockedBy("has_" + gem + "_crystal_block", has(crystalBlock))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_crystal_block_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(crystalBlock), RecipeCategory.BUILDING_BLOCKS, pillar)
            .unlockedBy("has_" + gem + "_crystal_block", has(crystalBlock))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_pillar_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(crystalBlock), RecipeCategory.BUILDING_BLOCKS, bricks)
            .unlockedBy("has_" + gem + "_crystal_block", has(crystalBlock))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_bricks_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(polished), RecipeCategory.BUILDING_BLOCKS, polSlab, 2)
            .unlockedBy("has_polished_" + gem + "_crystal_block", has(polished))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_crystal_block_slab_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(polished), RecipeCategory.BUILDING_BLOCKS, polStairs)
            .unlockedBy("has_polished_" + gem + "_crystal_block", has(polished))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_crystal_block_stairs_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(polished), RecipeCategory.BUILDING_BLOCKS, bricks)
            .unlockedBy("has_polished_" + gem + "_crystal_block", has(polished))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_bricks_from_polished_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(polished), RecipeCategory.BUILDING_BLOCKS, bricksSlab, 2)
            .unlockedBy("has_polished_" + gem + "_crystal_block", has(polished))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_bricks_slab_from_polished_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(polished), RecipeCategory.BUILDING_BLOCKS, bricksStairs)
            .unlockedBy("has_polished_" + gem + "_crystal_block", has(polished))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_bricks_stairs_from_polished_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(bricks), RecipeCategory.BUILDING_BLOCKS, bricksSlab, 2)
            .unlockedBy("has_" + gem + "_crystal_bricks", has(bricks))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_bricks_slab_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(bricks), RecipeCategory.BUILDING_BLOCKS, bricksStairs)
            .unlockedBy("has_" + gem + "_crystal_bricks", has(bricks))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_crystal_bricks_stairs_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(pillar), RecipeCategory.BUILDING_BLOCKS, cutPillar)
            .unlockedBy("has_" + gem + "_crystal_pillar", has(pillar))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "cut_" + gem + "_crystal_pillar_from_stonecutting"));
    }

    private void stoneBlocks(Consumer<FinishedRecipe> writer, Item shard,
            Block stone, Block stoneSlab, Block stoneStairs,
            Block polished, Block polishedSlab, Block polishedStairs,
            String gem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stone, 4)
            .define('C', Items.COBBLESTONE).define('S', shard)
            .pattern("CS").pattern("SC")
            .unlockedBy("has_" + gem + "_crystal_shard", has(shard))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_stone_from_cobblestone"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, polished, 4)
            .define('B', stone).pattern("BB").pattern("BB")
            .unlockedBy("has_" + gem + "_stone", has(stone))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_stone_from_stone"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stoneSlab, 6)
            .define('B', stone).pattern("BBB")
            .unlockedBy("has_" + gem + "_stone", has(stone))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_stone_slab_from_stone"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stoneStairs, 4)
            .define('B', stone).pattern("B  ").pattern("BB ").pattern("BBB")
            .unlockedBy("has_" + gem + "_stone", has(stone))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_stone_stairs_from_stone"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, polishedSlab, 6)
            .define('B', polished).pattern("BBB")
            .unlockedBy("has_polished_" + gem + "_stone", has(polished))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_stone_slab_from_polished"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, polishedStairs, 4)
            .define('B', polished).pattern("B  ").pattern("BB ").pattern("BBB")
            .unlockedBy("has_polished_" + gem + "_stone", has(polished))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_stone_stairs_from_polished"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(stone), RecipeCategory.BUILDING_BLOCKS, stoneSlab, 2)
            .unlockedBy("has_" + gem + "_stone", has(stone))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_stone_slab_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(stone), RecipeCategory.BUILDING_BLOCKS, stoneStairs)
            .unlockedBy("has_" + gem + "_stone", has(stone))
            .save(writer, new ResourceLocation(Constants.MOD_ID, gem + "_stone_stairs_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(stone), RecipeCategory.BUILDING_BLOCKS, polished)
            .unlockedBy("has_" + gem + "_stone", has(stone))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_stone_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(stone), RecipeCategory.BUILDING_BLOCKS, polishedSlab, 2)
            .unlockedBy("has_" + gem + "_stone", has(stone))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_stone_slab_from_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(stone), RecipeCategory.BUILDING_BLOCKS, polishedStairs)
            .unlockedBy("has_" + gem + "_stone", has(stone))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_stone_stairs_from_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(polished), RecipeCategory.BUILDING_BLOCKS, polishedSlab, 2)
            .unlockedBy("has_polished_" + gem + "_stone", has(polished))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_stone_slab_from_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(polished), RecipeCategory.BUILDING_BLOCKS, polishedStairs)
            .unlockedBy("has_polished_" + gem + "_stone", has(polished))
            .save(writer, new ResourceLocation(Constants.MOD_ID, "polished_" + gem + "_stone_stairs_from_stonecutting"));
    }

    private void toolAndArmor(Consumer<FinishedRecipe> writer, Item gem, String n,
                               Item sword, Item pickaxe, Item axe, Item shovel, Item hoe,
                               Item helmet, Item chestplate, Item leggings, Item boots) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
                .define('G', gem).define('S', Items.STICK)
                .pattern("G").pattern("G").pattern("S")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_sword"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                .define('G', gem).define('S', Items.STICK)
                .pattern("GGG").pattern(" S ").pattern(" S ")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_pickaxe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                .define('G', gem).define('S', Items.STICK)
                .pattern("GG").pattern("GS").pattern(" S")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_axe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                .define('G', gem).define('S', Items.STICK)
                .pattern("G").pattern("S").pattern("S")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_shovel"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
                .define('G', gem).define('S', Items.STICK)
                .pattern("GG").pattern(" S").pattern(" S")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_hoe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
                .define('G', gem)
                .pattern("GGG").pattern("G G")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_helmet"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
                .define('G', gem)
                .pattern("G G").pattern("GGG").pattern("GGG")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
                .define('G', gem)
                .pattern("GGG").pattern("G G").pattern("G G")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
                .define('G', gem)
                .pattern("G G").pattern("G G")
                .unlockedBy("has_" + n, has(gem))
                .save(writer, new ResourceLocation(Constants.MOD_ID, n + "_boots"));
    }
}
