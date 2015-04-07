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

    protected void renderEquippedItems(EntityLiving entityliving, float f)
 {
		if (entityliving instanceof EntityWorker) {
			EntityWorker ea = ((EntityWorker) entityliving);
			Item ii = ea.getItemID();

			if (ii != null) {
				ItemStack itemstack = new ItemStack(ii, 1,
						ea.getItemDamage());
				// if(itemstack.itemID!=0){
				GL11.glPushMatrix();
				model.light.postRender(0.0625F);
				GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

                Block block = Block.getBlockFromItem(itemstack.getItem());
				if (block != null
						&& RenderBlocks
								.renderItemIn3d(block.getRenderType())) {
					float f1 = 0.3F;
					GL11.glTranslatef(0.0875F, -0.9F, -0.1F);// GL11.glTranslatef(0.0F,
																// -0.1875F,
																// -0.3125F);
					// f1 *= 0.75F;
					// GL11.glRotatef(20F, 1.0F, 0.0F, 0.0F);
					GL11.glScalef(f1, -f1, f1);
				} else if (itemstack.getItem().isFull3D()) {
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
