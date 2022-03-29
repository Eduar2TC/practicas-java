package test.valuation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.Minimax;
import control.Valuation;

public class ValuationTest extends JPanel {

	private static final int FIELDS_PER_LINE = 8;

	private static final int MAX_NUM_TARGETS = 14;

	private static final long serialVersionUID = 1L;

	private static String arrayToString(double[] a) {
		String s = "";
		for (int i = 0; i < a.length; i++) {
			s += a[i] + "\t";
		}

		return s;
	}

	private static double[] getNumTargets() {

		Minimax mm = new Minimax();

		double[] tars = new double[101];

		for (int a = 1; a <= 8; a++) {
			for (int b = 1; b <= 8; b++) {

				int f = 10 * b + a;

				// make new clear board
				int[] board = new int[101];

				// set all error
				for (int i = 0; i < board.length; i++) {
					board[i] = Minimax.ERROR;
				}

				// set pos free
				for (int x = 1; x <= 8; x++) {
					for (int y = 1; y <= 8; y++) {
						board[10 * y + x] = Minimax.POS_FREE;
					}
				}

				// add one figure
				board[f] = 1;
				// set the one figure marked
				board[100] = f;

				tars[f] = mm.getAllMoves(board, f).size();

			}
		}

		return tars;

	}

	private static double[] getTargetValue() {

		Minimax mm = new Minimax();
		Valuation val = new Valuation(mm);

		double[] tars = new double[101];

		for (int a = 1; a <= FIELDS_PER_LINE; a++) {
			for (int b = 1; b <= FIELDS_PER_LINE; b++) {

				int f = 10 * b + a;

				int[] board = new int[101];

				for (int i = 0; i < board.length; i++) {
					board[i] = Minimax.ERROR;
				}

				for (int x = 1; x <= FIELDS_PER_LINE; x++) {
					for (int y = 1; y <= FIELDS_PER_LINE; y++) {
						board[10 * y + x] = Minimax.POS_FREE;
					}
				}

				board[f] = 1;
				board[100] = f;

				tars[f] = val.getTargetValue(f, mm.getPlayerByFig(1));

			}
		}

		return tars;

	}

	public static void main(String[] args) {
		try {
			ValuationTest vt1 = new ValuationTest("num targets");

			vt1.testNumTargets();

			ValuationTest vt2 = new ValuationTest("target value");

			vt2.testTargetValue();

			ValuationTest vt3 = new ValuationTest("fig value");

			vt3.testFigValue();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	private static String makeString(double[][] a) {

		String s = "";

		for (int y = 0; y < a.length; y++) {
			for (int x = 0; x < a.length; x++) {
				s += a[x][y] + "\t";
			}
			s += "\n";
		}

		return s;
	}

	private static void print(Object o) {
		System.out.println(o);
	}

	private static double[] getFigValue() {

		Minimax mm = new Minimax();
		Valuation val = new Valuation(mm);

		double[] tars = new double[101];

		for (int a = 1; a <= 8; a++) {
			for (int b = 1; b <= 8; b++) {

				int f = 10 * b + a;

				int[] board = new int[101];

				for (int i = 0; i < board.length; i++) {
					board[i] = Minimax.ERROR;
				}

				for (int x = 1; x <= 8; x++) {
					for (int y = 1; y <= 8; y++) {
						board[10 * y + x] = Minimax.POS_FREE;
					}
				}

				board[f] = 1;
				board[100] = f;

				tars[f] = val.getFigValue(f, board);

			}
		}

		return tars;

	}

	private static void printValue() {

		Minimax mm = new Minimax();
		Valuation val = new Valuation(mm);

		double[] tars = new double[101];

		for (int a = 1; a <= 8; a++) {
			for (int b = 1; b <= 8; b++) {

				int f = 10 * b + a;

				int[] board = new int[101];

				for (int i = 0; i < board.length; i++) {
					board[i] = Minimax.ERROR;
				}

				for (int x = 1; x <= 8; x++) {
					for (int y = 1; y <= 8; y++) {
						board[10 * y + x] = Minimax.POS_FREE;
					}
				}

				board[f] = 1;
				board[100] = f;

				tars[f] = val.getValue(board, 0);

			}
		}

		int size = 8;
		double[][] tars2 = new double[size][size];

		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				int z = 10 * y + x;
				tars2[x][y] = tars[z];

			}
		}

	}

	private static double[][] qualify(double[][] a) {

		double max = 0;

		for (int x = 0; x < a.length; x++) {
			for (int y = 0; y < a.length; y++) {
				double temp = Math.abs(a[x][y]);
				if (temp > max)
					max = temp;
			}
		}

		System.out.println("qualify " + max + "\n");

		for (int x = 0; x < a.length; x++) {
			for (int y = 0; y < a[x].length; y++) {
				a[x][y] = Math.round(a[x][y] / max * 100) / 100.;
			}
		}

		return a;

	}

	private static double[][] transform(double[] a) {
		double[][] b = new double[8][8];

		for (int x = 0; x < b.length; x++) {
			for (int y = 0; y < b.length; y++) {
				b[x][y] = a[11 + 10 * y + x];
			}
		}

		return b;
	}

	private double[][] numTargets = new double[8][8];

	private boolean paintNumTargets = false;

	private boolean paintTargetValue = false;

	private double[][] targetValue;

	private double[][] figValue;

	private boolean paintFigValue;

	public ValuationTest(String title) {
		JFrame f = new JFrame(title);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setContentPane(this);
		f.setLocationRelativeTo(null);
		// f.setExtendedState(Frame.MAXIMIZED_BOTH);
		f.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		if (paintNumTargets)
			paintNumTargets(g2);

		else if (paintTargetValue)
			paintTargetValue(g2);

		else if (paintFigValue)
			paintFigValue(g2);

	}

	private void paintFigValue(Graphics2D g2) {

		g2.setBackground(Color.WHITE);

		Map<Rectangle2D.Double, Double> recs = new HashMap<Rectangle2D.Double, Double>();

		int maxX = getWidth();
		int maxY = getHeight();

		g2.clearRect(0, 0, maxX, maxY);

		double min = (maxX > maxY) ? maxY : maxX;

		double size = min / (FIELDS_PER_LINE + 1);

		double padX = (maxX - (FIELDS_PER_LINE * size)) / 2;
		double padY = (maxY - (FIELDS_PER_LINE * size)) / 2;

		for (int x = 0; x < FIELDS_PER_LINE; x++) {
			for (int y = 0; y < FIELDS_PER_LINE; y++) {

				double rx = x * size + padX;
				double ry = y * size + padY;
				double rw = size;
				double rh = size;

				recs.put(new Rectangle2D.Double(rx, ry, rw, rh), figValue[x][y]);

			}

		}

		for (Map.Entry<Rectangle2D.Double, Double> e : recs.entrySet()) {

			float f = e.getValue().floatValue();
			g2.setColor(new Color(f, f, f));
			g2.fill(e.getKey());

			g2.setColor(Color.RED);
			g2.draw(e.getKey());

			g2.setFont(new Font("Verdana", Font.PLAIN, 15));
			g2.drawString(

			e.getValue() + "",

			new Double(e.getKey().x + 5).floatValue(),

			new Double(e.getKey().y + g2.getFontMetrics().getHeight())
					.floatValue());

		}
	}

	private void paintNumTargets(Graphics2D g2) {

		g2.setBackground(Color.WHITE);

		Map<Rectangle2D.Double, Double> recs = new HashMap<Rectangle2D.Double, Double>();

		int maxX = getWidth();
		int maxY = getHeight();

		g2.clearRect(0, 0, maxX, maxY);

		double min = (maxX > maxY) ? maxY : maxX;

		double size = min / (FIELDS_PER_LINE + 1);

		double padX = (maxX - (FIELDS_PER_LINE * size)) / 2;
		double padY = (maxY - (FIELDS_PER_LINE * size)) / 2;

		for (int x = 0; x < FIELDS_PER_LINE; x++) {
			for (int y = 0; y < FIELDS_PER_LINE; y++) {

				double rx = x * size + padX;
				double ry = y * size + padY;
				double rw = size;
				double rh = size;

				recs.put(new Rectangle2D.Double(rx, ry, rw, rh),
						numTargets[x][y]);

			}

		}

		for (Map.Entry<Rectangle2D.Double, Double> e : recs.entrySet()) {

			float f = e.getValue().floatValue();
			g2.setColor(new Color(f, f, f));
			g2.fill(e.getKey());

			g2.setColor(Color.RED);
			g2.draw(e.getKey());

			g2.setFont(new Font("Verdana", Font.PLAIN, 15));
			g2.drawString(

			e.getValue() + "",

			new Double(e.getKey().x + 5).floatValue(),

			new Double(e.getKey().y + g2.getFontMetrics().getHeight())
					.floatValue());

		}
	}

	private void paintTargetValue(Graphics2D g2) {

		g2.setBackground(Color.WHITE);

		Map<Rectangle2D.Double, Double> recs = new HashMap<Rectangle2D.Double, Double>();

		int maxX = getWidth();
		int maxY = getHeight();

		g2.clearRect(0, 0, maxX, maxY);

		double min = (maxX > maxY) ? maxY : maxX;

		double size = min / (FIELDS_PER_LINE + 1);

		double padX = (maxX - (FIELDS_PER_LINE * size)) / 2;
		double padY = (maxY - (FIELDS_PER_LINE * size)) / 2;

		for (int x = 0; x < FIELDS_PER_LINE; x++) {
			for (int y = 0; y < FIELDS_PER_LINE; y++) {

				double rx = x * size + padX;
				double ry = y * size + padY;
				double rw = size;
				double rh = size;

				recs.put(new Rectangle2D.Double(rx, ry, rw, rh),
						targetValue[x][y]);

			}

		}

		for (Map.Entry<Rectangle2D.Double, Double> e : recs.entrySet()) {

			float f = e.getValue().floatValue();
			g2.setColor(new Color(f, f, f));
			g2.fill(e.getKey());

			g2.setColor(Color.RED);
			g2.draw(e.getKey());

			g2.setFont(new Font("Verdana", Font.PLAIN, 15));
			g2.drawString(

			e.getValue() + "",

			new Double(e.getKey().x + 5).floatValue(),

			new Double(e.getKey().y + g2.getFontMetrics().getHeight())
					.floatValue());

		}
	}

	private void testNumTargets() {
		System.out.println("num targets\n");
		numTargets = qualify(transform(getNumTargets()));
		System.out.println(makeString(numTargets));
		paintNumTargets = true;
	}

	private void testTargetValue() {
		System.out.println("target value\n");
		targetValue = qualify(transform(getTargetValue()));
		System.out.println(makeString(targetValue));
		paintTargetValue = true;
	}

	private void testFigValue() {
		System.out.println("fig value\n");
		figValue = qualify(transform(getFigValue()));
		System.out.println(makeString(figValue));
		paintFigValue = true;// TODO Auto-generated method stub

	}
}
