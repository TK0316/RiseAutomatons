package riseautomatons.common.block;

import java.util.Random;

import riseautomatons.common.CommonProxy;
import riseautomatons.common.Ids;
import riseautomatons.common.entity.EntityGolem;
import riseautomatons.common.entity.EntityGolemPure;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockArchBend extends Block {

	protected BlockArchBend(int par1) {
		super(par1, 3, Material.glass);
		setBlockBounds(0.25F, 0.0F, 0.25F, .75F, 1, .75F);
		this.setTickRandomly(true);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		//
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3,
				par4);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		if (world.getBlockMetadata(i, j, k) != 4)
			grow(world, i, j, k, random);
	}

	public void grow(World world, int i, int j, int k, Random random) {
		if (!world.isRemote) {
			int id = world.getBlockId(i, j + 1, k);
			if (id != blockID && id != Ids.blockCrink) {
				if (world.canBlockSeeTheSky(i, j, k)) {
					if (random.nextInt(7) == 0) {
						payload(world, i, j, k, random);

					} else {
						world.setBlockAndMetadataWithNotify(i, j + 1, k,
								blockID, 1);
						archy(world, i, j, k);
					}
				} else if (!planterCheck(world, i, j, k)) {
					if (random.nextInt(4) == 0) {
						switch (random.nextInt(4)) {
						case 1:
							if (world.getBlockId(i - 1, j, k) != blockID)
								world.setBlockAndMetadataWithNotify(i - 1, j,
										k, blockID, 2);
							break;
						case 2:
							if (world.getBlockId(i + 1, j, k) != blockID)
								world.setBlockAndMetadataWithNotify(i + 1, j,
										k, blockID, 2);
							break;
						case 3:
							if (world.getBlockId(i, j, k - 1) != blockID)
								world.setBlockAndMetadataWithNotify(i, j,
										k - 1, blockID, 2);
							break;
						default:
							if (world.getBlockId(i, j, k + 1) != blockID)
								world.setBlockAndMetadataWithNotify(i, j,
										k + 1, blockID, 2);
							break;
						}
					} else {
						archy(world, i, j, k);
						world.setBlockWithNotify(i, j + 1, k, blockID);
					}
				}
			}
		}
	}

	public void payload(World world, int i, int j, int k, Random random) {
		int ii = random.nextInt(5);
		if (ii == 0) {
			ii = 10;
			int it = 0;
			int count = 0;

			while (world.getBlockId(i, j - it, k) == blockID) {
				it++;
				if (world.getBlockMetadata(i, j - it, k) == 3)
					count++;
			}
			if (count > 0) {
				for (int n = 0; n < it; n++) {
					world.spawnEntityInWorld(new EntityGolemPure(world,
							(float) i + 0.5F, (float) j + 1, (float) k + 0.5F,
							Ids.blockArch, 8, 0));
				}
			} else {
				world.spawnEntityInWorld(new EntityGolem(world,
						(float) i + 0.5F, (float) j + 1, (float) k + 0.5F,
						Ids.blockArch, 2, 0));
			}
		}
		if (ii == 4 || ii == 3) {
			boop(world, i, j + 1, k, ii);
			boop(world, i + 1, j + 1, k, ii);
			boop(world, i + 2, j + 1, k, ii);
			boop(world, i - 1, j + 1, k, ii);
			boop(world, i - 2, j + 1, k, ii);
			boop(world, i, j + 1, k + 1, ii);
			boop(world, i, j + 1, k + 2, ii);
			boop(world, i, j + 1, k - 1, ii);
			boop(world, i, j + 1, k - 2, ii);
			boop(world, i, j + 2, k, ii);

			boop(world, i - 1, j + 1, k - 1, ii);
			boop(world, i - 1, j + 1, k + 1, ii);
			boop(world, i + 1, j + 1, k + 1, ii);
			boop(world, i + 1, j + 1, k - 1, ii);
		} else {
			world.setBlockAndMetadataWithNotify(i, j + 1, k, Ids.blockCrink, ii);
		}

	}

	public boolean planterCheck(World world, int i, int j, int k) {
		boolean b1 = infect(world, i - 1, j, k);
		boolean b2 = infect(world, i + 1, j, k);
		boolean b3 = infect(world, i, j, k - 1);
		boolean b4 = infect(world, i, j, k + 1);
		boolean b5 = infect(world, i, j + 1, k);

		return b1 || b2 || b3 || b4 || b5;
	}

	public void boop(World world, int i, int j, int k, int ii) {
		if (world.getBlockId(i, j, k) == 0) {
			world.setBlockAndMetadataWithNotify(i, j, k, Ids.blockCrink, ii);
		}
	}

	public boolean infect(World world, int i, int j, int k) {
		Material th = world.getBlockMaterial(i, j, k);
		if (th == Material.wood || th == Material.vine || th == Material.plants
				|| th == Material.leaves) {
			world.setBlockAndMetadataWithNotify(i, j, k, blockID, 3);
			return true;
		}
		return false;
	}

	public void archy(World world, int i, int j, int k) {
		BlockArch.place(world, i - 1, j, k);
		BlockArch.place(world, i + 1, j, k);
		BlockArch.place(world, i, j, k - 1);
		BlockArch.place(world, i, j, k + 1);
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l) {
		if (world.getBlockId(i, j + 1, k) == Ids.blockCrink) {
			world.setBlockWithNotify(i, j + 1, k, 0);
			dropBlockAsItem_do(world, i, j + 1, k, new ItemStack(Ids.blockCrink, 1,
					0));
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		checkBlockCoordValid(world, i, j, k);
	}

	protected final void checkBlockCoordValid(World world, int i, int j, int k) {
		if (!canBlockStay(world, i, j, k)) {
			dropBlockAsItem(world, i, j, k, 0, 0);
			world.setBlockWithNotify(i, j, k, 0);
		}
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {

	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public int damageDropped(int par1) {
		return 4;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		int bbb = world.getBlockId(i, j - 1, k);
		int meta = world.getBlockMetadata(i, j, k);
		if (meta >= 3) {
			return true;
		}
		if (bbb == 0) {
			if (meta == 2
					&& (world.getBlockId(i - 1, j, k) != 0
							|| world.getBlockId(i + 1, j, k) != 0
							|| world.getBlockId(i, j, k - 1) != 0 || world
							.getBlockId(i, j, k + 1) != 0)) {
				return true;
			} else {
				return false;
			}
		}
		return bbb != 0;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess,
			int i, int j, int k) {

		boolean bound[]=new boolean[6];
		int id=iblockaccess.getBlockId(i, j-1, k);
		if(id!=blockID){
			bound[0]=iblockaccess.getBlockId(i-1, j, k)!=0;
			bound[1]=iblockaccess.getBlockId(i, j, k-1)!=0;
			bound[2]=iblockaccess.getBlockId(i+1, j, k)!=0;
			bound[3]=iblockaccess.getBlockId(i, j, k+1)!=0;
			bound[4]=iblockaccess.getBlockId(i, j+1, k)!=0;
			//boolean any=bound[0]||bound[1]||bound[2]||bound[3];

			setBlockBounds(bound[0]?0:0.25F, 0.0F, bound[1]?0:0.25F, bound[2]?1:0.75F, bound[4]?1:0.5f, bound[3]?1:0.75F);
		}else{

			bound[0]=iblockaccess.getBlockId(i-1, j, k)==blockID && iblockaccess.getBlockId(i-1, j-1, k)==0;
			bound[1]=iblockaccess.getBlockId(i, j, k-1)==blockID && iblockaccess.getBlockId(i, j-1, k-1)==0;
			bound[2]=iblockaccess.getBlockId(i+1, j, k)==blockID && iblockaccess.getBlockId(i+1, j-1, k)==0;
			bound[3]=iblockaccess.getBlockId(i, j, k+1)==blockID && iblockaccess.getBlockId(i, j-1, k+1)==0;
			setBlockBounds(bound[0]?0:0.25F, 0.0F, bound[1]?0:0.25F, bound[2]?1:0.75F, 1f, bound[3]?1:0.75F);

			//setBlockBounds(0.25F, 0.0F, 0.25F, .75F, 1, .75F);
		}
	}

	@Override
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int i, int j,
			int k) {
		switch (par1IBlockAccess.getBlockMetadata(i, j, k)) {
		case 1:
			return 0x5EA4FF;
		case 3:
			int var5 = 0;
			int var6 = 0;
			int var7 = 0;

			for (int var8 = -1; var8 <= 1; ++var8) {
				for (int var9 = -1; var9 <= 1; ++var9) {
					int var10 = par1IBlockAccess.getBiomeGenForCoords(i + var9,
							k + var8).getBiomeGrassColor();
					var5 += (var10 & 16711680) >> 16;
					var6 += (var10 & 65280) >> 8;
					var7 += var10 & 255;
				}
			}

			return (var5 / 9 & 255) << 16 | (var6 / 9 & 255) << 8 | var7 / 9
					& 255;

		default:
			return 0xffffff;
		}
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.25F, 0.25F, 0.25F, .75F, 0.75f, .75F);
	}

	@Override
	public boolean canBlockStay(World world, int i, int j, int k) {
		return canPlaceBlockAt(world, i, j, k);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLiving par5EntityLiving) {

	}

	@Override
	public boolean removeBlockByPlayer(World world, EntityPlayer player, int x,
			int y, int z) {
		//onBlockDestroyedByPlayer(world, x, y, z, 0);
		return super.removeBlockByPlayer(world, player, x, y, z);
	}

	@Override
	public String getTextureFile() {
		return Blocks.BLOCK_PNG;
	}

}
