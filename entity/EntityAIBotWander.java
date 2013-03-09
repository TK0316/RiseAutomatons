package riseautomatons.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWander;

public class EntityAIBotWander extends EntityAIWander {

	EntityOwnedBot taskOwner;
	public EntityAIBotWander(EntityOwnedBot entityOwnedBot, float par2) {
		super(entityOwnedBot, par2);
		taskOwner = entityOwnedBot;
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public boolean shouldExecute() {
		if(taskOwner.getMode() == EnumBotMode.WANDER) {
			return super.shouldExecute();
		}
		return false;
	}

}
