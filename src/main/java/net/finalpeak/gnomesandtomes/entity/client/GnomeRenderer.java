package net.finalpeak.gnomesandtomes.entity.client;

import net.finalpeak.gnomesandtomes.GnomesAndTomes;
import net.finalpeak.gnomesandtomes.entity.custom.GnomeEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GnomeRenderer extends EntityRenderer<GnomeEntity> {
    private static final Identifier TEXTURE = new Identifier(GnomesAndTomes.MOD_ID, "textures/entity/stonecutout.png");
    private final GnomeModel<GnomeEntity> model;

    public GnomeRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new GnomeModel<>(context.getPart(ModModelLayers.BOULDER));
    }

    @Override
    public void render(GnomeEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        if (entity.isBaby()) {
            matrices.scale(0.5f, 0.5f, 0.5f);
        }

        this.model.setAngles(entity, 0, 0, entity.age, entity.getYaw(tickDelta), entity.getPitch(tickDelta));
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(GnomeEntity entity) {
        return TEXTURE;
    }
}