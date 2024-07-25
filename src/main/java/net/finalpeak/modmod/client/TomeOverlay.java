package net.finalpeak.modmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.finalpeak.modmod.item.custom.GnomicTomeItem;
import net.minecraft.client.gui.DrawableHelper;

public class TomeOverlay extends DrawableHelper {
    private static final Identifier HUD_TEXTURE = new Identifier("modid", "textures/gui/custom_hud.png");

    public static void render(MatrixStack matrices) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            ItemStack heldItem = client.player.getMainHandStack();
            if (heldItem.getItem() instanceof GnomicTomeItem) {
                // Bind the custom HUD texture
                RenderSystem.setShaderTexture(0, HUD_TEXTURE);

                // Define the position and size of the HUD
                int x = (client.getWindow().getScaledWidth() - 256) / 2; // Center the HUD
                int y = 10; // Adjust as needed

                // Draw the HUD
                drawTexture(matrices, x, y, 0, 0, 256, 256, 256, 256);
            }
        }
    }
}
