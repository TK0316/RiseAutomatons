package riseautomatons.block;

import riseautomatons.Ids;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;


public class BlockGlow extends Block {

	private Icon icons[];

	public int D[] = {27, 28};

	protected BlockGlow(int i) {
		super(i, Material.glass);
		float f = 0.1875F;
		float f2 = 1F - f;
		this.setBlockBounds(f, 0, f, f2, 1F - 0.5F, f2);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 13;
	}

	public Block loadSprites(int i, int j) {
		this.D[0] = i;
		this.D[1] = j;
		return this;
	}

	@Override
	public Icon getIcon(int i, int par2) {
		if(this.blockID == Ids.blockGlowy) {
			if (i <= 1) {
				return icons[3];
			} else {
				return icons[2];
			}
		}
		else {
			if (i <= 1) {
				return icons[1];
			} else {
				return icons[0];
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[4];
		icons[0] = par1IconRegister.registerIcon("riseautomatons:crystal1");
		icons[1] = par1IconRegister.registerIcon("riseautomatons:crystal2");
		icons[2] = par1IconRegister.registerIcon("riseautomatons:glowy1");
		icons[3] = par1IconRegister.registerIcon("riseautomatons:glowy2");
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World,
			int i, int j, int k) {

		float f = 0.25F;
		return AxisAlignedBB.getBoundingBox((float) i + f, j, (float) k + f,
				(float) (i + 1) - f, (float) (j + 0.5F), (float) (k + 1) - f);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int i, int j, int k) {

		float f = 0.25F;
		return AxisAlignedBB.getBoundingBox((float) i + f, j, (float) k + f,
				(float) (i + 1) - f, (float) (j + 0.5F), (float) (k + 1) - f);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		if (!super.canPlaceBlockAt(world, i, j, k)) {
			return false;
		} else {
			return canBlockStay(world, i, j, k);
		}
	}

	@Override
	public boolean canBlockStay(World world, int i, int j, int k) {
		if (world.getBlockMaterial(i, j - 1, k).isSolid()) {
			return true;
		} else {
			return false;
		}
	}

}
