package riseautomatons.common.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import riseautomatons.common.Ids;
import riseautomatons.common.world.Biomes;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockFrass extends Block {

	public static boolean spread = true;

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
		if(spread == false && world.getBiomeGenForCoords(i, k).biomeName != Biomes.tech.biomeName) {
			return;
		}
		for(int n = j + 1; n < 255; n++) {
			if(world.isAirBlock(i, n, k) == false) {
				return;
			}
		}

		int m = world.getBlockMetadata(i, j, k);
		for(int x = -1; x <= 1; x++) {
			for(int z = -1; z <= 1; z++) {
				for(int y = -1; y <= 1; y++) {
					if(m < 8) {
						if(random.nextInt(3) != 1) {
							continue;
						}
						int blockId = world.getBlockId(i + x, j + y, k + z);
						int meta = 0;
						if(blockId == Block.sand.blockID) {
							meta = 1;
						}
						else if(blockId == Block.grass.blockID) {
							meta = 2;
						}
						else if(blockId == Block.dirt.blockID) {
							meta = 2;
						}
						else if(blockId == Block.blockClay.blockID) {
							meta = 3;
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
					else {
						int blockId = world.getBlockId(i + x, j + y, k + z);
						int meta = world.getBlockMetadata(i + x, j + y, k + z);
						if(blockId == Blocks.frass.blockID) {
							if(meta < 8) {
								world.setBlockAndMetadataWithNotify(i + x, j + y, k + z, Ids.blockFrass, meta + 8);
							}
						}
					}
				}
			}
		}
		if(random.nextInt(3) != 1) {
			return;
		}
		switch(m) {
		case 9:
			world.setBlockAndMetadata(i, j, k, Block.sand.blockID, 0);
			break;
		case 10:
			world.setBlockAndMetadata(i, j, k, Block.dirt.blockID, 0);
			break;
		case 11:
			world.setBlockAndMetadata(i, j, k, Block.blockClay.blockID, 0);
			break;
		case 12:
			world.setBlockAndMetadata(i, j, k, Block.waterStill.blockID, 0);
			break;
		default:
			break;
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j,
			int k, int l) {
		switch(l) {
		case 0:
			world.setBlockWithNotify(i, j, k, 0);
			break;
		case 1:
		case 9:
			world.setBlockWithNotify(i, j, k, Block.sand.blockID);
			break;
		case 2:
		case 10:
			world.setBlockWithNotify(i, j, k, Block.dirt.blockID);
			break;
		case 3:
		case 11:
			world.setBlockWithNotify(i, j, k, Block.blockClay.blockID);
			break;
		case 4:
		case 12:
			world.setBlockWithNotify(i, j, k, Block.waterStill.blockID);
			break;
		default:
			world.setBlockWithNotify(i, j, k, 0);
			break;
		}
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
		else if(meta == 1 || meta == 9) {
			if(par1 == 0 || par1 == 1) {
				return 21;
			}
			return 22;
		}
		else if(meta == 2 || meta == 10) {
			if(par1 == 0 || par1 == 1) {
				return 24;
			}
			return 25;
		}
		else if(meta == 3 || meta == 11) {
			if(par1 == 0 || par1 == 1) {
				return 19;
			}
			return 20;
		}
		else if(meta == 4 || meta == 12) {
			return 23;
		}
		return 26;
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 8));
	}

}
