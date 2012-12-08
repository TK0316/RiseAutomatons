package riseautomatons.common.entity;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityAgeable;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.World;

public abstract class EntityAniBot extends EntityAnimal {

	public EntityAniBot(World par1World) {
		super(par1World);
	}

	@Override
	public int getMaxHealth() {
		return 0;
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, int i) {
		if (damagesource == DamageSource.inWall) {
			this.pushOutOfBlocks(posX, posY, posZ);
			return false;
		}
		return super.attackEntityFrom(damagesource, i);
	}

}
