package riseautomatons.common.item;

import riseautomatons.common.Ids;
import riseautomatons.common.RiseAutomatons;
import riseautomatons.common.entity.EntityWorker;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {

	public static final String ITEMS_PNG = "/riseautomatons/items.png";
	public static final String CRAFTSET_PNG = "/riseautomatons/craftset.png";
	public static final String SOULCORE_PNG = "/riseautomatons/soulcore.png";

	public static void init() {
		// Automatons
		LanguageRegistry.instance().addNameForObject(worker, "en_US", "Worker");
		LanguageRegistry.instance().addNameForObject(sentry, "en_US", "Sentry");
		LanguageRegistry.instance().addNameForObject(factotum, "en_US", "Factotum");
		LanguageRegistry.instance().addNameForObject(beacon, "en_US", "Beacon");
		LanguageRegistry.instance().addNameForObject(guard, "en_US", "Guard Turret");

		// CraftSet
		for (int i = 0; i < EnumCraftSetType.values().length; i++) {
			LanguageRegistry.instance().addNameForObject(
					new ItemStack(Ids.craftSet, 1, i), "en_US",
					EnumCraftSetType.values()[i].fullname);
		}

		// SoulCore
		for (int i = 0; i < EnumSoulCore.values().length; i++) {
			LanguageRegistry.instance().addNameForObject(
					new ItemStack(Ids.soulCore, 1, i), "en_US",
					EnumSoulCore.values()[i].fullname);
		}

		// Normal Items
		LanguageRegistry.instance().addNameForObject(spring, "en_US", "Spring");
		LanguageRegistry.instance().addNameForObject(chalk, "en_US", "Chalk");
		LanguageRegistry.instance().addNameForObject(skull, "en_US", "Human Skull");
		LanguageRegistry.instance().addNameForObject(skullAnimal, "en_US", "Animal Skull");
		LanguageRegistry.instance().addNameForObject(chisel, "en_US", "Chisel");
		LanguageRegistry.instance().addNameForObject(blaster, "en_US", "Pulse Rifle");
		LanguageRegistry.instance().addNameForObject(smack, "en_US", "Slider Pan");
		LanguageRegistry.instance().addNameForObject(mortar, "en_US", "Grinder");

		registerTextures();
	}

	@SideOnly(Side.CLIENT)
	public static void registerTextures() {
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
	}

	// Automatons
	public static Item worker = (new ItemBot(Ids.itemWorker - 256, EnumBotType.WORKER))
			.setTextureFile(ITEMS_PNG).setIconIndex(0)
			.setItemName("Worker").setMaxStackSize(8)
			.setCreativeTab(RiseAutomatons.tabAutomatons);
    public static Item sentry = (new ItemBot(Ids.itemSentry - 256, EnumBotType.SENTRY))
			.setTextureFile(ITEMS_PNG).setIconIndex(1)
    		.setItemName("Sentry").setMaxStackSize(1)
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item factotum = (new ItemBot(Ids.itemFactotum - 256,EnumBotType.FACTOTUM))
			.setTextureFile(ITEMS_PNG).setIconIndex(2)
			.setItemName("factotum").setMaxStackSize(1)
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item beacon = (new ItemBot(Ids.itemBeacon - 256, EnumBotType.BEACON))
			.setTextureFile(ITEMS_PNG).setIconIndex(3)
			.setItemName("beacon")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item guard = (new ItemBot(Ids.itemGuard - 256, EnumBotType.GUARD))
			.setTextureFile(ITEMS_PNG).setIconIndex(4)
			.setItemName("guard").setMaxStackSize(64)
			.setCreativeTab(RiseAutomatons.tabAutomatons);

	// Normal Items
	public static Item craftset = (new ItemCraftSet(Ids.craftSet - 256))
			.setTextureFile(CRAFTSET_PNG)
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item chalk = (new ItemChalk(Ids.itemChalk - 256))
			.setTextureFile(ITEMS_PNG).setIconIndex(16)
			.setItemName("Chalk")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item spring = (new Item(Ids.spring - 256))
			.setTextureFile(ITEMS_PNG).setIconIndex(17)
			.setItemName("Spring")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item soulCore = (new ItemSoulCore(Ids.soulCore - 256))
			.setTextureFile(SOULCORE_PNG)
			.setItemName("soulCore")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item skull = (new ItemSkull(Ids.skull - 256))
			.setTextureFile(ITEMS_PNG).setIconIndex(18)
			.setItemName("Human Skull")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item skullAnimal = (new ItemSkullAnimal(Ids.skullA - 256))
			.setTextureFile(ITEMS_PNG).setIconIndex(19)
			.setItemName("Animal Skull")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item blaster = (new ItemBlaster(Ids.blaster - 256))
			.setTextureFile(ITEMS_PNG).setIconIndex(21)
			.setItemName("blaster")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item smack = new ItemSmack(Ids.smack - 256)
	.setTextureFile(ITEMS_PNG).setIconIndex(22)
			.setItemName("smack")
			.setCreativeTab(RiseAutomatons.tabAutomatons);

	public static Item chisel;
	public static Item mortar;
	static {
		chisel = (new ItemChisel(Ids.chisel - 256)).setMaxStackSize(1)
				.setTextureFile(ITEMS_PNG).setIconIndex(20)
				.setItemName("Chisel")
				.setCreativeTab(RiseAutomatons.tabAutomatons);
		chisel.setContainerItem(chisel);
		mortar = new Item(Ids.mortar - 256)
				.setTextureFile(ITEMS_PNG).setIconIndex(23)
				.setMaxStackSize(1).setContainerItem(mortar)
				.setItemName("mortar")
				.setCreativeTab(RiseAutomatons.tabAutomatons);
	}
}

