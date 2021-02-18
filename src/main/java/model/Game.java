package model;

import java.awt.Point;
import java.util.List;

import logic.MoveGenerator;
import logic.MoveLogic;


public class Game {

	private Board board;

	private boolean isP1Turn;

	private int skipIndex;
	
	public Game() {
		restart();
	}

	public Game copy() {
		Game g = new Game();
		g.board = board.copy();
		g.isP1Turn = isP1Turn;
		g.skipIndex = skipIndex;
		return g;
	}


	public void restart() {
		this.board = new Board();
		this.isP1Turn = true;
		this.skipIndex = -1;
	}

	public boolean move(Point start, Point end) {
		if (start == null || end == null) {
			return false;
		}
		return move(Board.toIndex(start), Board.toIndex(end));
	}
	

	boolean move(int startIndex, int endIndex) {

		if (!MoveLogic.isValidMove(this, startIndex, endIndex)) {
			return false;
		}

		Point middle = Board.middle(startIndex, endIndex);
		int midIndex = Board.toIndex(middle);
		board.set(endIndex, board.get(startIndex));
		board.set(midIndex, Board.EMPTY);
		board.set(startIndex, Board.EMPTY);
		Point end = Board.toPoint(endIndex);
		int id = board.get(endIndex);
		boolean switchTurn = false;
		if (end.y == 0 && id == Board.WHITE_CHECKER) {
			board.set(endIndex, Board.WHITE_KING);
			switchTurn = true;
		} else if (end.y == 7 && id == Board.BLACK_CHECKER) {
			board.set(endIndex, Board.BLACK_KING);
			switchTurn = true;
		}

		boolean midValid = Board.isValidIndex(midIndex);
		if (midValid) {
			skipIndex = endIndex;
		}
		if (!midValid || MoveGenerator.getSkips(
				board.copy(), endIndex).isEmpty()) {
			switchTurn = true;
		}
		if (switchTurn) {
			isP1Turn = !isP1Turn;
			skipIndex = -1;
		}
		
		return true;
	}

	public Board getBoard() {
		return board.copy();
	}
	

	public boolean isGameOver() {

		List<Point> black = board.find(Board.BLACK_CHECKER);
		black.addAll(board.find(Board.BLACK_KING));
		if (black.isEmpty()) {
			return true;
		}
		List<Point> white = board.find(Board.WHITE_CHECKER);
		white.addAll(board.find(Board.WHITE_KING));
		if (white.isEmpty()) {
			return true;
		}

		List<Point> test = isP1Turn? black : white;
		for (Point p : test) {
			int i = Board.toIndex(p);
			if (!MoveGenerator.getMoves(board, i).isEmpty() ||
					!MoveGenerator.getSkips(board, i).isEmpty()) {
				return false;
			}
		}

		return true;
	}
	
	public boolean isP1Turn() {
		return isP1Turn;
	}

	public int getSkipIndex() {
		return skipIndex;
	}
	

	public String getGameState() {

		StringBuilder state = new StringBuilder();
		for (int i = 0; i < 32; i ++) {
			state.append(board.get(i));
		}

		state.append(isP1Turn ? "1" : "0");
		state.append(skipIndex);
		
		return state.toString();
	}

	public void setGameState(String state) {
		restart();
		if (state == null || state.isEmpty()) {
			return;
		}
		int n = state.length();
		for (int i = 0; i < 32 && i < n; i ++) {
			try {
				int id = Integer.parseInt("" + state.charAt(i));
				this.board.set(i, id);
			} catch (NumberFormatException ignored) {}
		}

		if (n > 32) {
			this.isP1Turn = (state.charAt(32) == '1');
		}
		if (n > 33) {
			try {
				this.skipIndex = Integer.parseInt(state.substring(33));
			} catch (NumberFormatException e) {
				this.skipIndex = -1;
			}
		}
	}
}