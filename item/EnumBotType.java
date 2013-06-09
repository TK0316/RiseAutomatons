package riseautomatons.item;

public enum EnumBotType {
	WORKER("worker", "Worker"),
	SENTRY("sentry", "Sentry"),
	BEACON("beacon", "Beacon"),
	TOTE("itemTote", "Toter"),
	FACTOTUM("itemfactotum", "Factotum"),
	GUARD("guard", "Guard"),
	OMNI("itemOmni", "Omni");

	public final String name;
	public final String fullname;
	EnumBotType(String par1, String par2) {
		this.name = par1;
		this.fullname = par2;
	}
}
