package org.sylves.affichage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class ComposantResoudre extends JPanel implements ActionListener  {
private JButton btnResoudre =null;
private GridBagLayout mgr = null;
private GridBagConstraints gbc =null;
//Application parente
FrmGrilleSudokuAvecMenu monAppli;

	public ComposantResoudre(FrmGrilleSudokuAvecMenu appli) {
		super();
		mgr = new GridBagLayout();
		gbc = new GridBagConstraints();
		gbc.gridheight=2;
		gbc.gridwidth=1;
		gbc.ipadx=0;
		gbc.ipady=0;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.anchor=GridBagConstraints.FIRST_LINE_START;
		// affectation du layout principale au panneau principal
		setLayout(mgr);

		btnResoudre = new JButton("Résoudre");
		btnResoudre.setToolTipText("Lancer la résolution de la grille");
		btnResoudre.addActionListener(this);
		add(btnResoudre);
		mgr.setConstraints(btnResoudre,gbc);
		JLabel Copyright = new JLabel("L'elfe Sylvain");
		add(Copyright);
		gbc.anchor=GridBagConstraints.LAST_LINE_END;
		gbc.fill=GridBagConstraints.REMAINDER;
		mgr.setConstraints(Copyright,gbc);
		// affectation de l'appli parent
		monAppli=appli;
	}

	public void actionPerformed(ActionEvent e) {
		//		 aiguillage évenementiel
		if (e.getSource()==btnResoudre)
			monAppli.calculer();
	}

}
