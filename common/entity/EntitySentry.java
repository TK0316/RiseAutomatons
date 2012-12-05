package riseautomatons.common.entity;

import riseautomatons.common.Ids;
import riseautomatons.common.Universal;
import riseautomatons.common.entity.EntityWorker.EnumWorkState;
import riseautomatons.common.item.EnumSoulCore;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIAttackOnCollide;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAILeapAtTarget;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAINearestAttackableTarget;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntitySentry extends EntityOwnedBot implements IBot {

	public static final String SENTRY_PNG =  "/riseautomatons/sentry.png";
	public static final String SENTRYBLOCK_PNG =  "/riseautomatons/sentryBlock.png";

	public static final int INDEX_MODE = 18;

	private EntityPlayer entityplayer;

	public EntitySentry(World world) {
		super(world);
		setSize(0.8F, 1.2F);
		moveSpeed = 0.4F;
		health = 20;

		getNavigator().setAvoidsWater(true);

		tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F)); // 0.4f
		tasks.addTask(4, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(5, new EntityAIBotFollowOwner(this, moveSpeed, 8F, 4.0F));
		tasks.addTask(9, new EntityAIWatchClosest(this,
				net.minecraft.src.EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIBotOwnerHurtByTarget(this));
		targetTasks.addTask(2, new EntityAIBotOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityChicken.class, 16F, 50, false));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this,
				net.minecraft.src.EntityMob.class, 24F, 1, true));

	}

	public EntitySentry(World world, double d, double d1, double d2, int turn,
			String s) {
		this(world);
		setPosition(d, d1 + (double) yOffset, d2);
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		//Universal.rotateEntity(this, turn * 90);
		setBotOwner(s);
	}

	@Override
	public int getMaxHealth() {
		return 20;
	}

	@Override
	protected boolean isMovementCeased() {
		return entityplayer == null;
	}

	@Override
	protected void attackEntity(Entity entity, float f) {
		if (attackTime <= 0 && f < 5F
				&& entity.boundingBox.maxY > boundingBox.minY
				&& entity.boundingBox.minY < boundingBox.maxY) {
			attackTime = 2;
			attackEntityAsMob(entity);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 5);

	}

	@Override
	public float getEyeHeight() {
		return height * 0.8F;
	}

	@Override
	public void onUpdate() {
		// System.out.println(this.rotationYawHead);
		super.onUpdate();

		if (isWet()) {
			if (isEntityAlive()) {

				dropper();
				health = 0;
			}
		}
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected String getLivingSound() {
		return "automatons.techy";
	}

	@Override
	protected String getHurtSound() {
		return "step.stone";
	}

	@Override
	protected String getDeathSound() {
		return "random.glass";
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);

		if (!Universal.improperWorld(worldObj)) {

			dropper();// a(field_34905_c > 0);
			setDead();
		}

	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	void dropper() {
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
			EntityItem entityitem = new EntityItem(worldObj, posX, posY + 1,
					posZ, new ItemStack(Ids.soulCore, 1, 0));
			entityitem.delayBeforeCanPickup = 10;
			entityitem.setVelocity(Math.random() * 0.25 - 0.125, 0.25,
					Math.random() * 0.25 - 0.125);
			worldObj.spawnEntityInWorld(entityitem);
			//
			int meta = MathHelper
					.floor_double((double) (rotationYawHead * 4.0F / 360.0F) + 0.5D) & 3;

			worldObj.setBlockAndMetadataWithNotify(
					MathHelper.floor_double(posX),
					MathHelper.floor_double(posY),
					MathHelper.floor_double(posZ), Ids.blockSentry, meta);
			setDead();
		}
	}

	@Override
	public String getTexture() {
		return SENTRY_PNG;
	}


	private void setMode(EnumBotMode mode) {
		dataWatcher.updateObject(INDEX_MODE, mode.ordinal());
	}

	@Override
	public EnumBotMode getMode() {
		int mode = dataWatcher.getWatchableObjectInt(INDEX_MODE);
		if(0 <= mode && mode < EnumBotMode.values().length) {
			return EnumBotMode.values()[mode];
		}
		return EnumBotMode.STAY;
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		if(itemstack != null && itemstack.itemID == Ids.soulCore && itemstack.getItemDamage() == EnumSoulCore.SOULSYNTH.ordinal()) {
			if(getBotOwner() == "") {
				setBotOwner(entityplayer.username);
				setMode(EnumBotMode.FOLLOW);
				Universal.poof(worldObj, posX, posY, posZ);
			}
			else {
				return true;
			}
		}

		if(reallyGetBotOwner() != entityplayer) {
			return true;
		}
		if(getMode() == EnumBotMode.STAY) {
			setMode(EnumBotMode.FOLLOW);
		}
		else {
			setMode(EnumBotMode.STAY);
		}
		Universal.poof(worldObj, posX, posY, posZ);
		return true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(INDEX_MODE, EnumBotMode.STAY.ordinal());
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger("Mode", getMode().ordinal());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);
		setMode(EnumBotMode.values()[par1nbtTagCompound.getInteger("Mode")]);
	}

}
