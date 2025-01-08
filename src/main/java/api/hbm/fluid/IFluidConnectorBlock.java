package api.hbm.fluid;

import com.hbm.inventory.fluid.FluidType;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.Direction;

public interface IFluidConnectorBlock {

	/** dir is the face that is connected to, the direction going outwards from the block */
	boolean canConnect(FluidType type, BlockGetter world, BlockPos pos, Direction dir);
}
