package org.sylves.affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
/**
 * Construit un composant JPanel représentant une grille de SUdoku.
 * Cette grille peut être initialisée (ComposantGrilleSudoku(String chaine, boolean enabled) lors de la construction,
 * soit en utilisant setGrille(String maGrille) à tout moment).
 * Le composant gère lui-même les événements qui se produisent sur sa surface.
 * On peut modifier les valeurs de chaque case en cliquant simplement.
 * On peut désactiver la modification des valeurs.
 * */
@SuppressWarnings("serial")
public class ComposantGrilleSudoku extends JPanel implements MouseListener {
	//Booléen pour vérifier si la grille peut être modifiée
	//private boolean enabled =false;

	// Constantes pour la mise en page de la grille
	private   int frmLargeur = 500; // fenêtre
	private   int frmHauteur = 350;
	private   int espBtnHauteur = 0;
	private   int ligLargeur = 8; // espace entre deux séries de 3 lignes
	private   int ligHauteur = 8;
	private   int btnLargeur = 45;//
	private   int btnHauteur = 20;

	//Composants graphique de la grille de Sudoku
	private JButton[] tabbutton = new JButton[81] ;
	private FlowLayout flayout;
	private GridLayout glayout;
	private JPanel fPane;
	private JPanel gPane;
	private GridLayout layout;

	/** tableau du Sudoku */
	private int[] monTab = new int[81];
	/** Données statistiques à suivre*/
	private int nbIndices =0;
//	 Application Parent
	GrilleSudoku monAppli = null;
	/**
	 * @return le nombre d'indice figurant sur la grille visible à l'écran
	 */
	public int getNbIndices() {
		return nbIndices;
	}
	/**
	 * incrémente le nombre d'indice figurant sur la grille visible à l'écran
	 */
	private void incNbIndices() {
		nbIndices++;
		monAppli.actualiserIndice();
	}
	/**
	 * décrémente le nombre d'indice figurant sur la grille visible à l'écran
	 */
	private void decNbIndices() {
		nbIndices--;
		if (nbIndices<0)
			nbIndices=0;
		monAppli.actualiserIndice();
	}

	/*****************************
	 * Constructeur : construit une grille vierge
	 *
	 */
	public ComposantGrilleSudoku(GrilleSudoku a){
		//grille vide
		this(a,"000000000000000000000000000000000000000000000000000000000000000000000000000000000",true);
	}
	/**
	 * Constructeur : Construit le composant avec les paramètres grille qui contient la grille
	 * et enabled qui autorise/interdit la modification de la grille
	 * @param grille sous la forme d'une chaine de caractère constituée de 81 chiffres sans séparation
	 * @param enabled est à vrai si l'on veut autoriser la modification de la grille, à faux sinon
	 * */
	public ComposantGrilleSudoku(GrilleSudoku a,String grille, boolean enabled){
		super();
		//reference à l'appli parent
		monAppli=a;
		construireGrille();
		setGrille(grille);
		this.setMaximumSize(new Dimension(frmLargeur,frmHauteur));
		// indique si la grille est modifiable
		//this.enabled = enabled;
		setEnabled(enabled);
		this.setToolTipText("Cliquez sur un bouton pour augmenter sa valeur.");

		this.setBorder(new TitledBorder(BorderFactory.createEtchedBorder(), ""));
	}
	/******************************
	 * Accesseur pour affecter une grille
	 * @param maGrille sous la forme d'une chaine de caractère constituée de 81 chiffres sans séparation
	 */
	public void setGrille(String maGrille){
		int i;
		nbIndices=0;
		for(i=0;i<tabbutton.length;i++){
			monTab[i]=maGrille.charAt(i)-48;
			if (monTab[i]==0)
				monTab[i]=47;
			else
				incNbIndices();
			tabbutton[i].setText(""+ (char) (monTab[i]+48));
		}
	}
	/******************************
	 * Accesseur pour colorer une grille avec la couleur passée en paramètre
	 * @param maGrille sous la forme d'une chaine de caractère constituée de 81 chiffres sans séparation \n les 0 ne seront pas colorés
	 * @param couleur : la couleur choisie pour les numéros
	 */
	public void setCouleur(String maGrille, Color maCouleur){
		int i;
		for(i=0;i<tabbutton.length;i++){
			if (maGrille.charAt(i)-48!=0)
					tabbutton[i].setForeground(maCouleur);
		}
	}
	/******************************
	 * Accesseur pour lire une grille
	 * @ return maGrille sous la forme d'une chaine de caractère constituée de 81 chiffres sans séparation
	 */
	public String getGrille(){
		int i;
		String maGrille="";
		int tampon;
		for (i=0;i<tabbutton.length;i++){
			tampon=tabbutton[i].getText().charAt(0)-48;
			if (tampon==47)
				tampon=0;
			maGrille+=tampon;
		}
		return maGrille;
	}

	/************************
	 * Construit la grille sous forme de JPanel
	 *
	 */
	private void construireGrille(){
		int i; //indice du tableau
		// groupe de trois boutons
		fPane = new JPanel();
		flayout = new FlowLayout();
		fPane.setLayout(flayout);
		// groupe de trois lignes de 9 boutons
		gPane = new JPanel();
		glayout = new GridLayout(3,3,ligLargeur,espBtnHauteur) ; //Instanciation du layout
		gPane.setLayout(glayout);
		// layout principal
		layout = new GridLayout(3,1,0,ligHauteur);
		setLayout(layout);
		// création de la grille de bouton
		for (i=0;i<tabbutton.length;i++){
			//initialisation de la grille de bouton à partir de la grille passée en paramètre

			tabbutton[i] = new JButton( );
			tabbutton[i].setName(Integer.toString(i));
			tabbutton[i].setPreferredSize(new Dimension(btnLargeur,btnHauteur));
			tabbutton[i].addMouseListener(this) ;
			fPane.add(tabbutton[i]);
			if ((i+1)% 3 ==0 ){//tous les trois boutons on ajoute un espacement en renouvelant le GridPanel
				gPane.add(fPane);
				fPane = new JPanel();
				if ((i+1)% 27 ==0 ) {// mais tous les 3 lignes on enregistre ces lignes et on recommence
					ajouteUnBlanc();
				}
			}
		}
	}


	/** ajoute un espacement dans la grille de sudoku*/
	private void ajouteUnBlanc() {
		add(gPane);// on ajoute le groupe de trois lignes au layout principale
		gPane = new JPanel();
		gPane.setLayout(glayout);
	}
	/***************************
	 * Gestion des événements
	 * Teste si la modification est autorisée
	 */
	public void mouseClicked(MouseEvent e){
		// aiguillage évenementiel
		// attention il n'y a pas de contrôle de la nature de l'événement génénré
		if (isEnabled()) {// les modifications de la grille sont autorisées
			int numBouton;
			char numero;
			JButton boutonClique;
			boutonClique= (JButton) e.getSource();//recherche du bouton cliqué
			// ici on pourrait travailler directement avec le
			//bouton renvoyé par e.getSourve() cad boutonClique...	mais j'en ai besoin pour
			// faire évoluer ma grille miroir.
			numBouton= Integer.parseInt(boutonClique.getName());//numéro du bouton cliqué
			// fait évoluer l'affichage du bouton de 1
			// et revient à 0 après 9
			numero=  tabbutton[numBouton].getText().charAt(0) ;
			if (e.getButton()==1) {// clic bouton gauche
				if (numero=='9'){
					numero='_';
					decNbIndices();
				}
				else if (numero=='_'){
					numero = '1';
					incNbIndices();
				}
				else
					numero++;
			}//clic avec le bouton droit
			else if (e.getButton()==3) {
				if (numero=='1'){
					numero='_';
					decNbIndices();
				}
				else if (numero=='_'){
					numero = '9';
					incNbIndices();
				}
				else
					numero--;
			} else //clic avec le bouton du milieu
				if (numero!='_'){
					numero='_';
					decNbIndices();
			}
			tabbutton[numBouton].setText("" + numero);
			monTab[numBouton]= numero - 48;
		}// pas de modif de la grille autorisée
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
