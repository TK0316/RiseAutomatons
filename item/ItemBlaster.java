package riseautomatons.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import riseautomatons.entity.EntityLaser;

public class ItemBlaster extends Item {

	public ItemBlaster() {
		super();
		maxStackSize = 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		// if(entityplayer.inventory.consumeInventoryItem(Item.arrow.itemID))
		// {
        world.playSoundAtEntity(entityplayer, "riseautomatons:fwoom", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));

		//if (!Universal.improperWorld(world)) {
			// Entity targetedEntity=entity;
			double d8 = 4D;
			Vec3 vec3d = entityplayer.getLook(1.0F);
			/*
			 * double d5 = targetedEntity.posX - posX; double d6 =
			 * (targetedEntity.boundingBox.minY + (double)(targetedEntity.height
			 * / 2.0F)) - (posY + (double)(height )); double d7 =
			 * targetedEntity.posZ - posZ;
			 */
			EntityLaser entityfireball = new EntityLaser(world,entityplayer, vec3d.xCoord * d8, vec3d.yCoord * d8, vec3d.zCoord * d8, 0.2D);
			//entityfireball.posX = entityplayer.posX;// + vec3d.xCoord * d8;
			//entityfireball.posY = entityplayer.posY - 0.75D;
			//entityfireball.posZ = entityplayer.posZ;// + vec3d.zCoord * d8;
			if(world.isRemote == false) {
				world.spawnEntityInWorld(entityfireball);
			}
			// world.entityJoinedWorld(new EntityArrow(world, entityplayer));
		//}

		// }
		return itemstack;
	}

}
