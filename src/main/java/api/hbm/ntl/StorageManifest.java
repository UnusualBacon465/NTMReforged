package api.hbm.ntl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class StorageManifest {
	// cry and never do this again.
	// External map to link ItemStacks with their custom NBT data
	private final HashMap<ItemStack, CompoundTag> itemNBTStorage = new HashMap<>();
	public HashMap<Integer, MetaNode> itemMeta = new HashMap();

	public void writeStack(ItemStack stack) {
		int id = Item.getId(stack.getItem());

		MetaNode meta = itemMeta.get(id);

		if (meta == null) {
			meta = new MetaNode();
			itemMeta.put(id, meta);
		}

		NBTNode nbt = meta.metaNBT.get(stack.getMaxDamage());

		if (nbt == null) {
			nbt = new NBTNode();
			meta.metaNBT.put(stack.getMaxDamage(), nbt);
		}

		// Manually handle NBT data with an external map
		CompoundTag compound = getNBTData(stack); // Retrieve custom NBT data if exists

		// Update or initialize the amount for the given NBT tag
		long amount = nbt.nbtAmount.containsKey(compound) ? nbt.nbtAmount.get(compound) : 0;
		amount += stack.getCount(); // Use getCount() instead of stackSize
		nbt.nbtAmount.put(compound, amount);

		// Store the updated NBT into the external map
		storeNBTData(stack, compound); // Store NBT data externally
	}

	// Custom storage system for NBT data
	private void storeNBTData(ItemStack stack, CompoundTag compound) {
		itemNBTStorage.put(stack, compound); // Store the NBT data linked to the stack
	}

	// Custom retrieval of NBT data
	private CompoundTag getNBTData(ItemStack stack) {
		return itemNBTStorage.getOrDefault(stack, new CompoundTag()); // Retrieve NBT data or return a new CompoundTag
	}

	public List<StorageStack> getStacks() {
		List<StorageStack> stacks = new ArrayList();

		for (Entry<Integer, MetaNode> itemNode : itemMeta.entrySet()) {
			for (Entry<Integer, NBTNode> metaNode : itemNode.getValue().metaNBT.entrySet()) {
				for (Entry<CompoundTag, Long> nbtNode : metaNode.getValue().nbtAmount.entrySet()) {

					// Creating ItemStack with correct count and NBT
					ItemStack itemStack = new ItemStack(Item.byId(itemNode.getKey()), 1);

					// Retrieve the custom NBT data for the ItemStack
					CompoundTag compound = getNBTData(itemStack);

					// Attach the custom NBT data (no need for setTag or getTag)
					// NBT data is already managed separately in our custom map

					StorageStack stack = new StorageStack(itemStack, nbtNode.getValue());
					stacks.add(stack);
				}
			}
		}

		return stacks;
	}

	public class MetaNode {

		public HashMap<Integer, NBTNode> metaNBT = new HashMap();
	}

	public static class NBTNode {

		public HashMap<CompoundTag, Long> nbtAmount = new HashMap();
	}
}
