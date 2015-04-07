package riseautomatons.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.world.Biomes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFrass extends Block {

	private IIcon icons[];

	public static boolean spread = true;

	public BlockFrass() {
		super(Material.grass);
		setTickRandomly(true);

	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess,
			int i, int j, int k, int l) {
		Block i1 = iblockaccess.getBlock(i, j, k);

		if (i1 == this) {
			return false;
		} else {
			return super.shouldSideBeRendered(iblockaccess, i, j, k, l);
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return Blocks.leaves.isOpaqueCube();
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
						if(random.nextInt(10) != 1) {
							continue;
						}
						Block block = world.getBlock(i + x, j + y, k + z);
						int meta = 0;
						if(block == Blocks.sand) {
							meta = 1;
						}
						else if(block == Blocks.grass) {
							meta = 2;
						}
						else if(block == Blocks.dirt) {
							meta = 2;
						}
						else if(block == Blocks.clay) {
							meta = 3;
						}
						else if(block == Blocks.water) {
							meta = 4;
						}
						else if(block == Blocks.ice) {
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
						
						if(world.isDaytime()) {
							world.setBlock(i + x, j + y, k + z, Ids.blockFrass, meta, 3);
						}
					}
					else {
						Block block = world.getBlock(i + x, j + y, k + z);
						int meta = world.getBlockMetadata(i + x, j + y, k + z);
						if(block == Blocks.frass) {
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
			world.setBlockToAir(i, j, k);
			break;
		case 9:
			world.setBlock(i, j, k, Blocks.sand, 0, 3);
			break;
		case 10:
			world.setBlock(i, j, k, Blocks.dirt, 0, 3);
			break;
		case 11:
			world.setBlock(i, j, k, Blocks.clay, 0, 3);
			break;
		case 12:
			world.setBlock(i, j, k, Blocks.water, 0, 3);
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
			world.setBlockToAir(i, j, k);
			break;
		case 1:
		case 9:
			world.setBlock(i, j, k, Blocks.sand, 0, 3);
			break;
		case 2:
		case 10:
			world.setBlock(i, j, k, Blocks.dirt, 0, 3);
			break;
		case 3:
		case 11:
			world.setBlock(i, j, k, Blocks.clay, 0, 3);
			break;
		case 4:
		case 12:
			world.setBlock(i, j, k, Blocks.water, 0, 3);
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
		return (Blocks.leaves.isOpaqueCube()) ? 0 : 1;
	}

	@Override
	public IIcon getIcon(int par1, int meta) {
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
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[8];
		icons[0] = par1IconRegister.registerIcon("riseautomatons:frass1");
		icons[1] = par1IconRegister.registerIcon("riseautomatons:frass2");
		icons[2] = par1IconRegister.registerIcon("riseautomatons:frass3");
		icons[3] = par1IconRegister.registerIcon("riseautomatons:frass4");
		icons[4] = par1IconRegister.registerIcon("riseautomatons:frass5");
		icons[5] = par1IconRegister.registerIcon("riseautomatons:frass6");
		icons[6] = par1IconRegister.registerIcon("riseautomatons:frass7");
		icons[7] = par1IconRegister.registerIcon("riseautomatons:frassn");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs tab, List subItems) {
		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 8));
	}

}
