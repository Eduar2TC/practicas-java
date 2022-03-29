package control;

import model.GameColor;
import model.Pos;

/**
 * Abstrakte Klasse für Spieler zur Festlegung von minimal notwendigen Methoden.
 * Alle anderen Spielerklassen erben von dieser Klasse.
 * 
 * @author Harry
 * 
 */
public abstract class Player implements Runnable {

	private GameColor color;
	private String name;	
	

	public Player(String name, GameColor c) {		
		this.name = name;
		color = c;
	}

	public abstract void abort();

	public GameColor getColor() {
		return color;
	}

	public abstract Pos getNextMove(int[] board);

	public String getPlayerName() {
		return name;
	}

	public boolean isWhitePlayer() {
		if (color.getCode() == 1)
			return true;
		else
			return false;
	}

	public abstract void run();

	public abstract void setMove(int x, int y);

	@Override
	public String toString() {
		return "" + color;
	}

	public abstract void setKILevel(int level);

}
