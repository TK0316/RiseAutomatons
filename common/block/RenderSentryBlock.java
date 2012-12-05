package riseautomatons.common.block;

import riseautomatons.common.entity.EntitySentry;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ModLoader;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.Tessellator;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderSentryBlock implements ISimpleBlockRenderingHandler {

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
        renderengine.bindTexture(renderengine.getTexture(EntitySentry.SENTRYBLOCK_PNG));
        boolean bool = render(renderblocks, iblockaccess, block, i, j, k);
        Tessellator.instance.draw();
        Tessellator.instance.startDrawingQuads();
        renderengine.bindTexture(renderengine.getTexture("/terrain.png"));
        return bool;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return BlockSentry.renderId;
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

	static float f3f = 3 * ff;

	static float f13f = 13 * ff;

	static float ff5s = 5.5f * ff;
	static float ff5e = 10.5f * ff;

	static float ff5su = ff5s * ff;
	static float ff5eu = ff5e * ff;

	static  float f5f = 5 * ff;

	static float f332 = 3f / 32f;

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

	static float f532 = 5f / 32f;
	static float f1132 = 11f / 32f;

	static float f2732 = 27f / 32f;
	static float f432 = 4f / 32f;
	static float f1432 = 14f / 32f;
	static float f1832 = 18f / 32f;
	static float f3032 = 30f / 32f;
	static float f2232 = 22f / 32f;

	static float f2032=20f/32f;
	public static boolean render(RenderBlocks renderblocks, IBlockAccess blockAccess, Block block, int i, int j, int k)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess, i, j, k));
		tessellator.setColorOpaque_F(1, 1, 1);

		int m= blockAccess.getBlockMetadata(i, j, k);
		if(m==0){
			tessellator.addVertexWithUV(i, j + 1, k + 0.125f, 0, 0.5f);
			tessellator.addVertexWithUV(i + 1, j + 1, k + 0.125f, 0.5f, 0.5f);
			tessellator.addVertexWithUV(i + 1, j, k + 0.125f, 0.5f, 1f);
			tessellator.addVertexWithUV(i, j, k + 0.125f, 0, 1f);

			tessellator.addVertexWithUV(i + 1, j + 1, k + 0.875f, 0.5f, 0);
			tessellator.addVertexWithUV(i, j + 1, k + 0.875f, 0, 0);
			tessellator.addVertexWithUV(i, j, k + 0.875f, 0, 0.5f);
			tessellator.addVertexWithUV(i + 1, j, k + 0.875f, 0.5f, 0.5f);

			tessellator.addVertexWithUV(i + 0.8125f, j + 1, k, 1, 0);
			tessellator.addVertexWithUV(i + 0.8125f, j + 1, k + 1, 0.5f, 0);
			tessellator.addVertexWithUV(i + 0.8125f, j, k + 1, 0.5f, 0.5f);
			tessellator.addVertexWithUV(i + 0.8125f, j, k, 1, 0.5f);

			tessellator.addVertexWithUV(i + 0.1875f, j + 1, k + 1, 0.5f, 0);
			tessellator.addVertexWithUV(i + 0.1875f, j + 1, k, 1, 0);
			tessellator.addVertexWithUV(i + 0.1875f, j, k, 1, 0.5f);
			tessellator.addVertexWithUV(i + 0.1875f, j, k + 1, 0.5f, 0.5f);

			tessellator.addVertexWithUV(i + 0.1875f, j + 0.75f, k + 0.125f, f3032, f1432);
			tessellator.addVertexWithUV(i + 0.1875f, j + 0.75f, k + 0.875f, f1832, f1432);
			tessellator.addVertexWithUV(i + 0.8125f, j + 0.75f, k + 0.875f, f1832, f432);
			tessellator.addVertexWithUV(i + 0.8125f, j + 0.75f, k + 0.125f, f3032, f432);



			//snout
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.5f, k, 1, f2732);
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.5f, k + 1, 0.5f, f2732);
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.1875f, k + 1, 0.5f, 1);
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.1875f, k, 1, 1);

			tessellator.addVertexWithUV(i + 0.3125f, j + 0.5f, k + 1, 0.5f, f2732);
			tessellator.addVertexWithUV(i + 0.3125f, j + 0.5f, k, 1, f2732);
			tessellator.addVertexWithUV(i + 0.3125f, j + 0.1875f, k, 1, 1);
			tessellator.addVertexWithUV(i + 0.3125f, j + 0.1875f, k + 1, 0.5f, 1);

			tessellator.addVertexWithUV(i + 0.6875f, j + 0.5f, k + 1, f1132, 0.25);
			tessellator.addVertexWithUV(i + 0.3125f, j + 0.5f, k + 1, f532, 0.25);
			tessellator.addVertexWithUV(i + 0.3125f, j + 0.1875f, k + 1, f532, 0.40625f);
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.1875f, k + 1, f1132, 0.40625f);

			tessellator.addVertexWithUV(i + 0.6875f, j + 0.5f, k + 0.875f, f2232, 0.5f); //f2232, 0.5f);
			tessellator.addVertexWithUV(i + 0.3125f, j + 0.5f, k + 0.875f, 0.5f, 0.5f); //0.5f, 0.5f);
			tessellator.addVertexWithUV(i + 0.3125f, j + 0.5f, k + 1, 0.5f, f1832); //, f1832);
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.5f, k + 1, f2232, f1832); //f2232, f1832);

		}else if(m==1){
			tessellator.addVertexWithUV(i +  0.125f, j + 1, k +1, 0.5f, 0);
			tessellator.addVertexWithUV(i+ 0.125f, j + 1, k , 0, 0);
			tessellator.addVertexWithUV(i+ 0.125f, j, k , 0, 0.5f);
			tessellator.addVertexWithUV(i + 0.125f , j, k + 1, 0.5f, 0.5f);

			tessellator.addVertexWithUV(i + 0.875f, j + 1, k , 0.5f, 0.5f);
			tessellator.addVertexWithUV(i+0.875f, j + 1, k + 1, 0, 0.5f);
			tessellator.addVertexWithUV(i+0.875f, j, k + 1, 0, 1);
			tessellator.addVertexWithUV(i + 0.875f, j, k , 0.5f, 1);

			tessellator.addVertexWithUV(i + 1, j + 1, k+0.8125f, 1, 0);
			tessellator.addVertexWithUV(i , j + 1, k + 0.8125f, 0.5f, 0);
			tessellator.addVertexWithUV(i , j, k + 0.8125f, 0.5f, 0.5f);
			tessellator.addVertexWithUV(i + 1, j, k+0.8125f, 1, 0.5f);

			tessellator.addVertexWithUV(i , j + 1, k + 0.1875f, 0.5f, 0);
			tessellator.addVertexWithUV(i + 1, j + 1, k+0.1875f, 1, 0);
			tessellator.addVertexWithUV(i + 1, j, k+0.1875f, 1, 0.5f);
			tessellator.addVertexWithUV(i , j, k + 0.1875f, 0.5f, 0.5f);

			tessellator.addVertexWithUV(i + 0.125f, j + 0.75f, k + 0.1875f, 1, f2032);
			tessellator.addVertexWithUV(i + 0.125f, j + 0.75f, k + 0.8125f, f2232, f2032);
			tessellator.addVertexWithUV(i + 0.875f, j + 0.75f, k + 0.8125f, f2232, 1);
			tessellator.addVertexWithUV(i + 0.875f, j + 0.75f, k + 0.1875f, 1, 1);




			//snout
			tessellator.addVertexWithUV(i + 1, j + 0.5f, k + 0.6875f, 1, f2732);
			tessellator.addVertexWithUV(i , j + 0.5f, k+ 0.6875f,  0.5f, f2732);
			tessellator.addVertexWithUV(i , j + 0.1875f, k+ 0.6875f,  0.5f, 1);
			tessellator.addVertexWithUV(i + 1, j + 0.1875f, k + 0.6875f, 1, 1);

			tessellator.addVertexWithUV(i , j + 0.5f, k+ 0.3125f, 0.5f, f2732);
			tessellator.addVertexWithUV(i +1, j + 0.5f, k+ 0.3125f,1, f2732);
			tessellator.addVertexWithUV(i + 1, j + 0.1875f, k + 0.3125f,1, 1);
			tessellator.addVertexWithUV(i , j + 0.1875f, k+ 0.3125f,  0.5f, 1);

			tessellator.addVertexWithUV(i , j + 0.5f, k + 0.6875f, f1132, 0.25);
			tessellator.addVertexWithUV(i , j + 0.5f, k + 0.3125f, f532, 0.25);
			tessellator.addVertexWithUV(i , j + 0.1875f, k + 0.3125f, f532, 0.40625f);
			tessellator.addVertexWithUV(i , j + 0.1875f, k + 0.6875f, f1132, 0.40625f);

			tessellator.addVertexWithUV(i , j + 0.5f, k + 0.6875f, f2232, 0.5f);
			tessellator.addVertexWithUV(i + 0.125f, j + 0.5f, k + 0.6875f, 0.5f, 0.5f);
			tessellator.addVertexWithUV(i + 0.125f, j + 0.5f, k + 0.3125f, 0.5f, f1832);
			tessellator.addVertexWithUV(i , j + 0.5f, k + 0.3125f, f2232, f1832);

		}else if(m==2){
			tessellator.addVertexWithUV(i, j + 1, k + 0.125f, 0, 0);
			tessellator.addVertexWithUV(i + 1, j + 1, k + 0.125f, 0.5f, 0);
			tessellator.addVertexWithUV(i + 1, j, k + 0.125f, 0.5f, 0.5f);
			tessellator.addVertexWithUV(i, j, k + 0.125f, 0, 0.5f);

			tessellator.addVertexWithUV(i + 1, j + 1, k + 0.875f, 0.5f, 0.5f);
			tessellator.addVertexWithUV(i, j + 1, k + 0.875f, 0, 0.5f);
			tessellator.addVertexWithUV(i, j, k + 0.875f, 0, 1);
			tessellator.addVertexWithUV(i + 1, j, k + 0.875f, 0.5f, 1);

			tessellator.addVertexWithUV(i + 0.1875f, j + 1, k + 1, 1, 0);
			tessellator.addVertexWithUV(i + 0.1875f, j + 1, k, 0.5f, 0);
			tessellator.addVertexWithUV(i + 0.1875f, j, k, 0.5f, 0.5f);
			tessellator.addVertexWithUV(i + 0.1875f, j, k + 1,1, 0.5f);

			tessellator.addVertexWithUV(i + 0.8125f, j + 1, k, 0.5f, 0);
			tessellator.addVertexWithUV(i + 0.8125f, j + 1, k + 1,1, 0);
			tessellator.addVertexWithUV(i + 0.8125f, j, k + 1, 1, 0.5f);
			tessellator.addVertexWithUV(i + 0.8125f, j, k, 0.5f, 0.5f);

			tessellator.addVertexWithUV(i + 0.1875f, j + 0.75f, k + 0.125f, f1832 , f1432);
			tessellator.addVertexWithUV(i + 0.1875f, j + 0.75f, k + 0.875f, f3032, f1432);
			tessellator.addVertexWithUV(i + 0.8125f, j + 0.75f, k + 0.875f, f3032, f432);
			tessellator.addVertexWithUV(i + 0.8125f, j + 0.75f, k + 0.125f, f1832, f432);




			//snout
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.5f, k, 0.5f, f2732);
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.5f, k + 1, 1, f2732);
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.1875f, k + 1, 1, 1);
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.1875f, k, 0.5f, 1);

			tessellator.addVertexWithUV(i + 0.3125f, j + 0.5f, k + 1, 1, f2732);
			tessellator.addVertexWithUV(i + 0.3125f, j + 0.5f, k, 0.5f, f2732);
			tessellator.addVertexWithUV(i + 0.3125f, j + 0.1875f, k, 0.5f, 1);
			tessellator.addVertexWithUV(i + 0.3125f, j + 0.1875f, k + 1,1, 1);

			tessellator.addVertexWithUV(i + 0.3125f, j + 0.5f, k , f532, 0.25);
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.5f, k , f1132, 0.25);
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.1875f, k, f1132, 0.40625f);
			tessellator.addVertexWithUV(i + 0.3125f, j + 0.1875f, k , f532, 0.40625f);

			tessellator.addVertexWithUV(i + 0.3125f, j + 0.5f, k + 0.125f, 0.5f, f1832); //0.5f, 0.5f);
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.5f, k + 0.125f, f2232, f1832); //f2232, 0.5f);
			tessellator.addVertexWithUV(i + 0.6875f, j + 0.5f, k , f2232, 0.5f); //f2232, f1832);
			tessellator.addVertexWithUV(i + 0.3125f, j + 0.5f, k , 0.5f, 0.5f); //, f1832);

		}else {//if(m==3){
			tessellator.addVertexWithUV(i +  0.125f, j + 1, k +1, 0, 0.5f);
			tessellator.addVertexWithUV(i+ 0.125f, j + 1, k , 0.5f, 0.5f);
			tessellator.addVertexWithUV(i+ 0.125f, j, k , 0.5f, 1);
			tessellator.addVertexWithUV(i + 0.125f , j, k + 1, 0, 1);

			tessellator.addVertexWithUV(i + 0.875f, j + 1, k , 0, 0);
			tessellator.addVertexWithUV(i+0.875f, j + 1, k + 1, 0.5f, 0f);
			tessellator.addVertexWithUV(i+0.875f, j, k + 1, 0.5f, 0.5f);
			tessellator.addVertexWithUV(i + 0.875f, j, k , 0, 0.5f);

			tessellator.addVertexWithUV(i + 1, j + 1, k+0.8125f, 0.5f, 0);
			tessellator.addVertexWithUV(i , j + 1, k + 0.8125f, 1, 0);
			tessellator.addVertexWithUV(i , j, k + 0.8125f, 1, 0.5f);
			tessellator.addVertexWithUV(i + 1, j, k+0.8125f, 0.5f, 0.5f);

			tessellator.addVertexWithUV(i , j + 1, k + 0.1875f, 1, 0);
			tessellator.addVertexWithUV(i + 1, j + 1, k+0.1875f, 0.5f, 0);
			tessellator.addVertexWithUV(i + 1, j, k+0.1875f, 0.5f, 0.5f);
			tessellator.addVertexWithUV(i , j, k + 0.1875f,1, 0.5f);

			tessellator.addVertexWithUV(i + 0.125f, j + 0.75f, k + 0.1875f, 1, 1);
			tessellator.addVertexWithUV(i + 0.125f, j + 0.75f, k + 0.8125f, f2232, 1);
			tessellator.addVertexWithUV(i + 0.875f, j + 0.75f, k + 0.8125f, f2232, f2032);
			tessellator.addVertexWithUV(i + 0.875f, j + 0.75f, k + 0.1875f, 1, f2032);



			//snout
			tessellator.addVertexWithUV(i + 1, j + 0.5f, k + 0.6875f, 0.5f, f2732);
			tessellator.addVertexWithUV(i , j + 0.5f, k+ 0.6875f,  1, f2732);
			tessellator.addVertexWithUV(i , j + 0.1875f, k+ 0.6875f,  1, 1);
			tessellator.addVertexWithUV(i + 1, j + 0.1875f, k + 0.6875f, 0.5f, 1);

			tessellator.addVertexWithUV(i , j + 0.5f, k+ 0.3125f, 1, f2732);
			tessellator.addVertexWithUV(i +1, j + 0.5f, k+ 0.3125f,0.5f, f2732);
			tessellator.addVertexWithUV(i + 1, j + 0.1875f, k + 0.3125f,0.5f, 1);
			tessellator.addVertexWithUV(i , j + 0.1875f, k+ 0.3125f,  1, 1);

			tessellator.addVertexWithUV(i+1 , j + 0.5f, k + 0.3125f, f532, 0.25);
			tessellator.addVertexWithUV(i +1, j + 0.5f, k + 0.6875f, f1132, 0.25);
			tessellator.addVertexWithUV(i +1, j + 0.1875f, k + 0.6875f, f1132, 0.40625f);
			tessellator.addVertexWithUV(i+1 , j + 0.1875f, k + 0.3125f, f532, 0.40625f);

			tessellator.addVertexWithUV(i + 0.875f, j + 0.5f, k + 0.6875f, 0.5f, 0.5f);
			tessellator.addVertexWithUV(i+1 , j + 0.5f, k + 0.6875f, f2232, 0.5f);
			tessellator.addVertexWithUV(i+1 , j + 0.5f, k + 0.3125f, f2232, f1832);
			tessellator.addVertexWithUV(i + 0.875f, j + 0.5f, k + 0.3125f, 0.5f, f1832);

		}




		return true;
	}
}
