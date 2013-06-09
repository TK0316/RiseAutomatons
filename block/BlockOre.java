package riseautomatons.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import riseautomatons.Ids;
import riseautomatons.item.EnumCraftSetType;

public class BlockOre extends Block {

	private Icon icons[];
	int type;

    public BlockOre(int par1, int par2)
    {
        super(par1, Material.rock);
    	type = par2;
    }

	@Override
	public int quantityDropped(Random par1Random) {

        if (blockID == Ids.saltOre)
        {
            return 1 + par1Random.nextInt(8);
        }
        else
        {
            return 4;
        }
	}

	@Override
	public Icon getBlockTextureFromSideAndMetadata(int i, int j) {
		int n = MathHelper.clamp_int(j, 0, 1);
		return icons[type];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[2];
		icons[0] = par1IconRegister.registerIcon("riseautomatons:saltOre");
		icons[1] = par1IconRegister.registerIcon("riseautomatons:sulfOre");
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Ids.craftSet;
	}

	@Override
	public int damageDropped(int par1) {
		return blockID == Ids.saltOre ? EnumCraftSetType.SALT.ordinal() : EnumCraftSetType.SURF.ordinal();
	}
}
