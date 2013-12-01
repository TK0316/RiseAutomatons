package riseautomatons.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityOwnedBot extends EntityGolem {

	public EntityOwnedBot(World par1World) {
		super(par1World);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(15, "");
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {

		if (par1DamageSource == DamageSource.inWall) {
			this.pushOutOfBlocks(posX, posY, posZ);
			return false;
		}
		Entity entity = par1DamageSource.getEntity();

		if (entity != null && entity != this
				&& (entity instanceof EntityPlayer)
				&& ((EntityPlayer) entity).username.equals(getBotOwner())) {
			par2 = 20;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	protected int getDropItemId() {
		return 0;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {

		super.writeEntityToNBT(par1nbtTagCompound);

		if (getBotOwner() == null) {
			par1nbtTagCompound.setString("Owner", "");
		} else {
			par1nbtTagCompound.setString("Owner", getBotOwner());
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {

		super.readEntityFromNBT(par1nbtTagCompound);
		String s = par1nbtTagCompound.getString("Owner");

		if (s.length() > 0) {
			setBotOwner(s);
		}
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	public String getBotOwner() {
		return dataWatcher.getWatchableObjectString(15);
	}

	public void setBotOwner(String s) {
		dataWatcher.updateObject(15, s);
	}

	public EntityPlayer reallyGetBotOwner() {
		return (EntityPlayer) worldObj.getPlayerEntityByName(getBotOwner());
	}

	public EnumBotMode getMode() {
		return EnumBotMode.STAY;
	}

	public boolean getAvoidsWater() {
		return false;
	}

}
