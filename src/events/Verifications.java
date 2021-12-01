package events;

import java.util.ArrayList;
import java.util.HashMap;

import model.Joueur;
import model.Mouvement;
import model.Piece;
import utilitaires.Utilitaires;

public class Verifications {
	public static boolean verif_Choice(String position, ArrayList<String> alMoves) {
		if (position.length() > 0) {
			if (!((position.charAt(0) >= 'A') && (position.charAt(0) <= 'L'))) {
				return false;
			}
			for (int i = 1; i < position.length(); i++) {
				if (!((position.charAt(i) >= '0') && (position.charAt(i) <= '9'))) {
					return false;
				}
			}
			if (!(alMoves.contains(position))) {
				return false;
			}
		} else {
			return false;
		}

		return true;
	}

	public static void eat_Condition(ArrayList<String> tabMovable, HashMap<String, Piece> hmP,
			HashMap<String, Piece> Eat_Map, Piece P, ArrayList<String> alMoves, String[][] board, int signY, int signX,
			boolean chosen) {
		// HashMap<String, Piece> Eat_mouvement = new HashMap<>();
		if (hmP.containsKey(board[P.getY() + 1 * signY][P.getX() + 1 * signX])) {
			if (hmP.get(board[P.getY() + 1 * signY][P.getX() + 1 * signX]).getColour() != hmP
					.get(board[P.getY()][P.getX()]).getColour()) {
				if (board[P.getY() + 2 * signY][P.getX() + 2 * signX] == ".") {
					Eat_Map.put(Utilitaires.get_Position(board, P.getX() + 2 * signX, P.getY() + 2 * signY),
							hmP.get(board[P.getY() + 1 * signY][P.getX() + 1 * signX]));
					alMoves.add(Utilitaires.get_Position(board, P.getX() + 2 * signX, P.getY() + 2 * signY));
					tabMovable.add(P.getName());
					if (chosen) {
						board[P.getY() + 2 * signY][P.getX() + 2 * signX] = Utilitaires.get_Position(board,
								P.getX() + 2 * signX, P.getY() + 2 * signY);
					}

				}
			}
		}
	}

	// cette fonction permet de verifier le mouvement du pion en dépendant de sign
	// (si signY=-1 on verifie en haut sinon en bas)
	public static void verif_Movement(ArrayList<String> tabMovable, Piece P, ArrayList<String> alMoves,
			String[][] board, int signY, int signX, boolean chosen) {
		if (board[P.getY() + 1 * signY][P.getX() + 1 * signX] == ".") {
			alMoves.add(Utilitaires.get_Position(board, P.getX() + 1 * signX, P.getY() + 1 * signY));
			tabMovable.add(P.getName());
			if (chosen) {
				board[P.getY() + 1 * signY][P.getX() + 1 * signX] = Utilitaires.get_Position(board,
						P.getX() + 1 * signX, P.getY() + 1 * signY);
			}

		}

	}
	public static void verif_If_Player_Lost(Joueur j) {
		j.setLost(j.getNbPieces()==0);
	}
	// afficher les mouvement possibles
	public static Mouvement possible_Moves(HashMap<String, Piece> hmP, Piece P, String[][] board, boolean chosen) {
		// possible moves
		int[] directions = new int[] { -1, 1 };
		Mouvement mvt = new Mouvement(false, null, hmP, null);
		ArrayList<String> alMoves = new ArrayList<String>();
		HashMap<String, Piece> Eat_Map = new HashMap<>();
		// String[] tabMovable = null;
		ArrayList<String> tabMovable = new ArrayList<String>();
		if (P.isQueen()) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					eat_Condition(tabMovable, hmP, Eat_Map, P, alMoves, board, directions[i], directions[j], chosen);
					verif_Movement(tabMovable, P, alMoves, board, directions[i], directions[j], chosen);
				}
			}
		} else if (P.getColour() == "Blanc") {
			for (int j = 0; j < 2; j++) {
				eat_Condition(tabMovable, hmP, Eat_Map, P, alMoves, board, directions[0], directions[j], chosen);
				verif_Movement(tabMovable, P, alMoves, board, directions[0], directions[j], chosen);
			}
		} else if (P.getColour() == "Noir") {
			for (int j = 0; j < 2; j++) {
				eat_Condition(tabMovable, hmP, Eat_Map, P, alMoves, board, directions[1], directions[j], chosen);
				verif_Movement(tabMovable, P, alMoves, board, directions[1], directions[j], chosen);
			}
		}
		mvt.setEat_Moves(Eat_Map);
		mvt.setPossible_Moves(alMoves);
		if (alMoves.isEmpty()) {
			mvt.setPossible_Moves_Exist(false);
		} else {
			mvt.setPossible_Moves_Exist(true);
		}
		return mvt;

	}
}
