package riseautomatons.common;

public class Coord {
	public int x = 0;
	public int y = 0;
	public int z = 0;
	boolean isValid = false;

	public Coord(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		isValid = true;
	}

	public Coord() {

	}

	public Coord(Coord c) {
		this.x = c.x;
		this.y = c.y;
		this.z = c.z;
		isValid = c.isValid;
	}

	public void setCoord(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		isValid = true;
	}

	public void setCoord(Coord c) {
		this.x = c.x;
		this.y = c.y;
		this.z = c.z;
		isValid = c.isValid;
	}

	public boolean isValid() {
		return isValid;
	}

	public void addCoord(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

	public void addCoord(Coord c) {
		addCoord(c.x, c.y, c.z);
	}

}
