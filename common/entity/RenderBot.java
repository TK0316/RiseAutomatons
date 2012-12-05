package riseautomatons.common.entity;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderLiving;

public class RenderBot extends RenderLiving {

	public RenderBot(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2,
			double par4, double par6, float par8, float par9) {
		renderBot(par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		renderBot((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
	}

	private void renderBot(EntityLiving entity, double d, double d1, double d2,
			float f, float f1) {
		super.doRenderLiving(entity, d, d1, d2, f, f1);
	}

}
