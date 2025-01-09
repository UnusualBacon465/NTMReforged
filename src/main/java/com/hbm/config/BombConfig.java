package com.hbm.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class BombConfig {

	public static int gadgetRadius = 150;
	public static int boyRadius = 120;
	public static int manRadius = 175;
	public static int mikeRadius = 250;
	public static int tsarRadius = 500;
	public static int prototypeRadius = 150;
	public static int fleijaRadius = 50;
	public static int soliniumRadius = 150;
	public static int n2Radius = 200;
	public static int missileRadius = 100;
	public static int mirvRadius = 100;
	public static int fatmanRadius = 35;
	public static int nukaRadius = 25;
	public static int aSchrabRadius = 20;

	public static int mk5 = 50;
	public static int blastSpeed = 1024;
	public static int falloutRange = 100;
	public static int fDelay = 4;
	public static int limitExplosionLifespan = 0;
	public static int rain = 0;
	public static int cont = 0;
	public static boolean chunkloading = true;

	public static ModConfigSpec configSpec;

	static {
		ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

		builder.comment("Nuclear Bomb Settings");

		builder.define("3.00_gadgetRadius", gadgetRadius);
		builder.define("3.01_boyRadius", boyRadius);
		builder.define("3.02_manRadius", manRadius);
		builder.define("3.03_mikeRadius", mikeRadius);
		builder.define("3.04_tsarRadius", tsarRadius);
		builder.define("3.05_prototypeRadius", prototypeRadius);
		builder.define("3.06_fleijaRadius", fleijaRadius);
		builder.define("3.07_missileRadius", missileRadius);
		builder.define("3.08_mirvRadius", mirvRadius);
		builder.define("3.09_fatmanRadius", fatmanRadius);
		builder.define("3.10_nukaRadius", nukaRadius);
		builder.define("3.11_aSchrabRadius", aSchrabRadius);
		builder.define("3.12_soliniumRadius", soliniumRadius);
		builder.define("3.13_n2Radius", n2Radius);

		builder.define("6.00_limitExplosionLifespan", limitExplosionLifespan);
		builder.define("6.01_blastSpeed", blastSpeed);
		builder.define("6.02_mk5BlastTime", mk5);
		builder.define("6.03_falloutRange", falloutRange);
		builder.define("6.04_falloutDelay", fDelay);
		builder.define("6.05_falloutRainDuration", rain);
		builder.define("6.06_falloutRainRadiation", cont);
		builder.define("6.XX_enableChunkLoading", chunkloading);

		configSpec = builder.build();
	}
}