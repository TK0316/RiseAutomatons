package riseautomatons.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class TeleportNether extends Spell {

	@Override
	public boolean cast(World world, int i, int j, int k,
			EntityPlayer entityPlayer) {
		if (entityPlayer.ridingEntity == null
				&& entityPlayer.riddenByEntity == null
				&& entityPlayer instanceof EntityPlayerMP) {

			EntityPlayerMP thePlayer = (EntityPlayerMP) entityPlayer;
			thePlayer.timeUntilPortal = 10;

			if (world.provider.dimensionId != -1) {
				thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, -1);
			}
		}
		return false;
	}

}
