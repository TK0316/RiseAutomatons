package riseautomatons.entity;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerFactotum extends Container {

	private EntityFactotum furnace;
	private int cookTime;
	private int burnTime;
	private int itemBurnTime;

	public ContainerFactotum(InventoryPlayer inventoryplayer,
			EntityFactotum tileentityfurnace) {
		cookTime = 0;
		burnTime = 0;
		itemBurnTime = 0;
		furnace = tileentityfurnace;
		// addSlot(new Slot(tileentityfurnace, 0, 56, 17));
		// addSlot(new Slot(tileentityfurnace, 1, 56, 53));
		// addSlot(new SlotFurnace(inventoryplayer.player, tileentityfurnace, 2,
		// 116, 35));
		addSlotToContainer(new Slot(tileentityfurnace, 0, 8, 17));
		addSlotToContainer(new Slot(tileentityfurnace, 1, 26, 17));
		addSlotToContainer(new Slot(tileentityfurnace, 2, 44, 17));
		addSlotToContainer(new Slot(tileentityfurnace, 3, 8, 53));
		addSlotToContainer(new Slot(tileentityfurnace, 4, 26, 53));
		addSlotToContainer(new Slot(tileentityfurnace, 5, 44, 53));

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 5; y++) {
				addSlotToContainer(new Slot(tileentityfurnace, 6 + y + x * 5, 80 + y * 18,
						17 + x * 18));
			}
		}

		// addSlot(new Slot(tileentityfurnace, 0, 80, 17));

		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
				addSlotToContainer(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18,
						84 + i * 18));
			}
		}

		for (int j = 0; j < 9; j++) {
			addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return furnace.isUseableByPlayer(entityplayer);
	}

	@Override
	public void detectAndSendChanges() {

		super.detectAndSendChanges();

		for (int i = 0; i < crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) crafters.get(i);

			if (cookTime != furnace.furnaceCookTime) {
				icrafting.sendProgressBarUpdate(this, 0,
						furnace.furnaceCookTime);
			}

			if (burnTime != furnace.furnaceBurnTime) {
				icrafting.sendProgressBarUpdate(this, 1,
						furnace.furnaceBurnTime);
			}

			if (itemBurnTime != furnace.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2,
						furnace.currentItemBurnTime);
			}
		}

		cookTime = furnace.furnaceCookTime;
		burnTime = furnace.furnaceBurnTime;
		itemBurnTime = furnace.currentItemBurnTime;
	}

	@Override
	public void updateProgressBar(int i, int j) {

		if (i == 0) {
			furnace.furnaceCookTime = j;
		}

		if (i == 1) {
			furnace.furnaceBurnTime = j;
		}

		if (i == 2) {
			furnace.currentItemBurnTime = j;
		}
	}

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (6 <= par2 && par2 <= 20)
            {
                if (!this.mergeItemStack(var5, 21, 57, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if ( !( 0 <= par2 && par2 <= 5) )
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(var5) != null)
                {
                    if (!this.mergeItemStack(var5, 0, 3, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(var5))
                {
                    if (!this.mergeItemStack(var5, 3, 6, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 21 && par2 < 48)
                {
                    if (!this.mergeItemStack(var5, 48, 57, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 48 && par2 < 57 && !this.mergeItemStack(var5, 21, 57, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 21, 57, false))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }

}
