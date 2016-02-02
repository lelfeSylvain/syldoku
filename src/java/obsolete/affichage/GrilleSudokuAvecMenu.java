package org.sylves.affichage;
//bibliothèques pour le GUI

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;




import org.sylves.Controleur.InterfaceControleurSyldoku;
import org.sylves.syldoku.suNoyau.Convert;
import org.sylves.syldoku.suNoyau.InterfaceModeleResolution;
import org.sylves.syldoku.suNoyau.ResolutionGrille;
/**
 *	Fenêtre principale de l'application Syldoku
 */
public class GrilleSudokuAvecMenu extends GrilleSudoku implements ActionListener, InterfaceObservateurResolution{


	private static final long serialVersionUID = 1L;
	//paramètres de la fenêtre

	/** Sujet de l'observateur : le modèle du MVC */
	@SuppressWarnings("unused")
	private InterfaceModeleResolution modele;
	/** Controleur du MVC */
	private InterfaceControleurSyldoku controleur;


	/*****************************
	 * Constructeur : a besoin des références au controleur et au modèle
	 *
	 */

	public GrilleSudokuAvecMenu(InterfaceModeleResolution modele, InterfaceControleurSyldoku controleur){
		super();
		this.modele=modele;
		modele.enregistrerObservateur(this);
		this.controleur=controleur;


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l’application de se fermer lors du clic sur la croix
		this.setVisible(true);//la fenêtre apparaît
	}

	protected void dimensionnerLaFenetre() {
		this.setSize(730,385); //On donne une taille à notre fenêtre
		this.setLocation(0,0); //On place la fenêtre sur l’écran en haut à gauche
	}

	protected void ajouterComposantEnDessous() {
		compDessous = new ComposantResoudre(this);
		monPanel.add(compDessous);
		layout.setConstraints(compDessous, gbc);
		/*gbc.gridwidth=1;
		gbc.anchor=GridBagConstraints.FIRST_LINE_START;
		gbc.fill=GridBagConstraints.NONE;
		layout.setConstraints(compDessous,gbc);*/

	}

	protected void ajouterComposantADroite(){
		compADroite = new ComposantStat(this);
		monPanel.add(compADroite);
		/*gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbc.anchor=GridBagConstraints.FIRST_LINE_START;
		gbc.fill=GridBagConstraints.NONE;*/
		layout.setConstraints(compADroite,gbc);
	}
	/***************************
	 * Ajoute le menu principal dans la fenêtre principale
	 */
	protected void ajouterMenu() {
		menuBar = new ComposantMenuPcpal(this) ;
		this.setJMenuBar(menuBar) ;
	}
	/*********************
	 *
	 * fermeture de l'application
	 */
	public void conclusion(){
		//JOptionPane.showMessageDialog(null,"En êtes-vous sur ?");
		System.exit(0);
	}
	/*********************
	 * Appel au controleur qui va contacter le modèle pour la résolution de la grille de Sudoku
	 * actuellement affiché à l'écran
	 */
	public void calculer() {
		container.setEnabled(false);
		controleur.setGrille(Convert.sans2avec(container.getGrille()));
		//JOptionPane.showMessageDialog(null,container.getGrille());
		controleur.resoudreGrille(compADroite.getProfondeur());
	}

	/***************************
	 * Gestion des événements de la fenêtre principale de l'application
	 */
	public void actionPerformed(ActionEvent e){
		// aiguillage évenementiel inutile car chaque composant gère sa propre partie
		//TODO si redimensionnement imposer une taille minimum
		JOptionPane.showMessageDialog(this,e.getActionCommand());
	}
	/**
	 * Remet à zéro la grille
	 * */
	public void raz() {
		container.setGrille("000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		container.setEnabled(true);
	}

	/***************************
	 * Ouvre une boîte de dialogue pour l'ouverture d'une grille sauvegardée
	 */
	public void ouvrir() {
		// procédure muette en attendant de gérer les E/S
		String maGrille=("000000209300800000000000400730060000000029000500000000069400000000500010000008000");
		container.setEnabled(true);
		container.setGrille(maGrille);

	}
	/***************************
	 * Cette méthode met à jour la vue en fonction des modifs du modèle
	 */
	public void actualiser() {
		int nbsol= modele.getGrille().getLesSolutions().size();

		compADroite.lblNbGrilleTrouvee.setText("Nombre de grille trouvée : " + nbsol );
		compADroite.lblEstResolue.setText("Résolution achevée !" );
		compADroite.lblProfondeur.setText("Profondeur max : " + ResolutionGrille.maxprofondeur );

		compADroite.comboModel.removeAllElements();

		if (nbsol>0) {
			for (int i=0;i<nbsol;i++){
				compADroite.comboModel.addElement("solution n°"+String.valueOf(i+1));
			}
			compADroite.combo.setEnabled(true);
			compADroite.lblAucuneGrille.setVisible(false);
		}
		else {
			compADroite.combo.setEnabled(false);
			compADroite.lblAucuneGrille.setVisible(true);
		}
	}

	public void afficherSolution(int selectedIndex) {
		GrilleSudoku solution = new GrilleSudoku(Convert.avec2sans(modele.getGrille().getSolution(selectedIndex)), compADroite.combo.getSelectedItem().toString());
		solution.setVisible(true);

	}
	/** observateur de changement d'indice */
	public void actualiserIndice() {
		compADroite.actualiserIndice();
	}

	public void editer() {
		container.setEnabled(true);

	}
}