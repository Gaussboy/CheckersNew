package model;

import java.awt.Point;

public class Move {
	

	static final double WEIGHT_INVALID = Double.NEGATIVE_INFINITY;

	private byte startIndex;

	private byte endIndex;

	private double weight;
	
	Move(int startIndex, int endIndex) {
		setStartIndex(startIndex);
		setEndIndex(endIndex);
	}

	int getStartIndex() {
		return startIndex;
	}
	
	private void setStartIndex(int startIndex) {
		this.startIndex = (byte) startIndex;
	}
	
	int getEndIndex() {
		return endIndex;
	}
	
	private void setEndIndex(int endIndex) {
		this.endIndex = (byte) endIndex;
	}
	
	Point getStart() {
		return Board.toPoint(startIndex);
	}

	Point getEnd() {
		return Board.toPoint(endIndex);
	}

	double getWeight() {
		return weight;
	}

	void setWeight(double weight) {
		this.weight = weight;
	}
	
	void changeWeight(double delta) {
		this.weight += delta;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[startIndex=" + startIndex + ", "
				+ "endIndex=" + endIndex + ", weight=" + weight + "]";
	}
}
