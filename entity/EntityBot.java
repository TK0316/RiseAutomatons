package riseautomatons.entity;

import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.world.World;

public class EntityBot extends EntityGolem {

	public EntityBot(World par1World) {
		super(par1World);
	}

	@Override
	public int getMaxHealth() {
		return 0;
	}

}
