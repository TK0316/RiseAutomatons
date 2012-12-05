package riseautomatons.common.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAIPanic;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagInt;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.PathEntity;
import net.minecraft.src.TileEntityChest;
import net.minecraft.src.World;
import riseautomatons.common.Coord;
import riseautomatons.common.Ids;
import riseautomatons.common.Universal;
import riseautomatons.common.item.EnumSoulCore;

public class EntityWorker extends EntityOwnedBot implements IBot {

	public static final String GOLEM1_PNG = "/riseautomatons/golem1.png";
	public static final String GOLEM2_PNG = "/riseautomatons/golem2.png";
	public static final String GOLEM3_PNG = "/riseautomatons/golem3.png";
	public static final String GOLEM4_PNG = "/riseautomatons/golem4.png";
	public static final String GOLEM5_PNG = "/riseautomatons/golem5.png";
	public static final String GOLEM6_PNG = "/riseautomatons/golem6.png";

	public static final int INDEX_MODE = 18;
	public static final int INDEX_STATE = 19;
	public static final int INDEX_ITEMID = 20;
	public static final int INDEX_STACKSIZE = 21;
	public static final int INDEX_ITEMDAMAGE = 22;
	public static final int INDEX_HOME = 23;
	public static final int INDEX_DEST = 24;

	private static Map<Integer, Integer> target = new LinkedHashMap<Integer, Integer>();

	enum EnumWorkState {MOVE, CHECK, ACTION, RETURN};
	private int dig;

	private Entity collectTargetItemEntity = null;

	int getItemID() {
		return dataWatcher.getWatchableObjectInt(INDEX_ITEMID);
	}

	private void setItemID(int itemID) {
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

	private void setMode(EnumBotMode mode) {
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
		moveSpeed = 0.25F;

		getNavigator().setAvoidsWater(true);
		tasks.addTask(5, new EntityAIBotFollowOwner(this, moveSpeed, 7F, 2.0F));
		tasks.addTask(5, new EntityAIWorkerDig(this));
		tasks.addTask(5, new EntityAIWorkerCollect(this));
		tasks.addTask(7, new EntityAIWorkerWander(this, moveSpeed));
		//tasks.addTask(200, new EntityAIWorkerBeacon(this));
		tasks.addTask(9, new EntityAIWatchClosest(this, net.minecraft.src.EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
	}

	public EntityWorker(World world, double d, double d1, double d2,int turn, String s)
	{
		this(world);
		setPosition(d, d1 + (double)yOffset, d2);
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

	public static void init() {
		target.put(Block.cobblestone.blockID, Block.stone.blockID);

		target.put(Item.coal.shiftedIndex, Block.oreCoal.blockID);
		target.put(Item.dyePowder.shiftedIndex, Block.oreLapis.blockID);
		target.put(Item.emerald.shiftedIndex, Block.oreEmerald.blockID);
		target.put(Item.diamond.shiftedIndex, Block.oreDiamond.blockID);
		target.put(Item.redstone.shiftedIndex, Block.oreRedstone.blockID);
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {

		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		if(itemstack == null) {
			if(reallyGetBotOwner() != entityplayer) {
				return true;
			}
			setItemID(0);
			setStackSize(0);
			setItemDamage(0);
			if(getMode() == EnumBotMode.STAY) {
				setMode(EnumBotMode.FOLLOW);
			}
			else {
				setMode(EnumBotMode.STAY);
			}
		}
		else if(itemstack.itemID == Ids.soulCore && itemstack.getItemDamage() == EnumSoulCore.SOULSYNTH.ordinal()) {
			if(getBotOwner() == "") {
				setBotOwner(entityplayer.username);
				setMode(EnumBotMode.FOLLOW);
			}
			else {
				return true;
			}
		}
		// when hand block and right click again, mode chage PICKUP to DIG
		else if(itemstack.itemID == getItemID()) {
			if(reallyGetBotOwner() != entityplayer) {
				return true;
			}
			if(target.containsKey(itemstack.itemID)) {
				setItemID(target.get(itemstack.itemID));
			}
			else {
				setItemID(itemstack.itemID);
			}
			setMode(EnumBotMode.DIG);
		}
		else {
			if(reallyGetBotOwner() != entityplayer) {
				return true;
			}
			setMode(EnumBotMode.PICKUP);
			setItemID(itemstack.itemID);
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
			nbttaglist.appendTag(new NBTTagInt(null, d));
		}

		return nbttaglist;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger("Mode", getMode().ordinal());
		par1nbtTagCompound.setInteger("State", getState().ordinal());
		par1nbtTagCompound.setInteger("ItemID", getItemID());
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
		setItemID(par1nbtTagCompound.getInteger("ItemID"));
		setStackSize(par1nbtTagCompound.getInteger("StackSize"));
		setItemDamage(par1nbtTagCompound.getInteger("ItemDamage"));
		NBTTagList homeTag = par1nbtTagCompound.getTagList("Home");
		int x = ((NBTTagInt)homeTag.tagAt(0)).data;
		int y = ((NBTTagInt)homeTag.tagAt(1)).data;
		int z = ((NBTTagInt)homeTag.tagAt(2)).data;
		setHomeCoord(new Coord(x,y,z));
		NBTTagList destTag = par1nbtTagCompound.getTagList("Dest");
		x = ((NBTTagInt)destTag.tagAt(0)).data;
		y = ((NBTTagInt)destTag.tagAt(1)).data;
		z = ((NBTTagInt)destTag.tagAt(2)).data;
		setDestCoord(new Coord(x,y,z));

		if(getMode() == EnumBotMode.PANIC){
			tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		}
	}

	@Override
	public int getMaxHealth() {
		return 6;
	}

	@Override
	public String getTexture() {
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

	public Object getHome() {
		// TODO implement getHome
		return null;
	}

	private boolean isTargetBlockId(int blockId) {
		if (blockId == Block.grass.blockID && getItemID() == Block.dirt.blockID) {
			return true;
		}
		if (blockId == Block.dirt.blockID && getItemID() == Block.grass.blockID) {
			return true;
		}
		if (blockId == getItemID()) {
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
			if(isTargetBlockId(worldObj.getBlockId(nextDest.x, nextDest.y, nextDest.z)) == false) {
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
			if(isTargetBlockId(worldObj.getBlockId(dest.x, dest.y, dest.z)) == false) {
				setState(EnumWorkState.MOVE);
			}
			else
			{
				int diggingCount = getDig();
				Block bb = Block.blocksList[getItemID()];

				if(bb==null)
					return;

				if (diggingCount >= bb.getBlockHardness(worldObj, dest.x, dest.y, dest.z) * 30)
				{
					int metadata = worldObj.getBlockMetadata(dest.x, dest.y, dest.z);
					worldObj.setBlockWithNotify(dest.x, dest.y, dest.z, 0);
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
		if(isTargetBlockId(worldObj.getBlockId(xo, yo, zo)))
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

		if(getItemID() == 0) {
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

			if (worldObj.getBlockId(nextDest.x, nextDest.y, nextDest.z) == Block.chest.blockID)
			{
				dest.setCoord(nextDest);
				setDestCoord(dest);
				setState(EnumWorkState.CHECK);
			}
		}
		else if (getState() == EnumWorkState.CHECK)
		{
			Coord dest = getDestCoord();
			if (worldObj.getBlockId(dest.x, dest.y, dest.z) == Block.chest.blockID)
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
			if (worldObj.getBlockId(dest.x, dest.y, dest.z) == Block.chest.blockID)
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

			if (worldObj.getBlockId(dest.x, dest.y, dest.z) == Block.chest.blockID)
			{
				TileEntityChest tc = ((TileEntityChest)worldObj.getBlockTileEntity(dest.x, dest.y, dest.z));
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
					else if ((is.itemID == getItemID() && is.getItemDamage() == getItemDamage() && is.getItem() != null && is.stackSize < is.getMaxStackSize()))
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
				net.minecraft.src.EntityItem.class,
				boundingBox.expand(16D, 3D, 16D));

		if (!list.isEmpty()) {
			for (int j = 0; j < list.size(); j++) {
				Entity entity = (Entity) list.get(j);

				if (getItemID() != 0) {
					ItemStack is = ((EntityItem) entity).item;

					if(is.hasTagCompound()) {
						continue;
					}

					if (is.itemID == getItemID()
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
		if(ent.item.hasTagCompound()) {
			return;
		}
		setItemID(ent.item.itemID);
		setItemDamage(ent.item.getItemDamage());
		setStackSize(getStackSize() + ent.item.stackSize);
		ent.setDead();
		collectTargetItemEntity = null;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if (getMode() == EnumBotMode.PANIC) {
			fleeingTick = 60;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
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
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 8;
	}

	private void dropper()
	{
		for (int j = 0; j < 20; j++)
		{
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode", (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
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
			int id = worldObj.getBlockId(xx, yy, zz);

			if (id == 0 || Block.blocksList[id].getCollisionBoundingBoxFromPool(worldObj, xx, yy, zz) == null)
			{
				int meta = MathHelper.floor_double((double)(rotationYawHead * 4.0F / 360.0F) + 0.5D) & 3;

				worldObj.setBlockAndMetadataWithNotify(xx, yy, zz, Ids.blockWorker, meta);
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
		setItemID(0);

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
	public void setRevengeTarget(EntityLiving par1EntityLiving) {
	}

	@Override
	public boolean getAvoidsWater() {
		return true;
	}

}
