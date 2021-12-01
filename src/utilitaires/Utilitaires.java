package utilitaires;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import events.Verifications;
import model.Joueur;
import model.Mouvement;
import model.Piece;

public class Utilitaires {
	public static void draw_Board(String[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				board[0][0] = "*";
				board[0][board[i].length - 1] = "*";
				board[board.length - 1][0] = "*";
				board[board.length - 1][board[i].length - 1] = "*";
			}

		}
		add_Boarders_To_Board(board);
	}

	public static void add_Boarders_To_Board(String[][] board) {
		String[] letters = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
		String[] numbers = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		;
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board[i].length - 1; j++) {
				board[0][j] = letters[j - 1];
				board[board.length - 1][j] = letters[j - 1];
				board[i][0] = numbers[i - 1];
				board[i][board[i].length - 1] = numbers[i - 1];
				board[i][j] = ".";
			}
		}
	}

//add_Pieces_To_List
	public static void add_Pieces_To_List(ArrayList<Piece> alP, int size) {
		int id = 1;
		for (int line = 1; line < (size / 2) - 1; line++) {
			for (int column = 1; column < size - 2; column = column + 2) {
				if (line % 2 != 0) {
					if (size == 12) {
						alP.add(new Piece("PN" + String.valueOf(id), "Noir", id, line, column, false));
						alP.add(new Piece("PB" + String.valueOf(id), "Blanc", id, line + (size / 2), column, false));
					} else {
						alP.add(new Piece("PN" + String.valueOf(id), "Noir", id, line, column, false));
						alP.add(new Piece("PB" + String.valueOf(id), "Blanc", id, line + (size / 2), column + 1,
								false));
					}
				} else {
					if (size == 12) {
						alP.add(new Piece("PN" + String.valueOf(id), "Noir", id, line, column + 1, false));
						alP.add(new Piece("PB" + String.valueOf(id), "Blanc", id, line + (size / 2), column + 1,
								false));
					} else {
						alP.add(new Piece("PN" + String.valueOf(id), "Noir", id, line, column + 1, false));
						alP.add(new Piece("PB" + String.valueOf(id), "Blanc", id, line + (size / 2), column, false));
					}
				}
				id++;
			}

		}
	}

//add_Pieces_To_Board sert à ajouter les pieces dans la matrice
	public static void add_Pieces_To_Board(ArrayList<Piece> alP, HashMap<String, Piece> hmP, String[][] board) {
		for (Piece P : alP) {
			board[P.getY()][P.getX()] = P.getName();
			hmP.put(P.getName(), P);
		}
	}

//read_Board sert à afficher une matrice
	public static void read_Board(String[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print("	" + board[i][j]);
			}
			System.out.println("\n\n");
		}
	}

//saisieString() sert à demander à l'utilisateur de saisir une chaine de caractère
	public static String saisieString() {
		@SuppressWarnings("resource")
		String s = new Scanner(System.in).nextLine();
		return s;
	}

	public static boolean isNumeric(String str) {
		return str != null && str.matches("[-+]?\\d*\\.?\\d+");
	}

	/*
	 * cette fonction prend une valeur et verifie si le caractère saisie est
	 * numerique ou pas après elle la compare avec cette valeur maximale (cette
	 * fonction est utilisée dans les choix avec les nums)
	 */
	public static int saisie_char_num(int maxValue, String msg) {
		boolean verif = false;
		int character = 0;
		while (verif == false) {
			System.out.println(msg);
			String c = saisieString();
			while (!isNumeric(c)) {
				System.out.println("valeur numerique  !!!\n" + msg);
				c = saisieString();
			}
			character = Integer.valueOf(c);
			if (character >= 1 && character <= maxValue) {
				verif = true;
			}
		}
		return character;
	}

//get_Position sert à récuperer la position(x,y) mais sous la forme (lettrechiffre)exemple: (1,1)A1
	public static String get_Position(String[][] board, int x, int y) {
		String pos_checkers = "";
		pos_checkers = pos_checkers + board[0][x] + board[y][0];
		return pos_checkers;
	}

//affichage d'une liste
	public static void display_List(ArrayList<String> almoves, String msg) {
		System.out.println(msg);
		for (String move : almoves) {
			System.out.println(move);
		}
	}

//réinitialiser la board après chaque mouvement
	public static void reinitBoard(HashMap<String, Piece> hmP, String[][] board) {
		for (int i = 1; i < board.length - 1; i++) {
			for (int j = 1; j < board[i].length - 1; j++) {
				if (!hmP.containsKey(board[i][j])) {
					board[i][j] = ".";
				}
			}
		}
	}

	public static int get_Nb_Pieces(HashMap<String, Piece> hmP, String colour) {
		int nb_Pieces = 0;
		for (String key : hmP.keySet()) {
			if (hmP.get(key).getColour() == colour) {
				nb_Pieces++;
			}
		}
		return nb_Pieces;

	}

	// afficher les informations du joueur
	public static void displayPlayer(Joueur j) {
		System.out.println("Name : " + j.getName());
		System.out.println("Colour : " + j.getCouleur());
		System.out.println("nbPieces : " + j.getNbPieces());
		System.out.println("nbPoints : " + j.getNbPoints());
	}

	// récuperer une liste qui contient les pieces qu'on peut bouger
	public static void get_Movable_Pieces(Mouvement mvt, HashMap<String, Piece> hmP, String[][] board, String colour) {
		ArrayList<String> tabMovable = new ArrayList<String>();
		for (String key : hmP.keySet()) {
			if (hmP.get(key).getColour() == colour) {
				if (Verifications.possible_Moves(hmP, hmP.get(key), board, false).isPossible_Moves_Exist()) {
					tabMovable.add(hmP.get(key).getName());
				}
			}
		}
		mvt.setTabMovable(tabMovable);
	}
}
