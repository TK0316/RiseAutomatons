package riseautomatons.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riseautomatons.Ids;

public class BlockGrower extends Block {

	private Icon icons[];

	static int iconIndexSide = 29;
	static int iconIndexTop = 31;

	public static int derk[] = {
		15599658,
		12913722,
		10227786,
		7541850,
		4855914,
		2169978 };

	protected BlockGrower(int i) {
		super(i, Material.plants);
		// blockIndexInTexture = j;
		float f = 0.375F;
		// setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		// float f = 0.1875F;
		float f2 = 1F - f;
		this.setBlockBounds(f, 0, f, f2, 1F, f2);
		setTickRandomly(true);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public Icon getBlockTextureFromSideAndMetadata(int i, int par2) {

		if (i <= 1) {
			return icons[1];
		} else {
			return icons[0];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[2];
		icons[0] = par1IconRegister.registerIcon("riseautomatons:grower1");
		icons[1] = par1IconRegister.registerIcon("riseautomatons:grower2");
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World,
			int i, int j, int k) {

		float f = 0.375f;
		return AxisAlignedBB.getBoundingBox((float) i + f, j, (float) k
				+ f, (float) (i + 1) - f, (float) j + 1, (float) (k + 1) - f);

	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int i, int j, int k) {

		float f = 0.375F;
		return AxisAlignedBB.getBoundingBox((float) i + f, j, (float) k
				+ f, (float) (i + 1) - f, (float) j + 1, (float) (k + 1) - f);

	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void updateTick(World world, int i, int j, int k,
			Random par5Random) {

		int i1 = world.getBlockMetadata(i, j, k);

		if (world.isAirBlock(i, j + 1, k)) {
			int l;

			for (l = 1; world.getBlockId(i, j - l, k) == blockID; l++) {
			}

			if (l < 6) {
				if (i1 >= 3) {
					// world.setBlockMetadataWithNotify(i, j, k, 5);
					world.setBlock(i, j + 1, k, blockID, 3, 3);
				} else {
					world.setBlock(i, j, k, blockID,
							i1 + 1, 3);
				}
			}
			else {
				world.setBlock(i, j + 1, k, Blocks.crink.blockID, 0, 3);
			}
		} else if (i1 < 5) {
			world.setBlock(i, j, k, blockID, i1 + 1, 3);
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j,
			int k, int l) {
		if (world.getBlockId(i, j, k) == 0
				&& world.getBlockId(i, j - 1, k) == blockID) {
			world.setBlockMetadataWithNotify(i, j - 1, k, 0, 3);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j,
			int k, int l) {
		checkBlockCoordValid(world, i, j, k);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {

		int bbb = world.getBlockId(i, j - 1, k);
		int hhh = world.getBlockId(i, j, k);
		// hhh=0 hhh=mine
		// bbb=frass bbb=mine
		return (hhh == 0 || hhh == blockID)
				&& (bbb == Ids.blockArch || bbb == Ids.blockArchBend || bbb == Ids.blockFrass || bbb == blockID);

	}

	@Override
	public int colorMultiplier(IBlockAccess iblockaccess, int i,
			int j, int k) {
		int g = iblockaccess.getBlockMetadata(i, j, k);
		return derk[Math.min(g, 5)];
	}

	@Override
	public boolean canBlockStay(World world, int i, int j, int k) {
		return canPlaceBlockAt(world, i, j, k);
	}

	protected final void checkBlockCoordValid(World world, int i, int j, int k) {
		if (!canBlockStay(world, i, j, k)) {
			dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
			world.setBlock(i, j, k, 0, 0, 3);
		}
	}

}
