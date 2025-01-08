package api.hbm.block;

import com.hbm.inventory.material.NTMMaterial;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public interface ICrucibleAcceptor {

	// MaterialStack Class
	public static class MaterialStack {
		public final NTMMaterial material;
		public int amount;

		public MaterialStack(NTMMaterial material, int amount) {
			this.material = material;
			this.amount = amount;
		}

		public MaterialStack copy() {
			return new MaterialStack(material, amount);
		}
	}


	/*
	 * Pouring: The metal leaves the channel/crucible and usually (but not always) falls down. The additional double coords give a more precise impact location.
	 * Also useful for entities like large crucibles since they are filled from the top.
	 */
	//public boolean canAcceptPour(Level level, int x, int y, int z, double dX, double dY, double dZ, ForgeDirection side, MaterialStack stack);
	public boolean canAcceptPartialPour(Level level, int x, int y, int z, double dX, double dY, double dZ, Direction side, MaterialStack stack);
	public MaterialStack pour(Level level, int x, int y, int z, double dX, double dY, double dZ, Direction side, MaterialStack stack);

	/*
	 * Flowing: The "safe" transfer of metal using a channel or other means, usually from block to block and usually horizontally (but not necessarily).
	 * May also be used for entities like minecarts that could be loaded from the side.
	 */
	//public boolean canAcceptFlow(Level level, int x, int y, int z, ForgeDirection side, MaterialStack stack);
	public boolean canAcceptPartialFlow(Level level, int x, int y, int z, Direction side, MaterialStack stack);
	public MaterialStack flow(Level level, int x, int y, int z, Direction side, MaterialStack stack);
}
