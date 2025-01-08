package api.hbm.fluid;

import com.hbm.inventory.fluid.tank.FluidTank;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.toclient.AuxParticlePacketNT;
import com.hbm.inventory.fluid.FluidType;

public interface IFluidUser extends IFluidConnector {

	default void sendFluid(FluidTank tank, Level level, BlockPos pos, Direction dir) {
		sendFluid(tank.getTankType(), tank.getPressure(), level, pos, dir);
	}

	default void sendFluid(FluidType type, int pressure, Level level, BlockPos pos, Direction dir) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		boolean wasSubscribed = false;
		boolean red = false;

		if (blockEntity instanceof IFluidConductor conductor) {
			if (conductor.getPipeNet(type) != null && conductor.getPipeNet(type).isSubscribed(this)) {
				conductor.getPipeNet(type).unsubscribe(this);
				wasSubscribed = true;
			}
		}

		if (blockEntity instanceof IFluidConnector connector) {
			if (connector.canConnect(type, dir.getOpposite())) {
				long toSend = this.getTotalFluidForSend(type, pressure);
				if (toSend > 0) {
					long transfer = toSend - connector.transferFluid(type, pressure, toSend);
					this.removeFluidForTransfer(type, pressure, transfer);
				}
				red = true;
			}
		}

		if (wasSubscribed && blockEntity instanceof IFluidConductor conductor) {
			if (conductor.getPipeNet(type) != null && !conductor.getPipeNet(type).isSubscribed(this)) {
				conductor.getPipeNet(type).subscribe(this);
			}
		}

		if (level instanceof ServerLevel serverLevel && particleDebug()) {
			CompoundTag data = new CompoundTag();
			data.putString("type", "network");
			data.putString("mode", "fluid");
			data.putInt("color", type.getColor());
			double offsetX = 0.5 - dir.getStepX() * 0.5;
			double offsetY = 0.5 - dir.getStepY() * 0.5;
			double offsetZ = 0.5 - dir.getStepZ() * 0.5;
			double posX = pos.getX() + offsetX + serverLevel.random.nextDouble() * 0.5 - 0.25;
			double posY = pos.getY() + offsetY + serverLevel.random.nextDouble() * 0.5 - 0.25;
			double posZ = pos.getZ() + offsetZ + serverLevel.random.nextDouble() * 0.5 - 0.25;
			data.putDouble("mX", dir.getStepX() * (red ? 0.025 : 0.1));
			data.putDouble("mY", dir.getStepY() * (red ? 0.025 : 0.1));
			data.putDouble("mZ", dir.getStepZ() * (red ? 0.025 : 0.1));
			PacketDispatcher.sendToAllAround(new AuxParticlePacketNT(data, posX, posY, posZ), serverLevel.dimension(), pos, 25);
		}
	}

	static IPipeNet getPipeNet(Level level, BlockPos pos, FluidType type) {
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (blockEntity instanceof IFluidConductor conductor) {
			return conductor.getPipeNet(type);
		}
		return null;
	}

	default long getTotalFluidForSend(FluidType type, int pressure) {
		return 0;
	}

	default void removeFluidForTransfer(FluidType type, int pressure, long amount) {}

	FluidTank[] getAllTanks();

	// Utility for debugging
	default boolean particleDebug() {
		return false;
	}
}