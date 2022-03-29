package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.View;
import control.Kamisado;
import control.Minimax;
import control.Player;
import control.PlayerMinMax;

/**
 * Die Schiedsrichter/Spielregel/Datenbank Klasse. Hier wird die meiste
 * Spiellogik umgesetzt.
 * 
 * @author Harry
 * 
 */
public class Game {

	private Field[][] board;

	private Figure firstBlockedFigure;

	private boolean gameOver;

	private GameColor lastMoveColor;

	public List<String> log;

	private boolean marked;

	private int markX;

	private int markY;

	private Player player1;

	private Player player2;

	private Player turn;

	private View view;

	public Game(Player p1, Player p2) {
		gameOver = false;
		player1 = p1;
		player2 = p2;
		turn = player1;
		board = new Field[8][8];
		log = new ArrayList<String>();
		marked = false;
		markX = 0;
		markY = 0;
		initBoard();
	}

	private boolean bothPlayerBlocked() {
		// wieder am Anfang vom Blockzyklus?
		if (getMarkedFigure() == firstBlockedFigure)
			return true;
		else
			return false;
	}

	private void checkBlocking() {

		// Wenn Blockzyklus erkannt
		if (bothPlayerBlocked()) {

			logBothPlayerBlocked();

			// Setze Spiel Game Over, also Gewinner anzeigen
			setGameOver();

			// Falls nur gerade markierte Figur geblockt
		} else if (turnBlocked()) {

			logTurnBlocked();

			// Falls noch keine Figur als erste geblockte Figur gemerkt
			if (firstBlockedFigure == null)
				// merke markierte Figur als erste geblockte Figur (wird bei der
				// nächsten nicht geblockten Figur gelöscht)
				firstBlockedFigure = getMarkedFigure();

			// Markierung entfernen und Feldfarbe für nächsten Zug merken
			Field field = board[markX][markY];
			lastMoveColor = field.getColor();
			field.getFigure().setMark(false);

			switchTurn();

		} else
			// Blockzyklus aufgelöst
			firstBlockedFigure = null;

	}

	/**
	 * Gibt Feld in der notwendigen Kodierung für die Alpha Beta Suche wieder.
	 * 
	 * @return Spielstatus als int[100]
	 */
	public int[] getBoard() {

		int size = 101;

		int[] result = new int[size];

		// Deklariere zunächst alle Felder mit -1
		for (int i = 0; i < result.length; i++) {
			// Felder außerhalb bleiben -1
			result[i] = Minimax.ERROR;
		}

		// Für jedes Feld aus Game
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				// rechne Positionsnummer aus
				int num = (i + 1) * 10 + j + 1;
				Figure fig = board[i][j].getFigure();

				if (fig != null)
					result[num] = fig.getCode();
				else
					result[num] = Minimax.POS_FREE;
			}
		}

		// Setze Positon der markierten Figur auf result[100]
		if (marked)
			result[100] = PlayerMinMax.posToCode(getMarkedPos());
		else
			result[100] = Minimax.NO_MARK;

		return result;
	}

	public Field getField(int x, int y) {
		return board[x][y];
	}

	private Field getField(Pos pos) {
		return getField(pos.getX(), pos.getY());
	}

	public Figure getFigure(int posX, int posY) {
		return board[posX][posY].getFigure();
	}

	public Figure getFigure(Pos pos) {
		return getFigure(pos.getX(), pos.getY());
	}

	/**
	 * Die GUI entnimmt hierdurch die Zugliste zur Anzeige in der JList.
	 * 
	 * @return Liste mit den bisher gemachten Zügen.
	 */
	public List<String> getLog() {
		return log;
	}

	private Field getMarkedField() {
		return getField(markX, markY);

	}

	private Figure getMarkedFigure() {
		return getMarkedField().getFigure();
	}

	private Pos getMarkedPos() {
		return new Pos(markX, markY);
	}

	/**
	 * Ermittelt alle möglichen Zugfelder für aktuell markierte Figur. Notwendig
	 * für die GUI.
	 * 
	 * @return Liste aller regelkonformen Zielfelder
	 */
	public List<Pos> getTargets() {

		List<Pos> targets = new ArrayList<Pos>();

		// Überprüfe für alle Felder, ob Zug dorthin möglich
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {

				if (isMovePossible(x, y))
					targets.add(new Pos(x, y));
			}
		}

		return targets;
	}

	public Player getTurn() {
		return turn;
	}

	private void initBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = new Field(Kamisado.getFieldColor(i, j), i, j);
				if (i == 0) {
					Figure fig = new Figure(Kamisado.getFieldColor(i, j),
							player2);
					board[i][j].setFigure(fig);
				}
				if (i == 7) {
					Figure fig = new Figure(Kamisado.getFieldColor(i, j),
							player1);
					board[i][j].setFigure(fig);
				}
			}
		}
	}

	public boolean isGameOver() {
		return gameOver;
	}

	private boolean isMarkingPossible(int x, int y) {

		// pos out of board
		if (isPosOutOfBoard(x, y))
			return false;

		if (!marked && board[x][y].getFigure() != null
				&& board[x][y].getFigure().getPlayer() == turn)
			return true;
		else
			return false;
	}

	private boolean isMovePossible(int posX, int posY) {

		// pos out of board
		if (isPosOutOfBoard(posX, posY))
			return false;

		// pos busy
		else if (board[posX][posY].getFigure() != null)
			return false;

		else if (turn == player1)
			// if white move
			return moveWhitePossible(posX, posY);

		else
			// if black move
			return moveBlackPossible(posX, posY);

	}

	private boolean isPosOutOfBoard(int posX, int posY) {
		int size = board.length - 1;
		if (posX < 0 | posX > size | posY < 0 | posY > size)
			return true;
		else
			return false;
	}

	private void logBothPlayerBlocked() {
		log.add("Blockzyklus");
		view.updateLog();
	}

	private void logGameOver() {
		log.add(turn + " wins");

		if (turn == player1)
			setStatus(player1.getPlayerName() + " gewinnt. "
					+ player2.getPlayerName() + " verliert.");
		else
			setStatus(player2.getPlayerName() + " gewinnt. "
					+ player1.getPlayerName() + " verliert.");

		view.updateLog();
	}

	private void logMarked() {
		log.add(turn + " " + getMarkedFigure() + " " + getMarkedField()
				+ " marked");
		setStatus(turn.getPlayerName() + " wähle ein Ziel!");
		view.updateLog();

	}

	private void logMove(Pos pos) {
		log.add(turn + " " + getMarkedFigure() + " " + getMarkedField() + " "
				+ getField(pos) + " " + getField(pos).getColor());

		if (turn == player1)
			setStatus(player2.getPlayerName() + " wähle ein Ziel!");
		else
			setStatus(player1.getPlayerName() + " wähle ein Ziel!");

		view.updateLog();
	}

	private void logTurnBlocked() {
		log.add(turn + " " + getMarkedFigure() + " " + getMarkedField() + " "
				+ getMarkedField().getColor() + " blocked");
		view.updateLog();
	}

	private void move(int posX, int posY) {

		// copy figure from old to new pos
		board[posX][posY].setFigure(board[markX][markY].getFigure());

		// delete old figure
		board[markX][markY].setFigure(null);

		// game over?
		if (turn == player1 & posX == 0)
			setGameOver();
		else if (turn == player2 & posX == 7)
			setGameOver();
		else {

			// remove mark from moved figure
			board[posX][posY].getFigure().setMark(false);

			lastMoveColor = board[posX][posY].getColor();
			switchTurn();
		}

	}

	private boolean moveBlackPossible(int posX, int posY) {

		if (posY < markY && markY - posY == posX - markX) {
			for (int i = 1; i <= posX - markX; i++)
				if (board[markX + i][markY - i].getFigure() != null)
					return false;
			return true;
		}

		if (posY == markY && posX > markX) {
			for (int i = markX + 1; i <= posX; i++)
				if (board[i][markY].getFigure() != null)
					return false;
			return true;
		}

		if (posY > markY && posY - markY == posX - markX) {
			for (int i = 1; i <= posY - markY; i++)
				if (board[markX + i][markY + i].getFigure() != null)
					return false;
			return true;
		}

		return false;

	}

	private boolean moveWhitePossible(int posX, int posY) {

		if (posY < markY) {
			// if white move left up
			if (markX - posX == markY - posY) {
				for (int i = 1; i <= (markX - posX); i++) {
					if (board[markX - i][markY - i].getFigure() != null) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}

		} else if (posY == markY) {
			// if white move up
			if (posX < markX) {
				for (int i = markX - 1; i >= posX; i--) {
					if (board[i][posY].getFigure() != null) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}

		} else {
			// if white move right up
			// posY > markedY
			if (markX - posX == posY - markY) {
				for (int i = 1; i <= markX - posX; i++) {
					if (board[markX - i][markY + i].getFigure() != null) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		}

	}

	private void setGameOver() {
		gameOver = true;
		logGameOver();
	}

	private void setMark(Player player, Color color) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Figure f = board[i][j].getFigure();
				if (f != null && f.getColor() == color
						&& f.getPlayer() == player) {
					setMarked(i, j);
				}
			}
		}
	}

	private void setMarked(int posX, int posY) {
		board[posX][posY].getFigure().setMark(true);
		marked = true;
		markX = posX;
		markY = posY;
	}

	public void setNextMove(Pos pos) {

		if (pos == null)
			return;

		int x = pos.getX();
		int y = pos.getY();

		if (isMarkingPossible(x, y)) {

			setMarked(x, y);
			logMarked();

		} else if (isMovePossible(x, y)) {

			logMove(pos);
			view.drawMove(getMarkedPos(), pos);
			move(x, y);

		}

	}

	private void setStatus(String s) {
		view.setStatus(s);
	}

	private void setTurn(Player p) {

		turn = p;

		if (lastMoveColor != null)
			setMark(p, lastMoveColor);

		if (marked)
			checkBlocking();

	}

	public void setView(View v) {
		view = v;
		setStatus(turn.getPlayerName() + " ist dran. Markiere eine Figur!");
		view.setMoveVisible(false);
	}

	private void switchTurn() {
		if (turn == player2)
			setTurn(player1);
		else
			setTurn(player2);
	}

	/**
	 * Check if player cannot move figure, because one step in every moving
	 * direction is not possbible (blocked by other figure or is out of game
	 * field).
	 * 
	 * @return true, if turn blocked, else false
	 */
	private boolean turnBlocked() {

		boolean blocked = true;

		if (turn == player1) {

			// up left
			if (isMovePossible(markX - 1, markY - 1))
				blocked = false;

			// up
			if (isMovePossible(markX - 1, markY))
				blocked = false;

			// up right
			if (isMovePossible(markX - 1, markY + 1))
				blocked = false;

		} else if (turn == player2) {

			// down left
			if (isMovePossible(markX + 1, markY - 1))
				blocked = false;

			// down
			if (isMovePossible(markX + 1, markY))
				blocked = false;

			// down right
			if (isMovePossible(markX + 1, markY + 1))
				blocked = false;

		}

		return blocked;
	}

}
