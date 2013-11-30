package riseautomatons.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderTote extends RenderLiving {

	public RenderTote(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	@Override
	public void doRenderLiving(EntityLiving entityliving, double d,
			double d1, double d2, float f, float f1) {
		renderTote((EntityCreature)entityliving, d, d1, d2, f, f1);
	}

	@Override
	public void doRender(Entity entity, double d, double d1,
			double d2, float f, float f1) {
		renderTote((EntityCreature)entity, d, d1, d2, f, f1);
	}

	public void renderTote(EntityCreature e, double d, double d1,
			double d2, float f, float f1) {
		super.doRenderLiving(e, d, d1, d2, f, f1);

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((IBot)entity).getTexture();
	}

}
