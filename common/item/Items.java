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

	public static final String ITEMS_PNG = "/RiseAutomatons/items.png";
	public static final String CRAFTSET_PNG = "/RiseAutomatons/craftset.png";
	public static final String SOULCORE_PNG = "/RiseAutomatons/soulcore.png";

	public static void init() {
		LanguageRegistry.instance().addNameForObject(worker, "en_US", "Worker");
		for (int i = 0; i < EnumCraftSetType.values().length; i++) {
			LanguageRegistry.instance().addNameForObject(
					new ItemStack(Ids.craftSet, 1, i), "en_US",
					EnumCraftSetType.values()[i].fullname);
		}
		LanguageRegistry.instance().addNameForObject(spring, "en_US", "Spring");
		LanguageRegistry.instance().addNameForObject(chalk, "en_US", "Chalk");
		for (int i = 0; i < EnumSoulCore.values().length; i++) {
			LanguageRegistry.instance().addNameForObject(
					new ItemStack(Ids.soulCore, 1, i), "en_US",
					EnumSoulCore.values()[i].fullname);
		}
		LanguageRegistry.instance().addNameForObject(skull, "en_US", "Human Skull");
		LanguageRegistry.instance().addNameForObject(skullAnimal, "en_US", "Animal Skull");
		LanguageRegistry.instance().addNameForObject(chisel, "en_US", "Chisel");
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

	public static Item chisel;
	static {
		chisel = (new ItemChisel(Ids.chisel - 256)).setMaxStackSize(1)
				.setTextureFile(ITEMS_PNG).setIconIndex(20)
				.setItemName("Chisel")
				.setCreativeTab(RiseAutomatons.tabAutomatons);
		chisel.setContainerItem(chisel);
	}
}

