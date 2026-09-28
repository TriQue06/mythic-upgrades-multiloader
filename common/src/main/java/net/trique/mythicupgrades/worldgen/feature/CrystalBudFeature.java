package net.trique.mythicupgrades.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record CrystalBudFeature(
    Holder<BlockStateProvider> state,
    int tries,
    int spreadXZ,
    int spreadY
) implements Feature {

    public static final MapCodec<CrystalBudFeature> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
        BlockStateProvider.CODEC.fieldOf("state").forGetter(CrystalBudFeature::state),
        Codec.intRange(1, 512).fieldOf("tries").forGetter(CrystalBudFeature::tries),
        Codec.intRange(0, 16).fieldOf("spread_xz").forGetter(CrystalBudFeature::spreadXZ),
        Codec.intRange(0, 16).fieldOf("spread_y").forGetter(CrystalBudFeature::spreadY)
    ).apply(inst, CrystalBudFeature::new));

    private static final Direction[] DIRECTIONS = {
        Direction.DOWN, Direction.UP,
        Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST
    };

    @Override
    public MapCodec<CrystalBudFeature> codec() {
        return CODEC;
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin) {
        boolean placed = false;

        for (int i = 0; i < tries; i++) {
            int dx = random.nextInt(spreadXZ * 2 + 1) - spreadXZ;
            int dy = random.nextInt(spreadY * 2 + 1) - spreadY;
            int dz = random.nextInt(spreadXZ * 2 + 1) - spreadXZ;
            BlockPos pos = origin.offset(dx, dy, dz);

            if (!level.getBlockState(pos).isAir()) continue;

            Direction[] dirs = random.nextFloat() < 0.4f
                ? new Direction[]{Direction.DOWN, Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST}
                : DIRECTIONS;

            for (Direction dir : dirs) {
                BlockPos support = pos.relative(dir.getOpposite());
                if (!level.getBlockState(support).isFaceSturdy(level, support, dir)) continue;

                BlockState bud = state.value().getState(level, random, pos);
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
