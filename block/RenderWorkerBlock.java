package riseautomatons.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.ModLoader;
import net.minecraft.world.IBlockAccess;
import riseautomatons.entity.EntityWorker;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderWorkerBlock implements ISimpleBlockRenderingHandler {

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
        renderengine.bindTexture(EntityWorker.GOLEM1_PNG);
        boolean bool = render(renderblocks, iblockaccess, block, i, j, k);
        Tessellator.instance.draw();
        Tessellator.instance.startDrawingQuads();
        renderengine.bindTexture("/terrain.png");
        return bool;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return BlockWorker.renderId;
	}

    static float ff = 0.0625f;

    static float f2f = 2 * ff;
    static float f8f = 8 * ff;
    static float f14f = 14 * ff;

    static float f4f = 4 * ff;
    static float f6f = 6 * ff;
    static float f12f = 12 * ff;

    static float f10f = 10 * ff;
    static float f9f = 9 * ff;
    static float f7f = 7 * ff;

    static float f3f = 3 * ff;

    static float f13f = 13 * ff;

    static float ff5s = 5.5f * ff;
    static float ff5e = 10.5f * ff;

    static float ff5su = ff5s * ff;
    static float ff5eu = ff5e * ff;

    static  float f5f = 5 * ff;

    static float f332 = 3f / 32f;
    static  float f1132 = 11f / 32f;
    static float f1932 = 19f / 32f;
    static  float f4564 = 45f / 64f;
    static  float f5564 = (55f / 64f);
    static float f5864 = (58f / 64f);
    static float f5964 = (59f / 64f);

    static float f4964 = (49f / 64f);
    static  float f2332 = (23f / 32f);
    static  float f2632 = (26f / 32f);

    static float f1464 = 14f / 64f;
    static float f1964 = 19f / 64f;
    static float f2732 = 27f / 32f;

    static float f564 = 5 / 64f;

    static float f764 = 7f / 64f;
    static float f2664 = 26f / 64f;
    static float f3164 = 31f / 64f;
    static float f2032 = 20f / 32f;

    static float f6164=61f/64f;
    static float f2932=29f/32f;

    public static boolean render(RenderBlocks renderblocks, IBlockAccess blockAccess, Block block, int i, int j, int k)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess, i, j, k));
        tessellator.setColorOpaque_F(1, 1, 1);
        //if(blockAccess.isBlockOpaqueCube(i-1, j, k)){

        int meta=blockAccess.getBlockMetadata(i, j, k);

        if(meta==0){
        tessellator.addVertexWithUV(i + f2f, j + f8f, k + f6f, f4564, f332);
        tessellator.addVertexWithUV(i + f14f, j + f8f, k + f6f, f5564, f332);
        tessellator.addVertexWithUV(i + f14f, j, k + f6f, f5564, f1132);
        tessellator.addVertexWithUV(i + f2f, j, k + f6f, f4564, f1132);

        tessellator.addVertexWithUV(i + f14f, j + f8f, k + f9f, f4564, f1132);
        tessellator.addVertexWithUV(i + f2f, j + f8f, k + f9f, f5564, f1132);
        tessellator.addVertexWithUV(i + f2f, j, k + f9f, f5564, f1932);
        tessellator.addVertexWithUV(i + f14f, j, k + f9f, f4564, f1932);

        tessellator.addVertexWithUV(i + f2f, j + f8f, k + f9f, f5864, f1132);
        tessellator.addVertexWithUV(i + f2f, j + f8f, k + f6f, f5564, f1132);
        tessellator.addVertexWithUV(i + f2f, j, k + f6f, f5564, f1932);
        tessellator.addVertexWithUV(i + f2f, j, k + f9f, f5864, f1932);

        tessellator.addVertexWithUV(i + f14f, j + f8f, k + f6f, f5564, f1932);
        tessellator.addVertexWithUV(i + f14f, j + f8f, k + f9f, f5864, f1932);
        tessellator.addVertexWithUV(i + f14f, j, k + f9f, f5864, f1132);
        tessellator.addVertexWithUV(i + f14f, j, k + f6f, f5564, f1132);


        //top bod
        tessellator.addVertexWithUV(i + f2f, j + f8f, k + f6f, f4964, f2332);
        tessellator.addVertexWithUV(i + f2f, j + f8f, k + f9f, f4964, f2632);
        tessellator.addVertexWithUV(i + f14f, j + f8f, k + f9f, f5964, f2632);
        tessellator.addVertexWithUV(i + f14f, j + f8f, k + f6f, f5964, f2332);

        tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f13f, 0, f2732);
        tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f13f, f564, f2732);
        tessellator.addVertexWithUV(i + ff5s, j + f8f, k + f13f, f564, 1);
        tessellator.addVertexWithUV(i + ff5e, j + f8f, k + f13f, 0, 1);

        tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f6f, f3164, f2732);
        tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f6f, f2664, f2732);
        tessellator.addVertexWithUV(i + ff5e, j + f8f, k + f6f, f2664, 1);
        tessellator.addVertexWithUV(i + ff5s, j + f8f, k + f6f, f3164, 1);

        tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f13f, f1464, f2732);
        tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f6f, f764, f2732);
        tessellator.addVertexWithUV(i + ff5s, j + f8f, k + f6f, f764, 1);
        tessellator.addVertexWithUV(i + ff5s, j + f8f, k + f13f, f1464, 1);

        tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f6f, f764, f2732);
        tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f13f, f1464, f2732);
        tessellator.addVertexWithUV(i + ff5e, j + f8f, k + f13f, f1464, 1);
        tessellator.addVertexWithUV(i + ff5e, j + f8f, k + f6f, f764, 1);


        //top head
        tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f13f, f1464, f2032);
        tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f13f, f1464, f2732);
        tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f6f, f1964, f2732);
        tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f6f, f1964, f2032);
        }else if(meta==1){
        	tessellator.addVertexWithUV(i + f7f, j + f8f, k + f14f, f5564, f1132);
        	tessellator.addVertexWithUV(i + f7f, j + f8f, k + f2f, f4564, f1132);
            tessellator.addVertexWithUV(i + f7f, j, k + f2f, f4564, f1932);
            tessellator.addVertexWithUV(i + f7f, j, k + f14f, f5564, f1932);

            tessellator.addVertexWithUV(i + f10f, j + f8f, k + f2f, f5564, f332);
            tessellator.addVertexWithUV(i + f10f, j + f8f, k + f14f, f4564, f332);
            tessellator.addVertexWithUV(i + f10f, j, k + f14f, f4564, f1132);
            tessellator.addVertexWithUV(i + f10f, j, k + f2f, f5564, f1132);

            tessellator.addVertexWithUV(i + f7f, j + f8f, k + f2f, f5564, f1132);
            tessellator.addVertexWithUV(i + f10f, j + f8f, k + f2f, f5864, f1132);
            tessellator.addVertexWithUV(i + f10f, j, k + f2f, f5864, f1932);
            tessellator.addVertexWithUV(i + f7f, j, k + f2f, f5564, f1932);

            tessellator.addVertexWithUV(i + f10f, j + f8f, k + f14f, f5864, f1932);
            tessellator.addVertexWithUV(i + f7f, j + f8f, k + f14f, f5564, f1932);
            tessellator.addVertexWithUV(i + f7f, j, k + f14f, f5564, f1132);
            tessellator.addVertexWithUV(i + f10f, j, k + f14f, f5864, f1132);

            //top bod
            tessellator.addVertexWithUV(i + f7f, j + f8f, k + f2f, f6164, f2032);
            tessellator.addVertexWithUV(i + f7f, j + f8f, k + f14f, f6164, f2932);
            tessellator.addVertexWithUV(i + f10f, j + f8f, k + f14f, 1, f2932);
            tessellator.addVertexWithUV(i + f10f, j + f8f, k + f2f, 1, f2032);

            tessellator.addVertexWithUV(i + f10f, j + f13f, k + ff5s, f2664, f2732);
            tessellator.addVertexWithUV(i + f10f, j + f13f, k + ff5e, f3164, f2732);
            tessellator.addVertexWithUV(i + f10f, j + f8f, k + ff5e, f3164, 1);
            tessellator.addVertexWithUV(i + f10f, j + f8f, k + ff5s, f2664, 1);

            tessellator.addVertexWithUV(i + f3f, j + f13f, k + ff5e, f564, f2732);
            tessellator.addVertexWithUV(i + f3f, j + f13f, k + ff5s, 0, f2732);
            tessellator.addVertexWithUV(i + f3f, j + f8f, k + ff5s, 0, 1);
            tessellator.addVertexWithUV(i + f3f, j + f8f, k + ff5e, f564, 1);

            tessellator.addVertexWithUV(i + f3f, j + f13f, k + ff5s, f1464, f2732);
            tessellator.addVertexWithUV(i + f10f, j + f13f, k + ff5s, f764, f2732);
            tessellator.addVertexWithUV(i + f10f, j + f8f, k + ff5s, f764, 1);
            tessellator.addVertexWithUV(i + f3f, j + f8f, k + ff5s, f1464, 1);

            tessellator.addVertexWithUV(i + f10f, j + f13f, k + ff5e, f764, f2732);
            tessellator.addVertexWithUV(i + f3f, j + f13f, k + ff5e, f1464, f2732);
            tessellator.addVertexWithUV(i + f3f, j + f8f, k + ff5e, f1464, 1);
            tessellator.addVertexWithUV(i + f10f, j + f8f, k + ff5e, f764, 1);


            //top head
            tessellator.addVertexWithUV(i + f10f, j + f13f, k + ff5e, f1464, f2732);
            tessellator.addVertexWithUV(i + f10f, j + f13f, k + ff5s, f1464, f2032);
            tessellator.addVertexWithUV(i + f3f, j + f13f, k + ff5s, f1964, f2032);
            tessellator.addVertexWithUV(i + f3f, j + f13f, k + ff5e, f1964, f2732);

        }else if(meta==2){
            tessellator.addVertexWithUV(i + f2f, j + f8f, k + f7f, f4564, f1132);
            tessellator.addVertexWithUV(i + f14f, j + f8f, k + f7f, f5564, f1132);
            tessellator.addVertexWithUV(i + f14f, j, k + f7f, f5564, f1932);
            tessellator.addVertexWithUV(i + f2f, j, k + f7f, f4564, f1932);

            tessellator.addVertexWithUV(i + f14f, j + f8f, k + f10f, f4564, f332);
            tessellator.addVertexWithUV(i + f2f, j + f8f, k + f10f, f5564, f332);
            tessellator.addVertexWithUV(i + f2f, j, k + f10f, f5564, f1132);
            tessellator.addVertexWithUV(i + f14f, j, k + f10f, f4564, f1132);

            tessellator.addVertexWithUV(i + f2f, j + f8f, k + f10f, f5864, f1132);
            tessellator.addVertexWithUV(i + f2f, j + f8f, k + f7f, f5564, f1132);
            tessellator.addVertexWithUV(i + f2f, j, k + f7f, f5564, f1932);
            tessellator.addVertexWithUV(i + f2f, j, k + f10f, f5864, f1932);

            tessellator.addVertexWithUV(i + f14f, j + f8f, k + f7f, f5564, f1932);
            tessellator.addVertexWithUV(i + f14f, j + f8f, k + f10f, f5864, f1932);
            tessellator.addVertexWithUV(i + f14f, j, k + f10f, f5864, f1132);
            tessellator.addVertexWithUV(i + f14f, j, k + f7f, f5564, f1132);


            //top bod
            tessellator.addVertexWithUV(i + f2f, j + f8f, k + f7f, f4964, f2332);
            tessellator.addVertexWithUV(i + f2f, j + f8f, k + f10f, f4964, f2632);
            tessellator.addVertexWithUV(i + f14f, j + f8f, k + f10f, f5964, f2632);
            tessellator.addVertexWithUV(i + f14f, j + f8f, k + f7f, f5964, f2332);

            tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f10f, f2664, f2732);
            tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f10f, f3164, f2732);
            tessellator.addVertexWithUV(i + ff5s, j + f8f, k + f10f, f3164, 1);
            tessellator.addVertexWithUV(i + ff5e, j + f8f, k + f10f, f2664, 1);

            tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f3f, f564, f2732);
            tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f3f, 0, f2732);
            tessellator.addVertexWithUV(i + ff5e, j + f8f, k + f3f, 0, 1);
            tessellator.addVertexWithUV(i + ff5s, j + f8f, k + f3f, f564, 1);

            tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f10f, f764, f2732);
            tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f3f, f1464, f2732);
            tessellator.addVertexWithUV(i + ff5s, j + f8f, k + f3f, f1464, 1);
            tessellator.addVertexWithUV(i + ff5s, j + f8f, k + f10f, f764, 1);

            tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f3f, f1464, f2732);
            tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f10f, f764, f2732);
            tessellator.addVertexWithUV(i + ff5e, j + f8f, k + f10f, f764, 1);
            tessellator.addVertexWithUV(i + ff5e, j + f8f, k + f3f, f1464, 1);


            //top head
            tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f10f, f1464, f2032);
            tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f10f, f1464, f2732);
            tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f3f, f1964, f2732);
            tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f3f, f1964, f2032);
        }else{

        	tessellator.addVertexWithUV(i + f6f, j + f8f, k + f14f, f5564, f332);
        	tessellator.addVertexWithUV(i + f6f, j + f8f, k + f2f, f4564, f332);
            tessellator.addVertexWithUV(i + f6f, j, k + f2f, f4564, f1132);
            tessellator.addVertexWithUV(i + f6f, j, k + f14f, f5564, f1132);

            tessellator.addVertexWithUV(i + f9f, j + f8f, k + f2f, f5564, f1132);
            tessellator.addVertexWithUV(i + f9f, j + f8f, k + f14f, f4564, f1132);
            tessellator.addVertexWithUV(i + f9f, j, k + f14f, f4564, f1932);
            tessellator.addVertexWithUV(i + f9f, j, k + f2f, f5564, f1932);

            tessellator.addVertexWithUV(i + f6f, j + f8f, k + f2f, f5564, f1132);
            tessellator.addVertexWithUV(i + f9f, j + f8f, k + f2f, f5864, f1132);
            tessellator.addVertexWithUV(i + f9f, j, k + f2f, f5864, f1932);
            tessellator.addVertexWithUV(i + f6f, j, k + f2f, f5564, f1932);

            tessellator.addVertexWithUV(i + f9f, j + f8f, k + f14f, f5864, f1932);
            tessellator.addVertexWithUV(i + f6f, j + f8f, k + f14f, f5564, f1932);
            tessellator.addVertexWithUV(i + f6f, j, k + f14f, f5564, f1132);
            tessellator.addVertexWithUV(i + f9f, j, k + f14f, f5864, f1132);

            //top bod
            tessellator.addVertexWithUV(i + f6f, j + f8f, k + f2f, f6164, f2032);
            tessellator.addVertexWithUV(i + f6f, j + f8f, k + f14f, f6164, f2932);
            tessellator.addVertexWithUV(i + f9f, j + f8f, k + f14f, 1, f2932);
            tessellator.addVertexWithUV(i + f9f, j + f8f, k + f2f, 1, f2032);

            tessellator.addVertexWithUV(i + f13f, j + f13f, k + ff5s, 0, f2732);
            tessellator.addVertexWithUV(i + f13f, j + f13f, k + ff5e, f564, f2732);
            tessellator.addVertexWithUV(i + f13f, j + f8f, k + ff5e, f564, 1);
            tessellator.addVertexWithUV(i + f13f, j + f8f, k + ff5s, 0, 1);

            tessellator.addVertexWithUV(i + f6f, j + f13f, k + ff5e, f3164, f2732);
            tessellator.addVertexWithUV(i + f6f, j + f13f, k + ff5s, f2664, f2732);
            tessellator.addVertexWithUV(i + f6f, j + f8f, k + ff5s, f2664, 1);
            tessellator.addVertexWithUV(i + f6f, j + f8f, k + ff5e, f3164, 1);

            tessellator.addVertexWithUV(i + f6f, j + f13f, k + ff5s, f764, f2732);
            tessellator.addVertexWithUV(i + f13f, j + f13f, k + ff5s, f1464, f2732);
            tessellator.addVertexWithUV(i + f13f, j + f8f, k + ff5s, f1464, 1);
            tessellator.addVertexWithUV(i + f6f, j + f8f, k + ff5s, f764, 1);

            tessellator.addVertexWithUV(i + f13f, j + f13f, k + ff5e, f1464, f2732);
            tessellator.addVertexWithUV(i + f6f, j + f13f, k + ff5e, f764, f2732);
            tessellator.addVertexWithUV(i + f6f, j + f8f, k + ff5e, f764, 1);
            tessellator.addVertexWithUV(i + f13f, j + f8f, k + ff5e, f1464, 1);


            //top head
            tessellator.addVertexWithUV(i + f13f, j + f13f, k + ff5e, f1464, f2732);
            tessellator.addVertexWithUV(i + f13f, j + f13f, k + ff5s, f1464, f2032);
            tessellator.addVertexWithUV(i + f6f, j + f13f, k + ff5s, f1964, f2032);
            tessellator.addVertexWithUV(i + f6f, j + f13f, k + ff5e, f1964, f2732);

        }
        return true;
    }
}
