package riseautomatons.common.item;

import java.util.ArrayList;
import java.util.Random;

import riseautomatons.common.Ids;
import riseautomatons.common.block.BlockFrass;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class AutomatonActions
{
  static int[][] nerp;
  static int[][] nerk;
  static int M = 5;
  static int H = 3;

  public static int max = 88;
  public static int[] blocks = new int[max];


  private static int getType(int i) {
	  switch(i) {
	  case 82:
		  return 1;
	  case 12:
		  return 2;
	  case 9:
		  return 3;
	  case 8:
	  case 79:
		  return 4;
	  case 2:
	  case 3:
	  case 4:
		  return 5;
	  case 39:
	  case 40:
	  case 83:
	  case 81:
	  case 86:
	  case 59:
		  return 6;
	  case 17:
	  case 18:
		  return 7;
	  case 37:
	  case 38:
	  case 6:
	  case 31:
	  case 32:
		  return 8;
	  case 60:
		  return 9;
	  case 78:
		  return 10;
	  default:
		  return 0;
	  }

  }

  public static void Frassify(World world, EntityPlayer entityplayer)
  {
    int i = MathHelper.floor_double(entityplayer.posX);
    int j = MathHelper.floor_double(entityplayer.posY);
    int k = MathHelper.floor_double(entityplayer.posZ);
    Frassify(world, i, j, k);
  }

  public static void Frassify(World world, int i, int j, int k)
  {
    for (int l = 0; l < nerp.length; l++)
    {
      int j1 = world.getBlockId(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2]);
      if ((j1 <= 0) || (j1 >= max))
      {
        continue;
      }
      int k1 = getType(j1);
      if (k1 <= 0)
        continue;
      int l1 = i + nerp[l][0];
      int i2 = j + nerp[l][1];
      int j2 = k + nerp[l][2];
      switch (k1)
      {
      case 1:
        world.setBlockWithNotify(l1, i2, j2, Ids.blockFrass);
      case 2:
        world.setBlockAndMetadataWithNotify(l1, i2, j2, Ids.blockFrass, 2);
        break;
      case 3:
        world.setBlockAndMetadataWithNotify(l1, i2, j2, Ids.blockFrass, 0);
        break;
      case 4:
        world.setBlockAndMetadataWithNotify(l1, i2, j2, Ids.blockFrass, 1);
        break;
      case 5:
        world.setBlockAndMetadataWithNotify(l1, i2, j2, Ids.blockFrass, 3);
        break;
      case 6:
        world.setBlockWithNotify(l1, i2, j2, Ids.blockGrower);
        break;
      case 7:
        world.setBlockAndMetadataWithNotify(l1, i2, j2, j1 != 17 ? Ids.blockCrink : Ids.blockTech, j1 != 17 ? 0 : 1);
        break;
      case 8:
        world.setBlockWithNotify(l1, i2, j2, Ids.blockDapling);
        break;
      case 9:
        world.setBlockAndMetadataWithNotify(l1, i2, j2, Ids.blockFrass, 1);
        break;
      case 10:
        world.setBlockWithNotify(l1, i2, j2, 0);
      }

    }

    int i1 = M - 1;
    world.markBlocksDirtyVertical(i - i1, j - i1, k - i1, i + i1);
  }

  public static void Blockify(int i, World world, EntityPlayer entityplayer)
  {
    int j = MathHelper.floor_double(entityplayer.posX);
    int k = MathHelper.floor_double(entityplayer.posY);
    int l = MathHelper.floor_double(entityplayer.posZ);
    Blockify(i, world, j, k, l);
  }

  public static void Blockify(int i, World world, int j, int k, int l)
  {
    for (int i1 = 0; i1 < nerp.length; i1++)
    {
      if (world.getBlockId(j + nerp[i1][0], k + nerp[i1][1], l + nerp[i1][2]) == 0)
        continue;
      world.setBlockWithNotify(j + nerp[i1][0], k + nerp[i1][1], l + nerp[i1][2], i);
    }
  }

  public static void Naturalization(World world, EntityPlayer entityplayer)
  {
    int i = MathHelper.floor_double(entityplayer.posX);
    int j = MathHelper.floor_double(entityplayer.posY);
    int k = MathHelper.floor_double(entityplayer.posZ);
    Naturalization(world, i, j, k);
  }

  public static void Naturalization(World world, int i, int j, int k)
  {
    for (int l = 0; l < nerp.length; l++)
    {
      int j1 = world.getBlockId(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2]);
      int k1 = world.getBlockMetadata(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2]);
      if (j1 > 96)
      {
        if ((j1 == 3) || ((j1 == Ids.blockFrass) && ((k1 == 0) || (k1 == 3))))
        {
          world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], 0);
          world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], 2);
        }
        else if ((j1 == Ids.blockFrass))
        {
          world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], 0);
        }
        else if (j1 == Ids.blockCrink)
        {
          world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], 18);
        }
        else if ((j1 == Ids.blockTech) && (k1 == 1))
        {
          world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], 17);
        }
        else {
          if (j1 != Ids.blockTech)
            continue;
          world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], 4);
        }
      }
      else {
        if ((j1 != Ids.blockGrower))
        {
          continue;
        }
        int l1 = world.rand.nextInt(4);
        switch (l1)
        {
        case 0:
          world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.sapling.blockID);
          break;
        case 1:
          world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.plantYellow.blockID);
          break;
        case 2:
          world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.plantRed.blockID);
          break;
        default:
          world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.reed.blockID);
        }
      }
    }

    int i1 = M - 1;
    world.markBlocksDirtyVertical(i - i1, j - i1, k - i1, i + i1);
  }

}