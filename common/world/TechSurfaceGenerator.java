package riseautomatons.common.world;

import java.util.Random;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import cpw.mods.fml.common.IWorldGenerator;

public class TechSurfaceGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
		case -1:
			this.generateNether(world, random, chunkX << 4, chunkZ << 4);
			break;

		case 0:
			this.generateSurface(world, random, chunkX << 4, chunkZ << 4);
			break;

		default:
		}
	}

	private void generateSurface(World world, Random random, int chunkX,
			int chunkZ) {
		System.out.println(world.getBiomeGenForCoords(chunkX, chunkZ).biomeName);
		if(world.getBiomeGenForCoords(chunkX, chunkZ).biomeName.equals(Biomes.tech.biomeName)) {
			int randPosX = chunkX;
			int randPosY = 63;
			int randPosZ = chunkZ;
			(new WorldGenTechSurface()).generate(world, random,
					randPosX , randPosY, randPosZ);
		}
	}

	private void generateNether(World world, Random random, int chunkX,
			int chunkZ) {
	}
}
