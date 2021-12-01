package utilitaires;

import game.Texts;
import model.Joueur;
import model.Match;

public class Menu {

	public static void menu_Size(Match match, Joueur j1, Joueur j2) {
		System.out.println(Texts.size_Choice);
		int choice = Utilitaires.saisie_char_num(3, "votre choix : ");
		switch (choice) {
		case 1:
			match.setSize(10);
			j1.setNbPieces(12);
			j2.setNbPieces(12);
			match.setJ1(j1);
			match.setJ2(j2);
			break;
		case 2:
			match.setSize(12);
			j1.setNbPieces(20);
			j2.setNbPieces(20);
			match.setJ1(j1);
			match.setJ2(j2);
			break;
		case 3:
			match.setSize(14);
			j1.setNbPieces(30);
			j2.setNbPieces(30);
			match.setJ1(j1);
			match.setJ2(j2);
			break;
		default:
			System.out.println("choix invalide alors la taille sera 8*8");
			break;
		}
	}

	public static void menu_Player_Two(Joueur j) {

	}

	public static void menu_Player(Match match) {
		int choice = 0;
		do {
			String nom = "";
			Utilitaires.displayPlayer(match.getJ1());
			Utilitaires.displayPlayer(match.getJ2());
			if(match.getJ2().isControlled()) {
				System.out.println(match.getJ2().getName()+" is Controlled by a Player");
			}else {
				System.out.println(match.getJ2().getName()+" is Controlled by an AI");
			}
			System.out.println("\n\n" + Texts.menu_Joueurs);
			choice = Utilitaires.saisie_char_num(5, "votre choix : ");
			switch (choice) {
			case 1:

				do {
					System.out.println("Saisir le nom du joueur 1 : ");
					nom = Utilitaires.saisieString();
				} while (nom.toUpperCase().equals(match.getJ2().getName().toUpperCase()));
				match.getJ1().setName(nom);
				if (nom == "") {
					System.out.println("pseudo vide alors votre pseudo sera Player1");
					nom = "Player1";
				}
				break;
			case 2:
				do {
					System.out.println("Saisir le nom du joueur 2 : ");
					nom = Utilitaires.saisieString();
				} while (nom.toUpperCase().equals(match.getJ1().getName().toUpperCase()));
				if (nom == "") {
					System.out.println("pseudo vide alors votre pseudo sera Player2");
					nom = "Player2";
				}
				match.getJ2().setName(nom);
				break;
			case 3:
				match.getJ2().setControlled(!match.getJ2().isControlled());
				break;
			case 4:
				String aux = match.getJ1().getCouleur();
				match.getJ1().setCouleur(match.getJ2().getCouleur());
				match.getJ2().setCouleur(aux);
				break;
			case 5:
				System.out.println("quitter");
				break;
			default:
				break;
			}
		} while (choice != 5);
		// String name_J1=Utilitaires.saisieString();
	}

	public static Match menu_Principale() {
		Joueur j1 = new Joueur("Player1", "Blanc", true, true, false, 0, 12);
		Joueur j2 = new Joueur("Player2", "Noir", false, false, false, 0, 12);
		// String choice;
		// int choice_Int;
		int choice = 0;
		Match match = new Match(10, j1, j2);
		do {
			System.out.println(Texts.menu_Principale);
			choice = Utilitaires.saisie_char_num(3, "votre choix : ");
			switch (choice) {
			case 1:
				menu_Size(match, j1, j2);
				break;
			case 2:
				menu_Player(match);
				break;
			case 3:
				System.out.println();
				break;
			default:
				System.out.println("Erreur menu principale !!");
				System.out.flush();
				break;
			}
		} while (choice != 3);
		Utilitaires.displayPlayer(j1);
		Utilitaires.displayPlayer(j2);
		return match;
	}

}
