package riseautomatons.world;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeDecoratorCustom extends BiomeDecorator {

	public BiomeDecoratorCustom(BiomeGenBase par1BiomeGenBase) {
		super(par1BiomeGenBase);
	}

	public void setTreesPerChunk(int per1) {
		treesPerChunk = per1;
	}

	public void setGrassPerChunk(int per1) {
		grassPerChunk = per1;
	}

	public void setFlowersPerChunk(int per1) {
		flowersPerChunk = per1;
	}

	public void setSandPerChunk(int per1) {
		sandPerChunk = per1;
	}

	public void setClayPerChunk(int per1) {
		clayPerChunk = per1;
	}

}
