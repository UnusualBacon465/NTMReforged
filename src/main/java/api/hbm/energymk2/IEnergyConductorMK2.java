package api.hbm.energymk2;

import com.hbm.lib.Library;
import com.hbm.util.fauxpointtwelve.BlockPos;
import com.hbm.util.fauxpointtwelve.DirPos;

import api.hbm.energymk2.Nodespace.PowerNode;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction; // Import the correct Direction class

public interface IEnergyConductorMK2 extends IEnergyConnectorMK2 {

	public default PowerNode createNode() {
		BlockEntity tile = (BlockEntity) this;
		return new PowerNode(new BlockPos(tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ())).setConnections(
				new DirPos(tile.getBlockPos().getX() + 1, tile.getBlockPos().getY(), tile.getBlockPos().getZ(), Direction.EAST), // Use Direction.EAST
				new DirPos(tile.getBlockPos().getX() - 1, tile.getBlockPos().getY(), tile.getBlockPos().getZ(), Direction.WEST), // Use Direction.WEST
				new DirPos(tile.getBlockPos().getX(), tile.getBlockPos().getY() + 1, tile.getBlockPos().getZ(), Direction.UP), // Use Direction.UP
				new DirPos(tile.getBlockPos().getX(), tile.getBlockPos().getY() - 1, tile.getBlockPos().getZ(), Direction.DOWN), // Use Direction.DOWN
				new DirPos(tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ() + 1, Direction.SOUTH), // Use Direction.SOUTH
				new DirPos(tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ() - 1, Direction.NORTH) // Use Direction.NORTH
		);
	}
}