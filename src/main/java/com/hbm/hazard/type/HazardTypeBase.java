package com.hbm.hazard.type;

import com.hbm.hazard.modifier.HazardModifier;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import java.util.List;

/**
 * Base class for defining hazard types.
 * Each hazard type is responsible for its specific behavior and effects.
 */
public abstract class HazardTypeBase {

	/**
	 * Applies the hazard effect to the entity holding the hazardous item.
	 *
	 * @param target The living entity holding the item.
	 * @param level  The final hazard level after applying modifiers.
	 * @param stack  The ItemStack causing the hazard.
	 */
	public abstract void onUpdate(LivingEntity target, float level, ItemStack stack);

	/**
	 * Updates hazard behavior for dropped items, such as reactive or explosive items.
	 *
	 * @param itemEntity The dropped item entity.
	 * @param level      The hazard level of the item.
	 */
	public abstract <EntityItem> void updateEntity(EntityItem itemEntity, float level);

	/**
	 * Adds hazard-related tooltip information to an item.
	 * Called client-side when rendering item tooltips.
	 *
	 * @param player     The player viewing the tooltip (may be null in some contexts).
	 * @param tooltip    The tooltip list to append hazard information to.
	 * @param level      The base hazard level of the item.
	 * @param stack      The ItemStack being inspected.
	 * @param modifiers  A list of hazard modifiers applied to the item.
	 */
	@OnlyIn(Dist.CLIENT)
	public abstract void addHazardInformation(LocalPlayer player, List<String> tooltip, float level, ItemStack stack, List<HazardModifier> modifiers);

	public void updateEntity(ItemEntity itemEntity, float level) {

	}
}
