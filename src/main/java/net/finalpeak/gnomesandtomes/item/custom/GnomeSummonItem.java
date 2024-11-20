package net.finalpeak.gnomesandtomes.item.custom;

import net.finalpeak.gnomesandtomes.entity.ModEntities;
import net.finalpeak.gnomesandtomes.entity.custom.GnomeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.Timer;
import java.util.TimerTask;

public class GnomeSummonItem extends Item {
    public GnomeSummonItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        int delay = 3000; // In ms

        if (entity.getType() == EntityType.PLAYER || (
                entity.getType().getSpawnGroup() != SpawnGroup.CREATURE ||
                entity.getType().getSpawnGroup() != SpawnGroup.MONSTER )) {
            return ActionResult.FAIL;
        }

        World world = user.getWorld();
        BlockPos pos = entity.getBlockPos();
        Timer timer = new Timer();

        if (entity.getType().getSpawnGroup() == SpawnGroup.CREATURE) {
            ParticleEffect particles = ParticleTypes.WITCH;
            Entity outputEntity = new GnomeEntity(ModEntities.GNOME, world);
        }
        else{
            ParticleEffect particles = new DustParticleEffect(new Vector3f(1.0F, 0F, 0F), 1.0F);
            //Entity outputEntity = new monster
        }

        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;

            for(int i = 0; i <= delay; i+=500) {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        serverWorld.spawnParticles(
                                particles,
                                pos.getX(), pos.getY() + 0.5, pos.getZ(),
                                100,
                                0.25, 0.25, 0.25,
                                1
                        );
                    }
                }, i);
            }
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    entity.remove(Entity.RemovalReason.DISCARDED);
                    world.spawnEntity(new GnomeEntity(ModEntities.GNOME, world));
                }
            }, delay);
        }

        return ActionResult.CONSUME;
    }
}
