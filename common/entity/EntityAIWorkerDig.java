package riseautomatons.common.entity;

import java.util.Random;

import riseautomatons.common.Universal;
import riseautomatons.common.entity.EntityWorker.EnumWorkMode;

import net.minecraft.src.Block;
import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.PathNavigate;

public class EntityAIWorkerDig extends EntityAIBase {

	EntityWorker bot;
	EntityLiving owner;
	PathNavigate pathFinder;
	Random rand;

	public EntityAIWorkerDig(EntityWorker entity) {
		bot = entity;
		setMutexBits(3);
		pathFinder = bot.getNavigator();
		rand = new Random();
	}

	@Override
	public boolean continueExecuting() {
		return pathFinder.noPath() == false && bot.getMode() == EnumWorkMode.DIG;
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
		modeDig(owner);
	}

	@Override
	public void updateTask() {
		super.updateTask();
	}

	@Override
	public boolean shouldExecute() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}






	private boolean optimizeDig()
	{
		int xo = MathHelper.floor_double(bot.dX);
		int yo = MathHelper.floor_double(bot.dY);
		int zo = MathHelper.floor_double(bot.dZ);
		boolean bool = even(bot.R);
		if(Universal.distance(bot.hx, bot.hy, bot.hz, bot.posX, bot.posY, bot.posZ)>24){
			return false;
		}


		if (bot.F < 6 && bot.F>=0 && derp(xo, yo, zo + (bool?1:-1))){
			if(bool){
				bot.F++;
			}else{
				bot.F--;
			}
			return true;
		}
		else if (derp(xo - 1, yo, zo)){
			bot.R++;
			return true;
		}
		else if (derp(xo + 1, yo, zo)){
			bot.R--;
			return true;
		}
		else if (derp(xo, yo - 1, zo)){
			bot.R=3;
			bot.F=3;
			return true;
		}
		return false;
	}

	private boolean lastResortDig(){
		int xo = MathHelper.floor_double(bot.posX);
		int yo = MathHelper.floor_double(bot.posY);
		int zo = MathHelper.floor_double(bot.posZ);

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



	public void modeDig(EntityLiving entityplayer){  //digger


		if (bot.getState() == 0)
		{
			int xo;
			int zo;
			int yo;
			boolean boo = (bot.getHome() != null);

			if (boo)
			{
				xo = bot.hx + rand.nextInt(32) - 16;
				zo = bot.hz + rand.nextInt(32) - 16;
				yo = bot.hy + rand.nextInt(4) - 2;
			}
			else
			{

				xo = MathHelper.floor_double(entityplayer.posX) + rand.nextInt(16) - 8;
				zo = MathHelper.floor_double(entityplayer.posZ) + rand.nextInt(16) - 8;
				yo = MathHelper.floor_double(entityplayer.posY) + rand.nextInt(4) - 2;
			}
			//System.out.println(xo+","+yo+","+zo);

			int bbb = bot.worldObj.getBlockId(xo, yo, zo);

			if (bbb == 2)
			{
				bbb = 3;
			}
			//System.out.println("uh: "+bot.getInventoryType());
			if (bbb == bot.getInventoryType())
			{
				//System.out.println("stage 2!");
				bot.setState(1);
				bot.gotoSpot(xo, yo, zo, 16F);
				bot.dX = xo;
				bot.dY = yo;
				bot.dZ = zo;
			}
			else
			{
				if (boo)
				{
					xo = bot.hx + rand.nextInt(6) - 3;
					zo = bot.hz + rand.nextInt(6) - 3;
					yo = bot.hy + rand.nextInt(4) - 2;
				}
				else
				{
					xo = MathHelper.floor_double(entityplayer.posX) + rand.nextInt(6) - 3;
					zo = MathHelper.floor_double(entityplayer.posZ) + rand.nextInt(6) - 3;
					yo = MathHelper.floor_double(entityplayer.posY) + rand.nextInt(4) - 2;
				}

				int bbb2 = bot.worldObj.getBlockId(xo, yo, zo);

				if (bbb2 == 2)
				{
					bbb2 = 3;
				}

				if (bbb2 == bot.getInventoryType())
				{
					bot.setState(1);
					bot.gotoSpot(xo, yo, zo, 5F);
					bot.dX = xo;
					bot.dY = yo;
					bot.dZ = zo;
				}
			}
		}
		else if (bot.getState() == 1)
		{
			if (bot.getDistance(bot.dX, bot.dY, bot.dZ) < 2)
			{
				bot.setState(2);
			}
			else
			{
				if (lastResortDig())
				{
					bot.setState(2);
				}
				else
				{
					bot.setState(0);
				}
			}
		}
		else if (bot.getState() == 2)
		{
			int bbb = bot.worldObj.getBlockId(bot.dX, bot.dY, bot.dZ);

			if (bbb == 2)
			{
				bbb = 3;
			}

			if (bbb != bot.getInventoryType())
			{
				bot.setState(0);
			}
			else
			{
				if (bot.getT() != 1)
				{
					bot.setT(1);
				}

				int dd = bot.getDig();
				bot.setD("" + bot.dX + "," + bot.dY + "," + bot.dZ);
				Block bb = Block.blocksList[bot.getInventoryType()];

				if(bb==null)
					return;

				if (dd >= bb.getBlockHardness(bot.worldObj, bot.dX, bot.dY, bot.dZ) * 30)
				{
					bot. worldObj.setBlockWithNotify(bot.dX, bot.dY, bot.dZ, 0);
					EntityItem entityitem = new EntityItem(bot.worldObj, bot.dX, bot.dY, bot.dZ, new ItemStack(bb.idDropped(0, rand, 0), 1, 0));
					entityitem.delayBeforeCanPickup = 10;
					bot.worldObj.spawnEntityInWorld(entityitem);
					bot.setT(0);
					bot.setDig(0);

					//TODO
					if (optimizeDig())
					{
						bot.setState(1);
						bot.gotoSpot(bot.dX, bot.dY, bot.dZ, 5F);
					}
					else
					{
						bot.F = 0;
						bot.R = 0;
						bot.setState(0);
					}

					//setState(0);
				}
				else
				{
					bot.setDig(dd + 1);
				}
			}
		}

	}

	public boolean even(int i)
	{
		return i % 2 == 0;
	}

	private boolean derp(int xo, int yo, int zo)
	{
		if (bot.worldObj.getBlockId(xo, yo, zo) == bot.getInventoryType())
		{
			bot.dX = xo;
			bot.dY = yo;
			bot.dZ = zo;
			return true;
		}

		return false;
	}

}
