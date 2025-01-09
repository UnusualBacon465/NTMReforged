package com.hbm.calc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class Location {

	public int x;
	public int y;
	public int z;
	public Level level;
	
	public Location(Level level, int x, int y, int z) {
		this.level = level;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Location add(int xa, int ya, int za) {		
		return new Location(level, x + xa, y + ya, z + za);
	}
	
	public Location add(Direction dir) {
		return add(dir.getStepX(), dir.getStepY(), dir.getStepZ());
	}
	
	public BlockEntity getBlockEntity() {
		return level.getBlockEntity(new BlockPos(x, y, z));
	}
	
}
