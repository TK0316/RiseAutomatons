package riseautomatons.common;

import net.minecraft.src.World;

public class Universal {

	public static boolean improperWorld(World world) {
		return world.isRemote;
	}

}
