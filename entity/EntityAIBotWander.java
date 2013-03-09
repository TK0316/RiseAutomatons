package riseautomatons.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWander;

public class EntityAIBotWander extends EntityAIWander {

	EntityOwnedBot taskOwner;
	public EntityAIBotWander(EntityOwnedBot entityOwnedBot, float par2) {
		super(entityOwnedBot, par2);
		taskOwner = entityOwnedBot;
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	@Override
	public boolean shouldExecute() {
		if(taskOwner.getMode() == EnumBotMode.WANDER) {
			return super.shouldExecute();
		}
		return false;
	}

}
