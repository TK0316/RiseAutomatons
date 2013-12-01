package riseautomatons.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.Universal;
import riseautomatons.spell.ChalkLogic;

public class BlockChalk extends Block {

	public static int rendererId;

	public BlockChalk(int i, int j) {
		super(i, Material.circuits);
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
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("redstone_dust_cross");
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
			//return true;
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

	@SideOnly(Side.CLIENT)
	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l) {

		super.onBlockDestroyedByPlayer(world, i, j, k, l);
		Universal.fwoo(world, i, j, k);
	}

	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int i, int j, int k) {
		int meta = world.getBlockMetadata(i, j, k);

		if (meta > 0) {
			notifyWireNeighborsOfNeighborChange(world, i - 1, j, k);
			notifyWireNeighborsOfNeighborChange(world, i + 1, j, k);
			notifyWireNeighborsOfNeighborChange(world, i, j, k - 1);
			notifyWireNeighborsOfNeighborChange(world, i, j, k + 1);
		}
		return super.removeBlockByPlayer(world, player, i, j, k);
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {

		if (world.isRemote) {
			//return;
		}

		int i1 = world.getBlockMetadata(i, j, k);
		boolean flag = canPlaceBlockAt(world, i, j, k);

		if (!flag) {
			world.setBlock(i, j, k, 0, 0, 3);
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
			//return;
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
		int blockID = world.getBlockId(i, j, k);
		int metadata = world.getBlockMetadata(i, j, k);
		if(blockID == Blocks.chalk.blockID && metadata < 7) {
			return true;
		}
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
