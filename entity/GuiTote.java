package riseautomatons.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class GuiTote extends GuiContainer {

    public GuiTote(InventoryPlayer inventoryplayer, IInventory e) {
    	super(new ContainerTote(inventoryplayer, e));
        tote = e;
	}

	private IInventory tote;

    @Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture("/riseautomatons/toteg.png");
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Toter", 70, 6, 0x31a8d4);
		fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
	}

}
