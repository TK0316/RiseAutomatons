package riseautomatons.entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityAniBot extends EntityMob {

	public EntityAniBot(World par1World) {
		super(par1World);
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float i) {
		if (damagesource == DamageSource.inWall) {
            //pushOutOfBlocks
			this.func_145771_j(posX, posY, posZ);
			return false;
		}
		return super.attackEntityFrom(damagesource, i);
	}

}
