package riseautomatons.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import riseautomatons.Universal;

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

	public EntityLaser(World par1World, EntityLiving host, EntityLiving target, float par4, float par5) {
		super(par1World);
		this.shootingEntity = host;
		// this.doesArrowBelongToPlayer = host instanceof EntityPlayer;
		this.posY = host.posY + (double) host.getEyeHeight() - 0.10000000149011612D;
		double var6 = target.posX - host.posX;
		double var8 = target.posY + (double) target.getEyeHeight() - 1 - this.posY;
		double var10 = target.posZ - host.posZ;
		double var12 = (double) MathHelper.sqrt_double(var6 * var6 + var10 * var10);
		setSize(0.2F, 0.2F);
		if (var12 >= 1.0E-7D) {
			float var14 = (float) (Math.atan2(var10, var6) * 180.0D / Math.PI) - 90.0F;
			float var15 = (float) (-(Math.atan2(var8, var12) * 180.0D / Math.PI));
			double var16 = var6 / var12;
			double var18 = var10 / var12;
			this.setLocationAndAngles(host.posX + var16, this.posY, host.posZ + var18, var14, var15);
			this.yOffset = 0.0F;
			float var20 = (float) var12 * 0.2F;
			this.setArrowHeading(var6, var8 + (double) var20, var10, par4, par5);
		}
	}

	public void setArrowHeading(double par1, double par3, double par5, float par7, float par8) {
		float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= (double) var9;
		par3 /= (double) var9;
		par5 /= (double) var9;
		par1 += this.rand.nextGaussian() * 0.007499999832361937D * (double) par8;
		par3 += this.rand.nextGaussian() * 0.007499999832361937D * (double) par8;
		par5 += this.rand.nextGaussian() * 0.007499999832361937D * (double) par8;
		par1 *= (double) par7;
		par3 *= (double) par7;
		par5 *= (double) par7;
		this.motionX = par1;
		this.motionY = par3;
		this.motionZ = par5;
		float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3, (double) var10) * 180.0D / Math.PI);
		// this.ticksInGround = 0;
	}

	public EntityLaser(World world, double d, double d1, double d2, double d3, double d4, double d5) {
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

	public EntityLaser(World world, EntityLiving entityliving, double dx, double dy, double dz, double accuracy) {
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = 0;
		inGround = false;
		shake = 0;
		ticksFlying = 0;

		shootingEntity = entityliving;
		setSize(0.5F, 0.5F);
		setLocationAndAngles(entityliving.posX, entityliving.posY, entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
		yOffset = 0.0F;
		motionX = motionY = motionZ = 0.0D;
		// double accuracy=0.2D;
		dx += rand.nextGaussian() * accuracy;
		dy += rand.nextGaussian() * accuracy;
		dz += rand.nextGaussian() * accuracy;
		double d3 = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
		motionX = (dx / d3) * 1.5D;
		motionY = (dy / d3) * 1.5D;
		motionZ = (dz / d3) * 1.5D;
		accelerationX = (dx / d3) * 0.50000000000000001D;
		accelerationY = (dy / d3) * 0.50000000000000001D;
		accelerationZ = (dz / d3) * 0.50000000000000001D;
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
		if(!this.worldObj.isRemote) {
			worldObj.spawnParticle("reddust", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
			double x = (this.motionX);
			double y = (this.motionY);
			double z = (this.motionZ);
			if(x * x + y * y + z * z < 0.0001D) {
				this.setDead();
			}
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.0F;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {

		setBeenAttacked();
		Entity entity = par1DamageSource.getSourceOfDamage();

		if (entity != null) {
			if (entity instanceof EntityGuard) {
				return false;
			}

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
		}
	}

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
			if (var1.entityHit == shootingEntity) {
				return;
			}
			if (var1.entityHit instanceof EntityLiving) {
				var1.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(var1.entityHit, shootingEntity), 2);

				if(!this.worldObj.isRemote) {
					for (int j = 0; j < 5; j++) {
						double dh = rand.nextGaussian() * 0.1D;
						double dh1 = rand.nextGaussian() * 0.1D;
						double dh2 = rand.nextGaussian() * 0.1D;
						worldObj.spawnParticle("largesmoke", (posX + (double) (rand.nextFloat() * 2.0F)) - 1F, posY + (double) (rand.nextFloat() * 2.0F) - 1F, (posZ + (double) (rand.nextFloat() * 2.0F)) - 1F, dh, dh1, dh2);
					}
				}
			}
		}

		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}

}
