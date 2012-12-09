package riseautomatons.common.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import riseautomatons.common.Ids;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockFrass extends Block {

	public BlockFrass(int par1) {
		super(par1, Material.grass);
		setTickRandomly(true);

	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess,
			int i, int j, int k, int l) {
		int i1 = iblockaccess.getBlockId(i, j, k);

		if (i1 == blockID) {
			return false;
		} else {
			return super.shouldSideBeRendered(iblockaccess, i, j, k, l);
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return (!Block.leaves.graphicsLevel);
	}

	@Override
	public void updateTick(World world, int i, int j, int k,
			Random random) {
		{
			int m = world.getBlockMetadata(i, j, k);
			if(m > -1) {
				//world.setBlockAndMetadataWithNotify(i, j, k, m, 0);
				//ereturn;
			}
		}
		for(int n = j + 1; n < 255; n++) {
			if(world.isAirBlock(i, n, k) == false) {
				return;
			}
		}

		for(int x = -1; x <= 1; x++) {
			for(int z = -1; z <= 1; z++) {
				for(int y = -1; y <= 1; y++) {
					int blockId = world.getBlockId(i + x, j + y, k + z);
					int meta = 0;
					if(blockId == Block.sand.blockID) {
						meta = 1;
					}
					else if(blockId == Block.grass.blockID) {
						meta = 2;
					}
					else if(blockId == Block.blockClay.blockID) {
						meta = 3;
					}
					else if(blockId == Block.dirt.blockID) {
						meta = 2;
					}
					else if(blockId == Block.waterStill.blockID) {
						meta = 4;
					}
					else if(blockId == Block.ice.blockID) {
						meta = 4;
					}
					else {
						continue;
					}
					boolean isShine = true;
					for(int n = j + y + 1; n < 255; n++) {
						if(world.isAirBlock(i + x, n, k + z) == false) {
							isShine = false;
							break;
						}
					}
					if(isShine == false) {
						continue;
					}
					world.setBlockAndMetadataWithNotify(i + x, j + y, k + z, Ids.blockFrass, meta);
				}
			}
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j,
			int k, int l) {
		world.setBlockWithNotify(i, j, k, l);
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public int getRenderBlockPass() {
		return (!Block.leaves.graphicsLevel) ? 0 : 1;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int meta) {
		if(meta == 0) {
			return 26;
		}
		if(meta == 3) {
			if(par1 == 0 || par1 == 1) {
				return 19;
			}
			return 20;
		}
		else if(meta == 1) {
			if(par1 == 0 || par1 == 1) {
				return 21;
			}
			return 22;
		}
		else if(meta == 4) {
			return 23;
		}
		else if(meta == 2) {
			if(par1 == 0 || par1 == 1) {
				return 24;
			}
			return 25;
		}
		return 26;
	}

}
