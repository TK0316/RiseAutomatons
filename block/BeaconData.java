package riseautomatons.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class BeaconData extends WorldSavedData {

	int x;
	int y;
	int z;

	public BeaconData(String par1Str) {
		super(par1Str);
	}

	public BeaconData(String par1Str,int x,int y,int z) {
		super(par1Str);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
    	x = par1NBTTagCompound.getInteger("x");
    	y = par1NBTTagCompound.getInteger("y");
    	z = par1NBTTagCompound.getInteger("z");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
    	par1NBTTagCompound.setInteger("x", x);
    	par1NBTTagCompound.setInteger("y", y);
    	par1NBTTagCompound.setInteger("z", z);
	}

	@Override
	public String toString() {
		return this.mapName + ": " + x + "," + y + "," + z;
	}

}
