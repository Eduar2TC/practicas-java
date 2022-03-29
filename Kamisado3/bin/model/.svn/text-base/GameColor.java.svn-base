package model;

import java.awt.Color;

/**
 * Spezielle Implementierung von Farbe mit Name und Code
 * 
 * @author Harry
 * 
 */
public class GameColor extends Color {

	private static final long serialVersionUID = 1L;

	/**
	 * Farbcode von 1-8.
	 */
	private int code;

	/**
	 * Name von Orange bis Braun
	 */
	private String name;

	public GameColor(Color c, String s, int i) {
		super(c.getRGB());
		name = s;
		code = i;
	}

	public GameColor(int r, int g, int b, String s, int i) {
		super(r, g, b);
		name = s;
		code = i;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return name.substring(0, 3);
	}

}
