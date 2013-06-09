package riseautomatons.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riseautomatons.Ids;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockArch extends Block {

	private Icon icons[];

	public BlockArch(int par1, Material par2Material) {
		super(par1, par2Material);
		BlockArch.loadSprites();
	}

	protected BlockArch(int par1) {
		super(par1, Material.glass);
		BlockArch.loadSprites();
	}

	static int D[];

	static void loadSprites() {
		D = new int[2];
		D[0] = 2;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/arch1.png");
		D[1] = 4;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/arch3.png");
	}

	public String getTextureFile() {
		return Blocks.BLOCK_PNG;
	}

	@Override
	public Icon getBlockTextureFromSideAndMetadata(int i, int j) {
		if (j == 5) {
			return icons[2];
		}
		return icons[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[3];
		icons[0] = par1IconRegister.registerIcon("riseautomatons:arch1");
		icons[1] = par1IconRegister.registerIcon("riseautomatons:arch2");
		icons[2] = par1IconRegister.registerIcon("riseautomatons:arch3");
	}

	public static boolean place(World world, int i, int j, int k) {
		int id = world.getBlockId(i, j, k);

		if (id == 3) {
			world.setBlock(i, j, k, Ids.blockArch, 4, 3);
			return true;
		} else if (id == 1 || id == 4) {
			world.setBlock(i, j, k, Ids.blockArch, 1, 3);
			return true;
		} else if (id == 8 || id == 9) {
			world.setBlock(i, j, k, Ids.blockArch, 2, 3);
			return true;
		} else if (id == 2) {
			world.setBlock(i, j, k, Ids.blockArch, 3, 3);
			return true;
		}/*
		 * else if(id!=zei_Ids.arch && id!=zei_Ids.arch2&& id!=0){
		 * world.setBlock(i, j, k,zei_Ids.arch, 0);return
		 * true; }
		 */
		return false;
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		if (world.getBlockMetadata(i, j, k) == 5) {
			int it = 1;
			while (j - it > 20) {
				place(world, i, j - it, k);
				it++;
			}
			world.setBlock(i, 20, k, Ids.blockArchitect, 0, 3);
		}
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public int damageDropped(int par1) {
		return 0;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j, int k,
			int metadata) {

		if (metadata == 4) {
			world.setBlock(i, j, k, Block.dirt.blockID, 0, 3);
		} else if (metadata == 1) {
			world.setBlock(i, j, k, Block.stone.blockID, 0, 3);
		} else if (metadata == 2) {
			world.setBlock(i, j, k, 9, 0, 3);
		} else if (metadata == 3) {
			world.setBlock(i, j, k, 2, 0, 3);
		}

		dropBlockAsItem_do(world, i, j, k, new ItemStack(blockID, 1, 0));
	}

	@Override
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int i, int j,
			int k) {
		switch (par1IBlockAccess.getBlockMetadata(i, j, k)) {

		case 1:
			return 0x8A8A8A;
		case 2:
			return 0x469DC2;
		case 3:// return 0x148C24;
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
		case 4:
			return 0x806045;
		case 5:
			return 0xffffff;

		default:
			return 0xADA890;
		}
	}
}
