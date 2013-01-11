package riseautomatons.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riseautomatons.Ids;

public class BlockSentry extends BlockContainer {

	public static int renderId;

	protected BlockSentry(int par1) {
		super(par1, Material.piston);
		float f = 0.0625F;
		setBlockBounds(f * 4f, 0.0F, f * 4f, 12f * f, 13f * f, 12f * f);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityBeacon();
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		int ii = BeaconManager.addBeacon(world, i, j, k);
		TileEntityBeacon beacon = (TileEntityBeacon) world
				.getBlockTileEntity(i, j, k);
		beacon.numeral = ii;
		beacon.mode = 8; // sentry
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean getBlocksMovement(IBlockAccess par1iBlockAccess, int par2,
			int par3, int par4) {
		return true;
	}

	@Override
	public int getRenderType() {
		return renderId;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3,
			int par4, int par5) {
		int i = par1World.getBlockMetadata(par2, par3, par4);

		if (i > 0) {
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4, blockID);
			par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4,
					blockID);
		}

		super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Ids.itemSentry;
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return par1World.isBlockOpaqueCube(par2, par3 - 1, par4);
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, int par5) {
		canSnowStay(par1World, par2, par3, par4);
	}

	private boolean canSnowStay(World par1World, int par2, int par3, int par4) {
		if (!canPlaceBlockAt(par1World, par2, par3, par4)) {
			dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(
					Ids.itemSentry, 1, 0));
			par1World.setBlockWithNotify(par2, par3, par4, 0);
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void setBlockBoundsForItemRender() {
		float f = 0.5F;
		float f1 = 0.125F;
		float f2 = 0.5F;
		setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1,
				0.5F + f2);
	}

	@Override
	public int getMobilityFlag() {
		return 2;
	}

}