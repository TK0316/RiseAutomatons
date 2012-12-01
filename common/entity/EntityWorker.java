package riseautomatons.common.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import riseautomatons.common.Universal;

public class EntityWorker extends EntityMob implements IBot {

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

		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		if(itemstack == null) {
			if(getMode() == EnumWorkMode.STAY) {
				setMode(EnumWorkMode.FOLLOW);
			}
			else {
				setMode(EnumWorkMode.STAY);
			}
			return true;
		}

		if(itemstack.itemID == Item.shovelStone.shiftedIndex) {
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

		mode = EnumWorkMode.values()[(mode.ordinal() + 1) % EnumWorkMode.values().length];


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


	private void setMode(EnumWorkMode follow) {
		if(Universal.improperWorld(worldObj)) {
			return;
		}
	}

	public EnumWorkMode getMode() {
		return mode;
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

}
