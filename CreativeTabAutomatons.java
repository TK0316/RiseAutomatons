package riseautomatons;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import riseautomatons.item.Items;


public class CreativeTabAutomatons extends CreativeTabs {

	public CreativeTabAutomatons(String label) {
		super(label);
	}

	@Override
	public String getTranslatedTabLabel() {
		return "RiseAutomatons";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		// TODO CreativeTabs IIcon
		return Items.golden_apple;
	}

}
