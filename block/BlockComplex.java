package riseautomatons.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockComplex extends Block {

	private IIcon icons[];

	protected BlockComplex() {
		super(Material.rock);
		loadSprites();
	}

	public BlockComplex(int par2, Material par3Material) {
		super(Material.rock);
		loadSprites();
	}

	public BlockComplex(Material par2Material) {
		super(Material.rock);
		loadSprites();
	}

	@Override
	public IIcon getIcon(int i, int j) {
		int n = MathHelper.clamp_int(j, 0, 3);
		return icons[n]; // D[0]
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[4];
		icons[0] = par1IconRegister.registerIcon("riseautomatons:tech");
		icons[1] = par1IconRegister.registerIcon("riseautomatons:tree");
		icons[2] = par1IconRegister.registerIcon("riseautomatons:wallo");
		icons[3] = par1IconRegister.registerIcon("riseautomatons:bwop");
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	static int D[];

	static void loadSprites() {
		D = new int[16];
		D[0] = 11;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/tech.png");
		D[1] = 15;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/tree.png");
		D[2] = 16;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/wallo.png");
		D[3] = 6;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/bwop.png");
		D[4] = 12;// zei_Universal.modOverride("/terrain.png",
					// "/riseautomatons/tech2.png");
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int ix = 0; ix < 4; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}

}
