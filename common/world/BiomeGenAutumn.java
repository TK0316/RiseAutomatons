package riseautomatons.common.world;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.WorldGenerator;

public class BiomeGenAutumn extends BiomeGenBase {

	public BiomeGenAutumn(int par1) {
		super(par1);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {

		if (par1Random.nextInt(5) == 0) {
			return worldGeneratorTrees;
		}

		if (par1Random.nextInt(10) == 0) {
			return worldGeneratorForest;
		} else {
			return worldGeneratorTrees;
		}
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		BiomeDecoratorCustom decorator = new BiomeDecoratorCustom(this);
		decorator.setTreesPerChunk(6);
		decorator.setGrassPerChunk(10);
		return decorator;
	}

}
