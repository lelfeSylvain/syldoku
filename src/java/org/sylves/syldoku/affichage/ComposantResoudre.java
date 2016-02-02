package org.sylves.syldoku.affichage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class ComposantResoudre extends JPanel implements ActionListener  {
private JButton btnResoudre =null;
private JCheckBox chkVerrouiller = null;
private GridBagLayout mgr = null;
private GridBagConstraints gbc =null;
//Application parente
FrmGrilleSudokuAvecMenu monAppli;

	public ComposantResoudre(FrmGrilleSudokuAvecMenu appli) {
		super();
		mgr = new GridBagLayout();
		gbc = new GridBagConstraints();
		gbc.gridx=GridBagConstraints.RELATIVE;
		gbc.gridy=GridBagConstraints.REMAINDER;
		gbc.gridheight=GridBagConstraints.RELATIVE;
		gbc.gridwidth=GridBagConstraints.RELATIVE;
		gbc.ipadx=30;
		gbc.ipady=0;
		gbc.weightx=0;
		gbc.weighty=1;
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.LINE_START;
		// affectation du layout principale au panneau principal
		setLayout(mgr);

		btnResoudre = new JButton("Résoudre");
		btnResoudre.setToolTipText("Lancer la résolution de la grille");
		btnResoudre.addActionListener(this);
		mgr.setConstraints(btnResoudre,gbc);
		add(btnResoudre);

		chkVerrouiller = new JCheckBox("Grille verrouillée",false);
		chkVerrouiller.setToolTipText("Permet de (dé)verrouiller la grille");
		chkVerrouiller.addActionListener(this);
		//gbc.gridx=2;gbc.anchor=GridBagConstraints.WEST;

		mgr.setConstraints(chkVerrouiller, gbc);
		add(chkVerrouiller);

		JLabel Copyright = new JLabel("(c) L'elfe Sylvain");
		mgr.setConstraints(Copyright,gbc);
		add(Copyright);

		/*JLabel blanc = new JLabel("(c) L'elfe Sylvain");
		gbc.gridx=GridBagConstraints.RELATIVE;
		gbc.anchor=GridBagConstraints.EAST;
		mgr.setConstraints(blanc,gbc);
		add(blanc);*/
		// affectation de l'appli parent
		monAppli=appli;
	}

	public void actionPerformed(ActionEvent e) {
		//		 aiguillage évenementiel
		if (e.getSource()==btnResoudre){
			monAppli.calculer();
			setVerrouiller(true);
		}
		else
			if(e.getSource()==chkVerrouiller)
				setVerrouiller(chkVerrouiller.isSelected());

	}
	public void setVerrouiller(boolean b){
		chkVerrouiller.setSelected(b);
		monAppli.compGrilleVierge.setEnabled(!b);
	}
}
