package riseautomatons.common.block;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;

public class TileEntityBeacon extends TileEntity {
	public int numeral = 0;
	public int mode = 1;

	public TileEntityBeacon() {
		System.out.println("NEW BEACON" + numeral);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		numeral = par1NBTTagCompound.getInteger("numeral");
		mode = par1NBTTagCompound.getInteger("mode");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("numeral", numeral);
		par1NBTTagCompound.setInteger("mode", mode);
	}

	public int getSiren() {
		return mode;
	}
}
