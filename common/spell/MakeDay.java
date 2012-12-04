package riseautomatons.common.spell;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public class MakeDay extends Spell {

	@Override
	public boolean cast(World world, int i, int j, int k,
			EntityPlayer entityPlayer) {
		world.setWorldTime(0);
		return true;
	}
}
