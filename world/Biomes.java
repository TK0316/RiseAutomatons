package riseautomatons.world;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.EnumHelper;
import riseautomatons.Ids;
import cpw.mods.fml.common.registry.GameRegistry;

public class Biomes {

	//public static final BiomeGenBase giants = (new BiomeGenGiants(Ids.biomeGiants)).setColor(0xff0000).setBiomeName("Giants");
	//public static final BiomeGenBase autumn = (new BiomeGenAutumn(Ids.biomeAutumn)).setColor(0xff0000).setBiomeName("Autumn");
	//public static final BiomeGenBase windFarm = (new BiomeGenWindFarm(Ids.biomeWindFarm)).setColor(0xff0000).setBiomeName("Autumn");
	//public static final BiomeGenBase city = (new BiomeGenCity(Ids.biomeCity)).setColor(0xff0000).setBiomeName("Autumn");

	public static final BiomeGenBase tech = (new BiomeGenTech(Ids.biomeTech)).setColor(0xff0000).setBiomeName("Tech");

	public static void init(boolean generateTechBiome) {
		if(generateTechBiome) {
			for(int i = 0; i < Ids.techBiomeFrequency; i++) {
                BiomeManager.desertBiomes.add(new BiomeManager.BiomeEntry(tech, 10));
                BiomeManager.icyBiomes.add(new BiomeManager.BiomeEntry(tech, 10));
                BiomeManager.warmBiomes.add(new BiomeManager.BiomeEntry(tech, 10));
                BiomeManager.coolBiomes.add(new BiomeManager.BiomeEntry(tech, 10));
                //BiomeDictionary.registerBiomeType(tech, typeTech);
				//GameRegistry.addBiome((new BiomeGenTech(Ids.biomeTech + i)).setColor(0xff0000).setBiomeName("Tech"));
			}
		}

		GameRegistry.registerWorldGenerator(new OreGenerator(), 1);
		GameRegistry.registerWorldGenerator(new TechSurfaceGenerator(), 2);
		GameRegistry.registerWorldGenerator(new TechRuinGenerator(), 3);
		//GameRegistry.addBiome(giants);
		//GameRegistry.addBiome(autumn);
		//GameRegistry.addBiome(windFarm);
		//GameRegistry.addBiome(city);
		WorldChunkManagerEupraxia.init();
		DimensionManager.registerProviderType(Ids.eupraxia, WorldProviderEupraxia.class, false);
		DimensionManager.registerDimension(Ids.eupraxia, Ids.eupraxia);
	}

}
