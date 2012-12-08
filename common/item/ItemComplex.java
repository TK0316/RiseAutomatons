package riseautomatons.common.item;

import riseautomatons.common.Universal;
import riseautomatons.common.block.Blocks;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemComplex extends ItemBlock {

	public ItemComplex(int par1) {
		super(par1);
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
		return Blocks.tech.getBlockTextureFromSideAndMetadata(2, i);
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	public static final String dyeColorNames[] = { "tech", "tree", "wallo",
			"tech2", "rod", "automatonBack", "biterHead", "robo", "greens",
			"coals", "superMetal", "techo", "lightBlue", "magenta", "orange",
			"white" };
}
