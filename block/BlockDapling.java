package riseautomatons.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.world.WorldGenFakeTrees;

public class BlockDapling extends Block {

	protected BlockDapling(int i) {
		super(i, Material.plants);
		setTickRandomly(true);
		float f = 0.4F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
	}
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World,
			int par2, int par3, int par4) {
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void updateTick(World world, int i, int j, int k,
			Random random) {

		/*if (Universal.improperWorld(world)) {
			return;
		}*/

		if (random.nextInt(5) == 0) {
			growTree(world, i, j, k, random);
		}
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		world.scheduleBlockUpdate(i, j, k, blockID, 3);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		int bbb = world.getBlockId(i, j - 1, k);
		return world.getBlockId(i, j, k) == 0
				&& (bbb == Ids.blockArch || bbb == Ids.blockArchBend || bbb == Ids.blockFrass);
	}

	public void growTree(World world, int i, int j, int k, Random random) {
		// int l = world.getBlockMetadata(i, j, k) & 3;
		world.setBlock(i, j, k, 0, 0, 3);
		WorldGenFakeTrees obj = new WorldGenFakeTrees(false);

		if (!obj.generate(world, random, i, j, k)) {
			world.setBlock(i, j, k, blockID, 0, 3);
		}
	}
}
