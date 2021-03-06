package riseautomatons.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.item.Items;

public class BlockBeacon extends BlockContainer {

	protected BlockBeacon() {
		super(Material.circuits);
		setBlockBounds(6f / 16f, 0.0F, 6f / 16f, 10f / 16f, 0.875f, 10f / 16f);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int par2) {
		return new TileEntityBeacon();
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		int ii = BeaconManager.addBeacon(world, i, j, k);
		TileEntityBeacon beacon = (TileEntityBeacon) world
				.getTileEntity(i, j, k);
		beacon.numeral = ii;
	}
/*
	@Override
	public IIcon getBlockTextureFromSideAndMetadata(int i, int j) {
		if (j == 0) {
			return 64;
		} else if (j == 1) {
			return 177;
		}
		return 129;
	}
*/
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j,
			int k, int l) {
		TileEntityBeacon beacon = (TileEntityBeacon) world
				.getTileEntity(i, j, k);
		if(beacon != null) {
			BeaconManager.removeBeacon(world, beacon.numeral);
		}
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Ids.itemBeacon;
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j,
			int k, EntityPlayer entityPlayer, int par6, float par7,
			float par8, float par9) {
		select(world, i, j, k, entityPlayer);
		return false;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k,
			EntityLivingBase entity, ItemStack itemstack) {
		if (entity instanceof EntityPlayer) {
			select(world, i, j, k, (EntityPlayer) entity);
		}
	}

	void select(World world, int i, int j, int k, EntityPlayer ep) {
		TileEntityBeacon beacon = (TileEntityBeacon) world
				.getTileEntity(i, j, k);
		BeaconManager.select(ep, beacon.numeral);
		ItemStack is = ep.inventory.getCurrentItem();
		if (is != null && is.getItem() == Items.stick) {
			beacon.mode = 2;
			world.setBlockMetadataWithNotify(i, j, k, 2, 3);
			world.markBlockForUpdate(i, j, k);
		} else if (is != null && is.getItem() == Items.compass) {
			// beacon.mode=2;
			// world.setBlockMetadataWithNotify(i, j, k, 2);
			// world.markBlockAsNeedsUpdate(i, j, k);
			BeaconManager.alertPlayerOnBots(world, ep);
		} else if (beacon.mode != 0) {
			beacon.mode = 0;
			world.setBlockMetadataWithNotify(i, j, k, 0, 3);
			world.markBlockForUpdate(i, j, k);
		} else {
			beacon.mode = 1;
			world.setBlockMetadataWithNotify(i, j, k, 1, 3);
			world.markBlockForUpdate(i, j, k);
		}
	}
}
