package riseautomatons.common.item;

import riseautomatons.common.Ids;
import riseautomatons.common.RiseAutomatons;
import riseautomatons.common.entity.EntityWorker;
import net.minecraft.src.Item;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {

	public static final String ITEMS_PNG = "/RiseAutomatons/items.png";

	public static void init() {
		LanguageRegistry.instance().addNameForObject(worker, "en_US", "Worker");
		registerTextures();
	}

	@SideOnly(Side.CLIENT)
	public static void registerTextures() {
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
	}

	public static Item worker = (new ItemBot(Ids.itemWorker - 256, EnumBotType.WORKER))
			.setTextureFile(ITEMS_PNG).setIconIndex(0)
			.setItemName("Worker").setMaxStackSize(8)
			.setCreativeTab(RiseAutomatons.tabAutomatons);
}
