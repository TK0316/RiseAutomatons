package riseautomatons.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import riseautomatons.block.Blocks;

public class ItemChalk extends Item {

	public ItemChalk(int par1) {
		super(par1);
		this.setMaxDamage(1000);
		this.setMaxStackSize(1);
	}

	@Override
	public String getTextureFile() {
		return Items.ITEMS_PNG;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer,
			World world, int i, int j, int k, int l, float par8, float par9,
			float par10) {

		int id = world.getBlockId(i, j, k);

		if (id == Blocks.chalk.blockID) {
			int meta = world.getBlockMetadata(i, j, k);

			if (meta < 7) {
				world.setBlockMetadataWithNotify(i, j, k, meta + 1); // zei_Ids.chalk,
				world.markBlockForUpdate(i, j, k);
				itemstack.damageItem(1, entityplayer);
				return true;
			}
		}

		if (id != Block.snow.blockID) {
			if (l == 0) {
				j--;
			}

			if (l == 1) {
				j++;
			}

			if (l == 2) {
				k--;
			}

			if (l == 3) {
				k++;
			}

			if (l == 4) {
				i--;
			}

			if (l == 5) {
				i++;
			}

			id = world.getBlockId(i, j, k);
			if (!world.isAirBlock(i, j, k) && world.getBlockId(i, j, k) != Blocks.chalk.blockID) {
				return false;
			}
		}

		if (!entityplayer.canPlayerEdit(i, j, k, l, null)) {
			return false;
		}

		if (Blocks.chalk.canPlaceBlockAt(world, i, j, k)) {
			// itemstack.stackSize--;
			itemstack.damageItem(1, entityplayer);
			int meta = (id == Blocks.chalk.blockID) ? world.getBlockMetadata(i, j, k) : 0;
			if (meta < 7) {
				meta++;
			}
			world.setBlockAndMetadataWithNotify(i, j, k, Blocks.chalk.blockID, meta);
		}

		return true;
	}
}
