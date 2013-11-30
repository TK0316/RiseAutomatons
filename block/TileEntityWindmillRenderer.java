package riseautomatons.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntityWindmillRenderer extends TileEntitySpecialRenderer {

	public static final ResourceLocation TEXTURE = new ResourceLocation("riseautomatons", "textures/windmill.png");

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2,
			double par4, double par6, float par8) {
		renderTurnAt((TileEntityWindmill) par1TileEntity, par2, par4, par6,
				par8);
	}

	public void renderTurnAt(TileEntityWindmill enti, double x, double y,
			double z, float par8) {
		int i;

		if (enti.worldObj == null) {
			i = 0;
		} else {
			Block block = enti.getBlockType();
			i = enti.getBlockMetadata();
		}

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
		GL11.glScalef(1.0F, -1F, -1F);

		/*
		 * bindTextureByName("/terrain.png"); GL11.glBegin(GL11.GL_QUADS);
		 *
		 * GL11.glTexCoord2f(1, 0); GL11.glVertex3f(1, 0, 1);
		 *
		 * GL11.glTexCoord2f(0, 0); GL11.glVertex3f(0, 0, 1);
		 *
		 * GL11.glTexCoord2f(0, 1); GL11.glVertex3f(0, 0, 0);
		 *
		 * GL11.glTexCoord2f(1, 1); GL11.glVertex3f(1, 0, 0);
		 *
		 * GL11.glEnd();
		 */

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		this.bindTexture(TEXTURE);

		int j = 45;
		int u = 45;
		int du = i;

		if (i < 2) {
			j = 90;
			// u = 0;
		} else if (i < 4) {
			j = 180;
			// u = 0;
			du -= 2;
		} else if (i < 6) {
			j = 270;
			// u = 0;
			du -= 4;
		} else {
			j = 0;
			// u = 90;
			du -= 6;
		}

		float dir = 0.5f;

		if (du == 1) {
			dir = -0.5f;
		}

		// GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
		// GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		// modelSign.renderSign();
		// GL11.glRotatef(u, 0, 0, 1);
		GL11.glRotatef(j, 0, 1, 0);
		GL11.glRotatef(enti.ang, 1, 0, 0);
		GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
		enti.ang += dir;

		if (enti.ang > 360) {
			enti.ang = 0;
		} else if (enti.ang < 0) {
			enti.ang = 360;
		}

		// ends
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glNormal3f(-1, 0, 0);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-0.1f, -1f, -1f);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(-0.1f, -1f, 2f);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(-0.1f, 2f, 2f);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(-0.1f, 2f, -1f);

		/*
		 * GL11.glNormal3f(1, 0, 0); GL11.glTexCoord2f(0.25f + ee, ss);
		 * GL11.glVertex3f(1, 0.375f, 0.625f); GL11.glTexCoord2f(0.25f + ss,
		 * ss); GL11.glVertex3f(1, 0.375f, 0.375f); GL11.glTexCoord2f(0.25f +
		 * ss, ee); GL11.glVertex3f(1, 0.625f, 0.375f); GL11.glTexCoord2f(0.25f
		 * + ee, ee); GL11.glVertex3f(1, 0.625f, 0.625f);
		 */
		GL11.glEnd();
		GL11.glEnable(GL11.GL_CULL_FACE);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

}
