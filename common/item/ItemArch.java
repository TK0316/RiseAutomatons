package riseautomatons.common.item;

import riseautomatons.common.Universal;
import riseautomatons.common.block.Blocks;
import net.minecraft.src.ItemBlock;

public class ItemArch extends ItemBlock {

	public ItemArch(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	@Override
	public int getIconFromDamage(int i) {
		return Blocks.arch.getBlockTextureFromSideAndMetadata(2, i);
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

}
