package riseautomatons.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import riseautomatons.Ids;
import riseautomatons.Universal;
import riseautomatons.item.EnumCraftSetType;

public class EntityBobby extends EntityBot implements IBot {

	public static final ResourceLocation BOBBY_PNG = new ResourceLocation("riseautomatons", "textures/entities/bobby.png");
	public static int renderId;

	public EntityBobby(World world) {
		super(world);
		setSize(0.9F, 0.9F);
		setHealth(getMaxHealth());
	}

	public EntityBobby(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		setPathToEntity(null);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.4F);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(5.0D);
	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}
	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}
	@Override
	protected String getLivingSound() {
		return "automatons.beep";
	}
	@Override
	protected String getHurtSound() {
		return "automatons.clank";
	}
	@Override
	protected String getDeathSound() {
		return "automatons.botdie";
	}
	@Override
	public void onDeath(DamageSource damagesource) {

		super.onDeath(damagesource);

		if (!Universal.improperWorld(worldObj)) {
			dropper();
		}

		setDead();
	}

	void dropper() {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode",
					(posX + (rand.nextFloat() * width * 2.0F))
							- width, posY
							+ (rand.nextFloat() * height),
					(posZ + (rand.nextFloat() * width * 2.0F))
							- width, d, d1, d2);
		}

		if (!Universal.improperWorld(worldObj)) {
			int R = rand.nextInt(2);

			if (R == 0) {
				if (worldObj.countEntities(EntityBobby.class) < 50) {
					worldObj.spawnEntityInWorld(new EntityBobby(worldObj,
							posX, posY, posZ));
					worldObj.spawnEntityInWorld(new EntityBobby(worldObj,
							posX, posY, posZ));
				}
			} else {
				if (rand.nextInt(3) == 0) {
					entityDropItem(new ItemStack(Ids.craftSet, 1, EnumCraftSetType.ROD.ordinal()),
							0.0F);
				}
			}

			deathTime = 999; // setEntityDead();
		}
	}

	@Override
	protected int getDropItemId() {
		return 0;
	}
	@Override
	public void onLivingUpdate() {

		if (isWet()) {
			setDead();
		}

		super.onLivingUpdate();
	}
	@Override
	public int getMaxSpawnedInChunk() {
		return 100;
	}

	@Override
	public ResourceLocation getTexture() {
		return BOBBY_PNG;
	}
}
