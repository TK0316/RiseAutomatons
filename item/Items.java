package riseautomatons.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import riseautomatons.Ids;
import riseautomatons.RiseAutomatons;

public class Items {

	public static final ResourceLocation ITEMS_PNG = new ResourceLocation("riseautomatons", "textures/items.png");
	public static final ResourceLocation CRAFTSET_PNG = new ResourceLocation("riseautomatons", "craftset.png");
	public static final ResourceLocation SOULCORE_PNG = new ResourceLocation("riseautomatons", "soulcore.png");

	public static void init() {
		/*
		// Automatons
		LanguageRegistry.instance().addNameForObject(worker, "en_US", "Worker");
		LanguageRegistry.instance().addNameForObject(sentry, "en_US", "Sentry");
		LanguageRegistry.instance().addNameForObject(tote, "en_US", "Tote");
		LanguageRegistry.instance().addNameForObject(factotum, "en_US", "Factotum");
		LanguageRegistry.instance().addNameForObject(beacon, "en_US", "Beacon");
		LanguageRegistry.instance().addNameForObject(guard, "en_US", "Guard Turret");
		LanguageRegistry.instance().addNameForObject(omni, "en_US", "Omni");

		// CraftSet
		for (int i = 0; i < EnumCraftSetType.values().length; i++) {
			LanguageRegistry.instance().addNameForObject(
					new ItemStack(Ids.craftSet, 1, i), "en_US",
					EnumCraftSetType.values()[i].fullname);
		}
		OreDictionary.registerOre("dustSalt", new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SALT.ordinal()));
		OreDictionary.registerOre("dustSulfur", new ItemStack(Ids.craftSet, 1, EnumCraftSetType.SURF.ordinal()));
		OreDictionary.registerOre("itemIronRod", new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal()));

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
		LanguageRegistry.instance().addNameForObject(naturizer, "en_US", "Organic Conversion System");
		LanguageRegistry.instance().addNameForObject(techifier, "en_US", "Bionic Conversion System");
		LanguageRegistry.instance().addNameForObject(pickTech, "en_US", "Charged Pick");

		LanguageRegistry.instance().addNameForObject(new ItemStack(Ids.itemVirus, 1, 0), "en_US", "Self-Destruction Program(Inactive)");
		LanguageRegistry.instance().addNameForObject(new ItemStack(Ids.itemVirus, 1, 1), "en_US", "Self-Destruction Program(Active)");
		*/
	}
	// Automatons
	public static Item worker = (new ItemBot(Ids.itemWorker - 256, EnumBotType.WORKER))
			.setUnlocalizedName("riseautomatons:worker")
			.setTextureName("riseautomatons:worker")
			.setMaxStackSize(8)
			.setCreativeTab(RiseAutomatons.tabAutomatons);
    public static Item sentry = (new ItemBot(Ids.itemSentry - 256, EnumBotType.SENTRY))
			.setUnlocalizedName("riseautomatons:sentry")
			.setTextureName("riseautomatons:sentry")
			.setMaxStackSize(1)
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item factotum = (new ItemBot(Ids.itemFactotum - 256,EnumBotType.FACTOTUM))
			.setUnlocalizedName("riseautomatons:itemfactotum")
			.setTextureName("riseautomatons:itemfactotum")
			.setMaxStackSize(1)
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item beacon = (new ItemBot(Ids.itemBeacon - 256, EnumBotType.BEACON))
			.setUnlocalizedName("riseautomatons:beacon")
			.setTextureName("riseautomatons:beacon")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item guard = (new ItemBot(Ids.itemGuard - 256, EnumBotType.GUARD))
			.setUnlocalizedName("riseautomatons:guard")
			.setTextureName("riseautomatons:guard")
			.setMaxStackSize(64)
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item omni = (new ItemBot(Ids.itemOmni - 256, EnumBotType.OMNI))
			.setUnlocalizedName("riseautomatons:itemOmni")
			.setTextureName("riseautomatons:itemOmni")
			.setMaxStackSize(1)
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item tote = (new ItemBot(Ids.itemTote - 256, EnumBotType.TOTE))
			.setUnlocalizedName("riseautomatons:itemTote")
			.setTextureName("riseautomatons:itemTote")
			.setMaxStackSize(1)
			.setCreativeTab(RiseAutomatons.tabAutomatons);

	// Normal Items
	public static Item craftset = (new ItemCraftSet(Ids.craftSet - 256))
			.setUnlocalizedName("riseautomatons:craftset")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item chalk = (new ItemChalk(Ids.itemChalk - 256))
			.setUnlocalizedName("riseautomatons:chalk")
			.setTextureName("riseautomatons:chalk")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item spring = (new Item(Ids.spring - 256))
			.setUnlocalizedName("riseautomatons:spring")
			.setTextureName("riseautomatons:spring")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item soulCore = (new ItemSoulCore(Ids.soulCore - 256))
			.setUnlocalizedName("riseautomatons:soulCore")
			.setTextureName("riseautomatons:soulCore")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item skull = (new ItemSkull(Ids.skull - 256))
			.setUnlocalizedName("riseautomatons:skull")
			.setTextureName("riseautomatons:skull")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item skullAnimal = (new ItemSkullAnimal(Ids.skullA - 256))
			.setUnlocalizedName("riseautomatons:skullA")
			.setTextureName("riseautomatons:skullA")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item blaster = (new ItemBlaster(Ids.blaster - 256))
			.setUnlocalizedName("riseautomatons:blaster")
			.setTextureName("riseautomatons:blaster")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item smack = new ItemSmack(Ids.smack - 256)
			.setUnlocalizedName("riseautomatons:sliderpan")
			.setTextureName("riseautomatons:sliderpan")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item naturizer = new ItemFunctional(Ids.naturizer - 256, 4)
			.setUnlocalizedName("riseautomatons:naturizer")
			.setTextureName("riseautomatons:naturizer")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item techifier = new ItemFunctional(Ids.techifier - 256, 2)
			.setUnlocalizedName("riseautomatons:techifier")
			.setTextureName("riseautomatons:techifier")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item pickTech = new ItemAPickaxe(Ids.pickTech - 256)
			.setUnlocalizedName("riseautomatons:picktech")
			.setTextureName("riseautomatons:picktech")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Item virus = new ItemVirus(Ids.itemVirus - 256)
	.setUnlocalizedName("riseautomatons:virus")
	.setTextureName("riseautomatons:virus")
	.setCreativeTab(RiseAutomatons.tabAutomatons);

	public static Item chisel;
	public static Item mortar;
	static {
		chisel = (new ItemChisel(Ids.chisel - 256)).setMaxStackSize(1)
				.setUnlocalizedName("riseautomatons:Chisel")
				.setTextureName("riseautomatons:Chisel")
				.setCreativeTab(RiseAutomatons.tabAutomatons);
		chisel.setContainerItem(chisel);
		mortar = new Item(Ids.mortar - 256)
				.setMaxStackSize(1)
				.setUnlocalizedName("riseautomatons:mortar")
				.setTextureName("riseautomatons:mortar")
				.setCreativeTab(RiseAutomatons.tabAutomatons);
		mortar.setContainerItem(mortar);
	}
}

