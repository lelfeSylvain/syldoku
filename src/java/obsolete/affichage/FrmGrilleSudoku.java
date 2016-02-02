package org.sylves.affichage;
//biblioth�ques pour le GUI

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


/**
 * Construit une fen�tre swing qui h�rite de JFrame et qui utilise le composant ComposantGrilleSudoku
 * */
@SuppressWarnings("serial")
public class FrmGrilleSudoku extends GrilleSudoku implements WindowListener, InterfaceObservateurResolution, ComposantGrilleSudokuAbstrait{

	// Constantes pour la mise en page de la grille
	 int frmLargeur = 505; // fen�tre
	 int frmHauteur = 350;
	 // appli parente
	 FrmGrilleSudokuAvecMenu monAppli = null;
	/*****************************
	 * Constructeur : Construit une fen�tre avec la grille pass�e en param�tre et la fen�tre prend le titre titre
	 *@param grille : la grille sous la forme d'une chaine de caract�re constitu�e de 81 chiffres sans s�paration
	 *@param titre : titre de la fen�tre
	 */
	public FrmGrilleSudoku(String grille, String titre, FrmGrilleSudokuAvecMenu appli){
		super(grille,titre);
		addWindowListener(this);
		dimensionnerLaFenetre(JFrame.DISPOSE_ON_CLOSE,false,frmLargeur,frmHauteur);
		monAppli = appli;
	}


	protected void colorerGrille(String grille) {
		container.setCouleur(grille, Color.BLACK);
	}

	protected void ajouterTitreContainer() {
		TitledBorder monborder = (TitledBorder) container.getBorder();
		monborder.setTitle("Grille solution");
	}

	protected void ajouterComposantEnDessous() {
		//m�thode � surcharger
	}
	protected void ajouterComposantADroite() {
		//m�thode � surcharger
	}

	protected void dimensionnerLaFenetre() {
		//this.setSize(this.frmLargeur,this.frmHauteur); //On donne une taille � notre fen�tre
		//this.setResizable(false) ; //On interdit la redimensionnement de l��cran
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //On dit quoi faire lors du clic sur la croix de la fenetre
	}

	protected void ajouterMenu(){
		//m�thode � surcharger
	}


	/** n�cessaire pour impl�menter le pattern Observateur du MVC
	 *
	 */
	public void actualiser(){
//pour le moment il s'agit d'une m�thode nulle
	}
/** observateur de changement d'indice */
	public void actualiserIndice() {
		// TODO Auto-generated method stub

	}

public void windowActivated(WindowEvent arg0) {
	// TODO Auto-generated method stub

}

public void windowClosed(WindowEvent arg0) {
	// TODO Auto-generated method stub

}

public void windowClosing(WindowEvent arg0) {
	monAppli.dispose(this);

}

public void windowDeactivated(WindowEvent arg0) {
	// TODO Auto-generated method stub

}

public void windowDeiconified(WindowEvent arg0) {
	// TODO Auto-generated method stub

}

public void windowIconified(WindowEvent arg0) {
	// TODO Auto-generated method stub

}

public void windowOpened(WindowEvent arg0) {
	// TODO Auto-generated method stub

}

}