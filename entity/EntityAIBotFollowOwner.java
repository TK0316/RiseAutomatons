package riseautomatons.entity;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;

public class EntityAIBotFollowOwner extends EntityAIBase {

	private EntityOwnedBot bot;
	private PathNavigate pathFinder;
	private float moveSpeed;
	private float distanceMin;
	private float distanceMax;
	private int interval;

	public EntityAIBotFollowOwner(EntityOwnedBot entityOwnedBot, float moveSpeed,
			float min, float max) {
		bot = entityOwnedBot;
		pathFinder = entityOwnedBot.getNavigator();
		this.moveSpeed = moveSpeed;
		distanceMin = min;
		distanceMax = max;
        setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		if(bot.getMode() != EnumBotMode.FOLLOW) {
			return false;
		}

		EntityPlayer theOwner = bot.reallyGetBotOwner();
		if(theOwner == null) {
			return false;
		}

		if(bot.getDistanceSqToEntity(theOwner) < distanceMin * distanceMin * (bot.getAITarget() != null ? 1.5 : 1)) {
			return false;
		}

		return true;
	}

	@Override
	public void resetTask() {
		pathFinder.clearPathEntity();
		bot.getNavigator().setAvoidsWater(bot.getAvoidsWater());
	}

	@Override
	public void updateTask() {
		EntityPlayer theOwner = bot.reallyGetBotOwner();
		if(theOwner == null) {
			return;
		}

		bot.getLookHelper().setLookPositionWithEntity(theOwner, 10F, bot.getVerticalFaceSpeed());

		if( --interval > 0) {
			return;
		}

		interval = 10;

		if(pathFinder.tryMoveToEntityLiving(theOwner, moveSpeed)) {
			return;
		}

		if(bot.getDistanceSqToEntity(theOwner) < 144D) {
			return;
		}

		int i = MathHelper.floor_double(theOwner.posX) - 2;
		int j = MathHelper.floor_double(theOwner.posZ) - 2;
		int k = MathHelper.floor_double(theOwner.boundingBox.minY);

		for (int l = 0; l <= 4; l++) {
			for (int i1 = 0; i1 <= 4; i1++) {
				if ((l < 1 || i1 < 1 || l > 3 || i1 > 3)
						&& bot.worldObj.isBlockNormalCubeDefault(i + l, k - 1, j + i1, false)
						&& !bot.worldObj.isBlockNormalCubeDefault(i + l, k, j + i1, false)
						&& !bot.worldObj.isBlockNormalCubeDefault(i + l, k + 1, j + i1, false)) {
					bot.setLocationAndAngles((float) (i + l) + 0.5F, k,
							(float) (j + i1) + 0.5F, bot.rotationYaw,
							bot.rotationPitch);
					pathFinder.clearPathEntity();
					return;
				}
			}
		}	}

	@Override
	public boolean continueExecuting() {
		EntityPlayer theOwner = bot.reallyGetBotOwner();
		if(theOwner == null) {
			return false;
		}

		if(bot.dimension != theOwner.dimension) {
			return false;
		}

		return !pathFinder.noPath() && (bot.getDistanceSqToEntity(theOwner) > distanceMax * distanceMax);
	}

	@Override
	public void startExecuting() {
		interval = 0;
		bot.getNavigator().setAvoidsWater(bot.getAvoidsWater());
	}

}
