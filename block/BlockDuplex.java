package riseautomatons.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDuplex extends Block {

	public BlockDuplex() {
		super(Material.glass);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess,
			int i, int j, int k, int l) {

	    Block i1 = iblockaccess.getBlock(i, j, k);
	    if (i1 == this)
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
	    if (world.getBlock(i + 1, j, k) == this)
	    {
	      world.setBlockToAir(i + 1, j, k);
	    }
	    if (world.getBlock(i - 1, j, k) == this)
	    {
	      world.setBlockToAir(i - 1, j, k);
	    }
	    if (world.getBlock(i, j, k - 1) == this)
	    {
	      world.setBlockToAir(i, j, k - 1);
	    }
	    if (world.getBlock(i, j, k + 1) == this)
	    {
	      world.setBlockToAir(i, j, k + 1);
	    }
	    if (world.getBlock(i, j - 1, k) == this)
	    {
	      world.setBlockToAir(i, j - 1, k);
	    }
	    if (world.getBlock(i, j + 1, k) == this)
	    {
	      world.setBlockToAir(i, j + 1, k);
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
