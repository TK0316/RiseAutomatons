package riseautomatons.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.Universal;
import riseautomatons.item.EnumCraftSetType;

public class EntitySlider extends EntityAniBot implements IBot {

	public static final ResourceLocation SLIDER_PNG = new ResourceLocation("riseautomatons", "textures/entities/slider.png");
	public static int renderId;
	private PathEntity pathToEntity;

	public EntitySlider(World world) {
		super(world);
		// attackStrength = 0;
		setHealth(getMaxHealth());
		setSize(1.0F, 0.1F);
	}

	public EntitySlider(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		// setPathToEntity(null);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0F);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float i) {

		if(damagesource.damageType == DamageSource.fall.damageType) {
			return true;
		}

		if (super.attackEntityFrom(damagesource, i)) {
			fleeingTick = 0;
			Entity entity = damagesource.getEntity();

			if (riddenByEntity == entity || ridingEntity == entity) {
				return true;
			}

			if (entity != this) {
				entityToAttack = entity;
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onLivingUpdate() {

		if (isWet()) {
			this.setHealth(0);
		}

		super.onLivingUpdate();
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
		super.onCollideWithPlayer(par1EntityPlayer);
		par1EntityPlayer.motionY = 1F;
	}

	@Override
	protected void attackEntity(Entity par1Entity, float par2) {
	}

	@Override
	protected Entity findPlayerToAttack() {

		EntityPlayer entityplayer = worldObj
				.getClosestVulnerablePlayerToEntity(this, 16D);

		if (entityplayer != null && canEntityBeSeen(entityplayer)) {
			return entityplayer;
		} else {
			return null;
		}
	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}

	@Override
	protected void updateWanderPath() {

		boolean flag = false;
		int i = -1;
		int j = -1;
		int k = -1;
		float f = -99999F;

		for (int l = 0; l < 10; l++) {
			int i1 = MathHelper
					.floor_double((posX + rand.nextInt(13)) - 6D);
			int j1 = MathHelper
					.floor_double((posY + rand.nextInt(7)) - 3D);
			int k1 = MathHelper
					.floor_double((posZ + rand.nextInt(13)) - 6D);
			float f1 = getBlockPathWeight(i1, j1, k1);

			if (f1 > f) {
				f = f1;
				i = i1;
				j = j1;
				k = k1;
				flag = true;
			}
		}

		if (flag) {
			pathToEntity = worldObj.getEntityPathToXYZ(this, i, j, k, 10F,
					false, true, false, true);
		}
	}

	@Override
	protected float getSoundVolume() {
		return 0.5F;
	}

	@Override
	protected String getLivingSound() {
		return "riseautomatons:crank";
	}

	@Override
	protected String getHurtSound() {
		return "riseautomatons:crank";
	}

	@Override
	protected String getDeathSound() {
		return "riseautomatons:botdie";
	}

	@Override
	public void onDeath(DamageSource damagesource) {

		super.onDeath(damagesource);

		if (!Universal.improperWorld(worldObj)) {
			dropper();
		}

		setDead();
	}

	protected void dropper() {
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

		if (!Universal.improperWorld(worldObj)) {
			int R = rand.nextInt(5);

			if (R == 1) {
				entityDropItem(new ItemStack(Ids.blockBoing, 1, 0), 0.0F);
			} else if (R == 2) {
				entityDropItem(new ItemStack(Ids.soulCore, 1, 0), 0.0F);
			} else if (R == 3) {
				entityDropItem(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal()), 0.0F);
			} else if (R == 4) {
				entityDropItem(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.BLUECORE.ordinal()), 0.0F);
			} else {
				entityDropItem(new ItemStack(Ids.spring, 3, 0), 0.0F);
			}

			deathTime = 999; // setEntityDead();
		}
	}
	@Override
	protected Item getDropItem() {
		return null;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}

	@Override
	public void applyEntityCollision(Entity entity) {

		if (entity.riddenByEntity == this || entity.ridingEntity == this) {
			return;
		}

		double d = entity.posX - posX;
		double d1 = entity.posZ - posZ;
		double f1 = d1;
		double f = d;
		double d2 = MathHelper.abs_max(d, d1);

		if (d2 >= 0.0099999997764825821D) {
			d2 = MathHelper.sqrt_double(d2);
			d /= d2;
			d1 /= d2;
			double d3 = 1.0D / d2;

			if (d3 > 1.0D) {
				d3 = 1.0D;
			}

			d *= d3;
			d1 *= d3;
			d *= 0.05000000074505806D;
			d1 *= 0.05000000074505806D;
			d *= 1.0F; // - entityCollisionReduction;
			d1 *= 1.0F; // - entityCollisionReduction;
			addVelocity(-d, 0.0D, -d1);
			entity.addVelocity(f, 0.75D, f1);
			worldObj.playSoundAtEntity(this, "riseautomatons:clank", 1.0F, 1.0F);
			// entity.posY+=5;
		}
	}

	@Override
	public void onUpdate() {

		super.onUpdate();

		if (!Universal.improperWorld(worldObj)
				&& worldObj.difficultySetting == EnumDifficulty.PEACEFUL) {
			setDead();
		}
	}

	@Override
	public ResourceLocation getTexture() {
		return SLIDER_PNG;
	}

}
