package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.Figure;
import model.Pos;
import control.Kamisado;
import control.PlayerMinMax;

/**
 * Spielbrett GUI mit Koordinatensystem. Sklaiert automatisch mit Fenstergröße.
 * 
 * @author Harry
 * 
 */
public class Board extends JPanel {

	private static final long serialVersionUID = 1L;

	private int boardLen;

	private int boardX;

	private int boardY;

	private int fieldLen;

	private int fieldsPerLine = 8;

	private int figureCrossScale = 20;

	private int figureScale = 70;

	private int fontScale = 4;

	private Graphics2D g2;

	private Color markColor = Color.GRAY;

	private int maxX;

	private int maxY;

	private Pos moveStart;

	private Pos moveTarget;

	private boolean moveVisible = false;

	private int numberOfFields = fieldsPerLine * fieldsPerLine;

	private int pad;

	private int padScale = 22;

	private List<Pos> ratings = new ArrayList<Pos>();

	private int targetArcScale = 7;

	private List<Pos> targets = new ArrayList<Pos>();

	private View view;

	public Board(View view) {
		this.view = view;
		addListener();
		view.setBoard(this);
	}

	private void addListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				clicked(e);
			}
		});
	}

	private void clicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > boardX & x < boardX + boardLen & y > boardY
				& y < boardY + boardLen) {

			x = (x - boardX) / fieldLen;
			y = (y - boardY) / fieldLen;

			view.clicked(x, y);
		}
	}

	private void drawBackground() {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, maxX, maxY);
	}

	private void drawBoard() {
		g2.setColor(Color.YELLOW);

		int max = maxX;

		if (maxX > maxY)
			max = maxY;

		pad = max / padScale;
		boardLen = max - 2 * pad;

		boardX = (maxX - boardLen) / 2;
		boardY = pad;

		drawCoords();

		drawFields();

		if (moveVisible)
			drawMove();

		drawTargets();

		synchronized (ratings) {
			drawRatings();
		}

	}

	private void drawCoords() {

		g2.setColor(Color.WHITE);
		Font f = new Font("Arial", Font.PLAIN, fieldLen / fontScale);
		g2.setFont(f);
		int fh = (int) (g2.getFontMetrics().getHeight() * 0.6);

		for (int i = 0; i < fieldsPerLine; i++) {

			// vertikal

			String s = Kamisado.getPosTextY(i);

			int fw = g2.getFontMetrics().stringWidth(s);

			int x = boardX - (pad - fw) / 2 - fw;
			int y = boardY + i * fieldLen + (fieldLen - fh) / 2 + fh;

			g2.drawString(s, x, y);
			g2.drawString(s, x + boardLen + pad, y);

			// horizontal

			s = Kamisado.getPosTextX(i);

			fw = g2.getFontMetrics().stringWidth(s);

			x = boardX + i * fieldLen + (fieldLen - fw) / 2;
			y = (pad - fh) / 2 + fh;

			g2.drawString(s, x, y);
			g2.drawString(s, x, y + pad + boardLen);

		}
	}

	private void drawFields() {

		fieldLen = boardLen / fieldsPerLine;

		for (int i = 0; i < numberOfFields; i++) {
			int posX = i % fieldsPerLine;
			int posY = i / fieldsPerLine;
			int x = boardX + posX * fieldLen;
			int y = boardY + posY * fieldLen;
			g2.setColor(Kamisado.getFieldColor(posY, posX));
			g2.fillRect(x, y, fieldLen, fieldLen);

			Figure fig = view.getFigure(posY, posX);
			if (fig != null)
				drawFigure(fig, x, y);
		}

	}

	private void drawFigure(Figure fig, int posX, int posY) {

		drawFigureArc(fig.getPlayer().getColor(), posX, posY);

		drawFigureCross(fig.getColor(), posX, posY);

		if (fig.isMarked()) {
			drawFigureMark(markColor, posX, posY);
		}

	}

	private void drawFigureArc(Color c, int posX, int posY) {
		// Ausgefüllter Kreis in Spielerfarbe
		g2.setColor(c);
		int w = figureScale * fieldLen / 100;
		int x = posX + (fieldLen - w) / 2;
		int y = posY + (fieldLen - w) / 2;
		g2.fillArc(x, y, w, w, 0, 360);
	}

	private void drawFigureCross(Color c, int posX, int posY) {
		// Kreuz in Figurfarbe
		g2.setColor(c);
		int w = figureCrossScale * fieldLen / 100;
		g2.setStroke(new BasicStroke(w / 2));
		int x1 = posX + (fieldLen - w) / 2;
		int y1 = posY + (fieldLen - w) / 2;
		int x2 = x1 + w;
		int y2 = y1 + w;
		g2.drawLine(x1, y1, x2, y2);
		g2.drawLine(x1, y2, x2, y1);
	}

	private void drawFigureMark(Color c, int posX, int posY) {
		g2.setColor(c);
		int w = figureScale * fieldLen / 100;
		int x = posX + (fieldLen - w) / 2;
		int y = posY + (fieldLen - w) / 2;
		g2.drawArc(x, y, w, w, 0, 360);
	}

	private void drawMove() {
		g2.setColor(markColor);
		int x1 = boardX + moveStart.getY() * fieldLen + fieldLen / 2;
		int y1 = boardY + moveStart.getX() * fieldLen + fieldLen / 2;

		int cornerX = 0;

		if (moveStart.getX() < moveTarget.getX())
			cornerX = 25;
		else if (moveStart.getX() == moveTarget.getX())
			cornerX = 50;
		else
			cornerX = 75;

		int cornerY = 0;

		if (moveStart.getY() < moveTarget.getY())
			cornerY = 25;
		else if (moveStart.getY() == moveTarget.getY())
			cornerY = 50;
		else
			cornerY = 75;

		int x2 = boardX + moveTarget.getY() * fieldLen + cornerY * fieldLen
				/ 100;
		int y2 = boardY + moveTarget.getX() * fieldLen + cornerX * fieldLen
				/ 100;
		g2.drawLine(x1, y1, x2, y2);
	}

	public void drawMove(Pos start, Pos target) {
		moveVisible = true;
		moveStart = start;
		moveTarget = target;
	}

	private synchronized void drawRatings() {

		Font f = new Font("Arial", Font.BOLD, fieldLen / fontScale);
		g2.setFont(f);

		g2.setColor(Kamisado.model.getTurn().getColor());

		for (Pos p : ratings) {

			Pos pos = PlayerMinMax.codeToPos(p.getX());

			int posX = pos.getX();
			int posY = pos.getY();

			int x = boardX + posY * fieldLen;
			int y = boardY + posX * fieldLen + fieldLen;

			g2.drawString("" + Math.round(p.getV() * 1000) / 1000., x, y);

		}

	}

	private void drawTargets() {

		int w = fieldLen / targetArcScale;

		for (Pos p : targets) {
			g2.setColor(Kamisado.model.getTurn().getColor());
			int x = boardX + p.getY() * fieldLen + fieldLen / 2 - w / 2;
			int y = boardY + p.getX() * fieldLen + fieldLen / 2 - w / 2;
			g2.fillArc(x, y, w, w, 0, 360);
		}
	}

	@Override
	public void paint(Graphics g) {

		this.g2 = (Graphics2D) g;
		maxX = getWidth();
		maxY = getHeight();

		drawBackground();
		drawBoard();

	}

	public void setMoveVisible(boolean b) {
		moveVisible = b;
	}

	public void setTargets(List<Pos> tar) {
		targets = tar;
	}

	public synchronized void updateRatings(List<Pos> ratings) {
		synchronized (this.ratings) {
			this.ratings = ratings;
		}
	}

}
