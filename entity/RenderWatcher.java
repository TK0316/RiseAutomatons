package riseautomatons.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderWatcher extends RenderLiving {

	public RenderWatcher(ModelBase modelbase, float f) {
		super(modelbase, f);
	}

	public void renderAutomaton(EntityCreature entityautomaton, double d,
			double d1, double d2, float f, float f1) {
		super.doRenderLiving(entityautomaton, d, d1, d2, f, f1);
	}

	@Override
	public void doRenderLiving(EntityLiving entityliving, double d,
			double d1, double d2, float f, float f1) {
		renderAutomaton((EntityCreature) entityliving, d, d1, d2, f, f1);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2) {
		GL11.glScalef(1.2f, 1.2f, 1.2f);
	}

	@Override
	public void doRender(Entity entity, double d, double d1,
			double d2, float f, float f1) {

		renderAutomaton((EntityCreature) entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return ((IBot)entity).getTexture();
	}

}
