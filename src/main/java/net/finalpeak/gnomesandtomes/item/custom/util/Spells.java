package net.finalpeak.gnomesandtomes.item.custom.util;

import net.finalpeak.gnomesandtomes.block.ModBlocks;
import net.finalpeak.gnomesandtomes.block.custom.ThornBlock;
import net.finalpeak.gnomesandtomes.damage.ModDamageTypes;
import net.finalpeak.gnomesandtomes.entity.custom.BoulderEntity;
import net.finalpeak.gnomesandtomes.entity.custom.PillarEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

import java.util.*;

public class Spells {
    public static final Set<Block> REPLACEABLE_BY_SPELL = Set.of(
            Blocks.AIR,
            Blocks.TALL_GRASS,
            Blocks.SHORT_GRASS
    );

    public static boolean entangle(World world, PlayerEntity player) {
        int range = 30;
        int radius = 5;
        int windup = 600;
        int duration = 15000;
        Timer timer = new Timer();

        BlockPos targetedBlock = Detection.raycastGetBlock(world, player, range);
        if(targetedBlock == null){
            return false;
        }

        //Store all valid locations
        ArrayList<BlockPos> positions = new ArrayList<>();
        List<BlockPos> blockCircle = BlockUtil.getCircle(world,
                new BlockPos(targetedBlock.getX(), targetedBlock.getY(), targetedBlock.getZ()), radius, true);

        for(BlockPos block: blockCircle){
            for(int y = -radius; y <= radius; y++){
                BlockPos targetPos = block.down(y);
                if (REPLACEABLE_BY_SPELL.contains(world.getBlockState(targetPos).getBlock()) &&
                        world.isTopSolid(block.down(y + 1), player)) {
                    positions.add(targetPos);
                }
            }
        }

        //Dust Windup
        for(int i = 0; i < windup; i += 200){
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
                                    0.25, 0, 0.25,
                                    1
                            );
                        }
                    }
                }
            }, i);
        }

        // Thorn placement
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BlockState thornBlockState = ModBlocks.THORN.getDefaultState().with(ThornBlock.AGE, 0);
                for (BlockPos pos : positions){
                    world.setBlockState(pos, thornBlockState, 3);
                }
            }
        }, windup);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BlockState thornBlockState = ModBlocks.THORN.getDefaultState().with(ThornBlock.AGE, 1);
                for (BlockPos pos : positions){
                    world.setBlockState(pos, thornBlockState, 3);
                }
            }
        }, windup+250);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (BlockPos pos : positions) {
                    if (world.getBlockState(pos).getBlock().equals(ModBlocks.THORN)){
                        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                    }
                }
            }
        }, windup+duration);

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

    public static boolean launch(World world, PlayerEntity player){
        float horizontalSpeed = 0.5f;
        float verticalSpeed = 1.0f;
        float damage = 8.0f;
        int range = 30;
        int radius = 3;
        int delay = 250;
        int yForgiveness = 5;

        Timer timer = new Timer();

        BlockPos targetBlock = Detection.raycastGetBlock(world, player, range);
        if(targetBlock == null){return false;}

        boolean nearTheGround = false;
        for(int i = 0; i <= yForgiveness; i++){
            if(world.isTopSolid(targetBlock.down(i), player)){
                nearTheGround = true;
            }
        }
        if(!nearTheGround){return false;}

        List<Entity> entities = Detection.getEntitiesNearbyBlock(world, targetBlock, radius);

        Vec3d direction = player.getRotationVec(1.0F);
        Vec3d horizontalVelocity = direction.multiply(horizontalSpeed);
        Vec3d finalVelocity = new Vec3d(horizontalVelocity.x, verticalSpeed, horizontalVelocity.z);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for(Entity entity: entities){
                    entity.setVelocity(finalVelocity);
                    entity.velocityModified = true;
                    if (entity != player){
                        entity.damage(ModDamageTypes.of(world, ModDamageTypes.EARTH), damage);
                    }
                }
            }
        }, delay);

        BlockPos targetPos = targetBlock.up();
        while (REPLACEABLE_BY_SPELL.contains(world.getBlockState(targetPos).getBlock())
                && targetPos.getY() > world.getBottomY()) {
            targetPos = targetPos.down();
        }

        // Now `targetPos` is at the closest ground level (or bottom of the world if none found)
        BoulderEntity boulderEntity = new BoulderEntity(world);
        boulderEntity.setPos(targetPos.getX() + 0.5, targetPos.getY()+1, targetPos.getZ() + 0.5);
        world.spawnEntity(boulderEntity);
        boulderEntity.earthquakeAnimationState.start(boulderEntity.age);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                boulderEntity.kill();
            }
        }, 5000);

        return true;
    }

    public static boolean pillar(World world, PlayerEntity player){
        int delay = 500;
        int yForgiveness = 5;
        int duration = 30000;

        Timer timer = new Timer();

        BlockPos targetPos = player.getBlockPos();

        boolean nearTheGround = false;
        for(int i = 0; i <= yForgiveness; i++){
            if(world.isTopSolid(targetPos.down(i), player)){
                nearTheGround = true;
            }
        }
        if(!nearTheGround){return false;}

        while (REPLACEABLE_BY_SPELL.contains(world.getBlockState(targetPos).getBlock())
                && targetPos.getY() > world.getBottomY()) {
            targetPos = targetPos.down();
        }

        Vec3d currentVelocity = player.getVelocity();
        player.setVelocity(currentVelocity.x, currentVelocity.y + 1.0f, currentVelocity.z);
        player.velocityDirty = true;

        PillarEntity pillarEntity = new PillarEntity(world);
        pillarEntity.setPos(targetPos.getX() + 0.5, targetPos.getY()+1, targetPos.getZ() + 0.5);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                world.spawnEntity(pillarEntity);
            }
        }, delay);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                pillarEntity.kill();
            }
        }, duration);

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
