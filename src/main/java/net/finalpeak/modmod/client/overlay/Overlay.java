package net.finalpeak.modmod.client.overlay;

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

                inputDrawer(0, -35, -20, sizeCof, item, context, client);
                inputDrawer(1, 35, -20, sizeCof, item, context, client);
                inputDrawer(2, 0, 40, sizeCof, item, context, client);
            }
        }
    }

    public void inputDrawer(int index, int xOffset, int yOffset, float sizeCof,
                            MagicTool item, DrawContext context, MinecraftClient client){
        if (item.getInputs().size() >= index+1) {
            int x = (int)((client.getWindow().getScaledWidth() - 256) / 2 + xOffset * sizeCof);
            int y = (int)((client.getWindow().getScaledHeight() - 256) / 2 + yOffset * sizeCof);
            context.drawTexture(
                    checkLR(item, index, context),
                    x, y, 0, 0, 256, 256, 256, 256);
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
