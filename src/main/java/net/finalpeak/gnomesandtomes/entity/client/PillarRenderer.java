package net.finalpeak.gnomesandtomes.entity.client;

import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.finalpeak.gnomesandtomes.entity.custom.PillarEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class PillarRenderer extends EntityRenderer<PillarEntity> {
    private static final Identifier TEXTURE = new Identifier(GnomesAndTomes.MOD_ID, "textures/entity/pillar.png");
    private final PillarModel<PillarEntity> model;  // Correctly declare the model type

    public PillarRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new PillarModel<>(context.getPart(ModModelLayers.PILLAR));
    }

    public void render(PillarEntity pillar, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();

        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180.0F)); // Rotate
        matrixStack.scale(5f, 5f, 5f); // Scale
        matrixStack.translate(0.0, -1.5, 0.0); // Position (NOT IN BLOCKS)
        this.model.setAngles(pillar, 0.0F, 0.0F, 0.0F, 0, 0); // Orientation

        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();

        super.render(pillar, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(PillarEntity entity) {
        return TEXTURE;
    }
}
