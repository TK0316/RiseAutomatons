package riseautomatons.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import riseautomatons.RiseAutomatons;
import riseautomatons.entity.EntityWatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBad extends Block {

	private IIcon icons[];

	public int D[] = {27, 28};

	protected BlockBad() {
		super(Material.glass);
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

	@Override
	public IIcon getIcon(int i, int par2) {
		if (i <= 1) {
			return icons[1];
		} else {
			return icons[0];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[2];
		icons[0] = par1IconRegister.registerIcon("riseautomatons:crystal1");
		icons[1] = par1IconRegister.registerIcon("riseautomatons:crystal2");
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World,
			int i, int j, int k) {
        float f = 0.25F;
        return AxisAlignedBB.getBoundingBox((float)i + f, j, (float)k + f, (float)(i + 1) - f, (float)(j + 0.5F), (float)(k + 1) - f);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int i, int j, int k) {
        float f = 0.25F;
        return AxisAlignedBB.getBoundingBox((float)i + f, j, (float)k + f, (float)(i + 1) - f, (float)(j + 0.5F), (float)(k + 1) - f);
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
	public void harvestBlock(World world, EntityPlayer entityplayer,
			int i, int j, int k, int l) {
		if(RiseAutomatons.disableWatcherCrystal) {
			EntityBlaze e = new EntityBlaze(world);
			e.setPosition((float) i + 0.5F, (float) j + 0.5F, (float) k + 0.5F);
			world.spawnEntityInWorld(e);
			e.setTarget(entityplayer);
			ItemStack itemStack = new ItemStack(Blocks.crystal);
			world.spawnEntityInWorld(new EntityItem(world, i, j, k, itemStack));
		}
		else {
			EntityWatcher et = new EntityWatcher(world, (float) i + 0.5F, (float) j + 0.5F, (float) k + 0.5F);
			world.spawnEntityInWorld(et);
			et.setTarget(entityplayer);
		}
	}

	@Override
	public boolean canBlockStay(World world, int i, int j, int k) {
		if (world.getBlock(i, j - 1, k).getMaterial().isSolid()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return null;
	}

}
