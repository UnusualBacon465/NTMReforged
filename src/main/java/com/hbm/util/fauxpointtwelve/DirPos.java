package com.hbm.util.fauxpointtwelve;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class DirPos {
	private int x = 0;
    private int y = 0;
    private int z = 0;
	private Direction dir = null;  // Direction that the connection points to

	// Constructor that takes x, y, z coordinates and a Direction
	public DirPos(int x, int y, int z, Direction dir) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.dir = dir;
	}


	// Method to return a BlockPos based on the stored coordinates
	public BlockPos getBlockPos() {
		return new BlockPos(x, y, z);
	}

	// Method to get the Direction for this connection
	public Direction getDir() {
		return dir;
	}

	// Getter methods for the individual coordinates
	public int getX() { return x; }
	public int getY() { return y; }
	public int getZ() { return z; }
}