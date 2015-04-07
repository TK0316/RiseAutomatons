package riseautomatons.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCraftSet extends Item {

	private IIcon icons[];

	public ItemCraftSet() {
		super();
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public IIcon getIconFromDamage(int par1) {
		int i = MathHelper.clamp_int(par1, 0, EnumCraftSetType.values().length-1);
		return icons[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[EnumCraftSetType.values().length];
		for (int var4 = 0; var4 < EnumCraftSetType.values().length; ++var4) {
			icons[var4] = par1IconRegister.registerIcon("riseautomatons:"+EnumCraftSetType.values()[var4].name);
		}
	}

	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		for (int var4 = 0; var4 < EnumCraftSetType.values().length; ++var4) {
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName(par1ItemStack) + "."
				+ EnumCraftSetType.values()[par1ItemStack.getItemDamage()].name;
	}
}
