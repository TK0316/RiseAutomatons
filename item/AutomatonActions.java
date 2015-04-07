package riseautomatons.item;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.block.Blocks;

public class AutomatonActions {
    static int[][] nerp;
    static int[][] nerk;
    static int M = 5;
    static int H = 3;

    public static int max = 88;
    public static int[] blocks = new int[max];

    static {
        sphereGen(M);
    }

    private static int getType(Block block) {
        if (block == Blocks.sand) {
            return 1;
        } else if (block == Blocks.grass || block == Blocks.dirt) {
            return 2;
        } else if (block == Blocks.clay) {
            return 3;
        } else if (block == Blocks.flowing_water || block == Blocks.water || block == Blocks.ice) {
            return 4;
        } else if (block == Blocks.brown_mushroom || block == Blocks.red_mushroom || block == Blocks.wheat || block == Blocks.cactus || block == Blocks.reeds || block == Blocks.pumpkin) {
            return 5;
        } else if (block == Blocks.log || block == Blocks.leaves) {
            return 6;
        } else if (block == Blocks.sapling || block == Blocks.tallgrass || block == Blocks.deadbush || block == Blocks.yellow_flower || block == Blocks.red_flower) {
            return 7;
        } else if (block == Blocks.snow) {
            return 8;
        }
        return 0;
    }

    public static void Frassify(World world, EntityPlayer entityplayer, int color) {
        int i = MathHelper.floor_double(entityplayer.posX);
        int j = MathHelper.floor_double(entityplayer.posY);
        int k = MathHelper.floor_double(entityplayer.posZ);
        Frassify(world, i, j, k, color);
    }

    public static void Frassify(World world, int i, int j, int k, int color) {
        for (int l = 0; l < nerp.length; l++) {
            if (world.isAirBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2])) {
                continue;
            }

            Block j1 = world.getBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2]);
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
                    world.setBlock(l1, i2, j2, j1 != Blocks.log ? Ids.blockCrink : Ids.blockTech, j1 != Blocks.log ? color : 1, 3);
                    break;
                case 7:
                    world.setBlock(l1, i2, j2, Ids.blockDapling, 0, 3);
                    break;
                default:
                    world.setBlockToAir(l1, i2, j2);
            }
        }

        int i1 = M - 1;
        world.markBlocksDirtyVertical(i - i1, j - i1, k - i1, i + i1);
    }


    public static void Naturalization(World world, EntityPlayer entityplayer) {
        int i = MathHelper.floor_double(entityplayer.posX);
        int j = MathHelper.floor_double(entityplayer.posY);
        int k = MathHelper.floor_double(entityplayer.posZ);
        Naturalization(world, i, j, k);
    }

    public static void Naturalization(World world, int i, int j, int k) {
        for (int l = 0; l < nerp.length; l++) {
            Block block = world.getBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2]);
            int metadata = world.getBlockMetadata(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2]);
            if (block == Ids.blockFrass) {
                if (metadata == 0) {
                    world.setBlockToAir(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2]);
                } else if (metadata == 1) {
                    world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Blocks.sand, 0, 3);
                } else if (metadata == 2) {
                    world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Blocks.dirt, 0, 3);
                } else if (metadata == 3) {
                    world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Blocks.clay, 0, 3);
                } else if (metadata == 4) {
                    world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Blocks.water, 0, 3);
                } else {
                    world.setBlockToAir(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2]);
                }
            } else if (block == Ids.blockCrink) {
                world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Blocks.leaves, 0, 3);
            } else if (block == Ids.blockTech) {
                if (metadata == 1) {
                    world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Blocks.log, 0, 3);
                } else {
                    world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Blocks.cobblestone, 0, 3);
                }
            } else if ((block == Ids.blockGrower)) {
                Block stayingBlock = world.getBlock(i + nerp[l][0], j + nerp[l][1] - 1, k + nerp[l][2]);
                if (world.isAirBlock(i + nerp[l][0], j + nerp[l][1] - 1, k + nerp[l][2]) || stayingBlock == Ids.blockGrower || stayingBlock == Blocks.sapling || stayingBlock == Blocks.yellow_flower || stayingBlock == Blocks.red_flower || stayingBlock == Blocks.reeds) {
                    world.setBlockToAir(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2]);
                } else {
                    int l1 = world.rand.nextInt(4);
                    switch (l1) {
                        case 0:
                            world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Blocks.sapling, 0, 3);
                            break;
                        case 1:
                            world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Blocks.yellow_flower, 0, 3);
                            break;
                        case 2:
                            world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Blocks.red_flower, 0, 3);
                            break;
                        default:
                            world.setBlock(i + nerp[l][0], j + nerp[l][1], k + nerp[l][2], Blocks.reeds, 0, 3);
                    }
                }
            }
        }

        int i1 = M - 1;
        world.markBlocksDirtyVertical(i - i1, j - i1, k - i1, i + i1);
    }

    public static int FValue(int i, int j, int k, int l) {
        return i * i + j * j + k * k - l * l;
    }

    public static void sphereGen(int i) {
        int j = i * 2 + 1;
        ArrayList arraylist = new ArrayList();
        for (int k = 0; k < j; k++) {
            for (int i1 = 0; i1 < j; i1++) {
                for (int k1 = 0; k1 < j; k1++) {
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
        for (int j1 = 0; j1 < l; j1++) {
            nerp[j1][0] = ((Integer) ((ArrayList) arraylist.get(j1)).get(0)).intValue();
            nerp[j1][1] = ((Integer) ((ArrayList) arraylist.get(j1)).get(1)).intValue();
            nerp[j1][2] = ((Integer) ((ArrayList) arraylist.get(j1)).get(2)).intValue();
        }
    }
}