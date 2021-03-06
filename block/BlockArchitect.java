package riseautomatons.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import riseautomatons.Ids;

public class BlockArchitect extends BlockContainer {
	protected BlockArchitect() {
		super(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int par2) {
		return new TileEntityArchitect();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("piston_bottom");
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3,
			int par4, Random par5Random) {
		int i = par1World.getBlockMetadata(par2, par3, par4);
		float f = (float) par2 + 0.5F;
		float f1 = (float) par3 + 0.0F + (par5Random.nextFloat() * 6F) / 16F;
		float f2 = (float) par4 + 0.5F;
		float f3 = 0.52F;
		float f4 = par5Random.nextFloat() * 0.6F - 0.3F;

		if (i == 4) {
			par1World.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D,
					0.0D);
			par1World.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D,
					0.0D);
		} else if (i == 5) {
			par1World.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D,
					0.0D);
			par1World.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D,
					0.0D);
		} else if (i == 2) {
			par1World.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D,
					0.0D);
			par1World.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D,
					0.0D);
		} else if (i == 3) {
			par1World.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D,
					0.0D);
			par1World.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D,
					0.0D);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block l) {
		if (l == Ids.blockChalk)
			world.setBlock(i, j, k, Ids.blockArchBend, 0, 3);
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (par1World.isRemote) {
			return true;
		}

		return true;
	}

}
