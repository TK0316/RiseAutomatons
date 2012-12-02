package riseautomatons.common.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.PathEntity;
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

	enum EnumWorkMode {STAY, FOLLOW, DIG, PANIC, PICKUP};
	private EnumWorkMode mode = EnumWorkMode.STAY;
	private int inventryItemID = 0;

	public EntityWorker(World par1World) {
		super(par1World);
		setSize(0.6F, 0.8F);
		moveSpeed = 0.25F;

		getNavigator().setAvoidsWater(true);
		//tasks.addTask(5, new EntityAIWorkerFollow(this, moveSpeed, 7F, 2.0F));
		tasks.addTask(5, new EntityAIWorkerDig(this));
		//tasks.addTask(5, new EntityAIWorkerCollect(this));
		//tasks.addTask(7, new EntityAIWorkerWander(this, moveSpeed));
		//tasks.addTask(200, new EntityAIWorkerBeacon(this));
		tasks.addTask(9, new EntityAIWatchClosest(this, net.minecraft.src.EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
	}

	private static Map<Integer, Integer> target = new LinkedHashMap<Integer, Integer>();

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
			setInventoryType(target.get(itemstack.itemID));
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
		setState(EnumDigState.MOVE);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, new Integer(mode != null ? mode.ordinal() : EnumWorkMode.STAY.ordinal()));//mode
		//dataWatcher.addObject(18, new Integer(invType));//type
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

	public int getInventoryDamage() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	public int getInventoryType() {
		return inventryItemID;
	}

	private void setInventoryType(int itemID) {
		inventryItemID  = itemID;

	}

	private Coord dest = new Coord();
	private Coord home = new Coord();
	private int dig;

	enum EnumDigState {MOVE, CHECK, DIG};
	private EnumDigState state = EnumDigState.MOVE;

	private EnumDigState getState() {
		return state;
	}

	private void setState(EnumDigState state) {
		this.state = state;
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

	private boolean isInventoryType(int blockId) {
		if (blockId == Block.grass.blockID && getInventoryType() == Block.dirt.blockID) {
			return true;
		}
		if (blockId == Block.dirt.blockID && getInventoryType() == Block.grass.blockID) {
			return true;
		}
		if (blockId == getInventoryType()) {
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

		if(isInventoryType(worldObj.getBlockId(nextDest.x, nextDest.y, nextDest.z))) {
			return nextDest;
		}
		return new Coord();
	}

	public void modeDig() {
		if(getMode() != EnumWorkMode.DIG) {
			return;
		}

		EntityPlayer entityplayer = reallyGetBotOwner();
		if(entityplayer == null) {
			return;
		}

		if (getState() == EnumDigState.MOVE)
		{
			boolean hasHome = (getHome() != null);

			int posX = MathHelper.floor_double(entityplayer.posX);
			int posY = MathHelper.floor_double(entityplayer.posY);
			int posZ = MathHelper.floor_double(entityplayer.posZ);

			// if home exists, search farther
			Coord homePosition = hasHome ? new Coord(home) : new Coord(posX, posY, posZ) ;
			Coord nextDest = hasHome ? getNextDest(homePosition, 32, 4, 32) : getNextDest(homePosition, 16, 4, 16);

			float moveSpeed = nextDest.isValid() ? 16F : 5F;

			// dest block unwanted, search nearby
			if(nextDest.isValid() == false) {
				nextDest = getNextDest(homePosition, 6, 4, 6);
			}

			// dest block match searching, change state
			if(nextDest.isValid()) {
				setState(EnumDigState.CHECK);
				gotoSpot(nextDest.x, nextDest.y, nextDest.z, moveSpeed);
				dest.setCoord(nextDest);
			}
		}
		else if (getState() == EnumDigState.CHECK)
		{
			if (getDistance(dest.x, dest.y, dest.z) < 2)
			{
				setState(EnumDigState.DIG);
			}
			else
			{
				if (lastResortDig())
				{
					setState(EnumDigState.DIG);
				}
				else
				{
					setState(EnumDigState.MOVE);
				}
			}
		}
		else if (getState() == EnumDigState.DIG)
		{
			if(isInventoryType(worldObj.getBlockId(dest.x, dest.y, dest.z)) == false) {
				setState(EnumDigState.MOVE);
			}
			else
			{
				int diggingCount = getDig();
				setD("" + dest.x + "," + dest.y + "," + dest.z);
				Block bb = Block.blocksList[getInventoryType()];

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
					setState(EnumDigState.MOVE);
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
		if(isInventoryType(worldObj.getBlockId(xo, yo, zo)))
		{
			dest.x = xo;
			dest.y = yo;
			dest.z = zo;
			return true;
		}

		return false;
	}
}
