package api.hbm.energymk2;

import api.hbm.energymk2.Nodespace.PowerNode;
import com.hbm.handler.threading.PacketThreading;
import com.hbm.main.NetworkHandler;
import com.hbm.packet.toclient.AuxParticlePacketNT;
import com.hbm.util.Compat;
import net.minecraft.world.level.biome.Climate;
import net.neoforged.neoforge.network.PacketDistributor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.neoforged.neoforge.network.registration.NetworkChannel;
import net.neoforged.neoforge.network.registration.NetworkRegistry;

/** If it sends energy, use this */
public interface IEnergyProviderMK2 extends IEnergyHandlerMK2 {

	/** Uses up available power, default implementation has no sanity checking, make sure that the requested power is lequal to the current power */
	public default void usePower(long power) {
		this.setPower(this.getPower() - power);
	}

	public default long getProviderSpeed() {
		return this.getMaxPower();
	}

	public default void tryProvide(Level level, int x, int y, int z, Direction dir) {

		BlockEntity te = Compat.getTileStandard(level, x, y, z);
		boolean red = false;

		if(te instanceof IEnergyConductorMK2) {
			IEnergyConductorMK2 con = (IEnergyConductorMK2) te;
			if(con.canConnect(dir.getOpposite())) {

				PowerNode node = Nodespace.getNode(level, x, y, z);

				if(node != null && node.net != null) {
					node.net.addProvider(this);
					red = true;
				}
			}
		}

		if(te instanceof IEnergyReceiverMK2 && te != this) {
			IEnergyReceiverMK2 rec = (IEnergyReceiverMK2) te;
			if(rec.canConnect(dir.getOpposite())) {
				long provides = Math.min(this.getPower(), this.getProviderSpeed());
				long receives = Math.min(rec.getMaxPower() - rec.getPower(), rec.getReceiverSpeed());
				long toTransfer = Math.min(provides, receives);
				toTransfer -= rec.transferPower(toTransfer);
				this.usePower(toTransfer);
			}
		}

		if(particleDebug) {
			CompoundTag tag = new CompoundTag();
			tag.putString("type", "network");
			tag.putString("mode", "power");
			double posX = x + 0.5 - dir.getStepX() * 0.5 + level.getRandom().nextDouble() * 0.5 - 0.25;
			double posY = y + 0.5 - dir.getStepY() * 0.5 + level.getRandom().nextDouble() * 0.5 - 0.25;
			double posZ = z + 0.5 - dir.getStepZ() * 0.5 + level.getRandom().nextDouble() * 0.5 - 0.25;
			tag.putDouble("mX", dir.getStepX() * (red ? 0.025 : 0.1));
			tag.putDouble("mY", dir.getStepY() * (red ? 0.025 : 0.1));
			tag.putDouble("mZ", dir.getStepZ() * (red ? 0.025 : 0.1));
			//todo fix auxparticlent and other packets
		}
	}
}
