package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.Timer;

import model.Figure;
import model.Game;
import model.Pos;
import control.Kamisado;
import control.Player;

/**
 * Oberklasse für die GUI. Erzeugt Frame als Thread und ist Schnittstelle zu
 * Game und Control.
 * 
 * @author Harry
 * 
 */
public class View extends Thread {

	private Board board;
	private Kamisado control;
	private int delay = 100;

	/**
	 * Zeitgeberschritte in Millisekunden. Beispiel: timer1 / delta = sek
	 */
	private int delta = 1000 / delay;

	private Frame frame;

	private JList<String> list;

	private Game model;

	private JLabel statusLabel;

	private int time1 = 0;

	private int time2 = 0;

	private JTextField timeField1;

	private JTextField timeField2;

	private Timer timer1;

	private Timer timer2;

	public View(Kamisado k, Game m) {
		// setPriority(MAX_PRIORITY);
		control = k;
		model = m;
	}

	public synchronized void clearRatings() {
		List<Pos> emptyList = new ArrayList<Pos>();
		board.updateRatings(emptyList);
	}

	public void clearTargets() {
		List<Pos> emptyList = new ArrayList<Pos>();
		board.setTargets(emptyList);
	}

	public void clicked(int x, int y) {
		control.clicked(y, x);
	}

	public void continueGame() {
		control.continueGame();
	}

	public void drawMove(Pos start, Pos target) {
		board.drawMove(start, target);
	}

	public Figure getFigure(int x, int y) {
		return model.getField(x, y).getFigure();
	}

	public Timer getTimer(Player player) {
		if (player.isWhitePlayer())
			return timer1;
		else
			return timer2;
	}

	private String getTimeText(int time) {
		double d = (double) time / delta;
		return "" + d;
	}

	public void newGameCvC() {
		control.newGameCvC();
	}

	public void newGameHvC() {
		control.newGameHvC();
	}

	public void newGameHvH() {
		control.newGameHvH();
	}

	@Override
	public void run() {
		frame = new Frame(this);
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setLog(JList<String> l) {
		list = l;
	}

	public void addLog(String s) {

		DefaultListModel<String> listModel = (DefaultListModel<String>) list
				.getModel();

		listModel.addElement(s);
		int size = listModel.getSize();
		list.ensureIndexIsVisible(size - 1);

	}

	public void setModel(Game m) {
		model = m;
		time1 = 0;
		timeField1.setText("0");
		time2 = 0;
		timeField2.setText("0");
		frame.repaint();
	}

	public void setMoveVisible(boolean b) {
		board.setMoveVisible(b);
	}

	public void setStatus(String s) {
		statusLabel.setText(s);
		frame.repaint();
	}

	public void setStatusLabel(JLabel statusLabel) {
		this.statusLabel = statusLabel;
	}

	public void setTimer(JTextField t1, JTextField t2) {

		timeField1 = t1;

		timer1 = new Timer(delay, new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				timeField1.setText(getTimeText(time1++));

				if (timeField1.getBackground() != Color.RED) {
					timeField1.setBackground(Color.RED);
					timeField2.setBackground(Color.GRAY);
				}

			}

		});

		timeField2 = t2;

		timer2 = new Timer(delay, new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				timeField2.setText(getTimeText(time2++));

				if (timeField2.getBackground() != Color.RED) {
					timeField1.setBackground(Color.GRAY);
					timeField2.setBackground(Color.RED);
				}

			}

		});

	}

	public void showPossibleTargets() {
		List<Pos> targets = model.getTargets();
		board.setTargets(targets);
	}

	public synchronized void updateLog() {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (String s : model.getLog()) {
			listModel.addElement(s);
		}
		list.setModel(listModel);
		list.ensureIndexIsVisible(listModel.getSize() - 1);
		frame.repaint();
	}

	public synchronized void updateRatings(List<Pos> ratings) {
		board.updateRatings(ratings);
		frame.repaint();
	}

	public void repaint() {
		frame.repaint();
	}

	private boolean optionWait = false;

	public void setOptionWait(boolean b) {
		optionWait = b;
	}

	public boolean getOptionWait() {
		return optionWait;
	}

	private int kiLevel = 10;

	public void setKILevel(int value) {
		kiLevel = value;
	}

	public int getKILevel() {
		return kiLevel;
	}

}
