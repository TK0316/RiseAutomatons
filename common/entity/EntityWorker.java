package riseautomatons.common.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAIPanic;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.NBTTagInt;
import net.minecraft.src.PathEntity;
import net.minecraft.src.TileEntityChest;
import net.minecraft.src.World;
import riseautomatons.common.Coord;
import riseautomatons.common.Universal;

public class EntityWorker extends EntityOwnedBot implements IBot {

	public static final String GOLEM1_PNG = "/RiseAutomatons/golem1.png";
	public static final String GOLEM2_PNG = "/RiseAutomatons/golem2.png";
	public static final String GOLEM3_PNG = "/RiseAutomatons/golem3.png";
	public static final String GOLEM4_PNG = "/RiseAutomatons/golem4.png";
	public static final String GOLEM5_PNG = "/RiseAutomatons/golem5.png";
	public static final String GOLEM6_PNG = "/RiseAutomatons/golem6.png";

	private static Map<Integer, Integer> target = new LinkedHashMap<Integer, Integer>();

	enum EnumWorkMode {STAY, FOLLOW, DIG, PANIC, PICKUP};
	private EnumWorkMode mode = EnumWorkMode.STAY;

	enum EnumWorkState {MOVE, CHECK, ACTION, RETURN};
	private EnumWorkState state = EnumWorkState.MOVE;

	private Coord dest = new Coord();
	private Coord home = new Coord();
	private int dig;

	private int itemID = 0;
	private int stackSize = 0;
	private int itemDamage = 0;

	private Entity collectTargetItemEntity = null;

	int getItemID() {
		return dataWatcher.getWatchableObjectInt(18);
	}

	private void setItemID(int itemID) {
		dataWatcher.updateObject(18, itemID);
		this.itemID = itemID;
	}

	private int getStackSize() {
		return stackSize;
	}

	private void setStackSize(int stackSize) {
		this.stackSize = stackSize;
	}

	int getItemDamage() {
		return itemDamage;
	}

	private void setItemDamage(int itemDamage) {
		this.itemDamage = itemDamage;
	}

	private EnumWorkState getState() {
		return state;
	}

	private void setState(EnumWorkState state) {
		this.state = state;
	}


	public EntityWorker(World par1World) {
		super(par1World);
		System.out.println(String.valueOf(this.entityId) + ":" + mode );
		setSize(0.6F, 0.8F);
		moveSpeed = 0.25F;

		getNavigator().setAvoidsWater(true);
		//tasks.addTask(5, new EntityAIWorkerFollow(this, moveSpeed, 7F, 2.0F));
		tasks.addTask(5, new EntityAIWorkerDig(this));
		tasks.addTask(5, new EntityAIWorkerCollect(this));
		//tasks.addTask(7, new EntityAIWorkerWander(this, moveSpeed));
		//tasks.addTask(200, new EntityAIWorkerBeacon(this));
		tasks.addTask(9, new EntityAIWatchClosest(this, net.minecraft.src.EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
	}

	public static void init() {
		target.put(Block.stone.blockID, Block.stone.blockID);
		target.put(Block.grass.blockID, Block.grass.blockID);
		target.put(Block.dirt.blockID, Block.dirt.blockID);
		target.put(Block.cobblestone.blockID, Block.stone.blockID);

		target.put(Block.sand.blockID, Block.sand.blockID);
		target.put(Block.gravel.blockID, Block.gravel.blockID);
		target.put(Block.oreGold.blockID, Block.oreGold.blockID);
		target.put(Block.oreIron.blockID, Block.oreIron.blockID);
		target.put(Block.sandStone.blockID, Block.sandStone.blockID);
		target.put(Block.obsidian.blockID, Block.obsidian.blockID);
		target.put(Block.netherBrick.blockID, Block.netherBrick.blockID);
		target.put(Item.coal.shiftedIndex, Block.oreCoal.blockID);
		target.put(Item.dyePowder.shiftedIndex, Block.oreLapis.blockID);
		target.put(Item.emerald.shiftedIndex, Block.oreEmerald.blockID);
		target.put(Item.diamond.shiftedIndex, Block.oreDiamond.blockID);
		target.put(Item.redstone.shiftedIndex, Block.oreRedstone.blockID);
	}

	@Override
	public boolean interact(EntityPlayer entityplayer) {

		// TODO set owner properly
		setBotOwner(entityplayer.username);

		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		if(itemstack == null) {
			if(getMode() == EnumWorkMode.STAY) {
				setMode(EnumWorkMode.FOLLOW);
			}
			else {
				setMode(EnumWorkMode.STAY);
			}
		}
		else if(itemstack.itemID == Item.shovelStone.shiftedIndex) {
			setMode(EnumWorkMode.PANIC);
		}
		else if(itemstack.itemID == Item.stick.shiftedIndex) {
			setMode(EnumWorkMode.PICKUP);
		}
		else if(target.containsKey(itemstack.itemID)) {
			setMode(EnumWorkMode.DIG);
			setItemID(target.get(itemstack.itemID));
		}
		else {
			return false;
		}


		String s  = "explode";
		Random rand = worldObj.rand;

		for (int i = 0; i < 7; i++)
		{
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle(s, (posX + rand.nextFloat() * 1.6F - 0.8f), posY + 0.5f + (rand.nextFloat() * 0.2f), (posZ + rand.nextFloat() * 1.6F) - 0.8f, d, d1, d2);
		}

		isJumping = false;
		setPathToEntity(null);

		return true;
	}


	private void setMode(EnumWorkMode mode) {
		this.mode = mode;
		dataWatcher.updateObject(16, Integer.valueOf(mode.ordinal()));
		setState(EnumWorkState.MOVE);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(mode != null ? mode.ordinal() : EnumWorkMode.STAY.ordinal()));//mode
		dataWatcher.addObject(18, new Integer(itemID));//type
		//dataWatcher.addObject(19, new Integer(trigger));//state
		dataWatcher.addObject(20, ""); //dstring
	}

	public EnumWorkMode getMode() {
		int mode = dataWatcher.getWatchableObjectInt(16);
		if(0<= mode && mode < EnumWorkMode.values().length) {
			return EnumWorkMode.values()[mode];
		}
		return EnumWorkMode.STAY;
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
		par1nbtTagCompound.setInteger("mode", mode.ordinal());
		System.out.println("W " + String.valueOf(this.entityId) + ":" + mode );
		par1nbtTagCompound.setInteger("state", getState().ordinal());
		par1nbtTagCompound.setTag("Dest", newIntNBTList(new int[]
				{
				dest.x, dest.y, dest.z
				}));
		par1nbtTagCompound.setTag("Home", newIntNBTList(new int[]
				{
				home.x, home.y, home.z
				}));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {

		super.readEntityFromNBT(par1nbtTagCompound);
		setMode(EnumWorkMode.values()[par1nbtTagCompound.getInteger("mode")]);
		System.out.println("R " + String.valueOf(this.entityId) + ":" + mode );
		setState(EnumWorkState.values()[par1nbtTagCompound.getInteger("state")]);
		NBTTagList nbttaglist = par1nbtTagCompound.getTagList("Dest");
		dest.x = ((NBTTagInt)nbttaglist.tagAt(0)).data;
		dest.y = ((NBTTagInt)nbttaglist.tagAt(1)).data;
		dest.z = ((NBTTagInt)nbttaglist.tagAt(2)).data;
		NBTTagList nbttaglist2 = par1nbtTagCompound.getTagList("Home");
		home.x = ((NBTTagInt)nbttaglist.tagAt(0)).data;
		home.y = ((NBTTagInt)nbttaglist.tagAt(1)).data;
		home.z = ((NBTTagInt)nbttaglist.tagAt(2)).data;

		if(getMode() == EnumWorkMode.PANIC){
			tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		}
	}

	@Override
	public int getMaxHealth() {
		return 6;
	}

	@Override
	public String getTexture() {
		switch(mode) {
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

	public String getD()
	{
		return dataWatcher.getWatchableObjectString(20);
	}

	public void setD(String s)
	{
		dataWatcher.updateObject(20, s);
	}

	void gotoSpot(int x, int y, int z, float f)
	{
		PathEntity pathentity = worldObj.getEntityPathToXYZ(this, x, y, z, 16F, true, true, false, true);
		this.getNavigator().setPath(pathentity, moveSpeed);
	}

	public Object getHome() {
		// TODO 自動生成されたメソッド・スタブ
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
		if(getMode() != EnumWorkMode.DIG) {
			return;
		}

		EntityPlayer entityplayer = reallyGetBotOwner();
		if(entityplayer == null) {
			return;
		}

		if (getState() == EnumWorkState.MOVE)
		{
			boolean hasHome = (getHome() != null);

			int posX = MathHelper.floor_double(entityplayer.posX);
			int posY = MathHelper.floor_double(entityplayer.posY);
			int posZ = MathHelper.floor_double(entityplayer.posZ);

			// if home exists, search farther
			Coord homePosition = hasHome ? new Coord(home) : new Coord(posX, posY, posZ) ;
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
				setD("" + dest.x + "," + dest.y + "," + dest.z);
				Block bb = Block.blocksList[getItemID()];

				if(bb==null)
					return;

				if (diggingCount >= bb.getBlockHardness(worldObj, dest.x, dest.y, dest.z) * 30)
				{
					 worldObj.setBlockWithNotify(dest.x, dest.y, dest.z, 0);
					EntityItem entityitem = new EntityItem(worldObj, dest.x, dest.y, dest.z, new ItemStack(bb.idDropped(0, rand, 0), 1, 0));
					entityitem.delayBeforeCanPickup = 10;
					worldObj.spawnEntityInWorld(entityitem);
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
			dest.x = xo;
			dest.y = yo;
			dest.z = zo;
			return true;
		}

		return false;
	}

	public Entity getPickupTarget() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}

	public Coord getHomeCoord() {
		return home;
	}

	public void modeCollect() {
		if(getMode() != EnumWorkMode.PICKUP) {
			return;
		}

		if(getItemID() == 0) {
			setMode(EnumWorkMode.STAY);
			return;
		}

		EntityPlayer entityplayer = reallyGetBotOwner();
		if(entityplayer == null) {
			return;
		}

		if (getState() == EnumWorkState.MOVE)
		{
			boolean hasHome = (getHome() != null);

			int posX = MathHelper.floor_double(entityplayer.posX);
			int posY = MathHelper.floor_double(entityplayer.posY);
			int posZ = MathHelper.floor_double(entityplayer.posZ);

			Coord homePosition = hasHome ? new Coord(home) : new Coord(posX, posY, posZ) ;
			Coord nextDest = hasHome ? getNextDest(homePosition, 5, 3, 5) : getNextDest(homePosition, 3, 3, 3);

			if (worldObj.getBlockId(nextDest.x, nextDest.y, nextDest.z) == Block.chest.blockID)
			{
				dest.setCoord(nextDest);
				setState(EnumWorkState.CHECK);
			}
		}
		else if (getState() == EnumWorkState.CHECK)
		{
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
			System.out.println(getItemID() + "," + getStackSize() + "," + getItemDamage());

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
		setItemID(ent.item.itemID);
		setItemDamage(ent.item.getItemDamage());
		setStackSize(getStackSize() + ent.item.stackSize);
		ent.setDead();
		collectTargetItemEntity = null;
	}

}
