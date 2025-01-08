package api.hbm.block;

import net.minecraft.world.level.Level;

public interface IRadioControllable {

	public String[] getVariables(Level level, int x, int y, int z);
	public void receiveSignal(Level level, int x, int y, int z, String channel, String signal);
}
