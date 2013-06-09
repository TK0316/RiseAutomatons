package riseautomatons.item;

public enum EnumCraftSetType {
	LENS("lens", "Lens"),
	EYEPIECE("eyePiece", "Eye Piece"),
	STONECOG("stonecog", "Stone Cog"),
	LOOP("loop", "Metal Ring"),
	CHAIN("chain", "Chain"),
	SPROCKET("sprocket", "Sprocket"),
	JOINT("joint","Joint"),
	SHARP("sharp", "Sharpened Point"),
	SMALLPLATE("smallPlate", "Small Plate"),
	CYLINDER("cylinder", "Cylinder"),
	ROD("rod", "Rod"),
	PISTON("piston", "Tiny Piston"),
	ACTUATOR("actuator", "Soul Actuator"),
	CANVAS("canvas", "Canvas"),
	WING("wing", "Wing"),
	PASSIVE("passive", "Controller (Passive)"),
	AGGRESSIVE("aggressive", "Controller (Aggressive)"),
	JAW("jaw", "Jaw"),
	SENSOR("sensor", "NVS"),
	DRILL("drill", "Pickaxe Module"),
	ROLLERCHAIN("rollerChain", "Roller Chain"),
	SMALLHEAD("wHead", "Small Head"),
	SMALLBODY("smallBody", "Small Body"),
	TOTEHEAD("totehead", "Tote Head"),
	MIDBODY("medBody", "Greater Body"),
	SMALLLEG("smallLeg", "Small Leg"),
	SENTRYHEAD("sHead", "Sentry Head"),
	SALT("salt", "Salt"),
	SURF("sulf", "Sulf"),
	FACTOTUMHEAD("factotumhead", "Factotum Head"),
	FACTOTUMCHUNK("furnaceChunk", "Factotum Chunk"),
	BLUECORE("automatonCore", "Blue Core"),
	REDCORE("superCore", "Red Core"),
	PLANTMATTER("planty", "Plant Matter"),
	PARTICULATE("bitty", "Cybernetic Particulate"),
	BIONICCONGLOMERATE("ABC", "Adaptive Bionic Conglomerate");

	public final String name;
	public final String fullname;

	EnumCraftSetType(String par1, String par2) {
		this.name = par1;
		this.fullname = par2;
	}
}
