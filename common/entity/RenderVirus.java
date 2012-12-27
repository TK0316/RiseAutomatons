package riseautomatons.common.entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import riseautomatons.common.item.Items;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityPotion;
import net.minecraft.src.PotionHelper;
import net.minecraft.src.Render;
import net.minecraft.src.Tessellator;

public class RenderVirus extends Render {

	public RenderVirus() {
	}

	@Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        this.loadTexture("/riseautomatons/items.png");
        Tessellator var10 = Tessellator.instance;

        if(par1Entity instanceof EntityVirus) {

	        this.func_77026_a(var10, Items.virus.getIconFromDamage(1));
	        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	        GL11.glPopMatrix();
        }
    }

    private void func_77026_a(Tessellator par1Tessellator, int par2)
    {
        float var3 = (float)(par2 % 16 * 16 + 0) / 256.0F;
        float var4 = (float)(par2 % 16 * 16 + 16) / 256.0F;
        float var5 = (float)(par2 / 16 * 16 + 0) / 256.0F;
        float var6 = (float)(par2 / 16 * 16 + 16) / 256.0F;
        float var7 = 1.0F;
        float var8 = 0.5F;
        float var9 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
        par1Tessellator.addVertexWithUV((double)(0.0F - var8), (double)(0.0F - var9), 0.0D, (double)var3, (double)var6);
        par1Tessellator.addVertexWithUV((double)(var7 - var8), (double)(0.0F - var9), 0.0D, (double)var4, (double)var6);
        par1Tessellator.addVertexWithUV((double)(var7 - var8), (double)(var7 - var9), 0.0D, (double)var4, (double)var5);
        par1Tessellator.addVertexWithUV((double)(0.0F - var8), (double)(var7 - var9), 0.0D, (double)var3, (double)var5);
        par1Tessellator.draw();
    }
}
