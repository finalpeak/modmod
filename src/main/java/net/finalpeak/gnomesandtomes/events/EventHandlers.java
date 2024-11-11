package net.finalpeak.gnomesandtomes.events;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.finalpeak.gnomesandtomes.client.overlay.ShardOverlay;
import net.finalpeak.gnomesandtomes.client.overlay.StaffOverlay;
import net.finalpeak.gnomesandtomes.item.custom.AzureShardItem;
import net.finalpeak.gnomesandtomes.item.custom.EarthenStaffItem;
import net.finalpeak.gnomesandtomes.item.custom.GnomicTomeItem;
import net.finalpeak.gnomesandtomes.item.custom.MagicTool;
import net.finalpeak.gnomesandtomes.item.custom.util.Detection;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.finalpeak.gnomesandtomes.client.overlay.TomeOverlay;
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
                // Handle left-click interactions
                handleLeftClick(client.player, client.world);

                // Get the currently held item stack
                ItemStack currentStack = client.player.getStackInHand(client.player.getActiveHand());
                if (!currentStack.equals(previousStack)) {
                    // If the previous item is a GnomicTomeItem, clear its inputs
                    if (previousStack.getItem() instanceof GnomicTomeItem tomeItem) {
                        tomeItem.clearInputs(); // Clear inputs for the previous item
                        tomeItem.resetSpelling();
                    }
                    if (previousStack.getItem() instanceof EarthenStaffItem staffItem) {
                        staffItem.clearInputs(); // Clear inputs for the previous item
                        staffItem.resetSpelling();
                    }
                    if (previousStack.getItem() instanceof EarthenStaffItem shardItem) {
                        shardItem.clearInputs(); // Clear inputs for the previous item
                        shardItem.resetSpelling();
                    }
                    previousStack = currentStack; // Update the previousStack to the new item

                    //HUD Renderer
                    ItemStack heldItem = client.player.getStackInHand(client.player.getActiveHand());
                    VertexConsumerProvider.Immediate vertexConsumers = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();

                    if (heldItem.getItem() instanceof GnomicTomeItem) {
                        DrawContext context = new DrawContext(client, vertexConsumers);
                        new TomeOverlay().render(context);
                    }
                    else if (heldItem.getItem() instanceof EarthenStaffItem) {
                        DrawContext context = new DrawContext(client, vertexConsumers);
                        new StaffOverlay().render(context);
                    }
                    else if (heldItem.getItem() instanceof AzureShardItem) {
                        DrawContext context = new DrawContext(client, vertexConsumers);
                        new ShardOverlay().render(context);
                    }
                    vertexConsumers.draw(); // Ensure all vertices are drawn

                    // Target entity glowing
                    if (heldItem.getItem() instanceof MagicTool) {
                        int range = 30;
                        Entity target = Detection.raycastGetEntity(client.world, client.player, range);
                        if (target != null){
                            target.setGlowing(true);
                        }
                    }

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
                if (!halter) {
                    Item item = stack.getItem();

                    if (item instanceof GnomicTomeItem magicItem) {
                        magicItem.input("L", world, player);
                    } else if (item instanceof EarthenStaffItem magicItem) {
                        magicItem.input("L", world, player);
                    } else if (item instanceof AzureShardItem magicItem) {
                        magicItem.input("L", world, player);
                    }

                    halter = true;

                    // Use Timer to reset `halter` after a short delay
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            halter = false;
                        }
                    }, 500);
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