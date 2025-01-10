package com.hbm.hazard.modifier;

import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;

public abstract class HazardModifier {

	public abstract float modify(ItemStack stack, LivingEntity holder, float level);
	
	/**
	 * Returns the level after applying all modifiers to it, in order.
	 * @param stack
	 * @param entity nullable
	 * @param level
	 * @param mods
	 * @return
	 */
	public static float evalAllModifiers(ItemStack stack, LivingEntity entity, float level, List<HazardModifier> mods) {
		
		for(HazardModifier mod : mods) {
			level = mod.modify(stack, entity, level);
		}
		
		return level;
	}
}
