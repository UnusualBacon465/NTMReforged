package api.hbm.energymk2;

import api.hbm.energymk2.Nodespace.PowerNode;
import com.hbm.lib.Library;
import com.hbm.util.fauxpointtwelve.BlockPos;
import com.hbm.util.fauxpointtwelve.DirPos;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface IEnergyConductorMK2 extends IEnergyConnectorMK2 {
	
	public default PowerNode createNode() {
		BlockEntity tile = (BlockEntity) this;
		return new PowerNode(new BlockPos(tile.xCoord, tile.yCoord, tile.zCoord)).setConnections(
				new DirPos(tile.xCoord + 1, tile.yCoord, tile.zCoord, Library.POS_X),
				new DirPos(tile.xCoord - 1, tile.yCoord, tile.zCoord, Library.NEG_X),
				new DirPos(tile.xCoord, tile.yCoord + 1, tile.zCoord, Library.POS_Y),
				new DirPos(tile.xCoord, tile.yCoord - 1, tile.zCoord, Library.NEG_Y),
				new DirPos(tile.xCoord, tile.yCoord, tile.zCoord + 1, Library.POS_Z),
				new DirPos(tile.xCoord, tile.yCoord, tile.zCoord - 1, Library.NEG_Z)
				);
	}
}
