package szte.mi;

public class Board {
	final int XY = 8;
	protected int[][] board = new int[XY][XY];

	public void printBoard() { // feld ausgeben cmd
		for (int k = 0; k < XY; k++) {
			for (int l = 0; l < XY; l++) {
				System.out.print(board[l][k] + " ");
			}
			System.out.println();
		}
	}

	public void startPosition() { // anfangspositionen
		clearBoard();
		board[3][3] = 2;
		board[3][4] = 1;
		board[4][3] = 1;
		board[4][4] = 2;
	}

	public int[][] getBoard() {
		return board;
	}

	public int getXY() {
		return XY;
	}

	public int getBlack() { // anzahl schwarzer steine
		int m = 0;
		for (int k = 0; k < XY; k++) {
			for (int l = 0; l < XY; l++) {
				if (board[k][l] == 1) {
					m++;
				}
			}
		}
		return m;
	}

	public int getWhite() { // anzahl weisser steine
		int m = 0;
		for (int k = 0; k < XY; k++) {
			for (int l = 0; l < XY; l++) {
				if (board[k][l] == 2) {
					m++;
				}
			}
		}
		return m;
	}

	public void clearBoard() { // gesamtes board zuruecksetzen
		for (int k = 0; k < XY; k++) {
			for (int l = 0; l < XY; l++) {
				board[k][l] = 0;
			}
		}
	}

	public int getWinner() {
		if (getWhite() < getBlack()) {
			return 1;
		} else if (getWhite() == getBlack()) {
			return 3;
		} else
			return 2;
	}

}
