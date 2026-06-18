package net.trique.mythicupgrades.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.trique.mythicupgrades.block.entity.MythicUpgradingTableBlockEntity;
import net.trique.mythicupgrades.registry.MythicBlockEntityTypes;

import javax.annotation.Nullable;

public class MythicUpgradingTableBlock extends BaseEntityBlock {

    private static final VoxelShape SHAPE = makeShape();

    private static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.125, 0.25, 0.125, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0, 0.125, 1, 0.125, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 0, 0.625, 1, 0.125, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.625, 0.25, 0.125, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.125, 0.1875, 0.1875, 0.6875, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.125, 0.6875, 0.1875, 0.6875, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0.125, 0.1875, 0.9375, 0.6875, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0.125, 0.6875, 0.9375, 0.6875, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.6875, 0.0625, 1, 0.875, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0.875, 0.25, 1.0625, 1.375, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.3125, 0.1875, 0.8125, 0.4375, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.875, 0.25, 0.5625, 1, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 1, 0.375, 0.5625, 1.125, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.875, 0.375, 0.4375, 1, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0.875, 0.75, 0.8125, 1, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 1, 0.75, 0.6875, 1.125, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.057598304703363135, 0.875, 0.5506662607362388, 0.31740169529663687, 1.125, 0.8006662607362388), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.059819173824159244, 0.9375, 0.17566626073623878, 0.18481917382415924, 1.0625, 0.5506662607362388), BooleanOp.OR);
        return shape;
    }

    public MythicUpgradingTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MythicUpgradingTableBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                   BlockEntityType<T> type) {
        return createTickerHelper(type, MythicBlockEntityTypes.UPGRADING_TABLE,
                MythicUpgradingTableBlockEntity.createTicker(level));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                  Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof MythicUpgradingTableBlockEntity table) {
            player.openMenu(table);
        }
        return InteractionResult.CONSUME;
    }
}
