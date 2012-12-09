package riseautomatons.common.entity;

import java.util.List;

import riseautomatons.common.Ids;
import riseautomatons.common.Universal;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAgeable;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBehaviorDispenseItem;
import net.minecraft.src.IBlockSource;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityGolem extends EntityAniBot implements IBot,
		IBehaviorDispenseItem {

	public static final String GOLEM_PNG = "/riseautomatons/agol1.png";
	public static int renderId;
	int maxHealth = 5;
	public int type = 0;
	public int colo = 0;

	public EntityGolem(World par1World) {
		super(par1World);
		health = 5;
		maxHealth = 5;
		texture = GOLEM_PNG;
	}

	public EntityGolem(World world, double d, double d1, double d2, int I,
			int h, int dam) {
		this(world);
		setPosition(d, d1 + (double) yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		setPathToEntity(null);
		setType(I);
		setColo(dam);
		health = h;
		maxHealth = h;
	}

	@Override
	public int getMaxHealth() {
		return maxHealth;
	}

	@Override
	public void onLivingUpdate() {
		derk();
		if (isWet()) {
			dropper();
		}

		super.onLivingUpdate();
	}

	public void derk() {
		fleeingTick = 0;
	}

	@Override
	protected void attackEntity(Entity entity, float par2) {
		if (entity instanceof EntityGolem) {
			if (attackTime <= 0 && par2 < 2.0F
					&& entity.boundingBox.maxY > boundingBox.minY
					&& entity.boundingBox.minY < boundingBox.maxY) {
				attackTime = 10;
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), 1);
			}
		} else {
			entityToAttack = null;
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("type", getType());
		nbttagcompound.setInteger("colo", getColo());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		setType(nbttagcompound.getInteger("type"));
		setColo(nbttagcompound.getInteger("colo"));
	}

	@Override
	protected Entity findPlayerToAttack() {
		if (fleeingTick > 0) {
			return null;
		}

		float f = 8F;
		List list1 = worldObj.getEntitiesWithinAABB(
				net.minecraft.src.EntityPlayer.class,
				boundingBox.expand(f, f, f));

		int T = getType();
		for (int j = 0; j < list1.size(); j++) {
			EntityPlayer entityplayer = (EntityPlayer) list1.get(j);

			if (entityplayer.getCurrentEquippedItem() != null
					&& entityplayer.getCurrentEquippedItem().itemID == T) {
				return entityplayer;
			}
		}
		return null;
	}

	protected int getType() {
		return Universal.getInt(dataWatcher, 16);
	}

	protected void setType(int i) {
		type = i;
		dataWatcher.updateObject(16, Integer.valueOf(i));
	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, Integer.valueOf(type));
		dataWatcher.addObject(17, Integer.valueOf(colo));
	}

	@Override
	protected String getLivingSound() {
		return "";
	}

	@Override
	protected String getHurtSound() {
		return "step.stone";
	}

	@Override
	protected String getDeathSound() {
		return "step.gravel";
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);

		if (!Universal.improperWorld(worldObj)) {
			dropper();// a(field_34905_c > 0);
		}

		setDead();
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

			entityDropItem(item(), 0);
			int xx = MathHelper.floor_double(posX);
			int yy = MathHelper.floor_double(posY);
			int zz = MathHelper.floor_double(posZ);
			Material mat = worldObj.getBlockMaterial(xx, yy, zz);
			int iii = getType();

			if(Block.blocksList[iii] != null) {
				if (Block.blocksList[iii].canPlaceBlockAt(worldObj, xx, yy, zz)) {
					worldObj.setBlockAndMetadataWithNotify(xx, yy, zz, iii,
							getColo());
				}
			}

			setDead();
		}
	}

	public ItemStack item() {
		return new ItemStack(Ids.soulCore, 1, 0);
	}

	protected int getColo() {
		return Universal.getInt(dataWatcher, 17);
	}

	protected void setColo(int i) {
		colo = i;
		dataWatcher.updateObject(17, Integer.valueOf(i));
	}

	@Override
	protected int getDropItemId() {
		return 0;
	}

	@Override
	public boolean canBreatheUnderwater() {
		//
		return super.canBreatheUnderwater();
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 20;
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {
		return par1Entity.boundingBox;
	}

	@Override
	public ItemStack dispense(IBlockSource var1, ItemStack var2) {
		return null;
	}

	@Override
	public EntityAgeable func_90011_a(EntityAgeable var1) {
		return null;
	}

	@Override
	public String getTexture() {
		return GOLEM_PNG;
	}

}
