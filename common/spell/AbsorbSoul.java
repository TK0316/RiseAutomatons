package riseautomatons.common.spell;

import java.util.List;

import riseautomatons.common.Ids;
import riseautomatons.common.Universal;
import riseautomatons.common.entity.IBot;
import riseautomatons.common.item.EnumSoulCore;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityDragon;
import net.minecraft.src.EntityEnderman;
import net.minecraft.src.EntityGolem;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPigZombie;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntityTameable;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class AbsorbSoul extends Spell {

	@Override
	public boolean cast(World world, int i, int j, int k, EntityPlayer entityPlayer) {
		if (world.isRemote) {
			return false;
		}
		List L = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(i - 2, j - 2, k - 2, i + 2, j + 2, k + 2));
		EntityItem targetItem = null;

		if (!L.isEmpty()){
			for (int n = 0; n < L.size(); n++){
				EntityItem e = (EntityItem)L.get(n);
				if (e.item.itemID == Ids.soulCore && e.item.getItemDamage() == 4){
					targetItem = e;
					break;
				}
			}
		}
		else{
			return false;
		}
		if (targetItem == null){
			return false;
		}
		L = world.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(i - 2, j - 2, k - 2, i + 2, j + 2, k + 2));
		boolean complete=false;
		if (!L.isEmpty()){
			for(int n=0;n<L.size();n++){
				EntityLiving el = (EntityLiving) L.get(n);
				if(!complete){
					System.out.println();
					if(el instanceof EntityPlayer || el instanceof IBot || el instanceof EntityTameable || el instanceof EntityDragon || el instanceof EntityGolem){
						System.out.println(el.getEntityName() + " is not target type");

					}else if(el instanceof EntityAnimal){
						if (!(el instanceof EntityChicken)){
							dropA(world,el);
						}

						dropB(world,el);
						// TODO Universal.gorey(world, i, j, k);
						complete=true;
						targetItem.item.stackSize = 1;
						targetItem.item.setItemDamage(EnumSoulCore.SOULPURE.ordinal());
						System.out.println("Pure Soul");

					}else if(el instanceof EntityMob){
						if(el instanceof EntityZombie){
							if(el instanceof EntityPigZombie){
								dropA(world,el);
							}else{
								dropC(world,el);
							}
							dropB(world,el);
							// TODO Universal.gorey(world, i, j, k);
						}else if(el instanceof EntitySkeleton){
							dropC(world,el);
							dropB(world,el);
						}else if(el instanceof EntityEnderman){
							EntityItem ei = new EntityItem(world, el.posX + Math.random() * 2 - 1, el.posY, el.posZ + Math.random() * 2 - 1, new ItemStack(Item.flint, 1));
							world.spawnEntityInWorld(ei);
						}else{
							dropB(world,el);
							// TODO Universal.gorey(world, i, j, k);
						}
						complete=true;
						targetItem.item.stackSize = 1;
						targetItem.item.setItemDamage(EnumSoulCore.SOULEVIL.ordinal());
						System.out.println("Evil Soul");
					}

					if(complete){
						el.setDead();
						//el.attackEntityFrom(DamageSource.magic, 999);
					}
				}
				el.attackEntityFrom(DamageSource.magic, 4);
			}
		}
		return complete;
	}
	private static void dropA(World world,Entity e){
		EntityItem ei = new EntityItem(world, e.posX + Math.random() * 2 - 1, e.posY, e.posZ + Math.random() * 2 - 1, new ItemStack(Ids.skullA, 1, 0));
		world.spawnEntityInWorld(ei);
	}
	private static void dropC(World world,Entity e){
		EntityItem ei = new EntityItem(world, e.posX + Math.random() * 2 - 1, e.posY, e.posZ + Math.random() * 2 - 1, new ItemStack(Ids.skull, 1, 0));
		world.spawnEntityInWorld(ei);
	}
	private static void dropB(World world,Entity e){
		EntityItem ei;
		for (int m = 0; m < 5; m++){
			ei = new EntityItem(world, e.posX + Math.random() * 2 - 1, e.posY, e.posZ + Math.random() * 2 - 1, new ItemStack(Item.bone, 1));
			world.spawnEntityInWorld(ei);
		}
	}
}
