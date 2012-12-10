package riseautomatons.common.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelFactotum extends ModelBase {

	public ModelRenderer bod;
	public ModelRenderer furn1;
	public ModelRenderer eyes;
	public ModelRenderer furn2;
	public ModelRenderer furn3;
	public ModelRenderer furn13;
	public ModelRenderer leg1;
	public ModelRenderer leg2;
	public ModelRenderer leg3;
	public ModelRenderer leg4;
	public ModelRenderer head;
	public ModelRenderer hat;

	public float F1 = 0f;
	public float F2 = 0f;

	public ModelFactotum() {
		bod = new ModelRenderer(this, 18, 16);
		bod.addBox(0F, 0F, -4F, 15, 7, 8, 0F);
		bod.setRotationPoint(0F, 14F, -7F);
		bod.rotateAngleX = 0F;
		bod.rotateAngleY = ((float) Math.PI * 3F / 2F);
		bod.rotateAngleZ = 0F;
		bod.mirror = false;
		furn1 = new ModelRenderer(this, 0, 0);
		furn1.addBox(-4F, -8F, -4F, 8, 8, 8, 0F);
		furn1.setRotationPoint(0F, 15F, 4F);
		furn1.rotateAngleX = -0.1487144F;
		furn1.rotateAngleY = -0.03717861F;
		furn1.rotateAngleZ = 0F;
		furn1.mirror = false;
		eyes = new ModelRenderer(this, 0, 27);
		eyes.addBox(-2F, 1F, -5F, 4, 2, 1, 0F);
		eyes.setRotationPoint(0F, 16F, -7F);
		eyes.rotateAngleX = 0F;
		eyes.rotateAngleY = 0F;
		eyes.rotateAngleZ = 0F;
		eyes.mirror = false;
		furn2 = new ModelRenderer(this, 1, 0);
		furn2.addBox(-3F, -8F, -4F, 6, 8, 8, 0F);
		furn2.setRotationPoint(0F, 18F, -2F);
		furn2.rotateAngleX = 0.1858931F;
		furn2.rotateAngleY = -0.03717861F;
		furn2.rotateAngleZ = 0F;
		furn2.mirror = false;
		furn3 = new ModelRenderer(this, 0, 0);
		furn3.addBox(-4F, -8F, -4F, 8, 8, 8, 0F);
		furn3.setRotationPoint(4F, 19F, 4F);
		furn3.rotateAngleX = 0.3543328F;
		furn3.rotateAngleY = -2.007645F;
		furn3.rotateAngleZ = 0F;
		furn3.mirror = false;
		furn13 = new ModelRenderer(this, 0, 0);
		furn13.addBox(-4F, -8F, -4F, 8, 8, 8, 0F);
		furn13.setRotationPoint(-4F, 19F, 4F);
		furn13.rotateAngleX = 0.2974289F;
		furn13.rotateAngleY = 2.082002F;
		furn13.rotateAngleZ = 0F;
		furn13.mirror = false;
		leg1 = new ModelRenderer(this, 25, 0);
		leg1.addBox(-1F, 0F, -1F, 2, 5, 2, 0F);
		leg1.setRotationPoint(2F, 19F, -4F);
		leg1.rotateAngleX = 0F;
		leg1.rotateAngleY = 0F;
		leg1.rotateAngleZ = 0F;
		leg1.mirror = false;
		leg2 = new ModelRenderer(this, 25, 0);
		leg2.addBox(-1F, 0F, -1F, 2, 5, 2, 0F);
		leg2.setRotationPoint(-2F, 19F, -4F);
		leg2.rotateAngleX = 0F;
		leg2.rotateAngleY = 0F;
		leg2.rotateAngleZ = 0F;
		leg2.mirror = false;
		leg3 = new ModelRenderer(this, 25, 0);
		leg3.addBox(-1F, 0F, -1F, 2, 5, 2, 0F);
		leg3.setRotationPoint(-2F, 19F, 4F);
		leg3.rotateAngleX = 0F;
		leg3.rotateAngleY = 0F;
		leg3.rotateAngleZ = 0F;
		leg3.mirror = false;
		leg4 = new ModelRenderer(this, 25, 0);
		leg4.addBox(-1F, 0F, -1F, 2, 5, 2, 0F);
		leg4.setRotationPoint(2F, 19F, 4F);
		leg4.rotateAngleX = 0F;
		leg4.rotateAngleY = 0F;
		leg4.rotateAngleZ = 0F;
		leg4.mirror = false;
		head = new ModelRenderer(this, 0, 16);
		head.addBox(-3F, -1F, -6F, 6, 5, 6, 0F);
		head.setRotationPoint(0F, 16F, -7F);
		head.rotateAngleX = 0F;
		head.rotateAngleY = 0F;
		head.rotateAngleZ = 0F;
		head.mirror = false;
		hat = new ModelRenderer(this, 32, 0);
		hat.addBox(-4F, -3F, -8F, 8, 3, 8, 0F);
		hat.setRotationPoint(0F, 16F, -7F);
		hat.rotateAngleX = 0.1858931F;
		hat.rotateAngleY = 0F;
		hat.rotateAngleZ = 0F;
		hat.mirror = false;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2,
			float f3, float f4, float f5) {

		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bod.render(f5);
		furn1.render(f5);
		eyes.render(f5);
		furn2.render(f5);
		furn3.render(f5);
		furn13.render(f5);
		GL11.glPushMatrix();
		GL11.glTranslatef(0f, F1, 0f);
		leg1.render(f5);
		leg3.render(f5);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslatef(0f, F2, 0f);
		leg2.render(f5);
		leg4.render(f5);
		GL11.glPopMatrix();
		head.render(f5);
		hat.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2,
			float f3, float f4, float f5, Entity par7Entity) {

		super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
		// leg1.rotateAngleX = -((float)Math.PI * 2F / 9F)+MathHelper.cos(f *
		// 0.6662F) * 1.4F * f1;
		// System.out.println(f);
		F1 = (float) MathHelper.cos(f * 1.333F) * f1 * 0.1f;
		F2 = (float) MathHelper.cos(f * 1.333F + (float) Math.PI) * f1 * 0.1f;
		// System.out.println(F1);
		head.rotateAngleX = -0.25f + (f4 / (180F / (float) Math.PI));
		head.rotateAngleY = f3 / (180F / (float) Math.PI);
		hat.rotateAngleX = head.rotateAngleX + 0.1858931F;
		hat.rotateAngleY = head.rotateAngleY;
		eyes.rotateAngleX = head.rotateAngleX;
		eyes.rotateAngleY = head.rotateAngleY;
	}

}
