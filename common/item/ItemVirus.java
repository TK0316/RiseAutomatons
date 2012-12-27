package riseautomatons.common.item;

import java.util.List;

import riseautomatons.common.entity.EntityVirus;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemVirus extends Item {

	public ItemVirus(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	@Override
	public int getIconFromDamage(int par1) {
		return this.iconIndex + par1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if(par1ItemStack.getItemDamage() == 0) {
			return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
		}
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }

        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2World.isRemote)
        {
        	EntityVirus e = new EntityVirus(par2World, par3EntityPlayer);
        	if(par1ItemStack.getItemDamage() > 0) {
        		e.active = true;
        	}
            par2World.spawnEntityInWorld(e);
        }

        return par1ItemStack;
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
	}

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		if(itemstack.getItemDamage() == 0) {
			return (new StringBuilder()).append(super.getItemName()).append(".")
					.append("Inactive")
					.toString();
		}
		return (new StringBuilder()).append(super.getItemName()).append(".")
				.append("Active")
				.toString();
	}

}
