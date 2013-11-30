package riseautomatons.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import riseautomatons.block.Blocks;

public class ItemComplex extends ItemBlock {

	public ItemComplex(int par1) {
		super(par1);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return (new StringBuilder()).append(super.getUnlocalizedName()).append(".")
				.append(dyeColorNames[itemstack.getItemDamage()]).toString();
	}

	@Override
	public Icon getIconFromDamage(int i) {
		return Blocks.tech.getIcon(2, i);
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
