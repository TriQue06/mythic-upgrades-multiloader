package net.trique.mythicupgrades.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class CrystalBudFeature extends Feature<CrystalBudFeatureConfig> {

    private static final Direction[] DIRECTIONS = {
        Direction.DOWN, Direction.UP,
        Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST
    };

    public CrystalBudFeature(Codec<CrystalBudFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CrystalBudFeatureConfig> ctx) {
        CrystalBudFeatureConfig cfg = ctx.config();
        RandomSource random = ctx.random();
        WorldGenLevel level = ctx.level();
        BlockPos origin = ctx.origin();
        boolean placed = false;

        for (int i = 0; i < cfg.tries(); i++) {
            int dx = random.nextInt(cfg.spreadXZ() * 2 + 1) - cfg.spreadXZ();
            int dy = random.nextInt(cfg.spreadY() * 2 + 1) - cfg.spreadY();
            int dz = random.nextInt(cfg.spreadXZ() * 2 + 1) - cfg.spreadXZ();
            BlockPos pos = origin.offset(dx, dy, dz);

            if (!level.getBlockState(pos).isAir()) continue;

            // Slightly favour ceiling placement for a more natural cave look
            Direction[] dirs = random.nextFloat() < 0.4f
                ? new Direction[]{Direction.DOWN, Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST}
                : DIRECTIONS;

            for (Direction dir : dirs) {
                BlockPos support = pos.relative(dir.getOpposite());
                if (!level.getBlockState(support).isFaceSturdy(level, support, dir)) continue;

                BlockState bud = cfg.state().getState(random, pos);
                if (bud.hasProperty(BlockStateProperties.FACING)) {
                    bud = bud.setValue(BlockStateProperties.FACING, dir);
                }
                if (bud.canSurvive(level, pos)) {
                    level.setBlock(pos, bud, 2);
                    placed = true;
                    break;
                }
            }
        }
        return placed;
    }
}
