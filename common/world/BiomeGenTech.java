package riseautomatons.common.world;

import java.util.Random;

import riseautomatons.common.Ids;
import riseautomatons.common.entity.*;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenForest;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class BiomeGenTech extends BiomeGenForest {

	WorldGenerator worldGeneratorBigTree = new WorldGenFakeBigTree(false);
	public BiomeGenTech(int par1) {
		super(par1);
		worldGeneratorTrees = new WorldGenFakeTrees(false);
		worldGeneratorForest = new WorldGenFakeForest(false);
		worldGeneratorBigTree = new WorldGenFakeBigTree(false);

		this.spawnableCreatureList.add(new SpawnListEntry(EntityHelios.class, 14, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityBobby.class, 16, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityGolem.class, 5, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntitySlider.class, 4, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWatcher.class, 12, 4, 4));
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {

		if (par1Random.nextInt(5) == 0) {
			return this.worldGeneratorForest;
		}
		if (par1Random.nextInt(10) == 0) {
			return new WorldGenTechRuin();
			//return this.worldGeneratorBigTree;
		}

		return this.worldGeneratorTrees;
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		BiomeDecoratorCustom decorator = new BiomeDecoratorCustom(this);
		decorator.setSandPerChunk(20);
		decorator.setClayPerChunk(10);
		return decorator;
	}

}
