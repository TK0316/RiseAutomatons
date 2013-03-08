package riseautomatons.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.world.TeleporterEupraxia;

public class Eupraxia extends Spell {

	@Override
	public boolean cast(World world, int i, int j, int k,
			EntityPlayer entityPlayer) {
		if (entityPlayer.isRiding() == false
				&& entityPlayer instanceof EntityPlayerMP) {

			EntityPlayerMP thePlayer = (EntityPlayerMP) entityPlayer;
			thePlayer.timeUntilPortal = 10;

			if (world.provider.dimensionId == Ids.eupraxia) {
				thePlayer.mcServer.getConfigurationManager()
				.transferPlayerToDimension(thePlayer, 0, new TeleporterEupraxia(thePlayer.mcServer.worldServerForDimension(0)));
			} else {
				thePlayer.mcServer.getConfigurationManager()
				.transferPlayerToDimension(thePlayer, Ids.eupraxia, new TeleporterEupraxia(thePlayer.mcServer.worldServerForDimension(Ids.eupraxia)));
			}
		}
		return false;
	}

}
