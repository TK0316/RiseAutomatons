package riseautomatons.common.spell;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public class MakeExplosion extends Spell {

	@Override
	public boolean cast(World world, int i, int j, int k,
			EntityPlayer entityPlayer) {
		if (!world.isRemote) {
			world.createExplosion(entityPlayer, i, j, k, 5.0F, true);
		}
		return true;
	}

}
