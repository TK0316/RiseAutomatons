package riseautomatons.common.world;

import java.util.Random;

import riseautomatons.common.Ids;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenTechRuin extends WorldGenerator {

	@Override
	public boolean generate(World world, Random random, int i, int j,
			int k) {
		System.out.println(String.valueOf(i) + "," + String.valueOf(j) + "," + String.valueOf(k));

	    byte byte0 = 5;
	    int l = 3;
	    int i1 = 3;
	    int j1 = 0;
	    for (int k1 = i - 1; k1 <= i + l + 1; k1++)
	    {
	      for (int i3 = k - 1; i3 <= k + i1 + 1; i3++)
	      {
	        for (int j2 = j - 1; j2 <= j + byte0 + 1; j2++)
	        {
	          Material material = world.getBlockMaterial(k1, j2, i3);
	          if ((j2 == j - 1) && (!material.isSolid()))
	          {
	            int blockID = world.getBlockId(k1, j2, i3);
	            if ((blockID != Block.dirt.blockID) && (blockID != Block.grass.blockID) && (blockID != Block.sand.blockID))
	            {
	              return false;
	            }
	          }
	          if ((j2 == j + byte0 + 1) && (material != Material.air))
	          {
	            return false;
	          }
	          if (((k1 != i - 1) && (k1 != i + l + 1) && (i3 != k - 1) && (i3 != k + i1 + 1)) || (j2 != j) || (!world.isAirBlock(k1, j2, i3)) || (!world.isAirBlock(k1, j2 + 1, i3)))
	            continue;
	          j1++;
	        }
	      }

	    }

	    if (j1 >= 1);
	    System.out.println("ruin");
	    for (int l1 = i - 1; l1 <= i + l + 1; l1++)
	    {
	      for (int k2 = j + byte0; k2 >= j; k2--)
	      {
	        for (int j3 = k - 1; j3 <= k + i1 + 1; j3++)
	        {
	          if (k2 == j) {
	            world.setBlockAndMetadataWithNotify(l1, k2, j3, Ids.blockTech, 0);
	          }
	          else if (k2 == j + byte0) {
	            world.setBlockAndMetadataWithNotify(l1, k2, j3, Block.stoneDoubleSlab.blockID, 0);
	          }
	          else if ((l1 == i - 1) || (j3 == k - 1) || (l1 == i + l + 1) || (j3 == k + i1 + 1))
	          {
	            world.setBlockAndMetadataWithNotify(l1, k2, j3, Ids.blockTech, 0);
	          }
	          else
	          {
	            world.setBlockAndMetadataWithNotify(l1, k2, j3, 0, 0);
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
	      world.setBlockAndMetadataWithNotify(i, j + 1, k, Ids.blockSky, 1);
	    }
	    else if (r < 40) {
	      world.setBlockAndMetadataWithNotify(i, j + 1, k, Ids.blockSky, 0);
	    }/*
	    else if (r < 60) {
	      world.setBlockWithNotify(i, j + 1, k, Blocks.heal.blockID);
	    }
	    else if (r < 80) {
	      world.setBlockWithNotify(i, j + 1, k, Blocks.fakeCrystal.blockID);
	    }
	    else {
	      world.setBlockWithNotify(i, j + 1, k, Blocks.crystal.blockID);
	    }*/

	    return true;
	}

}
