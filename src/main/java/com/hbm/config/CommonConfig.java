package com.hbm.config;

import java.util.Locale;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;

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

	// Declare all your configuration variables
	public static int gadgetRadius;
	public static int boyRadius;
	public static int manRadius;
	public static int mikeRadius;
	public static int tsarRadius;
	public static int fleijaRadius;
	public static int missileRadius;
	public static int mirvRadius;
	public static int fatmanRadius;
	public static int nukaRadius;
	public static int aSchrabRadius;
	public static int soliniumRadius;
	public static int n2Radius;

	public static int mk5;
	public static int blastSpeed;
	public static int falloutRange;
	public static int fDelay;
	public static int limitExplosionLifespan;
	public static int rain;
	public static int cont;
	public static boolean chunkloading;

	// Methods to define default configuration values
	public static void defineConfig(Builder builder) {
		builder.define("gadgetRadius", 150); // Radius of the Gadget
		builder.define("boyRadius", 120); // Radius of Little Boy
		builder.define("manRadius", 175); // Radius of Fat Man
		builder.define("mikeRadius", 250); // Radius of Ivy Mike
		builder.define("tsarRadius", 500); // Radius of the Tsar Bomba
		builder.define("prototypeRadius", 150); // Radius of the Prototype
		builder.define("fleijaRadius", 50); // Radius of F.L.E.I.J.A.
		builder.define("missileRadius", 100); // Radius of the nuclear missile
		builder.define("mirvRadius", 100); // Radius of a MIRV
		builder.define("fatmanRadius", 35); // Radius of the Fatman Launcher
		builder.define("nukaRadius", 25); // Radius of the nuka grenade
		builder.define("aSchrabRadius", 20); // Radius of dropped anti schrabidium
		builder.define("soliniumRadius", 150); // Radius of the blue rinse
		builder.define("n2Radius", 200); // Radius of the N2 mine

		builder.define("mk5", 50); // MK5 system speed
		builder.define("blastSpeed", 1024); // Base speed of MK3 system detonations (Blocks / tick)
		builder.define("falloutRange", 100); // Radius of fallout area (base radius * value in percent)
		builder.define("fDelay", 4); // How many ticks to wait for the next fallout chunk computation
		builder.define("limitExplosionLifespan", 0); // How long an explosion can be unloaded until it dies in seconds. Based on system time. 0 disables the effect
		builder.define("rain", 0); // Duration of fallout rain duration
		builder.define("cont", 0); // Radiation in 100th RADs created by fallout rain

		builder.define("chunkloading", true); // Allows all types of procedural explosions to keep the central chunk loaded.
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

	// Utility method to handle default values when a value is zero or less
	public static int setDef(int value, int def) {
		if (value <= 0) {
			System.err.println("Fatal error config: Randomizer value has been set to zero, despite bound having to be positive integer!");
			System.err.println(String.format(Locale.US, "Errored value will default back to %d, PLEASE REVIEW CONFIGURATION DESCRIPTION BEFORE MEDDLING WITH VALUES!", def));
			return def;
		}
		return value;
	}

	// Load the configuration values from the defined ModConfigSpec
	public static void loadFromConfig(ModConfigSpec configSpec) {
		// After defining, retrieve the values from the ModConfigSpec
		ConfigValue gadgetRadiusValue = configSpec.get("gadgetRadius");
		gadgetRadius = gadgetRadiusValue.getInt();

		ConfigValue boyRadiusValue = configSpec.get("boyRadius");
		boyRadius = boyRadiusValue.getInt();

		ConfigValue manRadiusValue = configSpec.get("manRadius");
		manRadius = manRadiusValue.getInt();

		ConfigValue mikeRadiusValue = configSpec.get("mikeRadius");
		mikeRadius = mikeRadiusValue.getInt();

		ConfigValue tsarRadiusValue = configSpec.get("tsarRadius");
		tsarRadius = tsarRadiusValue.getInt();

		ConfigValue fleijaRadiusValue = configSpec.get("fleijaRadius");
		fleijaRadius = fleijaRadiusValue.getInt();

		ConfigValue missileRadiusValue = configSpec.get("missileRadius");
		missileRadius = missileRadiusValue.getInt();

		ConfigValue mirvRadiusValue = configSpec.get("mirvRadius");
		mirvRadius = mirvRadiusValue.getInt();

		ConfigValue fatmanRadiusValue = configSpec.get("fatmanRadius");
		fatmanRadius = fatmanRadiusValue.getInt();

		ConfigValue nukaRadiusValue = configSpec.get("nukaRadius");
		nukaRadius = nukaRadiusValue.getInt();

		ConfigValue aSchrabRadiusValue = configSpec.get("aSchrabRadius");
		aSchrabRadius = aSchrabRadiusValue.getInt();

		ConfigValue soliniumRadiusValue = configSpec.get("soliniumRadius");
		soliniumRadius = soliniumRadiusValue.getInt();

		ConfigValue n2RadiusValue = configSpec.get("n2Radius");
		n2Radius = n2RadiusValue.getInt();

		// Repeat for the rest of the variables
		mk5 = configSpec.get("mk5").getInt();
		blastSpeed = configSpec.get("blastSpeed").getInt();
		falloutRange = configSpec.get("falloutRange").getInt();
		fDelay = configSpec.get("fDelay").getInt();
		limitExplosionLifespan = configSpec.get("limitExplosionLifespan").getInt();
		rain = configSpec.get("rain").getInt();
		cont = configSpec.get("cont").getInt();
		chunkloading = configSpec.get("chunkloading").getBoolean();
	}
}
