package net.finalpeak.modmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.finalpeak.modmod.item.custom.MagicTool;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Overlay {

    protected Identifier baseOverlay;
    protected Identifier rightClick;
    protected Identifier leftClick;

    public Overlay(Identifier baseOverlay, Identifier rightClick, Identifier leftClick) {
        this.baseOverlay = baseOverlay;
        this.rightClick = rightClick;
        this.leftClick = leftClick;
    }

    public void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            ItemStack heldItem = client.player.getMainHandStack();

            if (heldItem.getItem() instanceof MagicTool item) {
                RenderSystem.setShaderTexture(0, baseOverlay);

                int x = (client.getWindow().getScaledWidth() - 256) / 2;
                int y = (client.getWindow().getScaledHeight() - 256) / 2;

                context.drawTexture(baseOverlay, x, y, 0, 0, 256, 256, 256, 256);

                float sizeCof = 1.75f;

                if (!item.getInputs().isEmpty()) {


                    x = (int)((client.getWindow().getScaledWidth() - 256) / 2 - 35 * sizeCof);
                    y = (int)((client.getWindow().getScaledHeight() - 256) / 2 - 20 * sizeCof);
                    context.drawTexture(
                            checkLR(item, 0, context),
                            x, y, 0, 0, 256, 256, 256, 256);
                }

                if (item.getInputs().size() >= 2) {
                    checkLR(item, 1, context);

                    x = (int)((client.getWindow().getScaledWidth() - 256) / 2 + 35 * sizeCof);
                    y = (int)((client.getWindow().getScaledHeight() - 256) / 2 - 20 * sizeCof);
                    context.drawTexture(
                            checkLR(item, 0, context),
                            x, y, 0, 0, 256, 256, 256, 256);
                }

                if (item.getInputs().size() >= 3) {
                    checkLR(item, 2, context);

                    x = (client.getWindow().getScaledWidth() - 256) / 2;
                    y = (int)((client.getWindow().getScaledHeight() - 256) / 2 + 40 * sizeCof);
                    context.drawTexture(
                            checkLR(item, 0, context),
                            x, y, 0, 0, 256, 256, 256, 256);
                }
            }
        }
    }

    protected Identifier checkLR(MagicTool item, int index, DrawContext context) {
        if (item.getInputs().get(index).equals("R")) {
           return rightClick;
        } else {
            return leftClick;
        }
    }
}
