package riseautomatons.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riseautomatons.Ids;

public class BlockGearBox extends Block {
	float f = 0.375F;

	int it = 0;
	int jt = 0;
	int kt = 0;

	protected BlockGearBox(int i, int j, Material mat) {
		super(i, j, mat);
		// setBlockBounds(f,f,f,1-f,1-f,1-f);
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int par5) {
		if (world.isRemote) {
			return;
		}

		computeState(world, i, j, k);
	}

	@Override
	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k) {
		return iblockaccess.getBlockMetadata(i, j, k) == 0 ? 0xaa5555
				: 0xffffff; // ColorizerGrass.getGrassColor(d, d1);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLiving par5EntityLiving) {
		computeState(par1World, par2, par3, par4);
	}

	private void computeState(World world, int i, int j, int k) {
		int m = world.getBlockMetadata(i, j, k);
		it = i;
		jt = j;
		kt = k;

		if (allow(world, i, j, k + 1, 2) || allow(world, i, j, k - 1, 1)
				|| allow(world, i + 1, j, k, 5) || allow(world, i - 1, j, k, 4)
				|| allow(world, i, j + 1, k, 8) || allow(world, i, j - 1, k, 7)) {
		} else {
			if (m != 0) {
				world.setBlockMetadataWithNotify(i, j, k, 0);
			}
		}
	}

	private boolean allow(World world, int i, int j, int k, int g) {
		boolean b = (world.getBlockId(i, j, k) == Ids.blockTurn && world
				.getBlockMetadata(i, j, k) == g);

		if (b) {
			if (world.getBlockMetadata(it, jt, kt) != g) {
				world.setBlockMetadataWithNotify(it, jt, kt, g);
			}
		}

		return b;
	}

}
