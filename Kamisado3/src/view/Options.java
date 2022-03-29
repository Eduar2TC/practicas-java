package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import control.Kamisado;

public class Options extends JPanel {

	private static final long serialVersionUID = 1L;
	private View view;
	private GridBagConstraints gbc;

	public Options(View view) {
		this.view = view;
		setBorder(BorderFactory.createTitledBorder("Optionen"));

		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		gbc = new GridBagConstraints();

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		int pad = 10;
		gbc.insets.set(pad, pad, pad, pad);

		makeListTest();
		makeWait();
		makeKISlider();
	}

	private void makeKISlider() {

		final JLabel label = new JLabel("KI Level " + Kamisado.START_KI_LEVEL);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;

		add(label, gbc);

		final JSlider slider = new JSlider(1, 29, Kamisado.START_KI_LEVEL);

		slider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				view.setKILevel(slider.getValue());
				label.setText("KI Level " + slider.getValue());
			}
		});

		slider.setMajorTickSpacing(7);
		slider.setMinorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;

		add(slider, gbc);
	}

	private void makeWait() {
		final JButton continueButton = new JButton("Weiter");
		continueButton.setEnabled(false);

		continueButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				view.continueGame();
			}
		});

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;

		add(continueButton, gbc);

		JCheckBox cb1 = new JCheckBox("KI Warten");

		cb1.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					view.setOptionWait(true);
					continueButton.setEnabled(true);
				} else {
					view.setOptionWait(false);
					continueButton.setEnabled(false);
				}
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;

		add(cb1, gbc);
	}

	private void makeListTest() {
		JButton test = new JButton("list test");

		test.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				view.addLog("test test test");
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;

		add(test, gbc);
	}

}
