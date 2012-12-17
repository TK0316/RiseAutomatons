package riseautomatons.client;

import riseautomatons.common.CommonProxy;
import riseautomatons.common.Universal;
import riseautomatons.common.block.Blocks;
import riseautomatons.common.entity.Entities;
import riseautomatons.common.item.Items;

public class ClientProxy extends CommonProxy {

	@Override
	public void regiserTextures() {
		Entities.initEffect();
		Entities.registerTextures();
		Blocks.registerTileEntities();
		Blocks.registerTextures();
		Items.registerTextures();
		Universal.loadSound();

	}

}
