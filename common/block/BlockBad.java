package riseautomatons.common.block;

import java.util.Random;

import riseautomatons.common.entity.EntityWatcher;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockBad extends Block {

	public int D[] = {27, 28};

	protected BlockBad(int i) {
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
        return AxisAlignedBB.getBoundingBox((float)i + f, j, (float)k + f, (float)(i + 1) - f, (float)(j + 0.5F), (float)(k + 1) - f);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int i, int j, int k) {
        float f = 0.25F;
        return AxisAlignedBB.getBoundingBox((float)i + f, j, (float)k + f, (float)(i + 1) - f, (float)(j + 0.5F), (float)(k + 1) - f);
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
	public void harvestBlock(World world, EntityPlayer entityplayer,
			int i, int j, int k, int l) {
		EntityWatcher et = new EntityWatcher(world, (float) i + 0.5F,
				(float) j + 0.5F, (float) k + 0.5F);
		world.spawnEntityInWorld(et);
		et.setTarget(entityplayer);
	}

	@Override
	public boolean canBlockStay(World world, int i, int j, int k) {
		if (world.getBlockMaterial(i, j - 1, k).isSolid()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return 0;
	}

}
