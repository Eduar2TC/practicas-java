package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MoveAnimTest extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		for (int i = 1; i < 5; i *= 2) {
			new Thread(new MoveAnimTest(i)).start();
		}

	}

	public MoveAnimTest(int buffer) {
		JFrame f = new JFrame("Move Animation Test");
		f.setSize(800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		// setExtendedState(Frame.MAXIMIZED_BOTH);
		f.setContentPane(this);
		f.setVisible(true);
		f.setTitle(buffer + "");
		f.createBufferStrategy(buffer);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		double maxX = getWidth();
		double maxY = getHeight();

		g2.setBackground(Color.BLACK);

		g2.clearRect(0, 0, (int) maxX, (int) maxY);

		g2.setColor(Color.WHITE);

		g2.fill(new Arc2D.Double(x, y, 100, 100, 0, 360, Arc2D.OPEN));
	}

	private double x = 0;
	private double y = 0;
	private double stepX = 1;
	private double stepY = 1;
	private long time = 10;

	public void run() {

		double maxX = getWidth();
		double maxY = getHeight();

		while (true) {

			maxX = getWidth();
			maxY = getHeight();

			if (x < 0 | x > maxX - 100)
				stepX = -stepX;

			x += stepX;

			if (y < 0 | y > maxY - 100)
				stepY = -stepY;

			y += stepY;

			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			repaint();
		}

	}
}
