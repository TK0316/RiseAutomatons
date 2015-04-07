package riseautomatons.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import riseautomatons.Ids;
import static net.minecraft.init.Blocks.air;
import static net.minecraft.init.Blocks.double_stone_slab;
import static net.minecraft.init.Blocks.stone_slab;
import static net.minecraft.init.Blocks.cobblestone;
import static net.minecraft.init.Blocks.mossy_cobblestone;
import static net.minecraft.init.Blocks.stone_pressure_plate;
import static net.minecraft.init.Blocks.flowing_water;
import static net.minecraft.init.Blocks.water;
import static net.minecraft.init.Blocks.stone;
import static net.minecraft.init.Blocks.redstone_torch;

public class WorldGenPool extends WorldGenerator {

	Block xyz1[][][]={{
	{double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab},
	{double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab},
	{double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab},
	{double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab},
	{double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab}},
	{
	{double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab},
	{double_stone_slab,water,water,water,double_stone_slab},
	{double_stone_slab,water,double_stone_slab,water,double_stone_slab},
	{double_stone_slab,water,water,water,double_stone_slab},
	{double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab,double_stone_slab}},
	{
	{air,stone_slab,stone_slab,stone_slab,air},
	{stone_slab,air,air,air,stone_slab},
	{stone_slab,air,stone,air,stone_slab},
	{stone_slab,air,air,air,stone_slab},
	{air,stone_slab,stone_slab,stone_slab,air}},
	{
	{air,air,air,air,air},
	{air,air,air,air,air},
	{air,air,flowing_water,air,air},
	{air,air,air,air,air},
	{air,air,air,air,air}}};


	Block S=Ids.blockGearbox; //deployer
	Block t=Ids.blockTech;
	Block xyz2[][][]={{
	{cobblestone,cobblestone,cobblestone,mossy_cobblestone,cobblestone},
	{mossy_cobblestone,cobblestone,cobblestone,mossy_cobblestone,cobblestone},
	{cobblestone,mossy_cobblestone,cobblestone,cobblestone,cobblestone},
	{cobblestone,cobblestone,cobblestone,cobblestone,mossy_cobblestone},
	{cobblestone,cobblestone,cobblestone,cobblestone,cobblestone}},
	{
	{t,t,t,t,t},
	{t,cobblestone,cobblestone,mossy_cobblestone,t},
	{t,cobblestone,redstone_torch,cobblestone,t},
	{t,cobblestone,mossy_cobblestone,cobblestone,t},
	{t,t,t,t,t}},
	{
	{air,air,air,air,air},
	{air,stone_pressure_plate,stone_pressure_plate,stone_pressure_plate,air},
	{air,stone_pressure_plate,S,stone_pressure_plate,air},
	{air,stone_pressure_plate,stone_pressure_plate,stone_pressure_plate,air},
	{air,air,air,air,air}}
	};

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {

		Block xyz[][][];
		boolean bb = false;
		if (random.nextInt(3) == 0) {
			xyz = xyz1;
		} else {
			xyz = xyz2;
			bb = true;
		}

		int tall = xyz.length;
		int wid = xyz[0].length;
		int len = xyz[0][0].length;
		int w2 = wid / 2;
		int l2 = len / 2;
		int h2 = tall / 2;
		for (int x = 0; x < wid; x++) {
			for (int y = 0; y < tall; y++) {
				for (int z = 0; z < len; z++) {
					world.setBlock(i + x - w2, j + y - h2, k + z - l2,
							xyz[y][x][z], 0, 3);
					// if(xyz[y][x][z]==AutomatonLogger.deployer){
					// System.out.println((i+x-w2)+","+(j+y-h2)+","+(k+z-l2));
					// }
				}
			}
		}
		if (bb) {
			world.setBlockMetadataWithNotify(i, j + 1, k, 1, 3);
			// System.out.println((i)+","+(j+1)+","+(k));
		}

		return true;
	}
}
