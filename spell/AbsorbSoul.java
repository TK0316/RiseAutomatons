package riseautomatons.spell;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.entity.IBot;
import riseautomatons.item.EnumSoulCore;
import riseautomatons.item.Items;

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
				if (e.getEntityItem().getItem() == Ids.soulCore && e.getEntityItem().getItemDamage() == 4){
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
		L = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(i - 2, j - 2, k - 2, i + 2, j + 2, k + 2));
		boolean complete=false;
		if (!L.isEmpty()){
			for(int n=0;n<L.size();n++){
				EntityLivingBase el = (EntityLivingBase) L.get(n);
				if(!complete){
					System.out.println();
					if(el instanceof EntityPlayer || el instanceof IBot || el instanceof EntityTameable || el instanceof EntityDragon || el instanceof EntityGolem){
						System.out.println(el.getCommandSenderName() + " is not target type");

					}else if(el instanceof EntityAnimal){
						if (!(el instanceof EntityChicken)){
							dropA(world,el);
						}

						dropB(world,el);
						// TODO Universal.gorey(world, i, j, k);
						complete=true;
						targetItem.getEntityItem().stackSize = 1;
						targetItem.getEntityItem().setItemDamage(EnumSoulCore.SOULPURE.ordinal());
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
							EntityItem ei = new EntityItem(world, el.posX + Math.random() * 2 - 1, el.posY, el.posZ + Math.random() * 2 - 1, new ItemStack(Items.flint, 1));
							world.spawnEntityInWorld(ei);
						}else{
							dropB(world,el);
							// TODO Universal.gorey(world, i, j, k);
						}
						complete=true;
						targetItem.getEntityItem().stackSize = 1;
						targetItem.getEntityItem().setItemDamage(EnumSoulCore.SOULEVIL.ordinal());
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
			ei = new EntityItem(world, e.posX + Math.random() * 2 - 1, e.posY, e.posZ + Math.random() * 2 - 1, new ItemStack(Items.bone, 1));
			world.spawnEntityInWorld(ei);
		}
	}
}
