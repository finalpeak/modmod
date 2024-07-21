package net.finalpeak.modmod.events;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.finalpeak.modmod.item.custom.GnomicTomeItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EventHandlers {
    private static boolean wasLeftClicking = false; // Track if the left click was pressed last tick
    private static ItemStack previousStack = ItemStack.EMPTY; // Track the previously held item stack

    // Register client tick event to handle item interactions and changes
    public static void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Ensure we have access to the player and world
            if (client.player != null && client.world != null) {
                handleLeftClick(client.player, client.world); // Handle left-click interactions

                // Get the currently held item stack
                ItemStack currentStack = client.player.getStackInHand(client.player.getActiveHand());

                // Check if the held item has changed
                if (!currentStack.equals(previousStack)) {
                    // If the previous item is a GnomicTomeItem, clear its inputs
                    if (previousStack.getItem() instanceof GnomicTomeItem) {
                        GnomicTomeItem tomeItem = (GnomicTomeItem) previousStack.getItem();
                        tomeItem.clearInputs(); // Clear inputs for the previous item
                    }
                    // Update the previousStack to the new item
                    previousStack = currentStack;
                }
            }
        });
    }

    // Handle left-click interactions
    private static void handleLeftClick(PlayerEntity player, World world) {
        // Ensure client instance is available
        MinecraftClient client = MinecraftClient.getInstance();

        // Check if the attack key (left-click) is pressed
        if (client.options.attackKey.isPressed()) {
            // Only process if the left click was not pressed last tick
            if (!wasLeftClicking) {
                ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);

                // Check if the item is an instance of GnomicTomeItem
                if (stack.getItem() instanceof GnomicTomeItem) {
                    GnomicTomeItem tomeItem = (GnomicTomeItem) stack.getItem();

                    // Add input and send inputs to player if there are less than 3 inputs
                    if (!tomeItem.getInputs().isEmpty() && tomeItem.getInputs().size() < 3) {
                        tomeItem.addInput("L");
                        tomeItem.sendInputs(player);
                    }

                    // If exactly 3 inputs are present, trigger spells
                    if (tomeItem.getInputs().size() == 3) {
                        tomeItem.spells(world, player);
                    }
                }
            }
            // Mark that the left click was pressed this tick
            wasLeftClicking = true;
        } else {
            // Reset the left click state when the attack key is not pressed
            wasLeftClicking = false;
        }
    }
}