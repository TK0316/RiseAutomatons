package riseautomatons.common.spell;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public class MakeNight extends Spell {

	@Override
	public boolean cast(World world, int i, int j, int k,
			EntityPlayer entityPlayer) {
		world.setWorldTime(13500);
		return true;
	}
}
