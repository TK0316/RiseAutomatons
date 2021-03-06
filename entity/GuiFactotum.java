package riseautomatons.entity;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiFactotum extends GuiContainer {

	public static final ResourceLocation TEXTURE = new ResourceLocation("riseautomatons", "textures/gui/factotumg.png");
	private EntityFactotum furnaceInventory;

	public GuiFactotum(InventoryPlayer inventoryplayer,
			EntityFactotum tileentityfurnace) {
		super(new ContainerFactotum(inventoryplayer, tileentityfurnace));
		furnaceInventory = tileentityfurnace;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(TEXTURE);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

		if (furnaceInventory.isBurning()) {
			int l = furnaceInventory.getBurnTimeRemainingSc(12);
			// System.out.println(l);
			drawTexturedModalRect(j + 26, (k + 36 + 12) - l, 176, 12 - l, 14,
					l + 2);// 26 56
		}

		int i1 = furnaceInventory.getCookProgressSc(24);
		drawTexturedModalRect(j + 55, k + 34, 176, 14, i1 + 1, 16);// 55 79
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRendererObj.drawString("Furnace", 12, 6, 0xD14600);
		fontRendererObj.drawString("Chest", 110, 6, 0x8C7632);
		fontRendererObj.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
	}

}
