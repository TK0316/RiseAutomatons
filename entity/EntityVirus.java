package riseautomatons.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import riseautomatons.block.Blocks;

public class EntityVirus extends EntityThrowable {

	public boolean active = false;
	public EntityVirus(World par1World) {
		super(par1World);
	}

	public EntityVirus(World par1World, EntityLivingBase par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}

	public EntityVirus(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        if (par1MovingObjectPosition.entityHit != null)
        {
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);
            if(par1MovingObjectPosition.entityHit instanceof EntityLiving) {
            	EntityLiving e = (EntityLiving) par1MovingObjectPosition.entityHit;
                if(e instanceof EntityOwnedBot || e instanceof EntityBot || e instanceof EntityAniBot || e instanceof EntityWatcher) {
                	e.addPotionEffect(new PotionEffect(Potion.harm.id, 200, 0));
                }
            }
        }

        int x = par1MovingObjectPosition.blockX;
        int y = par1MovingObjectPosition.blockY;
        int z = par1MovingObjectPosition.blockZ;

    	int range = 3;
        for(int i = x - range; i < x + range; i++) {
            for(int j = y - range; j < y + range; j++) {
            	if( j <= 1 || 255 <= j) {
            		continue;
            	}
                for(int k = z - range; k < z + range; k++) {
                	if(this.worldObj.getBlockId(i, j, k) == Blocks.frass.blockID) {
                		int meta = this.worldObj.getBlockMetadata(i, j, k);
                		if(meta < 8) {
                			//System.out.println(String.valueOf(i) + "," + String.valueOf(j) + "," + String.valueOf(k));
                			this.worldObj.setBlockMetadataWithNotify(i, j, k, meta + 8, 3);
                		}
                	}
                }
            }
        }

        if (!this.worldObj.isRemote)
        {
            this.worldObj.playAuxSFX(2002, (int)Math.round(this.posX), (int)Math.round(this.posY), (int)Math.round(this.posZ), 0);
            this.setDead();
        }

	}

}
