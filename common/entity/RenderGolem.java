package riseautomatons.common.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityCreature;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModelBase;
import net.minecraft.src.Render;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.RenderLiving;

public class RenderGolem extends RenderLiving {

	public RenderGolem(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2,
			double par4, double par6, float par8, float par9) {
		renderAutomaton((EntityCreature)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	protected void renderEquippedItems(EntityLiving entityliving, float par2) {

		if (entityliving instanceof EntityGolem) {
			EntityGolem ea = ((EntityGolem) entityliving);
			int ii = ea.getType();
			int dam = ea.getColo();

			if (ii > 0) // &&ii!=2
			{
				ItemStack itemstack = new ItemStack(ii, 1, dam);
				// if(itemstack.itemID!=0){
				GL11.glPushMatrix();

				// model.light.postRender(0.0625F);
				// GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
				if (itemstack.itemID < 256
						&& RenderBlocks
								.renderItemIn3d(Block.blocksList[itemstack.itemID]
										.getRenderType())) {
					// float f1 = 0.3F;
					GL11.glTranslatef(0F, 1.0F, 0F);
					// GL11.glTranslatef(0.0875F, -0.9F,
					// -0.1F);//GL11.glTranslatef(0.0F, -0.1875F, -0.3125F);
					// f1 *= 0.75F;
					GL11.glRotatef(180F, 1.0F, 0.0F, 0.0F);
					// GL11.glScalef(f1, -f1, f1);
				} else if (Item.itemsList[itemstack.itemID].isFull3D()) {
					float f2 = 0.3125F;
					GL11.glTranslatef(0.1F, -0.8F, 0.4F);
					GL11.glScalef(f2, -f2, f2);
					GL11.glRotatef(-80F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
				} else {
					float f3 = 0.375F;
					GL11.glTranslatef(0.25F, -0.1875F, -0.1875F);
					GL11.glScalef(f3, f3, f3);
					/*
					 * GL11.glRotatef(60F, 0.0F, 0.0F, 1.0F);
					 * GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
					 * GL11.glRotatef(20F, 0.0F, 0.0F, 1.0F);
					 */
				}

				renderManager.itemRenderer.renderItem(entityliving, itemstack,
						0);
				GL11.glPopMatrix();
			}
		}
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		 renderAutomaton((EntityCreature)par1Entity, par2, par4, par6, par8, par9);
	}

	public void renderAutomaton(EntityCreature entityautomaton, double d,
			double d1, double d2, float f, float f1) {
		super.doRenderLiving(entityautomaton, d, d1, d2, f, f1);
	}

}
