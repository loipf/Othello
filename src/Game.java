package szte.mi;

import java.util.ArrayList;

public class Game extends Board {

	private int player;
	public ArrayList<Integer> moves = new ArrayList<Integer>();

	public Game() {
		startPosition();
		player = 1; // schwarz = 1 beginnt
	}

	public void turnP() { // spielerwechsel
		if (player == 1) {
			player = 2; // weiss = 2
		} else
			player = 1;
	}

	public int getPlayer() {
		return player;
	}
	public int setPlayer() {
		return player;
	}

	public boolean movePossible(Move move, int who) {
		int foe = 2;
		int pla = 1;
		if (who == 2) {
			foe = 1;
			pla = 2;
		}

		moves.clear(); // moves leeren
		if (board[move.x][move.y] != 0) { // bereits besetzt
			return false;
		}
		if (move.x < XY - 2 && board[move.x + 1][move.y] == foe && board[move.x + 2][move.y] != 0) {
			for (int k = move.x + 2; k < XY; k++) { // horizontal rechts
				if (board[k][move.y] == pla) {
					moves.add(1);
				}
			}

		}
		if (move.x > 1 && board[move.x - 1][move.y] == foe && board[move.x - 2][move.y] != 0) {
			for (int k = move.x - 2; k >= 0; k--) { // horizontal links
				if (board[k][move.y] == pla) {
					moves.add(2);
				}
			}
		}
		if (move.y < XY - 2 && board[move.x][move.y + 1] == foe && board[move.x][move.y + 2] != 0) {
			for (int k = move.y + 2; k < XY; k++) { // senkrecht unten
				if (board[move.x][k] == pla) {
					moves.add(3);
				}
			}
		}
		if (move.y > 1 && board[move.x][move.y - 1] == foe && board[move.x][move.y - 2] != 0) {
			for (int k = move.y - 2; k >= 0; k--) { // senkrecht oben
				if (board[move.x][k] == pla) {
					moves.add(4);
				}
			}
		}
		if (move.x < XY - 2 && move.y < XY - 2 && board[move.x + 1][move.y + 1] == foe
				&& board[move.x + 2][move.y + 2] != 0) {
			for (int k = move.x + 2, l = move.y + 2; k < XY && l < XY; k++, l++) { // diagonal
																					// rechts
																					// unten
				if (board[k][l] == pla) {
					moves.add(5);
				}
			}
		}
		if (move.x < XY - 2 && move.y > 1 && board[move.x + 1][move.y - 1] == foe && board[move.x + 2][move.y - 2] != 0) {
			for (int k = move.x + 2, l = move.y - 2; k < XY && l >= 0; k++, l--) { // diagonal
																					// rechts
																					// oben
				if (board[k][l] == pla) {
					moves.add(6);
				}
			}
		}
		if (move.x > 1 && move.y > 2 && board[move.x - 1][move.y - 1] == foe && board[move.x - 2][move.y - 2] != 0) {
			for (int k = move.x - 2, l = move.y - 2; k >= 0 && l >= 0; k--, l--) { // diagonal
																					// links
																					// oben
				if (board[k][l] == pla) {
					moves.add(7);
				}
			}
		}
		if (move.x > 1 && move.y < XY - 2 && board[move.x - 1][move.y + 1] == foe && board[move.x - 2][move.y + 2] != 0) {
			for (int k = move.x - 2, l = move.y + 2; k >= 0 && l < XY; k--, l++) { // diagonal
																					// links
																					// unten
				if (board[k][l] == pla) {
					moves.add(8);
				}
			}
		}

		if (moves.isEmpty() == true) {
			return false;
		} else
			return true;

	}

	public boolean makeMove(Move move, int player) { // zug wird durchgeführt
		if(move==null) {return false;}
		move = new Move(move.y, move.x); // wechsel x und y sodass [x][y]
		if (movePossible(move, player)) {
			board[move.x][move.y] = player; // move setzen
			for (int nr : moves) { // steine aendern
				changeColor(move, nr, player);
			}
//			System.out.println("wechsel zu"+player);
//			turnP(); // spielerwechsel nach erfolgreichen move
			return true;
		} else
			System.out.println("falsch");
			return false;
	}

	public int numberOfMovesPossible(int who) { // anzahl moeglicher zuege
		int re = 0;
		for (int k = 0; k < XY; k++) {
			for (int l = 0; l < XY; l++) {
				if (movePossible(new Move(l, k), who) == true) {
					re++;
				}
			}
		}
		return re;
	}

	public void changeColor(Move m, int a, int player) { // aendert farbe in richung a
		int va = 0; // hilfsvariablen
		int vb = 0;

		switch (a) {
		case 1: // -r
			va = m.x + 1;
			while (board[va][m.y] != player) {
				board[va][m.y] = player;
				va++;
			}
			break;
		case 2: // -l
			va = m.x - 1;
			while (board[va][m.y] != player) {
				board[va][m.y] = player;
				va--;
			}
			break;
		case 3: // |u
			va = m.y + 1;
			while (board[m.x][va] != player) {
				board[m.x][va] = player;
				va++;
			}
			break;
		case 4: // |o
			va = m.y - 1;
			while (board[m.x][va] != player) {
				board[m.x][va] = player;
				va--;
			}
			break;
		case 5: // \ru
			va = m.x + 1;
			vb = m.y + 1;
			while (board[va][vb] != player) {
				board[va][vb] = player;
				va++;
				vb++;
			}
			break;
		case 6: // /ro
			va = m.x + 1;
			vb = m.y - 1;
			while (board[va][vb] != player) {
				board[va][vb] = player;
				va++;
				vb--;
			}
			break;
		case 7: // \lo
			va = m.x - 1;
			vb = m.y - 1;
			while (board[va][vb] != player) {
				board[va][vb] = player;
				va--;
				vb--;
			}
			break;
		case 8: // /lu
			va = m.x - 1;
			vb = m.y + 1;
			while (board[va][vb] != player) {
				board[va][vb] = player;
				va--;
				vb++;
			}
			break;
		}

	}

	public boolean gameOver() {
		int foe = 2;
		if (player == 2) {
			foe = 2;
		}
		if (numberOfMovesPossible(player) == 0 && numberOfMovesPossible(foe) == 0) {
			return true;
		}
		return false;
	}

	public int getChanges(Move move, int p) {	// gibt anzahl farbwechsel zurueck
		movePossible(move, p);
		return countChanges(move,moves);
	}

	public int countChanges(Move m, ArrayList<Integer> list) { // zaehlt farbwechsel
		int va = 0; // hilfsvariablen
		int vb = 0;
		int counter = 0;
		for (int a : list) {
			switch (a) {
			case 1: // -r
				va = m.x + 1;
				while (board[va][m.y] != player) {
					counter++;
					va++;
				}
				break;
			case 2: // -l
				va = m.x - 1;
				while (board[va][m.y] != player) {
					counter++;
					va--;
				}
				break;
			case 3: // |u
				va = m.y + 1;
				while (board[m.x][va] != player) {
					counter++;
					va++;
				}
				break;
			case 4: // |o
				va = m.y - 1;
				while (board[m.x][va] != player) {
					counter++;
					va--;
				}
				break;
			case 5: // \ru
				va = m.x + 1;
				vb = m.y + 1;
				while (board[va][vb] != player) {
					counter++;
					va++;
					vb++;
				}
				break;
			case 6: // /ro
				va = m.x + 1;
				vb = m.y - 1;
				while (board[va][vb] != player) {
					counter++;
					va++;
					vb--;
				}
				break;
			case 7: // \lo
				va = m.x - 1;
				vb = m.y - 1;
				while (board[va][vb] != player) {
					counter++;
					va--;
					vb--;
				}
				break;
			case 8: // /lu
				va = m.x - 1;
				vb = m.y + 1;
				while (board[va][vb] != player) {
					counter++;
					va--;
					vb++;
				}
				break;
			}
		}

		return counter;

	}

}
