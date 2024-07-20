package net.finalpeak.modmod.events;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.finalpeak.modmod.item.custom.GnomicTomeItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;

public class EventHandlers {
    private static boolean wasLeftClicking = false;  // Corrected to be static

    public static void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                handleLeftClick(client.player);
            }
        });
    }

    private static void handleLeftClick(PlayerEntity player) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options.attackKey.isPressed()) {
            if (!wasLeftClicking) {
                ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);
                if (stack.getItem() instanceof GnomicTomeItem) {
                    player.sendMessage(new LiteralText("left click"), true);
                }
            }
            wasLeftClicking = true;
        } else {
            wasLeftClicking = false;
        }
    }
}
