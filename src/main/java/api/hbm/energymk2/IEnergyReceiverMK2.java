package api.hbm.energymk2;

import com.hbm.interfaces.NotableComments;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.toclient.AuxParticlePacketNT;
import com.hbm.util.Compat;

import api.hbm.energymk2.Nodespace.PowerNode;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;

/** If it receives energy, use this */
@NotableComments
public interface IEnergyReceiverMK2 extends IEnergyHandlerMK2 {

	public default long transferPower(long power) {
		if(power + this.getPower() <= this.getMaxPower()) {
			this.setPower(power + this.getPower());
			return 0;
		}
		long capacity = this.getMaxPower() - this.getPower();
		long overshoot = power - capacity;
		this.setPower(this.getMaxPower());
		return overshoot;
	}

	public default long getReceiverSpeed() {
		return this.getMaxPower();
	}

	public default void trySubscribe(Level level, int x, int y, int z, Direction dir) {

		BlockEntity te = Compat.getTileStandard(level, x, y, z);
		boolean red = false;

		if(te instanceof IEnergyConductorMK2) {
			IEnergyConductorMK2 con = (IEnergyConductorMK2) te;
			if(!con.canConnect(dir.getOpposite())) return;

			PowerNode node = Nodespace.getNode(level, x, y, z);

			if(node != null && node.net != null) {
				node.net.addReceiver(this);
				red = true;
			}
		}

		if(particleDebug) {
			CompoundTag data = new CompoundTag();
			data.putString("type", "network");
			data.putString("mode", "power");
			double posX = x + 0.5 + dir.getStepX() * 0.5 + level.random.nextDouble() * 0.5 - 0.25;
			double posY = y + 0.5 + dir.getStepY() * 0.5 + level.random.nextDouble() * 0.5 - 0.25;
			double posZ = z + 0.5 + dir.getStepZ() * 0.5 + level.random.nextDouble() * 0.5 - 0.25;
			data.putDouble("mX", -dir.getStepX() * (red ? 0.025 : 0.1));
			data.putDouble("mY", -dir.getStepY() * (red ? 0.025 : 0.1));
			data.putDouble("mZ", -dir.getStepZ() * (red ? 0.025 : 0.1));

			// Get the ResourceKey for the current world
			ResourceKey<Level> levelKey = level.dimension();

			// Send the packet with ResourceKey for the world instead of Level
			PacketDispatcher.sendToAllAround(new AuxParticlePacketNT(data, posX, posY, posZ),
					levelKey, new BlockPos(x, y, z), 25); // Adjusted send method
		}
	}

	public default void tryUnsubscribe(Level level, int x, int y, int z) {

		BlockEntity te = level.getBlockEntity(new BlockPos(x, y, z));

		if(te instanceof IEnergyConductorMK2) {
			IEnergyConductorMK2 con = (IEnergyConductorMK2) te;
			PowerNode node = con.createNode();

			if(node != null && node.net != null) {
				node.net.removeReceiver(this);
			}
		}
	}

	/**
	 * Project MKUltra was an illegal human experiments program designed and undertaken by the U.S. Central Intelligence Agency (CIA)
	 * to develop procedures and identify drugs that could be used during interrogations to weaken people and force confessions through
	 * brainwashing and psychological torture. It began in 1953 and was halted in 1973. MKUltra used numerous methods to manipulate
	 * its subjects' mental states and brain functions, such as the covert administration of high doses of psychoactive drugs (especially LSD)
	 * and other chemicals without the subjects' consent, electroshocks, hypnosis, sensory deprivation, isolation, verbal and sexual
	 * abuse, and other forms of torture.
	 * MKUltra was preceded by Project Artichoke. It was organized through the CIA's Office of Scientific Intelligence and coordinated
	 * with the United States Army Biological Warfare Laboratories. The program engaged in illegal activities, including the
	 * use of U.S. and Canadian citizens as unwitting test subjects. MKUltra's scope was broad, with activities carried
	 * out under the guise of research at more than 80 institutions aside from the military, including colleges and universities,
	 * hospitals, prisons, and pharmaceutical companies. The CIA operated using front organizations, although some top officials at these
	 * institutions were aware of the CIA's involvement.
	 * MKUltra was revealed to the public in 1975 by the Church Committee of the United States Congress and Gerald Ford's United States
	 * President's Commission on CIA activities within the United States (the Rockefeller Commission). Investigative efforts were hampered
	 * by CIA Director Richard Helms's order that all MKUltra files be destroyed in 1973; the Church Committee and Rockefeller Commission
	 * investigations relied on the sworn testimony of direct participants and on the small number of documents that survived Helms's order.
	 * In 1977, a Freedom of Information Act request uncovered a cache of 20,000 documents relating to MKUltra, which led to Senate hearings.
	 * Some surviving information about MKUltra was declassified in 2001.
	 * */
	public default ConnectionPriority getPriority() {
		return ConnectionPriority.NORMAL;
	}

	/** More is better-er */
	public enum ConnectionPriority {
		LOWEST,
		LOW,
		NORMAL,
		HIGH,
		HIGHEST
	}
}
