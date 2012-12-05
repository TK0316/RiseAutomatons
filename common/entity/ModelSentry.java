package riseautomatons.common.entity;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelQuadruped;
import net.minecraft.src.ModelRenderer;

public class ModelSentry extends ModelQuadruped {

	ModelRenderer hip;
	ModelRenderer seg1;

	ModelRenderer bar;
	ModelRenderer neck;
	ModelRenderer seg2;
	ModelRenderer seg3;
	ModelRenderer jaw;
	ModelRenderer leg12;
	ModelRenderer tail;

	public ModelSentry() {
		super(0, 0);
		textureWidth = 64;
		textureHeight = 32;
		hip = new ModelRenderer(this, 44, 11);
		hip.addBox(-3F, -3F, 0F, 6, 5, 4);
		hip.setRotationPoint(0F, 12F, 4F);
		hip.setTextureSize(64, 32);
		hip.mirror = true;
		setRotation(hip, -0.5130648F, 0F, 0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3F, -3F, -9F, 6, 3, 10);
		head.setRotationPoint(0F, 10F, -12F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 40, 1);
		body.addBox(-2F, -2F, 0F, 4, 2, 10);
		body.setRotationPoint(0F, 13F, -7F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, -0.0223072F, 0F, 0F);
		seg1 = new ModelRenderer(this, 15, 14);
		seg1.addBox(-4F, -9F, -4F, 8, 10, 5);
		seg1.setRotationPoint(0F, 11F, -7F);
		seg1.setTextureSize(64, 32);
		seg1.mirror = true;
		setRotation(seg1, -0.1549109F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 16);
		leg1.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
		leg1.setRotationPoint(5F, 12F, -9F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		leg2 = new ModelRenderer(this, 0, 16);
		leg2.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
		leg2.setRotationPoint(-5F, 12F, -9F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		leg3 = new ModelRenderer(this, 0, 16);
		leg3.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
		leg3.setRotationPoint(5F, 12F, 6F);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		leg4 = new ModelRenderer(this, 0, 16);
		leg4.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
		leg4.setRotationPoint(-5F, 12F, 6F);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		leg12 = new ModelRenderer(this, 0, 24);
		leg12.addBox(-1F, 0F, -1F, 2, 6, 2);
		leg12.setRotationPoint(1F, 6F, 2F);// leg12.setRotationPoint(6F, 18F,
											// -7F);
		leg12.setTextureSize(64, 32);
		leg12.mirror = true;
		setRotation(leg12, -0.4461433F, 0F, 0F);
		leg1.addChild(leg12);
		leg2.addChild(leg12);
		leg3.addChild(leg12);
		leg4.addChild(leg12);
		setRotation(leg1, 0.4758862F, 0F, 0F);
		bar = new ModelRenderer(this, 40, 0);
		bar.addBox(-1F, -1F, 0F, 2, 2, 10);
		bar.setRotationPoint(1F, 8F, -3F);
		bar.setTextureSize(64, 32);
		bar.mirror = true;
		setRotation(bar, -0.3346075F, 0F, 0F);
		neck = new ModelRenderer(this, 40, 1);
		neck.addBox(-2F, -1F, 0F, 4, 2, 9);
		neck.setRotationPoint(0F, 11F, -12F);
		neck.setTextureSize(64, 32);
		neck.mirror = true;
		setRotation(neck, 0.3271718F, 0F, 0F);
		seg2 = new ModelRenderer(this, 14, 14);
		seg2.addBox(-5F, -9F, -4F, 10, 10, 5);
		seg2.setRotationPoint(0F, 10F, -1F);
		seg2.setTextureSize(64, 32);
		seg2.mirror = true;
		setRotation(seg2, -0.4523398F, 0F, 0F);
		seg3 = new ModelRenderer(this, 17, 18);
		seg3.addBox(-4F, -7F, -3F, 8, 8, 3);
		seg3.setRotationPoint(0F, 11F, 3F);
		seg3.setTextureSize(64, 32);
		seg3.mirror = true;
		setRotation(seg3, -0.928226F, 0F, 0F);
		jaw = new ModelRenderer(this, 38, 22);
		jaw.addBox(-3F, 0F, -7F, 6, 3, 7);
		jaw.setRotationPoint(0F, 0F, -2F);// jaw.setRotationPoint(0F, 10F,
											// -14F);
		jaw.setTextureSize(64, 32);
		jaw.mirror = true;
		setRotation(jaw, 0F, 0F, 0F);
		head.addChild(jaw);
		tail = new ModelRenderer(this, 40, 0);
		tail.addBox(-1F, -1F, -3F, 2, 2, 10);
		tail.setRotationPoint(0F, 11F, 8F);
		tail.setTextureSize(64, 32);
		tail.mirror = true;
		setRotation(tail, -0.6915222F, 0F, 0F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		hip.render(f5);
		// head.render(f5);
		// body.render(f5);
		seg1.render(f5);
		// leg1.render(f5);
		// leg12.render(f5);
		bar.render(f5);
		neck.render(f5);
		seg2.render(f5);
		seg3.render(f5);
		// jaw.render(f5);
		tail.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2,
			float f3, float f4, float f5, Entity par7Entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
		jaw.rotateAngleX = -0.26f
				* MathHelper.cos(f2 * (0.09F + 0.01f * f * f1));
	}

}
