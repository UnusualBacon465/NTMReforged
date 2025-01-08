package api.hbm.energymk2;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;
import java.util.Map;

public interface IBatteryItem {

	public void chargeBattery(ItemStack stack, long i);
	public void setCharge(ItemStack stack, long i);
	public void dischargeBattery(ItemStack stack, long i);
	public long getCharge(ItemStack stack);
	public long getMaxCharge(ItemStack stack);
	public long getChargeRate();
	public long getDischargeRate();

	/** Returns a string for the NBT tag name of the long storing power */
	public default String getChargeTagName() {
		return "charge";
	}

	/** Returns a string for the NBT tag name of the long storing power */
	public static String getChargeTagName(ItemStack stack) {
		return ((IBatteryItem) stack.getItem()).getChargeTagName();
	}

	// External NBT storage map to store NBT data
	static Map<ItemStack, CompoundTag> itemNBTStorage = new HashMap<>();

	/** Returns an empty battery stack from the passed ItemStack, the original won't be modified */
	public static ItemStack emptyBattery(ItemStack stack) {
		// Ensure the ItemStack is valid and the item is a battery
		if (!stack.isEmpty() && stack.getItem() instanceof IBatteryItem) {
			String keyName = getChargeTagName(stack); // Get the NBT key for charge
			ItemStack stackOut = stack.copy(); // Create a copy of the stack to modify

			// Retrieve the NBT data from the external storage map
			CompoundTag tag = itemNBTStorage.getOrDefault(stackOut, new CompoundTag()); // Get stored NBT or create a new one

			long currentCharge = tag.getLong(keyName); // Fetch current charge

			// Set the charge value to 0 if it's not already
			if (currentCharge > 0) {
				tag.putLong(keyName, 0L); // Reset the charge to 0
			}

			// Store the updated NBT data back in the external storage map
			itemNBTStorage.put(stackOut, tag);

			return stackOut; // Return the modified ItemStack
		}

		return ItemStack.EMPTY; // Return empty stack if invalid
	}

	/** Returns an empty battery stack from the passed Item */
	public static ItemStack emptyBattery(Item item) {
		return item instanceof IBatteryItem ? emptyBattery(new ItemStack(item)) : null;
	}
}
