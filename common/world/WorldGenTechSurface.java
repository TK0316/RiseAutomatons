package riseautomatons.common.world;

import java.util.Random;

import riseautomatons.common.Ids;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenTechSurface extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int var3, int var4,
			int var5) {
		for(int i = var3; i < var3 + 16; i++) {
			for(int k = var5; k < var5 + 16; k++) {
				for(int j = 255; j >= var4; --j) {
					int blockId = world.getBlockId(i, j, k);
					int meta = 0;
					if(blockId == Block.sand.blockID) {
						meta = 1;
					}
					else if(blockId == Block.grass.blockID) {
						meta = 2;
					}
					else if(blockId == Block.blockClay.blockID) {
						meta = 3;
					}
					else if(blockId == Block.dirt.blockID) {
						meta = 2;
					}
					else {
						continue;
					}
					world.setBlockAndMetadata(i, j, k, Ids.blockFrass, meta);
					break;
				}
			}
		}
		return true;
	}

}
