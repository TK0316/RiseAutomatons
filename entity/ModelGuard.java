package riseautomatons.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGuard extends ModelBase {

	public ModelRenderer rod;
	public ModelRenderer head;
	public ModelRenderer thing;
	public ModelRenderer stand2;

	public ModelGuard() {
		this.rod = new ModelRenderer(this, 0, 0);
		this.rod.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
		this.rod.setRotationPoint(0.0F, 20.0F, 0.0F);
		this.rod.rotateAngleX = 0.0F;
		this.rod.rotateAngleY = 3.141593F;
		this.rod.rotateAngleZ = 0.0F;
		this.rod.mirror = false;
		this.head = new ModelRenderer(this, 13, 0);
		this.head.addBox(-2.5F, -2.5F, -9.0F, 5, 5, 12, 0.0F);
		this.head.setRotationPoint(0.0F, 18.0F, 0.0F);
		this.head.rotateAngleX = 0.0F;
		this.head.rotateAngleY = 0.0F;
		this.head.rotateAngleZ = 0.0F;
		this.head.mirror = false;
		this.thing = new ModelRenderer(this, 5, 19);
		this.thing.addBox(-1.5F, -1.5F, -2.0F, 3, 3, 7, 0.0F);
		this.thing.setRotationPoint(0.0F, 18.0F, 0.0F);
		this.thing.rotateAngleX = 0.0F;
		this.thing.rotateAngleY = 0.0F;
		this.thing.rotateAngleZ = 0.0F;
		this.thing.mirror = false;
		this.stand2 = new ModelRenderer(this, 26, 19);
		this.stand2.addBox(-4.0F, 0.0F, -4.0F, 8, 2, 8, 0.0F);
		this.stand2.setRotationPoint(0.0F, 22.0F, 0.0F);
		this.stand2.rotateAngleX = 0.0F;
		this.stand2.rotateAngleY = 0.0F;
		this.stand2.rotateAngleZ = 0.0F;
		this.stand2.mirror = false;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2,
			float f3, float f4, float f5) {

		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.rod.render(f5);
		this.head.render(f5);
		this.thing.render(f5);
		this.stand2.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2,
			float f3, float f4, float f5, Entity entity) {

		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.head.rotateAngleX = (f4 / 57.295799F);
		this.head.rotateAngleY = (f3 / 57.29578F);
		this.thing.rotateAngleX = this.head.rotateAngleX;
		this.thing.rotateAngleY = this.head.rotateAngleY;
	}

}
