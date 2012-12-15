package riseautomatons.common.world;

import riseautomatons.common.Ids;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

public class Biomes {

	public static final BiomeGenBase giants = (new BiomeGenGiants(42)).setColor(0xff0000).setBiomeName("Giants");
	public static final BiomeGenBase autumn = (new BiomeGenAutumn(43)).setColor(0xff0000).setBiomeName("Autumn");
	public static final BiomeGenBase windFarm = (new BiomeGenWindFarm(44)).setColor(0xff0000).setBiomeName("Autumn");
	public static final BiomeGenBase city = (new BiomeGenCity(45)).setColor(0xff0000).setBiomeName("Autumn");

	public static final BiomeGenBase tech = (new BiomeGenTech(46)).setColor(0xff0000).setBiomeName("Tech");

	public static void init(boolean generateTechBiome) {
		if(generateTechBiome) {
			GameRegistry.addBiome(tech);
		}
		for(int i = 0; i < 20; i++) {
			//GameRegistry.addBiome((new BiomeGenTech(47 + i)).setColor(0xff0000).setBiomeName("Tech"));

		}
		GameRegistry.registerWorldGenerator(new OreGenerator());
		GameRegistry.registerWorldGenerator(new TechSurfaceGenerator());
		GameRegistry.registerWorldGenerator(new TechRuinGenerator());
		//GameRegistry.addBiome(giants);
		//GameRegistry.addBiome(autumn);
		//GameRegistry.addBiome(windFarm);
		//GameRegistry.addBiome(city);
		WorldChunkManagerEupraxia.init();
		DimensionManager.registerProviderType(Ids.eupraxia, WorldProviderEupraxia.class, false);
		DimensionManager.registerDimension(Ids.eupraxia, Ids.eupraxia);
	}

}
