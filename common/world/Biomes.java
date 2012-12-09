package riseautomatons.common.world;

import cpw.mods.fml.common.registry.GameRegistry;

public class Biomes {

	public static void init() {
		GameRegistry.registerWorldGenerator(new OreGenerator());
	}

}
