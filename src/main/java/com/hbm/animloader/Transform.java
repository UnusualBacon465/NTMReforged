package com.hbm.animloader;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

/**
 * Bob:
 * Ported from 1.12.2.
 * Mostly gibberish to me, probably written in the ancient egyptian language.
 * Any unmarked code comments are probably from Drillgon or code I had to throw out myself.
 *
 * @author Drillgon200 for the most part
 */
public class Transform {

	private static final FloatBuffer AUX_GL_MATRIX = BufferUtils.createFloatBuffer(16);

	private Vector3f scale;
	private Vector3f translation;
	private Quaternionf rotation;

	protected boolean hidden = false;

	public Transform(float[] matrix) {
		this.scale = extractScaleFromMatrix(matrix);
		Matrix4f matrix4f = new Matrix4f().set(matrix);
		this.rotation = extractRotationFromMatrix(matrix4f);
		this.translation = new Vector3f(matrix[12], matrix[13], matrix[14]);
	}

	private Vector3f extractScaleFromMatrix(float[] matrix) {
		Vector3f scaleX = new Vector3f(matrix[0], matrix[1], matrix[2]);
		Vector3f scaleY = new Vector3f(matrix[4], matrix[5], matrix[6]);
		Vector3f scaleZ = new Vector3f(matrix[8], matrix[9], matrix[10]);

		float lengthX = scaleX.length();
		float lengthY = scaleY.length();
		float lengthZ = scaleZ.length();

		scaleX.normalize();
		scaleY.normalize();
		scaleZ.normalize();

		matrix[0] /= lengthX;
		matrix[1] /= lengthX;
		matrix[2] /= lengthX;

		matrix[4] /= lengthY;
		matrix[5] /= lengthY;
		matrix[6] /= lengthY;

		matrix[8] /= lengthZ;
		matrix[9] /= lengthZ;
		matrix[10] /= lengthZ;

		return new Vector3f(lengthX, lengthY, lengthZ);
	}

	private Quaternionf extractRotationFromMatrix(Matrix4f matrix) {
		Quaternionf quaternion = new Quaternionf();
		matrix.getNormalizedRotation(quaternion);
		return quaternion;
	}

	public void interpolateAndApply(Transform other, float inter) {
		Vector3f interpolatedTranslation = interpolate(this.translation, other.translation, inter);
		Vector3f interpolatedScale = interpolate(this.scale, other.scale, inter);
		Quaternionf interpolatedRotation = slerp(this.rotation, other.rotation, inter);

		Matrix4f resultMatrix = new Matrix4f()
				.translation(interpolatedTranslation)
				.rotate(interpolatedRotation)
				.scale(interpolatedScale);

		resultMatrix.get(AUX_GL_MATRIX);
		GL11.glMultMatrixf(AUX_GL_MATRIX);
	}

	private Vector3f interpolate(Vector3f start, Vector3f end, float factor) {
		return new Vector3f(
				start.x + factor * (end.x - start.x),
				start.y + factor * (end.y - start.y),
				start.z + factor * (end.z - start.z)
		);
	}

	private Quaternionf slerp(Quaternionf q1, Quaternionf q2, float t) {
		return q1.slerp(q2, t, new Quaternionf());
	}
}