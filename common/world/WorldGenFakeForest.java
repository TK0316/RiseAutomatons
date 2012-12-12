package riseautomatons.common.world;

import java.util.Random;

import riseautomatons.common.block.Blocks;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenForest;

public class WorldGenFakeForest extends WorldGenForest {

	public WorldGenFakeForest(boolean par1) {
		super(par1);
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3,
			int par4, int par5) {

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
