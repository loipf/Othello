package szte.mi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Gui extends JFrame implements ActionListener {

	protected CustomButton[][] buttons; // neuer extra Buttons
	private Runner r;
	final int XY = 8;
	private Dimension frameSize;
	private JLabel textP; // anzeige player
	private JLabel textS; // anzeige schwarzer steine
	private JLabel textW; // anzeige weisser steine
	public boolean actionMade;

	public Gui(Runner r) {
		super("Othello");
		this.r = r;

		GridLayout grid = new GridLayout(XY + 1, XY); // layout
		setLayout(grid);

		buttons = new CustomButton[XY][XY];
		for (int k = 0; k < XY; k++) {
			for (int l = 0; l < XY; l++) {
				CustomButton b = new CustomButton();
				b.setBackground(Color.ORANGE);
				b.addActionListener(this);
				b.setActionCommand(String.valueOf(l) + String.valueOf(k));
				b.setPreferredSize(new Dimension(50, 50));
				add(b);
				buttons[l][k] = b;
			}
		}
		textP = new JLabel(""); // anzeige spieler
		add(textP);
		JLabel free = new JLabel(""); // platzhalter
		add(free);
		textS = new JLabel(""); // anzeige schwarz
		add(textS);
		JLabel free2 = new JLabel(""); // platzhalter
		add(free2);
		textW = new JLabel(""); // anzeg spieler 2
		add(textW);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setFrameSize(new Dimension(getSize().width, getSize().height));
		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent e) {
		String m = e.getActionCommand();
		int y = Integer.parseInt(m.substring(0, 1));
		int x = Integer.parseInt(m.substring(1, 2));

		r.aktMove = new Move(x, y); // weitergabe an runner
		actionMade = true;
	}

	public Dimension getFrameSize() {
		return frameSize;
	}

	public void setFrameSize(Dimension frameSize) {
		this.frameSize = frameSize;
	}

	public void setTextN(int p, int b, int w) {
		if (p == 1) {
			textP.setText(" BLACK");
		} else
			textP.setText(" WHITE");
		textS.setText("Black " + b);
		textW.setText("White " + w);
	}

	public void updateGui(Game b) {
		for (int k = 0; k < b.getXY(); k++) {
			for (int l = 0; l < b.getXY(); l++) {
				buttons[l][k].setPlayer(b.getBoard()[l][k]);
			}
		}
		setTextN(b.getPlayer(), b.getBlack(), b.getWhite());
		repaint();
	}

	public void showGameDialog(int a) {
		if (a == 1) {
			JOptionPane.showMessageDialog(this, "Spieler 1 gewinnt!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		if (a == 2) {
			JOptionPane.showMessageDialog(this, "Spieler 2 gewinnt!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		if (a == 3) {
			JOptionPane.showMessageDialog(this, "Unentschieden!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}

	}

}

class CustomButton extends JButton {
	private int player = 0;

	public CustomButton() {
		super();
	}

	public void setPlayer(int p) {
		player = p;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Graphics2D g2 = (Graphics2D) g;
		int sH = getSize().height;
		int sW = getSize().width;

		if (player == 1) {
			g.setColor(Color.BLACK);
			g.fillOval((int) (sH - sH * 0.95), (int) (sW - sW * 0.95), (int) (sW * 0.9), (int) (sH * 0.9));
		}
		if (player == 2) {
			g.setColor(Color.WHITE);
			g.fillOval((int) (sH - sH * 0.95), (int) (sW - sW * 0.95), (int) (sW * 0.9), (int) (sH * 0.9));
		}
	}

}
