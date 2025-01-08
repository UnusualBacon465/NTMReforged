package api.hbm.energymk2;

import com.hbm.lib.Library;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.toclient.AuxParticlePacketNT;
import com.hbm.util.Compat;
import api.hbm.energymk2.Nodespace.PowerNode;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

/** If it sends energy, use this */
public interface IEnergyProviderMK2 extends IEnergyHandlerMK2 {

	public default void usePower(long power) {
		this.setPower(this.getPower() - power);
	}

	public default long getProviderSpeed() {
		return this.getMaxPower();
	}

	public default void tryProvide(Level level, int x, int y, int z, Direction dir) {
		BlockEntity te = Compat.getTileStandard(level, x, y, z);
		boolean red = false;

		if (te instanceof IEnergyConductorMK2) {
			IEnergyConductorMK2 con = (IEnergyConductorMK2) te;
			if (con.canConnect(dir.getOpposite())) {
				PowerNode node = Nodespace.getNode(level, x, y, z);
				if (node != null && node.net != null) {
					node.net.addProvider(this);
					red = true;
				}
			}
		}

		if (te instanceof IEnergyReceiverMK2 && te != this) {
			IEnergyReceiverMK2 rec = (IEnergyReceiverMK2) te;
			if (rec.canConnect(dir.getOpposite())) {
				long provides = Math.min(this.getPower(), this.getProviderSpeed());
				long receives = Math.min(rec.getMaxPower() - rec.getPower(), rec.getReceiverSpeed());
				long toTransfer = Math.min(provides, receives);
				toTransfer -= rec.transferPower(toTransfer);
				this.usePower(toTransfer);
			}
		}

		if (particleDebug) {
			// Create NBT data for the particle
			CompoundTag data = new CompoundTag();
			data.putString("type", "network");
			data.putString("mode", "power");

			// Compute particle position
			double posX = x + 0.5 - dir.getStepX() * 0.5 + level.random.nextDouble() * 0.5 - 0.25;
			double posY = y + 0.5 - dir.getStepY() * 0.5 + level.random.nextDouble() * 0.5 - 0.25;
			double posZ = z + 0.5 - dir.getStepZ() * 0.5 + level.random.nextDouble() * 0.5 - 0.25;

			// Add motion data
			data.putDouble("mX", dir.getStepX() * (red ? 0.025 : 0.1));
			data.putDouble("mY", dir.getStepY() * (red ? 0.025 : 0.1));
			data.putDouble("mZ", dir.getStepZ() * (red ? 0.025 : 0.1));

			// Create the packet
			AuxParticlePacketNT packet = new AuxParticlePacketNT(data, posX, posY, posZ);

			// Send the packet to nearby players
			BlockPos blockPos = new BlockPos(x, y, z);
			PacketDispatcher.sendToAllAround(packet, level.dimension(), blockPos, 25);
		}
	}
}