package net.finalpeak.gnomesandtomes.block.custom;

import net.finalpeak.gnomesandtomes.damage.ModDamageTypes;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ThornBlock extends Block {
    public static final IntProperty AGE = IntProperty.of("age", 0, 2); // Defines the age property with max age of 2
    private static final VoxelShape SMALL_SHAPE;
    private static final VoxelShape LARGE_SHAPE;

    public ThornBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0)); // Set default age to 0
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE); // Add AGE property to block's state
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Use SMALL_SHAPE for age 0 and 1, LARGE_SHAPE for age 2
        return state.get(AGE) < 2 ? SMALL_SHAPE : LARGE_SHAPE;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < 2; // Allow random ticks for ages 0 and 1
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
            entity.slowMovement(state, new Vec3d(0.800000011920929, 0.75, 0.800000011920929));
            if (!world.isClient && state.get(AGE) > 0 && (entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ())) {
                double d = Math.abs(entity.getX() - entity.lastRenderX);
                double e = Math.abs(entity.getZ() - entity.lastRenderZ);
                if (d >= 0.003 || e >= 0.003) {
                    entity.damage(ModDamageTypes.of(world, ModDamageTypes.PLANT), 3.0f); // Apply damage
                }
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if ((random.nextInt(3) == 0 || this.canMelt(world, pos, 4)) && world.getLightLevel(pos) > 11 - state.get(AGE) - state.getOpacity(world, pos) && this.increaseAge(state, world, pos)) {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (Direction direction : Direction.values()) {
                mutable.set(pos, direction);
                BlockState blockState = world.getBlockState(mutable);
                if (blockState.isOf(this) && !this.increaseAge(blockState, world, mutable)) {
                    world.scheduleBlockTick(mutable, this, MathHelper.nextInt(random, 20, 40));
                }
            }
        } else {
            world.scheduleBlockTick(pos, this, MathHelper.nextInt(random, 20, 40));
        }
    }

    private boolean canMelt(BlockView world, BlockPos pos, int maxNeighbors) {
        int count = 0;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (Direction direction : Direction.values()) {
            mutable.set(pos, direction);
            if (world.getBlockState(mutable).isOf(this)) {
                count++;
                if (count >= maxNeighbors) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean increaseAge(BlockState state, World world, BlockPos pos) {
        int age = state.get(AGE);
        if (age < 2) {
            world.setBlockState(pos, state.with(AGE, age + 1), 2);
            return false;
        } else {
            world.removeBlock(pos, false);
            return true;
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient()) {
            world.scheduleBlockTick(pos, this, MathHelper.nextInt(world.getRandom(), 20, 40)); // Schedule first tick
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    static {
        SMALL_SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 3.0, 13.0); // Small shape for ages 0 and 1
        LARGE_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 5.0, 16.0); // Larger shape for age 2
    }
}
