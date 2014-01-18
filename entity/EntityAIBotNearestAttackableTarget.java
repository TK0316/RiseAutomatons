package riseautomatons.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIBotNearestAttackableTarget extends
		EntityAINearestAttackableTarget {

	public EntityAIBotNearestAttackableTarget(EntityCreature par1EntityCreature,
			Class par2Class, int par3, boolean par4) {
		super(par1EntityCreature, par2Class, par3, par4);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public boolean shouldExecute() {
		if(((EntityOwnedBot)taskOwner).getMode() != EnumBotMode.WANDER) {
			return false;
		}
		return super.shouldExecute();
	}

	@Override
	public void startExecuting() {
		if(((EntityOwnedBot)taskOwner).getMode() != EnumBotMode.WANDER) {
			return;
		}
		super.startExecuting();
		EntityLivingBase target = taskOwner.getAttackTarget();
		if(target == null) return;

		if(taskOwner instanceof EntityOwnedBot) {
			if(target instanceof EntityPlayer) {
				String targetName = ((EntityPlayer)target).getEntityName();
				EntityPlayer botOwner = ((EntityOwnedBot)taskOwner).reallyGetBotOwner();
				if(botOwner == null) return;
				if(targetName == botOwner.getEntityName()) {
					taskOwner.setAttackTarget(null);
				}
			}
			if(target instanceof EntityOwnedBot) {
				EntityPlayer targetOwner = ((EntityOwnedBot)target).reallyGetBotOwner();
				EntityPlayer botOwner = ((EntityOwnedBot)taskOwner).reallyGetBotOwner();
				if(botOwner == null) return;
				if(targetOwner == null) return;
				if(targetOwner.getEntityName() == botOwner.getEntityName()) {
					taskOwner.setAttackTarget(null);
				}
			}
		}
	}

}
