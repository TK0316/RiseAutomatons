package riseautomatons.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderBot extends RenderLiving {
    private static final ResourceLocation field_110865_p = new ResourceLocation("tofucraft", "textures/mob/tofunian.png");

	public RenderBot(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	@Override
    //public void doRenderLiving(EntityLiving par1EntityLiving, double par2,
	public void doRender(EntityLiving par1EntityLiving, double par2,
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
        //super.doRenderLiving(entity, d, d1, d2, f, f1);
		super.doRender(entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((IBot)entity).getTexture();
	}
/*
    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        super.doRender(par1EntityLiving, par2, par4, par6, par8, par9);
    }
*/
    @Override
    public void doRender(EntityLivingBase par1Entity, double par2, double par4, double par6, float par8, float par9) {
        super.doRender(par1Entity, par2, par4, par6, par8, par9);
    }
/*
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        super.doRender(par1Entity, par2, par4, par6, par8, par9);
    }
*/
}
