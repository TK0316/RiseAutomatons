package riseautomatons.common.block;

import riseautomatons.common.Ids;
import riseautomatons.common.OreGenerator;
import riseautomatons.common.RiseAutomatons;
import riseautomatons.common.entity.EntityWorker;
import riseautomatons.common.item.ItemArch;
import riseautomatons.common.item.ItemComplex;
import riseautomatons.common.item.ItemLumo;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	public static final String BLOCK_PNG = "/riseautomatons/block.png";
	public static final String PATTERNS_PNG = "/riseautomatons/patterns.png";
	public static int skyIconIndex = 13;
	public static int walkIconIndex  = 14;

	public static void init() {
		GameRegistry.registerBlock(worker);
		LanguageRegistry.addName(worker, "Worker");
		GameRegistry.registerBlock(sentry);
		LanguageRegistry.addName(sentry, "Sentry");

		GameRegistry.registerBlock(gearbox);
		LanguageRegistry.addName(gearbox, "Gearbox");
		GameRegistry.registerBlock(chalk);
		LanguageRegistry.addName(chalk, "Chalk");
		BlockChalk.rendererId = RenderingRegistry.getNextAvailableRenderId();
		GameRegistry.registerBlock(turnBlock);
		LanguageRegistry.addName(turnBlock, "Shaft");
		ModLoader.registerTileEntity(TileEntityTurn.class, "Turn",
				new TileEntityTurnRenderer());
		GameRegistry.registerBlock(windmill);
		LanguageRegistry.addName(windmill, "Windmill");
		ModLoader.registerTileEntity(TileEntityWindmill.class, "Windmill",
				new TileEntityWindmillRenderer());
		GameRegistry.registerBlock(slabBlock);
		LanguageRegistry.addName(slabBlock, "Slab");
		GameRegistry.registerBlock(saltOre);
		LanguageRegistry.addName(saltOre, "Salt Ore");
		GameRegistry.registerBlock(sulfOre);
		LanguageRegistry.addName(sulfOre, "Sulfide");
		GameRegistry.registerBlock(tech, ItemComplex.class);
		LanguageRegistry.addName(new ItemStack(tech, 1, 0), "Ancient Construct");
		LanguageRegistry.addName(new ItemStack(tech, 1, 1), "Bionic Mass");
		LanguageRegistry.addName(new ItemStack(tech, 1, 2), "Domestic Tile");
		LanguageRegistry.addName(new ItemStack(tech, 1, 3), "Domestic Tile2");
		GameRegistry.registerBlock(sky, ItemLumo.class);
		LanguageRegistry.addName(new ItemStack(sky, 1, 0), "Atmospheric simulator");
		LanguageRegistry.addName(new ItemStack(sky, 1, 1), "Walkway Tile");
		GameRegistry.registerBlock(arch, ItemArch.class);
		LanguageRegistry.addName(arch, "Architectural Shell");
		GameRegistry.registerBlock(architect);
		LanguageRegistry.addName(architect, "Architect");
		ModLoader.registerTileEntity(TileEntityArchitect.class, "Architect",
				new TileEntityArchitectRenderer());
		GameRegistry.registerBlock(archBend);
		LanguageRegistry.addName(archBend, "Architectural Connector");
		GameRegistry.registerBlock(crink);
		LanguageRegistry.addName(crink, "Foliage Array");
		GameRegistry.registerBlock(frass);
		LanguageRegistry.addName(frass, "Frass");

		GameRegistry.registerBlock(teleporter);
		LanguageRegistry.addName(teleporter, "Teleporter");

		GameRegistry.registerWorldGenerator(new OreGenerator());
		registerTextures();
	}

	@SideOnly(Side.CLIENT)
	public static void registerTextures() {
		MinecraftForgeClient.preloadTexture(BLOCK_PNG);
		MinecraftForgeClient.preloadTexture(PATTERNS_PNG);
		RenderingRegistry.registerBlockHandler(new RenderWorkerBlock());
		RenderingRegistry.registerBlockHandler(new RenderSentryBlock());
		RenderingRegistry.registerBlockHandler(new RenderChalkBlock());
	}

	public static Block worker = (new BlockWorker(Ids.blockWorker))
			.setHardness(0.5F).setResistance(10F).setBlockName("Worker");
    public static Block sentry = (new BlockSentry(Ids.blockSentry))
    		.setHardness(0.5F).setResistance(10F).setBlockName("Sentry");

	public static Block gearbox = (new BlockGearBox(Ids.blockGearbox, 10, Material.iron))
			.setTextureFile(BLOCK_PNG)
			.setHardness(1F).setResistance(5F).setStepSound(Block.soundMetalFootstep).setBlockName("Gearbox")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block chalk = (new BlockChalk(Ids.blockChalk, 164))
			.setHardness(0f).setStepSound(Block.soundPowderFootstep).setBlockName("Chalk");
	public static Block turnBlock = (new BlockTurn(Ids.blockTurn))
			.setHardness(0.05f).setResistance(0)
			.setStepSound(Block.soundStoneFootstep).setBlockName("TurnBlock")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block windmill = (new BlockWindmill(Ids.blockWindmill, 4,
			Material.wood)).setHardness(0.5F).setResistance(1F)
			.setStepSound(Block.soundWoodFootstep).setBlockName("Windmill")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block slabBlock = (new BlockSlab(Ids.blockSlab))
			.setHardness(1.5F).setResistance(10F).setBlockName("SlabBlock")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block sulfOre = (new BlockOre(Ids.sulfOre, 0))
			.setTextureFile(BLOCK_PNG)
			.setHardness(3F).setResistance(5F)
			.setStepSound(Block.soundStoneFootstep).setBlockName("SulfOre")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block saltOre = (new BlockOre(Ids.saltOre, 1))
			.setTextureFile(BLOCK_PNG)
			.setHardness(3F).setResistance(5F)
			.setStepSound(Block.soundStoneFootstep).setBlockName("SaltOre")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block tech = (new BlockComplex(Ids.blockTech))
			.setHardness(1.0F).setResistance(5F)
			.setStepSound(Block.soundStoneFootstep).setBlockName("tech")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block arch = (new BlockArch(Ids.blockArch))
			.setHardness(0.25F).setStepSound(Block.soundGlassFootstep)
			.setBlockName("arch").setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block sky = (new BlockLumo(Ids.blockSky)).setHardness(1F)
			.setResistance(5F).setLightValue(1F)
			.setStepSound(Block.soundGlassFootstep).setBlockName("sky")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block archBend = (new BlockArchBend(Ids.blockArchBend))
			.setHardness(0.15F).setStepSound(Block.soundGlassFootstep)
			.setBlockName("archbend").setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block architect = (new BlockArchitect(Ids.blockArchitect))
			.setBlockUnbreakable().setStepSound(Block.soundStoneFootstep)
			.setBlockName("coreMachine")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block crink = (new BlockCrink(Ids.blockCrink, 234))
			.setHardness(0.1F).setLightOpacity(1).setLightValue(0.5F)
			.setStepSound(Block.soundGlassFootstep).setBlockName("crink")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
    public static Block frass = (new BlockFrass(Ids.blockFrass))
			.setTextureFile(BLOCK_PNG)
    		.setHardness(0.25F).setStepSound(Block.soundGlassFootstep).setBlockName("frass")
    		.setCreativeTab(RiseAutomatons.tabAutomatons);

	public static Block teleporter = (new BlockTeleporter())
			.setCreativeTab(RiseAutomatons.tabAutomatons);

}
