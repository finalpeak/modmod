package net.finalpeak.gnomesandtomes.item.custom.util;

import net.finalpeak.gnomesandtomes.block.ModBlocks;
import net.finalpeak.gnomesandtomes.block.custom.ThornBlock;
import net.finalpeak.gnomesandtomes.damage.ModDamageTypes;
import net.finalpeak.gnomesandtomes.entity.custom.BoulderEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;
import net.minecraft.block.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Spells {

    public static boolean entangle(World world, PlayerEntity player) {
        int range = 30;
        int radius = 3;
        Timer timer = new Timer();

        BlockPos targetBlock = Detection.raycastGetBlock(world, player, range);
        if(targetBlock == null){
            return false;
        }

        //Store all valid locations
        ArrayList<BlockPos> positions = new ArrayList<>();

        int minX = targetBlock.getX()-radius;
        int maxX = targetBlock.getX()+radius;
        int minZ = targetBlock.getZ()-radius;
        int maxZ = targetBlock.getZ()+radius;
        int maxY = targetBlock.getY()+radius;

        for(int x = minX; x <= maxX; x++){
            for(int z = minZ; z <= maxZ; z++){
                for(int y = 0; y <= radius*2; y++){
                    BlockPos block = new BlockPos(x, maxY, z);
                    if(world.isAir(block.down(y)) && world.isTopSolid(block.down(y+1), player)){
                        positions.add(block.down(y));
                    }
                }
            }
        }

        //Dust Windup
        for(int i = 0; i <= 250; i += 50){
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!world.isClient) {
                        ServerWorld serverWorld = (ServerWorld) world;
                        DustParticleEffect dustEffect = new DustParticleEffect(new Vector3f(0.8F, 0.52F, 0.25F), 3.0F);

                        for (BlockPos pos : positions){
                            serverWorld.spawnParticles(
                                    dustEffect,
                                    pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5,
                                    1,
                                    2, 0, 2,
                                    1
                            );
                        }
                    }
                }
            }, i);
        }


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BlockState thornBlockState = ModBlocks.THORN.getDefaultState().with(ThornBlock.AGE, 0);
                for (BlockPos pos : positions){
                    world.setBlockState(pos, thornBlockState, 3);
                }
            }
        }, 250);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BlockState thornBlockState = ModBlocks.THORN.getDefaultState().with(ThornBlock.AGE, 1);
                for (BlockPos pos : positions){
                    world.setBlockState(pos, thornBlockState, 3);
                }
            }
        }, 500);

       return true;
    }

    public static boolean test(World world, PlayerEntity player) {
        int range = 30;
        Timer timer = new Timer();

        if(Detection.raycastGetBlock(world, player, range) == null){
            return false;
        }
        BlockPos blockPos = Detection.raycastGetBlock(world, player, range);

        for(int i = 0; i <= 1000; i += 50){
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!world.isClient) {
                        ServerWorld serverWorld = (ServerWorld) world;
                        DustParticleEffect dustEffect = new DustParticleEffect(new Vector3f(0.8F, 0.52F, 0.25F), 3.0F);
                        serverWorld.spawnParticles(
                                dustEffect,
                                blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5,
                                5,
                                2, 0, 2,
                                1
                        );
                    }
                }
            }, i);
        }

        return true;
    }

    // Summon lightning at the block the player is looking at
    public static boolean lightning(World world, PlayerEntity player) {
        int range = 30;
        Timer timer = new Timer();

        if(Detection.raycastGetBlock(world, player, range) == null){
            return false;
        }
        BlockPos blockPos = Detection.raycastGetBlock(world, player, range);

        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            serverWorld.spawnParticles(ParticleTypes.GLOW,
                    blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5,
                    300, // Number of particles
                    0, 10, 0, // x, y, z offsets
                    0.025 // Particle speed
            );
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);

                if (lightning != null) {
                    assert blockPos != null;
                    lightning.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
                    world.spawnEntity(lightning);

                    List<Entity> entities = Detection.getEntitiesNearbyBlock(world, blockPos, 10);
                    for(Entity entity: entities){
                        entity.damage(ModDamageTypes.of(world, ModDamageTypes.SKY), 5.0f);
                    }

                    if (!world.isClient) {
                        ServerWorld serverWorld = (ServerWorld) world;
                        serverWorld.spawnParticles(ParticleTypes.DUST_PLUME,
                                blockPos.getX(), blockPos.getY()+1, blockPos.getZ(),
                                150, // Number of particles
                                1.5, 0, 1.5, // x, y, z offsets
                                0.1 // Particle speed
                        );
                    }
                }
            }
        }, 500);
        return true;
    }

    public static boolean launch(World world, PlayerEntity player, double horizontalSpeed, double verticalSpeed){
        int range = 30;
        int radius = 3;

        Timer timer = new Timer();

        BlockPos targetBlock = Detection.raycastGetBlock(world, player, range);
        if(targetBlock == null){
            return false;
        }

        List<Entity> entities = Detection.getEntitiesNearbyBlock(world, targetBlock, radius);

        Vec3d direction = player.getRotationVec(1.0F);
        Vec3d horizontalVelocity = direction.multiply(horizontalSpeed);
        Vec3d finalVelocity = new Vec3d(horizontalVelocity.x, verticalSpeed, horizontalVelocity.z);

        for(Entity entity: entities){
            entity.setVelocity(finalVelocity);
            entity.velocityModified = true;
            if (entity != player){
                entity.damage(ModDamageTypes.of(world, ModDamageTypes.EARTH), 8.0f);
            }
        }

        BlockPos targetPos = targetBlock.up();
        while ((world.isAir(targetPos.down()) || !world.getBlockState(targetPos.down()).isSolidBlock(world, targetPos.down()))
                && targetPos.getY() > world.getBottomY()) {
            targetPos = targetPos.down();
        }

        // Now `targetPos` is at the closest ground level (or bottom of the world if none found)
        BoulderEntity boulderEntity = new BoulderEntity(world);
        boulderEntity.setPos(targetPos.getX() + 0.5, targetPos.getY() + 1, targetPos.getZ() + 0.5);
        world.spawnEntity(boulderEntity);
        boulderEntity.setVelocity(finalVelocity);
        boulderEntity.earthquakeAnimationState.start(boulderEntity.age);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                boulderEntity.kill();
            }
        }, 5000);

        return true;
    }

    public static boolean spell2(World world, PlayerEntity player){
        // Implement spell2
        return false;
    }

    public static boolean spell3(World world, PlayerEntity player){
        // Implement spell3
        return false;
    }

    public static boolean spell4(World world, PlayerEntity player){
        // Implement spell4
        return false;
    }
}
