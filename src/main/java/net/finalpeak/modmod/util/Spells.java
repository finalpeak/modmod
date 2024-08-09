package net.finalpeak.modmod.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Spells {

    // Spell 1: Summon lightning at the block the player is looking at
    public static boolean lightning(World world, PlayerEntity player) {
        int range = 30;
        Timer timer = new Timer();

        if(Detection.raycastGetBlock(world, player, range) == null){
            return false;
        }

        BlockPos blockPos = Detection.raycastGetBlock(world, player, range);
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;

            // Particle type, position coordinates (x, y, z), and velocity components (x, y, z)
            serverWorld.spawnParticles(ParticleTypes.GLOW,
                    blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5,
                    300, // Number of particles
                    0, 10, 0, // x, y, z offsets
                    0.1 // Particle speed
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
                }
            }
        }, 500);
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


    public static boolean launch(World world, PlayerEntity player, double horizontalSpeed, double verticalSpeed){

        Entity targetEntity = Detection.raycastGetEntity(world, player, 30);
        if(targetEntity == null){
            return false;
        }

        List<Entity> entities = Detection.nearbyEntities(targetEntity, 5);

        Vec3d direction = player.getRotationVec(1.0F);
        Vec3d horizontalVelocity = direction.multiply(horizontalSpeed);
        Vec3d finalVelocity = new Vec3d(horizontalVelocity.x, verticalSpeed, horizontalVelocity.z);

        for(Entity entity: entities){
            if(entity != player) {
                entity.setVelocity(finalVelocity);
                entity.velocityModified = true;
            }
        }

        return true;
    }
}
