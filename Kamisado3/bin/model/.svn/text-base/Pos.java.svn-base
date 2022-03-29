package model;

import control.Kamisado;

/**
 * Klasse für die Übermittlung von Spielkoordinaten in einer Instanz.
 * 
 * @author Harry
 * 
 */
public class Pos {

	private double v;
	private int x;

	private int y;

	public Pos(int move, double value) {
		x = move;
		v = value;
	}

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public double getV() {
		return v;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toRawString() {
		return x + ", " + y;
	}

	@Override
	public String toString() {
		return Kamisado.getPosText(getX(), getY());
	}

}
