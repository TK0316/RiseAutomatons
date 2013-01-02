package riseautomatons.entity;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import riseautomatons.Coord;

public class EntityAIWorkerCollect extends EntityAIBase {

	EntityWorker bot;
	PathNavigate pathFinder;
	int currentPathIndex = -1;
	int continueCount = 0;

	public EntityAIWorkerCollect(EntityWorker entityWorker) {
		bot = entityWorker;
		setMutexBits(3);
		pathFinder = entityWorker.getNavigator();
	}

	@Override
	public boolean shouldExecute() {
		if(bot.getMode() == EnumBotMode.PICKUP && bot.reallyGetBotOwner() != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean continueExecuting() {
		if(pathFinder.noPath()) {
			return false;
		}

		int index = pathFinder.getPath().getCurrentPathIndex();
		if(currentPathIndex != index) {
			currentPathIndex = index;
			continueCount = 0;
		}
		else {
			continueCount++;
		}

		if(continueCount >= 200) {
			Coord home = bot.getHomeCoord();
			pathFinder.tryMoveToXYZ(home.x, home.y, home.z, bot.getMoveSpeed());
		}

		return pathFinder.noPath() == false && bot.getMode() == EnumBotMode.PICKUP;
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
		bot.modeCollect();
	}

	@Override
	public void updateTask() {
		if(bot.getPickupTarget() != null) {
			bot.getLookHelper().setLookPositionWithEntity(bot.getPickupTarget(), 10F, bot.getVerticalFaceSpeed());
		}
	}

}
