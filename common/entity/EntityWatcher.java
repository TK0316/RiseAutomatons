package riseautomatons.common.entity;

import java.util.List;

import riseautomatons.common.Ids;
import riseautomatons.common.Universal;

import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIAttackOnCollide;
import net.minecraft.src.EntityAIHurtByTarget;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAINearestAttackableTarget;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public class EntityWatcher extends EntityMob implements IBot {

	public static final String WATCHER_PNG = "/riseautomatons/watcher.png";
	public static int renderId;

	public EntityWatcher(World world) {
		super(world);
		health = 60;
		moveSpeed = 0.2F;
		setSize(1.0F, 3.8F);
		this.tasks.addTask(4, new EntityAIBotArrowAttack(this, this.moveSpeed,
				1, 6));

		tasks.addTask(4, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 2F, 1, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityOwnedBot.class, 2F, 1, true));

	}

	public EntityWatcher(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + (double) yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
	}

	@Override
	public int getMaxHealth() {
		return 60;
	}

	@Override
	protected Entity findPlayerToAttack() {

		List list = worldObj.getEntitiesWithinAABB(
				net.minecraft.src.EntityPlayer.class,
				boundingBox.expand(1D, 3D, 1D));

		if (!list.isEmpty()) {
			return (EntityPlayer) list.get(0);
		}

		return null;
	}

	@Override
	protected void attackEntity(Entity entity, float f) {

		if (f < 15F) {
			double d = entity.posX - posX;
			double d1 = entity.posZ - posZ;

			if (attackTime == 0) {
				Entity targetedEntity = entity;
				double d5 = targetedEntity.posX - posX;
				double d6 = (targetedEntity.boundingBox.minY + (double) (targetedEntity.height / 2.0F))
						- (posY + (double) (height));
				double d7 = targetedEntity.posZ - posZ;
				EntityLaser entityfireball = new EntityLaser(worldObj,
						this, d5, d6, d7, 0.4D);
				// double d8 = 4D;
				// Vec3D vec3d = getLook(1.0F);
				entityfireball.posX = posX;// + vec3d.xCoord * d8;
				entityfireball.posY = posY + (double) (height);
				entityfireball.posZ = posZ;// + vec3d.zCoord * d8;
				worldObj.spawnEntityInWorld(entityfireball);
				worldObj.playSoundAtEntity(this, "automatons.spark", 1.0F, 1.0F);
				attackTime = 10;
			}

			rotationYaw = (float) ((Math.atan2(d1, d) * 180D) / Math.PI) - 90F;
			hasAttacked = true;
		}
	}

	@Override
	protected String getLivingSound() {
		return "";
	}

	@Override
	protected String getHurtSound() {
		return "automatons.thunk";
	}

	@Override
	protected String getDeathSound() {
		return "automatons.thunk";
	}

	@Override
	public void onDeath(DamageSource damagesource) {

		super.onDeath(damagesource);

		if (!Universal.improperWorld(worldObj)) {
			dropper();// a(field_34905_c > 0);
		}

		this.setDead();
	}

	protected void dropper() {
		int i = rand.nextInt(3);

		for (int j = 0; j < i; j++) {
			dropItem(Ids.blockCrystal, 1);
		}
	}
	@Override
	protected int getDropItemId() {
		return 0;
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}

	@Override
	public String getTexture() {
		return WATCHER_PNG;
	}
}
