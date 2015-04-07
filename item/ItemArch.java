package riseautomatons.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import riseautomatons.block.Blocks;

public class ItemArch extends ItemBlock {

	public ItemArch(Block par1) {
		super(par1);
		setHasSubtypes(true);
	}

	@Override
	public IIcon getIconFromDamage(int i) {
		return Blocks.arch.getIcon(2, i);
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

}
