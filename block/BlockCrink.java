package riseautomatons.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrink extends Block {

	private IIcon icons[];
	protected BlockCrink(int j) {

		super( Material.circuits);
		slipperiness = 1.0F;
	}

	public BlockCrink(Material par2Material) {
		super(par2Material);
	}

	public BlockCrink(int par2, Material par3Material) {
		super(par3Material);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i,
			int j, int k, int l) {
		return super.shouldSideBeRendered(iblockaccess, i, j, k, l);
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	public IIcon getIcon(int i, int j) {
        //if (!Blocks.leaves.graphicsLevel) {
        if (Blocks.leaves.isOpaqueCube()) {
			return icons[2];
		}

		if (j > 0) {
			return icons[0]; // top 235
		}

		return icons[1]; // D[0]
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[3];
		icons[0] = par1IconRegister.registerIcon("riseautomatons:crink");
		icons[1] = par1IconRegister.registerIcon("riseautomatons:crink2");
		icons[2] = par1IconRegister.registerIcon("riseautomatons:crink3");
	}

	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int j = 0; j < derk.length; ++j) {
			par3List.add(new ItemStack(par1, 1, j));
		}
	}

	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer,
			int par3, int par4, int par5, int par6) {
		super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
	}

	@Override
	public boolean isOpaqueCube() {
        //return (!Block.leaves.graphicsLevel);// ?1:0;//false;
		return (Blocks.leaves.isOpaqueCube());// ?1:0;//false;
	}

	@Override
	public int getRenderBlockPass() {
        //return (!Block.leaves.graphicsLevel) ? 0 : 1; // false;;
		return (Blocks.leaves.isOpaqueCube()) ? 0 : 1; // false;;
	}

	@Override
	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k) {
		int g = iblockaccess.getBlockMetadata(i, j, k);
		return derk[g];
	}

    @Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1) {
		return derk[par1];
	}

	public static int derk[] = {
			0xffffff, // 0x0093bd,
			0x496FD6, 0x003469, 0x96E6EB, 0x43cef1, 0x4b6e8a, 0x7D4B94,
			0x292f3f, 0x08143a, 0x737a7b, 0x3ED613 };

}
