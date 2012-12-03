package riseautomatons.common.block;

import riseautomatons.common.Ids;
import riseautomatons.common.RiseAutomatons;
import net.minecraft.src.Block;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {

	public static void init() {
		GameRegistry.registerBlock(worker);
		LanguageRegistry.addName(worker, "Worker");
		registerTextures();
	}

	@SideOnly(Side.CLIENT)
	public static void registerTextures() {
		RenderingRegistry.registerBlockHandler(new RenderWorkerBlock());
	}

	public static Block worker = (new BlockWorker(Ids.blockWorker))
			.setHardness(0.5F).setResistance(10F).setBlockName("Worker");
}
