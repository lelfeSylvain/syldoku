package org.sylves.syldoku.affichage;
//biblioth�ques pour le GUI

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

import org.sylves.syldoku.suNoyau.InterfaceGrille;


/**
 * Construit une fen�tre swing qui h�rite de JFrame et qui utilise le composant ComposantGrilleSudoku
 * */
@SuppressWarnings("serial")
public class FrmGrilleSudoku extends GrilleSudoku implements WindowListener {

	// Constantes pour la mise en page de la grille
	 int frmLargeur = 505; // fen�tre
	 int frmHauteur = 350;
	 // appli parente
	 GrilleSudoku monAppli = null;
	/*****************************
	 * Constructeur : Construit une fen�tre avec la grille pass�e en param�tre et la fen�tre prend le titre titre
	 *@param grille : la grille sous la forme d'une chaine de caract�re constitu�e de 81 chiffres sans s�paration
	 *@param titre : titre de la fen�tre
	 */
	public FrmGrilleSudoku(InterfaceGrille grille, String titre, GrilleSudoku appli){
		super(grille,titre);
		addWindowListener(this);
		dimensionnerLaFenetre(JFrame.DISPOSE_ON_CLOSE,false,frmLargeur,frmHauteur);
		monAppli = appli;
	}


	/*protected void colorerGrille(String grille) {
		compGrilleVierge.setCouleur(grille, Color.BLACK);
	}*/

	protected void ajouterTitreContainer() {
		TitledBorder monborder = (TitledBorder) compGrilleVierge.getBorder();
		monborder.setTitle("Grille solution");
	}

	protected void ajouterComposantEnDessous() {
		//m�thode n�cessaire pour impl�menter la classe GrilleSudoku qui est abstraite
	}
	protected void ajouterComposantADroite() {
		//m�thode n�cessaire pour impl�menter la classe GrilleSudoku qui est abstraite
	}

	protected void dimensionnerLaFenetre() {
		//m�thode n�cessaire pour impl�menter la classe GrilleSudoku qui est abstraite
		}

	protected void ajouterMenu(){
		//m�thode n�cessaire pour impl�menter la classe GrilleSudoku qui est abstraite
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


public void dispose(GrilleSudoku toto) {
	// TODO Auto-generated method stub

}


@Override
protected void ajouterTitreContainer(String txt) {
	// n/a ici

}

}