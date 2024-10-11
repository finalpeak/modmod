package net.finalpeak.modmod.item.custom.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.ShapeType;

import java.util.List;

public class Detection {

    public static BlockPos raycastGetBlock(World world, PlayerEntity player, int range) {
        Vec3d start = player.getCameraPosVec(1.0F);
        Vec3d end = start.add(player.getRotationVec(1.0F).multiply(range)); // Adjust the range as needed

        RaycastContext context = new RaycastContext(start, end, ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player);

        // Perform block raycasting
        BlockHitResult blockHitResult = world.raycast(context);

        if (blockHitResult.getType() != BlockHitResult.Type.MISS) {
            BlockPos blockPos = blockHitResult.getBlockPos();

            // Perform entity raycasting
            EntityHitResult entityHitResult = getEntityHitResult(world, start, end, player);
            if (entityHitResult != null) {
                // If entity is hit first, return the block the entity occupies
                Entity entity = entityHitResult.getEntity();
                return getBlockOccupiedByEntity(entity);
            } else {
                // Return the block hit by raycast
                return blockPos;
            }
        }

        // Return null if nothing is hit
        return null;
    }

    public static Entity raycastGetEntity(World world, PlayerEntity player, int range) {
        Vec3d start = player.getCameraPosVec(1.0F);
        Vec3d end = start.add(player.getRotationVec(1.0F).multiply(range)); // Adjust the range as needed

        RaycastContext context = new RaycastContext(start, end, ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player);

        // Perform block raycasting
        BlockHitResult blockHitResult = world.raycast(context);

        if (blockHitResult.getType() != BlockHitResult.Type.MISS) {
            BlockPos blockPos = blockHitResult.getBlockPos();

            // Perform entity raycasting
            EntityHitResult entityHitResult = getEntityHitResult(world, start, end, player);
            if (entityHitResult != null) {
                // If entity is hit first, return the block the entity occupies
                return entityHitResult.getEntity();
            } else {
                // Return the block hit by raycast
                return null;
            }
        }

        // Return null if nothing is hit
        return null;
    }

    private static EntityHitResult getEntityHitResult(World world, Vec3d start, Vec3d end, PlayerEntity player) {
        // Define the search area around the raycast
        Box box = new Box(start, end).expand(0); // Adjust the expansion as needed

        // Get all entities within the bounding box
        List<Entity> entities = world.getEntitiesByClass(Entity.class, box, e -> e != player);

        // Find the closest entity to the start point
        return entities.stream()
                .map(EntityHitResult::new)
                .min((result1, result2) -> Double.compare(start.squaredDistanceTo(result1.getEntity().getPos()), start.squaredDistanceTo(result2.getEntity().getPos())))
                .orElse(null);
    }

    private static BlockPos getBlockOccupiedByEntity(Entity entity) {
        // Get the center of the entity's bounding box
        Vec3d center = entity.getBoundingBox().getCenter();

        // Convert the Vec3d to BlockPos
        return new BlockPos((int) center.x, (int) center.y, (int) center.z);
    }

    public static List<Entity> nearbyEntities(Entity entity, int radius){
        Box box = new Box(
                entity.getX() - radius, entity.getY() - radius, entity.getZ() - radius,
                entity.getX() + radius, entity.getY() + radius, entity.getZ() + radius
        );

        // Get all entities within the bounding box
        List<Entity> entities = entity.getWorld().getOtherEntities(entity, box);
        entities.add(entity);

        return entities;
    }

    public static List<Entity> getEntitiesNearbyBlock(World world, BlockPos blockPos, int radius) {
        // Create a bounding box around the block position with the given radius
        Box box = new Box(
                blockPos.getX() - radius, blockPos.getY() - radius, blockPos.getZ() - radius,
                blockPos.getX() + radius, blockPos.getY() + radius, blockPos.getZ() + radius
        );

        // Get all entities within the bounding box
        return world.getEntitiesByClass(Entity.class, box, entity -> true); // Returns all entities in the box
    }




}
