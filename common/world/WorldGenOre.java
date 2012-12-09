package riseautomatons.common.world;

import java.util.Random;

import riseautomatons.common.Ids;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenOre implements IWorldGenerator {

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

	private void generateSurface(World world, Random rand, int chunkX,
			int chunkZ) {
		for (int i = 0; i < 8; i++) {
			int randPosX = chunkX << 4 + rand.nextInt(16);
			int randPosY = rand.nextInt(16) + 48;
			int randPosZ = chunkZ << 4 + rand.nextInt(16);
			(new WorldGenMinable(Ids.saltOre, 9)).generate(world, rand,
					randPosX, randPosY, randPosZ);
		}

		for (int i = 0; i < 4; i++) {
			int randPosX = chunkX << 4 + rand.nextInt(16);
			int randPosY = rand.nextInt(36);
			int randPosZ = chunkZ << 4 + rand.nextInt(16);
			(new WorldGenMinable(Ids.sulfOre, 5)).generate(world, rand,
					randPosX, randPosY, randPosZ);
		}
	}

	private void generateNether(World world, Random random, int chunkX,
			int chunkZ) {
	}

}
