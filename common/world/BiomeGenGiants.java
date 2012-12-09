package riseautomatons.common.world;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.WorldGenerator;


public class BiomeGenGiants extends BiomeGenBase {

	public BiomeGenGiants(int par1) {
		super(par1);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		if (par1Random.nextInt(5) == 0) {
			return worldGeneratorBigTree;
		}

		if (par1Random.nextInt(10) == 0) {
			return worldGeneratorForest;
		} else {
			return worldGeneratorTrees;
		}
	}

	@Override
	public int getBiomeGrassColor() {
		return 0xffb033;
	}

	@Override
	public int getBiomeFoliageColor() {
		float FF=getFloatRainfall()*getFloatTemperature();
		return (int) (0xffffff*FF);
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		BiomeDecoratorCustom decorator = new BiomeDecoratorCustom(this);
		decorator.setTreesPerChunk(6);
		decorator.setGrassPerChunk(10);
		return decorator;
	}

}
