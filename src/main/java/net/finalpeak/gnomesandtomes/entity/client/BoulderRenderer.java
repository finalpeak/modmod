package net.finalpeak.gnomesandtomes.entity.client;

import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.finalpeak.gnomesandtomes.entity.custom.BoulderEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class BoulderRenderer extends EntityRenderer<BoulderEntity> {
    private static final Identifier TEXTURE = new Identifier(GnomesAndTomes.MOD_ID, "textures/entity/boulder.png");
    private final BoulderModel<BoulderEntity> model;  // Correctly declare the model type

    public BoulderRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new BoulderModel<>(context.getPart(ModModelLayers.BOULDER));
    }

    public void render(BoulderEntity boulder, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();

        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0F)); // Rotate
        matrixStack.scale(5f, 5f, 5f); // Scale
        matrixStack.translate(0.0, -1.5, 0.0); // Position (NOT IN BLOCKS)
        this.model.setAngles(boulder, 0.0F, 0.0F, 0.0F, 0, 0); // Orientation

        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();

        super.render(boulder, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(BoulderEntity entity) {
        return TEXTURE;
    }
}
