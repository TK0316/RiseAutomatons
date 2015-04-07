package riseautomatons.world;

import java.util.Random;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenAutumn extends BiomeGenBase {

	public BiomeGenAutumn(int par1) {
		super(par1);
	}

	@Override
    //public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
    public WorldGenAbstractTree func_150567_a(Random par1Random) {

		if (par1Random.nextInt(5) == 0) {
			return worldGeneratorTrees;
		}

		if (par1Random.nextInt(10) == 0) {
			return super.func_150567_a(par1Random);
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
