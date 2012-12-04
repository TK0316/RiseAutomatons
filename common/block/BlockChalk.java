package riseautomatons.common.block;

import java.util.Random;

import riseautomatons.common.Ids;
import riseautomatons.common.Universal;
import riseautomatons.common.spell.ChalkLogic;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockChalk extends Block {

	public static int rendererId;

	public BlockChalk(int i, int j) {
		super(i, j, Material.circuits);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return rendererId;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return blockIndexInTexture;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k,
			EntityPlayer entityplayer, int par6, float par7, float par8,
			float par9) {

		if (world.isRemote) {
			return true;
		}

		if (entityplayer.getCurrentEquippedItem() != null) {
			if (entityplayer.getCurrentEquippedItem().itemID == Ids.itemChalk) {
				return false;
			}
		}

		ChalkLogic.translate(world, i, j, k, entityplayer);
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l) {

		super.onBlockDestroyedByPlayer(world, i, j, k, l);

		if (world.isRemote) {
			return;
		}

		int meta = world.getBlockMetadata(i, j, k);

		if (meta > 0) {
			/*
			 * if (meta > 1) { EntityItem entityitem = new EntityItem(world, i +
			 * 0.5f, j + 0.5f, k + 0.5f, new ItemStack(zei_Ids.chalk2, meta - 1,
			 * 0)); entityitem.delayBeforeCanPickup = 10;
			 * world.spawnEntityInWorld(entityitem); }
			 */

			notifyWireNeighborsOfNeighborChange(world, i - 1, j, k);
			notifyWireNeighborsOfNeighborChange(world, i + 1, j, k);
			notifyWireNeighborsOfNeighborChange(world, i, j, k - 1);
			notifyWireNeighborsOfNeighborChange(world, i, j, k + 1);
		} else {
			// TODO Universal.fwoo(world, i, j, k);
			// zei_Universal.makeParticle(new
			// EntityLargeExplodeFX(zei_Universal.mc.renderEngine, world, i +
			// 0.5, j + 1, k + 0.5, 0, 0, 0));
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {

		if (world.isRemote) {
			return;
		}

		int i1 = world.getBlockMetadata(i, j, k);
		boolean flag = canPlaceBlockAt(world, i, j, k);

		if (!flag) {
			world.setBlockWithNotify(i, j, k, 0);
		} else {
			boolean flag1 = world.isBlockIndirectlyGettingPowered(i, j, k);

			if ((flag1) && l != blockID) // || l > 0 &&
											// Block.blocksList[l].canProvidePower()
											// || l == 0
			{
				// onPoweredBlockChange(world, i, j, k, flag1);
				ChalkLogic.translate(world, i, j, k, world.getClosestPlayer(i, j, k, -1));
			}
		}

		super.onNeighborBlockChange(world, i, j, k, l);
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {

		super.onBlockAdded(world, i, j, k);

		if (world.isRemote) {
			return;
		}

		notifyWireNeighborsOfNeighborChange(world, i - 1, j, k);
		notifyWireNeighborsOfNeighborChange(world, i + 1, j, k);
		notifyWireNeighborsOfNeighborChange(world, i, j, k - 1);
		notifyWireNeighborsOfNeighborChange(world, i, j, k + 1);

	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Ids.itemChalk;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		return world.isBlockNormalCube(i, j - 1, k);
	}

	private void notifyWireNeighborsOfNeighborChange(World world, int i, int j,
			int k) {
		if (world.getBlockId(i, j, k) != blockID) {
			return;
		} else {
			world.notifyBlocksOfNeighborChange(i, j, k, blockID);
			return;
		}
	}
}
