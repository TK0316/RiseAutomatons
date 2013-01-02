package riseautomatons.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHelios extends ModelBase {
	ModelRenderer head;
	ModelRenderer propeller;
	ModelRenderer bar2;
	ModelRenderer core;
	ModelRenderer bar1;

	public ModelHelios() {
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 32, 16);
		head.addBox(-2F, -2F, -8F, 4, 4, 5);
		head.setRotationPoint(0F, 17F, -2F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		propeller = new ModelRenderer(this, 40, 0);
		propeller.addBox(-6F, -6F, 0F, 12, 12, 0);
		propeller.setRotationPoint(0F, 12.95F, 0F);
		propeller.setTextureSize(64, 32);
		propeller.mirror = true;
		setRotation(propeller, 1.570796F, 0F, 0F);
		bar2 = new ModelRenderer(this, 0, 16);
		bar2.addBox(-5F, -3F, -3F, 10, 6, 6);
		bar2.setRotationPoint(0F, 17F, 0F);
		bar2.setTextureSize(64, 32);
		bar2.mirror = true;
		setRotation(bar2, 0F, 0F, 0F);
		core = new ModelRenderer(this, 0, 0);
		core.addBox(-4F, 0F, -4F, 8, 8, 8);
		core.setRotationPoint(0F, 13F, 0F);
		core.setTextureSize(64, 32);
		core.mirror = true;
		setRotation(core, 0F, 0F, 0F);
		bar1 = new ModelRenderer(this, 0, 16);
		bar1.addBox(-5F, -3F, -3F, 10, 6, 6);
		bar1.setRotationPoint(0F, 17F, 0F);
		bar1.setTextureSize(64, 32);
		bar1.mirror = true;
		setRotation(bar1, 0F, 1.570796F, 0F);
	}
	@Override
	public void render(Entity entity, float f, float f1, float f2,
			float f3, float f4, float f5) {

		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		propeller.render(f5);
		bar2.render(f5);
		core.render(f5);
		bar1.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2,
			float f3, float f4, float f5, Entity par7Entity) {
		head.rotateAngleX = f4 / (180F / (float) Math.PI);
		head.rotateAngleY = f3 / (180F / (float) Math.PI);
		propeller.rotateAngleY = f * f2 * 3.14F;
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
