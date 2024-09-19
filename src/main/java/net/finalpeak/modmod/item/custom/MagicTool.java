package net.finalpeak.modmod.item.custom;

import net.finalpeak.modmod.item.custom.util.Sounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MagicTool extends Item {

    protected List<String> inputs = new ArrayList<>();
    protected static boolean spelling = false;

    public MagicTool(Item.Settings settings) {
        super(settings);
    }

    public void addInput(String input) {
        inputs.add(input);
    }

    public List<String> getInputs() {
        return inputs;
    }

    public void clearInputs() {
        inputs.clear();
    }

    public void resetSpelling() {
        spelling = false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, @NotNull PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            input("R", world, user);
        }
        return super.use(world, user, hand);
    }

    public void input(String input, World world, @NotNull PlayerEntity user) {
        if (spelling) {
            Sounds.playSound(world, user, SoundEvents.ENTITY_BLAZE_HURT);
            return;
        }

        if (inputs.size() >= 3) {
            clearInputs(); // Clear inputs if the list is full
        }

        // Add input and play the corresponding sound
        inputs.add(input);
        if (input.equals("R")) {
            Sounds.playSound(world, user, SoundEvents.ENTITY_ARROW_HIT);
        } else {
            Sounds.playSound(world, user, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP);
        }

        // If three inputs are registered, cast the spell
        if (inputs.size() == 3) {
            spells(world, user);
        }
    }

    public void spells(World world, PlayerEntity player) {
        // This will be overridden by subclasses like EarthenStaffItem
    }

    public void removeMagic(int value) {
        // Implement magic storage/usage logic
    }
}
