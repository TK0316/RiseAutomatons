package riseautomatons.common.world;

import net.minecraft.src.BiomeGenBase;
import cpw.mods.fml.common.registry.GameRegistry;

public class Biomes {

	public static final BiomeGenBase tech = (new BiomeGenTech(46)).setColor(0xff0000).setBiomeName("Tech");

	public static void init() {
		GameRegistry.addBiome(tech);
		GameRegistry.registerWorldGenerator(new OreGenerator());
		GameRegistry.registerWorldGenerator(new TechSurfaceGenerator());
	}

}
