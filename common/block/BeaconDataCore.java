package riseautomatons.common.block;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.WorldSavedData;

public class BeaconDataCore extends WorldSavedData {

	int cap;

	public BeaconDataCore(String par1Str) {
		super(par1Str);
	}

	public BeaconDataCore(String par1Str, int cap) {
		super(par1Str);
		this.cap = cap;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		cap = par1NBTTagCompound.getInteger("cap");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setInteger("cap", cap);
	}

}
