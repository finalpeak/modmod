package net.finalpeak.modmod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class Sounds {

    public static void playSound(World world, @NotNull PlayerEntity user, SoundEvent sound){
        if (!world.isClient) { // Server-side
            // Send a packet to the player to play the sound
            ((ServerPlayerEntity) user).networkHandler.sendPacket(new PlaySoundS2CPacket(
                    sound,
                    SoundCategory.PLAYERS,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    1.0f,
                    1.0f
            ));
        } else { // Client-side
            // Directly play the sound for the player
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(
                    sound,
                    1.0f
            ));
        }
    }

}
