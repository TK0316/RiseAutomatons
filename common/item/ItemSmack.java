package riseautomatons.common.item;

import java.util.List;

import riseautomatons.common.Universal;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class ItemSmack extends Item {

	public ItemSmack(int i) {
		super(i);
		setMaxDamage(25);
		this.maxStackSize = 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world,
			EntityPlayer entityplayer) {

		itemstack.damageItem(10, entityplayer);
		List list = world.getEntitiesWithinAABBExcludingEntity(entityplayer,
				entityplayer.boundingBox.expand(6.0D, 3.0D, 6.0D));
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				if (!(obj instanceof EntityLiving))
					continue;
				POW((EntityLiving) obj, entityplayer);
			}

		}

		return itemstack;	}

	@Override
	public boolean hitEntity(ItemStack itemstack,
			EntityLiving entityliving, EntityLiving entityliving1) {

		itemstack.damageItem(1, entityliving1);
		POW(entityliving, entityliving1);
		return true;
	}

	public void POW(EntityLiving entityliving, EntityLiving entityliving1) {
		World world = entityliving1.worldObj;
		world.playSoundAtEntity(entityliving1, "mob.clank", 1.0F, 1.0F);
		if (Universal.improperWorld(world)) {
		//	return;
		}
		if ((entityliving.riddenByEntity == entityliving1)
				|| (entityliving.ridingEntity == entityliving1)) {
			return;
		}
		double d = entityliving.posX - entityliving1.posX;
		double d1 = entityliving.posZ - entityliving1.posZ;
		double d2 = d1;
		double d3 = d;
		double d4 = MathHelper.abs_max(d, d1);
		if (d4 >= 0.009999999776482582D) {
			d4 = MathHelper.sqrt_double(d4);
			d /= d4;
			d1 /= d4;
			double d5 = 1.0D / d4;
			if (d5 > 1.0D) {
				d5 = 1.0D;
			}
			d *= d5;
			d1 *= d5;
			d *= 0.0500000007450581D;
			d1 *= 0.0500000007450581D;
			d *= 1.0D;
			d1 *= 1.0D;
			entityliving1.addVelocity(-d, 0.0D, -d1);
			entityliving.addVelocity(d3, 0.75D, d2);
		}
	}

}
