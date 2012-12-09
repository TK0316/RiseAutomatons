package riseautomatons.common.entity;

import riseautomatons.common.Ids;
import riseautomatons.common.Universal;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAgeable;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.PathEntity;
import net.minecraft.src.World;

public class EntitySlider extends EntityAniBot implements IBot {

	public static final String SLIDER_PNG = "/riseautomatons/slider.png";
	public static int renderId;
	private PathEntity pathToEntity;

	public EntitySlider(World world) {
		super(world);
		texture = SLIDER_PNG;
		moveSpeed = 1.0F;
		// attackStrength = 0;
		health = 8;
		setSize(1.0F, 0.1F);
	}

	public EntitySlider(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + (double) yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		// setPathToEntity(null);
	}

	@Override
	public EntityAgeable func_90011_a(EntityAgeable var1) {
		return new EntitySlider(worldObj);
	}

	@Override
	public int getMaxHealth() {
		return 8;
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, int i) {

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
			health = 0;
		}

		super.onLivingUpdate();
	}

	@Override
	protected void attackEntity(Entity par1Entity, float par2) {
	}

	@Override
	protected Entity findPlayerToAttack() {

		EntityPlayer entityplayer = worldObj
				.getClosestPlayerToEntity(this, 16D);

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
					.floor_double((posX + (double) rand.nextInt(13)) - 6D);
			int j1 = MathHelper
					.floor_double((posY + (double) rand.nextInt(7)) - 3D);
			int k1 = MathHelper
					.floor_double((posZ + (double) rand.nextInt(13)) - 6D);
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
		return "automatons.crank";
	}

	@Override
	protected String getHurtSound() {
		return "automatons.crank";
	}

	@Override
	protected String getDeathSound() {
		return "automatons.botdie";
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
					(posX + (double) (rand.nextFloat() * width * 2.0F))
							- (double) width, posY
							+ (double) (rand.nextFloat() * height),
					(posZ + (double) (rand.nextFloat() * width * 2.0F))
							- (double) width, d, d1, d2);
		}

		if (!Universal.improperWorld(worldObj)) {
			int R = rand.nextInt(4);

			if (R == 1) {
				entityDropItem(new ItemStack(Ids.blockBoing, 1, 0), 0.0F);
			} else if (R == 2) {
				entityDropItem(new ItemStack(Ids.soulCore, 1, 0), 0.0F);
			} else if (R == 3) {
				entityDropItem(new ItemStack(Ids.craftSet, 1, 11), 0.0F);
			} else {
				entityDropItem(new ItemStack(Ids.spring, 3, 0), 0.0F);
			}

			deathTime = 999; // setEntityDead();
		}
	}
	@Override
	protected int getDropItemId() {
		return 0;
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
			worldObj.playSoundAtEntity(this, "automatons.clank", 1.0F, 1.0F);
			// entity.posY+=5;
		}
	}

	@Override
	public void onUpdate() {

		super.onUpdate();

		if (!Universal.improperWorld(worldObj)
				&& worldObj.difficultySetting == 0) {
			setDead();
		}
	}

}
