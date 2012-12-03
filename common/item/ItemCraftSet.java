package riseautomatons.common.item;

import java.util.List;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ItemCraftSet extends Item {

	public ItemCraftSet(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public int getIconFromDamage(int par1) {
		return par1;
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		for (int var4 = 0; var4 < EnumCraftSetType.values().length; ++var4) {
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	@Override
	public String getItemNameIS(ItemStack par1ItemStack) {
		return super.getItemNameIS(par1ItemStack)
				+ EnumCraftSetType.values()[par1ItemStack.getItemDamage()].name;
	}
}
