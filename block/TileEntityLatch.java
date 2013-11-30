package riseautomatons.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLatch extends TileEntity implements IInventory {

	public ItemStack dispenserContents[] = new ItemStack[9];;

	public TileEntityLatch() {
	}

	@Override
	public int getSizeInventory() {
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int par1) {
		return dispenserContents[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {

		if (dispenserContents[par1] != null) {
			if (dispenserContents[par1].stackSize <= par2) {
				ItemStack itemstack = dispenserContents[par1];
				dispenserContents[par1] = null;
				onInventoryChanged();
				return itemstack;
			}

			ItemStack itemstack1 = dispenserContents[par1].splitStack(par2);

			if (dispenserContents[par1].stackSize == 0) {
				dispenserContents[par1] = null;
			}

			onInventoryChanged();
			return itemstack1;
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {

		if (dispenserContents[par1] != null) {
			ItemStack itemstack = dispenserContents[par1];
			dispenserContents[par1] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {

		dispenserContents[par1] = par2ItemStack;

		if (par2ItemStack != null
				&& par2ItemStack.stackSize > getInventoryStackLimit()) {
			par2ItemStack.stackSize = getInventoryStackLimit();
		}

		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "container.latch";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {

		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		dispenserContents = new ItemStack[getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist
					.tagAt(i);
			int j = nbttagcompound.getByte("Slot") & 0xff;

			if (j >= 0 && j < dispenserContents.length) {
				dispenserContents[j] = ItemStack
						.loadItemStackFromNBT(nbttagcompound);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {

		super.writeToNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < dispenserContents.length; i++) {
			if (dispenserContents[i] != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				dispenserContents[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {

		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}

		return par1EntityPlayer.getDistanceSq((double) xCoord + 0.5D,
				(double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64D;

	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	public int getNextStackFromInventory() {

		int i = -1;

		for (int k = 0; k < dispenserContents.length; k++) {
			if (dispenserContents[k] != null) {
				i = k;
				break;
			}
		}

		return i;

	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

}
