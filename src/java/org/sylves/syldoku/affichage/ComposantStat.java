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

/** Constructeur : n�cessite une r�ference � l'appli qui l'utilise */
public ComposantStat(FrmGrilleSudokuAvecMenu appli) {
	super();
//	affectation de l'appli parent
	monAppli=appli;

	//Cr�ation du layout principal
	mgrPrincipal = new BoxLayout(this,BoxLayout.Y_AXIS);
	// affectation du layout principale au panneau principal
	setLayout(mgrPrincipal);


	//composant Config (haut du panneau)
	mgrConfig = new GridBagLayout();
	compConfig = new JPanel();
	compConfig.setLayout(mgrConfig);
	compConfig.setToolTipText("Param�tre de la futur recherche");
	compConfig.setBorder(new TitledBorder(BorderFactory.createEtchedBorder(), "Configuration "));
	gbcConfig = new GridBagConstraints();
	gbcConfig.ipadx=0;
	gbcConfig.ipady=0;
	// premi�re ligne du composant COnfig: nombre d'indice
	lblNbIndice= new JLabel("Nombre d'indice : 0");
	lblNbIndice.setToolTipText("affiche le nombre de chiffre visible dans la grille probl�me");
	compConfig.add(lblNbIndice);
	construire_contrainte(gbcConfig, 0, 0, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
	mgrConfig.setConstraints(lblNbIndice,gbcConfig);
	// ligne 2 du composant Config: JSlider pour param�trer la profondeur de recherche
	jsliProfondeur = new JSliProfondeur();
	compConfig.add(jsliProfondeur);
	construire_contrainte(gbcConfig, 0, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.FIRST_LINE_START);
	mgrConfig.setConstraints(jsliProfondeur,gbcConfig);
	//on ajoute le compConfig au panneau principal
	add(compConfig);


	// composant du bas compResultat: un composant pour afficher les stats r�sultats
	compResultat= new JPanel();
	mgrResolue = new GridBagLayout();
	gbcResolue = new GridBagConstraints();
	compResultat.setLayout(mgrResolue);
	// ? ligne du composant r�solue : nb grille trouv�e
	lblNbGrilleTrouvee = new JLabel("Nombre de grille trouv�e : 0");
	compResultat.add(lblNbGrilleTrouvee);
	construire_contrainte(gbcResolue, 0, 4, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
	mgrResolue.setConstraints(lblNbGrilleTrouvee,gbcResolue);
	// ? ligne du composant r�solue :
	lblEstResolue = new JLabel("Pas de r�solution faite.");
	compResultat.add(lblEstResolue);
	construire_contrainte(gbcResolue, 0, 0, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
	mgrResolue.setConstraints(lblEstResolue,gbcResolue);
	//	? ligne du composant r�solue :
	lblProfondeur = new JLabel("Profondeur max : 0");
	compResultat.add(lblProfondeur);
	construire_contrainte(gbcResolue, 0, 2, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
	mgrResolue.setConstraints(lblProfondeur,gbcResolue);

	lblHorloge = new JLabel("Dur�e de la recherche : 0 s");
	compResultat.add(lblHorloge);
	construire_contrainte(gbcResolue, 0, 3, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
	mgrResolue.setConstraints(lblHorloge,gbcResolue);

	// ? ligne du composant r�solue : combobox des grilles r�sulats
	comboModel = new DefaultComboBoxModel();
	combo = new JComboBox(comboModel);
	combo.addItem("Aucune grille trouv�e !");
	combo.addActionListener(this);
	compResultat.add(combo);
	combo.setEnabled(false);
	construire_contrainte(gbcResolue, 0, 5, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
	mgrResolue.setConstraints(combo,gbcResolue);
	compResultat.setBorder(new TitledBorder(BorderFactory.createEtchedBorder(), "R�solution "));
	// Ajout du composant r�solue
	add(compResultat);
}

/** @return la profondeur choisie par l'utilisateur grace au Jslider*/
public int getProfondeur(){

	return jsliProfondeur.getProfondeur();
}
/** G�re les �v�nements du bloc */
public void actionPerformed(ActionEvent e) {
	//		 aiguillage �venementiel
	if ((e.getID()==1001)) {//la combobox a chang� de valeur
		// cet �v�nement arrive aussi lorsqu'on vide la liste
		// aussi il faut d�tecter sur quel �l�ment de la liste
		// j'ai cliqu� !
		if ((combo.getSelectedIndex()>=0) && (combo.isEnabled())){
			monAppli.afficherSolution(combo.getSelectedIndex());

		}
	}
	/*else if (e.getSource()==jsliProfondeur){
			JOptionPane.showMessageDialog(null," VALEUR SELECTIONNEE :" +jsliProfondeur.getValue());
		}*/
	else {//evenement non r�solu
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
		lblNbGrilleTrouvee.setText("Nombre de grille trouv�e : " + nbsol );
		lblEstResolue.setText("R�solution achev�e !" );
		lblProfondeur.setText("Profondeur max : " + ResolutionGrille.maxprofondeur );
		comboModel.removeAllElements();
		for (int i=0;i<nbsol;i++){
			comboModel.addElement("solution n�"+String.valueOf(i+1));
		}
		combo.setEnabled(true);

	}
	else {

		lblNbGrilleTrouvee.setText("Nombre de grille trouv�e : " + nbsol );
		if (estRAZ){
			lblEstResolue.setText("Pas de r�solution faite." );
			lblHorloge.setText("Dur�e de la recherche : 0 s");
		}
		else
			lblEstResolue.setText("R�solution achev�e !" );
		lblProfondeur.setText("Profondeur max : 0"  );
		comboModel.removeAllElements();
		combo.setEnabled(false);
		combo.addItem("Aucune grille trouv�e !");

	}
}

public void topChrono(boolean b) {
	long time=System.currentTimeMillis();
	if (b)
		top=time;
	else {
		top=time-top;
		lblHorloge.setText("Dur�e de la recherche : " + Convert.milli2String(top) );
	}
}

}
