package api.hbm.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IDepthRockTool {

	/**
	 * Whether our item can break depthrock, has a couple of params so we can restrict mining for certain blocks, dimensions or positions
	 * @param level
	 * @param player
	 * @param tool
	 * @param block
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public boolean canBreakRock(Level level, Player player, ItemStack tool, Block block, int x, int y, int z);
}
