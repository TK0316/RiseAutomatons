package riseautomatons.common.entity;

import riseautomatons.common.block.*;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Entities {

	public static void init() {
		// Automatons
		LanguageRegistry.instance().addStringLocalization("entity.Worker.name", "en_US", "Worker");
		BlockWorker.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityWorker.class, "Worker", BlockWorker.renderId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Sentry.name", "en_US", "Sentry");
		BlockSentry.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntitySentry.class, "Sentry", BlockSentry.renderId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Golem.name", "en_US", "Golem");
		EntityGolem.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityGolem.class, "Golem", EntityGolem.renderId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.GolemPure.name", "en_US", "GolemPure");
		EntityGolem.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityGolemPure.class, "GolemPure", EntityGolemPure.renderId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Watcher.name", "en_US", "Watcher");
		EntityWatcher.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityWatcher.class, "Watcher", EntityWatcher.renderId, 0xCCCCCC, 0xFFFFFF);

		// Effect
		EntityFwooshFX.rendererId = ModLoader.getMinecraftInstance().renderEngine
				.getTexture("/riseautomatons/fwoosh.png");
		EntityGoreFX.rendererId = ModLoader.getMinecraftInstance().renderEngine
				.getTexture("/riseautomatons/gore.png");
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

		MinecraftForgeClient.preloadTexture(EntitySentry.SENTRY_PNG);
		MinecraftForgeClient.preloadTexture(EntitySentry.SENTRYBLOCK_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntitySentry.class, new RenderBot(new ModelSentry(), 0.75F));

		MinecraftForgeClient.preloadTexture(EntityGolem.GOLEM_PNG);
		MinecraftForgeClient.preloadTexture(EntityGolemPure.GOLEM_PURE_PNG);
		MinecraftForgeClient.preloadTexture(EntityGolemPure.GOLEM_PURE_BI_PNG);
		MinecraftForgeClient.preloadTexture(EntityGolemPure.GOLEM_PURE_LONG_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntityGolem.class, new RenderGolem(new ModelGolem(), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGolemPure.class, new RenderGolem(new ModelGolem(), 0.25F));

		MinecraftForgeClient.preloadTexture(EntityWatcher.WATCHER_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntityWatcher.class, new RenderWatcher(new ModelWatcher(), 0.5F));

		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser());
	}

}
