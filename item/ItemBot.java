package riseautomatons.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.Universal;
import riseautomatons.entity.EntityFactotum;
import riseautomatons.entity.EntityGuard;
import riseautomatons.entity.EntityOmni;


public class ItemBot extends Item {

	private EnumBotType type;

	public ItemBot(int i, EnumBotType type) {
		super(i);
		this.type = type;
		if(type == EnumBotType.TOTE) {
			maxStackSize = 8;
		}
	}

	@Override
	public boolean onItemUse(ItemStack itemstack,
			EntityPlayer entityplayer, World world, int i, int j,
			int k, int l, float par8, float par9, float par10) {

		if (world.getBlockId(i, j, k) != Block.snow.blockID) {
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

			if (!world.isAirBlock(i, j, k)
					|| !world.getBlockMaterial(i, j - 1, k).isSolid()) {
				return false;
			}
		}

		if (!entityplayer.canPlayerEdit(i, j, k, l, itemstack)) {
			return false;
		}
		int meta = MathHelper
				.floor_double((double) (entityplayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		switch(type) {
		case WORKER:
			world.setBlockAndMetadataWithNotify(i, j, k, Ids.blockWorker, meta);
			break;
		case SENTRY:
			world.setBlockAndMetadataWithNotify(i, j, k, Ids.blockSentry, meta);
			break;
		case FACTOTUM:
			if (!Universal.improperWorld(world)) {
				world.spawnEntityInWorld(new EntityFactotum(world, (double)i + 0.5, (double)j + 0.5, (double)k + 0.5, entityplayer.username));
			}
			break;
		case BEACON:
			if (!Universal.improperWorld(world)) {
				world.setBlockWithNotify(i, j, k, Ids.blockBeacon);
			}
			break;
		case GUARD:
			if (!Universal.improperWorld(world)) {
				world.spawnEntityInWorld(new EntityGuard(world, (float)i + 0.5F, (float)j, (float)k + 0.5F));
			}
			break;
		case OMNI:
			if (!Universal.improperWorld(world)) {
				world.spawnEntityInWorld(new EntityOmni(world, (float)i + 0.5F, (float)j, (float)k + 0.5F));
			}
			break;
		case TOTE:
			if (!Universal.improperWorld(world)) {
				world.setBlockWithNotify(i, j, k, Ids.blockTote);
			}
			break;
		default:
			return false;
		}

		itemstack.stackSize--;
		return true;
	}
}
