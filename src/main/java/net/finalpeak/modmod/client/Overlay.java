package net.finalpeak.modmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.finalpeak.modmod.item.custom.GnomicTomeItem;
import net.finalpeak.modmod.item.custom.MagicTool;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Overlay extends DrawableHelper {

    // These should be non-static and protected to allow subclasses to override them
    protected Identifier baseOverlay;
    protected Identifier rightClick;
    protected Identifier leftClick;

    // Constructor to initialize the textures (can be called by subclasses)
    public Overlay(Identifier baseOverlay, Identifier rightClick, Identifier leftClick) {
        this.baseOverlay = baseOverlay;
        this.rightClick = rightClick;
        this.leftClick = leftClick;
    }

    public void render(MatrixStack matrices) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            ItemStack heldItem = client.player.getMainHandStack();

            // Default Overlay
            if (heldItem.getItem() instanceof MagicTool item) {

                // Bind the custom HUD texture
                RenderSystem.setShaderTexture(0, baseOverlay);

                // Center overlay
                int x = (client.getWindow().getScaledWidth() - 256) / 2;
                int y = (client.getWindow().getScaledHeight() - 256) / 2;

                // Draw the overlay
                drawTexture(matrices, x, y, 0, 0, 256, 256, 256, 256);

                float sizeCof = 1.75f;

                // Slot 1
                if (!item.getInputs().isEmpty()) {
                    checkLR(item, 0);

                    x = (int)((client.getWindow().getScaledWidth() - 256) / 2 - 35 * sizeCof);
                    y = (int)((client.getWindow().getScaledHeight() - 256) / 2 - 20 * sizeCof);
                    drawTexture(matrices, x, y, 0, 0, 256, 256, 256, 256);
                }

                // Slot 2
                if (item.getInputs().size() >= 2) {
                    checkLR(item, 1);

                    x = (int)((client.getWindow().getScaledWidth() - 256) / 2 + 35 * sizeCof); // Adjusted to prevent overlap
                    y = (int)((client.getWindow().getScaledHeight() - 256) / 2 - 20 * sizeCof);
                    drawTexture(matrices, x, y, 0, 0, 256, 256, 256, 256);
                }

                // Slot 3
                if (item.getInputs().size() >= 3) {
                    checkLR(item, 2);

                    x = (client.getWindow().getScaledWidth() - 256) / 2;
                    y = (int)((client.getWindow().getScaledHeight() - 256) / 2 + 40 * sizeCof); // Adjusted to prevent overlap
                    drawTexture(matrices, x, y, 0, 0, 256, 256, 256, 256);
                }
            }
        }
    }

    protected void checkLR(MagicTool tomeItem, int index) {
        if (tomeItem.getInputs().get(index).equals("R")) {
            RenderSystem.setShaderTexture(0, rightClick);
        } else {
            RenderSystem.setShaderTexture(0, leftClick);
        }
    }
}

