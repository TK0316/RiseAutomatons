package riseautomatons.world;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterEupraxia extends Teleporter {

	public TeleporterEupraxia(WorldServer par1WorldServer) {
		super(par1WorldServer);
	}

	@Override
	public void placeInPortal(Entity par1Entity, double par2, double par4,
			double par6, float par8) {
		int x = (int)par1Entity.posX;
		int y = (int)par1Entity.posY;
		int z = (int)par1Entity.posZ;
		for(int j = y; j < 255; ++j) {
			//Material m = par1Entity.worldObj.getBlockMaterial(x, j - 2, z);
			//if(m == null) continue;
			//if(m.isSolid() == false) continue;
			if(!par1Entity.worldObj.isAirBlock(x, j - 1, z)) continue;
			if(!par1Entity.worldObj.isAirBlock(x, j, z)) continue;
			if(!par1Entity.worldObj.isAirBlock(x, j + 1, z)) continue;
			par1Entity.setLocationAndAngles(x, j + 2, z, 0, 0);
			break;
		}
	}

}
