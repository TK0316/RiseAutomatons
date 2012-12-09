package riseautomatons.common;

import java.io.File;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import riseautomatons.common.entity.EntityFwooshFX;
import riseautomatons.common.entity.EntityGoreFX;

import net.minecraft.client.Minecraft;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.DataWatcher;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;

public class Universal {

	public static Minecraft mc;

	public static void init() {
		mc = ModLoader.getMinecraftInstance();
		// mc.session.username="Aninon";
		// effectRenderer=ModLoader.getMinecraftInstance().effectRenderer;
		String path = mc.mcDataDir.getAbsolutePath()
				+ "\\resources\\mod\\sound\\automatons";
		File jar = new File(path);
		if (jar.exists()) {
			File files[] = jar.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					mc.sndManager.addSound("automatons/" + files[i].getName(),
							files[i]);
				}
			}
		}
		// mc.sndManager.addSound(par1Str, par2File)
	}

	public static void fwoo(World world, double posX, double posY, double posZ) {
		Random rand = world.rand;
		for (int i = 0; i < 10; i++) {
			double nx = rand.nextFloat() - 0.5f;
			double ny = rand.nextFloat() - 0.5f;
			double nz = rand.nextFloat() - 0.5f;
			mc.effectRenderer.addEffect(new EntityFwooshFX(world, (posX + nx),
					posY + ny, posZ + nz, nx * .2f, ny * 1.2f, nz * .2f, 10f,
					true));
		}
	}

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

	public static void gorey(World world, double posX, double posY, double posZ) {
		Random rand = world.rand;

		for (int i = 0; i < 100; i++) {
			// double d =( rand.nextGaussian() * 0.2D) -0.1d;
			// double d1 = rand.nextGaussian() *0.6d;
			// double d2 = (rand.nextGaussian() * 0.2D)-0.1d;
			double nx = rand.nextFloat() - 0.5f;
			double ny = rand.nextFloat() - 0.5f;
			double nz = rand.nextFloat() - 0.5f;
			mc.effectRenderer.addEffect(new EntityGoreFX(world, (posX + nx),
					posY + ny, posZ + nz, nx * 1.2f, ny * 1.2f, nz * 1.2f, 2f));
			// world.spawnParticle(s, (posX + rand.nextFloat() * 1.6F - 0.8f),
			// posY + (rand.nextFloat() * 1.6f), (posZ +rand.nextFloat() * 1.6F)
			// - 0.8f, d, d1, d2);
		}
	}

	public static void poof(World world, double posX, double posY, double posZ)
	{
		String s  = "explode";
		Random rand = world.rand;

		for (int i = 0; i < 7; i++)
		{
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			world.spawnParticle(s, (posX + rand.nextFloat() * 1.6F - 0.8f), posY + 0.5f + (rand.nextFloat() * 0.2f), (posZ + rand.nextFloat() * 1.6F) - 0.8f, d, d1, d2);
		}
	}

	public static double anotherAxisFunc(AxisAlignedBB aa) {
		return aa.getAverageEdgeLength() * 4D;
	}

}
