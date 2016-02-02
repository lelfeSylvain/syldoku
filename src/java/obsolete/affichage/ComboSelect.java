package org.sylves.affichage;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Created on Jul 19, 2005
 *
 */

/**
 * @author HackTrack
 *
 */
public class ComboSelect extends JFrame {
	private Container c;
	private DefaultComboBoxModel comboModel;
	private JComboBox combo;
	private JTextField display;

	public ComboSelect() {
		super("Démo de JComboBox");
		initialize();
	}

	private void initialize() {
		c = getContentPane();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		comboModel = new DefaultComboBoxModel();
		comboModel.addElement("HackTrack");
		comboModel.addElement("Java");
		comboModel.addElement("Duke");
		combo = new JComboBox(comboModel);
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.setText((String)combo.getSelectedItem());
			}
		});
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(320, 200));
		jp.add(combo);
		display = new JTextField(10);
		jp.add(display);
		c.add(jp);
	}

	public static void main(String[] args) {
		ComboSelect select = new ComboSelect();
		select.pack();
		select.setVisible(true);
	}
}