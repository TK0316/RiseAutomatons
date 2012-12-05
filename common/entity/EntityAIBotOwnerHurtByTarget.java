package riseautomatons.common.entity;

import net.minecraft.src.EntityAITarget;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityTameable;

public class EntityAIBotOwnerHurtByTarget extends EntityAITarget {
	EntityOwnedBot bot;
    EntityLiving theOwnerAttacker;

    public EntityAIBotOwnerHurtByTarget(EntityOwnedBot bot)
    {
        super(bot, 32.0F, false);
        this.bot = bot;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.bot.reallyGetBotOwner() == null)
        {
            return false;
        }
        else
        {
            EntityLiving var1 = this.bot.reallyGetBotOwner();

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
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.theOwnerAttacker);
        super.startExecuting();
    }
}
