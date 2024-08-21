package net.finalpeak.modmod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class Sounds {

    public static void playSound(World world, @NotNull PlayerEntity user, SoundEvent sound) {
        if (!world.isClient) { // Server-side

            RegistryEntry.Reference<SoundEvent> soundEntry = Registries.SOUND_EVENT.getEntry(Registries.SOUND_EVENT.getKey(sound).orElseThrow())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid SoundEvent"));

                    // Send a packet to the player to play the sound
            ((ServerPlayerEntity) user).networkHandler.sendPacket(new PlaySoundS2CPacket(
                    soundEntry, // Use sound's ID instead of the SoundEvent directly
                    SoundCategory.PLAYERS,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    1.0f,
                    1.0f,
                    world.getRandom().nextLong() // Added seed for uniqueness
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
