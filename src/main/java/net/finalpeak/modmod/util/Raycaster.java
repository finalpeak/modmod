package net.finalpeak.modmod.util;

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

public class Raycaster {

    public static BlockPos getBlockOrEntity(World world, PlayerEntity player) {
        Vec3d start = player.getCameraPosVec(1.0F);
        Vec3d end = start.add(player.getRotationVec(1.0F).multiply(30)); // Adjust the range as needed

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

    private static EntityHitResult getEntityHitResult(World world, Vec3d start, Vec3d end, PlayerEntity player) {
        // Define the search area around the raycast
        Box box = new Box(start, end).expand(0.5); // Adjust the expansion as needed

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
        return new BlockPos(center.x, center.y, center.z);
    }
}
