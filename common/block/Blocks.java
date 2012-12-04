package riseautomatons.common.block;

import riseautomatons.common.Ids;
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
	public static final String BLOCK_PNG = "/RiseAutomatons/block.png";
	public static final String PATTERNS_PNG = "/RiseAutomatons/patterns.png";

	public static void init() {
		GameRegistry.registerBlock(worker);
		LanguageRegistry.addName(worker, "Worker");
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
		registerTextures();
	}

	@SideOnly(Side.CLIENT)
	public static void registerTextures() {
		MinecraftForgeClient.preloadTexture(PATTERNS_PNG);
		RenderingRegistry.registerBlockHandler(new RenderWorkerBlock());
		RenderingRegistry.registerBlockHandler(new RenderChalkBlock());
	}

	public static Block worker = (new BlockWorker(Ids.blockWorker))
			.setHardness(0.5F).setResistance(10F).setBlockName("Worker");
	public static Block gearbox = (new BlockGearBox(Ids.blockGearbox, 10, Material.iron))
			.setTextureFile(BLOCK_PNG)
			.setHardness(1F).setResistance(5F).setStepSound(Block.soundMetalFootstep).setBlockName("Gearbox")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block chalk = (new BlockChalk(Ids.itemChalk, 164))
			.setHardness(0f).setStepSound(Block.soundPowderFootstep).setBlockName("Chalk");
	public static Block turnBlock = (new BlockTurn(Ids.blockTurn))
			.setHardness(0.05f).setResistance(0)
			.setStepSound(Block.soundStoneFootstep).setBlockName("turnBlock")
			.setCreativeTab(RiseAutomatons.tabAutomatons);
	public static Block windmill = (new BlockWindmill(Ids.blockWindmill, 4,
			Material.wood)).setHardness(0.5F).setResistance(1F)
			.setStepSound(Block.soundWoodFootstep).setBlockName("windmill")
			.setCreativeTab(RiseAutomatons.tabAutomatons);


}
