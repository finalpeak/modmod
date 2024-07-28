package net.finalpeak.modmod.item.custom;

import net.finalpeak.modmod.util.Raycaster;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.Sound;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.*;
import net.minecraft.client.MinecraftClient;

public class GnomicTomeItem extends Item {
    // List to store inputs for the tome
    private List<String> inputs = new ArrayList<>();
    private static boolean spelling = false;

    public GnomicTomeItem(Settings settings) {
        super(settings);
    }

    // Add input to the inputs list
    public void addInput(String input) {
        inputs.add(input);
    }

    // Get the current inputs
    public List<String> getInputs() {
        return inputs;
    }

    // Clear the current inputs
    public void clearInputs(){
        inputs.clear();
    }

    // Send inputs to the player as a message
    public void sendInputs(PlayerEntity user) {
        String msg = inputs.get(0);
        for (int i = 1; i < inputs.size(); i++) {
            msg += " - " + inputs.get(i);
        }
        user.sendMessage(new LiteralText(msg), true);
    }

    public void resetSpelling(){
        spelling = false;
    }

    // Add tooltips to the item
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.modmod.gnomic_tome.tooltip.shift"));
            tooltip.add(new TranslatableText("item.modmod.gnomic_tome.tooltip.spell1"));
            tooltip.add(new TranslatableText("item.modmod.gnomic_tome.tooltip.spell2"));
            tooltip.add(new TranslatableText("item.modmod.gnomic_tome.tooltip.spell3"));
            tooltip.add(new TranslatableText("item.modmod.gnomic_tome.tooltip.spell4"));
        } else {
            tooltip.add(new TranslatableText("item.modmod.gnomic_tome.tooltip"));
        }
    }

    // Handle the use of the item
    @Override
    public TypedActionResult<ItemStack> use(World world, @NotNull PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            input("R", world, user);
        }

        return super.use(world, user, hand);
    }

    public void input(String input, World world, @NotNull PlayerEntity user) {
        SoundEvent sound;
        if(spelling){
            playSound(world, user, SoundEvents.ENTITY_BLAZE_HURT);

        }else {

            // Clear inputs if there are already 3
            if (inputs.size() >= 3) {
                inputs.clear();
            }

            // Play sounds
            if (input.equals("R")) {
                playSound(world, user, SoundEvents.BLOCK_NOTE_BLOCK_HARP);
            } else {
                playSound(world, user, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP);
            }

            // Add input and send to player
            inputs.add(input);
            sendInputs(user);

            // If inputs size is 3, trigger spells
            if (inputs.size() == 3) {
                spells(world, user);
            }

            /*Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (inputs.size() < 3) {
                        inputs.clear();
                    }
                }
            }, 3000);*/
        }
    }

    // Check and cast spells based on inputs
    public void spells(World world, PlayerEntity player) {
        spelling = true;

        if (inputs.equals(new ArrayList<>(Arrays.asList("R", "R", "R")))) {
            if (spell1(world, player)) {
                removeMagic(1);
            }
        }
        if (inputs.equals(new ArrayList<>(Arrays.asList("R", "L", "R")))) {
            if (spell2(world, player)) {
                removeMagic(1);
            }
        }
        if (inputs.equals(new ArrayList<>(Arrays.asList("L", "L", "L")))) {
            if (spell3(world, player)) {
                removeMagic(1);
            }
        }
        if (inputs.equals(new ArrayList<>(Arrays.asList("L", "R", "L")))) {
            if (spell4(world, player)) {
                removeMagic(1);
            }
        }
    }

    // Spell 1: Summon lightning at the block the player is looking at
    public boolean spell1(World world, PlayerEntity player) {
        Timer timer = new Timer();

        if(Raycaster.getBlockOrEntity(world, player) == null){
            // Always clear inputs and reset spelling
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    inputs.clear();
                    spelling = false;
                }
            }, 500);
            return false;
        }

        BlockPos blockPos = Raycaster.getBlockOrEntity(world, player);

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

                // Always clear inputs and reset spelling
                inputs.clear();
                spelling = false;
            }
        }, 500);
        return true;
    }

    public boolean spell2(World world, PlayerEntity player){
        // Implement spell2
        return false;
    }

    public boolean spell3(World world, PlayerEntity player){
        // Implement spell3
        return false;
    }

    public boolean spell4(World world, PlayerEntity player){
        // Implement spell4
        return false;
    }

    // Placeholder for removing magic, to be implemented
    public void removeMagic(int value) {
        // Implement magic storage/usage logic
    }

    public void playSound(World world, @NotNull PlayerEntity user, SoundEvent sound){
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
