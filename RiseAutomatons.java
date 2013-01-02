package riseautomatons;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import riseautomatons.block.Blocks;
import riseautomatons.entity.Entities;
import riseautomatons.item.Items;
import riseautomatons.spell.ChalkLogic;
import riseautomatons.world.Biomes;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = "RiseAutomatons", name = "RiseAutomatons", version = "1.0")
@NetworkMod(clientSideRequired = false, serverSideRequired = true, channels = { "rota" }, packetHandler = PacketHandler.class, connectionHandler = ConnectionHandler.class, versionBounds = "[1.0]")
public class RiseAutomatons {
	@SidedProxy(clientSide = "riseautomatons.ClientProxy", serverSide = "riseautomatons.CommonProxy")
	public static CommonProxy proxy;

	@Instance("RiseAutomatons")
	public static RiseAutomatons instance;

	public static Logger logger = Logger.getLogger("Minecraft");

	public static boolean debug = false;

	public static final CreativeTabs tabAutomatons = new CreativeTabAutomatons(
			"RiseAutomatons");

	public static boolean enableCheatRecipe = false;
	public static boolean generateTechBiome = true;
	public static  boolean enableFrassSpread = true;

	@Mod.Init
	public void load(FMLInitializationEvent event) {

		Entities.init();
		Blocks.init();
		Blocks.setFrassSpread(enableFrassSpread);
		Items.init();
		Recipes.init();
		Biomes.init(generateTechBiome);
		if(enableCheatRecipe) {
			Recipes.initCheatRecipe();
		}
		ChalkLogic.init();
		NetworkRegistry.instance().registerGuiHandler(instance,  proxy);
		Universal.init();
		proxy.regiserTextures();
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(
				event.getSuggestedConfigurationFile());

		try {
			cfg.load();
			Ids.soulCore = cfg.get(Configuration.CATEGORY_ITEM, "soulCore", Ids.soulCore).getInt();
			Ids.itemChalk = cfg.get(Configuration.CATEGORY_ITEM, "itemChalk", Ids.itemChalk).getInt();
			Ids.blaster = cfg.get(Configuration.CATEGORY_ITEM, "blaster", Ids.blaster).getInt();
			Ids.smack = cfg.get(Configuration.CATEGORY_ITEM, "smack", Ids.smack).getInt();
			Ids.pickTech = cfg.get(Configuration.CATEGORY_ITEM, "pickTech", Ids.pickTech).getInt();
			Ids.mortar = cfg.get(Configuration.CATEGORY_ITEM, "mortar", Ids.mortar).getInt();
			Ids.chisel = cfg.get(Configuration.CATEGORY_ITEM, "chisel", Ids.chisel).getInt();
			Ids.techifier = cfg.get(Configuration.CATEGORY_ITEM, "techifier", Ids.techifier).getInt();
			Ids.naturizer = cfg.get(Configuration.CATEGORY_ITEM, "naturizer", Ids.naturizer).getInt();
			Ids.craftSet = cfg.get(Configuration.CATEGORY_ITEM, "craftSet", Ids.craftSet).getInt();
			Ids.spring = cfg.get(Configuration.CATEGORY_ITEM, "spring", Ids.spring).getInt();
			Ids.skull = cfg.get(Configuration.CATEGORY_ITEM, "skull", Ids.skull).getInt();
			Ids.skullA = cfg.get(Configuration.CATEGORY_ITEM, "skullA", Ids.skullA).getInt();
			Ids.itemWorker = cfg.get(Configuration.CATEGORY_ITEM, "itemWorker", Ids.itemWorker).getInt();
			Ids.itemSentry = cfg.get(Configuration.CATEGORY_ITEM, "itemSentry", Ids.itemSentry).getInt();
			Ids.itemFactotum = cfg.get(Configuration.CATEGORY_ITEM, "itemFactotum", Ids.itemFactotum).getInt();
			Ids.itemBeacon = cfg.get(Configuration.CATEGORY_ITEM, "itemBeacon", Ids.itemBeacon).getInt();
			Ids.itemGuard = cfg.get(Configuration.CATEGORY_ITEM, "itemGuard", Ids.itemGuard).getInt();

			Ids.blockWorker = cfg.get(Configuration.CATEGORY_BLOCK, "blockWorker", Ids.blockWorker).getInt();
			Ids.blockChalk = cfg.get(Configuration.CATEGORY_BLOCK, "blockChalk", Ids.blockChalk).getInt();
			Ids.blockGearbox = cfg.get(Configuration.CATEGORY_BLOCK, "blockGearbox", Ids.blockGearbox).getInt();
			Ids.blockTurn = cfg.get(Configuration.CATEGORY_BLOCK, "blockTurn", Ids.blockTurn).getInt();
			Ids.blockWindmill = cfg.get(Configuration.CATEGORY_BLOCK, "blockWindmill", Ids.blockWindmill).getInt();

			Ids.blockSlab = cfg.get(Configuration.CATEGORY_BLOCK, "blockSlab", Ids.blockSlab).getInt();
			Ids.saltOre = cfg.get(Configuration.CATEGORY_BLOCK, "saltOre", Ids.saltOre).getInt();
			Ids.sulfOre = cfg.get(Configuration.CATEGORY_BLOCK, "sulfOre", Ids.sulfOre).getInt();
			Ids.blockSentry = cfg.get(Configuration.CATEGORY_BLOCK, "blockSentry", Ids.blockSentry).getInt();
			Ids.blockTech = cfg.get(Configuration.CATEGORY_BLOCK, "blockTech", Ids.blockTech).getInt();
			Ids.blockArch = cfg.get(Configuration.CATEGORY_BLOCK, "blockArch", Ids.blockArch).getInt();
			Ids.blockSky = cfg.get(Configuration.CATEGORY_BLOCK, "blockSky", Ids.blockSky).getInt();
			Ids.blockArchBend = cfg.get(Configuration.CATEGORY_BLOCK, "blockArchBend", Ids.blockArchBend).getInt();
			Ids.blockArchitect = cfg.get(Configuration.CATEGORY_BLOCK, "blockArchitect", Ids.blockArchitect).getInt();
			Ids.blockCrink = cfg.get(Configuration.CATEGORY_BLOCK, "blockCrink", Ids.blockCrink).getInt();
			Ids.blockFrass = cfg.get(Configuration.CATEGORY_BLOCK, "blockFrass", Ids.blockFrass).getInt();
			Ids.blockPlantMass = cfg.get(Configuration.CATEGORY_BLOCK, "blockPlantMass", Ids.blockPlantMass).getInt();
			Ids.blockCrystal = cfg.get(Configuration.CATEGORY_BLOCK, "blockCrystal", Ids.blockCrystal).getInt();
			Ids.blockFakeCrystal = cfg.get(Configuration.CATEGORY_BLOCK, "blockFakeCrystal", Ids.blockFakeCrystal).getInt();
			Ids.blockGlowy = cfg.get(Configuration.CATEGORY_BLOCK, "blockGlowy", Ids.blockGlowy).getInt();
			Ids.blockBoing = cfg.get(Configuration.CATEGORY_BLOCK, "blockBoing", Ids.blockBoing).getInt();
			Ids.blockBeacon = cfg.get(Configuration.CATEGORY_BLOCK, "blockBeacon", Ids.blockBeacon).getInt();
			Ids.blockTote = cfg.get(Configuration.CATEGORY_BLOCK, "blockTote", Ids.blockTote).getInt();
			Ids.blockGrower = cfg.get(Configuration.CATEGORY_BLOCK, "blockGrower", Ids.blockGrower).getInt();
			Ids.blockHeal = cfg.get(Configuration.CATEGORY_BLOCK, "blockHeal", Ids.blockHeal).getInt();
			Ids.blockDapling = cfg.get(Configuration.CATEGORY_BLOCK, "blockDapling", Ids.blockDapling).getInt();
			Ids.blockDuplex = cfg.get(Configuration.CATEGORY_BLOCK, "blockDuplex", Ids.blockDuplex).getInt();

			Ids.eupraxia = cfg.get(Configuration.CATEGORY_GENERAL, "eupraxia", Ids.eupraxia).getInt();

			Ids.biomeGiants = cfg.get(Configuration.CATEGORY_GENERAL, "biomeGiants", Ids.biomeGiants).getInt();
			Ids.biomeAutumn = cfg.get(Configuration.CATEGORY_GENERAL, "biomeAutumn", Ids.biomeAutumn).getInt();
			Ids.biomeWindFarm = cfg.get(Configuration.CATEGORY_GENERAL, "biomeWindFarm", Ids.biomeWindFarm).getInt();
			Ids.biomeCity = cfg.get(Configuration.CATEGORY_GENERAL, "biomeCity", Ids.biomeCity).getInt();
			Ids.biomeTech = cfg.get(Configuration.CATEGORY_GENERAL, "biomeTech", Ids.biomeTech).getInt();
			Ids.techBiomeFrequency = cfg.get(Configuration.CATEGORY_GENERAL, "techBiomeFrequency", Ids.techBiomeFrequency).getInt();

			Ids.guiFactotum = cfg.get(Configuration.CATEGORY_GENERAL, "guiFactotum", Ids.guiFactotum).getInt();

			enableCheatRecipe = cfg.get(Configuration.CATEGORY_GENERAL, "enableCheatRecipe", false).getBoolean(false);
			generateTechBiome = cfg.get(Configuration.CATEGORY_GENERAL, "generateTechBiome", true).getBoolean(true);
			enableFrassSpread  = cfg.get(Configuration.CATEGORY_GENERAL, "enableFrassSpread", true).getBoolean(true);

			Ids.entityWorkerId = cfg.get(Configuration.CATEGORY_GENERAL, "entityWorkerId", Ids.entityWorkerId).getInt();
			Ids.entitySentryId = cfg.get(Configuration.CATEGORY_GENERAL, "entitySentryId", Ids.entitySentryId).getInt();
			Ids.entityFactotumId = cfg.get(Configuration.CATEGORY_GENERAL, "entityFactotumId", Ids.entityFactotumId).getInt();
			Ids.entityBeaconId = cfg.get(Configuration.CATEGORY_GENERAL, "entityBeaconId", Ids.entityBeaconId).getInt();
			Ids.entityGuardId = cfg.get(Configuration.CATEGORY_GENERAL, "entityGuardId", Ids.entityGuardId).getInt();
			Ids.entityLaserId = cfg.get(Configuration.CATEGORY_GENERAL, "entityLaserId", Ids.entityLaserId).getInt();
			Ids.entityOmniId = cfg.get(Configuration.CATEGORY_GENERAL, "entityOmniId", Ids.entityOmniId).getInt();

			Ids.entityGolemId = cfg.get(Configuration.CATEGORY_GENERAL, "entityGolemId", Ids.entityGolemId).getInt();
			Ids.entityGolemPureId = cfg.get(Configuration.CATEGORY_GENERAL, "entityGolemPureId", Ids.entityGolemPureId).getInt();
			Ids.entityWatcherId = cfg.get(Configuration.CATEGORY_GENERAL, "entityWatcherId", Ids.entityWatcherId).getInt();
			Ids.entitySliderId = cfg.get(Configuration.CATEGORY_GENERAL, "entitySliderId", Ids.entitySliderId).getInt();
			Ids.entityBobbyId = cfg.get(Configuration.CATEGORY_GENERAL, "entityBobbyId", Ids.entityBobbyId).getInt();

			cfg.save();

			Property debug = cfg
					.get(Configuration.CATEGORY_GENERAL, "debug", false);
			this.debug = debug.getBoolean(false);
		}
		catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, "RiseAutomatons load config exception");
        }
        finally
        {
            cfg.save();
        }
	}

}
