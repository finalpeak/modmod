package net.finalpeak.modmod.client;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class StaffOverlay extends Overlay {

    private static final Identifier STAFF_BASE_OVERLAY = new Identifier("modmod", "textures/gui/staff_0.png");
    private static final Identifier STAFF_RIGHT_CLICK = new Identifier("modmod", "textures/gui/staff_right.png");
    private static final Identifier STAFF_LEFT_CLICK = new Identifier("modmod", "textures/gui/staff_left.png");

    // Constructor calls the superclass constructor with specific textures
    public StaffOverlay() {
        super(STAFF_BASE_OVERLAY, STAFF_RIGHT_CLICK, STAFF_LEFT_CLICK);
    }

    // You can override the render method if additional logic is needed
    @Override
    public void render(MatrixStack matrices) {
        super.render(matrices); // Call the superclass render method
    }
}
