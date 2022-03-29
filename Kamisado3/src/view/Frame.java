package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * GUI Fensterklasse mit Menü und Content.
 * 
 * @author Harry
 * 
 */
public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Content content;

	private View view;

	public Frame(View view) {
		setTitle("Kamisado auf Java - "
				+ "Nullsummen Spiel mit perfekter Information - "
				+ "KI mit Alpha Beta Suche");
		setIconImage(new ImageIcon("images/icon.jpg").getImage());
		this.view = view;
		setSize(1051, 751);
		setBackground(Color.RED);

		addComponents();
		finish();
	}

	private void addComponents() {
		addMenu();
		content = new Content(view);
		setContentPane(content);
	}

	private void addMenu() {
		JMenuBar bar = new JMenuBar();

		JMenu game = new JMenu("Neues Spiel");

		JMenuItem hvh = new JMenuItem("Mensch gegen Mensch");

		hvh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				view.newGameHvH();
			}

		});

		game.add(hvh);

		JMenuItem hvc = new JMenuItem("Mensch gegen Computer");

		hvc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				view.newGameHvC();
			}

		});

		game.add(hvc);

		JMenuItem cvc = new JMenuItem("Computer gegen Computer");

		cvc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				view.newGameCvC();
			}

		});

		game.add(cvc);

		JMenu help = new JMenu("Hilfe");
		JMenuItem about = new JMenuItem("Über Kamisado");
		help.add(about);

		about.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				showInfo();
			}
		});

		bar.add(game);
		bar.add(help);
		setJMenuBar(bar);
	}

	private void showInfo() {
		JOptionPane.showMessageDialog(this, "Kamisado \n osbornx22@gmail.com",
				"Über Kamisado", JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon("images/icon.jpg"));
	}

	private void finish() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		// pack();
		setVisible(true);
	}

}
