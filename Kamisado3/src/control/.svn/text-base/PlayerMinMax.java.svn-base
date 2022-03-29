package control;

import model.GameColor;
import model.Pos;

/**
 * Alpha Beta KI Spieler
 * 
 * @author Harry
 * 
 */
public class PlayerMinMax extends Player {

	public static Pos codeToPos(int pos) {
		if (pos == 0)
			return null;

		int x = pos / 10 - 1;
		int y = pos % 10 - 1;

		return new Pos(x, y);
	}

	public static int posToCode(Pos pos) {
		return (pos.getX() + 1) * 10 + pos.getY() + 1;
	}

	public synchronized static void printBoard(int[] board) {
		for (int i = 0; i < board.length; i++) {
			if (i % 10 == 0)
				Kamisado.print("");
			Kamisado.print(board[i] + "\t");
		}
		Kamisado.print("");
	}

	public static void printCoords() {
		for (int i = 0; i < 100; i++) {
			if (i % 10 == 0)
				Kamisado.print("");
			Kamisado.print(i + "\t");
		}
		Kamisado.print("");
	}

	private int[] board;
	private Thread calcMove;

	private int depth = 0;

	private Minimax mm;

	private Pos nextMove;

	public PlayerMinMax(String name, GameColor c, int depth) {
		super(name, c);
		this.depth = depth;
	}

	@Override
	public void abort() {
		if (mm != null)
			mm.abort();

		if (calcMove != null)
			try {
				calcMove.join();
			} catch (InterruptedException e) {
				Kamisado.handleException(e,
						"Fehler beim warten auf Tod von calcMove in PlayerMinimax.abort()!");
			}
	}

	@Override
	public Pos getNextMove(int[] board) {

		this.board = board;

		calcMove = new Thread(this);

		calcMove.start();

		try {
			calcMove.join();
		} catch (InterruptedException e) {
			Kamisado.handleException(e,
					"Fehler beim warten auf Beedingung von Minimax!");
		}

		return nextMove;
	}

	@Override
	public void run() {
		mm = new Minimax();

		int move = mm.minimax(board, depth);

		nextMove = codeToPos(move);

	}

	@Override
	public void setMove(int x, int y) {
		// nichts zu tun hier
	}

	@Override
	public void setKILevel(int level) {
		depth = level;
	}
}
