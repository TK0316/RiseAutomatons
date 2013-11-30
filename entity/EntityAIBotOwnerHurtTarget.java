package riseautomatons.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

public class EntityAIBotOwnerHurtTarget extends EntityAITarget {

    EntityOwnedBot bot;
    EntityLivingBase theTarget;

    public EntityAIBotOwnerHurtTarget(EntityOwnedBot bot)
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
        if (bot.reallyGetBotOwner() == null)
        {
            return false;
        }
        else
        {
            EntityLivingBase var1 =  bot.reallyGetBotOwner();

            if (var1 == null)
            {
                return false;
            }
            else
            {
                this.theTarget = var1.getLastAttacker();
                return this.isSuitableTarget(this.theTarget, false);
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.theTarget);
        super.startExecuting();
    }

}
