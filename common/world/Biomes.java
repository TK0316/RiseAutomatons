package riseautomatons.common.world;

import cpw.mods.fml.common.registry.GameRegistry;

public class Biomes {

	public static final WorldGenOre ore = new WorldGenOre();
	public static void init() {
		GameRegistry.registerWorldGenerator(ore);
	}

}
