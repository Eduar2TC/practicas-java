package model;

import control.Player;

/**
 * Figur Modell
 * 
 * @author Harry
 * 
 */
public class Figure {

	private GameColor color;

	private boolean mark;

	private Player player;

	public Figure(GameColor color, Player player) {
		this.color = color;
		this.player = player;
	}

	/**
	 * Gibt Farbcode für Figur wieder.
	 * 
	 * @return Weis 1-8, Schwarz 9-16.
	 */
	public int getCode() {
		if (player.isWhitePlayer())
			return getColor().getCode();
		else
			return 8 + getColor().getCode();
	}

	public GameColor getColor() {
		return color;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isMarked() {
		return mark;
	}

	public void setMark(boolean b) {
		mark = b;
	}

	@Override
	public String toString() {
		// gibt Name der Farbe der Figur in gekürzter Form wieder (die ersten
		// beiden Buchstaben)
		return "" + color;
	}

}
