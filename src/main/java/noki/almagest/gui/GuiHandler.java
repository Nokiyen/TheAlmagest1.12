package noki.almagest.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import noki.almagest.AlmagestData;


/**********
 * @class GuiHandler
 *
 * @description
 */
public class GuiHandler implements IGuiHandler {
	
	//******************************//
	// define member variables.
	//******************************//
	
	
	//******************************//
	// define member methods.
	//******************************//
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		
		if(id == AlmagestData.guiID_bookrest) {
			return new ContainerBookrest(player.inventory, world, new BlockPos(x, y, z));
		}
		else if(id == AlmagestData.guiID_mira) {
			return new ContainerMira(player, world, new BlockPos(x, y, z));
		}
		return null;
		
	}
	
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		
		if(id == AlmagestData.guiID_bookrest) {
			return new GuiContainerBookrest(player, world, x, y, z);
		}
		else if(id == AlmagestData.guiID_mira) {
			return new GuiContainerMira(player, world, x, y, z);
		}

		return null;
		
	}
	
}
