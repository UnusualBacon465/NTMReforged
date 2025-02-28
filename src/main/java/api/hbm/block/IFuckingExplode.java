package api.hbm.block;

import com.hbm.entity.item.EntityTNTPrimedBase;

import net.minecraft.world.level.Level;

public interface IFuckingExplode {

	// Anything that can be detonated by another explosion should implement this and spawn an EntityTNTPrimedBase when hit by an explosion
	// This prevents chained explosions causing a stack overflow
	// Note that the block can still safely immediately explode, as long as the source isn't another explosion

	public void explodeEntity(Level level, double x, double y, double z, EntityTNTPrimedBase entity);

}
