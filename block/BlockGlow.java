package riseautomatons.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;


public class BlockGlow extends Block {

	public int D[] = {27, 28};

	protected BlockGlow(int i) {
		super(i, Material.glass);
		float f = 0.1875F;
		float f2 = 1F - f;
		this.setBlockBounds(f, 0, f, f2, 1F - 0.5F, f2);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 13;
	}

	public Block loadSprites(int i, int j) {
		this.D[0] = i;
		this.D[1] = j;
		return this;
	}

	@Override
	public int getBlockTextureFromSide(int i) {
		if (i <= 1) {
			return D[1];
		} else {
			return D[0];
		}
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World,
			int i, int j, int k) {

		float f = 0.25F;
		return AxisAlignedBB.getBoundingBox((float) i + f, j, (float) k + f,
				(float) (i + 1) - f, (float) (j + 0.5F), (float) (k + 1) - f);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int i, int j, int k) {

		float f = 0.25F;
		return AxisAlignedBB.getBoundingBox((float) i + f, j, (float) k + f,
				(float) (i + 1) - f, (float) (j + 0.5F), (float) (k + 1) - f);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		if (!super.canPlaceBlockAt(world, i, j, k)) {
			return false;
		} else {
			return canBlockStay(world, i, j, k);
		}
	}

	@Override
	public boolean canBlockStay(World world, int i, int j, int k) {
		if (world.getBlockMaterial(i, j - 1, k).isSolid()) {
			return true;
		} else {
			return false;
		}
	}

}
