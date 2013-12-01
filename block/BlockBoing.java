package riseautomatons.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockBoing extends Block {

	protected BlockBoing(int i) {
		super(i, Material.grass);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("riseautomatons:boing");
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
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess,
			int i, int j, int k) {

		// float f = 0.125F;
		int l = iblockaccess.getBlockMetadata(i, j, k);
		float F = 0.0625F;
		float F2 = 1f - F;

		if (l == 1) {
			setBlockBounds(0.0F, F2, 0.0F, 1.0F, 1.0f, 1.0F);
		} else if (l == 0) {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, F, 1.0F);
		} else if (l == 2) {
			setBlockBounds(0.0F, 0.0F, F2, 1.0F, 1.0f, 1.0f);
		} else if (l == 3) {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0f, F);
		} else if (l == 4) {
			setBlockBounds(F2, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
		} else {
			setBlockBounds(0.0F, 0.0F, 0.0F, F, 1.0f, 1.0F);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j,
			int k, Entity entity) {

		int l = world.getBlockMetadata(i, j, k);
		entity.fallDistance = -10;
		//entity.motionX = -0.75F;
		entity.motionY = 0.75F;
		//entity.motionZ = -0.75F;
/*
		if (l == 0) {
			entity.motionY = -0.75F;
			// entity.motionZ=0;
			// entity.motionX=0;
			entity.motionZ *= 1.2F;
			entity.motionX *= 1.2F;
		} else if (l == 1) {
			entity.motionY = 0.75F;
			// entity.motionZ=0;
			// entity.motionX=0;
			entity.motionZ *= 1.2F;
			entity.motionX *= 1.2F;
		} else if (l == 2) {
			entity.motionZ = -1.25F;
			// entity.motionY=0;
			// entity.motionX=0;
			entity.motionY *= 1.2F;
			entity.motionX *= 1.2F;
		} else if (l == 3) {
			entity.motionZ = 1.25F;
			// entity.motionY=0;
			// entity.motionX=0;
			entity.motionY *= 1.2F;
			entity.motionX *= 1.2F;
		} else if (l == 4) {
			entity.motionX = -1.25F;
			// entity.motionZ=0;
			// entity.motionY=0;
			entity.motionY *= 1.2F;
			entity.motionZ *= 1.2F;
		} else {
			entity.motionX = 1.25F;
			// entity.motionZ=0;
			// entity.motionY=0;
			entity.motionY *= 1.2F;
			entity.motionZ *= 1.2F;
		}*/
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
	public void onBlockPreDestroy(World world, int i, int j,
			int k, int l) {
		world.setBlockMetadataWithNotify(i, j, k, l, 3);
	}

}
