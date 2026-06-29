package net.trique.mythicupgrades.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
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
            MythicBlocks.AQUAMARINE_CRYSTAL_BLOCK, true);

        gemGroup(output, "citrine",
            MythicItems.CITRINE, MythicItems.CITRINE_INGOT, MythicItems.CITRINE_CRYSTAL_SHARD,
            MythicBlocks.CITRINE_ORE, MythicBlocks.DEEPSLATE_CITRINE_ORE, MythicBlocks.CITRINE_BLOCK,
            MythicBlocks.CITRINE_CRYSTAL_BLOCK, true);

        gemGroup(output, "topaz",
            MythicItems.TOPAZ, MythicItems.TOPAZ_INGOT, MythicItems.TOPAZ_CRYSTAL_SHARD,
            MythicBlocks.TOPAZ_ORE, MythicBlocks.DEEPSLATE_TOPAZ_ORE, MythicBlocks.TOPAZ_BLOCK,
            MythicBlocks.TOPAZ_CRYSTAL_BLOCK, true);

        gemGroup(output, "peridot",
            MythicItems.PERIDOT, MythicItems.PERIDOT_INGOT, MythicItems.PERIDOT_CRYSTAL_SHARD,
            MythicBlocks.PERIDOT_ORE, MythicBlocks.DEEPSLATE_PERIDOT_ORE, MythicBlocks.PERIDOT_BLOCK,
            MythicBlocks.PERIDOT_CRYSTAL_BLOCK, true);

        // Nether gems (no deepslate ore)
        gemGroupNether(output, "ruby",
            MythicItems.RUBY, MythicItems.RUBY_INGOT, MythicItems.RUBY_CRYSTAL_SHARD,
            MythicBlocks.RUBY_ORE, MythicBlocks.RUBY_BLOCK, MythicBlocks.RUBY_CRYSTAL_BLOCK);

        gemGroupNether(output, "sapphire",
            MythicItems.SAPPHIRE, MythicItems.SAPPHIRE_INGOT, MythicItems.SAPPHIRE_CRYSTAL_SHARD,
            MythicBlocks.SAPPHIRE_ORE, MythicBlocks.SAPPHIRE_BLOCK, MythicBlocks.SAPPHIRE_CRYSTAL_BLOCK);

        // End gems (no deepslate ore)
        gemGroupNether(output, "jade",
            MythicItems.JADE, MythicItems.JADE_INGOT, MythicItems.JADE_CRYSTAL_SHARD,
            MythicBlocks.JADE_ORE, MythicBlocks.JADE_BLOCK, MythicBlocks.JADE_CRYSTAL_BLOCK);

        gemGroupNether(output, "ametrine",
            MythicItems.AMETRINE, MythicItems.AMETRINE_INGOT, MythicItems.AMETRINE_CRYSTAL_SHARD,
            MythicBlocks.AMETRINE_ORE, MythicBlocks.AMETRINE_BLOCK, MythicBlocks.AMETRINE_CRYSTAL_BLOCK);

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

        mythicUpgradeSmithing(output);

        // Mythic upgrade smithing template
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE)
            .define('I', MythicItems.NECOIUM_INGOT)
            .define('T', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
            .define('B', MythicBlocks.NECOIUM_BLOCK)
            .pattern("ITI").pattern("IBI").pattern("III")
            .unlockedBy("has_necoium_ingot", has(MythicItems.NECOIUM_INGOT))
            .save(output, rl("mythic_upgrade_smithing_template"));
    }

    private record GemSet(String name, Item ingot, Item sword, Item pickaxe, Item axe, Item shovel, Item hoe,
                          Item helmet, Item chestplate, Item leggings, Item boots) {}
    private record BaseGear(String name, Item sword, Item pickaxe, Item axe, Item shovel, Item hoe,
                            Item helmet, Item chestplate, Item leggings, Item boots) {}

    private void mythicUpgradeSmithing(RecipeOutput output) {
        GemSet[] gems = {
            new GemSet("aquamarine", MythicItems.AQUAMARINE_INGOT,  MythicItems.AQUAMARINE_SWORD,  MythicItems.AQUAMARINE_PICKAXE,  MythicItems.AQUAMARINE_AXE,  MythicItems.AQUAMARINE_SHOVEL,  MythicItems.AQUAMARINE_HOE,  MythicItems.AQUAMARINE_HELMET,  MythicItems.AQUAMARINE_CHESTPLATE,  MythicItems.AQUAMARINE_LEGGINGS,  MythicItems.AQUAMARINE_BOOTS),
            new GemSet("citrine",    MythicItems.CITRINE_INGOT,     MythicItems.CITRINE_SWORD,     MythicItems.CITRINE_PICKAXE,     MythicItems.CITRINE_AXE,     MythicItems.CITRINE_SHOVEL,     MythicItems.CITRINE_HOE,     MythicItems.CITRINE_HELMET,     MythicItems.CITRINE_CHESTPLATE,     MythicItems.CITRINE_LEGGINGS,     MythicItems.CITRINE_BOOTS),
            new GemSet("topaz",      MythicItems.TOPAZ_INGOT,       MythicItems.TOPAZ_SWORD,       MythicItems.TOPAZ_PICKAXE,       MythicItems.TOPAZ_AXE,       MythicItems.TOPAZ_SHOVEL,       MythicItems.TOPAZ_HOE,       MythicItems.TOPAZ_HELMET,       MythicItems.TOPAZ_CHESTPLATE,       MythicItems.TOPAZ_LEGGINGS,       MythicItems.TOPAZ_BOOTS),
            new GemSet("peridot",    MythicItems.PERIDOT_INGOT,     MythicItems.PERIDOT_SWORD,     MythicItems.PERIDOT_PICKAXE,     MythicItems.PERIDOT_AXE,     MythicItems.PERIDOT_SHOVEL,     MythicItems.PERIDOT_HOE,     MythicItems.PERIDOT_HELMET,     MythicItems.PERIDOT_CHESTPLATE,     MythicItems.PERIDOT_LEGGINGS,     MythicItems.PERIDOT_BOOTS),
            new GemSet("ruby",       MythicItems.RUBY_INGOT,        MythicItems.RUBY_SWORD,        MythicItems.RUBY_PICKAXE,        MythicItems.RUBY_AXE,        MythicItems.RUBY_SHOVEL,        MythicItems.RUBY_HOE,        MythicItems.RUBY_HELMET,        MythicItems.RUBY_CHESTPLATE,        MythicItems.RUBY_LEGGINGS,        MythicItems.RUBY_BOOTS),
            new GemSet("sapphire",   MythicItems.SAPPHIRE_INGOT,    MythicItems.SAPPHIRE_SWORD,    MythicItems.SAPPHIRE_PICKAXE,    MythicItems.SAPPHIRE_AXE,    MythicItems.SAPPHIRE_SHOVEL,    MythicItems.SAPPHIRE_HOE,    MythicItems.SAPPHIRE_HELMET,    MythicItems.SAPPHIRE_CHESTPLATE,    MythicItems.SAPPHIRE_LEGGINGS,    MythicItems.SAPPHIRE_BOOTS),
            new GemSet("jade",       MythicItems.JADE_INGOT,        MythicItems.JADE_SWORD,        MythicItems.JADE_PICKAXE,        MythicItems.JADE_AXE,        MythicItems.JADE_SHOVEL,        MythicItems.JADE_HOE,        MythicItems.JADE_HELMET,        MythicItems.JADE_CHESTPLATE,        MythicItems.JADE_LEGGINGS,        MythicItems.JADE_BOOTS),
            new GemSet("ametrine",   MythicItems.AMETRINE_INGOT,    MythicItems.AMETRINE_SWORD,    MythicItems.AMETRINE_PICKAXE,    MythicItems.AMETRINE_AXE,    MythicItems.AMETRINE_SHOVEL,    MythicItems.AMETRINE_HOE,    MythicItems.AMETRINE_HELMET,    MythicItems.AMETRINE_CHESTPLATE,    MythicItems.AMETRINE_LEGGINGS,    MythicItems.AMETRINE_BOOTS),
        };
        BaseGear[] bases = {
            new BaseGear("netherite", Items.NETHERITE_SWORD, Items.NETHERITE_PICKAXE, Items.NETHERITE_AXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE, Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS),
            new BaseGear("aquamarine", MythicItems.AQUAMARINE_SWORD,  MythicItems.AQUAMARINE_PICKAXE,  MythicItems.AQUAMARINE_AXE,  MythicItems.AQUAMARINE_SHOVEL,  MythicItems.AQUAMARINE_HOE,  MythicItems.AQUAMARINE_HELMET,  MythicItems.AQUAMARINE_CHESTPLATE,  MythicItems.AQUAMARINE_LEGGINGS,  MythicItems.AQUAMARINE_BOOTS),
            new BaseGear("citrine",    MythicItems.CITRINE_SWORD,     MythicItems.CITRINE_PICKAXE,     MythicItems.CITRINE_AXE,     MythicItems.CITRINE_SHOVEL,     MythicItems.CITRINE_HOE,     MythicItems.CITRINE_HELMET,     MythicItems.CITRINE_CHESTPLATE,     MythicItems.CITRINE_LEGGINGS,     MythicItems.CITRINE_BOOTS),
            new BaseGear("topaz",      MythicItems.TOPAZ_SWORD,       MythicItems.TOPAZ_PICKAXE,       MythicItems.TOPAZ_AXE,       MythicItems.TOPAZ_SHOVEL,       MythicItems.TOPAZ_HOE,       MythicItems.TOPAZ_HELMET,       MythicItems.TOPAZ_CHESTPLATE,       MythicItems.TOPAZ_LEGGINGS,       MythicItems.TOPAZ_BOOTS),
            new BaseGear("peridot",    MythicItems.PERIDOT_SWORD,     MythicItems.PERIDOT_PICKAXE,     MythicItems.PERIDOT_AXE,     MythicItems.PERIDOT_SHOVEL,     MythicItems.PERIDOT_HOE,     MythicItems.PERIDOT_HELMET,     MythicItems.PERIDOT_CHESTPLATE,     MythicItems.PERIDOT_LEGGINGS,     MythicItems.PERIDOT_BOOTS),
            new BaseGear("ruby",       MythicItems.RUBY_SWORD,        MythicItems.RUBY_PICKAXE,        MythicItems.RUBY_AXE,        MythicItems.RUBY_SHOVEL,        MythicItems.RUBY_HOE,        MythicItems.RUBY_HELMET,        MythicItems.RUBY_CHESTPLATE,        MythicItems.RUBY_LEGGINGS,        MythicItems.RUBY_BOOTS),
            new BaseGear("sapphire",   MythicItems.SAPPHIRE_SWORD,    MythicItems.SAPPHIRE_PICKAXE,    MythicItems.SAPPHIRE_AXE,    MythicItems.SAPPHIRE_SHOVEL,    MythicItems.SAPPHIRE_HOE,    MythicItems.SAPPHIRE_HELMET,    MythicItems.SAPPHIRE_CHESTPLATE,    MythicItems.SAPPHIRE_LEGGINGS,    MythicItems.SAPPHIRE_BOOTS),
            new BaseGear("jade",       MythicItems.JADE_SWORD,        MythicItems.JADE_PICKAXE,        MythicItems.JADE_AXE,        MythicItems.JADE_SHOVEL,        MythicItems.JADE_HOE,        MythicItems.JADE_HELMET,        MythicItems.JADE_CHESTPLATE,        MythicItems.JADE_LEGGINGS,        MythicItems.JADE_BOOTS),
            new BaseGear("ametrine",   MythicItems.AMETRINE_SWORD,    MythicItems.AMETRINE_PICKAXE,    MythicItems.AMETRINE_AXE,    MythicItems.AMETRINE_SHOVEL,    MythicItems.AMETRINE_HOE,    MythicItems.AMETRINE_HELMET,    MythicItems.AMETRINE_CHESTPLATE,    MythicItems.AMETRINE_LEGGINGS,    MythicItems.AMETRINE_BOOTS),
        };

        for (GemSet gem : gems) {
            for (BaseGear base : bases) {
                if (gem.name().equals(base.name())) continue;
                smithingUpgrade(output, gem.ingot(), base.sword(),      gem.sword(),      gem.name() + "_from_" + base.name() + "_sword");
                smithingUpgrade(output, gem.ingot(), base.pickaxe(),    gem.pickaxe(),    gem.name() + "_from_" + base.name() + "_pickaxe");
                smithingUpgrade(output, gem.ingot(), base.axe(),        gem.axe(),        gem.name() + "_from_" + base.name() + "_axe");
                smithingUpgrade(output, gem.ingot(), base.shovel(),     gem.shovel(),     gem.name() + "_from_" + base.name() + "_shovel");
                smithingUpgrade(output, gem.ingot(), base.hoe(),        gem.hoe(),        gem.name() + "_from_" + base.name() + "_hoe");
                smithingUpgrade(output, gem.ingot(), base.helmet(),     gem.helmet(),     gem.name() + "_from_" + base.name() + "_helmet");
                smithingUpgrade(output, gem.ingot(), base.chestplate(), gem.chestplate(), gem.name() + "_from_" + base.name() + "_chestplate");
                smithingUpgrade(output, gem.ingot(), base.leggings(),   gem.leggings(),   gem.name() + "_from_" + base.name() + "_leggings");
                smithingUpgrade(output, gem.ingot(), base.boots(),      gem.boots(),      gem.name() + "_from_" + base.name() + "_boots");
            }
        }
    }

    private void smithingUpgrade(RecipeOutput output, Item ingot, Item base, Item result, String id) {
        SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE),
            Ingredient.of(base),
            Ingredient.of(ingot),
            RecipeCategory.COMBAT,
            result
        ).unlocks("has_mythic_upgrade_smithing_template", has(MythicItems.MYTHIC_UPGRADE_SMITHING_TEMPLATE))
         .save(output, rl("smithing_upgrade/" + id));
    }

    private void gemGroup(RecipeOutput output, String gem,
                          Item gemItem, Item ingot, Item shard,
                          Block ore, Block deepslateOre, Block storageBlock, Block crystalBlock,
                          boolean hasCaveOres) {
        oreSmelting(output, List.of(ore, deepslateOre), RecipeCategory.MISC, gemItem, 0.7f, 200, gem);
        oreBlasting(output, List.of(ore, deepslateOre), RecipeCategory.MISC, gemItem, 0.7f, 100, gem);
        storageAndUnpack(output, gem, ingot, storageBlock);
        crystalBlockRecipes(output, gem, shard, crystalBlock);
    }

    private void gemGroupNether(RecipeOutput output, String gem,
                                Item gemItem, Item ingot, Item shard,
                                Block ore, Block storageBlock, Block crystalBlock) {
        oreSmelting(output, List.of(ore), RecipeCategory.MISC, gemItem, 0.7f, 200, gem);
        oreBlasting(output, List.of(ore), RecipeCategory.MISC, gemItem, 0.7f, 100, gem);
        storageAndUnpack(output, gem, ingot, storageBlock);
        crystalBlockRecipes(output, gem, shard, crystalBlock);
    }

    private void storageAndUnpack(RecipeOutput output, String gem, Item ingotItem, Block storageBlock) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, storageBlock)
            .define('#', ingotItem)
            .pattern("###").pattern("###").pattern("###")
            .unlockedBy("has_" + gem + "_ingot", has(ingotItem))
            .save(output, rl(gem + "_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingotItem, 9)
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
        Block stone = getBlock(gem + "_schist");
        Block polishedStone = getBlock("polished_" + gem + "_schist");

        // Schist from cobblestone + shard
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stone)
            .define('C', Items.COBBLESTONE)
            .define('S', shard)
            .pattern("CS")
            .unlockedBy("has_shard", has(shard))
            .save(output, rl(gem + "_schist"));

        // Polished schist from schist
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, polishedStone, 4)
            .define('#', stone)
            .pattern("##").pattern("##")
            .unlockedBy("has_schist", has(stone))
            .save(output, rl(gem + "_polished_schist"));

        stonecutAndSlabStairs(output, gem + "_schist", stone,
            getBlock(gem + "_schist_slab"), getBlock(gem + "_schist_stairs"));
        stonecutAndSlabStairs(output, gem + "_polished_schist", polishedStone,
            getBlock("polished_" + gem + "_schist_slab"), getBlock("polished_" + gem + "_schist_stairs"));
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
            case "aquamarine_schist"                    -> MythicBlocks.AQUAMARINE_SCHIST;
            case "citrine_schist"                       -> MythicBlocks.CITRINE_SCHIST;
            case "topaz_schist"                         -> MythicBlocks.TOPAZ_SCHIST;
            case "peridot_schist"                       -> MythicBlocks.PERIDOT_SCHIST;
            case "ruby_schist"                          -> MythicBlocks.RUBY_SCHIST;
            case "sapphire_schist"                      -> MythicBlocks.SAPPHIRE_SCHIST;
            case "jade_schist"                          -> MythicBlocks.JADE_SCHIST;
            case "ametrine_schist"                      -> MythicBlocks.AMETRINE_SCHIST;
            case "polished_aquamarine_schist"           -> MythicBlocks.POLISHED_AQUAMARINE_SCHIST;
            case "polished_citrine_schist"              -> MythicBlocks.POLISHED_CITRINE_SCHIST;
            case "polished_topaz_schist"                -> MythicBlocks.POLISHED_TOPAZ_SCHIST;
            case "polished_peridot_schist"              -> MythicBlocks.POLISHED_PERIDOT_SCHIST;
            case "polished_ruby_schist"                 -> MythicBlocks.POLISHED_RUBY_SCHIST;
            case "polished_sapphire_schist"             -> MythicBlocks.POLISHED_SAPPHIRE_SCHIST;
            case "polished_jade_schist"                 -> MythicBlocks.POLISHED_JADE_SCHIST;
            case "polished_ametrine_schist"             -> MythicBlocks.POLISHED_AMETRINE_SCHIST;
            case "aquamarine_schist_slab"               -> MythicBlocks.AQUAMARINE_SCHIST_SLAB;
            case "citrine_schist_slab"                  -> MythicBlocks.CITRINE_SCHIST_SLAB;
            case "topaz_schist_slab"                    -> MythicBlocks.TOPAZ_SCHIST_SLAB;
            case "peridot_schist_slab"                  -> MythicBlocks.PERIDOT_SCHIST_SLAB;
            case "ruby_schist_slab"                     -> MythicBlocks.RUBY_SCHIST_SLAB;
            case "sapphire_schist_slab"                 -> MythicBlocks.SAPPHIRE_SCHIST_SLAB;
            case "jade_schist_slab"                     -> MythicBlocks.JADE_SCHIST_SLAB;
            case "ametrine_schist_slab"                 -> MythicBlocks.AMETRINE_SCHIST_SLAB;
            case "aquamarine_schist_stairs"             -> MythicBlocks.AQUAMARINE_SCHIST_STAIRS;
            case "citrine_schist_stairs"                -> MythicBlocks.CITRINE_SCHIST_STAIRS;
            case "topaz_schist_stairs"                  -> MythicBlocks.TOPAZ_SCHIST_STAIRS;
            case "peridot_schist_stairs"                -> MythicBlocks.PERIDOT_SCHIST_STAIRS;
            case "ruby_schist_stairs"                   -> MythicBlocks.RUBY_SCHIST_STAIRS;
            case "sapphire_schist_stairs"               -> MythicBlocks.SAPPHIRE_SCHIST_STAIRS;
            case "jade_schist_stairs"                   -> MythicBlocks.JADE_SCHIST_STAIRS;
            case "ametrine_schist_stairs"               -> MythicBlocks.AMETRINE_SCHIST_STAIRS;
            case "polished_aquamarine_schist_slab"      -> MythicBlocks.POLISHED_AQUAMARINE_SCHIST_SLAB;
            case "polished_citrine_schist_slab"         -> MythicBlocks.POLISHED_CITRINE_SCHIST_SLAB;
            case "polished_topaz_schist_slab"           -> MythicBlocks.POLISHED_TOPAZ_SCHIST_SLAB;
            case "polished_peridot_schist_slab"         -> MythicBlocks.POLISHED_PERIDOT_SCHIST_SLAB;
            case "polished_ruby_schist_slab"            -> MythicBlocks.POLISHED_RUBY_SCHIST_SLAB;
            case "polished_sapphire_schist_slab"        -> MythicBlocks.POLISHED_SAPPHIRE_SCHIST_SLAB;
            case "polished_jade_schist_slab"            -> MythicBlocks.POLISHED_JADE_SCHIST_SLAB;
            case "polished_ametrine_schist_slab"        -> MythicBlocks.POLISHED_AMETRINE_SCHIST_SLAB;
            case "polished_aquamarine_schist_stairs"    -> MythicBlocks.POLISHED_AQUAMARINE_SCHIST_STAIRS;
            case "polished_citrine_schist_stairs"       -> MythicBlocks.POLISHED_CITRINE_SCHIST_STAIRS;
            case "polished_topaz_schist_stairs"         -> MythicBlocks.POLISHED_TOPAZ_SCHIST_STAIRS;
            case "polished_peridot_schist_stairs"       -> MythicBlocks.POLISHED_PERIDOT_SCHIST_STAIRS;
            case "polished_ruby_schist_stairs"          -> MythicBlocks.POLISHED_RUBY_SCHIST_STAIRS;
            case "polished_sapphire_schist_stairs"      -> MythicBlocks.POLISHED_SAPPHIRE_SCHIST_STAIRS;
            case "polished_jade_schist_stairs"          -> MythicBlocks.POLISHED_JADE_SCHIST_STAIRS;
            case "polished_ametrine_schist_stairs"      -> MythicBlocks.POLISHED_AMETRINE_SCHIST_STAIRS;
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
