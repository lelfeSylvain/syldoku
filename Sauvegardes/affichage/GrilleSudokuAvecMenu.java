package org.sylves.affichage;
//biblioth�ques pour le GUI

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
 *	Fen�tre principale de l'application Syldoku
 */
public class GrilleSudokuAvecMenu extends GrilleSudoku implements ActionListener, InterfaceObservateurResolution{


	private static final long serialVersionUID = 1L;
	//param�tres de la fen�tre

	/** Sujet de l'observateur : le mod�le du MVC */
	@SuppressWarnings("unused")
	private InterfaceModeleResolution modele;
	/** Controleur du MVC */
	private InterfaceControleurSyldoku controleur;


	/*****************************
	 * Constructeur : a besoin des r�f�rences au controleur et au mod�le
	 *
	 */

	public GrilleSudokuAvecMenu(InterfaceModeleResolution modele, InterfaceControleurSyldoku controleur){
		super();
		this.modele=modele;
		modele.enregistrerObservateur(this);
		this.controleur=controleur;


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit � l�application de se fermer lors du clic sur la croix
		this.setVisible(true);//la fen�tre appara�t
	}

	protected void dimensionnerLaFenetre() {
		this.setSize(730,385); //On donne une taille � notre fen�tre
		this.setLocation(0,0); //On place la fen�tre sur l��cran en haut � gauche
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
	 * Ajoute le menu principal dans la fen�tre principale
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
		//JOptionPane.showMessageDialog(null,"En �tes-vous sur ?");
		System.exit(0);
	}
	/*********************
	 * Appel au controleur qui va contacter le mod�le pour la r�solution de la grille de Sudoku
	 * actuellement affich� � l'�cran
	 */
	public void calculer() {
		container.setEnabled(false);
		controleur.setGrille(Convert.sans2avec(container.getGrille()));
		//JOptionPane.showMessageDialog(null,container.getGrille());
		controleur.resoudreGrille(compADroite.getProfondeur());
	}

	/***************************
	 * Gestion des �v�nements de la fen�tre principale de l'application
	 */
	public void actionPerformed(ActionEvent e){
		// aiguillage �venementiel inutile car chaque composant g�re sa propre partie
		//TODO si redimensionnement imposer une taille minimum
		JOptionPane.showMessageDialog(this,e.getActionCommand());
	}
	/**
	 * Remet � z�ro la grille
	 * */
	public void raz() {
		container.setGrille("000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		container.setEnabled(true);
	}

	/***************************
	 * Ouvre une bo�te de dialogue pour l'ouverture d'une grille sauvegard�e
	 */
	public void ouvrir() {
		// proc�dure muette en attendant de g�rer les E/S
		String maGrille=("000000209300800000000000400730060000000029000500000000069400000000500010000008000");
		container.setEnabled(true);
		container.setGrille(maGrille);

	}
	/***************************
	 * Cette m�thode met � jour la vue en fonction des modifs du mod�le
	 */
	public void actualiser() {
		int nbsol= modele.getGrille().getLesSolutions().size();

		compADroite.lblNbGrilleTrouvee.setText("Nombre de grille trouv�e : " + nbsol );
		compADroite.lblEstResolue.setText("R�solution achev�e !" );
		compADroite.lblProfondeur.setText("Profondeur max : " + ResolutionGrille.maxprofondeur );

		compADroite.comboModel.removeAllElements();

		if (nbsol>0) {
			for (int i=0;i<nbsol;i++){
				compADroite.comboModel.addElement("solution n�"+String.valueOf(i+1));
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