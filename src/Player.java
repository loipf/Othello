package szte.mi;

import java.util.Random;

public abstract interface Player {
	public abstract void init(int order, long t, Random rnd);

	public abstract Move nextMove(Move prevMove, long tOpponent, long t);
}

// order: 0: spieler zieht erster
// 1: spieler zieht zweiter
// t: verbleibende laufzeit
// rnd: zufallsgenerator
//
// prevMove: vorhergehender zug des gegners
// tOpponent: verbleibende zeit vom gegner
// t: verbleibende Zeit von diesem Spieler
// return: zug des spielers

