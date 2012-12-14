package riseautomatons.common.item;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemFunctional extends Item {

	public int derp;

	public ItemFunctional(int i, int j) {

		super(i);
		this.derp = 0;
		if (j == 1) {
			setMaxDamage(1);
		} else if (j == 4) {
			setMaxDamage(26);
			this.maxStackSize = 1;
		} else {
			setMaxDamage(13);
			this.maxStackSize = 1;
		}
		this.derp = j;	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world,
			EntityPlayer entityplayer) {

		itemstack.damageItem(1, entityplayer);
		if (this.derp == 1) {
			if (itemstack.getItemDamage() == 1) {
				world.setWorldTime(0L);
			} else {
				world.setWorldTime(13500L);
			}
		} else if (this.derp == 2) {
			AutomatonActions.Frassify(world, entityplayer);
		} else if (this.derp == 4) {
			AutomatonActions.Naturalization(world, entityplayer);
		}
		return itemstack;
	}

}
