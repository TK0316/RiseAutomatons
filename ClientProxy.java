package riseautomatons;

import riseautomatons.block.Blocks;
import riseautomatons.entity.Entities;
import riseautomatons.item.Items;

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
