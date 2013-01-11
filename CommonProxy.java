package riseautomatons;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import riseautomatons.block.TileEntityLatch;
import riseautomatons.entity.ContainerFactotum;
import riseautomatons.entity.ContainerTote;
import riseautomatons.entity.EntityFactotum;
import riseautomatons.entity.EntityTote;
import riseautomatons.entity.GuiFactotum;
import riseautomatons.entity.GuiTote;
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
		if(ID == Ids.guiTote) {
			List list = world.getEntitiesWithinAABB(EntityTote.class, AxisAlignedBB.getBoundingBox(x - 2, y - 2, z - 2, x + 2, y + 2, z + 2));
			if(list.isEmpty() == false) {
				return new ContainerTote(player.inventory, (EntityTote)list.get(0));
			}
			TileEntity e = world.getBlockTileEntity(x, y, z);
			if(e instanceof TileEntityLatch) {
				return new ContainerTote(player.inventory, (TileEntityLatch) e);
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
		if(ID == Ids.guiTote) {
			List list = world.getEntitiesWithinAABB(EntityTote.class, AxisAlignedBB.getBoundingBox(x - 2, y - 2, z - 2, x + 2, y + 2, z + 2));
			if(list.isEmpty() == false) {
				return new GuiTote(player.inventory, (EntityTote)list.get(0));
			}
			TileEntity e = world.getBlockTileEntity(x, y, z);
			if(e instanceof TileEntityLatch) {
				return new GuiTote(player.inventory, (TileEntityLatch) e);
			}
		}
		return null;
	}

}
