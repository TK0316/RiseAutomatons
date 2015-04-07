package riseautomatons.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCrink extends ItemBlock {

    public ItemCrink(Block par1)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        return Blocks.crink.getIcon(2, par1);
    }

    @Override
	public int getMetadata(int par1)
    {
        return par1;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
    {
        return par1ItemStack.getItemDamage();
    }

    @Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName();
    }
}
