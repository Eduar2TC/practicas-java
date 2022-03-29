package model;

import control.Kamisado;

/**
 * Feld Modell
 * 
 * @author Harry
 * 
 */
public class Field {

	private GameColor color;

	/**
	 * Figur auf diesem Feld. NULL falls keine Figur vorhanden.
	 */
	private Figure figure;

	private int posX;

	private int posY;

	public Field(GameColor color, int x, int y) {
		this.color = color;
		posX = x;
		posY = y;
	}

	public GameColor getColor() {
		return color;
	}

	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure fig) {
		figure = fig;
	}

	@Override
	public String toString() {
		// gibt Feld in der Form "B5" wieder
		return Kamisado.getPosText(posX, posY);
	}
}
