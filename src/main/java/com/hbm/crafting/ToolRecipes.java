package com.hbm.crafting;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import com.hbm.inventory.fluid.Fluids;

import static com.hbm.inventory.OreDictManager.*;
import static com.hbm.items.ModItems.*;

import com.hbm.items.ItemEnums.EnumPlantType;
import com.hbm.items.ItemGenericPart.EnumPartType;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemBattery;
import com.hbm.items.machine.ItemCircuit.EnumCircuitType;
import com.hbm.items.tool.ItemBlowtorch;
import com.hbm.items.tool.ItemModMinecart;
import com.hbm.items.tool.ItemModMinecart.EnumCartBase;
import com.hbm.items.tool.ItemModMinecart.EnumMinecart;
import com.hbm.items.tool.ItemToolAbilityFueled;
import com.hbm.main.CraftingManager;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * For mining and utility tools
 * @author hbm
 */
public class ToolRecipes {
	
	public static void register() {
		
		//Regular tools
		addSword(	STEEL.ingot(), steel_sword);
		addPickaxe(	STEEL.ingot(), steel_pickaxe);
		addAxe(		STEEL.ingot(), steel_axe);
		addShovel(	STEEL.ingot(), steel_shovel);
		addHoe(		STEEL.ingot(), steel_hoe);
		addSword(	TI.ingot(), titanium_sword);
		addPickaxe(	TI.ingot(), titanium_pickaxe);
		addAxe(		TI.ingot(), titanium_axe);
		addShovel(	TI.ingot(), titanium_shovel);
		addHoe(		TI.ingot(), titanium_hoe);
		addSword(	CO.ingot(), cobalt_sword);
		addPickaxe(	CO.ingot(), cobalt_pickaxe);
		addAxe(		CO.ingot(), cobalt_axe);
		addShovel(	CO.ingot(), cobalt_shovel);
		addHoe(		CO.ingot(), cobalt_hoe);
		addSword(	ALLOY.ingot(), alloy_sword);
		addPickaxe(	ALLOY.ingot(), alloy_pickaxe);
		addAxe(		ALLOY.ingot(), alloy_axe);
		addShovel(	ALLOY.ingot(), alloy_shovel);
		addHoe(		ALLOY.ingot(), alloy_hoe);
		addSword(	CMB.ingot(), cmb_sword);
		addPickaxe(	CMB.ingot(), cmb_pickaxe);
		addAxe(		CMB.ingot(), cmb_axe);
		addShovel(	CMB.ingot(), cmb_shovel);
		addHoe(		CMB.ingot(), cmb_hoe);
		addSword(	DESH.ingot(), desh_sword);
		addPickaxe(	DESH.ingot(), desh_pickaxe);
		addAxe(		DESH.ingot(), desh_axe);
		addShovel(	DESH.ingot(), desh_shovel);
		addHoe(		DESH.ingot(), desh_hoe);
		
		CraftingManager.addRecipeAuto(new ItemStack(elec_sword, 1), new Object[] { "RPR", "RPR", " B ", 'P', ANY_PLASTIC.ingot(), 'D', DURA.ingot(), 'R', DURA.bolt(), 'M', motor, 'B', battery_lithium });
		CraftingManager.addRecipeAuto(new ItemStack(elec_pickaxe, 1), new Object[] { "RDM", " PB", " P ", 'P', ANY_PLASTIC.ingot(), 'D', DURA.ingot(), 'R', DURA.bolt(), 'M', motor, 'B', battery_lithium });
		CraftingManager.addRecipeAuto(new ItemStack(elec_axe, 1), new Object[] { " DP", "RRM", " PB", 'P', ANY_PLASTIC.ingot(), 'D', DURA.ingot(), 'R', DURA.bolt(), 'M', motor, 'B', battery_lithium });
		CraftingManager.addRecipeAuto(new ItemStack(elec_shovel, 1), new Object[] { "  P", "RRM", "  B", 'P', ANY_PLASTIC.ingot(), 'D', DURA.ingot(), 'R', DURA.bolt(), 'M', motor, 'B', battery_lithium });
		CraftingManager.addShapelessAuto(new ItemStack(centri_stick, 1), new Object[] { centrifuge_element, energy_core, KEY_STICK });
		CraftingManager.addRecipeAuto(new ItemStack(smashing_hammer, 1), new Object[] { "STS", "SPS", " P ", 'S', STEEL.block(), 'T', W.block(), 'P', ANY_PLASTIC.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(meteorite_sword, 1), new Object[] { "  B", "GB ", "SG ", 'B', blade_meteorite, 'G', GOLD.plate(), 'S', KEY_STICK });
		CraftingManager.addRecipeAuto(new ItemStack(dwarven_pickaxe, 1), new Object[] { "CIC", " S ", " S ", 'C', CU.ingot(), 'I', IRON.ingot(), 'S', KEY_STICK });

		//Drax
		CraftingManager.addRecipeAuto(new ItemStack(drax, 1), new Object[] { "BDS", "CDC", "FMF", 'B', starmetal_pickaxe, 'S', starmetal_shovel, 'C', CO.ingot(), 'F', fusion_core, 'D', DESH.ingot(), 'M', motor_desh });
		CraftingManager.addRecipeAuto(new ItemStack(drax_mk2, 1), new Object[] { "SCS", "IDI", "FEF", 'S', STAR.ingot(), 'C', crystal_trixite, 'I', BIGMT.ingot(), 'D', drax, 'F', fusion_core, 'E', DictFrame.fromOne(circuit, EnumCircuitType.ADVANCED) });
		CraftingManager.addRecipeAuto(new ItemStack(drax_mk3, 1), new Object[] { "ECE", "CDC", "SBS", 'E', ModBlocks.block_euphemium_cluster, 'C', crystal_schrabidium, 'D', drax_mk2, 'S', DictFrame.fromOne(circuit, EnumCircuitType.BISMOID), 'B', ItemBattery.getFullBattery(battery_spark) });

		//Super pickaxes
		CraftingManager.addRecipeAuto(new ItemStack(bismuth_pickaxe, 1), new Object[] { " BM", "BPB", "TB ", 'B', ingot_bismuth, 'M', ingot_meteorite, 'P', starmetal_pickaxe, 'T', W.bolt() });
		CraftingManager.addRecipeAuto(new ItemStack(volcanic_pickaxe, 1), new Object[] { " BM", "BPB", "TB ", 'B', gem_volcanic, 'M', ingot_meteorite, 'P', starmetal_pickaxe, 'T', W.bolt() });
		CraftingManager.addRecipeAuto(new ItemStack(chlorophyte_pickaxe, 1), new Object[] { " SD", "APS", "FA ", 'S', blades_steel, 'D', powder_chlorophyte, 'A', FIBER.ingot(), 'P', bismuth_pickaxe, 'F', DURA.bolt() });
		CraftingManager.addRecipeAuto(new ItemStack(chlorophyte_pickaxe, 1), new Object[] { " SD", "APS", "FA ", 'S', blades_steel, 'D', powder_chlorophyte, 'A', FIBER.ingot(), 'P', volcanic_pickaxe, 'F', DURA.bolt() });
		CraftingManager.addRecipeAuto(new ItemStack(mese_pickaxe, 1), new Object[] { " SD", "APS", "FA ", 'S', blades_desh, 'D', powder_dineutronium, 'A', plate_paa, 'P', chlorophyte_pickaxe, 'F', shimmer_handle });

		//Super Axes
		CraftingManager.addRecipeAuto(new ItemStack(bismuth_axe, 1), new Object[] { " BM", "BPB", "TB ", 'B', ingot_bismuth, 'M', ingot_meteorite, 'P', starmetal_axe, 'T', W.bolt() });
		CraftingManager.addRecipeAuto(new ItemStack(volcanic_axe, 1), new Object[] { " BM", "BPB", "TB ", 'B', gem_volcanic, 'M', ingot_meteorite, 'P', starmetal_axe, 'T', W.bolt() });
		CraftingManager.addRecipeAuto(new ItemStack(chlorophyte_axe, 1), new Object[] { " SD", "APS", "FA ", 'S', blades_steel, 'D', powder_chlorophyte, 'A', FIBER.ingot(), 'P', bismuth_axe, 'F', DURA.bolt() });
		CraftingManager.addRecipeAuto(new ItemStack(chlorophyte_axe, 1), new Object[] { " SD", "APS", "FA ", 'S', blades_steel, 'D', powder_chlorophyte, 'A', FIBER.ingot(), 'P', volcanic_axe, 'F', DURA.bolt() });
		CraftingManager.addRecipeAuto(new ItemStack(mese_axe, 1), new Object[] { " SD", "APS", "FA ", 'S', blades_desh, 'D', powder_dineutronium, 'A', plate_paa, 'P', chlorophyte_axe, 'F', shimmer_handle });
		
		//Chainsaws
		CraftingManager.addRecipeAuto(ItemToolAbilityFueled.getEmptyTool(chainsaw), new Object[] { "CCH", "BBP", "CCE", 'H', STEEL.shell(), 'B', blades_steel, 'P', piston_selenium, 'C', ModBlocks.chain, 'E', canister_empty });

		//Misc
		CraftingManager.addRecipeAuto(new ItemStack(crowbar, 1), new Object[] { "II", " I", " I", 'I', STEEL.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(bottle_opener, 1), new Object[] { "S", "P", 'S', STEEL.plate(), 'P', KEY_PLANKS });
		CraftingManager.addRecipeAuto(new ItemStack(Items.saddle, 1), new Object[] { "LLL", "LRL", " S ", 'S', STEEL.ingot(), 'L', Items.leather, 'R', DictFrame.fromOne(plant_item, EnumPlantType.ROPE) });

		//Matches
		CraftingManager.addRecipeAuto(new ItemStack(matchstick, 16), new Object[] { "I", "S", 'I', S.dust(), 'S', KEY_STICK });
		CraftingManager.addRecipeAuto(new ItemStack(matchstick, 24), new Object[] { "I", "S", 'I', P_RED.dust(), 'S', KEY_STICK });

		//Gavels
		CraftingManager.addRecipeAuto(new ItemStack(wood_gavel, 1), new Object[] { "SWS", " R ", " R ", 'S', KEY_SLAB, 'W', KEY_LOG, 'R', KEY_STICK });
		CraftingManager.addRecipeAuto(new ItemStack(lead_gavel, 1), new Object[] { "PIP", "IGI", "PIP", 'P', pellet_buckshot, 'I', PB.ingot(), 'G', wood_gavel });

		//Misc weapons
		CraftingManager.addRecipeAuto(new ItemStack(saw, 1), new Object[] { "IIL", "PP ", 'P', STEEL.plate(), 'I', STEEL.ingot(), 'L', Items.leather });
		CraftingManager.addRecipeAuto(new ItemStack(bat, 1), new Object[] { "P", "P", "S", 'S', STEEL.plate(), 'P', KEY_PLANKS });
		CraftingManager.addShapelessAuto(new ItemStack(bat_nail, 1), new Object[] { bat, STEEL.plate() });
		CraftingManager.addRecipeAuto(new ItemStack(golf_club, 1), new Object[] { "IP", " P", " P", 'P', STEEL.plate(), 'I', STEEL.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(pipe_rusty, 1), new Object[] { "II", " I", " I", 'I', IRON.pipe() });
		CraftingManager.addRecipeAuto(new ItemStack(pipe_lead, 1), new Object[] { "II", " I", " I", 'I', PB.pipe() });
		CraftingManager.addRecipeAuto(new ItemStack(ullapool_caber, 1), new Object[] { "ITI", " S ", " S ", 'I', IRON.plate(), 'T', Blocks.tnt, 'S', KEY_STICK });

		//Utility
		CraftingManager.addRecipeAuto(new ItemStack(designator, 1), new Object[] { "  A", "#B#", "#B#", '#', IRON.plate(), 'A', STEEL.plate(), 'B', DictFrame.fromOne(circuit, EnumCircuitType.BASIC) });
		CraftingManager.addRecipeAuto(new ItemStack(designator_range, 1), new Object[] { "RRD", "PIC", "  P", 'P', STEEL.plate(), 'R', Items.redstone, 'C', DictFrame.fromOne(circuit, EnumCircuitType.ADVANCED), 'D', designator, 'I', STEEL.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(designator_manual, 1), new Object[] { "  A", "#C#", "#B#", '#', ANY_PLASTIC.ingot(), 'A', PB.plate(), 'B', DictFrame.fromOne(circuit, EnumCircuitType.ADVANCED), 'C', designator });
		CraftingManager.addRecipeAuto(new ItemStack(designator_arty_range, 1), new Object[] { "M", "C", "P", 'M', magnetron, 'C', DictFrame.fromOne(circuit, EnumCircuitType.ADVANCED), 'P', ANY_PLASTIC.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(linker, 1), new Object[] { "I I", "ICI", "GGG", 'I', IRON.plate(), 'G', GOLD.plate(), 'C', DictFrame.fromOne(circuit, EnumCircuitType.ADVANCED) });
		CraftingManager.addRecipeAuto(new ItemStack(oil_detector, 1), new Object[] { "W I", "WCI", "PPP", 'W', GOLD.wireFine(), 'I', CU.ingot(), 'C', DictFrame.fromOne(circuit, EnumCircuitType.ANALOG), 'P', STEEL.plate528() });
		CraftingManager.addRecipeAuto(new ItemStack(turret_chip, 1), new Object[] { "WWW", "CPC", "WWW", 'W', GOLD.wireFine(), 'P', ANY_PLASTIC.ingot(), 'C', DictFrame.fromOne(circuit, EnumCircuitType.ADVANCED), });
		CraftingManager.addRecipeAuto(new ItemStack(survey_scanner, 1), new Object[] { "SWS", " G ", "PCP", 'W', GOLD.wireFine(), 'P', ANY_PLASTIC.ingot(), 'C', DictFrame.fromOne(circuit, EnumCircuitType.ADVANCED), 'S', STEEL.plate528(), 'G', GOLD.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(geiger_counter, 1), new Object[] { "GPP", "WCS", "WBB", 'W', GOLD.wireFine(), 'P', ANY_RUBBER.ingot(), 'C', DictFrame.fromOne(circuit, EnumCircuitType.BASIC), 'G', GOLD.ingot(), 'S', STEEL.plate528(), 'B', ingot_beryllium });
		CraftingManager.addRecipeAuto(new ItemStack(dosimeter, 1), new Object[] { "WGW", "WCW", "WBW", 'W', KEY_PLANKS, 'G', KEY_ANYPANE, 'C', DictFrame.fromOne(circuit, EnumCircuitType.VACUUM_TUBE), 'B', BE.ingot() });
		CraftingManager.addShapelessAuto(new ItemStack(ModBlocks.geiger), new Object[] { geiger_counter });
		CraftingManager.addShapelessAuto(new ItemStack(digamma_diagnostic), new Object[] { geiger_counter, PO210.billet(), ASBESTOS.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(pollution_detector, 1), new Object[] { "SFS", "SCS", " S ", 'S', STEEL.plate(), 'F', filter_coal, 'C', DictFrame.fromOne(circuit, EnumCircuitType.VACUUM_TUBE) });
		CraftingManager.addRecipeAuto(new ItemStack(ore_density_scanner, 1), new Object[] { "VVV", "CSC", "GGG", 'V', DictFrame.fromOne(circuit, EnumCircuitType.VACUUM_TUBE), 'C', DictFrame.fromOne(circuit, EnumCircuitType.CAPACITOR), 'S', DictFrame.fromOne(circuit, EnumCircuitType.CONTROLLER_CHASSIS), 'G', GOLD.plate() });
		CraftingManager.addRecipeAuto(new ItemStack(defuser, 1), new Object[] { " PS", "P P", " P ", 'P', ANY_PLASTIC.ingot(), 'S', STEEL.plate() });
		CraftingManager.addRecipeAuto(new ItemStack(coltan_tool, 1), new Object[] { "ACA", "CXC", "ACA", 'A', ALLOY.ingot(), 'C', CINNABAR.crystal(), 'X', Items.compass });
		CraftingManager.addRecipeAuto(new ItemStack(reacher, 1), new Object[] { "BIB", "P P", "B B", 'B', W.bolt(), 'I', W.ingot(), 'P', ANY_RUBBER.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(bismuth_tool, 1), new Object[] { "TBT", "SRS", "SCS", 'T', TA.nugget(), 'B', nugget_bismuth, 'S', ANY_RESISTANTALLOY.ingot(), 'R', reacher, 'C', DictFrame.fromOne(circuit, EnumCircuitType.CHIP) });
		CraftingManager.addRecipeAuto(new ItemStack(sat_designator, 1), new Object[] { "RRD", "PIC", "  P", 'P', GOLD.plate(), 'R', Items.redstone, 'C', DictFrame.fromOne(circuit, EnumCircuitType.ADVANCED), 'D', sat_chip, 'I', GOLD.ingot() });
		CraftingManager.addShapelessAuto(new ItemStack(sat_relay), new Object[] { sat_chip, ducttape, radar_linker });
		CraftingManager.addRecipeAuto(new ItemStack(settings_tool), new Object[] { " P ", "PCP", "III", 'P', IRON.plate(), 'C', DictFrame.fromOne(circuit, EnumCircuitType.ANALOG), 'I', plate_polymer });

		CraftingManager.addRecipeAuto(new ItemStack(pipette, 1), new Object[] { "  L", " G ", "G  ", 'L', ANY_RUBBER.ingot(), 'G', KEY_CLEARGLASS});
		CraftingManager.addRecipeAuto(new ItemStack(pipette_boron, 1), new Object[] { "  P", " B ", "B  ", 'P', RUBBER.ingot(), 'B', ModBlocks.glass_boron});
		CraftingManager.addRecipeAuto(new ItemStack(pipette_laboratory, 1), new Object[] { "  C", " R ", "P  ", 'C', DictFrame.fromOne(circuit, EnumCircuitType.CHIP), 'R', RUBBER.ingot(), 'P', pipette_boron });

		CraftingManager.addRecipeAuto(new ItemStack(siphon, 1), new Object[] { " GR", " GR", " G ", 'G', KEY_CLEARGLASS, 'R', ANY_RUBBER.ingot()});

		CraftingManager.addRecipeAuto(new ItemStack(mirror_tool), new Object[] { " A ", " IA", "I  ", 'A', AL.ingot(), 'I', IRON.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(rbmk_tool), new Object[] { " A ", " IA", "I  ", 'A', PB.ingot(), 'I', IRON.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(power_net_tool), new Object[] { "WRW", " I ", " B ", 'W', MINGRADE.wireFine(), 'R', REDSTONE.dust(), 'I', IRON.ingot(), 'B', battery_generic });
		CraftingManager.addRecipeAuto(new ItemStack(analysis_tool), new Object[] { "  G", " S ", "S  ", 'G', KEY_ANYPANE, 'S', STEEL.ingot() });

		CraftingManager.addRecipeAuto(new ItemStack(kit_toolbox_empty), new Object[] { "CCC", "CIC", 'C', CU.plate(), 'I', IRON.ingot() });

		CraftingManager.addRecipeAuto(new ItemStack(screwdriver, 1), new Object[] { "  I", " I ", "S  ", 'S', STEEL.ingot(), 'I', IRON.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(screwdriver_desh, 1), new Object[] { "  I", " I ", "S  ", 'S', ANY_PLASTIC.ingot(), 'I', DESH.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(hand_drill), new Object[] { " D", "S ", " S", 'D', DURA.ingot(), 'S', KEY_STICK });
		CraftingManager.addRecipeAuto(new ItemStack(hand_drill_desh), new Object[] { " D", "S ", " S", 'D', DESH.ingot(), 'S', ANY_PLASTIC.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(chemistry_set), new Object[] { "GIG", "GCG", 'G', KEY_ANYGLASS, 'I', IRON.ingot(), 'C', CU.ingot() });
		CraftingManager.addRecipeAuto(new ItemStack(chemistry_set_boron), new Object[] { "GIG", "GCG", 'G', ModBlocks.glass_boron, 'I', STEEL.ingot(), 'C', CO.ingot() });
		CraftingManager.addRecipeAuto(ItemBlowtorch.getEmptyTool(blowtorch), new Object[] { "CC ", " I ", "CCC", 'C', CU.plate528(), 'I', IRON.ingot() });
		CraftingManager.addRecipeAuto(ItemBlowtorch.getEmptyTool(acetylene_torch), new Object[] { "SS ", " PS", " T ", 'S', STEEL.plate528(), 'P', ANY_PLASTIC.ingot(), 'T', tank_steel });
		CraftingManager.addRecipeAuto(new ItemStack(boltgun), new Object[] { "DPS", " RD", " D ", 'D', DURA.ingot(), 'P', DictFrame.fromOne(part_generic, EnumPartType.PISTON_PNEUMATIC), 'R', RUBBER.ingot(), 'S', STEEL.shell() });
		
		//Bobmazon
		CraftingManager.addShapelessAuto(new ItemStack(bobmazon_materials), new Object[] { Items.book, Items.gold_nugget, Items.string });
		CraftingManager.addShapelessAuto(new ItemStack(bobmazon_machines), new Object[] { Items.book, Items.gold_nugget, KEY_RED });
		CraftingManager.addShapelessAuto(new ItemStack(bobmazon_weapons), new Object[] { Items.book, Items.gold_nugget, KEY_GRAY });
		CraftingManager.addShapelessAuto(new ItemStack(bobmazon_tools), new Object[] { Items.book, Items.gold_nugget, KEY_GREEN });
		
		//Carts
		CraftingManager.addRecipeAuto(ItemModMinecart.createCartItem(EnumCartBase.WOOD, EnumMinecart.EMPTY), new Object[] { "P P", "WPW", 'P',KEY_SLAB, 'W', KEY_PLANKS });
		CraftingManager.addRecipeAuto(ItemModMinecart.createCartItem(EnumCartBase.STEEL, EnumMinecart.EMPTY), new Object[] { "P P", "IPI", 'P', STEEL.plate(), 'I', STEEL.ingot() });
		CraftingManager.addShapelessAuto(ItemModMinecart.createCartItem(EnumCartBase.PAINTED, EnumMinecart.EMPTY), new Object[] { ItemModMinecart.createCartItem(EnumCartBase.STEEL, EnumMinecart.EMPTY), KEY_RED });
		CraftingManager.addRecipeAuto(new ItemStack(boat_rubber), new Object[] { "L L", "LLL", 'L', ANY_RUBBER.ingot() });
		
		for(EnumCartBase base : EnumCartBase.values()) {
			
			if(EnumMinecart.DESTROYER.supportsBase(base))	CraftingManager.addRecipeAuto(ItemModMinecart.createCartItem(base, EnumMinecart.DESTROYER), new Object[] { "S S", "BLB", "SCS", 'S', STEEL.ingot(), 'B', blades_steel, 'L', Fluids.LAVA.getDict(1000), 'C', ItemModMinecart.createCartItem(base, EnumMinecart.EMPTY) });
			if(EnumMinecart.POWDER.supportsBase(base))		CraftingManager.addRecipeAuto(ItemModMinecart.createCartItem(base, EnumMinecart.POWDER), new Object[] { "PPP", "PCP", "PPP", 'P', Items.gunpowder, 'C', ItemModMinecart.createCartItem(base, EnumMinecart.EMPTY) });
			if(EnumMinecart.SEMTEX.supportsBase(base))		CraftingManager.addRecipeAuto(ItemModMinecart.createCartItem(base, EnumMinecart.SEMTEX), new Object[] { "S", "C", 'S', ModBlocks.semtex, 'C', ItemModMinecart.createCartItem(base, EnumMinecart.EMPTY) });
		}
		net.minecraft.item.crafting.CraftingManager.getInstance().addRecipe(DictFrame.fromOne(cart, EnumMinecart.CRATE), new Object[] { "C", "S", 'C', ModBlocks.crate_steel, 'S', Items.minecart }).func_92100_c();
		
		//Configged
		if(GeneralConfig.enableLBSM && GeneralConfig.enableLBSMSimpleToolRecipes) {
			addSword(	CO.block(), cobalt_decorated_sword);
			addPickaxe(	CO.block(), cobalt_decorated_pickaxe);
			addAxe(		CO.block(), cobalt_decorated_axe);
			addShovel(	CO.block(), cobalt_decorated_shovel);
			addHoe(		CO.block(), cobalt_decorated_hoe);
			addSword(	STAR.ingot(), starmetal_sword);
			addPickaxe(	STAR.ingot(), starmetal_pickaxe);
			addAxe(		STAR.ingot(), starmetal_axe);
			addShovel(	STAR.ingot(), starmetal_shovel);
			addHoe(		STAR.ingot(), starmetal_hoe);
			addSword(	SA326.ingot(), schrabidium_sword);
			addPickaxe(	SA326.ingot(), schrabidium_pickaxe);
			addAxe(		SA326.ingot(), schrabidium_axe);
			addShovel(	SA326.ingot(), schrabidium_shovel);
			addHoe(		SA326.ingot(), schrabidium_hoe);
		} else {
			/*
			CraftingManager.addRecipeAuto(new ItemStack(ModItems.cobalt_decorated_sword, 1), new Object[] { " I ", " I ", "SBS", 'I', CO.ingot(), 'S', ModItems.ingot_meteorite_forged, 'B', ModItems.cobalt_sword });
			CraftingManager.addRecipeAuto(new ItemStack(ModItems.cobalt_decorated_pickaxe, 1), new Object[] { "III", " B ", " S ", 'I', CO.ingot(), 'S', ModItems.ingot_meteorite_forged, 'B', ModItems.cobalt_pickaxe });
			CraftingManager.addRecipeAuto(new ItemStack(ModItems.cobalt_decorated_axe, 1), new Object[] { "II", "IB", " S", 'I', CO.ingot(), 'S', ModItems.ingot_meteorite_forged, 'B', ModItems.cobalt_axe });
			CraftingManager.addRecipeAuto(new ItemStack(ModItems.cobalt_decorated_shovel, 1), new Object[] { "I", "B", "S", 'I', CO.ingot(), 'S', ModItems.ingot_meteorite_forged, 'B', ModItems.cobalt_shovel });
			CraftingManager.addRecipeAuto(new ItemStack(ModItems.cobalt_decorated_hoe, 1), new Object[] { "II", " B", " S", 'I', CO.ingot(), 'S', ModItems.ingot_meteorite_forged, 'B', ModItems.cobalt_hoe });

			 */
			CraftingManager.addRecipeAuto(new ItemStack(starmetal_sword, 1), new Object[] { " I ", " B ", "ISI", 'I', STAR.ingot(), 'S', ring_starmetal, 'B', cobalt_decorated_sword });
			CraftingManager.addRecipeAuto(new ItemStack(starmetal_pickaxe, 1), new Object[] { "ISI", " B ", " I ", 'I', STAR.ingot(), 'S', ring_starmetal, 'B', cobalt_decorated_pickaxe });
			CraftingManager.addRecipeAuto(new ItemStack(starmetal_axe, 1), new Object[] { "IS", "IB", " I", 'I', STAR.ingot(), 'S', ring_starmetal, 'B', cobalt_decorated_axe });
			CraftingManager.addRecipeAuto(new ItemStack(starmetal_shovel, 1), new Object[] { "I", "B", "I", 'I', STAR.ingot(), 'B', cobalt_decorated_shovel });
			CraftingManager.addRecipeAuto(new ItemStack(starmetal_hoe, 1), new Object[] { "IS", " B", " I", 'I', STAR.ingot(), 'S', ring_starmetal, 'B', cobalt_decorated_hoe });
			CraftingManager.addRecipeAuto(new ItemStack(schrabidium_sword, 1), new Object[] { "I", "W", "S", 'I', SA326.block(), 'W', desh_sword, 'S', ANY_PLASTIC.ingot() });
			CraftingManager.addRecipeAuto(new ItemStack(schrabidium_pickaxe, 1), new Object[] { "BSB", " W ", " P ", 'B', blades_desh, 'S', SA326.block(), 'W', desh_pickaxe, 'P', ANY_PLASTIC.ingot() });
			CraftingManager.addRecipeAuto(new ItemStack(schrabidium_axe, 1), new Object[] { "BS", "BW", " P", 'B', blades_desh, 'S', SA326.block(), 'W', desh_axe, 'P', ANY_PLASTIC.ingot() });
			CraftingManager.addRecipeAuto(new ItemStack(schrabidium_shovel, 1), new Object[] { "B", "W", "P", 'B', SA326.block(), 'W', desh_shovel, 'P', ANY_PLASTIC.ingot() });
			CraftingManager.addRecipeAuto(new ItemStack(schrabidium_hoe, 1), new Object[] { "IW", " S", " S", 'I', SA326.ingot(), 'W', desh_hoe, 'S', ANY_PLASTIC.ingot() });
		}
	}

	//Common wrappers
	public static void addSword(Object ingot, Item sword) {
		addTool(ingot, sword, patternSword);
	}
	public static void addPickaxe(Object ingot, Item pick) {
		addTool(ingot, pick, patternPick);
	}
	public static void addAxe(Object ingot, Item axe) {
		addTool(ingot, axe, patternAxe);
	}
	public static void addShovel(Object ingot, Item shovel) {
		addTool(ingot, shovel, patternShovel);
	}
	public static void addHoe(Object ingot, Item hoe) {
		addTool(ingot, hoe, patternHoe);
	}
	
	public static void addTool(Object ingot, Item tool, String[] pattern) {
		CraftingManager.addRecipeAuto(new ItemStack(tool), new Object[] { pattern, 'X', ingot, '#', KEY_STICK });
	}
	
	public static final String[] patternSword = new String[] {"X", "X", "#"};
	public static final String[] patternPick = new String[] {"XXX", " # ", " # "};
	public static final String[] patternAxe = new String[] {"XX", "X#", " #"};
	public static final String[] patternShovel = new String[] {"X", "#", "#"};
	public static final String[] patternHoe = new String[] {"XX", " #", " #"};
}
