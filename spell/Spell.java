package riseautomatons.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class Spell implements ISpell {

	@Override
	public boolean cast(World world, int i, int j, int k,
			EntityPlayer entityPlayer) {
		return false;
	}

}
