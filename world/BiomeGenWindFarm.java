package riseautomatons.world;

import java.util.Random;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenWindFarm extends BiomeGenBase {

	public BiomeGenWindFarm(int par1) {
		super(par1);
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		BiomeDecoratorCustom decorator = new BiomeDecoratorCustom(this);
		decorator.setTreesPerChunk(4);
		decorator.setFlowersPerChunk(1);
		decorator.setGrassPerChunk(10);
		return decorator;
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return new WorldGenWindMill();
	}

}
