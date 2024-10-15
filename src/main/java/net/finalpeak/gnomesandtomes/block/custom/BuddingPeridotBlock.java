package net.finalpeak.gnomesandtomes.block.custom;

import com.mojang.serialization.MapCodec;
import net.finalpeak.gnomesandtomes.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

public class BuddingPeridotBlock extends AmethystBlock {
    public static final MapCodec<BuddingPeridotBlock> CODEC = createCodec(BuddingPeridotBlock::new);
    public static final int GROW_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    @Override
    public MapCodec<BuddingPeridotBlock> getCodec() {
        return CODEC;
    }

    public BuddingPeridotBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            Block block = null;
            if (canGrowIn(blockState)) {
                block = ModBlocks.SMALL_PERIDOT_BUD;
            } else if (blockState.isOf(ModBlocks.SMALL_PERIDOT_BUD) && blockState.get(PeridotClusterBlock.FACING) == direction) {
                block = ModBlocks.MEDIUM_PERIDOT_BUD;
            } else if (blockState.isOf(ModBlocks.MEDIUM_PERIDOT_BUD) && blockState.get(PeridotClusterBlock.FACING) == direction) {
                block = ModBlocks.LARGE_PERIDOT_BUD;
            } else if (blockState.isOf(ModBlocks.LARGE_PERIDOT_BUD) && blockState.get(PeridotClusterBlock.FACING) == direction) {
                block = ModBlocks.PERIDOT_CLUSTER;
            }

            if (block != null) {
                BlockState blockState2 = block.getDefaultState()
                        .with(PeridotClusterBlock.FACING, direction)
                        .with(PeridotClusterBlock.WATERLOGGED, Boolean.valueOf(blockState.getFluidState().getFluid() == Fluids.WATER));
                world.setBlockState(blockPos, blockState2);
            }
        }
    }

    public static boolean canGrowIn(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) && state.getFluidState().getLevel() == 8;
    }
}
