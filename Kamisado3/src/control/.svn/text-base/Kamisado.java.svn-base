package control;

import java.awt.Color;

import model.GameColor;
import model.Game;
import model.Pos;
import view.View;

/**
 * Implemtierung des Brettspiel Kamisado in Java.
 * 
 * @author Harry
 * 
 */
public class Kamisado implements Runnable {

	/**
	 * Farbmuster für das Spielbrett.
	 */
	private static GameColor[][] COLOR_PATTERN;

	public static Kamisado control;

	private static boolean debug = false;

	public static int START_KI_LEVEL = 10;

	/**
	 * Das Game mit den Spielregeln und dem Spielstatus
	 */
	public static Game model;

	private static boolean pause = true;

	private static int printCount = 0;

	/**
	 * GUI mit JFrame und Nutzung von paintComponent().
	 */
	public static View view;

	/**
	 * Gibt den Farbwert für ein bestimmtes Spielfeld zurück.
	 * 
	 * @param x
	 *            vertikale Koordinate
	 * @param y
	 *            horizontzale Koordinate
	 * @return Farbe des Spielfelds auf Position (x,y)
	 */
	public static GameColor getFieldColor(int x, int y) {
		return COLOR_PATTERN[x][y];
	}

	/**
	 * Wandelt Koordinaten der Form (1,2) in (C2).
	 * 
	 * @param x
	 *            horizontale Koordinate
	 * @param y
	 *            vertikale Koordinate
	 * @return Koordinate in Ausgabetextform
	 */
	public static String getPosText(int x, int y) {
		return getPosTextX(y) + getPosTextY(x);
	}

	/**
	 * Wandelt horizontale Zahlenkoordinate in Buchstabenkoordinate um.
	 * 
	 * @param i
	 *            Horizontale Koordinate als Zahl (0-7).
	 * @return Horizontale Koordinate als Buchstabe.
	 */
	public static String getPosTextX(int i) {
		// dec 65 = ascii A
		return (char) (i + 65) + "";
	}

	/**
	 * Wandelt vertikale Koordinate in Zahl entsprechend der GUI um.
	 * 
	 * @param x
	 *            vertikale Koordinate (0-7).
	 * @return Vertikale Koordinate (1-8).
	 */
	public static String getPosTextY(int x) {
		return (8 - x) + "";
	}

	/**
	 * Methode, die als erstes von der JVM aufgerufen wird. Startet das Spiel an
	 * sich, also KI, GUI, etc.
	 * 
	 * @param args
	 *            Wird ignoriert.
	 */
	public static void main(String[] args) {
		// Deklatiert das Farbmuster;
		setColors();

		control = new Kamisado();
	}

	public static synchronized void print(String s) {
		if (debug)
			System.out.println(printCount++ + " " + s);
	}

	/**
	 * Festlegung des Farbmusters inklusive Erzeugung der entsprechende
	 * Spielfarben.
	 */
	private static void setColors() {

		// deklariere Farben mit Codes und Namen

		GameColor orange = new GameColor(238, 118, 0, "Orange", 1);
		GameColor blue = new GameColor(72, 118, 255, "Blue", 2);
		GameColor purple = new GameColor(160, 32, 240, "Purple", 3);
		GameColor pink = new GameColor(255, 105, 180, "Pink", 4);
		GameColor yellow = new GameColor(255, 215, 0, "Yellow", 5);
		GameColor red = new GameColor(205, 0, 0, "Red", 6);
		GameColor green = new GameColor(34, 139, 34, "Green", 7);
		GameColor brown = new GameColor(139, 69, 19, "Brown", 8);

		// Aufbau des Farbmusters

		GameColor[][] cols = {
				{ orange, blue, purple, pink, yellow, red, green, brown },
				{ red, orange, pink, green, blue, yellow, brown, purple },
				{ green, pink, orange, red, purple, brown, yellow, blue },
				{ pink, purple, blue, orange, brown, green, red, yellow },
				{ yellow, red, green, brown, orange, blue, purple, pink },
				{ blue, yellow, brown, purple, red, orange, pink, green },
				{ purple, brown, yellow, blue, green, pink, orange, red },
				{ brown, green, red, yellow, pink, purple, blue, orange } };

		COLOR_PATTERN = cols;

	}

	/**
	 * Festlegung der Spielfarbe mit Code für den schwarzen Spieler.
	 */
	private GameColor black = new GameColor(Color.BLACK, "Black", 2);

	private Thread game;

	/**
	 * Spiler Schwarz Nord 2
	 */
	private Player playerBlack;

	/**
	 * Spieler Weiss Süd 1
	 */
	private Player playerWhite;

	/**
	 * Abbruchbedingung, falls Spiel vorzeitig beendet werden soll (neues Spiel
	 * starten).
	 */
	private boolean stopGame = false;

	/**
	 * Festlegung der Spielerfarbe Weiss.
	 */
	private GameColor white = new GameColor(Color.WHITE, "White", 1);

	/**
	 * Konstruktor deklariert Spieler, "Regeln" und GUI.
	 */
	public Kamisado() {
		playerWhite = new PlayerHuman("Weis", white);
		playerBlack = new PlayerMinMax("AB" + START_KI_LEVEL, black,
				START_KI_LEVEL);
		model = new Game(playerWhite, playerBlack);
		view = new View(this, model);

		// Aufbau der GUI als eigenständiger Thread, damit andere Prozesse nicht
		// die GUI einfrieren können.
		view.start();

		// Spielstart, also abwechselnd ziehen, etc.
		game = new Thread(this);
		game.start();
	}

	/**
	 * Laufendes Spiel abbrechen.
	 */
	private void abortGame() {

		continueGame();

		// Abbruchbedingung setzen.
		stopGame = true;

		// beide Spieler aufwecken, falls kamisado.run() auf
		// player.getNextMove() wartet.

		playerWhite.abort();
		playerBlack.abort();

		try {
			game.join();
		} catch (InterruptedException e) {
			handleException(e, "Fehler beim warten auf Tod vom letzten Spiel");
		}

	}

	public static void handleException(Exception e, String string) {
		System.err.println(string);
		System.exit(0);
	}

	/**
	 * Mensch hat auf ein Spielfeld geklickt. Kann Zielfeld oder Markierung
	 * sein.
	 * 
	 * @param x
	 *            Horizontale Koordinate des Spielfeldes.
	 * @param y
	 *            Vertikale Koordinate des Spielfeldes.
	 */
	public void clicked(int x, int y) {
		model.getTurn().setMove(x, y);
	}

	public synchronized void continueGame() {
		pause = false;
		notify();
	}

	/**
	 * Erzeugt neues Spiel Computer gegen Computer.
	 */
	public void newGameCvC() {
		abortGame();
		int level = view.getKILevel();
		playerWhite = new PlayerMinMax("AB" + level, white, level);
		playerBlack = new PlayerMinMax("AB" + level, black, level);
		startNewGame();
	}

	/**
	 * Erzeugt neues Spiel Computer gegen Mensch.
	 */
	public void newGameCvH() {
		abortGame();
		int level = view.getKILevel();
		playerWhite = new PlayerMinMax("AB" + level, white, level);
		playerBlack = new PlayerHuman("Hans", black);
		startNewGame();
	}

	/**
	 * Erzeugt neues Spiel Mensch gegen Computer.
	 */
	public void newGameHvC() {
		abortGame();
		int level = view.getKILevel();
		playerWhite = new PlayerHuman("Hans", white);
		playerBlack = new PlayerMinMax("AB" + level, black, level);
		startNewGame();
	}

	/**
	 * Erzeugt neues Spiel Mensch gegen Mensch.
	 */
	public void newGameHvH() {
		abortGame();
		playerWhite = new PlayerHuman("Hans", white);
		playerBlack = new PlayerHuman("Peter", black);
		startNewGame();
	}

	public synchronized void pauseGame() {

		while (pause) {

			try {
				wait();
			} catch (InterruptedException e) {
				handleException(e, "Fehler beim pasuieren in pauseGame()!");
			}

		}

		pause = true;

	}

	public void run() {

		// Auf Fertigstellung der GUI warten.
		waitForView();

		// erst hier wird dem Game die GUI zugewiesen, also nachdem
		// sichergestellt ist, dass die GUI fertig aufgebaut ist.
		model.setView(view);

		// Schleife in der abwechselnd gezogen wird, bis ein Spieler gewinnt.
		while (!stopGame && !model.isGameOver()) {

			view.showPossibleTargets();

			Player player = model.getTurn();

			// zur Übergabe an KI, bei Mensch unnötig.
			int[] board = model.getBoard();

			view.getTimer(player).start();

			player.setKILevel(view.getKILevel());

			// Anfrage nach nächstem Zug beim Spieler, der gerade dran ist. Hier
			// muss gewartet werden, bis eine Antwort kommt.
			Pos pos = player.getNextMove(board);

			view.getTimer(player).stop();

			model.setNextMove(pos);

			view.clearTargets();

		}

	}

	/**
	 * Einstellungen für neues Spiel vornehmen.
	 */
	private synchronized void startNewGame() {
		model = new Game(playerWhite, playerBlack);
		view.setModel(model);
		stopGame = false;
		game = new Thread(this);
		game.start();
		model.log.add(playerWhite.getPlayerName() + " vs "
				+ playerBlack.getPlayerName());
		view.updateLog();
	}

	/**
	 * Warten, bis der GUI Prozess abgeschlossen ist, also view.run()
	 * durchgelaufen.
	 */
	private void waitForView() {
		try {
			view.join();
		} catch (InterruptedException e) {
			handleException(e, "Fehler beim warten auf GUI!");
		}
	}

}
