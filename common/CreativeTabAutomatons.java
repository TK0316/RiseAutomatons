package riseautomatons.common;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Block;
import net.minecraft.src.BlockDispenser;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

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
		// TODO CreativeTabs Icon
		return Item.appleGold;
	}

}
