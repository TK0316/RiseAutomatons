package riseautomatons.common.model;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelEye extends ModelBase {
	public ModelRenderer base;
	public ModelRenderer cornea;
	public ModelRenderer lid2;
	public ModelRenderer Shape3;
	public ModelRenderer ball;
	public ModelRenderer lid1;
	public ModelRenderer bit1;
	public ModelRenderer bit2;
	public ModelRenderer bit3;
	public ModelRenderer bit4;

	public ModelEye() {
		textureWidth = 128;
		textureHeight = 64;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-8F, -4F, -8F, 16, 6, 16);
		base.setRotationPoint(0F, 22F, 0F);
		base.setTextureSize(128, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		cornea = new ModelRenderer(this, 48, 0);
		cornea.addBox(-3F, -3F, -3F, 6, 6, 6);
		cornea.setRotationPoint(0F, 0F, 0F);
		cornea.setTextureSize(128, 64);
		cornea.mirror = true;
		setRotation(cornea, 0F, 0F, 3.141593F);
		lid2 = new ModelRenderer(this, 30, 40);
		lid2.addBox(-4F, -4.5F, 0F, 4, 9, 9);
		lid2.setRotationPoint(0F, 0F, -5F);
		lid2.setTextureSize(128, 64);
		lid2.mirror = true;
		setRotation(lid2, 0F, 1.635859F, -1.571F);// 3.141593F);
		Shape3 = new ModelRenderer(this, 0, 22);
		Shape3.addBox(-7F, -4F, -7F, 14, 4, 14);
		Shape3.setRotationPoint(0F, 18F, 0F);
		Shape3.setTextureSize(128, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		ball = new ModelRenderer(this, 0, 40);
		ball.addBox(-4F, -4F, -6F, 8, 8, 7);
		ball.setRotationPoint(0F, 10F, 1F);
		ball.setTextureSize(128, 64);
		ball.mirror = true;
		setRotation(ball, 0F, 0F, 0F);
		lid1 = new ModelRenderer(this, 30, 40);
		lid1.addBox(-4F, -4.5F, 0F, 4, 9, 9);
		lid1.setRotationPoint(0F, 0F, -5F);
		lid1.setTextureSize(128, 64);
		lid1.mirror = true;
		setRotation(lid1, 0F, -0.5948578F, 1.571F);

		bit1 = new ModelRenderer(this, 72, 0);
		bit1.addBox(-3F, -3F, -3F, 6, 6, 6);
		bit1.setRotationPoint(10F, 15F, 10F);
		bit1.setTextureSize(128, 64);
		bit1.mirror = true;

		bit2 = new ModelRenderer(this, 72, 0);
		bit2.addBox(-3F, -3F, -3F, 6, 6, 6);
		bit2.setRotationPoint(10F, 0F, 10F);
		bit2.setTextureSize(128, 64);
		bit2.mirror = true;

		bit2.rotateAngleX = 0.01f;

		bit3 = new ModelRenderer(this, 72, 0);
		bit3.addBox(-3F, -3F, -3F, 6, 6, 6);
		bit3.setRotationPoint(-10F, 15F, 10F);
		bit3.setTextureSize(128, 64);
		bit3.mirror = true;

		bit4 = new ModelRenderer(this, 72, 0);
		bit4.addBox(-3F, -3F, -3F, 6, 6, 6);
		bit4.setRotationPoint(-10F, 15F, -10F);
		bit4.setTextureSize(128, 64);
		bit4.mirror = true;

		// setRotation(bit1, 0F, 0F, 0F);

		ball.addChild(cornea);
		ball.addChild(lid1);
		ball.addChild(lid2);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	int tem = 0;

	public void renderAll() {
		float f5 = 0.0625F;

		base.render(f5);
		Shape3.render(f5);
		ball.render(f5);

		if (tem == 0) {
			bit1.rotationPointX -= 0.1f;
			bit3.rotationPointZ -= 0.1f;
			bit4.rotationPointX += 0.1f;
			if (bit1.rotationPointX <= -10) {
				tem++;
			}
		} else if (tem == 1) {
			bit1.rotationPointZ -= 0.1f;
			bit3.rotationPointX += 0.1f;
			bit4.rotationPointZ += 0.1f;
			if (bit1.rotationPointZ <= -10) {
				tem++;
			}
		} else if (tem == 2) {
			bit1.rotationPointX += 0.1f;
			bit3.rotationPointZ += 0.1f;
			bit4.rotationPointX -= 0.1f;
			if (bit1.rotationPointX >= 10) {
				tem++;
			}
		} else if (tem == 3) {
			bit1.rotationPointZ += 0.1f;
			bit3.rotationPointX -= 0.1f;
			bit4.rotationPointZ -= 0.1f;
			if (bit1.rotationPointZ >= 10) {
				tem++;
			}
		} else if (tem == 4) {
			if (bit1.rotateAngleX > 0)
				bit1.rotationPointY += 0.1f;
			else
				bit1.rotationPointY -= 0.1f;

			if (bit2.rotateAngleX > 0)
				bit2.rotationPointY += 0.1f;
			else
				bit2.rotationPointY -= 0.1f;

			if (bit3.rotateAngleX > 0)
				bit3.rotationPointY += 0.1f;
			else
				bit3.rotationPointY -= 0.1f;

			if (bit4.rotateAngleX > 0)
				bit4.rotationPointY += 0.1f;
			else
				bit4.rotationPointY -= 0.1f;

			if (bit1.rotationPointY >= 15 || bit1.rotationPointY <= 0) {
				tem = 0;
				if (bit1.rotateAngleX > 0)
					bit1.rotateAngleX = 0;
				else
					bit1.rotateAngleX = 0.01f;

				if (bit2.rotateAngleX > 0)
					bit2.rotateAngleX = 0;
				else
					bit2.rotateAngleX = 0.01f;

				if (bit3.rotateAngleX > 0)
					bit3.rotateAngleX = 0;
				else
					bit3.rotateAngleX = 0.01f;

				if (bit4.rotateAngleX > 0)
					bit4.rotateAngleX = 0;
				else
					bit4.rotateAngleX = 0.01f;
			}
		}
		bit1.render(f5);

		// bit1.rotateAngleX
		bit2.rotationPointX = bit3.rotationPointZ;
		bit2.rotationPointZ = bit3.rotationPointX;
		bit2.render(f5);
		bit3.render(f5);
		bit4.render(f5);
	}
}
