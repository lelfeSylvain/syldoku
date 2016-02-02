package org.sylves.affichage;
//biblioth�ques pour le GUI

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 * Construit une fen�tre swing qui h�rite de JFrame et qui utilise le composant ComposantGrilleSudoku
 * */
@SuppressWarnings("serial")
public class GrilleSudoku extends JFrame implements InterfaceObservateurResolution, ComposantGrilleSudokuAbstrait{

	// Constantes pour la mise en page de la grille
	protected  int frmLargeur = 505; // fen�tre
	protected  int frmHauteur = 350;

	protected ComposantGrilleSudoku container = null;//D�claration de l�objet sudoku

	/**Composant du dessous*/
	protected ComposantResoudre compDessous = null;
	/** Composant � Droite (stat) */
	protected ComposantStat compADroite=null;
	/** Composant menu */
	protected ComposantMenuPcpal menuBar=null;

	protected GridBagLayout layout = null ;//D�claration de notre layout
	protected GridBagConstraints gbc = null ; // COntrainte de disposition des widgets de la fen�tre
	protected JPanel monPanel = null;// notre JPanel principal

	/*
	 *  Retourne la possibilit� de modifier la grille de Sudoku
	 *  La javadoc est d�j� �crite dans l'interface
	 */
	public boolean isSudokuEnabled() {
		if (container==null)
					return false;
		else
			return container.isEnabled();
	}

	/*
	 * param enabled permet d'autoriser/interdir la modification de la grille de Sudoku
	 * La javadoc est d�j� �crite dans l'interface
	 */
	public void setSudokuEnabled(boolean enabled) {
		if (container!=null)
			container.setEnabled(enabled);
	}

	/*****************************
	 * Constructeur : Construit une fen�tre avec une grillle vierge
	 *
	 */
	public GrilleSudoku(){

		super();
		//grille vide
		String machaine = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		construireFenetre(machaine,true,"SylDoku : Grille vierge");
	}

	/*****************************
	 * Constructeur : Construit une fen�tre avec la grille pass�e en param�tre et la fen�tre prend le titre titre
	 *@param grille : la grille sous la forme d'une chaine de caract�re constitu�e de 81 chiffres sans s�paration
	 *@param titre : titre de la fen�tre
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
	 * Proc�dures de contruction de la fen�tre graphique
	 *@param grille : la grille sous la forme d'une chaine de caract�re constitu�e de 81 chiffres sans s�paration
	 *@param enabled : autorise/interdit la modification du composant grilleSudoku
	 *@param titre : titre de la fen�tre
	 */
	private void construireFenetre(String grille, boolean enabled, String titre){
		//cr�ation du layout et de ses contraintes
		//pattern patron de m�thode
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

		this.setTitle(titre); //On donne un titre � l�application
		//		dimensionnerLaFenetre();
		ajouterMenu();
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //On dit quoi faire lors du clic sur la croix de la fenetre

	}

	protected void colorerGrille(String grille) {
		container.setCouleur(grille, Color.BLACK);
	}

	protected void ajouterTitreContainer() {
//		m�thode � surcharger
	}

	protected void ajouterComposantEnDessous() {
		//m�thode � surcharger
	}
	protected void ajouterComposantADroite() {
		//m�thode � surcharger
	}

	protected void dimensionnerLaFenetre(int onClose,boolean sizable, int l, int h) {//m�thode � surcharger
		this.setSize(l,h); //On donne une taille � notre fen�tre
		this.setResizable(sizable) ; //On interdit/autorise la redimensionnement de l��cran
		this.setDefaultCloseOperation(onClose); //On dit quoi faire lors du clic sur la croix de la fenetre
		this.setLocation(0,0);
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



}