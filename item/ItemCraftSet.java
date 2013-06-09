package riseautomatons.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCraftSet extends Item {

	private Icon icons[];

	public ItemCraftSet(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		int i = MathHelper.clamp_int(par1, 0, EnumCraftSetType.values().length-1);
		return icons[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister) {
		icons = new Icon[EnumCraftSetType.values().length];
		for (int var4 = 0; var4 < EnumCraftSetType.values().length; ++var4) {
			icons[var4] = par1IconRegister.registerIcon("riseautomatons:"+EnumCraftSetType.values()[var4].name);
		}
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		for (int var4 = 0; var4 < EnumCraftSetType.values().length; ++var4) {
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName(par1ItemStack)
				+ EnumCraftSetType.values()[par1ItemStack.getItemDamage()].name;
	}
}
