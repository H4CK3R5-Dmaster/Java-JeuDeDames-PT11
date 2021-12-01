package model;

public class Joueur {
	String name;
	String couleur;
	boolean tour, isControlled, lost;
	int nbPoints, nbPieces;

	public Joueur(String name, String couleur, boolean tour, boolean isControlled, boolean lost, int nbPoints,
			int nbPieces) {
		super();
		this.name = name;
		this.couleur = couleur;
		this.tour = tour;
		this.isControlled = isControlled;
		this.lost = lost;
		this.nbPoints = nbPoints;
		this.nbPieces = nbPieces;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public boolean isTour() {
		return tour;
	}

	public void setTour(boolean tour) {
		this.tour = tour;
	}

	public boolean isControlled() {
		return isControlled;
	}

	public void setControlled(boolean isControlled) {
		this.isControlled = isControlled;
	}

	public boolean isLost() {
		return lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}

	public int getNbPoints() {
		return nbPoints;
	}

	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}

	public int getNbPieces() {
		return nbPieces;
	}

	public void setNbPieces(int nbPieces) {
		this.nbPieces = nbPieces;
	}

}
