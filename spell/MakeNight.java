package riseautomatons.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MakeNight extends Spell {

	@Override
	public boolean cast(World world, int i, int j, int k,
			EntityPlayer entityPlayer) {
		world.setWorldTime(13500);
		return true;
	}
}
