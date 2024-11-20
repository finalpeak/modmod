// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.finalpeak.gnomesandtomes.entity.client;

import net.finalpeak.gnomesandtomes.entity.animation.GnomeAnimation;
import net.finalpeak.gnomesandtomes.entity.custom.GnomeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class GnomeModel<T extends GnomeEntity> extends SinglePartEntityModel<T> {
    private final ModelPart boulder;

    public GnomeModel(ModelPart root) {
        this.boulder = root.getChild("boulder");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData boulder = modelPartData.addChild("boulder", ModelPartBuilder.create().uv(12, 6).cuboid(-4.5F, -5.0F, -3.75F, 8.0F, 7.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = boulder.addChild("cube_r1", ModelPartBuilder.create().uv(4, 40).cuboid(2.0F, -6.0F, -10.0F, 6.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(5.5F, 3.0F, 3.25F, 0.0F, 1.2217F, 0.0F));

        ModelPartData cube_r2 = boulder.addChild("cube_r2", ModelPartBuilder.create().uv(30, 27).cuboid(2.0F, -6.0F, -10.0F, 6.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-7.5F, 3.0F, 6.25F, 0.0F, 0.2182F, 0.0F));

        ModelPartData cube_r3 = boulder.addChild("cube_r3", ModelPartBuilder.create().uv(17, 51).cuboid(2.0F, -6.0F, -10.0F, 6.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-5.5F, 3.0F, 4.25F, 0.0F, -0.6109F, 0.0F));

        ModelPartData cube_r4 = boulder.addChild("cube_r4", ModelPartBuilder.create().uv(37, 47).cuboid(2.0F, -10.0F, -7.0F, 5.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-4.5F, 4.0F, -0.75F, 0.0F, -0.5672F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(GnomeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(GnomeAnimation.EARTHQUAKE, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, GnomeAnimation.EARTHQUAKE, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        //this.head.yaw = headYaw * 0.017453292F;
        //this.head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        boulder.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return boulder;
    }
}