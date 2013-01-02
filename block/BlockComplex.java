package riseautomatons.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockComplex extends Block {
	protected BlockComplex(int i) {
		super(i, Material.rock);
		loadSprites();
	}

	public BlockComplex(int par1, int par2, Material par3Material) {
		super(par1, par2, Material.rock);
		loadSprites();
	}

	public BlockComplex(int par1, Material par2Material) {
		super(par1, Material.rock);
		loadSprites();
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return D[j]; // D[0]
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	public String getTextureFile() {
		return Blocks.BLOCK_PNG;
	}

	static int D[];

	static void loadSprites() {
		D = new int[16];
		D[0] = 11;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/tech.png");
		D[1] = 15;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/tree.png");
		D[2] = 16;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/wallo.png");
		D[3] = 6;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/bwop.png");
		D[4] = 12;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/tech2.png");
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int ix = 0; ix < 4; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}

}
