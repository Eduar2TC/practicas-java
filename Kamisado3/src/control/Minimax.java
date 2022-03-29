package control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Pos;

/**
 * Wichtigste Klasse für die KI. Implementation der Alpha Beta Suche mit
 * Zugsortierung bei vorgegebener maximaler Suchtiefe.
 * 
 * @author Harry
 * 
 */
public class Minimax {

	private final static int[] COLOR_PATTERN = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 0, 6, 1, 4, 7, 2, 5, 8, 3, 0, 0, 7,
			4, 1, 6, 3, 8, 5, 2, 0, 0, 4, 3, 2, 1, 8, 7, 6, 5, 0, 0, 5, 6, 7,
			8, 1, 2, 3, 4, 0, 0, 2, 5, 8, 3, 6, 1, 4, 7, 0, 0, 3, 8, 5, 2, 7,
			4, 1, 6, 0, 0, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0 };

	public final static int ERROR = -1;

	/**
	 * Position der auf dem Board ( int[] ), wo die Position der markierten
	 * Figur steht. Also board[MARK] ergibt Position der markierten Figur und
	 * board[board[MARK]] ergibt markierte Figur.
	 */
	public final static int MARK = 100;

	/**
	 * Festlegung von positiv unendlich auf +10.
	 */
	private final static int MAX = 10;

	/**
	 * Festlegung von negativ unendlich auf -10.
	 */
	private final static int MIN = -10;

	public final static int NO_MARK = -1;

	public final static int PLAYER_BLACK = 2;

	public final static int PLAYER_WHITE = 1;

	public final static int POS_FREE = 0;

	private final static int WIN_BLACK = -1;

	private final static int WIN_NULL = 0;

	private final static int WIN_WHITE = 1;

	private boolean abort = false;

	/**
	 * Anzahl aller minValue() und maxValue() Aufrufe insgesamt.
	 */
	private int counter = 0;

	/**
	 * Anzahl Alpha Cuts.
	 */
	private int cutAlpha = 0;

	/**
	 * Anzahl Beta Cuts.
	 */
	private int cutBeta = 0;

	private int cutDepth = 0;

	private int cutEmpty = 0;

	private int cutLoop = 0;

	private int cutMax = 0;

	private int cutMin = 0;

	private double cutSum = 0;

	private int cutWin = 0;

	private double maxDepth = 0;

	private List<Pos> ratings = new ArrayList<Pos>();

	public void abort() {
		abort = true;
	}

	/**
	 * Führt nächsten Schritt aus, falls markierte Figur geblockt ist. Bei
	 * einfachem Block, geht es weiter mit Min oder Max. Bei einem Blockzyklus
	 * wird der Gewinner zurückgegeben.
	 * 
	 * @param board
	 *            aktueller Status
	 * @param alpha
	 *            Alpha Wert
	 * @param beta
	 *            Beta Wert
	 * @param depth
	 *            Maximale restliche Suchtiefe
	 * @param blockCycleFig
	 *            Letzte geblockte Figur. Null, falls im Zug davor die zu
	 *            ziehende Figur nicht geblockt war.
	 * @return Bewertung des Spielausgangs nach Alpha Beta Suche.
	 */
	private double blocked(int[] board, double alpha, double beta, int depth,
			int blockCycleFig) {

		int markFig = board[board[MARK]];

		// Falls Blockzyklus komplett
		if (blockCycleFig == markFig) {

			cutLoop++;

			return getWinValue(board, depth);

			// Falls erste geblockte Figur, also davor kein Zugblock
		} else if (blockCycleFig == 0) {
			// Merke geblockte Figur, um Blockzyklus erkennen zu können
			blockCycleFig = markFig;
		}

		cutEmpty++;

		// Spezielle Aktualisierung der Markierung, da keine Figur bewegt.
		updateMarkAfterBlock(board);

		// weiter mit Alpha Beta
		if (isWhiteFig(markFig))
			return minValue(board, alpha, beta, depth - 1, blockCycleFig);
		else
			return maxValue(board, alpha, beta, depth - 1, blockCycleFig);

	}

	/**
	 * Debug Funktion: Überprüft gesamte Anzahl aller Methodenaufrufe.
	 * 
	 * @return Wahr, falls maximale Anzahl Aufrufe überschritten, sonst Falsch.
	 */
	private boolean checkCounter() {
		counter++;
		if (counter < 0) {
			// counter = 0;
			Kamisado.control.pauseGame();
			return true;
		} else
			return false;
	}

	/**
	 * Führt einen Zug aus, aktualisiert die Markierung und gibt neuen Status
	 * zurück.
	 * 
	 * @param board
	 *            Alter Status
	 * @param move
	 *            Auszuführender Zug
	 * @return Neuer Status
	 */
	private int[] doMove(int[] board, int move) {

		// Merke Spieler, der dran ist, um Markierung danach auf anderen Spieler
		// zu setzen.
		int player;

		if (isWhiteFig(board[board[MARK]]))
			player = PLAYER_BLACK;
		else
			player = PLAYER_WHITE;

		// Erzeuge Kopie vom alten Status
		int[] result = board.clone();

		// Figur zum Ziel schieben
		result[move] = result[result[MARK]];
		result[result[MARK]] = 0;

		// Markierung aktualisieren
		result[MARK] = getPosByFig(board, getFig(getColByPos(move), player));

		return result;
	}

	private int firstMark(int[] board, int depth) {

		double bestValue = MIN;
		int bestMark = 81;

		List<Runner> runs = new ArrayList<Runner>();

		for (int pos = 81; pos <= 88; pos++) {

			if (abort)
				return 0;

			int[] markedBoard = board.clone();

			markedBoard[MARK] = pos;

			Runner r = new Runner(markedBoard, pos, depth, true);
			r.start();
			runs.add(r);

		}

		for (Runner run : runs) {

			try {
				run.join();
			} catch (InterruptedException e) {
				Kamisado.handleException(e, "Join Runner");
			}

			double temp = run.getValue();

			if (temp >= bestValue) {
				bestValue = temp;
				bestMark = run.getMove();
			}
		}

		return bestMark;
	}

	/**
	 * Methode die zuerst aufgerufen wird, falls bester Zug für Spieler Weiss
	 * gesucht wird. Im gegensatz zu maxValue() wird hier der Zug zur Bewertung
	 * gemerkt, da dieser dann zurückgegeben werden muss.
	 * 
	 * @param board
	 *            Aktueller Status
	 * @param depth
	 *            Resttiefe
	 * @return Bester Zug nach Alpha Beta Suche
	 */
	private int firstMax(int[] board, int depth) {

		List<Integer> moves = getAllMovesSorted(board);

		double bestValue = MIN;
		int bestMove = 0;

		List<Runner> runs = new ArrayList<Runner>();

		for (int move : moves) {

			if (abort)
				return 0;

			Runner r = new Runner(doMove(board, move), move, depth, false);
			r.start();
			runs.add(r);

		}

		for (Runner run : runs) {

			try {
				run.join();
			} catch (InterruptedException e) {
				Kamisado.handleException(e, "Join Runner");
			}

			double temp = run.getValue();

			if (temp >= bestValue) {
				bestValue = temp;
				bestMove = run.getMove();
			}
		}

		return bestMove;

	}

	/**
	 * Wie firstMax() nur für Schwarzen Spieler.
	 * 
	 * @param board
	 *            Aktueller Status.
	 * @param depth
	 *            Restsuchtiefe.
	 * @return Bester Zug für Schwarz.
	 */
	private int firstMin(int[] board, int depth) {

		List<Integer> moves = getAllMovesSorted(board);

		double bestValue = MAX;
		int bestMove = 0;

		List<Runner> runs = new ArrayList<Runner>();

		for (int move : moves) {

			if (abort)
				return 0;

			Runner r = new Runner(doMove(board, move), move, depth, true);
			r.start();
			runs.add(r);

		}

		for (Runner run : runs) {

			try {
				run.join();
			} catch (InterruptedException e) {
				Kamisado.handleException(e, "Join Runner");
			}

			double temp = run.getValue();

			if (temp <= bestValue) {
				bestValue = temp;
				bestMove = run.getMove();
			}
		}

		return bestMove;

	}

	public class Runner extends Thread {

		private int[] board;
		private int move;
		private int depth;
		private boolean isMax;
		private double value;

		public Runner(int[] board, int move, int depth, boolean isMax) {
			setPriority(MIN_PRIORITY);
			// yield();
			this.move = move;
			this.isMax = isMax;
			this.board = board;
			this.move = move;
			this.depth = depth;
		}

		@Override
		public void run() {
			if (isMax)
				value = maxValue(board, MIN, MAX, depth, 0);
			else
				value = minValue(board, MIN, MAX, depth, 0);

			updateRating(move, value);
		}

		public double getValue() {
			return value;
		}

		public int getMove() {
			return move;
		}
	}

	private List<Integer> getAllMarkMoves(int[] board) {
		int markPos = board[MARK];
		return getAllMoves(board, markPos);
	}

	public List<Integer> getAllMoves(int[] board, int pos) {
		List<Integer> moves = new ArrayList<Integer>();

		int fig = board[pos];

		int horDir = 1;

		// Falls Weiss dran, dann ziehe nach Norden.
		if (isWhiteFig(fig)) {
			horDir = -1;
		}

		// Züge für Weiss
		// links oben -11
		// oben -10
		// rechts oben -9

		// für Schwarz
		// links unten +9
		// unten +10
		// unten rechts +11

		// für alle Richtungen
		for (int verDir = 9; verDir <= 11; verDir++) {
			// füge alle zulässigen Züge in jeweilige Richtung hinzu
			moves.addAll(getMoves(board, horDir * verDir, pos));
		}

		return moves;
	}

	/**
	 * Ermittelt alle regelkonformen Züge und sortiert nach Entfernung zur
	 * gegnerischen Grundlinie. Weite Züge vor nahen Zügen. Sortierung mit
	 * stabilem Mergesort.
	 * 
	 * @param board
	 *            Aktueller Status.
	 * @return Sortierte Liste aller zulässigen Züge.
	 */
	private List<Integer> getAllMovesSorted(int[] board) {

		List<Integer> moves = getAllMarkMoves(board);

		// sortiere nach Entferung zur gegnerischen Grundlinie
		sortMoves(moves, getTurn(board));

		return moves;
	}

	/**
	 * Gibt Farbe einer Position auf dem Spielbrett wieder
	 * 
	 * @param pos
	 *            Position auf dem Spielbrett
	 * @return Farbcode des Spielfeldes auf gegebener Position
	 */
	private int getColByPos(int pos) {
		return COLOR_PATTERN[pos];
	}

	/**
	 * Gibt geschätzten Wert eines Zustands wieder, der kein Sieg und keine
	 * Niederlage ist. Wird nach einem depthCut aufgerufen.
	 * 
	 * @param board
	 *            Zu bewertetender Zustand.
	 * @param depth
	 *            Resttiefe
	 * @return Bewertung x des gegebenen Zustands. Für x gilt: -1 < x < 1.
	 */
	private double getCutValue(int[] board, int depth) {

		return new Valuation(this).getValue(board, depth);

	}

	/**
	 * Gibt Figur mit bestimmter Farbe und Spieler wieder. Weisse Figuren sind 1
	 * bis 8, schwarze Figuren 9 bis 16.
	 * 
	 * @param color
	 *            Farbcode der Figur
	 * @param player
	 *            Spieldercode
	 * @return Figurcode
	 */
	private int getFig(int color, int player) {
		if (player == PLAYER_WHITE)
			return color;
		else
			return color + 8;
	}

	private List<Integer> getMoves(int[] board, int dir, int pos) {
		List<Integer> moves = new ArrayList<Integer>();

		int step = 1;

		// solange Position in Richtung frei ist, füge hinzu und gehe einen
		// Schritt weiter
		while (board[pos + step * dir] == POS_FREE) {
			moves.add(0, pos + step * dir);
			step++;
		}

		return moves;
	}

	/**
	 * Sammelt alle mögliche Folgezustände für gegebenen Zustand.
	 * 
	 * @param board
	 *            Aktueller Zustand
	 * @return Alle regelgerechten Folgezustände.
	 */
	private List<int[]> getNextStates(int[] board) {

		List<int[]> states = new ArrayList<int[]>();

		for (int move : getAllMovesSorted(board)) {
			states.add(doMove(board, move));
		}

		return states;
	}

	/**
	 * Gibt Besitzer einer Figur wieder.
	 * 
	 * @param fig
	 *            Figur
	 * @return Spieler
	 */
	public int getPlayerByFig(int fig) {
		if (isWhiteFig(fig))
			return PLAYER_WHITE;
		else
			return PLAYER_BLACK;
	}

	/**
	 * Gibt Position einer Figur wieder.
	 * 
	 * @param board
	 *            Spielfeld
	 * @param fig
	 *            Figur
	 * @return Position der Figur
	 */
	private int getPosByFig(int[] board, int fig) {

		// Durchlaufe Spielfeld bist Figurcode = gesuchter Figur
		for (int i = 11; i < 89; i++) {
			if (board[i] == fig)
				return i;
		}

		// Falls Figur nicht gefunden wurde, was nur sein kann, wenn illegale
		// Figur übergeben wurde oder Spielfeld fehlerhaft ist
		return ERROR;
	}

	public int getTurn(int[] board) {

		int markPos = board[MARK];
		int markFig = board[markPos];

		if (isWhiteFig(markFig))
			return PLAYER_WHITE;
		else
			return PLAYER_BLACK;

	}

	/**
	 * Ermittelt den Gewinner eines Zustandes. Falls kein Gewinner vorhanden,
	 * wird der entsprechedne Code wiedergegeben.
	 * 
	 * @param board
	 *            Zustand
	 * @return Gewinner bzw. NULL
	 */
	private int getWinner(int[] board) {

		for (int i = 11; i < 89; i++) {

			if (i <= 18 & isWhiteFig(board[i]))
				return WIN_WHITE;

			else if (i >= 81 & isBlackFig(board[i]))
				return WIN_BLACK;

		}

		return WIN_NULL;

	}

	/**
	 * Bewertungsfunktion, die den Wert x eines Zustands wiedergibt, der bereits
	 * einen Sieg oder eine Niederlage darstellt.
	 * 
	 * Für x gilt:
	 * 
	 * 1 <= betrag(x) <= 2 und
	 * 
	 * betrag(x) = Resttiefe / Maximaltiefe.
	 * 
	 * Das beudetet, dass der betrag(x) umso höher, je mehr Resttiefe übrig ist
	 * bzw. umso weniger Züge für diesen Zustand nötig sind. Wichtig ist dies
	 * beim Vergleich zweier Siegzustände, wo jener besser, der in weniger Zügen
	 * erreicht wird.
	 * 
	 * Ein Sieg in maximaler Suchtiefe bzw. Resttiefe = 0 ergibt eine Bewertung
	 * von 1 für Weiß bzw. -1 für Schwarz.
	 * 
	 * Ein Sieg im ersten Zug, also bei Resttiefe = Maximaltiefe ergibt eine
	 * Bewertung von 2 für Weiß bzw. -2 für Schwarz.
	 * 
	 * @param board
	 *            Zu bewertender Game Over Zustand. Es hat also bereits Weiß
	 *            oder Schwarz gewonnen (die gegnerishe Grundlinie erreicht).
	 * @param depth
	 *            Resttiefe. Also verbleibende Anzahl an Zügen, bevor die Suche
	 *            aufgrund zu hoher Suchtiefe abgebrochen wird um endlos lange
	 *            Berechnung zu verhindern.
	 * 
	 *            Für die Resttiefe r und maximale Suchtiefe m gilt: 0 <= r <=
	 *            m.
	 * @return Bewertung x des gegebenen Zustands. Falls Weiß gewinnt, gilt: 1
	 *         <= x <= 2. Fall Schwarz gewinnt, gilt: -2 <= x <= -1. Je höher
	 *         die Resttiefe ist, desto höher ist betrag(x).
	 */
	private double getWinValue(int[] board, int depth) {
		int markPos = board[MARK];
		int markFig = board[markPos];

		double value = depth / maxDepth;

		if (isWhiteFig(markFig))
			value = (-1) - value;
		else
			value = 1 + value;

		return value;

	}

	public boolean isBlackFig(int fig) {
		if (fig >= 9 & fig <= 16)
			return true;
		else
			return false;
	}

	public boolean isWhiteFig(int fig) {
		if (fig >= 1 & fig <= 8)
			return true;
		else
			return false;
	}

	/**
	 * Wichtiger Teil der Alpha Beta Suche. maxValue() und minValue() bauen
	 * Suchbaum maximal bis zu gegebenen Suchtiefe auf. Dabei gibt es Alpha,
	 * Beta, Depth oder GameOver Cuts.
	 * 
	 * @param board
	 *            Zustand
	 * @param alpha
	 *            Alpha
	 * @param beta
	 *            Beta
	 * @param depth
	 *            Restsuchtiefe
	 * @param blockCycleFig
	 *            Zuletzt geblockte Figur. Muss als Argument übergeben werden
	 *            und kann nicht als Klassenvariable gespeichert werden, da
	 *            jeder Suchbaumzweig eigene zuletzte geblockte Figur haben
	 *            kann.
	 * @return Bester/Höchster Bewertungswert für alle Folgezustände für Spieler
	 *         Weiss.
	 */
	private double maxValue(int[] board, double alpha, double beta, int depth,
			int blockCycleFig) {

		if (abort)
			return WIN_NULL;

		// Falls insgesamt zuviele Methodenaufrufe, womöglich Endlosschleife
		if (checkCounter()) {
			return WIN_NULL;
		}

		int winner = getWinner(board);

		// Falls GameOver, kann auch Blockzyklus sein
		if (winner != WIN_NULL) {
			cutWin++;
			return getWinValue(board, depth);
		}

		// Falls maximale Suchtiefe erreicht
		if (depth <= 0) {
			cutDepth++;
			return getCutValue(board, depth);
		}

		List<int[]> states = getNextStates(board);

		// Falls kein Zug möglich, also Figur blockiert
		if (states.isEmpty()) {

			// Dieser Abschnitt wurde Ausgelagert in eigene Methode, da relativ
			// lang und bei minValue() und maxValue() gleich
			return blocked(board, alpha, beta, depth, blockCycleFig);

		} else {

			// mindestens ein Zug ist möglich, also keine Blockierung, also
			// letzte gemerkte geblockte Figur kann kein Blockzyklus bilden,
			// also Blockzyklus aufgehoben
			blockCycleFig = 0;

		}

		double value = MIN;

		// für alle Folgezustände, ermittle besten zustand für Weiss
		for (int[] state : states) {

			// value = Math.max(value,
			// minValue(state, alpha, beta, depth - 1, blockCycleFig));
			//
			// if (value >= beta)
			// return value;
			//
			// alpha = Math.max(alpha, value);

			double temp = minValue(state, alpha, beta, depth - 1, blockCycleFig);

			if (temp > value)
				value = temp;

			if (value >= beta) {
				cutBeta++;
				return value;
			}

			if (value > alpha)
				alpha = value;

		}

		cutMax++;
		return value;
	}

	private double negamax(int[] board, double alpha, double beta, int depth,
			int blockCycleFig) {

		if (abort)
			return WIN_NULL;

		// Falls insgesamt zuviele Methodenaufrufe, womöglich Endlosschleife
		if (checkCounter()) {
			return WIN_NULL;
		}

		int winner = getWinner(board);

		// Falls GameOver, kann auch Blockzyklus sein
		if (winner != WIN_NULL) {
			cutWin++;
			return getWinValue(board, depth);
		}

		// Falls maximale Suchtiefe erreicht
		if (depth <= 0) {
			cutDepth++;
			return getCutValue(board, depth);
		}

		List<int[]> states = getNextStates(board);

		// Falls kein Zug möglich, also Figur blockiert
		if (states.isEmpty()) {

			// Dieser Abschnitt wurde Ausgelagert in eigene Methode, da relativ
			// lang und bei minValue() und maxValue() gleich
			return blocked(board, alpha, beta, depth, blockCycleFig);

		} else {

			// mindestens ein Zug ist möglich, also keine Blockierung, also
			// letzte gemerkte geblockte Figur kann kein Blockzyklus bilden,
			// also Blockzyklus aufgehoben
			blockCycleFig = 0;

		}

		// für alle Folgezustände, ermittle besten zustand für Weiss
		for (int[] state : states) {

			double value = -negamax(state, -beta, -alpha, depth - 1,
					blockCycleFig);

			if (value >= beta) {
				cutBeta++;
				return beta;
			}

			if (value > alpha)
				alpha = value;

		}

		cutMax++;
		return alpha;
	}

	/**
	 * Quellknoten für die Alpha Beta Suche
	 * 
	 * @param board
	 *            Status
	 * @param depth
	 *            Maximale Suchtiefe
	 * @return bester Zug für markierte Figur
	 */
	public int minimax(int[] board, int depth) {

		// System.out.println(new Valuation(this).getValue(board, depth));

		maxDepth = depth;

		int markPos = board[MARK];
		int bestMove = 0;

		if (markPos == NO_MARK) {

			bestMove = firstMark(board, depth);

		} else if (isWhiteFig(board[markPos])) {

			bestMove = firstMax(board, depth);

		} else {

			bestMove = firstMin(board, depth);
		}

		// printCuts();

		if (Kamisado.view.getOptionWait())
			Kamisado.control.pauseGame();

		Kamisado.view.clearRatings();

		return bestMove;

	}

	/**
	 * Siehe maxValue(), prinzipiell gleicher Aufbau, nur für Schwarz, statt für
	 * Weiss.
	 * 
	 * @param board
	 *            Status
	 * @param alpha
	 *            Alpha
	 * @param beta
	 *            Beta
	 * @param depth
	 *            Tiefe
	 * @param blockCycleFig
	 *            geblockte Figur
	 * @return bester Wert
	 */
	private double minValue(int[] board, double alpha, double beta, int depth,
			int blockCycleFig) {

		if (abort)
			return WIN_NULL;

		if (checkCounter()) {
			return WIN_NULL;
		}

		int winner = getWinner(board);

		if (winner != WIN_NULL) {
			cutWin++;
			return getWinValue(board, depth);
		}

		if (depth <= 0) {
			cutDepth++;
			return getCutValue(board, depth);
		}

		List<int[]> states = getNextStates(board);

		if (states.isEmpty()) {
			return blocked(board, alpha, beta, depth, blockCycleFig);
		} else {
			blockCycleFig = 0;
		}

		double value = MAX;

		for (int[] state : states) {

			double temp = maxValue(state, alpha, beta, depth - 1, blockCycleFig);

			if (temp < value)
				value = temp;

			if (value <= alpha) {
				cutAlpha++;
				return value;
			}

			if (value < beta)
				beta = value;
		}

		cutMin++;
		return value;
	}

	private void printCut(int cuts, String name) {
		int cutPercent = (int) (cuts / cutSum * 100);
		Kamisado.print(name + " cuts \t" + cutPercent + " \t" + cuts);
	}

	@SuppressWarnings("unused")
	private synchronized void printCuts() {

		cutSum = cutAlpha + cutBeta + cutWin + cutLoop + cutEmpty + cutDepth
				+ cutMax + cutMin;

		printCut(cutAlpha, "alpha");
		printCut(cutBeta, "beta");
		printCut(cutWin, "leaf");
		printCut(cutLoop, "loop");
		printCut(cutEmpty, "block");
		printCut(cutDepth, "depth");
		printCut(cutMax, "max");
		printCut(cutMin, "min");

	}

	/**
	 * Sortiert Züge nach nähe zur gegnerischen Grundlinie.
	 * 
	 * @param moves
	 *            Liste von Zügen, die sortiert werden soll
	 */
	private void sortMoves(List<Integer> moves, int player) {

		if (player == PLAYER_WHITE) {

			Collections.sort(moves, new Comparator<Integer>() {

				public int compare(Integer x, Integer y) {
					if (x / 10 > y / 10)
						return 1;
					else if (x / 10 < y / 10)
						return -1;
					else
						return 0;
				}

			});

		} else {
			// if player = black

			Collections.sort(moves, new Comparator<Integer>() {

				public int compare(Integer x, Integer y) {
					if (x / 10 > y / 10)
						return -1;
					else if (x / 10 < y / 10)
						return 1;
					else
						return 0;
				}

			});

		}

	}

	/**
	 * Aktualisiert Markierung. Notwendig, wenn aktuelle Marierung blockiert
	 * ist.
	 * 
	 * @param board
	 *            Status
	 */
	private void updateMarkAfterBlock(int[] board) {
		int markPos = board[MARK];
		int markFig = board[markPos];
		int fieldColor = getColByPos(markPos);

		int newPlayer = 0;

		// ermittle nächsten Spieler
		if (isWhiteFig(markFig))
			newPlayer = PLAYER_BLACK;
		else
			newPlayer = PLAYER_WHITE;

		int newFig = getFig(fieldColor, newPlayer);
		int newPos = getPosByFig(board, newFig);
		board[MARK] = newPos;
	}

	private synchronized void updateRating(int move, double temp) {

		// TODO Aktualisieren der Ratings muss irgendwie anders gemacht werden,
		// sonst greifen auch abgestoßene Spieler (newGame) auf die GUI zu

		synchronized (ratings) {

			ratings.add(new Pos(move, temp));
			Kamisado.view.updateRatings(ratings);

		}

		// pause();
	}

}