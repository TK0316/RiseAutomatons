package riseautomatons.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderChalkBlock implements ISimpleBlockRenderingHandler {
	private static final ResourceLocation BLOCK_TEXTURE = TextureMap.field_110575_b;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess iblockaccess, int i, int j,
			int k, Block block, int modelId, RenderBlocks renderer) {
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		//ModLoader.getMinecraftInstance().func_110434_K().func_110577_a(Blocks.PATTERNS_PNG);
		TextureManager renderengine = ModLoader.getMinecraftInstance().renderEngine;
		//renderengine.func_110577_a(Blocks.PATTERNS_PNG);
		boolean bool = RenderChalkBlock.chalkPatterns(block, i, j, k, iblockaccess);
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		//ModLoader.getMinecraftInstance().func_110434_K().func_110577_a(new ResourceLocation("/terrain.png"));
		//renderengine.bindTexture("/terrain.png");
        renderengine.func_110577_a(BLOCK_TEXTURE);
		return true;
	}

	private static boolean chalkPatterns(Block block, int i, int j, int k,
			IBlockAccess blockAccess) {

		Tessellator tessellator = Tessellator.instance;
		int l = blockAccess.getBlockMetadata(i, j, k);

		if (l > 0) {
			l--;
		}
		ModLoader.getMinecraftInstance().func_110434_K().func_110577_a(new ResourceLocation("riseautomatons", "textures/patterns.png"));

		tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess,
				i, j, k));
		// int i1 = block.getBlockTextureFromSideAndMetadata(1, l);
		// if(l==4) {
		// Tessellator.instance.setBrightness(255);
		// GL11.glColor3f(1f, 1f, 1f);
		// }else {
		// /
		// tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess,
		// i, j, k));
		// }
		// float f = 1.0F;
		// float f1 = (float)(l) / 4F;
		// float f2 = f1 * 0.6F + 0.4F;
		// float f9 = f1 * 0.6F + 0.2F;
		// tessellator.setColorOpaque_F(1, 1, 1);
		// tessellator.setColorOpaque_F(f9, f9, f2);
		// GL11.glColor3f(f9, f9, f2);
		// tessellator.setColorOpaque_F(f9, f9, f2);
		tessellator.setColorOpaque_F(1, 1, 1);
		boolean left = block.blockID == blockAccess.getBlockId(i - 1, j, k);
		boolean right = block.blockID == blockAccess.getBlockId(i + 1, j, k);
		boolean down = block.blockID == blockAccess.getBlockId(i, j, k - 1);
		boolean up = block.blockID == blockAccess.getBlockId(i, j, k + 1);

		if (l > 6) {
			l = 6;
		}

		int yo = l;
		int xo = 0;
		int turn = 0;

		if (left || up || right || down) {
			xo = 1;

			if ((left && up) || (up && right) || (right && down)
					|| (down && left)) {
				xo = 2;

				if (left && right && up && down) {
					xo = 5;
				} else if ((left && up && right) || (up && right && down)
						|| (right && down && left) || (down && left && up)) {
					xo = 3;

					if (!right) {
						turn = 1;
					} else if (!left) {
						turn = 3;
					} else if (!down) {
						turn = 2;
					}
				} else {
					if (up && left) {
						turn = 1;
					} else if (up && right) {
						turn = 2;
					} else if (down && right) {
						turn = 3;
					}
				}
			} else if ((left && right) || (up && down)) {
				xo = 4;

				if (up || down) {
					turn = 1;
				}
			} else {
				if (up) {
					turn = 1;
				} else if (right) {
					turn = 2;
				} else if (down) {
					turn = 3;
				}
			}
		}

		double d = (float) xo / 8f;
		double d2 = ((float) xo + 0.99F) / 8f;
		double d4 = (float) yo / 8f;
		double d6 = ((float) yo + 0.99F) / 8f;
		float f5 = i + 0;
		float f6 = i + 1;
		float f7 = k + 0;
		float f8 = k + 1;
		byte byte0 = 0;

		// + 0.015625D is the height off the ground

		double hu = 0.015625D;
		double hj = 0.012D;
		double hi = 0.03125D;
		if (turn == 1) {
			tessellator.addVertexWithUV(f6, (double) j + hu, f8, d, d6);
			tessellator.addVertexWithUV(f6, (double) j + hu, f7, d2, d6);
			tessellator.addVertexWithUV(f5, (double) j + hu, f7, d2, d4);
			tessellator.addVertexWithUV(f5, (double) j + hu, f8, d, d4);

			tessellator.setColorOpaque_F(0, 0, 0);
			tessellator.addVertexWithUV(f6 + hi, (double) j + hj, f8, d, d6);
			tessellator.addVertexWithUV(f6 + hi, (double) j + hj, f7, d2, d6);
			tessellator.addVertexWithUV(f5 + hi, (double) j + hj, f7, d2, d4);
			tessellator.addVertexWithUV(f5 + hi, (double) j + hj, f8, d, d4);
		} else if (turn == 2) {
			tessellator.addVertexWithUV(f6, (double) j + hu, f8, d, d4);
			tessellator.addVertexWithUV(f6, (double) j + hu, f7, d, d6);
			tessellator.addVertexWithUV(f5, (double) j + hu, f7, d2, d6);
			tessellator.addVertexWithUV(f5, (double) j + hu, f8, d2, d4);

			tessellator.setColorOpaque_F(0, 0, 0);
			tessellator.addVertexWithUV(f6 + hi, (double) j + hj, f8, d, d4);
			tessellator.addVertexWithUV(f6 + hi, (double) j + hj, f7, d, d6);
			tessellator.addVertexWithUV(f5 + hi, (double) j + hj, f7, d2, d6);
			tessellator.addVertexWithUV(f5 + hi, (double) j + hj, f8, d2, d4);

		} else if (turn == 3) {
			tessellator.addVertexWithUV(f6, (double) j + hu, f8, d2, d4);
			tessellator.addVertexWithUV(f6, (double) j + hu, f7, d, d4);
			tessellator.addVertexWithUV(f5, (double) j + hu, f7, d, d6);
			tessellator.addVertexWithUV(f5, (double) j + hu, f8, d2, d6);

			tessellator.setColorOpaque_F(0, 0, 0);
			tessellator.addVertexWithUV(f6 + hi, (double) j + hj, f8, d2, d4);
			tessellator.addVertexWithUV(f6 + hi, (double) j + hj, f7, d, d4);
			tessellator.addVertexWithUV(f5 + hi, (double) j + hj, f7, d, d6);
			tessellator.addVertexWithUV(f5 + hi, (double) j + hj, f8, d2, d6);
		} else {
			tessellator.addVertexWithUV(f6, (double) j + hu, f8, d2, d6);
			tessellator.addVertexWithUV(f6, (double) j + hu, f7, d2, d4);
			tessellator.addVertexWithUV(f5, (double) j + hu, f7, d, d4);
			tessellator.addVertexWithUV(f5, (double) j + hu, f8, d, d6);

			tessellator.setColorOpaque_F(0, 0, 0);
			tessellator.addVertexWithUV(f6 + hi, (double) j + hj, f8, d2, d6);
			tessellator.addVertexWithUV(f6 + hi, (double) j + hj, f7, d2, d4);
			tessellator.addVertexWithUV(f5 + hi, (double) j + hj, f7, d, d4);
			tessellator.addVertexWithUV(f5 + hi, (double) j + hj, f8, d, d6);
		}

		/*
		 *
		 * tessellator.setColorOpaque_F(f, f, f);
		 * tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f8, d2, d6 +
		 * 0.0625D); tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f7,
		 * d2, d4 + 0.0625D); tessellator.addVertexWithUV(f5, (double)j +
		 * 0.015625D, f7, d, d4 + 0.0625D); tessellator.addVertexWithUV(f5,
		 * (double)j + 0.015625D, f8, d, d6 + 0.0625D);
		 */
		/*
		 * } else if(byte0 == 1) { tessellator.addVertexWithUV(f6, (double)j +
		 * 0.015625D, f8, d2, d6); tessellator.addVertexWithUV(f6, (double)j +
		 * 0.015625D, f7, d2, d4); tessellator.addVertexWithUV(f5, (double)j +
		 * 0.015625D, f7, d, d4); tessellator.addVertexWithUV(f5, (double)j +
		 * 0.015625D, f8, d, d6); tessellator.setColorOpaque_F(f, f, f);
		 * tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f8, d2, d6 +
		 * 0.0625D); tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f7,
		 * d2, d4 + 0.0625D); tessellator.addVertexWithUV(f5, (double)j +
		 * 0.015625D, f7, d, d4 + 0.0625D); tessellator.addVertexWithUV(f5,
		 * (double)j + 0.015625D, f8, d, d6 + 0.0625D); } else if(byte0 == 2) {
		 * tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f8, d2, d6);
		 * tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f7, d, d6);
		 * tessellator.addVertexWithUV(f5, (double)j + 0.015625D, f7, d, d4);
		 * tessellator.addVertexWithUV(f5, (double)j + 0.015625D, f8, d2, d4);
		 * tessellator.setColorOpaque_F(f, f, f);
		 * tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f8, d2, d6 +
		 * 0.0625D); tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f7,
		 * d, d6 + 0.0625D); tessellator.addVertexWithUV(f5, (double)j +
		 * 0.015625D, f7, d, d4 + 0.0625D); tessellator.addVertexWithUV(f5,
		 * (double)j + 0.015625D, f8, d2, d4 + 0.0625D); }
		 *
		 * /*
		 *
		 * if(!blockAccess.isBlockNormalCube(i, j + 1, k)) { double d1 =
		 * (float)(xo + 16) / 64f; double d3 = ((float)(xo + 16) + 15.99F) /
		 * 64f; double d5 = (float)yo / 64f; double d7 = ((float)yo + 15.99F) /
		 * 64f; if(blockAccess.isBlockNormalCube(i - 1, j, k) &&
		 * blockAccess.getBlockId(i - 1, j + 1, k) == zei_Ids.chalk) {
		 * tessellator.setColorOpaque_F(f * f2, f * f2,f * f2);
		 * tessellator.addVertexWithUV((double)i + 0.015625D, (float)(j + 1) +
		 * 0.021875F, k + 1, d3, d5); tessellator.addVertexWithUV((double)i +
		 * 0.015625D, j + 0, k + 1, d1, d5);
		 * tessellator.addVertexWithUV((double)i + 0.015625D, j + 0, k + 0, d1,
		 * d7); tessellator.addVertexWithUV((double)i + 0.015625D, (float)(j +
		 * 1) + 0.021875F, k + 0, d3, d7); tessellator.setColorOpaque_F(f, f,
		 * f); tessellator.addVertexWithUV((double)i + 0.015625D, (float)(j + 1)
		 * + 0.021875F, k + 1, d3, d5 + 0.0625D);
		 * tessellator.addVertexWithUV((double)i + 0.015625D, j + 0, k + 1, d1,
		 * d5 + 0.0625D); tessellator.addVertexWithUV((double)i + 0.015625D, j +
		 * 0, k + 0, d1, d7 + 0.0625D); tessellator.addVertexWithUV((double)i +
		 * 0.015625D, (float)(j + 1) + 0.021875F, k + 0, d3, d7 + 0.0625D); }
		 * if(blockAccess.isBlockNormalCube(i + 1, j, k) &&
		 * blockAccess.getBlockId(i + 1, j + 1, k) == zei_Ids.chalk) {
		 * tessellator.setColorOpaque_F(f * f2, f * f2, f * f2);
		 * tessellator.addVertexWithUV((double)(i + 1) - 0.015625D, j + 0, k +
		 * 1, d1, d7); tessellator.addVertexWithUV((double)(i + 1) - 0.015625D,
		 * (float)(j + 1) + 0.021875F, k + 1, d3, d7);
		 * tessellator.addVertexWithUV((double)(i + 1) - 0.015625D, (float)(j +
		 * 1) + 0.021875F, k + 0, d3, d5);
		 * tessellator.addVertexWithUV((double)(i + 1) - 0.015625D, j + 0, k +
		 * 0, d1, d5); tessellator.setColorOpaque_F(f, f, f);
		 * tessellator.addVertexWithUV((double)(i + 1) - 0.015625D, j + 0, k +
		 * 1, d1, d7 + 0.0625D); tessellator.addVertexWithUV((double)(i + 1) -
		 * 0.015625D, (float)(j + 1) + 0.021875F, k + 1, d3, d7 + 0.0625D);
		 * tessellator.addVertexWithUV((double)(i + 1) - 0.015625D, (float)(j +
		 * 1) + 0.021875F, k + 0, d3, d5 + 0.0625D);
		 * tessellator.addVertexWithUV((double)(i + 1) - 0.015625D, j + 0, k +
		 * 0, d1, d5 + 0.0625D); } if(blockAccess.isBlockNormalCube(i, j, k - 1)
		 * && blockAccess.getBlockId(i, j + 1, k - 1) == zei_Ids.chalk) {
		 * tessellator.setColorOpaque_F(f * f2, f * f2, f * f2);
		 * tessellator.addVertexWithUV(i + 1, j + 0, (double)k + 0.015625D, d1,
		 * d7); tessellator.addVertexWithUV(i + 1, (float)(j + 1) + 0.021875F,
		 * (double)k + 0.015625D, d3, d7); tessellator.addVertexWithUV(i + 0,
		 * (float)(j + 1) + 0.021875F, (double)k + 0.015625D, d3, d5);
		 * tessellator.addVertexWithUV(i + 0, j + 0, (double)k + 0.015625D, d1,
		 * d5); tessellator.setColorOpaque_F(f, f, f);
		 * tessellator.addVertexWithUV(i + 1, j + 0, (double)k + 0.015625D, d1,
		 * d7 + 0.0625D); tessellator.addVertexWithUV(i + 1, (float)(j + 1) +
		 * 0.021875F, (double)k + 0.015625D, d3, d7 + 0.0625D);
		 * tessellator.addVertexWithUV(i + 0, (float)(j + 1) + 0.021875F,
		 * (double)k + 0.015625D, d3, d5 + 0.0625D);
		 * tessellator.addVertexWithUV(i + 0, j + 0, (double)k + 0.015625D, d1,
		 * d5 + 0.0625D); } if(blockAccess.isBlockNormalCube(i, j, k + 1) &&
		 * blockAccess.getBlockId(i, j + 1, k + 1) == zei_Ids.chalk) {
		 * tessellator.setColorOpaque_F(f * f2, f * f2, f * f2);
		 * tessellator.addVertexWithUV(i + 1, (float)(j + 1) + 0.021875F,
		 * (double)(k + 1) - 0.015625D, d3, d5); tessellator.addVertexWithUV(i +
		 * 1, j + 0, (double)(k + 1) - 0.015625D, d1, d5);
		 * tessellator.addVertexWithUV(i + 0, j + 0, (double)(k + 1) -
		 * 0.015625D, d1, d7); tessellator.addVertexWithUV(i + 0, (float)(j + 1)
		 * + 0.021875F, (double)(k + 1) - 0.015625D, d3, d7);
		 * tessellator.setColorOpaque_F(f, f, f); tessellator.addVertexWithUV(i
		 * + 1, (float)(j + 1) + 0.021875F, (double)(k + 1) - 0.015625D, d3, d5
		 * + 0.0625D); tessellator.addVertexWithUV(i + 1, j + 0, (double)(k + 1)
		 * - 0.015625D, d1, d5 + 0.0625D); tessellator.addVertexWithUV(i + 0, j
		 * + 0, (double)(k + 1) - 0.015625D, d1, d7 + 0.0625D);
		 * tessellator.addVertexWithUV(i + 0, (float)(j + 1) + 0.021875F,
		 * (double)(k + 1) - 0.015625D, d3, d7 + 0.0625D); } }
		 */
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return BlockChalk.rendererId;
	}

}
