package test;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GBCTest {

	public static void main(String[] args) {
		new GBCTest();
	}

	public GBCTest() {

		JFrame f = new JFrame("gbc test");
		f.setSize(800, 600);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p = new JPanel();

		GridBagLayout gbl = new GridBagLayout();

		GridBagConstraints gbc = new GridBagConstraints();

		p.setLayout(gbl);
		p.setBackground(Color.RED);

		JLabel l1 = new JLabel("rofl1");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		JButton l2 = new JButton("rofl2");
		JTextField l3 = new JTextField("rofl3");
		l3.setHorizontalAlignment(JTextField.CENTER);
		JLabel l4 = new JLabel("rofl4");

		l1.setOpaque(true);
		l2.setOpaque(true);
		l3.setOpaque(true);
		l4.setOpaque(true);

		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 10;
		gbc.weighty = 1;

		p.add(l1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 3;
		gbc.weighty = 1;

		p.add(l2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;

		p.add(l3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;

		p.add(l4, gbc);

		f.setContentPane(p);

		f.setVisible(true);
	}

}
