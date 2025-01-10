package com.hbm.hazard;

import com.hbm.hazard.modifier.HazardModifier;
import com.hbm.hazard.transformer.HazardTransformerBase;
import com.hbm.inventory.RecipesCommon.ComparableStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.*;

/**
 * HazardSystem: Manages hazard-related behavior for items, entities, and more.
 */
public class HazardSystem {

	// Tag-based hazard data map
	private static final Map<ResourceLocation, HazardData> TAG_MAP = new HashMap<>();

	// Item map for general items
	private static final Map<Item, HazardData> ITEM_MAP = new HashMap<>();

	// Specific stacks (including metadata, but no NBT)
	private static final Map<ComparableStack, HazardData> STACK_MAP = new HashMap<>();

	// Blacklist for items completely exempt from hazard checks
	private static final Set<ComparableStack> STACK_BLACKLIST = new HashSet<>();
	private static final Set<ResourceLocation> TAG_BLACKLIST = new HashSet<>();

	// List of hazard transformers
	private static final List<HazardTransformerBase> TRANSFORMERS = new ArrayList<>();

	/**
	 * Registers a new hazard association to the system.
	 * Automatically determines where to store the hazard data based on the object type.
	 *
	 * @param object The object to associate the hazard with (String, Item, etc.).
	 * @param data   The hazard data to associate.
	 */
	public static void register(Object object, HazardData data) {
		if (object instanceof ResourceLocation tag) {
			TAG_MAP.put(tag, data);
		} else if (object instanceof Item item) {
			ITEM_MAP.put(item, data);
		} else if (object instanceof ComparableStack stack) {
			STACK_MAP.put(stack, data);
		} else if (object instanceof ItemStack stack) {
			STACK_MAP.put(new ComparableStack(stack.getItem()), data);
		}
	}

	/**
	 * Blacklists an object from hazard checks.
	 *
	 * @param object The object to blacklist.
	 */
	public static void blacklist(Object object) {
		if (object instanceof ResourceLocation tag) {
			TAG_BLACKLIST.add(tag);
		} else if (object instanceof ItemStack stack) {
			STACK_BLACKLIST.add(new ComparableStack(stack.getItem()).makeSingular());
		}
	}

	/**
	 * Checks if a given ItemStack is blacklisted.
	 *
	 * @param stack The ItemStack to check.
	 * @return True if blacklisted, otherwise false.
	 */
	public static boolean isItemBlacklisted(ItemStack stack) {
		if (STACK_BLACKLIST.contains(new ComparableStack(stack.getItem()).makeSingular())) {
			return true;
		}

		for (ResourceLocation tag : TAG_BLACKLIST) {
			if (stack.is(ItemTags.create(tag))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Fetches all applicable hazard entries for a given ItemStack.
	 *
	 * @param stack The ItemStack to check.
	 * @return A list of applicable HazardEntry objects.
	 */
	public static List<HazardEntry> getHazardsFromStack(ItemStack stack) {
		if (isItemBlacklisted(stack)) {
			return Collections.emptyList();
		}

		List<HazardData> chronologicalData = new ArrayList<>();
		addTagHazards(stack, chronologicalData);
		addItemHazards(stack, chronologicalData);
		addStackHazards(stack, chronologicalData);

		return resolveHazardEntries(stack, chronologicalData);
	}

	private static void addTagHazards(ItemStack stack, List<HazardData> hazards) {
		for (ResourceLocation tag : TAG_MAP.keySet()) {
			if (stack.is(ItemTags.create(tag))) {
				hazards.add(TAG_MAP.get(tag));
			}
		}
	}

	private static void addItemHazards(ItemStack stack, List<HazardData> hazards) {
		HazardData data = ITEM_MAP.get(stack.getItem());
		if (data != null) {
			hazards.add(data);
		}
	}

	private static void addStackHazards(ItemStack stack, List<HazardData> hazards) {
		ComparableStack comparableStack = new ComparableStack(stack.getItem()).makeSingular();
		HazardData data = STACK_MAP.get(comparableStack);
		if (data != null) {
			hazards.add(data);
		}
	}

	private static List<HazardEntry> resolveHazardEntries(ItemStack stack, List<HazardData> data) {
		List<HazardEntry> entries = new ArrayList<>();
		for (HazardTransformerBase transformer : TRANSFORMERS) {
			transformer.transformPre(stack, entries);
		}

		int mutex = 0;
		for (HazardData hazard : data) {
			if (hazard.doesOverride) {
				entries.clear();
			}

			if ((hazard.getMutex() & mutex) == 0) {
				entries.addAll(hazard.entries);
				mutex |= hazard.getMutex();
			}
		}

		for (HazardTransformerBase transformer : TRANSFORMERS) {
			transformer.transformPost(stack, entries);
		}

		return entries;
	}

	/**
	 * Applies hazards from a stack to an entity.
	 *
	 * @param stack  The ItemStack to check.
	 * @param entity The entity to apply hazards to.
	 */
	public static void applyHazards(ItemStack stack, LivingEntity entity) {
		for (HazardEntry entry : getHazardsFromStack(stack)) {
			entry.applyHazard(stack, entity);
		}
	}

	/**
	 * Updates the inventory of a player, applying hazards for all items.
	 *
	 * @param player The player to update.
	 */
	public static void updatePlayerInventory(Player player) {
		player.getInventory().items.forEach(stack -> {
			if (!stack.isEmpty()) {
				applyHazards(stack, player);
			}
		});

		player.getInventory().armor.forEach(stack -> {
			if (!stack.isEmpty()) {
				applyHazards(stack, player);
			}
		});
	}

	/**
	 * Updates hazards for a dropped item.
	 *
	 * @param entity The dropped item entity.
	 */
	public static void updateDroppedItem(Entity entity) {
		if (!(entity instanceof ItemEntity itemEntity)) {
			return;
		}

		ItemStack stack = itemEntity.getItem();
		if (stack.isEmpty() || !itemEntity.isAlive()) {
			return;
		}

		for (HazardEntry entry : getHazardsFromStack(stack)) {
			entry.type.updateEntity(itemEntity, HazardModifier.evalAllModifiers(stack, null, entry.baseLevel, entry.mods));
		}
	}
}
