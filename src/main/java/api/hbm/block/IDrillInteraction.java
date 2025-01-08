package api.hbm.block;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IDrillInteraction {

	/**
	 * Whether the breaking of the block should be successful. Will destroy the block and not drop anything from clusters.
	 * Should use a random function, otherwise the clusters will stay there indefinitely printing free ore.
	 * @param level
	 * @param x
	 * @param y
	 * @param z
	 * @param drill Might be a tool, tile entity or anything that breaks blocks
	 * @return
	 */
	public boolean canBreak(Level level, int x, int y, int z, int meta, IMiningDrill drill);
	
	/**
	 * Returns an itemstack, usually when the block is not destroyed. Laser drills may drop this and mechanical drills will add it to their inventories.
	 * @param level
	 * @param x
	 * @param y
	 * @param z
	 * @param drill Might be a tool, tile entity or anything that breaks blocks
	 * @return
	 */
	public ItemStack extractResource(Level level, int x, int y, int z, int meta, IMiningDrill drill);
	
	/**
	 * The hardness that should be considered instead of the hardness value of the block itself
	 * @param level
	 * @param x
	 * @param y
	 * @param z
	 * @param meta
	 * @param drill
	 * @return
	 */
	public float getRelativeHardness(Level level, int x, int y, int z, int meta, IMiningDrill drill);
}
