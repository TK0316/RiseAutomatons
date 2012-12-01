package riseautomatons.common;

import net.minecraft.src.DataWatcher;
import net.minecraft.src.World;

public class Universal {

	public static boolean improperWorld(World world) {
		return world.isRemote;
	}

	public static double distance(double xi,double yi,double zi,double xe,double ye,double ze){
		double dx=xi-xe;
		double dy=yi-ye;
		double dz=zi-ze;
		return Math.sqrt(dx*dx +dy*dy + dz*dz);
	}

	public static int getInt(DataWatcher datawatcher, int i)
	{
		return datawatcher.getWatchableObjectInt(i);
	}

}
