package riseautomatons.world;

import java.util.Random;

import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;
import riseautomatons.entity.EntityBobby;
import riseautomatons.entity.EntityHelios;
import riseautomatons.entity.EntitySlider;
import riseautomatons.entity.EntityWatcher;

public class BiomeGenCity extends BiomeGenBase {

	public BiomeGenCity(int par1) {
		super(par1);

		spawnableMonsterList.clear();
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();

		spawnableMonsterList.add(new SpawnListEntry(EntitySlider.class, 10, 1, 6));
		spawnableCreatureList.add(new SpawnListEntry(EntityHelios.class, 10, 1, 8));
		spawnableCreatureList.add(new SpawnListEntry(EntityBobby.class, 16, 4, 4));
		spawnableCreatureList.add(new SpawnListEntry(EntityGolem.class, 5, 4, 4));

		//spawnableMonsterList.add(new SpawnListEntry(EntityAncient.class, 1, 1, 1));
		spawnableMonsterList.add(new SpawnListEntry(EntityWatcher.class, 10, 1, 3));
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		if(par1Random.nextInt(10) < 1) {
			return new WorldGenStructure();
		}
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
