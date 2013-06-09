package riseautomatons.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.ModLoader;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderToteBlock implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess iblockaccess, int i, int j, int k,
			Block block, int modelId, RenderBlocks renderblocks) {
		RenderEngine renderengine = ModLoader.getMinecraftInstance().renderEngine;

		Tessellator.instance.draw();
        Tessellator.instance.startDrawingQuads();
        renderengine.bindTexture("/riseautomatons/tote.png");
        boolean bool = render(renderblocks, iblockaccess, block, i, j, k);
        Tessellator.instance.draw();
        Tessellator.instance.startDrawingQuads();
        renderengine.bindTexture("/terrain.png");
        return bool;
	}

	static float  f632=6f/32f;
	static float f2064=20f/64f;
	static float f2664=26f/64f;
	static float f3664=36f/64f;
	static float f4664=46f/64f;

	static float  f1032=10f/32f;
	static float  f5464=54f/64f;

	private boolean render(RenderBlocks renderblocks,
			IBlockAccess blockAccess, Block block, int i, int j, int k) {

		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess, i, j, k));
		tessellator.setColorOpaque_F(1, 1, 1);


		tessellator.addVertexWithUV(i+0.1875f, j + 0.5625f, k + 0.1875f, f3664, 0);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.5625f, k + 0.1875f, f4664, 0);
		tessellator.addVertexWithUV(i + 0.8125f, j+0.0625f, k + 0.1875f, f4664, f632);
		tessellator.addVertexWithUV(i+0.1875f, j+0.0625f, k + 0.1875f, f3664, f632);

		tessellator.addVertexWithUV(i + 0.8125f, j + 0.5625f, k + 0.8125f, f3664, 0);
		tessellator.addVertexWithUV(i+0.1875f, j + 0.5625f, k + 0.8125f, f2664, 0);
		tessellator.addVertexWithUV(i+0.1875f, j+0.0625f, k + 0.8125f, f2664, f632);
		tessellator.addVertexWithUV(i + 0.8125f, j+0.0625f, k + 0.8125f, f3664, f632);

		tessellator.addVertexWithUV(i + 0.8125f, j + 0.5625f, k+0.1875f, 1,  f1032);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.5625f, k + 0.8125f,f5464, f1032);
		tessellator.addVertexWithUV(i + 0.8125f, j+0.0625f, k + 0.8125f, f5464, 0.5f);
		tessellator.addVertexWithUV(i + 0.8125f, j+0.0625f, k+0.1875f, 1,  0.5f);

		tessellator.addVertexWithUV(i + 0.1875f, j + 0.5625f, k + 0.8125f,f5464, f1032);
		tessellator.addVertexWithUV(i + 0.1875f, j + 0.5625f, k+0.1875f, 1,  f1032);
		tessellator.addVertexWithUV(i + 0.1875f, j+0.0625f, k+0.1875f, 1,  0.5f);
		tessellator.addVertexWithUV(i + 0.1875f, j+0.0625f, k + 0.8125f, f5464, 0.5f);

		tessellator.addVertexWithUV(i + 0.1875f, j + 0.5625f, k + 0.1875f, f2664, f632);
		tessellator.addVertexWithUV(i + 0.1875f, j + 0.5625f, k + 0.8125f, f2664, 0.5f);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.5625f, k + 0.8125f, f3664, 0.5f);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.5625f, k + 0.1875f, f3664, f632);

		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return BlockTote.renderId;
	}

}
