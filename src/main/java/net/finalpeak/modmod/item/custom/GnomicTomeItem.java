package net.finalpeak.modmod.item.custom;

import net.finalpeak.modmod.util.Raycaster;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class GnomicTomeItem extends Item {
    // List to store inputs for the tome
    private List<String> inputs = new ArrayList<>();

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
            // Clear inputs if there are already 3
            if (inputs.size() >= 3) {
                inputs.clear();
            }

            // Add "R" to inputs and send to player
            inputs.add("R");
            sendInputs(user);

            // If inputs size is 3, trigger spells
            if (inputs.size() == 3) {
                spells(world, user);
            }
        }

        return super.use(world, user, hand);
    }

    // Check and cast spells based on inputs
    public void spells(World world, PlayerEntity player) {
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
        if (inputs.equals(new ArrayList<>(Arrays.asList("R", "R", "L")))) {
            if (spell3(world, player)) {
                removeMagic(1);
            }
        }
        if (inputs.equals(new ArrayList<>(Arrays.asList("R", "L", "L")))) {
            if (spell4(world, player)) {
                removeMagic(1);
            }
        }
    }

    // Spell 1: Summon lightning at the block the player is looking at
    public boolean spell1(World world, PlayerEntity player) {
        BlockPos blockPos = Raycaster.getBlockOrEntity(world, player);

        LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
        if (lightning != null) {
            assert blockPos != null;
            lightning.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
            world.spawnEntity(lightning);
            return true;
        }

        return false;
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
}
