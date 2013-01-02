package riseautomatons.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import riseautomatons.block.Blocks;

public class ItemLumo extends ItemBlock {

	public ItemLumo(int i) {
		super(i);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return (new StringBuilder()).append(super.getItemName()).append(".")
				.append(dyeColorNames[itemstack.getItemDamage()]).toString();

	}

	@Override
	public int getIconFromDamage(int i) {
		return Blocks.sky.getBlockTextureFromSideAndMetadata(2, i);
	}

	@Override
	public int getMetadata(int i) {
		return i;
	}

	public static final String dyeColorNames[] = { "sky", "walk", "wallo",
			"automatonLeg", "rod", "automatonBack", "biterHead", "robo",
			"greens", "coals", "superMetal", "techo", "lightBlue", "magenta",
			"orange", "white" };

}
