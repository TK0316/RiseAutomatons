package riseautomatons.common;

import java.util.logging.Logger;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
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

	@Mod.Init
	public void load(FMLInitializationEvent event) {
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(
				event.getSuggestedConfigurationFile());

		cfg.load();
		cfg.save();

		Property debug = cfg
				.get(Configuration.CATEGORY_GENERAL, "debug", false);
		this.debug = debug.getBoolean(false);
	}

}
