package riseautomatons.common.item;

import riseautomatons.common.Ids;
import riseautomatons.common.block.Blocks;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

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

		if (id == Ids.blockChalk) {
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

			if (!world.isAirBlock(i, j, k)) {
				return false;
			}
		}

		if (!entityplayer.canPlayerEdit(i, j, k, l, null)) {
			return false;
		}

		if (Blocks.chalk.canPlaceBlockAt(world, i, j, k)) {
			// itemstack.stackSize--;
			itemstack.damageItem(1, entityplayer);
			world.setBlockAndMetadataWithNotify(i, j, k, Blocks.chalk.blockID, 1);
		}

		return true;
	}
}
