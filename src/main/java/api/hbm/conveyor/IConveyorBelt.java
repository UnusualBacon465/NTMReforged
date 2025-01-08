package api.hbm.conveyor;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;

public interface IConveyorBelt {

	/** Returns true if the item should stay on the conveyor, false if the item should drop off */
	public boolean canItemStay(Level level, int x, int y, int z, Vec3 itemPos);
	public Vec3 getTravelLocation(Level level, int x, int y, int z, Vec3 itemPos, double speed);
	public Vec3 getClosestSnappingPosition(Level level, int x, int y, int z, Vec3 itemPos);
}
