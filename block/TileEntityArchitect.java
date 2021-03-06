package riseautomatons.block;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import riseautomatons.Ids;

public class TileEntityArchitect extends TileEntity {
	float Rotation;
	float prevRotation;
	float Pitch;
	float lid;

	int delay = 0;
	int tick = 0;
	float ppr;
	float rnx;
	float rny;
	float lid2;

	int tock;
	float rott;
	float pitt;

	int focx;
	int focz;
	int focy;
	boolean focus = false;

	public TileEntityArchitect() {
		Pitch = 1.57f;
		Rotation = (float) (Math.random() * Math.PI * 2);
		lid = 0.3f;
		lid2 = 1f;
		System.out.println("Architect!");
	}

	@Override
	public void updateEntity() {
		EntityPlayer ep = this.worldObj.getClosestPlayer(
				(double) ((float) this.xCoord + 0.5F),
				(double) ((float) this.yCoord + 0.5F),
				(double) ((float) this.zCoord + 0.5F), 3.0D);
		delay++;
		if (delay % 10 == 0) {
			rnx = (float) (Math.random() - 0.5f) * 0.5f;
			rny = (float) (Math.random() - 0.5f) * 0.5f;
		}
		if (delay % 40 == 0) {
			tick = 10;
		}
		prevRotation = ppr;
		ppr = Rotation;
		if (ep != null) {
			double xx = ep.posX - (double) ((float) this.xCoord + 0.5F);
			double zz = ep.posZ - (double) ((float) this.zCoord + 0.5F);
			double yy = ep.posY - (double) ((float) this.yCoord + 0.5F);
			this.Rotation = (float) Math.atan2(xx, zz);
			this.Pitch = (float) Math.atan2(Math.sqrt(xx * xx + zz * zz), yy);
			lid2 = (float) ((Math.sqrt(xx * xx + yy * yy + zz * zz) / 3f));
		}
		if (tick > 0) {
			tick--;
		}
		float factor = Math.abs(tick - 5) / 5f;
		lid = 0.3f * lid2 * factor;

		if (delay > 80) {
			delay = 0;
			tock = 20;
			if (ep == null) {
				rott = (float) (Math.random() - 0.5f) / 2f;
				pitt = (float) (Math.random() - 0.5f) / 10f;
			}
		}
		if (tock > 0) {
			tock--;
			Rotation += rott;
			Pitch += pitt;
			if (Pitch > 1.57f) {
				Pitch = 1.57f;
			} else if (Pitch < 0.2f) {
				Pitch = 0.2f;
			}
		}

		if (!worldObj.isRemote)
			plague();
	}

	public void plague() {
		Random rand = worldObj.rand;
		if (focus) {
			// System.out.println("focus!");
			if (worldObj.getBlock(focx, focy, focz) == Ids.blockArchBend) {
				focy++;
			} else {
				((BlockArchBend) Blocks.archBend).grow(worldObj, focx,
						focy - 1, focz, rand);
				focus = false;
				// System.out.println("peaked!");
			}

		} else {

			int xi = rand.nextInt(5) - 2;
			int yi = rand.nextInt(9) - 2;
			int zi = rand.nextInt(5) - 2;

			int xx = xCoord + xi;
			int yy = yCoord + yi;
			int zz = zCoord + zi;

			if (xi == 0 && yi == 0 && zi == 0) {
				return;
			}
			if ((zi == 2 && (xi == 2 || xi == -2))
					|| (zi == -2 && (xi == 2 || xi == -2))) {
				if (worldObj.getBlock(xx, yy, zz) == Ids.blockArchBend) {
					focx = xx;
					focy = yy;
					focz = zz;
					focus = true;
				} else {
					worldObj.setBlock(xx, yy, zz,
							Ids.blockArchBend, 2, 3);
				}

			} else if (Math.abs(zi) <= 1 && Math.abs(xi) <= 1 && yi > -1
					&& yi < 5) {
				if (yi == 4) {
					worldObj.setBlock(xx, yy, zz, Blocks.obsidian, 0, 3);
				} else {
					if (yi == 0
							&& ((xi == 0 && Math.abs(zi) == 1) || (zi == 0 && Math
									.abs(xi) == 1))) {
						worldObj.setBlock(xx, yy, zz,
								Ids.blockArchBend, 4, 3);
					} else {
						worldObj.setBlockToAir(xx, yy, zz);
					}

				}

			} else if (worldObj.getBlock(xx, yy, zz).isOpaqueCube()) {
				BlockArch.place(worldObj, xx, yy, zz);
			}

		}
	}

}
