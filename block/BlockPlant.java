package riseautomatons.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockPlant extends Block {

	protected BlockPlant() {
		super(Material.ground);
		setBlockBounds(0.25F, 0.0F, 0.25F, .75F, 1, .75F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {

		double sx = 0;
		double sx2 = 1;
		return AxisAlignedBB.getBoundingBox((double) (par2 + sx),
				(double) par3, (double) (par4 + sx), (double) (par2 + sx2),
				(double) (par3 + 0.5), (double) (par4 + sx2));
	}

	@Override
	public boolean isOpaqueCube() {
		 return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess,
			int i, int j, int k) {

		boolean bound[] = new boolean[6];
		// int id=iblockaccess.getBlock(i, j-1, k);

		bound[0] = iblockaccess.getBlock(i - 1, j, k) == this;
		bound[1] = iblockaccess.getBlock(i, j, k - 1) == this;
		bound[2] = iblockaccess.getBlock(i + 1, j, k) == this;
		bound[3] = iblockaccess.getBlock(i, j, k + 1) == this;
		bound[4] = iblockaccess.getBlock(i, j + 1, k) == this;
		// boolean any=bound[0]||bound[1]||bound[2]||bound[3];

		setBlockBounds(bound[0] ? 0 : 0.125F, 0.0F, bound[1] ? 0 : 0.125F,
				bound[2] ? 1 : 0.875F, bound[4] ? 1 : 0.75f, bound[3] ? 1
						: 0.875F);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.125F, 0.125F, 0.125F, .875F, 0.875f, .875F);
	}

}
