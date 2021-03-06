package riseautomatons.entity;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.RiseAutomatons;
import riseautomatons.Universal;

public class EntityFactotum extends EntityOwnedBot implements IInventory, IBot {

	public static final ResourceLocation FACTOTUM1_PNG = new ResourceLocation("riseautomatons", "textures/entities/factotum1.png");
	public static final ResourceLocation FACTOTUM2_PNG = new ResourceLocation("riseautomatons", "textures/entities/factotum2.png");
	public static final ResourceLocation FACTOTUM3_PNG = new ResourceLocation("riseautomatons", "textures/entities/factotum3.png");
	public static int renderId;
	public int furnaceBurnTime;
	public int currentItemBurnTime;
	public int furnaceCookTime;

	private ItemStack cargoItems[];
	private ItemStack furnaceItemStacks[];
	private int who2Cook = 0;
	private int who2Burn = 3;
	private int availableSlot2 = 6;
	private final float moveSpeed = 0.5F;

	public EntityFactotum(World world) {
		super(world);
		cargoItems = new ItemStack[21];
		setSize(2F, 2F);
		setHealth(getMaxHealth());
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(moveSpeed);
		isImmuneToFire = true;
		furnaceBurnTime = 0;
		currentItemBurnTime = 0;
		furnaceCookTime = 0;
		tasks.addTask(3, new EntityAILeapAtTarget(this, moveSpeed)); // 0.4f
		tasks.addTask(5, new EntityAIBotFollowOwner(this, moveSpeed, 8F, 4.0F));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
	}

	public EntityFactotum(World world, double d, double e, double f,
			String username) {
		super(world);
		cargoItems = new ItemStack[21];
		setSize(2F, 2F);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(moveSpeed);
		setHealth(getMaxHealth());
		isImmuneToFire = true;
		furnaceBurnTime = 0;
		currentItemBurnTime = 0;
		furnaceCookTime = 0;
		setPosition(d, e + yOffset, f);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = e;
		prevPosZ = f;
		setPathToEntity(null);
		setBotOwner(username);
		tasks.addTask(3, new EntityAILeapAtTarget(this, moveSpeed)); // 0.4f
		tasks.addTask(5, new EntityAIBotFollowOwner(this, moveSpeed, 8F, 4.0F));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(moveSpeed);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
	}

	@Override
	public int getSizeInventory() {
		return 21;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return cargoItems[i];
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public EnumBotMode getMode() {
		return EnumBotMode.FOLLOW;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {

		if (cargoItems[i] != null) {
			if (cargoItems[i].stackSize <= j) {
				ItemStack itemstack = cargoItems[i];
				cargoItems[i] = null;
				return itemstack;
			}

			ItemStack itemstack1 = cargoItems[i].splitStack(j);

			if (cargoItems[i].stackSize == 0) {
				cargoItems[i] = null;
			}

			return itemstack1;
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {

		cargoItems[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return "Factotum";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {

		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setShort("BurnTime", (short) furnaceBurnTime);
		nbttagcompound.setShort("CookTime", (short) furnaceCookTime);

		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < cargoItems.length; i++) {
			if (cargoItems[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				cargoItems[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbttagcompound.setTag("Items", nbttaglist);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);

		NBTTagList nbttaglist = (NBTTagList)nbttagcompound.getTag("Items");
		cargoItems = new ItemStack[getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
					.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 0xff;
			if (j >= 0 && j < cargoItems.length) {
				cargoItems[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		furnaceBurnTime = nbttagcompound.getShort("BurnTime");
		furnaceCookTime = nbttagcompound.getShort("CookTime");
		currentItemBurnTime = checkBurn2();
	}

	@Override
	protected void updateWanderPath() {
	}

	@Override
	public ResourceLocation getTexture() {
		if (furnaceBurnTime > 0) {
			if ((furnaceBurnTime % 2) == 1) {
				return FACTOTUM2_PNG;
			} else {
				return FACTOTUM3_PNG;
			}
		}

		return FACTOTUM1_PNG;
	}

	@Override
	public void onDeath(DamageSource damagesource) {

		super.onDeath(damagesource);
		if (!Universal.improperWorld(worldObj)) {
			dropper();// a(field_34905_c > 0);
		}
	}

	@Override
	public void setDead() {
		label98:
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
					continue label98;
				}
				int j = rand.nextInt(21) + 10;
				if (j > itemstack.stackSize) {
					j = itemstack.stackSize;
				}
				itemstack.stackSize -= j;
				EntityItem entityitem = new EntityItem(worldObj, posX
						+ f, posY + f1, posZ + f2,
						new ItemStack(itemstack.getItem(), j,
								itemstack.getItemDamage()));
				float f3 = 0.05F;
				entityitem.motionX = (float) rand.nextGaussian() * f3;
				entityitem.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
				entityitem.motionZ = (float) rand.nextGaussian() * f3;
				worldObj.spawnEntityInWorld(entityitem);
			} while (true);
		}
		super.setDead();
	}

	@Override
	public boolean interact(EntityPlayer entityPlayer) {

		entityPlayer.openGui(RiseAutomatons.instance, Ids.guiFactotum, worldObj, (int)posX, (int)posY, (int)posZ);
		//Universal.factotumGui(entityplayer, this);
		return true;
	}

	@Override
	public boolean isBurning() {
		return furnaceBurnTime > 0;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected String getLivingSound() {
		return "riseautomatons:techy";
	}

	@Override
	protected String getHurtSound() {
		return "riseautomatons:thunk";
	}

	@Override
	protected String getDeathSound() {
		return "random.glass";
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
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
			entityDropItem(new ItemStack(Ids.itemFactotum, 1, 0), 0.0F);
			setDead();
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		updateBurnin();
	}

	public int getCookProgressSc(int i) {
		return (furnaceCookTime * i) / 200;
	}

	public int getBurnTimeRemainingSc(int i) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = 200;
		}

		return (furnaceBurnTime * i) / currentItemBurnTime;
	}

	private boolean icanSmelt() {
		availableSlot2 = 6;
		who2Cook = 0;

		if (cargoItems[0] == null && cargoItems[1] == null
				&& cargoItems[2] == null) {
			return false;
		}

		ItemStack itemstack1 = null;
		ItemStack itemstack2 = null;
		ItemStack itemstack3 = null;

		if (cargoItems[0] != null) {
			itemstack1 = FurnaceRecipes.smelting().getSmeltingResult(
					cargoItems[0]);
		}

		if (cargoItems[1] != null) {
			itemstack2 = FurnaceRecipes.smelting().getSmeltingResult(
					cargoItems[1]);
		}

		if (cargoItems[2] != null) {
			itemstack3 = FurnaceRecipes.smelting().getSmeltingResult(
					cargoItems[2]);
		}

		ItemStack itemstack;

		if (itemstack1 != null) {
			who2Cook = 0;
			itemstack = itemstack1;
		} else if (itemstack2 != null) {
			who2Cook = 1;
			itemstack = itemstack2;
		} else if (itemstack3 != null) {
			who2Cook = 2;
			itemstack = itemstack3;
		} else {
			return false;
		}

		while (availableSlot2 < 21) {
			if (cargoItems[availableSlot2] == null) {
				return true;
			}

			if (cargoItems[availableSlot2].isItemEqual(itemstack)) {
				if (cargoItems[availableSlot2].stackSize < getInventoryStackLimit()
						&& cargoItems[availableSlot2].stackSize < cargoItems[availableSlot2]
								.getMaxStackSize()) {
					return true;
				}
			}

			availableSlot2++;
		}

		return false;
	}

	public void updateBurnin() {
		boolean flag = furnaceBurnTime > 0;
		boolean flag1 = false;

		if (furnaceBurnTime > 0) {
			furnaceBurnTime -= 3;
		}

		if (!Universal.improperWorld(worldObj)) {
			if (furnaceBurnTime <= 0 && icanSmelt()) {
				furnaceBurnTime = checkBurn2();
				currentItemBurnTime = furnaceBurnTime;

				if (furnaceBurnTime > 0) {
					flag1 = true;

					if (cargoItems[who2Burn] != null) {
						if (cargoItems[who2Burn].getItem().hasContainerItem()) {
							cargoItems[who2Burn] = new ItemStack(
									cargoItems[who2Burn].getItem()
											.getContainerItem());
						} else {
							cargoItems[who2Burn].stackSize--;
						}

						if (cargoItems[who2Burn].stackSize == 0) {
							cargoItems[who2Burn] = null;
						}
					}
				}
			}

			if (isBurning() && icanSmelt()) {
				furnaceCookTime += 3;

				if (furnaceCookTime >= 200) {
					furnaceCookTime = 0;
					smeltItem2();
					flag1 = true;
				}
			} else {
				furnaceCookTime = 0;
			}

			if (flag != (furnaceBurnTime > 0)) {
				flag1 = true;
				setIsSmelting2(furnaceBurnTime > 0);
			}
		}

		if (flag1) {
			markDirty();
		}
	}

	private int checkBurn2() {
		who2Burn = 3;
		int temp = 0;

		while (who2Burn <= 5) {
			temp = getItemBurnTime2(cargoItems[who2Burn]);
			if (temp > 0) {
				return temp;
			}

			who2Burn++;
		}

		return 0;
	}

	public void smeltItem2() {
		if (!icanSmelt()) {
			return;
		}

		if (cargoItems[who2Cook] == null) {
			return;
		}

		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(
				cargoItems[who2Cook]);

		if (cargoItems[availableSlot2] == null) {
			cargoItems[availableSlot2] = itemstack.copy();
		} else if (cargoItems[availableSlot2] == itemstack) {
			cargoItems[availableSlot2].stackSize += itemstack.stackSize;
		}

		if (cargoItems[who2Cook].getItem().hasContainerItem()) {
			cargoItems[who2Cook] = new ItemStack(cargoItems[who2Cook].getItem()
					.getContainerItem());
		} else {
			cargoItems[who2Cook].stackSize--;
		}

		if (cargoItems[who2Cook].stackSize <= 0) {
			cargoItems[who2Cook] = null;
		}
	}

	private void setIsSmelting2(boolean b) {
	}

	public static int getItemBurnTime2(ItemStack par1ItemStack) {
        return TileEntityFurnace.getItemBurnTime(par1ItemStack);
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
