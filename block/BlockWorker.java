package riseautomatons.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riseautomatons.Ids;

public class BlockWorker extends BlockContainer {

	public static int renderId;

	protected BlockWorker() {
		super(Material.piston);
		float f = 0.0625F;
		setBlockBounds(f * 2f, 0.0F, f * 2f, 14f * f, 14f * f, 14f * f);
	}

	@Override
    public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityBeacon();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister
				.registerIcon("stone");
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		int ii = BeaconManager.addBeacon(world, i, j, k);
		TileEntityBeacon beacon = (TileEntityBeacon) world
				.getTileEntity(i, j, k);
		beacon.numeral = ii;
		beacon.mode = 9; // worker
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
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this);
			par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4,
                    this);
		}

		super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, Block par5) {
		canSnowStay(par1World, par2, par3, par4);
	}

	private boolean canSnowStay(World par1World, int par2, int par3, int par4) {

        if (!canPlaceBlockAt(par1World, par2, par3, par4))
        {
            dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(Ids.itemWorker, 1, 0));
            par1World.setBlockToAir(par2, par3, par4);
            return false;
        }
        else
        {
            return true;
        }	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Ids.itemWorker;
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return par1World.getBlock(par2, par3 - 1, par4).isOpaqueCube();
	}

	@Override
	public void setBlockBoundsForItemRender() {
		float f = 0.5F;
		float f1 = 0.125F;
		float f2 = 0.5F;
		setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
	}

	@Override
	public int getMobilityFlag() {
		return 2;
	}

}
