package riseautomatons.common;

import java.util.List;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import riseautomatons.common.entity.ContainerFactotum;
import riseautomatons.common.entity.EntityFactotum;
import riseautomatons.common.entity.GuiFactotum;
import cpw.mods.fml.common.network.IGuiHandler;
public class CommonProxy implements IGuiHandler {

	public void regiserTextures() {
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == Ids.guiFactotum) {
			List list = world.getEntitiesWithinAABB(EntityFactotum.class, AxisAlignedBB.getBoundingBox(x - 2, y - 2, z - 2, x + 2, y + 2, z + 2));
			if(list.isEmpty() == false) {
				return new ContainerFactotum(player.inventory, (EntityFactotum)list.get(0));
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == Ids.guiFactotum) {
			List list = world.getEntitiesWithinAABB(EntityFactotum.class, AxisAlignedBB.getBoundingBox(x - 2, y - 2, z - 2, x + 2, y + 2, z + 2));
			if(list.isEmpty() == false) {
				return new GuiFactotum(player.inventory, (EntityFactotum)list.get(0));
			}
		}
		return null;
	}

}
