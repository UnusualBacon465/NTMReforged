package api.hbm.energymk2;

import net.minecraft.world.IBlockAccess;
import net.minecraft.core.Direction;

/**
 * Interface for all blocks that should visually connect to cables without having an IEnergyConnector tile entity.
 * This is meant for BLOCKS
 * @author hbm
 *
 */
public interface IEnergyConnectorBlock {
	
	/**
	 * Same as IEnergyConnector's method but for regular blocks that might not even have TEs. Used for rendering only!
	 * @param level
	 * @param x
	 * @param y
	 * @param z
	 * @param dir
	 * @return
	 */
	public boolean canConnect(IBlockAccess level, int x, int y, int z, Direction dir);
}
