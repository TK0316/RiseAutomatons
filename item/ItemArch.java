package riseautomatons.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.util.Icon;
import riseautomatons.block.Blocks;

public class ItemArch extends ItemBlock {

	public ItemArch(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	@Override
	public Icon getIconFromDamage(int i) {
		return Blocks.arch.getBlockTextureFromSideAndMetadata(2, i);
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

}
