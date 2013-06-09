package riseautomatons.item;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import riseautomatons.Ids;
import riseautomatons.block.BlockFrass;

public class AutomatonActions
{
  static int[][] nerp;
  static int[][] nerk;
  static int M = 5;
  static int H = 3;

  public static int max = 88;
  public static int[] blocks = new int[max];

  static { sphereGen(M);
  }

  private static int getType(int i) {
	  if(i == Block.sand.blockID) {
		  return 1;
	  }
	  else if(i == Block.grass.blockID || i == Block.dirt.blockID) {
		  return 2;
	  }
	  else if(i == Block.blockClay.blockID) {
		  return 3;
	  }
	  else if(i == Block.waterMoving.blockID || i == Block.waterStill.blockID || i == Block.ice.blockID) {
		  return 4;
	  }
	  else if(i == Block.mushroomBrown.blockID || i == Block.mushroomRed.blockID || i == Block.crops.blockID || i == Block.cactus.blockID || i == Block.reed.blockID || i == Block.pumpkin.blockID) {
		  return 5;
	  }
	  else if(i == Block.wood.blockID || i == Block.leaves.blockID) {
		  return 6;
	  }
	  else if(i == Block.sapling.blockID || i == Block.tallGrass.blockID || i == Block.deadBush.blockID || i == Block.plantYellow.blockID || i == Block.plantRed.blockID) {
		  return 7;
	  }
	  else if(i == Block.snow.blockID) {
		  return 8;
	  }
	  return 0;
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
			switch (k1) {
			case 1:
				world.setBlock(l1, i2, j2, Ids.blockFrass, 1, 3);
				break;
			case 2:
				world.setBlock(l1, i2, j2, Ids.blockFrass, 2, 3);
				break;
			case 3:
				world.setBlock(l1, i2, j2, Ids.blockFrass, 3, 3);
				break;
			case 4:
				world.setBlock(l1, i2, j2, Ids.blockFrass, 4, 3);
				break;
			case 5:
				world.setBlock(l1, i2, j2, Ids.blockGrower, 0, 3);
				break;
			case 6:
				world.setBlock(l1, i2, j2, j1 != 17 ? Ids.blockCrink : Ids.blockTech, j1 != 17 ? 0 : 1, 3);
				break;
			case 7:
				world.setBlock(l1, i2, j2, Ids.blockDapling, 0, 3);
				break;
			default:
				world.setBlock(l1, i2, j2, 0, 0, 3);
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
      world.setBlock(j + nerp[i1][0], k + nerp[i1][1], l + nerp[i1][2], i, 0, 3);
    }
  }

  public static void Naturalization(World world, EntityPlayer entityplayer)
  {
    int i = MathHelper.floor_double(entityplayer.posX);
    int j = MathHelper.floor_double(entityplayer.posY);
    int k = MathHelper.floor_double(entityplayer.posZ);
    Naturalization(world, i, j, k);
  }

	public static void Naturalization(World world, int i, int j, int k) {
		for (int l = 0; l < nerp.length; l++) {
			int blockId = world.getBlockId(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2]);
			int metadata = world.getBlockMetadata(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2]);
			if (blockId == Ids.blockFrass) {
				if (metadata == 0) {
					world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], 0, 0, 3);
				} else if (metadata == 1) {
					world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.sand.blockID, 0, 3);
				} else if (metadata == 2) {
					world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.dirt.blockID, 0, 3);
				} else if (metadata == 3) {
					world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.blockClay.blockID, 0, 3);
				} else if (metadata == 4) {
					world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.waterStill.blockID, 0, 3);
				} else {
					world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], 0, 0, 3);
				}
			} else if (blockId == Ids.blockCrink) {
				world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.leaves.blockID, 0, 3);
			} else if (blockId == Ids.blockTech) {
				if (metadata == 1) {
					world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.wood.blockID, 0, 3);
				} else {
					world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.cobblestone.blockID, 0, 3);
				}
			} else if ((blockId == Ids.blockGrower)) {
				int stayingBlockId = world.getBlockId(i + nerp[l][0], j + nerp[l][1] - 1, k + nerp[l][2]);
				if(stayingBlockId == 0 || stayingBlockId == Ids.blockGrower || stayingBlockId == Block.sapling.blockID || stayingBlockId == Block.plantYellow.blockID || stayingBlockId == Block.plantRed.blockID || stayingBlockId == Block.reed.blockID) {
					world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], 0, 0, 3);
				}
				else {
					int l1 = world.rand.nextInt(4);
					switch (l1) {
					case 0:
						world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.sapling.blockID, 0, 3);
						break;
					case 1:
						world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.plantYellow.blockID, 0, 3);
						break;
					case 2:
						world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.plantRed.blockID, 0, 3);
						break;
					default:
						world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Block.reed.blockID, 0, 3);
					}
				}
			}
		}

    int i1 = M - 1;
    world.markBlocksDirtyVertical(i - i1, j - i1, k - i1, i + i1);
  }

  public static int FValue(int i, int j, int k, int l)
  {
    return i * i + j * j + k * k - l * l;
  }

  public static void sphereGen(int i)
  {
    int j = i * 2 + 1;
    ArrayList arraylist = new ArrayList();
    for (int k = 0; k < j; k++)
    {
      for (int i1 = 0; i1 < j; i1++)
      {
        for (int k1 = 0; k1 < j; k1++)
        {
          if (FValue(i1 - i, k - i, k1 - i, i) > 0)
            continue;
          ArrayList arraylist1 = new ArrayList();
          arraylist1.add(Integer.valueOf(i1 - i));
          arraylist1.add(Integer.valueOf(k - i));
          arraylist1.add(Integer.valueOf(k1 - i));
          arraylist.add(arraylist1);
        }

      }

    }

    int l = arraylist.size();
    nerp = new int[l][3];
    for (int j1 = 0; j1 < l; j1++)
    {
      nerp[j1][0] = ((Integer)((ArrayList)arraylist.get(j1)).get(0)).intValue();
      nerp[j1][1] = ((Integer)((ArrayList)arraylist.get(j1)).get(1)).intValue();
      nerp[j1][2] = ((Integer)((ArrayList)arraylist.get(j1)).get(2)).intValue();
    }
  }

}