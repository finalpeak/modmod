// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.finalpeak.gnomesandtomes.entity.client;

import net.finalpeak.gnomesandtomes.entity.custom.PillarEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class PillarModel<T extends PillarEntity> extends SinglePartEntityModel<T> {
	private final ModelPart pillar;
	public PillarModel(ModelPart root) {
		this.pillar = root.getChild("pillar");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData pillar = modelPartData.addChild("pillar", ModelPartBuilder.create().uv(30, 0).cuboid(-4.0F, 6.0F, -4.0F, 8.0F, 2.0F, 8.0F, new Dilation(0.0F))
		.uv(30, 16).cuboid(-4.0F, -18.0F, -4.0F, 8.0F, 2.0F, 8.0F, new Dilation(0.0F))
		.uv(4, 30).cuboid(-2.0F, -16.0F, -2.0F, 4.0F, 22.0F, 4.0F, new Dilation(0.0F))
		.uv(22, 40).cuboid(2.0F, -3.0F, -2.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F))
		.uv(34, 36).cuboid(2.0F, -16.0F, 0.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(24, 40).cuboid(2.0F, -16.0F, -2.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F))
		.uv(38, 41).cuboid(0.0F, -16.0F, 2.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(40, 41).cuboid(-4.0F, -16.0F, 2.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(4, 2).mirrored().cuboid(2.0F, -16.0F, 2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(40, 6).cuboid(2.0F, -16.0F, -4.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
		.uv(8, 10).cuboid(2.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(38, 14).cuboid(0.0F, 2.0F, 2.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(30, 18).cuboid(-2.0F, -2.0F, 2.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F))
		.uv(2, 22).mirrored().cuboid(-2.0F, -16.0F, 2.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(42, 42).cuboid(2.0F, 4.0F, -4.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(24, 42).cuboid(-4.0F, 0.0F, 2.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(4, 42).cuboid(-4.0F, 2.0F, -4.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(28, 42).cuboid(0.0F, 0.0F, -4.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(20, 38).mirrored().cuboid(0.0F, -16.0F, -4.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(8, 43).mirrored().cuboid(-2.0F, -16.0F, -4.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(14, 43).cuboid(-4.0F, -16.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(10, 43).cuboid(-2.0F, -7.0F, -4.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F))
		.uv(4, 0).mirrored().cuboid(-4.0F, -2.0F, -2.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(20, 2).mirrored().cuboid(-4.0F, -7.0F, 0.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		.uv(38, 4).cuboid(-4.0F, -16.0F, 0.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		pillar.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return pillar;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {	}
}