package com.hbm.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Locale;

public class CommonConfig {

	// Category Constants
	public static final String CATEGORY_GENERAL = "01_general";
	public static final String CATEGORY_ORES = "02_ores";
	public static final String CATEGORY_NUKES = "03_nukes";
	public static final String CATEGORY_DUNGEONS = "04_dungeons";
	public static final String CATEGORY_METEORS = "05_meteors";
	public static final String CATEGORY_EXPLOSIONS = "06_explosions";
	public static final String CATEGORY_MISSILE = "07_missile_machines";
	public static final String CATEGORY_POTION = "08_potion_effects";
	public static final String CATEGORY_MACHINES = "09_machines";
	public static final String CATEGORY_DROPS = "10_dangerous_drops";
	public static final String CATEGORY_TOOLS = "11_tools";
	public static final String CATEGORY_MOBS = "12_mobs";
	public static final String CATEGORY_RADIATION = "13_radiation";
	public static final String CATEGORY_HAZARD = "14_hazard";
	public static final String CATEGORY_STRUCTURES = "15_structures";
	public static final String CATEGORY_POLLUTION = "16_pollution";
	public static final String CATEGORY_BIOMES = "17_biomes";
	public static final String CATEGORY_WEAPONS = "18_weapons";
	public static final String CATEGORY_528 = "528";
	public static final String CATEGORY_LBSM = "LESS BULLSHIT MODE";

	// Configuration Fields
	public static ModConfigSpec.ConfigValue<Integer> gadgetRadius;
	public static ModConfigSpec.ConfigValue<Integer> boyRadius;
	public static ModConfigSpec.ConfigValue<Integer> manRadius;
	public static ModConfigSpec.ConfigValue<Integer> mikeRadius;
	public static ModConfigSpec.ConfigValue<Integer> tsarRadius;
	public static ModConfigSpec.ConfigValue<Integer> fleijaRadius;
	public static ModConfigSpec.ConfigValue<Integer> missileRadius;
	public static ModConfigSpec.ConfigValue<Integer> mirvRadius;
	public static ModConfigSpec.ConfigValue<Integer> fatmanRadius;
	public static ModConfigSpec.ConfigValue<Integer> nukaRadius;
	public static ModConfigSpec.ConfigValue<Integer> aSchrabRadius;
	public static ModConfigSpec.ConfigValue<Integer> soliniumRadius;
	public static ModConfigSpec.ConfigValue<Integer> n2Radius;

	public static ModConfigSpec.ConfigValue<Integer> mk5;
	public static ModConfigSpec.ConfigValue<Integer> blastSpeed;
	public static ModConfigSpec.ConfigValue<Integer> falloutRange;
	public static ModConfigSpec.ConfigValue<Integer> fDelay;
	public static ModConfigSpec.ConfigValue<Integer> limitExplosionLifespan;
	public static ModConfigSpec.ConfigValue<Integer> rain;
	public static ModConfigSpec.ConfigValue<Integer> cont;
	public static ModConfigSpec.BooleanValue chunkloading;

	// ModConfigSpec
	public static ModConfigSpec COMMON_SPEC;

	static {
		ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

		// Define configuration values
		builder.push(CATEGORY_GENERAL);
		gadgetRadius = builder.define("gadgetRadius", 150);
		boyRadius = builder.define("boyRadius", 120);
		manRadius = builder.define("manRadius", 175);
		mikeRadius = builder.define("mikeRadius", 250);
		tsarRadius = builder.define("tsarRadius", 500);
		builder.pop();

		builder.push(CATEGORY_NUKES);
		fleijaRadius = builder.define("fleijaRadius", 50);
		missileRadius = builder.define("missileRadius", 100);
		mirvRadius = builder.define("mirvRadius", 100);
		fatmanRadius = builder.define("fatmanRadius", 35);
		nukaRadius = builder.define("nukaRadius", 25);
		aSchrabRadius = builder.define("aSchrabRadius", 20);
		soliniumRadius = builder.define("soliniumRadius", 150);
		n2Radius = builder.define("n2Radius", 200);

		mk5 = builder.define("mk5", 50);
		blastSpeed = builder.define("blastSpeed", 1024);
		falloutRange = builder.define("falloutRange", 100);
		fDelay = builder.define("fDelay", 4);
		limitExplosionLifespan = builder.define("limitExplosionLifespan", 0);
		rain = builder.define("rain", 0);
		cont = builder.define("cont", 0);
		chunkloading = builder.define("chunkloading", true);
		builder.pop();

		// Add more categories as needed (placeholder for all categories from the original file)
		builder.push(CATEGORY_ORES);
		// Add ore-related configs here (if applicable)
		builder.pop();

		builder.push(CATEGORY_DUNGEONS);
		// Add dungeon-related configs here (if applicable)
		builder.pop();

		builder.push(CATEGORY_METEORS);
		// Add meteor-related configs here (if applicable)
		builder.pop();

		builder.push(CATEGORY_EXPLOSIONS);
		// Add explosion-related configs here (if applicable)
		builder.pop();

		// Finalize the specification
		COMMON_SPEC = builder.build();
	}

	// Load configuration values
	public static void loadFromConfig(ModConfigSpec configSpec) {
		gadgetRadius = configSpec.getOrDefault("gadgetRadius", 150);
		boyRadius = configSpec.getOrDefault("boyRadius", 120);
		manRadius = configSpec.getOrDefault("manRadius", 175);
		mikeRadius = configSpec.getOrDefault("mikeRadius", 250);
		tsarRadius = configSpec.getOrDefault("tsarRadius", 500);

		fleijaRadius = configSpec.getOrDefault("fleijaRadius", 50);
		missileRadius = configSpec.getOrDefault("missileRadius", 100);
		mirvRadius = configSpec.getOrDefault("mirvRadius", 100);
		fatmanRadius = configSpec.getOrDefault("fatmanRadius", 35);
		nukaRadius = configSpec.getOrDefault("nukaRadius", 25);
		aSchrabRadius = configSpec.getOrDefault("aSchrabRadius", 20);
		soliniumRadius = configSpec.getOrDefault("soliniumRadius", 150);
		n2Radius = configSpec.getOrDefault("n2Radius", 200);

		mk5 = configSpec.getOrDefault("mk5", 50);
		blastSpeed = configSpec.getOrDefault("blastSpeed", 1024);
		falloutRange = configSpec.getOrDefault("falloutRange", 100);
		fDelay = configSpec.getOrDefault("fDelay", 4);
		limitExplosionLifespan = configSpec.getOrDefault("limitExplosionLifespan", 0);
		rain = configSpec.getOrDefault("rain", 0);
		cont = configSpec.getOrDefault("cont", 0);
		chunkloading = configSpec.getOrDefault("chunkloading", true);
	}

	// Utility method to handle default values when a value is invalid or below zero
	public static int setDefZero(int value, int def) {
		if (value < 0) {
			System.err.println("Fatal error config: Randomizer value has been below zero, despite bound having to be positive integer!");
			System.err.println(String.format(Locale.US, "Errored value will default back to %d, PLEASE REVIEW CONFIGURATION DESCRIPTION BEFORE MEDDLING WITH VALUES!", def));
			return def;
		}
		return value;
	}

	public static int setDef(int value, int def) {
		if (value <= 0) {
			System.err.println("Fatal error config: Randomizer value has been set to zero, despite bound having to be positive integer!");
			System.err.println(String.format(Locale.US, "Errored value will default back to %d, PLEASE REVIEW CONFIGURATION DESCRIPTION BEFORE MEDDLING WITH VALUES!", def));
			return def;
		}
		return value;
	}
}