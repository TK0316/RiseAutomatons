package riseautomatons.common.entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityBat;
import net.minecraft.src.EntityBlaze;
import net.minecraft.src.EntityCaveSpider;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityCow;
import net.minecraft.src.EntityCreeper;
import net.minecraft.src.EntityDragon;
import net.minecraft.src.EntityEnderman;
import net.minecraft.src.EntityGhast;
import net.minecraft.src.EntityGiantZombie;
import net.minecraft.src.EntityGolem;
import net.minecraft.src.EntityIronGolem;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMagmaCube;
import net.minecraft.src.EntityMooshroom;
import net.minecraft.src.EntityOcelot;
import net.minecraft.src.EntityPig;
import net.minecraft.src.EntityPigZombie;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntitySheep;
import net.minecraft.src.EntitySilverfish;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntitySlime;
import net.minecraft.src.EntitySnowman;
import net.minecraft.src.EntitySpider;
import net.minecraft.src.EntitySquid;
import net.minecraft.src.EntityVillager;
import net.minecraft.src.EntityWitch;
import net.minecraft.src.EntityWither;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import riseautomatons.common.Universal;
import riseautomatons.common.block.Blocks;
import riseautomatons.common.item.EnumCraftSetType;
import riseautomatons.common.item.Items;

public class EntityOmni extends EntityGolem implements IBot {

    public static final String OMNI_PNG = "/riseautomatons/omni.png";
	public static int renderId;
	public static Map<ItemStack, Class> preset = new LinkedHashMap();

	public EntityOmni(World world)
    {
        super(world);
        setSize(0.3F, 1.2F);
        health = 1;
        texture = OMNI_PNG;
    }

    public EntityOmni(World world, double d, double d1, double d2)
    {
        this(world);
        setPosition(d, d1 + (double)yOffset, d2);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
    }
	@Override
	public int getMaxHealth() {
		return 1;
	}

	@Override
	protected String getHurtSound() {
		return "";
	}

	@Override
	protected String getDeathSound() {
		return "";
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public void onLivingUpdate() {
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack itemStack = par1EntityPlayer.inventory.getCurrentItem();
		if(itemStack == null) {
			return false;
		}
		ItemStack key = new ItemStack(itemStack.itemID, 1, itemStack.getItemDamage());
		if(preset.containsKey(key)) {
			try {
				metamorph((Entity) preset.get(key).getConstructor(
						new Class[] { World.class }).newInstance(
						new Object[] { this.worldObj }));
				return true;
			} catch (InstantiationException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			return super.interact(par1EntityPlayer);
		}
		//System.out.println("CurrentItemID = "+ Item.itemsList[itemStack.itemID].getItemName());

		for(Class c : (Set<Class>)EntityList.classToStringMapping.keySet()) {
			if(EntityLiving.class == c) {
				continue;
			}
			if(EntityLiving.class.isAssignableFrom(c)) {
				//System.out.println(c);
				try {
					boolean obfuscated = true;
					try {
						c.getDeclaredMethod("func_70633_aT");
					} catch (NoSuchMethodException e) {
						obfuscated = false;
					}
					final Method method = obfuscated ? c.getDeclaredMethod("func_70633_aT") : c.getDeclaredMethod("getDropItemId");

					if (!method.isAccessible()) {
						AccessController
								.doPrivileged(new PrivilegedAction<Object>() {
									public Object run() {
										method.setAccessible(true);
										return null;
									}
								});
					}

					int itemID = (Integer) method.invoke(c.getConstructor(
							new Class[] { World.class }).newInstance(
							new Object[] { this.worldObj }));
					if(itemID > 0 && Item.itemsList[itemID] != null) {
						//System.out.println("DropItemId = "+ Item.itemsList[itemID].getItemName() + " : " + c.getSimpleName());
					}

					if (itemID == itemStack.itemID) {
						metamorph((Entity) c.getConstructor(
								new Class[] { World.class }).newInstance(
								new Object[] { this.worldObj }));
						return true;

					}
				} catch (InstantiationException e) {
					// TODO �����������ꂽ catch �u���b�N
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO �����������ꂽ catch �u���b�N
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO �����������ꂽ catch �u���b�N
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO �����������ꂽ catch �u���b�N
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					//System.out.println("skip: " + c.getSimpleName());
					// TODO �����������ꂽ catch �u���b�N
					//e.printStackTrace();
				} catch (SecurityException e) {
					// TODO �����������ꂽ catch �u���b�N
					e.printStackTrace();
				}

			}

		}
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.interact(par1EntityPlayer);
	}

	public void metamorph(Entity ep) {
		if (!Universal.improperWorld(worldObj)) {
			double d = posX;
			double d1 = posY;
			double d2 = posZ;
			ep.setPosition(d, d1 + (double) yOffset, d2);
			ep.motionX = 0.0D;
			ep.motionY = 0.0D;
			ep.motionZ = 0.0D;
			ep.prevPosX = d;
			ep.prevPosY = d1;
			ep.prevPosZ = d2;
			worldObj.spawnEntityInWorld(ep);
			setDead();
		}

		// particles?
		// if(!worldObj.multiplayerWorld){
		// if(s=="pig"){
		// Entity ep= new Entity(worldObj);
		// ep2=cl.cast(ep);
		// (cl) ep= new (cl)(worldObj);
		// cl.cast(ep);
		// ep.posX=posX;ep.posY=posY;ep.posZ=posZ;
		// worldObj.entityJoinedWorld(ep);
		// }
		// }
	}

	@Override
	public String getTexture() {
		return OMNI_PNG;
	}

	@Override
	protected void entityInit() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.entityInit();
	}

	static {
		preset.put(new ItemStack(Item.gunpowder), EntityCreeper.class);
		preset.put(new ItemStack(Item.bone), EntitySkeleton.class);
		preset.put(new ItemStack(Item.silk), EntitySpider.class);
		//preset.put(EntityGiantZombie.class, null);
		preset.put(new ItemStack(Item.rottenFlesh), EntityZombie.class);
		preset.put(new ItemStack(Item.slimeBall), EntitySlime.class);
		preset.put(new ItemStack(Item.ghastTear), EntityGhast.class);
		preset.put(new ItemStack(Item.swordGold), EntityPigZombie.class);
		preset.put(new ItemStack(Item.enderPearl), EntityEnderman.class);
		preset.put(new ItemStack(Block.web), EntityCaveSpider.class);
		//preset.put(EntitySilverfish.class, null);
		preset.put(new ItemStack(Item.blazeRod), EntityBlaze.class);
		preset.put(new ItemStack(Item.magmaCream), EntityMagmaCube.class);
		//preset.put(EntityDragon.class, null);
		//preset.put(EntityWither.class, null);
		//preset.put(EntityBat.class, null);
		//preset.put(EntityWitch.class, null);
		preset.put(new ItemStack(Item.porkRaw), EntityPig.class);
		preset.put(new ItemStack(Item.porkCooked), EntityPig.class);
		preset.put(new ItemStack(Block.cloth), EntitySheep.class);
		preset.put(new ItemStack(Item.leather), EntityCow.class);
		preset.put(new ItemStack(Item.egg), EntityChicken.class);
		preset.put(new ItemStack(Item.chickenRaw), EntityChicken.class);
		preset.put(new ItemStack(Item.feather), EntityChicken.class);
		preset.put(new ItemStack(Item.dyePowder, 1, 0), EntitySquid.class);
		//preset.put(EntityWolf.class, null);
		//preset.put(EntityMooshroom.class,  null);
		preset.put(new ItemStack(Item.snowball), EntitySnowman.class);
		//preset.put(EntityOcelot.class,  null);
		//preset.put(EntityIronGolem.class,  null);
		//preset.put(EntityVillager.class,  null);
		//preset.put(EntityWorker.class,  null);
		//preset.put(EntitySentry.class,  null);
		//preset.put(EntityFactotum.class,  null);
		//preset.put(EntityBeacon.class,  null);
		//preset.put(EntityGuard.class,  null);
		preset.put(new ItemStack(Blocks.frass), EntityGolem.class);
		//preset.put(EntityGolemPure.class,  null);
		preset.put(new ItemStack(Blocks.crystal), EntityWatcher.class);
		preset.put(new ItemStack(Blocks.boing), EntitySlider.class);
		preset.put(new ItemStack(Items.craftset,1,EnumCraftSetType.ROD.ordinal()), EntityBobby.class);
		preset.put(new ItemStack(Items.craftset,1,EnumCraftSetType.BLUECORE.ordinal()), EntityHelios.class);
		//preset.put(EntityOmni.class,new ItemStack(Item.gunpowder));
	}
}
