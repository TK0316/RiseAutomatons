package riseautomatons.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderFactotum extends RenderLiving {

	public RenderFactotum(ModelBase modelbase, float f) {
		super(modelbase, f);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2) {
		GL11.glScalef(2f, 2f, 2f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((IBot)entity).getTexture();
	}
}
