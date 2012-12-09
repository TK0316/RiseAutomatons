package riseautomatons.common.entity;

import net.minecraft.src.EntityGolem;
import net.minecraft.src.World;

public class EntityBot extends EntityGolem {

	public EntityBot(World par1World) {
		super(par1World);
	}

	@Override
	public int getMaxHealth() {
		return 0;
	}

}
