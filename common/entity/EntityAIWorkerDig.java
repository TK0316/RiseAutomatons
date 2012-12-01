package riseautomatons.common.entity;

import java.util.Random;

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
