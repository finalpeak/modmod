package net.finalpeak.modmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.finalpeak.modmod.item.custom.GnomicTomeItem;
import net.minecraft.client.gui.DrawableHelper;

import java.util.*;

public class TomeOverlay extends DrawableHelper {
    private static final Identifier BASE_OVERLAY = new Identifier("modmod", "textures/gui/tome_0.png");
    private static final Identifier RIGHT_CLICK = new Identifier("modmod", "textures/gui/tome_right.png");
    private static final Identifier LEFT_CLICK = new Identifier("modmod", "textures/gui/tome_left.png");

    private static boolean clearSlots = false;
    private static List<String> inputsHolder = new ArrayList<>();

    public static void render(MatrixStack matrices) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            ItemStack heldItem = client.player.getMainHandStack();

            // Default Overlay
            if (heldItem.getItem() instanceof GnomicTomeItem tomeItem) {

                if (inputsHolder != tomeItem.getInputs()){
                    clearSlots = false;
                    inputsHolder = new ArrayList<>(List.of("empty"));
                }

                // Bind the custom HUD texture
                RenderSystem.setShaderTexture(0, BASE_OVERLAY);

                // Center overlay
                int x = (client.getWindow().getScaledWidth() - 256) / 2;
                int y = (client.getWindow().getScaledHeight() - 256) / 2;

                // Draw the overlay
                drawTexture(matrices, x, y, 0, 0, 256, 256, 256, 256);

                // Slot 1
                if (!tomeItem.getInputs().isEmpty() && !clearSlots) {
                    if (tomeItem.getInputs().get(0).equals("R")) {
                        RenderSystem.setShaderTexture(0, RIGHT_CLICK);
                    } else {
                        RenderSystem.setShaderTexture(0, LEFT_CLICK);
                    }

                    x = (client.getWindow().getScaledWidth() - 256) / 2 - 35;
                    y = (client.getWindow().getScaledHeight() - 256) / 2 - 20;
                    drawTexture(matrices, x, y, 0, 0, 256, 256, 256, 256);
                }

                // Slot 2
                if (tomeItem.getInputs().size() >= 2) {
                    if (tomeItem.getInputs().get(1).equals("R")) {
                        RenderSystem.setShaderTexture(0, RIGHT_CLICK);
                    } else {
                        RenderSystem.setShaderTexture(0, LEFT_CLICK);
                    }

                    x = (client.getWindow().getScaledWidth() - 256) / 2 + 35; // Adjusted to prevent overlap
                    y = (client.getWindow().getScaledHeight() - 256) / 2 - 20;
                    drawTexture(matrices, x, y, 0, 0, 256, 256, 256, 256);
                }

                // Slot 3
                if (tomeItem.getInputs().size() >= 3) {
                    if (tomeItem.getInputs().get(2).equals("R")) {
                        RenderSystem.setShaderTexture(0, RIGHT_CLICK);
                    } else {
                        RenderSystem.setShaderTexture(0, LEFT_CLICK);
                    }

                    x = (client.getWindow().getScaledWidth() - 256) / 2;
                    y = (client.getWindow().getScaledHeight() - 256) / 2 + 40; // Adjusted to prevent overlap
                    drawTexture(matrices, x, y, 0, 0, 256, 256, 256, 256);

                    // Schedule clearing the slots after 2 seconds
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            clearSlots = true;
                            inputsHolder = tomeItem.getInputs();
                        }
                    }, 1000);
                }
            }
        }
    }
}