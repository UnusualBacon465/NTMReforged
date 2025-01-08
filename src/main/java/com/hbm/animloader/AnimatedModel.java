package com.hbm.animloader;

import com.hbm.util.BobMathUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.DefaultVertexFormat;
import net.minecraft.world.phys.Vec3;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class AnimatedModel {

	// Replace GLAllocation with FloatBuffer for modern buffer allocation
	public static FloatBuffer auxGLMatrix = FloatBuffer.allocate(16);

	public AnimationController controller;

	public String name = "";

	public float[] transform;

	boolean hasGeometry = true;
	boolean hasTransform = false;

	public String geo_name = "";
	public AnimatedModel parent;
	public List<AnimatedModel> children = new ArrayList<AnimatedModel>();
	int callList;

	public AnimatedModel() {
	}

	public void renderAnimated(long sysTime) {
		renderAnimated(sysTime, null);
	}

	public void renderAnimated(long sysTime, IAnimatedModelCallback c) {
		if (controller.activeAnim == AnimationWrapper.EMPTY) {
			render(c);
			return;
		}

		AnimationWrapper activeAnim = controller.activeAnim;
		int numKeyFrames = activeAnim.anim.numKeyFrames;
		int diff = (int) (sysTime - activeAnim.startTime);
		diff *= activeAnim.speedScale;

		if (diff > activeAnim.anim.length) {
			int diff2 = diff % activeAnim.anim.length;
			switch (activeAnim.endResult.type) {
				case END:
					controller.activeAnim = AnimationWrapper.EMPTY;
					render(c);
					return;
				case REPEAT:
					activeAnim.startTime = sysTime - diff2;
					break;
				case REPEAT_REVERSE:
					activeAnim.startTime = sysTime - diff2;
					activeAnim.reverse = !activeAnim.reverse;
					break;
				case START_NEW:
					activeAnim.cloneStats(activeAnim.endResult.next);
					activeAnim.startTime = sysTime - diff2;
					break;
				case STAY:
					activeAnim.startTime = sysTime - activeAnim.anim.length;
					break;
			}
		}

		diff = (int) (sysTime - activeAnim.startTime);
		if (activeAnim.reverse) diff = activeAnim.anim.length - diff;
		diff *= activeAnim.speedScale;
		float remappedTime = BobMathUtil.remap(diff, 0, activeAnim.anim.length, 0, numKeyFrames - 1);
		float diffN = BobMathUtil.remap01_clamp(diff, 0, activeAnim.anim.length);
		int index = (int) remappedTime;
		int first = index;
		int next = (index < numKeyFrames - 1) ? index + 1 : first;

		renderWithIndex((float) fract(remappedTime), first, next, diffN, c);
		controller.activeAnim.prevFrame = first;
	}

	protected void renderWithIndex(float inter, int firstIndex, int nextIndex, float diffN, IAnimatedModelCallback c) {
		PoseStack matrixStack = new PoseStack();
		RenderSystem.pushPose(); // Push the matrix for transformations

		boolean hidden = false;

		if (hasTransform) {
			Transform[] transforms = controller.activeAnim.anim.objectTransforms.get(name);
			if (transforms != null) {
				hidden = transforms[firstIndex].hidden;
				transforms[firstIndex].interpolateAndApply(transforms[nextIndex], inter);
			} else {
				auxGLMatrix.put(transform);
				auxGLMatrix.rewind();
				matrixStack.mulPose(auxGLMatrix); // Using mulPose for modern matrix multiplication
			}
		}

		if (c != null)
			hidden |= c.onRender(controller.activeAnim.prevFrame, firstIndex, callList, diffN, name);

		if (hasGeometry && !hidden) {
			RenderSystem.setShader(GameRenderer::getPositionShader); // Set the shader
			RenderSystem.drawElements(VertexFormat.POSITION); // Use VertexFormat.POSITION (or a proper format)
		}

		if (c != null)
			c.postRender(controller.activeAnim.prevFrame, firstIndex, callList, diffN, name);

		for (AnimatedModel m : children) {
			m.renderWithIndex(inter, firstIndex, nextIndex, diffN, c);
		}

		RenderSystem.popPose(); // Pop the matrix after rendering
	}

	public void render() {
		render(null);
	}

	public void render(IAnimatedModelCallback c) {
		PoseStack matrixStack = new PoseStack();
		RenderSystem.pushPose(); // Push the matrix for transformations

		if (hasTransform) {
			auxGLMatrix.put(transform);
			auxGLMatrix.rewind();
			matrixStack.mulPose(auxGLMatrix); // Using mulPose for matrix multiplication
		}

		boolean hidden = false;
		if (c != null)
			hidden = c.onRender(-1, -1, callList, -1, name);

		if (hasGeometry && !hidden) {
			RenderSystem.setShader(GameRenderer::getPositionShader); // Set the shader
			RenderSystem.drawElements(VertexFormat.POSITION); // Use VertexFormat.POSITION (or a proper format)
		}

		if (c != null)
			c.postRender(-1, -1, callList, -1, name);

		for (AnimatedModel m : children) {
			m.render(c);
		}

		RenderSystem.popPose(); // Pop the matrix after rendering
	}

	private static float fract(float number) {
		return (float) (number - Math.floor(number));
	}

	public static interface IAnimatedModelCallback {
		public boolean onRender(int prevFrame, int currentFrame, int model, float diffN, String modelName);

		public default void postRender(int prevFrame, int currentFrame, int model, float diffN, String modelName) {}
	}
}
