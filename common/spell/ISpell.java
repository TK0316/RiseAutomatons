package riseautomatons.common.spell;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public interface ISpell {

	public abstract boolean cast(World world, int i, int j, int k, EntityPlayer entityPlayer);
}
