package riseautomatons.common.entity;

import riseautomatons.common.block.*;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Entities {

	public static void init() {
		LanguageRegistry.instance().addStringLocalization("entity.Worker.name", "en_US", "Worker");
		BlockWorker.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityWorker.class, "Worker", BlockWorker.renderId, 0x00CC00, 0x77FF49);
		registerTextures();
		EntityWorker.init();
	}

	@SideOnly(Side.CLIENT)
	public static void registerTextures() {
		MinecraftForgeClient.preloadTexture(EntityWorker.GOLEM1_PNG);
		MinecraftForgeClient.preloadTexture(EntityWorker.GOLEM2_PNG);
		MinecraftForgeClient.preloadTexture(EntityWorker.GOLEM3_PNG);
		MinecraftForgeClient.preloadTexture(EntityWorker.GOLEM4_PNG);
		MinecraftForgeClient.preloadTexture(EntityWorker.GOLEM5_PNG);
		MinecraftForgeClient.preloadTexture(EntityWorker.GOLEM6_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntityWorker.class, new RenderWorker(new ModelWorker(), 0.5F));

	}

}
