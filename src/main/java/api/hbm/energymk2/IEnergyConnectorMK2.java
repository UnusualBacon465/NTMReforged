package api.hbm.energymk2;

import net.minecraft.core.Direction;

public interface IEnergyConnectorMK2 {

	/**
	 * Whether the given side can be connected to.
	 * 'dir' refers to the side of this block, not the connecting block doing the check.
	 *
	 * @param dir The direction to check.
	 * @return true if the side can connect, false otherwise.
	 */
	public default boolean canConnect(Direction dir) {
		// Ensure dir is not null and check that it is a valid direction
		return dir != null && dir != Direction.values()[0];  // Direction.values()[0] should be 'UP', which we use for null-like cases.
	}
}