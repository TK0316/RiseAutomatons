package riseautomatons.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import riseautomatons.Ids;

public class BlockTeleporter extends Block {
	public BlockTeleporter() {
		super(500, 0, Material.iron);
		setBlockName("teleporter");
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (par5EntityPlayer.ridingEntity == null
				&& par5EntityPlayer.riddenByEntity == null
				&& par5EntityPlayer instanceof EntityPlayerMP) {

			EntityPlayerMP thePlayer = (EntityPlayerMP) par5EntityPlayer;
			thePlayer.timeUntilPortal = 10;

			if (par1World.provider.dimensionId == Ids.eupraxia) {
				thePlayer.mcServer.getConfigurationManager()
				.transferPlayerToDimension(thePlayer, 0);
			} else {
				thePlayer.mcServer.getConfigurationManager()
				.transferPlayerToDimension(thePlayer, Ids.eupraxia);
			}
		}
		return false;
	}
}
