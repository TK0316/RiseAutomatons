package riseautomatons.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.Universal;

public class EntityBeacon extends EntityOwnedBot {

	public static final ResourceLocation BEACON_PNG = new ResourceLocation("riseautomatons", "textures/entities/beaco.png");
	public static final ResourceLocation BEACON1_PNG = new ResourceLocation("riseautomatons", "textures/entities/beac1.png");
	public static final ResourceLocation BEACON2_PNG = new ResourceLocation("riseautomatons", "textures/entities/beac2.png");
	public static int renderId;
	protected int siren;

	public EntityBeacon(World par1World) {
		super(par1World);
		setSize(0.3F, 1F);
		setHealth(getMaxHealth());
		siren = 0;
	}

	public EntityBeacon(World world, double d, double d1, double d2,
			String s) {
		this(world);
		setPosition(d, d1 + (double) yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		setBotOwner(s);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(1.0D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(18, new Integer(siren)); // health
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {

		super.writeEntityToNBT(nbttagcompound);

		if (getBotOwner() == null) {
			nbttagcompound.setString("Owner", "");
		} else {
			nbttagcompound.setString("Owner", getBotOwner());
			// System.out.println("owned");
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {

		super.readEntityFromNBT(nbttagcompound);
		String s = nbttagcompound.getString("Owner");

		if (s.length() > 0) {
			setBotOwner(s);
		}
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
	public ResourceLocation getTexture() {
		switch (getSiren()) {
		case 1:
			return BEACON1_PNG;

		case 2:
			return BEACON2_PNG;

			// case 0:return "/zeitgeist/.png";
		default:
			return BEACON_PNG;// super.getTexture();
		}
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	public void onDeath(DamageSource par1DamageSource) {

		if (!Universal.improperWorld(worldObj)) {
			dropper();// a(field_34905_c > 0);
		}

		worldObj.setEntityState(this, (byte) 3);
	}

	@Override
	public void onLivingUpdate() {
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {

		if (entityplayer.username.equalsIgnoreCase(getBotOwner())) {
			ItemStack itemstack = entityplayer.inventory.getCurrentItem();

			if (itemstack != null
					&& itemstack.itemID == Item.stick.itemID) {
					setSiren(2);
			} else {
				if (getSiren() != 0) {
					setSiren(0);
				} else {
					setSiren(1);
				}
			}

			return true;
		}

		return false;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	protected void dropper() {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode",
					(posX + (double) (rand.nextFloat() * width * 2.0F))
							- (double) width, posY
							+ (double) (rand.nextFloat() * height),
					(posZ + (double) (rand.nextFloat() * width * 2.0F))
							- (double) width, d, d1, d2);
		}

		if (!Universal.improperWorld(worldObj)) {
			entityDropItem(new ItemStack(Ids.itemBeacon, 1, 0), 0.0F);
			setDead();
		}
	}

	public void setSiren(int b) {
		siren = b;
		dataWatcher.updateObject(18, b);
	}

	public int getSiren() {
		return dataWatcher.getWatchableObjectInt(18);
	}
}
