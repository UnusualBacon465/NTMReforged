package api.hbm.conveyor;

import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;

public interface IEnterableBlock {

	/**
	 * Returns true of the moving item can enter from the given side. When this happens, the IConveyorItem will call onEnter and despawn
	 * @param level
	 * @param x
	 * @param y
	 * @param z
	 * @param dir
	 * @param entity
	 * @return
	 */
	public boolean canItemEnter(Level level, int x, int y, int z, Direction dir, IConveyorItem entity);
	public void onItemEnter(Level level, int x, int y, int z, Direction dir, IConveyorItem entity);
	
	public boolean canPackageEnter(Level level, int x, int y, int z, Direction dir, IConveyorPackage entity);
	public void onPackageEnter(Level level, int x, int y, int z, Direction dir, IConveyorPackage entity);
}
