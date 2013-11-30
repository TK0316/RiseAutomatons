package riseautomatons.entity;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderWorker extends RenderLiving {
	protected ModelWorker model;

	public RenderWorker(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		model = (ModelWorker)par1ModelBase;
	}

	@Override
	public void doRenderLiving(EntityLiving entityliving, double d,
			double d1, double d2, float f, float f1) {
		renderAutomaton((EntityCreature)entityliving, d, d1, d2, f, f1);
	}

	@Override
	public void doRender(Entity entity, double d, double d1,
			double d2, float f, float f1) {
		renderAutomaton((EntityCreature)entity, d, d1, d2, f, f1);
	}

    public void renderAutomaton(EntityCreature entityautomaton, double d, double d1, double d2,
            float f, float f1)
    {
        super.doRenderLiving(entityautomaton, d, d1, d2, f, f1);
    }

    protected void renderEquippedItems(EntityLiving entityliving, float f)
 {
		if (entityliving instanceof EntityWorker) {
			EntityWorker ea = ((EntityWorker) entityliving);
			int ii = ea.getItemID();

			if (ii > 0) {
				ItemStack itemstack = new ItemStack(ii, 1,
						ea.getItemDamage());
				// if(itemstack.itemID!=0){
				GL11.glPushMatrix();
				model.light.postRender(0.0625F);
				GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

				if (itemstack.itemID < 256
						&& RenderBlocks
								.renderItemIn3d(Block.blocksList[itemstack.itemID]
										.getRenderType())) {
					float f1 = 0.3F;
					GL11.glTranslatef(0.0875F, -0.9F, -0.1F);// GL11.glTranslatef(0.0F,
																// -0.1875F,
																// -0.3125F);
					// f1 *= 0.75F;
					// GL11.glRotatef(20F, 1.0F, 0.0F, 0.0F);
					GL11.glScalef(f1, -f1, f1);
				} else if (Item.itemsList[itemstack.itemID].isFull3D()) {
					float f2 = 0.3125F;
					GL11.glTranslatef(0.1F, -0.8F, 0.4F);
					GL11.glScalef(f2, -f2, f2);
					GL11.glRotatef(-80F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
				} else {
					/*
					 * float f3 = 0.375F; GL11.glTranslatef(0.25F, -0.1875F,
					 * -0.1875F); GL11.glScalef(f3, f3, f3);
					 */
					float f2 = 0.3125F;
					GL11.glTranslatef(0.1F, -0.8F, 0.4F);
					GL11.glScalef(f2, -f2, f2);
					GL11.glRotatef(-80F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(45F, 0.0F, 1.0F, 0.0F);
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
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((IBot)entity).getTexture();
	}

}
