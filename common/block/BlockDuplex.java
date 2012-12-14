package riseautomatons.common.block;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockDuplex extends Block {

	public BlockDuplex(int par1) {
		super(par1, Material.glass);
		this.blockIndexInTexture = 34;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess,
			int i, int j, int k, int l) {

	    int i1 = iblockaccess.getBlockId(i, j, k);
	    if (i1 == this.blockID)
	    {
	      return false;
	    }

	    return super.shouldSideBeRendered(iblockaccess, i, j, k, l);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j,
			int k, int par5) {

	    world.spawnParticle("reddust", i + 0.5F, j + 0.5F, k + 0.5F, 0.0D, 0.2000000029802322D, 0.0D);
	    if (world.getBlockId(i + 1, j, k) == this.blockID)
	    {
	      world.setBlockWithNotify(i + 1, j, k, 0);
	    }
	    if (world.getBlockId(i - 1, j, k) == this.blockID)
	    {
	      world.setBlockWithNotify(i - 1, j, k, 0);
	    }
	    if (world.getBlockId(i, j, k - 1) == this.blockID)
	    {
	      world.setBlockWithNotify(i, j, k - 1, 0);
	    }
	    if (world.getBlockId(i, j, k + 1) == this.blockID)
	    {
	      world.setBlockWithNotify(i, j, k + 1, 0);
	    }
	    if (world.getBlockId(i, j - 1, k) == this.blockID)
	    {
	      world.setBlockWithNotify(i, j - 1, k, 0);
	    }
	    if (world.getBlockId(i, j + 1, k) == this.blockID)
	    {
	      world.setBlockWithNotify(i, j + 1, k, 0);
	    }
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 9;
	}

	@Override
	public int getRenderBlockPass() {
		return 1;
	}

}
