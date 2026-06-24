package net.trique.mythicupgrades.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.trique.mythicupgrades.Constants;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class MythicBlocks {

    private static final List<Map.Entry<String, Block>> DEFERRED = new ArrayList<>();

    private static <T extends Block> T defer(String name, T block) {
        DEFERRED.add(new AbstractMap.SimpleEntry<>(name, block));
        return block;
    }

    public static final Block AQUAMARINE_ORE = defer("aquamarine_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).mapColor(MapColor.STONE).strength(3.0F, 3.0F),
        UniformInt.of(3, 7)));

    public static final Block DEEPSLATE_AQUAMARINE_ORE = defer("deepslate_aquamarine_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F),
        UniformInt.of(3, 7)));

    public static final Block CITRINE_ORE = defer("citrine_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).mapColor(MapColor.STONE).strength(3.0F, 3.0F),
        UniformInt.of(3, 7)));

    public static final Block DEEPSLATE_CITRINE_ORE = defer("deepslate_citrine_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F),
        UniformInt.of(3, 7)));

    public static final Block TOPAZ_ORE = defer("topaz_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).mapColor(MapColor.STONE).strength(3.0F, 3.0F),
        UniformInt.of(3, 7)));

    public static final Block DEEPSLATE_TOPAZ_ORE = defer("deepslate_topaz_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F),
        UniformInt.of(3, 7)));

    public static final Block PERIDOT_ORE = defer("peridot_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).mapColor(MapColor.STONE).strength(3.0F, 3.0F),
        UniformInt.of(3, 7)));

    public static final Block DEEPSLATE_PERIDOT_ORE = defer("deepslate_peridot_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F),
        UniformInt.of(3, 7)));

    public static final Block RUBY_ORE = defer("ruby_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.NETHER_GOLD_ORE).mapColor(MapColor.NETHER).strength(3.0F, 3.0F),
        UniformInt.of(2, 5)));

    public static final Block SAPPHIRE_ORE = defer("sapphire_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.NETHER_GOLD_ORE).mapColor(MapColor.NETHER).strength(3.0F, 3.0F),
        UniformInt.of(2, 5)));

    public static final Block JADE_ORE = defer("jade_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops().strength(3.0F, 9.0F),
        UniformInt.of(3, 7)));

    public static final Block AMETRINE_ORE = defer("ametrine_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops().strength(3.0F, 9.0F),
        UniformInt.of(3, 7)));

    public static final Block NECOIUM_ORE = defer("necoium_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).mapColor(MapColor.STONE).strength(3.0F, 3.0F),
        UniformInt.of(3, 7)));

    public static final Block DEEPSLATE_NECOIUM_ORE = defer("deepslate_necoium_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F),
        UniformInt.of(3, 7)));

    public static final Block RAW_NECOIUM_BLOCK = defer("raw_necoium_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK).mapColor(MapColor.WARPED_NYLIUM).strength(5.0F, 6.0F)));

    public static final Block AQUAMARINE_BLOCK = defer("aquamarine_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(5.0F, 6.0F)));

    public static final Block CITRINE_BLOCK = defer("citrine_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_YELLOW).strength(5.0F, 6.0F)));

    public static final Block TOPAZ_BLOCK = defer("topaz_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_ORANGE).strength(5.0F, 6.0F)));

    public static final Block PERIDOT_BLOCK = defer("peridot_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(5.0F, 6.0F)));

    public static final Block RUBY_BLOCK = defer("ruby_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_RED).strength(5.0F, 6.0F)));

    public static final Block SAPPHIRE_BLOCK = defer("sapphire_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.LAPIS).strength(5.0F, 6.0F)));

    public static final Block JADE_BLOCK = defer("jade_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.PLANT).strength(5.0F, 6.0F)));

    public static final Block AMETRINE_BLOCK = defer("ametrine_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_PURPLE).strength(5.0F, 6.0F)));

    public static final Block NECOIUM_BLOCK = defer("necoium_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.WARPED_NYLIUM).strength(5.0F, 6.0F)));

    public static final Block AQUAMARINE_CRYSTAL_BLOCK = defer("aquamarine_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block AQUAMARINE_CRYSTAL_CLUSTER = defer("aquamarine_crystal_cluster", new AmethystClusterBlock(7, 3,
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block LARGE_AQUAMARINE_CRYSTAL_BUD = defer("large_aquamarine_crystal_bud", new AmethystClusterBlock(5, 3,
        BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block MEDIUM_AQUAMARINE_CRYSTAL_BUD = defer("medium_aquamarine_crystal_bud", new AmethystClusterBlock(4, 3,
        BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block SMALL_AQUAMARINE_CRYSTAL_BUD = defer("small_aquamarine_crystal_bud", new AmethystClusterBlock(3, 4,
        BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block BUDDING_AQUAMARINE_CRYSTAL = defer("budding_aquamarine_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_AQUAMARINE_CRYSTAL_BUD, () -> MEDIUM_AQUAMARINE_CRYSTAL_BUD,
        () -> LARGE_AQUAMARINE_CRYSTAL_BUD, () -> AQUAMARINE_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.copy(Blocks.BUDDING_AMETHYST).mapColor(MapColor.COLOR_LIGHT_BLUE)));

    public static final Block CITRINE_CRYSTAL_BLOCK = defer("citrine_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CITRINE_CRYSTAL_CLUSTER = defer("citrine_crystal_cluster", new AmethystClusterBlock(7, 3,
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block LARGE_CITRINE_CRYSTAL_BUD = defer("large_citrine_crystal_bud", new AmethystClusterBlock(5, 3,
        BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block MEDIUM_CITRINE_CRYSTAL_BUD = defer("medium_citrine_crystal_bud", new AmethystClusterBlock(4, 3,
        BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block SMALL_CITRINE_CRYSTAL_BUD = defer("small_citrine_crystal_bud", new AmethystClusterBlock(3, 4,
        BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block BUDDING_CITRINE_CRYSTAL = defer("budding_citrine_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_CITRINE_CRYSTAL_BUD, () -> MEDIUM_CITRINE_CRYSTAL_BUD,
        () -> LARGE_CITRINE_CRYSTAL_BUD, () -> CITRINE_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.copy(Blocks.BUDDING_AMETHYST).mapColor(MapColor.COLOR_YELLOW)));

    public static final Block TOPAZ_CRYSTAL_BLOCK = defer("topaz_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block TOPAZ_CRYSTAL_CLUSTER = defer("topaz_crystal_cluster", new AmethystClusterBlock(7, 3,
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block LARGE_TOPAZ_CRYSTAL_BUD = defer("large_topaz_crystal_bud", new AmethystClusterBlock(5, 3,
        BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block MEDIUM_TOPAZ_CRYSTAL_BUD = defer("medium_topaz_crystal_bud", new AmethystClusterBlock(4, 3,
        BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block SMALL_TOPAZ_CRYSTAL_BUD = defer("small_topaz_crystal_bud", new AmethystClusterBlock(3, 4,
        BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block BUDDING_TOPAZ_CRYSTAL = defer("budding_topaz_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_TOPAZ_CRYSTAL_BUD, () -> MEDIUM_TOPAZ_CRYSTAL_BUD,
        () -> LARGE_TOPAZ_CRYSTAL_BUD, () -> TOPAZ_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.copy(Blocks.BUDDING_AMETHYST).mapColor(MapColor.COLOR_ORANGE)));

    public static final Block PERIDOT_CRYSTAL_BLOCK = defer("peridot_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block PERIDOT_CRYSTAL_CLUSTER = defer("peridot_crystal_cluster", new AmethystClusterBlock(7, 3,
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block LARGE_PERIDOT_CRYSTAL_BUD = defer("large_peridot_crystal_bud", new AmethystClusterBlock(5, 3,
        BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block MEDIUM_PERIDOT_CRYSTAL_BUD = defer("medium_peridot_crystal_bud", new AmethystClusterBlock(4, 3,
        BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block SMALL_PERIDOT_CRYSTAL_BUD = defer("small_peridot_crystal_bud", new AmethystClusterBlock(3, 4,
        BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block BUDDING_PERIDOT_CRYSTAL = defer("budding_peridot_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_PERIDOT_CRYSTAL_BUD, () -> MEDIUM_PERIDOT_CRYSTAL_BUD,
        () -> LARGE_PERIDOT_CRYSTAL_BUD, () -> PERIDOT_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.copy(Blocks.BUDDING_AMETHYST).mapColor(MapColor.COLOR_LIGHT_GREEN)));

    public static final Block RUBY_CRYSTAL_BLOCK = defer("ruby_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_RED)));
    public static final Block RUBY_CRYSTAL_CLUSTER = defer("ruby_crystal_cluster", new AmethystClusterBlock(7, 3,
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).mapColor(MapColor.COLOR_RED)));
    public static final Block LARGE_RUBY_CRYSTAL_BUD = defer("large_ruby_crystal_bud", new AmethystClusterBlock(5, 3,
        BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).mapColor(MapColor.COLOR_RED)));
    public static final Block MEDIUM_RUBY_CRYSTAL_BUD = defer("medium_ruby_crystal_bud", new AmethystClusterBlock(4, 3,
        BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).mapColor(MapColor.COLOR_RED)));
    public static final Block SMALL_RUBY_CRYSTAL_BUD = defer("small_ruby_crystal_bud", new AmethystClusterBlock(3, 4,
        BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).mapColor(MapColor.COLOR_RED)));
    public static final Block BUDDING_RUBY_CRYSTAL = defer("budding_ruby_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_RUBY_CRYSTAL_BUD, () -> MEDIUM_RUBY_CRYSTAL_BUD,
        () -> LARGE_RUBY_CRYSTAL_BUD, () -> RUBY_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.copy(Blocks.BUDDING_AMETHYST).mapColor(MapColor.COLOR_RED)));

    public static final Block SAPPHIRE_CRYSTAL_BLOCK = defer("sapphire_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.LAPIS)));
    public static final Block SAPPHIRE_CRYSTAL_CLUSTER = defer("sapphire_crystal_cluster", new AmethystClusterBlock(7, 3,
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).mapColor(MapColor.LAPIS)));
    public static final Block LARGE_SAPPHIRE_CRYSTAL_BUD = defer("large_sapphire_crystal_bud", new AmethystClusterBlock(5, 3,
        BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).mapColor(MapColor.LAPIS)));
    public static final Block MEDIUM_SAPPHIRE_CRYSTAL_BUD = defer("medium_sapphire_crystal_bud", new AmethystClusterBlock(4, 3,
        BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).mapColor(MapColor.LAPIS)));
    public static final Block SMALL_SAPPHIRE_CRYSTAL_BUD = defer("small_sapphire_crystal_bud", new AmethystClusterBlock(3, 4,
        BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).mapColor(MapColor.LAPIS)));
    public static final Block BUDDING_SAPPHIRE_CRYSTAL = defer("budding_sapphire_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_SAPPHIRE_CRYSTAL_BUD, () -> MEDIUM_SAPPHIRE_CRYSTAL_BUD,
        () -> LARGE_SAPPHIRE_CRYSTAL_BUD, () -> SAPPHIRE_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.copy(Blocks.BUDDING_AMETHYST).mapColor(MapColor.LAPIS)));

    public static final Block JADE_CRYSTAL_BLOCK = defer("jade_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.PLANT)));
    public static final Block JADE_CRYSTAL_CLUSTER = defer("jade_crystal_cluster", new AmethystClusterBlock(7, 3,
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).mapColor(MapColor.PLANT)));
    public static final Block LARGE_JADE_CRYSTAL_BUD = defer("large_jade_crystal_bud", new AmethystClusterBlock(5, 3,
        BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).mapColor(MapColor.PLANT)));
    public static final Block MEDIUM_JADE_CRYSTAL_BUD = defer("medium_jade_crystal_bud", new AmethystClusterBlock(4, 3,
        BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).mapColor(MapColor.PLANT)));
    public static final Block SMALL_JADE_CRYSTAL_BUD = defer("small_jade_crystal_bud", new AmethystClusterBlock(3, 4,
        BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).mapColor(MapColor.PLANT)));
    public static final Block BUDDING_JADE_CRYSTAL = defer("budding_jade_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_JADE_CRYSTAL_BUD, () -> MEDIUM_JADE_CRYSTAL_BUD,
        () -> LARGE_JADE_CRYSTAL_BUD, () -> JADE_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.copy(Blocks.BUDDING_AMETHYST).mapColor(MapColor.PLANT)));

    public static final Block AMETRINE_CRYSTAL_BLOCK = defer("ametrine_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block AMETRINE_CRYSTAL_CLUSTER = defer("ametrine_crystal_cluster", new AmethystClusterBlock(7, 3,
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block LARGE_AMETRINE_CRYSTAL_BUD = defer("large_ametrine_crystal_bud", new AmethystClusterBlock(5, 3,
        BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block MEDIUM_AMETRINE_CRYSTAL_BUD = defer("medium_ametrine_crystal_bud", new AmethystClusterBlock(4, 3,
        BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block SMALL_AMETRINE_CRYSTAL_BUD = defer("small_ametrine_crystal_bud", new AmethystClusterBlock(3, 4,
        BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block BUDDING_AMETRINE_CRYSTAL = defer("budding_ametrine_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_AMETRINE_CRYSTAL_BUD, () -> MEDIUM_AMETRINE_CRYSTAL_BUD,
        () -> LARGE_AMETRINE_CRYSTAL_BUD, () -> AMETRINE_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.copy(Blocks.BUDDING_AMETHYST).mapColor(MapColor.COLOR_PURPLE)));

    public static final Block AQUAMARINE_CRYSTAL_BLOCK_SLAB = defer("aquamarine_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block AQUAMARINE_CRYSTAL_BLOCK_STAIRS = defer("aquamarine_crystal_block_stairs", new MythicStairBlock(
        AQUAMARINE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block POLISHED_AQUAMARINE_CRYSTAL_BLOCK = defer("polished_aquamarine_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block POLISHED_AQUAMARINE_CRYSTAL_BLOCK_SLAB = defer("polished_aquamarine_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block POLISHED_AQUAMARINE_CRYSTAL_BLOCK_STAIRS = defer("polished_aquamarine_crystal_block_stairs", new MythicStairBlock(
        POLISHED_AQUAMARINE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block AQUAMARINE_CRYSTAL_BRICKS = defer("aquamarine_crystal_bricks", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block AQUAMARINE_CRYSTAL_BRICKS_SLAB = defer("aquamarine_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block AQUAMARINE_CRYSTAL_BRICKS_STAIRS = defer("aquamarine_crystal_bricks_stairs", new MythicStairBlock(
        AQUAMARINE_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block AQUAMARINE_CRYSTAL_PILLAR = defer("aquamarine_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block CUT_AQUAMARINE_CRYSTAL_PILLAR = defer("cut_aquamarine_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE)));

    public static final Block CITRINE_CRYSTAL_BLOCK_SLAB = defer("citrine_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CITRINE_CRYSTAL_BLOCK_STAIRS = defer("citrine_crystal_block_stairs", new MythicStairBlock(
        CITRINE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block POLISHED_CITRINE_CRYSTAL_BLOCK = defer("polished_citrine_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block POLISHED_CITRINE_CRYSTAL_BLOCK_SLAB = defer("polished_citrine_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block POLISHED_CITRINE_CRYSTAL_BLOCK_STAIRS = defer("polished_citrine_crystal_block_stairs", new MythicStairBlock(
        POLISHED_CITRINE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CITRINE_CRYSTAL_BRICKS = defer("citrine_crystal_bricks", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CITRINE_CRYSTAL_BRICKS_SLAB = defer("citrine_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CITRINE_CRYSTAL_BRICKS_STAIRS = defer("citrine_crystal_bricks_stairs", new MythicStairBlock(
        CITRINE_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CITRINE_CRYSTAL_PILLAR = defer("citrine_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CUT_CITRINE_CRYSTAL_PILLAR = defer("cut_citrine_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_YELLOW)));

    public static final Block TOPAZ_CRYSTAL_BLOCK_SLAB = defer("topaz_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block TOPAZ_CRYSTAL_BLOCK_STAIRS = defer("topaz_crystal_block_stairs", new MythicStairBlock(
        TOPAZ_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block POLISHED_TOPAZ_CRYSTAL_BLOCK = defer("polished_topaz_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block POLISHED_TOPAZ_CRYSTAL_BLOCK_SLAB = defer("polished_topaz_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block POLISHED_TOPAZ_CRYSTAL_BLOCK_STAIRS = defer("polished_topaz_crystal_block_stairs", new MythicStairBlock(
        POLISHED_TOPAZ_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block TOPAZ_CRYSTAL_BRICKS = defer("topaz_crystal_bricks", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block TOPAZ_CRYSTAL_BRICKS_SLAB = defer("topaz_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block TOPAZ_CRYSTAL_BRICKS_STAIRS = defer("topaz_crystal_bricks_stairs", new MythicStairBlock(
        TOPAZ_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block TOPAZ_CRYSTAL_PILLAR = defer("topaz_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block CUT_TOPAZ_CRYSTAL_PILLAR = defer("cut_topaz_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_ORANGE)));

    public static final Block PERIDOT_CRYSTAL_BLOCK_SLAB = defer("peridot_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block PERIDOT_CRYSTAL_BLOCK_STAIRS = defer("peridot_crystal_block_stairs", new MythicStairBlock(
        PERIDOT_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block POLISHED_PERIDOT_CRYSTAL_BLOCK = defer("polished_peridot_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block POLISHED_PERIDOT_CRYSTAL_BLOCK_SLAB = defer("polished_peridot_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block POLISHED_PERIDOT_CRYSTAL_BLOCK_STAIRS = defer("polished_peridot_crystal_block_stairs", new MythicStairBlock(
        POLISHED_PERIDOT_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block PERIDOT_CRYSTAL_BRICKS = defer("peridot_crystal_bricks", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block PERIDOT_CRYSTAL_BRICKS_SLAB = defer("peridot_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block PERIDOT_CRYSTAL_BRICKS_STAIRS = defer("peridot_crystal_bricks_stairs", new MythicStairBlock(
        PERIDOT_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block PERIDOT_CRYSTAL_PILLAR = defer("peridot_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block CUT_PERIDOT_CRYSTAL_PILLAR = defer("cut_peridot_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN)));

    public static final Block RUBY_CRYSTAL_BLOCK_SLAB = defer("ruby_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_RED)));
    public static final Block RUBY_CRYSTAL_BLOCK_STAIRS = defer("ruby_crystal_block_stairs", new MythicStairBlock(
        RUBY_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_RED)));
    public static final Block POLISHED_RUBY_CRYSTAL_BLOCK = defer("polished_ruby_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_RED)));
    public static final Block POLISHED_RUBY_CRYSTAL_BLOCK_SLAB = defer("polished_ruby_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_RED)));
    public static final Block POLISHED_RUBY_CRYSTAL_BLOCK_STAIRS = defer("polished_ruby_crystal_block_stairs", new MythicStairBlock(
        POLISHED_RUBY_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_RED)));
    public static final Block RUBY_CRYSTAL_BRICKS = defer("ruby_crystal_bricks", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_RED)));
    public static final Block RUBY_CRYSTAL_BRICKS_SLAB = defer("ruby_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_RED)));
    public static final Block RUBY_CRYSTAL_BRICKS_STAIRS = defer("ruby_crystal_bricks_stairs", new MythicStairBlock(
        RUBY_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_RED)));
    public static final Block RUBY_CRYSTAL_PILLAR = defer("ruby_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_RED)));
    public static final Block CUT_RUBY_CRYSTAL_PILLAR = defer("cut_ruby_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_RED)));

    public static final Block SAPPHIRE_CRYSTAL_BLOCK_SLAB = defer("sapphire_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.LAPIS)));
    public static final Block SAPPHIRE_CRYSTAL_BLOCK_STAIRS = defer("sapphire_crystal_block_stairs", new MythicStairBlock(
        SAPPHIRE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.LAPIS)));
    public static final Block POLISHED_SAPPHIRE_CRYSTAL_BLOCK = defer("polished_sapphire_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.LAPIS)));
    public static final Block POLISHED_SAPPHIRE_CRYSTAL_BLOCK_SLAB = defer("polished_sapphire_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.LAPIS)));
    public static final Block POLISHED_SAPPHIRE_CRYSTAL_BLOCK_STAIRS = defer("polished_sapphire_crystal_block_stairs", new MythicStairBlock(
        POLISHED_SAPPHIRE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.LAPIS)));
    public static final Block SAPPHIRE_CRYSTAL_BRICKS = defer("sapphire_crystal_bricks", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.LAPIS)));
    public static final Block SAPPHIRE_CRYSTAL_BRICKS_SLAB = defer("sapphire_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.LAPIS)));
    public static final Block SAPPHIRE_CRYSTAL_BRICKS_STAIRS = defer("sapphire_crystal_bricks_stairs", new MythicStairBlock(
        SAPPHIRE_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.LAPIS)));
    public static final Block SAPPHIRE_CRYSTAL_PILLAR = defer("sapphire_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.LAPIS)));
    public static final Block CUT_SAPPHIRE_CRYSTAL_PILLAR = defer("cut_sapphire_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.LAPIS)));

    public static final Block JADE_CRYSTAL_BLOCK_SLAB = defer("jade_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.PLANT)));
    public static final Block JADE_CRYSTAL_BLOCK_STAIRS = defer("jade_crystal_block_stairs", new MythicStairBlock(
        JADE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.PLANT)));
    public static final Block POLISHED_JADE_CRYSTAL_BLOCK = defer("polished_jade_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.PLANT)));
    public static final Block POLISHED_JADE_CRYSTAL_BLOCK_SLAB = defer("polished_jade_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.PLANT)));
    public static final Block POLISHED_JADE_CRYSTAL_BLOCK_STAIRS = defer("polished_jade_crystal_block_stairs", new MythicStairBlock(
        POLISHED_JADE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.PLANT)));
    public static final Block JADE_CRYSTAL_BRICKS = defer("jade_crystal_bricks", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.PLANT)));
    public static final Block JADE_CRYSTAL_BRICKS_SLAB = defer("jade_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.PLANT)));
    public static final Block JADE_CRYSTAL_BRICKS_STAIRS = defer("jade_crystal_bricks_stairs", new MythicStairBlock(
        JADE_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.PLANT)));
    public static final Block JADE_CRYSTAL_PILLAR = defer("jade_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.PLANT)));
    public static final Block CUT_JADE_CRYSTAL_PILLAR = defer("cut_jade_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.PLANT)));

    public static final Block AMETRINE_CRYSTAL_BLOCK_SLAB = defer("ametrine_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block AMETRINE_CRYSTAL_BLOCK_STAIRS = defer("ametrine_crystal_block_stairs", new MythicStairBlock(
        AMETRINE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block POLISHED_AMETRINE_CRYSTAL_BLOCK = defer("polished_ametrine_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block POLISHED_AMETRINE_CRYSTAL_BLOCK_SLAB = defer("polished_ametrine_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block POLISHED_AMETRINE_CRYSTAL_BLOCK_STAIRS = defer("polished_ametrine_crystal_block_stairs", new MythicStairBlock(
        POLISHED_AMETRINE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block AMETRINE_CRYSTAL_BRICKS = defer("ametrine_crystal_bricks", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block AMETRINE_CRYSTAL_BRICKS_SLAB = defer("ametrine_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block AMETRINE_CRYSTAL_BRICKS_STAIRS = defer("ametrine_crystal_bricks_stairs", new MythicStairBlock(
        AMETRINE_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block AMETRINE_CRYSTAL_PILLAR = defer("ametrine_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block CUT_AMETRINE_CRYSTAL_PILLAR = defer("cut_ametrine_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PURPLE)));

    public static final Block AQUAMARINE_STONE = defer("aquamarine_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));
    public static final Block AQUAMARINE_STONE_SLAB = defer("aquamarine_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));
    public static final Block AQUAMARINE_STONE_STAIRS = defer("aquamarine_stone_stairs", new MythicStairBlock(
        AQUAMARINE_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_AQUAMARINE_STONE = defer("polished_aquamarine_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_AQUAMARINE_STONE_SLAB = defer("polished_aquamarine_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_AQUAMARINE_STONE_STAIRS = defer("polished_aquamarine_stone_stairs", new MythicStairBlock(
        POLISHED_AQUAMARINE_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));

    public static final Block CITRINE_STONE = defer("citrine_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));
    public static final Block CITRINE_STONE_SLAB = defer("citrine_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));
    public static final Block CITRINE_STONE_STAIRS = defer("citrine_stone_stairs", new MythicStairBlock(
        CITRINE_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));
    public static final Block POLISHED_CITRINE_STONE = defer("polished_citrine_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));
    public static final Block POLISHED_CITRINE_STONE_SLAB = defer("polished_citrine_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));
    public static final Block POLISHED_CITRINE_STONE_STAIRS = defer("polished_citrine_stone_stairs", new MythicStairBlock(
        POLISHED_CITRINE_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));

    public static final Block TOPAZ_STONE = defer("topaz_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));
    public static final Block TOPAZ_STONE_SLAB = defer("topaz_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));
    public static final Block TOPAZ_STONE_STAIRS = defer("topaz_stone_stairs", new MythicStairBlock(
        TOPAZ_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_TOPAZ_STONE = defer("polished_topaz_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_TOPAZ_STONE_SLAB = defer("polished_topaz_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_TOPAZ_STONE_STAIRS = defer("polished_topaz_stone_stairs", new MythicStairBlock(
        POLISHED_TOPAZ_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));

    public static final Block PERIDOT_STONE = defer("peridot_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));
    public static final Block PERIDOT_STONE_SLAB = defer("peridot_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));
    public static final Block PERIDOT_STONE_STAIRS = defer("peridot_stone_stairs", new MythicStairBlock(
        PERIDOT_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));
    public static final Block POLISHED_PERIDOT_STONE = defer("polished_peridot_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));
    public static final Block POLISHED_PERIDOT_STONE_SLAB = defer("polished_peridot_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));
    public static final Block POLISHED_PERIDOT_STONE_STAIRS = defer("polished_peridot_stone_stairs", new MythicStairBlock(
        POLISHED_PERIDOT_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));

    public static final Block RUBY_STONE = defer("ruby_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));
    public static final Block RUBY_STONE_SLAB = defer("ruby_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));
    public static final Block RUBY_STONE_STAIRS = defer("ruby_stone_stairs", new MythicStairBlock(
        RUBY_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));
    public static final Block POLISHED_RUBY_STONE = defer("polished_ruby_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));
    public static final Block POLISHED_RUBY_STONE_SLAB = defer("polished_ruby_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));
    public static final Block POLISHED_RUBY_STONE_STAIRS = defer("polished_ruby_stone_stairs", new MythicStairBlock(
        POLISHED_RUBY_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));

    public static final Block SAPPHIRE_STONE = defer("sapphire_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.LAPIS).strength(1.5F, 6.0F)));
    public static final Block SAPPHIRE_STONE_SLAB = defer("sapphire_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.LAPIS).strength(1.5F, 6.0F)));
    public static final Block SAPPHIRE_STONE_STAIRS = defer("sapphire_stone_stairs", new MythicStairBlock(
        SAPPHIRE_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.LAPIS).strength(1.5F, 6.0F)));
    public static final Block POLISHED_SAPPHIRE_STONE = defer("polished_sapphire_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.LAPIS).strength(1.5F, 6.0F)));
    public static final Block POLISHED_SAPPHIRE_STONE_SLAB = defer("polished_sapphire_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.LAPIS).strength(1.5F, 6.0F)));
    public static final Block POLISHED_SAPPHIRE_STONE_STAIRS = defer("polished_sapphire_stone_stairs", new MythicStairBlock(
        POLISHED_SAPPHIRE_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.LAPIS).strength(1.5F, 6.0F)));

    public static final Block JADE_STONE = defer("jade_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.PLANT).strength(1.5F, 6.0F)));
    public static final Block JADE_STONE_SLAB = defer("jade_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.PLANT).strength(1.5F, 6.0F)));
    public static final Block JADE_STONE_STAIRS = defer("jade_stone_stairs", new MythicStairBlock(
        JADE_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.PLANT).strength(1.5F, 6.0F)));
    public static final Block POLISHED_JADE_STONE = defer("polished_jade_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.PLANT).strength(1.5F, 6.0F)));
    public static final Block POLISHED_JADE_STONE_SLAB = defer("polished_jade_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.PLANT).strength(1.5F, 6.0F)));
    public static final Block POLISHED_JADE_STONE_STAIRS = defer("polished_jade_stone_stairs", new MythicStairBlock(
        POLISHED_JADE_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.PLANT).strength(1.5F, 6.0F)));

    public static final Block AMETRINE_STONE = defer("ametrine_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));
    public static final Block AMETRINE_STONE_SLAB = defer("ametrine_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));
    public static final Block AMETRINE_STONE_STAIRS = defer("ametrine_stone_stairs", new MythicStairBlock(
        AMETRINE_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_AMETRINE_STONE = defer("polished_ametrine_stone", new Block(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_AMETRINE_STONE_SLAB = defer("polished_ametrine_stone_slab", new SlabBlock(
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_AMETRINE_STONE_STAIRS = defer("polished_ametrine_stone_stairs", new MythicStairBlock(
        POLISHED_AMETRINE_STONE.defaultBlockState(),
        BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));

    public static void register(BiFunction<String, Block, Block> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
        Constants.LOG.info("MythicBlocks registered.");
    }

    public static void registerItems(BiFunction<String, Item, Item> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), new BlockItem(e.getValue(), new Item.Properties())));
    }
}
