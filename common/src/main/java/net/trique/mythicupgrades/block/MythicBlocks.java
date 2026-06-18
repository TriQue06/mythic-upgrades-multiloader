package net.trique.mythicupgrades.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
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

    public static final Block KYANITE_ORE = defer("kyanite_ore", new DropExperienceBlock(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).mapColor(MapColor.STONE).strength(3.0F, 3.0F),
        UniformInt.of(3, 7)));

    public static final Block DEEPSLATE_KYANITE_ORE = defer("deepslate_kyanite_ore", new DropExperienceBlock(
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
        BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops().strength(3.0F, 9.0F),
        UniformInt.of(3, 7)));

    public static final Block RAW_NECOIUM_BLOCK = defer("raw_necoium_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.RAW_GOLD_BLOCK).mapColor(MapColor.WARPED_NYLIUM).strength(5.0F, 6.0F)));

    public static final Block AQUAMARINE_BLOCK = defer("aquamarine_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(5.0F, 6.0F)));

    public static final Block KYANITE_BLOCK = defer("kyanite_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_CYAN).strength(5.0F, 6.0F)));

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

    public static final Block KYANITE_CRYSTAL_BLOCK = defer("kyanite_crystal_block", new Block(
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_CYAN)));
    public static final Block KYANITE_CRYSTAL_CLUSTER = defer("kyanite_crystal_cluster", new AmethystClusterBlock(7, 3,
        BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).mapColor(MapColor.COLOR_CYAN)));
    public static final Block LARGE_KYANITE_CRYSTAL_BUD = defer("large_kyanite_crystal_bud", new AmethystClusterBlock(5, 3,
        BlockBehaviour.Properties.copy(Blocks.LARGE_AMETHYST_BUD).mapColor(MapColor.COLOR_CYAN)));
    public static final Block MEDIUM_KYANITE_CRYSTAL_BUD = defer("medium_kyanite_crystal_bud", new AmethystClusterBlock(4, 3,
        BlockBehaviour.Properties.copy(Blocks.MEDIUM_AMETHYST_BUD).mapColor(MapColor.COLOR_CYAN)));
    public static final Block SMALL_KYANITE_CRYSTAL_BUD = defer("small_kyanite_crystal_bud", new AmethystClusterBlock(3, 4,
        BlockBehaviour.Properties.copy(Blocks.SMALL_AMETHYST_BUD).mapColor(MapColor.COLOR_CYAN)));
    public static final Block BUDDING_KYANITE_CRYSTAL = defer("budding_kyanite_crystal", new MythicBuddingCrystalBlock(
        () -> SMALL_KYANITE_CRYSTAL_BUD, () -> MEDIUM_KYANITE_CRYSTAL_BUD,
        () -> LARGE_KYANITE_CRYSTAL_BUD, () -> KYANITE_CRYSTAL_CLUSTER,
        BlockBehaviour.Properties.copy(Blocks.BUDDING_AMETHYST).mapColor(MapColor.COLOR_CYAN)));

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

    public static final MythicUpgradingTableBlock MYTHIC_UPGRADING_TABLE = defer("mythic_upgrading_table",
        new MythicUpgradingTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE).strength(3.5F)));

    public static void register(BiFunction<String, Block, Block> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), e.getValue()));
        Constants.LOG.info("MythicBlocks registered.");
    }

    public static void registerItems(BiFunction<String, Item, Item> reg) {
        DEFERRED.forEach(e -> reg.apply(e.getKey(), new BlockItem(e.getValue(), new Item.Properties())));
    }
}
