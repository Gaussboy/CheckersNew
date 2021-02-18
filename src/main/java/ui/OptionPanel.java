package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.ComputerPlayer;
import model.HumanPlayer;
import model.Player;


class OptionPanel extends JPanel {

	private CheckersWindow window;

	private JButton helpBtn;

	private JButton restartBtn;

	private JComboBox<String> player1Opts;

	private JComboBox<String> player2Opts;


	OptionPanel(CheckersWindow window) {
		super(new GridLayout(0, 1));

		this.window = window;

		OptionListener ol = new OptionListener();
		final String[] playerTypeOpts = {"Human", "Computer"};
		helpBtn = new JButton("Help");
		restartBtn = new JButton("Restart");
		player1Opts = new JComboBox<>(playerTypeOpts);
		player2Opts = new JComboBox<>(playerTypeOpts);
		restartBtn.addActionListener(ol);
		helpBtn.addActionListener(ol);
		player1Opts.addActionListener(ol);
		player2Opts.addActionListener(ol);
		JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel middle = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top.add(helpBtn);
		top.add(restartBtn);
		middle.add(new JLabel("(black) Player 1: "));
		middle.add(player1Opts);
		bottom.add(new JLabel("(white) Player 2: "));
		bottom.add(player2Opts);
		add(top);
		add(middle);
		add(bottom);
	}

	private static Player getPlayer(JComboBox<String> playerOpts) {

		Player player = new HumanPlayer();
		if (playerOpts == null) {
			return player;
		}

		String type = "" + playerOpts.getSelectedItem();
		if (type.equals("Computer")) {
			player = new ComputerPlayer();
		}

		return player;
	}


	private class OptionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (window == null) {
				return;
			}

			Object src = e.getSource();

			if (src == restartBtn) {
				window.restart();
				window.getBoard();
			} else if (src == player1Opts) {
				Player player = getPlayer(player1Opts);
				window.setPlayer1(player);
			} else if (src == player2Opts) {
				Player player = getPlayer(player2Opts);
				window.setPlayer2(player);
			} else if (src == helpBtn) {
				JOptionPane.showMessageDialog(null, "1.\tCheckers can only move diagonally, on dark tiles.\n" +
						"  \n" +
						"  2.\tNormal checkers can only move forward diagonally (for black checkers,\n" +
						"  \t\tthis is down and for white checkers, this is up).\n" +
						"  \n" +
						"  3.\tA checker becomes a king when it reaches the opponents end and cannot\n" +
						"  \t\tmove forward anymore.\n" +
						"  \n" +
						"  4.\tOnce a checker becomes a king, the player's turn is over.\n" +
						"  \n" +
						"  5.\tAfter a checker/king moves one space diagonally, the player's turn is\n" +
						"  \t\tover.\n" +
						"  \n" +
						"  6.\tIf an opponent's checker/king can be skipped, it must be skipped.\n" +
						"  \n" +
						"  7.\tIf after a skip, the same checker can skip again, it must. Otherwise,\n" +
						"  \t\tthe turn is over.\n" +
						"  \n" +
						"  8.\tThe game is over if a player either has no more checkers or cannot make\n" +
						"  \t\ta move on their turn.\n" +
						"  \n" +
						"  9.\tThe player with the black checkers moves first.");
			}
		}
	}
}