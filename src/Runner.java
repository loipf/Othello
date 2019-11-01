package szte.mi;

import java.util.Random;

import javax.swing.JOptionPane;

public class Runner {

	Gui gui;
	Ai ai;
	Random random = new Random();
	Move aktMove = null;

	public Runner(int pla) {
		gui = new Gui(this);
		ai = new Ai();
		ai.init(pla, 8000, random);
		gui.updateGui(ai.g); // anfangsposition

		if (pla == 0) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			moveKI(null);
			ai.g.turnP();
		}

		while (ai.g.gameOver() != true) {
			gui.updateGui(ai.g);
			if (gui.actionMade == true && moveHuman(aktMove)) {
				ai.g.turnP();
				moveKI(aktMove);
				ai.g.turnP();
//				ai.g.printBoard();
			}
			gui.actionMade = false;
			if (ai.g.numberOfMovesPossible(ai.getFoe()) == 0) {
				ai.g.turnP();
				moveKI(aktMove);
				ai.g.turnP();
			}
		}
		gui.showGameDialog(ai.g.getWinner());

	}

	public static void main(String[] args) {
		int i = startFrame();
		if(i>=0) {
			new Runner(i);
		}
	}

	public boolean moveHuman(Move m) { 
		if (ai.g.movePossible(new Move(m.y, m.x), ai.getFoe())) {
			return true;
		}
		return false;
	}

	public void moveKI(Move pre) {
		ai.nextMove(pre, 8000, 8000);
		gui.updateGui(ai.g);
	}

	public static int startFrame() {
		Object[] options = { "ComputerKI", "ich" };
		int selected =-1; 
		selected=JOptionPane.showOptionDialog(null, "Wer soll beginnen? \n (schwarz beginnt)", "Start",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		return selected;
	}

}
