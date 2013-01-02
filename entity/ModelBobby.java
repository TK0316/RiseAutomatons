package riseautomatons.entity;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBobby extends ModelQuadruped {

	public ModelRenderer bod2;

	public ModelBobby() {
		super(6, 0.0f);
		head = new ModelRenderer(this, 0, 11);
		head.addBox(-3F, -2F, -4F, 6, 4, 4, 0F);
		head.setRotationPoint(0F, 12F, -6F);
		head.rotateAngleX = 0F;
		head.rotateAngleY = 0F;
		head.rotateAngleZ = 0F;
		head.mirror = false;
		body = new ModelRenderer(this, 21, 4);
		body.addBox(-4F, 0F, 0F, 8, 6, 8, 0F);
		body.setRotationPoint(0F, 8F, -6F);
		body.rotateAngleX = 0F;
		body.rotateAngleY = 0F;
		body.rotateAngleZ = 0F;
		body.mirror = false;
		bod2 = new ModelRenderer(this, 4, 0);
		bod2.addBox(-3F, 0F, 0F, 6, 4, 6, 0F);
		bod2.setRotationPoint(0F, 10F, 2F);
		bod2.rotateAngleX = 0F;
		bod2.rotateAngleY = 0F;
		bod2.rotateAngleZ = 0F;
		bod2.mirror = false;
		leg1 = new ModelRenderer(this, 0, 0);
		leg1.addBox(0F, 0F, 0F, 1, 10, 1, 0F);
		leg1.setRotationPoint(2F, 14F, 5F);
		leg1.rotateAngleX = 0F;
		leg1.rotateAngleY = 0F;
		leg1.rotateAngleZ = 0F;
		leg1.mirror = false;
		leg2 = new ModelRenderer(this, 0, 0);
		leg2.addBox(-1F, 0F, 0F, 1, 10, 1, 0F);
		leg2.setRotationPoint(-2F, 14F, 5F);
		leg2.rotateAngleX = 0F;
		leg2.rotateAngleY = 0F;
		leg2.rotateAngleZ = 0F;
		leg2.mirror = false;
		leg3 = new ModelRenderer(this, 0, 0);
		leg3.addBox(0F, 0F, 0F, 1, 10, 1, 0F);
		leg3.setRotationPoint(2F, 14F, -2F);
		leg3.rotateAngleX = 0F;
		leg3.rotateAngleY = 0F;
		leg3.rotateAngleZ = 0F;
		leg3.mirror = false;
		leg4 = new ModelRenderer(this, 0, 0);
		leg4.addBox(-1F, 0F, 0F, 1, 10, 1, 0F);
		leg4.setRotationPoint(-2F, 14F, -2F);
		leg4.rotateAngleX = 0F;
		leg4.rotateAngleY = 0F;
		leg4.rotateAngleZ = 0F;
		leg4.mirror = false;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2,
			float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bod2.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2,
			float f3, float f4, float f5, Entity par7Entity) {
		head.rotateAngleX = f4 / (180F / (float) Math.PI);
		head.rotateAngleY = f3 / (180F / (float) Math.PI);
		// body.rotateAngleX = ((float)Math.PI / 2F);
		leg1.rotateAngleX = MathHelper.cos(f * 1.333F) * 1.2F * f1;
		leg2.rotateAngleX = MathHelper.cos(f * 1.333F + (float) Math.PI) * 1.2F
				* f1;
		leg3.rotateAngleX = MathHelper.cos(f * 1.333F + (float) Math.PI) * 1.2F
				* f1;
		leg4.rotateAngleX = MathHelper.cos(f * 1.333F) * 1.2F * f1;
	}

}
