package riseautomatons.common.block;

import java.util.Random;

import riseautomatons.common.Ids;
import riseautomatons.common.item.EnumCraftSetType;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockSlab extends Block {

	public BlockSlab(int par1) {
		super(par1, 6, Material.rock);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess,
			int par2, int par3, int par4, int par5) {
		return super.shouldSideBeRendered(par1iBlockAccess, par2, par3, par4,
				par5);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return 1;
	}

	@Override
	public int getBlockTextureFromSide(int par1) {
		return getBlockTextureFromSideAndMetadata(par1, 0);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3,
				par4);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Ids.craftSet;
	}

	@Override
	public int damageDropped(int par1) {
		return EnumCraftSetType.SMALLPLATE.ordinal();
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess,
			int par2, int par3, int par4) {
		int into = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, (16 - into) / 16f, 1.0F);
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

}
