package control;

import model.GameColor;
import model.Pos;

/**
 * Menschlicher Spieler
 * 
 * @author Harry
 * 
 */
public class PlayerHuman extends Player {

	private Pos pos;

	public PlayerHuman(String name, GameColor c) {
		super(name, c);
	}

	@Override
	public synchronized void abort() {
		notify();
	}

	@Override
	public synchronized Pos getNextMove(int[] board) {

		// System.out.println(Math.round(new Valuation(new Minimax()).getValue(
		// board, 0) * 1000) / 1000.);

		try {
			// warte auf Weckruf von GUI, also wenn Spieler auf ein Spielfeld
			// geklickt hat
			wait();
		} catch (InterruptedException e) {
			Kamisado.handleException(e,
					"Fehler beim warten auf Weckruf von GUI bei HumanPlayer!");
		}
		return pos;
	}

	@Override
	public void run() {
		// wird zur Zeit nicht genutzt
	}

	@Override
	public synchronized void setMove(int x, int y) {
		pos = new Pos(x, y);
		notify();
	}

	@Override
	public void setKILevel(int level) {
		// TODO Auto-generated method stub

	}

}
