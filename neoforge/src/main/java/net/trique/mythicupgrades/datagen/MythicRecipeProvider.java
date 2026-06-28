package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.trique.mythicupgrades.Constants;
import net.trique.mythicupgrades.block.MythicBlocks;
import net.trique.mythicupgrades.item.MythicItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MythicRecipeProvider extends RecipeProvider {

    public MythicRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        // Cave gems: aquamarine, citrine, topaz, peridot
        gemGroup(output, "aquamarine",
            MythicItems.AQUAMARINE, MythicItems.AQUAMARINE_INGOT, MythicItems.AQUAMARINE_CRYSTAL_SHARD,
            MythicBlocks.AQUAMARINE_ORE, MythicBlocks.DEEPSLATE_AQUAMARINE_ORE, MythicBlocks.AQUAMARINE_BLOCK,
            MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK,
            MythicItems.AQUAMARINE_SWORD, MythicItems.AQUAMARINE_PICKAXE, MythicItems.AQUAMARINE_AXE,
            MythicItems.AQUAMARINE_SHOVEL, MythicItems.AQUAMARINE_HOE,
            MythicItems.AQUAMARINE_HELMET, MythicItems.AQUAMARINE_CHESTPLATE, MythicItems.AQUAMARINE_LEGGINGS, MythicItems.AQUAMARINE_BOOTS,
            true);

        gemGroup(output, "citrine",
            MythicItems.CITRINE, MythicItems.CITRINE_INGOT, MythicItems.CITRINE_CRYSTAL_SHARD,
            MythicBlocks.CITRINE_ORE, MythicBlocks.DEEPSLATE_CITRINE_ORE, MythicBlocks.CITRINE_BLOCK,
            MythicBlocks.CITRINE_CRYSTAL_BLOCK,
            MythicItems.CITRINE_SWORD, MythicItems.CITRINE_PICKAXE, MythicItems.CITRINE_AXE,
            MythicItems.CITRINE_SHOVEL, MythicItems.CITRINE_HOE,
            MythicItems.CITRINE_HELMET, MythicItems.CITRINE_CHESTPLATE, MythicItems.CITRINE_LEGGINGS, MythicItems.CITRINE_BOOTS,
            true);

        gemGroup(output, "topaz",
            MythicItems.TOPAZ, MythicItems.TOPAZ_INGOT, MythicItems.TOPAZ_CRYSTAL_SHARD,
            MythicBlocks.TOPAZ_ORE, MythicBlocks.DEEPSLATE_TOPAZ_ORE, MythicBlocks.TOPAZ_BLOCK,
            MythicBlocks.TOPAZ_CRYSTAL_BLOCK,
            MythicItems.TOPAZ_SWORD, MythicItems.TOPAZ_PICKAXE, MythicItems.TOPAZ_AXE,
            MythicItems.TOPAZ_SHOVEL, MythicItems.TOPAZ_HOE,
            MythicItems.TOPAZ_HELMET, MythicItems.TOPAZ_CHESTPLATE, MythicItems.TOPAZ_LEGGINGS, MythicItems.TOPAZ_BOOTS,
            true);

        gemGroup(output, "peridot",
            MythicItems.PERIDOT, MythicItems.PERIDOT_INGOT, MythicItems.PERIDOT_CRYSTAL_SHARD,
            MythicBlocks.PERIDOT_ORE, MythicBlocks.DEEPSLATE_PERIDOT_ORE, MythicBlocks.PERIDOT_BLOCK,
            MythicBlocks.PERIDOT_CRYSTAL_BLOCK,
            MythicItems.PERIDOT_SWORD, MythicItems.PERIDOT_PICKAXE, MythicItems.PERIDOT_AXE,
            MythicItems.PERIDOT_SHOVEL, MythicItems.PERIDOT_HOE,
            MythicItems.PERIDOT_HELMET, MythicItems.PERIDOT_CHESTPLATE, MythicItems.PERIDOT_LEGGINGS, MythicItems.PERIDOT_BOOTS,
            true);

        // Nether gems (no deepslate ore)
        gemGroupNether(output, "ruby",
            MythicItems.RUBY, MythicItems.RUBY_INGOT, MythicItems.RUBY_CRYSTAL_SHARD,
            MythicBlocks.RUBY_ORE, MythicBlocks.RUBY_BLOCK, MythicBlocks.RUBY_CRYSTAL_BLOCK,
            MythicItems.RUBY_SWORD, MythicItems.RUBY_PICKAXE, MythicItems.RUBY_AXE,
            MythicItems.RUBY_SHOVEL, MythicItems.RUBY_HOE,
            MythicItems.RUBY_HELMET, MythicItems.RUBY_CHESTPLATE, MythicItems.RUBY_LEGGINGS, MythicItems.RUBY_BOOTS);

        gemGroupNether(output, "sapphire",
            MythicItems.SAPPHIRE, MythicItems.SAPPHIRE_INGOT, MythicItems.SAPPHIRE_CRYSTAL_SHARD,
            MythicBlocks.SAPPHIRE_ORE, MythicBlocks.SAPPHIRE_BLOCK, MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK,
            MythicItems.SAPPHIRE_SWORD, MythicItems.SAPPHIRE_PICKAXE, MythicItems.SAPPHIRE_AXE,
            MythicItems.SAPPHIRE_SHOVEL, MythicItems.SAPPHIRE_HOE,
            MythicItems.SAPPHIRE_HELMET, MythicItems.SAPPHIRE_CHESTPLATE, MythicItems.SAPPHIRE_LEGGINGS, MythicItems.SAPPHIRE_BOOTS);

        // End gems (no deepslate ore)
        gemGroupNether(output, "jade",
            MythicItems.JADE, MythicItems.JADE_INGOT, MythicItems.JADE_CRYSTAL_SHARD,
            MythicBlocks.JADE_ORE, MythicBlocks.JADE_BLOCK, MythicBlocks.JADE_CRYSTAL_BLOCK,
            MythicItems.JADE_SWORD, MythicItems.JADE_PICKAXE, MythicItems.JADE_AXE,
            MythicItems.JADE_SHOVEL, MythicItems.JADE_HOE,
            MythicItems.JADE_HELMET, MythicItems.JADE_CHESTPLATE, MythicItems.JADE_LEGGINGS, MythicItems.JADE_BOOTS);

        gemGroupNether(output, "ametrine",
            MythicItems.AMETRINE, MythicItems.AMETRINE_INGOT, MythicItems.AMETRINE_CRYSTAL_SHARD,
            MythicBlocks.AMETRINE_ORE, MythicBlocks.AMETRINE_BLOCK, MythicBlocks.AMETRINE_CRYSTAL_BLOCK,
            MythicItems.AMETRINE_SWORD, MythicItems.AMETRINE_PICKAXE, MythicItems.AMETRINE_AXE,
            MythicItems.AMETRINE_SHOVEL, MythicItems.AMETRINE_HOE,
            MythicItems.AMETRINE_HELMET, MythicItems.AMETRINE_CHESTPLATE, MythicItems.AMETRINE_LEGGINGS, MythicItems.AMETRINE_BOOTS);

        // Crystal sub-blocks for each gem
        for (String gem : new String[]{"aquamarine","citrine","topaz","peridot","ruby","sapphire","jade","ametrine"}) {
            crystalSubBlocks(output, gem);
            stoneBlocks(output, gem);
        }

        // Necoium
        oreSmelting(output, List.of(MythicBlocks.NECOIUM_ORE, MythicBlocks.DEEPSLATE_NECOIUM_ORE, MythicItems.RAW_NECOIUM), RecipeCategory.MISC, MythicItems.NECOIUM_INGOT, 0.7f, 200, "necoium");
        oreBlasting(output, List.of(MythicBlocks.NECOIUM_ORE, MythicBlocks.DEEPSLATE_NECOIUM_ORE, MythicItems.RAW_NECOIUM), RecipeCategory.MISC, MythicItems.NECOIUM_INGOT, 0.7f, 100, "necoium");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MythicBlocks.NECOIUM_BLOCK)
            .define('#', MythicItems.NECOIUM_INGOT)
            .pattern("###").pattern("###").pattern("###")
            .unlockedBy("has_necoium_ingot", has(MythicItems.NECOIUM_INGOT))
            .save(output, rl("necoium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MythicItems.NECOIUM_INGOT, 9)
            .requires(MythicBlocks.NECOIUM_BLOCK)
            .unlockedBy("has_necoium_block", has(MythicBlocks.NECOIUM_BLOCK))
            .save(output, rl("necoium_ingot_from_block"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MythicBlocks.RAW_NECOIUM_BLOCK)
            .define('#', MythicItems.RAW_NECOIUM)
            .pattern("###").pattern("###").pattern("###")
            .unlockedBy("has_raw_necoium", has(MythicItems.RAW_NECOIUM))
            .save(output, rl("raw_necoium_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MythicItems.RAW_NECOIUM, 9)
            .requires(MythicBlocks.RAW_NECOIUM_BLOCK)
            .unlockedBy("has_raw_necoium_block", has(MythicBlocks.RAW_NECOIUM_BLOCK))
            .save(output, rl("raw_necoium_from_block"));

        // Necoium carrot
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MythicItems.NECOIUM_CARROT)
            .define('N', MythicBlocks.NECOIUM_BLOCK)
            .define('C', Items.CARROT)
            .pattern("NNN").pattern("NCN").pattern("NNN")
            .unlockedBy("has_necoium_block", has(MythicBlocks.NECOIUM_BLOCK))
            .save(output, rl("necoium_carrot"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MythicItems.NECOIUM_CARROT)
            .define('N', MythicBlocks.NECOIUM_BLOCK)
            .define('C', Items.GOLDEN_CARROT)
            .pattern("NNN").pattern("NCN").pattern("NNN")
            .unlockedBy("has_necoium_block", has(MythicBlocks.NECOIUM_BLOCK))
            .save(output, rl("necoium_carrot_from_golden_carrot"));

        // Mythic upgrade smithing template
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE)
            .define('I', MythicItems.NECOIUM_INGOT)
            .define('T', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
            .define('B', MythicBlocks.NECOIUM_BLOCK)
            .pattern("ITI").pattern("IBI").pattern("III")
            .unlockedBy("has_necoium_ingot", has(MythicItems.NECOIUM_INGOT))
            .save(output, rl("mythic_upgrade_smithing_template"));
    }

    private void gemGroup(RecipeOutput output, String gem,
                          Item gemItem, Item ingot, Item shard,
                          Block ore, Block deepslateOre, Block storageBlock, Block crystalBlock,
                          Item sword, Item pickaxe, Item axe, Item shovel, Item hoe,
                          Item helmet, Item chestplate, Item leggings, Item boots,
                          boolean hasCaveOres) {
        oreSmelting(output, List.of(ore, deepslateOre), RecipeCategory.MISC, gemItem, 0.7f, 200, gem);
        oreBlasting(output, List.of(ore, deepslateOre), RecipeCategory.MISC, gemItem, 0.7f, 100, gem);
        storageAndUnpack(output, gem, gemItem, storageBlock);
        crystalBlockRecipes(output, gem, shard, crystalBlock);
        tools(output, gem, gemItem, sword, pickaxe, axe, shovel, hoe);
        armor(output, gem, gemItem, helmet, chestplate, leggings, boots);
    }

    private void gemGroupNether(RecipeOutput output, String gem,
                                Item gemItem, Item ingot, Item shard,
                                Block ore, Block storageBlock, Block crystalBlock,
                                Item sword, Item pickaxe, Item axe, Item shovel, Item hoe,
                                Item helmet, Item chestplate, Item leggings, Item boots) {
        oreSmelting(output, List.of(ore), RecipeCategory.MISC, gemItem, 0.7f, 200, gem);
        oreBlasting(output, List.of(ore), RecipeCategory.MISC, gemItem, 0.7f, 100, gem);
        storageAndUnpack(output, gem, gemItem, storageBlock);
        crystalBlockRecipes(output, gem, shard, crystalBlock);
        tools(output, gem, gemItem, sword, pickaxe, axe, shovel, hoe);
        armor(output, gem, gemItem, helmet, chestplate, leggings, boots);
    }

    private void storageAndUnpack(RecipeOutput output, String gem, Item gemItem, Block storageBlock) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, storageBlock)
            .define('#', gemItem)
            .pattern("###").pattern("###").pattern("###")
            .unlockedBy("has_" + gem, has(gemItem))
            .save(output, rl(gem + "_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, gemItem, 9)
            .requires(storageBlock)
            .unlockedBy("has_" + gem + "_block", has(storageBlock))
            .save(output, rl(gem + "_from_block"));
    }

    private void crystalBlockRecipes(RecipeOutput output, String gem, Item shard, Block crystalBlock) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, crystalBlock)
            .define('#', shard)
            .pattern("##").pattern("##")
            .unlockedBy("has_" + gem + "_shard", has(shard))
            .save(output, rl(gem + "_crystal_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, shard, 4)
            .requires(crystalBlock)
            .unlockedBy("has_" + gem + "_crystal_block", has(crystalBlock))
            .save(output, rl(gem + "_crystal_shard_from_block"));
    }

    private void tools(RecipeOutput output, String gem, Item mat,
                       Item sword, Item pickaxe, Item axe, Item shovel, Item hoe) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
            .define('#', mat).define('S', Items.STICK)
            .pattern("#").pattern("#").pattern("S")
            .unlockedBy("has_" + gem, has(mat))
            .save(output, rl(gem + "_sword"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
            .define('#', mat).define('S', Items.STICK)
            .pattern("###").pattern(" S ").pattern(" S ")
            .unlockedBy("has_" + gem, has(mat))
            .save(output, rl(gem + "_pickaxe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
            .define('#', mat).define('S', Items.STICK)
            .pattern("##").pattern("#S").pattern(" S")
            .unlockedBy("has_" + gem, has(mat))
            .save(output, rl(gem + "_axe"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
            .define('#', mat).define('S', Items.STICK)
            .pattern("#").pattern("S").pattern("S")
            .unlockedBy("has_" + gem, has(mat))
            .save(output, rl(gem + "_shovel"));
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
            .define('#', mat).define('S', Items.STICK)
            .pattern("##").pattern(" S").pattern(" S")
            .unlockedBy("has_" + gem, has(mat))
            .save(output, rl(gem + "_hoe"));
    }

    private void armor(RecipeOutput output, String gem, Item mat,
                       Item helmet, Item chestplate, Item leggings, Item boots) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
            .define('#', mat)
            .pattern("###").pattern("# #")
            .unlockedBy("has_" + gem, has(mat))
            .save(output, rl(gem + "_helmet"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
            .define('#', mat)
            .pattern("# #").pattern("###").pattern("###")
            .unlockedBy("has_" + gem, has(mat))
            .save(output, rl(gem + "_chestplate"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
            .define('#', mat)
            .pattern("###").pattern("# #").pattern("# #")
            .unlockedBy("has_" + gem, has(mat))
            .save(output, rl(gem + "_leggings"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
            .define('#', mat)
            .pattern("# #").pattern("# #")
            .unlockedBy("has_" + gem, has(mat))
            .save(output, rl(gem + "_boots"));
    }

    private void crystalSubBlocks(RecipeOutput output, String gem) {
        Block crystalBlock = getGemCrystalBlock(gem);
        Block polishedCrystalBlock = getBlock("polished_" + gem + "_crystal_block");
        Block crystalBricks = getBlock(gem + "_crystal_bricks");
        Block crystalPillar = getBlock(gem + "_crystal_pillar");
        Block cutCrystalPillar = getBlock("cut_" + gem + "_crystal_pillar");

        // Polished crystal: 2x2 crystal blocks → 4 polished
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, polishedCrystalBlock, 4)
            .define('#', crystalBlock)
            .pattern("##").pattern("##")
            .unlockedBy("has_crystal", has(crystalBlock))
            .save(output, rl(gem + "_polished_crystal_block"));

        // Crystal bricks: 2x2 polished → 4 bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, crystalBricks, 4)
            .define('#', polishedCrystalBlock)
            .pattern("##").pattern("##")
            .unlockedBy("has_crystal", has(polishedCrystalBlock))
            .save(output, rl(gem + "_crystal_bricks"));

        // Crystal pillar: 2 crystal blocks vertically → 2 pillars
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, crystalPillar, 2)
            .define('#', crystalBlock)
            .pattern("#").pattern("#")
            .unlockedBy("has_crystal", has(crystalBlock))
            .save(output, rl(gem + "_crystal_pillar"));

        // Cut crystal pillar: 2 polished vertically → 2 cut pillars
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, cutCrystalPillar, 2)
            .define('#', polishedCrystalBlock)
            .pattern("#").pattern("#")
            .unlockedBy("has_crystal", has(polishedCrystalBlock))
            .save(output, rl(gem + "_cut_crystal_pillar"));

        // Slabs and stairs via stonecutting
        stonecutAndSlabStairs(output, gem + "_crystal_block", crystalBlock,
            getBlock(gem + "_crystal_block_slab"), getBlock(gem + "_crystal_block_stairs"));
        stonecutAndSlabStairs(output, gem + "_polished_crystal_block", polishedCrystalBlock,
            getBlock("polished_" + gem + "_crystal_block_slab"), getBlock("polished_" + gem + "_crystal_block_stairs"));
        stonecutAndSlabStairs(output, gem + "_crystal_bricks", crystalBricks,
            getBlock(gem + "_crystal_bricks_slab"), getBlock(gem + "_crystal_bricks_stairs"));
    }

    private void stonecutAndSlabStairs(RecipeOutput output, String name, Block source, Block slab, Block stairs) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slab, 6)
            .define('#', source)
            .pattern("###")
            .unlockedBy("has_block", has(source))
            .save(output, rl(name + "_slab"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4)
            .define('#', source)
            .pattern("#  ").pattern("## ").pattern("###")
            .unlockedBy("has_block", has(source))
            .save(output, rl(name + "_stairs"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(source), RecipeCategory.BUILDING_BLOCKS, slab, 2)
            .unlockedBy("has_block", has(source))
            .save(output, rl(name + "_slab_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(source), RecipeCategory.BUILDING_BLOCKS, stairs)
            .unlockedBy("has_block", has(source))
            .save(output, rl(name + "_stairs_stonecutting"));
    }

    private void stoneBlocks(RecipeOutput output, String gem) {
        Item shard = getGemShard(gem);
        Block stone = getBlock(gem + "_stone");
        Block polishedStone = getBlock("polished_" + gem + "_stone");

        // Stone from cobblestone + shard
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stone)
            .define('C', Items.COBBLESTONE)
            .define('S', shard)
            .pattern("CS")
            .unlockedBy("has_shard", has(shard))
            .save(output, rl(gem + "_stone"));

        // Polished stone from stone
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, polishedStone, 4)
            .define('#', stone)
            .pattern("##").pattern("##")
            .unlockedBy("has_stone", has(stone))
            .save(output, rl(gem + "_polished_stone"));

        stonecutAndSlabStairs(output, gem + "_stone", stone,
            getBlock(gem + "_stone_slab"), getBlock(gem + "_stone_stairs"));
        stonecutAndSlabStairs(output, gem + "_polished_stone", polishedStone,
            getBlock("polished_" + gem + "_stone_slab"), getBlock("polished_" + gem + "_stone_stairs"));
    }

    private static Block getBlock(String name) {
        return switch (name) {
            case "polished_aquamarine_crystal_block"   -> MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK;
            case "polished_citrine_crystal_block"      -> MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK;
            case "polished_topaz_crystal_block"        -> MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK;
            case "polished_peridot_crystal_block"      -> MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK;
            case "polished_ruby_crystal_block"         -> MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK;
            case "polished_sapphire_crystal_block"     -> MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK;
            case "polished_jade_crystal_block"         -> MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK;
            case "polished_ametrine_crystal_block"     -> MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK;
            case "aquamarine_crystal_bricks"           -> MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS;
            case "citrine_crystal_bricks"              -> MythicBlocks.CITRINE_CRYSTAL_BRICKS;
            case "topaz_crystal_bricks"                -> MythicBlocks.TOPAZ_CRYSTAL_BRICKS;
            case "peridot_crystal_bricks"              -> MythicBlocks.PERIDOT_CRYSTAL_BRICKS;
            case "ruby_crystal_bricks"                 -> MythicBlocks.RUBY_CRYSTAL_BRICKS;
            case "sapphire_crystal_bricks"             -> MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS;
            case "jade_crystal_bricks"                 -> MythicBlocks.JADE_CRYSTAL_BRICKS;
            case "ametrine_crystal_bricks"             -> MythicBlocks.AMETRINE_CRYSTAL_BRICKS;
            case "aquamarine_crystal_pillar"           -> MythicBlocks.AQUAMARINE_CRYSTAL_PILLAR;
            case "citrine_crystal_pillar"              -> MythicBlocks.CITRINE_CRYSTAL_PILLAR;
            case "topaz_crystal_pillar"                -> MythicBlocks.TOPAZ_CRYSTAL_PILLAR;
            case "peridot_crystal_pillar"              -> MythicBlocks.PERIDOT_CRYSTAL_PILLAR;
            case "ruby_crystal_pillar"                 -> MythicBlocks.RUBY_CRYSTAL_PILLAR;
            case "sapphire_crystal_pillar"             -> MythicBlocks.SAPPHIRE_CRYSTAL_PILLAR;
            case "jade_crystal_pillar"                 -> MythicBlocks.JADE_CRYSTAL_PILLAR;
            case "ametrine_crystal_pillar"             -> MythicBlocks.AMETRINE_CRYSTAL_PILLAR;
            case "cut_aquamarine_crystal_pillar"       -> MythicBlocks.CUT_AQUAMARINE_CRYSTAL_PILLAR;
            case "cut_citrine_crystal_pillar"          -> MythicBlocks.CUT_CITRINE_CRYSTAL_PILLAR;
            case "cut_topaz_crystal_pillar"            -> MythicBlocks.CUT_TOPAZ_CRYSTAL_PILLAR;
            case "cut_peridot_crystal_pillar"          -> MythicBlocks.CUT_PERIDOT_CRYSTAL_PILLAR;
            case "cut_ruby_crystal_pillar"             -> MythicBlocks.CUT_RUBY_CRYSTAL_PILLAR;
            case "cut_sapphire_crystal_pillar"         -> MythicBlocks.CUT_SAPPHIRE_CRYSTAL_PILLAR;
            case "cut_jade_crystal_pillar"             -> MythicBlocks.CUT_JADE_CRYSTAL_PILLAR;
            case "cut_ametrine_crystal_pillar"         -> MythicBlocks.CUT_AMETRINE_CRYSTAL_PILLAR;
            case "aquamarine_crystal_block_slab"       -> MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_SLAB;
            case "citrine_crystal_block_slab"          -> MythicBlocks.CITRINE_CRYSTAL_BLOCK_SLAB;
            case "topaz_crystal_block_slab"            -> MythicBlocks.TOPAZ_CRYSTAL_BLOCK_SLAB;
            case "peridot_crystal_block_slab"          -> MythicBlocks.PERIDOT_CRYSTAL_BLOCK_SLAB;
            case "ruby_crystal_block_slab"             -> MythicBlocks.RUBY_CRYSTAL_BLOCK_SLAB;
            case "sapphire_crystal_block_slab"         -> MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_SLAB;
            case "jade_crystal_block_slab"             -> MythicBlocks.JADE_CRYSTAL_BLOCK_SLAB;
            case "ametrine_crystal_block_slab"         -> MythicBlocks.AMETRINE_CRYSTAL_BLOCK_SLAB;
            case "aquamarine_crystal_block_stairs"     -> MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK_STAIRS;
            case "citrine_crystal_block_stairs"        -> MythicBlocks.CITRINE_CRYSTAL_BLOCK_STAIRS;
            case "topaz_crystal_block_stairs"          -> MythicBlocks.TOPAZ_CRYSTAL_BLOCK_STAIRS;
            case "peridot_crystal_block_stairs"        -> MythicBlocks.PERIDOT_CRYSTAL_BLOCK_STAIRS;
            case "ruby_crystal_block_stairs"           -> MythicBlocks.RUBY_CRYSTAL_BLOCK_STAIRS;
            case "sapphire_crystal_block_stairs"       -> MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK_STAIRS;
            case "jade_crystal_block_stairs"           -> MythicBlocks.JADE_CRYSTAL_BLOCK_STAIRS;
            case "ametrine_crystal_block_stairs"       -> MythicBlocks.AMETRINE_CRYSTAL_BLOCK_STAIRS;
            case "polished_aquamarine_crystal_block_slab"   -> MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_SLAB;
            case "polished_citrine_crystal_block_slab"      -> MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_SLAB;
            case "polished_topaz_crystal_block_slab"        -> MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_SLAB;
            case "polished_peridot_crystal_block_slab"      -> MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_SLAB;
            case "polished_ruby_crystal_block_slab"         -> MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_SLAB;
            case "polished_sapphire_crystal_block_slab"     -> MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_SLAB;
            case "polished_jade_crystal_block_slab"         -> MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_SLAB;
            case "polished_ametrine_crystal_block_slab"     -> MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_SLAB;
            case "polished_aquamarine_crystal_block_stairs" -> MythicBlocks.POLISHED_AQUAMARINE_CRYSTAL_BLOCK_STAIRS;
            case "polished_citrine_crystal_block_stairs"    -> MythicBlocks.POLISHED_CITRINE_CRYSTAL_BLOCK_STAIRS;
            case "polished_topaz_crystal_block_stairs"      -> MythicBlocks.POLISHED_TOPAZ_CRYSTAL_BLOCK_STAIRS;
            case "polished_peridot_crystal_block_stairs"    -> MythicBlocks.POLISHED_PERIDOT_CRYSTAL_BLOCK_STAIRS;
            case "polished_ruby_crystal_block_stairs"       -> MythicBlocks.POLISHED_RUBY_CRYSTAL_BLOCK_STAIRS;
            case "polished_sapphire_crystal_block_stairs"   -> MythicBlocks.POLISHED_SAPPHIRE_CRYSTAL_BLOCK_STAIRS;
            case "polished_jade_crystal_block_stairs"       -> MythicBlocks.POLISHED_JADE_CRYSTAL_BLOCK_STAIRS;
            case "polished_ametrine_crystal_block_stairs"   -> MythicBlocks.POLISHED_AMETRINE_CRYSTAL_BLOCK_STAIRS;
            case "aquamarine_crystal_bricks_slab"      -> MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_SLAB;
            case "citrine_crystal_bricks_slab"         -> MythicBlocks.CITRINE_CRYSTAL_BRICKS_SLAB;
            case "topaz_crystal_bricks_slab"           -> MythicBlocks.TOPAZ_CRYSTAL_BRICKS_SLAB;
            case "peridot_crystal_bricks_slab"         -> MythicBlocks.PERIDOT_CRYSTAL_BRICKS_SLAB;
            case "ruby_crystal_bricks_slab"            -> MythicBlocks.RUBY_CRYSTAL_BRICKS_SLAB;
            case "sapphire_crystal_bricks_slab"        -> MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_SLAB;
            case "jade_crystal_bricks_slab"            -> MythicBlocks.JADE_CRYSTAL_BRICKS_SLAB;
            case "ametrine_crystal_bricks_slab"        -> MythicBlocks.AMETRINE_CRYSTAL_BRICKS_SLAB;
            case "aquamarine_crystal_bricks_stairs"    -> MythicBlocks.AQUAMARINE_CRYSTAL_BRICKS_STAIRS;
            case "citrine_crystal_bricks_stairs"       -> MythicBlocks.CITRINE_CRYSTAL_BRICKS_STAIRS;
            case "topaz_crystal_bricks_stairs"         -> MythicBlocks.TOPAZ_CRYSTAL_BRICKS_STAIRS;
            case "peridot_crystal_bricks_stairs"       -> MythicBlocks.PERIDOT_CRYSTAL_BRICKS_STAIRS;
            case "ruby_crystal_bricks_stairs"          -> MythicBlocks.RUBY_CRYSTAL_BRICKS_STAIRS;
            case "sapphire_crystal_bricks_stairs"      -> MythicBlocks.SAPPHIRE_CRYSTAL_BRICKS_STAIRS;
            case "jade_crystal_bricks_stairs"          -> MythicBlocks.JADE_CRYSTAL_BRICKS_STAIRS;
            case "ametrine_crystal_bricks_stairs"      -> MythicBlocks.AMETRINE_CRYSTAL_BRICKS_STAIRS;
            case "aquamarine_stone"                    -> MythicBlocks.AQUAMARINE_STONE;
            case "citrine_stone"                       -> MythicBlocks.CITRINE_STONE;
            case "topaz_stone"                         -> MythicBlocks.TOPAZ_STONE;
            case "peridot_stone"                       -> MythicBlocks.PERIDOT_STONE;
            case "ruby_stone"                          -> MythicBlocks.RUBY_STONE;
            case "sapphire_stone"                      -> MythicBlocks.SAPPHIRE_STONE;
            case "jade_stone"                          -> MythicBlocks.JADE_STONE;
            case "ametrine_stone"                      -> MythicBlocks.AMETRINE_STONE;
            case "polished_aquamarine_stone"           -> MythicBlocks.POLISHED_AQUAMARINE_STONE;
            case "polished_citrine_stone"              -> MythicBlocks.POLISHED_CITRINE_STONE;
            case "polished_topaz_stone"                -> MythicBlocks.POLISHED_TOPAZ_STONE;
            case "polished_peridot_stone"              -> MythicBlocks.POLISHED_PERIDOT_STONE;
            case "polished_ruby_stone"                 -> MythicBlocks.POLISHED_RUBY_STONE;
            case "polished_sapphire_stone"             -> MythicBlocks.POLISHED_SAPPHIRE_STONE;
            case "polished_jade_stone"                 -> MythicBlocks.POLISHED_JADE_STONE;
            case "polished_ametrine_stone"             -> MythicBlocks.POLISHED_AMETRINE_STONE;
            case "aquamarine_stone_slab"               -> MythicBlocks.AQUAMARINE_STONE_SLAB;
            case "citrine_stone_slab"                  -> MythicBlocks.CITRINE_STONE_SLAB;
            case "topaz_stone_slab"                    -> MythicBlocks.TOPAZ_STONE_SLAB;
            case "peridot_stone_slab"                  -> MythicBlocks.PERIDOT_STONE_SLAB;
            case "ruby_stone_slab"                     -> MythicBlocks.RUBY_STONE_SLAB;
            case "sapphire_stone_slab"                 -> MythicBlocks.SAPPHIRE_STONE_SLAB;
            case "jade_stone_slab"                     -> MythicBlocks.JADE_STONE_SLAB;
            case "ametrine_stone_slab"                 -> MythicBlocks.AMETRINE_STONE_SLAB;
            case "aquamarine_stone_stairs"             -> MythicBlocks.AQUAMARINE_STONE_STAIRS;
            case "citrine_stone_stairs"                -> MythicBlocks.CITRINE_STONE_STAIRS;
            case "topaz_stone_stairs"                  -> MythicBlocks.TOPAZ_STONE_STAIRS;
            case "peridot_stone_stairs"                -> MythicBlocks.PERIDOT_STONE_STAIRS;
            case "ruby_stone_stairs"                   -> MythicBlocks.RUBY_STONE_STAIRS;
            case "sapphire_stone_stairs"               -> MythicBlocks.SAPPHIRE_STONE_STAIRS;
            case "jade_stone_stairs"                   -> MythicBlocks.JADE_STONE_STAIRS;
            case "ametrine_stone_stairs"               -> MythicBlocks.AMETRINE_STONE_STAIRS;
            case "polished_aquamarine_stone_slab"      -> MythicBlocks.POLISHED_AQUAMARINE_STONE_SLAB;
            case "polished_citrine_stone_slab"         -> MythicBlocks.POLISHED_CITRINE_STONE_SLAB;
            case "polished_topaz_stone_slab"           -> MythicBlocks.POLISHED_TOPAZ_STONE_SLAB;
            case "polished_peridot_stone_slab"         -> MythicBlocks.POLISHED_PERIDOT_STONE_SLAB;
            case "polished_ruby_stone_slab"            -> MythicBlocks.POLISHED_RUBY_STONE_SLAB;
            case "polished_sapphire_stone_slab"        -> MythicBlocks.POLISHED_SAPPHIRE_STONE_SLAB;
            case "polished_jade_stone_slab"            -> MythicBlocks.POLISHED_JADE_STONE_SLAB;
            case "polished_ametrine_stone_slab"        -> MythicBlocks.POLISHED_AMETRINE_STONE_SLAB;
            case "polished_aquamarine_stone_stairs"    -> MythicBlocks.POLISHED_AQUAMARINE_STONE_STAIRS;
            case "polished_citrine_stone_stairs"       -> MythicBlocks.POLISHED_CITRINE_STONE_STAIRS;
            case "polished_topaz_stone_stairs"         -> MythicBlocks.POLISHED_TOPAZ_STONE_STAIRS;
            case "polished_peridot_stone_stairs"       -> MythicBlocks.POLISHED_PERIDOT_STONE_STAIRS;
            case "polished_ruby_stone_stairs"          -> MythicBlocks.POLISHED_RUBY_STONE_STAIRS;
            case "polished_sapphire_stone_stairs"      -> MythicBlocks.POLISHED_SAPPHIRE_STONE_STAIRS;
            case "polished_jade_stone_stairs"          -> MythicBlocks.POLISHED_JADE_STONE_STAIRS;
            case "polished_ametrine_stone_stairs"      -> MythicBlocks.POLISHED_AMETRINE_STONE_STAIRS;
            default -> throw new IllegalArgumentException("Unknown block: " + name);
        };
    }

    private static Block getGemCrystalBlock(String gem) {
        return switch (gem) {
            case "aquamarine" -> MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK;
            case "citrine"    -> MythicBlocks.CITRINE_CRYSTAL_BLOCK;
            case "topaz"      -> MythicBlocks.TOPAZ_CRYSTAL_BLOCK;
            case "peridot"    -> MythicBlocks.PERIDOT_CRYSTAL_BLOCK;
            case "ruby"       -> MythicBlocks.RUBY_CRYSTAL_BLOCK;
            case "sapphire"   -> MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK;
            case "jade"       -> MythicBlocks.JADE_CRYSTAL_BLOCK;
            case "ametrine"   -> MythicBlocks.AMETRINE_CRYSTAL_BLOCK;
            default -> throw new IllegalArgumentException("Unknown gem: " + gem);
        };
    }

    private static Item getGemShard(String gem) {
        return switch (gem) {
            case "aquamarine" -> MythicItems.AQUAMARINE_CRYSTAL_SHARD;
            case "citrine"    -> MythicItems.CITRINE_CRYSTAL_SHARD;
            case "topaz"      -> MythicItems.TOPAZ_CRYSTAL_SHARD;
            case "peridot"    -> MythicItems.PERIDOT_CRYSTAL_SHARD;
            case "ruby"       -> MythicItems.RUBY_CRYSTAL_SHARD;
            case "sapphire"   -> MythicItems.SAPPHIRE_CRYSTAL_SHARD;
            case "jade"       -> MythicItems.JADE_CRYSTAL_SHARD;
            case "ametrine"   -> MythicItems.AMETRINE_CRYSTAL_SHARD;
            default -> throw new IllegalArgumentException("Unknown gem: " + gem);
        };
    }

    private static ResourceLocation rl(String name) {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name);
    }
}
