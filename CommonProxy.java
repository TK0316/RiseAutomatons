package riseautomatons;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import riseautomatons.entity.ContainerFactotum;
import riseautomatons.entity.EntityFactotum;
import riseautomatons.entity.GuiFactotum;
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
