package org.sylves.syldoku.affichage;

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

import org.sylves.syldoku.suNoyau.Coordonnee;
import org.sylves.syldoku.suNoyau.Grille;
import org.sylves.syldoku.suNoyau.InterfaceGrille;
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
	private InterfaceGrille monTab;
	/** Données statistiques à suivre*/

//	Application Parent
	GrilleSudoku monAppli = null;
	/**
	 * @return le nombre d'indice figurant sur la grille visible à l'écran
	 */
	public int getNbIndices() {
		return monTab.getNombreIndice();
	}

	/*****************************
	 * Constructeur : construit une grille vierge
	 *
	 */
	public ComposantGrilleSudoku(GrilleSudoku a){
		//grille vide
		this(a,new Grille(),true);
	}
	/**
	 * Constructeur : Construit le composant avec les paramètres grille qui contient la grille
	 * et enabled qui autorise/interdit la modification de la grille
	 * @param grille sous la forme d'une chaine de caractère constituée de 81 chiffres sans séparation
	 * @param enabled est à vrai si l'on veut autoriser la modification de la grille, à faux sinon
	 * */
	public ComposantGrilleSudoku(GrilleSudoku a,InterfaceGrille grille, boolean enabled){
		super();
		//reference à l'appli parent
		monAppli=a;
		construireGrille();

		setGrille(grille);//initialise la grille destinée à l'affichage
		this.setMaximumSize(new Dimension(frmLargeur,frmHauteur));
		// indique si la grille est modifiable
		setEnabled(enabled);
		this.setToolTipText("Cliquez sur un bouton pour augmenter sa valeur.");

		this.setBorder(new TitledBorder(BorderFactory.createEtchedBorder(), ""));
	}
	/******************************
	 * Accesseur pour affecter une grille
	 * @param maGrille sous la forme d'une Grille
	 */
	public void setGrille(InterfaceGrille maGrille){
		int i, j,val,indice=0;
		monTab = new Grille(maGrille);
		for(i=0; i<InterfaceGrille.BORNE;i++)
			for(j=0;j<InterfaceGrille.BORNE;j++){
				val=monTab.getCase(j, i).getVal();
				if (val==0)
					val=47;
				tabbutton[indice].setText(""+ (char) (val+48));
				indice++;
			}
	}
	/******************************
	 * Accesseur pour colorer une grille avec la couleur passée en paramètre
	 * @param maGrille sous la forme d'une Grille
	 * @param couleur : la couleur choisie pour les numéros
	 */
	public void setCouleur(InterfaceGrille maGrille, Color maCouleur){
		int i, j,indice=0;
		for(i=0; i<InterfaceGrille.BORNE;i++)
			for(j=0;j<InterfaceGrille.BORNE;j++,indice++){
				if (maGrille.getCase(j, i).getVal()!=0)
					tabbutton[indice].setForeground(maCouleur);
			}

	}

	/******************************
	 * Accesseur pour lire la grille à l'écran
	 * @return maGrille sous la forme d'une Grille
	 */
	public InterfaceGrille getGrille(){
		int i;
		String maGrille="";
		int tampon;
		for (i=0;i<tabbutton.length;i++){
			tampon=tabbutton[i].getText().charAt(0)-48;
			if (tampon==47)
				tampon=0;
			maGrille+=tampon;
		}
		return new Grille(maGrille);
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
				}
				else if (numero=='_'){
					numero = '1';
				}
				else
					numero++;
			}//clic avec le bouton droit
			else if (e.getButton()==3) {
				if (numero=='1'){
					numero='_';
				}
				else if (numero=='_'){
					numero = '9';
				}
				else
					numero--;
			} else //clic avec le bouton du milieu
				if (numero!='_'){
					numero='_';
				}

			//System.out.println("\n numBouton : " + numBouton + " numero : " + numero + " -48 = " + (((int)numero) - 48) );
			tabbutton[numBouton].setText("" + numero);
			int val;
			if (numero=='_')
				val = 0;
			else
				val = numero -48;
			monTab.setVal(indice2ij(numBouton), val);
			monAppli.actualiserIndice();
		}// pas de modif de la grille autorisée
	}

	private Coordonnee indice2ij(int indice){
		int i,j;
		//System.out.println("\n indice2ij - indice :" + indice  );
		i = (int) indice/InterfaceGrille.BORNE;
		j= indice - i * InterfaceGrille.BORNE;
		//System.out.println(" i : " + i +" j : " + j);
		Coordonnee c = new Coordonnee(i, j);
		return c;
	}

	private int ij2Indice(int i, int j){
		//System.out.println("\n indice2ij - i : " + i +" j : " + j+ "indice :" + j+i*InterfaceGrille.BORNE  );
		return j+i*InterfaceGrille.BORNE;
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
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if (monAppli!= null)
			if (enabled)
				monAppli.ajouterTitreContainer(GrilleSudoku.TXTTITRE);
			else
				monAppli.ajouterTitreContainer(GrilleSudoku.TXTTITREVER);
	}
}
