package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Frame Inhalt mit Spielbrett, Logliste, usw.
 * 
 * @author Harry
 * 
 */
public class Content extends JPanel {

	private static final long serialVersionUID = 1L;

	private GridBagConstraints gbc = new GridBagConstraints();

	private JList<String> list;

	private DefaultListModel<String> listModel;

	private View view;

	public Content(View view) {
		this.view = view;

		setBackground(Color.BLACK);

		setLayout(new GridBagLayout());

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		int pad = 5;
		gbc.insets.set(pad, pad, pad, pad);

		makeStatusLabel();
		makeTimer();
		makeBoard();
		makeLog();
		makeControls();
	}

	private void makeBoard() {
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 4;
		gbc.weightx = 100;
		gbc.weighty = 100;

		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.setBackground(Color.YELLOW);
		Board board = new Board(view);
		panel2.add(board);
		add(panel2, gbc);
	}

	private void makeControls() {
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;

		Options options = new Options(view);

		add(options, gbc);

	}

	private void makeLog() {
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 100;

		listModel = new DefaultListModel<String>();
		listModel.addElement("Move Log");
		list = new JList<String>(listModel);
		// list.setFixedCellWidth(200);
		JScrollPane listScroller = new JScrollPane(list);

		listScroller.setBorder(BorderFactory.createTitledBorder("Log"));

		add(listScroller, gbc);

		view.setLog(list);
	}

	private void makeStatusLabel() {
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;

		JLabel label = new JLabel("Kamisado");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(BorderFactory.createTitledBorder("Status"));
		label.setOpaque(true);
		label.setForeground(Color.RED);

		view.setStatusLabel(label);

		add(label, gbc);
	}

	private void makeTimer() {
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;

		JTextField timer1 = new JTextField("0", 5);
		// timer1.setBorder(BorderFactory.createTitledBorder("WHITE"));
		timer1.setEditable(false);
		JLabel label1 = new JLabel("White");
		JPanel panel1 = new JPanel();
		panel1.add(label1);
		panel1.add(timer1);

		JTextField timer2 = new JTextField("0", 5);
		timer2.setEditable(false);
		JLabel label2 = new JLabel("Black");
		JPanel panel2 = new JPanel();
		panel2.add(label2);
		panel2.add(timer2);

		JPanel timerPanel = new JPanel();
		// timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.Y_AXIS));
		timerPanel.add(panel1);
		timerPanel.add(panel2);
		timerPanel.setBorder(BorderFactory.createTitledBorder("Timer"));

		view.setTimer(timer1, timer2);

		add(timerPanel, gbc);
	}

}
