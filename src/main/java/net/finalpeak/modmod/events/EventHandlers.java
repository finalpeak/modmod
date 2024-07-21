package net.finalpeak.modmod.events;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.finalpeak.modmod.item.custom.GnomicTomeItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EventHandlers {
    private static boolean wasLeftClicking = false;  // Corrected to be static

    public static void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.world != null) {
                handleLeftClick(client.player, client.world);
            }
        });
    }

    private static void handleLeftClick(PlayerEntity player, World world) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options.attackKey.isPressed()) {
            if (!wasLeftClicking) {
                ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);
                if (stack.getItem() instanceof GnomicTomeItem) {
                    GnomicTomeItem tomeItem = (GnomicTomeItem) stack.getItem();
                    if (!tomeItem.getInputs().isEmpty() && tomeItem.getInputs().size() < 3){
                        tomeItem.addInput("L");
                        tomeItem.sendInputs(player);
                    }
                    if(tomeItem.getInputs().size() == 3){
                        tomeItem.spells(world, player);
                    }
                }
            }
            wasLeftClicking = true;
        } else {
            wasLeftClicking = false;
        }
    }
}
