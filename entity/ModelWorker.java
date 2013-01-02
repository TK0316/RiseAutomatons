package riseautomatons.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWorker extends ModelBase {
	public ModelRenderer bod;
	public ModelRenderer head;
	public ModelRenderer leg1;
	public ModelRenderer light;
	public ModelRenderer leg2;

	public ModelWorker() {
		bod = new ModelRenderer(this, 10, 0);
		bod.addBox(-2F, -4F, -2F, 4, 8, 4, 0F);
		bod.setRotationPoint(0F, 14F, 0F);
		bod.rotateAngleX = 0.3839724F;
		bod.rotateAngleY = 0F;
		bod.rotateAngleZ = 0F;
		bod.mirror = false;
		leg1 = new ModelRenderer(this, 33, 21);
		leg1.addBox(0F, 0F, -1F, 3, 8, 3, 0F);
		leg1.setRotationPoint(2F, 16F, 0F);
		leg1.rotateAngleX = 0F;
		leg1.rotateAngleY = 0F;
		leg1.rotateAngleZ = 0F;
		leg1.mirror = false;
		leg2 = new ModelRenderer(this, 33, 21);
		leg2.addBox(-3F, 0F, -1F, 3, 8, 3, 0F);
		leg2.setRotationPoint(-2F, 16F, 0F);
		leg2.rotateAngleX = 0F;
		leg2.rotateAngleY = 0F;
		leg2.rotateAngleZ = 0F;
		leg2.mirror = false;
		head = new ModelRenderer(this, 7, 20);
		head.addBox(-2.5F, -5F, -4F, 5, 5, 7, 0F);
		head.setRotationPoint(0F, 12F, -2F);
		head.rotateAngleX = 0F;
		head.rotateAngleY = 0F;
		head.rotateAngleZ = 0F;
		head.mirror = false;
		light = new ModelRenderer(this, 28, 0);
		light.addBox(-1.5F, -6F, -1.5F, 3, 5, 3, 0F);
		light.setRotationPoint(0F, 12F, -2F);
		light.rotateAngleX = 0F;
		light.rotateAngleY = 0F;
		light.rotateAngleZ = 0F;
		light.mirror = false;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2,
			float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bod.render(f5);
		head.render(f5);
		leg1.render(f5);
		light.render(f5);
		leg2.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2,
			float f3, float f4, float f5, Entity par7Entity) {
		head.rotateAngleX = f4 / (180F / (float) Math.PI);
		head.rotateAngleY = f3 / (180F / (float) Math.PI);
		light.rotateAngleX = head.rotateAngleX;
		light.rotateAngleY = head.rotateAngleY;
		leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI)
				* 1.4F * f1;
		leg1.rotateAngleY = 0.0F;
		leg2.rotateAngleY = 0.0F;
	}

}
