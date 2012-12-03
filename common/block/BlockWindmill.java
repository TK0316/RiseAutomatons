package riseautomatons.common.block;

import java.util.Random;

import riseautomatons.common.block.TileEntityWindmill;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockWindmill extends BlockContainer {

	float f = 0.75F; // 375

	protected BlockWindmill(int i, int j, Material mat) {
		super(i, j, mat);
		setTickRandomly(true);

	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityWindmill();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random par5Random) {
		computeState(world, i, j, k);
	}

	public void computeState(World world, int i, int j, int k) {
		boolean b1 = world.isAirBlock(i, j, k - 1)
				&& world.isAirBlock(i - 1, j, k - 1)
				&& world.isAirBlock(i + 1, j, k - 1)
				&& world.isAirBlock(i, j + 1, k - 1)
				&& world.isAirBlock(i, j - 1, k - 1);
		boolean b2 = world.isAirBlock(i + 1, j, k)
				&& world.isAirBlock(i + 1, j, k + 1)
				&& world.isAirBlock(i + 1, j, k - 1)
				&& world.isAirBlock(i + 1, j + 1, k)
				&& world.isAirBlock(i + 1, j - 1, k);
		boolean b3 = world.isAirBlock(i, j, k + 1)
				&& world.isAirBlock(i - 1, j, k + 1)
				&& world.isAirBlock(i + 1, j, k + 1)
				&& world.isAirBlock(i, j + 1, k + 1)
				&& world.isAirBlock(i, j - 1, k + 1);
		boolean b4 = world.isAirBlock(i - 1, j, k)
				&& world.isAirBlock(i - 1, j, k + 1)
				&& world.isAirBlock(i - 1, j, k - 1)
				&& world.isAirBlock(i - 1, j + 1, k)
				&& world.isAirBlock(i - 1, j - 1, k);
		int hh = world.getBlockMetadata(i, j, k);
		if (hh % 2 == 0) {
			if (hh == 0) {
				if (b1) {
					world.setBlockMetadataWithNotify(i, j, k, 1);
				}
			} else if (hh == 2) {
				if (b2) {
					world.setBlockMetadataWithNotify(i, j, k, 3);
				}
			} else if (hh == 4) {
				if (b3) {
					world.setBlockMetadataWithNotify(i, j, k, 5);
				}
			} else if (hh == 6) {
				if (b4) {
					world.setBlockMetadataWithNotify(i, j, k, 7);
				}
			}
			// world.setBlockMetadataWithNotify(i, j, k,hh+1);
		} else {
			if (hh == 1) {
				if (!b1) {
					world.setBlockMetadataWithNotify(i, j, k, 0);
				}
			} else if (hh == 3) {
				if (!b2) {
					world.setBlockMetadataWithNotify(i, j, k, 2);
				}
			} else if (hh == 5) {
				if (!b3) {
					world.setBlockMetadataWithNotify(i, j, k, 4);
				}
			} else if (hh == 7) {
				if (!b4) {
					world.setBlockMetadataWithNotify(i, j, k, 6);
				}
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		if (world.isRemote) {
			return;
		}

		computeState(world, i, j, k);
	}

	@Override
	public int tickRate() {
		return 0;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i,
			int j, int k) {
		// float f = 0.125F;
		int m = iblockaccess.getBlockMetadata(i, j, k);

		switch (m) {

		case 0:
			setBlockBounds(0, 0, 0, 1, 1, f);
			break;
		case 1:
			setBlockBounds(0, 0, 0, 1, 1, f);
			break;

		case 2:
			setBlockBounds(1 - f, 0, 0, 1, 1, 1);
			break;
		case 3:
			setBlockBounds(1 - f, 0, 0, 1, 1, 1);
			break;

		case 4:
			setBlockBounds(0, 0, 1 - f, 1, 1, 1);
			break;
		case 5:
			setBlockBounds(0, 0, 1 - f, 1, 1, 1);
			break;

		case 6:
			setBlockBounds(0, 0, 0, f, 1, 1);
			break;
		case 7:
			setBlockBounds(0, 0, 0, f, 1, 1);
			break;

		}
	}

	@Override
	public void setBlockBoundsForItemRender() {
		super.setBlockBoundsForItemRender();
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLiving par5EntityLiving) {
		int i = ((MathHelper
				.floor_double((double) ((par5EntityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 3) + 2) % 4;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, i * 2);
	}

}
