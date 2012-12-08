package riseautomatons.common.world;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.WorldGenerator;

public class BiomeGenCity extends BiomeGenBase {

	public BiomeGenCity(int par1) {
		super(par1);

		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
/*
		spawnableMonsterList.add(new SpawnListEntry(EntitySlider.class, 50, 1, 6));
		spawnableCreatureList.add(new SpawnListEntry(EntityHelios.class, 100, 1, 8));

		spawnableMonsterList.add(new SpawnListEntry(EntityAncient.class, 1, 1, 1));
		spawnableMonsterList.add(new SpawnListEntry(EntityWatcher.class, 50, 1, 3));*/
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return new WorldGenDwelling();
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		BiomeDecoratorCustom decorator = new BiomeDecoratorCustom(this);
		decorator.setTreesPerChunk(2);
		decorator.setFlowersPerChunk(1);
		decorator.setGrassPerChunk(2);
		return decorator;
	}

}
