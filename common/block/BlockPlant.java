package riseautomatons.common.block;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockPlant extends Block {

	protected BlockPlant(int i) {
		super(i, 18, Material.ground);
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
		// int id=iblockaccess.getBlockId(i, j-1, k);

		bound[0] = iblockaccess.getBlockId(i - 1, j, k) == blockID;
		bound[1] = iblockaccess.getBlockId(i, j, k - 1) == blockID;
		bound[2] = iblockaccess.getBlockId(i + 1, j, k) == blockID;
		bound[3] = iblockaccess.getBlockId(i, j, k + 1) == blockID;
		bound[4] = iblockaccess.getBlockId(i, j + 1, k) == blockID;
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
