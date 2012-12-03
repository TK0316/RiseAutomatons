package riseautomatons.common.entity;

import riseautomatons.common.entity.EntityWorker.EnumWorkMode;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityCreature;

public class EntityAIWorkerWander extends EntityAIWander {

	EntityWorker bot;
	public EntityAIWorkerWander(EntityWorker par1EntityCreature, float par2) {
		super(par1EntityCreature, par2);
		bot = par1EntityCreature;
	}

	@Override
	public boolean shouldExecute() {
		if(bot.getMode() != EnumWorkMode.PANIC) {
			return false;
		}
		return super.shouldExecute();
	}

}
