package api.hbm.ntl;

import net.minecraft.world.item.ItemStack;

public class StorageStack {

	private ItemStack type;
	private long amount;

	// Constructor with ItemStack, uses getCount() to retrieve the stack size
	public StorageStack(ItemStack type) {
		this(type, type.getCount()); // Updated to getCount()
	}

	// Constructor with ItemStack and amount
	public StorageStack(ItemStack type, long amount) {
		this.type = type.copy(); // Use copy() correctly
		this.amount = amount;
		this.type.setCount(0); // Use setCount instead of stackSize
	}

	// Constructor with ItemStack and Long value
	public StorageStack(ItemStack itemStack, Long value) {
		this(itemStack, value.longValue()); // Ensure the long value is passed correctly
	}

	// Getter for type (returns a copy of ItemStack)
	public ItemStack getType() {
		return this.type.copy(); // Return a copy to prevent external modification
	}

	// Getter for amount
	public long getAmount() {
		return this.amount;
	}
}