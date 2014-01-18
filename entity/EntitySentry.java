package riseautomatons.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.Universal;
import riseautomatons.item.EnumSoulCore;

public class EntitySentry extends EntityOwnedBot implements IBot {

	public static final ResourceLocation SENTRY1_PNG =  new ResourceLocation("riseautomatons", "textures/entities/sentry1.png");
	public static final ResourceLocation SENTRY2_PNG =  new ResourceLocation("riseautomatons", "textures/entities/sentry2.png");
	public static final ResourceLocation SENTRY3_PNG =  new ResourceLocation("riseautomatons", "textures/entities/sentry3.png");
	public static final ResourceLocation SENTRYBLOCK_PNG =  new ResourceLocation("riseautomatons", "textures/entities/sentryBlock.png");

	public static final int INDEX_MODE = 18;

	private EntityPlayer entityplayer;

	public EntitySentry(World world) {
		super(world);
		setSize(0.8F, 1.2F);
		float moveSpeed = 0.7F;
		setHealth(getMaxHealth());

		getNavigator().setAvoidsWater(true);

		tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F)); // 0.4f
		tasks.addTask(4, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(5, new EntityAIBotFollowOwner(this, moveSpeed, 8F, 4.0F));
		tasks.addTask(5, new EntityAIBotWander(this, moveSpeed));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIBotOwnerHurtByTarget(this));
		targetTasks.addTask(2, new EntityAIBotOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityChicken.class, 1, false));
		// targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityCreeper.class, 1, false));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityMob.class, 1, false));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityAniBot.class, 1, false));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityBot.class, 1, false));
		targetTasks.addTask(4, new EntityAIBotNearestAttackableTarget(this, EntityLiving.class, 1, false));
	}

	public EntitySentry(World world, double d, double d1, double d2, int turn,
			String s) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		//Universal.rotateEntity(this, turn * 90);
		setBotOwner(s);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(24.0D);
		//getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.7F);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
	}

	@Override
	public int getAge() {
		return 1;
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

				if (!worldObj.isRemote) {
					dropper();
				}
				setHealth(0);
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

		if (!worldObj.isRemote) {

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
					(posX + (rand.nextFloat() * width * 2.0F))
							- width, posY
							+ (rand.nextFloat() * height),
					(posZ + (rand.nextFloat() * width * 2.0F))
							- width, d, d1, d2);
		}

		if (!worldObj.isRemote) {
			EntityItem entityitem = new EntityItem(worldObj, posX, posY + 1,
					posZ, new ItemStack(Ids.soulCore, 1, 0));
			entityitem.delayBeforeCanPickup = 10;
			//entityitem.setVelocity(Math.random() * 0.25 - 0.125, 0.25, Math.random() * 0.25 - 0.125);
			worldObj.spawnEntityInWorld(entityitem);
			//
			int meta = MathHelper
					.floor_double((rotationYawHead * 4.0F / 360.0F) + 0.5D) & 3;

			worldObj.setBlock(
					MathHelper.floor_double(posX),
					MathHelper.floor_double(posY),
					MathHelper.floor_double(posZ), Ids.blockSentry, meta, 3);
			setDead();
		}
	}

	@Override
	public ResourceLocation getTexture() {
		switch(getMode()) {
		case STAY:
			return SENTRY1_PNG;
		case FOLLOW:
			return SENTRY2_PNG;
		case WANDER:
			return SENTRY3_PNG;
		}
		return SENTRY1_PNG;
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
		else if(getMode() == EnumBotMode.FOLLOW) {
			//setMode(EnumBotMode.STAY);
			setMode(EnumBotMode.WANDER);
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
