package riseautomatons.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBeacon extends ModelBase {

	public ModelRenderer top;
	public ModelRenderer bottom;

	public ModelBeacon() {
		top = new ModelRenderer(this, 0, 0);
		top.addBox(-2.5F, -12F, -2.5F, 5, 12, 5, 0F);
		top.setRotationPoint(0F, 20F, 0F);
		top.rotateAngleX = 0F;
		top.rotateAngleY = 0F;
		top.rotateAngleZ = 0F;
		top.mirror = false;
		bottom = new ModelRenderer(this, 12, 15);
		bottom.addBox(-6.5F, -4F, -6.5F, 13, 4, 13, 0F);
		bottom.setRotationPoint(0F, 24F, 0F);
		bottom.rotateAngleX = 0F;
		bottom.rotateAngleY = 0F;
		bottom.rotateAngleZ = 0F;
		bottom.mirror = false;
	}

	@Override
	public void render(Entity par1Entity, float f, float f1, float f2,
			float f3, float f4, float f5) {
		top.render(f5);
		bottom.render(f5);
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3,
			float par4, float par5, float par6, Entity par7Entity) {
	}

}
