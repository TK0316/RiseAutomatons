package riseautomatons.entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import riseautomatons.Universal;
import riseautomatons.block.Blocks;
import riseautomatons.item.EnumCraftSetType;
import riseautomatons.item.Items;

public class EntityOmni extends EntityOwnedBot implements IBot {

    public static final ResourceLocation OMNI_PNG = new ResourceLocation("riseautomatons", "textures/entities/omni.png");
	public static int renderId;
	public static Map<ItemStack, Class> preset = new LinkedHashMap();

	public EntityOmni(World world)
    {
        super(world);
        setSize(0.3F, 1.2F);
        setHealth(getMaxHealth());
    }

    public EntityOmni(World world, double d, double d1, double d2)
    {
        this(world);
        setPosition(d, d1 + yOffset, d2);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
    }

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0D);
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
		ItemStack key = new ItemStack(itemStack.getItem(), 1, itemStack.getItemDamage());
		for (ItemStack is : preset.keySet()) {
			if (is.isItemEqual(key)) {
				try {
					metamorph((Entity) preset.get(is).getConstructor(
							new Class[] { World.class }).newInstance(
							new Object[] { this.worldObj }));
					return true;
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
                return super.interact(par1EntityPlayer);
			}
		}
		if(preset.containsKey(key)) {
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
						c.getDeclaredMethod("func_146068_u");
					} catch (NoSuchMethodException e) {
						obfuscated = false;
					}
					final Method method = obfuscated ? c.getDeclaredMethod("func_146068_u") : c.getDeclaredMethod("getDropItem");

					if (!method.isAccessible()) {
						AccessController
								.doPrivileged(new PrivilegedAction<Object>() {
									@Override
									public Object run() {
										method.setAccessible(true);
										return null;
									}
								});
					}

					Item item = (Item) method.invoke(c.getConstructor(
							new Class[] { World.class }).newInstance(
							new Object[] { this.worldObj }));
					if(item != null) {
						//System.out.println("DropItemId = "+ Item.itemsList[itemID].getItemName() + " : " + c.getSimpleName());
					}

					if (item == itemStack.getItem()) {
						metamorph((Entity) c.getConstructor(
								new Class[] { World.class }).newInstance(
								new Object[] { this.worldObj }));
						return true;

					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					//System.out.println("skip: " + c.getSimpleName());
					//e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}

			}

		}
		return super.interact(par1EntityPlayer);
	}

	public void metamorph(Entity ep) {
		if (!Universal.improperWorld(worldObj)) {
			double d = posX;
			double d1 = posY;
			double d2 = posZ;
			ep.setPosition(d, d1 + yOffset, d2);
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
	public ResourceLocation getTexture() {
		return OMNI_PNG;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	static {
		preset.put(new ItemStack(Items.gunpowder), EntityCreeper.class);
		preset.put(new ItemStack(Items.bone), EntitySkeleton.class);
		preset.put(new ItemStack(Items.string), EntitySpider.class);
		//preset.put(EntityGiantZombie.class, null);
		preset.put(new ItemStack(Items.rotten_flesh), EntityZombie.class);
		preset.put(new ItemStack(Items.slime_ball), EntitySlime.class);
		preset.put(new ItemStack(Items.ghast_tear), EntityGhast.class);
		preset.put(new ItemStack(Items.golden_sword), EntityPigZombie.class);
		preset.put(new ItemStack(Items.ender_pearl), EntityEnderman.class);
		preset.put(new ItemStack(Blocks.web), EntityCaveSpider.class);
		//preset.put(EntitySilverfish.class, null);
		preset.put(new ItemStack(Items.blaze_rod), EntityBlaze.class);
		preset.put(new ItemStack(Items.magma_cream), EntityMagmaCube.class);
		//preset.put(EntityDragon.class, null);
		//preset.put(EntityWither.class, null);
		//preset.put(EntityBat.class, null);
		//preset.put(EntityWitch.class, null);
		preset.put(new ItemStack(Items.porkchop), EntityPig.class);
		preset.put(new ItemStack(Items.cooked_porkchop), EntityPig.class);
		preset.put(new ItemStack(Blocks.wool), EntitySheep.class);
		preset.put(new ItemStack(Items.leather), EntityCow.class);
		preset.put(new ItemStack(Items.egg), EntityChicken.class);
		preset.put(new ItemStack(Items.chicken), EntityChicken.class);
		preset.put(new ItemStack(Items.feather), EntityChicken.class);
		preset.put(new ItemStack(Items.dye, 1, 0), EntitySquid.class);
		//preset.put(EntityWolf.class, null);
		//preset.put(EntityMooshroom.class,  null);
		preset.put(new ItemStack(Items.snowball), EntitySnowman.class);
		//preset.put(EntityOcelot.class,  null);
		//preset.put(EntityIronGolem.class,  null);
		//preset.put(EntityVillager.class,  null);
		//preset.put(EntityWorker.class,  null);
		//preset.put(EntitySentry.class,  null);
		//preset.put(EntityFactotum.class,  null);
		//preset.put(EntityBeacon.class,  null);
		//preset.put(EntityGuard.class,  null);
		preset.put(new ItemStack(Blocks.frass), EntityGolemNormal.class);
		//preset.put(EntityGolemPure.class,  null);
		preset.put(new ItemStack(Blocks.crystal), EntityWatcher.class);
		preset.put(new ItemStack(Blocks.boing), EntitySlider.class);
		preset.put(new ItemStack(Items.craftset,1,EnumCraftSetType.ROD.ordinal()), EntityBobby.class);
		preset.put(new ItemStack(Items.craftset,1,EnumCraftSetType.BLUECORE.ordinal()), EntityHelios.class);
		//preset.put(EntityOmni.class,new ItemStack(Item.gunpowder));
	}
}
