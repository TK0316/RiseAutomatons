package riseautomatons.common.block;

import riseautomatons.common.Ids;
import riseautomatons.common.OreGenerator;
import riseautomatons.common.RiseAutomatons;
import riseautomatons.common.entity.EntityWorker;
import net.minecraft.src.Block;
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
}
