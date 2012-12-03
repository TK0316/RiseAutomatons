package riseautomatons.common.item;

public enum EnumSoulCore {
	SOULSYNTH("soulSynth", "Synthetic Soul"),
	STONEROUNDED("stoneRounded", "Rounded Stone"),
	STONEBALL("stoneBall", "Stone Sphere"),
	STONEINCOMPLETE("stoneIncomplete", "Unfinished Core"),
	STONEEMPTY("stoneEmpty", "Empty Core"),
	SOULPURE("soulPure", "Pure Soul"),
	SOULEVIL("soulEvil", "Tenebrae Soul");

	public final String name;
	public final String fullname;
	EnumSoulCore(String par1, String par2) {
		this.name = par1;
		this.fullname = par2;
	}
}
