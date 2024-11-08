package net.finalpeak.gnomesandtomes.item.custom.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockUtil {
    public static List<BlockPos> getCircle(World world, BlockPos center, int radius) {
        List<BlockPos> blockPositions = new ArrayList<>();

        // Get the center coordinates
        int centerX = center.getX();
        int centerY = center.getY();
        int centerZ = center.getZ();

        // Loop over angles from 0 to 360 degrees (or 0 to 2 * PI radians)
        for (double angle = 0; angle < 360; angle += 1.0) {
            // Convert angle to radians
            double radians = Math.toRadians(angle);

            // Calculate the x and z offsets from the center using trigonometry
            int offsetX = (int) (Math.cos(radians) * radius);
            int offsetZ = (int) (Math.sin(radians) * radius);

            // Get the BlockPos for the calculated coordinates
            BlockPos blockPos = new BlockPos((int)(centerX + offsetX), centerY, (int)(centerZ + offsetZ));

            // Add the block position to the list
            blockPositions.add(blockPos);
        }

        blockPositions = removeDuplicateBlockPositions(blockPositions);
        return blockPositions;
    }

    // Function to trim duplicates from the list
    public static List<BlockPos> removeDuplicateBlockPositions(List<BlockPos> blockPositions) {
        Set<BlockPos> uniquePositions = new HashSet<>(blockPositions);
        return new ArrayList<>(uniquePositions);
    }
}
