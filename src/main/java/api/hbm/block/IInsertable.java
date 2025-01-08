package api.hbm.block;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;

public interface IInsertable { //uwu
	
	public boolean insertItem(Level level, int x, int y, int z, Direction dir, ItemStack stack);
}
