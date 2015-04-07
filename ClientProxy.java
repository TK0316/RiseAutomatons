package riseautomatons;

import riseautomatons.block.Blocks;
import riseautomatons.entity.Entities;

public class ClientProxy extends CommonProxy {

	@Override
	public void regiserTextures() {
		Entities.initEffect();
		Entities.registerTextures();
		Blocks.registerTileEntities();
		Blocks.registerRenderer();

	}

}
