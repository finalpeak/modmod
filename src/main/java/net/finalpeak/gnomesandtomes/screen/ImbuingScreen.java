package net.finalpeak.gnomesandtomes.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ImbuingScreen extends HandledScreen<ImbuingScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(GnomesAndTomes.MOD_ID, "textures/gui/imbuing_table_gui.png");

    public ImbuingScreen(ImbuingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleY = 1000;
        playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int backgroundWidth = 176;
        int backgroundHeight = 210;

        // Center the texture
        int x = (this.width - backgroundWidth) / 2;
        int y = (this.height - backgroundHeight) / 2;

        // Draw the texture scaled properly
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, 176, 210);

        renderProgress(context, x, y);
    }



    private void renderProgress(DrawContext context, int x, int y) {
        if(handler.isCrafting()){
            context.drawTexture(TEXTURE, x + 85, y + 30, 176, 0, 6, handler.getScaledProgress());
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}