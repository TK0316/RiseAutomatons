package riseautomatons.common.block;

import java.util.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import riseautomatons.common.CommonProxy;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;

public class BlockLumo extends Block {
	protected BlockLumo(int i) {
		super(i, Material.rock);
	}
	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		if(par2 == 1) {
			return Blocks.skyIconIndex;
		}
		return Blocks.walkIconIndex;
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	public String getTextureFile() {
		return Blocks.BLOCK_PNG;
	}
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int ix = 0; ix < 2; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}

}
