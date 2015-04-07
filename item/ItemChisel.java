package riseautomatons.item;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import riseautomatons.Ids;

public class ItemChisel extends Item {
	public ItemChisel() {
		super();
		setMaxDamage(63);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer,
			World world, int i, int j, int k, int l, float par8, float par9,
			float par10) {

		if (l != 0) {
			Block block = world.getBlock(i, j, k);

			if (block == Blocks.stone) {
				if (!world.isAirBlock(i, j + 1, k)) {
					world.setBlock(i, j, k, Blocks.cobblestone, 0, 3);
					itemstack.damageItem(4, entityplayer);
				} else {
					world.setBlock(i, j, k, Ids.blockSlab,
							1, 3);
					if (!world.isRemote)
						world.spawnEntityInWorld(new EntityItem(world,
								i + 0.5f, j + 1, k + 0.5f, new ItemStack(
										Ids.craftSet, 1, EnumCraftSetType.SMALLPLATE.ordinal())));

					itemstack.damageItem(1, entityplayer);
				}

				return true;
			} else if (block == Ids.blockSlab) {
				int meta = world.getBlockMetadata(i, j, k);

				if (meta < 15) {
					world.setBlock(i, j, k, Ids.blockSlab,
							meta + 1, 3); // Ids.chalk,
				} else {
					world.setBlockToAir(i, j, k);
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
