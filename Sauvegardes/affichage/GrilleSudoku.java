package org.sylves.affichage;
//bibliothèques pour le GUI

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 * Construit une fenêtre swing qui hérite de JFrame et qui utilise le composant ComposantGrilleSudoku
 * */
@SuppressWarnings("serial")
public class GrilleSudoku extends JFrame implements InterfaceObservateurResolution, ComposantGrilleSudokuAbstrait{

	// Constantes pour la mise en page de la grille
	protected  int frmLargeur = 505; // fenêtre
	protected  int frmHauteur = 350;

	protected ComposantGrilleSudoku container = null;//Déclaration de l’objet sudoku

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
		if (container==null)
					return false;
		else
			return container.isEnabled();
	}

	/*
	 * param enabled permet d'autoriser/interdir la modification de la grille de Sudoku
	 * La javadoc est déjà écrite dans l'interface
	 */
	public void setSudokuEnabled(boolean enabled) {
		if (container!=null)
			container.setEnabled(enabled);
	}

	/*****************************
	 * Constructeur : Construit une fenêtre avec une grillle vierge
	 *
	 */
	public GrilleSudoku(){

		super();
		//grille vide
		String machaine = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		construireFenetre(machaine,true,"SylDoku : Grille vierge");
	}

	/*****************************
	 * Constructeur : Construit une fenêtre avec la grille passée en paramètre et la fenêtre prend le titre titre
	 *@param grille : la grille sous la forme d'une chaine de caractère constituée de 81 chiffres sans séparation
	 *@param titre : titre de la fenêtre
	 */
	public GrilleSudoku(String grille, String titre){
		super();
		construireFenetre(grille,false,titre);
	}
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

	/******************************
	 * Procédures de contruction de la fenêtre graphique
	 *@param grille : la grille sous la forme d'une chaine de caractère constituée de 81 chiffres sans séparation
	 *@param enabled : autorise/interdit la modification du composant grilleSudoku
	 *@param titre : titre de la fenêtre
	 */
	private void construireFenetre(String grille, boolean enabled, String titre){
		//création du layout et de ses contraintes
		//pattern patron de méthode
		monPanel = new JPanel();//panneau principal
		layout = new GridBagLayout();//layout principal
		gbc = new GridBagConstraints();//contrainte du layout
		gbc.ipadx=0;
		gbc.ipady=0;
		construire_contrainte(gbc, 0, 0, 1, 1, 0.8, 1, GridBagConstraints.NONE,GridBagConstraints.FIRST_LINE_START);
		// affectation du layout principale au panneau principal
		monPanel.setLayout(layout);
		// ajout du composant SUdoku
		container = new ComposantGrilleSudoku(this,grille,enabled);
		ajouterTitreContainer();
		colorerGrille( grille);
		monPanel.add(container);
		layout.setConstraints(container,gbc);
		construire_contrainte(gbc, 1, 0, 1, 1, 0.1, 1, GridBagConstraints.NONE,GridBagConstraints.FIRST_LINE_START);
		ajouterComposantADroite();
		construire_contrainte(gbc, 0, 1, 1, 1, 1, 1, GridBagConstraints.NONE,GridBagConstraints.FIRST_LINE_START);
		ajouterComposantEnDessous();
		this.setContentPane(monPanel) ;//On lui dit de mettre le panel en fond

		this.setTitle(titre); //On donne un titre à l’application
		//		dimensionnerLaFenetre();
		ajouterMenu();
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //On dit quoi faire lors du clic sur la croix de la fenetre

	}

	protected void colorerGrille(String grille) {
		container.setCouleur(grille, Color.BLACK);
	}

	protected void ajouterTitreContainer() {
//		méthode à surcharger
	}

	protected void ajouterComposantEnDessous() {
		//méthode à surcharger
	}
	protected void ajouterComposantADroite() {
		//méthode à surcharger
	}

	protected void dimensionnerLaFenetre(int onClose,boolean sizable, int l, int h) {//méthode à surcharger
		this.setSize(l,h); //On donne une taille à notre fenêtre
		this.setResizable(sizable) ; //On interdit/autorise la redimensionnement de l’écran
		this.setDefaultCloseOperation(onClose); //On dit quoi faire lors du clic sur la croix de la fenetre
		this.setLocation(0,0);
	}

	protected void ajouterMenu(){
		//méthode à surcharger
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