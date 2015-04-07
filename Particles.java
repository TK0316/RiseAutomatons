package riseautomatons;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Particles {

	private static Particles instance;
	String[] iconNames = {"riseautomatons:fwoosh", "riseautomatons:gore"};
	IIcon icons[];

	public static Particles getInstance() {
		if (instance == null) {
			instance = new Particles();
		}

		return instance;
	}

    @SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void handleTextureRemap(TextureStitchEvent.Pre event) {
		if (event.map.getTextureType() == 1) {
			this.getInstance().registerIcons(event.map);
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[iconNames.length];
		for(int i = 0; i < icons.length; ++i) {
			icons[i] = par1IconRegister.registerIcon(iconNames[i]);
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(String iconName) {
		for(int i = 0; i < iconNames.length; ++i) {
			if(iconName.equalsIgnoreCase(iconNames[i])) {
				return icons[i];
			}
		}
		return null;
	}

}
