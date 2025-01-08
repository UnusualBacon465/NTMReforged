package api.hbm.energymk2;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;

/**
 * Interface for all blocks that should visually connect to cables without having an IEnergyConnector tile entity.
 * This is meant for BLOCKS.
 * @author hbm
 */
public interface IEnergyConnectorBlock {

	/**
	 * Same as IEnergyConnector's method but for regular blocks that might not even have TEs. Used for rendering only!
	 * @param world The world (BlockGetter interface).
	 * @param pos The block position.
	 * @param dir The direction of the connection.
	 * @return true if the block can connect to the cable in the specified direction.
	 */
	public boolean canConnect(BlockGetter world, BlockPos pos, Direction dir);
}