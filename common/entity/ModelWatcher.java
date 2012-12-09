package riseautomatons.common.entity;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelWatcher extends ModelBase {
	public ModelWatcher() {
		top = new ModelRenderer(this, 24, 0);
		top.addBox(-3F, -20F, -3F, 6, 20, 6, 0F);
		top.setRotationPoint(0F, -16F, 0F);
		top.rotateAngleX = 0F;
		top.rotateAngleY = ((float) Math.PI / 4F);
		top.rotateAngleZ = 0F;
		top.mirror = false;
		middle = new ModelRenderer(this, 0, 0);
		middle.addBox(-3F, -20F, -3F, 6, 20, 6, 0F);
		middle.setRotationPoint(0F, 4F, 0F);
		middle.rotateAngleX = 0F;
		middle.rotateAngleY = 0.3490658F;
		middle.rotateAngleZ = 0F;
		middle.mirror = false;
		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-3F, -20F, -3F, 6, 20, 6, 0F);
		bottom.setRotationPoint(0F, 24F, 0F);
		bottom.rotateAngleX = 0F;
		bottom.rotateAngleY = ((float) Math.PI / 4F);
		bottom.rotateAngleZ = 0F;
		bottom.mirror = false;
		leg1 = new ModelRenderer(this, 48, 0);
		leg1.addBox(-2F, -6F, -2F, 4, 28, 4, 0F);
		leg1.setRotationPoint(6F, 2F, -5F);
		leg1.rotateAngleX = 0F;
		leg1.rotateAngleY = 0F;
		leg1.rotateAngleZ = 0F;
		leg1.mirror = false;
		leg2 = new ModelRenderer(this, 48, 0);
		leg2.addBox(-2F, -6F, -2F, 4, 28, 4, 0F);
		leg2.setRotationPoint(6F, 2F, 5F);
		leg2.rotateAngleX = 0F;
		leg2.rotateAngleY = 0F;
		leg2.rotateAngleZ = 0F;
		leg2.mirror = false;
		leg3 = new ModelRenderer(this, 48, 0);
		leg3.addBox(-2F, -6F, -2F, 4, 28, 4, 0F);
		leg3.setRotationPoint(-6F, 2F, -5F);
		leg3.rotateAngleX = 0F;
		leg3.rotateAngleY = 0F;
		leg3.rotateAngleZ = 0F;
		leg3.mirror = false;
		leg4 = new ModelRenderer(this, 48, 0);
		leg4.addBox(-2F, -6F, -2F, 4, 28, 4, 0F);
		leg4.setRotationPoint(-6F, 2F, 5F);
		leg4.rotateAngleX = 0F;
		leg4.rotateAngleY = 0F;
		leg4.rotateAngleZ = 0F;
		leg4.mirror = false;
		leg5 = new ModelRenderer(this, 48, 0);
		leg5.addBox(-2F, -6F, -2F, 4, 28, 4, 0F);
		leg5.setRotationPoint(10F, 2F, 0F);
		leg5.rotateAngleX = 0F;
		leg5.rotateAngleY = 0F;
		leg5.rotateAngleZ = 0F;
		leg5.mirror = false;
		leg6 = new ModelRenderer(this, 48, 0);
		leg6.addBox(-2F, -6F, -2F, 4, 28, 4, 0F);
		leg6.setRotationPoint(-10F, 2F, 0F);
		leg6.rotateAngleX = 0F;
		leg6.rotateAngleY = 0F;
		leg6.rotateAngleZ = 0F;
		leg6.mirror = false;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		top.render(f5);
		middle.render(f5);
		bottom.render(f5);
		leg1.rotationPointY = 2f + MathHelper.cos(f) * 30F * f1;
		leg2.rotationPointY = 2f + MathHelper.cos(f + (float) Math.PI) * 30F
				* f1;
		leg5.rotationPointY = 2f + MathHelper.cos(f + 1.57f) * 30F * f1;
		leg6.rotationPointY = 2f + MathHelper.cos(f + 4.71f) * 30F * f1;
		// leg3.rotateAngleX=leg1.rotateAngleX;
		leg4.rotationPointY = leg1.rotationPointY;
		// leg4.rotateAngleX=leg2.rotateAngleX;
		leg3.rotationPointY = leg2.rotationPointY;
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		leg5.render(f5);
		leg6.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity par7Entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
		// top.rotateAngleZ = f4 / (180F / (float)Math.PI);
		top.rotateAngleY = 0.785F + f3 / (180F / (float) Math.PI);
		middle.rotateAngleY = -f3 / (180F / (float) Math.PI);
		// leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		// leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) *
		// 1.4F * f1;
	}

	// fields
	public ModelRenderer top;
	public ModelRenderer middle;
	public ModelRenderer bottom;
	public ModelRenderer leg1;
	public ModelRenderer leg2;
	public ModelRenderer leg3;
	public ModelRenderer leg4;
	public ModelRenderer leg5;
	public ModelRenderer leg6;
}
