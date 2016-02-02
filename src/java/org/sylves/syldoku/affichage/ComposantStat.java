package org.sylves.syldoku.affichage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import org.sylves.syldoku.suNoyau.ResolutionGrille;
import org.sylves.syldoku.utilitaires.Convert;

@SuppressWarnings("serial")
public class ComposantStat extends JPanel implements ActionListener  {


//Application parente
FrmGrilleSudokuAvecMenu monAppli;
//widgets
public DefaultComboBoxModel comboModel;
public JComboBox combo;
public JLabel lblProfondeur;
public JLabel lblEstResolue;
public JLabel lblNbGrilleTrouvee;
public JLabel lblHorloge;
private JLabel lblNbIndice;
//gestion des widgets
private GridBagLayout mgrConfig ;
private GridBagConstraints gbcConfig;
private JSliProfondeur jsliProfondeur;
private JPanel compResultat;
private GridBagLayout mgrResolue;
private GridBagConstraints gbcResolue;
private BoxLayout mgrPrincipal;
private JPanel compConfig;
//gestion du chrono
private long top;

public void construire_contrainte(GridBagConstraints gbc,int gx,int gy, int gw,int gh, double wx,double wy, int fill, int anchor){
	gbc.gridx=gx;
	gbc.gridy=gy;
	gbc.gridwidth=gw;
	gbc.gridheight=gh;
	gbc.weightx=wx;
	gbc.weighty=wy;
	gbc.anchor=anchor;
	gbc.fill=fill;
}

/** Constructeur : nécessite une réference à l'appli qui l'utilise */
public ComposantStat(FrmGrilleSudokuAvecMenu appli) {
	super();
//	affectation de l'appli parent
	monAppli=appli;

	//Création du layout principal
	mgrPrincipal = new BoxLayout(this,BoxLayout.Y_AXIS);
	// affectation du layout principale au panneau principal
	setLayout(mgrPrincipal);


	//composant Config (haut du panneau)
	mgrConfig = new GridBagLayout();
	compConfig = new JPanel();
	compConfig.setLayout(mgrConfig);
	compConfig.setToolTipText("Paramètre de la futur recherche");
	compConfig.setBorder(new TitledBorder(BorderFactory.createEtchedBorder(), "Configuration "));
	gbcConfig = new GridBagConstraints();
	gbcConfig.ipadx=0;
	gbcConfig.ipady=0;
	// première ligne du composant COnfig: nombre d'indice
	lblNbIndice= new JLabel("Nombre d'indice : 0");
	lblNbIndice.setToolTipText("affiche le nombre de chiffre visible dans la grille problème");
	compConfig.add(lblNbIndice);
	construire_contrainte(gbcConfig, 0, 0, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
	mgrConfig.setConstraints(lblNbIndice,gbcConfig);
	// ligne 2 du composant Config: JSlider pour paramétrer la profondeur de recherche
	jsliProfondeur = new JSliProfondeur();
	compConfig.add(jsliProfondeur);
	construire_contrainte(gbcConfig, 0, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.FIRST_LINE_START);
	mgrConfig.setConstraints(jsliProfondeur,gbcConfig);
	//on ajoute le compConfig au panneau principal
	add(compConfig);


	// composant du bas compResultat: un composant pour afficher les stats résultats
	compResultat= new JPanel();
	mgrResolue = new GridBagLayout();
	gbcResolue = new GridBagConstraints();
	compResultat.setLayout(mgrResolue);
	// ? ligne du composant résolue : nb grille trouvée
	lblNbGrilleTrouvee = new JLabel("Nombre de grille trouvée : 0");
	compResultat.add(lblNbGrilleTrouvee);
	construire_contrainte(gbcResolue, 0, 4, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
	mgrResolue.setConstraints(lblNbGrilleTrouvee,gbcResolue);
	// ? ligne du composant résolue :
	lblEstResolue = new JLabel("Pas de résolution faite.");
	compResultat.add(lblEstResolue);
	construire_contrainte(gbcResolue, 0, 0, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
	mgrResolue.setConstraints(lblEstResolue,gbcResolue);
	//	? ligne du composant résolue :
	lblProfondeur = new JLabel("Profondeur max : 0");
	compResultat.add(lblProfondeur);
	construire_contrainte(gbcResolue, 0, 2, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
	mgrResolue.setConstraints(lblProfondeur,gbcResolue);

	lblHorloge = new JLabel("Durée de la recherche : 0 s");
	compResultat.add(lblHorloge);
	construire_contrainte(gbcResolue, 0, 3, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
	mgrResolue.setConstraints(lblHorloge,gbcResolue);

	// ? ligne du composant résolue : combobox des grilles résulats
	comboModel = new DefaultComboBoxModel();
	combo = new JComboBox(comboModel);
	combo.addItem("Aucune grille trouvée !");
	combo.addActionListener(this);
	compResultat.add(combo);
	combo.setEnabled(false);
	construire_contrainte(gbcResolue, 0, 5, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
	mgrResolue.setConstraints(combo,gbcResolue);
	compResultat.setBorder(new TitledBorder(BorderFactory.createEtchedBorder(), "Résolution "));
	// Ajout du composant résolue
	add(compResultat);
}

/** @return la profondeur choisie par l'utilisateur grace au Jslider*/
public int getProfondeur(){

	return jsliProfondeur.getProfondeur();
}
/** Gère les événements du bloc */
public void actionPerformed(ActionEvent e) {
	//		 aiguillage évenementiel
	if ((e.getID()==1001)) {//la combobox a changé de valeur
		// cet événement arrive aussi lorsqu'on vide la liste
		// aussi il faut détecter sur quel élément de la liste
		// j'ai cliqué !
		if ((combo.getSelectedIndex()>=0) && (combo.isEnabled())){
			monAppli.afficherSolution(combo.getSelectedIndex());

		}
	}
	/*else if (e.getSource()==jsliProfondeur){
			JOptionPane.showMessageDialog(null," VALEUR SELECTIONNEE :" +jsliProfondeur.getValue());
		}*/
	else {//evenement non résolu
		JOptionPane.showMessageDialog(null,e.getID() + " - " +e.getActionCommand());

	}
}
public void actualiserIndice() {
	lblNbIndice.setText("Nombre d'indice : " + Integer.toString(monAppli.compGrilleVierge.getNbIndices()));
	actualiserPanneau(0,true);
	//JOptionPane.showMessageDialog(null,"tout va bien "+ Integer.toString(monAppli.container.getNbIndices()));

}
public void actualiserPanneau(int nbsol, boolean estRAZ) {
	if (nbsol>0) {
		lblNbGrilleTrouvee.setText("Nombre de grille trouvée : " + nbsol );
		lblEstResolue.setText("Résolution achevée !" );
		lblProfondeur.setText("Profondeur max : " + ResolutionGrille.maxprofondeur );
		comboModel.removeAllElements();
		for (int i=0;i<nbsol;i++){
			comboModel.addElement("solution n°"+String.valueOf(i+1));
		}
		combo.setEnabled(true);

	}
	else {

		lblNbGrilleTrouvee.setText("Nombre de grille trouvée : " + nbsol );
		if (estRAZ){
			lblEstResolue.setText("Pas de résolution faite." );
			lblHorloge.setText("Durée de la recherche : 0 s");
		}
		else
			lblEstResolue.setText("Résolution achevée !" );
		lblProfondeur.setText("Profondeur max : 0"  );
		comboModel.removeAllElements();
		combo.setEnabled(false);
		combo.addItem("Aucune grille trouvée !");

	}
}

public void topChrono(boolean b) {
	long time=System.currentTimeMillis();
	if (b)
		top=time;
	else {
		top=time-top;
		lblHorloge.setText("Durée de la recherche : " + Convert.milli2String(top) );
	}
}

}
