package riseautomatons.common.world;

import riseautomatons.common.Ids;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.WorldProvider;
import net.minecraft.src.WorldProviderSurface;

public class WorldProviderEupraxia extends WorldProvider {

/*	@Override
	public BiomeGenBase getBiomeGenForCoords(int x, int z) {
		BiomeGenBase biome = super.getBiomeGenForCoords(x, z);
		if(biome == null) {
			biome = BiomeGenBase.forest;
		}
		return  BiomeGenBase.forest;
	}
*/

	@Override
	public String getDimensionName() {
		return "Eupraxia";
	}

	@Override
	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderEupraxia(worldObj, worldObj.getSeed(), true);
	}

	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		int i = worldObj.getFirstUncoveredBlock(par1, par2);

		if (i == 0) {
			return false;
		} else {
			return Block.blocksList[i].blockMaterial.blocksMovement();
		}
	}

	@Override
	public float calculateCelestialAngle(long par1, float par3) {
		return 2.0F;
	}

	@Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public float getCloudHeight() {
		return 99F;
	}

	@Override
	public boolean isSkyColored() {
		return true;
	}

	@Override
	public ChunkCoordinates getEntrancePortalLocation() {
		return new ChunkCoordinates(100, 50, 0);
	}

	@Override
	public int getAverageGroundLevel() {
		return 50;
	}

	@Override
	protected void registerWorldChunkManager() {
		worldChunkMgr =new WorldChunkManagerEupraxia(worldObj);
		this.dimensionId = Ids.eupraxia;
		hasNoSky = false;
	}

}
