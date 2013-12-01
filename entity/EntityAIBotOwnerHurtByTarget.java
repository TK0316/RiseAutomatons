package riseautomatons.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

public class EntityAIBotOwnerHurtByTarget extends EntityAITarget {
	EntityOwnedBot bot;
	EntityLivingBase theOwnerAttacker;

    public EntityAIBotOwnerHurtByTarget(EntityOwnedBot bot)
    {
        super(bot, false);
        this.bot = bot;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
	public boolean shouldExecute()
    {
        if (this.bot.reallyGetBotOwner() == null)
        {
            return false;
        }
        else
        {
        	EntityLivingBase var1 = this.bot.reallyGetBotOwner();

            if (var1 == null)
            {
                return false;
            }
            else
            {
                this.theOwnerAttacker = var1.getAITarget();
                return this.isSuitableTarget(this.theOwnerAttacker, false);
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
	public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.theOwnerAttacker);
        super.startExecuting();
    }
}
