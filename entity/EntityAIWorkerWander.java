package riseautomatons.entity;

import net.minecraft.entity.ai.EntityAIWander;

public class EntityAIWorkerWander extends EntityAIWander {

	EntityWorker bot;
	public EntityAIWorkerWander(EntityWorker par1EntityCreature, float par2) {
		super(par1EntityCreature, par2);
		bot = par1EntityCreature;
	}

	@Override
	public boolean shouldExecute() {
		if(bot.getMode() != EnumBotMode.PANIC) {
			return false;
		}
		return super.shouldExecute();
	}

}
