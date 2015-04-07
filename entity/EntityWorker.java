package riseautomatons.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import riseautomatons.Coord;
import riseautomatons.Ids;
import riseautomatons.Universal;
import riseautomatons.block.Blocks;
import riseautomatons.block.TileEntityBeacon;
import riseautomatons.item.EnumSoulCore;
import riseautomatons.item.Items;

public class EntityWorker extends EntityOwnedBot implements IBot {

	public static final ResourceLocation GOLEM1_PNG = new ResourceLocation("riseautomatons", "textures/entities/golem1.png");
	public static final ResourceLocation GOLEM2_PNG = new ResourceLocation("riseautomatons", "textures/entities/golem2.png");
	public static final ResourceLocation GOLEM3_PNG = new ResourceLocation("riseautomatons", "textures/entities/golem3.png");
	public static final ResourceLocation GOLEM4_PNG = new ResourceLocation("riseautomatons", "textures/entities/golem4.png");
	public static final ResourceLocation GOLEM5_PNG = new ResourceLocation("riseautomatons", "textures/entities/golem5.png");
	public static final ResourceLocation GOLEM6_PNG = new ResourceLocation("riseautomatons", "textures/entities/golem6.png");

	public static final int INDEX_MODE = 18;
	public static final int INDEX_STATE = 19;
	public static final int INDEX_ITEMID = 20;
	public static final int INDEX_STACKSIZE = 21;
	public static final int INDEX_ITEMDAMAGE = 22;
	public static final int INDEX_HOME = 23;
	public static final int INDEX_DEST = 24;
	float moveSpeed = 0.5F;

	private static Map<Item, Item> target = new LinkedHashMap<Item, Item>();

	enum EnumWorkState {MOVE, CHECK, ACTION, RETURN};
	private int dig;
	TileEntityBeacon home = null;

	private Entity collectTargetItemEntity = null;

	Item getItemID() {
		int itemID = dataWatcher.getWatchableObjectInt(INDEX_ITEMID);
        if(itemID == 0) {
            return null;
        }
        return (Item)Item.itemRegistry.getObjectById(itemID);
    }

	private void setItemID(Item item) {
        int itemID = item == null ? 0 : Item.itemRegistry.getIDForObject(item);
        dataWatcher.updateObject(INDEX_ITEMID, itemID);
	}

	private int getStackSize() {
		return dataWatcher.getWatchableObjectInt(INDEX_STACKSIZE);
	}

	private void setStackSize(int stackSize) {
		dataWatcher.updateObject(INDEX_STACKSIZE, stackSize);
	}

	int getItemDamage() {
		return dataWatcher.getWatchableObjectInt(INDEX_ITEMDAMAGE);
	}

	private void setItemDamage(int itemDamage) {
		dataWatcher.updateObject(INDEX_ITEMDAMAGE, itemDamage);
	}

	private EnumWorkState getState() {
		int state = dataWatcher.getWatchableObjectInt(INDEX_STATE);
		if(0 <= state && state < EnumWorkState.values().length) {
			return EnumWorkState.values()[state];
		}
		return EnumWorkState.MOVE;
	}

	private void setState(EnumWorkState state) {
		dataWatcher.updateObject(INDEX_STATE, state.ordinal());
	}

	void setMode(EnumBotMode mode) {
		dataWatcher.updateObject(INDEX_MODE, mode.ordinal());
		setState(EnumWorkState.MOVE);
	}

	@Override
	public EnumBotMode getMode() {
		int mode = dataWatcher.getWatchableObjectInt(INDEX_MODE);
		if(0 <= mode && mode < EnumBotMode.values().length) {
			return EnumBotMode.values()[mode];
		}
		return EnumBotMode.STAY;
	}

	private void setHomeCoord(Coord home) {
		dataWatcher.updateObject(INDEX_HOME, home.toString());
	}

	public Coord getHomeCoord() {
		String str = dataWatcher.getWatchableObjectString(INDEX_HOME);
		return new Coord(str);
	}

	private void setDestCoord(Coord dest) {
		dataWatcher.updateObject(INDEX_DEST, dest.toString());
	}

	public Coord getDestCoord() {
		String str = dataWatcher.getWatchableObjectString(INDEX_DEST);
		return new Coord(str);
	}


	public EntityWorker(World par1World) {
		super(par1World);
		setSize(0.6F, 0.8F);

		getNavigator().setAvoidsWater(true);
		tasks.addTask(5, new EntityAIBotFollowOwner(this, moveSpeed, 7F, 2.0F));
		tasks.addTask(5, new EntityAIWorkerDig(this));
		tasks.addTask(5, new EntityAIWorkerCollect(this));
		tasks.addTask(7, new EntityAIWorkerWander(this, moveSpeed));
		tasks.addTask(200, new EntityAIWorkerBeacon(this));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
	}

	public EntityWorker(World world, double d, double d1, double d2,int turn, String s)
	{
		this(world);
		setPosition(d, d1 + yOffset, d2);
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		//Universal.rotateEntity(this,turn*90);

		if(s==""){
			setMode(EnumBotMode.PANIC);
			tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		}
		setBotOwner(s);


	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		// getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.35F);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0F);
	}

	public static void init() {
		target.put(Item.getItemFromBlock(Blocks.cobblestone), Item.getItemFromBlock(Blocks.stone));

		target.put(Items.coal, Item.getItemFromBlock(Blocks.coal_ore));
		target.put(Items.dye, Item.getItemFromBlock(Blocks.lapis_ore));
		target.put(Items.emerald, Item.getItemFromBlock(Blocks.emerald_ore));
		target.put(Items.diamond, Item.getItemFromBlock(Blocks.diamond_ore));
		target.put(Items.redstone, Item.getItemFromBlock(Blocks.redstone_ore));
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {

		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		if(itemstack == null) {
			if(reallyGetBotOwner() != entityplayer) {
				return true;
			}
			setItemID(null);
			setStackSize(0);
			setItemDamage(0);
			if(getMode() == EnumBotMode.STAY) {
				setMode(EnumBotMode.FOLLOW);
			}
			else {
				setMode(EnumBotMode.STAY);
			}
		}
		else if(itemstack.getItem() == Ids.soulCore && itemstack.getItemDamage() == EnumSoulCore.SOULSYNTH.ordinal()) {
			if(getBotOwner() == "") {
				setBotOwner(entityplayer.getCommandSenderName());
				setMode(EnumBotMode.FOLLOW);
			}
			else {
				return true;
			}
		}
		// when hand block and right click again, mode chage PICKUP to DIG
		else if(itemstack.getItem() == getItemID()) {
			if(reallyGetBotOwner() != entityplayer) {
				return true;
			}
			if(target.containsKey(itemstack.getItem())) {
				setItemID(target.get(itemstack.getItem()));
			}
			else {
				setItemID(itemstack.getItem());
			}
			setMode(EnumBotMode.DIG);
		}
		else {
			if(reallyGetBotOwner() != entityplayer) {
				return true;
			}
			setMode(EnumBotMode.PICKUP);
			setItemID(itemstack.getItem());
			setStackSize(0);
			setItemDamage(itemstack.getItemDamage());
		}


		Universal.poof(worldObj, posX, posY, posZ);

		isJumping = false;
		setPathToEntity(null);

		return true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(INDEX_MODE, EnumBotMode.STAY.ordinal());
		dataWatcher.addObject(INDEX_STATE, EnumWorkState.MOVE.ordinal());
		dataWatcher.addObject(INDEX_ITEMID, 0);
		dataWatcher.addObject(INDEX_STACKSIZE, 0);
		dataWatcher.addObject(INDEX_ITEMDAMAGE, 0);
		dataWatcher.addObject(INDEX_HOME, "0,0,0");
		dataWatcher.addObject(INDEX_DEST, "0,0,0");
	}

	protected NBTTagList newIntNBTList(int ad[])
	{
		NBTTagList nbttaglist = new NBTTagList();
		int ad1[] = ad;
		int i = ad1.length;

		for (int j = 0; j < i; j++)
		{
			int d = ad1[j];
			nbttaglist.appendTag(new NBTTagInt(d));
		}

		return nbttaglist;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger("Mode", getMode().ordinal());
		par1nbtTagCompound.setInteger("State", getState().ordinal());
        Item item = getItemID();
        int itemID = item == null ? 0 : Item.itemRegistry.getIDForObject(item);
		par1nbtTagCompound.setInteger("ItemID", itemID);
		par1nbtTagCompound.setInteger("StackSize", getStackSize());
		par1nbtTagCompound.setInteger("ItemDamage", getItemDamage());
		Coord home = getHomeCoord();
		par1nbtTagCompound.setTag("Home", newIntNBTList(new int[]
				{
				home.x, home.y, home.z
				}));
		Coord dest = getDestCoord();
		par1nbtTagCompound.setTag("Dest", newIntNBTList(new int[]
				{
				dest.x, dest.y, dest.z
				}));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {

		super.readEntityFromNBT(par1nbtTagCompound);
		setMode(EnumBotMode.values()[par1nbtTagCompound.getInteger("Mode")]);
		setState(EnumWorkState.values()[par1nbtTagCompound.getInteger("State")]);
        int itemID = par1nbtTagCompound.getInteger("ItemID");
        Item item = itemID == 0 ? null : (Item)Item.itemRegistry.getObjectById(itemID);
		setItemID(item);
		setStackSize(par1nbtTagCompound.getInteger("StackSize"));
		setItemDamage(par1nbtTagCompound.getInteger("ItemDamage"));
		NBTTagList homeTag = (NBTTagList)par1nbtTagCompound.getTag("Home");
		int x = ((NBTTagInt)homeTag.getCompoundTagAt(0).copy()).func_150287_d();
		int y = ((NBTTagInt)homeTag.getCompoundTagAt(1).copy()).func_150287_d();
		int z = ((NBTTagInt)homeTag.getCompoundTagAt(2).copy()).func_150287_d();
		setHomeCoord(new Coord(x,y,z));
		NBTTagList destTag = (NBTTagList)par1nbtTagCompound.getTag("Dest");
		x = ((NBTTagInt)destTag.getCompoundTagAt(0).copy()).func_150287_d();
		y = ((NBTTagInt)destTag.getCompoundTagAt(1).copy()).func_150287_d();
		z = ((NBTTagInt)destTag.getCompoundTagAt(2).copy()).func_150287_d();
		setDestCoord(new Coord(x,y,z));

		if(getMode() == EnumBotMode.PANIC){
			tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		}
	}

	@Override
	public ResourceLocation getTexture() {
		switch(getMode()) {
		case STAY:
			return GOLEM1_PNG;
		case FOLLOW:
			return GOLEM2_PNG;
		case DIG:
			return GOLEM3_PNG;
		case PANIC:
			return GOLEM4_PNG;
		case PICKUP:
			return GOLEM5_PNG;
		}
		return GOLEM1_PNG;
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	protected int getDig()
	{
		return dig;//dataWatcher.getWatchableObjectInt(19);
	}
	protected void setDig(int i)
	{
		dig = i;
		//dataWatcher.updateObject(19, Integer.valueOf(i));
	}

	void gotoSpot(int x, int y, int z, float f)
	{
		PathEntity pathentity = worldObj.getEntityPathToXYZ(this, x, y, z, 16F, true, true, false, true);
		this.getNavigator().setPath(pathentity, moveSpeed);
	}

	protected TileEntityBeacon getHome()
 {
		Coord homeCoord = getHomeCoord();
		if(homeCoord.isValid() == false) {
			return null;
		}
		if (worldObj.getBlock(homeCoord.x, homeCoord.y, homeCoord.z) != Ids.blockBeacon) {
			home = null;
			return home;
		} else {
			if (home == null) {
				home = null;
				TileEntityBeacon beacon = (TileEntityBeacon) worldObj
						.getTileEntity(homeCoord.x, homeCoord.y, homeCoord.z);
				if (beacon != null) {
					home = beacon;
				}
			}
		}

		return home;
	}
	protected void setHome(TileEntityBeacon i) {
		home = i;
		Coord homeCoord = new Coord(home.xCoord, home.yCoord, home.zCoord);
		setHomeCoord(homeCoord);
	}

	private boolean isTargetBlock(Block block) {
		if (block == Blocks.grass && getItemID() == Item.getItemFromBlock(Blocks.dirt)) {
			return true;
		}
		if (block == Blocks.dirt && getItemID() == Item.getItemFromBlock(Blocks.grass)) {
			return true;
		}
		if (Item.getItemFromBlock(block) == getItemID()) {
			return true;
		}
		return false;
	}

	private Coord getNextDest(Coord homePosition, int randX, int randY, int randZ) {
		Coord nextDest = new Coord(homePosition);
		nextDest.addCoord(
				rand.nextInt(randX) - randX / 2,
				rand.nextInt(randY) - randY / 2,
				rand.nextInt(randZ) - randZ / 2);

		return nextDest;
	}

	public void modeDig() {
		if(getMode() != EnumBotMode.DIG) {
			return;
		}

		EntityPlayer entityplayer = reallyGetBotOwner();
		if(entityplayer == null) {
			return;
		}

		Coord dest = getDestCoord();
		if (getState() == EnumWorkState.MOVE)
		{
			boolean hasHome = (getHome() != null);

			int posX = MathHelper.floor_double(entityplayer.posX);
			int posY = MathHelper.floor_double(entityplayer.posY);
			int posZ = MathHelper.floor_double(entityplayer.posZ);

			// if home exists, search farther
			Coord homePosition = hasHome ? new Coord(getHomeCoord()) : new Coord(posX, posY, posZ) ;
			Coord nextDest = hasHome ? getNextDest(homePosition, 32, 4, 32) : getNextDest(homePosition, 16, 4, 16);
			if(isTargetBlock(worldObj.getBlock(nextDest.x, nextDest.y, nextDest.z)) == false) {
				nextDest =  new Coord();
			}

			float moveSpeed = nextDest.isValid() ? 16F : 5F;

			// dest block unwanted, search nearby
			if(nextDest.isValid() == false) {
				nextDest = getNextDest(homePosition, 6, 4, 6);
			}

			// dest block match searching, change state
			if(nextDest.isValid()) {
				setState(EnumWorkState.CHECK);
				gotoSpot(nextDest.x, nextDest.y, nextDest.z, moveSpeed);
				dest.setCoord(nextDest);
			}
		}
		else if (getState() == EnumWorkState.CHECK)
		{
			if (getDistance(dest.x, dest.y, dest.z) < 2)
			{
				setState(EnumWorkState.ACTION);
			}
			else
			{
				if (lastResortDig())
				{
					setState(EnumWorkState.ACTION);
				}
				else
				{
					setState(EnumWorkState.MOVE);
				}
			}
		}
		else if (getState() == EnumWorkState.ACTION)
		{
			if(isTargetBlock(worldObj.getBlock(dest.x, dest.y, dest.z)) == false) {
				setState(EnumWorkState.MOVE);
			}
			else
			{
				int diggingCount = getDig();
				Block bb = Block.getBlockFromItem(getItemID());

				if(bb==null)
					return;

				if (diggingCount >= bb.getBlockHardness(worldObj, dest.x, dest.y, dest.z) * 30)
				{
					int metadata = worldObj.getBlockMetadata(dest.x, dest.y, dest.z);
					worldObj.setBlockToAir(dest.x, dest.y, dest.z);
					int fortune = 0;
					bb.dropBlockAsItem(worldObj, dest.x, dest.y, dest.z, metadata, fortune);
					//EntityItem entityitem = new EntityItem(worldObj, dest.x, dest.y, dest.z, new ItemStack(bb.idDropped(0, rand, 0), 1, 0));
					//entityitem.delayBeforeCanPickup = 10;
					//worldObj.spawnEntityInWorld(entityitem);
					setDig(0);

					//TODO optimize dig
					setState(EnumWorkState.MOVE);
				}
				else
				{
					setDig(diggingCount + 1);
				}
			}
		}

	}

	private boolean lastResortDig(){
		int xo = MathHelper.floor_double(posX);
		int yo = MathHelper.floor_double(posY);
		int zo = MathHelper.floor_double(posZ);

		if (derp(xo, yo + 1, zo))
		{
			return true;
		}
		else if (derp(xo - 1, yo, zo))
		{
			return true;
		}
		else if (derp(xo + 1, yo, zo))
		{
			return true;
		}
		else if (derp(xo, yo, zo - 1))
		{
			return true;
		}
		else if (derp(xo, yo, zo + 1))
		{
			return true;
		}
		else if (derp(xo, yo - 1, zo))
		{
			return true;
		}

		return false;
	}

	private boolean derp(int xo, int yo, int zo)
	{
		if(isTargetBlock(worldObj.getBlock(xo, yo, zo)))
		{
			setDestCoord(new Coord(xo, yo, zo));
			return true;
		}

		return false;
	}

	public Entity getPickupTarget() {
		return collectTargetItemEntity;
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}

	public void modeCollect() {
		if(getMode() != EnumBotMode.PICKUP) {
			return;
		}

		if(getItemID() == null) {
			setMode(EnumBotMode.STAY);
			return;
		}

		EntityPlayer entityplayer = reallyGetBotOwner();
		if(entityplayer == null) {
			return;
		}

		if (getState() == EnumWorkState.MOVE)
		{
			Coord dest = getDestCoord();
			boolean hasHome = (getHome() != null);

			int posX = MathHelper.floor_double(entityplayer.posX);
			int posY = MathHelper.floor_double(entityplayer.posY);
			int posZ = MathHelper.floor_double(entityplayer.posZ);

			Coord homePosition = hasHome ? new Coord(getHomeCoord()) : new Coord(posX, posY, posZ) ;
			Coord nextDest = hasHome ? getNextDest(homePosition, 5, 3, 5) : getNextDest(homePosition, 3, 3, 3);

			if (worldObj.getBlock(nextDest.x, nextDest.y, nextDest.z) == Blocks.chest)
			{
				dest.setCoord(nextDest);
				setDestCoord(dest);
				setState(EnumWorkState.CHECK);
			}
		}
		else if (getState() == EnumWorkState.CHECK)
		{
			Coord dest = getDestCoord();
			if (worldObj.getBlock(dest.x, dest.y, dest.z) == Blocks.chest)
			{
				int num = getStackSize();

				// if stack full, goto chest
				if(num == 64) {
					setState(EnumWorkState.ACTION);
					return;
				}

				// if had no target to pickup, search new target
				if (collectTargetItemEntity == null || collectTargetItemEntity.isDead) {
					collectTargetItemEntity = searchCollectTarget();
				}

				// if target not found but had stack, goto chest
				if(collectTargetItemEntity == null && num > 0) {
					setState(EnumWorkState.ACTION);
					return;
				}

				// if target found, goto target
				if (collectTargetItemEntity != null) {
					// if target is nearby, pickup and goto chest
					if (getDistanceToEntity(collectTargetItemEntity) < 2F) {
						pickup();
					}
					// if target is faraway, move to target
					else {
						getNavigator().setPath(worldObj.getPathEntityToEntity(this, collectTargetItemEntity, 16F, true, true, false, true), moveSpeed);
						if(getNavigator().getPath() == null) {
							setState(EnumWorkState.ACTION);
						}
					}
				}
				// if target not found, continue searching
			}
			else
			{
				setState(EnumWorkState.MOVE);
			}
		}
		else if (getState() == EnumWorkState.ACTION)
		{
			Coord dest = getDestCoord();
			if (worldObj.getBlock(dest.x, dest.y, dest.z) == Blocks.chest)
			{
				if (getDistance(dest.x, dest.y, dest.z) < 2)
				{
					setState(EnumWorkState.RETURN);
				}else{
					gotoSpot(dest.x, dest.y, dest.z, 16F);
				}
			}
			else
			{
				setState(EnumWorkState.MOVE);
			}
		}
		else if ( getState() == EnumWorkState.RETURN)
		{
			Coord dest = getDestCoord();

			if (worldObj.getBlock(dest.x, dest.y, dest.z) == Blocks.chest)
			{
				TileEntityChest tc = ((TileEntityChest)worldObj.getTileEntity(dest.x, dest.y, dest.z));
				int sl = 0;

				while (sl < tc.getSizeInventory())
				{
					ItemStack is = tc.getStackInSlot(sl);

					if (is == null)
					{
						is = new ItemStack(getItemID(), getStackSize(), getItemDamage());
						tc.setInventorySlotContents(sl, is);
						setStackSize(0);
						setState(EnumWorkState.CHECK);
						break;
					}
					else if ((is.getItem() == getItemID() && is.getItemDamage() == getItemDamage() && is.getItem() != null && is.stackSize < is.getMaxStackSize()))
					{
						if (is.stackSize + getStackSize() <= is.getMaxStackSize())
						{
							is.stackSize += getStackSize();
							tc.setInventorySlotContents(sl, is);
							setStackSize(0);
							setState(EnumWorkState.CHECK);
							break;
						}
						else
						{
							int ii = getStackSize() - (is.getMaxStackSize() - is.stackSize);
							setStackSize(ii);
							is.stackSize += ii;
							tc.setInventorySlotContents(sl, is);
						}
					}
					else
					{
						sl++;
					}
				}

				if (sl > tc.getSizeInventory())
				{
					setState(EnumWorkState.MOVE);
				}
			}
			else
			{
				setState(EnumWorkState.MOVE);
			}
		}

	}

	private Entity searchCollectTarget() {

		List list = worldObj.getEntitiesWithinAABB(
				EntityItem.class,
				boundingBox.expand(16D, 3D, 16D));

		if (!list.isEmpty()) {
			for (int j = 0; j < list.size(); j++) {
				Entity entity = (Entity) list.get(j);

				if (getItemID() != null) {
					ItemStack is = ((EntityItem) entity).getEntityItem();

					if(is.hasTagCompound()) {
						continue;
					}

					if (is.getItem() == getItemID()
							&& is.getItemDamage() == getItemDamage()) {
						return entity;
					}
				} else {
					if (!entity.isWet()) {
						return entity;
					}
				}
			}
		}

		return null;
	}

	private void pickup()
	{

		EntityItem ent = (EntityItem) collectTargetItemEntity;
		if(ent.getEntityItem().hasTagCompound()) {
			return;
		}
		setItemID(ent.getEntityItem().getItem());
		setItemDamage(ent.getEntityItem().getItemDamage());
		setStackSize(getStackSize() + ent.getEntityItem().stackSize);
		ent.setDead();
		collectTargetItemEntity = null;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if (getMode() == EnumBotMode.PANIC) {
			fleeingTick = 60;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	protected String getLivingSound() {
		return "riseautomatons:beep";
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
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 8;
	}

	void dropper()
	{
		for (int j = 0; j < 20; j++)
		{
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode", (posX + (rand.nextFloat() * width * 2.0F)) - width, posY + (rand.nextFloat() * height), (posZ + (rand.nextFloat() * width * 2.0F)) - width, d, d1, d2);
		}

		if (!Universal.improperWorld(worldObj))
		{
			//poof();
			EnumBotMode e = getMode();
			EntityItem entityitem = new EntityItem(worldObj, posX, posY + 1 , posZ, new ItemStack(Ids.soulCore, 1, (e != EnumBotMode.PANIC) ? (e != EnumBotMode.OTHER ? EnumSoulCore.SOULSYNTH.ordinal() : EnumSoulCore.SOULEVIL.ordinal()) : EnumSoulCore.SOULPURE.ordinal()));
			entityitem.delayBeforeCanPickup = 10;
			entityitem.setVelocity(Math.random() * 0.25 - 0.125, 0.25, Math.random() * 0.25 - 0.125);
			worldObj.spawnEntityInWorld(entityitem);
			dropInventory();
			int xx = MathHelper.floor_double(posX);
			int yy = MathHelper.floor_double(posY);
			int zz = MathHelper.floor_double(posZ);
			Block block = worldObj.getBlock(xx, yy, zz);

			if (block == Blocks.air || block.getCollisionBoundingBoxFromPool(worldObj, xx, yy, zz) == null)
			{
				int meta = MathHelper.floor_double((rotationYawHead * 4.0F / 360.0F) + 0.5D) & 3;

				worldObj.setBlock(xx, yy, zz, Ids.blockWorker, meta, 3);
			}
			else
			{

				entityitem = new EntityItem(worldObj, posX, posY , posZ, new ItemStack(Ids.itemWorker, 1, 0));
				worldObj.spawnEntityInWorld(entityitem);
			}

			setDead();
		}
	}

	private void dropInventory()
	{
		if (getStackSize() > 0)
		{
			entityDropItem(new ItemStack(getItemID(), getStackSize(), getItemDamage()), 0.0F);

			setStackSize(0);
			setItemDamage(0);
		}
		setItemID(null);

	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (isWet())
		{
			if (isEntityAlive())
			{
				dropper();
			}
		}
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);

		if (!worldObj.isRemote) {

			dropper();// a(field_34905_c > 0);
			setDead();
		}

	}

	@Override
	public void setRevengeTarget(EntityLivingBase par1EntityLiving) {
	}

	@Override
	public boolean getAvoidsWater() {
		return true;
	}

}
