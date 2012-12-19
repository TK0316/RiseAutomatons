package riseautomatons.common.entity;

import java.util.List;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityThrowable;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;
import riseautomatons.common.Universal;

public class EntityLaser extends EntityThrowable {

	private int xTile;
	private int yTile;
	private int zTile;
	private int inTile;
	private boolean inGround;
	public int shake;
	public EntityLiving shootingEntity;
	private int ticksAlive;
	private int ticksFlying;
	public double accelerationX;
	public double accelerationY;
	public double accelerationZ;

	public EntityLaser(World world) {
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inGround = false;
		shake = 0;
		ticksFlying = 0;
		setSize(0.2F, 0.2F);
	}

	public EntityLaser(World par1World, EntityLiving host,
			EntityLiving target, float par4, float par5) {
		super(par1World);
		this.shootingEntity = host;
		// this.doesArrowBelongToPlayer = host instanceof EntityPlayer;
		this.posY = host.posY + (double) host.getEyeHeight()
				- 0.10000000149011612D;
		double var6 = target.posX - host.posX;
		double var8 = target.posY + (double) target.getEyeHeight() - 1
				- this.posY;
		double var10 = target.posZ - host.posZ;
		double var12 = (double) MathHelper.sqrt_double(var6 * var6 + var10
				* var10);
		setSize(0.2F, 0.2F);
		if (var12 >= 1.0E-7D) {
			float var14 = (float) (Math.atan2(var10, var6) * 180.0D / Math.PI) - 90.0F;
			float var15 = (float) (-(Math.atan2(var8, var12) * 180.0D / Math.PI));
			double var16 = var6 / var12;
			double var18 = var10 / var12;
			this.setLocationAndAngles(host.posX + var16, this.posY, host.posZ
					+ var18, var14, var15);
			this.yOffset = 0.0F;
			float var20 = (float) var12 * 0.2F;
			this.setArrowHeading(var6, var8 + (double) var20, var10, par4, par5);
		}
	}

	public void setArrowHeading(double par1, double par3, double par5,
			float par7, float par8) {
		float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5
				* par5);
		par1 /= (double) var9;
		par3 /= (double) var9;
		par5 /= (double) var9;
		par1 += this.rand.nextGaussian() * 0.007499999832361937D
				* (double) par8;
		par3 += this.rand.nextGaussian() * 0.007499999832361937D
				* (double) par8;
		par5 += this.rand.nextGaussian() * 0.007499999832361937D
				* (double) par8;
		par1 *= (double) par7;
		par3 *= (double) par7;
		par5 *= (double) par7;
		this.motionX = par1;
		this.motionY = par3;
		this.motionZ = par5;
		float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1,
				par5) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3,
				(double) var10) * 180.0D / Math.PI);
		// this.ticksInGround = 0;
	}

	public EntityLaser(World world, double d, double d1, double d2,
			double d3, double d4, double d5) {
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inGround = false;
		shake = 0;
		ticksFlying = 0;
		setSize(0.2F, 0.2F);
		setLocationAndAngles(d, d1, d2, rotationYaw, rotationPitch);
		setPosition(d, d1, d2);
		double d6 = MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
		accelerationX = (d3 / d6) * 0.10000000000000001D;
		accelerationY = (d4 / d6) * 0.10000000000000001D;
		accelerationZ = (d5 / d6) * 0.10000000000000001D;
	}

	public EntityLaser(World world, EntityLiving entityliving, double d,
			double d1, double d2, double accuracy) {
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inGround = false;
		shake = 0;
		ticksFlying = 0;
		shootingEntity = entityliving;
		setSize(0.2F, 0.2F);
		setLocationAndAngles(entityliving.posX, entityliving.posY,
				entityliving.posZ, entityliving.rotationYaw,
				entityliving.rotationPitch);
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = motionY = motionZ = 0.0D;
		// double accuracy=0.2D;
		d += rand.nextGaussian() * accuracy;
		d1 += rand.nextGaussian() * accuracy;
		d2 += rand.nextGaussian() * accuracy;
		double d3 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
		accelerationX = (d / d3) * 0.50000000000000001D;
		accelerationY = (d1 / d3) * 0.50000000000000001D;
		accelerationZ = (d2 / d3) * 0.50000000000000001D;
	}

	@Override
	protected void entityInit() {
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {

		xTile = nbttagcompound.getShort("xTile");
		yTile = nbttagcompound.getShort("yTile");
		zTile = nbttagcompound.getShort("zTile");
		inTile = nbttagcompound.getByte("inTile") & 0xff;
		shake = nbttagcompound.getByte("shake") & 0xff;
		inGround = nbttagcompound.getByte("inGround") == 1;

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {

		nbttagcompound.setShort("xTile", (short) xTile);
		nbttagcompound.setShort("yTile", (short) yTile);
		nbttagcompound.setShort("zTile", (short) zTile);
		nbttagcompound.setByte("inTile", (byte) inTile);
		nbttagcompound.setByte("shake", (byte) shake);
		nbttagcompound.setByte("inGround", (byte) (inGround ? 1 : 0));

	}

	@Override
	public void onUpdate() {

		super.onUpdate();

		// fire = 10;
		if (shake > 0) {
			shake--;
		}

		if (inGround) {
			int i = worldObj.getBlockId(xTile, yTile, zTile);

			if (i != inTile) {
				inGround = false;
				motionX *= rand.nextFloat() * 0.2F;
				motionY *= rand.nextFloat() * 0.2F;
				motionZ *= rand.nextFloat() * 0.2F;
				ticksAlive = 0;
				ticksFlying = 0;
			} else {
				ticksAlive++;

				if (ticksAlive >= 200) {
					setDead();
				}

				return;
			}
		} else {
			ticksFlying++;
			if (ticksFlying >= 40) {
				setDead();
			}
		}

		Vec3 vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ
				+ motionZ);
		MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(
				vec3d, vec3d1);
		vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ
				+ motionZ);

		if (movingobjectposition != null) {
			vec3d1 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord,
					movingobjectposition.hitVec.yCoord,
					movingobjectposition.hitVec.zCoord);
		}

		Entity entity = null;
		List list = worldObj
				.getEntitiesWithinAABBExcludingEntity(this, boundingBox
						.addCoord(posX, posY, posZ).expand(1.0D, 1.0D, 1.0D));
		double d = 0.0D;

		for (int j = 0; j < list.size(); j++) {
			Entity entity1 = (Entity) list.get(j);

			if (!entity1.canBeCollidedWith() || entity1 == shootingEntity
					&& ticksFlying < 25) {
				continue;
			}

			float f2 = 0.3F;
			AxisAlignedBB axisalignedbb = entity1.boundingBox
					.expand(f2, f2, f2);
			MovingObjectPosition movingobjectposition1 = worldObj
					.rayTraceBlocks(vec3d, vec3d1);

			if (movingobjectposition1 == null) {
				continue;
			}

			double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);

			if (d1 < d || d == 0.0D) {
				entity = entity1;
				d = d1;
			}
		}

		if (entity != null) {
			movingobjectposition = new MovingObjectPosition(entity);
		}

		if (movingobjectposition != null) {
			if (!Universal.improperWorld(worldObj)) {
				if (movingobjectposition.entityHit != null) {
					// movingobjectposition.entityHit.dealFireDamage(2);
					movingobjectposition.entityHit.attackEntityFrom(
							DamageSource.magic, 2);
				}

				// worldObj.newExplosion(null, posX, posY, posZ, 1.0F, true);
			}

			for (int j = 0; j < 5; j++) {
				double dh = rand.nextGaussian() * 0.1D;
				double dh1 = rand.nextGaussian() * 0.1D;
				double dh2 = rand.nextGaussian() * 0.1D;
				worldObj.spawnParticle("largesmoke",
						(posX + (double) (rand.nextFloat() * 2.0F)) - 1F, posY
								+ (double) (rand.nextFloat() * 2.0F) - 1F,
						(posZ + (double) (rand.nextFloat() * 2.0F)) - 1F, dh,
						dh1, dh2);
			}
			setDead();
		}

		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float) ((Math.atan2(motionX, motionZ) * 180D) / Math.PI);

		for (rotationPitch = (float) ((Math.atan2(motionY, f) * 180D) / Math.PI); rotationPitch
				- prevRotationPitch < -180F; prevRotationPitch -= 360F) {
		}

		for (; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) {
		}

		for (; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) {
		}

		for (; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) {
		}

		rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch)
				* 0.2F;
		rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
		float f1 = 0.95F;

		if (isInWater()) {
			for (int k = 0; k < 4; k++) {
				float f3 = 0.25F;
				worldObj.spawnParticle("bubble", posX - motionX * (double) f3,
						posY - motionY * (double) f3, posZ - motionZ
								* (double) f3, motionX, motionY, motionZ);
			}

			f1 = 0.8F;
		}

		motionX += accelerationX;
		motionY += accelerationY;
		motionZ += accelerationZ;
		motionX *= f1;
		motionY *= f1;
		motionZ *= f1;
		worldObj.spawnParticle("reddust", posX, posY + 0.5D, posZ, 0.0D, 0.0D,
				0.0D);
		setPosition(posX, posY, posZ);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {

		setBeenAttacked();
		Entity entity = par1DamageSource.getSourceOfDamage();

		if (entity != null) {
			Vec3 vec3d = entity.getLookVec();

			if (vec3d != null) {
				motionX = vec3d.xCoord;
				motionY = vec3d.yCoord;
				motionZ = vec3d.zCoord;
				accelerationX = motionX * 0.10000000000000001D;
				accelerationY = motionY * 0.10000000000000001D;
				accelerationZ = motionZ * 0.10000000000000001D;
			}

			return true;
		} else {
			return false;
		}	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean isInRangeToRenderDist(double d) {

		double d1 = boundingBox.getAverageEdgeLength() * 4D;
		d1 *= 64D;
		return d < d1 * d1;
	}

	@Override
	public float getShadowSize() {
		// TODO 自動生成されたメソッド・スタブ
		return super.getShadowSize();
	}

	@Override
	public float getCollisionBorderSize() {
		return 1.0F;
	}

	@Override
	protected void onImpact(MovingObjectPosition var1) {

		if (var1.entityHit != null) {
			if (var1.entityHit instanceof EntityLiving) {
				var1.entityHit.attackEntityFrom(DamageSource.generic, 1);
			}
		}

		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}

}
