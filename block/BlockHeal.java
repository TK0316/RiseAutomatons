package riseautomatons.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHeal extends Block {

	static int D = 17;
	int[] ferp = { 1835782, 2819338, 4197138, 5378329, 6362656, 7677745,
			9386051, 11028307, 11753058, 13200761, 14586011, 14722220,
			15774908, 16428224, 16763351 };

	protected BlockHeal(int i) {
		super(i, Material.grass);
		setTickRandomly(true);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int i,
			int j, int k, int l) {

	    if (l <= 1)
	    {
	      return blockIcon;
	    }

	    return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("riseautomatons:thing");
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void updateTick(World world, int i, int j, int k,
			Random par5Random) {

		int l = world.getBlockMetadata(i, j, k);
		if (l < 4) {
			world.setBlock(i, j, k, this.blockID, l + 1, 3);
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1iBlockAccess,
			int par2, int par3, int par4) {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	}

	@Override
	public int colorMultiplier(IBlockAccess iblockaccess, int i,
			int j, int k) {
		return this.ferp[ferp.length - 1 - iblockaccess.getBlockMetadata(i, j, k)];
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j,
			int k, Entity entity) {
		healEntity(world, i, j, k, entity);
	}

	@Override
	public void onEntityWalking(World world, int i, int j, int k, Entity entity) {
		healEntity(world, i, j, k, entity);
	}

	private void healEntity(World world, int i, int j, int k, Entity entity) {

		if ((entity instanceof EntityLivingBase)) {
			int l = world.getBlockMetadata(i, j, k);
			if (l > 0) {
				world.spawnParticle("heart", i + 0.5F, j + 0.5F, k + 0.5F,
						0.0D, 0.4000000059604645D, 0.0D);
				((EntityLivingBase) entity).heal(l * 2);
				world.setBlock(i, j, k, this.blockID, 0, 3);
			}
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
	public int getRenderColor(int par1) {
		return this.ferp[11];
	}

}
