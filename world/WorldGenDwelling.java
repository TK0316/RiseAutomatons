package riseautomatons.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import riseautomatons.Ids;
import riseautomatons.block.Blocks;

public class WorldGenDwelling extends WorldGenAbstractTree {

    public WorldGenDwelling(boolean p_i45448_1_) {
        super(p_i45448_1_);
    }

    @Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		Block bb = world.getBlock(i, j - 1, k);
		if (bb == Blocks.grass || bb == Blocks.sand || bb == Ids.blockArch) {
			boxy(world, random, 5, 7, 7, i, j + 3, k);

		}

		return true;
	}

	private void boxy(World world, Random random, int height, int width,
			int length, int i, int j, int k) {
		int w11 = width + 1;
		int l11 = length + 1;
		i = w11 * (i / w11);
		k = l11 * (k / l11);

		Block idd = world.getBlock(i, world.getTopSolidOrLiquidBlock(i, k) - 1,
                k);
		if (!(idd == Blocks.grass || idd == Blocks.dirt || idd == Blocks.sand || idd == Blocks.air)) {
			return;
		}

		int w2 = width / 2;
		int l2 = length / 2;
		int h2 = height / 2;
		int w3 = width - 2;
		int l3 = length - 2;
		int h3 = height - 2;

		int w4 = w2 - 1;
		int l4 = l2 - 1;
		int h4 = h2 - 1;

		int a1 = world.getTopSolidOrLiquidBlock(i + w2, k + l2);
		int a2 = world.getTopSolidOrLiquidBlock(i - w2, k + l2);
		int a3 = world.getTopSolidOrLiquidBlock(i - w2, k - l2);
		int a4 = world.getTopSolidOrLiquidBlock(i + w2, k - l2);
		int maxa = j;
		if (a1 < maxa) {
			maxa = a1;
		}
		if (a2 < maxa) {
			maxa = a2;
		}
		if (a3 < maxa) {
			maxa = a3;
		}
		if (a4 < maxa) {
			maxa = a4;
		}
		j = maxa - 1;

		// twice because frass is an asshole about that kinda thing
		for (int x = -w2; x <= width - w2; x++) {
			for (int z = -l2; z <= length - l2; z++) {
				for (int y = 0; y <= height; y++) {
					world.setBlockToAir(i + x, j + y, z + k);
				}
			}
		}

		int levels = random.nextInt(12) + 1;
		int ho = height * levels;

		for (int z = 0; z <= length; z++) {
			for (int x = 0; x <= width; x++) {

				world.setBlock(i + (x - (w2)), j, k + (z - (l2)),
						Ids.blockSky, 0, 3);
			}
		}

		boolean bool = true;
		int ph = 9000;
		for (int hi = 0; hi < levels; hi++) {
			int hhh = random.nextInt(4);
			int b = 0;
			if (ph > hhh) {
				b = 5;
			}
			ph = hhh;

			struct(b, world, 0, height, width - hhh, length - hhh, i, j + hi
					* height, k, bool);
			bool = false;
		}

		switch (random.nextInt(4)) {
		case 1:
			for (int z = ph; z < length - ph; z++) {
				for (int x = ph; x < width - ph; x++) {
					world.setBlock(i + (x - (w2)), j + ho + 1, k
							+ (z - (l2)), Blocks.stone_slab, 1, 3);
				}
			}
			break;
		case 2:
			for (int z = ph; z < length - ph; z++) {
				for (int x = ph; x < width - ph; x++) {
					world.setBlock(i + (x - (w2)), j + ho + 1, k
							+ (z - (l2)), Ids.blockTech, 3, 3);
				}
			}

			for (int y = 2; y <= 6; y++) {
				world.setBlock(i - 1, j + ho + y, k, Ids.blockSky, 0, 3);
				world.setBlock(i, j + ho + y, k - 1, Ids.blockSky, 0, 3);
				world.setBlock(i + 1, j + ho + y, k, Ids.blockSky, 0, 3);
				world.setBlock(i, j + ho + y, k + 1, Ids.blockSky, 0, 3);
				world.setBlock(i, j + ho + y + 5, k, Ids.blockSky, 0, 3);
			}
			break;

		case 3:
			for (int z = ph; z < length - ph; z++) {
				for (int x = ph; x < width - ph; x++) {
					world.setBlock(i + (x - (w2)), j + ho + 1, k
							+ (z - (l2)), Ids.blockTech, 3, 3);
				}
			}

			for (int y = 1; y <= 10; y++) {
				world.setBlock(i - w4 + 1, j + ho + y, k + l4 - 1,
						Ids.blockTech, 3, 3);
				world.setBlock(i - w4 + 1, j + ho + y, k - l4 + 1,
						Ids.blockTech, 3, 3);
				world.setBlock(i + w4 - 1, j + ho + y, k - l4 + 1,
						Ids.blockTech, 3, 3);
				world.setBlock(i + w4 - 1, j + ho + y, k + l4 - 1,
						Ids.blockTech, 3, 3);
			}
			world.setBlock(i - w4 + 1, j + ho + 10, k + l4 - 1,
					Ids.blockSky, 1, 3);
			world.setBlock(i - w4 + 1, j + ho + 10, k - l4 + 1,
					Ids.blockSky, 1, 3);
			world.setBlock(i + w4 - 1, j + ho + 10, k - l4 + 1,
					Ids.blockSky, 1, 3);
			world.setBlock(i + w4 - 1, j + ho + 10, k + l4 - 1,
					Ids.blockSky, 1, 3);

			break;
		default:
			int ph2 = ((width - ph) + 1) * 2;
			for (int y = 1; y <= ph2; y++) {
				for (int z = ph - 1; z <= length - ph; z++) {
					for (int x = ph - 1; x <= width - ph; x++) {
						world.setBlock(i + (x - (w2)), j + ho + y, k
								+ (z - (l2)), Ids.blockTech, 1, 3);
					}
				}
				if (y % 2 == 1) {
					ph++;
				}
			}
			break;

		}

		//TODO TowerManager.addTower(world, i, j, k, levels);

		// proterova1@hotmail.com
	}

	private void struct(int bb, World world, int O, int height, int width,
			int length, int i, int j, int k, boolean bottom) {
		Block b = Ids.blockTech;
		int bb1;
		int bb2;
		if (bb != 0) {
			bb1 = 0;
			bb2 = 4;
		} else {
			bb1 = 2;
			bb2 = 3;
		}

		int w2 = width / 2;
		int l2 = length / 2;
		int h2 = height / 2;
		int w3 = width - 2 - width % 2;
		int l3 = length - 2 - length % 2;
		int h3 = height - 2;

		int w4 = w2 - 1;
		int l4 = l2 - 1;
		int h4 = h2 - 1;

		for (int x = 0; x <= w3; x++) {
			for (int y = 0; y <= height; y++) {
				world.setBlock((x - w4) + i, j + y,
						k + l2, b, bb1, 3);
			}
		}

		for (int x = 0; x <= w3; x++) {
			for (int y = 0; y <= height; y++) {
				world.setBlock(((x - w4)) + i, j + y, k
						- l2, b, bb1, 3);
			}
		}

		// int l9=l2-1;
		for (int z = 1; z < length; z++) {
			for (int y = 0; y <= height; y++) {
				world.setBlock(i + w2, j + y,
						k + (z - l2), b, bb1, 3);
			}
		}

		for (int z = 1; z < length; z++) {
			for (int y = 0; y <= height; y++) {
				world.setBlock(i - w2, j + y,
						k + (z - l2), b, bb1, 3);
			}
		}

		for (int y = 0; y < height; y++) {
			world.setBlock(i - w2, j + y, k - l2, b, bb2, 3);
		}
		for (int y = 0; y < height; y++) {
			world.setBlock(i - w2, j + y, k + l2, b, bb2, 3);
		}
		for (int y = 0; y < height; y++) {
			world.setBlock(i + w2, j + y, k + l2, b, bb2, 3);
		}
		for (int y = 0; y < height; y++) {
			world.setBlock(i + w2, j + y, k - l2, b, bb2, 3);
		}

		world.setBlock(i + w2, j + height, k - l2, Blocks.stone_slab, 1, 3);
		world.setBlock(i + w2, j + height, k + l2, Blocks.stone_slab, 1, 3);
		world.setBlock(i - w2, j + height, k + l2, Blocks.stone_slab, 1, 3);
		world.setBlock(i - w2, j + height, k - l2, Blocks.stone_slab, 1, 3);
		/*
		 * for(int z=0;z<=l3;z++){ for(int y=0;y<=h3;y++){ for(int
		 * x=0;x<=w3;x++){ world.setBlockWithNotify(i+(x-w4), j+y+1, k+(z-l4),
		 * 0); } } }
		 */

		for (int z = 0; z <= l3; z++) {
			for (int x = 0; x <= w3; x++) {
				world.setBlock(i + (x - w4), j, k + (z - l4),
						Blocks.sandstone, 0, 3);
			}
		}

		if (bottom) {
			int par = world.rand.nextInt(4);
			switch (par) {
			case 1:

				world.setBlockToAir(i, j + height - 3, k + l2);
				world.setBlockToAir(i, j + height - 4, k + l2);
				break;
			case 2:
				world.setBlockToAir(i, j + height - 3, k - l2);
				world.setBlockToAir(i, j + height - 4, k - l2);
				break;

			case 3:
				world.setBlockToAir(i - w2, j + height - 3, k);
				world.setBlockToAir(i - w2, j + height - 4, k);
				break;
			default:
				world.setBlockToAir(i + w2, j + height - 3, k);
				world.setBlockToAir(i + w2, j + height - 4, k);

			}
		} else {
			if (world.rand.nextInt(2) == 0) {
				int par = world.rand.nextInt(16);
				int mod = world.rand.nextInt(3);

				if ((par & 1) == 0) {
					if (mod != 0) {
						for (int x = 0; x <= w3; x++) {
							world.setBlock((x - w4) + i, j + h3, k
									+ l2, Blocks.glass_pane, 0, 3);
						}
					} else {
						world.setBlock(i, j + h3, k + l2,
								Ids.blockSky, 1, 3);
						world.setBlock(i, j + h3 - 1, k
								+ l2, Ids.blockSky, 1, 3);
						world.setBlock(i, j + h3 - 2, k
								+ l2, Ids.blockSky, 1, 3);
					}

				}
				if ((par & 2) == 0) {
					if (mod != 0) {
						for (int x = 0; x <= w3; x++) {
							world.setBlock(((x - w4)) + i, j + h3, k
									- l2, Blocks.glass_pane, 0, 3);
						}
					} else {
						world.setBlock(i, j + h3, k - l2,
								Ids.blockSky, 1, 3);
						world.setBlock(i, j + h3 - 1, k
								- l2, Ids.blockSky, 1, 3);
						world.setBlock(i, j + h3 - 2, k
								- l2, Ids.blockSky, 1, 3);
					}
				}
				if ((par & 4) == 0) {
					if (mod != 0) {
						for (int z = 0; z <= l3; z++) {
							world.setBlock(i + w2, j + h3, k
									+ (z - l4), Blocks.glass_pane, 0, 3);
						}
					} else {
						world.setBlock(i + w2, j + h3, k,
								Ids.blockSky, 1, 3);
						world.setBlock(i + w2, j + h3 - 1,
								k, Ids.blockSky, 1, 3);
						world.setBlock(i + w2, j + h3 - 2,
								k, Ids.blockSky, 1, 3);
					}
				}
				if ((par & 8) == 0) {
					if (mod != 0) {
						for (int z = 0; z <= l3; z++) {
							world.setBlock(i - w2, j + h3, k
									+ (z - l4), Blocks.glass_pane, 0, 3);
						}
					} else {
						world.setBlock(i - w2, j + h3, k,
								Ids.blockSky, 1, 3);
						world.setBlock(i - w2, j + h3 - 1,
								k, Ids.blockSky, 1, 3);
						world.setBlock(i - w2, j + h3 - 2,
								k, Ids.blockSky, 1, 3);
					}
				}

			}
		}

		if (bb > 4) {
			for (int u = 1; u <= 4; u++) {
				world.setBlock(i + w2, j - u, k - l2, Blocks.iron_bars, 0, 3);
				// world.setBlockWithNotify(i+w2-1, j-u, k-l2, 101);
				// world.setBlockWithNotify(i+w2, j-u, k-l2+1, 101);

				world.setBlock(i + w2, j - u, k + l2, Blocks.iron_bars, 0, 3);
				// world.setBlockWithNotify(i+w2-1, j-u, k+l2, 101);
				// world.setBlockWithNotify(i+w2, j-u, k+l2-1, 101);

				world.setBlock(i - w2, j - u, k + l2, Blocks.iron_bars, 0, 3);
				// world.setBlockWithNotify(i-w2, j-u, k+l2-1, 101);
				// world.setBlockWithNotify(i-w2+1, j-u, k+l2, 101);

				world.setBlock(i - w2, j - u, k - l2, Blocks.iron_bars, 0, 3);
				// world.setBlockWithNotify(i-w2+1, j-u, k-l2, 101);
				// world.setBlockWithNotify(i-w2, j-u, k-l2+1, 101);
			}
		}

	}
}
