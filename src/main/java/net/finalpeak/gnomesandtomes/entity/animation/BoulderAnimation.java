package net.finalpeak.gnomesandtomes.entity.animation;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

/**
 * Made with Blockbench 4.11.2
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 * @author Author
 */
public class BoulderAnimation {
	public static final Animation EARTHQUAKE = Animation.Builder.create(5.0F)
			.addBoneAnimation("boulder", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(2.0F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(2.125F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, 2.5F), Transformation.Interpolations.LINEAR),
					new Keyframe(2.25F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, -2.5F), Transformation.Interpolations.LINEAR),
					new Keyframe(2.375F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, 2.5F), Transformation.Interpolations.LINEAR),
					new Keyframe(2.5F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, -2.5F), Transformation.Interpolations.LINEAR),
					new Keyframe(2.625F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, 2.5F), Transformation.Interpolations.LINEAR),
					new Keyframe(2.75F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, -2.5F), Transformation.Interpolations.LINEAR),
					new Keyframe(2.875F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, 2.5F), Transformation.Interpolations.LINEAR),
					new Keyframe(3.0F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, -2.5F), Transformation.Interpolations.LINEAR),
					new Keyframe(3.125F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, 2.5F), Transformation.Interpolations.LINEAR),
					new Keyframe(3.25F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, -2.5F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("boulder", new Transformation(Transformation.Targets.TRANSLATE,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -13.0F, -6.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.125F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(3.5F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(5.0F, AnimationHelper.createTranslationalVector(0.0F, -8.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.build();
}