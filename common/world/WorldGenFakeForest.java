package riseautomatons.common.world;

import java.util.Random;

import net.minecraft.src.World;
import net.minecraft.src.WorldGenForest;

public class WorldGenFakeForest extends WorldGenForest {

	public WorldGenFakeForest(boolean par1) {
		super(par1);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3,
			int par4, int par5) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.generate(par1World, par2Random, par3, par4, par5);
	}

}
