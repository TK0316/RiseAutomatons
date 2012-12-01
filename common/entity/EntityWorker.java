package riseautomatons.common.entity;

import net.minecraft.src.EntityMob;
import net.minecraft.src.World;

public class EntityWorker extends EntityMob implements IBot {

	public static final String GOLEM1_PNG = "/RiseAutomatons/golem1.png";

	public EntityWorker(World par1World) {
		super(par1World);
		setSize(0.6F, 0.8F);
	}

	@Override
	public int getMaxHealth() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 10;
	}

	@Override
	public String getTexture() {
		return GOLEM1_PNG;
	}

	public int getInventoryDamage() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
	}

	public int getInventoryType() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
	}

}
