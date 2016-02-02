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
 * Construit un composant JPanel repr�sentant une grille de SUdoku.
 * Cette grille peut �tre initialis�e (ComposantGrilleSudoku(String chaine, boolean enabled) lors de la construction,
 * soit en utilisant setGrille(String maGrille) � tout moment).
 * Le composant g�re lui-m�me les �v�nements qui se produisent sur sa surface.
 * On peut modifier les valeurs de chaque case en cliquant simplement.
 * On peut d�sactiver la modification des valeurs.
 * */
@SuppressWarnings("serial")
public class ComposantGrilleSudoku extends JPanel implements MouseListener {
	//Bool�en pour v�rifier si la grille peut �tre modifi�e
	//private boolean enabled =false;

	// Constantes pour la mise en page de la grille
	private   int frmLargeur = 500; // fen�tre
	private   int frmHauteur = 350;
	private   int espBtnHauteur = 0;
	private   int ligLargeur = 8; // espace entre deux s�ries de 3 lignes
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
	/** Donn�es statistiques � suivre*/

//	Application Parent
	GrilleSudoku monAppli = null;
	/**
	 * @return le nombre d'indice figurant sur la grille visible � l'�cran
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
	 * Constructeur : Construit le composant avec les param�tres grille qui contient la grille
	 * et enabled qui autorise/interdit la modification de la grille
	 * @param grille sous la forme d'une chaine de caract�re constitu�e de 81 chiffres sans s�paration
	 * @param enabled est � vrai si l'on veut autoriser la modification de la grille, � faux sinon
	 * */
	public ComposantGrilleSudoku(GrilleSudoku a,InterfaceGrille grille, boolean enabled){
		super();
		//reference � l'appli parent
		monAppli=a;
		construireGrille();

		setGrille(grille);//initialise la grille destin�e � l'affichage
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
	 * Accesseur pour colorer une grille avec la couleur pass�e en param�tre
	 * @param maGrille sous la forme d'une Grille
	 * @param couleur : la couleur choisie pour les num�ros
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
	 * Accesseur pour lire la grille � l'�cran
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
		// cr�ation de la grille de bouton
		for (i=0;i<tabbutton.length;i++){
			//initialisation de la grille de bouton � partir de la grille pass�e en param�tre

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
	 * Gestion des �v�nements
	 * Teste si la modification est autoris�e
	 */
	public void mouseClicked(MouseEvent e){
		// aiguillage �venementiel
		// attention il n'y a pas de contr�le de la nature de l'�v�nement g�n�nr�
		if (isEnabled()) {// les modifications de la grille sont autoris�es
			int numBouton;
			char numero;
			JButton boutonClique;
			boutonClique= (JButton) e.getSource();//recherche du bouton cliqu�
			// ici on pourrait travailler directement avec le
			//bouton renvoy� par e.getSourve() cad boutonClique...	mais j'en ai besoin pour
			// faire �voluer ma grille miroir.
			numBouton= Integer.parseInt(boutonClique.getName());//num�ro du bouton cliqu�
			// fait �voluer l'affichage du bouton de 1
			// et revient � 0 apr�s 9
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
		}// pas de modif de la grille autoris�e
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
