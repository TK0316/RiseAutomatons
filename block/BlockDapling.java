package riseautomatons.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.world.WorldGenFakeTrees;

public class BlockDapling extends Block {

	protected BlockDapling() {
		super(Material.plants);
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
		world.scheduleBlockUpdate(i, j, k, this, 3);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		Block bbb = world.getBlock(i, j - 1, k);
		return world.getBlock(i, j, k).isAir(world, i, j, k)
				&& (bbb == Ids.blockArch || bbb == Ids.blockArchBend || bbb == Ids.blockFrass);
	}

	public void growTree(World world, int i, int j, int k, Random random) {
		// int l = world.getBlockMetadata(i, j, k) & 3;
		world.setBlockToAir(i, j, k);
		WorldGenFakeTrees obj = new WorldGenFakeTrees(false);

		if (!obj.generate(world, random, i, j, k)) {
			world.setBlock(i, j, k, this, 0, 3);
		}
	}
}
