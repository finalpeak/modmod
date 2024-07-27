package net.finalpeak.modmod.events;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.finalpeak.modmod.item.custom.GnomicTomeItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.finalpeak.modmod.client.TomeOverlay;
import java.util.Timer;
import java.util.TimerTask;

public class EventHandlers {
    private static boolean wasLeftClicking = false; // Track if the left click was pressed last tick
    private static ItemStack previousStack = ItemStack.EMPTY; // Track the previously held item stack
    private static boolean halter = false;

    // Register client tick event to handle item interactions and changes
    public static void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.world != null) {
                handleLeftClick(client.player, client.world); // Handle left-click interactions

                // Get the currently held item stack
                ItemStack currentStack = client.player.getStackInHand(client.player.getActiveHand());

                // Check if the held item has changed
                if (!currentStack.equals(previousStack)) {
                    // If the previous item is a GnomicTomeItem, clear its inputs
                    if (previousStack.getItem() instanceof GnomicTomeItem tomeItem) {
                        tomeItem.clearInputs(); // Clear inputs for the previous item
                        tomeItem.resetSpelling();
                    }
                    // Update the previousStack to the new item
                    previousStack = currentStack;
                }
            }
        });

        // Register HUD render callback
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                ItemStack heldItem = client.player.getStackInHand(client.player.getActiveHand());
                if (heldItem.getItem() instanceof GnomicTomeItem tomeItem) {
                    MatrixStack matrices = new MatrixStack();
                    TomeOverlay.render(matrices);
                }
            }
        });
    }

    // Handle left-click interactions
    private static void handleLeftClick(PlayerEntity player, World world) {
        MinecraftClient client = MinecraftClient.getInstance();

        // Check if the attack key (left-click) is pressed
        if (client.options.attackKey.isPressed()) {

            // Only process if the left click was not pressed last tick
            if (!wasLeftClicking) {
                ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);

                // Check if the item is an instance of GnomicTomeItem
                if (stack.getItem() instanceof GnomicTomeItem tomeItem && !halter) {
                    tomeItem.input("L", world, player);
                    halter = true;
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            halter = false;
                        }
                    }, 50);
                    // Add input
                }

                // Mark that the left click was pressed this tick
                wasLeftClicking = true;
            } else {
                // Reset the left click state when the attack key is not pressed
                wasLeftClicking = false;
            }
        }
    }
}