package net.trique.mythicupgrades.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

import org.jetbrains.annotations.Nullable;
import java.util.function.Supplier;

public class MythicBuddingCrystalBlock extends Block implements BonemealableBlock {

    private static final Direction[] DIRECTIONS = Direction.values();

    private final Supplier<Block> smallBud;
    private final Supplier<Block> mediumBud;
    private final Supplier<Block> largeBud;
    private final Supplier<Block> cluster;

    public MythicBuddingCrystalBlock(Supplier<Block> smallBud, Supplier<Block> mediumBud,
                                      Supplier<Block> largeBud, Supplier<Block> cluster,
                                      BlockBehaviour.Properties props) {
        super(props);
        this.smallBud = smallBud;
        this.mediumBud = mediumBud;
        this.largeBud = largeBud;
        this.cluster = cluster;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(5) != 0) return;
        Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
        BlockPos neighbor = pos.relative(direction);
        BlockState neighborState = level.getBlockState(neighbor);
        Block next = nextStage(neighborState, direction);
        if (next != null) {
            placeGrowth(level, next, neighbor, neighborState, direction);
        } else if (canGrow(neighborState)) {
            placeGrowth(level, smallBud.get(), neighbor, neighborState, direction);
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        for (Direction dir : DIRECTIONS) {
            BlockPos neighbor = pos.relative(dir);
            BlockState nState = level.getBlockState(neighbor);
            if (canGrow(nState) || nextStage(nState, dir) != null) return true;
        }
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        for (Direction dir : DIRECTIONS) {
            BlockPos neighbor = pos.relative(dir);
            BlockState nState = level.getBlockState(neighbor);
            Block next = nextStage(nState, dir);
            if (next != null) {
                placeGrowth(level, next, neighbor, nState, dir);
                return;
            }
            if (canGrow(nState)) {
                placeGrowth(level, smallBud.get(), neighbor, nState, dir);
                return;
            }
        }
    }

    @Nullable
    private Block nextStage(BlockState state, Direction facing) {
        Block b = state.getBlock();
        if (b == smallBud.get() && state.getValue(AmethystClusterBlock.FACING) == facing) return mediumBud.get();
        if (b == mediumBud.get() && state.getValue(AmethystClusterBlock.FACING) == facing) return largeBud.get();
        if (b == largeBud.get() && state.getValue(AmethystClusterBlock.FACING) == facing) return cluster.get();
        return null;
    }

    private void placeGrowth(ServerLevel level, Block growTo, BlockPos pos, BlockState current, Direction facing) {
        level.setBlockAndUpdate(pos, growTo.defaultBlockState()
            .setValue(AmethystClusterBlock.FACING, facing)
            .setValue(AmethystClusterBlock.WATERLOGGED, current.getFluidState().getType() == Fluids.WATER));
    }

    private static boolean canGrow(BlockState state) {
        return state.isAir() || state.getFluidState().getType() == Fluids.WATER;
    }
}
