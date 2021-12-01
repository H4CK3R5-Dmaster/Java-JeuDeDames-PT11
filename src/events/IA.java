package events;

import java.util.HashMap;

import game.Texts;
import model.Joueur;
import model.Mouvement;
import model.Piece;
import utilitaires.Utilitaires;

public class IA {
	public static void round_IA(HashMap<String, Piece> hmPiece, String[][] board, Joueur joueur) {
		String piece="";
		String position="";
		System.out.println("\n\n" + Texts.display_Player_Turn + joueur.getName() + "\n");
		Utilitaires.displayPlayer(joueur);
		Mouvement mvt = new Mouvement(false, null, hmPiece, null);
		Utilitaires.get_Movable_Pieces(mvt, hmPiece, board, joueur.getCouleur());
		if (mvt.getTabMovable().isEmpty()) {
			joueur.setLost(true);
		} else {
			int choix_Piece_IA = (int) ((Math.random() * (mvt.getTabMovable().size())));
			piece=mvt.getTabMovable().get(choix_Piece_IA);
			// prev_pos est la position precedente de Pion avant de le bouger
			String prev_pos = Utilitaires.get_Position(board,
					hmPiece.get(piece).getX(),
					hmPiece.get(piece).getY());
			Utilitaires.read_Board(board);
			mvt = Verifications.possible_Moves(hmPiece, hmPiece.get(mvt.getTabMovable().get(choix_Piece_IA)), board, true);
			int choix_Pos_IA = (int) ((Math.random() * (mvt.getPossible_Moves().size())));
			position=mvt.getPossible_Moves().get(choix_Pos_IA);
			if (mvt.getEat_Moves().containsKey(position)) {
				Events.eat_Event(hmPiece.get(piece),
						mvt.getEat_Moves().get(position), board, hmPiece);
				joueur.setNbPoints(joueur.getNbPoints() + 1);
			}
			int x = position.charAt(0);
			x = x - 64;
			int y = Integer.valueOf(position.substring(1));
			System.out.println(joueur.getName() + " moved " + hmPiece.get(piece).getName() + " from " + prev_pos
					+ " to " + Utilitaires.get_Position(board, x, y));
			Events.move(hmPiece, hmPiece.get(piece), board, x, y);
			joueur.setTour(false);
		}
		Utilitaires.reinitBoard(hmPiece, board);
	}
}
