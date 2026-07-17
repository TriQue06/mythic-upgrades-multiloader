package net.trique.mythicupgrades.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.trique.mythicupgrades.Constants;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class MythicBlocks {

    private static final List<Map.Entry<String, Block>> DEFERRED = new ArrayList<>();

    private static ResourceKey<Block> key(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    private static <T extends Block> T defer(String name, T block) {
        DEFERRED.add(new AbstractMap.SimpleEntry<>(name, block));
        return block;
    }

    public static final Block AQUAMARINE_ORE = defer("aquamarine_ore", new DropExperienceBlock(
        UniformInt.of(6, 14),
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).setId(key("aquamarine_ore")).mapColor(MapColor.STONE).strength(3.0F, 3.0F)));

    public static final Block DEEPSLATE_AQUAMARINE_ORE = defer("deepslate_aquamarine_ore", new DropExperienceBlock(
        UniformInt.of(6, 14),
        BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).setId(key("deepslate_aquamarine_ore")).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F)));

    public static final Block CITRINE_ORE = defer("citrine_ore", new DropExperienceBlock(
        UniformInt.of(6, 14),
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).setId(key("citrine_ore")).mapColor(MapColor.STONE).strength(3.0F, 3.0F)));

    public static final Block DEEPSLATE_CITRINE_ORE = defer("deepslate_citrine_ore", new DropExperienceBlock(
        UniformInt.of(6, 14),
        BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).setId(key("deepslate_citrine_ore")).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F)));

    public static final Block TOPAZ_ORE = defer("topaz_ore", new DropExperienceBlock(
        UniformInt.of(6, 14),
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).setId(key("topaz_ore")).mapColor(MapColor.STONE).strength(3.0F, 3.0F)));

    public static final Block DEEPSLATE_TOPAZ_ORE = defer("deepslate_topaz_ore", new DropExperienceBlock(
        UniformInt.of(6, 14),
        BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).setId(key("deepslate_topaz_ore")).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F)));

    public static final Block PERIDOT_ORE = defer("peridot_ore", new DropExperienceBlock(
        UniformInt.of(6, 14),
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).setId(key("peridot_ore")).mapColor(MapColor.STONE).strength(3.0F, 3.0F)));

    public static final Block DEEPSLATE_PERIDOT_ORE = defer("deepslate_peridot_ore", new DropExperienceBlock(
        UniformInt.of(6, 14),
        BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).setId(key("deepslate_peridot_ore")).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F)));

    public static final Block RUBY_ORE = defer("ruby_ore", new DropExperienceBlock(
        UniformInt.of(4, 10),
        BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_GOLD_ORE).setId(key("ruby_ore")).mapColor(MapColor.NETHER).strength(3.0F, 3.0F)));

    public static final Block SAPPHIRE_ORE = defer("sapphire_ore", new DropExperienceBlock(
        UniformInt.of(4, 10),
        BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_GOLD_ORE).setId(key("sapphire_ore")).mapColor(MapColor.NETHER).strength(3.0F, 3.0F)));

    public static final Block JADE_ORE = defer("jade_ore", new DropExperienceBlock(
        UniformInt.of(6, 14),
        BlockBehaviour.Properties.of().setId(key("jade_ore")).mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops().strength(3.0F, 9.0F)));

    public static final Block AMETRINE_ORE = defer("ametrine_ore", new DropExperienceBlock(
        UniformInt.of(6, 14),
        BlockBehaviour.Properties.of().setId(key("ametrine_ore")).mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops().strength(3.0F, 9.0F)));

    public static final Block NECOIUM_ORE = defer("necoium_ore", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE).setId(key("necoium_ore")).mapColor(MapColor.STONE).strength(3.0F, 3.0F)));

    public static final Block DEEPSLATE_NECOIUM_ORE = defer("deepslate_necoium_ore", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).setId(key("deepslate_necoium_ore")).mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F)));

    public static final Block RAW_NECOIUM_BLOCK = defer("raw_necoium_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_GOLD_BLOCK).setId(key("raw_necoium_block")).mapColor(MapColor.WARPED_NYLIUM).strength(5.0F, 6.0F)));

    public static final Block AQUAMARINE_BLOCK = defer("aquamarine_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).setId(key("aquamarine_block")).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(5.0F, 6.0F)));

    public static final Block CITRINE_BLOCK = defer("citrine_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).setId(key("citrine_block")).mapColor(MapColor.COLOR_YELLOW).strength(5.0F, 6.0F)));

    public static final Block TOPAZ_BLOCK = defer("topaz_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).setId(key("topaz_block")).mapColor(MapColor.COLOR_ORANGE).strength(5.0F, 6.0F)));

    public static final Block PERIDOT_BLOCK = defer("peridot_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).setId(key("peridot_block")).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(5.0F, 6.0F)));

    public static final Block RUBY_BLOCK = defer("ruby_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).setId(key("ruby_block")).mapColor(MapColor.COLOR_RED).strength(5.0F, 6.0F)));

    public static final Block SAPPHIRE_BLOCK = defer("sapphire_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).setId(key("sapphire_block")).mapColor(MapColor.LAPIS).strength(5.0F, 6.0F)));

    public static final Block JADE_BLOCK = defer("jade_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).setId(key("jade_block")).mapColor(MapColor.PLANT).strength(5.0F, 6.0F)));

    public static final Block AMETRINE_BLOCK = defer("ametrine_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).setId(key("ametrine_block")).mapColor(MapColor.COLOR_PURPLE).strength(5.0F, 6.0F)));

    public static final Block NECOIUM_BLOCK = defer("necoium_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).setId(key("necoium_block")).mapColor(MapColor.WARPED_NYLIUM).strength(5.0F, 6.0F)));

    public static final Block AQUAMARINE_CRYSTAL_BLOCK = defer("aquamarine_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("aquamarine_crystal_block")).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block AQUAMARINE_CRYSTAL_CLUSTER = defer("aquamarine_crystal_cluster", new AmethystClusterBlock(7, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).setId(key("aquamarine_crystal_cluster")).lightLevel(state -> 6).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block LARGE_AQUAMARINE_CRYSTAL_BUD = defer("large_aquamarine_crystal_bud", new AmethystClusterBlock(5, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD).setId(key("large_aquamarine_crystal_bud")).lightLevel(state -> 5).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block MEDIUM_AQUAMARINE_CRYSTAL_BUD = defer("medium_aquamarine_crystal_bud", new AmethystClusterBlock(4, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.MEDIUM_AMETHYST_BUD).setId(key("medium_aquamarine_crystal_bud")).lightLevel(state -> 3).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block SMALL_AQUAMARINE_CRYSTAL_BUD = defer("small_aquamarine_crystal_bud", new AmethystClusterBlock(3, 8,
        BlockBehaviour.Properties.ofFullCopy(Blocks.SMALL_AMETHYST_BUD).setId(key("small_aquamarine_crystal_bud")).lightLevel(state -> 2).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block BUDDING_AQUAMARINE_CRYSTAL = defer("budding_aquamarine_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_AQUAMARINE_CRYSTAL_BUD, () -> MEDIUM_AQUAMARINE_CRYSTAL_BUD,
        () -> LARGE_AQUAMARINE_CRYSTAL_BUD, () -> AQUAMARINE_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST).setId(key("budding_aquamarine_crystal")).mapColor(MapColor.COLOR_LIGHT_BLUE)));

    public static final Block CITRINE_CRYSTAL_BLOCK = defer("citrine_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("citrine_crystal_block")).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CITRINE_CRYSTAL_CLUSTER = defer("citrine_crystal_cluster", new AmethystClusterBlock(7, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).setId(key("citrine_crystal_cluster")).lightLevel(state -> 6).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block LARGE_CITRINE_CRYSTAL_BUD = defer("large_citrine_crystal_bud", new AmethystClusterBlock(5, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD).setId(key("large_citrine_crystal_bud")).lightLevel(state -> 5).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block MEDIUM_CITRINE_CRYSTAL_BUD = defer("medium_citrine_crystal_bud", new AmethystClusterBlock(4, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.MEDIUM_AMETHYST_BUD).setId(key("medium_citrine_crystal_bud")).lightLevel(state -> 3).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block SMALL_CITRINE_CRYSTAL_BUD = defer("small_citrine_crystal_bud", new AmethystClusterBlock(3, 8,
        BlockBehaviour.Properties.ofFullCopy(Blocks.SMALL_AMETHYST_BUD).setId(key("small_citrine_crystal_bud")).lightLevel(state -> 2).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block BUDDING_CITRINE_CRYSTAL = defer("budding_citrine_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_CITRINE_CRYSTAL_BUD, () -> MEDIUM_CITRINE_CRYSTAL_BUD,
        () -> LARGE_CITRINE_CRYSTAL_BUD, () -> CITRINE_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST).setId(key("budding_citrine_crystal")).mapColor(MapColor.COLOR_YELLOW)));

    public static final Block TOPAZ_CRYSTAL_BLOCK = defer("topaz_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("topaz_crystal_block")).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block TOPAZ_CRYSTAL_CLUSTER = defer("topaz_crystal_cluster", new AmethystClusterBlock(7, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).setId(key("topaz_crystal_cluster")).lightLevel(state -> 6).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block LARGE_TOPAZ_CRYSTAL_BUD = defer("large_topaz_crystal_bud", new AmethystClusterBlock(5, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD).setId(key("large_topaz_crystal_bud")).lightLevel(state -> 5).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block MEDIUM_TOPAZ_CRYSTAL_BUD = defer("medium_topaz_crystal_bud", new AmethystClusterBlock(4, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.MEDIUM_AMETHYST_BUD).setId(key("medium_topaz_crystal_bud")).lightLevel(state -> 3).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block SMALL_TOPAZ_CRYSTAL_BUD = defer("small_topaz_crystal_bud", new AmethystClusterBlock(3, 8,
        BlockBehaviour.Properties.ofFullCopy(Blocks.SMALL_AMETHYST_BUD).setId(key("small_topaz_crystal_bud")).lightLevel(state -> 2).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block BUDDING_TOPAZ_CRYSTAL = defer("budding_topaz_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_TOPAZ_CRYSTAL_BUD, () -> MEDIUM_TOPAZ_CRYSTAL_BUD,
        () -> LARGE_TOPAZ_CRYSTAL_BUD, () -> TOPAZ_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST).setId(key("budding_topaz_crystal")).mapColor(MapColor.COLOR_ORANGE)));

    public static final Block PERIDOT_CRYSTAL_BLOCK = defer("peridot_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("peridot_crystal_block")).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block PERIDOT_CRYSTAL_CLUSTER = defer("peridot_crystal_cluster", new AmethystClusterBlock(7, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).setId(key("peridot_crystal_cluster")).lightLevel(state -> 6).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block LARGE_PERIDOT_CRYSTAL_BUD = defer("large_peridot_crystal_bud", new AmethystClusterBlock(5, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD).setId(key("large_peridot_crystal_bud")).lightLevel(state -> 5).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block MEDIUM_PERIDOT_CRYSTAL_BUD = defer("medium_peridot_crystal_bud", new AmethystClusterBlock(4, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.MEDIUM_AMETHYST_BUD).setId(key("medium_peridot_crystal_bud")).lightLevel(state -> 3).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block SMALL_PERIDOT_CRYSTAL_BUD = defer("small_peridot_crystal_bud", new AmethystClusterBlock(3, 8,
        BlockBehaviour.Properties.ofFullCopy(Blocks.SMALL_AMETHYST_BUD).setId(key("small_peridot_crystal_bud")).lightLevel(state -> 2).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block BUDDING_PERIDOT_CRYSTAL = defer("budding_peridot_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_PERIDOT_CRYSTAL_BUD, () -> MEDIUM_PERIDOT_CRYSTAL_BUD,
        () -> LARGE_PERIDOT_CRYSTAL_BUD, () -> PERIDOT_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST).setId(key("budding_peridot_crystal")).mapColor(MapColor.COLOR_LIGHT_GREEN)));

    public static final Block RUBY_CRYSTAL_BLOCK = defer("ruby_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ruby_crystal_block")).mapColor(MapColor.COLOR_RED)));
    public static final Block RUBY_CRYSTAL_CLUSTER = defer("ruby_crystal_cluster", new AmethystClusterBlock(7, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).setId(key("ruby_crystal_cluster")).lightLevel(state -> 6).mapColor(MapColor.COLOR_RED)));
    public static final Block LARGE_RUBY_CRYSTAL_BUD = defer("large_ruby_crystal_bud", new AmethystClusterBlock(5, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD).setId(key("large_ruby_crystal_bud")).lightLevel(state -> 5).mapColor(MapColor.COLOR_RED)));
    public static final Block MEDIUM_RUBY_CRYSTAL_BUD = defer("medium_ruby_crystal_bud", new AmethystClusterBlock(4, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.MEDIUM_AMETHYST_BUD).setId(key("medium_ruby_crystal_bud")).lightLevel(state -> 3).mapColor(MapColor.COLOR_RED)));
    public static final Block SMALL_RUBY_CRYSTAL_BUD = defer("small_ruby_crystal_bud", new AmethystClusterBlock(3, 8,
        BlockBehaviour.Properties.ofFullCopy(Blocks.SMALL_AMETHYST_BUD).setId(key("small_ruby_crystal_bud")).lightLevel(state -> 2).mapColor(MapColor.COLOR_RED)));
    public static final Block BUDDING_RUBY_CRYSTAL = defer("budding_ruby_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_RUBY_CRYSTAL_BUD, () -> MEDIUM_RUBY_CRYSTAL_BUD,
        () -> LARGE_RUBY_CRYSTAL_BUD, () -> RUBY_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST).setId(key("budding_ruby_crystal")).mapColor(MapColor.COLOR_RED)));

    public static final Block SAPPHIRE_CRYSTAL_BLOCK = defer("sapphire_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("sapphire_crystal_block")).mapColor(MapColor.LAPIS)));
    public static final Block SAPPHIRE_CRYSTAL_CLUSTER = defer("sapphire_crystal_cluster", new AmethystClusterBlock(7, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).setId(key("sapphire_crystal_cluster")).lightLevel(state -> 6).mapColor(MapColor.LAPIS)));
    public static final Block LARGE_SAPPHIRE_CRYSTAL_BUD = defer("large_sapphire_crystal_bud", new AmethystClusterBlock(5, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD).setId(key("large_sapphire_crystal_bud")).lightLevel(state -> 5).mapColor(MapColor.LAPIS)));
    public static final Block MEDIUM_SAPPHIRE_CRYSTAL_BUD = defer("medium_sapphire_crystal_bud", new AmethystClusterBlock(4, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.MEDIUM_AMETHYST_BUD).setId(key("medium_sapphire_crystal_bud")).lightLevel(state -> 3).mapColor(MapColor.LAPIS)));
    public static final Block SMALL_SAPPHIRE_CRYSTAL_BUD = defer("small_sapphire_crystal_bud", new AmethystClusterBlock(3, 8,
        BlockBehaviour.Properties.ofFullCopy(Blocks.SMALL_AMETHYST_BUD).setId(key("small_sapphire_crystal_bud")).lightLevel(state -> 2).mapColor(MapColor.LAPIS)));
    public static final Block BUDDING_SAPPHIRE_CRYSTAL = defer("budding_sapphire_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_SAPPHIRE_CRYSTAL_BUD, () -> MEDIUM_SAPPHIRE_CRYSTAL_BUD,
        () -> LARGE_SAPPHIRE_CRYSTAL_BUD, () -> SAPPHIRE_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST).setId(key("budding_sapphire_crystal")).mapColor(MapColor.LAPIS)));

    public static final Block JADE_CRYSTAL_BLOCK = defer("jade_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("jade_crystal_block")).mapColor(MapColor.PLANT)));
    public static final Block JADE_CRYSTAL_CLUSTER = defer("jade_crystal_cluster", new AmethystClusterBlock(7, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).setId(key("jade_crystal_cluster")).lightLevel(state -> 6).mapColor(MapColor.PLANT)));
    public static final Block LARGE_JADE_CRYSTAL_BUD = defer("large_jade_crystal_bud", new AmethystClusterBlock(5, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD).setId(key("large_jade_crystal_bud")).lightLevel(state -> 5).mapColor(MapColor.PLANT)));
    public static final Block MEDIUM_JADE_CRYSTAL_BUD = defer("medium_jade_crystal_bud", new AmethystClusterBlock(4, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.MEDIUM_AMETHYST_BUD).setId(key("medium_jade_crystal_bud")).lightLevel(state -> 3).mapColor(MapColor.PLANT)));
    public static final Block SMALL_JADE_CRYSTAL_BUD = defer("small_jade_crystal_bud", new AmethystClusterBlock(3, 8,
        BlockBehaviour.Properties.ofFullCopy(Blocks.SMALL_AMETHYST_BUD).setId(key("small_jade_crystal_bud")).lightLevel(state -> 2).mapColor(MapColor.PLANT)));
    public static final Block BUDDING_JADE_CRYSTAL = defer("budding_jade_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_JADE_CRYSTAL_BUD, () -> MEDIUM_JADE_CRYSTAL_BUD,
        () -> LARGE_JADE_CRYSTAL_BUD, () -> JADE_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST).setId(key("budding_jade_crystal")).mapColor(MapColor.PLANT)));

    public static final Block AMETRINE_CRYSTAL_BLOCK = defer("ametrine_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ametrine_crystal_block")).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block AMETRINE_CRYSTAL_CLUSTER = defer("ametrine_crystal_cluster", new AmethystClusterBlock(7, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER).setId(key("ametrine_crystal_cluster")).lightLevel(state -> 6).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block LARGE_AMETRINE_CRYSTAL_BUD = defer("large_ametrine_crystal_bud", new AmethystClusterBlock(5, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD).setId(key("large_ametrine_crystal_bud")).lightLevel(state -> 5).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block MEDIUM_AMETRINE_CRYSTAL_BUD = defer("medium_ametrine_crystal_bud", new AmethystClusterBlock(4, 10,
        BlockBehaviour.Properties.ofFullCopy(Blocks.MEDIUM_AMETHYST_BUD).setId(key("medium_ametrine_crystal_bud")).lightLevel(state -> 3).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block SMALL_AMETRINE_CRYSTAL_BUD = defer("small_ametrine_crystal_bud", new AmethystClusterBlock(3, 8,
        BlockBehaviour.Properties.ofFullCopy(Blocks.SMALL_AMETHYST_BUD).setId(key("small_ametrine_crystal_bud")).lightLevel(state -> 2).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block BUDDING_AMETRINE_CRYSTAL = defer("budding_ametrine_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_AMETRINE_CRYSTAL_BUD, () -> MEDIUM_AMETRINE_CRYSTAL_BUD,
        () -> LARGE_AMETRINE_CRYSTAL_BUD, () -> AMETRINE_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST).setId(key("budding_ametrine_crystal")).mapColor(MapColor.COLOR_PURPLE)));

    public static final Block AQUAMARINE_CRYSTAL_BLOCK_SLAB = defer("aquamarine_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("aquamarine_crystal_block_slab")).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block AQUAMARINE_CRYSTAL_BLOCK_STAIRS = defer("aquamarine_crystal_block_stairs", new MythicStairBlock(
        AQUAMARINE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("aquamarine_crystal_block_stairs")).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block POLISHED_AQUAMARINE_CRYSTAL_BLOCK = defer("polished_aquamarine_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_aquamarine_crystal_block")).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block POLISHED_AQUAMARINE_CRYSTAL_BLOCK_SLAB = defer("polished_aquamarine_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_aquamarine_crystal_block_slab")).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block POLISHED_AQUAMARINE_CRYSTAL_BLOCK_STAIRS = defer("polished_aquamarine_crystal_block_stairs", new MythicStairBlock(
        POLISHED_AQUAMARINE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_aquamarine_crystal_block_stairs")).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block AQUAMARINE_CRYSTAL_BRICKS = defer("aquamarine_crystal_bricks", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("aquamarine_crystal_bricks")).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block AQUAMARINE_CRYSTAL_BRICKS_SLAB = defer("aquamarine_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("aquamarine_crystal_bricks_slab")).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block AQUAMARINE_CRYSTAL_BRICKS_STAIRS = defer("aquamarine_crystal_bricks_stairs", new MythicStairBlock(
        AQUAMARINE_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("aquamarine_crystal_bricks_stairs")).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block AQUAMARINE_CRYSTAL_PILLAR = defer("aquamarine_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("aquamarine_crystal_pillar")).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block CUT_AQUAMARINE_CRYSTAL_PILLAR = defer("cut_aquamarine_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("cut_aquamarine_crystal_pillar")).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final Block AQUAMARINE_CRYSTAL_BRICKS_WALL = defer("aquamarine_crystal_bricks_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("aquamarine_crystal_bricks_wall")).mapColor(MapColor.COLOR_LIGHT_BLUE)));

    public static final Block CITRINE_CRYSTAL_BLOCK_SLAB = defer("citrine_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("citrine_crystal_block_slab")).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CITRINE_CRYSTAL_BLOCK_STAIRS = defer("citrine_crystal_block_stairs", new MythicStairBlock(
        CITRINE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("citrine_crystal_block_stairs")).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block POLISHED_CITRINE_CRYSTAL_BLOCK = defer("polished_citrine_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_citrine_crystal_block")).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block POLISHED_CITRINE_CRYSTAL_BLOCK_SLAB = defer("polished_citrine_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_citrine_crystal_block_slab")).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block POLISHED_CITRINE_CRYSTAL_BLOCK_STAIRS = defer("polished_citrine_crystal_block_stairs", new MythicStairBlock(
        POLISHED_CITRINE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_citrine_crystal_block_stairs")).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CITRINE_CRYSTAL_BRICKS = defer("citrine_crystal_bricks", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("citrine_crystal_bricks")).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CITRINE_CRYSTAL_BRICKS_SLAB = defer("citrine_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("citrine_crystal_bricks_slab")).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CITRINE_CRYSTAL_BRICKS_STAIRS = defer("citrine_crystal_bricks_stairs", new MythicStairBlock(
        CITRINE_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("citrine_crystal_bricks_stairs")).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CITRINE_CRYSTAL_PILLAR = defer("citrine_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("citrine_crystal_pillar")).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CUT_CITRINE_CRYSTAL_PILLAR = defer("cut_citrine_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("cut_citrine_crystal_pillar")).mapColor(MapColor.COLOR_YELLOW)));
    public static final Block CITRINE_CRYSTAL_BRICKS_WALL = defer("citrine_crystal_bricks_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("citrine_crystal_bricks_wall")).mapColor(MapColor.COLOR_YELLOW)));

    public static final Block TOPAZ_CRYSTAL_BLOCK_SLAB = defer("topaz_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("topaz_crystal_block_slab")).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block TOPAZ_CRYSTAL_BLOCK_STAIRS = defer("topaz_crystal_block_stairs", new MythicStairBlock(
        TOPAZ_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("topaz_crystal_block_stairs")).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block POLISHED_TOPAZ_CRYSTAL_BLOCK = defer("polished_topaz_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_topaz_crystal_block")).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block POLISHED_TOPAZ_CRYSTAL_BLOCK_SLAB = defer("polished_topaz_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_topaz_crystal_block_slab")).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block POLISHED_TOPAZ_CRYSTAL_BLOCK_STAIRS = defer("polished_topaz_crystal_block_stairs", new MythicStairBlock(
        POLISHED_TOPAZ_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_topaz_crystal_block_stairs")).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block TOPAZ_CRYSTAL_BRICKS = defer("topaz_crystal_bricks", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("topaz_crystal_bricks")).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block TOPAZ_CRYSTAL_BRICKS_SLAB = defer("topaz_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("topaz_crystal_bricks_slab")).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block TOPAZ_CRYSTAL_BRICKS_STAIRS = defer("topaz_crystal_bricks_stairs", new MythicStairBlock(
        TOPAZ_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("topaz_crystal_bricks_stairs")).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block TOPAZ_CRYSTAL_PILLAR = defer("topaz_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("topaz_crystal_pillar")).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block CUT_TOPAZ_CRYSTAL_PILLAR = defer("cut_topaz_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("cut_topaz_crystal_pillar")).mapColor(MapColor.COLOR_ORANGE)));
    public static final Block TOPAZ_CRYSTAL_BRICKS_WALL = defer("topaz_crystal_bricks_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("topaz_crystal_bricks_wall")).mapColor(MapColor.COLOR_ORANGE)));

    public static final Block PERIDOT_CRYSTAL_BLOCK_SLAB = defer("peridot_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("peridot_crystal_block_slab")).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block PERIDOT_CRYSTAL_BLOCK_STAIRS = defer("peridot_crystal_block_stairs", new MythicStairBlock(
        PERIDOT_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("peridot_crystal_block_stairs")).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block POLISHED_PERIDOT_CRYSTAL_BLOCK = defer("polished_peridot_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_peridot_crystal_block")).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block POLISHED_PERIDOT_CRYSTAL_BLOCK_SLAB = defer("polished_peridot_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_peridot_crystal_block_slab")).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block POLISHED_PERIDOT_CRYSTAL_BLOCK_STAIRS = defer("polished_peridot_crystal_block_stairs", new MythicStairBlock(
        POLISHED_PERIDOT_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_peridot_crystal_block_stairs")).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block PERIDOT_CRYSTAL_BRICKS = defer("peridot_crystal_bricks", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("peridot_crystal_bricks")).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block PERIDOT_CRYSTAL_BRICKS_SLAB = defer("peridot_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("peridot_crystal_bricks_slab")).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block PERIDOT_CRYSTAL_BRICKS_STAIRS = defer("peridot_crystal_bricks_stairs", new MythicStairBlock(
        PERIDOT_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("peridot_crystal_bricks_stairs")).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block PERIDOT_CRYSTAL_PILLAR = defer("peridot_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("peridot_crystal_pillar")).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block CUT_PERIDOT_CRYSTAL_PILLAR = defer("cut_peridot_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("cut_peridot_crystal_pillar")).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Block PERIDOT_CRYSTAL_BRICKS_WALL = defer("peridot_crystal_bricks_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("peridot_crystal_bricks_wall")).mapColor(MapColor.COLOR_LIGHT_GREEN)));

    public static final Block RUBY_CRYSTAL_BLOCK_SLAB = defer("ruby_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ruby_crystal_block_slab")).mapColor(MapColor.COLOR_RED)));
    public static final Block RUBY_CRYSTAL_BLOCK_STAIRS = defer("ruby_crystal_block_stairs", new MythicStairBlock(
        RUBY_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ruby_crystal_block_stairs")).mapColor(MapColor.COLOR_RED)));
    public static final Block POLISHED_RUBY_CRYSTAL_BLOCK = defer("polished_ruby_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_ruby_crystal_block")).mapColor(MapColor.COLOR_RED)));
    public static final Block POLISHED_RUBY_CRYSTAL_BLOCK_SLAB = defer("polished_ruby_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_ruby_crystal_block_slab")).mapColor(MapColor.COLOR_RED)));
    public static final Block POLISHED_RUBY_CRYSTAL_BLOCK_STAIRS = defer("polished_ruby_crystal_block_stairs", new MythicStairBlock(
        POLISHED_RUBY_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_ruby_crystal_block_stairs")).mapColor(MapColor.COLOR_RED)));
    public static final Block RUBY_CRYSTAL_BRICKS = defer("ruby_crystal_bricks", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ruby_crystal_bricks")).mapColor(MapColor.COLOR_RED)));
    public static final Block RUBY_CRYSTAL_BRICKS_SLAB = defer("ruby_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ruby_crystal_bricks_slab")).mapColor(MapColor.COLOR_RED)));
    public static final Block RUBY_CRYSTAL_BRICKS_STAIRS = defer("ruby_crystal_bricks_stairs", new MythicStairBlock(
        RUBY_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ruby_crystal_bricks_stairs")).mapColor(MapColor.COLOR_RED)));
    public static final Block RUBY_CRYSTAL_PILLAR = defer("ruby_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ruby_crystal_pillar")).mapColor(MapColor.COLOR_RED)));
    public static final Block CUT_RUBY_CRYSTAL_PILLAR = defer("cut_ruby_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("cut_ruby_crystal_pillar")).mapColor(MapColor.COLOR_RED)));
    public static final Block RUBY_CRYSTAL_BRICKS_WALL = defer("ruby_crystal_bricks_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ruby_crystal_bricks_wall")).mapColor(MapColor.COLOR_RED)));

    public static final Block SAPPHIRE_CRYSTAL_BLOCK_SLAB = defer("sapphire_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("sapphire_crystal_block_slab")).mapColor(MapColor.LAPIS)));
    public static final Block SAPPHIRE_CRYSTAL_BLOCK_STAIRS = defer("sapphire_crystal_block_stairs", new MythicStairBlock(
        SAPPHIRE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("sapphire_crystal_block_stairs")).mapColor(MapColor.LAPIS)));
    public static final Block POLISHED_SAPPHIRE_CRYSTAL_BLOCK = defer("polished_sapphire_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_sapphire_crystal_block")).mapColor(MapColor.LAPIS)));
    public static final Block POLISHED_SAPPHIRE_CRYSTAL_BLOCK_SLAB = defer("polished_sapphire_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_sapphire_crystal_block_slab")).mapColor(MapColor.LAPIS)));
    public static final Block POLISHED_SAPPHIRE_CRYSTAL_BLOCK_STAIRS = defer("polished_sapphire_crystal_block_stairs", new MythicStairBlock(
        POLISHED_SAPPHIRE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_sapphire_crystal_block_stairs")).mapColor(MapColor.LAPIS)));
    public static final Block SAPPHIRE_CRYSTAL_BRICKS = defer("sapphire_crystal_bricks", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("sapphire_crystal_bricks")).mapColor(MapColor.LAPIS)));
    public static final Block SAPPHIRE_CRYSTAL_BRICKS_SLAB = defer("sapphire_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("sapphire_crystal_bricks_slab")).mapColor(MapColor.LAPIS)));
    public static final Block SAPPHIRE_CRYSTAL_BRICKS_STAIRS = defer("sapphire_crystal_bricks_stairs", new MythicStairBlock(
        SAPPHIRE_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("sapphire_crystal_bricks_stairs")).mapColor(MapColor.LAPIS)));
    public static final Block SAPPHIRE_CRYSTAL_PILLAR = defer("sapphire_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("sapphire_crystal_pillar")).mapColor(MapColor.LAPIS)));
    public static final Block CUT_SAPPHIRE_CRYSTAL_PILLAR = defer("cut_sapphire_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("cut_sapphire_crystal_pillar")).mapColor(MapColor.LAPIS)));
    public static final Block SAPPHIRE_CRYSTAL_BRICKS_WALL = defer("sapphire_crystal_bricks_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("sapphire_crystal_bricks_wall")).mapColor(MapColor.LAPIS)));

    public static final Block JADE_CRYSTAL_BLOCK_SLAB = defer("jade_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("jade_crystal_block_slab")).mapColor(MapColor.PLANT)));
    public static final Block JADE_CRYSTAL_BLOCK_STAIRS = defer("jade_crystal_block_stairs", new MythicStairBlock(
        JADE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("jade_crystal_block_stairs")).mapColor(MapColor.PLANT)));
    public static final Block POLISHED_JADE_CRYSTAL_BLOCK = defer("polished_jade_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_jade_crystal_block")).mapColor(MapColor.PLANT)));
    public static final Block POLISHED_JADE_CRYSTAL_BLOCK_SLAB = defer("polished_jade_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_jade_crystal_block_slab")).mapColor(MapColor.PLANT)));
    public static final Block POLISHED_JADE_CRYSTAL_BLOCK_STAIRS = defer("polished_jade_crystal_block_stairs", new MythicStairBlock(
        POLISHED_JADE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_jade_crystal_block_stairs")).mapColor(MapColor.PLANT)));
    public static final Block JADE_CRYSTAL_BRICKS = defer("jade_crystal_bricks", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("jade_crystal_bricks")).mapColor(MapColor.PLANT)));
    public static final Block JADE_CRYSTAL_BRICKS_SLAB = defer("jade_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("jade_crystal_bricks_slab")).mapColor(MapColor.PLANT)));
    public static final Block JADE_CRYSTAL_BRICKS_STAIRS = defer("jade_crystal_bricks_stairs", new MythicStairBlock(
        JADE_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("jade_crystal_bricks_stairs")).mapColor(MapColor.PLANT)));
    public static final Block JADE_CRYSTAL_PILLAR = defer("jade_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("jade_crystal_pillar")).mapColor(MapColor.PLANT)));
    public static final Block CUT_JADE_CRYSTAL_PILLAR = defer("cut_jade_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("cut_jade_crystal_pillar")).mapColor(MapColor.PLANT)));
    public static final Block JADE_CRYSTAL_BRICKS_WALL = defer("jade_crystal_bricks_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("jade_crystal_bricks_wall")).mapColor(MapColor.PLANT)));

    public static final Block AMETRINE_CRYSTAL_BLOCK_SLAB = defer("ametrine_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ametrine_crystal_block_slab")).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block AMETRINE_CRYSTAL_BLOCK_STAIRS = defer("ametrine_crystal_block_stairs", new MythicStairBlock(
        AMETRINE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ametrine_crystal_block_stairs")).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block POLISHED_AMETRINE_CRYSTAL_BLOCK = defer("polished_ametrine_crystal_block", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_ametrine_crystal_block")).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block POLISHED_AMETRINE_CRYSTAL_BLOCK_SLAB = defer("polished_ametrine_crystal_block_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_ametrine_crystal_block_slab")).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block POLISHED_AMETRINE_CRYSTAL_BLOCK_STAIRS = defer("polished_ametrine_crystal_block_stairs", new MythicStairBlock(
        POLISHED_AMETRINE_CRYSTAL_BLOCK.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("polished_ametrine_crystal_block_stairs")).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block AMETRINE_CRYSTAL_BRICKS = defer("ametrine_crystal_bricks", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ametrine_crystal_bricks")).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block AMETRINE_CRYSTAL_BRICKS_SLAB = defer("ametrine_crystal_bricks_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ametrine_crystal_bricks_slab")).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block AMETRINE_CRYSTAL_BRICKS_STAIRS = defer("ametrine_crystal_bricks_stairs", new MythicStairBlock(
        AMETRINE_CRYSTAL_BRICKS.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ametrine_crystal_bricks_stairs")).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block AMETRINE_CRYSTAL_PILLAR = defer("ametrine_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ametrine_crystal_pillar")).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block CUT_AMETRINE_CRYSTAL_PILLAR = defer("cut_ametrine_crystal_pillar", new RotatedPillarBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("cut_ametrine_crystal_pillar")).mapColor(MapColor.COLOR_PURPLE)));
    public static final Block AMETRINE_CRYSTAL_BRICKS_WALL = defer("ametrine_crystal_bricks_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).setId(key("ametrine_crystal_bricks_wall")).mapColor(MapColor.COLOR_PURPLE)));

    public static final Block AQUAMARINE_SCHIST = defer("aquamarine_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("aquamarine_schist")).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));
    public static final Block AQUAMARINE_SCHIST_SLAB = defer("aquamarine_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("aquamarine_schist_slab")).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));
    public static final Block AQUAMARINE_SCHIST_STAIRS = defer("aquamarine_schist_stairs", new MythicStairBlock(
        AQUAMARINE_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("aquamarine_schist_stairs")).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_AQUAMARINE_SCHIST = defer("polished_aquamarine_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_aquamarine_schist")).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_AQUAMARINE_SCHIST_SLAB = defer("polished_aquamarine_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_aquamarine_schist_slab")).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_AQUAMARINE_SCHIST_STAIRS = defer("polished_aquamarine_schist_stairs", new MythicStairBlock(
        POLISHED_AQUAMARINE_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_aquamarine_schist_stairs")).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));
    public static final Block AQUAMARINE_SCHIST_WALL = defer("aquamarine_schist_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("aquamarine_schist_wall")).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 6.0F)));

    public static final Block CITRINE_SCHIST = defer("citrine_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("citrine_schist")).mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));
    public static final Block CITRINE_SCHIST_SLAB = defer("citrine_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("citrine_schist_slab")).mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));
    public static final Block CITRINE_SCHIST_STAIRS = defer("citrine_schist_stairs", new MythicStairBlock(
        CITRINE_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("citrine_schist_stairs")).mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));
    public static final Block POLISHED_CITRINE_SCHIST = defer("polished_citrine_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_citrine_schist")).mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));
    public static final Block POLISHED_CITRINE_SCHIST_SLAB = defer("polished_citrine_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_citrine_schist_slab")).mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));
    public static final Block POLISHED_CITRINE_SCHIST_STAIRS = defer("polished_citrine_schist_stairs", new MythicStairBlock(
        POLISHED_CITRINE_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_citrine_schist_stairs")).mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));
    public static final Block CITRINE_SCHIST_WALL = defer("citrine_schist_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("citrine_schist_wall")).mapColor(MapColor.COLOR_YELLOW).strength(1.5F, 6.0F)));

    public static final Block TOPAZ_SCHIST = defer("topaz_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("topaz_schist")).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));
    public static final Block TOPAZ_SCHIST_SLAB = defer("topaz_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("topaz_schist_slab")).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));
    public static final Block TOPAZ_SCHIST_STAIRS = defer("topaz_schist_stairs", new MythicStairBlock(
        TOPAZ_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("topaz_schist_stairs")).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_TOPAZ_SCHIST = defer("polished_topaz_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_topaz_schist")).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_TOPAZ_SCHIST_SLAB = defer("polished_topaz_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_topaz_schist_slab")).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_TOPAZ_SCHIST_STAIRS = defer("polished_topaz_schist_stairs", new MythicStairBlock(
        POLISHED_TOPAZ_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_topaz_schist_stairs")).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));
    public static final Block TOPAZ_SCHIST_WALL = defer("topaz_schist_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("topaz_schist_wall")).mapColor(MapColor.COLOR_ORANGE).strength(1.5F, 6.0F)));

    public static final Block PERIDOT_SCHIST = defer("peridot_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("peridot_schist")).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));
    public static final Block PERIDOT_SCHIST_SLAB = defer("peridot_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("peridot_schist_slab")).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));
    public static final Block PERIDOT_SCHIST_STAIRS = defer("peridot_schist_stairs", new MythicStairBlock(
        PERIDOT_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("peridot_schist_stairs")).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));
    public static final Block POLISHED_PERIDOT_SCHIST = defer("polished_peridot_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_peridot_schist")).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));
    public static final Block POLISHED_PERIDOT_SCHIST_SLAB = defer("polished_peridot_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_peridot_schist_slab")).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));
    public static final Block POLISHED_PERIDOT_SCHIST_STAIRS = defer("polished_peridot_schist_stairs", new MythicStairBlock(
        POLISHED_PERIDOT_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_peridot_schist_stairs")).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));
    public static final Block PERIDOT_SCHIST_WALL = defer("peridot_schist_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("peridot_schist_wall")).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.5F, 6.0F)));

    public static final Block RUBY_SCHIST = defer("ruby_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("ruby_schist")).mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));
    public static final Block RUBY_SCHIST_SLAB = defer("ruby_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("ruby_schist_slab")).mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));
    public static final Block RUBY_SCHIST_STAIRS = defer("ruby_schist_stairs", new MythicStairBlock(
        RUBY_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("ruby_schist_stairs")).mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));
    public static final Block POLISHED_RUBY_SCHIST = defer("polished_ruby_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_ruby_schist")).mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));
    public static final Block POLISHED_RUBY_SCHIST_SLAB = defer("polished_ruby_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_ruby_schist_slab")).mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));
    public static final Block POLISHED_RUBY_SCHIST_STAIRS = defer("polished_ruby_schist_stairs", new MythicStairBlock(
        POLISHED_RUBY_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_ruby_schist_stairs")).mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));
    public static final Block RUBY_SCHIST_WALL = defer("ruby_schist_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("ruby_schist_wall")).mapColor(MapColor.COLOR_RED).strength(1.5F, 6.0F)));

    public static final Block SAPPHIRE_SCHIST = defer("sapphire_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("sapphire_schist")).mapColor(MapColor.LAPIS).strength(1.5F, 6.0F)));
    public static final Block SAPPHIRE_SCHIST_SLAB = defer("sapphire_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("sapphire_schist_slab")).mapColor(MapColor.LAPIS).strength(1.5F, 6.0F)));
    public static final Block SAPPHIRE_SCHIST_STAIRS = defer("sapphire_schist_stairs", new MythicStairBlock(
        SAPPHIRE_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("sapphire_schist_stairs")).mapColor(MapColor.LAPIS).strength(1.5F, 6.0F)));
    public static final Block POLISHED_SAPPHIRE_SCHIST = defer("polished_sapphire_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_sapphire_schist")).mapColor(MapColor.LAPIS).strength(1.5F, 6.0F)));
    public static final Block POLISHED_SAPPHIRE_SCHIST_SLAB = defer("polished_sapphire_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_sapphire_schist_slab")).mapColor(MapColor.LAPIS).strength(1.5F, 6.0F)));
    public static final Block POLISHED_SAPPHIRE_SCHIST_STAIRS = defer("polished_sapphire_schist_stairs", new MythicStairBlock(
        POLISHED_SAPPHIRE_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_sapphire_schist_stairs")).mapColor(MapColor.LAPIS).strength(1.5F, 6.0F)));
    public static final Block SAPPHIRE_SCHIST_WALL = defer("sapphire_schist_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("sapphire_schist_wall")).mapColor(MapColor.LAPIS).strength(1.5F, 6.0F)));

    public static final Block JADE_SCHIST = defer("jade_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("jade_schist")).mapColor(MapColor.PLANT).strength(1.5F, 6.0F)));
    public static final Block JADE_SCHIST_SLAB = defer("jade_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("jade_schist_slab")).mapColor(MapColor.PLANT).strength(1.5F, 6.0F)));
    public static final Block JADE_SCHIST_STAIRS = defer("jade_schist_stairs", new MythicStairBlock(
        JADE_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("jade_schist_stairs")).mapColor(MapColor.PLANT).strength(1.5F, 6.0F)));
    public static final Block POLISHED_JADE_SCHIST = defer("polished_jade_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_jade_schist")).mapColor(MapColor.PLANT).strength(1.5F, 6.0F)));
    public static final Block POLISHED_JADE_SCHIST_SLAB = defer("polished_jade_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_jade_schist_slab")).mapColor(MapColor.PLANT).strength(1.5F, 6.0F)));
    public static final Block POLISHED_JADE_SCHIST_STAIRS = defer("polished_jade_schist_stairs", new MythicStairBlock(
        POLISHED_JADE_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_jade_schist_stairs")).mapColor(MapColor.PLANT).strength(1.5F, 6.0F)));
    public static final Block JADE_SCHIST_WALL = defer("jade_schist_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("jade_schist_wall")).mapColor(MapColor.PLANT).strength(1.5F, 6.0F)));

    public static final Block AMETRINE_SCHIST = defer("ametrine_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("ametrine_schist")).mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));
    public static final Block AMETRINE_SCHIST_SLAB = defer("ametrine_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("ametrine_schist_slab")).mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));
    public static final Block AMETRINE_SCHIST_STAIRS = defer("ametrine_schist_stairs", new MythicStairBlock(
        AMETRINE_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("ametrine_schist_stairs")).mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_AMETRINE_SCHIST = defer("polished_ametrine_schist", new Block(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_ametrine_schist")).mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_AMETRINE_SCHIST_SLAB = defer("polished_ametrine_schist_slab", new SlabBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_ametrine_schist_slab")).mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));
    public static final Block POLISHED_AMETRINE_SCHIST_STAIRS = defer("polished_ametrine_schist_stairs", new MythicStairBlock(
        POLISHED_AMETRINE_SCHIST.defaultBlockState(),
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("polished_ametrine_schist_stairs")).mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));
    public static final Block AMETRINE_SCHIST_WALL = defer("ametrine_schist_wall", new WallBlock(
        BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(key("ametrine_schist_wall")).mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)));

    public static void register(BiFunction<String, Block, Block> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
        Constants.LOG.info("MythicBlocks registered.");
    }

    public static void registerItems(BiFunction<String, Item, Item> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), new BlockItem(e.getValue(), new Item.Properties()
            .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, e.getKey())))
            .useBlockDescriptionPrefix())));
    }
}
