package net.finalpeak.modmod.client.overlay;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class ShardOverlay extends Overlay {

    private static final Identifier SHARD_BASE_OVERLAY = new Identifier("modmod", "textures/gui/shard_0.png");
    private static final Identifier SHARD_RIGHT_CLICK = new Identifier("modmod", "textures/gui/shard_right.png");
    private static final Identifier SHARD_LEFT_CLICK = new Identifier("modmod", "textures/gui/shard_left.png");

    // Constructor calls the superclass constructor with specific textures
    public ShardOverlay() {
        super(SHARD_BASE_OVERLAY, SHARD_RIGHT_CLICK, SHARD_LEFT_CLICK);
    }

    // You can override the render method if additional logic is needed
    @Override
    public void render(DrawContext context) {
        super.render(context); // Call the superclass render method
    }
}
