package riseautomatons.common.item;

import riseautomatons.common.CommonProxy;
import riseautomatons.common.Ids;
import net.minecraft.src.Block;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemChisel extends Item {
	public ItemChisel(int i) {
		super(i);
		setMaxDamage(63);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer,
			World world, int i, int j, int k, int l, float par8, float par9,
			float par10) {

		if (l != 0) {
			int id = world.getBlockId(i, j, k);

			if (id == 1) {
				if (!world.isAirBlock(i, j + 1, k)) {
					world.setBlockWithNotify(i, j, k, 4);
					itemstack.damageItem(4, entityplayer);
				} else {
					world.setBlockAndMetadataWithNotify(i, j, k, Ids.blockSlab,
							1);
					if (!world.isRemote)
						world.spawnEntityInWorld(new EntityItem(world,
								i + 0.5f, j + 1, k + 0.5f, new ItemStack(
										Ids.craftSet, 1, EnumCraftSetType.SMALLPLATE.ordinal())));

					itemstack.damageItem(1, entityplayer);
				}

				return true;
			} else if (id == Ids.blockSlab) {
				int meta = world.getBlockMetadata(i, j, k);

				if (meta < 15) {
					world.setBlockAndMetadataWithNotify(i, j, k, Ids.blockSlab,
							meta + 1); // Ids.chalk,
				} else {
					world.setBlockWithNotify(i, j, k, 0);
				}

				if (!world.isRemote)
					world.spawnEntityInWorld(new EntityItem(world, i + 0.5f,
							j + 1, k + 0.5f, new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SMALLPLATE.ordinal())));
				itemstack.damageItem(1, entityplayer);
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
		return false;
	}

}
