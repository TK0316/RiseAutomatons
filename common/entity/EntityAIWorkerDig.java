package riseautomatons.common.entity;

import java.util.Random;

import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.PathNavigate;
import riseautomatons.common.entity.EntityWorker.EnumWorkMode;

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
		bot.modeDig();
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
}
