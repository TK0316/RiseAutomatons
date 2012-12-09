package riseautomatons.common.world;

import java.util.Random;

import riseautomatons.common.Ids;

import net.minecraft.src.BiomeGenForest;
import net.minecraft.src.WorldGenerator;

public class BiomeGenTech extends BiomeGenForest {

	WorldGenerator worldGeneratorBigTree = new WorldGenFakeBigTree(false);
	public BiomeGenTech(int par1) {
		super(par1);
		worldGeneratorTrees = new WorldGenFakeTrees(false);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {

		if (par1Random.nextInt(5) == 0) {
			return this.worldGeneratorForest;
		}
		if (par1Random.nextInt(10) == 0) {
			//return this.worldGeneratorBigTree;
		}

		return this.worldGeneratorTrees;
	}

}
