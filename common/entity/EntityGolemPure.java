package riseautomatons.common.entity;

import java.util.List;

import riseautomatons.common.Ids;
import riseautomatons.common.Universal;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityAITempt;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityGolemPure extends EntityGolem implements IBot {

	public static final String GOLEM_PURE_PNG = "/riseautomatons/agol1.png";
	public static final String GOLEM_PURE_BI_PNG = "/riseautomatons/agol1.png";
	public static final String GOLEM_PURE_LONG_PNG = "/riseautomatons/agol1.png";
	public int form = 0;

	public EntityGolemPure(World par1World) {
		super(par1World);
		health = 5;
		maxHealth = 5;
		setForm(worldObj.rand.nextInt(3));
	}

	public EntityGolemPure(World world, double d, double d1, double d2, int I,
			int h, int dam) {
		super(world, d, d1, d2, I, h, dam);
		setForm(worldObj.rand.nextInt(3));
	}

	public EntityGolemPure(int u, World world, double d, double d1, double d2,
			int I, int h, int dam) {
		super(world, d, d1, d2, I, h, dam);
		setForm(u);
	}

	@Override
	public void derk() {
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("form", getForm());
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

		list1 = worldObj.getEntitiesWithinAABB(EntityGolem.class,
				boundingBox.expand(f, f, f));
		for (int j = 0; j < list1.size(); j++) {
			EntityGolem e = (EntityGolem) list1.get(j);

			if (!(e instanceof EntityGolemPure) && e.getType() == T) {
				return e;
			}
		}

		return null;
	}

	@Override
	protected void setType(int i) {
		this.tasks.addTask(1, new EntityAITempt(this, 0.25F, i, false));
		super.setType(i);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(18, Integer.valueOf(form));
	}

	@Override
	public ItemStack item() {
		return new ItemStack(Ids.soulCore, 1, 5);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}

	@Override
	public boolean isOnLadder() {
		return isCollidedHorizontally && getForm() == 0;
	}

	protected int getForm() {
		return dataWatcher.getWatchableObjectInt(18);
	}

	protected void setForm(int i) {
		form = i;
		dataWatcher.updateObject(18, Integer.valueOf(i));

		float var7;
		float var8;
		switch (i) {
		case 1:
			texture = GOLEM_PURE_LONG_PNG;
			set(0.2F, 1.5F);
			break;
		case 2:
			texture = GOLEM_PURE_BI_PNG;
			set(0.25f, 1.5f);
			break;
		default:
			texture = GOLEM_PURE_PNG;
			set(1.2f, 0.8f);
		}
	}

	protected void set(float x, float y) {
		setSize(x, y);
		float var7 = x / 2f;
		float var8 = y;
		this.boundingBox.setBounds(posX - (double) var7, posY
				- (double) this.yOffset + (double) this.ySize, posZ
				- (double) var7, posX + (double) var7, posY
				- (double) this.yOffset + (double) this.ySize + (double) var8,
				posZ + (double) var7);

	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		setForm(nbttagcompound.getInteger("form"));
	}

	@Override
	public String getTexture() {
		return texture;
	}

}
