package riseautomatons.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface ISpell {

	public abstract boolean cast(World world, int i, int j, int k, EntityPlayer entityPlayer);
}
