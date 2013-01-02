package riseautomatons.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import riseautomatons.Ids;
import riseautomatons.item.EnumCraftSetType;

public class BlockOre extends Block {
    public BlockOre(int par1, int par2)
    {
        super(par1, par2, Material.rock);
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
	public int idDropped(int par1, Random par2Random, int par3) {
		return Ids.craftSet;
	}

	@Override
	public int damageDropped(int par1) {
		return blockID == Ids.saltOre ? EnumCraftSetType.SALT.ordinal() : EnumCraftSetType.SURF.ordinal();
	}
}
