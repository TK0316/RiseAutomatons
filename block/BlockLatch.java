package riseautomatons.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import riseautomatons.Ids;
import riseautomatons.RiseAutomatons;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLatch extends BlockContainer {

	protected BlockLatch(int i) {
		super(i, Material.iron);
		float F = 0.25f;
		setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.875f, 0.875F);
	}
	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityLatch();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("iron_block");
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4,
			Random par5Random) {
		if (!par1World.isRemote
				&& (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) || par1World
						.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4))) {
			dispenseItem(par1World, par2, par3, par4, par5Random);
		}
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
	public void onNeighborBlockChange(World world, int par2, int par3,
			int par4, int par5) {

		if (par5 > 0 && Block.blocksList[par5].canProvidePower()) {
			boolean flag = world.isBlockIndirectlyGettingPowered(par2, par3,
					par4)
					|| world.isBlockIndirectlyGettingPowered(par2, par3 + 1,
							par4);

			if (flag) {
				world.scheduleBlockUpdate(par2, par3, par4, blockID, tickRate(world));

			}
		}
	}

	@Override
	public int tickRate(World world) {
		return 4;
	}

	@Override
	public boolean onBlockActivated(World world, int par2, int par3,
			int par4, EntityPlayer ep, int par6, float par7,
			float par8, float par9) {

		if (world.isRemote) {
			return true;
		}

		TileEntityLatch latch = (TileEntityLatch) world
				.getBlockTileEntity(par2, par3, par4);

		if (latch != null) {
			ep.openGui(RiseAutomatons.instance, Ids.guiTote, world, par2, par3, par4);
		}

		return true;
	}

	private void dispenseItem(World world, int x, int y, int z,
			Random par5Random) {
		int i = world.getBlockMetadata(x, y, z);
		int j = 0;
		int k = 0;

		if (i == 3) {
			k = 1;
		} else if (i == 2) {
			k = -1;
		} else if (i == 5) {
			j = 1;
		} else {
			j = -1;
		}

		TileEntityLatch latch = (TileEntityLatch) world
				.getBlockTileEntity(x, y, z);

		if (latch != null) {
			int id = latch.getNextStackFromInventory();

			double d = (double) x + (double) j * 0.59999999999999998D + 0.5D;
			double d1 = (double) y + 0.5D;
			double d2 = (double) z + (double) k * 0.59999999999999998D + 0.5D;
			ItemStack itemstack = null;
			if (id == -1) {

				world.playAuxSFX(1001, x, y, z, 0);
			} else {
				itemstack = latch.dispenserContents[id];
				if (itemstack.itemID < 256) {
					Block b = Block.blocksList[itemstack.itemID];
					if (world.getBlockId(x, y - 1, z) == 0
							&& b.canPlaceBlockAt(world, x, y - 1, z)) {
						latch.decrStackSize(id, 1);
						world.setBlock(x, y - 1, z, itemstack.itemID, 0, 3);
					} else if (world.getBlockId(x - 1, y, z) == 0
							&& b.canPlaceBlockAt(world, x - 1, y, z)) {
						latch.decrStackSize(id, 1);
						world.setBlock(x - 1, y, z, itemstack.itemID, 0, 3);
					} else if (world.getBlockId(x, y, z - 1) == 0
							&& b.canPlaceBlockAt(world, x, y, z - 1)) {
						latch.decrStackSize(id, 1);
						world.setBlock(x, y, z - 1, itemstack.itemID, 0, 3);
					} else if (world.getBlockId(x + 1, y, z) == 0
							&& b.canPlaceBlockAt(world, x + 1, y, z)) {
						latch.decrStackSize(id, 1);
						world.setBlock(x + 1, y, z, itemstack.itemID, 0, 3);
					} else if (world.getBlockId(x, y, z + 1) == 0
							&& b.canPlaceBlockAt(world, x, y, z + 1)) {
						latch.decrStackSize(id, 1);
						world.setBlock(x, y, z + 1, itemstack.itemID, 0, 3);
					} else if (world.getBlockId(x, y + 1, z) == 0
							&& b.canPlaceBlockAt(world, x, y + 1, z)) {
						latch.decrStackSize(id, 1);
						world.setBlock(x, y + 1, z, itemstack.itemID, 0, 3);
					}
				}
				world.playAuxSFX(2000, x, y, z, j + 1 + (k + 1) * 3);
			}
		}
	}

}
