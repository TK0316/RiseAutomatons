package riseautomatons.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import riseautomatons.block.BlockCrink;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFunctional extends Item {

	private Icon icons[];

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
		this.derp = j;
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		if(derp == 2) {
			return icons[0];
		}
		return icons[1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[2];
		icons[0] = par1IconRegister.registerIcon("riseautomatons:techifier");
		icons[1] = par1IconRegister.registerIcon("riseautomatons:naturizer");
	}

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
			AutomatonActions.Frassify(world, entityplayer, world.rand.nextInt(BlockCrink.derk.length));
		} else if (this.derp == 4) {
			AutomatonActions.Naturalization(world, entityplayer);
		}
		return itemstack;
	}

}
