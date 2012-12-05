package riseautomatons.common.entity;

import net.minecraft.src.EntityAITarget;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityTameable;

public class EntityAIBotOwnerHurtTarget extends EntityAITarget {

    EntityOwnedBot bot;
    EntityLiving theTarget;

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
            EntityLiving var1 =  bot.reallyGetBotOwner();

            if (var1 == null)
            {
                return false;
            }
            else
            {
                this.theTarget = var1.getLastAttackingEntity();
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
