package riseautomatons.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import riseautomatons.Ids;
import riseautomatons.block.Blocks;

public class WorldGenTechRuin extends WorldGenerator {

	@Override
	public boolean generate(World world, Random random, int i, int j,
			int k) {
		System.out.println(String.valueOf(i) + "," + String.valueOf(j) + "," + String.valueOf(k));

	    byte byte0 = 5;
	    int width = 3;
	    int j1 = 0;
	    for (int x = i - 1; x <= i + width + 1; x++)
	    {
	      for (int z = k - 1; z <= k + width + 1; z++)
	      {
	        for (int y = j - 1; y <= j + byte0 + 1; y++)
	        {
	          Material material = world.getBlockMaterial(x, y, z);
	          if ((y == j - 1) && (!material.isSolid()))
	          {
	            int blockID = world.getBlockId(x, y, z);
	            if ((blockID != Block.dirt.blockID) && (blockID != Block.grass.blockID) && (blockID != Block.sand.blockID))
	            {
	              return false;
	            }
	          }
	          if ((y == j + byte0 + 1) && (material != Material.air))
	          {
	            return false;
	          }
	          if (((x != i - 1) && (x != i + width + 1) && (z != k - 1) && (z != k + width + 1)) || (y != j) || (!world.isAirBlock(x, y, z)) || (!world.isAirBlock(x, y + 1, z)))
	            continue;
	          j1++;
	        }
	      }

	    }

	    if (j1 >= 1);
	    System.out.println("ruin");
	    for (int x = i - 1; x < i + width + 1; x++)
	    {
	      for (int y = j + byte0; y >= j; y--)
	      {
	        for (int z = k - 1; z < k + width + 1; z++)
	        {
	          if (y == j) {
	            world.setBlockAndMetadataWithNotify(x, y, z, Ids.blockTech, 0);
	          }
	          else if (y == j + byte0) {
	            world.setBlockAndMetadataWithNotify(x, y, z, Block.stoneDoubleSlab.blockID, 0);
	          }
	          else if ((x == i - 1) || (z == k - 1) || (x == i + width) || (z == k + width))
	          {
	            world.setBlockAndMetadataWithNotify(x, y, z, Ids.blockTech, 0);
	          }
	          else
	          {
	            world.setBlockAndMetadataWithNotify(x, y, z, 0, 0);
	          }

	        }

	      }

	    }

	    int r = random.nextInt(100);

	    System.out.print(i);
	    System.out.print(",");
	    System.out.print(j);
	    System.out.print(",");
	    System.out.print(k);
	    System.out.print(",");
	    System.out.println(r);
	    if (r < 20) {
	      world.setBlockAndMetadataWithNotify(i + width / 2, j + 1, k + width / 2, Ids.blockSky, 1);
	    }
	    else if (r < 40) {
	      world.setBlockAndMetadataWithNotify(i + width / 2, j + 1, k + width / 2, Ids.blockSky, 0);
	    }
	    else if (r < 60) {
	      world.setBlockWithNotify(i + width / 2, j + 1, k + width / 2, Blocks.heal.blockID);
	    }
	    else if (r < 80) {
	      world.setBlockWithNotify(i + width / 2, j + 1, k + width / 2, Blocks.fakeCrystal.blockID);
	    }
	    else {
	      world.setBlockWithNotify(i + width / 2, j + 1, k + width / 2, Blocks.crystal.blockID);
	    }

	    return true;
	}

}
