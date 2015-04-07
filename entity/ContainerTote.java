package riseautomatons.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTote extends Container {

	private IInventory lowerChestInventory;
	private int numRows;

	public ContainerTote(IInventory par1IInventory,
			IInventory par2IInventory) {
		lowerChestInventory = par2IInventory;
		numRows = 3;// par2IInventory.getSizeInventory() / 9;
		par2IInventory.openInventory();
		int i = (numRows - 4) * 18;

		for (int j = 0; j < 3; j++) {
			for (int i1 = 0; i1 < 3; i1++) {
				addSlotToContainer(new Slot(par2IInventory, i1 + j * 3, 62 + i1 * 18,
						17 + j * 18));
			}
		}

		for (int k = 0; k < 3; k++) {
			for (int j1 = 0; j1 < 9; j1++) {
				addSlotToContainer(new Slot(par1IInventory, j1 + k * 9 + 9, 8 + j1 * 18,
						102 + k * 18 + i));
			}
		}

		for (int l = 0; l < 9; l++) {
			addSlotToContainer(new Slot(par1IInventory, l, 8 + l * 18, 160 + i)); // 161
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return lowerChestInventory.isUseableByPlayer(par1EntityPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1) {

		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(par1);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par1 < numRows * 9) {
				if (!mergeItemStack(itemstack1, numRows * 9,
						inventorySlots.size(), true)) {
					return null;
				}
			} else if (!mergeItemStack(itemstack1, 0, numRows * 9, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		super.onContainerClosed(par1EntityPlayer);
		this.lowerChestInventory.closeInventory();
	}

}
