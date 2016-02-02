package org.sylves.syldoku.affichage;
//bibliothèques pour le GUI

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.sylves.syldoku.suNoyau.Grille;
import org.sylves.syldoku.suNoyau.InterfaceGrille;
import org.sylves.syldoku.utilitaires.Convert;



/**
 * Construit une fenêtre swing qui hérite de JFrame et qui utilise le composant ComposantGrilleSudoku
 * */
@SuppressWarnings("serial")
public abstract class GrilleSudoku extends JFrame implements InterfaceObservateurResolution, ComposantGrilleSudokuAbstrait{

	//Constantes pour le titre de la grille en cas de grille problème
	protected static final String TXTTITRE = "Grille problème";
	protected static final String TXTTITREVER = "Grille problème (verrouillée)";
	// Constantes pour la mise en page de la grille
	protected  int frmLargeur = 505; // fenêtre
	protected  int frmHauteur = 350;

	protected ComposantGrilleSudoku compGrilleVierge = null;//Déclaration de l’objet sudoku

	/**Composant du dessous*/
	protected ComposantResoudre compDessous = null;
	/** Composant à Droite (stat) */
	protected ComposantStat compADroite=null;
	/** Composant menu */
	protected ComposantMenuPcpal menuBar=null;

	protected GridBagLayout layout = null ;//Déclaration de notre layout
	protected GridBagConstraints gbc = null ; // COntrainte de disposition des widgets de la fenêtre
	protected JPanel monPanel = null;// notre JPanel principal

	/*
	 *  Retourne la possibilité de modifier la grille de Sudoku
	 *  La javadoc est déjà écrite dans l'interface
	 */
	public boolean isSudokuEnabled() {
		if (compGrilleVierge==null)
					return false;
		else
			return compGrilleVierge.isEnabled();
	}

	/*
	 * param enabled permet d'autoriser/interdir la modification de la grille de Sudoku
	 * La javadoc est déjà écrite dans l'interface
	 */
	public void setSudokuEnabled(boolean enabled) {
		if (compGrilleVierge!=null)
			compGrilleVierge.setEnabled(enabled);
	}

	/*****************************
	 * Constructeur : Construit une fenêtre avec une grillle vierge
	 *
	 */
	public GrilleSudoku(){

		super();
		//grille vide
		String machaine = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		construireFenetre(new Grille(machaine),true,"SylDoku : Grille vierge");
	}

	/*****************************
	 * Constructeur : Construit une fenêtre avec la grille passée en paramètre et la fenêtre prend le titre titre
	 *@param grille : la grille sous la forme d'une chaine de caractère constituée de 81 chiffres sans séparation
	 *@param titre : titre de la fenêtre
	 */
	public GrilleSudoku(InterfaceGrille grille, String titre){
		super();
		construireFenetre(grille,false,titre);
	}


	/******************************
	 * Procédures de contruction de la fenêtre graphique
	 *@param grille : la grille sous la forme d'une chaine de caractère constituée de 81 chiffres sans séparation
	 *@param enabled : autorise/interdit la modification du composant grilleSudoku
	 *@param titre : titre de la fenêtre
	 */
	private void construireFenetre(InterfaceGrille grille, boolean enabled, String titre){
		//création du layout et de ses contraintes
		//pattern patron de méthode
		monPanel = new JPanel();//panneau principal
		layout = new GridBagLayout();//layout principal
		gbc = new GridBagConstraints();//contrainte du layout
		gbc.ipadx=0;
		gbc.ipady=0;
		Convert.construire_contrainte(gbc, 0, 0, 1, 1, 0.8, 1, GridBagConstraints.NONE,GridBagConstraints.FIRST_LINE_START);
		// affectation du layout principale au panneau principal
		monPanel.setLayout(layout);
		// ajout du composant SUdoku
		compGrilleVierge = new ComposantGrilleSudoku(this,grille,enabled);
		ajouterTitreContainer(TXTTITRE);
		colorerGrille( grille);
		monPanel.add(compGrilleVierge);
		layout.setConstraints(compGrilleVierge,gbc);
		Convert.construire_contrainte(gbc, 1, 0, 1, 1, 0.1, 1, GridBagConstraints.NONE,GridBagConstraints.FIRST_LINE_START);
		ajouterComposantADroite();
		Convert.construire_contrainte(gbc, 0, 1, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL,GridBagConstraints.FIRST_LINE_START);
		ajouterComposantEnDessous();
		this.setContentPane(monPanel) ;//On lui dit de mettre le panel en fond

		this.setTitle(titre); //On donne un titre à l’application
		//		dimensionnerLaFenetre();
		ajouterMenu();
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //On dit quoi faire lors du clic sur la croix de la fenetre

	}

	protected void colorerGrille(InterfaceGrille grille) {
		compGrilleVierge.setCouleur(grille, Color.BLACK);
	}

	protected abstract void ajouterTitreContainer(String txt);
	protected abstract void ajouterMenu();
	protected abstract void ajouterComposantEnDessous() ;
	protected abstract void ajouterComposantADroite() ;

	protected void dimensionnerLaFenetre(int onClose,boolean sizable, int l, int h) {//méthode à surcharger
		this.setSize(l,h); //On donne une taille à notre fenêtre
		this.setResizable(sizable) ; //On interdit/autorise la redimensionnement de l’écran
		this.setDefaultCloseOperation(onClose); //On dit quoi faire lors du clic sur la croix de la fenetre
		this.setLocation(0,0);
	}



	/** nécessaire pour implémenter le pattern Observateur du MVC
	 *
	 */
	public void actualiser(){
//pour le moment il s'agit d'une méthode nulle
	}
/** observateur de changement d'indice */
	public void actualiserIndice() {
		// TODO Auto-generated method stub

	}





}