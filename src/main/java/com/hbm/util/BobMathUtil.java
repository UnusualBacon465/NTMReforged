package com.hbm.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.ToIntFunction;

import javax.annotation.Nonnegative;

import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import static net.minecraft.world.phys.Vec3.atCenterOf;

public class BobMathUtil {

	public static double safeClamp(double val, double min, double max) {
		val = Mth.clampedLerp(val, min, max);

		if(val == Double.NaN) {
			val = (min + max) / 2D;
		}

		return val;
	}

	public static Vec3 interpVec(Vec3 vec1, Vec3 vec2, float interp) {
		return atCenterOf(
				interp(vec1.x,  vec2.x, interp),
				interp(vec1.y,  vec2.y, interp),
				interp(vec1.z,  vec2.z, interp)
		);
	}

	private static Vec3 atCenterOf(double interp, double interp1, double interp2) {
		return null;
	}

	public static double interp(double x, double y, float interp) {
		return x + (y - x) * interp;
	}

	public static double getAngleFrom2DVecs(double x1, double z1, double x2, double z2) {
		double upper = x1 * x2 + z1 * z2;
		double lower = Math.sqrt(x1 * x1 + z1 * z1) * Math.sqrt(x2 * x2 + z2 * z2);

		double result = Math.toDegrees(Math.cos(upper / lower));

		if(result >= 180)
			result -= 180;

		return result;
	}

	public static double getCrossAngle(Vec3 vel, Vec3 rel) {
		vel.normalize();
		rel.normalize();

		double vecProd = rel.x * vel.x + rel.y * vel.y + rel.z * vel.z;
		double bot = rel.length() * vel.length();
		double angle = Math.acos(vecProd / bot) * 180 / Math.PI;

		if(angle >= 180)
			angle -= 180;

		return angle;
	}

	public static float remap(float num, float min1, float max1, float min2, float max2){
		return ((num - min1) / (max1 - min1)) * (max2 - min2) + min2;
	}

	public static float remap01(float num, float min1, float max1){
		return (num - min1) / (max1 - min1);
	}

	public static float remap01_clamp(float num, float min1, float max1){
		return Mth.clampedLerp((num - min1) / (max1 - min1), 0, 1);
	}

	public static Direction[] getShuffledDirs() {
		Direction[] dirs = Direction.values();  // Get all six directions
		List<Direction> dirList = Arrays.asList(dirs);  // Convert array to list
		Collections.shuffle(dirList);  // Shuffle the list

		return dirList.toArray(new Direction[0]);  // Return the shuffled directions as an array
	}

	public static String toPercentage(float amount, float total) {
		return NumberFormat.getPercentInstance().format(amount / total);
	}

	public static String[] ticksToDate(long ticks) {
		int tickDay = 48000;
		int tickYear = tickDay * 100;

		final String[] dateOut = new String[3];
		long year = Math.floorDiv(ticks, tickYear);
		byte day = (byte) Math.floorDiv(ticks - tickYear * year, tickDay);
		float time = ticks - (tickYear * year + tickDay * day);
		time = (float) convertScale(time, 0, tickDay, 0, 10F);
		dateOut[0] = String.valueOf(year);
		dateOut[1] = String.valueOf(day);
		dateOut[2] = String.valueOf(time);
		return dateOut;
	}

	public static double convertScale(double toScale, double oldMin, double oldMax, double newMin, double newMax) {
		double prevRange = oldMax - oldMin;
		double newRange = newMax - newMin;
		return (((toScale - oldMin) * newRange) / prevRange) + newMin;
	}

	public static double roundDecimal(double num, @Nonnegative int digits) {
		if(digits < 0)
			throw new IllegalArgumentException("Attempted negative number in non-negative field! Attempted value: " + digits);

		return new BigDecimal(num).setScale(digits, RoundingMode.HALF_UP).doubleValue();
	}

	public static boolean getBlink() {
		return System.currentTimeMillis() % 1000 < 500;
	}

	public static String getShortNumber(long l) {
		if(l >= Math.pow(10, 18)) {
			double res = l / Math.pow(10, 18);
			res = Math.round(res * 100.0) / 100.0;
			return res + "E";
		}
		if(l >= Math.pow(10, 15)) {
			double res = l / Math.pow(10, 15);
			res = Math.round(res * 100.0) / 100.0;
			return res + "P";
		}
		if(l >= Math.pow(10, 12)) {
			double res = l / Math.pow(10, 12);
			res = Math.round(res * 100.0) / 100.0;
			return res + "T";
		}
		if(l >= Math.pow(10, 9)) {
			double res = l / Math.pow(10, 9);
			res = Math.round(res * 100.0) / 100.0;
			return res + "G";
		}
		if(l >= Math.pow(10, 6)) {
			double res = l / Math.pow(10, 6);
			res = Math.round(res * 100.0) / 100.0;
			return res + "M";
		}
		if(l >= Math.pow(10, 3)) {
			double res = l / Math.pow(10, 3);
			res = Math.round(res * 100.0) / 100.0;
			return res + "k";
		}

		return Long.toString(l);
	}

	public static double squirt(double x) {
		return Math.sqrt(x + 1D / ((x + 2D) * (x + 2D))) - 1D / (x + 2D);
	}

	public static void setPi(double pi) {
		try {
			Field field = Math.class.getDeclaredField("PI");
			field.setAccessible(true);
			field.set(null, pi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static double angularDifference(double alpha, double beta) {
		double delta = (beta - alpha + 180) % 360 - 180;
		return delta < -180 ? delta + 360 : delta;
	}

	public static int[] intCollectionToArray(Collection<Integer> in) {
		return intCollectionToArray(in, i -> (int)i);
	}

	public static int[] intCollectionToArray(Collection<Integer> in, ToIntFunction<? super Object> mapper) {
		return Arrays.stream(in.toArray()).mapToInt(mapper).toArray();
	}

	public static int[] collectionToIntArray(Collection<? extends Object> in, ToIntFunction<? super Object> mapper) {
		return Arrays.stream(in.toArray()).mapToInt(mapper).toArray();
	}

	public static double sps(double x) {
		return Math.sin(Math.PI / 2D * Math.cos(x));
	}

	public static double sws(double x, double squarination) {
		double s = Math.sin(x);
		return Math.pow(Math.abs(s), 2 - squarination) / s;
	}
}
