package riseautomatons.world;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import riseautomatons.Ids;
import riseautomatons.item.EnumCraftSetType;
import riseautomatons.item.EnumSoulCore;
import riseautomatons.item.Items;

public class WorldGenStructure extends WorldGenerator {
	public int M = 7;
	public int W;
	public int set = 0;

	@Override
	public boolean generate(World world, Random random, int i, int j,
			int k) {

		// JJ=j;

		// int bb=world.getBlockId(i,j-1,k);
		// if(bb==2 || bb==12 || bb==AutomatonLogger.frass){

		int m2 = M / 2;

		int m = M - 1;
		int m5 = M + 1;
		int m6 = M + 3;

		int m22 = m / 2;

		int m33 = m22 - 1;
		// System.out.println(i+","+k);

		W = M;
		// i=(i/W)*W;
		// k=(k/W)*W;
		j = Ids.builderLevel;

		if (world.getBlockId(i, j, k) == Ids.blockTech) {
			return true;
		}

		System.out.println("city generating.........");
		System.out.println(String.valueOf(i) + "," + String.valueOf(j) + "," + String.valueOf(k));

		// for(int ii=0;ii<M;ii++){
		// for(int jj=0;jj<M;jj++){

		int RRR = set;
		if (random.nextInt(2) == 0) {
		//if (RRR == 0) {
			emptyOut(world, 24, m, m, i, j, k);
		} else {
			cavernize(world, random, 27, m, m, i, j, k);
			return true;
		}

		// }
		// }

		// for(int ii=0;ii<M;ii++){
		// for(int jj=0;jj<M;jj++){
		if (RRR == 0) {
			boolean[] bo = { true, true, true, true };

			int w = W;
			// if(jj<m ){

			int bb1 = world.getBlockId(i, j, k + w);
			// int mm1=world.getBlockMetadata(i,j,k+w);
			bo[0] = !(bb1 == Ids.blockSky);

			/*
			 * if(bo[0] && bb1!=AutomatonLogger.frass){ //AM_WorldGenStructure
			 * foliage = new AM_WorldGenStructure(); //foliage.set=1;
			 * //foliage.generate( world, random, i, j, k+w);
			 * world.setBlockAndMetadata
			 * (i,AutomatonLogger.builderLevel,k,AutomatonLogger
			 * .importantBuildingThingy,1); }
			 */
			// bo[0]=!cell[ii][jj+1];
			// }

			// if(jj>0){
			// bo[1]=!cell[ii][jj-1];

			int bb2 = world.getBlockId(i, j, k - w);
			// int mm2=world.getBlockMetadata(i,j,k-w);
			bo[1] = !(bb2 == Ids.blockSky);
			/*
			 * if(bo[1] && bb2!=AutomatonLogger.frass){ //AM_WorldGenStructure
			 * foliage = new AM_WorldGenStructure(); //foliage.set=1;
			 * //foliage.generate( world, random, i, j, k-w);
			 * world.setBlockAndMetadata
			 * (i,AutomatonLogger.builderLevel,k,AutomatonLogger
			 * .importantBuildingThingy,1); }
			 */

			// }

			// if(ii<m ){
			// bo[2]=!cell[ii+1][jj];
			int bb3 = world.getBlockId(i + w, j, k);
			// int mm3=world.getBlockMetadata(i+w,j,k);
			bo[2] = !(bb3 == Ids.blockSky);
			/*
			 * if(bo[2] && bb3!=AutomatonLogger.frass){ //AM_WorldGenStructure
			 * foliage = new AM_WorldGenStructure(); //foliage.set=1;
			 * //foliage.generate( world, random, i+w, j, k);
			 * world.setBlockAndMetadata
			 * (i,AutomatonLogger.builderLevel,k,AutomatonLogger
			 * .importantBuildingThingy,1); }
			 */

			// }

			// if(ii>0){
			// bo[3]=!cell[ii-1][jj];
			int bb4 = world.getBlockId(i - w, j, k);
			// int mm4=world.getBlockMetadata(i-w,j,k);
			bo[3] = !(bb4 == Ids.blockSky);
			/*
			 * if(bo[3] && bb4!=AutomatonLogger.frass){ //AM_WorldGenStructure
			 * foliage = new AM_WorldGenStructure(); //foliage.set=1;
			 * //foliage.generate( world, random, i-w, j, k);
			 * world.setBlockAndMetadata
			 * (i,AutomatonLogger.builderLevel,k,AutomatonLogger
			 * .importantBuildingThingy,1); }
			 */
			// }

			boxy(world, random, bo, 7, m, m, i, j, k);

			boxy(world, random, bo, 7, m5, m5, i, j + 8, k);
			boxy(world, random, bo, 7, m6, m6, i, j + 16, k);

			for (int z = 0; z <= m; z++) {
				for (int x = 0; x <= m; x++) {
					world.setBlockAndMetadata(i + (x - m22), j, k + (z - m22),
							Ids.blockSky, 1);
				}
			}
		}
		// }
		// }
		int r = m2;

		/*
		 * for(int x=j;x<=j+20;x++){ world.setBlockAndMetadata(i, x, k,
		 * AutomatonLogger.frass,1); //AndMetadata }
		 */

		world.markBlocksDirtyVertical(i - r, j - 3, k - r, i + r);

		//addTunnels(world,i,j,k);

		applyChests(world,random);

		// cell=null;
		chests = null;

		System.out.println("done");
		return true;
	}

	ArrayList<ArrayList<Integer>> chests = new ArrayList<ArrayList<Integer>>();

	// //////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////////////////////////////

	public void applyChests(World world, Random random) {

		for (int ii = 0; ii < chests.size(); ii++) {
			int i = chests.get(ii).get(0);
			int j = chests.get(ii).get(1);
			int k = chests.get(ii).get(2);

			world.setBlock(i, j, k, 54);
			chestMe(world, random, i, j, k);
		}

	}

	public void addTunnels(World world, int i, int j, int k) {

		int bb = 4;
		int ww = (W / 2);
		int WWW = W * 2;
		int F;
		F = world.getBlockId(i + WWW, j, k);
		if (F == Ids.blockSky
				|| F == Ids.blockArch) {
			// System.out.println("add tunnel south(-x)");
			int xe = (1 + i + ww);

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = k - 3; z <= k + 3; z++) {
					world.setBlock(x, j, z, bb);
				}
			}

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(x, y, k - 3, bb);
				}
			}

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(x, y, k + 3, bb);
				}
			}

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = k - 3; z <= k + 3; z++) {
					world.setBlock(x, j + 5, z, bb);
				}
			}

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = k - 2; z <= k + 2; z++) {
					for (int y = j + 1; y < j + 5; y++) {
						world.setBlock(z, y, x, 0);
					}
				}
			}

		}
		F = world.getBlockId(i - WWW, j, k);
		if (F == Ids.blockSky
				|| F == Ids.blockArch) {
			// world.setBlock(i-ww,j,k,bb);
			// System.out.println("add tunnel north(+x)");
			int xe = (i - ww);

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = k - 3; z <= k + 3; z++) {
					world.setBlock(x, j, z, bb);
				}
			}

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(x, y, k - 3, bb);
				}
			}

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(x, y, k + 3, bb);
				}
			}

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = k - 3; z <= k + 3; z++) {
					world.setBlock(x, j + 5, z, bb);
				}
			}

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = k - 2; z <= k + 2; z++) {
					for (int y = j + 1; y < j + 5; y++) {
						world.setBlock(z, y, x, 0);
					}
				}
			}

		}
		F = world.getBlockId(i, j, k + WWW);
		if (F == Ids.blockSky
				|| F == Ids.blockArch) {
			// System.out.println("add tunnel west(+z)");
			int xe = (k + ww);

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = i - 3; z <= i + 3; z++) {
					world.setBlock(z, j, x, bb);
				}
			}

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(i - 3, y, x, bb);
				}
			}

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(i + 3, y, x, bb);
				}
			}

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = i - 3; z <= i + 3; z++) {
					world.setBlock(z, j + 5, x, bb);
				}
			}

			for (int x = xe - 3; x < xe + 3; x++) {
				for (int z = i - 2; z <= i + 2; z++) {
					for (int y = j + 1; y < j + 5; y++) {
						world.setBlock(z, y, x, 0);
					}
				}
			}

		}
		F = world.getBlockId(i, j, k - WWW);
		if (F == Ids.blockSky
				|| F == Ids.blockArch) {
			// System.out.println("add tunnel east(-z)");
			int xe = (k - ww);

			for (int x = (xe - 3); x <= (xe + 3); x++) {
				for (int z = (i - 3); z <= (i + 3); z++) {
					world.setBlock(z, j, x, bb);
				}
			}

			for (int x = xe - 3; x <= xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(i - 3, y, x, bb);
				}
			}

			for (int x = xe - 3; x <= xe + 3; x++) {
				for (int y = j + 1; y < j + 5; y++) {
					world.setBlock(i + 3, y, x, bb);
				}
			}

			for (int x = (xe - 3); x <= (xe + 3); x++) {
				for (int z = (i - 3); z <= (i + 3); z++) {
					world.setBlock(z, j + 5, x, bb);
				}
			}

			for (int x = xe - 3; x <= xe + 3; x++) {
				for (int z = i - 2; z <= i + 2; z++) {
					for (int y = j + 1; y < j + 5; y++) {
						world.setBlock(z, y, x, 0);
					}
				}
			}

		}

	}

	private void boxy(World world, Random random, boolean[] boo, int height,
			int width, int length, int i, int j, int k) {
		struct(world, random, boo, height, width, length, i, j, k);
	}

	private void emptyOut(World world, int height, int width, int length,
			int i, int j, int k) {

		int w2 = width / 2;
		int l2 = length / 2;

		for (int z = 0; z <= length; z++) {
			for (int y = 0; y <= height; y++) {
				for (int x = 0; x <= width; x++) {
					world.setBlock(i + (x - w2), j + y, k + (z - l2), 0);
					// System.out.println("HERE: "+(x-w2));
				}
			}
		}
	}

	private void cavernize(World world, Random random, int height, int width,
			int length, int i, int j, int k) {

		int w2 = width / 2;
		int l2 = length / 2;

		int newheight = random.nextInt((height / 2)) + (height / 2);
		int he = newheight - (height / 6);

		for (int z = 0; z <= length; z++) {

			for (int x = 0; x <= width; x++) {
				world.setBlockAndMetadata(i + (x - w2), j, k + (z - l2),
						Ids.blockArch, 1); // AndMetadata
			}
		}
		world.setBlockAndMetadata(i, j, k, Ids.blockArchitect, 1);

		for (int z = 0; z <= length; z++) {
			for (int y = 1; y <= he; y++) {
				for (int x = 0; x <= width; x++) {
					world.setBlock(i + (x - w2), j + y, k + (z - l2), 0);
				}
			}
		}

		for (int z = 0; z <= length; z++) {
			for (int y = he; y <= newheight; y++) {
				for (int x = 0; x <= width; x++) {
					// System.out.println(newheight+" : "+y);
					if (random.nextInt(1 + newheight - y) != 0) {
						world.setBlock(i + (x - w2), j + y, k + (z - l2), 0);
					}
				}
			}
		}

		int RR = random.nextInt(7);
		if (RR > 2) {
			WorldGenerator obj;
			switch (RR) {
			case 4:
				obj = new WorldGenDerk();
				break;
			case 5:
				obj = new WorldGenPool();
				break;
			default:
				obj = new WorldGenFakeTrees(false);
				break;
			}

			obj.generate(world, random, i, j + 1, k);
		}

	}

	private void struct(World world, Random random, boolean[] boo, int height,
			int width, int length, int i, int j, int k) {
		int b = Ids.blockTech;

		int m = 2;
		int m2 = 3;
		int m3 = 8;
		int b3 = Ids.blockCrink;
		int w2 = width / 2;
		int l2 = length / 2;
		int h2 = height / 2;
		int w3 = width - 2;
		int l3 = length - 2;
		int h3 = height - 2;

		int w4 = w2 - 1;
		int l4 = l2 - 1;
		int h4 = h2 - 1;

		int h5 = h2 + 2;

		int w5 = w3 / 2;
		int l5 = l3 / 2;

		boolean zup = boo[0];
		boolean zown = boo[1];
		boolean xup = boo[2];
		boolean xown = boo[3];

		int h99 = height + 1;
		for (int y = 0; y <= h99; y++) {
			world.setBlockAndMetadata(i + w2, j + y, k + l2, b, m); // AndMetadata
		}

		for (int y = 0; y <= h99; y++) {
			world.setBlockAndMetadata(i - w2, j + y, k + l2, b, m);
		}

		for (int y = 0; y <= h99; y++) {
			world.setBlockAndMetadata(i + w2, j + y, k - l2, b, m);
		}

		for (int y = 0; y <= h99; y++) {
			world.setBlockAndMetadata(i - w2, j + y, k - l2, b, m);
		}

		int zh = 1;
		if (zup) {

			for (int x = 0; x <= w3; x++) {
				for (int y = 0; y <= h5; y++) {
					world.setBlockAndMetadata((x - w4) + i, j + y, k + l2 - zh,
							b, m);
				}
			}

			for (int x = 0; x < w5; x++) {
				world.setBlockAndMetadata((x - w4) + i, j + h2, k + l2 - zh,
						b3, m3);
			}
			for (int x = w5 + 1; x <= w3; x++) {
				world.setBlockAndMetadata((x - w4) + i, j + h2, k + l2 - zh,
						b3, m3);
			}

			for (int x = 0; x <= w3; x++) {

				world.setBlockAndMetadata((x - w4) + i, j + h5, k + l2 - zh,
						Ids.blockSky, 0);

			}

			for (int x = 0; x <= w3; x++) {
				for (int y = h5 + 1; y <= height; y++) {
					world.setBlockAndMetadata((x - w4) + i, j + y, k + l2, b,
							m2);
				}
			}

		}

		if (zown) {
			for (int x = 0; x <= w3; x++) {
				for (int y = 0; y <= h5; y++) {
					world.setBlockAndMetadata((x - w4) + i, j + y, k - l2 + zh,
							b, m);
				}
			}

			for (int x = 0; x < w5; x++) {
				world.setBlockAndMetadata((x - w4) + i, j + h2, k - l2 + zh,
						b3, m3);
			}
			for (int x = w5 + 1; x <= w3; x++) {
				world.setBlockAndMetadata((x - w4) + i, j + h2, k - l2 + zh,
						b3, m3);
			}

			for (int x = 0; x <= w3; x++) {

				world.setBlockAndMetadata((x - w4) + i, j + h5, k - l2 + zh,
						Ids.blockSky, 0);

			}

			for (int x = 0; x <= w3; x++) {
				for (int y = h5 + 1; y <= height; y++) {
					world.setBlockAndMetadata((x - w4) + i, j + y, k - l2, b,
							m2);
				}
			}

		}

		if (xup) {
			for (int z = 0; z <= l3; z++) {
				for (int y = 0; y <= h5; y++) {
					world.setBlockAndMetadata(i + w2 - zh, j + y, k + (z - l4),
							b, m);
				}
			}

			for (int z = 0; z < l5; z++) {
				world.setBlockAndMetadata(i + w2 - zh, j + h2, k + (z - l4),
						b3, m3);
			}
			for (int z = l5 + 1; z <= l3; z++) {
				world.setBlockAndMetadata(i + w2 - zh, j + h2, k + (z - l4),
						b3, m3);
			}

			for (int z = 0; z <= l3; z++) {
				world.setBlockAndMetadata(i + w2 - zh, j + h5, k + (z - l4),
						Ids.blockSky, 0);
			}

			for (int z = 0; z <= l3; z++) {
				for (int y = h5 + 1; y <= height; y++) {
					world.setBlockAndMetadata(i + w2, j + y, k + (z - l4), b,
							m2);
				}
			}

		}

		if (xown) {
			for (int z = 0; z <= l3; z++) {
				for (int y = 0; y <= h5; y++) {
					world.setBlockAndMetadata(i - w2 + zh, j + y, k + (z - l4),
							b, m);
				}
			}

			for (int z = 0; z < l5; z++) {
				world.setBlockAndMetadata(i - w2 + zh, j + h2, k + (z - l4),
						b3, m3);
			}
			for (int z = l5 + 1; z <= l3; z++) {
				world.setBlockAndMetadata(i - w2 + zh, j + h2, k + (z - l4),
						b3, m3);
			}

			for (int z = 0; z <= l3; z++) {

				world.setBlockAndMetadata(i - w2 + zh, j + h5, k + (z - l4),
						Ids.blockSky, 0);

			}

			for (int z = 0; z <= l3; z++) {
				for (int y = h5 + 1; y <= height; y++) {
					world.setBlockAndMetadata(i - w2, j + y, k + (z - l4), b,
							m2);
				}
			}

		}

		l3--;
		w3--;
		// w5+=2;
		// l5+=2;
		if (random.nextInt(4) != 0) {

			for (int z = 1; z <= l3; z++) {
				for (int x = 1; x <= w3; x++) {
					world.setBlock(i + (x - w5), j, k + (z - l5), 1);
				}
			}

			if (random.nextInt(5) == 0) {
				ArrayList<Integer> L = new ArrayList<Integer>();
				L.add(i);
				L.add(j + 1);
				L.add(k);
				chests.add(L);
				System.out.println(L.toString());
				// world.setBlock(i, j+1, k, 54);

			} else if (random.nextInt(5) == 0) {

				// world.setBlockAndMetadata(i, j+1, k,
				// Ids.deployer,random.nextInt(2)+1);
			}

		}

		/*
		 * switch(O){ case
		 * 1:world.setBlockWithNotify(i-w2,j+1,k,0);world.setBlockWithNotify
		 * (i-w2,j+2,k,0);world.setBlockWithNotify(i-w2,j+3,k,0);return; case
		 * 2:world
		 * .setBlockWithNotify(i+w2,j+1,k,0);world.setBlockWithNotify(i+w2
		 * ,j+2,k,0);world.setBlockWithNotify(i+w2,j+3,k,0);return; case
		 * 3:world.
		 * setBlockWithNotify(i,j+1,k-l2,0);world.setBlockWithNotify(i,j+
		 * 2,k-l2,0);world.setBlockWithNotify(i,j+3,k-l2,0);return; case
		 * 4:world.
		 * setBlockWithNotify(i,j+1,k+l2,0);world.setBlockWithNotify(i,j+
		 * 2,k+l2,0);world.setBlockWithNotify(i,j+3,k+l2,0);return; }
		 */

	}

	public void chestMe(World world, Random random, int i, int j, int k) {

		TileEntityChest tileentitychest = (TileEntityChest) world
				.getBlockTileEntity(i, j, k);
		// random.nextInt(tileentitychest.getSizeInventory())
		for(int x = 0; x < 27; x++) {
			ItemStack itemstack = pickCheckLootItem(random);
			if(itemstack != null) {
				tileentitychest.setInventorySlotContents(x, itemstack);
			}
		}
	}

	private ItemStack pickCheckLootItem(Random random) {
		int i = random.nextInt(50);
		if (i == 0) {
			return new ItemStack(Ids.itemChalk, random.nextInt(20) + 1, 0);
		}
		if (i == 1) {
			return new ItemStack(Item.ingotIron, random.nextInt(4) + 1);
		}
		if (i == 2) {
			return new ItemStack(Ids.soulCore, 1, EnumSoulCore.STONEEMPTY.ordinal());
		}
		if (i == 3) {
			return new ItemStack(Ids.soulCore, 1, EnumSoulCore.SOULSYNTH.ordinal());
		}
		if (i == 4) {
			return new ItemStack(Ids.itemWorker, random.nextInt(4) + 1, 0);
		}
		if (i == 5) {
			//return new ItemStack(Ids.blockCrystal, random.nextInt(4) + 1,0);
		}

		if (i == 7) {
			return new ItemStack(Item.ingotGold, random.nextInt(4) + 1);
		}

		if (i == 10) {
			return new ItemStack(Item.silk, random.nextInt(40) + 1);
		}

		if (i == 11) {
			return new ItemStack(Ids.itemSentry, random.nextInt(3) + 1, 0);
		}

		if (i == 12) {
			return new ItemStack(Ids.soulCore, random.nextInt(20) + 3, EnumSoulCore.SOULPURE.ordinal());
		}
		if (i == 13) {
			return new ItemStack(Item.diamond, 1);
		}
		if (i == 14) {
			return new ItemStack(Ids.craftSet, random.nextInt(15) + 3, random.nextInt(EnumCraftSetType.values().length));
		}
		if (i == 15) {
			return new ItemStack(Item.reed, random.nextInt(15) + 3);
		}
		if (i == 16) {
			return new ItemStack(Item.appleRed, random.nextInt(10) + 1);
		}

		if (i == 17) {
			return new ItemStack(Item.seeds, random.nextInt(7) + 1);
		}
		if (i == 18) {
			return new ItemStack(Block.sapling, random.nextInt(30) + 1);
		}
		if (i == 19) {
			return new ItemStack(Items.virus, random.nextInt(30) + 1, 0);
		}
		if( i > 40) {
			int j = random.nextInt(1000);
			int l = 0;
			for(int k = 0;; k = (k+1) % Item.itemsList.length) {
				if(Item.itemsList[k] != null && Item.itemsList[k].getCreativeTab() != null) {
					l++;
				}
				if(l >= j) {
					if(Item.itemsList[k] != null && Item.itemsList[k].getCreativeTab() != null) {
						return new ItemStack(Item.itemsList[k], random.nextInt(30) + 1);
					}
				}
			}
		}

		return null;
	}

	private void column(World world, int height, int i, int j, int k) {
		for (int x = -2; x <= 2; x++) {
			for (int z = -2; z <= 2; z++) {
				for (int y = 0; y >= -height; y--) {
					world.setBlock(i + x, j + y, k + z,
							Block.cobblestoneMossy.blockID);
				}
			}
		}

		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				for (int y = 0; y >= -height; y--) {
					world.setBlock(i + x, j + y, k + z, 0);
				}
			}
		}
	}

}
