package riseautomatons.entity;

import riseautomatons.Ids;
import riseautomatons.RiseAutomatons;
import riseautomatons.Universal;
import riseautomatons.block.TileEntityLatch;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityTote extends EntityOwnedBot implements IInventory, IBot {

	@Override
	public EnumBotMode getMode() {
		return EnumBotMode.FOLLOW;
	}
	@Override
	public boolean getAvoidsWater() {
		return true;
	}
	public static final ResourceLocation TOTE_PNG = new ResourceLocation("riseautomatons", "textures/entities/tote.png");
	public ItemStack cargoItems[] = new ItemStack[9];
	double angle = 90;
	double dir = -10;

	public EntityTote(World world)
	{
		super(world);
		setSize(0.4F, 0.5F);
		moveSpeed = 0.2F;
		setHealth(6);

		getNavigator().setAvoidsWater(true);
		tasks.addTask(5, new EntityAIBotFollowOwner(this, moveSpeed, 4F, 2.0F));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
	}
	public EntityTote(World world, double d, double d1, double d2, String s)
	{
		this(world);
		setPosition(d, d1 + (double)yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		setPathToEntity(null);
		setBotOwner(s);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);

		NBTTagList nbttaglist = new NBTTagList();
		for(int i = 0; i < cargoItems.length; i++){
			if(cargoItems[i] != null){
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				cargoItems[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbttagcompound.setTag("Items", nbttaglist);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);

		NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
		cargoItems = new ItemStack[getSizeInventory()];
		for(int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 0xff;
			if(j >= 0 && j < cargoItems.length)
			{
				cargoItems[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	protected String getLivingSound() {
		return "automatons.beep";
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
	public float getEyeHeight() {
		return height * 0.8F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (isWet()) {
			if (isEntityAlive()) {
				dropper();
			}
		}
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);
		if (!Universal.improperWorld(worldObj)) {
			dropper();
		}
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public void setDead() {
		super.setDead();
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		par1EntityPlayer.openGui(RiseAutomatons.instance, Ids.guiTote, worldObj, (int)posX, (int)posY, (int)posZ);
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {
		return par1Entity.boundingBox;
	}

	@Override
	public int getSizeInventory() {
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return cargoItems[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {

		if (cargoItems[i] != null)
		{
			if (cargoItems[i].stackSize <= j)
			{
				ItemStack itemstack = cargoItems[i];
				cargoItems[i] = null;
				return itemstack;
			}

			ItemStack itemstack1 = cargoItems[i].splitStack(j);

			if (cargoItems[i].stackSize == 0)
			{
				cargoItems[i] = null;
			}

			return itemstack1;
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		cargoItems[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		return "Tote";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onInventoryChanged() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openChest() {
		dir = -10;
	}

	@Override
	public void closeChest() {
		dir = 2.5;
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

			int xx = MathHelper.floor_double(posX);
			int yy = MathHelper.floor_double(posY);
			int zz = MathHelper.floor_double(posZ);
			int id = worldObj.getBlockId(xx, yy, zz);

			if (id == 0
					|| Block.blocksList[id].getCollisionBoundingBoxFromPool(
							worldObj, xx, yy, zz) == null) {
				worldObj.setBlock(xx, yy, zz, Ids.blockTote, 0, 3);
				TileEntityLatch latch = (TileEntityLatch) worldObj
						.getBlockTileEntity(xx, yy, zz);
				latch.dispenserContents = cargoItems.clone();
				cargoItems = null;
				// inv=null;
			} else {
				entityitem = new EntityItem(worldObj, posX, posY, posZ,
						new ItemStack(Ids.itemTote, 1, 0));
				worldObj.spawnEntityInWorld(entityitem);
				dropInventory();
			}
			setDead();
		}
	}
	private void dropInventory() {
		label9768:
		for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack itemstack = getStackInSlot(i);
			if (itemstack == null) {
				continue;
			}
			float f = rand.nextFloat() * 0.8F + 0.1F;
			float f1 = rand.nextFloat() * 0.8F + 0.1F;
			float f2 = rand.nextFloat() * 0.8F + 0.1F;
			do {
				if (itemstack.stackSize <= 0) {
					continue label9768;
				}
				int j = rand.nextInt(21) + 10;
				if (j > itemstack.stackSize) {
					j = itemstack.stackSize;
				}
				itemstack.stackSize -= j;
				EntityItem entityitem = new EntityItem(worldObj, posX
						+ (double) f, posY + (double) f1, posZ + (double) f2,
						new ItemStack(itemstack.itemID, j,
								itemstack.getItemDamage()));
				float f3 = 0.05F;
				entityitem.motionX = (float) rand.nextGaussian() * f3;
				entityitem.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
				entityitem.motionZ = (float) rand.nextGaussian() * f3;
				worldObj.spawnEntityInWorld(entityitem);
			} while (true);
		}
	}
	@Override
	public boolean isInvNameLocalized() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public ResourceLocation getTexture() {
		return TOTE_PNG;
	}
}
