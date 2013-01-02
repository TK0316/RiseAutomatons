package riseautomatons.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenForest;
import riseautomatons.block.Blocks;

public class WorldGenFakeForest extends WorldGenForest {

	public WorldGenFakeForest(boolean par1) {
		super(par1);
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3,
			int par4, int par5) {

		for (int var6 = 0; var6 < 5; ++var6) {
			int i = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int j = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int k = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(i, j, k)) {
				// height
				int var10 = 1 + par2Random.nextInt(par2Random.nextInt(10) + 1);

				int length = 0;
				for (int n = 0; n < var10 && j - n > 0; ++n) {
					if(par1World.isAirBlock(i, j - n, k)
							&& par1World.isAirBlock(i + 1, j - n, k)
							&& par1World.isAirBlock(i - 1, j - n, k)
							&& par1World.isAirBlock(i, j - n, k + 1)
							&& par1World.isAirBlock(i, j - n, k - 1)
							) {
						length++;
					}
					else if(length > 0 && par1World.getBlockId(i, j - n, k) == Block.grass.blockID){
						//System.out.println("placable");
						int meta = length < 6 ? length : 5;
						for (int m = j - n; m < j && m < j - n + meta; ++m) {
							if(par1World.isAirBlock(i, m, k)) {
								//par1World.setBlockAndMetadata(var7, var12, var9, Blocks.grower.blockID, meta);
								par1World.setBlockAndMetadata(i, m, k, Blocks.grower.blockID, meta);
								//par1World.setBlock(i, m, k, Block.brick.blockID);
								//System.out.println(String.valueOf(var7) + ", " +  String.valueOf(var8)  + ", " +  String.valueOf(var9));
							}
						}
						if(meta == 5) {
							par1World.setBlock(i, j - n + meta, k, Blocks.crink.blockID);
						}
						break;
					}
				}

			}
		}

		for (int var6 = 0; var6 < 5; ++var6) {
			int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(var7, var8, var9)) {
				// height
				int var10 = 4 + par2Random.nextInt(par2Random.nextInt(10) + 1);

				int length = 0;
				for (int var11 = 0; var11 < var10 && var8 - var11 > 0; ++var11) {
					if(par1World.isAirBlock(var7, var8 - var11, var9)
							&& par1World.isAirBlock(var7 + 1, var8 - var11, var9)
							&& par1World.isAirBlock(var7 - 1, var8 - var11, var9)
							&& par1World.isAirBlock(var7, var8 - var11, var9 + 1)
							&& par1World.isAirBlock(var7, var8 - var11, var9 - 1)
							) {
						length++;
					}
					else if(length > 4){
						for (int var12 = var11 + 1; var12 < var8; ++var12) {
							par1World.setBlock(var7, var12, var9, Block.stone.blockID);
						}
						par1World.setBlock(var7, var8, var9, Blocks.glowy.blockID);
						break;
					}
				}

			}
		}

		return true;
	}

}
