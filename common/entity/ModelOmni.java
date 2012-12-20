package riseautomatons.common.entity;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelOmni extends ModelBase {

	public ModelRenderer doink;
	public ModelRenderer din;
	public ModelRenderer doink2;
	public ModelRenderer dink1;
	public ModelRenderer dink2;

	public ModelOmni() {
		doink = new ModelRenderer(this, 32, 12);
		doink.addBox(-4F, 0F, -4F, 8, 12, 8, 0F);
		doink.setRotationPoint(0F, 12F, 0F);
		doink.rotateAngleX = 0F;
		doink.rotateAngleY = 0F;
		doink.rotateAngleZ = 0F;
		doink.mirror = false;
		din = new ModelRenderer(this, 8, 12);
		din.addBox(-3F, 0F, -3F, 6, 15, 6, 0F);
		din.setRotationPoint(0F, -2F, 0F);
		din.rotateAngleX = 0F;
		din.rotateAngleY = 0F;
		din.rotateAngleZ = 0F;
		din.mirror = false;
		doink2 = new ModelRenderer(this, 32, 4);
		doink2.addBox(-4F, 0F, -4F, 8, 8, 8, 0F);
		doink2.setRotationPoint(0F, 0F, 0F);
		doink2.rotateAngleX = 0F;
		doink2.rotateAngleY = 0F;
		doink2.rotateAngleZ = 0F;
		doink2.mirror = false;
		dink1 = new ModelRenderer(this, 0, 0);
		dink1.addBox(-6F, 0F, -3F, 12, 6, 6, 0F);
		dink1.setRotationPoint(0F, 1F, 0F);
		dink1.rotateAngleX = 0F;
		dink1.rotateAngleY = 0F;
		dink1.rotateAngleZ = 0F;
		dink1.mirror = false;
		dink2 = new ModelRenderer(this, 0, 0);
		dink2.addBox(-6F, 0F, -3F, 12, 6, 6, 0F);
		dink2.setRotationPoint(0F, 1F, 0F);
		dink2.rotateAngleX = 0F;
		dink2.rotateAngleY = ((float) Math.PI / 2F);
		dink2.rotateAngleZ = 0F;
		dink2.mirror = false;
	}
	@Override
	public void render(Entity entity, float f, float f1, float f2,
			float f3, float f4, float f5) {

		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		doink.render(f5);
		din.render(f5);
		doink2.render(f5);
		dink1.render(f5);
		dink2.render(f5);
	}
}
