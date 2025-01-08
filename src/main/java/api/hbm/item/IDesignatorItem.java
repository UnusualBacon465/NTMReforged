package api.hbm.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;

public interface IDesignatorItem {

	/**
	 * Whether the target is valid
	 * @param level for things like restricting dimensions or getting entities
	 * @param stack to check NBT and metadata
	 * @param x position of the launch pad
	 * @param y position of the launch pad
	 * @param z position of the launch pad
	 * @return
	 */
	public boolean isReady(Level level, ItemStack stack, int x, int y, int z);
	
	/**
	 * The target position if the designator is ready
	 * @param level
	 * @param stack
	 * @param x
	 * @param y
	 * @param z
	 * @return the target
	 */
	public Vec3 getCoords(Level level, ItemStack stack, int x, int y, int z);
}
