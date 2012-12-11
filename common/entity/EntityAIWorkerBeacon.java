package riseautomatons.common.entity;

import riseautomatons.common.Coord;
import riseautomatons.common.block.BeaconManager;
import riseautomatons.common.block.TileEntityBeacon;
import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityPlayer;

public class EntityAIWorkerBeacon extends EntityAIBase {

	EntityWorker bot;

	public EntityAIWorkerBeacon(EntityWorker ento) {
		bot = ento;
	}

	@Override
	public boolean shouldExecute() {
		if (bot.getHome() == null) {
			EntityPlayer owner = bot.reallyGetBotOwner();
			if (owner != null) {
				findBeacon(owner);
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean continueExecuting() {
		return false;
	}

	@Override
	public void startExecuting() {
		super.startExecuting();
		beaconCheck();
	}

	void findBeacon(EntityPlayer playah) {
		TileEntityBeacon beacon = BeaconManager.getSelection(playah);
		if (beacon != null) {
			bot.setHome(beacon);
			bot.getLookHelper().setLookPosition(beacon.xCoord, beacon.yCoord,
					beacon.zCoord, 10f, bot.getVerticalFaceSpeed());
		}
	}

	public void beaconCheck() {
		if (bot.home.getSiren() == 1) {
			bot.setMode(EnumBotMode.FOLLOW);
		} else if (bot.home.getSiren() == 2) {
			Coord home = bot.getHomeCoord();
			bot.setPosition(home.x, home.y, home.z);
			bot.dropper();
		}
	}
}
