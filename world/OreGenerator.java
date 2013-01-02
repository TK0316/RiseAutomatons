package riseautomatons.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import riseautomatons.Ids;
import cpw.mods.fml.common.IWorldGenerator;

public class OreGenerator implements IWorldGenerator {

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
		for (int i = 0; i < 8; i++) {
			int randPosX = chunkX + random.nextInt(16);
			int randPosY = random.nextInt(16) + 48;
			int randPosZ = chunkZ + random.nextInt(16);
			(new WorldGenMinable(Ids.saltOre, 9)).generate(world, random,
					randPosX, randPosY, randPosZ);
		}

		for (int i = 0; i < 4; i++) {
			int randPosX = chunkX + random.nextInt(16);
			int randPosY = random.nextInt(36);
			int randPosZ = chunkZ + random.nextInt(16);
			(new WorldGenMinable(Ids.sulfOre, 5)).generate(world, random,
					randPosX, randPosY, randPosZ);
		}
	}

	private void generateNether(World world, Random random, int chunkX,
			int chunkZ) {
	}

}
