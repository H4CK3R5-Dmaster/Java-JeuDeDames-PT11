package model;

public class Piece {
	String name, colour;
	int id, y, x;
	boolean isQueen;

	public Piece(String name, String colour, int id, int y, int x, boolean isQueen) {
		super();
		this.name = name;
		this.colour = colour;
		this.id = id;
		this.y = y;
		this.x = x;
		this.isQueen = isQueen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public boolean isQueen() {
		return isQueen;
	}

	public void setQueen(boolean isQueen) {
		this.isQueen = isQueen;
	}

}
