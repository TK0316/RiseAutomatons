package riseautomatons.common.spell;

import riseautomatons.common.Ids;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.World;

public class Eupraxia extends Spell {

	@Override
	public boolean cast(World world, int i, int j, int k,
			EntityPlayer entityPlayer) {
		if (entityPlayer.ridingEntity == null
				&& entityPlayer.riddenByEntity == null
				&& entityPlayer instanceof EntityPlayerMP) {

			EntityPlayerMP thePlayer = (EntityPlayerMP) entityPlayer;
			thePlayer.timeUntilPortal = 10;

			if (world.provider.dimensionId == Ids.eupraxia) {
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
