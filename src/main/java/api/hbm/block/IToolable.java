package api.hbm.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hbm.inventory.RecipesCommon.ComparableStack;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IToolable {
	
	public boolean onScrew(Level level, Player player, int x, int y, int z, int side, float fX, float fY, float fZ, ToolType tool);
	
	public static enum ToolType {
		SCREWDRIVER,
		HAND_DRILL,
		DEFUSER,
		WRENCH,
		TORCH,
		BOLT;
		
		public List<ItemStack> stacksForDisplay = new ArrayList();
		private static HashMap<ComparableStack, ToolType> map = new HashMap();
		
		public void register(ItemStack stack) {
			stacksForDisplay.add(stack);
		}
		
		public static ToolType getType(ItemStack stack) {
			
			if(!map.isEmpty()) {
				return map.get(new ComparableStack(stack.getItem()));
			}
			
			for(ToolType type : ToolType.values()) {
				for(ItemStack tool : type.stacksForDisplay) {
					map.put(new ComparableStack(tool.getItem()), type);
				}
			}
			
			return map.get(new ComparableStack(stack.getItem()));
		}
	}
}
