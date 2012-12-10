package riseautomatons.common.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderLiving;

public class RenderFactotum extends RenderLiving {

	public RenderFactotum(ModelBase modelbase, float f) {
		super(modelbase, f);
	}

	@Override
	public void doRenderLiving(EntityLiving entityliving, double d,
			double d1, double d2, float f, float f1) {
		renderAutomaton((EntityCreature)entityliving, d, d1, d2, f, f1);
	}

	@Override
	protected void preRenderCallback(EntityLiving par1EntityLiving, float par2) {
		GL11.glScalef(2f, 2f, 2f);
	}

	@Override
	public void doRender(Entity entity, double d, double d1,
			double d2, float f, float f1) {
		renderAutomaton((EntityCreature)entity, d, d1, d2, f, f1);
	}

	public void renderAutomaton(EntityCreature entityautomaton, double d,
			double d1, double d2, float f, float f1) {
		super.doRenderLiving(entityautomaton, d, d1, d2, f, f1);
	}
}
