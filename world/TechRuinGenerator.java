package riseautomatons.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class TechRuinGenerator implements IWorldGenerator {

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
	}

	private void generateNether(World world, Random random, int chunkX,
			int chunkZ) {
	}

}
