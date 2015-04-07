package riseautomatons.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.Universal;
import riseautomatons.block.Blocks;

public class EntityGolemNormal extends EntityAniBot implements IBot,
		IBehaviorDispenseItem {

	public static final ResourceLocation GOLEM_PNG = new ResourceLocation("riseautomatons", "textures/entities/agol3.png");
	public static int renderId;
	public Block blockType = Blocks.air;
	public int colo = 0;

	public EntityGolemNormal(World par1World) {
		super(par1World);
		setHealth(getMaxHealth());
	}

	public EntityGolemNormal(World world, double d, double d1, double d2, Block I,
			int h, int dam) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		setPathToEntity(null);
		setType(I.getUnlocalizedName());
		setColo(dam);
		setHealth(h);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(h);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4F);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0D);
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
		if (entity instanceof EntityGolemNormal) {
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
		nbttagcompound.setInteger("type", 0);
		nbttagcompound.setInteger("colo", getColo());
        Block block = getType();
        String str = block == null ? Blocks.air.getUnlocalizedName() : block.getUnlocalizedName();
        nbttagcompound.setString("blockType", str);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		nbttagcompound.getInteger("type");
		setColo(nbttagcompound.getInteger("colo"));
        setType(nbttagcompound.getString("blockType"));
	}

	@Override
	protected Entity findPlayerToAttack() {
		if (fleeingTick > 0) {
			return null;
		}

		float f = 8F;
		List list1 = worldObj.getEntitiesWithinAABB(
				EntityPlayer.class,
				boundingBox.expand(f, f, f));

		Block T = getType();
		for (int j = 0; j < list1.size(); j++) {
			EntityPlayer entityplayer = (EntityPlayer) list1.get(j);

			if (entityplayer.getCurrentEquippedItem() != null
					&& entityplayer.getCurrentEquippedItem().getItem() == Item.getItemFromBlock(T)) {
				return entityplayer;
			}
		}
		return null;
	}

	protected Block getType() {
		String str  = dataWatcher.getWatchableObjectString(17);
        return Block.getBlockFromName(str);
	}

	protected void setType(String str) {
        Block block = Block.getBlockFromName(str);
		blockType = block;
        dataWatcher.updateObject(16, 0);
        dataWatcher.updateObject(17, str);
	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, 0);
		dataWatcher.addObject(14, Integer.valueOf(colo));
        String str = blockType == null ? Blocks.air.getUnlocalizedName() : blockType.getUnlocalizedName();
        dataWatcher.addObject(17, str);
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
					(posX + (rand.nextFloat() * width * 2.0F))
							- width, posY
							+ (rand.nextFloat() * height),
					(posZ + (rand.nextFloat() * width * 2.0F))
							- width, d, d1, d2);
		}

		if (!Universal.improperWorld(worldObj)) {

			entityDropItem(item(), 0);
			int xx = MathHelper.floor_double(posX);
			int yy = MathHelper.floor_double(posY);
			int zz = MathHelper.floor_double(posZ);
			Material mat = worldObj.getBlock(xx, yy, zz).getMaterial();

			Block b =  getType();
			if(b == null) {
				b = Blocks.frass;
			}
			if (b.canPlaceBlockAt(worldObj, xx, yy, zz)) {
				worldObj.setBlock(xx, yy, zz, b,
						getColo(), 3);
			}

			setDead();
		}
	}

	public ItemStack item() {
		return new ItemStack(Ids.soulCore, 1, 0);
	}

	protected int getColo() {
		return dataWatcher.getWatchableObjectInt(14);
	}

	protected void setColo(int i) {
		colo = i;
		dataWatcher.updateObject(14, Integer.valueOf(i));
	}

	@Override
    protected Item getDropItem() {
        return null;
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
	public ResourceLocation getTexture() {
		return GOLEM_PNG;
	}

}
