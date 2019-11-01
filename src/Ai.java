package szte.mi;

import java.util.ArrayList;
import java.util.Random;



public class Ai implements Player {

//	private int[][] b; // board b
	public Game g;
	private Random random;
	private int player; // ai spielernummer
	private int foe; // gegner spielernummer
	private long timeLeft;

	public Ai() {
	}

	@Override
	public void init(int order, long t, Random rnd) {
		if (order == 0) {
			player = 1;
			foe = 2;
		} else if (order == 1) {
			player = 2;
			foe = 1;
		}
		random = rnd;
		timeLeft = t;

		g = new Game();
//		g.startPosition();
//		b = g.getBoard();
	}

	@Override
	public Move nextMove(Move prevMove, long tOpponent, long t) {
		long time = System.currentTimeMillis();
		g.makeMove(prevMove,foe);
		
		ArrayList<Move> m = getPossibleMoves();
		if (m.size()==0) {
			timeLeft = timeLeft - (System.currentTimeMillis() - time);
//			System.out.println("no possiblitly");
			return null;
		}
//		printMoves();
		
		Move now = returnBest(m);
		now = new Move(now.y,now.x); 	// wechsel 
		
		g.makeMove(now,player);
		timeLeft = timeLeft - (System.currentTimeMillis() - time);
		return now;

	}

	public ArrayList<Move> getPossibleMoves() { // alle moeglichen zuege
		ArrayList<Move> m = new ArrayList<Move>();
		for (int k = 0; k < g.XY; k++) {
			for (int l = 0; l < g.XY; l++) {
				if (g.movePossible(new Move(l, k), this.player)) {
					m.add(new Move(l, k));
				}
			}
		}
		return m;
	}
	
	public void printMoves() {
		ArrayList<Move> a = getPossibleMoves();
		for(Move m : a) {
			System.out.print("move:"+m.x+" "+m.y+", ");
		}
	}

	public void printBoard() {
		g.printBoard();
	}
	
	public int getPlayer() {
		return player;
	}
	public int getFoe(){
		return foe;
	}

	public Move returnBest(ArrayList<Move> m) {
		int[] list = new int[m.size()];
		int count = 0;
		for (Move move : m) { // bewertet jeden Move
			int points = 0;
			if (atCorner(move)) {
				points += 10;
			}

			points += g.getChanges(move, player);

			if (nextToCorner(move)) {
				points=-1;
			}
			list[count] = points;
			count++;
		}
		
		int max = Integer.MIN_VALUE;	// stelle mit max herausfinden
		int position = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i] > max) {
				max = list[i];
				position = i;
			}
		}
//		System.out.println("make move:"+m.get(position).x+" "+m.get(position).y);
		return m.get(position);
	}

	public boolean atCorner(Move move) {
		if (move.x == 0 && move.y == 0 || move.x == 7 && move.y == 0 || move.x == 0 && move.y == 7 || move.x == 7
				&& move.y == 7) {
			return true;
		}
		return false;
	}

	public boolean nextToCorner(Move move) {
		if (move.x == 1 && (move.y == 0 || move.y == 7) || move.x == 6 && (move.y == 0 || move.y == 7) || move.y == 1
				&& (move.x == 0 || move.x == 7) || move.y == 6 && (move.x == 0 || move.x == 7)) {
			return true;
		}
		return false;
	}

}
