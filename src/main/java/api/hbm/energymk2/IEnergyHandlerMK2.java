package api.hbm.energymk2;

import api.hbm.tile.ILoadedTile;
import com.hbm.util.CompatEnergyControl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

/** DO NOT USE DIRECTLY! This is simply the common ancestor to providers and receivers, because all this behavior has to be excluded from conductors! */
public interface IEnergyHandlerMK2 extends IEnergyConnectorMK2, ILoadedTile {

	public long getPower();
	public void setPower(long power);
	public long getMaxPower();
	
	public static final boolean particleDebug = false;
	
	public default Vec3 getDebugParticlePosMK2() {
		BlockEntity be = (BlockEntity) this;
		Vec3 vec = Vec3.createVectorHelper(be.xCoord + 0.5, be.yCoord + 1, be.zCoord + 0.5);
		return vec;
	}
	
	public default void provideInfoForECMK2(CompoundTag tag) {
		tag.putLong(CompatEnergyControl.L_ENERGY_HE, this.getPower());
		tag.putLong(CompatEnergyControl.L_CAPACITY_HE, this.getMaxPower());
	}
}
