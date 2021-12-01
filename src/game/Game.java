package game;

import java.util.ArrayList;
import java.util.HashMap;

import events.IA;
import events.Round;
import events.Verifications;
import model.Match;
import model.Piece;
import utilitaires.Menu;
import utilitaires.Utilitaires;

public class Game {
	Match match = Menu.menu_Principale();
	String[][] board = new String[match.getSize()][match.getSize()];
	ArrayList<Piece> alP = new ArrayList<Piece>();
	HashMap<String, Piece> hmP = new HashMap<String, Piece>();

	public void game() {
		Utilitaires.draw_Board(board);
		Utilitaires.add_Pieces_To_List(alP, board.length);
		Utilitaires.add_Pieces_To_Board(alP, hmP, board);
		while (!match.getJ1().isLost() && !match.getJ2().isLost()) {
			if (match.getJ1().isTour()) {
				Round.round(hmP, board, match.getJ1());
				match.getJ2().setTour(true);
			} else {
				if(match.getJ2().isControlled()) {
					Round.round(hmP, board, match.getJ2());
					
				}else {
					IA.round_IA(hmP, board, match.getJ2());
				}
				match.getJ1().setTour(true);
			}
			match.getJ1().setNbPieces(Utilitaires.get_Nb_Pieces(hmP, match.getJ1().getCouleur()));
			match.getJ2().setNbPieces(Utilitaires.get_Nb_Pieces(hmP, match.getJ2().getCouleur()));
			Verifications.verif_If_Player_Lost(match.getJ1());
			Verifications.verif_If_Player_Lost(match.getJ2());

		}
		if (match.getJ1().isLost()) {
			System.out.println(match.getJ2().getName() + " IS THE WINNER!!!");
		} else {
			System.out.println(match.getJ1().getName() + " IS THE WINNER!!!");
		}

	}
}
