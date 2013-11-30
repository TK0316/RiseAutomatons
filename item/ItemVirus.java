package riseautomatons.item;

import java.util.List;


import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import riseautomatons.entity.EntityVirus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemVirus extends Item {

	private Icon icons[];

	public ItemVirus(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		int i = MathHelper.clamp_int(par1, 0, 1);
		return icons[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[2];
		icons[0] = par1IconRegister.registerIcon("riseautomatons:virus");
		icons[1] = par1IconRegister.registerIcon("riseautomatons:virusActive");
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
	public String getUnlocalizedName(ItemStack itemstack) {
		if(itemstack.getItemDamage() == 0) {
			return (new StringBuilder()).append(super.getUnlocalizedName()).append(".")
					.append("Inactive")
					.toString();
		}
		return (new StringBuilder()).append(super.getUnlocalizedName()).append(".")
				.append("Active")
				.toString();
	}

}
