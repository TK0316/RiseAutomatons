package riseautomatons.entity;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

public class ModelTote extends ModelQuadruped {

	ModelRenderer flap;

	public ModelTote() {
		super(6, 1);
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3F, -2F, -3F, 6, 4, 3);
		head.setRotationPoint(0F, 16F, -5F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		flap = new ModelRenderer(this, 20, 19);
		flap.addBox(-5F, -10F, 0F, 10, 10, 1);
		flap.setRotationPoint(0F, 14F, 5F);
		flap.setTextureSize(64, 32);
		flap.mirror = true;
		setRotation(flap, 1.570796F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 16);
		leg1.addBox(-1F, 0F, -1F, 2, 4, 2);
		leg1.setRotationPoint(-3F, 20F, 5F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 16);
		leg2.addBox(-1F, 0F, -1F, 2, 4, 2);
		leg2.setRotationPoint(3F, 20F, 5F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 16);
		leg3.addBox(-1F, 0F, -1F, 2, 4, 2);
		leg3.setRotationPoint(-3F, 20F, -5F);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 16);
		leg4.addBox(-1F, 0F, -1F, 2, 4, 2);
		leg4.setRotationPoint(3F, 20F, -5F);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		body = new ModelRenderer(this, 20, 0);
		body.addBox(-5F, -10F, -7F, 10, 10, 6);
		body.setRotationPoint(0F, 13F, 5F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 1.570796F, 0F, 0F);
	}
	@Override
	public void render(Entity entity, float f, float f1, float f2,
			float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		flap.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2,
			float f3, float f4, float f5, Entity par7Entity) {
		head.rotateAngleX = (f4) / (180F / (float) Math.PI);
		head.rotateAngleY = (0.5f * f3) / (180F / (float) Math.PI);
		body.rotateAngleX = ((float) Math.PI / 2F);
		float ff = f * 4f;
		leg1.rotateAngleX = MathHelper.cos(ff) * 1.4F * f1;
		leg2.rotateAngleX = MathHelper.cos(ff + (float) Math.PI) * 2F * f1;
		leg3.rotateAngleX = MathHelper.cos(ff + (float) Math.PI) * 2F * f1;
		leg4.rotateAngleX = MathHelper.cos(ff) * 1.4F * f1;
	}

	@Override
	public void setLivingAnimations(EntityLiving par1EntityLiving, float par2,
			float par3, float par4) {
		EntityTote tote = (EntityTote) par1EntityLiving;
		tote.angle += tote.dir;
		if (tote.angle > 90) {
			tote.angle = 90;
		} else if (tote.angle < 20) {
			tote.angle = 20;
		}
		flap.rotateAngleX = (float) (Math.PI * tote.angle / 180.0);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
