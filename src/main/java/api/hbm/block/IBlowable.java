package api.hbm.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public interface IBlowable {

	/**
	 * Called server-side when a fan blows on an IBlowable in range every tick.
	 *
	 * @param level The current world level (server-side)
	 * @param pos   The position of the block being blown on
	 * @param dir   The direction the fan is blowing from
	 * @param dist  The distance from the fan to the block
	 */
	void applyFan(Level level, BlockPos pos, Direction dir, int dist);
}