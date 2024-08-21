package net.finalpeak.modmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

public class MysticMushroomBlock extends Block {

    // Define the shape of the block using VoxelShapes
    // The numbers represent the bounds of the box in pixels (0-16 for a full block)
    private static final VoxelShape SHAPE = VoxelShapes.cuboid(4.0 / 16.0, 0.0, 4.0 / 16.0, 12.0 / 16.0, 8.0 / 16.0, 12.0 / 16.0);

    public MysticMushroomBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return SHAPE;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        // Get the block beneath the one being placed
        BlockPos blockBelow = pos.down();
        BlockState stateBelow = world.getBlockState(blockBelow);

        // Check if the block below is either dirt or grass block
        return stateBelow.isSideSolidFullSquare(world, blockBelow, net.minecraft.util.math.Direction.UP);
    }
}