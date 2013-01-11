package riseautomatons.entity;

import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;
import riseautomatons.Ids;
import riseautomatons.RiseAutomatons;
import riseautomatons.block.BlockSentry;
import riseautomatons.block.BlockTote;
import riseautomatons.block.BlockWorker;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Entities {

	public static void init() {
		// Automatons
		LanguageRegistry.instance().addStringLocalization("entity.Worker.name", "en_US", "Worker");
		BlockWorker.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityWorker.class, "Worker", Ids.entityWorkerId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Sentry.name", "en_US", "Sentry");
		BlockSentry.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntitySentry.class, "Sentry", Ids.entitySentryId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Tote.name", "en_US", "Tote");
		BlockTote.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityTote.class, "Tote", Ids.entityToteId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Golem.name", "en_US", "Golem");
		EntityGolemNormal.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityGolemNormal.class, "Golem", Ids.entityGolemId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.GolemPure.name", "en_US", "GolemPure");
		EntityGolemNormal.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityGolemPure.class, "GolemPure", Ids.entityGolemPureId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Watcher.name", "en_US", "Watcher");
		EntityWatcher.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityWatcher.class, "Watcher", Ids.entityWatcherId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Slider.name", "en_US", "Slider");
		EntitySlider.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntitySlider.class, "Slider", Ids.entitySliderId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Bobby.name", "en_US", "Bobby");
		EntityBobby.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityBobby.class, "Bobby", Ids.entityBobbyId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Helios.name", "en_US", "Helios");
		EntityHelios.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityHelios.class, "Helios", Ids.entityHeliosId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Factotum.name", "en_US", "Factotum");
		EntityFactotum.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityFactotum.class, "Factotum", Ids.entityFactotumId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Beacon.name", "en_US", "Beacon");
		EntityBeacon.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityBeacon.class, "Beacon", Ids.entityBeaconId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Guard.name", "en_US", "Guard");
		EntityGuard.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityGuard.class, "Guard", Ids.entityGuardId, 0xCCCCCC, 0xFFFFFF);

		LanguageRegistry.instance().addStringLocalization("entity.Omni.name", "en_US", "Omni");
		EntityOmni.renderId = RenderingRegistry.getNextAvailableRenderId();
		EntityRegistry.registerGlobalEntityID(EntityOmni.class, "Omni", Ids.entityOmniId, 0xCCCCCC, 0xFFFFFF);

		EntityRegistry.registerModEntity(EntityLaser.class, "Laser", Ids.entityLaserId, RiseAutomatons.instance, 64, 2, true);
		EntityRegistry.registerGlobalEntityID(EntityLaser.class, "Laser", Ids.entityLaserId);

		EntityRegistry.registerModEntity(EntityVirus.class, "Virus", Ids.entityVirusId, RiseAutomatons.instance, 64, 2, true);
		EntityRegistry.registerGlobalEntityID(EntityVirus.class, "Virus", Ids.entityVirusId);

		// Effect

		EntityWorker.init();
	}

	@SideOnly(Side.CLIENT)
	public static void initEffect() {
		EntityFwooshFX.rendererId = ModLoader.getMinecraftInstance().renderEngine
				.getTexture("/riseautomatons/fwoosh.png");
		EntityGoreFX.rendererId = ModLoader.getMinecraftInstance().renderEngine
				.getTexture("/riseautomatons/gore.png");
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

		MinecraftForgeClient.preloadTexture(EntityTote.TOTE_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntityTote.class, new RenderTote(new ModelTote(), 0.5F));

		MinecraftForgeClient.preloadTexture(EntityGolemNormal.GOLEM_PNG);
		MinecraftForgeClient.preloadTexture(EntityGolemPure.GOLEM_PURE_PNG);
		MinecraftForgeClient.preloadTexture(EntityGolemPure.GOLEM_PURE_BI_PNG);
		MinecraftForgeClient.preloadTexture(EntityGolemPure.GOLEM_PURE_LONG_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntityGolemNormal.class, new RenderGolem(new ModelGolem(), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGolemPure.class, new RenderGolem(new ModelGolem(), 0.25F));

		MinecraftForgeClient.preloadTexture(EntityWatcher.WATCHER_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntityWatcher.class, new RenderWatcher(new ModelWatcher(), 0.5F));

		MinecraftForgeClient.preloadTexture(EntitySlider.SLIDER_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntitySlider.class, new RenderBot(new ModelSlider(), 1F));

		MinecraftForgeClient.preloadTexture(EntityBobby.BOBBY_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntityBobby.class, new RenderBot(new ModelBobby(), 0.25F));

		MinecraftForgeClient.preloadTexture(EntityHelios.HELIOS_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntityHelios.class, new RenderBot(new ModelHelios(), 0.3F));

		MinecraftForgeClient.preloadTexture(EntityFactotum.FACTOTUM1_PNG);
		MinecraftForgeClient.preloadTexture(EntityFactotum.FACTOTUM2_PNG);
		MinecraftForgeClient.preloadTexture(EntityFactotum.FACTOTUM3_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntityFactotum.class, new RenderFactotum(new ModelFactotum(), 1.5F));

		MinecraftForgeClient.preloadTexture(EntityBeacon.BEACON_PNG);
		MinecraftForgeClient.preloadTexture(EntityBeacon.BEACON1_PNG);
		MinecraftForgeClient.preloadTexture(EntityBeacon.BEACON2_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntityBeacon.class, new RenderBot(new ModelBeacon(), 0.5F));

		MinecraftForgeClient.preloadTexture(EntityGuard.GUARD_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntityGuard.class, new RenderBot(new ModelGuard(), 0.3F));

		MinecraftForgeClient.preloadTexture(EntityOmni.OMNI_PNG);
		RenderingRegistry.registerEntityRenderingHandler(EntityOmni.class, new RenderBot(new ModelOmni(), 0.25F));

		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser());

		RenderingRegistry.registerEntityRenderingHandler(EntityVirus.class, new RenderVirus());
	}

}