package riseautomatons.common;

import java.util.logging.Level;
import java.util.logging.Logger;

import riseautomatons.common.block.Blocks;
import riseautomatons.common.entity.Entities;
import riseautomatons.common.item.Items;
import riseautomatons.common.spell.ChalkLogic;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "RiseAutomatons", name = "RiseAutomatons", version = "1.0")
@NetworkMod(clientSideRequired = false, serverSideRequired = true, channels = { "rota" }, packetHandler = PacketHandler.class, connectionHandler = ConnectionHandler.class, versionBounds = "[1.0]")
public class RiseAutomatons {
	@SidedProxy(clientSide = "riseautomatons.client.ClientProxy", serverSide = "riseautomatons.CommonProxy")
	public static CommonProxy proxy;

	@Instance("RiseAutomatons")
	public static RiseAutomatons instance;

	public static Logger logger = Logger.getLogger("Minecraft");

	public static boolean debug = false;

	public static final CreativeTabs tabAutomatons = new CreativeTabAutomatons(
			"RiseAutomatons");

	@Mod.Init
	public void load(FMLInitializationEvent event) {

		Universal.init();
		Entities.init();
		Blocks.init();
		Items.init();
		ChalkLogic.init();
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(
				event.getSuggestedConfigurationFile());

		try {
			cfg.load();
			Ids.itemWorker = cfg.get(Configuration.CATEGORY_ITEM, "itemWorker", Ids.itemWorker).getInt();
			Ids.itemChalk = cfg.get(Configuration.CATEGORY_ITEM, "itemChalk", Ids.itemChalk).getInt();
			Ids.craftSet = cfg.get(Configuration.CATEGORY_ITEM, "craftSet", Ids.craftSet).getInt();
			Ids.spring = cfg.get(Configuration.CATEGORY_ITEM, "spring", Ids.spring).getInt();
			Ids.soulCore = cfg.get(Configuration.CATEGORY_ITEM, "soulCore", Ids.soulCore).getInt();
			Ids.chisel = cfg.get(Configuration.CATEGORY_ITEM, "chisel", Ids.chisel).getInt();

			Ids.blockWorker = cfg.get(Configuration.CATEGORY_BLOCK, "blockWorker", Ids.blockWorker).getInt();
			Ids.blockChalk = cfg.get(Configuration.CATEGORY_BLOCK, "blockChalk", Ids.blockChalk).getInt();
			Ids.blockGearbox = cfg.get(Configuration.CATEGORY_BLOCK, "blockGearbox", Ids.blockGearbox).getInt();
			Ids.blockTurn = cfg.get(Configuration.CATEGORY_BLOCK, "blockTurn", Ids.blockTurn).getInt();
			Ids.blockWindmill = cfg.get(Configuration.CATEGORY_BLOCK, "blockWindmill", Ids.blockWindmill).getInt();

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
