package api.hbm.energymk2;

import com.hbm.util.CompatEnergyControl;

import api.hbm.tile.ILoadedTile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

/** DO NOT USE DIRECTLY! This is simply the common ancestor to providers and receivers, because all this behavior has to be excluded from conductors! */
public interface IEnergyHandlerMK2 extends IEnergyConnectorMK2, ILoadedTile {

	long getPower();
	void setPower(long power);
	long getMaxPower();

	public static final boolean particleDebug = false;

	// Get the position for debugging particles (updated for 1.21.1)
	public default Vec3 getDebugParticlePosMK2() {
		BlockEntity be = (BlockEntity) this; // Update to BlockEntity
		// Minecraft 1.21 uses Vec3 instead of Vec3.createVectorHelper
		Vec3 vec = new Vec3(be.getBlockPos().getX() + 0.5, be.getBlockPos().getY() + 1, be.getBlockPos().getZ() + 0.5);
		return vec;
	}

	// Provide info for the energy control system (updated NBT)
	public default void provideInfoForECMK2(CompoundTag data) {
		data.putLong(CompatEnergyControl.L_ENERGY_HE, this.getPower());
		data.putLong(CompatEnergyControl.L_CAPACITY_HE, this.getMaxPower());
	}
}