package riseautomatons.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityAniBot extends EntityMob {

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
