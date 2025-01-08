package api.hbm.fluid;

import com.hbm.inventory.fluid.FluidType;
import api.hbm.tile.ILoadedTile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface IFluidConnector extends ILoadedTile {

	/**
	 * Returns the amount of fluid that remains.
	 *
	 * @param type      The type of fluid.
	 * @param pressure  The pressure of the fluid.
	 * @param fluid     The amount of fluid to transfer.
	 * @return The remaining fluid after transfer.
	 */
	long transferFluid(FluidType type, int pressure, long fluid);

	/**
	 * Whether the given side can be connected to.
	 *
	 * @param type The fluid type.
	 * @param dir  The direction to connect.
	 * @return True if the side can be connected.
	 */
	default boolean canConnect(FluidType type, Direction dir) {
		return dir != null;
	}

	/**
	 * Returns the amount of fluid that this machine can receive.
	 *
	 * @param type     The type of fluid.
	 * @param pressure The fluid pressure.
	 * @return The amount of fluid that can be received.
	 */
	long getDemand(FluidType type, int pressure);

	/**
	 * Subscribes this tile to a nearby fluid network.
	 *
	 * @param type The fluid type.
	 * @param level The level (world) the tile is in.
	 * @param pos  The position of this tile.
	 * @param dir  The direction to subscribe.
	 */
	default void trySubscribe(FluidType type, Level level, BlockPos pos, Direction dir) {
		BlockPos neighborPos = pos.relative(dir);
		BlockEntity blockEntity = level.getBlockEntity(neighborPos);
		boolean isConnected = false;

		if (blockEntity instanceof IFluidConductor conductor) {
			if (!conductor.canConnect(type, dir)) {
				return;
			}

			IPipeNet network = conductor.getPipeNet(type);
			if (network != null && !network.isSubscribed(this)) {
				network.subscribe(this);
				isConnected = true;
			}
		}

		if (particleDebug) {
			spawnDebugParticles(type, level, neighborPos, dir, isConnected);
		}
	}

	/**
	 * Unsubscribes this tile from a nearby fluid network.
	 *
	 * @param type  The fluid type.
	 * @param level The level (world) the tile is in.
	 * @param pos   The position of this tile.
	 */
	default void tryUnsubscribe(FluidType type, Level level, BlockPos pos) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof IFluidConductor conductor) {
			IPipeNet network = conductor.getPipeNet(type);
			if (network != null && network.isSubscribed(this)) {
				network.unsubscribe(this);
			}
		}
	}

	/**
	 * Spawns debug particles for fluid connections.
	 */
	private void spawnDebugParticles(FluidType type, Level level, BlockPos pos, Direction dir, boolean isConnected) {
		CompoundTag data = new CompoundTag();
		data.putString("type", "network");
		data.putString("mode", "fluid");
		data.putInt("color", type.getColor());

		double offsetX = dir.getStepX() * 0.5 + 0.5;
		double offsetY = dir.getStepY() * 0.5 + 0.5;
		double offsetZ = dir.getStepZ() * 0.5 + 0.5;

		data.putDouble("mX", -dir.getStepX() * (isConnected ? 0.025 : 0.1));
		data.putDouble("mY", -dir.getStepY() * (isConnected ? 0.025 : 0.1));
		data.putDouble("mZ", -dir.getStepZ() * (isConnected ? 0.025 : 0.1));

		// Replace with your modern particle spawning logic
		// e.g., send particle packets to clients
	}

	boolean particleDebug = false;
}