package riseautomatons.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.world.Biomes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFrass extends Block {

	private Icon icons[];

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

		boolean sun = true;
		for(int n = j + 1; n < 255; n++) {
			if(world.isAirBlock(i, n, k) == false) {
				sun = false;;
			}
		}

		int m = world.getBlockMetadata(i, j, k);
		for(int x = -1; x <= 1; x++) {
			for(int z = -1; z <= 1; z++) {
				for(int y = -1; y <= 1; y++) {
					if(m < 8) {
						if(sun == false) {
							continue;
						}
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
						world.setBlock(i + x, j + y, k + z, Ids.blockFrass, meta, 3);
					}
					else {
						int blockId = world.getBlockId(i + x, j + y, k + z);
						int meta = world.getBlockMetadata(i + x, j + y, k + z);
						if(blockId == Blocks.frass.blockID) {
							if(meta < 8) {
								world.setBlock(i + x, j + y, k + z, Ids.blockFrass, meta + 8, 3);
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
		case 8:
			world.setBlock(i, j, k, 0, 0, 3);
			break;
		case 9:
			world.setBlock(i, j, k, Block.sand.blockID, 0, 3);
			break;
		case 10:
			world.setBlock(i, j, k, Block.dirt.blockID, 0, 3);
			break;
		case 11:
			world.setBlock(i, j, k, Block.blockClay.blockID, 0, 3);
			break;
		case 12:
			world.setBlock(i, j, k, Block.waterStill.blockID, 0, 3);
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
			world.setBlock(i, j, k, 0, 0, 3);
			break;
		case 1:
		case 9:
			world.setBlock(i, j, k, Block.sand.blockID, 0, 3);
			break;
		case 2:
		case 10:
			world.setBlock(i, j, k, Block.dirt.blockID, 0, 3);
			break;
		case 3:
		case 11:
			world.setBlock(i, j, k, Block.blockClay.blockID, 0, 3);
			break;
		case 4:
		case 12:
			world.setBlock(i, j, k, Block.waterStill.blockID, 0, 3);
			break;
		default:
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
	public Icon getIcon(int par1, int meta) {
		if(meta == 0) {
			return icons[7];
		}
		else if(meta == 1 || meta == 9) {
			if(par1 == 0 || par1 == 1) {
				return icons[2];
			}
			return icons[3];
		}
		else if(meta == 2 || meta == 10) {
			if(par1 == 0 || par1 == 1) {
				return icons[5];
			}
			return icons[6];
		}
		else if(meta == 3 || meta == 11) {
			if(par1 == 0 || par1 == 1) {
				return icons[0];
			}
			return icons[1];
		}
		else if(meta == 4 || meta == 12) {
			return icons[4];
		}
		return icons[7];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[8];
		icons[0] = par1IconRegister.registerIcon("riseautomatons:frass1");
		icons[1] = par1IconRegister.registerIcon("riseautomatons:frass2");
		icons[2] = par1IconRegister.registerIcon("riseautomatons:frass3");
		icons[3] = par1IconRegister.registerIcon("riseautomatons:frass4");
		icons[4] = par1IconRegister.registerIcon("riseautomatons:frass5");
		icons[5] = par1IconRegister.registerIcon("riseautomatons:frass6");
		icons[6] = par1IconRegister.registerIcon("riseautomatons:frass7");
		icons[7] = par1IconRegister.registerIcon("riseautomatons:frassn");
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 8));
	}

}
