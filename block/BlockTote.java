package riseautomatons.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import riseautomatons.Ids;
import riseautomatons.RiseAutomatons;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTote extends BlockContainer {

	public static int renderId;

	protected BlockTote(int i) {
		super(i, Material.piston);
		float F = 0.25f;
		setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.5625f, 0.875F);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityLatch();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister
				.registerIcon("stone");
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		BeaconManager.addBeacon(world, i, j, k);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
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
	public void onBlockDestroyedByPlayer(World world, int par2, int par3,
			int par4, int par5) {

		TileEntityLatch latch = (TileEntityLatch) world
				.getBlockTileEntity(par2, par3, par4);

		if (latch != null) {
			for (int i = 0; i < latch.getSizeInventory(); i++) {
				ItemStack itemstack = latch.getStackInSlot(i);

				if (itemstack != null) {
					float f = world.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int j = world.rand.nextInt(21) + 10;

						if (j > itemstack.stackSize) {
							j = itemstack.stackSize;
						}

						itemstack.stackSize -= j;
						EntityItem entityitem = new EntityItem(world,
								(float) par2 + f, (float) par3 + f1,
								(float) par4 + f2, new ItemStack(
										itemstack.itemID, j,
										itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem()
									.setTagCompound((NBTTagCompound) itemstack
											.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (float) world.rand.nextGaussian() * f3;
						entityitem.motionY = (float) world.rand.nextGaussian() * f3
								+ 0.2F;
						entityitem.motionZ = (float) world.rand.nextGaussian() * f3;
						world.spawnEntityInWorld(entityitem);
					}
				}
			}
		}

		super.onBlockDestroyedByPlayer(world, par2, par3, par4, par5);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Ids.itemTote;
	}

	@Override
	public boolean onBlockActivated(World world, int par2, int par3,
			int par4, EntityPlayer ep, int par6, float par7,
			float par8, float par9) {

		if (world.isRemote) {
			return true;
		}
		ItemStack it = ep.inventory.getCurrentItem();
		if (it != null && it.itemID == Ids.soulCore) {
			return false;
		}

		TileEntityLatch latch = (TileEntityLatch) world
				.getBlockTileEntity(par2, par3, par4);

		if (latch != null) {
			ep.openGui(RiseAutomatons.instance, Ids.guiTote, world, par2, par3, par4);
		}

		return true;
	}

}
