package ui;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Player;



class CheckersWindow extends JFrame {

	private static final long serialVersionUID = 8782122389400590079L;

	private static final int DEFAULT_WIDTH = 500;

	private static final int DEFAULT_HEIGHT = 600;

	private static final String DEFAULT_TITLE = "MY CHECKERS";

	private CheckerBoard board;


	CheckersWindow() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_TITLE);
	}

	private CheckersWindow(int width, int height, String title) {

		super(title);
		super.setSize(width, height);
		super.setLocationByPlatform(true);

		JPanel layout = new JPanel(new BorderLayout());
		this.board = new CheckerBoard(this);
		OptionPanel opts = new OptionPanel(this);
		layout.add(board, BorderLayout.CENTER);
		layout.add(opts, BorderLayout.SOUTH);
		this.add(layout);
	}
	
	void getBoard() {
	}

	void setPlayer1(Player player1) {
		this.board.setPlayer1(player1);
		this.board.update();
	}

	void setPlayer2(Player player2) {
		this.board.setPlayer2(player2);
		this.board.update();
	}
	
	void restart() {
		this.board.getGame().restart();
		this.board.update();
	}

}
