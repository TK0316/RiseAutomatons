package riseautomatons.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import riseautomatons.Ids;
import riseautomatons.item.EnumCraftSetType;

public class BlockOre extends Block {

	private IIcon icons[];
	int type;

    public BlockOre(int par2)
    {
        super(Material.rock);
    	type = par2;
    }

	@Override
	public int quantityDropped(Random par1Random) {

        if (this == Ids.saltOre)
        {
            return 1 + par1Random.nextInt(8);
        }
        else
        {
            return 4;
        }
	}

	@Override
	public IIcon getIcon(int i, int j) {
		int n = MathHelper.clamp_int(j, 0, 1);
		return icons[type];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[2];
		icons[0] = par1IconRegister.registerIcon("riseautomatons:sulfOre");
		icons[1] = par1IconRegister.registerIcon("riseautomatons:saltOre");
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Ids.craftSet;
	}

	@Override
	public int damageDropped(int par1) {
		return this == Ids.saltOre ? EnumCraftSetType.SALT.ordinal() : EnumCraftSetType.SURF.ordinal();
	}
}
