package api.hbm.block;

import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;

//applicable to blocks and tile entities
public interface ILaserable {
	
	public void addEnergy(Level level, int x, int y, int z, long energy, Direction dir);

}
