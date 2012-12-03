package riseautomatons.common.block;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.src.Block;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;

public class TileEntityTurnRenderer extends TileEntitySpecialRenderer {
	static float ss = 6f / 256f;
	static float ee = 10f / 256f;

	public TileEntityTurnRenderer() {
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2,
			double par4, double par6, float par8) {
		renderTurnAt((TileEntityTurn) par1TileEntity, par2, par4, par6, par8);

	}

	public void renderTurnAt(TileEntityTurn enti, double x, double y, double z,
			float par8) {
		int i;

		if (enti.worldObj == null) {
			i = 0;
		} else {
			Block block = enti.getBlockType();
			i = enti.getBlockMetadata();
		}

		bindTextureByName("/terrain.png");
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
		GL11.glScalef(1.0F, -1F, -1F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		int j = 45;
		int u = 45;
		int du = i;

		if (i < 3) {
			j = 90;
			u = 0;
		} else if (i < 6) {
			j = 0;
			u = 0;
			du -= 3;
		} else {
			j = 0;
			u = 90;
			du -= 6;
		}

		float dir = 0;

		if (du == 1) {
			dir = -1f;
		} else if (du == 2) {
			dir = 1f;
		}

		// GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
		// GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		// modelSign.renderSign();
		GL11.glRotatef(u, 0, 0, 1);
		GL11.glRotatef(j, 0, 1, 0);
		GL11.glRotatef(enti.ang, 1, 0, 0);
		GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
		enti.ang += dir;

		if (enti.ang > 360) {
			enti.ang = 0;
		} else if (enti.ang < 0) {
			enti.ang = 360;
		}

		GL11.glColor4f(1, 1, 1, 1);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glNormal3f(0, 0, 1);
		GL11.glTexCoord2f(0.25f, ss);
		GL11.glVertex3f(0, 0.375f, 0.625f);
		GL11.glTexCoord2f(0.3125f, ss);
		GL11.glVertex3f(1, 0.375f, 0.625f);
		GL11.glTexCoord2f(0.3125f, ee);
		GL11.glVertex3f(1, 0.625f, 0.625f);
		GL11.glTexCoord2f(0.25f, ee);
		GL11.glVertex3f(0, 0.625f, 0.625f);
		GL11.glNormal3f(0, 0, -1);
		GL11.glTexCoord2f(0.3125f, ss);
		GL11.glVertex3f(1, 0.375f, 0.375f);
		GL11.glTexCoord2f(0.25f, ss);
		GL11.glVertex3f(0, 0.375f, 0.375f);
		GL11.glTexCoord2f(0.25f, ee);
		GL11.glVertex3f(0, 0.625f, 0.375f);
		GL11.glTexCoord2f(0.3125f, ee);
		GL11.glVertex3f(1, 0.625f, 0.375f);
		GL11.glNormal3f(0, -1, 0);
		GL11.glTexCoord2f(0.25f, ss);
		GL11.glVertex3f(0, 0.375f, 0.375f);
		GL11.glTexCoord2f(0.3125f, ss);
		GL11.glVertex3f(1, 0.375f, 0.375f);
		GL11.glTexCoord2f(0.3125f, ee);
		GL11.glVertex3f(1, 0.375f, 0.625f);
		GL11.glTexCoord2f(0.25f, ee);
		GL11.glVertex3f(0, 0.375f, 0.625f);
		GL11.glNormal3f(0, 1, 0);
		GL11.glTexCoord2f(0.3125f, ss);
		GL11.glVertex3f(1, 0.625f, 0.375f);
		GL11.glTexCoord2f(0.25f, ss);
		GL11.glVertex3f(0, 0.625f, 0.375f);
		GL11.glTexCoord2f(0.25f, ee);
		GL11.glVertex3f(0, 0.625f, 0.625f);
		GL11.glTexCoord2f(0.3125f, ee);
		GL11.glVertex3f(1, 0.625f, 0.625f);
		// ends
		GL11.glNormal3f(-1, 0, 0);
		GL11.glTexCoord2f(0.25f + ss, ss);
		GL11.glVertex3f(0, 0.375f, 0.375f);
		GL11.glTexCoord2f(0.25f + ee, ss);
		GL11.glVertex3f(0, 0.375f, 0.625f);
		GL11.glTexCoord2f(0.25f + ee, ee);
		GL11.glVertex3f(0, 0.625f, 0.625f);
		GL11.glTexCoord2f(0.25f + ss, ee);
		GL11.glVertex3f(0, 0.625f, 0.375f);
		GL11.glNormal3f(1, 0, 0);
		GL11.glTexCoord2f(0.25f + ee, ss);
		GL11.glVertex3f(1, 0.375f, 0.625f);
		GL11.glTexCoord2f(0.25f + ss, ss);
		GL11.glVertex3f(1, 0.375f, 0.375f);
		GL11.glTexCoord2f(0.25f + ss, ee);
		GL11.glVertex3f(1, 0.625f, 0.375f);
		GL11.glTexCoord2f(0.25f + ee, ee);
		GL11.glVertex3f(1, 0.625f, 0.625f);
		GL11.glEnd();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

}
